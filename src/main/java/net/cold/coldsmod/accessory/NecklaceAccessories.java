package net.cold.coldsmod.accessory;

import net.cold.coldsmod.ColdsMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.cold.coldsmod.stat.CustomStats;
import net.cold.coldsmod.stat.ItemRarity;

public class NecklaceAccessories {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    // collar
    public static final RegistryObject<AccessoryItem> COLLAR_OF_ENVY_RARE = ITEMS.register(
            "collar_of_envy_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Collar of Envy")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setWis(1).setDex(3).setMeleeDamage(4).setFort(4).setArmorToughness(1).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Hell)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> COLLAR_OF_ENVY_EPIC = ITEMS.register(
            "collar_of_envy_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Collar of Envy")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setWis(2).setDex(3).setMeleeDamage(6).setFort(4).setArmorToughness(2).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Hell)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> COLLAR_OF_ENVY_LEGENDARY = ITEMS.register(
            "collar_of_envy_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Collar of Envy")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setWis(3).setDex(5).setMeleeDamage(9).setFort(4).setArmorToughness(4).setMeleeDamageMultiplier(5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Hell)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> COLLAR_OF_ENVY_MYTHIC = ITEMS.register(
            "collar_of_envy_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Collar of Envy")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setWis(4).setDex(6).setMeleeDamage(12).setFort(6).setArmorToughness(6).setMeleeDamageMultiplier(10).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Hell)
                    .build()
    );

    // Key of the Unknown
    public static final RegistryObject<AccessoryItem> KEY_OF_THE_UNKNOWN_RARE = ITEMS.register(
            "key_of_the_unknown_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Key of the Unknown")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setWis(1).setFort(3).setKnockbackResist(5).setArmor(2).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Otherworlds)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> KEY_OF_THE_UNKNOWN_EPIC = ITEMS.register(
            "key_of_the_unknown_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Key of the Unknown")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setWis(2).setFort(6).setKnockbackResist(10).setArmor(3).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Otherworlds)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> KEY_OF_THE_UNKNOWN_LEGENDARY = ITEMS.register(
            "key_of_the_unknown_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Key of the Unknown")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setWis(3).setFort(8).setKnockbackResist(15).setArmor(4).setCon(3).setArmorMultiplier(3.75).setDebuffResist(5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Otherworlds)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> KEY_OF_THE_UNKNOWN_MYTHIC = ITEMS.register(
            "key_of_the_unknown_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Key of the Unknown")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setWis(4).setFort(10).setKnockbackResist(15).setArmor(5).setArmorMultiplier(7.5).setCon(6).setDebuffResist(12).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Otherworlds)
                    .build()
    );


    // Dragon Teeth Necklace
    public static final RegistryObject<AccessoryItem> DRAGON_TEETH_NECKLACE_RARE = ITEMS.register(
            "dragon_teeth_necklace_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Dragon Teeth Necklace")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setWis(1).setStr(2).setCritChance(6).setPerc(2).build())
                    .withLocation(AccessoryItem.AccessoryLocation.End)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> DRAGON_TEETH_NECKLACE_EPIC = ITEMS.register(
            "dragon_teeth_necklace_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Dragon Teeth Necklace")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setWis(2).setStr(3).setCritChance(10).setMaxHealth(1).setPerc(3).build())
                    .withLocation(AccessoryItem.AccessoryLocation.End)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> DRAGON_TEETH_NECKLACE_LEGENDARY = ITEMS.register(
            "dragon_teeth_necklace_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Dragon Teeth Necklace")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setWis(3).setStr(6).setCritChance(12).setMaxHealth(2.5).setPerc(6).setMeleeCritDamageMultiplier(6).setProjectileCritDamageMultiplier(3).build())
                    .withLocation(AccessoryItem.AccessoryLocation.End)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> DRAGON_TEETH_NECKLACE_MYTHIC = ITEMS.register(
            "dragon_teeth_necklace_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Dragon Teeth Necklace")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setWis(4).setStr(8).setCritChance(15).setMaxHealth(3.5).setPerc(8).setMeleeCritDamageMultiplier(10).setProjectileCritDamageMultiplier(5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.End)
                    .build()
    );


    // Hanging Tiger Tooth
    public static final RegistryObject<AccessoryItem> HANGING_TIGER_TOOTH_RARE = ITEMS.register(
            "hanging_tiger_tooth_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Hanging Tiger Tooth")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setWis(1).setDex(4).setProjectileDamage(3).setDrawSpeed(3).setAttackSpeed(2).setDebuffResist(5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Jungle)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> HANGING_TIGER_TOOTH_EPIC = ITEMS.register(
            "hanging_tiger_tooth_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Hanging Tiger Tooth")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setWis(2).setDex(6).setProjectileDamage(4.5).setDrawSpeed(5).setAttackSpeed(3.5).setDebuffResist(7.5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Jungle)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> HANGING_TIGER_TOOTH_LEGENDARY = ITEMS.register(
            "hanging_tiger_tooth_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Hanging Tiger Tooth")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setWis(3).setDex(9).setProjectileDamage(7.5).setDrawSpeed(7.5).setAttackSpeed(6).setDebuffResist(10).setDrawSpeedMultiplier(5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Jungle)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> HANGING_TIGER_TOOTH_MYTHIC = ITEMS.register(
            "hanging_tiger_tooth_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Hanging Tiger Tooth")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setWis(4).setDex(12).setProjectileDamage(12.5).setDrawSpeed(13.5).setAttackSpeed(10).setDrawSpeedMultiplier(10).setDebuffResist(12.5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Jungle)
                    .build()
    );


    // Bottled Tsunami Sea
    public static final RegistryObject<AccessoryItem> BOTTLED_TSUNAMI_SEA_RARE = ITEMS.register(
            "bottled_tsunami_sea_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Bottled Tsunami Sea")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setWis(1).setFort(5).setSwimSpeed(10).setMaxHealth(3).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Ocean)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> BOTTLED_TSUNAMI_SEA_EPIC = ITEMS.register(
            "bottled_tsunami_sea_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Bottled Tsunami Sea")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setWis(2).setFort(8).setSwimSpeed(20).setMaxHealth(5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Ocean)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> BOTTLED_TSUNAMI_SEA_LEGENDARY = ITEMS.register(
            "bottled_tsunami_sea_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Bottled Tsunami Sea")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setWis(3).setFort(11).setSwimSpeed(30).setMaxHealth(6).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Ocean)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> BOTTLED_TSUNAMI_SEA_MYTHIC = ITEMS.register(
            "bottled_tsunami_sea_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Bottled Tsunami Sea")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setWis(4).setFort(14).setSwimSpeed(40).setMaxHealth(7).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Ocean)
                    .build()
    );


    // Necklace of Floating Snowflake
    public static final RegistryObject<AccessoryItem> PENDANT_OF_FLOATING_SNOWFLAKE_RARE = ITEMS.register(
            "pendant_of_floating_snowflake_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Pendant of Floating Snowflake")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setCon(6).setWis(1).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Cold)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> PENDANT_OF_FLOATING_SNOWFLAKE_EPIC = ITEMS.register(
            "pendant_of_floating_snowflake_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Pendant of Floating Snowflake")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setCon(10).setWis(2).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Cold)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> PENDANT_OF_FLOATING_SNOWFLAKE_LEGENDARY = ITEMS.register(
            "pendant_of_floating_snowflake_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Pendant of Floating Snowflake")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setCon(16).setWis(3).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Cold)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> PENDANT_OF_FLOATING_SNOWFLAKE_MYTHIC = ITEMS.register(
            "pendant_of_floating_snowflake_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Pendant of Floating Snowflake")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setCon(24).setWis(4).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Cold)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> STOLEN_LEGACIES_CHOKER_RARE = ITEMS.register(
            "stolen_legacies_choker_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Stolen Legacies Choker")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setWis(1).setXpGain(15).setProjectileDamage(6).setCritDamage(6).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Pillage)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> STOLEN_LEGACIES_CHOKER_EPIC = ITEMS.register(
            "stolen_legacies_choker_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Stolen Legacies Choker")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setWis(2).setXpGain(20).setProjectileDamage(10).setCritDamage(10).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Pillage)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> STOLEN_LEGACIES_CHOKER_LEGENDARY = ITEMS.register(
            "stolen_legacies_choker_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Stolen Legacies Choker")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setWis(3).setXpGain(25).setProjectileDamage(16).setCritDamage(12).setCritDamageMultiplier(5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Pillage)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> STOLEN_LEGACIES_CHOKER_MYTHIC = ITEMS.register(
            "stolen_legacies_choker_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Stolen Legacies Choker")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Necklace)
                    .withStats(new CustomStats.Builder().setWis(4).setXpGain(30).setProjectileDamage(20).setCritDamage(16).setCritDamageMultiplier(10).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Pillage)
                    .build()
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
