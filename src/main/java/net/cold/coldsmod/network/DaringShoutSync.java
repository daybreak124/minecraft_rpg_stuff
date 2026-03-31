package net.cold.coldsmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DaringShoutSync {

    public static class DaringClientData {
        public static boolean DaringEligible = false;
    }

    public record DaringShoutSyncPacket(boolean DaringEligible) {

        public static void encode(DaringShoutSyncPacket msg, FriendlyByteBuf buf) {
            buf.writeBoolean(msg.DaringEligible);
        }

        public static DaringShoutSyncPacket decode(FriendlyByteBuf buf) {
            return new DaringShoutSyncPacket(buf.readBoolean());
        }

        public static void handle(DaringShoutSyncPacket msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                DaringClientData.DaringEligible = msg.DaringEligible();
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
