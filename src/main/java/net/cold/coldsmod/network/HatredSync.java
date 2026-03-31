package net.cold.coldsmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class HatredSync {

    public static class HatredData {
        public static boolean HatredEligible = false;
    }

    public record HatredSyncPacket(boolean HatredEligible) {

        public static void encode(HatredSyncPacket msg, FriendlyByteBuf buf) {
            buf.writeBoolean(msg.HatredEligible);
        }

        public static HatredSyncPacket decode(FriendlyByteBuf buf) {
            return new HatredSyncPacket(buf.readBoolean());
        }

        public static void handle(HatredSyncPacket msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                HatredData.HatredEligible = msg.HatredEligible();
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
