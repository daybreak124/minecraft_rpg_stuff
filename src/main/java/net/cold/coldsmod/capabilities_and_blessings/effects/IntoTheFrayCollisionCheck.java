package net.cold.coldsmod.capabilities_and_blessings.effects;

import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusRegistry;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusTrigger;
import net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils;
import net.cold.coldsmod.capabilities_and_blessings.registry.ModEffects;
import net.cold.coldsmod.damage_types.ModDamageTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Collections;
import java.util.List;

public class IntoTheFrayCollisionCheck extends MobEffect {
    public IntoTheFrayCollisionCheck() {
        super(MobEffectCategory.NEUTRAL, 0x00000);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 4 == 0;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level().isClientSide()) return;
        if (!(entity instanceof Player player)) return;
        checkCollisions(player, amplifier);
    }

    public static void checkCollisions(Player player, int amplifier) {
        Level level = player.level();
        double collisionRadiusSq = 4;

        List<LivingEntity> nearby = level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(2.3));
        boolean hasCollided = false;

        for (LivingEntity entity : nearby) {
            if (entity == player || !isValidTarget(entity)) continue;

            if (entity.distanceToSqr(player) <= collisionRadiusSq) {
                hasCollided = true;
                break;
            }
        }

        if (hasCollided) {
            triggerExplosion(player, nearby, amplifier + 1);

            player.addEffect(new MobEffectInstance(ModEffects.INTO_THE_FRAY_COOLDOWN.get(), 180, 0));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 1));
            player.removeEffect(ModEffects.INTO_THE_FRAY_COLLISION_CHECK.get());
            player.removeEffect(ModEffects.INTO_THE_FRAY_ACTIVE.get());
            BonusRegistry.process(player, null, player.level(), BonusTrigger.BLESSING_ACTIVATION);
        }
    }

    public static void triggerExplosion(Player player, List<LivingEntity> nearby, int stackCount) {
        Level level = player.level();
        Holder<DamageType> meleeType = level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(ModDamageTypes.CUSTOM_MELEE_DAMAGE);
        DamageSource source = new DamageSource(meleeType, player, player);


        for (LivingEntity target : nearby) {
            if (target == player || !isValidTarget(target)) continue;

            double distSq = target.distanceToSqr(player);
            if (distSq <= 16.0 && player.hasLineOfSight(target)) {
                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10 * stackCount, 3));

                target.hurt(source, (float) (2 * stackCount));

                // Knockback
                double dx = target.getX() - player.getX();
                double dz = target.getZ() - player.getZ();
                target.knockback(0.4f * stackCount, dx, dz);
            }
        }

        if (level instanceof ServerLevel serverLevel) {
            EffectUtils.spawnExplosionEffect(player);
            EffectUtils.spawnParticleRing(serverLevel, player, ParticleTypes.POOF, 4.0, 60);
            EffectUtils.playExplosionSound(player, 0.7F);
        }
    }

    public static boolean isValidTarget(LivingEntity target) {
        if (!target.isAlive() || target.isInvulnerable()) return false;

        return (target instanceof Enemy && !(target instanceof NeutralMob)) ||
                (target instanceof NeutralMob n && n.isAngry()) ||
                (target instanceof Mob m && m.getTarget() != null);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}