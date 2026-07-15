package net.cold.coldsmod.menu_accessory;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class AccessoryUnlockSyncPacket {
    private final CompoundTag data;

    public AccessoryUnlockSyncPacket(CompoundTag data) { this.data = data; }
    public AccessoryUnlockSyncPacket(FriendlyByteBuf buffer) { this.data = buffer.readNbt(); }
    public void toBytes(FriendlyByteBuf buffer) { buffer.writeNbt(data); }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            net.minecraft.client.Minecraft mc = net.minecraft.client.Minecraft.getInstance();
            if (mc.player != null) {
                mc.player.getPersistentData().merge(this.data);

                if (mc.screen instanceof AccessoryScreen accessoryScreen) {
                    accessoryScreen.refreshFromPacket();
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}