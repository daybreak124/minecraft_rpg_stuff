package net.cold.coldsmod.stat;

import java.util.*;

public class AttributeGeneration {

    private static final Random rand = new Random();

    public static final List<String> ATTRIBUTES = Arrays.asList(
            "STR", "DEX", "FORT", "CON", "PERC"
    );

    public static final List<String> SHIELD_ATTRIBUTES = Arrays.asList(
            "FORT", "CON", "PERC"
    );

    private static final Map<String, int[]> TOTAL_RANGES = new HashMap<>();
    private static final Map<String, int[]> INDIVIDUAL_RANGES = new HashMap<>();

    static {
        TOTAL_RANGES.put("helmet", new int[]{5, 11});
        TOTAL_RANGES.put("chestplate", new int[]{7, 15});
        TOTAL_RANGES.put("leggings", new int[]{6, 12});
        TOTAL_RANGES.put("boots", new int[]{4, 10});
        TOTAL_RANGES.put("sword", new int[]{6, 12});
        TOTAL_RANGES.put("trident", new int[]{6, 12});
        TOTAL_RANGES.put("axe", new int[]{6, 12});
        TOTAL_RANGES.put("bow", new int[]{6, 12});
        TOTAL_RANGES.put("crossbow", new int[]{6, 12});
        TOTAL_RANGES.put("shield", new int[]{6, 12});
        TOTAL_RANGES.put("tools", new int[]{2, 6});

        INDIVIDUAL_RANGES.put("helmet", new int[]{-3, 10});
        INDIVIDUAL_RANGES.put("chestplate", new int[]{-5, 13});
        INDIVIDUAL_RANGES.put("leggings", new int[]{-4, 11});
        INDIVIDUAL_RANGES.put("boots", new int[]{-2, 6});
        INDIVIDUAL_RANGES.put("sword", new int[]{-3, 12});
        INDIVIDUAL_RANGES.put("trident", new int[]{-3, 12});
        INDIVIDUAL_RANGES.put("axe", new int[]{-3, 12});
        INDIVIDUAL_RANGES.put("bow", new int[]{-3, 12});
        INDIVIDUAL_RANGES.put("crossbow", new int[]{-3, 12});
        INDIVIDUAL_RANGES.put("shield", new int[]{-3, 12});
        INDIVIDUAL_RANGES.put("tools", new int[]{2, 6});

    }

    public static Map<String, Integer> generateAttributes(String itemType, ItemRarity rarity) {
        Map<String, Integer> rolled = new HashMap<>();
        int bias = rarity.getAttributeBias(itemType);

        int minTotal = TOTAL_RANGES.get(itemType)[0] + bias * 2;
        int maxTotal = TOTAL_RANGES.get(itemType)[1] + bias * 3;

        int minInd = INDIVIDUAL_RANGES.get(itemType)[0];
        int maxInd = INDIVIDUAL_RANGES.get(itemType)[1] + bias;

        List<String> selected;


        if ("tools".equals(itemType)) {
            selected = Collections.singletonList("WIS");
        } else if ("shield".equals(itemType)) {
            selected = new ArrayList<>(SHIELD_ATTRIBUTES);
            Collections.shuffle(selected, rand);
            selected = selected.subList(0, 2);
        } else {
            selected = new ArrayList<>(ATTRIBUTES);
            Collections.shuffle(selected, rand);
            selected = selected.subList(0, 3);
        }

        int attempts = 0;

        while (true) {
            rolled.clear();
            for (String attr : ATTRIBUTES) rolled.put(attr, 0);

            int total = 0;

            for (String attr : selected) {
                int value = rand.nextInt(maxInd - minInd + 1) + minInd;
                rolled.put(attr, value);
                total += value;
            }

            if (total >= minTotal && total <= maxTotal) {
                return rolled;
            }

            attempts++;
            if (attempts > 50) {
                int diff = (total < minTotal) ? minTotal - total : total - maxTotal;
                String randomKey = selected.get(rand.nextInt(selected.size()));
                rolled.put(randomKey, rolled.get(randomKey) + (total < minTotal ? diff : -diff));
                return rolled;
            }
        }
    }
}
