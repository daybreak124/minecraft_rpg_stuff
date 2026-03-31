package net.cold.coldsmod.menu_blessing;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class BlessingPacket {
    private final String blessingId;
    private final boolean isActivating;

    public BlessingPacket(String blessingId, boolean isActivating) {
        this.blessingId = blessingId;
        this.isActivating = isActivating;
    }

    public BlessingPacket(FriendlyByteBuf buf) {
        this.blessingId = buf.readUtf();
        this.isActivating = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(this.blessingId);
        buf.writeBoolean(this.isActivating);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                if (this.isActivating) {
                    BlessingUpgradeHandler.tryUpgrade(player, this.blessingId);
                } else {
                    var entry = BlessingRegistry.MAP.get(this.blessingId);

                    if (entry != null) {
                        boolean canRemove = BlessingEffectRegistry.CAN_REMOVE.getOrDefault(entry.item(), p -> true).test(player);
                        if (canRemove) {
                            BlessingUpgradeHandler.tryDowngrade(player, this.blessingId);
                        }
                    }
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}