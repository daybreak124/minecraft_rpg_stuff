package net.cold.coldsmod.stat;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatUtils {

    public static void writeStatsToNBT(ItemStack stack, CustomStats stats) {
        if (stack == null || stats == null) return;
        CompoundTag tag = stack.getOrCreateTag();
        CompoundTag statsTag = new CompoundTag();
        statsTag.putInt("str", stats.getStr());
        statsTag.putInt("dex", stats.getDex());
        statsTag.putInt("fort", stats.getFort());
        statsTag.putInt("con", stats.getCon());
        statsTag.putInt("perc", stats.getPerc());
        statsTag.putInt("wis", stats.getWis());
        statsTag.putDouble("intelligence", stats.getIntelligence());
        statsTag.putDouble("armor", stats.getArmor());
        statsTag.putDouble("armorToughness", stats.getArmorToughness());
        statsTag.putDouble("maxHealth", stats.getMaxHealth());
        statsTag.putDouble("damage", stats.getDamage());
        statsTag.putDouble("attackSpeed", stats.getAttackSpeed());
        statsTag.putDouble("critChance", stats.getCritChance());
        statsTag.putDouble("critDamage", stats.getCritDamage());
        statsTag.putDouble("meleeCritChance", stats.getMeleeCritChance());
        statsTag.putDouble("meleeCritDamage", stats.getMeleeCritDamage());
        statsTag.putDouble("projectileCritChance", stats.getProjectileCritChance());
        statsTag.putDouble("projectileCritDamage", stats.getProjectileCritDamage());
        statsTag.putDouble("moveSpeed", stats.getMoveSpeed());
        statsTag.putDouble("swimSpeed", stats.getSwimSpeed());
        statsTag.putDouble("xpGain", stats.getXpGain());
        statsTag.putDouble("knockbackResist", stats.getKnockbackResist());
        statsTag.putDouble("debuffResist", stats.getDebuffResist());
        statsTag.putDouble("drawSpeed", stats.getDrawSpeed());
        statsTag.putDouble("projectileDamage", stats.getProjectileDamage());
        statsTag.putDouble("blockReach", stats.getBlockReach());
        statsTag.putDouble("entityReach", stats.getEntityReach());
        statsTag.putDouble("miningSpeed", stats.getMiningSpeed());
        statsTag.putDouble("jumpBoost", stats.getJumpBoost());
        statsTag.putDouble("stepHeight", stats.getStepHeight());
        statsTag.putDouble("Luck", stats.getLuck());
        statsTag.putDouble("armorMultiplier", stats.getArmorMultiplier());
        statsTag.putDouble("toughnessMultiplier", stats.getToughnessMultiplier());
        statsTag.putDouble("healthMultiplier", stats.getHealthMultiplier());
        statsTag.putDouble("damageMultiplier", stats.getDamageMultiplier());
        statsTag.putDouble("speedMultiplier", stats.getAttackSpeedMultiplier());
        statsTag.putDouble("critChanceMultiplier", stats.getCritChanceMultiplier());
        statsTag.putDouble("critDamageMultiplier", stats.getCritDamageMultiplier());
        statsTag.putDouble("meleeCritChanceMultiplier", stats.getMeleeCritChanceMultiplier());
        statsTag.putDouble("meleeCritDamageMultiplier", stats.getMeleeCritDamageMultiplier());
        statsTag.putDouble("projectileCritChanceMultiplier", stats.getProjectileCritChanceMultiplier());
        statsTag.putDouble("projectileCritDamageMultiplier", stats.getProjectileCritDamageMultiplier());
        statsTag.putDouble("projectileDamageMultiplier", stats.getProjectileDamageMultiplier());
        statsTag.putDouble("drawSpeedMultiplier", stats.getDrawSpeedMultiplier());
        statsTag.putDouble("meleeDamage", stats.getMeleeDamage());
        statsTag.putDouble("meleeDamageMultiplier", stats.getMeleeDamageMultiplier());

        tag.put("custom_stats", statsTag);
        stack.setTag(tag);
    }

    public static CustomStats readStatsFromNBT(ItemStack stack) {
        if (stack == null || stack.isEmpty() || stack.getTag() == null || !stack.getTag().contains("custom_stats")) {
            return new CustomStats();
        }
        CompoundTag tag = stack.getTag().getCompound("custom_stats");
        CustomStats stats = new CustomStats();
        stats.setStr(tag.getInt("str"));
        stats.setDex(tag.getInt("dex"));
        stats.setFort(tag.getInt("fort"));
        stats.setCon(tag.getInt("con"));
        stats.setPerc(tag.getInt("perc"));
        stats.setIntelligence(tag.getInt("intelligence"));
        stats.setWis(tag.getInt("wis"));
        stats.setArmor(tag.getDouble("armor"));
        stats.setArmorToughness(tag.getDouble("armorToughness"));
        stats.setMaxHealth(tag.getDouble("maxHealth"));
        stats.setDamage(tag.getDouble("damage"));
        stats.setAttackSpeed(tag.getDouble("attackSpeed"));
        stats.setCritChance(tag.getDouble("critChance"));
        stats.setCritDamage(tag.getDouble("critDamage"));
        stats.setMeleeCritChance(tag.getDouble("meleeCritChance"));
        stats.setMeleeCritDamage(tag.getDouble("meleeCritDamage"));
        stats.setProjectileCritChance(tag.getDouble("projectileCritChance"));
        stats.setProjectileCritDamage(tag.getDouble("projectileCritDamage"));
        stats.setMoveSpeed(tag.getDouble("moveSpeed"));
        stats.setSwimSpeed(tag.getDouble("swimSpeed"));
        stats.setXpGain(tag.getDouble("xpGain"));
        stats.setKnockbackResist(tag.getDouble("knockbackResist"));
        stats.setDebuffResist(tag.getDouble("debuffResist"));
        stats.setProjectileDamage(tag.getDouble("projectileDamage"));
        stats.setDrawSpeed(tag.getDouble("drawSpeed"));
        stats.setBlockReach(tag.getDouble("blockReach"));
        stats.setEntityReach(tag.getDouble("entityReach"));
        stats.setJumpBoost(tag.getDouble("jumpBoost"));
        stats.setStepHeight(tag.getDouble("stepHeight"));
        stats.setMiningSpeed(tag.getDouble("miningSpeed"));
        stats.setLuck(tag.getDouble("Luck"));
        stats.setArmorMultiplier(tag.getDouble("armorMultiplier"));
        stats.setToughnessMultiplier(tag.getDouble("toughnessMultiplier"));
        stats.setHealthMultiplier(tag.getDouble("healthMultiplier"));
        stats.setDamageMultiplier(tag.getDouble("damageMultiplier"));
        stats.setMeleeDamageMultiplier(tag.getDouble("meleeDamageMultiplier"));
        stats.setAttackSpeedMultiplier(tag.getDouble("speedMultiplier"));
        stats.setCritChanceMultiplier(tag.getDouble("critChanceMultiplier"));
        stats.setCritDamageMultiplier(tag.getDouble("critDamageMultiplier"));
        stats.setMeleeCritChanceMultiplier(tag.getDouble("meleeCritChanceMultiplier"));
        stats.setMeleeCritDamageMultiplier(tag.getDouble("meleeCritDamageMultiplier"));
        stats.setProjectileCritChanceMultiplier(tag.getDouble("projectileCritChanceMultiplier"));
        stats.setProjectileCritDamageMultiplier(tag.getDouble("projectileCritDamageMultiplier"));
        stats.setProjectileDamageMultiplier(tag.getDouble("projectileDamageMultiplier"));
        stats.setDrawSpeedMultiplier(tag.getDouble("drawSpeedMultiplier"));
        stats.setMeleeDamage(tag.getDouble("meleeDamage"));
        return stats;
    }

    public static boolean hasStats(ItemStack stack) {
        return stack != null && !stack.isEmpty() && stack.getTag() != null && stack.getTag().contains("custom_stats");
    }

    public static void writeRarityToNBT(ItemStack stack, ItemRarity rarity) {
        if (stack == null || rarity == null) return;
        CompoundTag tag = stack.getOrCreateTag();
        tag.putString("custom_rarity", rarity.displayName);
        stack.setTag(tag);
    }

    public static ItemRarity readRarityFromNBT(ItemStack stack) {
        if (stack == null || stack.isEmpty() || stack.getTag() == null || !stack.getTag().contains("custom_rarity")) return null;
        String rarityName = stack.getTag().getString("custom_rarity");
        for (ItemRarity r : ItemRarity.values()) {
            if (r.displayName.equals(rarityName)) return r;
        }
        return null;
    }

    public static void writeAttributesToNBT(ItemStack stack, Map<String, Integer> attributes) {
        if (stack == null || attributes == null) return;
        CompoundTag tag = stack.getOrCreateTag();
        CompoundTag attrTag = new CompoundTag();
        for (Map.Entry<String, Integer> entry : attributes.entrySet()) {
            attrTag.putInt(entry.getKey(), entry.getValue());
        }
        tag.put("custom_attributes", attrTag);
        stack.setTag(tag);
    }

    public static boolean hasAttributes(ItemStack stack) {
        return stack != null && !stack.isEmpty() && stack.getTag() != null && stack.getTag().contains("custom_attributes");
    }

    public static void applyAttributes(ItemStack stack, String itemType, ItemRarity rarity) {
        if (stack == null || itemType == null || rarity == null) return;
        Map<String, Integer> rolled = AttributeGeneration.generateAttributes(itemType, rarity);
        writeAttributesToNBT(stack, rolled);
        CustomStats stats = readStatsFromNBT(stack);
        stats.setStr(rolled.getOrDefault("STR", stats.getStr()));
        stats.setDex(rolled.getOrDefault("DEX", stats.getDex()));
        stats.setFort(rolled.getOrDefault("FORT", stats.getFort()));
        stats.setCon(rolled.getOrDefault("CON", stats.getCon()));
        stats.setPerc(rolled.getOrDefault("PERC", stats.getPerc()));
        stats.setIntelligence(rolled.getOrDefault("INT", stats.getIntelligence()));
        stats.setWis(rolled.getOrDefault("WIS", stats.getWis()));
        writeStatsToNBT(stack, stats);
    }

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (!hasStats(stack)) return;

        event.getToolTip().removeIf(
                line -> line.getString().contains("Knockback Resistance")
        );
    }


    public static void formatStatsTooltip(CustomStats stats, ItemRarity rarity, List<Component> tooltip, ItemStack stack) {
        double vanillaArmor = 0;
        double vanillaToughness = 0;
        double vanillaKnockbackResist = 0;
        boolean isArmorItem = stack.getItem() instanceof ArmorItem;

        if (isArmorItem) {
            ArmorItem armor = (ArmorItem) stack.getItem();
            vanillaArmor = armor.getDefense();
            vanillaToughness = armor.getToughness();
            vanillaKnockbackResist = 100 * armor.getMaterial().getKnockbackResistance();
        }

        Object[][] mainStats = new Object[][]{
                {"Strength", stats.getStr()},
                {"Dexterity", stats.getDex()},
                {"Fortitude", stats.getFort()},
                {"Constitution", stats.getCon()},
                {"Perception", stats.getPerc()},
                {"Intelligence", stats.getIntelligence()},
                {"Wisdom", stats.getWis()}
        };
        for (Object[] pair : mainStats) {
            String name = (String) pair[0];
            int value = (int) pair[1];
            if (value == 0) continue;
            tooltip.add(Component.literal(String.format("%s: %d", name, value)).withStyle(ChatFormatting.DARK_AQUA));
        }

        if (isArmorItem) {
            if (vanillaArmor != 0 || stats.getArmor() != 0) {
                String text = "Armor: " + formatValue(vanillaArmor);

                if (stats.getArmor() != 0) {
                    text += String.format(" (%s%s)", stats.getArmor() > 0 ? "+" : "", formatValue(stats.getArmor()));
                }

                tooltip.add(Component.literal(text).withStyle(ChatFormatting.BLUE));
            }

            if (vanillaToughness != 0 || stats.getArmorToughness() != 0) {
                String text = "Armor Toughness: " + formatValue(vanillaToughness);

                if (stats.getArmorToughness() != 0) {
                    text += String.format(" (%s%s)", stats.getArmorToughness() > 0 ? "+" : "", formatValue(stats.getArmorToughness()));
                }

                tooltip.add(Component.literal(text).withStyle(ChatFormatting.BLUE));
            }
            if (vanillaKnockbackResist != 0 || stats.getKnockbackResist() != 0) {
                String text = "Knockback Resist: " + formatValue(vanillaKnockbackResist) + "%";

                if (stats.getKnockbackResist() != 0) {
                    text += String.format(" (%s%s)", stats.getKnockbackResist() > 0 ? "+" : "", formatValue(stats.getKnockbackResist()) + "%");
                }

                tooltip.add(Component.literal(text).withStyle(ChatFormatting.BLUE));
            }
        }

        Object[][] defensiveStats = new Object[][]{
                {"Armor", stats.getArmor(), false},
                {"Armor Toughness", stats.getArmorToughness(), false},
                {"Max Health", stats.getMaxHealth(), false},
                {"Knockback Resist", stats.getKnockbackResist(), true},
                {"Debuff Resist", stats.getDebuffResist(), true}
        };

        for (Object[] pair : defensiveStats) {
            String name = (String) pair[0];
            double value = (double) pair[1];
            boolean percent = (boolean) pair[2];
            if (value == 0) continue;

            if (isArmorItem && (name.equals("Armor") || name.equals("Armor Toughness") || name.equals("Knockback Resist"))) continue;

            String formattedValue = formatValue(value);
            tooltip.add(Component.literal(String.format("%s: %s%s%s",
                            name, value > 0 ? "+" : "", formattedValue, percent ? "%" : ""))
                    .withStyle(ChatFormatting.BLUE));
        }

        Object[][] defensiveMultipliers = new Object[][]{
                {"Armor", stats.getArmorMultiplier()},
                {"Armor Toughness", stats.getToughnessMultiplier()},
                {"Max Health", stats.getHealthMultiplier()}
        };

        for (Object[] pair : defensiveMultipliers) {
            String name = (String) pair[0];
            double value = (double) pair[1];
            if (value == 0) continue;

            String formattedValue = formatValue(value);
            tooltip.add(Component.literal(String.format("%s: %s%s%%", name, value >= 0 ? "+" : "", formattedValue))
                    .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0F52BA))));
        }

        Object[][] offensiveStats = new Object[][]{
                {"Damage", stats.getDamage(), false},
                {"Melee Damage", stats.getMeleeDamage(), false},
                {"Attack Speed", stats.getAttackSpeed(), false},
                {"Projectile Damage", stats.getProjectileDamage(), false},
                {"Draw Speed", stats.getDrawSpeed(), false},
                {"Crit Chance", stats.getCritChance(), false},
                {"Crit Damage", stats.getCritDamage(), false},
                {"Melee Crit Chance", stats.getMeleeCritChance(), false},
                {"Melee Crit Damage", stats.getMeleeCritDamage(), false},
                {"Projectile Crit Chance", stats.getProjectileCritChance(), false},
                {"Projectile Crit Damage", stats.getProjectileCritDamage(), false}
        };

        for (Object[] pair : offensiveStats) {
            String name = (String) pair[0];
            double value = (double) pair[1];
            boolean percent = (boolean) pair[2];
            if (value == 0) continue;

            String formattedValue = formatValue(value);
            tooltip.add(Component.literal(String.format("%s: %s%s%s",
                            name, value > 0 ? "+" : "", formattedValue, percent ? "%" : ""))
                    .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
        }

        Object[][] offensiveMultipliers = new Object[][]{
                {"Damage", stats.getDamageMultiplier()},
                {"Melee Damage", stats.getMeleeDamageMultiplier()},
                {"Attack Speed", stats.getAttackSpeedMultiplier()},
                {"Projectile Damage", stats.getProjectileDamageMultiplier()},
                {"Draw Speed", stats.getDrawSpeedMultiplier()},
                {"Crit Chance", stats.getCritChanceMultiplier()},
                {"Crit Damage", stats.getCritDamageMultiplier()},
                {"Melee Crit Chance", stats.getMeleeCritChanceMultiplier()},
                {"Melee Crit Damage", stats.getMeleeCritDamageMultiplier()},
                {"Projectile Crit Chance", stats.getProjectileCritChanceMultiplier()},
                {"Projectile Crit Damage", stats.getProjectileCritDamageMultiplier()}
        };

        for (Object[] pair : offensiveMultipliers) {
            String name = (String) pair[0];
            double value = (double) pair[1];
            if (value == 0) continue;

            String formattedValue = formatValue(value);
            tooltip.add(Component.literal(String.format("%s: %s%s%%", name, value >= 0 ? "+" : "", formattedValue))
                    .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));
        }

        Object[][] utilityStats = new Object[][]{
                {"Speed", stats.getMoveSpeed(), true},
                {"Swim Speed", stats.getSwimSpeed(), true},
                {"Block Reach", stats.getBlockReach(), false},
                {"Entity Reach", stats.getEntityReach(), false},
                {"Step Height", stats.getStepHeight(), false},
                {"Mining Speed", stats.getMiningSpeed(), true},
                {"Jump Boost", stats.getJumpBoost(), true},
                {"XP Gain", stats.getXpGain(), true},
                {"Luck", stats.getLuck(), false}
        };
        for (Object[] pair : utilityStats) {
            String name = (String) pair[0];
            double value = (double) pair[1];
            boolean percent = (boolean) pair[2];
            if (value == 0) continue;

            String formattedValue = formatValue(value);

            tooltip.add(Component.literal(String.format("%s: %s%s%s",
                            name, value > 0 ? "+" : "", formattedValue, percent ? "%" : ""))
                    .withStyle(ChatFormatting.DARK_GREEN));
        }
    }

    public static String formatValue(double value) {
        if (Math.abs(value - Math.round(value)) < 0.01) {
            return String.format("%d", (int)Math.round(value));
        }

        int decimals = 0;
        double temp = value;

        while (decimals < 3 && Math.floor(temp) != temp) {
            temp *= 10;
            decimals++;
        }

        switch (decimals) {
            case 0: return String.format("%.0f", value);
            case 1: return String.format("%.1f", value);
            case 3: return String.format("%.3f", value);
            default: return String.format("%.2f", value);
        }
    }
}
