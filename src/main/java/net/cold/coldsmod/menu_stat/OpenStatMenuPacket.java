package net.cold.coldsmod.menu_stat;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;

import java.util.function.Supplier;

public class OpenStatMenuPacket {
    public OpenStatMenuPacket() {}
    public OpenStatMenuPacket(FriendlyByteBuf buffer) {}
    public void toBytes(FriendlyByteBuf buffer) {}

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                // This opens the ContainerMenu you created for the stats
                NetworkHooks.openScreen(player, new SimpleMenuProvider(
                        (id, inv, p) -> new StatMenu(id, inv),
                        Component.empty()
                ));
            }
        });
        return true;
    }
}