package net.cold.coldsmod.menu_stat;

import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusCapabilityProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FeatSyncPacket {
    private final CompoundTag data;

    public FeatSyncPacket(CompoundTag data) {
        this.data = data;
    }

    public FeatSyncPacket(FriendlyByteBuf buf) {
        this.data = buf.readNbt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeNbt(this.data);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            net.minecraft.client.Minecraft mc = net.minecraft.client.Minecraft.getInstance();
            if (mc.player != null) {
                mc.player.getPersistentData().merge(this.data);

                if (mc.screen instanceof FeatScreen screen) {
                    screen.init(mc, mc.screen.width, mc.screen.height);
                }

                mc.player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                    boolean phalanx = data.getCompound("ActiveFeats_STEEL").getBoolean("feat_44");
                    boolean marksman = data.getCompound("ActiveFeats_NIGHT").getBoolean("feat_25");

                    cache.setShieldSlowdownCancel(phalanx);
                    cache.setBowSlowdownCancel(marksman);

                });

                if (mc.screen instanceof FeatScreen featScreen) {
                    featScreen.refreshFromPacket();
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}