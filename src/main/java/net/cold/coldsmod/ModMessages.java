package net.cold.coldsmod;

import net.cold.coldsmod.menu_accessory.AccessoryMenuPacket;
import net.cold.coldsmod.menu_accessory.AccessoryPacket;
import net.cold.coldsmod.menu_accessory.AccessoryUnlockSyncPacket;
import net.cold.coldsmod.menu_blessing.BlessingMenuPacket;
import net.cold.coldsmod.menu_blessing.BlessingPacket;
import net.cold.coldsmod.menu_blessing.BlessingUnlockSyncPacket;
import net.cold.coldsmod.menu_stat.*;
import net.cold.coldsmod.network.*;
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


        net.messageBuilder(IntimidatingPresenceSync.IntimidatingPresenceFlagPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(IntimidatingPresenceSync.IntimidatingPresenceFlagPacket::encode)
                .decoder(IntimidatingPresenceSync.IntimidatingPresenceFlagPacket::decode)
                .consumerMainThread(IntimidatingPresenceSync.IntimidatingPresenceFlagPacket::handle)
                .add();

        net.messageBuilder(CombatantSync.CombatantFlagPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(CombatantSync.CombatantFlagPacket::encode)
                .decoder(CombatantSync.CombatantFlagPacket::decode)
                .consumerMainThread(CombatantSync.CombatantFlagPacket::handle)
                .add();

        net.messageBuilder(CombatantRecallSync.CombatantRecallFlagPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(CombatantRecallSync.CombatantRecallFlagPacket::encode)
                .decoder(CombatantRecallSync.CombatantRecallFlagPacket::decode)
                .consumerMainThread(CombatantRecallSync.CombatantRecallFlagPacket::handle)
                .add();

        net.messageBuilder(DFASync.DFAFlagPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(DFASync.DFAFlagPacket::encode)
                .decoder(DFASync.DFAFlagPacket::decode)
                .consumerMainThread(DFASync.DFAFlagPacket::handle)
                .add();

        net.messageBuilder(DfaAirborneSync.DfaAirborneFlagPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(DfaAirborneSync.DfaAirborneFlagPacket::encode)
                .decoder(DfaAirborneSync.DfaAirborneFlagPacket::decode)
                .consumerMainThread(DfaAirborneSync.DfaAirborneFlagPacket::handle)
                .add();

        net.messageBuilder(OverconfidenceSync.OverconfidenceSyncPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(OverconfidenceSync.OverconfidenceSyncPacket::encode)
                .decoder(OverconfidenceSync.OverconfidenceSyncPacket::decode)
                .consumerMainThread(OverconfidenceSync.OverconfidenceSyncPacket::handle)
                .add();

        net.messageBuilder(SeveranceSync.SeveranceFlagPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(SeveranceSync.SeveranceFlagPacket::encode)
                .decoder(SeveranceSync.SeveranceFlagPacket::decode)
                .consumerMainThread(SeveranceSync.SeveranceFlagPacket::handle)
                .add();

        net.messageBuilder(QuantumLeapSync.QuantumLeapFlagPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(QuantumLeapSync.QuantumLeapFlagPacket::encode)
                .decoder(QuantumLeapSync.QuantumLeapFlagPacket::decode)
                .consumerMainThread(QuantumLeapSync.QuantumLeapFlagPacket::handle)
                .add();

        net.messageBuilder(HatredSync.HatredSyncPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(HatredSync.HatredSyncPacket::encode)
                .decoder(HatredSync.HatredSyncPacket::decode)
                .consumerMainThread(HatredSync.HatredSyncPacket::handle)
                .add();

        net.messageBuilder(DaringShoutSync.DaringShoutSyncPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(DaringShoutSync.DaringShoutSyncPacket::encode)
                .decoder(DaringShoutSync.DaringShoutSyncPacket::decode)
                .consumerMainThread(DaringShoutSync.DaringShoutSyncPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}