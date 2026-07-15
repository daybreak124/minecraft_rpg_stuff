package net.cold.coldsmod.capabilities_and_blessings.effects;

import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusCapabilityProvider;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.PlayerBonusCache;
import net.cold.coldsmod.capabilities_and_blessings.registry.ModEffects;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils.spawnParticleBurst;
import static net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils.spawnParticleRing;
import static net.cold.coldsmod.network.SoulSeverancePacket.applyCollectorStacks;

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

    public static void executeVortex(ServerLevel level, Player player, int remainingTicks, int maxTicks, DamageSource source, Vec3 hitPos, boolean debuff, boolean buff) {
        if (player == null || !player.isAlive() || player.isRemoved()) return;

        AABB area = new AABB(hitPos.x - 5, hitPos.y - 5, hitPos.z - 5,
                hitPos.x + 5, hitPos.y + 5, hitPos.z + 5);

        List<LivingEntity> nearby = level.getEntitiesOfClass(LivingEntity.class, area,
                e -> e != null && e.isAlive() &&
                        e.distanceToSqr(hitPos.x, hitPos.y, hitPos.z) <= 25 &&
                        player.hasLineOfSight(e) &&
                        ((e instanceof Enemy && !(e instanceof net.minecraft.world.entity.NeutralMob)) ||
                                (e instanceof net.minecraft.world.entity.NeutralMob n && n.isAngryAt(player)) ||
                                (e instanceof Mob m && m.getTarget() == player))
        );

        int numOfMobs = 0;


        for (LivingEntity mob : nearby) {

            // Stronger pull if further
            Vec3 diff = hitPos.subtract(mob.position());
            Vec3 pullVec = diff.scale(0.125d);

            mob.setDeltaMovement(mob.getDeltaMovement().add(pullVec));
            mob.hurtMarked = true;

            if (remainingTicks % 1000 == 0 && remainingTicks != maxTicks) {
                Vec3 motion = mob.getDeltaMovement();
                mob.hurt(source, 2.75f);
                mob.setDeltaMovement(motion);
                numOfMobs++;

                spawnParticleBurst(mob, ParticleTypes.SOUL);

                if (debuff) {
                    mob.addEffect(new MobEffectInstance(ModEffects.RIPPED_SOUL.get(), 80, 0, false, true, false));
                }
            }
        }

        if (buff && numOfMobs > 0) {
            applyCollectorStacks(player, numOfMobs);
        }

        if (remainingTicks % 1000 == 0) {
            spawnParticleRing(level, hitPos, ParticleTypes.SOUL_FIRE_FLAME, 5, 100);

            if (remainingTicks != maxTicks) {
                level.playSound(null, hitPos.x, hitPos.y, hitPos.z, SoundEvents.SOUL_ESCAPE, SoundSource.PLAYERS, 7.0F, 1.0F);
            }
        }

        if (remainingTicks >= 200) {
            EXECUTOR.schedule(() -> {
                level.getServer().execute(() -> {
                    executeVortex(level, player, remainingTicks - 200, maxTicks, source, hitPos, debuff, buff);
                });
            }, 200, TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}