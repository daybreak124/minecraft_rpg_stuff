package net.cold.coldsmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class QuantumLeapSync {

    public static class QuantumLeapClientData {
        public static boolean quantumLeapEligible = false;
    }

    public record QuantumLeapFlagPacket(boolean quantumLeapEligible) {

        public static void encode(QuantumLeapFlagPacket msg, FriendlyByteBuf buf) {
            buf.writeBoolean(msg.quantumLeapEligible);
        }

        public static QuantumLeapFlagPacket decode(FriendlyByteBuf buf) {
            return new QuantumLeapFlagPacket(buf.readBoolean());
        }

        public static void handle(QuantumLeapFlagPacket msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                QuantumLeapClientData.quantumLeapEligible = msg.quantumLeapEligible;
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
