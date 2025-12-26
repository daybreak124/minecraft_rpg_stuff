package net.cold.coldsmod.stat;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.SmithingMenu;
import net.minecraft.world.item.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

import java.util.List;
import java.util.Map;

public class ItemModifier {

    public static void register() {
        MinecraftForge.EVENT_BUS.register(new ItemModifier());
    }

    @SubscribeEvent
    public void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        ItemStack result = event.getCrafting();

        if (event.getInventory() instanceof SmithingMenu smithingMenu) {
            ItemStack baseItem = smithingMenu.getSlot(0).getItem();

            if (StatUtils.hasStats(baseItem)) {
                CustomStats stats = StatUtils.readStatsFromNBT(baseItem);
                ItemRarity rarity = StatUtils.readRarityFromNBT(baseItem);
                StatUtils.writeRarityToNBT(result, rarity);
                StatUtils.writeStatsToNBT(result, stats);
            }
        }
        applyRarityAndStats(result);
        applyAttributes(result);
    }

    @SubscribeEvent
    public void onEntityItemPickup(EntityItemPickupEvent event) {
        ItemStack pickedUp = event.getItem().getItem();
        applyRarityAndStats(pickedUp);
        applyAttributes(pickedUp);
    }

    @SubscribeEvent
    public void onContainerClosed(PlayerContainerEvent.Close event) {
        if (event.getContainer() instanceof CraftingMenu) {
            for (ItemStack stack : event.getEntity().getInventory().items) {
                applyRarityAndStats(stack);
                applyAttributes(stack);
            }
        }

        if (event.getContainer() instanceof AnvilMenu) {
            for (ItemStack stack : event.getEntity().getInventory().items) {
                if (stack == null || stack.isEmpty() || stack.getTag() == null) continue;

                CompoundTag tag = stack.getTag();
                ItemRarity rarity = ItemRarityUtils.readRarityFromNBT(stack);
                if (rarity == null) continue;

                String type = ItemRarityUtils.getItemType(stack);

                if (tag.contains("needsAllStats")) {
                    CustomStats stats = StatGeneration.generateStats(type, rarity);
                    StatUtils.writeStatsToNBT(stack, stats);
                    StatUtils.applyAttributes(stack, type, rarity);
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
                    CompoundTag oldStats = tag.contains("custom_stats") ? tag.getCompound("custom_stats") : new CompoundTag();
                    CompoundTag newStats = new CompoundTag();

                    // Keep only attributes
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
                    StatUtils.applyAttributes(stack, type, rarity);
                    tag.remove("needsOnlyAttributes");
                }
            }
        }
    }

    @SubscribeEvent
    public void onChestOpen(PlayerContainerEvent.Open event) {
        var menu = event.getContainer();
        for (var slot : menu.slots) {
            ItemStack stack = slot.getItem();
            applyRarityAndStats(stack);
            applyAttributes(stack);
        }
    }

    public static void applyRarityAndStats(ItemStack stack) {
        if (stack == null || stack.isEmpty()) return;

        String itemType = ItemRarityUtils.getItemType(stack);
        if ("unknown".equals(itemType) || StatUtils.hasStats(stack)) return;

        ItemRarity rarity = ItemRarity.setCustomRarity();
        StatUtils.writeRarityToNBT(stack, rarity);

        CustomStats stats = StatGeneration.generateStats(itemType, rarity);
        StatUtils.writeStatsToNBT(stack, stats);
    }

    public static void applyStatsOnly(ItemStack stack) {
        if (stack == null || stack.isEmpty()) return;

        String itemType = ItemRarityUtils.getItemType(stack);
        if ("unknown".equals(itemType)) return;


        ItemRarity rarity = ItemRarity.getCustomRarity(stack);
        StatUtils.writeRarityToNBT(stack, rarity);


        CustomStats stats = StatGeneration.generateStats(itemType, rarity);
        StatUtils.writeStatsToNBT(stack, stats);
    }

    private void applyAttributes(ItemStack stack) {
        if (stack == null || stack.isEmpty()) return;

        if (StatUtils.hasAttributes(stack)) return;

        String itemType = ItemRarityUtils.getItemType(stack);
        if ("unknown".equals(itemType)) return;

        ItemRarity rarity = ItemRarityUtils.hasRarity(stack)
                ? ItemRarityUtils.readRarityFromNBT(stack)
                : ItemRarity.setCustomRarity();

        Map<String, Integer> attributes = AttributeGeneration.generateAttributes(itemType, rarity);

        CustomStats stats = StatUtils.hasStats(stack)
                ? StatUtils.readStatsFromNBT(stack)
                : new CustomStats();

        stats.setStr(attributes.getOrDefault("STR", stats.getStr()));
        stats.setDex(attributes.getOrDefault("DEX", stats.getDex()));
        stats.setFort(attributes.getOrDefault("FORT", stats.getFort()));
        stats.setCon(attributes.getOrDefault("CON", stats.getCon()));
        stats.setPerc(attributes.getOrDefault("PERC", stats.getPerc()));
        stats.setWis(attributes.getOrDefault("WIS", stats.getWis()));
        stats.setWis(attributes.getOrDefault("WIS", stats.getIntelligence()));

        StatUtils.writeStatsToNBT(stack, stats);
        StatUtils.writeAttributesToNBT(stack, attributes);
    }

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event) {
        ItemRarityUtils utils = new ItemRarityUtils();
        ItemStack stack = event.getItemStack();
        List<Component> tooltip = event.getToolTip();

        if (!ItemRarityUtils.hasRarity(stack) || !StatUtils.hasStats(stack)) return;

        ItemRarity rarity = ItemRarityUtils.readRarityFromNBT(stack);
        CustomStats stats = StatUtils.readStatsFromNBT(stack);

        if (!stack.hasCustomHoverName()) {
            String originalName = stack.getHoverName().getString();
            stack.setHoverName(
                    Component.literal(originalName)
                            .withStyle(style -> style.withColor(rarity.color).withItalic(false))
            );
        }

        tooltip.removeIf(comp -> {
            String s = comp.getString().toLowerCase();
            return s.contains("armor") || s.contains("armor toughness");
        });

        tooltip.add(1, Component.literal(rarity.displayName).withStyle(rarity.color));

        if ("shield".equals(ItemRarityUtils.getItemType(stack))) {
            tooltip.add(Component.literal(" "));
            tooltip.add(Component.literal("When Equipped:").withStyle(net.minecraft.ChatFormatting.GRAY));
        }
        else if ("bow".equals(ItemRarityUtils.getItemType(stack)) ||
                "crossbow".equals(ItemRarityUtils.getItemType(stack))) {
            tooltip.add(Component.literal(" "));
            tooltip.add(Component.literal("When Used:").withStyle(ChatFormatting.GRAY));
        }
        StatUtils.formatStatsTooltip(stats, rarity, tooltip, stack);
    }
}

