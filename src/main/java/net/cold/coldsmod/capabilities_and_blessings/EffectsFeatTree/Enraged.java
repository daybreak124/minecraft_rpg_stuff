package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class Enraged extends MobEffect {

    private static final UUID ENRAGED_UUID = UUID.fromString("4422b444-55c5-6666-7dd7-aa88acccc888");


    public Enraged() {
        super(MobEffectCategory.BENEFICIAL, 0xFF0000);

        this.addAttributeModifier(ModAttributes.MELEE_DAMAGE_MULTIPLIER.get(),
                ENRAGED_UUID.toString(),
                0.0125D,
                AttributeModifier.Operation.MULTIPLY_BASE);

        this.addAttributeModifier(ModAttributes.PROJECTILE_DAMAGE_MULTIPLIER.get(),
                ENRAGED_UUID.toString(),
                0.0125D,
                AttributeModifier.Operation.MULTIPLY_BASE);

        this.addAttributeModifier(ModAttributes.DOT_DAMAGE_MULTIPLIER.get(),
                ENRAGED_UUID.toString(),
                0.0125D,
                AttributeModifier.Operation.MULTIPLY_BASE);

        this.addAttributeModifier(ModAttributes.ALL_DAMAGE_MULTIPLIER.get(),
                ENRAGED_UUID.toString(),
                0.0125D,
                AttributeModifier.Operation.MULTIPLY_BASE);
    }
}
