package net.cold.coldsmod.blessingbonuses.neweffects;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

import static net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils.isAlly;
import static net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils.spawnParticleRing;
import static net.cold.coldsmod.stat.AttributeApplier.getScaledValue;

public class Sanctuary {

    public static final int CHANNEL_DELAY = 20; // 1s
    public static final int PULSE_INTERVAL = 20; // 1s
    public static final float BASE_HEAL = 1.25f;
    public static final float FATIGUE_REDUCTION_PER_STACK = 0.2f;

    public static final String BLOCK_TICKS = "divinity_block_ticks";
    public static final String PULSE_TICKS = "divinity_pulse_ticks";

    public static void performDivinityPulse(Player source) {
        Level level = source.level();
        double radius = 6.0 * (1.0 + (AttributeApplier.getScaledValue(source, ModAttributes.AMPLIFICATION.get(), ModAttributes.AMPLIFICATION_MULTIPLIER.get()) / 100.0));
        AABB area = source.getBoundingBox().inflate(radius);

        if (level instanceof ServerLevel serverLevel) {
            spawnParticleRing(serverLevel, source, ParticleTypes.COMPOSTER, radius, (int) (radius*20));
        }

        double radiusSq = radius * radius;
        List<LivingEntity> allies = level.getEntitiesOfClass(
                LivingEntity.class,
                area,
                e -> {
                    if (!isAlly(e) || !source.hasLineOfSight(e)) return false;
                    double dx = e.getX() - source.getX();
                    double dz = e.getZ() - source.getZ();
                    return (dx * dx + dz * dz) <= radiusSq;
                }
        );


        for (LivingEntity ally : allies) {
            applyDivinityHealing(source, ally);
            EffectUtils.spawnComposterBurst(ally);
            EffectUtils.playHealSound(ally);
        }
    }

    private static void applyDivinityHealing(Player source, LivingEntity target) {
        MobEffectInstance fatigue = target.getEffect(ModEffects.SANCTUARY_FATIGUE.get());
        int stacks = fatigue != null ? fatigue.getAmplifier() + 1 : 0;

        double healIncrease = getScaledValue(source,
                ModAttributes.RESTORATION.get(),
                ModAttributes.RESTORATION_MULTIPLIER.get());

        float reduction = stacks * FATIGUE_REDUCTION_PER_STACK;
        reduction = Math.min(reduction, 0.8f);
        float finalHeal = (float) (BASE_HEAL * ((1 + (healIncrease/100))) * (1f - reduction));

        target.heal(finalHeal);

        target.addEffect(new MobEffectInstance(ModEffects.SANCTUARY_FATIGUE.get(), 240, stacks, false, false, true));
        target.addEffect(new MobEffectInstance(ModEffects.SANCTUARY_SHARED.get(), 20, 0, false, false, true));
    }
}
