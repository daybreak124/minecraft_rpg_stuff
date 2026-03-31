package net.cold.coldsmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DFASync {

    public static class DFAClientData {
        public static boolean DFAEligible = false;
    }

    public record DFAFlagPacket(boolean DFAEligible) {

        public static void encode(DFAFlagPacket msg, FriendlyByteBuf buf) {
            buf.writeBoolean(msg.DFAEligible);
        }

        public static DFAFlagPacket decode(FriendlyByteBuf buf) {
            return new DFAFlagPacket(buf.readBoolean());
        }

        public static void handle(DFAFlagPacket msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                DFAClientData.DFAEligible = msg.DFAEligible();
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
