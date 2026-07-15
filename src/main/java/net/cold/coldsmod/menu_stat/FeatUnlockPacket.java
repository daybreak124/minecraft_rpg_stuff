package net.cold.coldsmod.menu_stat;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FeatUnlockPacket {
    private final String treeKey;
    private final int featId;
    private final boolean isActivating;

    public FeatUnlockPacket(String treeKey, int featId, boolean isActivating) {
        this.treeKey = treeKey;
        this.featId = featId;
        this.isActivating = isActivating;
    }

    public FeatUnlockPacket(FriendlyByteBuf buf) {
        this.treeKey = buf.readUtf();
        this.featId = buf.readInt();
        this.isActivating = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(this.treeKey);
        buf.writeInt(this.featId);
        buf.writeBoolean(this.isActivating);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                if (this.isActivating) {
                    FeatUpgradeHandlerRegistry.tryUpgrade(player, this.treeKey, this.featId);
                } else {
                    FeatUpgradeHandlerRegistry.tryDowngrade(player, this.treeKey, this.featId);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}