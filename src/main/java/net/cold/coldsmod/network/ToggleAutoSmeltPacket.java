package net.cold.coldsmod.network;

import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusCapabilityProvider;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.PlayerBonusCache;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ToggleAutoSmeltPacket {
    public ToggleAutoSmeltPacket() {}
    public ToggleAutoSmeltPacket(FriendlyByteBuf buf) {}
    public void encode(FriendlyByteBuf buf) {}
    public static ToggleAutoSmeltPacket decode(FriendlyByteBuf buf) { return new ToggleAutoSmeltPacket(); }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;

            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);

            if (!(cache.isFlameEligible() || cache.isForgeEligible())) return;
            boolean newState = !cache.isAutoSmeltEnabled();
            cache.setAutoSmeltEnabled(newState);

            String status = newState ? "§aEnabled" : "§cDisabled";
            player.displayClientMessage(Component.literal("Auto-Smelt: " + status), true);
        });
        return true;
    }
}