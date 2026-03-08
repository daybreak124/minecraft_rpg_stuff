package net.cold.coldsmod.blessingbonuses.skills;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.damage.ModDamageTypes;
import net.cold.coldsmod.network.QuantumLeapSync;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

import static net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils.*;

public class DeathFromAboveSkill {

    private static final double LAND_RADIUS = 7.0;
    private static final ResourceKey<DamageType> MELEE_DAMAGE_KEY =
            ResourceKey.create(Registries.DAMAGE_TYPE, ModDamageTypes.CUSTOM_MELEE_DAMAGE.location());

    @SubscribeEvent
    public static void onPlayerLand(LivingFallEvent event) {
        if (!(event.getEntity() instanceof Player player) || player.level().isClientSide()) return;
        if (!player.getPersistentData().getBoolean("DFA_fall_damage_cancel")) return;

        event.setCanceled(true);
        player.getPersistentData().putBoolean("DFA_Airborne", false);
        player.getPersistentData().putBoolean("DFA_fall_damage_cancel", false);

        Level level = player.level();

        Holder<DamageType> meleeType = level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(MELEE_DAMAGE_KEY);
        DamageSource source = new DamageSource(meleeType, null, player);

        if (level instanceof ServerLevel serverLevel) {
            spawnParticleRing(serverLevel, player, ParticleTypes.POOF, LAND_RADIUS, 140);
            spawnExplosionOnFeet(player);
            playExplosionSound(player, 0.6F);
        }

        double landRadiusSq = 49;
        List<LivingEntity> jumpTargets = level.getEntitiesOfClass(
                LivingEntity.class,
                player.getBoundingBox().inflate(LAND_RADIUS),
                e -> {
                    if (e.isInvulnerable() || !player.hasLineOfSight(e) || !((e instanceof Enemy && !(e instanceof NeutralMob)) || (e instanceof NeutralMob n && n.isAngry()) || (e instanceof Mob m && m.getTarget() != null))) return false;
                    return e.distanceToSqr(player) <= landRadiusSq;
                }
        );

        float dfaDamage = player.getPersistentData().getFloat("dfaFallDamage");
        for (LivingEntity target : jumpTargets) {
            target.hurt(source, dfaDamage);
            if (dfaDamage > 10.0f && !target.getType().is(Tags.EntityTypes.BOSSES)) {
                target.setDeltaMovement(target.getDeltaMovement().x, 0.8, target.getDeltaMovement().z);
                target.hurtMarked = true;
            }
        }

        player.hurtMarked = true;

        player.getPersistentData().putFloat("dfaFallDamage", 0);
        handleQuantumLeapSynergy(player);
    }

    private static void handleQuantumLeapSynergy(Player player) {
        if (player.hasEffect(ModEffects.QUANTUM_LEAP_COOLDOWN.get())) {
            MobEffectInstance current = player.getEffect(ModEffects.QUANTUM_LEAP_COOLDOWN.get());
            // avoid crash in some cases
            if (current != null) {
                int duration = Math.max(0, current.getDuration() - (20 * 5));
                player.removeEffect(ModEffects.QUANTUM_LEAP_COOLDOWN.get());
                if (duration > 0) {
                    player.addEffect(new MobEffectInstance(ModEffects.QUANTUM_LEAP_COOLDOWN.get(), duration, 0, false, false, true));
                }
            }
        }

        if (QuantumLeapSync.QuantumLeapClientData.quantumLeapEligible) {
            player.addEffect(new MobEffectInstance(ModEffects.ENHANCED_QUANTUM_LEAP.get(), 20 * 4, 0, false, false, true));
        }
    }
}