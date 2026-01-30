package net.cold.coldsmod.blessingbonuses.effects;

import net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils;
import net.cold.coldsmod.network.ClientKeyInputHandler;
import net.cold.coldsmod.network.NetworkHandler;
import net.cold.coldsmod.network.QuantumLeapPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class QuantumLeapActive extends MobEffect {

    public QuantumLeapActive() {
        super(MobEffectCategory.NEUTRAL, 0xFFD700); // gold color
    }

    public static void performDash(ServerPlayer player) {
        if (!player.isAlive()) return;

        Vec3 look = player.getLookAngle().normalize();
        Vec3 dashTarget = player.position().add(look.scale(10));

        double yOffset = 1.0;
        AABB targetBox = player.getBoundingBox().move(
                dashTarget.x - player.getX(),
                dashTarget.y - player.getY(),
                dashTarget.z - player.getZ()
        );

        if (!player.level().noCollision(player, targetBox)) {
            yOffset += 1.0;
        }

        CompoundTag tag = player.getPersistentData();
        tag.putDouble("dash_x", player.getX());
        tag.putDouble("dash_y", player.getY());
        tag.putDouble("dash_z", player.getZ());
        tag.putBoolean("quantum_leaped", true);
        tag.putLong("leap_timestamp", player.level().getGameTime());

        player.teleportTo(dashTarget.x, dashTarget.y + yOffset, dashTarget.z);
        player.setDeltaMovement(Vec3.ZERO);
        player.hurtMarked = true;

        EffectUtils.spawnParticleBurst(player, ParticleTypes.FISHING);

        player.removeEffect(ModEffects.QUANTUM_LEAP_READY.get());
        player.addEffect(new MobEffectInstance(ModEffects.QUANTUM_LEAP_ACTIVE.get(), 120, 0, false, false, true));
        player.addEffect(new MobEffectInstance(ModEffects.QUANTUM_LEAP_COOLDOWN.get(), 20 * 35, 0, false, false, true));

        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.PLAYER_SPLASH_HIGH_SPEED, SoundSource.PLAYERS, 0.6F, 1.0F);

        List<LivingEntity> nearby = player.level().getEntitiesOfClass(
                LivingEntity.class,
                player.getBoundingBox().inflate(10),
                e -> e instanceof Enemy
        );

        for (LivingEntity entity : nearby) {
            if (entity instanceof Mob mob) mob.setTarget(null);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.player.level().isClientSide()) return;

        Player player = event.player;
        CompoundTag tag = player.getPersistentData();

        if (tag.getBoolean("quantum_leaped")) {
            int invisDuration = 20 * 4;
            int leapActiveDuration = 20 * 6;

            if (player.hasEffect(ModEffects.ENHANCED_QUANTUM_LEAP.get())) {
                invisDuration = 20 * 6;
                leapActiveDuration = 20 * 9;
            }

            if (player.onGround() && player.hasEffect(ModEffects.QUANTUM_LEAP_ACTIVE.get())) {
                player.addEffect(new MobEffectInstance(ModEffects.QUANTUM_LEAP_ACTIVE.get(), leapActiveDuration, 0, false, false, true));

                if (!player.hasEffect(MobEffects.INVISIBILITY)) {
                    player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, invisDuration, 0, false, false, true));
                    tag.putBoolean("invis_added", true);
                }

                tag.putBoolean("quantum_leaped", false);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerAttack(LivingAttackEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!player.hasEffect(ModEffects.QUANTUM_LEAP_ACTIVE.get())) return;

        player.removeEffect(ModEffects.QUANTUM_LEAP_ACTIVE.get());

        if (player.getPersistentData().getBoolean("invis_added")) {
            player.removeEffect(MobEffects.INVISIBILITY);
            player.getPersistentData().putBoolean("invis_added", false);
        }
    }

    public static void returnToOrigin(ServerPlayer player) {
        CompoundTag tag = player.getPersistentData();
        if (!tag.contains("dash_x")) return;

        player.teleportTo(tag.getDouble("dash_x"), tag.getDouble("dash_y"), tag.getDouble("dash_z"));

        EffectUtils.playSound(player, SoundEvents.ENDERMAN_TELEPORT, 0.5F, 1.0F);
        EffectUtils.spawnParticleBurst(player, ParticleTypes.PORTAL);

        tag.remove("dash_x");
        tag.remove("dash_y");
        tag.remove("dash_z");
        tag.remove("leap_timestamp");
        tag.putBoolean("quantum_leaped", false);
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (ClientKeyInputHandler.quantumKey != null && ClientKeyInputHandler.quantumKey.consumeClick()) {
            Player player = Minecraft.getInstance().player;
            if (player == null) return;

            boolean hasReady = player.hasEffect(ModEffects.QUANTUM_LEAP_READY.get());
            boolean hasActive = player.hasEffect(ModEffects.QUANTUM_LEAP_ACTIVE.get());

            if (hasReady || hasActive) {
                NetworkHandler.CHANNEL.sendToServer(new QuantumLeapPacket());
            }
        }
    }
}