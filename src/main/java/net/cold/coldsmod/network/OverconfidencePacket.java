package net.cold.coldsmod.network;

import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusCapabilityProvider;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusRegistry;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusTrigger;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.PlayerBonusCache;
import net.cold.coldsmod.capabilities_and_blessings.registry.ModEffects;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

import static net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils.isAlly;

public class OverconfidencePacket {

    public OverconfidencePacket() {}

    public OverconfidencePacket(FriendlyByteBuf buf) {}

    public void encode(FriendlyByteBuf buf) {}
    public static OverconfidencePacket decode(FriendlyByteBuf buf) { return new OverconfidencePacket(); }


    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;


            player.playNotifySound(
                    SoundEvents.ENDER_DRAGON_GROWL, SoundSource.PLAYERS,
                    0.3F, 1.0F);

            BonusRegistry.process(player, null, player.level(), BonusTrigger.BLESSING_ACTIVATION);
            player.removeEffect(ModEffects.OVERCONFIDENCE_READY.get());
            player.addEffect(new MobEffectInstance(ModEffects.BLACKENED_HEART.get(), 300, 0, false, false, true));
            player.addEffect(new MobEffectInstance(ModEffects.OVERCONFIDENCE_ACTIVE.get(), 160, 0, false, false, true));


            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
            if (cache.isOverconfidenceHealSpecBuff()) {
                double radiusSq = 5d;
                List<LivingEntity> entities = player.level().getEntitiesOfClass(
                        LivingEntity.class,
                        player.getBoundingBox().inflate(25d),
                        e -> {
                            if (!isAlly(e) || !player.hasLineOfSight(e) || e == player) return false;
                            double dx = e.getX() - player.getX();
                            double dz = e.getZ() - player.getZ();
                            return (dx * dx + dz * dz) <= radiusSq;
                        }
                );

                for (LivingEntity target : entities) {
                    target.addEffect(new MobEffectInstance(ModEffects.OVERCONFIDENCE_BUFF.get(), 80, 0, false, false, false));
                }
            }
        });
        return true;
    }
}