package net.cold.coldsmod.stat;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CrossbowChargeDrawSpeedTag {

    @SubscribeEvent
    public static void onStartChargingCrossbow(LivingEntityUseItemEvent.Start event) {
        if (!(event.getEntity() instanceof Player player)) return;

        ItemStack stack = event.getItem();
        if (!"crossbow".equals(ItemRarityUtils.getItemType(stack))) return;
        CompoundTag data = player.getPersistentData();
        new AttributeApplier().applyBlessings(player);

        double baseDrawSpeedIncrease = data.getDouble("drawSpeedIncrease");
            stack.getOrCreateTag().putDouble("drawSpeedIncrease", (baseDrawSpeedIncrease));
    }
}
