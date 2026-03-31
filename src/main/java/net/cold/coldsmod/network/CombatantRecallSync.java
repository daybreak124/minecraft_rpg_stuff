package net.cold.coldsmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CombatantRecallSync {

    public static class CombatantRecallClientData {
        public static boolean combatantRecallEligible = false;
    }

    public record CombatantRecallFlagPacket(boolean combatantRecallEligible) {

        public static void encode(CombatantRecallFlagPacket msg, FriendlyByteBuf buf) {
            buf.writeBoolean(msg.combatantRecallEligible);
        }

        public static CombatantRecallFlagPacket decode(FriendlyByteBuf buf) {
            return new CombatantRecallFlagPacket(buf.readBoolean());
        }

        public static void handle(CombatantRecallFlagPacket msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                CombatantRecallClientData.combatantRecallEligible = msg.combatantRecallEligible;
            });
            ctx.get().setPacketHandled(true);
        }
    }
}