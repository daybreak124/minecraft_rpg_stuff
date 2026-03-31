package net.cold.coldsmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class OverconfidenceSync {

    public static class OverconfidenceData {
        public static boolean OverconfidenceEligible = false;
    }

    public record OverconfidenceSyncPacket(boolean OverconfidenceEligible) {

        public static void encode(OverconfidenceSyncPacket msg, FriendlyByteBuf buf) {
            buf.writeBoolean(msg.OverconfidenceEligible);
        }

        public static OverconfidenceSyncPacket decode(FriendlyByteBuf buf) {
            return new OverconfidenceSyncPacket(buf.readBoolean());
        }

        public static void handle(OverconfidenceSyncPacket msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                OverconfidenceData.OverconfidenceEligible = msg.OverconfidenceEligible();
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
