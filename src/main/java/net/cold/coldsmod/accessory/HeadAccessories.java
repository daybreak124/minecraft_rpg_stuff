package net.cold.coldsmod.accessory;

import net.cold.coldsmod.ColdsMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.cold.coldsmod.stat.CustomStats;
import net.cold.coldsmod.stat.ItemRarity;

public class HeadAccessories {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);


    // Greedy Wishes
    public static final RegistryObject<AccessoryItem> TEMPTING_WHISPERS_RARE = ITEMS.register(
            "tempting_whispers_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Tempting Whispers")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setFort(3).setInsight(1).setMeleeCritDamageMultiplier(30).setHealthMultiplier(-25).setPerc(-4).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Hell)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> TEMPTING_WHISPERS_EPIC = ITEMS.register(
            "tempting_whispers_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Tempting Whispers")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setFort(5).setInsight(2).setMeleeCritDamageMultiplier(60).setHealthMultiplier(-40).setPerc(-4).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Hell)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> TEMPTING_WHISPERS_LEGENDARY = ITEMS.register(
            "tempting_whispers_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Tempting Whispers")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setFort(7).setInsight(3).setMeleeCritDamageMultiplier(120).setHealthMultiplier(-50).setPerc(-4).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Hell)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> TEMPTING_WHISPERS_MYTHIC = ITEMS.register(
            "tempting_whispers_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Tempting Whispers")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setFort(9).setInsight(4).setMeleeCritDamageMultiplier(150).setHealthMultiplier(-50).setPerc(-4).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Hell)
                    .build()
    );


    // Shrieks of Unseeing
    public static final RegistryObject<AccessoryItem> SHRIEKS_OF_UNSEEING_RARE = ITEMS.register(
            "shrieks_of_unseeing_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Shrieks of Unseeing")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setCon(3).setInsight(1).setArmor(2).setPerc(-3).setProjectileDamage(-15).setDebuffResist(5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Otherworlds)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> SHRIEKS_OF_UNSEEING_EPIC = ITEMS.register(
            "shrieks_of_unseeing_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Shrieks of Unseeing")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setCon(5).setInsight(2).setArmor(4).setPerc(-3).setProjectileDamage(-20).setDebuffResist(7.5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Otherworlds)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> SHRIEKS_OF_UNSEEING_LEGENDARY = ITEMS.register(
            "shrieks_of_unseeing_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Shrieks of Unseeing")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setCon(9).setInsight(3).setArmor(6).setArmorMultiplier(3).setPerc(-3).setProjectileDamage(-25).setDebuffResist(10).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Otherworlds)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> SHRIEKS_OF_UNSEEING_MYTHIC = ITEMS.register(
            "shrieks_of_unseeing_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Shrieks of Unseeing")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setCon(13).setInsight(4).setArmor(8).setArmorMultiplier(5).setPerc(-3).setProjectileDamage(-30).setDebuffResist(12.5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Otherworlds)
                    .build()
    );


    // Dragon's Roar
    public static final RegistryObject<AccessoryItem> DRAGONS_ROAR_RARE = ITEMS.register(
            "dragons_roar_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Dragon's Roar")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setStr(-2).setInsight(1).setArmorToughness(4).build())
                    .withLocation(AccessoryItem.AccessoryLocation.End)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> DRAGONS_ROAR_EPIC = ITEMS.register(
            "dragons_roar_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Dragon's Roar")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setStr(-4).setInsight(2).setArmorToughness(6.75).build())
                    .withLocation(AccessoryItem.AccessoryLocation.End)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> DRAGONS_ROAR_LEGENDARY = ITEMS.register(
            "dragons_roar_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Dragon's Roar")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setStr(-6).setInsight(3).setToughnessMultiplier(20).build())
                    .withLocation(AccessoryItem.AccessoryLocation.End)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> DRAGONS_ROAR_MYTHIC = ITEMS.register(
            "dragons_roar_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Dragon's Roar")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setStr(-8).setInsight(4).setToughnessMultiplier(27).build())
                    .withLocation(AccessoryItem.AccessoryLocation.End)
                    .build()
    );


    // Footsteps of the Forgotten
    public static final RegistryObject<AccessoryItem> TEAR_OF_THE_FORGOTTEN_RARE = ITEMS.register(
            "tear_of_the_forgotten_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Tear of the Forgotten")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setInsight(1).setArmorToughness(3).setMaxHealth(1.5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Undergrounds)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> TEAR_OF_THE_FORGOTTEN_EPIC = ITEMS.register(
            "tear_of_the_forgotten_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Tear of the Forgotten")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setInsight(2).setArmorToughness(4).setMaxHealth(2.5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Undergrounds)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> TEAR_OF_THE_FORGOTTEN_LEGENDARY = ITEMS.register(
            "tear_of_the_forgotten_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Tear of the Forgotten")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setInsight(3).setArmorToughness(8).setMaxHealth(4).setToughnessMultiplier(3).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Undergrounds)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> TEAR_OF_THE_FORGOTTEN_MYTHIC = ITEMS.register(
            "tear_of_the_forgotten_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Tear of the Forgotten")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setInsight(4).setArmorToughness(10).setMaxHealth(6).setToughnessMultiplier(5).setKnockbackResist(15).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Undergrounds)
                    .build()
    );


    // Endless Waves
    public static final RegistryObject<AccessoryItem> ENDLESS_WAVES_RARE = ITEMS.register(
            "endless_waves_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Endless Waves")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setInsight(1).setCon(3).setFort(2).setAttackSpeed(5).setMoveSpeed(7.5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Ocean)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> ENDLESS_WAVES_EPIC = ITEMS.register(
            "endless_waves_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Endless Waves")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setInsight(2).setCon(6).setFort(4).setAttackSpeed(6.75).setMoveSpeed(10).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Ocean)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> ENDLESS_WAVES_LEGENDARY = ITEMS.register(
            "endless_waves_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Endless Waves")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setInsight(3).setCon(9).setFort(5).setAttackSpeed(7.5).setSpeedMultiplier(8).setMoveSpeed(10).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Ocean)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> ENDLESS_WAVES_MYTHIC = ITEMS.register(
            "endless_waves_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Endless Waves")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setInsight(4).setCon(12).setFort(8).setAttackSpeed(12.5).setSpeedMultiplier(17.5).setMoveSpeed(12.5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Ocean)
                    .build()
    );


    // Sun's Gaze
    public static final RegistryObject<AccessoryItem> SUNS_GAZE_RARE = ITEMS.register(
            "suns_gaze_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Sun's Gaze")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setDex(1).setInsight(1).setMeleeDamage(6).setProjectileDamage(4).setSwimSpeed(-5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Desert)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> SUNS_GAZE_EPIC = ITEMS.register(
            "suns_gaze_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Sun's Gaze")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setDex(3).setInsight(2).setMeleeDamage(9).setProjectileDamage(6).setSwimSpeed(-10).setDebuffResist(5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Desert)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> SUNS_GAZE_LEGENDARY = ITEMS.register(
            "suns_gaze_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Sun's Gaze")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setDex(6).setInsight(3).setMeleeDamage(12).setProjectileDamage(9).setSwimSpeed(-15).setMeleeDamageMultiplier(2.5).setDebuffResist(10).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Desert)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> SUNS_GAZE_MYTHIC = ITEMS.register(
            "suns_gaze_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Sun's Gaze")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setDex(9).setInsight(4).setMeleeDamage(16).setProjectileDamage(9).setMeleeDamageMultiplier(12.5).setSwimSpeed(-20).setDebuffResist(15).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Desert)
                    .build()
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
