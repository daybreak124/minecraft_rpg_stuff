package net.cold.coldsmod.gearbonuses.effects;

import net.cold.coldsmod.network.QuantumLeapSync;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class QuantumLeapActive extends MobEffect {

    public QuantumLeapActive() {
        super(MobEffectCategory.NEUTRAL, 0xFFD700); // gold color
    }

    @SubscribeEvent
    public static void onPlayerJump(LivingEvent.LivingJumpEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!player.hasEffect(ModEffects.QUANTUM_LEAP_READY.get())) return;
        if (!player.isShiftKeyDown()) return;
        if (!(QuantumLeapSync.QuantumLeapClientData.quantumLeapEligible)) return;

        Vec3 look = player.getLookAngle();
        double dashDistance = 2.5;
        player.setDeltaMovement(look.x * dashDistance, 0.75, look.z * dashDistance);
        player.addEffect(new MobEffectInstance(ModEffects.QUANTUM_LEAP_COOLDOWN.get(), 20*35, 0, false, false));


        if (player.hasEffect(ModEffects.ENHANCED_QUANTUM_LEAP.get())) {
            player.addEffect(new MobEffectInstance(ModEffects.QUANTUM_LEAP_ACTIVE.get(), 120, 0, false, false));
            player.addEffect(new MobEffectInstance(ModEffects.ENHANCED_QUANTUM_LEAP.get(), 20*4, 0, false, false));
        } else {
            player.addEffect(new MobEffectInstance(ModEffects.QUANTUM_LEAP_ACTIVE.get(), 80, 0, false, false));
        }

        player.removeEffect(ModEffects.QUANTUM_LEAP_READY.get());
        player.getPersistentData().putBoolean("quantum_leaped", true);

        // Play sound once
        player.level().playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.PLAYER_SPLASH_HIGH_SPEED,
                SoundSource.PLAYERS,
                0.6F,
                1.0F
        );
    }


    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        if (!player.getPersistentData().getBoolean("quantum_leap_eligible")) return;
        if (!player.getPersistentData().getBoolean("quantum_leaped")) return;

        int invisDuration = 20*4;
        int leapActiveDuration = 20*6;

        if (player.hasEffect(ModEffects.ENHANCED_QUANTUM_LEAP.get())) { invisDuration = 20*6; leapActiveDuration = 20*9; }

        if (player.onGround() && player.hasEffect(ModEffects.QUANTUM_LEAP_ACTIVE.get()) && !player.hasEffect(MobEffects.INVISIBILITY)) {
            player.addEffect(new MobEffectInstance(ModEffects.QUANTUM_LEAP_ACTIVE.get(), leapActiveDuration, 0, false, true));
            player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, invisDuration, 0, false, true));

            player.getPersistentData().remove("quantum_leaped");

            List<LivingEntity> nearby = player.level().getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(10),
                    e -> (e instanceof Monster)
            );

            for (LivingEntity entity : nearby) {
                if (entity instanceof Mob) {
                    ((Mob) entity).setTarget(null);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerAttack(LivingAttackEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!player.getPersistentData().getBoolean("quantum_leap_eligible")) return;
        if (!player.hasEffect(ModEffects.QUANTUM_LEAP_ACTIVE.get())) return;

        player.removeEffect(MobEffects.INVISIBILITY);
        player.removeEffect(ModEffects.QUANTUM_LEAP_ACTIVE.get());
    }
}
