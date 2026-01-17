package net.cold.coldsmod.gearbonuses.skills;

import net.cold.coldsmod.damage.CustomMeleeDamage;
import net.cold.coldsmod.damage.ModDamageTypes;
import net.cold.coldsmod.gearbonuses.effects.ModEffects;
import net.cold.coldsmod.network.DFASync;
import net.cold.coldsmod.network.QuantumLeapSync;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class DeathFromAboveSkill {

    private static final double JUMP_BOOST = 1.1;
    private static final double JUMP_RADIUS = 5.0;
    private static final double LAND_RADIUS = 7.0;
    private static final float JUMP_DAMAGE = 5f;
    private static final float LAND_DAMAGE = 15f;

    @SubscribeEvent
    public static void onPlayerJump(LivingEvent.LivingJumpEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!player.hasEffect(ModEffects.DEATH_FROM_ABOVE.get())) return;
        if (player.isInWater()) return;
        if (player.isShiftKeyDown() && player.hasEffect(ModEffects.QUANTUM_LEAP_READY.get())) return;
        if (!(DFASync.DFAClientData.DFAEligible)) return;

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

        player.setDeltaMovement(motionX, JUMP_BOOST, motionZ);
        player.getPersistentData().putBoolean("DFA_Airborne", true);
        player.getPersistentData().putBoolean("DFA_fall_damage_cancel", true);


        Holder<DamageType> explosionType = level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(ModDamageTypes.EXPLOSION_DAMAGE);
        DamageSource source = new CustomMeleeDamage(explosionType, player);


        List<LivingEntity> jumpTargets = level.getEntitiesOfClass(
                LivingEntity.class,
                player.getBoundingBox().inflate(JUMP_RADIUS),
                e -> e != player && e.isAlive() && !e.isInvulnerable()
        );

        for (LivingEntity target : jumpTargets) {
            if (!(target instanceof Player)) {
                target.hurt(source, JUMP_DAMAGE);
            }
        }

        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 0.5f, 1.0f);

        player.removeEffect(ModEffects.DEATH_FROM_ABOVE.get());
        player.addEffect(new MobEffectInstance(ModEffects.DEATH_FROM_ABOVE_COOLDOWN.get(), 20 * 15, 0, false, false, true));
    }


    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;

        Level world = player.getCommandSenderWorld();
        if (world.isClientSide) return;


        if (!player.getPersistentData().getBoolean("DFA_Airborne")) return;

        if (player.onGround()) {
            player.getPersistentData().putBoolean("DFA_Airborne", false);

            Level level = player.level();
            DamageSource source = new CustomMeleeDamage(
                    level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
                            .getHolderOrThrow(ModDamageTypes.EXPLOSION_DAMAGE),
                    player
            );

            for (LivingEntity target : level.getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(LAND_RADIUS),
                    e -> e != player && e.isAlive() && !e.isInvulnerable()
            )) {
                if (!(target instanceof Player)) {
                    target.hurt(source, LAND_DAMAGE);
                }
            }

            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 0.6f, 1.0f);

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
        if (!player.getPersistentData().getBoolean("DFA_fall_damage_cancel")) return;
        event.setCanceled(true);
    }
}
