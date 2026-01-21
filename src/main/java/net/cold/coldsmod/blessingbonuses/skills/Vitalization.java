package net.cold.coldsmod.blessingbonuses.skills;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ItemRarityUtils;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.LivingEntity;
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
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils.isAlly;
import static net.cold.coldsmod.stat.AttributeApplier.getScaledValue;

public class Vitalization {

    @SubscribeEvent
    public static void onArrowSpawn(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof AbstractArrow arrow)) return;
        if (!(arrow.getOwner() instanceof Player player)) return;
        if (!player.getPersistentData().getBoolean("life_touch_applied")) return;


        if (player.level().isClientSide()) return;

        ItemStack main = player.getMainHandItem();
        ItemStack off  = player.getOffhandItem();

        boolean mainIsBow = "bow".equals(ItemRarityUtils.getItemType(main));
        boolean mainIsCrossbow = "crossbow".equals(ItemRarityUtils.getItemType(main));
        boolean offIsBow = "bow".equals(ItemRarityUtils.getItemType(off));
        boolean isBow = mainIsBow || (offIsBow && !mainIsCrossbow); if (!isBow) return;


        arrow.getPersistentData().putBoolean("life_touch_tagged", true);
    }


    @SubscribeEvent
    public static void onArrowHit(LivingHurtEvent event) {
        if (!(event.getSource().getDirectEntity() instanceof Projectile proj)) return;
        if (!proj.getPersistentData().getBoolean("life_touch_tagged")) return;
        if (!(event.getSource().getEntity() instanceof Player player)) return;

        LivingEntity target = event.getEntity();
        if (!isAlly(target)) return;

        if (player.level().isClientSide()) return;

        double finalDamage = event.getAmount();
        if (!proj.getPersistentData().contains("ScaledDamage")) {
            double scaledPotency = getScaledValue(player, ModAttributes.PROJECTILE_POTENCY.get(), ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get());

            double boosted = finalDamage * (1.0 + (scaledPotency / 100.0));
            proj.getPersistentData().putDouble("ScaledDamage", boosted);
        }

        finalDamage = proj.getPersistentData().getDouble("ScaledDamage");

        double scaledAccuracy = getScaledValue(player, ModAttributes.PROJECTILE_ACCURACY.get(), ModAttributes.PROJECTILE_ACCURACY_MULTIPLIER.get());
        double totalCritChance = scaledAccuracy + 10.0;
        double totalCritDamage = getScaledValue(player, ModAttributes.PROJECTILE_PRECISION.get(), ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get());

        if (player.getRandom().nextDouble() < (totalCritChance / 100.0)) {
            finalDamage *= (1.5 + (totalCritDamage / 100.0));

            player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.PLAYER_ATTACK_CRIT, SoundSource.PLAYERS, 1.0F, 1.0F);
        }

        player.getPersistentData().putInt("hawkeye", 0);
        player.removeEffect(ModEffects.HAWKEYE.get());

        float healAmount = (float) (finalDamage * 0.35);

        EffectUtils.playHealSound(target);
        EffectUtils.spawnComposterBurst(target);

        double healIncrease = getScaledValue(player,
                ModAttributes.RESTORATION.get(),
                ModAttributes.RESTORATION_MULTIPLIER.get());

        target.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20*5, 0));
        target.heal((float) (healAmount * (1.0 + (healIncrease / 100.0))));
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        if (!(event.getProjectile() instanceof Arrow arrow)) return;
        if (!arrow.getPersistentData().getBoolean("life_touch_tagged")) return;
        if (!(arrow.getOwner() instanceof Player player) || !player.getPersistentData().getBoolean("life_touch_applied")) return;

        Level level = arrow.level();


        if (!(player.hasEffect(ModEffects.LIFE_TOUCH_READY.get()))) return;

        HitResult hit = event.getRayTraceResult();
        if (hit == null || hit.getType() != HitResult.Type.BLOCK) return;

        Vec3 hitVec = hit.getLocation();

        AreaEffectCloud cloud = new AreaEffectCloud(level,
                hitVec.x,
                hitVec.y,
                hitVec.z);

        float range = (float) (3.0f * (1.0f + (AttributeApplier.getScaledValue(player, ModAttributes.AMPLIFICATION.get(), ModAttributes.AMPLIFICATION_MULTIPLIER.get()) / 100.0f)));

        cloud.setRadius(range);
        cloud.setDuration(20*10);
        cloud.setRadiusPerTick(-0.010f);
        cloud.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 21, 0));
        cloud.setWaitTime(0);
        cloud.setFixedColor(0x008000);

        cloud.setOwner(player);

        level.addFreshEntity(cloud);

        player.level().playSound(
                null,
                hitVec.x, hitVec.y, hitVec.z,
                SoundEvents.SPLASH_POTION_BREAK,
                SoundSource.PLAYERS,
                0.6F,
                1.0F
        );
        arrow.discard();

        arrow.getPersistentData().putBoolean("life_touch_tagged", false);
        player.removeEffect(ModEffects.LIFE_TOUCH_READY.get());
        player.addEffect(new MobEffectInstance(ModEffects.LIFE_TOUCH_COOLDOWN.get(), 20*22, 0, false, false, true));
    }
}
