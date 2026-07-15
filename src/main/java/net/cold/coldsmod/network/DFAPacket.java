package net.cold.coldsmod.network;

import net.cold.coldsmod.ModMessages;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusCapabilityProvider;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusRegistry;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusTrigger;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.PlayerBonusCache;
import net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils;
import net.cold.coldsmod.capabilities_and_blessings.registry.ModEffects;
import net.cold.coldsmod.damage_types.ModDamageTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

import static net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils.spawnParticleRing;

public class DFAPacket {
    private final double motionX;
    private final double motionZ;
    private final boolean isCrouching;

    public DFAPacket(double motionX, double motionZ, boolean isCrouching) {
        this.motionX = motionX;
        this.motionZ = motionZ;
        this.isCrouching = isCrouching;
    }

    public static DFAPacket decode(FriendlyByteBuf buf) {
        return new DFAPacket(buf.readDouble(), buf.readDouble(), buf.readBoolean());
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeDouble(motionX);
        buf.writeDouble(motionZ);
        buf.writeBoolean(isCrouching);
    }

    private static final float JUMP_DAMAGE = 5f;


    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;
            if (!player.onGround()) return;
            if (player.isInWater()) return;

            Level level = player.level();

            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
            cache.setDFAAirborne(true);
            BonusRegistry.process(player, null, player.level(), BonusTrigger.BLESSING_ACTIVATION);


            double jumpBoost;
            if (this.isCrouching) {
                jumpBoost = 0.42;
                cache.setDfaJump(false);
            } else {
                jumpBoost = 1.1;
                cache.setDfaJump(true);
            }

            player.setDeltaMovement(this.motionX, jumpBoost, this.motionZ);
            player.setOnGround(false);
            player.fallDistance = 0;


            Holder<DamageType> meleeType = level.registryAccess()
                    .registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(ModDamageTypes.CUSTOM_MELEE_DAMAGE);
            DamageSource source = new DamageSource(meleeType, null, player);


            double jumpRadiusSq = 25;
            ModMessages.sendToPlayer(new DfaAirborneSync.DfaAirborneFlagPacket(true), player);


            List<LivingEntity> jumpTargets = level.getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(5),
                    e -> {
                        if (e == null || !e.isAlive() || e.isInvulnerable() || !player.hasLineOfSight(e) || !((e instanceof Enemy && !(e instanceof NeutralMob)) || (e instanceof NeutralMob n && n.isAngry()) || (e instanceof Mob m && m.getTarget() != null))) return false;

                        double dx = e.getX() - player.getX();
                        double dz = e.getZ() - player.getZ();
                        return (dx * dx + dz * dz) <= jumpRadiusSq;
                    }
            );

            for (LivingEntity target : jumpTargets) {target.hurt(source, JUMP_DAMAGE);}

            player.level().playSound(player, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 0.5F, 1.0F);

            EffectUtils.spawnExplosionOnFeet(player);
            if (level instanceof ServerLevel serverLevel) {
                spawnParticleRing(serverLevel, player, ParticleTypes.POOF, 5, 100);
            }

            player.removeEffect(ModEffects.DEATH_FROM_ABOVE_READY.get());
            player.addEffect(new MobEffectInstance(ModEffects.DEATH_FROM_ABOVE_COOLDOWN.get(), 420, 0, false, false, true));
            handleQuantumLeapSynergy(player, cache);
        });
        return true;
    }

    public static void handleQuantumLeapSynergy(Player player, PlayerBonusCache cache) {
        if (!cache.isDfaQuantumSynergy()) return;
        MobEffectInstance quantum = player.getEffect(ModEffects.QUANTUM_LEAP_COOLDOWN.get());
        if (quantum != null) {
            int duration = Math.max(0, quantum.getDuration() - (100));
            player.removeEffect(ModEffects.QUANTUM_LEAP_COOLDOWN.get());
            if (duration > 0) {
                player.addEffect(new MobEffectInstance(ModEffects.QUANTUM_LEAP_COOLDOWN.get(), duration, 0, false, false, true));
            }
        }
        player.addEffect(new MobEffectInstance(ModEffects.ENHANCED_QUANTUM_LEAP.get(), 80, 0, false, false, true));
    }
}
