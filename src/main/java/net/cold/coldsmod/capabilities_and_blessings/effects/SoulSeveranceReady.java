package net.cold.coldsmod.capabilities_and_blessings.effects;

import net.cold.coldsmod.ModMessages;
import net.cold.coldsmod.network.SeveranceSync;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class SoulSeveranceReady extends MobEffect {
    public SoulSeveranceReady() {
        super(MobEffectCategory.NEUTRAL, 0x00000);
    }

    @Override
    public void addAttributeModifiers(@NotNull LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        if (pLivingEntity instanceof ServerPlayer serverPlayer) {
            ModMessages.sendToPlayer(new SeveranceSync.SeveranceFlagPacket(true), serverPlayer);
        }
    }

    @Override
    public void removeAttributeModifiers(@NotNull LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        if (pLivingEntity instanceof ServerPlayer serverPlayer) {
            ModMessages.sendToPlayer(new SeveranceSync.SeveranceFlagPacket(false), serverPlayer);
        }
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}