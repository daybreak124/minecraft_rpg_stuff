package net.cold.coldsmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

//public class SolaraSync {

//    // Client-side cache for Solara flag
//    public static class SolaraClientData {
//        public static boolean solaraEligible = false;
//    }
//
//    // Packet to sync Solara eligibility
//    public record SolaraFlagPacket(boolean solaraEligible) {
//
//        public static void encode(SolaraFlagPacket msg, FriendlyByteBuf buf) {
//            buf.writeBoolean(msg.solaraEligible());
//        }
//
//        public static SolaraFlagPacket decode(FriendlyByteBuf buf) {
//            return new SolaraFlagPacket(buf.readBoolean());
//        }
//
//        public static void handle(SolaraFlagPacket msg, Supplier<NetworkEvent.Context> ctx) {
//            ctx.get().enqueueWork(() -> {
//                SolaraClientData.solaraEligible = msg.solaraEligible();
//            });
//            ctx.get().setPacketHandled(true);
//        }
//    }
//}
