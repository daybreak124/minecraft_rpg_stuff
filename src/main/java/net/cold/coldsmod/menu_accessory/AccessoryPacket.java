package net.cold.coldsmod.menu_accessory;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class AccessoryPacket {
    private final String accessoryId;
    private final boolean isActivating;

    public AccessoryPacket(String accessoryId, boolean isActivating) {
        this.accessoryId = accessoryId;
        this.isActivating = isActivating;
    }

    public AccessoryPacket(FriendlyByteBuf buf) {
        this.accessoryId = buf.readUtf();
        this.isActivating = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(this.accessoryId);
        buf.writeBoolean(this.isActivating);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                // Match the logic flow of your StatUpgradePacket
                if (this.isActivating) {
                    AccessoryUpgradeHandler.tryUpgrade(player, this.accessoryId);
                } else {
                    AccessoryUpgradeHandler.tryDowngrade(player, this.accessoryId);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}