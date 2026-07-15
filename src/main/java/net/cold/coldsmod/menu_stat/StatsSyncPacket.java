package net.cold.coldsmod.menu_stat;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class StatsSyncPacket {
    private String attrId;
    private int points;
    private CompoundTag statsTag;
    private final boolean isScreenOne;
    private final boolean isScreenUtil;
    private final boolean isBatch;

    public StatsSyncPacket(String attrId, int points, boolean isScreenOne, boolean isScreenUtil) {
        this.attrId = attrId;
        this.points = points;
        this.isScreenOne = isScreenOne;
        this.isScreenUtil = isScreenUtil;
        this.isBatch = false;
    }

    // for respawn/login logic
    public StatsSyncPacket(CompoundTag statsTag, boolean isScreenOne, boolean isScreenUtil) {
        this.statsTag = statsTag;
        this.isScreenOne = isScreenOne;
        this.isScreenUtil = isScreenUtil;
        this.isBatch = true;
    }

    public StatsSyncPacket(FriendlyByteBuf buf) {
        this.isBatch = buf.readBoolean();
        if (isBatch) {
            this.statsTag = buf.readNbt();
        } else {
            this.attrId = buf.readUtf();
            this.points = buf.readInt();
        }
        this.isScreenOne = buf.readBoolean();
        this.isScreenUtil = buf.readBoolean();
    }

    // Updated toBytes
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(isBatch);
        if (isBatch) {
            buf.writeNbt(statsTag);
        } else {
            buf.writeUtf(attrId);
            buf.writeInt(points);
        }
        buf.writeBoolean(isScreenOne);
        buf.writeBoolean(isScreenUtil);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            if (Minecraft.getInstance().player != null) {
                String nbtKey;
                if (isScreenOne) {
                    nbtKey = "SpentPointsOne";
                } else if (isScreenUtil) {
                    nbtKey = "SpentPointsUtil";
                } else {
                    nbtKey = "SpentPoints";
                }

                CompoundTag data = Minecraft.getInstance().player.getPersistentData();

                if (isBatch) {
                    data.put(nbtKey, statsTag.copy());
                } else {
                    if (!data.contains(nbtKey)) { data.put(nbtKey, new CompoundTag()); }
                    data.getCompound(nbtKey).putInt(attrId, points);
                }

                Minecraft mc = Minecraft.getInstance();
            }
        });
        return true;
    }
}