package net.cold.coldsmod.menu_blessing;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;

public class BlessingUnlockSyncPacket {
    private final CompoundTag data;

    public BlessingUnlockSyncPacket(CompoundTag data) {
        this.data = data;
    }

    public BlessingUnlockSyncPacket(FriendlyByteBuf buf) {
        this.data = buf.readNbt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeNbt(this.data);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // Run on Client
            net.minecraft.client.Minecraft mc = net.minecraft.client.Minecraft.getInstance();
            if (mc.player != null) {
                // 1. Update the local data
                // We use ForgeData because getPersistentData() maps to that on the client
                mc.player.getPersistentData().merge(this.data);

                // 2. Refresh the UI if it's open
                if (mc.screen instanceof BlessingScreen screen) {
                    // This forces the buttons to re-calculate their .active status
                    screen.init(mc, screen.width, screen.height);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}