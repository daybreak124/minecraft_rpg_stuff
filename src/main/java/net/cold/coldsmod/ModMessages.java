package net.cold.coldsmod;

import net.cold.coldsmod.menu_accessory.AccessoryMenuPacket;
import net.cold.coldsmod.menu_accessory.AccessoryPacket;
import net.cold.coldsmod.menu_accessory.AccessoryUnlockSyncPacket;
import net.cold.coldsmod.menu_blessing.BlessingMenuPacket;
import net.cold.coldsmod.menu_blessing.BlessingPacket;
import net.cold.coldsmod.menu_blessing.BlessingUnlockSyncPacket;
import net.cold.coldsmod.menu_stat.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;
    private static int packetId = 0;
    private static int id() { return packetId++; }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation("coldsmod", "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        // Register the packet that opens the menu
        net.messageBuilder(OpenStatMenuPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(OpenStatMenuPacket::new)
                .encoder(OpenStatMenuPacket::toBytes)
                .consumerMainThread(OpenStatMenuPacket::handle)
                .add();

        net.messageBuilder(StatUpgradePacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(StatUpgradePacket::new)
                .encoder(StatUpgradePacket::toBytes)
                .consumerMainThread(StatUpgradePacket::handle)
                .add();

        net.messageBuilder(StatUpgradePacketTwo.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(StatUpgradePacketTwo::new)
                .encoder(StatUpgradePacketTwo::toBytes)
                .consumerMainThread(StatUpgradePacketTwo::handle)
                .add();

        net.messageBuilder(StatUpgradePacketThree.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(StatUpgradePacketThree::new)
                .encoder(StatUpgradePacketThree::toBytes)
                .consumerMainThread(StatUpgradePacketThree::handle)
                .add();

        net.messageBuilder(StatsSyncPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(StatsSyncPacket::new)
                .encoder(StatsSyncPacket::toBytes)
                .consumerMainThread(StatsSyncPacket::handle)
                .add();

        // Register the packet that opens the menu
        net.messageBuilder(BlessingMenuPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(BlessingMenuPacket::new)
                .encoder(BlessingMenuPacket::toBytes)
                .consumerMainThread(BlessingMenuPacket::handle)
                .add();

        net.messageBuilder(BlessingPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(BlessingPacket::new)
                .encoder(BlessingPacket::toBytes)
                .consumerMainThread(BlessingPacket::handle)
                .add();

        net.messageBuilder(BlessingUnlockSyncPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(BlessingUnlockSyncPacket::new)
                .encoder(BlessingUnlockSyncPacket::toBytes)
                .consumerMainThread(BlessingUnlockSyncPacket::handle)
                .add();

        // --

        net.messageBuilder(AccessoryMenuPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(AccessoryMenuPacket::new)
                .encoder(AccessoryMenuPacket::toBytes)
                .consumerMainThread(AccessoryMenuPacket::handle)
                .add();

        net.messageBuilder(AccessoryPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(AccessoryPacket::new)
                .encoder(AccessoryPacket::toBytes)
                .consumerMainThread(AccessoryPacket::handle)
                .add();

        net.messageBuilder(AccessoryUnlockSyncPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(AccessoryUnlockSyncPacket::new).encoder(AccessoryUnlockSyncPacket::toBytes)
                .consumerMainThread(AccessoryUnlockSyncPacket::handle).add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}