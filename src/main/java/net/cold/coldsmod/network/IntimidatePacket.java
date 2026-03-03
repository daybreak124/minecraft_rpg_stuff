package net.cold.coldsmod.network;

import net.cold.coldsmod.ModSounds;
import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.damage.ModDamageTypes;
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
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils.*;

public class IntimidatePacket {

    public IntimidatePacket() {}
    public IntimidatePacket(FriendlyByteBuf buffer) {}
    public static IntimidatePacket decode(FriendlyByteBuf buf) { return new IntimidatePacket(); }
    public void encode(FriendlyByteBuf buffer) {}

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
            player.removeEffect(ModEffects.INTIMIDATING_PRESENCE.get());
            level.playSound(
                    null, player.getX(), player.getY(), player.getZ(),
                    ModSounds.INTIMIDATING_PRESENCE.get(), SoundSource.PLAYERS,
                    0.6F, 1.0F
            );

            Holder<DamageType> meleeType = level.registryAccess()
                    .registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(ModDamageTypes.CUSTOM_MELEE_DAMAGE);

            DamageSource source = new DamageSource(meleeType, player, player);

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

                CompletableFuture.delayedExecutor(300, TimeUnit.MILLISECONDS).execute(() -> {
                    player.getServer().execute(() -> {
                        if (target instanceof Mob mob && target.isAlive()) {
                            mob.setNoAi(true);
                            target.setNoGravity(true);

                            // 1. Define the Start (Player's heart/chest area)
                            double startX = player.getX();
                            double startY = player.getY() + 1.0;
                            double startZ = player.getZ();

                            // 2. Define the End (Target's chest area)
                            double endX = target.getX();
                            double endY = target.getY() + (target.getBbHeight() / 2.0);
                            double endZ = target.getZ();

                            // 3. Calculate the delta (distance components)
                            double dx = endX - startX;
                            double dy = endY - startY;
                            double dz = endZ - startZ;

                            double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
                            int particleCount = (int) (distance * 4);

                            // 4. Draw the line
                            for (int i = 0; i < particleCount; i++) {
                                double ratio = (double) i / particleCount;
                                ((ServerLevel)level).sendParticles(ParticleTypes.ELECTRIC_SPARK,
                                        startX + (dx * ratio),
                                        startY + (dy * ratio),
                                        startZ + (dz * ratio),
                                        1, 0, 0, 0, 0.0);
                            }
                        }
                    });
                });

                CompletableFuture.delayedExecutor(600, TimeUnit.MILLISECONDS).execute(() -> {
                    player.getServer().execute(() -> {
                        if (!target.isAlive()) return;

                        if (target instanceof Mob mob) {
                            if (!mob.getPersistentData().contains("freeze_timer")) {
                                mob.setNoAi(false);
                                mob.getPersistentData().putBoolean("intimidate_stun_applied", true);
                            }
                            if (!mob.getType().is(Tags.EntityTypes.BOSSES) || mob instanceof Warden) {
                                mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 8, 7, false, false));
                                mob.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 8, 255, false, false));
                            }
                        }

                        target.setNoGravity(false);
                        target.fallDistance = 0;

                        Vec3 targetPos = target.position();
                        Vec3 destination = player.getEyePosition();

                        Vec3 toPlayer = destination.subtract(targetPos);
                        double distance = toPlayer.length();

                        if (distance < 0.4) {
                            target.setDeltaMovement(Vec3.ZERO);
                            target.hurtMarked = true;
                            return;
                        }

                        // 1. Define the Start (Player's heart/chest area)
                        double startX = player.getX();
                        double startY = player.getY() + 1.0;
                        double startZ = player.getZ();

                        // 2. Define the End (Target's chest area)
                        double endX = target.getX();
                        double endY = target.getY() + (target.getBbHeight() / 2.0);
                        double endZ = target.getZ();

                        // 3. Calculate the delta (distance components)
                        double dx = endX - startX;
                        double dy = endY - startY;
                        double dz = endZ - startZ;

                        int particleCount = (int) (distance * 4);

                        // 4. Draw the line
                        for (int i = 0; i < particleCount; i++) {
                            double ratio = (double) i / particleCount;
                            ((ServerLevel)level).sendParticles(ParticleTypes.CRIT,
                                    startX + (dx * ratio),
                                    startY + (dy * ratio),
                                    startZ + (dz * ratio),
                                    1, 0, 0, 0, 0.0);
                        }

                        double maxSpeed = 1.3;
                        double speed = Math.min(distance * 0.35, maxSpeed);

                        Vec3 desired = toPlayer.normalize().scale(speed);

                        Vec3 current = target.getDeltaMovement();
                        Vec3 newMotion = current.scale(0.5).add(desired.scale(0.5));

                        target.setDeltaMovement(newMotion);
                        target.hurtMarked = true;
                    });
                });


                CompletableFuture.delayedExecutor(999, TimeUnit.MILLISECONDS).execute(() -> {
                    player.getServer().execute(() -> {
                        if (!target.isAlive()) return;

                        if (target instanceof Mob mob && !mob.getPersistentData().contains("freeze_timer")) {
                            mob.setNoAi(false);
                            mob.getPersistentData().remove("intimidate_stun_applied");
                        }
                    });
                });
            }

            CompletableFuture.delayedExecutor(1000, TimeUnit.MILLISECONDS).execute(() -> {
                player.getServer().execute(() -> {
                    List<LivingEntity> currentTargets = player.level().getEntitiesOfClass(
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
            });
        });
        return true;
    }
}