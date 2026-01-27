package net.cold.coldsmod.blessingbonuses.effects;

import net.cold.coldsmod.stat.ItemRarityUtils;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PickaxeTorch {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        Level world = event.getLevel();
        if (event.getLevel().isClientSide()) return;
        if (event.getEntity().level().isClientSide()) return;
        ItemStack stack = event.getItemStack();

        if (!player.getPersistentData().getBoolean("lightbringer_applied")) return;
        if (player.getHealth() <= 3f) return;

        ItemStack main = player.getMainHandItem();

        ItemStack off = player.getOffhandItem();
        boolean isShield = "shield".equals(ItemRarityUtils.getItemType(main)) ||
                "shield".equals(ItemRarityUtils.getItemType(off));

        if ((!(stack.getItem() instanceof PickaxeItem)) || isShield) return;

        var pos = event.getPos().relative(event.getFace());
        if (world.getBlockState(pos).canBeReplaced() && Blocks.TORCH.canSurvive(Blocks.TORCH.defaultBlockState(), world, pos)) {
            world.setBlockAndUpdate(pos, Blocks.TORCH.defaultBlockState());

            player.hurt(player.damageSources().magic(), 3f);

            world.playSound(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                    SoundEvents.WOOD_PLACE, player.getSoundSource(), 1.0f, 1.0f);

            player.swing(event.getHand(), true);
            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.SUCCESS);
        }
    }
}
