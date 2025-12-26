package net.cold.coldsmod.stat;

import java.util.*;

public class StatGeneration {

    private static final Random rand = new Random();

    private static final Map<String, Map<String, double[]>> STAT_CONFIG = new HashMap<>();
    static {
        // --- Armor ---
        Map<String, double[]> helmet = new HashMap<>();
        helmet.put("armor", new double[]{-1.25, 2.75, 1.0});
        helmet.put("armorToughness", new double[]{-0.85, 1.25, 1.0});
        helmet.put("maxHealth", new double[]{-1.0, 1.7, 1.0});
        helmet.put("damage", new double[]{-1.5, 3, 1.0});
        helmet.put("critChance", new double[]{-2.0, 5.25, 1.0});
        helmet.put("attackSpeed", new double[]{-1, 2.0, 0.5});
        helmet.put("drawSpeed", new double[]{-1, 2.0, 0.5});
        helmet.put("xpGain", new double[]{0, 10, 0.2});
        helmet.put("debuffResist", new double[]{0, 6.75, 1.0});
        helmet.put("knockbackResist", new double[]{-0.85, 2.25, 0.2});
        STAT_CONFIG.put("helmet", helmet);

        Map<String, double[]> chestplate = new HashMap<>();
        chestplate.put("armor", new double[]{-1.5, 3.75, 1.0});
        chestplate.put("armorToughness", new double[]{-0.85, 1.7, 1.0});
        chestplate.put("maxHealth", new double[]{-1.3, 2.1, 0.8});
        chestplate.put("damage", new double[]{-3, 5.5, 0.8});
        chestplate.put("critChance", new double[]{-2.25, 4.5, 0.8});
        chestplate.put("critDamage", new double[]{-2.625, 6.0, 0.8});
        chestplate.put("xpGain", new double[]{0, 10, 0.2});
        chestplate.put("knockbackResist", new double[]{0, 1.7, 1.0});
        chestplate.put("debuffResist", new double[]{0, 5.1, 0.6});
        STAT_CONFIG.put("chestplate", chestplate);

        Map<String, double[]> leggings = new HashMap<>();
        leggings.put("armor", new double[]{-1.7, 3.0, 1.0});
        leggings.put("armorToughness", new double[]{-0.6, 1.0, 1.0});
        leggings.put("maxHealth", new double[]{-1.0, 1.7, 0.8});
        leggings.put("damage", new double[]{-2.25, 3.3, 1.0});
        leggings.put("critChance", new double[]{-1.625, 4.75, 1.0});
        leggings.put("critDamage", new double[]{-2.25, 6.25, 1.0});
        leggings.put("xpGain", new double[]{0, 7.5, 0.2});
        leggings.put("knockbackResist", new double[]{0, 2.55, 1.0});
        leggings.put("speed", new double[]{-3, 5.5, 1.0});
        STAT_CONFIG.put("leggings", leggings);

        Map<String, double[]> boots = new HashMap<>();
        boots.put("armor", new double[]{-1.25, 1.9, 0.8});
        boots.put("armorToughness", new double[]{-0.5, 0.85, 0.8});
        boots.put("maxHealth", new double[]{-1.0, 1.55, 0.8});
        boots.put("damage", new double[]{-1.35, 2.7, 0.7});
        boots.put("critDamage", new double[]{-2.0, 3.75, 0.6});
        boots.put("xpGain", new double[]{0, 7.5, 0.2});
        boots.put("knockbackResist", new double[]{-1.7, 2.55, 0.7});
        boots.put("speed", new double[]{0, 7.5, 1.0});
        boots.put("swimSpeed", new double[]{0, 9.25, 0.8});
        STAT_CONFIG.put("boots", boots);

        // --- Weapons ---
        Map<String, double[]> sword = new HashMap<>();
        sword.put("meleeDamage", new double[]{-3.75, 7.5, 1.0});
        sword.put("attackSpeed", new double[]{-2, 4, 1.0});
        sword.put("meleeCritChance", new double[]{-5.0, 8, 1.0});
        sword.put("meleeCritDamage", new double[]{-4, 8, 1.0});
        sword.put("xpGain", new double[]{0, 8, 0.4});
        STAT_CONFIG.put("sword", sword);
        STAT_CONFIG.put("axe", sword);
        STAT_CONFIG.put("trident", sword);

        Map<String, double[]> bow = new HashMap<>();
        bow.put("projectileDamage", new double[]{-2, 4, 1.0});
        bow.put("drawSpeed", new double[]{-2, 4, 1.0});
        bow.put("projectileCritChance", new double[]{-6, 16, 1.0});
        bow.put("projectileCritDamage", new double[]{-2, 8, 1.0});
        bow.put("xpGain", new double[]{0, 8, 0.4});
        STAT_CONFIG.put("bow", bow);

        Map<String, double[]> crossbow = new HashMap<>();
        crossbow.put("projectileDamage", new double[]{-4.5, 12.5, 1.0});
        crossbow.put("drawSpeed", new double[]{-3, 9, 1.0});
        crossbow.put("projectileCritChance", new double[]{0, 8, 1.0});
        crossbow.put("projectileCritDamage", new double[]{0, 12.5, 1.0});
        crossbow.put("xpGain", new double[]{0, 8, 0.4});
        STAT_CONFIG.put("crossbow", crossbow);

        Map<String, double[]> shield = new HashMap<>();
        shield.put("armor", new double[]{0.9, 1.8, 1.0});
        shield.put("armorToughness", new double[]{1, 2, 1.0});
        shield.put("debuffResist", new double[]{2, 6, 1.0});
        shield.put("knockbackResist", new double[]{3, 6, 1.0});
        shield.put("attackSpeed", new double[]{-12, -6, 0.4});
        shield.put("moveSpeed", new double[]{-6, -3, 0.4});
        STAT_CONFIG.put("shield", shield);

        Map<String, double[]> tools = new HashMap<>();
        tools.put("blockReach", new double[]{0, 1, 1.0});
        tools.put("miningSpeed", new double[]{0, 10, 1.0});
        STAT_CONFIG.put("tools", tools);
    }

    public static CustomStats generateStats(String itemType, ItemRarity rarity) {
        return generateWithFallback(itemType, rarity, 24);
    }

    private static CustomStats generateWithFallback(String itemType, ItemRarity rarity, int retries) {
        for (int attempt = 0; attempt < retries; attempt++) {
            CustomStats stats = tryGenerate(itemType, rarity);
            if (stats != null) return stats;
        }
        return new CustomStats(); // failsafe empty stats
    }

    private static CustomStats tryGenerate(String itemType, ItemRarity rarity) {
        Map<String, double[]> config = STAT_CONFIG.get(itemType);
        if (config == null) return null;

        int numStats;
        int minStats = rarity.getMinStats(itemType);
        int maxStats = rarity.getMaxStats(itemType);
        if (minStats == maxStats) {
            numStats = minStats;
        } else {
            numStats = rand.nextBoolean() ? minStats : maxStats;
        }

        List<String> passed = new ArrayList<>();
        for (String stat : config.keySet()) {
            double chance = config.get(stat)[2];
            if (rand.nextDouble() <= chance) {
                passed.add(stat);
            }
        }

        if (passed.size() < rarity.minStats) {
            List<String> remaining = new ArrayList<>(config.keySet());
            remaining.removeAll(passed);
            Collections.shuffle(remaining, rand);
            int needed = rarity.minStats - passed.size();
            for (int i = 0; i < needed && i < remaining.size(); i++) {
                passed.add(remaining.get(i));
            }
        }

        Collections.shuffle(passed, rand);
        List<String> chosen = passed.size() > numStats ? passed.subList(0, numStats) : passed;

        CustomStats stats = new CustomStats();

        for (String stat : chosen) {
            double[] data = config.get(stat);
            double min = data[0];
            double max = data[1];

            double scaledMin = min < 0 ? min * ((5 - rarity.getAttributeBias(itemType)) / 5.0) : min;
            double roll = rand.nextDouble() * (max - scaledMin) + scaledMin;

            double value = roll >= 0 ? roll * rarity.getWeight(itemType) : roll / rarity.getWeight(itemType);

            if (stat.equals("damage")) {
                String[] dmgTypes = {"damage", "meleeDamage", "projectileDamage"};
                String selected = dmgTypes[rand.nextInt(dmgTypes.length)];
                if (!selected.equals("damage")) value *= 1.333;
                applyStat(stats, selected, value);
            } else {
                applyStat(stats, stat, value);
            }
        }
        return stats;
    }

    private static void applyStat(CustomStats stats, String name, double value) {
        switch (name) {
            case "armor" -> stats.setArmor(value);
            case "armorToughness" -> stats.setArmorToughness(value);
            case "damage" -> stats.setDamage(value);
            case "meleeDamage" -> stats.setMeleeDamage(value);
            case "critChance" -> stats.setCritChance(value);
            case "critDamage" -> stats.setCritDamage(value);
            case "meleeCritChance" -> stats.setMeleeCritChance(value);
            case "meleeCritDamage" -> stats.setMeleeCritDamage(value);
            case "projectileCritChance" -> stats.setProjectileCritChance(value);
            case "projectileCritDamage" -> stats.setProjectileCritDamage(value);
            case "maxHealth" -> stats.setMaxHealth(value);
            case "xpGain" -> stats.setXpGain(value);
            case "knockbackResist" -> stats.setKnockbackResist(value);
            case "speed" -> stats.setMoveSpeed(value);
            case "swimSpeed" -> stats.setSwimSpeed(value);
            case "debuffResist" -> stats.setDebuffResist(value);
            case "attackSpeed" -> stats.setAttackSpeed(value);
            case "projectileDamage" -> stats.setProjectileDamage(value);
            case "drawSpeed" -> stats.setDrawSpeed(value);
            case "blockReach" -> stats.setBlockReach(value);
            case "miningSpeed" -> stats.setMiningSpeed(value);

            case "armorMultiplier" -> stats.setArmorMultiplier(value);
            case "toughnessMultiplier" -> stats.setToughnessMultiplier(value);
            case "healthMultiplier" -> stats.setHealthMultiplier(value);
            case "damageMultiplier" -> stats.setDamageMultiplier(value);
            case "meleeDamageMultiplier" -> stats.setMeleeDamageMultiplier(value);
            case "attackSpeedMultiplier" -> stats.setAttackSpeedMultiplier(value);
            case "critChanceMultiplier" -> stats.setCritChanceMultiplier(value);
            case "critDamageMultiplier" -> stats.setCritDamageMultiplier(value);
            case "meleeCritChanceMultiplier" -> stats.setMeleeCritChanceMultiplier(value);
            case "meleeCritDamageMultiplier" -> stats.setMeleeCritDamageMultiplier(value);
            case "projectileDamageMultiplier" -> stats.setProjectileDamageMultiplier(value);
            case "projectileCritChanceMultiplier" -> stats.setProjectileCritChanceMultiplier(value);
            case "projectileCritDamageMultiplier" -> stats.setProjectileCritDamageMultiplier(value);
            case "drawSpeedMultiplier" -> stats.setDrawSpeedMultiplier(value);
        }
    }
}
