package net.cold.coldsmod.network;

import net.cold.coldsmod.blessingbonuses.neweffects.CombatantsAidReady;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CombatantsRecallPacket {
    public CombatantsRecallPacket() {}

    public static CombatantsRecallPacket decode(FriendlyByteBuf buf) { return new CombatantsRecallPacket(); }

    public void encode(FriendlyByteBuf buf) {}

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;
            CombatantsAidReady.returnToOrigin(player);
        });
        ctx.get().setPacketHandled(true);
    }
}