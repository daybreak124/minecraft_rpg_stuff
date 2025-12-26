package net.cold.coldsmod.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("coldsmod", "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int packetId = 0;
    private static int nextId() { return packetId++; }

    public static void register() {
        CHANNEL.messageBuilder(DrawSpeedSync.class, nextId(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(DrawSpeedSync::encode)
                .decoder(DrawSpeedSync::decode)
                .consumerMainThread(DrawSpeedSync::handle)
                .add();

//        CHANNEL.messageBuilder(SolaraSync.SolaraFlagPacket.class, nextId(), NetworkDirection.PLAY_TO_CLIENT)
//                .encoder(SolaraSync.SolaraFlagPacket::encode)
//                .decoder(SolaraSync.SolaraFlagPacket::decode)
//                .consumerMainThread(SolaraSync.SolaraFlagPacket::handle)
//                .add();

        CHANNEL.messageBuilder(DFASync.DFAFlagPacket.class, nextId(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(DFASync.DFAFlagPacket::encode)
                .decoder(DFASync.DFAFlagPacket::decode)
                .consumerMainThread(DFASync.DFAFlagPacket::handle)
                .add();

        // Quantum Leap packet
        CHANNEL.messageBuilder(QuantumLeapSync.QuantumLeapFlagPacket.class, nextId(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(QuantumLeapSync.QuantumLeapFlagPacket::encode)
                .decoder(QuantumLeapSync.QuantumLeapFlagPacket::decode)
                .consumerMainThread(QuantumLeapSync.QuantumLeapFlagPacket::handle)
                .add();
    }

    public static void sendToClient(Object msg, ServerPlayer player) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), msg);
    }
}
