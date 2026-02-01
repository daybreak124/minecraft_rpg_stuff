package net.cold.coldsmod.network;

import net.cold.coldsmod.blessingbonuses.effects.QuantumLeapActive;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class QuantumLeapPacket {
    public QuantumLeapPacket() {}

    public static QuantumLeapPacket decode(FriendlyByteBuf buf) {
        return new QuantumLeapPacket();
    }

    public void encode(FriendlyByteBuf buf) {
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            QuantumLeapActive.performDash(player);
        });
        ctx.get().setPacketHandled(true);
    }
}