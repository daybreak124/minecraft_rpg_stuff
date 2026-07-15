package net.cold.coldsmod.capabilities_and_blessings.effects;

import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusCapabilityProvider;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.PlayerBonusCache;
import net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils;
import net.cold.coldsmod.capabilities_and_blessings.registry.ModEffects;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils.isAlly;
import static net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils.spawnParticleRing;
import static net.cold.coldsmod.stat.AttributeApplier.getScaledValue;

public class SanctuaryShared extends MobEffect {

    private static final UUID SANCTUARY_UUID = UUID.fromString("f3e2b3c0-1738-5123-ab23-024031060446");

    public SanctuaryShared() {
        super(MobEffectCategory.BENEFICIAL, 0x800080);

        this.addAttributeModifier(ModAttributes.INCOMING_DAMAGE_MULTIPLIER.get(),
                SANCTUARY_UUID.toString(), -0.1D, AttributeModifier.Operation.MULTIPLY_BASE);
    }

    private static final float BASE_HEAL = 1f;
    private static final float FATIGUE_REDUCTION_PER_STACK = 0.2f;

    public static void performDivinityPulse(Player source) {
        Level level = source.level();
        double radius = 6.0 * (1.0 + (AttributeApplier.getScaledValue(source, ModAttributes.AMPLIFICATION.get()) / 100.0));
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
        int duration = 20;

        PlayerBonusCache cache = source.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
        if (cache.isSanctuaryEnhance()) duration += 100;

        double healIncrease = getScaledValue(source,
                ModAttributes.RESTORATION.get());

        float reduction = stacks * FATIGUE_REDUCTION_PER_STACK;
        reduction = Math.min(reduction, 0.8f);
        float finalHeal = (float) (BASE_HEAL * ((1 + (healIncrease/100))) * (1f - reduction));

        target.heal(finalHeal);

        target.addEffect(new MobEffectInstance(ModEffects.SANCTUARY_FATIGUE.get(), 240, stacks, false, false, true));
        target.addEffect(new MobEffectInstance(ModEffects.SANCTUARY_SHARED.get(), 20, duration, false, false, true));
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}