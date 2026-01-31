package net.cold.coldsmod.blessingbonuses.effects;

import net.cold.coldsmod.stat.ItemRarityUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PickaxeTorch {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getLevel().isClientSide()) return;
        if (event.getEntity().level().isClientSide()) return;
        Player player = event.getEntity();
        Level world = event.getLevel();
        if (!player.getPersistentData().getBoolean("lightbringer_applied")) return;
        if (player.isCrouching()) return;

        BlockPos clickedPos = event.getPos();
        BlockState clickedState = world.getBlockState(clickedPos);
        if (player.position().distanceToSqr(clickedPos.getX(), clickedPos.getY(), clickedPos.getZ()) > 9.0) return;

        if (!player.isSecondaryUseActive()) {
            if (clickedState.hasBlockEntity()) return;
            if (clickedState.is(net.minecraft.tags.BlockTags.DOORS) ||
                    clickedState.is(net.minecraft.tags.BlockTags.BUTTONS) ||
                    clickedState.is(net.minecraft.tags.BlockTags.TRAPDOORS)) {
                return;
            }
        }

        ItemStack main = player.getMainHandItem();

        ItemStack off = player.getOffhandItem();
        boolean isShield = "shield".equals(ItemRarityUtils.getItemType(main)) ||
                "shield".equals(ItemRarityUtils.getItemType(off));

        ItemStack stack = event.getItemStack();

        if ((!(stack.getItem() instanceof PickaxeItem)) || isShield) return;

        var pos = event.getPos().relative(event.getFace());
        BlockState torchState;

        if (event.getFace() == net.minecraft.core.Direction.UP) {
            torchState = Blocks.TORCH.defaultBlockState();
        } else if (event.getFace() == net.minecraft.core.Direction.DOWN) {
            return;
        } else {
            torchState = Blocks.WALL_TORCH.defaultBlockState()
                    .setValue(net.minecraft.world.level.block.WallTorchBlock.FACING, event.getFace());
        }

        if (world.getBlockState(pos).canBeReplaced() && torchState.canSurvive(world, pos)) {
            world.setBlockAndUpdate(pos, torchState);

            stack.hurtAndBreak(10, player, (p) -> p.broadcastBreakEvent(event.getHand()));
            world.playSound(null, pos, SoundEvents.WOOD_PLACE, player.getSoundSource(), 1.0f, 1.0f);

            player.swing(event.getHand(), true);
            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.SUCCESS);
        }
    }
}
