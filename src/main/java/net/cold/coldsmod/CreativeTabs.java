package net.cold.coldsmod;

import net.cold.coldsmod.accessory.*;
import net.cold.coldsmod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
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
                                output.accept(RingAccessories.COIL_OF_WRATH_RARE.get());
                                output.accept(RingAccessories.COIL_OF_WRATH_EPIC.get());
                                output.accept(RingAccessories.COIL_OF_WRATH_LEGENDARY.get());
                                output.accept(RingAccessories.COIL_OF_WRATH_MYTHIC.get());

                                output.accept(RingAccessories.GLUTTONY_SIGNET_RARE.get());
                                output.accept(RingAccessories.GLUTTONY_SIGNET_EPIC.get());
                                output.accept(RingAccessories.GLUTTONY_SIGNET_LEGENDARY.get());
                                output.accept(RingAccessories.GLUTTONY_SIGNET_MYTHIC.get());

                                output.accept(RingAccessories.BAND_OF_THE_UNKNOWN_RARE.get());
                                output.accept(RingAccessories.BAND_OF_THE_UNKNOWN_EPIC.get());
                                output.accept(RingAccessories.BAND_OF_THE_UNKNOWN_LEGENDARY.get());
                                output.accept(RingAccessories.BAND_OF_THE_UNKNOWN_MYTHIC.get());

                                output.accept(RingAccessories.DRAGON_EYE_EMBEDDED_RING_RARE.get());
                                output.accept(RingAccessories.DRAGON_EYE_EMBEDDED_RING_EPIC.get());
                                output.accept(RingAccessories.DRAGON_EYE_EMBEDDED_RING_LEGENDARY.get());
                                output.accept(RingAccessories.DRAGON_EYE_EMBEDDED_RING_MYTHIC.get());

                                output.accept(RingAccessories.CORRUPTED_RING_OF_THE_LOST_RARE.get());
                                output.accept(RingAccessories.CORRUPTED_RING_OF_THE_LOST_EPIC.get());
                                output.accept(RingAccessories.CORRUPTED_RING_OF_THE_LOST_LEGENDARY.get());
                                output.accept(RingAccessories.CORRUPTED_RING_OF_THE_LOST_MYTHIC.get());

                                output.accept(RingAccessories.COLDYS_COLD_COIL_OF_COLD_RARE.get());
                                output.accept(RingAccessories.COLDYS_COLD_COIL_OF_COLD_EPIC.get());
                                output.accept(RingAccessories.COLDYS_COLD_COIL_OF_COLD_LEGENDARY.get());
                                output.accept(RingAccessories.COLDYS_COLD_COIL_OF_COLD_MYTHIC.get());

                                output.accept(RingAccessories.SUNSTONE_FORGED_RING_RARE.get());
                                output.accept(RingAccessories.SUNSTONE_FORGED_RING_EPIC.get());
                                output.accept(RingAccessories.SUNSTONE_FORGED_RING_LEGENDARY.get());
                                output.accept(RingAccessories.SUNSTONE_FORGED_RING_MYTHIC.get());

                                output.accept(NecklaceAccessories.COLLAR_OF_ENVY_RARE.get());
                                output.accept(NecklaceAccessories.COLLAR_OF_ENVY_EPIC.get());
                                output.accept(NecklaceAccessories.COLLAR_OF_ENVY_LEGENDARY.get());
                                output.accept(NecklaceAccessories.COLLAR_OF_ENVY_MYTHIC.get());

                                output.accept(NecklaceAccessories.KEY_OF_THE_UNKNOWN_RARE.get());
                                output.accept(NecklaceAccessories.KEY_OF_THE_UNKNOWN_EPIC.get());
                                output.accept(NecklaceAccessories.KEY_OF_THE_UNKNOWN_LEGENDARY.get());
                                output.accept(NecklaceAccessories.KEY_OF_THE_UNKNOWN_MYTHIC.get());

                                output.accept(NecklaceAccessories.DRAGON_TEETH_NECKLACE_RARE.get());
                                output.accept(NecklaceAccessories.DRAGON_TEETH_NECKLACE_EPIC.get());
                                output.accept(NecklaceAccessories.DRAGON_TEETH_NECKLACE_LEGENDARY.get());
                                output.accept(NecklaceAccessories.DRAGON_TEETH_NECKLACE_MYTHIC.get());

                                output.accept(NecklaceAccessories.HANGING_TIGER_TOOTH_RARE.get());
                                output.accept(NecklaceAccessories.HANGING_TIGER_TOOTH_EPIC.get());
                                output.accept(NecklaceAccessories.HANGING_TIGER_TOOTH_LEGENDARY.get());
                                output.accept(NecklaceAccessories.HANGING_TIGER_TOOTH_MYTHIC.get());

                                output.accept(NecklaceAccessories.BOTTLED_TSUNAMI_SEA_RARE.get());
                                output.accept(NecklaceAccessories.BOTTLED_TSUNAMI_SEA_EPIC.get());
                                output.accept(NecklaceAccessories.BOTTLED_TSUNAMI_SEA_LEGENDARY.get());
                                output.accept(NecklaceAccessories.BOTTLED_TSUNAMI_SEA_MYTHIC.get());

                                output.accept(NecklaceAccessories.PENDANT_OF_FLOATING_SNOWFLAKE_RARE.get());
                                output.accept(NecklaceAccessories.PENDANT_OF_FLOATING_SNOWFLAKE_EPIC.get());
                                output.accept(NecklaceAccessories.PENDANT_OF_FLOATING_SNOWFLAKE_LEGENDARY.get());
                                output.accept(NecklaceAccessories.PENDANT_OF_FLOATING_SNOWFLAKE_MYTHIC.get());

                                output.accept(NecklaceAccessories.STOLEN_LEGACIES_CHOKER_RARE.get());
                                output.accept(NecklaceAccessories.STOLEN_LEGACIES_CHOKER_EPIC.get());
                                output.accept(NecklaceAccessories.STOLEN_LEGACIES_CHOKER_LEGENDARY.get());
                                output.accept(NecklaceAccessories.STOLEN_LEGACIES_CHOKER_MYTHIC.get());

                                output.accept(BracerAccessories.BRACELET_OF_PRIDE_RARE.get());
                                output.accept(BracerAccessories.BRACELET_OF_PRIDE_EPIC.get());
                                output.accept(BracerAccessories.BRACELET_OF_PRIDE_LEGENDARY.get());
                                output.accept(BracerAccessories.BRACELET_OF_PRIDE_MYTHIC.get());

                                output.accept(BracerAccessories.FINGERS_OF_LUST_RARE.get());
                                output.accept(BracerAccessories.FINGERS_OF_LUST_EPIC.get());
                                output.accept(BracerAccessories.FINGERS_OF_LUST_LEGENDARY.get());
                                output.accept(BracerAccessories.FINGERS_OF_LUST_MYTHIC.get());

                                output.accept(BracerAccessories.ENDERMANS_SEVERED_ARM_RARE.get());
                                output.accept(BracerAccessories.ENDERMANS_SEVERED_ARM_EPIC.get());
                                output.accept(BracerAccessories.ENDERMANS_SEVERED_ARM_LEGENDARY.get());
                                output.accept(BracerAccessories.ENDERMANS_SEVERED_ARM_MYTHIC.get());

                                output.accept(BracerAccessories.DRAGON_CLAW_GLOVES_RARE.get());
                                output.accept(BracerAccessories.DRAGON_CLAW_GLOVES_EPIC.get());
                                output.accept(BracerAccessories.DRAGON_CLAW_GLOVES_LEGENDARY.get());
                                output.accept(BracerAccessories.DRAGON_CLAW_GLOVES_MYTHIC.get());

                                output.accept(BracerAccessories.WARDEN_SKIN_FORGED_BRACERS_RARE.get());
                                output.accept(BracerAccessories.WARDEN_SKIN_FORGED_BRACERS_EPIC.get());
                                output.accept(BracerAccessories.WARDEN_SKIN_FORGED_BRACERS_LEGENDARY.get());
                                output.accept(BracerAccessories.WARDEN_SKIN_FORGED_BRACERS_MYTHIC.get());

                                output.accept(BracerAccessories.REINFORCED_STEEL_BRACERS_RARE.get());
                                output.accept(BracerAccessories.REINFORCED_STEEL_BRACERS_EPIC.get());
                                output.accept(BracerAccessories.REINFORCED_STEEL_BRACERS_LEGENDARY.get());
                                output.accept(BracerAccessories.REINFORCED_STEEL_BRACERS_MYTHIC.get());

                                output.accept(BracerAccessories.SERPENT_SKIN_WRAP_RARE.get());
                                output.accept(BracerAccessories.SERPENT_SKIN_WRAP_EPIC.get());
                                output.accept(BracerAccessories.SERPENT_SKIN_WRAP_LEGENDARY.get());
                                output.accept(BracerAccessories.SERPENT_SKIN_WRAP_MYTHIC.get());

                                output.accept(BracerAccessories.WRAPS_OF_THIEVERY_RARE.get());
                                output.accept(BracerAccessories.WRAPS_OF_THIEVERY_EPIC.get());
                                output.accept(BracerAccessories.WRAPS_OF_THIEVERY_LEGENDARY.get());
                                output.accept(BracerAccessories.WRAPS_OF_THIEVERY_MYTHIC.get());


                                output.accept(HeadAccessories.TEMPTING_WHISPERS_RARE.get());
                                output.accept(HeadAccessories.TEMPTING_WHISPERS_EPIC.get());
                                output.accept(HeadAccessories.TEMPTING_WHISPERS_LEGENDARY.get());
                                output.accept(HeadAccessories.TEMPTING_WHISPERS_MYTHIC.get());

                                output.accept(HeadAccessories.SHRIEKS_OF_UNSEEING_RARE.get());
                                output.accept(HeadAccessories.SHRIEKS_OF_UNSEEING_EPIC.get());
                                output.accept(HeadAccessories.SHRIEKS_OF_UNSEEING_LEGENDARY.get());
                                output.accept(HeadAccessories.SHRIEKS_OF_UNSEEING_MYTHIC.get());

                                output.accept(HeadAccessories.DRAGONS_ROAR_RARE.get());
                                output.accept(HeadAccessories.DRAGONS_ROAR_EPIC.get());
                                output.accept(HeadAccessories.DRAGONS_ROAR_LEGENDARY.get());
                                output.accept(HeadAccessories.DRAGONS_ROAR_MYTHIC.get());

                                output.accept(HeadAccessories.TEAR_OF_THE_FORGOTTEN_RARE.get());
                                output.accept(HeadAccessories.TEAR_OF_THE_FORGOTTEN_EPIC.get());
                                output.accept(HeadAccessories.TEAR_OF_THE_FORGOTTEN_LEGENDARY.get());
                                output.accept(HeadAccessories.TEAR_OF_THE_FORGOTTEN_MYTHIC.get());

                                output.accept(HeadAccessories.ENDLESS_WAVES_RARE.get());
                                output.accept(HeadAccessories.ENDLESS_WAVES_EPIC.get());
                                output.accept(HeadAccessories.ENDLESS_WAVES_LEGENDARY.get());
                                output.accept(HeadAccessories.ENDLESS_WAVES_MYTHIC.get());

                                output.accept(HeadAccessories.SUNS_GAZE_RARE.get());
                                output.accept(HeadAccessories.SUNS_GAZE_EPIC.get());
                                output.accept(HeadAccessories.SUNS_GAZE_LEGENDARY.get());
                                output.accept(HeadAccessories.SUNS_GAZE_MYTHIC.get());

                                output.accept(UtilityAccessories.CLOUDTREADER_BOOTS.get());
                                output.accept(UtilityAccessories.ENDERMAN_FINGERS.get());
                                output.accept(UtilityAccessories.REINFORCED_DIAMOND_PLATING.get());
                                output.accept(UtilityAccessories.MONIS_LUCKY_CHARM.get());
                                output.accept(UtilityAccessories.ANTIQUE_POCKET_WATCH.get());
                                output.accept(UtilityAccessories.CLOUDSPIRE_GEM.get());
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
                                output.accept(ModItems.HORN_OF_FEARMONGERING.get());
                                output.accept(ModItems.HANKS_EYE.get());

                                output.accept(ModItems.RAGE_AMPLIFIER.get());
                                output.accept(ModItems.DROP_OF_SACRIFICIAL_BLOOD.get());
                                output.accept(ModItems.HELL_ON_EARTH.get());

                                output.accept(ModItems.BANNER_OF_DETERMINATION.get());
                                output.accept(ModItems.WORMHOLE.get());

                                output.accept(ModItems.ORB_OF_WORLD_DESTRUCTION.get());
                                output.accept(ModItems.SOUL_MAGNET.get());

                                output.accept(ModItems.LIGHTNING_INFUSION.get());
                                output.accept(ModItems.BLOODTHIRST.get());
                                output.accept(ModItems.BRANCH_OF_THE_WORLD_TREE.get());

                                output.accept(ModItems.HANKS_OTHER_EYE.get());
                                output.accept(ModItems.CUPIDS_ARROW.get());
                                output.accept(ModItems.LIFE_TOUCH.get());

                                output.accept(ModItems.WEAK_POINT_STUDIES.get());
                                output.accept(ModItems.ENDLESS_ADRENALINE_SYRINGE.get());
                                output.accept(ModItems.IGNITION_MARK.get());

                                output.accept(ModItems.FORTRESS_OF_SOLITUDE.get());
                                output.accept(ModItems.GUARDIAN_ANGEL.get());

                                output.accept(ModItems.SUNSTONE_GEM.get());

                                output.accept(ModItems.BOTTLED_LIGHT.get());


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
                                output.accept(ModItems.GEM_CLUSTER.get());
                                output.accept(ModItems.FOCUSED_GEM_CLUSTER.get());
                                output.accept(ModItems.REINFORCED_GEM_CLUSTER.get());
                                output.accept(ModItems.PERFECTED_GEM_CLUSTER.get());

                                output.accept(ModItems.ACCESSORY_UPGRADE_SMITHING_TEMPLATE.get());

                                output.accept(ModItems.COMMON_SCRAP_ESSENCE.get());
                                output.accept(ModItems.UNCOMMON_SCRAP_ESSENCE.get());
                                output.accept(ModItems.RARE_SCRAP_ESSENCE.get());
                                output.accept(ModItems.EPIC_SCRAP_ESSENCE.get());
                                output.accept(ModItems.LEGENDARY_SCRAP_ESSENCE.get());

                                output.accept(ModItems.PEARL_OF_REPLENISHING.get());
                                output.accept(ModItems.PEARL_OF_RECHARGING.get());
                                output.accept(ModItems.PEARL_OF_RENEWING.get());
                                output.accept(ModItems.PEARL_OF_RESTORING.get());
                                output.accept(ModItems.PEARL_OF_REJUVENATING.get());
                                output.accept(ModItems.PEARL_OF_REVITALIZING.get());

                                output.accept(ModItems.SHARD_OF_INFUSION.get());
                                output.accept(ModItems.SHARD_OF_AUGMENTATION.get());
                                output.accept(ModItems.SHARD_OF_AMPLIFICATION.get());
                                output.accept(ModItems.SHARD_OF_EMPOWERMENT.get());
                                output.accept(ModItems.SHARD_OF_ASCENDANCY.get());
                                output.accept(ModItems.SHARD_OF_TRANSCENDENCE.get());
                            })
                            .build()
            );

    public static final RegistryObject<CreativeModeTab> TEST_TAB =
            CREATIVE_MODE_TABS.register("cold_test_item_tab", () ->
                    CreativeModeTab.builder()
                            .title(Component.translatable("creativetab.test_item_tab"))
                            .icon(() -> new ItemStack(ModItems.PEARL_ICON.get()))
                            .displayItems((parameters, output) -> {
                                output.accept(UtilityAccessories.CRIT_ITEM.get());
                                output.accept(UtilityAccessories.DAMAGE_ITEM.get());
                                output.accept(UtilityAccessories.DEFENSE.get());
                                output.accept(UtilityAccessories.DEFENSE2.get());
                                output.accept(UtilityAccessories.DEFENSE3.get());
                                output.accept(UtilityAccessories.PROJ.get());
                                output.accept(UtilityAccessories.CRIT_ITEM_2.get());
                                output.accept(UtilityAccessories.DAMAGE_ITEM_2.get());
                                output.accept(UtilityAccessories.PROJ_2.get());
                                output.accept(UtilityAccessories.AS.get());
                                output.accept(UtilityAccessories.AS2.get());
                                output.accept(UtilityAccessories.DRAW_SPEED.get());
                                output.accept(UtilityAccessories.DEFENSE4.get());
                                output.accept(UtilityAccessories.HEALTH.get());
                                output.accept(UtilityAccessories.HEALTH2.get());
                                output.accept(UtilityAccessories.STR.get());
                                output.accept(UtilityAccessories.DEX.get());
                                output.accept(UtilityAccessories.FORT.get());
                                output.accept(UtilityAccessories.CON.get());
                                output.accept(UtilityAccessories.PERC.get());
                                output.accept(UtilityAccessories.WIS.get());
                                output.accept(UtilityAccessories.ALL.get());
                            })
                            .build()
            );
}
