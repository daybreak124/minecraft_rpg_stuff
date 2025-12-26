package net.cold.coldsmod.stat;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.ItemStack;

import java.util.Random;

public class ItemRarity {

    public static final ItemRarity COMMON = new ItemRarity("Common", ChatFormatting.GRAY);
    public static final ItemRarity UNCOMMON = new ItemRarity("Uncommon", ChatFormatting.GREEN);
    public static final ItemRarity RARE = new ItemRarity("Rare", ChatFormatting.BLUE);
    public static final ItemRarity EPIC = new ItemRarity("Epic", ChatFormatting.DARK_PURPLE);
    public static final ItemRarity LEGENDARY = new ItemRarity("Legendary", ChatFormatting.GOLD);
    public static final ItemRarity MYTHIC = new ItemRarity("Mythic", ChatFormatting.AQUA);
    public static final ItemRarity DISTINCT = new ItemRarity("Distinct", ChatFormatting.LIGHT_PURPLE);

    public final String displayName;
    public final ChatFormatting color;
    public final int minStats;
    public final int maxStats;
    public final double bias;
    public final double weight;
    public final int attributeBias;

    private ItemRarity(String displayName, ChatFormatting color) {
        this.displayName = displayName;
        this.color = color;
        this.minStats = 0;
        this.maxStats = 0;
        this.bias = 0;
        this.weight = 0;
        this.attributeBias = 0;
    }

    public int getMinStats(String itemType) {
        return switch (itemType.toLowerCase()) {
            case "sword" -> switch (this.displayName) {
                case "Common" -> 1;
                case "Uncommon" -> 2;
                case "Rare" -> 2;
                case "Epic" -> 3;
                case "Legendary" -> 4;
                case "Mythic" -> 4;
                default -> 0;
            };

            case "bow" -> switch (this.displayName) {
                case "Common" -> 1;
                case "Uncommon" -> 2;
                case "Rare" -> 2;
                case "Epic" -> 3;
                case "Legendary" -> 4;
                case "Mythic" -> 4;
                default -> 0;
            };

            case "crossbow" -> switch (this.displayName) {
                case "Common" -> 1;
                case "Uncommon" -> 2;
                case "Rare" -> 2;
                case "Epic" -> 3;
                case "Legendary" -> 4;
                case "Mythic" -> 4;
                default -> 0;
            };
            case "shield" -> switch (this.displayName) {
                case "Common" -> 1;
                case "Uncommon" -> 2;
                case "Rare" -> 2;
                case "Epic" -> 3;
                case "Legendary" -> 4;
                case "Mythic" -> 5;
                default -> 0;
            };
            case "helmet", "chestplate", "leggings", "boots" -> switch (this.displayName) {
                case "Common" -> 2;
                case "Uncommon" -> 3;
                case "Rare" -> 3;
                case "Epic" -> 4;
                case "Legendary" -> 5;
                case "Mythic" -> 6;
                default -> 0;
            };

            case "tool" -> switch (this.displayName) {
                case "Common" -> 2;
                case "Uncommon" -> 2;
                case "Rare" -> 2;
                case "Epic" -> 2;
                case "Legendary" -> 2;
                case "Mythic" -> 2;
                default -> 2;
            };
            default -> 0;
        };
    }

    public int getMaxStats(String itemType) {
        return switch (itemType.toLowerCase()) {
            case "sword" -> switch (this.displayName) {
                case "Common" -> 2;
                case "Uncommon" -> 2;
                case "Rare" -> 3;
                case "Epic" -> 4;
                case "Legendary" -> 4;
                case "Mythic" -> 5;
                default -> 0;
            };

            case "bow" -> switch (this.displayName) {
                case "Common" -> 2;
                case "Uncommon" -> 2;
                case "Rare" -> 3;
                case "Epic" -> 4;
                case "Legendary" -> 4;
                case "Mythic" -> 5;
                default -> 0;
            };

            case "crossbow" -> switch (this.displayName) {
                case "Common" -> 2;
                case "Uncommon" -> 2;
                case "Rare" -> 3;
                case "Epic" -> 4;
                case "Legendary" -> 4;
                case "Mythic" -> 5;
                default -> 0;
            };
            case "shield" -> switch (this.displayName) {
                case "Common" -> 2;
                case "Uncommon" -> 2;
                case "Rare" -> 3;
                case "Epic" -> 4;
                case "Legendary" -> 4;
                case "Mythic" -> 5;
                default -> 0;
            };
            case "helmet", "chestplate", "leggings", "boots" -> switch (this.displayName) {
                case "Common" -> 3;
                case "Uncommon" -> 3;
                case "Rare" -> 4;
                case "Epic" -> 4;
                case "Legendary" -> 6;
                case "Mythic" -> 7;
                default -> 0;
            };
            case "tool" -> switch (this.displayName) {
                case "Common" -> 2;
                case "Uncommon" -> 2;
                case "Rare" -> 2;
                case "Epic" -> 2;
                case "Legendary" -> 2;
                case "Mythic" -> 2;
                default -> 0;
            };
            default -> 0;
        };
    }

    public double getWeight(String itemType) {
        return switch (itemType.toLowerCase()) {
            case "sword" -> switch (this.displayName) {
                case "Common" -> 1.0;
                case "Uncommon" -> 1.15;
                case "Rare" -> 1.35;
                case "Epic" -> 1.6;
                case "Legendary" -> 1.9;
                case "Mythic" -> 2.25;
                default -> 1.0;
            };
            case "bow" -> switch (this.displayName) {
                case "Common" -> 1.0;
                case "Uncommon" -> 1.15;
                case "Rare" -> 1.35;
                case "Epic" -> 1.60;
                case "Legendary" -> 1.9;
                case "Mythic" -> 2.25;
                default -> 1.0;
            };
            case "crossbow" -> switch (this.displayName) {
                case "Common" -> 1.0;
                case "Uncommon" -> 1.15;
                case "Rare" -> 1.35;
                case "Epic" -> 1.60;
                case "Legendary" -> 1.9;
                case "Mythic" -> 2.25;
                default -> 1.0;
            };
            case "shield" -> switch (this.displayName) {
                case "Common" -> 1.0;
                case "Uncommon" -> 1.15;
                case "Rare" -> 1.35;
                case "Epic" -> 1.60;
                case "Legendary" -> 1.9;
                case "Mythic" -> 2.25;
                default -> 1.0;
            };

            case "helmet", "chestplate", "leggings", "boots" -> switch (this.displayName) {
                case "Common" -> 1.0;
                case "Uncommon" -> 1.15;
                case "Rare" -> 1.35;
                case "Epic" -> 1.60;
                case "Legendary" -> 1.9;
                case "Mythic" -> 2.25;
                default -> 1.0;
            };


            case "tool" -> switch (this.displayName) {
                case "Common" -> 1.0;
                case "Uncommon" -> 1.2;
                case "Rare" -> 1.5;
                case "Epic" -> 2.0;
                case "Legendary" -> 3.0;
                case "Mythic" -> 4.0;
                default -> 1.0;
            };
            default -> 1.0;
        };
    }

//    public double getBias(String itemType) {
//        return switch (itemType.toLowerCase()) {
//            case "sword", "bow", "crossbow", "shield", "tool" -> 0;
//            default -> 0;
//        };
//    }

    public int getAttributeBias(String itemType) {
        return switch (this.displayName) {
            case "Common" -> 0;
            case "Uncommon" -> 1;
            case "Rare" -> 2;
            case "Epic" -> 3;
            case "Legendary" -> 4;
            case "Mythic" -> 5;
            default -> 0;
        };
    }


    private static final Random roll = new Random();

    public static ItemRarity setCustomRarity() {
        int rarityDecider = roll.nextInt(1000);
        if (rarityDecider < 400) return COMMON;
        else if (rarityDecider < 700) return UNCOMMON;
        else if (rarityDecider < 900) return RARE;
        else return EPIC;
    }


    public static ItemRarity getCustomRarity(ItemStack stack) {
        if (stack == null || stack.isEmpty() || stack.getTag() == null) {
            return COMMON;
        }

        if (stack.getTag().contains("rarity")) {
            String rarityName = stack.getTag().getString("rarity").toLowerCase();
            switch (rarityName) {
                case "common": return COMMON;
                case "uncommon": return UNCOMMON;
                case "rare": return RARE;
                case "epic": return EPIC;
                case "legendary": return LEGENDARY;
                case "Mythic": return MYTHIC;
                default: return COMMON;
            }
        }
        return COMMON;
    }

    public static ItemRarity[] values() {
        return new ItemRarity[]{
                COMMON, UNCOMMON, RARE, EPIC, LEGENDARY, MYTHIC,
        };
    }
}
