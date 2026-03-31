package net.cold.coldsmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DfaAirborneSync {

    public static class DfaAirborneClientData {
        public static boolean dfaAirborneEligible = false;
    }

    public record DfaAirborneFlagPacket(boolean dfaAirborneEligible) {

        public static void encode(DfaAirborneFlagPacket msg, FriendlyByteBuf buf) {
            buf.writeBoolean(msg.dfaAirborneEligible);
        }

        public static DfaAirborneFlagPacket decode(FriendlyByteBuf buf) {
            return new DfaAirborneFlagPacket(buf.readBoolean());
        }

        public static void handle(DfaAirborneFlagPacket msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                DfaAirborneClientData.dfaAirborneEligible = msg.dfaAirborneEligible;
            });
            ctx.get().setPacketHandled(true);
        }
    }
}