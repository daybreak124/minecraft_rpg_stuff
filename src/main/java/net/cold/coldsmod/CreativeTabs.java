package net.cold.coldsmod;

import net.cold.coldsmod.accessory.UtilityAccessories;
import net.cold.coldsmod.accessory.bracers.*;
import net.cold.coldsmod.accessory.mind.*;
import net.cold.coldsmod.accessory.necklace.*;
import net.cold.coldsmod.accessory.ring.*;
import net.cold.coldsmod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ColdsMod.MODID);

    public static final RegistryObject<CreativeModeTab> ACCESSORY_TAB =
            CREATIVE_MODE_TABS.register("cold_accessory_tab", () ->
                    CreativeModeTab.builder()
                            .title(Component.translatable("creativetab.accessory_tab"))
                            .icon(() -> new ItemStack(UtilityAccessories.ANTIQUE_POCKET_WATCH.get()))
                            .displayItems((parameters, output) -> {

                                output.accept(UtilityAccessories.CLOUDTREADER_BOOTS.get());
                                output.accept(UtilityAccessories.ENDERMAN_FINGERS.get());
                                output.accept(UtilityAccessories.REINFORCED_DIAMOND_PLATING.get());
                                output.accept(UtilityAccessories.MONIS_LUCKY_CHARM.get());
                                output.accept(UtilityAccessories.ANTIQUE_POCKET_WATCH.get());
                                output.accept(UtilityAccessories.CLOUDSPIRE_GEM.get());
                                output.accept(ModItems.BOTTLED_LIGHT.get());
                                output.accept(ModItems.FAIRY_TEARDROP.get());
                                output.accept(ModItems.HELLFORGED_PLATING.get());
                                output.accept(ModItems.FLAMEFORGED_PLATING.get());
                                output.accept(ModItems.HOOK_OF_THE_DEPTHS.get());

                                output.accept(CoilOfWrath.COIL_OF_WRATH_RARE.get());
                                output.accept(CoilOfWrath.COIL_OF_WRATH_EPIC.get());
                                output.accept(CoilOfWrath.COIL_OF_WRATH_LEGENDARY.get());
                                output.accept(CoilOfWrath.COIL_OF_WRATH_MYTHIC.get());

                                output.accept(GluttonySignet.GLUTTONY_SIGNET_RARE.get());
                                output.accept(GluttonySignet.GLUTTONY_SIGNET_EPIC.get());
                                output.accept(GluttonySignet.GLUTTONY_SIGNET_LEGENDARY.get());
                                output.accept(GluttonySignet.GLUTTONY_SIGNET_MYTHIC.get());

                                output.accept(BandOfUnknown.BAND_OF_THE_UNKNOWN_RARE.get());
                                output.accept(BandOfUnknown.BAND_OF_THE_UNKNOWN_EPIC.get());
                                output.accept(BandOfUnknown.BAND_OF_THE_UNKNOWN_LEGENDARY.get());
                                output.accept(BandOfUnknown.BAND_OF_THE_UNKNOWN_MYTHIC.get());

                                output.accept(DragonEyeEmbeddedRing.DRAGON_EYE_EMBEDDED_RING_RARE.get());
                                output.accept(DragonEyeEmbeddedRing.DRAGON_EYE_EMBEDDED_RING_EPIC.get());
                                output.accept(DragonEyeEmbeddedRing.DRAGON_EYE_EMBEDDED_RING_LEGENDARY.get());
                                output.accept(DragonEyeEmbeddedRing.DRAGON_EYE_EMBEDDED_RING_MYTHIC.get());

                                output.accept(CorruptedLostRing.CORRUPTED_RING_OF_THE_LOST_RARE.get());
                                output.accept(CorruptedLostRing.CORRUPTED_RING_OF_THE_LOST_EPIC.get());
                                output.accept(CorruptedLostRing.CORRUPTED_RING_OF_THE_LOST_LEGENDARY.get());
                                output.accept(CorruptedLostRing.CORRUPTED_RING_OF_THE_LOST_MYTHIC.get());

                                output.accept(ColdCoil.COLDYS_COLD_COIL_OF_COLD_RARE.get());
                                output.accept(ColdCoil.COLDYS_COLD_COIL_OF_COLD_EPIC.get());
                                output.accept(ColdCoil.COLDYS_COLD_COIL_OF_COLD_LEGENDARY.get());
                                output.accept(ColdCoil.COLDYS_COLD_COIL_OF_COLD_MYTHIC.get());

                                output.accept(SunstoneForged.SUNSTONE_FORGED_RING_RARE.get());
                                output.accept(SunstoneForged.SUNSTONE_FORGED_RING_EPIC.get());
                                output.accept(SunstoneForged.SUNSTONE_FORGED_RING_LEGENDARY.get());
                                output.accept(SunstoneForged.SUNSTONE_FORGED_RING_MYTHIC.get());

                                output.accept(EnvyCollar.COLLAR_OF_ENVY_RARE.get());
                                output.accept(EnvyCollar.COLLAR_OF_ENVY_EPIC.get());
                                output.accept(EnvyCollar.COLLAR_OF_ENVY_LEGENDARY.get());
                                output.accept(EnvyCollar.COLLAR_OF_ENVY_MYTHIC.get());

                                output.accept(KeyOfTheUnknown.KEY_OF_THE_UNKNOWN_RARE.get());
                                output.accept(KeyOfTheUnknown.KEY_OF_THE_UNKNOWN_EPIC.get());
                                output.accept(KeyOfTheUnknown.KEY_OF_THE_UNKNOWN_LEGENDARY.get());
                                output.accept(KeyOfTheUnknown.KEY_OF_THE_UNKNOWN_MYTHIC.get());

                                output.accept(DragonTeethNecklace.DRAGON_TEETH_NECKLACE_RARE.get());
                                output.accept(DragonTeethNecklace.DRAGON_TEETH_NECKLACE_EPIC.get());
                                output.accept(DragonTeethNecklace.DRAGON_TEETH_NECKLACE_LEGENDARY.get());
                                output.accept(DragonTeethNecklace.DRAGON_TEETH_NECKLACE_MYTHIC.get());

                                output.accept(HangingTigerTooth.HANGING_TIGER_TOOTH_RARE.get());
                                output.accept(HangingTigerTooth.HANGING_TIGER_TOOTH_EPIC.get());
                                output.accept(HangingTigerTooth.HANGING_TIGER_TOOTH_LEGENDARY.get());
                                output.accept(HangingTigerTooth.HANGING_TIGER_TOOTH_MYTHIC.get());

                                output.accept(BottledTsunami.BOTTLED_TSUNAMI_SEA_RARE.get());
                                output.accept(BottledTsunami.BOTTLED_TSUNAMI_SEA_EPIC.get());
                                output.accept(BottledTsunami.BOTTLED_TSUNAMI_SEA_LEGENDARY.get());
                                output.accept(BottledTsunami.BOTTLED_TSUNAMI_SEA_MYTHIC.get());

                                output.accept(PendantOfSnowflake.PENDANT_OF_FLOATING_SNOWFLAKE_RARE.get());
                                output.accept(PendantOfSnowflake.PENDANT_OF_FLOATING_SNOWFLAKE_EPIC.get());
                                output.accept(PendantOfSnowflake.PENDANT_OF_FLOATING_SNOWFLAKE_LEGENDARY.get());
                                output.accept(PendantOfSnowflake.PENDANT_OF_FLOATING_SNOWFLAKE_MYTHIC.get());

                                output.accept(StolenLegacies.STOLEN_LEGACIES_CHOKER_RARE.get());
                                output.accept(StolenLegacies.STOLEN_LEGACIES_CHOKER_EPIC.get());
                                output.accept(StolenLegacies.STOLEN_LEGACIES_CHOKER_LEGENDARY.get());
                                output.accept(StolenLegacies.STOLEN_LEGACIES_CHOKER_MYTHIC.get());

                                output.accept(BraceletOfPride.BRACELET_OF_PRIDE_RARE.get());
                                output.accept(BraceletOfPride.BRACELET_OF_PRIDE_EPIC.get());
                                output.accept(BraceletOfPride.BRACELET_OF_PRIDE_LEGENDARY.get());
                                output.accept(BraceletOfPride.BRACELET_OF_PRIDE_MYTHIC.get());

                                output.accept(FingersOfLust.FINGERS_OF_LUST_RARE.get());
                                output.accept(FingersOfLust.FINGERS_OF_LUST_EPIC.get());
                                output.accept(FingersOfLust.FINGERS_OF_LUST_LEGENDARY.get());
                                output.accept(FingersOfLust.FINGERS_OF_LUST_MYTHIC.get());

                                output.accept(Enderman.ENDERMANS_SEVERED_ARM_RARE.get());
                                output.accept(Enderman.ENDERMANS_SEVERED_ARM_EPIC.get());
                                output.accept(Enderman.ENDERMANS_SEVERED_ARM_LEGENDARY.get());
                                output.accept(Enderman.ENDERMANS_SEVERED_ARM_MYTHIC.get());

                                output.accept(DragonClaw.DRAGON_CLAW_GLOVES_RARE.get());
                                output.accept(DragonClaw.DRAGON_CLAW_GLOVES_EPIC.get());
                                output.accept(DragonClaw.DRAGON_CLAW_GLOVES_LEGENDARY.get());
                                output.accept(DragonClaw.DRAGON_CLAW_GLOVES_MYTHIC.get());

                                output.accept(WardenSkin.WARDEN_SKIN_FORGED_BRACERS_RARE.get());
                                output.accept(WardenSkin.WARDEN_SKIN_FORGED_BRACERS_EPIC.get());
                                output.accept(WardenSkin.WARDEN_SKIN_FORGED_BRACERS_LEGENDARY.get());
                                output.accept(WardenSkin.WARDEN_SKIN_FORGED_BRACERS_MYTHIC.get());

                                output.accept(ReinforcedSteel.REINFORCED_STEEL_BRACERS_RARE.get());
                                output.accept(ReinforcedSteel.REINFORCED_STEEL_BRACERS_EPIC.get());
                                output.accept(ReinforcedSteel.REINFORCED_STEEL_BRACERS_LEGENDARY.get());
                                output.accept(ReinforcedSteel.REINFORCED_STEEL_BRACERS_MYTHIC.get());

                                output.accept(SerpentSkin.SERPENT_SKIN_WRAP_RARE.get());
                                output.accept(SerpentSkin.SERPENT_SKIN_WRAP_EPIC.get());
                                output.accept(SerpentSkin.SERPENT_SKIN_WRAP_LEGENDARY.get());
                                output.accept(SerpentSkin.SERPENT_SKIN_WRAP_MYTHIC.get());

                                output.accept(ThieveryWraps.WRAPS_OF_THIEVERY_RARE.get());
                                output.accept(ThieveryWraps.WRAPS_OF_THIEVERY_EPIC.get());
                                output.accept(ThieveryWraps.WRAPS_OF_THIEVERY_LEGENDARY.get());
                                output.accept(ThieveryWraps.WRAPS_OF_THIEVERY_MYTHIC.get());


                                output.accept(TemptingWhispers.TEMPTING_WHISPERS_RARE.get());
                                output.accept(TemptingWhispers.TEMPTING_WHISPERS_EPIC.get());
                                output.accept(TemptingWhispers.TEMPTING_WHISPERS_LEGENDARY.get());
                                output.accept(TemptingWhispers.TEMPTING_WHISPERS_MYTHIC.get());

                                output.accept(Shrieks.SHRIEKS_OF_UNSEEING_RARE.get());
                                output.accept(Shrieks.SHRIEKS_OF_UNSEEING_EPIC.get());
                                output.accept(Shrieks.SHRIEKS_OF_UNSEEING_LEGENDARY.get());
                                output.accept(Shrieks.SHRIEKS_OF_UNSEEING_MYTHIC.get());

                                output.accept(DragonRoar.DRAGONS_ROAR_RARE.get());
                                output.accept(DragonRoar.DRAGONS_ROAR_EPIC.get());
                                output.accept(DragonRoar.DRAGONS_ROAR_LEGENDARY.get());
                                output.accept(DragonRoar.DRAGONS_ROAR_MYTHIC.get());

                                output.accept(Tear.TEAR_OF_THE_FORGOTTEN_RARE.get());
                                output.accept(Tear.TEAR_OF_THE_FORGOTTEN_EPIC.get());
                                output.accept(Tear.TEAR_OF_THE_FORGOTTEN_LEGENDARY.get());
                                output.accept(Tear.TEAR_OF_THE_FORGOTTEN_MYTHIC.get());

                                output.accept(EndlessWaves.ENDLESS_WAVES_RARE.get());
                                output.accept(EndlessWaves.ENDLESS_WAVES_EPIC.get());
                                output.accept(EndlessWaves.ENDLESS_WAVES_LEGENDARY.get());
                                output.accept(EndlessWaves.ENDLESS_WAVES_MYTHIC.get());

                                output.accept(SunsGaze.SUNS_GAZE_RARE.get());
                                output.accept(SunsGaze.SUNS_GAZE_EPIC.get());
                                output.accept(SunsGaze.SUNS_GAZE_LEGENDARY.get());
                                output.accept(SunsGaze.SUNS_GAZE_MYTHIC.get());
                            })
                            .build()
            );

    public static final RegistryObject<CreativeModeTab> BLESSING_TAB =
            CREATIVE_MODE_TABS.register("cold_blessing_tab", () ->
                    CreativeModeTab.builder()
                            .title(Component.translatable("creativetab.blessing_tab"))
                            .icon(() -> new ItemStack(ModItems.ORB_ICON.get()))
                            .displayItems((parameters, output) -> {
                                output.accept(ModItems.WARLORDS_GAZE.get());
                                output.accept(ModItems.PRIDE_INFUSED_AIGRETTE.get());
                                output.accept(ModItems.ORB_OF_WORLD_DESTRUCTION.get());
                                output.accept(ModItems.HANKS_EYE.get());
                                output.accept(ModItems.RAGE_AMPLIFIER.get());
                                output.accept(ModItems.BANNER_OF_DETERMINATION.get());
                                output.accept(ModItems.SOUL_MAGNET.get());

                                output.accept(ModItems.FOX_EYE.get());
                                output.accept(ModItems.DROP_OF_SACRIFICIAL_BLOOD.get());

                                output.accept(ModItems.HELL_ON_EARTH.get());
                                output.accept(ModItems.HORN_OF_FEARMONGERING.get());

                                output.accept(ModItems.BROKEN_HEALTH_POTION.get());
                                output.accept(ModItems.IMMOLATION_OF_HEART.get());
                                output.accept(ModItems.RESTORING_AURA.get());

                                output.accept(ModItems.WORMHOLE.get());


                                output.accept(ModItems.LIGHTNING_INFUSION.get());
                                output.accept(ModItems.BLOODTHIRST.get());
                                output.accept(ModItems.BRANCH_OF_THE_WORLD_TREE.get());
                                output.accept(ModItems.DIVINITY_EXTRACTION.get());

                                output.accept(ModItems.THORN_COVERED_FORCEFIELD.get());
                                output.accept(ModItems.FORTRESS_OF_SOLITUDE.get());
                                output.accept(ModItems.GUARDIAN_ANGEL.get());
                                output.accept(ModItems.DIVINE_SHIELD.get());

                                output.accept(ModItems.HANKS_OTHER_EYE.get());
                                output.accept(ModItems.WIND_WALKER_ARROW.get());
                                output.accept(ModItems.CUPIDS_ARROW.get());
                                output.accept(ModItems.LIFE_TOUCH.get());

                                output.accept(ModItems.IGNITION_MARK.get());
                                output.accept(ModItems.WEAK_POINT_STUDIES.get());
                                output.accept(ModItems.ENDLESS_ADRENALINE_SYRINGE.get());
                                output.accept(ModItems.VIAL_OF_BURSTING_ENERGY.get());

                                output.accept(ModItems.SUNSTONE_GEM.get());
                                output.accept(ModItems.NATURES_BLESSING.get());
                                output.accept(ModItems.SUMMONING_STONE.get());

                                // output.accept(ModItems.HOLLOW_STONE.get());

                            })
                            .build()
            );

    public static final RegistryObject<CreativeModeTab> UPGRADE_TAB =
            CREATIVE_MODE_TABS.register("cold_aupgrade_item_tab", () ->
                    CreativeModeTab.builder()
                            .title(Component.translatable("creativetab.upgrade_item_tab"))
                            .icon(() -> new ItemStack(ModItems.PEARL_ICON.get()))
                            .displayItems((parameters, output) -> {
                                output.accept(ModItems.PEARL_OF_REVITALIZING.get());
                                output.accept(ModItems.SHARD_OF_TRANSCENDENCE.get());
                                output.accept(ModItems.SCRAP_ESSENCE.get());

                                output.accept(ModItems.ACCESSORY_UPGRADE_SMITHING_TEMPLATE.get());
                                output.accept(ModItems.GEM_CLUSTER.get());
                                output.accept(ModItems.FOCUSED_GEM_CLUSTER.get());
                                output.accept(ModItems.REINFORCED_GEM_CLUSTER.get());
                                output.accept(ModItems.PERFECTED_GEM_CLUSTER.get());


                            })
                            .build()
            );

    public static final RegistryObject<CreativeModeTab> TEST_TAB =
            CREATIVE_MODE_TABS.register("cold_test_item_tab", () ->
                    CreativeModeTab.builder()
                            .title(Component.translatable("creativetab.test_item_tab"))
                            .icon(() -> new ItemStack(Items.DIAMOND_AXE))
                            .displayItems((parameters, output) -> {

                            })
                            .build()
            );
}
