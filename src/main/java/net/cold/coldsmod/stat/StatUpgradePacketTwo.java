package net.cold.coldsmod.stat;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class StatUpgradePacketTwo {
    private final String attrId;
    private final boolean isUpgrade;

    public StatUpgradePacketTwo(String attrId, boolean isUpgrade) {
        this.attrId = attrId;
        this.isUpgrade = isUpgrade;
    }

    public StatUpgradePacketTwo(FriendlyByteBuf buf) {
        this.attrId = buf.readUtf();
        this.isUpgrade = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(this.attrId);
        buf.writeBoolean(this.isUpgrade);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            Attribute attr = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(attrId));
            if (attr != null) {
                if (isUpgrade) {
                    // Point specifically to the Pearl/Stat logic
                    StatUpgradeHandlerTwo.tryUpgrade(player, attr);
                } else {
                    StatUpgradeHandlerTwo.tryDowngrade(player, attr);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}