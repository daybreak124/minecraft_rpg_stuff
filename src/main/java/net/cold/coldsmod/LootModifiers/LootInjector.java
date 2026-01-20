package net.cold.coldsmod.LootModifiers;

import net.cold.coldsmod.accessory.*;
import net.cold.coldsmod.accessory.bracers.*;
import net.cold.coldsmod.accessory.mind.*;
import net.cold.coldsmod.accessory.necklace.*;
import net.cold.coldsmod.accessory.ring.*;
import net.cold.coldsmod.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = "coldsmod")
public class LootInjector {

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        ResourceLocation name = event.getName();

        if (name.equals(new ResourceLocation("minecraft", "chests/nether_bridge"))) {

            List<LootEntry> netherLoot = List.of(
                    // --- Accessories (10% chance total table) ---
                    // --- Rare ---
                    new LootEntry(BraceletOfPride.BRACELET_OF_PRIDE_RARE.get(), 1, 0.007825f),
                    new LootEntry(FingersOfLust.FINGERS_OF_LUST_RARE.get(), 1, 0.017215f),
                    new LootEntry(TemptingWhispers.TEMPTING_WHISPERS_RARE.get(), 1, 0.017215f),
                    new LootEntry(CoilOfWrath.COIL_OF_WRATH_RARE.get(), 1, 0.017215f),
                    new LootEntry(GluttonySignet.GLUTTONY_SIGNET_RARE.get(), 1, 0.017215f),
                    new LootEntry(EnvyCollar.COLLAR_OF_ENVY_RARE.get(), 1, 0.017215f),

                    // --- Epic ---
                    new LootEntry(BraceletOfPride.BRACELET_OF_PRIDE_EPIC.get(), 1, 0.0004165f),
                    new LootEntry(FingersOfLust.FINGERS_OF_LUST_EPIC.get(), 1, 0.0009167f),
                    new LootEntry(TemptingWhispers.TEMPTING_WHISPERS_EPIC.get(), 1, 0.0009167f),
                    new LootEntry(CoilOfWrath.COIL_OF_WRATH_EPIC.get(), 1, 0.0009167f),
                    new LootEntry(GluttonySignet.GLUTTONY_SIGNET_EPIC.get(), 1, 0.0009167f),
                    new LootEntry(EnvyCollar.COLLAR_OF_ENVY_EPIC.get(), 1, 0.0009167f),

                    // --- Legendary ---
                    new LootEntry(BraceletOfPride.BRACELET_OF_PRIDE_LEGENDARY.get(), 1, 0.0000835f),
                    new LootEntry(FingersOfLust.FINGERS_OF_LUST_LEGENDARY.get(), 1, 0.0000167f),
                    new LootEntry(TemptingWhispers.TEMPTING_WHISPERS_LEGENDARY.get(), 1, 0.0000167f),
                    new LootEntry(CoilOfWrath.COIL_OF_WRATH_LEGENDARY.get(), 1, 0.0000167f),
                    new LootEntry(GluttonySignet.GLUTTONY_SIGNET_LEGENDARY.get(), 1, 0.0000167f),
                    new LootEntry(EnvyCollar.COLLAR_OF_ENVY_LEGENDARY.get(), 1, 0.0000167f),

                    // --- Mythic ---
                    new LootEntry(BraceletOfPride.BRACELET_OF_PRIDE_MYTHIC.get(), 1, 0.0000008335f),
                    new LootEntry(FingersOfLust.FINGERS_OF_LUST_MYTHIC.get(), 1, 0.0000018333f),
                    new LootEntry(TemptingWhispers.TEMPTING_WHISPERS_MYTHIC.get(), 1, 0.0000018333f),
                    new LootEntry(CoilOfWrath.COIL_OF_WRATH_MYTHIC.get(), 1, 0.0000018333f),
                    new LootEntry(GluttonySignet.GLUTTONY_SIGNET_MYTHIC.get(), 1, 0.0000018333f),
                    new LootEntry(EnvyCollar.COLLAR_OF_ENVY_MYTHIC.get(), 1, 0.0000018333f),

                    // --- Utility  ---
                    new LootEntry(UtilityAccessories.CLOUDTREADER_BOOTS.get(), 1, 0.0083f),
                    new LootEntry(UtilityAccessories.ENDERMAN_FINGERS.get(), 1, 0.0083f),
                    new LootEntry(UtilityAccessories.REINFORCED_DIAMOND_PLATING.get(), 1, 0.0083f),
                    new LootEntry(UtilityAccessories.MONIS_LUCKY_CHARM.get(), 1, 0.0083f),
                    new LootEntry(UtilityAccessories.ANTIQUE_POCKET_WATCH.get(), 1, 0.0083f),
                    new LootEntry(UtilityAccessories.CLOUDSPIRE_GEM.get(), 1, 0.0083f),

                    // --- Accessory Upgrade Smithing Template (3%) ---
                    new LootEntry(ModItems.ACCESSORY_UPGRADE_SMITHING_TEMPLATE.get(), 1, 0.03f),

                    // --- Pearls / Shards / Essences (51.125%) ---
                    new LootEntry(ModItems.PEARL_OF_REPLENISHING.get(), 1, 0.10f),
                    new LootEntry(ModItems.PEARL_OF_RECHARGING.get(), 1, 0.05f),
                    new LootEntry(ModItems.PEARL_OF_RENEWING.get(), 1, 0.025f),
                    new LootEntry(ModItems.PEARL_OF_RESTORING.get(), 1, 0.0125f),
                    new LootEntry(ModItems.PEARL_OF_REJUVENATING.get(), 1, 0.00625f),

                    new LootEntry(ModItems.SHARD_OF_INFUSION.get(), 1, 0.10f),
                    new LootEntry(ModItems.SHARD_OF_AUGMENTATION.get(), 1, 0.05f),
                    new LootEntry(ModItems.SHARD_OF_AMPLIFICATION.get(), 1, 0.025f),
                    new LootEntry(ModItems.SHARD_OF_EMPOWERMENT.get(), 1, 0.0125f),
                    new LootEntry(ModItems.SHARD_OF_ASCENDANCY.get(), 1, 0.00625f),

                    new LootEntry(ModItems.COMMON_SCRAP_ESSENCE.get(), 1, 0.05f),
                    new LootEntry(ModItems.UNCOMMON_SCRAP_ESSENCE.get(), 1, 0.025f),
                    new LootEntry(ModItems.RARE_SCRAP_ESSENCE.get(), 1, 0.0125f),
                    new LootEntry(ModItems.EPIC_SCRAP_ESSENCE.get(), 1, 0.00625f),

                    // new LootEntry(ModItems.HOLLOW_STONE.get(), 1, 0.01f),
                    new LootEntry(ModItems.WEAK_POINT_STUDIES.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.WARLORDS_GAZE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.RAGE_AMPLIFIER.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.HELL_ON_EARTH.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.ORB_OF_WORLD_DESTRUCTION.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.BLOODTHIRST.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.SOUL_MAGNET.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.PRIDE_INFUSED_AIGRETTE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.IMMOLATION_OF_HEART.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.SUMMONING_STONE.get(), 1, 0.0042857f)
            );
            injectLoot(event.getTable(), netherLoot);
        }


        if (name.equals(new ResourceLocation("minecraft", "chests/bastion_bridge")) ||
                name.equals(new ResourceLocation("minecraft", "chests/bastion_hoglin_stable")) ||
                name.equals(new ResourceLocation("minecraft", "chests/bastion_other")) ||
                name.equals(new ResourceLocation("minecraft", "chests/bastion_treasure"))) {

            List<LootEntry> bastionLoot = List.of(
                    // --- Rare (9.39%) ---
                    new LootEntry(BraceletOfPride.BRACELET_OF_PRIDE_RARE.get(), 1, 0.01937f),
                    new LootEntry(FingersOfLust.FINGERS_OF_LUST_RARE.get(), 1, 0.0149f),
                    new LootEntry(TemptingWhispers.TEMPTING_WHISPERS_RARE.get(), 1, 0.0149f),
                    new LootEntry(CoilOfWrath.COIL_OF_WRATH_RARE.get(), 1, 0.0149f),
                    new LootEntry(GluttonySignet.GLUTTONY_SIGNET_RARE.get(), 1, 0.0149f),
                    new LootEntry(EnvyCollar.COLLAR_OF_ENVY_RARE.get(), 1, 0.0149f),

                    // --- Epic (0.5%) ---
                    new LootEntry(BraceletOfPride.BRACELET_OF_PRIDE_EPIC.get(), 1, 0.001031f),
                    new LootEntry(FingersOfLust.FINGERS_OF_LUST_EPIC.get(), 1, 0.0007937f),
                    new LootEntry(TemptingWhispers.TEMPTING_WHISPERS_EPIC.get(), 1, 0.0007937f),
                    new LootEntry(CoilOfWrath.COIL_OF_WRATH_EPIC.get(), 1, 0.0007937f),
                    new LootEntry(GluttonySignet.GLUTTONY_SIGNET_EPIC.get(), 1, 0.0007937f),
                    new LootEntry(EnvyCollar.COLLAR_OF_ENVY_EPIC.get(), 1, 0.0007937f),

                    // --- Legendary (0.1%) ---
                    new LootEntry(BraceletOfPride.BRACELET_OF_PRIDE_LEGENDARY.get(), 1, 0.0002063f),
                    new LootEntry(FingersOfLust.FINGERS_OF_LUST_LEGENDARY.get(), 1, 0.0001587f),
                    new LootEntry(TemptingWhispers.TEMPTING_WHISPERS_LEGENDARY.get(), 1, 0.0001587f),
                    new LootEntry(CoilOfWrath.COIL_OF_WRATH_LEGENDARY.get(), 1, 0.0001587f),
                    new LootEntry(GluttonySignet.GLUTTONY_SIGNET_LEGENDARY.get(), 1, 0.0001587f),
                    new LootEntry(EnvyCollar.COLLAR_OF_ENVY_LEGENDARY.get(), 1, 0.0001587f),

                    // --- Mythic (0.01%) ---
                    new LootEntry(BraceletOfPride.BRACELET_OF_PRIDE_MYTHIC.get(), 1, 0.00002063f),
                    new LootEntry(FingersOfLust.FINGERS_OF_LUST_MYTHIC.get(), 1, 0.00001587f),
                    new LootEntry(TemptingWhispers.TEMPTING_WHISPERS_MYTHIC.get(), 1, 0.00001587f),
                    new LootEntry(CoilOfWrath.COIL_OF_WRATH_MYTHIC.get(), 1, 0.00001587f),
                    new LootEntry(GluttonySignet.GLUTTONY_SIGNET_MYTHIC.get(), 1, 0.00001587f),
                    new LootEntry(EnvyCollar.COLLAR_OF_ENVY_MYTHIC.get(), 1, 0.00001587f),

                    // Utility (~1.25% per item)
                    new LootEntry(UtilityAccessories.CLOUDTREADER_BOOTS.get(), 1, 0.0083f),
                    new LootEntry(UtilityAccessories.ENDERMAN_FINGERS.get(), 1, 0.0083f),
                    new LootEntry(UtilityAccessories.REINFORCED_DIAMOND_PLATING.get(), 1, 0.0083f),
                    new LootEntry(UtilityAccessories.MONIS_LUCKY_CHARM.get(), 1, 0.0083f),
                    new LootEntry(UtilityAccessories.ANTIQUE_POCKET_WATCH.get(), 1, 0.0083f),
                    new LootEntry(UtilityAccessories.CLOUDSPIRE_GEM.get(), 1, 0.0083f),

                    // Accessory upgrade smithing template (~3%)
                    new LootEntry(ModItems.ACCESSORY_UPGRADE_SMITHING_TEMPLATE.get(), 1, 0.03f),
                    new LootEntry(ModItems.PEARL_OF_REPLENISHING.get(), 1, 0.1F),
                    new LootEntry(ModItems.PEARL_OF_RECHARGING.get(), 1, 0.05F),
                    new LootEntry(ModItems.PEARL_OF_RENEWING.get(), 1, 0.025F),
                    new LootEntry(ModItems.PEARL_OF_RESTORING.get(), 1, 0.0125F),
                    new LootEntry(ModItems.PEARL_OF_REJUVENATING.get(), 1, 0.00625F),
                    new LootEntry(ModItems.SHARD_OF_INFUSION.get(), 1, 0.1F),
                    new LootEntry(ModItems.SHARD_OF_AUGMENTATION.get(), 1, 0.05F),
                    new LootEntry(ModItems.SHARD_OF_AMPLIFICATION.get(), 1, 0.025F),
                    new LootEntry(ModItems.SHARD_OF_EMPOWERMENT.get(), 1, 0.0125F),
                    new LootEntry(ModItems.SHARD_OF_ASCENDANCY.get(), 1, 0.00625F),
                    new LootEntry(ModItems.COMMON_SCRAP_ESSENCE.get(), 1, 0.05F),
                    new LootEntry(ModItems.UNCOMMON_SCRAP_ESSENCE.get(), 1, 0.025F),
                    new LootEntry(ModItems.RARE_SCRAP_ESSENCE.get(), 1, 0.0125F),
                    new LootEntry(ModItems.EPIC_SCRAP_ESSENCE.get(), 1, 0.00625F),

                    new LootEntry(ModItems.WEAK_POINT_STUDIES.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.WARLORDS_GAZE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.RAGE_AMPLIFIER.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.HELL_ON_EARTH.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.ORB_OF_WORLD_DESTRUCTION.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.BLOODTHIRST.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.SOUL_MAGNET.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.PRIDE_INFUSED_AIGRETTE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.IMMOLATION_OF_HEART.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.SUMMONING_STONE.get(), 1, 0.0042857f)
            );
            injectLoot(event.getTable(), bastionLoot);
        }

        if (name.equals(new ResourceLocation("minecraft", "chests/ruined_portal"))) {

            List<LootEntry> ruinedPortalLoot = List.of(
                    // Rare (0.7825% per item)
                    new LootEntry(BraceletOfPride.BRACELET_OF_PRIDE_RARE.get(), 1, 0.007825f),
                    new LootEntry(FingersOfLust.FINGERS_OF_LUST_RARE.get(), 1, 0.007825f),
                    new LootEntry(TemptingWhispers.TEMPTING_WHISPERS_RARE.get(), 1, 0.007825f),
                    new LootEntry(CoilOfWrath.COIL_OF_WRATH_RARE.get(), 1, 0.007825f),
                    new LootEntry(GluttonySignet.GLUTTONY_SIGNET_RARE.get(), 1, 0.007825f),
                    new LootEntry(EnvyCollar.COLLAR_OF_ENVY_RARE.get(), 1, 0.007825f),

                    // Epic (0.04165% per item)
                    new LootEntry(BraceletOfPride.BRACELET_OF_PRIDE_EPIC.get(), 1, 0.0004165f),
                    new LootEntry(FingersOfLust.FINGERS_OF_LUST_EPIC.get(), 1, 0.0004165f),
                    new LootEntry(TemptingWhispers.TEMPTING_WHISPERS_EPIC.get(), 1, 0.0004165f),
                    new LootEntry(CoilOfWrath.COIL_OF_WRATH_EPIC.get(), 1, 0.0004165f),
                    new LootEntry(GluttonySignet.GLUTTONY_SIGNET_EPIC.get(), 1, 0.0004165f),
                    new LootEntry(EnvyCollar.COLLAR_OF_ENVY_EPIC.get(), 1, 0.0004165f),

                    // Legendary (0.00835% per item)
                    new LootEntry(BraceletOfPride.BRACELET_OF_PRIDE_LEGENDARY.get(), 1, 0.0000835f),
                    new LootEntry(FingersOfLust.FINGERS_OF_LUST_LEGENDARY.get(), 1, 0.0000835f),
                    new LootEntry(TemptingWhispers.TEMPTING_WHISPERS_LEGENDARY.get(), 1, 0.0000835f),
                    new LootEntry(CoilOfWrath.COIL_OF_WRATH_LEGENDARY.get(), 1, 0.0000835f),
                    new LootEntry(GluttonySignet.GLUTTONY_SIGNET_LEGENDARY.get(), 1, 0.0000835f),
                    new LootEntry(EnvyCollar.COLLAR_OF_ENVY_LEGENDARY.get(), 1, 0.0000835f),

                    // Mythic (0.00085% per item)
                    new LootEntry(BraceletOfPride.BRACELET_OF_PRIDE_MYTHIC.get(), 1, 0.0000085f),
                    new LootEntry(FingersOfLust.FINGERS_OF_LUST_MYTHIC.get(), 1, 0.0000085f),
                    new LootEntry(TemptingWhispers.TEMPTING_WHISPERS_MYTHIC.get(), 1, 0.0000085f),
                    new LootEntry(CoilOfWrath.COIL_OF_WRATH_MYTHIC.get(), 1, 0.0000085f),
                    new LootEntry(GluttonySignet.GLUTTONY_SIGNET_MYTHIC.get(), 1, 0.0000085f),
                    new LootEntry(EnvyCollar.COLLAR_OF_ENVY_MYTHIC.get(), 1, 0.0000085f),

                    // Utility (~1.25% per item)
                    new LootEntry(UtilityAccessories.CLOUDTREADER_BOOTS.get(), 1, 0.0083f),
                    new LootEntry(UtilityAccessories.ENDERMAN_FINGERS.get(), 1, 0.0083f),
                    new LootEntry(UtilityAccessories.REINFORCED_DIAMOND_PLATING.get(), 1, 0.0083f),
                    new LootEntry(UtilityAccessories.MONIS_LUCKY_CHARM.get(), 1, 0.0083f),
                    new LootEntry(UtilityAccessories.ANTIQUE_POCKET_WATCH.get(), 1, 0.0083f),
                    new LootEntry(UtilityAccessories.CLOUDSPIRE_GEM.get(), 1, 0.0083f),

                    // Accessory upgrade smithing template (~3%)
                    new LootEntry(ModItems.ACCESSORY_UPGRADE_SMITHING_TEMPLATE.get(), 1, 0.03f),
                    new LootEntry(ModItems.PEARL_OF_REPLENISHING.get(), 1, 0.1F),
                    new LootEntry(ModItems.PEARL_OF_RECHARGING.get(), 1, 0.05F),
                    new LootEntry(ModItems.PEARL_OF_RENEWING.get(), 1, 0.025F),
                    new LootEntry(ModItems.PEARL_OF_RESTORING.get(), 1, 0.0125F),
                    new LootEntry(ModItems.PEARL_OF_REJUVENATING.get(), 1, 0.00625F),
                    new LootEntry(ModItems.SHARD_OF_INFUSION.get(), 1, 0.1F),
                    new LootEntry(ModItems.SHARD_OF_AUGMENTATION.get(), 1, 0.05F),
                    new LootEntry(ModItems.SHARD_OF_AMPLIFICATION.get(), 1, 0.025F),
                    new LootEntry(ModItems.SHARD_OF_EMPOWERMENT.get(), 1, 0.0125F),
                    new LootEntry(ModItems.SHARD_OF_ASCENDANCY.get(), 1, 0.00625F),
                    new LootEntry(ModItems.COMMON_SCRAP_ESSENCE.get(), 1, 0.05F),
                    new LootEntry(ModItems.UNCOMMON_SCRAP_ESSENCE.get(), 1, 0.025F),
                    new LootEntry(ModItems.RARE_SCRAP_ESSENCE.get(), 1, 0.0125F),
                    new LootEntry(ModItems.EPIC_SCRAP_ESSENCE.get(), 1, 0.00625F),

                    new LootEntry(ModItems.WEAK_POINT_STUDIES.get(), 1, 0.00214285f),
                    new LootEntry(ModItems.WARLORDS_GAZE.get(), 1, 0.00214285f),
                    new LootEntry(ModItems.RAGE_AMPLIFIER.get(), 1, 0.00214285f),
                    new LootEntry(ModItems.HELL_ON_EARTH.get(), 1, 0.00214285f),
                    new LootEntry(ModItems.ORB_OF_WORLD_DESTRUCTION.get(), 1, 0.00214285f),
                    new LootEntry(ModItems.BLOODTHIRST.get(), 1, 0.00214285f),
                    new LootEntry(ModItems.SOUL_MAGNET.get(), 1, 0.00214285f),
                    new LootEntry(ModItems.PRIDE_INFUSED_AIGRETTE.get(), 1, 0.00214285f),
                    new LootEntry(ModItems.IMMOLATION_OF_HEART.get(), 1, 0.00214285f)
            );
            injectLoot(event.getTable(), ruinedPortalLoot);
        }

        if (name.equals(new ResourceLocation("minecraft", "gameplay/piglin_bartering"))) {

            List<LootEntry> ruinedPortalLoot = List.of(
                    // Rare (0.07825% per item)
                    new LootEntry(BraceletOfPride.BRACELET_OF_PRIDE_RARE.get(), 1, 0.0007825f),
                    new LootEntry(FingersOfLust.FINGERS_OF_LUST_RARE.get(), 1, 0.0007825f),
                    new LootEntry(TemptingWhispers.TEMPTING_WHISPERS_RARE.get(), 1, 0.0007825f),
                    new LootEntry(CoilOfWrath.COIL_OF_WRATH_RARE.get(), 1, 0.0007825f),
                    new LootEntry(GluttonySignet.GLUTTONY_SIGNET_RARE.get(), 1, 0.0007825f),
                    new LootEntry(EnvyCollar.COLLAR_OF_ENVY_RARE.get(), 1, 0.0007825f),

                    // Epic (0.004165% per item)
                    new LootEntry(BraceletOfPride.BRACELET_OF_PRIDE_EPIC.get(), 1, 0.00004165f),
                    new LootEntry(FingersOfLust.FINGERS_OF_LUST_EPIC.get(), 1, 0.00004165f),
                    new LootEntry(TemptingWhispers.TEMPTING_WHISPERS_EPIC.get(), 1, 0.00004165f),
                    new LootEntry(CoilOfWrath.COIL_OF_WRATH_EPIC.get(), 1, 0.00004165f),
                    new LootEntry(GluttonySignet.GLUTTONY_SIGNET_EPIC.get(), 1, 0.00004165f),
                    new LootEntry(EnvyCollar.COLLAR_OF_ENVY_EPIC.get(), 1, 0.00004165f),

                    // Legendary (0.000835% per item)
                    new LootEntry(BraceletOfPride.BRACELET_OF_PRIDE_LEGENDARY.get(), 1, 0.00000835f),
                    new LootEntry(FingersOfLust.FINGERS_OF_LUST_LEGENDARY.get(), 1, 0.00000835f),
                    new LootEntry(TemptingWhispers.TEMPTING_WHISPERS_LEGENDARY.get(), 1, 0.00000835f),
                    new LootEntry(CoilOfWrath.COIL_OF_WRATH_LEGENDARY.get(), 1, 0.00000835f),
                    new LootEntry(GluttonySignet.GLUTTONY_SIGNET_LEGENDARY.get(), 1, 0.00000835f),
                    new LootEntry(EnvyCollar.COLLAR_OF_ENVY_LEGENDARY.get(), 1, 0.00000835f),

                    // Mythic (0.000085% per item)
                    new LootEntry(BraceletOfPride.BRACELET_OF_PRIDE_MYTHIC.get(), 1, 0.00000085f),
                    new LootEntry(FingersOfLust.FINGERS_OF_LUST_MYTHIC.get(), 1, 0.00000085f),
                    new LootEntry(TemptingWhispers.TEMPTING_WHISPERS_MYTHIC.get(), 1, 0.00000085f),
                    new LootEntry(CoilOfWrath.COIL_OF_WRATH_MYTHIC.get(), 1, 0.00000085f),
                    new LootEntry(GluttonySignet.GLUTTONY_SIGNET_MYTHIC.get(), 1, 0.00000085f),
                    new LootEntry(EnvyCollar.COLLAR_OF_ENVY_MYTHIC.get(), 1, 0.00000085f),

                    new LootEntry(UtilityAccessories.CLOUDTREADER_BOOTS.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.ENDERMAN_FINGERS.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.REINFORCED_DIAMOND_PLATING.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.MONIS_LUCKY_CHARM.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.ANTIQUE_POCKET_WATCH.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.CLOUDSPIRE_GEM.get(), 1, 0.00083f),

                    new LootEntry(ModItems.WEAK_POINT_STUDIES.get(), 1, 0.001071425f),
                    new LootEntry(ModItems.WARLORDS_GAZE.get(), 1, 0.001071425f),
                    new LootEntry(ModItems.RAGE_AMPLIFIER.get(), 1, 0.001071425f),
                    new LootEntry(ModItems.HELL_ON_EARTH.get(), 1, 0.001071425f),
                    new LootEntry(ModItems.ORB_OF_WORLD_DESTRUCTION.get(), 1, 0.001071425f),
                    new LootEntry(ModItems.BLOODTHIRST.get(), 1, 0.001071425f),
                    new LootEntry(ModItems.SOUL_MAGNET.get(), 1, 0.001071425f),
                    new LootEntry(ModItems.PRIDE_INFUSED_AIGRETTE.get(), 1, 0.001071425f),
                    new LootEntry(ModItems.IMMOLATION_OF_HEART.get(), 1, 0.001071425f),
                    new LootEntry(ModItems.RESTORING_AURA.get(), 1, 0.001071425f),
                    new LootEntry(ModItems.DIVINITY_EXTRACTION.get(), 1, 0.001071425f)
            );
            injectLoot(event.getTable(), ruinedPortalLoot);
        }

        if (name.equals(new ResourceLocation("minecraft", "chests/ancient_city"))) {

            List<LootEntry> ancientCityLoot = List.of(
                    // Rare (2.3475% per item)
                    new LootEntry(BandOfUnknown.BAND_OF_THE_UNKNOWN_RARE.get(), 1, 0.023475f),
                    new LootEntry(KeyOfTheUnknown.KEY_OF_THE_UNKNOWN_RARE.get(), 1, 0.023475f),
                    new LootEntry(WardenSkin.WARDEN_SKIN_FORGED_BRACERS_RARE.get(), 1, 0.023475f),
                    new LootEntry(Shrieks.SHRIEKS_OF_UNSEEING_RARE.get(), 1, 0.023475f),

                    // Epic (0.125% per item)
                    new LootEntry(BandOfUnknown.BAND_OF_THE_UNKNOWN_EPIC.get(), 1, 0.00125f),
                    new LootEntry(KeyOfTheUnknown.KEY_OF_THE_UNKNOWN_EPIC.get(), 1, 0.00125f),
                    new LootEntry(WardenSkin.WARDEN_SKIN_FORGED_BRACERS_EPIC.get(), 1, 0.00125f),
                    new LootEntry(Shrieks.SHRIEKS_OF_UNSEEING_EPIC.get(), 1, 0.00125f),

                    // Legendary (0.025% per item)
                    new LootEntry(BandOfUnknown.BAND_OF_THE_UNKNOWN_LEGENDARY.get(), 1, 0.00025f),
                    new LootEntry(KeyOfTheUnknown.KEY_OF_THE_UNKNOWN_LEGENDARY.get(), 1, 0.00025f),
                    new LootEntry(WardenSkin.WARDEN_SKIN_FORGED_BRACERS_LEGENDARY.get(), 1, 0.00025f),
                    new LootEntry(Shrieks.SHRIEKS_OF_UNSEEING_LEGENDARY.get(), 1, 0.00025f),

                    // Mythic (0.0025% per item)
                    new LootEntry(BandOfUnknown.BAND_OF_THE_UNKNOWN_MYTHIC.get(), 1, 0.000025f),
                    new LootEntry(KeyOfTheUnknown.KEY_OF_THE_UNKNOWN_MYTHIC.get(), 1, 0.000025f),
                    new LootEntry(WardenSkin.WARDEN_SKIN_FORGED_BRACERS_MYTHIC.get(), 1, 0.000025f),
                    new LootEntry(Shrieks.SHRIEKS_OF_UNSEEING_MYTHIC.get(), 1, 0.000025f),

                    // Utility (~1.25% per item)
                    new LootEntry(UtilityAccessories.CLOUDTREADER_BOOTS.get(), 1, 0.0083f),
                    new LootEntry(UtilityAccessories.ENDERMAN_FINGERS.get(), 1, 0.0083f),
                    new LootEntry(UtilityAccessories.REINFORCED_DIAMOND_PLATING.get(), 1, 0.0083f),
                    new LootEntry(UtilityAccessories.MONIS_LUCKY_CHARM.get(), 1, 0.0083f),
                    new LootEntry(UtilityAccessories.ANTIQUE_POCKET_WATCH.get(), 1, 0.0083f),
                    new LootEntry(UtilityAccessories.CLOUDSPIRE_GEM.get(), 1, 0.0083f),

                    // Accessory upgrade smithing template (~3%)
                    new LootEntry(ModItems.ACCESSORY_UPGRADE_SMITHING_TEMPLATE.get(), 1, 0.03f),
                    new LootEntry(ModItems.PEARL_OF_REPLENISHING.get(), 1, 0.1F),
                    new LootEntry(ModItems.PEARL_OF_RECHARGING.get(), 1, 0.05F),
                    new LootEntry(ModItems.PEARL_OF_RENEWING.get(), 1, 0.025F),
                    new LootEntry(ModItems.PEARL_OF_RESTORING.get(), 1, 0.0125F),
                    new LootEntry(ModItems.PEARL_OF_REJUVENATING.get(), 1, 0.00625F),
                    new LootEntry(ModItems.SHARD_OF_INFUSION.get(), 1, 0.1F),
                    new LootEntry(ModItems.SHARD_OF_AUGMENTATION.get(), 1, 0.05F),
                    new LootEntry(ModItems.SHARD_OF_AMPLIFICATION.get(), 1, 0.025F),
                    new LootEntry(ModItems.SHARD_OF_EMPOWERMENT.get(), 1, 0.0125F),
                    new LootEntry(ModItems.SHARD_OF_ASCENDANCY.get(), 1, 0.00625F),
                    new LootEntry(ModItems.COMMON_SCRAP_ESSENCE.get(), 1, 0.05F),
                    new LootEntry(ModItems.UNCOMMON_SCRAP_ESSENCE.get(), 1, 0.025F),
                    new LootEntry(ModItems.RARE_SCRAP_ESSENCE.get(), 1, 0.0125F),
                    new LootEntry(ModItems.EPIC_SCRAP_ESSENCE.get(), 1, 0.00625F),

                    new LootEntry(ModItems.ENDLESS_ADRENALINE_SYRINGE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.DROP_OF_SACRIFICIAL_BLOOD.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.WARLORDS_GAZE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.LIGHTNING_INFUSION.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.SOUL_MAGNET.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.RAGE_AMPLIFIER.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.LIFE_TOUCH.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.IMMOLATION_OF_HEART.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.DIVINITY_EXTRACTION.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.VIAL_OF_BURSTING_ENERGY.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.DIVINE_SHIELD.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.SUMMONING_STONE.get(), 1, 0.0042857f)
            );
            injectLoot(event.getTable(), ancientCityLoot);
        }


        if (name.equals(new ResourceLocation("minecraft", "chests/end_city_treasure"))) {

            List<LootEntry> endCityLoot = List.of(
                    // Rare (1.565% per item)
                    new LootEntry(DragonEyeEmbeddedRing.DRAGON_EYE_EMBEDDED_RING_RARE.get(), 1, 0.01565f),
                    new LootEntry(CorruptedLostRing.CORRUPTED_RING_OF_THE_LOST_RARE.get(), 1, 0.01565f),
                    new LootEntry(DragonTeethNecklace.DRAGON_TEETH_NECKLACE_RARE.get(), 1, 0.01565f),
                    new LootEntry(Enderman.ENDERMANS_SEVERED_ARM_RARE.get(), 1, 0.01565f),
                    new LootEntry(DragonClaw.DRAGON_CLAW_GLOVES_RARE.get(), 1, 0.01565f),
                    new LootEntry(DragonRoar.DRAGONS_ROAR_RARE.get(), 1, 0.01565f),

                    // Epic (0.0833% per item)
                    new LootEntry(DragonEyeEmbeddedRing.DRAGON_EYE_EMBEDDED_RING_EPIC.get(), 1, 0.000833f),
                    new LootEntry(CorruptedLostRing.CORRUPTED_RING_OF_THE_LOST_EPIC.get(), 1, 0.000833f),
                    new LootEntry(DragonTeethNecklace.DRAGON_TEETH_NECKLACE_EPIC.get(), 1, 0.000833f),
                    new LootEntry(Enderman.ENDERMANS_SEVERED_ARM_EPIC.get(), 1, 0.000833f),
                    new LootEntry(DragonClaw.DRAGON_CLAW_GLOVES_EPIC.get(), 1, 0.000833f),
                    new LootEntry(DragonRoar.DRAGONS_ROAR_EPIC.get(), 1, 0.000833f),

                    // Legendary (0.0167% per item)
                    new LootEntry(DragonEyeEmbeddedRing.DRAGON_EYE_EMBEDDED_RING_LEGENDARY.get(), 1, 0.000167f),
                    new LootEntry(CorruptedLostRing.CORRUPTED_RING_OF_THE_LOST_LEGENDARY.get(), 1, 0.000167f),
                    new LootEntry(DragonTeethNecklace.DRAGON_TEETH_NECKLACE_LEGENDARY.get(), 1, 0.000167f),
                    new LootEntry(Enderman.ENDERMANS_SEVERED_ARM_LEGENDARY.get(), 1, 0.000167f),
                    new LootEntry(DragonClaw.DRAGON_CLAW_GLOVES_LEGENDARY.get(), 1, 0.000167f),
                    new LootEntry(DragonRoar.DRAGONS_ROAR_LEGENDARY.get(), 1, 0.000167f),

                    // Mythic (0.00167% per item)
                    new LootEntry(DragonEyeEmbeddedRing.DRAGON_EYE_EMBEDDED_RING_MYTHIC.get(), 1, 0.0000167f),
                    new LootEntry(CorruptedLostRing.CORRUPTED_RING_OF_THE_LOST_MYTHIC.get(), 1, 0.0000167f),
                    new LootEntry(DragonTeethNecklace.DRAGON_TEETH_NECKLACE_MYTHIC.get(), 1, 0.0000167f),
                    new LootEntry(Enderman.ENDERMANS_SEVERED_ARM_MYTHIC.get(), 1, 0.0000167f),
                    new LootEntry(DragonClaw.DRAGON_CLAW_GLOVES_MYTHIC.get(), 1, 0.0000167f),
                    new LootEntry(DragonRoar.DRAGONS_ROAR_MYTHIC.get(), 1, 0.0000167f),

                    // Utility (~1.25% per item)
                    new LootEntry(UtilityAccessories.CLOUDTREADER_BOOTS.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.ENDERMAN_FINGERS.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.REINFORCED_DIAMOND_PLATING.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.MONIS_LUCKY_CHARM.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.ANTIQUE_POCKET_WATCH.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.CLOUDSPIRE_GEM.get(), 1, 0.00083f),

                    // Accessory upgrade smithing template (~3%)
                    new LootEntry(ModItems.ACCESSORY_UPGRADE_SMITHING_TEMPLATE.get(), 1, 0.03f),
                    new LootEntry(ModItems.PEARL_OF_REPLENISHING.get(), 1, 0.1F),
                    new LootEntry(ModItems.PEARL_OF_RECHARGING.get(), 1, 0.05F),
                    new LootEntry(ModItems.PEARL_OF_RENEWING.get(), 1, 0.025F),
                    new LootEntry(ModItems.PEARL_OF_RESTORING.get(), 1, 0.0125F),
                    new LootEntry(ModItems.PEARL_OF_REJUVENATING.get(), 1, 0.00625F),
                    new LootEntry(ModItems.SHARD_OF_INFUSION.get(), 1, 0.1F),
                    new LootEntry(ModItems.SHARD_OF_AUGMENTATION.get(), 1, 0.05F),
                    new LootEntry(ModItems.SHARD_OF_AMPLIFICATION.get(), 1, 0.025F),
                    new LootEntry(ModItems.SHARD_OF_EMPOWERMENT.get(), 1, 0.0125F),
                    new LootEntry(ModItems.SHARD_OF_ASCENDANCY.get(), 1, 0.00625F),
                    new LootEntry(ModItems.COMMON_SCRAP_ESSENCE.get(), 1, 0.05F),
                    new LootEntry(ModItems.UNCOMMON_SCRAP_ESSENCE.get(), 1, 0.025F),
                    new LootEntry(ModItems.RARE_SCRAP_ESSENCE.get(), 1, 0.0125F),
                    new LootEntry(ModItems.EPIC_SCRAP_ESSENCE.get(), 1, 0.00625F),

                    new LootEntry(ModItems.BOTTLED_LIGHT.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.HANKS_EYE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.HANKS_OTHER_EYE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.WEAK_POINT_STUDIES.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.LIGHTNING_INFUSION.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.WORMHOLE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.ENDLESS_ADRENALINE_SYRINGE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.DIVINITY_EXTRACTION.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.WIND_WALKER_ARROW.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.VIAL_OF_BURSTING_ENERGY.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.DIVINE_SHIELD.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.THORN_COVERED_FORCEFIELD.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.SUMMONING_STONE.get(), 1, 0.0042857f)
            );
            injectLoot(event.getTable(), endCityLoot);
        }

        if (name.equals(new ResourceLocation("minecraft", "chests/stronghold_corridor")) ||
                name.equals(new ResourceLocation("minecraft", "chests/stronghold_crossing")) ||
                name.equals(new ResourceLocation("minecraft", "chests/stronghold_library"))) {

            List<LootEntry> strongholdLoot = List.of(
                    // Rare (2.3475% per item)
                    new LootEntry(CorruptedLostRing.CORRUPTED_RING_OF_THE_LOST_RARE.get(), 1, 0.023475f),
                    new LootEntry(Enderman.ENDERMANS_SEVERED_ARM_RARE.get(), 1, 0.023475f),

                    // Epic (0.125% per item)
                    new LootEntry(CorruptedLostRing.CORRUPTED_RING_OF_THE_LOST_EPIC.get(), 1, 0.00125f),
                    new LootEntry(Enderman.ENDERMANS_SEVERED_ARM_EPIC.get(), 1, 0.00125f),

                    // Legendary (0.025% per item)
                    new LootEntry(CorruptedLostRing.CORRUPTED_RING_OF_THE_LOST_LEGENDARY.get(), 1, 0.00025f),
                    new LootEntry(Enderman.ENDERMANS_SEVERED_ARM_LEGENDARY.get(), 1, 0.00025f),

                    // Mythic (0.0025% per item)
                    new LootEntry(CorruptedLostRing.CORRUPTED_RING_OF_THE_LOST_MYTHIC.get(), 1, 0.000025f),
                    new LootEntry(Enderman.ENDERMANS_SEVERED_ARM_MYTHIC.get(), 1, 0.000025f),

                    // Utility
                    new LootEntry(UtilityAccessories.CLOUDTREADER_BOOTS.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.ENDERMAN_FINGERS.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.REINFORCED_DIAMOND_PLATING.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.MONIS_LUCKY_CHARM.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.ANTIQUE_POCKET_WATCH.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.CLOUDSPIRE_GEM.get(), 1, 0.00083f),

                    new LootEntry(ModItems.ACCESSORY_UPGRADE_SMITHING_TEMPLATE.get(), 1, 0.03f),
                    new LootEntry(ModItems.PEARL_OF_REPLENISHING.get(), 1, 0.1F),
                    new LootEntry(ModItems.PEARL_OF_RECHARGING.get(), 1, 0.05F),
                    new LootEntry(ModItems.PEARL_OF_RENEWING.get(), 1, 0.025F),
                    new LootEntry(ModItems.PEARL_OF_RESTORING.get(), 1, 0.0125F),
                    new LootEntry(ModItems.PEARL_OF_REJUVENATING.get(), 1, 0.00625F),
                    new LootEntry(ModItems.SHARD_OF_INFUSION.get(), 1, 0.1F),
                    new LootEntry(ModItems.SHARD_OF_AUGMENTATION.get(), 1, 0.05F),
                    new LootEntry(ModItems.SHARD_OF_AMPLIFICATION.get(), 1, 0.025F),
                    new LootEntry(ModItems.SHARD_OF_EMPOWERMENT.get(), 1, 0.0125F),
                    new LootEntry(ModItems.SHARD_OF_ASCENDANCY.get(), 1, 0.00625F),
                    new LootEntry(ModItems.COMMON_SCRAP_ESSENCE.get(), 1, 0.05F),
                    new LootEntry(ModItems.UNCOMMON_SCRAP_ESSENCE.get(), 1, 0.025F),
                    new LootEntry(ModItems.RARE_SCRAP_ESSENCE.get(), 1, 0.0125F),
                    new LootEntry(ModItems.EPIC_SCRAP_ESSENCE.get(), 1, 0.00625F),

                    new LootEntry(ModItems.BOTTLED_LIGHT.get(), 1, 0.00214285f),
                    new LootEntry(ModItems.HANKS_EYE.get(), 1, 0.00214285f),
                    new LootEntry(ModItems.HANKS_OTHER_EYE.get(), 1, 0.00214285f),
                    new LootEntry(ModItems.WEAK_POINT_STUDIES.get(), 1, 0.00214285f),
                    new LootEntry(ModItems.LIGHTNING_INFUSION.get(), 1, 0.00214285f),
                    new LootEntry(ModItems.WORMHOLE.get(), 1, 0.00214285f),
                    new LootEntry(ModItems.ENDLESS_ADRENALINE_SYRINGE.get(), 1, 0.00214285f),
                    new LootEntry(ModItems.DIVINITY_EXTRACTION.get(), 1, 0.00214285f),
                    new LootEntry(ModItems.WIND_WALKER_ARROW.get(), 1, 0.00214285f),
                    new LootEntry(ModItems.VIAL_OF_BURSTING_ENERGY.get(), 1, 0.00214285f),
                    new LootEntry(ModItems.DIVINE_SHIELD.get(), 1, 0.00214285f),
                    new LootEntry(ModItems.THORN_COVERED_FORCEFIELD.get(), 1, 0.00214285f),
                    new LootEntry(ModItems.CUPIDS_ARROW.get(), 1, 0.00214285f)

            );
            injectLoot(event.getTable(), strongholdLoot);
        }


        if (name.equals(new ResourceLocation("minecraft", "chests/igloo_chest")) ||
                name.equals(new ResourceLocation("minecraft", "archeology/ocean_ruin_cold"))) {

            List<LootEntry> iglooLoot = List.of(
                    // Rare (~9.39% per item)
                    new LootEntry(PendantOfSnowflake.PENDANT_OF_FLOATING_SNOWFLAKE_RARE.get(), 1, 2*0.0939f),
                    new LootEntry(ColdCoil.COLDYS_COLD_COIL_OF_COLD_RARE.get(), 1, 2*0.0939f),

                    // Epic (0.25% per item)
                    new LootEntry(PendantOfSnowflake.PENDANT_OF_FLOATING_SNOWFLAKE_EPIC.get(), 1, 2*0.0025f),
                    new LootEntry(ColdCoil.COLDYS_COLD_COIL_OF_COLD_EPIC.get(), 1, 2*0.0025f),

                    // Legendary (0.05% per item)
                    new LootEntry(PendantOfSnowflake.PENDANT_OF_FLOATING_SNOWFLAKE_LEGENDARY.get(), 1, 2*0.0005f),
                    new LootEntry(ColdCoil.COLDYS_COLD_COIL_OF_COLD_LEGENDARY.get(), 1, 2*0.0005f),

                    // Mythic (0.005% per item)
                    new LootEntry(PendantOfSnowflake.PENDANT_OF_FLOATING_SNOWFLAKE_MYTHIC.get(), 1, 2*0.00005f),
                    new LootEntry(ColdCoil.COLDYS_COLD_COIL_OF_COLD_MYTHIC.get(), 1, 2*0.00005f),

                    // Utility (~1.25% per item)
                    new LootEntry(UtilityAccessories.CLOUDTREADER_BOOTS.get(), 1, 2*0.00083f),
                    new LootEntry(UtilityAccessories.ENDERMAN_FINGERS.get(), 1, 2*0.00083f),
                    new LootEntry(UtilityAccessories.REINFORCED_DIAMOND_PLATING.get(), 1, 2*0.00083f),
                    new LootEntry(UtilityAccessories.MONIS_LUCKY_CHARM.get(), 1, 2*0.00083f),
                    new LootEntry(UtilityAccessories.ANTIQUE_POCKET_WATCH.get(), 1, 2*0.00083f),
                    new LootEntry(UtilityAccessories.CLOUDSPIRE_GEM.get(), 1, 2*0.00083f),

                    // Accessory upgrade smithing template (~3%)
                    new LootEntry(ModItems.ACCESSORY_UPGRADE_SMITHING_TEMPLATE.get(), 1, 2*0.03f),
                    new LootEntry(ModItems.PEARL_OF_REPLENISHING.get(), 1, 2*0.1F),
                    new LootEntry(ModItems.PEARL_OF_RECHARGING.get(), 1, 2*0.05F),
                    new LootEntry(ModItems.PEARL_OF_RENEWING.get(), 1, 2*0.025F),
                    new LootEntry(ModItems.PEARL_OF_RESTORING.get(), 1, 2*0.0125F),
                    new LootEntry(ModItems.PEARL_OF_REJUVENATING.get(), 1, 2*0.00625F),
                    new LootEntry(ModItems.SHARD_OF_INFUSION.get(), 1, 2*0.1F),
                    new LootEntry(ModItems.SHARD_OF_AUGMENTATION.get(), 1, 2*0.05F),
                    new LootEntry(ModItems.SHARD_OF_AMPLIFICATION.get(), 1, 2*0.025F),
                    new LootEntry(ModItems.SHARD_OF_EMPOWERMENT.get(), 1, 2*0.0125F),
                    new LootEntry(ModItems.SHARD_OF_ASCENDANCY.get(), 1, 2*0.00625F),
                    new LootEntry(ModItems.COMMON_SCRAP_ESSENCE.get(), 1, 2*0.05F),
                    new LootEntry(ModItems.UNCOMMON_SCRAP_ESSENCE.get(), 1, 2*0.025F),
                    new LootEntry(ModItems.RARE_SCRAP_ESSENCE.get(), 1, 2*0.0125F),
                    new LootEntry(ModItems.EPIC_SCRAP_ESSENCE.get(), 1, 2*0.00625F),

                    new LootEntry(ModItems.BOTTLED_LIGHT.get(), 1, 0.0085714f),
                    new LootEntry(ModItems.CUPIDS_ARROW.get(), 1, 0.0085714f),
                    new LootEntry(ModItems.DROP_OF_SACRIFICIAL_BLOOD.get(), 1, 0.0085714f),
                    new LootEntry(ModItems.HANKS_EYE.get(), 1, 0.0085714f),
                    new LootEntry(ModItems.HANKS_OTHER_EYE.get(), 1, 0.0085714f),
                    new LootEntry(ModItems.GUARDIAN_ANGEL.get(), 1, 0.0085714f),
                    new LootEntry(ModItems.WEAK_POINT_STUDIES.get(), 1, 0.0085714f),
                    new LootEntry(ModItems.RESTORING_AURA.get(), 1, 0.0085714f),
                    new LootEntry(ModItems.WIND_WALKER_ARROW.get(), 1, 0.0085714f),
                    new LootEntry(ModItems.BROKEN_HEALTH_POTION.get(), 1, 0.0085714f),
                    new LootEntry(ModItems.HORN_OF_FEARMONGERING.get(), 1, 0.0085714f)
            );
            injectLoot(event.getTable(), iglooLoot);
        }


        if (name.equals(new ResourceLocation("minecraft", "chests/desert_pyramid")) ||
                name.equals(new ResourceLocation("minecraft", "archeology/desert_pyramid")) ||
                name.equals(new ResourceLocation("minecraft", "archeology/desert_well"))) {

            List<LootEntry> desertLoot = List.of(
                    // Rare (9.39% total divided by 2 items)
                    new LootEntry(SunstoneForged.SUNSTONE_FORGED_RING_RARE.get(), 1, 0.04695f),
                    new LootEntry(SunsGaze.SUNS_GAZE_RARE.get(), 1, 0.04695f),

                    // Epic (0.5% total divided by 2 items)
                    new LootEntry(SunstoneForged.SUNSTONE_FORGED_RING_EPIC.get(), 1, 0.0025f),
                    new LootEntry(SunsGaze.SUNS_GAZE_EPIC.get(), 1, 0.0025f),

                    // Legendary (0.1% total divided by 2 items)
                    new LootEntry(SunstoneForged.SUNSTONE_FORGED_RING_LEGENDARY.get(), 1, 0.0005f),
                    new LootEntry(SunsGaze.SUNS_GAZE_LEGENDARY.get(), 1, 0.0005f),

                    // Mythic (0.01% total divided by 2 items)
                    new LootEntry(SunstoneForged.SUNSTONE_FORGED_RING_MYTHIC.get(), 1, 0.00005f),
                    new LootEntry(SunsGaze.SUNS_GAZE_MYTHIC.get(), 1, 0.00005f),

                    // Utility (~1.25% per item)
                    new LootEntry(UtilityAccessories.CLOUDTREADER_BOOTS.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.ENDERMAN_FINGERS.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.REINFORCED_DIAMOND_PLATING.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.MONIS_LUCKY_CHARM.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.ANTIQUE_POCKET_WATCH.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.CLOUDSPIRE_GEM.get(), 1, 0.00083f),

                    // Accessory upgrade smithing template (~3%)
                    new LootEntry(ModItems.ACCESSORY_UPGRADE_SMITHING_TEMPLATE.get(), 1, 0.03f),
                    new LootEntry(ModItems.PEARL_OF_REPLENISHING.get(), 1, 0.1F),
                    new LootEntry(ModItems.PEARL_OF_RECHARGING.get(), 1, 0.05F),
                    new LootEntry(ModItems.PEARL_OF_RENEWING.get(), 1, 0.025F),
                    new LootEntry(ModItems.PEARL_OF_RESTORING.get(), 1, 0.0125F),
                    new LootEntry(ModItems.PEARL_OF_REJUVENATING.get(), 1, 0.00625F),
                    new LootEntry(ModItems.SHARD_OF_INFUSION.get(), 1, 0.1F),
                    new LootEntry(ModItems.SHARD_OF_AUGMENTATION.get(), 1, 0.05F),
                    new LootEntry(ModItems.SHARD_OF_AMPLIFICATION.get(), 1, 0.025F),
                    new LootEntry(ModItems.SHARD_OF_EMPOWERMENT.get(), 1, 0.0125F),
                    new LootEntry(ModItems.SHARD_OF_ASCENDANCY.get(), 1, 0.00625F),
                    new LootEntry(ModItems.COMMON_SCRAP_ESSENCE.get(), 1, 0.05F),
                    new LootEntry(ModItems.UNCOMMON_SCRAP_ESSENCE.get(), 1, 0.025F),
                    new LootEntry(ModItems.RARE_SCRAP_ESSENCE.get(), 1, 0.0125F),
                    new LootEntry(ModItems.EPIC_SCRAP_ESSENCE.get(), 1, 0.00625F),

                    new LootEntry(ModItems.BOTTLED_LIGHT.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.BANNER_OF_DETERMINATION.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.HORN_OF_FEARMONGERING.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.HELL_ON_EARTH.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.RAGE_AMPLIFIER.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.SUNSTONE_GEM.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.WARLORDS_GAZE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.IGNITION_MARK.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.PRIDE_INFUSED_AIGRETTE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.RESTORING_AURA.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.WIND_WALKER_ARROW.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.NATURES_BLESSING.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.IGNITION_MARK.get(), 1, 0.0042857f)
            );
            injectLoot(event.getTable(), desertLoot);
        }


        if (name.equals(new ResourceLocation("minecraft", "chests/jungle_temple"))) {
            List<LootEntry> jungleLoot = List.of(
                    // Rare (9.39% total divided by 2 items)
                    new LootEntry(HangingTigerTooth.HANGING_TIGER_TOOTH_RARE.get(), 1, 0.04695f),
                    new LootEntry(SerpentSkin.SERPENT_SKIN_WRAP_RARE.get(), 1, 0.04695f),

                    // Epic (0.5% total divided by 2 items)
                    new LootEntry(HangingTigerTooth.HANGING_TIGER_TOOTH_EPIC.get(), 1, 0.0025f),
                    new LootEntry(SerpentSkin.SERPENT_SKIN_WRAP_EPIC.get(), 1, 0.0025f),

                    // Legendary (0.1% total divided by 2 items)
                    new LootEntry(HangingTigerTooth.HANGING_TIGER_TOOTH_LEGENDARY.get(), 1, 0.0005f),
                    new LootEntry(SerpentSkin.SERPENT_SKIN_WRAP_LEGENDARY.get(), 1, 0.0005f),

                    // Mythic (0.01% total divided by 2 items)
                    new LootEntry(HangingTigerTooth.HANGING_TIGER_TOOTH_MYTHIC.get(), 1, 0.00005f),
                    new LootEntry(SerpentSkin.SERPENT_SKIN_WRAP_MYTHIC.get(), 1, 0.00005f),

                    // Utility (~1.25% per item)
                    new LootEntry(UtilityAccessories.CLOUDTREADER_BOOTS.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.ENDERMAN_FINGERS.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.REINFORCED_DIAMOND_PLATING.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.MONIS_LUCKY_CHARM.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.ANTIQUE_POCKET_WATCH.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.CLOUDSPIRE_GEM.get(), 1, 0.00083f),

                    // Accessory upgrade smithing template (~3%)
                    new LootEntry(ModItems.ACCESSORY_UPGRADE_SMITHING_TEMPLATE.get(), 1, 0.03f),
                    new LootEntry(ModItems.PEARL_OF_REPLENISHING.get(), 1, 0.1F),
                    new LootEntry(ModItems.PEARL_OF_RECHARGING.get(), 1, 0.05F),
                    new LootEntry(ModItems.PEARL_OF_RENEWING.get(), 1, 0.025F),
                    new LootEntry(ModItems.PEARL_OF_RESTORING.get(), 1, 0.0125F),
                    new LootEntry(ModItems.PEARL_OF_REJUVENATING.get(), 1, 0.00625F),
                    new LootEntry(ModItems.SHARD_OF_INFUSION.get(), 1, 0.1F),
                    new LootEntry(ModItems.SHARD_OF_AUGMENTATION.get(), 1, 0.05F),
                    new LootEntry(ModItems.SHARD_OF_AMPLIFICATION.get(), 1, 0.025F),
                    new LootEntry(ModItems.SHARD_OF_EMPOWERMENT.get(), 1, 0.0125F),
                    new LootEntry(ModItems.SHARD_OF_ASCENDANCY.get(), 1, 0.00625F),
                    new LootEntry(ModItems.COMMON_SCRAP_ESSENCE.get(), 1, 0.05F),
                    new LootEntry(ModItems.UNCOMMON_SCRAP_ESSENCE.get(), 1, 0.025F),
                    new LootEntry(ModItems.RARE_SCRAP_ESSENCE.get(), 1, 0.0125F),
                    new LootEntry(ModItems.EPIC_SCRAP_ESSENCE.get(), 1, 0.00625F),

                    new LootEntry(ModItems.BOTTLED_LIGHT.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.BANNER_OF_DETERMINATION.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.BRANCH_OF_THE_WORLD_TREE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.GUARDIAN_ANGEL.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.WORMHOLE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.HANKS_EYE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.HANKS_OTHER_EYE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.IGNITION_MARK.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.PRIDE_INFUSED_AIGRETTE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.FOX_EYE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.DIVINITY_EXTRACTION.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.THORN_COVERED_FORCEFIELD.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.NATURES_BLESSING.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.ENDLESS_ADRENALINE_SYRINGE.get(), 1, 0.0042857f)
            );
            injectLoot(event.getTable(), jungleLoot);
        }

        if (name.equals(new ResourceLocation("minecraft", "archeology/trail_ruins_common")) ||
                name.equals(new ResourceLocation("minecraft", "archeology/trail_ruins_rare"))) {
            List<LootEntry> archeologyLoot = List.of(
                    // Rare (9.39% total divided by 2 items)
                    new LootEntry(HangingTigerTooth.HANGING_TIGER_TOOTH_RARE.get(), 1, 0.04695f),
                    new LootEntry(SerpentSkin.SERPENT_SKIN_WRAP_RARE.get(), 1, 0.04695f),

                    // Epic (0.5% total divided by 2 items)
                    new LootEntry(HangingTigerTooth.HANGING_TIGER_TOOTH_EPIC.get(), 1, 0.0025f),
                    new LootEntry(SerpentSkin.SERPENT_SKIN_WRAP_EPIC.get(), 1, 0.0025f),

                    // Legendary (0.1% total divided by 2 items)
                    new LootEntry(HangingTigerTooth.HANGING_TIGER_TOOTH_LEGENDARY.get(), 1, 0.0005f),
                    new LootEntry(SerpentSkin.SERPENT_SKIN_WRAP_LEGENDARY.get(), 1, 0.0005f),

                    // Mythic (0.01% total divided by 2 items)
                    new LootEntry(HangingTigerTooth.HANGING_TIGER_TOOTH_MYTHIC.get(), 1, 0.00005f),
                    new LootEntry(SerpentSkin.SERPENT_SKIN_WRAP_MYTHIC.get(), 1, 0.00005f),

                    // Utility (~1.25% per item)
                    new LootEntry(UtilityAccessories.CLOUDTREADER_BOOTS.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.ENDERMAN_FINGERS.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.REINFORCED_DIAMOND_PLATING.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.MONIS_LUCKY_CHARM.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.ANTIQUE_POCKET_WATCH.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.CLOUDSPIRE_GEM.get(), 1, 0.00083f),

                    new LootEntry(ModItems.BOTTLED_LIGHT.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.BANNER_OF_DETERMINATION.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.BRANCH_OF_THE_WORLD_TREE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.GUARDIAN_ANGEL.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.WORMHOLE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.HANKS_EYE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.HANKS_OTHER_EYE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.IGNITION_MARK.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.PRIDE_INFUSED_AIGRETTE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.FOX_EYE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.WIND_WALKER_ARROW.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.DIVINITY_EXTRACTION.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.THORN_COVERED_FORCEFIELD.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.NATURES_BLESSING.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.ENDLESS_ADRENALINE_SYRINGE.get(), 1, 0.0042857f)
            );

            injectLoot(event.getTable(), archeologyLoot);
        }


        if (name.equals(new ResourceLocation("minecraft", "chests/buried_treasure")) ||
                name.equals(new ResourceLocation("minecraft", "chests/underwater_ruin_big")) ||
                name.equals(new ResourceLocation("minecraft", "chests/underwater_ruin_small")) ||
                name.equals(new ResourceLocation("minecraft", "archeology/ocean_ruin_warm"))) {

            List<LootEntry> waterLoot = List.of(
                    // Rare (9.39% total divided by 2 items)
                    new LootEntry(BottledTsunami.BOTTLED_TSUNAMI_SEA_RARE.get(), 1, 0.04695f),
                    new LootEntry(EndlessWaves.ENDLESS_WAVES_RARE.get(), 1, 0.04695f),

                    // Epic (0.5% total divided by 2 items)
                    new LootEntry(BottledTsunami.BOTTLED_TSUNAMI_SEA_EPIC.get(), 1, 0.0025f),
                    new LootEntry(EndlessWaves.ENDLESS_WAVES_EPIC.get(), 1, 0.0025f),

                    // Legendary (0.1% total divided by 2 items)
                    new LootEntry(BottledTsunami.BOTTLED_TSUNAMI_SEA_LEGENDARY.get(), 1, 0.0005f),
                    new LootEntry(EndlessWaves.ENDLESS_WAVES_LEGENDARY.get(), 1, 0.0005f),

                    // Mythic (0.01% total divided by 2 items)
                    new LootEntry(BottledTsunami.BOTTLED_TSUNAMI_SEA_MYTHIC.get(), 1, 0.00005f),
                    new LootEntry(EndlessWaves.ENDLESS_WAVES_MYTHIC.get(), 1, 0.00005f),

                    // Utility (~1.25% per item)
                    new LootEntry(UtilityAccessories.CLOUDTREADER_BOOTS.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.ENDERMAN_FINGERS.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.REINFORCED_DIAMOND_PLATING.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.MONIS_LUCKY_CHARM.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.ANTIQUE_POCKET_WATCH.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.CLOUDSPIRE_GEM.get(), 1, 0.00083f),

                    // Accessory upgrade smithing template (~3%)
                    new LootEntry(ModItems.ACCESSORY_UPGRADE_SMITHING_TEMPLATE.get(), 1, 0.03f),
                    new LootEntry(ModItems.PEARL_OF_REPLENISHING.get(), 1, 0.1F),
                    new LootEntry(ModItems.PEARL_OF_RECHARGING.get(), 1, 0.05F),
                    new LootEntry(ModItems.PEARL_OF_RENEWING.get(), 1, 0.025F),
                    new LootEntry(ModItems.PEARL_OF_RESTORING.get(), 1, 0.0125F),
                    new LootEntry(ModItems.PEARL_OF_REJUVENATING.get(), 1, 0.00625F),
                    new LootEntry(ModItems.SHARD_OF_INFUSION.get(), 1, 0.1F),
                    new LootEntry(ModItems.SHARD_OF_AUGMENTATION.get(), 1, 0.05F),
                    new LootEntry(ModItems.SHARD_OF_AMPLIFICATION.get(), 1, 0.025F),
                    new LootEntry(ModItems.SHARD_OF_EMPOWERMENT.get(), 1, 0.0125F),
                    new LootEntry(ModItems.SHARD_OF_ASCENDANCY.get(), 1, 0.00625F),
                    new LootEntry(ModItems.COMMON_SCRAP_ESSENCE.get(), 1, 0.05F),
                    new LootEntry(ModItems.UNCOMMON_SCRAP_ESSENCE.get(), 1, 0.025F),
                    new LootEntry(ModItems.RARE_SCRAP_ESSENCE.get(), 1, 0.0125F),
                    new LootEntry(ModItems.EPIC_SCRAP_ESSENCE.get(), 1, 0.00625F),

                    new LootEntry(ModItems.GUARDIAN_ANGEL.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.DROP_OF_SACRIFICIAL_BLOOD.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.BLOODTHIRST.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.FORTRESS_OF_SOLITUDE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.LIFE_TOUCH.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.ORB_OF_WORLD_DESTRUCTION.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.SUNSTONE_GEM.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.IMMOLATION_OF_HEART.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.RESTORING_AURA.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.BROKEN_HEALTH_POTION.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.DIVINE_SHIELD.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.THORN_COVERED_FORCEFIELD.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.NATURES_BLESSING.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.BRANCH_OF_THE_WORLD_TREE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.CUPIDS_ARROW.get(), 1, 0.0042857f)
            );
            injectLoot(event.getTable(), waterLoot);
        }

        if (name.equals(new ResourceLocation("minecraft", "gameplay/fishing/treasure"))) {

            List<LootEntry> waterLoot = List.of(
                    new LootEntry(BottledTsunami.BOTTLED_TSUNAMI_SEA_RARE.get(), 1, 0.01173f),
                    new LootEntry(EndlessWaves.ENDLESS_WAVES_RARE.get(), 1, 0.01173f),


                    new LootEntry(BottledTsunami.BOTTLED_TSUNAMI_SEA_EPIC.get(), 1, 0.000625f),
                    new LootEntry(EndlessWaves.ENDLESS_WAVES_EPIC.get(), 1, 0.000625f),


                    new LootEntry(BottledTsunami.BOTTLED_TSUNAMI_SEA_LEGENDARY.get(), 1, 0.0001f),
                    new LootEntry(EndlessWaves.ENDLESS_WAVES_LEGENDARY.get(), 1, 0.0001f),


                    new LootEntry(BottledTsunami.BOTTLED_TSUNAMI_SEA_MYTHIC.get(), 1, 0.00001f),
                    new LootEntry(EndlessWaves.ENDLESS_WAVES_MYTHIC.get(), 1, 0.00001f),

                    new LootEntry(ModItems.PEARL_OF_RENEWING.get(), 1, 0.025F),
                    new LootEntry(ModItems.SHARD_OF_AMPLIFICATION.get(), 1, 0.025F),
                    new LootEntry(ModItems.UNCOMMON_SCRAP_ESSENCE.get(), 1, 0.025F),

                    new LootEntry(ModItems.GUARDIAN_ANGEL.get(), 1, 0.0005357f),
                    new LootEntry(ModItems.DROP_OF_SACRIFICIAL_BLOOD.get(), 1, 0.0005357f),
                    new LootEntry(ModItems.BLOODTHIRST.get(), 1, 0.0005357f),
                    new LootEntry(ModItems.FORTRESS_OF_SOLITUDE.get(), 1, 0.0005357f),
                    new LootEntry(ModItems.LIFE_TOUCH.get(), 1, 0.0005357f),
                    new LootEntry(ModItems.ORB_OF_WORLD_DESTRUCTION.get(), 1, 0.0005357f),
                    new LootEntry(ModItems.SUNSTONE_GEM.get(), 1, 0.0005357f),
                    new LootEntry(ModItems.FOX_EYE.get(), 1, 0.0005357f),
                    new LootEntry(ModItems.BROKEN_HEALTH_POTION.get(), 1, 0.0005357f),
                    new LootEntry(ModItems.DIVINE_SHIELD.get(), 1, 0.0005357f),
                    new LootEntry(ModItems.NATURES_BLESSING.get(), 1, 0.0005357f),
                    new LootEntry(ModItems.BRANCH_OF_THE_WORLD_TREE.get(), 1, 0.0005357f)
            );
            injectLoot(event.getTable(), waterLoot);
        }

        if (name.equals(new ResourceLocation("minecraft", "chests/abandoned_mineshaft")) ||
                name.equals(new ResourceLocation("minecraft", "chests/simple_dungeon"))) {

            List<LootEntry> dungeonLoot = List.of(
                    // Rare (9.39% total divided by 2 items)
                    new LootEntry(ReinforcedSteel.REINFORCED_STEEL_BRACERS_RARE.get(), 1, 0.04695f),
                    new LootEntry(Tear.TEAR_OF_THE_FORGOTTEN_RARE.get(), 1, 0.04695f),

                    // Epic (0.5% total divided by 2 items)
                    new LootEntry(ReinforcedSteel.REINFORCED_STEEL_BRACERS_EPIC.get(), 1, 0.0025f),
                    new LootEntry(Tear.TEAR_OF_THE_FORGOTTEN_EPIC.get(), 1, 0.0025f),

                    // Legendary (0.1% total divided by 2 items)
                    new LootEntry(ReinforcedSteel.REINFORCED_STEEL_BRACERS_LEGENDARY.get(), 1, 0.0005f),
                    new LootEntry(Tear.TEAR_OF_THE_FORGOTTEN_LEGENDARY.get(), 1, 0.0005f),

                    // Mythic (0.01% total divided by 2 items)
                    new LootEntry(ReinforcedSteel.REINFORCED_STEEL_BRACERS_MYTHIC.get(), 1, 0.00005f),
                    new LootEntry(Tear.TEAR_OF_THE_FORGOTTEN_MYTHIC.get(), 1, 0.00005f),

                    // Utility (~1.25% per item)
                    new LootEntry(UtilityAccessories.CLOUDTREADER_BOOTS.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.ENDERMAN_FINGERS.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.REINFORCED_DIAMOND_PLATING.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.MONIS_LUCKY_CHARM.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.ANTIQUE_POCKET_WATCH.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.CLOUDSPIRE_GEM.get(), 1, 0.00083f),

                    new LootEntry(ModItems.ACCESSORY_UPGRADE_SMITHING_TEMPLATE.get(), 1, 0.03f),
                    new LootEntry(ModItems.PEARL_OF_REPLENISHING.get(), 1, 0.1F),
                    new LootEntry(ModItems.PEARL_OF_RECHARGING.get(), 1, 0.05F),
                    new LootEntry(ModItems.PEARL_OF_RENEWING.get(), 1, 0.025F),
                    new LootEntry(ModItems.PEARL_OF_RESTORING.get(), 1, 0.0125F),
                    new LootEntry(ModItems.PEARL_OF_REJUVENATING.get(), 1, 0.00625F),
                    new LootEntry(ModItems.SHARD_OF_INFUSION.get(), 1, 0.1F),
                    new LootEntry(ModItems.SHARD_OF_AUGMENTATION.get(), 1, 0.05F),
                    new LootEntry(ModItems.SHARD_OF_AMPLIFICATION.get(), 1, 0.025F),
                    new LootEntry(ModItems.SHARD_OF_EMPOWERMENT.get(), 1, 0.0125F),
                    new LootEntry(ModItems.SHARD_OF_ASCENDANCY.get(), 1, 0.00625F),
                    new LootEntry(ModItems.COMMON_SCRAP_ESSENCE.get(), 1, 0.05F),
                    new LootEntry(ModItems.UNCOMMON_SCRAP_ESSENCE.get(), 1, 0.025F),
                    new LootEntry(ModItems.RARE_SCRAP_ESSENCE.get(), 1, 0.0125F),
                    new LootEntry(ModItems.EPIC_SCRAP_ESSENCE.get(), 1, 0.00625F),

                    new LootEntry(ModItems.FORTRESS_OF_SOLITUDE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.BLOODTHIRST.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.DROP_OF_SACRIFICIAL_BLOOD.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.HORN_OF_FEARMONGERING.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.LIGHTNING_INFUSION.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.RAGE_AMPLIFIER.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.CUPIDS_ARROW.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.IGNITION_MARK.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.RESTORING_AURA.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.BROKEN_HEALTH_POTION.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.VIAL_OF_BURSTING_ENERGY.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.THORN_COVERED_FORCEFIELD.get(), 1, 0.0042857f)
            );
            injectLoot(event.getTable(), dungeonLoot);
        }

        if (name.equals(EntityType.WARDEN.getDefaultLootTable())) {
            List<LootEntry> wardenLoot = List.of(
                    // Rare (2.3475% × 1/3 ≈ 0.007825 per item)
                    new LootEntry(BandOfUnknown.BAND_OF_THE_UNKNOWN_RARE.get(), 1, 0.007825f),
                    new LootEntry(KeyOfTheUnknown.KEY_OF_THE_UNKNOWN_RARE.get(), 1, 0.007825f),
                    new LootEntry(WardenSkin.WARDEN_SKIN_FORGED_BRACERS_RARE.get(), 1, 0.007825f),
                    new LootEntry(Shrieks.SHRIEKS_OF_UNSEEING_RARE.get(), 1, 0.007825f),

                    // Epic (0.125% × 1/3 ≈ 0.0004165 per item)
                    new LootEntry(BandOfUnknown.BAND_OF_THE_UNKNOWN_EPIC.get(), 1, 0.0004165f),
                    new LootEntry(KeyOfTheUnknown.KEY_OF_THE_UNKNOWN_EPIC.get(), 1, 0.0004165f),
                    new LootEntry(WardenSkin.WARDEN_SKIN_FORGED_BRACERS_EPIC.get(), 1, 0.0004165f),
                    new LootEntry(Shrieks.SHRIEKS_OF_UNSEEING_EPIC.get(), 1, 0.0004165f),

                    // Legendary (0.025% × 1/3 ≈ 0.0000835 per item)
                    new LootEntry(BandOfUnknown.BAND_OF_THE_UNKNOWN_LEGENDARY.get(), 1, 0.0000835f),
                    new LootEntry(KeyOfTheUnknown.KEY_OF_THE_UNKNOWN_LEGENDARY.get(), 1, 0.0000835f),
                    new LootEntry(WardenSkin.WARDEN_SKIN_FORGED_BRACERS_LEGENDARY.get(), 1, 0.0000835f),
                    new LootEntry(Shrieks.SHRIEKS_OF_UNSEEING_LEGENDARY.get(), 1, 0.0000835f),

                    // Mythic (0.0025% × 1/3 ≈ 0.0000085 per item)
                    new LootEntry(BandOfUnknown.BAND_OF_THE_UNKNOWN_MYTHIC.get(), 1, 0.0000085f),
                    new LootEntry(KeyOfTheUnknown.KEY_OF_THE_UNKNOWN_MYTHIC.get(), 1, 0.0000085f),
                    new LootEntry(WardenSkin.WARDEN_SKIN_FORGED_BRACERS_MYTHIC.get(), 1, 0.0000085f),
                    new LootEntry(Shrieks.SHRIEKS_OF_UNSEEING_MYTHIC.get(), 1, 0.0000085f)
            );

            injectLoot(event.getTable(), wardenLoot);

        }

        if (name.equals(EntityType.ENDER_DRAGON.getDefaultLootTable())) {
            List<LootEntry> dragonGuaranteedLoot = List.of(
                    // Rare - 80% total
                    new LootEntry(DragonEyeEmbeddedRing.DRAGON_EYE_EMBEDDED_RING_RARE.get(), 1, 0.2f),
                    new LootEntry(DragonTeethNecklace.DRAGON_TEETH_NECKLACE_RARE.get(), 1, 0.2f),
                    new LootEntry(DragonClaw.DRAGON_CLAW_GLOVES_RARE.get(), 1, 0.2f),
                    new LootEntry(DragonRoar.DRAGONS_ROAR_RARE.get(), 1, 0.2f),

                    // Epic - 15% total
                    new LootEntry(DragonEyeEmbeddedRing.DRAGON_EYE_EMBEDDED_RING_EPIC.get(), 1, 0.0375f),
                    new LootEntry(DragonTeethNecklace.DRAGON_TEETH_NECKLACE_EPIC.get(), 1, 0.0375f),
                    new LootEntry(DragonClaw.DRAGON_CLAW_GLOVES_EPIC.get(), 1, 0.0375f),
                    new LootEntry(DragonRoar.DRAGONS_ROAR_EPIC.get(), 1, 0.0375f),

                    // Legendary - 4% total
                    new LootEntry(DragonEyeEmbeddedRing.DRAGON_EYE_EMBEDDED_RING_LEGENDARY.get(), 1, 0.01f),
                    new LootEntry(DragonTeethNecklace.DRAGON_TEETH_NECKLACE_LEGENDARY.get(), 1, 0.01f),
                    new LootEntry(DragonClaw.DRAGON_CLAW_GLOVES_LEGENDARY.get(), 1, 0.01f),
                    new LootEntry(DragonRoar.DRAGONS_ROAR_LEGENDARY.get(), 1, 0.01f),

                    // Mythic - 1% total
                    new LootEntry(DragonEyeEmbeddedRing.DRAGON_EYE_EMBEDDED_RING_MYTHIC.get(), 1, 0.0025f),
                    new LootEntry(DragonTeethNecklace.DRAGON_TEETH_NECKLACE_MYTHIC.get(), 1, 0.0025f),
                    new LootEntry(DragonClaw.DRAGON_CLAW_GLOVES_MYTHIC.get(), 1, 0.0025f),
                    new LootEntry(DragonRoar.DRAGONS_ROAR_MYTHIC.get(), 1, 0.0025f)
            );

            injectLoot(event.getTable(), dragonGuaranteedLoot);
        }

        if (name.equals(new ResourceLocation("minecraft", "chests/pillager_outpost"))) {

            List<LootEntry> outpostLoot = List.of(
                    // Rare (9.39% total divided by 2 items)
                    new LootEntry(StolenLegacies.STOLEN_LEGACIES_CHOKER_RARE.get(), 1, 0.04695f),
                    new LootEntry(ThieveryWraps.WRAPS_OF_THIEVERY_RARE.get(), 1, 0.04695f),

                    // Epic (0.5% total divided by 2 items)
                    new LootEntry(StolenLegacies.STOLEN_LEGACIES_CHOKER_EPIC.get(), 1, 0.0025f),
                    new LootEntry(ThieveryWraps.WRAPS_OF_THIEVERY_EPIC.get(), 1, 0.0025f),

                    // Legendary (0.1% total divided by 2 items)
                    new LootEntry(StolenLegacies.STOLEN_LEGACIES_CHOKER_LEGENDARY.get(), 1, 0.0005f),
                    new LootEntry(ThieveryWraps.WRAPS_OF_THIEVERY_LEGENDARY.get(), 1, 0.0005f),

                    // Mythic (0.01% total divided by 2 items)
                    new LootEntry(StolenLegacies.STOLEN_LEGACIES_CHOKER_MYTHIC.get(), 1, 0.00005f),
                    new LootEntry(ThieveryWraps.WRAPS_OF_THIEVERY_MYTHIC.get(), 1, 0.00005f),

                    // Utility (~1.25% per item)
                    new LootEntry(UtilityAccessories.CLOUDTREADER_BOOTS.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.ENDERMAN_FINGERS.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.REINFORCED_DIAMOND_PLATING.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.MONIS_LUCKY_CHARM.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.ANTIQUE_POCKET_WATCH.get(), 1, 0.00083f),
                    new LootEntry(UtilityAccessories.CLOUDSPIRE_GEM.get(), 1, 0.00083f),

                    // Accessory upgrade smithing template (~3%)
                    new LootEntry(ModItems.ACCESSORY_UPGRADE_SMITHING_TEMPLATE.get(), 1, 0.03f),
                    new LootEntry(ModItems.PEARL_OF_REPLENISHING.get(), 1, 0.1F),
                    new LootEntry(ModItems.PEARL_OF_RECHARGING.get(), 1, 0.05F),
                    new LootEntry(ModItems.PEARL_OF_RENEWING.get(), 1, 0.025F),
                    new LootEntry(ModItems.PEARL_OF_RESTORING.get(), 1, 0.0125F),
                    new LootEntry(ModItems.PEARL_OF_REJUVENATING.get(), 1, 0.00625F),
                    new LootEntry(ModItems.SHARD_OF_INFUSION.get(), 1, 0.1F),
                    new LootEntry(ModItems.SHARD_OF_AUGMENTATION.get(), 1, 0.05F),
                    new LootEntry(ModItems.SHARD_OF_AMPLIFICATION.get(), 1, 0.025F),
                    new LootEntry(ModItems.SHARD_OF_EMPOWERMENT.get(), 1, 0.0125F),
                    new LootEntry(ModItems.SHARD_OF_ASCENDANCY.get(), 1, 0.00625F),
                    new LootEntry(ModItems.COMMON_SCRAP_ESSENCE.get(), 1, 0.05F),
                    new LootEntry(ModItems.UNCOMMON_SCRAP_ESSENCE.get(), 1, 0.025F),
                    new LootEntry(ModItems.RARE_SCRAP_ESSENCE.get(), 1, 0.0125F),
                    new LootEntry(ModItems.EPIC_SCRAP_ESSENCE.get(), 1, 0.00625F),

                    new LootEntry(ModItems.FORTRESS_OF_SOLITUDE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.BRANCH_OF_THE_WORLD_TREE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.BANNER_OF_DETERMINATION.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.HORN_OF_FEARMONGERING.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.SUNSTONE_GEM.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.LIFE_TOUCH.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.CUPIDS_ARROW.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.FOX_EYE.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.BROKEN_HEALTH_POTION.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.VIAL_OF_BURSTING_ENERGY.get(), 1, 0.0042857f),
                    new LootEntry(ModItems.WIND_WALKER_ARROW.get(), 1, 0.0042857f)
            );
            injectLoot(event.getTable(), outpostLoot);
        }

        if (name.equals(new ResourceLocation("minecraft", "chests/woodland_mansion"))) {

            List<LootEntry> mansionLoot = List.of(
                    // Rare (9.39% per)
                    new LootEntry(StolenLegacies.STOLEN_LEGACIES_CHOKER_RARE.get(), 1, 0.09390f),
                    new LootEntry(ThieveryWraps.WRAPS_OF_THIEVERY_RARE.get(), 1, 0.09390f),

                    // Epic (0.5% per)
                    new LootEntry(StolenLegacies.STOLEN_LEGACIES_CHOKER_EPIC.get(), 1, 0.0050f),
                    new LootEntry(ThieveryWraps.WRAPS_OF_THIEVERY_EPIC.get(), 1, 0.0050f),

                    // Legendary (0.1% per)
                    new LootEntry(StolenLegacies.STOLEN_LEGACIES_CHOKER_LEGENDARY.get(), 1, 0.0010f),
                    new LootEntry(ThieveryWraps.WRAPS_OF_THIEVERY_LEGENDARY.get(), 1, 0.0010f),

                    // Mythic (0.01% per)
                    new LootEntry(StolenLegacies.STOLEN_LEGACIES_CHOKER_MYTHIC.get(), 1, 0.00010f),
                    new LootEntry(ThieveryWraps.WRAPS_OF_THIEVERY_MYTHIC.get(), 1, 0.00010f),

                    // Utility (~1.25% per item)
                    new LootEntry(UtilityAccessories.CLOUDTREADER_BOOTS.get(), 1, 0.01f),
                    new LootEntry(UtilityAccessories.ENDERMAN_FINGERS.get(), 1, 0.01f),
                    new LootEntry(UtilityAccessories.REINFORCED_DIAMOND_PLATING.get(), 1, 0.01f),
                    new LootEntry(UtilityAccessories.MONIS_LUCKY_CHARM.get(), 1, 0.01f),
                    new LootEntry(UtilityAccessories.ANTIQUE_POCKET_WATCH.get(), 1, 0.01f),
                    new LootEntry(UtilityAccessories.CLOUDSPIRE_GEM.get(), 1, 0.01f),

                    // Accessory upgrade smithing template (~3%)
                    new LootEntry(ModItems.ACCESSORY_UPGRADE_SMITHING_TEMPLATE.get(), 1, 0.03f),
                    new LootEntry(ModItems.PEARL_OF_REPLENISHING.get(), 1, 0.1F),
                    new LootEntry(ModItems.PEARL_OF_RECHARGING.get(), 1, 0.05F),
                    new LootEntry(ModItems.PEARL_OF_RENEWING.get(), 1, 0.025F),
                    new LootEntry(ModItems.PEARL_OF_RESTORING.get(), 1, 0.0125F),
                    new LootEntry(ModItems.PEARL_OF_REJUVENATING.get(), 1, 0.00625F),
                    new LootEntry(ModItems.SHARD_OF_INFUSION.get(), 1, 0.1F),
                    new LootEntry(ModItems.SHARD_OF_AUGMENTATION.get(), 1, 0.05F),
                    new LootEntry(ModItems.SHARD_OF_AMPLIFICATION.get(), 1, 0.025F),
                    new LootEntry(ModItems.SHARD_OF_EMPOWERMENT.get(), 1, 0.0125F),
                    new LootEntry(ModItems.SHARD_OF_ASCENDANCY.get(), 1, 0.00625F),
                    new LootEntry(ModItems.COMMON_SCRAP_ESSENCE.get(), 1, 0.05F),
                    new LootEntry(ModItems.UNCOMMON_SCRAP_ESSENCE.get(), 1, 0.025F),
                    new LootEntry(ModItems.RARE_SCRAP_ESSENCE.get(), 1, 0.0125F),
                    new LootEntry(ModItems.EPIC_SCRAP_ESSENCE.get(), 1, 0.00625F),

                    new LootEntry(ModItems.FORTRESS_OF_SOLITUDE.get(), 1, 0.0085714f),
                    new LootEntry(ModItems.BRANCH_OF_THE_WORLD_TREE.get(), 1, 0.0085714f),
                    new LootEntry(ModItems.BANNER_OF_DETERMINATION.get(), 1, 0.0085714f),
                    new LootEntry(ModItems.HORN_OF_FEARMONGERING.get(), 1, 0.0085714f),
                    new LootEntry(ModItems.SUNSTONE_GEM.get(), 1, 0.0085714f),
                    new LootEntry(ModItems.LIFE_TOUCH.get(), 1, 0.0085714f),
                    new LootEntry(ModItems.LIGHTNING_INFUSION.get(), 1, 0.0085714f),
                    new LootEntry(ModItems.IMMOLATION_OF_HEART.get(), 1, 0.0085714f),
                    new LootEntry(ModItems.FOX_EYE.get(), 1, 0.0085714f),
                    new LootEntry(ModItems.BROKEN_HEALTH_POTION.get(), 1, 0.0085714f),
                    new LootEntry(ModItems.VIAL_OF_BURSTING_ENERGY.get(), 1, 0.0085714f),
                    new LootEntry(ModItems.WIND_WALKER_ARROW.get(), 1, 0.0085714f)
            );

            injectLoot(event.getTable(), mansionLoot);
        }

        if (name.equals(new ResourceLocation("minecraft", "gameplay/sniffer_digging"))) {

            List<LootEntry> snifferLoot = List.of(

                    new LootEntry(ModItems.FOCUSED_GEM_CLUSTER.get(), 1, 0.05f),
                    new LootEntry(ModItems.REINFORCED_GEM_CLUSTER.get(), 1, 0.025f),
                    new LootEntry(ModItems.PERFECTED_GEM_CLUSTER.get(), 1, 0.0125f),

                    new LootEntry(ModItems.PEARL_OF_RENEWING.get(), 1, 0.025F),
                    new LootEntry(ModItems.PEARL_OF_RESTORING.get(), 1, 0.0125F),
                    new LootEntry(ModItems.PEARL_OF_REJUVENATING.get(), 1, 0.00625F),

                    new LootEntry(ModItems.SHARD_OF_AMPLIFICATION.get(), 1, 0.025F),
                    new LootEntry(ModItems.SHARD_OF_EMPOWERMENT.get(), 1, 0.0125F),
                    new LootEntry(ModItems.SHARD_OF_ASCENDANCY.get(), 1, 0.00625F)
            );

            injectLoot(event.getTable(), snifferLoot);
        }

    }

    private static void injectLoot(LootTable table, List<LootEntry> entries) {
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .name("custom_injected_pool")
                .setRolls(ConstantValue.exactly(1));

        for (LootEntry entry : entries) {
            poolBuilder.add(LootItem.lootTableItem(entry.item)
                    .setWeight(entry.weight)
                    .when(LootItemRandomChanceCondition.randomChance(entry.chance)));
        }

        table.addPool(poolBuilder.build());
    }

    private static class LootEntry {
        final Item item;
        final int weight;
        final float chance;

        LootEntry(Item item, int weight, float chance) {
            this.item = item;
            this.weight = weight;
            this.chance = chance;
        }
    }

}
