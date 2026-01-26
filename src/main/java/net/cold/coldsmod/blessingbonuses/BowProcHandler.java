package net.cold.coldsmod.blessingbonuses;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.stat.ItemRarityUtils;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BowProcHandler {

    @SubscribeEvent
    public static void onArrowSpawn(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof AbstractArrow arrow)) return;
        if (event.getLevel().isClientSide()) return;
        if (!(arrow.getOwner() instanceof Player player)) return;

        ItemStack main = player.getMainHandItem();
        ItemStack off  = player.getOffhandItem();

        boolean mainIsBow = "bow".equals(ItemRarityUtils.getItemType(main));
        boolean mainIsCrossbow = "crossbow".equals(ItemRarityUtils.getItemType(main));
        boolean offIsBow = "bow".equals(ItemRarityUtils.getItemType(off));

        boolean isBow = mainIsBow || (offIsBow && !mainIsCrossbow);
        if (!isBow) return;

        if (player.getPersistentData().getBoolean("life_touch_applied")) {
            arrow.getPersistentData().putBoolean("life_touch_tagged", true);
            return;
        }

        if (player.hasEffect(ModEffects.DECEPTION_READY.get())) {
            arrow.getPersistentData().putBoolean("deception_tagged", true);
            return;
        }

        if (player.hasEffect(ModEffects.VORTEX_READY.get())) {
            arrow.getPersistentData().putBoolean("vortex_tagged", true);
        }
    }


}
