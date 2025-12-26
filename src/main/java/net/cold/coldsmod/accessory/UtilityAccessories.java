package net.cold.coldsmod.accessory;

import net.cold.coldsmod.ColdsMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.cold.coldsmod.stat.CustomStats;
import net.cold.coldsmod.stat.ItemRarity;

public class UtilityAccessories {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static final RegistryObject<AccessoryItem> CLOUDTREADER_BOOTS = ITEMS.register(
            "cloudtreader_boots",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Cloudtreader Boots")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setWis(2).setStepHeight(1).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> MONIS_LUCKY_CHARM = ITEMS.register(
            "monis_lucky_charm",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Moni's Lucky Charm")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setWis(2).setLuck(3).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> ENDERMAN_FINGERS = ITEMS.register(
            "enderman_fingers",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Enderman's Fingers")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setWis(2).setBlockReach(3).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> ANTIQUE_POCKET_WATCH = ITEMS.register(
            "antique_pocket_watch",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Antique Pocket Watch")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setWis(2).setXpGain(30).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> REINFORCED_DIAMOND_PLATING = ITEMS.register(
            "reinforced_diamond_plating",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Reinforced Diamond Plating")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setWis(2).setMiningSpeed(30).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> CLOUDSPIRE_GEM = ITEMS.register(
            "cloudspire_gem",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Cloudspire Gem")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setWis(2).setJumpBoost(100).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> CRIT_ITEM = ITEMS.register(
            "crit_item",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Crit")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setCritDamage(125).setCritChance(109.75609).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> DAMAGE_ITEM = ITEMS.register(
            "damage_item",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Damage")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setDamage(12500).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> DEFENSE = ITEMS.register(
            "defense_item_1",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Armor")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setArmor(100).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> DEFENSE2 = ITEMS.register(
            "defense_item_2",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("ARMOR")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setArmor(400).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> DEFENSE3 = ITEMS.register(
            "defense_item_3",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Toughness")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setArmorToughness(150).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> PROJ = ITEMS.register(
            "projectile_item",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Projectile & Draw Speed")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setProjectileDamage(125).setDrawSpeed(125).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> STR = ITEMS.register(
            "str",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("STR")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setStr(80).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> DEX = ITEMS.register(
            "dex",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("DEX")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setDex(80).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> FORT = ITEMS.register(
            "fort",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("FORT")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setFort(80).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> CON = ITEMS.register(
            "con",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("CON")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setCon(80).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> PERC = ITEMS.register(
            "perc",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("PERC")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setPerc(80).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> WIS = ITEMS.register(
            "wis",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("WIS")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setWis(80).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> CRIT_ITEM_2 = ITEMS.register(
            "crit_item_2",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Crit Multiplier")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setCritDamageMultiplier(100).setCritChanceMultiplier(100).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> DAMAGE_ITEM_2 = ITEMS.register(
            "damage_item_2",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Damage Multiplier")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setDamageMultiplier(100).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> PROJ_2 = ITEMS.register(
            "projectile_item_2",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Projectile & Draw Speed 2")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setProjectileDamageMultiplier(100).setDrawSpeedMultiplier(100).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> DEFENSE4 = ITEMS.register(
            "defense_item_4",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Defense Multiplier")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setArmorMultiplier(100).setToughnessMultiplier(100).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> HEALTH = ITEMS.register(
            "health_item",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Health")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setMaxHealth(20).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> HEALTH2 = ITEMS.register(
            "health_item_2",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Health Multiplier")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setHealthMultiplier(100).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> AS = ITEMS.register(
            "as_item",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Attack Speed Multiplier")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setSpeedMultiplier(100).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> AS2 = ITEMS.register(
            "as_item_2",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Attack Speed")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setAttackSpeed(125).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> DRAW_SPEED = ITEMS.register(
            "draw_speed",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("Draw Speed")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setDrawSpeed(125).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static final RegistryObject<AccessoryItem> ALL = ITEMS.register(
            "all",
            () -> AccessoryItem.builder(new Item.Properties())
                    .withDisplayName("ALL")
                    .withRarity(ItemRarity.DISTINCT)
                    .withType(AccessoryItem.AccessoryType.Utility)
                    .withStats(new CustomStats.Builder().setArmor(100).setArmorToughness(100).setMaxHealth(100)
                    .setKnockbackResist(100).setDebuffResist(100).setDamage(125).setAttackSpeed(125)
                    .setCritChance(125).setCritDamage(125).setMeleeDamage(125)
                    .setMeleeCritChance(125).setMeleeCritDamage(125).setProjectileDamage(125)
                    .setProjectileCritChance(125).setProjectileCritDamage(125).setMoveSpeed(100).setSwimSpeed(100)
                    .setXpGain(100).setBlockReach(5).setEntityReach(5).setLuck(5).setStepHeight(100)
                    .setJumpBoost(100).setMiningSpeed(100).setArmorMultiplier(100).setToughnessMultiplier(100)
                    .setHealthMultiplier(100).setDamageMultiplier(100).setSpeedMultiplier(100).setCritChanceMultiplier(100)
                    .setCritDamageMultiplier(100).setMeleeDamageMultiplier(100).setMeleeCritChanceMultiplier(100)
                    .setMeleeCritDamageMultiplier(100).setProjectileDamageMultiplier(100).setProjectileCritChanceMultiplier(100)
                    .setProjectileCritDamageMultiplier(100).setDrawSpeedMultiplier(100).setDrawSpeed(125).build())
                    .withLocation(AccessoryItem.AccessoryLocation.LostArtifact)
                    .build()
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
