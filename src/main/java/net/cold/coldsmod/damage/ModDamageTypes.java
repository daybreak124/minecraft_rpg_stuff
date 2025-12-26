package net.cold.coldsmod.damage;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public class ModDamageTypes {
    public static final ResourceKey<DamageType> EXPLOSION_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("coldsmod", "explosion_damage"));

    public static final ResourceKey<DamageType> RECKONING =
            ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("coldsmod", "reckoning"));

    public static final ResourceKey<DamageType> LIGHTNING_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("coldsmod", "lightning_damage"));

    public static final ResourceKey<DamageType> CURSE_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("coldsmod", "curse_damage"));
}
