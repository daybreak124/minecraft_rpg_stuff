package net.cold.coldsmod.network;

import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusCapabilityProvider;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.PlayerBonusCache;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;



public class ToggleJumpBoostPacket {
    public ToggleJumpBoostPacket() {}
    public ToggleJumpBoostPacket(FriendlyByteBuf buf) {}
    public void encode(FriendlyByteBuf buf) {}
    public static ToggleJumpBoostPacket decode(FriendlyByteBuf buf) { return new ToggleJumpBoostPacket(); }
    private static final UUID CLOUDSPIRE_GEM_UUID = UUID.fromString("66633366-7777-8888-9999-aaaa00aaaaaa");

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;

            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);

            if (!cache.isCloudspireEquipped()) return;

            boolean newState = !cache.isJumpBoostEnabled();
            cache.setJumpBoostEnabled(newState);

            if (!cache.isJumpBoostEnabled()) {
                AttributeApplier.removeModifier(player, ModAttributes.JUMP_BOOST.get(), CLOUDSPIRE_GEM_UUID);
            } else {
                AttributeApplier.applyModifier(player, ModAttributes.JUMP_BOOST.get(), 1.0, CLOUDSPIRE_GEM_UUID);
            }

            String status = newState ? "§aEnabled" : "§cDisabled";
            player.displayClientMessage(Component.literal("Jump Boost: " + status), true);
        });
        return true;
    }
}