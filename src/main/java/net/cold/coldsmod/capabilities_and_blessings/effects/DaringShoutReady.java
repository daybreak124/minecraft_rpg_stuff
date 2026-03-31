package net.cold.coldsmod.capabilities_and_blessings.effects;

import net.cold.coldsmod.ModMessages;
import net.cold.coldsmod.network.DaringShoutSync;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import org.jetbrains.annotations.NotNull;

public class DaringShoutReady extends MobEffect {
    public DaringShoutReady() {
        super(MobEffectCategory.NEUTRAL, 0x00000);
    }

    @Override
    public void addAttributeModifiers(@NotNull LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        if (pLivingEntity instanceof ServerPlayer serverPlayer) {
            ModMessages.sendToPlayer(new DaringShoutSync.DaringShoutSyncPacket(true), serverPlayer);
        }
    }

    @Override
    public void removeAttributeModifiers(@NotNull LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        if (pLivingEntity instanceof ServerPlayer serverPlayer) {
            ModMessages.sendToPlayer(new DaringShoutSync.DaringShoutSyncPacket(false), serverPlayer);
        }
    }
}