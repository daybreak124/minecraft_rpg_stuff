package net.cold.coldsmod.menu_accessory;

import net.cold.coldsmod.accessory.UtilityAccessories;
import net.cold.coldsmod.accessory.bracers.*;
import net.cold.coldsmod.accessory.mind.*;
import net.cold.coldsmod.accessory.necklace.*;
import net.cold.coldsmod.accessory.ring.*;
import net.cold.coldsmod.item.ModItems;
import net.minecraft.world.item.Item;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class AccessoryRegistry {
    public static final Map<String, AccessoryEntry> MAP = new LinkedHashMap<>();

    public record AccessoryEntry(Item item, String category) {}

    static {
        // --- COMBAT / GENERAL ---
        // --- BRACELETS ---

        // Bracelet of Pride
        register("bracelet_of_pride_rare", BraceletOfPride.BRACELET_OF_PRIDE_RARE, "bracelet");
        register("bracelet_of_pride_epic", BraceletOfPride.BRACELET_OF_PRIDE_EPIC, "bracelet");
        register("bracelet_of_pride_legendary", BraceletOfPride.BRACELET_OF_PRIDE_LEGENDARY, "bracelet");
        register("bracelet_of_pride_mythic", BraceletOfPride.BRACELET_OF_PRIDE_MYTHIC, "bracelet");

        // Dragon Claw Gloves
        register("dragon_claw_gloves_rare", DragonClaw.DRAGON_CLAW_GLOVES_RARE, "bracelet");
        register("dragon_claw_gloves_epic", DragonClaw.DRAGON_CLAW_GLOVES_EPIC, "bracelet");
        register("dragon_claw_gloves_legendary", DragonClaw.DRAGON_CLAW_GLOVES_LEGENDARY, "bracelet");
        register("dragon_claw_gloves_mythic", DragonClaw.DRAGON_CLAW_GLOVES_MYTHIC, "bracelet");

        // Enderman's Severed Arm
        register("endermans_severed_arm_rare", Enderman.ENDERMANS_SEVERED_ARM_RARE, "bracelet");
        register("endermans_severed_arm_epic", Enderman.ENDERMANS_SEVERED_ARM_EPIC, "bracelet");
        register("endermans_severed_arm_legendary", Enderman.ENDERMANS_SEVERED_ARM_LEGENDARY, "bracelet");
        register("endermans_severed_arm_mythic", Enderman.ENDERMANS_SEVERED_ARM_MYTHIC, "bracelet");

        // Fingers of Lust
        register("fingers_of_lust_rare", FingersOfLust.FINGERS_OF_LUST_RARE, "bracelet");
        register("fingers_of_lust_epic", FingersOfLust.FINGERS_OF_LUST_EPIC, "bracelet");
        register("fingers_of_lust_legendary", FingersOfLust.FINGERS_OF_LUST_LEGENDARY, "bracelet");
        register("fingers_of_lust_mythic", FingersOfLust.FINGERS_OF_LUST_MYTHIC, "bracelet");

        // Reinforced Steel Bracers
        register("reinforced_steel_bracers_rare", ReinforcedSteel.REINFORCED_STEEL_BRACERS_RARE, "bracelet");
        register("reinforced_steel_bracers_epic", ReinforcedSteel.REINFORCED_STEEL_BRACERS_EPIC, "bracelet");
        register("reinforced_steel_bracers_legendary", ReinforcedSteel.REINFORCED_STEEL_BRACERS_LEGENDARY, "bracelet");
        register("reinforced_steel_bracers_mythic", ReinforcedSteel.REINFORCED_STEEL_BRACERS_MYTHIC, "bracelet");

        // Serpent Skin Wrap
        register("serpent_skin_wrap_rare", SerpentSkin.SERPENT_SKIN_WRAP_RARE, "bracelet");
        register("serpent_skin_wrap_epic", SerpentSkin.SERPENT_SKIN_WRAP_EPIC, "bracelet");
        register("serpent_skin_wrap_legendary", SerpentSkin.SERPENT_SKIN_WRAP_LEGENDARY, "bracelet");
        register("serpent_skin_wrap_mythic", SerpentSkin.SERPENT_SKIN_WRAP_MYTHIC, "bracelet");

        // Wraps of Thievery
        register("wraps_of_thievery_rare", ThieveryWraps.WRAPS_OF_THIEVERY_RARE, "bracelet");
        register("wraps_of_thievery_epic", ThieveryWraps.WRAPS_OF_THIEVERY_EPIC, "bracelet");
        register("wraps_of_thievery_legendary", ThieveryWraps.WRAPS_OF_THIEVERY_LEGENDARY, "bracelet");
        register("wraps_of_thievery_mythic", ThieveryWraps.WRAPS_OF_THIEVERY_MYTHIC, "bracelet");

        // Warden Skin Forged Bracers
        register("warden_skin_forged_bracers_rare", WardenSkin.WARDEN_SKIN_FORGED_BRACERS_RARE, "bracelet");
        register("warden_skin_forged_bracers_epic", WardenSkin.WARDEN_SKIN_FORGED_BRACERS_EPIC, "bracelet");
        register("warden_skin_forged_bracers_legendary", WardenSkin.WARDEN_SKIN_FORGED_BRACERS_LEGENDARY, "bracelet");
        register("warden_skin_forged_bracers_mythic", WardenSkin.WARDEN_SKIN_FORGED_BRACERS_MYTHIC, "bracelet");

        // --- NEW ACCESSORIES ---

        // Dragon's Roar
        register("dragons_roar_rare", DragonRoar.DRAGONS_ROAR_RARE, "head");
        register("dragons_roar_epic", DragonRoar.DRAGONS_ROAR_EPIC, "head");
        register("dragons_roar_legendary", DragonRoar.DRAGONS_ROAR_LEGENDARY, "head");
        register("dragons_roar_mythic", DragonRoar.DRAGONS_ROAR_MYTHIC, "head");

        // Endless Waves
        register("endless_waves_rare", EndlessWaves.ENDLESS_WAVES_RARE, "head");
        register("endless_waves_epic", EndlessWaves.ENDLESS_WAVES_EPIC, "head");
        register("endless_waves_legendary", EndlessWaves.ENDLESS_WAVES_LEGENDARY, "head");
        register("endless_waves_mythic", EndlessWaves.ENDLESS_WAVES_MYTHIC, "head");

        // Shrieks of Unseeing
        register("shrieks_of_unseeing_rare", Shrieks.SHRIEKS_OF_UNSEEING_RARE, "head");
        register("shrieks_of_unseeing_epic", Shrieks.SHRIEKS_OF_UNSEEING_EPIC, "head");
        register("shrieks_of_unseeing_legendary", Shrieks.SHRIEKS_OF_UNSEEING_LEGENDARY, "head");
        register("shrieks_of_unseeing_mythic", Shrieks.SHRIEKS_OF_UNSEEING_MYTHIC, "head");

        // Sun's Gaze
        register("suns_gaze_rare", SunsGaze.SUNS_GAZE_RARE, "head");
        register("suns_gaze_epic", SunsGaze.SUNS_GAZE_EPIC, "head");
        register("suns_gaze_legendary", SunsGaze.SUNS_GAZE_LEGENDARY, "head");
        register("suns_gaze_mythic", SunsGaze.SUNS_GAZE_MYTHIC, "head");

        // Tear of the Forgotten
        register("tear_of_the_forgotten_rare", Tear.TEAR_OF_THE_FORGOTTEN_RARE, "head");
        register("tear_of_the_forgotten_epic", Tear.TEAR_OF_THE_FORGOTTEN_EPIC, "head");
        register("tear_of_the_forgotten_legendary", Tear.TEAR_OF_THE_FORGOTTEN_LEGENDARY, "head");
        register("tear_of_the_forgotten_mythic", Tear.TEAR_OF_THE_FORGOTTEN_MYTHIC, "head");

        // Tempting Whispers
        register("tempting_whispers_rare", TemptingWhispers.TEMPTING_WHISPERS_RARE, "head");
        register("tempting_whispers_epic", TemptingWhispers.TEMPTING_WHISPERS_EPIC, "head");
        register("tempting_whispers_legendary", TemptingWhispers.TEMPTING_WHISPERS_LEGENDARY, "head");
        register("tempting_whispers_mythic", TemptingWhispers.TEMPTING_WHISPERS_MYTHIC, "head");

        // Bottled Tsunami Sea
        register("bottled_tsunami_sea_rare", BottledTsunami.BOTTLED_TSUNAMI_SEA_RARE, "necklace");
        register("bottled_tsunami_sea_epic", BottledTsunami.BOTTLED_TSUNAMI_SEA_EPIC, "necklace");
        register("bottled_tsunami_sea_legendary", BottledTsunami.BOTTLED_TSUNAMI_SEA_LEGENDARY, "necklace");
        register("bottled_tsunami_sea_mythic", BottledTsunami.BOTTLED_TSUNAMI_SEA_MYTHIC, "necklace");

        // Dragon Teeth Necklace
        register("dragon_teeth_necklace_rare", DragonTeethNecklace.DRAGON_TEETH_NECKLACE_RARE, "necklace");
        register("dragon_teeth_necklace_epic", DragonTeethNecklace.DRAGON_TEETH_NECKLACE_EPIC, "necklace");
        register("dragon_teeth_necklace_legendary", DragonTeethNecklace.DRAGON_TEETH_NECKLACE_LEGENDARY, "necklace");
        register("dragon_teeth_necklace_mythic", DragonTeethNecklace.DRAGON_TEETH_NECKLACE_MYTHIC, "necklace");

        // Collar of Envy
        register("collar_of_envy_rare", EnvyCollar.COLLAR_OF_ENVY_RARE, "necklace");
        register("collar_of_envy_epic", EnvyCollar.COLLAR_OF_ENVY_EPIC, "necklace");
        register("collar_of_envy_legendary", EnvyCollar.COLLAR_OF_ENVY_LEGENDARY, "necklace");
        register("collar_of_envy_mythic", EnvyCollar.COLLAR_OF_ENVY_MYTHIC, "necklace");

        // Hanging Tiger Tooth
        register("hanging_tiger_tooth_rare", HangingTigerTooth.HANGING_TIGER_TOOTH_RARE, "necklace");
        register("hanging_tiger_tooth_epic", HangingTigerTooth.HANGING_TIGER_TOOTH_EPIC, "necklace");
        register("hanging_tiger_tooth_legendary", HangingTigerTooth.HANGING_TIGER_TOOTH_LEGENDARY, "necklace");
        register("hanging_tiger_tooth_mythic", HangingTigerTooth.HANGING_TIGER_TOOTH_MYTHIC, "necklace");

        // Key of the Unknown
        register("key_of_the_unknown_rare", KeyOfTheUnknown.KEY_OF_THE_UNKNOWN_RARE, "necklace");
        register("key_of_the_unknown_epic", KeyOfTheUnknown.KEY_OF_THE_UNKNOWN_EPIC, "necklace");
        register("key_of_the_unknown_legendary", KeyOfTheUnknown.KEY_OF_THE_UNKNOWN_LEGENDARY, "necklace");
        register("key_of_the_unknown_mythic", KeyOfTheUnknown.KEY_OF_THE_UNKNOWN_MYTHIC, "necklace");

        // Pendant of Snowflake
        register("pendant_of_floating_snowflake_rare", PendantOfSnowflake.PENDANT_OF_FLOATING_SNOWFLAKE_RARE, "necklace");
        register("pendant_of_floating_snowflake_epic", PendantOfSnowflake.PENDANT_OF_FLOATING_SNOWFLAKE_EPIC, "necklace");
        register("pendant_of_floating_snowflake_legendary", PendantOfSnowflake.PENDANT_OF_FLOATING_SNOWFLAKE_LEGENDARY, "necklace");
        register("pendant_of_floating_snowflake_mythic", PendantOfSnowflake.PENDANT_OF_FLOATING_SNOWFLAKE_MYTHIC, "necklace");

        // Stolen Legacies Choker
        register("stolen_legacies_choker_rare", StolenLegacies.STOLEN_LEGACIES_CHOKER_RARE, "necklace");
        register("stolen_legacies_choker_epic", StolenLegacies.STOLEN_LEGACIES_CHOKER_EPIC, "necklace");
        register("stolen_legacies_choker_legendary", StolenLegacies.STOLEN_LEGACIES_CHOKER_LEGENDARY, "necklace");
        register("stolen_legacies_choker_mythic", StolenLegacies.STOLEN_LEGACIES_CHOKER_MYTHIC, "necklace");

        // --- RINGS ---

        // Band of the Unknown
        register("band_of_the_unknown_rare", BandOfUnknown.BAND_OF_THE_UNKNOWN_RARE, "ring");
        register("band_of_the_unknown_epic", BandOfUnknown.BAND_OF_THE_UNKNOWN_EPIC, "ring");
        register("band_of_the_unknown_legendary", BandOfUnknown.BAND_OF_THE_UNKNOWN_LEGENDARY, "ring");
        register("band_of_the_unknown_mythic", BandOfUnknown.BAND_OF_THE_UNKNOWN_MYTHIC, "ring");

        // Coil of Wrath
        register("coil_of_wrath_rare", CoilOfWrath.COIL_OF_WRATH_RARE, "ring");
        register("coil_of_wrath_epic", CoilOfWrath.COIL_OF_WRATH_EPIC, "ring");
        register("coil_of_wrath_legendary", CoilOfWrath.COIL_OF_WRATH_LEGENDARY, "ring");
        register("coil_of_wrath_mythic", CoilOfWrath.COIL_OF_WRATH_MYTHIC, "ring");

        // Coldy's Cold Coil of Cold (Legendary naming choice, by the way)
        register("coldys_cold_coil_of_cold_rare", ColdCoil.COLDYS_COLD_COIL_OF_COLD_RARE, "ring");
        register("coldys_cold_coil_of_cold_epic", ColdCoil.COLDYS_COLD_COIL_OF_COLD_EPIC, "ring");
        register("coldys_cold_coil_of_cold_legendary", ColdCoil.COLDYS_COLD_COIL_OF_COLD_LEGENDARY, "ring");
        register("coldys_cold_coil_of_cold_mythic", ColdCoil.COLDYS_COLD_COIL_OF_COLD_MYTHIC, "ring");

        // Corrupted Ring of the Lost
        register("corrupted_ring_of_the_lost_rare", CorruptedLostRing.CORRUPTED_RING_OF_THE_LOST_RARE, "ring");
        register("corrupted_ring_of_the_lost_epic", CorruptedLostRing.CORRUPTED_RING_OF_THE_LOST_EPIC, "ring");
        register("corrupted_ring_of_the_lost_legendary", CorruptedLostRing.CORRUPTED_RING_OF_THE_LOST_LEGENDARY, "ring");
        register("corrupted_ring_of_the_lost_mythic", CorruptedLostRing.CORRUPTED_RING_OF_THE_LOST_MYTHIC, "ring");

        // Dragon Eye Embedded Ring
        register("dragon_eye_embedded_ring_rare", DragonEyeEmbeddedRing.DRAGON_EYE_EMBEDDED_RING_RARE, "ring");
        register("dragon_eye_embedded_ring_epic", DragonEyeEmbeddedRing.DRAGON_EYE_EMBEDDED_RING_EPIC, "ring");
        register("dragon_eye_embedded_ring_legendary", DragonEyeEmbeddedRing.DRAGON_EYE_EMBEDDED_RING_LEGENDARY, "ring");
        register("dragon_eye_embedded_ring_mythic", DragonEyeEmbeddedRing.DRAGON_EYE_EMBEDDED_RING_MYTHIC, "ring");

        // Gluttony Signet
        register("gluttony_signet_rare", GluttonySignet.GLUTTONY_SIGNET_RARE, "ring");
        register("gluttony_signet_epic", GluttonySignet.GLUTTONY_SIGNET_EPIC, "ring");
        register("gluttony_signet_legendary", GluttonySignet.GLUTTONY_SIGNET_LEGENDARY, "ring");
        register("gluttony_signet_mythic", GluttonySignet.GLUTTONY_SIGNET_MYTHIC, "ring");

        // Sunstone Forged Ring
        register("sunstone_forged_ring_rare", SunstoneForged.SUNSTONE_FORGED_RING_RARE, "ring");
        register("sunstone_forged_ring_epic", SunstoneForged.SUNSTONE_FORGED_RING_EPIC, "ring");
        register("sunstone_forged_ring_legendary", SunstoneForged.SUNSTONE_FORGED_RING_LEGENDARY, "ring");
        register("sunstone_forged_ring_mythic", SunstoneForged.SUNSTONE_FORGED_RING_MYTHIC, "ring");

        register("cloudtreader_boots", UtilityAccessories.CLOUDTREADER_BOOTS, "utility");
        register("monis_lucky_charm", UtilityAccessories.MONIS_LUCKY_CHARM, "utility");
        register("enderman_fingers", UtilityAccessories.ENDERMAN_FINGERS, "utility");
        register("antique_pocket_watch", UtilityAccessories.ANTIQUE_POCKET_WATCH, "utility");
        register("reinforced_diamond_plating", UtilityAccessories.REINFORCED_DIAMOND_PLATING, "utility");
        register("cloudspire_gem", UtilityAccessories.CLOUDSPIRE_GEM, "utility");
        register("hook_of_the_depths", ModItems.HOOK_OF_THE_DEPTHS, "utility");
        register("bottled_light", ModItems.BOTTLED_LIGHT, "utility");
        register("fairy_teardrop", ModItems.FAIRY_TEARDROP, "utility");
        register("hellforged_plating", ModItems.HELLFORGED_PLATING, "utility");
        register("flameforged_plating", ModItems.FLAMEFORGED_PLATING, "utility");
    }

    private static void register(String id, Supplier<Item> itemSupplier, String category) {
        MAP.put(id, new AccessoryEntry(itemSupplier.get(), category));
    }
}