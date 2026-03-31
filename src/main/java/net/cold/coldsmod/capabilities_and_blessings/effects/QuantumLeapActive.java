package net.cold.coldsmod.capabilities_and_blessings.effects;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static net.cold.coldsmod.stat.AttributeApplier.removeModifier;

public class QuantumLeapActive extends MobEffect {
    public QuantumLeapActive() {
        super(MobEffectCategory.NEUTRAL, 0x00000);
    }

    public static final UUID QUANTUM_UUID = UUID.fromString("a3e113c0-2128-5123-2223-024532550446");

    // QUANTUM LEAP PACKET

    @Override
    public void removeAttributeModifiers(@NotNull LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        removeModifier(pLivingEntity, ModAttributes.POTENCY.get(), QUANTUM_UUID);
        removeModifier(pLivingEntity, ModAttributes.MELEE_POTENCY.get(), QUANTUM_UUID);
        removeModifier(pLivingEntity, ModAttributes.PROJECTILE_POTENCY.get(), QUANTUM_UUID);
        removeModifier(pLivingEntity, Attributes.MOVEMENT_SPEED, QUANTUM_UUID);
    }
}