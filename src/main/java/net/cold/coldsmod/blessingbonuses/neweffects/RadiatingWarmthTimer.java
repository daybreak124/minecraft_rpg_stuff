package net.cold.coldsmod.blessingbonuses.neweffects;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;

import static net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils.isAlly;
import static net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils.spawnParticleRing;
import static net.cold.coldsmod.stat.AttributeApplier.getScaledValue;

public class RadiatingWarmthTimer extends MobEffect {

    public RadiatingWarmthTimer() {
        super(MobEffectCategory.NEUTRAL, 0xFF0000); // category + color
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false; // only trigger via events
    }


    public static void radiate(Player player) {
        if (player.level().isClientSide) return;

        double healIncrease = getScaledValue(player,
                ModAttributes.RESTORATION.get(),
                ModAttributes.RESTORATION_MULTIPLIER.get());

        Level level = player.level();

        if (level instanceof ServerLevel serverLevel) {
            spawnParticleRing(serverLevel, player, ParticleTypes.COMPOSTER, 8, 8*20);
        }

        double radiusSq = 64.0;
        List<LivingEntity> entities = level.getEntitiesOfClass(
                LivingEntity.class,
                player.getBoundingBox().inflate(8.0),
                e -> {
                    if (!isAlly(e) || !player.hasLineOfSight(e)) return false;
                    double dx = e.getX() - player.getX();
                    double dz = e.getZ() - player.getZ();
                    return (dx * dx + dz * dz) <= radiusSq;
                }
        );

        // player.heal((float) (1.25 * (1.0 + (healIncrease / 100.0))));

        // EffectUtils.spawnComposterBurst(player);

        for (LivingEntity target : entities) {

            target.heal((float) (1.25 * (1.0 + (healIncrease / 100.0))));
            EffectUtils.playHealSound(target);
            EffectUtils.spawnComposterBurst(target);
        }
    }
}