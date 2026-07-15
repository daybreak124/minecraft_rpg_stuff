package net.cold.coldsmod.menu_accessory;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import static net.cold.coldsmod.stat.AttributeApplier.*;

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
                if (this.isActivating) {
                    AccessoryUpgradeHandler.tryUpgrade(player, this.accessoryId);
                    player.getServer().tell(new net.minecraft.server.TickTask(
                            player.getServer().getTickCount() + 1,
                            () -> {
                                if (player.isAlive()) {
                                    syncAndApplyAttributes(player);
                                    refreshPerPointStats(player);
                                    refreshMilestones(player);
                                    recalculateDynamicBonuses(player);
                                }
                            }
                    ));
                } else {
                    AccessoryUpgradeHandler.tryDowngrade(player, this.accessoryId);
                    player.getServer().tell(new net.minecraft.server.TickTask(
                            player.getServer().getTickCount() + 1,
                            () -> {
                                if (player.isAlive()) {
                                    syncAndApplyAttributes(player);
                                    refreshPerPointStats(player);
                                    refreshMilestones(player);
                                    recalculateDynamicBonuses(player);
                                }
                            }
                    ));
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}