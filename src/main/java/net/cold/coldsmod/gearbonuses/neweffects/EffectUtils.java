package net.cold.coldsmod.gearbonuses.neweffects;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EffectUtils {

    public static void spawnComposterBurst(Player player) {
        if (player.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.COMPOSTER,
                    player.getX(), player.getY() + 1.0, player.getZ(),
                    25, 0.4, 0.5, 0.4, 0.05);
        }
    }

    public static void spawnExplosionEffect(Player player) {
        if (player.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.EXPLOSION,
                    player.getX(), player.getY() + 1.0, player.getZ(),
                    25, 0.4, 0.5, 0.4, 0.05);
        }
    }

    public static void spawnExplosionOnFeet(Player player) {
        if (player.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.EXPLOSION,
                    player.getX(), player.getY(), player.getZ(),
                    25, 0.4, 0.5, 0.4, 0.05);
        }
    }

    public static void spawnParticleRing(ServerLevel level, LivingEntity center, ParticleOptions particle, double radius, int count) {
        for (int i = 0; i < count; i++) {
            double angle = 2 * Math.PI * i / count;
            double x = center.getX() + (radius * Math.cos(angle));
            double z = center.getZ() + (radius * Math.sin(angle));
            double y = center.getY();

            level.sendParticles(particle, x, y, z, 1, 0, 0, 0, 0.0);
        }
    }

    public static void playHealSound(LivingEntity player) {
        player.level().playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.EXPERIENCE_ORB_PICKUP,
                SoundSource.PLAYERS,
                0.5F,
                1.0F
        );
    }

    public static void playExplosionSound(LivingEntity player) {
        player.level().playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.GENERIC_EXPLODE,
                SoundSource.PLAYERS,
                0.5F,
                1.0F
        );
    }

    public static void playSound(LivingEntity player, SoundEvent soundEvent, float volume, float pitch) {
        player.level().playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                soundEvent,
                SoundSource.PLAYERS,
                volume, pitch
        );
    }

    public static void spawnParticleBurst(Player player, ParticleOptions particleType) {
        if (player.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(particleType,
                    player.getX(), player.getY() + 1.0, player.getZ(),
                    25, 0.4, 0.5, 0.4, 0.05);
        }
    }

    @SubscribeEvent
    public static void onMobTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity().level().isClientSide || !(event.getEntity() instanceof Monster monster)) return;
        int freezeTimer = monster.getPersistentData().getInt("freeze_timer");

        if (freezeTimer > 0) {
            freezeTimer--;

            if (freezeTimer <= 0) {
                monster.setNoAi(false);
            }
            monster.getPersistentData().putInt("freeze_timer", freezeTimer);
        }
    }
}
