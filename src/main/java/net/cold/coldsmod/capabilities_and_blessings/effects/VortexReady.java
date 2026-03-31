package net.cold.coldsmod.capabilities_and_blessings.effects;

import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusCapabilityProvider;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.PlayerBonusCache;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils.spawnParticleBurst;
import static net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils.spawnParticleRing;

public class VortexReady extends MobEffect {
    public VortexReady() {
        super(MobEffectCategory.NEUTRAL, 0x00000);
    }

    private static final ScheduledExecutorService EXECUTOR = Executors.newSingleThreadScheduledExecutor();

    @Override
    public void addAttributeModifiers(@NotNull LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        PlayerBonusCache cache = pLivingEntity.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
        cache.setVortexReady(true);
    }

    @Override
    public void removeAttributeModifiers(@NotNull LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        PlayerBonusCache cache = pLivingEntity.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
        cache.setVortexReady(false);
    }

    public static void executeVortex(ServerLevel level, Player player, int delay, DamageSource source, Vec3 hitPos) {
        EXECUTOR.schedule(() -> {
            level.getServer().execute(() -> {
                if (player != null && player.isAlive() && !player.isRemoved()) {
                    level.playSound(null, hitPos.x, hitPos.y, hitPos.z, SoundEvents.SOUL_ESCAPE, SoundSource.PLAYERS, 7.0F, 1.0F);
                    spawnParticleRing(level, hitPos, ParticleTypes.SOUL_FIRE_FLAME, 6, 120);

                    AABB area = new AABB(hitPos.x - 6, hitPos.y - 6, hitPos.z - 6,
                            hitPos.x + 6, hitPos.y + 6, hitPos.z + 6);

                    List<LivingEntity> nearby = level.getEntitiesOfClass(LivingEntity.class, area);

                    for (LivingEntity mob : nearby) {
                        if (mob == player || mob instanceof Player) continue;

                        double dx = hitPos.x - mob.getX();
                        double dy = hitPos.y - mob.getY();
                        double dz = hitPos.z - mob.getZ();
                        double distSq = dx * dx + dy * dy + dz * dz;

                        if (distSq > 36.0 || distSq < 0.25) continue;

                        double distance = Math.sqrt(distSq);

                        double pullFactor = (distance - 0.5) / distance;
                        double targetX = mob.getX() + (dx * pullFactor);
                        double targetY = mob.getY() + (dy * pullFactor);
                        double targetZ = mob.getZ() + (dz * pullFactor);

                        mob.moveTo(targetX, targetY, targetZ, mob.getYRot(), mob.getXRot());

                        mob.hurtMarked = true;

                        if (player.hasLineOfSight(mob)) {
                            mob.hurt(source, 4.0f);
                            mob.setDeltaMovement(Vec3.ZERO);
                            spawnParticleBurst(mob, ParticleTypes.SOUL);
                        }
                    }
                }
            });
        }, delay, TimeUnit.SECONDS);
    }
}