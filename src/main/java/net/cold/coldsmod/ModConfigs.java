package net.cold.coldsmod;

import net.minecraftforge.common.ForgeConfigSpec;

public class ModConfigs {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Double> ACCESSORY_MULTIPLIER;
    public static final ForgeConfigSpec.ConfigValue<Double> BLESSING_MULTIPLIER;
    public static final ForgeConfigSpec.ConfigValue<Double> MATERIAL_MULTIPLIER;

    static {
        BUILDER.push("Drop rate multipliers");

        ACCESSORY_MULTIPLIER = BUILDER
                .comment("Multiplier for accessories (Default: 1.0, Recommended: 1.5-2.0)")
                .defineInRange("accessoryMultiplier", 1.0, 0.0, 100.0);

        BLESSING_MULTIPLIER = BUILDER
                .comment("Multiplier for blessings (Default: 1.0, Recommended: 1.0)")
                .defineInRange("blessingMultiplier", 1.0, 0.0, 100.0);

        MATERIAL_MULTIPLIER = BUILDER
                .comment("Multiplier for materials (Default: 1.0, Recommended: 1.5-2.0)")
                .defineInRange("materialMultiplier", 1.0, 0.0, 100.0);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
