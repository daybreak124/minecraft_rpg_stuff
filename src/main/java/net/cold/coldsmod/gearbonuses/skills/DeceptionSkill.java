package net.cold.coldsmod.gearbonuses.skills;

import net.cold.coldsmod.gearbonuses.effects.ModEffects;
import net.cold.coldsmod.stat.ItemRarityUtils;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class DeceptionSkill {

    @SubscribeEvent
    public static void onArrowSpawn(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof AbstractArrow arrow)) return;
        if (!(arrow.getOwner() instanceof Player player)) return;

        if (!player.getPersistentData().getBoolean("deception_applied")) return;


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
        if (!(event.getSource().getEntity() instanceof Player)) return;
        Player player = (Player) event.getSource().getEntity();
        if (!(event.getSource().getDirectEntity() instanceof Projectile proj)) return;
        if (!proj.getPersistentData().getBoolean("deception_tagged")) return;

        if (!(event.getEntity() instanceof LivingEntity)) return;
        LivingEntity target = (LivingEntity) event.getEntity();

        if (!(event.getSource().getDirectEntity() instanceof Projectile)) return;

        if (!player.hasEffect(ModEffects.DECEPTION_READY.get())) return;
        if (player.hasEffect(ModEffects.CLAIRVOYANCE_READY.get())) return;


        if (!player.getPersistentData().getBoolean("deception_applied")) return;

        double range = 9.0;

        List<LivingEntity> nearby = target.level().getEntitiesOfClass(
                LivingEntity.class,
                target.getBoundingBox().inflate(range),
                e -> (e instanceof Monster) && e != target
        );

        for (LivingEntity entity : nearby) {
            if (entity instanceof Mob) {
                ((Mob) entity).setTarget(target);
            }
        }

        player.level().playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.AMETHYST_BLOCK_RESONATE,
                SoundSource.PLAYERS,
                3.0F,
                1.0F
        );

        player.removeEffect(ModEffects.DECEPTION_READY.get());
        player.addEffect(new MobEffectInstance(ModEffects.DECEPTION_COOLDOWN.get(), 20 * 14, 0, false, false));
    }
}
