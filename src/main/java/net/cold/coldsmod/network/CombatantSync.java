package net.cold.coldsmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CombatantSync {

    public static class CombatantClientData {
        public static boolean combatantEligible = false;
    }

    public record CombatantFlagPacket(boolean combatantEligible) {

        public static void encode(CombatantFlagPacket msg, FriendlyByteBuf buf) {
            buf.writeBoolean(msg.combatantEligible);
        }

        public static CombatantFlagPacket decode(FriendlyByteBuf buf) {
            return new CombatantFlagPacket(buf.readBoolean());
        }

        public static void handle(CombatantFlagPacket msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                CombatantClientData.combatantEligible = msg.combatantEligible;
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
