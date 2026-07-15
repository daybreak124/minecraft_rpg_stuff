package net.cold.coldsmod.damage_types;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public class ModDamageTypes {
    public static final ResourceKey<DamageType> CUSTOM_MELEE_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("coldsmod", "custom_melee_damage"));

    public static final ResourceKey<DamageType> CUSTOM_RANGED_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("coldsmod", "custom_ranged_damage"));

    public static final ResourceKey<DamageType> TRUE_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("coldsmod", "true_damage"));

    public static final ResourceKey<DamageType> DIRECT_MELEE_HIT =
            ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("coldsmod", "direct_melee_damage"));

    public static final ResourceKey<DamageType> DOT_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("coldsmod", "melee_dot_damage"));

    public static final ResourceKey<DamageType> RECKONING_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("coldsmod", "reckoning_damage"));
}
