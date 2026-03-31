package net.cold.coldsmod.capabilities_and_blessings.effects;

import net.cold.coldsmod.ModMessages;
import net.cold.coldsmod.network.IntimidatingPresenceSync;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import org.jetbrains.annotations.NotNull;

public class IntimidatingPresenceReady extends MobEffect {
    public IntimidatingPresenceReady() {
        super(MobEffectCategory.NEUTRAL, 0x00000);
    }

    @Override
    public void addAttributeModifiers(@NotNull LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        if (pLivingEntity instanceof ServerPlayer serverPlayer) {
            ModMessages.sendToPlayer(new IntimidatingPresenceSync.IntimidatingPresenceFlagPacket(true), serverPlayer);
        }
    }

    @Override
    public void removeAttributeModifiers(@NotNull LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        if (pLivingEntity instanceof ServerPlayer serverPlayer) {
            ModMessages.sendToPlayer(new IntimidatingPresenceSync.IntimidatingPresenceFlagPacket(false), serverPlayer);
        }
    }
}