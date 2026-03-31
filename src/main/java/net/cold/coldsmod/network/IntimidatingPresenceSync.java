package net.cold.coldsmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class IntimidatingPresenceSync {

    public static class IntimidatingSync {
        public static boolean IntimidatingPresenceEligible = false;
    }

    public record IntimidatingPresenceFlagPacket(boolean IntimidatingPresenceEligible) {

        public static void encode(IntimidatingPresenceFlagPacket msg, FriendlyByteBuf buf) {
            buf.writeBoolean(msg.IntimidatingPresenceEligible);
        }

        public static IntimidatingPresenceFlagPacket decode(FriendlyByteBuf buf) {
            return new IntimidatingPresenceFlagPacket(buf.readBoolean());
        }

        public static void handle(IntimidatingPresenceFlagPacket msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                IntimidatingSync.IntimidatingPresenceEligible = msg.IntimidatingPresenceEligible();
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
