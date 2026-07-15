package net.cold.coldsmod.menu_stat;

import net.cold.coldsmod.item.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.List;

public class FeatCostRegistry {
    public record Cost(Item item, int count) {}

    public static List<Cost> getCostForPoint(int nextPointIndex) {
        return switch (nextPointIndex) {
            case 1 -> List.of(new Cost(Items.ENDER_PEARL, 3), new Cost(Items.IRON_BLOCK, 3));
            case 2 -> List.of(new Cost(Items.BLAZE_ROD, 3), new Cost(Items.DIAMOND_BLOCK, 2));
            case 3 -> List.of(new Cost(Items.NETHERITE_INGOT, 3));
            case 4 -> List.of(new Cost(Items.NETHERITE_INGOT, 3), new Cost(Items.GOLD_BLOCK, 3));
            case 5 -> List.of(new Cost(Items.NETHERITE_INGOT, 3), new Cost(Items.DIAMOND_BLOCK, 3));
            case 6 -> List.of(new Cost(Items.DRAGON_HEAD, 1), new Cost(Items.DRAGON_BREATH, 3), new Cost(Items.DIAMOND_BLOCK, 3));
            case 7 -> List.of(new Cost(Items.NETHERITE_INGOT, 3), new Cost(Items.WITHER_SKELETON_SKULL, 1));
            case 8 -> List.of(new Cost(Items.NETHER_STAR, 1), new Cost(Items.NETHERITE_BLOCK, 3));
            case 9 -> List.of(
                    new Cost(Items.NETHERITE_INGOT, 3),
                    new Cost(ModItems.PERFECTED_GEM_CLUSTER.get(), 1),
                    new Cost(ModItems.PEARL_OF_REVITALIZING.get(), 12),
                    new Cost(ModItems.SHARD_OF_TRANSCENDENCE.get(), 12)
            );
            default -> List.of();
        };
    }
}