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
                    .withStats(new CustomStats.Builder().setFort(3).setWis(1).setMeleeCritDamageMultiplier(30).setHealthMultiplier(-25).setPerc(-2).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Hell)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> TEMPTING_WHISPERS_EPIC = ITEMS.register(
            "tempting_whispers_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Tempting Whispers")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setFort(5).setWis(2).setMeleeCritDamageMultiplier(60).setHealthMultiplier(-40).setPerc(-2).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Hell)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> TEMPTING_WHISPERS_LEGENDARY = ITEMS.register(
            "tempting_whispers_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Tempting Whispers")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setFort(7).setWis(3).setMeleeCritDamageMultiplier(120).setHealthMultiplier(-50).setPerc(-2).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Hell)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> TEMPTING_WHISPERS_MYTHIC = ITEMS.register(
            "tempting_whispers_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Tempting Whispers")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setFort(9).setWis(4).setMeleeCritDamageMultiplier(150).setHealthMultiplier(-50).setPerc(-2).build())
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
                    .withStats(new CustomStats.Builder().setCon(5).setWis(1).setArmor(2).setPerc(-3).setProjectileDamage(-15).setDebuffResist(5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Otherworlds)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> SHRIEKS_OF_UNSEEING_EPIC = ITEMS.register(
            "shrieks_of_unseeing_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Shrieks of Unseeing")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setCon(8).setWis(2).setArmor(4).setPerc(-3).setProjectileDamage(-20).setDebuffResist(7.5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Otherworlds)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> SHRIEKS_OF_UNSEEING_LEGENDARY = ITEMS.register(
            "shrieks_of_unseeing_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Shrieks of Unseeing")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setCon(11).setWis(3).setArmor(7).setArmorMultiplier(5).setPerc(-3).setProjectileDamage(-25).setDebuffResist(10).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Otherworlds)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> SHRIEKS_OF_UNSEEING_MYTHIC = ITEMS.register(
            "shrieks_of_unseeing_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Shrieks of Unseeing")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setCon(15).setWis(4).setArmor(10).setArmorMultiplier(10).setPerc(-3).setProjectileDamage(-30).setDebuffResist(12.5).build())
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
                    .withStats(new CustomStats.Builder().setStr(-2).setWis(1).setArmorToughness(6).build())
                    .withLocation(AccessoryItem.AccessoryLocation.End)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> DRAGONS_ROAR_EPIC = ITEMS.register(
            "dragons_roar_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Dragon's Roar")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setStr(-4).setWis(2).setArmorToughness(10).build())
                    .withLocation(AccessoryItem.AccessoryLocation.End)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> DRAGONS_ROAR_LEGENDARY = ITEMS.register(
            "dragons_roar_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Dragon's Roar")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setStr(-6).setWis(3).setToughnessMultiplier(25).build())
                    .withLocation(AccessoryItem.AccessoryLocation.End)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> DRAGONS_ROAR_MYTHIC = ITEMS.register(
            "dragons_roar_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Dragon's Roar")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setStr(-8).setWis(4).setToughnessMultiplier(35).build())
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
                    .withStats(new CustomStats.Builder().setWis(1).setArmorToughness(3).setMaxHealth(3).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Undergrounds)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> TEAR_OF_THE_FORGOTTEN_EPIC = ITEMS.register(
            "tear_of_the_forgotten_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Tear of the Forgotten")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setWis(2).setArmorToughness(4).setMaxHealth(5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Undergrounds)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> TEAR_OF_THE_FORGOTTEN_LEGENDARY = ITEMS.register(
            "tear_of_the_forgotten_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Tear of the Forgotten")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setWis(3).setArmorToughness(8).setMaxHealth(6).setToughnessMultiplier(5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Undergrounds)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> TEAR_OF_THE_FORGOTTEN_MYTHIC = ITEMS.register(
            "tear_of_the_forgotten_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Tear of the Forgotten")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setWis(4).setArmorToughness(10).setMaxHealth(8).setToughnessMultiplier(5).setKnockbackResist(15).build())
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
                    .withStats(new CustomStats.Builder().setWis(1).setCon(3).setFort(2).setAttackSpeed(5).setMoveSpeed(7.5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Ocean)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> ENDLESS_WAVES_EPIC = ITEMS.register(
            "endless_waves_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Endless Waves")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setWis(2).setCon(6).setFort(4).setAttackSpeed(6.75).setMoveSpeed(10).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Ocean)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> ENDLESS_WAVES_LEGENDARY = ITEMS.register(
            "endless_waves_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Endless Waves")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setWis(3).setCon(9).setFort(5).setAttackSpeed(7.5).setSpeedMultiplier(15).setMoveSpeed(10).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Ocean)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> ENDLESS_WAVES_MYTHIC = ITEMS.register(
            "endless_waves_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Endless Waves")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setWis(4).setCon(12).setFort(8).setAttackSpeed(12.5).setSpeedMultiplier(25).setMoveSpeed(12.5).build())
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
                    .withStats(new CustomStats.Builder().setDex(1).setWis(1).setMeleeDamage(6).setProjectileDamage(4).setSwimSpeed(-5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Desert)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> SUNS_GAZE_EPIC = ITEMS.register(
            "suns_gaze_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Sun's Gaze")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setDex(3).setWis(2).setMeleeDamage(9).setProjectileDamage(6).setSwimSpeed(-10).setDebuffResist(5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Desert)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> SUNS_GAZE_LEGENDARY = ITEMS.register(
            "suns_gaze_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Sun's Gaze")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setDex(6).setWis(3).setMeleeDamage(12).setProjectileDamage(9).setSwimSpeed(-15).setMeleeDamageMultiplier(2.5).setDebuffResist(10).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Desert)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> SUNS_GAZE_MYTHIC = ITEMS.register(
            "suns_gaze_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Sun's Gaze")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Mind)
                    .withStats(new CustomStats.Builder().setDex(9).setWis(4).setMeleeDamage(16).setProjectileDamage(9).setMeleeDamageMultiplier(12.5).setSwimSpeed(-20).setDebuffResist(15).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Desert)
                    .build()
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
