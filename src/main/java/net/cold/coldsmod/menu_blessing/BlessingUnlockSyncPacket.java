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
            net.minecraft.client.Minecraft mc = net.minecraft.client.Minecraft.getInstance();
            if (mc.player != null) {
                mc.player.getPersistentData().merge(this.data);

                if (mc.screen instanceof BlessingScreen screen) {
                    screen.init(mc, screen.width, screen.height);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}