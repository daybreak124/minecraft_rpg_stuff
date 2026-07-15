package net.cold.coldsmod.capabilities_and_blessings.effects;

import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusCapabilityProvider;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.PlayerBonusCache;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class DeathFromAboveSynergy extends MobEffect {
    public DeathFromAboveSynergy() {
        super(MobEffectCategory.NEUTRAL, 0x00000);
    }

    // ENHANCED QUANTUM LEAP

    @Override
    public void addAttributeModifiers(@NotNull LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        PlayerBonusCache cache = pLivingEntity.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
        cache.setDfaQuantumSynergized(true);
    }

    @Override
    public void removeAttributeModifiers(@NotNull LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        PlayerBonusCache cache = pLivingEntity.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
        cache.setDfaQuantumSynergized(false);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}