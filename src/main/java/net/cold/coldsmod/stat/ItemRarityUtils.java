package net.cold.coldsmod.stat;

import net.cold.coldsmod.TagLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

public class ItemRarityUtils {

    public static Set<Item> MELEE_WEAPONS = new HashSet<>();
    public static Set<Item> BOWS = new HashSet<>();
    public static Set<Item> CROSSBOWS = new HashSet<>();
    public static Set<Item> SHIELDS = new HashSet<>();

    private static final TagKey<Item> SWORDS = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "swords"));
    private static final TagKey<Item> AXES = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "axes"));
    private static final TagKey<Item> TRIDENTS = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "tridents"));
    private static final TagKey<Item> FORGE_BOWS = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "bows"));
    private static final TagKey<Item> CROSSBOWS_TAG = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "crossbows"));
    private static final TagKey<Item> SHIELDS_TAG = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "shields"));

    private static final Map<Item, String> TYPE_CACHE = new HashMap<>();

    public static String getItemType(ItemStack stack) {
        if (stack.isEmpty()) return "unknown";
        Item item = stack.getItem();

        return TYPE_CACHE.computeIfAbsent(item, ItemRarityUtils::determineItemHype);
    }

    public static String determineItemHype(Item item) {
        var holder = item.builtInRegistryHolder();

        if (MELEE_WEAPONS.contains(item)) return "sword";
        if (BOWS.contains(item)) return "bow";
        if (CROSSBOWS.contains(item)) return "crossbow";
        if (SHIELDS.contains(item)) return "shield";

        if (holder.is(SWORDS) || holder.is(AXES) || holder.is(TRIDENTS)) return "sword";
        if (holder.is(FORGE_BOWS)) return "bow";
        if (holder.is(CROSSBOWS_TAG)) return "crossbow";
        if (holder.is(SHIELDS_TAG)) return "shield";

        if (item instanceof SwordItem || item instanceof TridentItem || item instanceof DiggerItem) return "sword";
        if (item instanceof BowItem) return "bow";
        if (item instanceof CrossbowItem) return "crossbow";
        if (item instanceof ShieldItem) return "shield";

        String id = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).getPath().toLowerCase();

        if (id.contains("sword") || (id.contains("axe") && !id.contains("waxed")) || id.contains("trident") ||
                id.contains("hammer") || id.contains("mace") ||
                id.contains("rapier") || id.contains("longsword") || id.contains("katana") ||
                id.contains("saber") || id.contains("club") ||
                id.contains("lance") || id.contains("warhammer") || id.contains("staff") ||
                id.contains("glaive") || id.contains("spear") || id.contains("gauntlet")) return "sword";

        if (id.contains("bow") && !id.contains("bowl")) return "bow";
        if (id.contains("crossbow")) return "crossbow";
        if (id.contains("pickaxe") || id.contains("shovel") || id.contains("hoe")) return "sword";

        return "unknown";
    }

    public static void init() {
        MELEE_WEAPONS = TagLoader.loadItemsFromConfig("melee_weapons.json");
        BOWS = TagLoader.loadItemsFromConfig("bows.json");
        CROSSBOWS = TagLoader.loadItemsFromConfig("crossbows.json");
        SHIELDS = TagLoader.loadItemsFromConfig("shields.json");
    }
}