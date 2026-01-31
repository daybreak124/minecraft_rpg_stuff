package net.cold.coldsmod.network;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

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

            if (player.hasEffect(ModEffects.OVERCONFIDENCE_READY.get())) {

                player.playNotifySound(
                        SoundEvents.ENDER_DRAGON_GROWL, SoundSource.PLAYERS,
                        0.3F, 1.0F);

                player.removeEffect(ModEffects.OVERCONFIDENCE_READY.get());

                player.addEffect(new MobEffectInstance(ModEffects.BLACKENED_HEART.get(), 300, 0, false, false, true));

                player.addEffect(new MobEffectInstance(ModEffects.OVERCONFIDENCE_ACTIVE.get(), 160, 0, false, false, true));
            }
        });
        return true;
    }
}