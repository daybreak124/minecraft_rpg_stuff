package net.cold.coldsmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SeveranceSync {

    public static class SeveranceClientData {
        public static boolean severanceEligible = false;
    }

    public record SeveranceFlagPacket(boolean severanceEligible) {

        public static void encode(SeveranceFlagPacket msg, FriendlyByteBuf buf) {
            buf.writeBoolean(msg.severanceEligible);
        }

        public static SeveranceFlagPacket decode(FriendlyByteBuf buf) {
            return new SeveranceFlagPacket(buf.readBoolean());
        }

        public static void handle(SeveranceFlagPacket msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                SeveranceClientData.severanceEligible = msg.severanceEligible;
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
