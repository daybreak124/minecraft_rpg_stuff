package net.cold.coldsmod.capabilities_and_blessings.effects;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static net.cold.coldsmod.stat.AttributeApplier.applyModifier;
import static net.cold.coldsmod.stat.AttributeApplier.removeModifier;

public class Hawkeye extends MobEffect {
    public Hawkeye() {
        super(MobEffectCategory.NEUTRAL, 0x00000);
    }

    public static final UUID HAWKEYE_UUID = UUID.fromString("d5553476-1234-5254-5454-113215411111");

    @Override
    public void addAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        super.addAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);


        double dex = pLivingEntity.getAttributeValue(ModAttributes.DEX.get());
        double perc = pLivingEntity.getAttributeValue(ModAttributes.PERC.get());

        double scalingMultiplier = 1.0 + ((dex + perc * 0.5) / 100.0);
        int stacks = pAmplifier + 1;

        double finalPotency = (5.0 * stacks) * scalingMultiplier;
        double finalNockHaste = (11.0 * stacks) * scalingMultiplier;

        applyModifier(pLivingEntity, ModAttributes.PROJECTILE_POTENCY.get(), finalPotency, HAWKEYE_UUID);
        applyModifier(pLivingEntity, ModAttributes.NOCK_HASTE.get(), finalNockHaste, HAWKEYE_UUID);
    }

    @Override
    public void removeAttributeModifiers(@NotNull LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        super.removeAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);

        removeModifier(pLivingEntity, ModAttributes.NOCK_HASTE.get(), HAWKEYE_UUID);
        removeModifier(pLivingEntity, ModAttributes.PROJECTILE_POTENCY.get(), HAWKEYE_UUID);
    }
}