package net.cold.coldsmod.blessingbonuses.neweffects;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.stat.ItemRarityUtils;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
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
    public static void onArrowSpawn(EntityJoinLevelEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        if (!(event.getEntity() instanceof AbstractArrow arrow)) return;
        if (!(arrow.getOwner() instanceof Player player)) return;
        if (!player.hasEffect(ModEffects.FOCUSED_ENERGY_READY.get())) return;

        ItemStack main = player.getMainHandItem();
        ItemStack off  = player.getOffhandItem();

        boolean mainIsBow = "bow".equals(ItemRarityUtils.getItemType(main));
        boolean mainIsCrossbow = "crossbow".equals(ItemRarityUtils.getItemType(main));
        boolean offIsCrossbow = "crossbow".equals(ItemRarityUtils.getItemType(off));

        boolean isCrossbow = mainIsCrossbow || (offIsCrossbow && !mainIsBow);
        if (!isCrossbow) return;

    }

    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        if (!(event.getProjectile() instanceof AbstractArrow arrow)) return;
        if (!(arrow.getOwner() instanceof Player player)) return;
        if (!arrow.getPersistentData().getBoolean("focused_energy_arrow")) return;

        Level level = arrow.level();

        HitResult hit = event.getRayTraceResult();
        if (hit == null) return;

        Vec3 hitVec = hit.getLocation();
        arrow.discard();

        Vec3 playerPos = player.position();
        Vec3 launchDir = playerPos.subtract(hitVec).normalize();

        double verticalPower = 1.3;
        double horizontalPower = 1.3;

        player.setDeltaMovement(new Vec3(launchDir.x * horizontalPower, verticalPower, launchDir.z * horizontalPower));

        player.hurtMarked = true;

        if (level instanceof ServerLevel) {
           EffectUtils.spawnExplosionOnFeet(player);
           EffectUtils.playExplosionSound(player, 0.5F);

            double radiusSq = 25.0;
            List<LivingEntity> entities = level.getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(5.0, 5.0, 5.0),
                    e -> {
                        if (!(e instanceof Enemy) || !e.isAlive() || e.isInvulnerable() || !player.hasLineOfSight(e)) return false;
                        double dx = e.getX() - player.getX();
                        double dz = e.getZ() - player.getZ();
                        return (dx * dx + dz * dz) <= radiusSq;
                    }
            );

            for (LivingEntity target : entities) {
                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 4));
            }
            spawnParticleRing((ServerLevel) level, player, ParticleTypes.POOF, 5.0, 100);
        }

        player.removeEffect(ModEffects.FOCUSED_ENERGY_READY.get());
        player.addEffect(new MobEffectInstance(ModEffects.FOCUSED_ENERGY_CD.get(), 20*6, 0, false, false, true));
    }
}
