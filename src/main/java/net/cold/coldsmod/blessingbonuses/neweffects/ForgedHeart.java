package net.cold.coldsmod.blessingbonuses.neweffects;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class ForgedHeart {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.getPlayer().level().isClientSide) return;
        Player player = event.getPlayer();
        if (player.isCrouching() || player.isCreative()) return;
        if (player.getPersistentData().getBoolean("smelt_eligible")) return;
        if (!player.getPersistentData().getBoolean("smelt2_eligible")) return;


        BlockState state = event.getState();
        if (!state.is(net.minecraftforge.common.Tags.Blocks.ORES)) return;
        if (state.is(net.minecraft.world.level.block.Blocks.NETHER_QUARTZ_ORE)) return;

        if (!player.hasCorrectToolForDrops(state)) return;

        Level level = (Level) event.getLevel();
        BlockPos pos = event.getPos();
        ItemStack tool = player.getMainHandItem();

        if (player.isCreative() || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, tool) > 0) return;
        List<ItemStack> drops = Block.getDrops(state, (ServerLevel) level, pos, null, player, tool);

        boolean smeltedAny = false;
        List<ItemStack> finalDrops = new ArrayList<>();

        for (ItemStack stack : drops) {
            ItemStack smeltedResult = getSmeltingResult(level, stack);

            if (!smeltedResult.isEmpty()) {
                ItemStack result = smeltedResult.copy();
                result.setCount(stack.getCount());
                finalDrops.add(result);
                smeltedAny = true;
            } else {
                finalDrops.add(stack);
            }
        }

        if (smeltedAny) {
            event.setCanceled(true);
            level.destroyBlock(pos, false);

            level.removeBlock(pos, false);

            for (ItemStack drop : finalDrops) {
                if (!player.getInventory().add(drop)) {
                    Block.popResource(level, pos, drop);
                }
            }
            ((ServerLevel)level).sendParticles(ParticleTypes.FLAME, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 5, 0.2, 0.2, 0.2, 0.05);
        }
    }

    private static ItemStack getSmeltingResult(Level level, ItemStack stack) {
        return level.getRecipeManager()
                .getRecipeFor(RecipeType.SMELTING, new SimpleContainer(stack), level)
                .map(recipe -> recipe.getResultItem(level.registryAccess()))
                .orElse(ItemStack.EMPTY);
    }
}
