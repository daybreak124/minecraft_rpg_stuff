package net.cold.coldsmod.gearbonuses.skills;

import net.cold.coldsmod.gearbonuses.effects.ModEffects;
import net.cold.coldsmod.stat.ItemRarityUtils;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Vitalization {

    @SubscribeEvent
    public static void onArrowSpawn(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof AbstractArrow arrow)) return;
        if (!(arrow.getOwner() instanceof Player player)) return;

        if (!player.getPersistentData().getBoolean("life_touch_applied")) return;

        ItemStack main = player.getMainHandItem();
        ItemStack off  = player.getOffhandItem();

        boolean mainIsBow = "bow".equals(ItemRarityUtils.getItemType(main));
        boolean mainIsCrossbow = "crossbow".equals(ItemRarityUtils.getItemType(main));
        boolean offIsBow = "bow".equals(ItemRarityUtils.getItemType(off));
        boolean isBow = mainIsBow || (offIsBow && !mainIsCrossbow); if (!isBow) return;


        arrow.getPersistentData().putBoolean("life_touch_tagged", true);
    }


    @SubscribeEvent
    public static void onArrowHit(LivingDamageEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!(event.getEntity() instanceof Player target)) return;
        if (!(event.getSource().getDirectEntity() instanceof Projectile proj)) return;
        if (!proj.getPersistentData().getBoolean("life_touch_tagged")) return;

        // --- Apply projectile scaling like your other projectile code ---
        double finalDamage = event.getAmount();

        if (!proj.getPersistentData().contains("ScaledDamage")) {
            double boosted = finalDamage * (1 + player.getPersistentData().getDouble("projectileDamageIncrease") / 100.0);
            proj.getPersistentData().putDouble("ScaledDamage", boosted);
        }

        finalDamage = proj.getPersistentData().getDouble("ScaledDamage");

        // Projectile crit
        if (player.getRandom().nextDouble() < player.getPersistentData().getDouble("projectileCritChanceIncrease") / 100.0) {
            finalDamage *= 1.5 + player.getPersistentData().getDouble("projectileCritDamageIncrease") / 100.0;

            // Play crit sound
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.PLAYER_ATTACK_CRIT, SoundSource.PLAYERS, 1.0F, 1.0F);
        }

        // Reset Hawkeye stacks
        player.getPersistentData().putInt("hawkeye", 0);
        player.removeEffect(ModEffects.HAWKEYE.get());

        float healAmount = (float) (finalDamage * 0.4);

        player.level().playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.EXPERIENCE_ORB_PICKUP,
                SoundSource.PLAYERS,
                0.5F,
                1.0F
        );

        target.level().playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.EXPERIENCE_ORB_PICKUP,
                SoundSource.PLAYERS,
                0.5F,
                1.0F
        );

        target.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20*4, 0));
        target.heal(healAmount);
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        if (event.getProjectile() == null) return;

        if (!(event.getProjectile() instanceof Arrow arrow)) return;

        Level level = arrow.level();
        if (level.isClientSide) return; // server only

        // Shooter must be a player
        Entity owner = arrow.getOwner();
        if (!(owner instanceof Player player)) return;

        if (!player.getPersistentData().getBoolean("life_touch_applied")) return;
        if (!arrow.getPersistentData().getBoolean("life_touch_tagged")) return;


        if (!(player.hasEffect(ModEffects.LIFE_TOUCH_READY.get()))) return;

        // Must be a block hit
        HitResult hit = event.getRayTraceResult();
        if (hit == null || hit.getType() != HitResult.Type.BLOCK) return;

        Vec3 hitVec = hit.getLocation(); // precise X/Y/Z of impact

        // --- Create lingering heal cloud at impact location ---
        AreaEffectCloud cloud = new AreaEffectCloud(level,
                hitVec.x,
                hitVec.y,
                hitVec.z);

        cloud.setRadius(3.0f);
        cloud.setDuration(120);            // 6s
        cloud.setRadiusPerTick(-0.015f);    // shrink over time
        cloud.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 21, 0)); // 40 ticks = 2 seconds
        cloud.setWaitTime(0);
        cloud.setFixedColor(0x008000);

        // If you want the cloud to be owned by the shooter (useful for team checks later):
        cloud.setOwner(player);

        level.addFreshEntity(cloud);

        // Remove the arrow so it doesn't stick around
        arrow.discard();

        arrow.level().playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.LINGERING_POTION_THROW,
                SoundSource.PLAYERS,
                0.6F,
                1.0F
        );

        player.removeEffect(ModEffects.LIFE_TOUCH_READY.get());
        player.addEffect(new MobEffectInstance(ModEffects.LIFE_TOUCH_COOLDOWN.get(), 20*15, 0, false, false));
    }
}
