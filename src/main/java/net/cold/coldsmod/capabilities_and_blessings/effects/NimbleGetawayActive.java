package net.cold.coldsmod.capabilities_and_blessings.effects;

import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusCapabilityProvider;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.PlayerBonusCache;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class NimbleGetawayActive extends MobEffect {

    public static final UUID NIMBLE_UUID = UUID.fromString("a3e2b3c0-1758-5cc3-ad23-02bb77060446");

    public NimbleGetawayActive() {
        super(MobEffectCategory.NEUTRAL, 0x00000);

        this.addAttributeModifier(ModAttributes.EVASION.get(),
                NIMBLE_UUID.toString(),
                1.0D,
                AttributeModifier.Operation.ADDITION);
    }

    @Override
    public void addAttributeModifiers(@NotNull LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        super.addAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
        PlayerBonusCache cache = pLivingEntity.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
        cache.setEvadeActive(true);
    }

    @Override
    public void removeAttributeModifiers(@NotNull LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        super.removeAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
        PlayerBonusCache cache = pLivingEntity.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
        cache.setEvadeActive(false);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}