package net.cold.coldsmod.network;

import net.cold.coldsmod.ModMessages;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusCapabilityProvider;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.PlayerBonusCache;
import net.cold.coldsmod.capabilities_and_blessings.effects.CombatantsAidReady;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CombatantsRecallPacket {
    public CombatantsRecallPacket() {}

    public static CombatantsRecallPacket decode(FriendlyByteBuf buf) { return new CombatantsRecallPacket(); }

    public void encode(FriendlyByteBuf buf) {}

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);

            CombatantsAidReady.returnToOrigin(player, cache);
            ModMessages.sendToPlayer(new CombatantRecallSync.CombatantRecallFlagPacket(false), player);
        });
        ctx.get().setPacketHandled(true);
    }
}