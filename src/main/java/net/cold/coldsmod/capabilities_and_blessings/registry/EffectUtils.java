package net.cold.coldsmod.capabilities_and_blessings.registry;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class EffectUtils {

    public static void spawnComposterBurst(LivingEntity ally) {
        if (ally.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.COMPOSTER,
                    ally.getX(), ally.getY() + 1.0, ally.getZ(),
                    25, 0.4, 0.5, 0.4, 0.05);
        }
    }

    public static void spawnExplosionEffect(LivingEntity player) {
        if (player.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.POOF,
                    player.getX(), player.getY() + 1.0, player.getZ(),
                    12, 0.4, 0.5, 0.4, 0.05);
        }
    }

    public static void spawnExplosionOnFeet(Player player) {
        if (player.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.POOF,
                    player.getX(), player.getY(), player.getZ(),
                    6, 0.4, 0.5, 0.4, 0.05);
        }
    }

    public static void spawnParticleRing(ServerLevel level, LivingEntity center, ParticleOptions particle, double radius, int count) {
        for (int i = 0; i < count; i++) {
            double angle = 2 * Math.PI * i / count;
            double x = center.getX() + (radius * Math.cos(angle));
            double z = center.getZ() + (radius * Math.sin(angle));
            double y = center.getY() + 0.2;

            level.sendParticles(particle, x, y, z, 1, 0, 0, 0, 0.0);
        }
    }

    public static void playHealSound(LivingEntity ally) {
        ally.level().playSound(
                null,
                ally.getX(),
                ally.getY(),
                ally.getZ(),
                SoundEvents.EXPERIENCE_ORB_PICKUP,
                SoundSource.PLAYERS,
                0.5F,
                1.0F
        );
    }

    public static void playExplosionSound(LivingEntity player, float volume) {
        player.level().playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.GENERIC_EXPLODE,
                SoundSource.PLAYERS,
                volume,
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

    public static void playSoundServer(Player player, SoundEvent soundEvent, float volume, float pitch) {
        player.level().playSound(
                player,
                player.getX(), player.getY(), player.getZ(),
                soundEvent,
                SoundSource.PLAYERS,
                volume, pitch
        );
    }


    public static void spawnParticleBurst(LivingEntity entity, ParticleOptions particleType) {
        if (entity.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(particleType,
                    entity.getX(), entity.getY() + 0.25, entity.getZ(),
                    10, 0.4, 0.5, 0.4, 0.05);
        }
    }

    public static void spawnParticleBurstLow(LivingEntity entity, ParticleOptions particleType) {
        if (entity.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(particleType,
                    entity.getX(), entity.getY()-0.4, entity.getZ(),
                    10, 0.4, 0.5, 0.4, 0.05);
        }
    }

    public static void spawnParticleBurstHigh(LivingEntity entity, ParticleOptions particleType) {
        if (entity.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(particleType,
                    entity.getX(), entity.getY() + 0.5, entity.getZ(),
                    10, 0.4, 0.5, 0.4, 0.05);
        }
    }

    public static void spawnParticleRing(ServerLevel level, Vec3 pos, ParticleOptions particle, double radius, int count) {
        for (int i = 0; i < count; i++) {
            double angle = 2 * Math.PI * i / count;
            double x = pos.x + (radius * Math.cos(angle));
            double z = pos.z + (radius * Math.sin(angle));
            double y = pos.y + 0.1;

            level.sendParticles(particle, x, y, z, 1, 0, 0, 0, 0.0);
        }
    }

    public static void spawnParticleRingHigh(ServerLevel level, LivingEntity center, ParticleOptions particle, double radius, int count) {
        for (int i = 0; i < count; i++) {
            double angle = 2 * Math.PI * i / count;
            double x = center.getX() + (radius * Math.cos(angle));
            double z = center.getZ() + (radius * Math.sin(angle));
            double y = center.getY() + 0.45;

            level.sendParticles(particle, x, y, z, 1, 0, 0, 0, 0.0);
        }
    }

    public static boolean isAlly(LivingEntity target) {
        return (target instanceof TamableAnimal t && t.isTame()) || (target instanceof Player);
    }

    public static void spawnExplosionAt(ServerLevel level, Vec3 pos) {
        level.sendParticles(ParticleTypes.EXPLOSION, pos.x, pos.y, pos.z, 6, 0.4, 0.5, 0.4, 0.05);
    }

    public static void playExplosionSoundAt(Level level, Vec3 pos, float volume) {
        level.playSound(null, pos.x, pos.y, pos.z,
                SoundEvents.GENERIC_EXPLODE,
                SoundSource.PLAYERS, volume, 1.0F);
    }
}
