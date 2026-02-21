package net.cold.coldsmod.menu_stat;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;

public class StatsSyncPacket {
    private final String attrId;
    private final int points;
    private final boolean isScreenOne;
    private final boolean isScreenUtil;

    public StatsSyncPacket(String attrId, int points, boolean isScreenOne, boolean isScreenUtil) {
        this.attrId = attrId;
        this.points = points;
        this.isScreenOne = isScreenOne;
        this.isScreenUtil = isScreenUtil;
    }

    public StatsSyncPacket(FriendlyByteBuf buf) {
        this.attrId = buf.readUtf();
        this.points = buf.readInt();
        this.isScreenOne = buf.readBoolean();
        this.isScreenUtil = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(attrId);
        buf.writeInt(points);
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

                if (!data.contains(nbtKey)) {data.put(nbtKey, new CompoundTag());}
                data.getCompound(nbtKey).putInt(attrId, points);
            }
        });
        return true;
    }
}