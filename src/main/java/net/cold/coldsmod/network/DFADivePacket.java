package net.cold.coldsmod.network;

import net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DFADivePacket {
    public DFADivePacket() {}

    public DFADivePacket(FriendlyByteBuf buffer) {}

    public void encode(FriendlyByteBuf buffer) {}

    public static DFADivePacket decode(FriendlyByteBuf buf) { return new DFADivePacket(); }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;


            if (player.getPersistentData().getFloat("dfaFallDamage") < 10f) return;
            double diveSpeed = -2.5;

            player.setDeltaMovement(player.getDeltaMovement().x * 0.1, diveSpeed, player.getDeltaMovement().z * 0.1);
            player.setOnGround(false);
            player.fallDistance = 5.0F;

            player.getPersistentData().putBoolean("DFA_Airborne", false);
            player.level().playSound(player, player.getX(),player.getY(),player.getZ(),
                    SoundEvents.TRIDENT_RIPTIDE_3, SoundSource.PLAYERS,
                    1.0F, 0.6F);
            EffectUtils.spawnParticleBurst(player, ParticleTypes.SONIC_BOOM);
        });
        context.setPacketHandled(true);
    }
}