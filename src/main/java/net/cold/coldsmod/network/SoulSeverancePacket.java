package net.cold.coldsmod.network;

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
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;
import static net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils.*;

public class SoulSeverancePacket {

    public SoulSeverancePacket() {}

    public SoulSeverancePacket(FriendlyByteBuf buf) {}

    public void encode(FriendlyByteBuf buf) {}
    public static SoulSeverancePacket decode(FriendlyByteBuf buf) { return new SoulSeverancePacket(); }

    private static final ScheduledExecutorService SCHEDULER = newSingleThreadScheduledExecutor();

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;

            Level level = player.level();
            ServerLevel sLevel = (ServerLevel) player.level();

            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);

            boolean heal = cache.isHealSeverance();
            if (heal) {
                player.addEffect(new MobEffectInstance(ModEffects.SOUL_SEVERANCE_COOLDOWN.get(), 600, 0, false, false, true));
            } else {
                player.addEffect(new MobEffectInstance(ModEffects.SOUL_SEVERANCE_COOLDOWN.get(), 400, 0, false, false, true));
            }

            player.removeEffect(ModEffects.SOUL_SEVERANCE_READY.get());

            Holder<DamageType> meleeType = level.registryAccess()
                    .registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(ModDamageTypes.DOT_DAMAGE);
            DamageSource source = new DamageSource(meleeType, null, player);

            boolean buff = cache.isVortexSeveranceEnhanced();

            executeSS(sLevel, player, 4000, 4000, source, buff, heal);

        });
        return true;
    }

    public static void executeSS(ServerLevel level, ServerPlayer player, int remainingTicks, int maxTicks, DamageSource source, boolean buff, boolean heal) {
        if (player == null || !player.isAlive() || player.isRemoved()) return;

        if (remainingTicks == maxTicks && maxTicks == 4000) {
            BonusRegistry.process(player, null, player.level(), BonusTrigger.BLESSING_ACTIVATION);
        }

        if (heal) {
            if (remainingTicks % 1000 == 0) {
                spawnParticleRing(level, player, ParticleTypes.COMPOSTER, 6, 120);

                if (remainingTicks >= 1000) {
                    SCHEDULER.schedule(() -> {
                        level.getServer().execute(() -> {
                            executeSS(level, player, remainingTicks - 1000, maxTicks, source, buff, true);
                        });
                    }, 1000, TimeUnit.MILLISECONDS);
                }

                if (remainingTicks != maxTicks) {
                    double radius = 6d;
                    AABB area = player.getBoundingBox().inflate(radius);
                    double radiusSq = radius * radius;
                    List<LivingEntity> allies = level.getEntitiesOfClass(
                            LivingEntity.class,
                            area,
                            e -> {
                                if (!isAlly(e) || !player.hasLineOfSight(e)) return false;
                                double dx = e.getX() - player.getX();
                                double dz = e.getZ() - player.getZ();
                                return (dx * dx + dz * dz) <= radiusSq;
                            }
                    );
                    for (LivingEntity ally : allies) {
                        ally.heal(0.75f);
                        EffectUtils.spawnComposterBurst(ally);
                        EffectUtils.playHealSound(ally);
                    }
                }
            }

        } else {
            if (remainingTicks % 1000 == 0) {

                EffectUtils.playSound(player, SoundEvents.SOUL_ESCAPE, 2.0F, 1.2F);
                spawnParticleRing(level, player, ParticleTypes.SOUL_FIRE_FLAME, 9, 180);

                if (remainingTicks != maxTicks) {
                    List<LivingEntity> nearby = level.getEntitiesOfClass(
                            LivingEntity.class,
                            player.getBoundingBox().inflate(9),
                            e -> e != null && e.isAlive() && e.distanceToSqr(player) <= 81 &&
                                    player.hasLineOfSight(e) &&
                                    ((e instanceof Enemy && !(e instanceof NeutralMob)) ||
                                            (e instanceof NeutralMob n && n.isAngry()) ||
                                            (e instanceof Mob m && m.getTarget() != null))
                    );

                    int numOfMobs = 0;
                    for (LivingEntity mob : nearby) {

                        Vec3 diff = player.position().subtract(mob.position());
                        double distance = diff.length();

                        if (distance > 3.4) {
                            double strength = 0.12d;
                            Vec3 pullVec = diff.scale(strength);

                            mob.setDeltaMovement(mob.getDeltaMovement().add(pullVec));
                            mob.hurtMarked = true;
                        }

                        Vec3 motion = mob.getDeltaMovement();
                        mob.hurt(source, 2.5f);
                        mob.setDeltaMovement(motion);
                        spawnParticleBurst(mob, ParticleTypes.SOUL);
                        numOfMobs++;
                    }

                    if (buff && numOfMobs > 0) {
                        applyCollectorStacks(player, numOfMobs);
                    }
                }
            }
            if (remainingTicks >= 200) {
                SCHEDULER.schedule(() -> {
                    level.getServer().execute(() -> {
                        executeSS(level, player, remainingTicks - 200, maxTicks, source, buff, false);
                    });
                }, 200, java.util.concurrent.TimeUnit.MILLISECONDS);
            }
        }
    }

    public static void applyCollectorStacks(Player player, int amount) {
        MobEffectInstance effect = player.getEffect(ModEffects.COLLECTOR.get());
        int current = (effect != null) ? effect.getAmplifier() : -1;
        player.addEffect(new MobEffectInstance(ModEffects.COLLECTOR.get(), 50, Math.min(19, current + amount), false, false, true));
    }
}