package net.cold.coldsmod.blessingbonuses.neweffects;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

import static net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils.spawnParticleRing;

public class FocusedEnergyReady extends MobEffect {

    public FocusedEnergyReady() {
        super(MobEffectCategory.NEUTRAL, 0xFF0000); // category + color
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false; // only trigger via events
    }

    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        if (!(event.getProjectile() instanceof AbstractArrow arrow)) return;
        if (!(arrow.getOwner() instanceof Player player)) return;
        if (!arrow.getPersistentData().getBoolean("focused_energy_arrow")) return;

        Level level = arrow.level();

        HitResult hit = event.getRayTraceResult();
        if (hit == null) return;

        Vec3 hitVec = hit.getLocation();

        // --- EXPLOSION LOGIC ---
        double explosionRadius = 5.0;
        double distance = player.position().distanceTo(hitVec);

        if (distance <= explosionRadius) {
            // 1. Calculate the Vector from impact to player center
            Vec3 playerVec = player.position().add(0, 0.5, 0);
            Vec3 rawDir = playerVec.subtract(hitVec);

            // 2. Calculate "Power" based on proximity (1.0 at center, 0.0 at edge)
            double ratio = 1.0 - (distance / explosionRadius);

            // 3. Set your Base Strengths
            double horizontalPower = 2.0;
            double verticalPower = 1.5;

            // 4. Calculate final velocity
            // We normalize the direction so the base power is consistent, then multiply by ratio
            Vec3 launchDir = rawDir.normalize();
            double velX = launchDir.x * horizontalPower * ratio;
            double velZ = launchDir.z * horizontalPower * ratio;
            double velY = verticalPower * ratio; // The closer you are, the higher you go

            player.setDeltaMovement(new Vec3(velX, velY, velZ));
            player.fallDistance = 0;
            player.hurtMarked = true;
        }

        if (level instanceof ServerLevel slevel) {
            EffectUtils.spawnExplosionAt(slevel, hitVec);
            EffectUtils.playExplosionSoundAt(level, hitVec, 0.5F);
            spawnParticleRing(slevel, hitVec, ParticleTypes.POOF, 5.0, 100);
        }

        double searchRadius = 5.0;
        AABB explosionBox = new AABB(
                hitVec.x - searchRadius, hitVec.y - searchRadius, hitVec.z - searchRadius,
                hitVec.x + searchRadius, hitVec.y + searchRadius, hitVec.z + searchRadius
        );
        double radiusSq = 25.0;

        List<LivingEntity> entities = level.getEntitiesOfClass(
                LivingEntity.class,
                explosionBox,
                e -> {
                    if (!(e instanceof Enemy) || !e.isAlive() || e.isInvulnerable()) return false;

                    if (level.clip(new ClipContext(hitVec, e.getEyePosition(), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, e)).getType().equals(HitResult.Type.MISS)) {
                        double dx = e.getX() - hitVec.x;
                        double dy = e.getY() - hitVec.y;
                        double dz = e.getZ() - hitVec.z;

                        return (dx * dx + dy * dy + dz * dz) <= radiusSq;
                    }
                    return false;
                }
        );

        for (LivingEntity target : entities) {
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 6));
        }

        player.removeEffect(ModEffects.FOCUSED_ENERGY_READY.get());
        player.addEffect(new MobEffectInstance(ModEffects.FOCUSED_ENERGY_CD.get(), 400, 0, false, false, true));
    }
}
