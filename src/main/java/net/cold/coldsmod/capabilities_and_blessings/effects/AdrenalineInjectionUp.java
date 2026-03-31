package net.cold.coldsmod.capabilities_and_blessings.effects;

import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusCapabilityProvider;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.PlayerBonusCache;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class AdrenalineInjectionUp extends MobEffect {
    public AdrenalineInjectionUp() {
        super(MobEffectCategory.NEUTRAL, 0x00000);
    }

    @Override
    public void addAttributeModifiers(@NotNull LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        PlayerBonusCache cache = pLivingEntity.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
        cache.setInjection(true);
        addCrossbowNBT(pLivingEntity);
    }

    public static void addCrossbowNBT(LivingEntity entity) {
        ItemStack mainHand = entity.getMainHandItem();
        ItemStack offHand = entity.getOffhandItem();

        if (mainHand.getItem() instanceof CrossbowItem) {
            mainHand.getOrCreateTag().putBoolean("adr", true);
        }

        if (offHand.getItem() instanceof CrossbowItem) {
            offHand.getOrCreateTag().putBoolean("adr", true);
        }
    }


    public static void removeCrossbowNBT(LivingEntity entity) {
        ItemStack mainHand = entity.getMainHandItem();
        ItemStack offHand = entity.getOffhandItem();
        if (mainHand.getItem() instanceof CrossbowItem) {
            mainHand.getOrCreateTag().remove("adr");
        }


        if (offHand.getItem() instanceof CrossbowItem) {
            offHand.getOrCreateTag().remove("adr");
        }
    }
}