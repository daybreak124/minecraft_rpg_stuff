package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class Blessed extends MobEffect {

    private static final UUID BLESSED_UUID = UUID.fromString("4422a144-5525-6666-7777-aa88bb8cc888");

    public Blessed() {
        super(MobEffectCategory.BENEFICIAL, 0xFF0000);

        this.addAttributeModifier(Attributes.ARMOR,
                BLESSED_UUID.toString(),
                3.0D,
                AttributeModifier.Operation.ADDITION);

        this.addAttributeModifier(ModAttributes.MELEE_DAMAGE_MULTIPLIER.get(),
                BLESSED_UUID.toString(),
                0.02D,
                AttributeModifier.Operation.MULTIPLY_BASE);

        this.addAttributeModifier(ModAttributes.PROJECTILE_DAMAGE_MULTIPLIER.get(),
                BLESSED_UUID.toString(),
                0.02D,
                AttributeModifier.Operation.MULTIPLY_BASE);

        this.addAttributeModifier(ModAttributes.DOT_DAMAGE_MULTIPLIER.get(),
                BLESSED_UUID.toString(),
                0.02D,
                AttributeModifier.Operation.MULTIPLY_BASE);

        this.addAttributeModifier(ModAttributes.ALL_DAMAGE_MULTIPLIER.get(),
                BLESSED_UUID.toString(),
                0.02D,
                AttributeModifier.Operation.MULTIPLY_BASE);
    }
}
