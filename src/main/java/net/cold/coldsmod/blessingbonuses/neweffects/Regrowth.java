package net.cold.coldsmod.blessingbonuses.neweffects;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Regrowth {

    @SubscribeEvent
    public static void onRightClickCrop(PlayerInteractEvent.RightClickBlock event) {
        if (event.getEntity().level().isClientSide) return;
        if (!event.getEntity().getPersistentData().getBoolean("regrowth_eligible")) return;
        Player player = event.getEntity();
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        ItemStack heldItem = event.getItemStack();

        if (!(heldItem.getItem() instanceof HoeItem)) return;

        Block block = state.getBlock();
        boolean handled = false;
        BlockState newState = null;

        if (block instanceof CropBlock crop && crop.isMaxAge(state)) {
            newState = crop.getStateForAge(0);
            handled = true;
        }
        else if (block instanceof NetherWartBlock && state.getValue(NetherWartBlock.AGE) >= 3) {
            newState = state.setValue(NetherWartBlock.AGE, 0);
            handled = true;
        }
        else if (block instanceof CocoaBlock && state.getValue(CocoaBlock.AGE) >= 2) {
            newState = state.setValue(CocoaBlock.AGE, 0);
            handled = true;
        }

        if (handled && !level.isClientSide) {
            ServerLevel serverLevel = (ServerLevel) level;

            Block.getDrops(state, serverLevel, pos, null, player, heldItem).forEach(stack -> {
                player.getInventory().add(stack);
                if (!stack.isEmpty()) {
                    Block.popResource(level, pos, stack);
                }
            });

            level.setBlock(pos, newState, 3);
            level.playSound(null, pos, SoundEvents.CROP_BREAK, SoundSource.BLOCKS, 1.0f, 1.0f);
            heldItem.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(event.getHand()));

            player.swing(event.getHand(), true);
            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.SUCCESS);
        }
    }
}
