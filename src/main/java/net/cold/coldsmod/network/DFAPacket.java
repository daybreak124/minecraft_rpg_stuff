package net.cold.coldsmod.network;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils;
import net.cold.coldsmod.damage.ModDamageTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

import static net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils.spawnParticleRing;

public class DFAPacket {
    public DFAPacket() {}

    public static DFAPacket decode(FriendlyByteBuf buf) {
        return new DFAPacket();
    }

    public void encode(FriendlyByteBuf buf) {
    }

    private static final double JUMP_RADIUS = 5.0;
    private static final float JUMP_DAMAGE = 7.5f;


    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;
            if (player.isInWater()) return;

            Level level = player.level();

            double motionX = player.getDeltaMovement().x;
            double motionZ = player.getDeltaMovement().z;

            if (Math.abs(motionX) < 0.01 && Math.abs(motionZ) < 0.01) {
                motionX = 0;
                motionZ = 0;
            } else {
                double dashMultiplier = 2.5;
                motionX *= dashMultiplier;
                motionZ *= dashMultiplier;
            }

            double jumpBoost = 1.1;

            if (player.isCrouching()) {
                player.getPersistentData().putFloat("dfaFallDamage", 6.25f);
            } else {
                player.setDeltaMovement(motionX, jumpBoost, motionZ);
                player.getPersistentData().putFloat("dfaFallDamage", 12.5f);
            }
            player.getPersistentData().putBoolean("DFA_Airborne", true);
            player.getPersistentData().putBoolean("DFA_fall_damage_cancel", true);


            Holder<DamageType> meleeType = level.registryAccess()
                    .registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(ModDamageTypes.CUSTOM_MELEE_DAMAGE);
            DamageSource source = new DamageSource(meleeType, player, player);


            double jumpRadiusSq = 25;

            List<LivingEntity> jumpTargets = level.getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(JUMP_RADIUS),
                    e -> {
                        if (e == null || !e.isAlive() || e.isInvulnerable() || !player.hasLineOfSight(e) || !((e instanceof Enemy && !(e instanceof NeutralMob)) || (e instanceof NeutralMob n && n.isAngry()) || (e instanceof Mob m && m.getTarget() != null))) return false;

                        double dx = e.getX() - player.getX();
                        double dz = e.getZ() - player.getZ();
                        return (dx * dx + dz * dz) <= jumpRadiusSq;
                    }
            );

            for (LivingEntity target : jumpTargets) {target.hurt(source, JUMP_DAMAGE);}

            EffectUtils.playExplosionSound(player, 0.5F);
            EffectUtils.spawnExplosionOnFeet(player);
            if (level instanceof ServerLevel serverLevel) {
                spawnParticleRing(serverLevel, player, ParticleTypes.POOF, JUMP_RADIUS, 100);
            }

            player.hurtMarked = true;
            player.removeEffect(ModEffects.DEATH_FROM_ABOVE.get());
            player.addEffect(new MobEffectInstance(ModEffects.DEATH_FROM_ABOVE_COOLDOWN.get(), 300, 0, false, false, true));

        });
        return true;
    }
}
