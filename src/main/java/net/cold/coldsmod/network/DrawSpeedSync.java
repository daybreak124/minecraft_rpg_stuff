package net.cold.coldsmod.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DrawSpeedSync {
    private final double drawSpeed;

    public DrawSpeedSync(double drawSpeed) {
        this.drawSpeed = drawSpeed;
    }

    public static void encode(DrawSpeedSync msg, FriendlyByteBuf buf) {
        buf.writeDouble(msg.drawSpeed);
    }

    public static DrawSpeedSync decode(FriendlyByteBuf buf) {
        return new DrawSpeedSync(buf.readDouble());
    }

    public static void handle(DrawSpeedSync msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                player.getPersistentData().putDouble("DrawSpeed", msg.drawSpeed);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
