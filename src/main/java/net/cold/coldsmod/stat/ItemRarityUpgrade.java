package net.cold.coldsmod.stat;

import net.cold.coldsmod.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber
public class ItemRarityUpgrade {

    private static final Map<ItemRarity, RegistryObject<Item>> UPGRADE_MATERIALS = new HashMap<>();
    private static final Map<ItemRarity, ItemRarity> NEXT_RARITY = new HashMap<>();

    static {
        UPGRADE_MATERIALS.put(ItemRarity.COMMON, ModItems.COMMON_SCRAP_ESSENCE);
        UPGRADE_MATERIALS.put(ItemRarity.UNCOMMON, ModItems.UNCOMMON_SCRAP_ESSENCE);
        UPGRADE_MATERIALS.put(ItemRarity.RARE, ModItems.RARE_SCRAP_ESSENCE);
        UPGRADE_MATERIALS.put(ItemRarity.EPIC, ModItems.EPIC_SCRAP_ESSENCE);
        UPGRADE_MATERIALS.put(ItemRarity.LEGENDARY, ModItems.LEGENDARY_SCRAP_ESSENCE);

        NEXT_RARITY.put(ItemRarity.COMMON, ItemRarity.UNCOMMON);
        NEXT_RARITY.put(ItemRarity.UNCOMMON, ItemRarity.RARE);
        NEXT_RARITY.put(ItemRarity.RARE, ItemRarity.EPIC);
        NEXT_RARITY.put(ItemRarity.EPIC, ItemRarity.LEGENDARY);
        NEXT_RARITY.put(ItemRarity.LEGENDARY, ItemRarity.MYTHIC);
    }

    private static final Map<ItemRarity, RegistryObject<Item>> STAT_PEARLS = new HashMap<>();

    static {
        STAT_PEARLS.put(ItemRarity.COMMON, ModItems.PEARL_OF_REPLENISHING);
        STAT_PEARLS.put(ItemRarity.UNCOMMON, ModItems.PEARL_OF_RECHARGING);
        STAT_PEARLS.put(ItemRarity.RARE, ModItems.PEARL_OF_RENEWING);
        STAT_PEARLS.put(ItemRarity.EPIC, ModItems.PEARL_OF_RESTORING);
        STAT_PEARLS.put(ItemRarity.LEGENDARY, ModItems.PEARL_OF_REJUVENATING);
        STAT_PEARLS.put(ItemRarity.MYTHIC, ModItems.PEARL_OF_REVITALIZING);
    }

    private static final Map<ItemRarity, RegistryObject<Item>> STAT_SHARDS = new HashMap<>();

    static {
        STAT_SHARDS.put(ItemRarity.COMMON, ModItems.SHARD_OF_INFUSION);
        STAT_SHARDS.put(ItemRarity.UNCOMMON, ModItems.SHARD_OF_AUGMENTATION);
        STAT_SHARDS.put(ItemRarity.RARE, ModItems.SHARD_OF_AMPLIFICATION);
        STAT_SHARDS.put(ItemRarity.EPIC, ModItems.SHARD_OF_EMPOWERMENT);
        STAT_SHARDS.put(ItemRarity.LEGENDARY, ModItems.SHARD_OF_ASCENDANCY);
        STAT_SHARDS.put(ItemRarity.MYTHIC, ModItems.SHARD_OF_TRANSCENDENCE);
    }


    @SubscribeEvent
    public static void onAnvilUpgrade(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();

        if (left.isEmpty() || right.isEmpty()) return;

        ItemRarity currentRarity = ItemRarityUtils.readRarityFromNBT(left);
        if (currentRarity == null) return;

        RegistryObject<Item> pearl = STAT_PEARLS.get(currentRarity);
        if (pearl != null && right.getItem() == pearl.get()) {
            ItemStack output = left.copy();

            CompoundTag tag = output.getOrCreateTag();
            if (tag.contains("custom_stats")) {
                CompoundTag oldStats = tag.getCompound("custom_stats");
                CompoundTag newStats = new CompoundTag();

                for (String key : new String[]{"str", "fort", "dex", "con", "perc", "intelligence", "wis"}) {
                    if (oldStats.contains(key)) {
                        newStats.putInt(key, oldStats.getInt(key));
                    }
                }
                tag.put("custom_stats", newStats);
            }

            tag.putBoolean("needsOnlyStats", true);
            ItemRarityUtils.writeRarityToNBT(output, currentRarity);

            CompoundTag displayTag = output.getOrCreateTagElement("display");
            displayTag.putString("Name", Component.Serializer.toJson(
                    Component.literal(left.getHoverName().getString())
                            .withStyle(currentRarity.color)
                            .withStyle(Style.EMPTY.withItalic(false))
            ));

            event.setOutput(output);
            event.setCost(5);
            event.setMaterialCost(1);
            return;
        }

        RegistryObject<Item> shard = STAT_SHARDS.get(currentRarity);
        if (shard != null && right.getItem() == shard.get()) {
            ItemStack output = left.copy();

            CompoundTag tag = output.getOrCreateTag();
            if (tag.contains("custom_stats")) {
                CompoundTag oldStats = tag.getCompound("custom_stats");
                CompoundTag newStats = new CompoundTag();

                for (String key : new String[]{
                        "armor", "armorToughness", "maxHealth", "knockbackResist", "debuffResist",
                        "damage", "meleeDamage", "attackSpeed", "critChance", "critDamage",
                        "meleeCritChance", "meleeCritDamage",
                        "projectileDamage", "projectileCritChance", "projectileCritDamage",

                        "moveSpeed", "swimSpeed", "xpGain", "drawSpeed",
                        "blockReach", "entityReach", "stepHeight", "jumpBoost", "luck",

                        "armorMultiplier", "armorToughnessMultiplier", "healthMultiplier", "damageMultiplier",
                        "meleeDamageMultiplier", "attackSpeedMultiplier", "projectileDamageMultiplier", "drawSpeedMultiplier",
                        "critChanceMultiplier", "critDamageMultiplier", "meleeCritChanceMultiplier", "meleeCritDamageMultiplier",
                        "projectileCritChanceMultiplier", "projectileCritDamageMultiplier"
                }) {
                    if (oldStats.contains(key)) {
                        newStats.putDouble(key, oldStats.getDouble(key));
                    }
                }

                tag.put("custom_stats", newStats);
            }

            tag.putBoolean("needsOnlyAttributes", true);
            ItemRarityUtils.writeRarityToNBT(output, currentRarity);

            CompoundTag displayTag = output.getOrCreateTagElement("display");
            displayTag.putString("Name", Component.Serializer.toJson(
                    Component.literal(left.getHoverName().getString())
                            .withStyle(currentRarity.color)
                            .withStyle(Style.EMPTY.withItalic(false))
            ));

            event.setOutput(output);
            event.setCost(5);
            event.setMaterialCost(1);
            return;
        }


        if (!NEXT_RARITY.containsKey(currentRarity)) return;
        RegistryObject<Item> required = UPGRADE_MATERIALS.get(currentRarity);
        if (required == null || right.getItem() != required.get()) return;

        ItemRarity nextRarity = NEXT_RARITY.get(currentRarity);
        ItemStack output = left.copy();

        if (output.hasTag() && output.getTag().contains("custom_stats")) {
            output.getTag().remove("custom_stats");
        }

        ItemRarityUtils.writeRarityToNBT(output, nextRarity);
        output.getOrCreateTag().putBoolean("needsAllStats", true);

        CompoundTag displayTag = output.getOrCreateTagElement("display");
        displayTag.putString("Name", Component.Serializer.toJson(
                Component.literal(left.getHoverName().getString())
                        .withStyle(nextRarity.color)
                        .withStyle(Style.EMPTY.withItalic(false))
        ));
        event.setOutput(output);
        event.setCost((currentRarity.attributeBias*5 + 5));
        event.setMaterialCost(1);
    }



    @SubscribeEvent
    public static void onAnvilTake(AnvilRepairEvent event) {
        ItemStack stack = event.getOutput();
        if (stack == null || stack.isEmpty()) return;

        CompoundTag tag = stack.getOrCreateTag();
        ItemRarity rarity = ItemRarityUtils.readRarityFromNBT(stack);
        if (rarity == null) return;

        if (tag.contains("needsAllStats")) {
            CustomStats stats = StatGeneration.generateStats(ItemRarityUtils.getItemType(stack), rarity);
            StatUtils.writeStatsToNBT(stack, stats);
            StatUtils.applyAttributes(stack, ItemRarityUtils.getItemType(stack), rarity);
            tag.remove("needsAllStats");
        }

        if (tag.contains("needsOnlyStats")) {
            CompoundTag core = tag.contains("custom_stats") ? tag.getCompound("custom_stats") : new CompoundTag();
            CustomStats generated = StatGeneration.generateStats(ItemRarityUtils.getItemType(stack), rarity);

            if (core.contains("str")) generated.setStr(core.getInt("str"));
            if (core.contains("fort")) generated.setFort(core.getInt("fort"));
            if (core.contains("dex")) generated.setDex(core.getInt("dex"));
            if (core.contains("con")) generated.setCon(core.getInt("con"));
            if (core.contains("perc")) generated.setPerc(core.getInt("perc"));
            if (core.contains("intelligence")) generated.setIntelligence(core.getInt("intelligence"));
            if (core.contains("wis")) generated.setWis(core.getInt("wis"));


            StatUtils.writeStatsToNBT(stack, generated);
            tag.remove("needsOnlyStats");
        }

        if (tag.contains("needsOnlyAttributes")) {
            StatUtils.applyAttributes(stack, ItemRarityUtils.getItemType(stack), rarity);
            tag.remove("needsOnlyAttributes");
        }
    }

    // last resort
//    @SubscribeEvent
//    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
//        if (event.phase != TickEvent.Phase.END || event.player.level().isClientSide) return;
//
//        for (ItemStack stack : event.player.getInventory().items) {
//            if (stack != null && !stack.isEmpty() && stack.hasTag() && stack.getTag().getBoolean("needsStats")) {
//                ItemRarity rarity = ItemRarityUtils.readRarityFromNBT(stack);
//                CustomStats stats = StatGeneration.generateStats(ItemRarityUpgrade.getItemType(stack), rarity);
//                StatUtils.writeStatsToNBT(stack, stats);
//                StatUtils.applyAttributes(stack, ItemRarityUpgrade.getItemType(stack), rarity);
//
//                // Remove the flag
//                stack.getTag().remove("needsStats");
//            }
//        }
//    }
}
