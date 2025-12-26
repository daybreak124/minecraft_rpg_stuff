package net.cold.coldsmod.gearbonuses.effects;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.cold.coldsmod.stat.ItemRarityUtils;

public class PickaxeTorch {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        Level world = event.getLevel();
        ItemStack stack = event.getItemStack();

        if (world.isClientSide) return;

        if (!player.getPersistentData().getBoolean("lightbringer_applied")) return;

        ItemStack main = player.getMainHandItem();

        ItemStack off = player.getOffhandItem();
        boolean isShield = "shield".equals(ItemRarityUtils.getItemType(main)) ||
                "shield".equals(ItemRarityUtils.getItemType(off));

        if ((!(stack.getItem() instanceof PickaxeItem)) || isShield) return;

        if (player.getHealth() <= 3f) return;

        var pos = event.getPos().relative(event.getFace());
        if (world.getBlockState(pos).isAir()) {
            world.setBlockAndUpdate(pos, Blocks.TORCH.defaultBlockState());

            // Change this to a custom damage source that wont get mitigated by armor
            player.hurt(player.damageSources().magic(), 3f);

            world.playSound(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                    SoundEvents.WOOD_PLACE, player.getSoundSource(), 1.0f, 1.0f);

            event.setCanceled(true);
        }
    }
}
