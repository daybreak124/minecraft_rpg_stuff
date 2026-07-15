package net.cold.coldsmod.network;

import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusCapabilityProvider;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusRegistry;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusTrigger;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.PlayerBonusCache;
import net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils;
import net.cold.coldsmod.capabilities_and_blessings.registry.ModEffects;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

import static net.cold.coldsmod.capabilities_and_blessings.effects.QuantumLeapActive.QUANTUM_UUID;
import static net.cold.coldsmod.stat.AttributeApplier.applyModifier;

public class QuantumLeapPacket {
    public QuantumLeapPacket() {}

    public static QuantumLeapPacket decode(FriendlyByteBuf buf) {
        return new QuantumLeapPacket();
    }

    public void encode(FriendlyByteBuf buf) {
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;
            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);

            performDash(player, cache);
        });
        ctx.get().setPacketHandled(true);
    }

    public static void performDash(ServerPlayer player, PlayerBonusCache cache) {
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


        player.teleportTo(dashTarget.x, dashTarget.y + yOffset, dashTarget.z);

        // Fall damage
        //player.setDeltaMovement(Vec3.ZERO);

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
            if (entity instanceof Mob mob && !entity.getType().is(Tags.EntityTypes.BOSSES) && !(entity instanceof Warden)) {
                mob.setTarget(null);
            }
        }

        int leapActiveDuration = 120;

        if (player.hasEffect(ModEffects.ENHANCED_QUANTUM_LEAP.get())) {
            leapActiveDuration = 180;
        }

        player.addEffect(new MobEffectInstance(ModEffects.QUANTUM_LEAP_ACTIVE.get(), leapActiveDuration, 0, false, false, true));
        player.addEffect(new MobEffectInstance(ModEffects.QUANTUM_LEAP_COOLDOWN.get(), 20 * 35, 0, false, false, true));

        player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 160, 0, false, false, true));
        BonusRegistry.process(player, null, player.level(), BonusTrigger.BLESSING_ACTIVATION);


        double potencyBuff = 30;
        double moveSpeedBuff = 0.02;


        if (cache.isDfaQuantumSynergized()) {
            potencyBuff *= 1.5;
            moveSpeedBuff *= 1.5;
        }
        applyModifier(player, ModAttributes.POTENCY.get(), potencyBuff, QUANTUM_UUID);
        applyModifier(player, ModAttributes.MELEE_POTENCY.get(), potencyBuff, QUANTUM_UUID);
        applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), potencyBuff, QUANTUM_UUID);
        applyModifier(player, Attributes.MOVEMENT_SPEED, moveSpeedBuff, QUANTUM_UUID);
    }
}