package net.cold.coldsmod.blessingbonuses.skills;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.stat.ItemRarityUtils;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class DeceptionSkill {

    @SubscribeEvent
    public static void onArrowSpawn(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof AbstractArrow arrow)) return;
        if (!(arrow.getOwner() instanceof Player player)) return;
        if (player.level().isClientSide) return;

        ItemStack main = player.getMainHandItem();
        ItemStack off = player.getOffhandItem();

        boolean mainIsBow = "bow".equals(ItemRarityUtils.getItemType(main));
        boolean mainIsCrossbow = "crossbow".equals(ItemRarityUtils.getItemType(main));
        boolean offIsBow = "bow".equals(ItemRarityUtils.getItemType(off));
        boolean isBow = mainIsBow || (offIsBow && !mainIsCrossbow);
        if (!isBow) return;

        arrow.getPersistentData().putBoolean("deception_tagged", true);
    }

    @SubscribeEvent
    public static void onArrowHit(LivingDamageEvent event) {
        if (!(event.getSource().getDirectEntity() instanceof Projectile proj)) return;
        if (!proj.getPersistentData().getBoolean("deception_tagged")) return;
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (player.level().isClientSide()) return;

        if (!(event.getEntity() instanceof LivingEntity)) return;
        LivingEntity target = event.getEntity();

        if (!(event.getSource().getDirectEntity() instanceof Projectile)) return;

        if (!player.hasEffect(ModEffects.DECEPTION_READY.get())) return;

        double range = 9.0;

        List<LivingEntity> nearby = target.level().getEntitiesOfClass(
                LivingEntity.class,
                target.getBoundingBox().inflate(range),
                e -> e instanceof Enemy && e != target && !e.getType().is(Tags.EntityTypes.BOSSES) && !(e instanceof Warden)
        );

        for (LivingEntity entity : nearby) {
            if (entity instanceof Mob mob) {
                mob.setTarget(target);
            }
        }

        player.level().playSound(
                null,
                target.getX(), target.getY(), target.getZ(),
                SoundEvents.AMETHYST_BLOCK_RESONATE,
                SoundSource.PLAYERS,
                3.0F,
                1.0F
        );

        player.removeEffect(ModEffects.DECEPTION_READY.get());
        player.addEffect(new MobEffectInstance(ModEffects.DECEPTION_COOLDOWN.get(), 20 * 14, 0, false, false, true));
    }
}
