package net.cold.coldsmod.blessingbonuses.neweffects;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.stat.ItemRarityUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

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

        arrow.getPersistentData().putBoolean("focused_energy_arrow", true);
    }

    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        if (event.getProjectile() == null) return;

        if (!(event.getProjectile() instanceof AbstractArrow arrow)) return;

        Level level = arrow.level();
        if (level.isClientSide) return;

        Entity owner = arrow.getOwner();
        if (!(owner instanceof Player player)) return;

        if (!arrow.getPersistentData().getBoolean("focused_energy_arrow")) return;

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
           EffectUtils.playExplosionSound(player);
        }

        player.removeEffect(ModEffects.FOCUSED_ENERGY_READY.get());
        player.addEffect(new MobEffectInstance(ModEffects.FOCUSED_ENERGY_CD.get(), 20*10, 0, false, false, true));
    }
}
