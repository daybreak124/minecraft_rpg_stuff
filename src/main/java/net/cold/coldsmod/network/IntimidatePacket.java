package net.cold.coldsmod.network;

import net.cold.coldsmod.ModSounds;
import net.cold.coldsmod.capabilities_and_blessings.registry.ModEffects;
import net.cold.coldsmod.damage_types.ModDamageTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils.*;

public class IntimidatePacket {

    public IntimidatePacket() {}
    public IntimidatePacket(FriendlyByteBuf buffer) {}
    public static IntimidatePacket decode(FriendlyByteBuf buf) { return new IntimidatePacket(); }
    public void encode(FriendlyByteBuf buffer) {}

    private static final ScheduledExecutorService EXECUTOR = Executors.newSingleThreadScheduledExecutor();

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;

            Level level = player.level();

            if (level instanceof ServerLevel serverLevel) {
                spawnParticleRing(serverLevel, player, ParticleTypes.POOF, 8.0, 160);
            }

            player.addEffect(new MobEffectInstance(ModEffects.INTIMIDATING_PRESENCE_COOLDOWN.get(), 240, 0, false, false, true));
            player.removeEffect(ModEffects.INTIMIDATING_PRESENCE_READY.get());
            level.playSound(
                    null, player.getX(), player.getY(), player.getZ(),
                    ModSounds.INTIMIDATING_PRESENCE.get(), SoundSource.PLAYERS,
                    0.6F, 1.0F
            );

            Holder<DamageType> meleeType = level.registryAccess()
                    .registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(ModDamageTypes.CUSTOM_MELEE_DAMAGE);

            DamageSource source = new DamageSource(meleeType, null, player);

            double radius = 8.0;
            double radiusSq = 64.0;

            List<LivingEntity> targets = level.getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(radius),
                    e -> {
                        if (e == null || !e.isAlive() || !player.hasLineOfSight(e)) return false;
                        if (e.distanceToSqr(player) > radiusSq) return false;

                        boolean isHostile = (e instanceof Enemy && !(e instanceof NeutralMob)) ||
                                (e instanceof NeutralMob n && n.isAngry()) ||
                                (e instanceof Mob m && m.getTarget() != null);

                        return isHostile;
                    }
            );

            for (LivingEntity target : targets) {
                target.setDeltaMovement(0, 0.8, 0);
                target.hurtMarked = true;

                EXECUTOR.schedule(() -> {
                    level.getServer().execute(() -> {
                        if (player.isAlive() && target instanceof Mob mob && target.isAlive()) {
                            mob.addEffect(new MobEffectInstance(ModEffects.STUN.get(), 6, 0, false, false, false));
                            // target.setNoGravity(true);

                            double startX = player.getX();
                            double startY = player.getY() + 1.0;
                            double startZ = player.getZ();

                            double endX = target.getX();
                            double endY = target.getY() + (target.getBbHeight() / 2.0);
                            double endZ = target.getZ();

                            double dx = endX - startX;
                            double dy = endY - startY;
                            double dz = endZ - startZ;

                            double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
                            int particleCount = (int) (distance * 4);

                            if (level instanceof ServerLevel serverLevel) {
                                for (int i = 0; i < particleCount; i++) {
                                    double ratio = (double) i / particleCount;
                                    serverLevel.sendParticles(ParticleTypes.ELECTRIC_SPARK,
                                            startX + (dx * ratio),
                                            startY + (dy * ratio),
                                            startZ + (dz * ratio),
                                            1, 0, 0, 0, 0.0);
                                }
                            }
                        }
                    });
                }, 300, TimeUnit.MILLISECONDS);

                EXECUTOR.schedule(() -> {
                    level.getServer().execute(() -> {
                        if (!target.isAlive() || !player.isAlive()) return;

                        Vec3 playerPos = player.position();
                        Vec3 targetPos = target.position();
                        Vec3 moveVec = playerPos.subtract(targetPos);
                        double distance = moveVec.length();

                        if (distance > 1.5) {
                            Vec3 destination = targetPos.add(moveVec.normalize().scale(distance - 2.5));

                            target.moveTo(destination.x, destination.y, destination.z, target.getYRot(), target.getXRot());
                            target.setDeltaMovement(Vec3.ZERO);
                            target.hurtMarked = true;
                        }

                        if (level instanceof ServerLevel serverLevel) {
                            for (double r = 0; r < 1; r += 0.1) {
                                serverLevel.sendParticles(ParticleTypes.CRIT,
                                        playerPos.x + (target.getX() - playerPos.x) * r,
                                        playerPos.y + 1.0 + (target.getY() - (playerPos.y + 1.0)) * r,
                                        playerPos.z + (target.getZ() - playerPos.z) * r,
                                        1, 0, 0, 0, 0.0);
                            }
                        }
                    });
                }, 600, TimeUnit.MILLISECONDS);
            }

            EXECUTOR.schedule(() -> {
                level.getServer().execute(() -> {
                    if (!player.isAlive()) return;

                    List<LivingEntity> currentTargets = level.getEntitiesOfClass(
                            LivingEntity.class,
                            player.getBoundingBox().inflate(radius),
                            e -> {
                                if (e == null || e == player || !e.isAlive() || !player.hasLineOfSight(e)) return false;
                                if (e.distanceToSqr(player) > (radius * radius)) return false;

                                return (e instanceof Enemy && !(e instanceof NeutralMob)) ||
                                        (e instanceof NeutralMob n && n.isAngry()) ||
                                        (e instanceof Mob m && m.getTarget() != null);
                            }
                    );

                    // Visuals and Sound
                    if (level instanceof ServerLevel serverLevel) {
                        spawnParticleRing(serverLevel, player, ParticleTypes.POOF, 8.0, 160);
                        spawnExplosionEffect(player);
                        playExplosionSound(player, 0.6F);
                    }

                    for (LivingEntity target : currentTargets) {
                        Vec3 blastDir = target.position().subtract(player.position()).normalize();

                        target.setDeltaMovement(blastDir.x * 1.1, 0.4, blastDir.z * 1.1);

                        target.hurtMarked = true;
                        target.hurt(source, 12.0f);
                    }
                });
            }, 800, TimeUnit.MILLISECONDS);
        });
        return true;
    }
}