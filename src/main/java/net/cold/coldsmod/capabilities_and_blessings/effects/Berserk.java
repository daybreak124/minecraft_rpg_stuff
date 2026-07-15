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

public class Berserk extends MobEffect {
    public Berserk() {
        super(MobEffectCategory.NEUTRAL, 0x00000);
    }

    @Override
    public void addAttributeModifiers(@NotNull LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        PlayerBonusCache cache = pLivingEntity.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
        cache.setBerserkStage1(true);
        cache.setBerserkStage0(false);
    }

    @Override
    public void removeAttributeModifiers(@NotNull LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        PlayerBonusCache cache = pLivingEntity.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
        cache.setBerserkStage1(false);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}