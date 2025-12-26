package net.cold.coldsmod.stat;

import net.cold.coldsmod.TagLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;

public class ItemRarityUtils {

    public static Set<Item> MELEE_WEAPONS;
    public static Set<Item> BOWS;
    public static Set<Item> CROSSBOWS;
    public static Set<Item> SHIELDS;
    public static Set<Item> TOOLS;
    private static final String NBT_KEY = "custom_rarity";

    private static final TagKey<Item> SWORDS = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "swords"));
    private static final TagKey<Item> AXES = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "axes"));
    private static final TagKey<Item> TRIDENTS = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "tridents"));
    private static final TagKey<Item> FORGE_BOWS = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "bows"));
    private static final TagKey<Item> CROSSBOWS_TAG = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "crossbows"));
    private static final TagKey<Item> SHIELDS_TAG = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "shields"));

    static {
        MELEE_WEAPONS = TagLoader.loadItemsFromConfig("melee_weapons.json");
        BOWS = TagLoader.loadItemsFromConfig("bows.json");
        CROSSBOWS = TagLoader.loadItemsFromConfig("crossbows.json");
        SHIELDS = TagLoader.loadItemsFromConfig("shields.json");
        TOOLS = TagLoader.loadItemsFromConfig("tools.json");
    }

    public static boolean hasRarity(ItemStack stack) {
        return stack != null && stack.hasTag() && stack.getTag().contains(NBT_KEY);
    }

    public static void writeRarityToNBT(ItemStack stack, ItemRarity rarity) {
        if (stack == null || rarity == null) return;
        stack.getOrCreateTag().putString(NBT_KEY, rarity.displayName);
    }

    public static ItemRarity readRarityFromNBT(ItemStack stack) {
        if (!hasRarity(stack)) return ItemRarity.COMMON;
        String name = stack.getTag().getString(NBT_KEY);
        for (ItemRarity r : ItemRarity.values()) {
            if (r.displayName.equals(name)) return r;
        }
        return ItemRarity.COMMON;
    }

    public static String getItemType(ItemStack stack) {
        if (stack == null || stack.isEmpty()) return "unknown";

        Item item = stack.getItem();

        if (item instanceof ArmorItem armor) {
            return switch (armor.getEquipmentSlot()) {
                case HEAD -> "helmet";
                case CHEST -> "chestplate";
                case LEGS -> "leggings";
                case FEET -> "boots";
                default -> "armor";
            };
        }

        String id = ForgeRegistries.ITEMS.getKey(item).getPath().toLowerCase();
        if (!item.builtInRegistryHolder().is(SWORDS) &&
                !item.builtInRegistryHolder().is(AXES) &&
                !item.builtInRegistryHolder().is(TRIDENTS) &&
                MELEE_WEAPONS.contains(item)) return "sword";

        if (!item.builtInRegistryHolder().is(FORGE_BOWS) && BOWS.contains(item)) return "bow";
        if (!item.builtInRegistryHolder().is(CROSSBOWS_TAG) && CROSSBOWS.contains(item)) return "crossbow";
        if (!item.builtInRegistryHolder().is(SHIELDS_TAG) && SHIELDS.contains(item)) return "shield";
        if (TOOLS.contains(item)) return "tools";


        if (item.builtInRegistryHolder().is(SWORDS) ||
                item.builtInRegistryHolder().is(AXES) ||
                item.builtInRegistryHolder().is(TRIDENTS)) return "sword";

        if (item.builtInRegistryHolder().is(FORGE_BOWS)) return "bow";
        if (item.builtInRegistryHolder().is(CROSSBOWS_TAG)) return "crossbow";
        if (item.builtInRegistryHolder().is(SHIELDS_TAG)) return "shield";

        if (item instanceof SwordItem || item instanceof AxeItem || item instanceof TridentItem) return "sword";
        if (item instanceof BowItem) return "bow";
        if (item instanceof CrossbowItem) return "crossbow";
        if (item instanceof ShieldItem) return "shield";
        if (item instanceof HoeItem || item instanceof ShovelItem || item instanceof PickaxeItem) return "tools";


        if (id.contains("sword") || id.contains("axe") || id.contains("trident") ||
                id.contains("hammer") || id.contains("mace") ||
                id.contains("rapier") || id.contains("longsword") || id.contains("katana") ||
                id.contains("saber") || id.contains("club") || id.contains("pike") ||
                id.contains("lance") || id.contains("warhammer") || id.contains("staff") ||
                id.contains("glaive") || id.contains("spear") || id.contains("gauntlet")) return "sword";

        if (id.contains("bow")) return "bow";
        if (id.contains("crossbow")) return "crossbow";
        if (id.contains("shield")) return "shield";

        if (id.contains("pickaxe") || id.contains("shovel") || id.contains("hoe")) return "tools";

        return "unknown";
    }

    public static void init() {
        MELEE_WEAPONS = TagLoader.loadItemsFromConfig("melee_weapons.json");
        BOWS = TagLoader.loadItemsFromConfig("bows.json");
        CROSSBOWS = TagLoader.loadItemsFromConfig("crossbows.json");
        SHIELDS = TagLoader.loadItemsFromConfig("shields.json");
        TOOLS = TagLoader.loadItemsFromConfig("tools.json");
    }
}