package net.cold.coldsmod.blessingbonuses;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.stat.ItemRarityUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CrossbowProcHandler {

    @SubscribeEvent
    public static void onArrowSpawn(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof AbstractArrow arrow)) return;
        if (event.getLevel().isClientSide()) return;
        if (!(arrow.getOwner() instanceof Player player)) return;

        ItemStack main = player.getMainHandItem();
        ItemStack off  = player.getOffhandItem();

        boolean mainIsBow = "bow".equals(ItemRarityUtils.getItemType(main));
        boolean mainIsCrossbow = "crossbow".equals(ItemRarityUtils.getItemType(main));
        boolean offIsCrossbow = "crossbow".equals(ItemRarityUtils.getItemType(off));

        boolean isCrossbow = mainIsCrossbow || (offIsCrossbow && !mainIsBow);
        if (!isCrossbow) return;

        CompoundTag tag = arrow.getPersistentData();

        if (player.hasEffect(ModEffects.EXPLOIT_WEAKNESS_READY.get())) {
            tag.putBoolean("exploit_weakness_arrow", true);
            return;
        }

        if (player.hasEffect(ModEffects.EXPLOSIVE_TENDENCY_STACK.get())) {
            tag.putBoolean("explosive_tendency_tagged", true);
            return;
        }

        if (player.hasEffect(ModEffects.FOCUSED_ENERGY_READY.get())) {
            tag.putBoolean("focused_energy_arrow", true);
        }
    }


}
