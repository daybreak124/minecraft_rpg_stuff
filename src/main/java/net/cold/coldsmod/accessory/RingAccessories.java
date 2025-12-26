package net.cold.coldsmod.accessory;

import net.cold.coldsmod.ColdsMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.cold.coldsmod.stat.CustomStats;
import net.cold.coldsmod.stat.ItemRarity;

public class RingAccessories {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    // Common Coil of Lies
    public static final RegistryObject<AccessoryItem> COIL_OF_WRATH_RARE = ITEMS.register(
            "coil_of_wrath_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Coil of Wrath")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(1).setDex(4).setCritDamage(6).setFort(-3).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Hell)
                    .build()
    );

    // Rare Coil of Lies
    public static final RegistryObject<AccessoryItem> COIL_OF_WRATH_EPIC = ITEMS.register(
            "coil_of_wrath_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Coil of Wrath")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(2).setDex(7).setCritDamage(12).setFort(-3).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Hell)
                    .build()
    );

    // Legendary Coil of Lies
    public static final RegistryObject<AccessoryItem> COIL_OF_WRATH_LEGENDARY = ITEMS.register(
            "coil_of_wrath_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Coil of Wrath")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(3).setDex(8).setCritDamage(20).setFort(-3).setAttackSpeed(4).setProjectileDamage(4.5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Hell)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> COIL_OF_WRATH_MYTHIC = ITEMS.register(
            "coil_of_wrath_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Coil of Wrath")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(4).setDex(12).setCritDamage(24).setAttackSpeed(9).setProjectileDamage(9).setFort(-3).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Hell)
                    .build()
    );

    // Band of Gluttony
    public static final RegistryObject<AccessoryItem> GLUTTONY_SIGNET_RARE = ITEMS.register(
            "gluttony_signet_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Gluttony Signet")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(1).setStr(1).setDex(1).setMoveSpeed(-15).setDebuffResist(-20).setFort(1).setArmor(1).setArmorToughness(1).setMaxHealth(1).setMeleeDamage(2).setAttackSpeed(2).setDrawSpeed(2).setProjectileDamage(2).setCon(1).setPerc(1).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Hell)
                    .build()
    );


    public static final RegistryObject<AccessoryItem> GLUTTONY_SIGNET_EPIC = ITEMS.register(
            "gluttony_signet_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Gluttony Signet")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(2).setStr(2).setDex(2).setMoveSpeed(-15).setDebuffResist(-20).setFort(2).setArmor(1.5).setArmorToughness(1.5).setMaxHealth(1.5).setMeleeDamage(2.5).setAttackSpeed(2.5).setDrawSpeed(2.5).setProjectileDamage(2.5).setCon(2).setPerc(2).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Hell)
                    .build()
    );


    public static final RegistryObject<AccessoryItem> GLUTTONY_SIGNET_LEGENDARY = ITEMS.register(
            "gluttony_signet_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Gluttony Signet")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(3).setStr(3).setDex(3).setMoveSpeed(-15).setDebuffResist(-20).setFort(3).setArmor(2).setArmorToughness(2).setMaxHealth(2).setMeleeDamage(3.25).setAttackSpeed(3.25).setDrawSpeed(3.25).setProjectileDamage(3.25).setCon(3).setPerc(3)
                            .setArmorMultiplier(2).setToughnessMultiplier(2).setSpeedMultiplier(3).setMeleeDamageMultiplier(3).setDrawSpeedMultiplier(3).setProjectileDamageMultiplier(3).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Hell)
                    .build()
    );


    public static final RegistryObject<AccessoryItem> GLUTTONY_SIGNET_MYTHIC = ITEMS.register(
            "gluttony_signet_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Gluttony Signet")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(4).setStr(4).setDex(4).setMoveSpeed(-15).setDebuffResist(-20).setFort(4).setArmor(2.5).setArmorToughness(2.5).setMaxHealth(2).setMeleeDamage(4).setAttackSpeed(4).setDrawSpeed(4).setProjectileDamage(4).setCon(4).setPerc(4)
                            .setArmorMultiplier(4).setToughnessMultiplier(4).setSpeedMultiplier(6).setMeleeDamageMultiplier(6).setDrawSpeedMultiplier(6).setProjectileDamageMultiplier(6).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Hell)
                    .build()
    );

    // Band of the Unknown
    public static final RegistryObject<AccessoryItem> BAND_OF_THE_UNKNOWN_RARE = ITEMS.register(
            "band_of_the_unknown_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Band of the Unknown")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(1).setStr(4).setFort(2).setCon(2).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Otherworlds)
                    .build()
    );


    public static final RegistryObject<AccessoryItem> BAND_OF_THE_UNKNOWN_EPIC = ITEMS.register(
            "band_of_the_unknown_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Band of the Unknown")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(2).setStr(7).setFort(3).setCon(3).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Otherworlds)
                    .build()
    );


    public static final RegistryObject<AccessoryItem> BAND_OF_THE_UNKNOWN_LEGENDARY = ITEMS.register(
            "band_of_the_unknown_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Band of the Unknown")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(3).setStr(10).setFort(5).setCon(5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Otherworlds)
                    .build()
    );


    public static final RegistryObject<AccessoryItem> BAND_OF_THE_UNKNOWN_MYTHIC = ITEMS.register(
            "band_of_the_unknown_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Band of the Unknown")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(4).setStr(15).setFort(7).setCon(7).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Otherworlds)
                    .build()
    );

    //Dragon Eye
    public static final RegistryObject<AccessoryItem> DRAGON_EYE_EMBEDDED_RING_RARE = ITEMS.register(
            "dragon_eye_embedded_ring_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Dragon Eye Embedded Ring")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(1).setStr(4).setMeleeDamage(7).setProjectileDamage(5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.End)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> DRAGON_EYE_EMBEDDED_RING_EPIC = ITEMS.register(
            "dragon_eye_embedded_ring_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Dragon Eye Embedded Ring")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(2).setStr(6).setMeleeDamage(12).setProjectileDamage(7.5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.End)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> DRAGON_EYE_EMBEDDED_RING_LEGENDARY = ITEMS.register(
            "dragon_eye_embedded_ring_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Dragon Eye Embedded Ring")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(3).setStr(8).setMeleeDamage(16).setProjectileDamage(9).setMeleeDamageMultiplier(5).setProjectileDamageMultiplier(3).setPerc(4).build())
                    .withLocation(AccessoryItem.AccessoryLocation.End)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> DRAGON_EYE_EMBEDDED_RING_MYTHIC = ITEMS.register(
            "dragon_eye_embedded_ring_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Dragon Eye Embedded Ring")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(4).setStr(9).setMeleeDamage(20).setProjectileDamage(12.5).setPerc(6).setMeleeDamageMultiplier(10).setProjectileDamageMultiplier(6).build())
                    .withLocation(AccessoryItem.AccessoryLocation.End)
                    .build()
    );

    //End 2
    public static final RegistryObject<AccessoryItem> CORRUPTED_RING_OF_THE_LOST_RARE = ITEMS.register(
            "corrupted_ring_of_the_lost_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Corrupted Ring of the Lost")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(1).setFort(4).setKnockbackResist(5).setArmor(3).build())
                    .withLocation(AccessoryItem.AccessoryLocation.End)
                    .build()
    );
    public static final RegistryObject<AccessoryItem> CORRUPTED_RING_OF_THE_LOST_EPIC = ITEMS.register(
            "corrupted_ring_of_the_lost_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Corrupted Ring of the Lost")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(2).setFort(5).setKnockbackResist(10).setArmor(5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.End)
                    .build()
    );
    public static final RegistryObject<AccessoryItem> CORRUPTED_RING_OF_THE_LOST_LEGENDARY = ITEMS.register(
            "corrupted_ring_of_the_lost_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Corrupted Ring of the Lost")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(3).setFort(6).setKnockbackResist(15).setArmor(7).setArmorToughness(4).setArmorMultiplier(3.5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.End)
                    .build()
    );
    public static final RegistryObject<AccessoryItem> CORRUPTED_RING_OF_THE_LOST_MYTHIC = ITEMS.register(
            "corrupted_ring_of_the_lost_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Corrupted Ring of the Lost")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(4).setFort(7).setKnockbackResist(20).setArmor(7).setArmorToughness(6).setArmorMultiplier(7).build())
                    .withLocation(AccessoryItem.AccessoryLocation.End)
                    .build()
    );

    //Cold
    public static final RegistryObject<AccessoryItem> COLDYS_COLD_COIL_OF_COLD_RARE = ITEMS.register(
            "coldys_cold_coil_of_cold_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Coldy's Cold Coil of Cold")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(1).setMoveSpeed(9).setDrawSpeed(3).setAttackSpeed(3).setCon(4).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Cold)
                    .build()
    );
    public static final RegistryObject<AccessoryItem> COLDYS_COLD_COIL_OF_COLD_EPIC = ITEMS.register(
            "coldys_cold_coil_of_cold_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Coldy's Colder Coil of Cold")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(2).setMoveSpeed(12).setDrawSpeed(5).setAttackSpeed(7.5).setCon(6).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Cold)
                    .build()
    );
    public static final RegistryObject<AccessoryItem> COLDYS_COLD_COIL_OF_COLD_LEGENDARY = ITEMS.register(
            "coldys_cold_coil_of_cold_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Coldy's Colder Coil of Cold")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(3).setMoveSpeed(15).setDrawSpeed(15).setAttackSpeed(12).setDrawSpeedMultiplier(5).setCon(9).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Cold)
                    .build()
    );
    public static final RegistryObject<AccessoryItem> COLDYS_COLD_COIL_OF_COLD_MYTHIC = ITEMS.register(
            "coldys_cold_coil_of_cold_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Coldy's Coldest Coil of Cold")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(4).setMoveSpeed(15).setDrawSpeed(20).setAttackSpeed(15).setDrawSpeedMultiplier(5).setSpeedMultiplier(5).setCon(13).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Cold)
                    .build()
    );

    //Desert
    public static final RegistryObject<AccessoryItem> SUNSTONE_FORGED_RING_RARE = ITEMS.register(
            "sunstone_forged_ring_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Sunstone Forged Ring")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(1).setDex(2).setPerc(4).setCritChance(8).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Desert)
                    .build()
    );
    public static final RegistryObject<AccessoryItem> SUNSTONE_FORGED_RING_EPIC = ITEMS.register(
            "sunstone_forged_ring_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Sunstone Forged Ring")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(2).setDex(3).setPerc(7).setCritChance(12).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Desert)
                    .build()
    );
    public static final RegistryObject<AccessoryItem> SUNSTONE_FORGED_RING_LEGENDARY = ITEMS.register(
            "sunstone_forged_ring_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Sunstone Forged Ring")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(3).setDex(4).setPerc(10).setCritChance(16).setDrawSpeed(8).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Desert)
                    .build()
    );
    public static final RegistryObject<AccessoryItem> SUNSTONE_FORGED_RING_MYTHIC = ITEMS.register(
            "sunstone_forged_ring_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Sunstone Forged Ring")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Ring)
                    .withStats(new CustomStats.Builder().setWis(4).setDex(6).setPerc(13).setCritChance(21).setDrawSpeed(12).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Desert)
                    .build()
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
