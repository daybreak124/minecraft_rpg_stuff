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
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.EventPriority;
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

        player.teleportTo(dashTarget.x, dashTarget.y + yOffset, dashTarget.z);
        player.setDeltaMovement(Vec3.ZERO);
        player.hurtMarked = true;

        EffectUtils.spawnParticleBurst(player, ParticleTypes.FISHING);

        player.removeEffect(ModEffects.QUANTUM_LEAP_READY.get());

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

        int invisDuration = 20 * 4;
        int leapActiveDuration = 20 * 6;

        if (player.hasEffect(ModEffects.ENHANCED_QUANTUM_LEAP.get())) {
            invisDuration = 20 * 6;
            leapActiveDuration = 20 * 9;
        }

        player.addEffect(new MobEffectInstance(ModEffects.QUANTUM_LEAP_ACTIVE.get(), leapActiveDuration, 0, false, false, true));
        player.addEffect(new MobEffectInstance(ModEffects.QUANTUM_LEAP_COOLDOWN.get(), 20 * 35, 0, false, false, true));

        if (!player.hasEffect(MobEffects.INVISIBILITY)) {
            player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, invisDuration, 0, false, false, true));
            tag.putBoolean("invis_added", true);
        }
    }

    @SubscribeEvent
    public static void onPlayerAttack(LivingAttackEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!player.hasEffect(ModEffects.QUANTUM_LEAP_ACTIVE.get())) return;

        CompoundTag tag = player.getPersistentData();
        player.removeEffect(ModEffects.QUANTUM_LEAP_ACTIVE.get());

        if (tag.getBoolean("invis_added")) {
            player.removeEffect(MobEffects.INVISIBILITY);
                tag.putBoolean("invis_added", false);
        }
    }
}