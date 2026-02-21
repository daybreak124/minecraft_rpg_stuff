package net.cold.coldsmod.menu_blessing;

import net.cold.coldsmod.item.ModItems;
import net.minecraft.world.item.Item;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BlessingRegistry {
    public static final Map<String, BlessingEntry> MAP = new LinkedHashMap<>();

    /**
     * @param item The physical item associated with the blessing.
     * @param category The group name (e.g., "combat", "sword", "bow", "utility").
     */
    public record BlessingEntry(Item item, String category) {}

    static {
        // --- COMBAT / GENERAL ---
        register("warlords_gaze", ModItems.WARLORDS_GAZE, "combat");
        register("horn_of_fearmongering", ModItems.HORN_OF_FEARMONGERING, "combat");
        register("fox_eye", ModItems.FOX_EYE, "combat");
        register("rage_amplifier", ModItems.RAGE_AMPLIFIER, "combat");
        register("drop_of_sacrificial_blood", ModItems.DROP_OF_SACRIFICIAL_BLOOD, "combat");
        register("hell_on_earth", ModItems.HELL_ON_EARTH, "combat");
        register("banner_of_determination", ModItems.BANNER_OF_DETERMINATION, "combat");
        register("orb_of_world_destruction", ModItems.ORB_OF_WORLD_DESTRUCTION, "combat");
        register("soul_magnet", ModItems.SOUL_MAGNET, "combat");
        register("pride_infused_aigrette", ModItems.PRIDE_INFUSED_AIGRETTE, "combat");
        register("immolation_of_heart", ModItems.IMMOLATION_OF_HEART, "combat");
        register("broken_health_potion", ModItems.BROKEN_HEALTH_POTION, "combat");
        register("restoring_aura", ModItems.RESTORING_AURA, "combat");
        register("hanks_eye", ModItems.HANKS_EYE, "combat");
        register("wormhole", ModItems.WORMHOLE, "combat");


        // SWORD
        register("bloodthirst", ModItems.BLOODTHIRST, "sword");
        register("branch_of_the_world_tree", ModItems.BRANCH_OF_THE_WORLD_TREE, "sword");
        register("divinity_extraction", ModItems.DIVINITY_EXTRACTION, "sword");
        register("lightning_infusion", ModItems.LIGHTNING_INFUSION, "sword");



        // SHIELD
        register("thorn_covered_forcefield", ModItems.THORN_COVERED_FORCEFIELD, "shield");
        register("fortress_of_solitude", ModItems.FORTRESS_OF_SOLITUDE, "shield");
        register("guardian_angel", ModItems.GUARDIAN_ANGEL, "shield");
        register("divine_shield", ModItems.DIVINE_SHIELD, "shield");


        // BOW
        register("hanks_other_eye", ModItems.HANKS_OTHER_EYE, "bow");
        register("cupids_arrow", ModItems.CUPIDS_ARROW, "bow");
        register("wind_walker_arrow", ModItems.WIND_WALKER_ARROW, "bow");
        register("life_touch", ModItems.LIFE_TOUCH, "bow");


        // CROSSBOW
        register("ignition_mark", ModItems.IGNITION_MARK, "crossbow");
        register("weak_point_studies", ModItems.WEAK_POINT_STUDIES, "crossbow");
        register("vial_of_bursting_energy", ModItems.VIAL_OF_BURSTING_ENERGY, "crossbow");
        register("endless_adrenaline_syringe", ModItems.ENDLESS_ADRENALINE_SYRINGE, "crossbow");


        // --- PRESENCE ---
        register("sunstone_gem", ModItems.SUNSTONE_GEM, "presence");
        register("natures_blessing", ModItems.NATURES_BLESSING, "presence");
        register("summoning_stone", ModItems.SUMMONING_STONE, "presence");


        // --- UTILITY / MISC ---
        register("fairy_teardrop", ModItems.FAIRY_TEARDROP, "utility");
        register("bottled_light", ModItems.BOTTLED_LIGHT, "utility");
        register("hook_of_the_depths", ModItems.HOOK_OF_THE_DEPTHS, "utility");
        register("hellforged_plating", ModItems.HELLFORGED_PLATING, "utility");
        register("selective_hellforged_plating", ModItems.SELECTIVE_HELLFORGED_PLATING, "utility");
    }

    private static void register(String id, Supplier<Item> itemSupplier, String category) {
        MAP.put(id, new BlessingEntry(itemSupplier.get(), category));
    }
}