package net.cold.coldsmod.accessory;

import net.cold.coldsmod.ColdsMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.cold.coldsmod.stat.CustomStats;
import net.cold.coldsmod.stat.ItemRarity;

public class BracerAccessories {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);


    // Bracelet of Pride
    public static final RegistryObject<AccessoryItem> BRACELET_OF_PRIDE_RARE = ITEMS.register(
            "bracelet_of_pride_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Bracelet of Pride")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setStr(3).setCon(2).setFort(2).setWis(1).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Hell)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> BRACELET_OF_PRIDE_EPIC = ITEMS.register(
            "bracelet_of_pride_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Bracelet of Pride")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setStr(5).setCon(3).setFort(3).setWis(2).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Hell)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> BRACELET_OF_PRIDE_LEGENDARY = ITEMS.register(
            "bracelet_of_pride_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Bracelet of Pride")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setStr(7).setCon(4).setFort(4).setDex(3).setWis(3).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Hell)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> BRACELET_OF_PRIDE_MYTHIC = ITEMS.register(
            "bracelet_of_pride_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Bracelet of Pride")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setStr(9).setCon(6).setFort(6).setDex(5).setWis(4).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Hell)
                    .build()
    );


    // Fingers of Lust
    public static final RegistryObject<AccessoryItem> FINGERS_OF_LUST_RARE = ITEMS.register(
            "fingers_of_lust_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Fingers of Lust")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setDex(5).setPerc(-6).setAttackSpeed(2).setMeleeDamage(4).setWis(1).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Hell)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> FINGERS_OF_LUST_EPIC = ITEMS.register(
            "fingers_of_lust_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Fingers of Lust")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setDex(6).setPerc(-6).setAttackSpeed(3.75).setMeleeDamage(6.75).setWis(2).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Hell)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> FINGERS_OF_LUST_LEGENDARY = ITEMS.register(
            "fingers_of_lust_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Fingers of Lust")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setDex(7).setPerc(-6).setAttackSpeed(4.5).setMeleeDamage(12).setSpeedMultiplier(10).setWis(3).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Hell)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> FINGERS_OF_LUST_MYTHIC = ITEMS.register(
            "fingers_of_lust_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Fingers of Lust")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setDex(8).setPerc(-6).setAttackSpeed(6).setMeleeDamage(18).setSpeedMultiplier(20).setWis(4).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Hell)
                    .build()
    );


    // Enderman's Severed Arm
    public static final RegistryObject<AccessoryItem> ENDERMANS_SEVERED_ARM_RARE = ITEMS.register(
            "endermans_severed_arm_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Enderman's Severed Arm")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder()
                            .setFort(1)
                            .setSwimSpeed(-25)
                            .setEntityReach(0.125)
                            .setBlockReach(0.5)
                            .setWis(2)
                            .setMiningSpeed(3).build())
                    .withLocation(AccessoryItem.AccessoryLocation.End)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> ENDERMANS_SEVERED_ARM_EPIC = ITEMS.register(
            "endermans_severed_arm_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Enderman's Severed Arm")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder()
                            .setFort(4)
                            .setSwimSpeed(-25)
                            .setEntityReach(0.25)
                            .setBlockReach(1.0)
                            .setWis(4)
                            .setMiningSpeed(6).build())
                    .withLocation(AccessoryItem.AccessoryLocation.End)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> ENDERMANS_SEVERED_ARM_LEGENDARY = ITEMS.register(
            "endermans_severed_arm_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Enderman's Severed Arm")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder()
                            .setFort(8)
                            .setSwimSpeed(-25)
                            .setEntityReach(0.375)
                            .setBlockReach(1.5)
                            .setWis(6)
                            .setMiningSpeed(9).build())
                    .withLocation(AccessoryItem.AccessoryLocation.End)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> ENDERMANS_SEVERED_ARM_MYTHIC = ITEMS.register(
            "endermans_severed_arm_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Enderman's Severed Arm")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder()
                            .setFort(10)
                            .setSwimSpeed(-25)
                            .setEntityReach(0.5)
                            .setBlockReach(2)
                            .setWis(8)
                            .setMiningSpeed(12).build())
                    .withLocation(AccessoryItem.AccessoryLocation.End)
                    .build()
    );


    // Dragon Claw Gloves
    public static final RegistryObject<AccessoryItem> DRAGON_CLAW_GLOVES_RARE = ITEMS.register(
            "dragon_claw_gloves_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Dragon Claw Gloves")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setStr(3).setCritChance(6).setWis(1).build())
                    .withLocation(AccessoryItem.AccessoryLocation.End)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> DRAGON_CLAW_GLOVES_EPIC = ITEMS.register(
            "dragon_claw_gloves_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Dragon Claw Gloves")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setStr(5).setCritChance(13.5).setWis(2).build())
                    .withLocation(AccessoryItem.AccessoryLocation.End)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> DRAGON_CLAW_GLOVES_LEGENDARY = ITEMS.register(
            "dragon_claw_gloves_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Dragon Claw Gloves")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setStr(7).setCritChance(18).setToughnessMultiplier(10).setWis(3).build())
                    .withLocation(AccessoryItem.AccessoryLocation.End)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> DRAGON_CLAW_GLOVES_MYTHIC = ITEMS.register(
            "dragon_claw_gloves_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Dragon Claw Gloves")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setStr(9).setCritChance(24).setToughnessMultiplier(20).setWis(4).build())
                    .withLocation(AccessoryItem.AccessoryLocation.End)
                    .build()
    );


    // Warden Skin Forged Bracers
    public static final RegistryObject<AccessoryItem> WARDEN_SKIN_FORGED_BRACERS_RARE = ITEMS.register(
            "warden_skin_forged_bracers_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Warden Skin Forged Bracers")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setFort(3).setArmor(1).setArmorToughness(1).setWis(1).setPerc(1).setDebuffResist(6).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Otherworlds)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> WARDEN_SKIN_FORGED_BRACERS_EPIC = ITEMS.register(
            "warden_skin_forged_bracers_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Warden Skin Forged Bracers")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setFort(4).setArmor(2).setArmorToughness(2.5).setWis(2).setPerc(2).setDebuffResist(8).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Otherworlds)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> WARDEN_SKIN_FORGED_BRACERS_LEGENDARY = ITEMS.register(
            "warden_skin_forged_bracers_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Warden Skin Forged Bracers")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setFort(5).setArmor(4).setMaxHealth(2).setArmorToughness(3.5).setToughnessMultiplier(6).setWis(3).setPerc(5).setDebuffResist(12).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Otherworlds)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> WARDEN_SKIN_FORGED_BRACERS_MYTHIC = ITEMS.register(
            "warden_skin_forged_bracers_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Warden Skin Forged Bracers")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setFort(7).setArmor(6).setMaxHealth(4).setArmorToughness(4.5).setToughnessMultiplier(12).setWis(4).setPerc(5).setDebuffResist(15).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Otherworlds)
                    .build()
    );


    // Reinforced Steel Bracers
    public static final RegistryObject<AccessoryItem> REINFORCED_STEEL_BRACERS_RARE = ITEMS.register(
            "reinforced_steel_bracers_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Reinforced Steel Bracers")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setMeleeDamage(4).setArmor(2).setMaxHealth(2).setWis(1).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Undergrounds)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> REINFORCED_STEEL_BRACERS_EPIC = ITEMS.register(
            "reinforced_steel_bracers_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Reinforced Steel Bracers")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setMeleeDamage(6.25).setArmor(3).setMaxHealth(3).setWis(2).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Undergrounds)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> REINFORCED_STEEL_BRACERS_LEGENDARY = ITEMS.register(
            "reinforced_steel_bracers_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Reinforced Steel Bracers")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setMeleeDamage(10).setArmor(4).setMaxHealth(3.4).setCritChanceMultiplier(8).setWis(3).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Undergrounds)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> REINFORCED_STEEL_BRACERS_MYTHIC = ITEMS.register(
            "reinforced_steel_bracers_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Reinforced Steel Bracers")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setMeleeDamage(13.5).setArmor(5).setMaxHealth(4).setCritChanceMultiplier(15).setWis(4).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Undergrounds)
                    .build()
    );


    // Serpent Skin Wrap
    public static final RegistryObject<AccessoryItem> SERPENT_SKIN_WRAP_RARE = ITEMS.register(
            "serpent_skin_wrap_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Serpent Skin Wrap")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setDrawSpeed(6).setProjectileDamage(12).setWis(1).setDebuffResist(5).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Jungle)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> SERPENT_SKIN_WRAP_EPIC = ITEMS.register(
            "serpent_skin_wrap_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Serpent Skin Wrap")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setDrawSpeed(8).setProjectileDamage(16).setWis(2).setDebuffResist(6).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Jungle)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> SERPENT_SKIN_WRAP_LEGENDARY = ITEMS.register(
            "serpent_skin_wrap_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Serpent Skin Wrap")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setDrawSpeed(10).setProjectileDamage(20).setDrawSpeedMultiplier(5).setProjectileDamageMultiplier(7.5).setWis(3).setDebuffResist(8).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Jungle)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> SERPENT_SKIN_WRAP_MYTHIC = ITEMS.register(
            "serpent_skin_wrap_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Serpent Skin Wrap")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setDrawSpeed(12.5).setProjectileDamage(25).setProjectileDamageMultiplier(7.5).setDrawSpeedMultiplier(12.5).setWis(4).setDebuffResist(10).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Jungle)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> WRAPS_OF_THIEVERY_RARE = ITEMS.register(
            "wraps_of_thievery_rare",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Wraps of Thievery")
                    .withRarity(ItemRarity.RARE)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setSpeedMultiplier(-8).setCritDamage(15).setMaxHealth(2).setWis(1).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Pillage)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> WRAPS_OF_THIEVERY_EPIC = ITEMS.register(
            "wraps_of_thievery_epic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Wraps of Thievery")
                    .withRarity(ItemRarity.EPIC)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setSpeedMultiplier(-12).setCritDamage(20).setMaxHealth(3).setWis(2).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Pillage)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> WRAPS_OF_THIEVERY_LEGENDARY = ITEMS.register(
            "wraps_of_thievery_legendary",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Wraps of Thievery")
                    .withRarity(ItemRarity.LEGENDARY)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setSpeedMultiplier(-15).setCritDamage(27.5).setMaxHealth(6).setMeleeDamageMultiplier(5).setWis(3).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Pillage)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> WRAPS_OF_THIEVERY_MYTHIC = ITEMS.register(
            "wraps_of_thievery_mythic",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Wraps of Thievery")
                    .withRarity(ItemRarity.MYTHIC)
                    .withType(AccessoryItem.AccessoryType.Bracer)
                    .withStats(new CustomStats.Builder().setSpeedMultiplier(-20).setCritDamage(30).setMeleeDamageMultiplier(10).setMaxHealth(8).setWis(4).build())
                    .withLocation(AccessoryItem.AccessoryLocation.Pillage)
                    .build()
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
