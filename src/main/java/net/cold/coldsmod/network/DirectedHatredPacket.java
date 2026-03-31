package net.cold.coldsmod.network;

import net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils;
import net.cold.coldsmod.capabilities_and_blessings.registry.ModEffects;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

import static net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils.spawnParticleRing;

public class DirectedHatredPacket {

    public DirectedHatredPacket() {}
    public DirectedHatredPacket(FriendlyByteBuf buffer) {}
    public static DirectedHatredPacket decode(FriendlyByteBuf buf) { return new DirectedHatredPacket(); }
    public void encode(FriendlyByteBuf buffer) {}

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;

            ServerLevel level = player.serverLevel();
            double rangeSq = 100.0;

            List<LivingEntity> nearby = level.getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(10.0),
                    e -> {
                        if (e.isInvulnerable() || !player.hasLineOfSight(e) || !((e instanceof Enemy && !(e instanceof NeutralMob)) || (e instanceof NeutralMob n && n.isAngryAt(player)) || (e instanceof Mob m && m.getTarget() != null))) return false;
                        return player.distanceToSqr(e) <= rangeSq;
                    }
            );

            for (LivingEntity entity : nearby) {
                if (entity instanceof Mob mob) {
                    mob.setTarget(player);
                    mob.addEffect(new MobEffectInstance(ModEffects.BLINDED_BY_HATRED.get(), 120, 0, false, true, false));
                }
            }

            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ANVIL_PLACE, SoundSource.PLAYERS, 0.35F, 1.0F);

            spawnParticleRing(level, player, ParticleTypes.ANGRY_VILLAGER, 6.0, 120);

            EffectUtils.spawnParticleBurst(player, ParticleTypes.WITCH);

            player.removeEffect(ModEffects.DIRECTED_HATRED_READY.get());
            player.addEffect(new MobEffectInstance(ModEffects.DIRECTED_HATRED_COOLDOWN.get(), 200, 0, false, false, true));
        });
        return true;
    }
}