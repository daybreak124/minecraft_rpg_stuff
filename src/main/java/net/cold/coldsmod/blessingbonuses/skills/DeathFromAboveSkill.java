package net.cold.coldsmod.blessingbonuses.skills;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils;
import net.cold.coldsmod.damage.CustomMeleeDamage;
import net.cold.coldsmod.damage.ModDamageTypes;
import net.cold.coldsmod.network.DFASync;
import net.cold.coldsmod.network.QuantumLeapSync;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

import static net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils.*;

public class DeathFromAboveSkill {

    private static double JUMP_BOOST = 1.1;
    private static final double JUMP_RADIUS = 5.0;
    private static final double LAND_RADIUS = 7.0;
    private static final float JUMP_DAMAGE = 7.5f;
    private static final float LAND_DAMAGE = 20f;

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onPlayerJump(LivingEvent.LivingJumpEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!(DFASync.DFAClientData.DFAEligible)) return;
        if (!player.hasEffect(ModEffects.DEATH_FROM_ABOVE.get())) return;
        if (player.hasEffect(ModEffects.QUANTUM_LEAP_READY.get()) && player.isShiftKeyDown()) return;
        if (player.isInWater()) return;

        Level level = player.level();

        double motionX = player.getDeltaMovement().x;
        double motionZ = player.getDeltaMovement().z;

        if (Math.abs(motionX) < 0.01 && Math.abs(motionZ) < 0.01) {
            motionX = 0;
            motionZ = 0;
        } else {
            double dashMultiplier = 2.5;
            motionX *= dashMultiplier;
            motionZ *= dashMultiplier;
        }

        if (player.isShiftKeyDown()) JUMP_BOOST = 0;
        player.setDeltaMovement(motionX, JUMP_BOOST, motionZ);
        player.hurtMarked = true;
        player.getPersistentData().putBoolean("DFA_Airborne", true);
        player.getPersistentData().putBoolean("DFA_fall_damage_cancel", true);


        Holder<DamageType> explosionType = level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(ModDamageTypes.EXPLOSION_DAMAGE);
        DamageSource source = new CustomMeleeDamage(explosionType, player);


        double jumpRadiusSq = 25;

        List<LivingEntity> jumpTargets = level.getEntitiesOfClass(
                LivingEntity.class,
                player.getBoundingBox().inflate(JUMP_RADIUS),
                e -> {
                    if (!(e instanceof Enemy) || e.isInvulnerable() || !player.hasLineOfSight(e)) return false;

                    double dx = e.getX() - player.getX();
                    double dz = e.getZ() - player.getZ();
                    return (dx * dx + dz * dz) <= jumpRadiusSq;
                }
        );

        for (LivingEntity target : jumpTargets) {target.hurt(source, JUMP_DAMAGE);}

        EffectUtils.playExplosionSound(player, 0.5F);
        EffectUtils.spawnExplosionOnFeet(player);
        if (level instanceof ServerLevel serverLevel) {
            spawnParticleRing(serverLevel, player, ParticleTypes.POOF, LAND_RADIUS, 140);
        }

        player.removeEffect(ModEffects.DEATH_FROM_ABOVE.get());
        player.addEffect(new MobEffectInstance(ModEffects.DEATH_FROM_ABOVE_COOLDOWN.get(), 20 * 15, 0, false, false, true));
    }


    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (event.player.level().isClientSide()) return;

        Player player = event.player;

        if (!player.getPersistentData().getBoolean("DFA_Airborne")) return;

        if (player.onGround()) {
            player.getPersistentData().putBoolean("DFA_Airborne", false);

            Level level = player.level();
            DamageSource source = new CustomMeleeDamage(
                    level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
                            .getHolderOrThrow(ModDamageTypes.EXPLOSION_DAMAGE),
                    player
            );

            if (player.level() instanceof ServerLevel serverLevel) {
                spawnParticleRing(serverLevel, player, ParticleTypes.POOF, LAND_RADIUS, 140);
                spawnExplosionOnFeet(player);
                playExplosionSound(player, 0.6F);
            }

            double landRadiusSq = 49;

            List<LivingEntity> jumpTargets = level.getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(LAND_RADIUS),
                    e -> {
                        if (!(e instanceof Enemy) || e.isInvulnerable() || !player.hasLineOfSight(e)) return false;
                        double dx = e.getX() - player.getX();
                        double dz = e.getZ() - player.getZ();

                        return (dx * dx + dz * dz) <= landRadiusSq;
                    }
            );

            for (LivingEntity target : jumpTargets) {target.hurt(source, LAND_DAMAGE);}

            if (player.hasEffect(ModEffects.QUANTUM_LEAP_COOLDOWN.get())) {
                int duration = Math.max(0, player.getEffect(ModEffects.QUANTUM_LEAP_COOLDOWN.get()).getDuration() - 20 * 5);
                player.removeEffect(ModEffects.QUANTUM_LEAP_COOLDOWN.get());
                player.addEffect(new MobEffectInstance(ModEffects.QUANTUM_LEAP_COOLDOWN.get(), duration, 0, false, false, true));
            }
            if (QuantumLeapSync.QuantumLeapClientData.quantumLeapEligible)
                player.addEffect(new MobEffectInstance(ModEffects.ENHANCED_QUANTUM_LEAP.get(), 20 * 4, 0, false, false, true));
        }
    }

    @SubscribeEvent
    public static void onPlayerLand(LivingFallEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.level().isClientSide()) return;
        if (!player.getPersistentData().getBoolean("DFA_fall_damage_cancel")) return;
        event.setCanceled(true);
    }
}
