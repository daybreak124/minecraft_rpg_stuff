package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class StolenArmor extends MobEffect {

    private static final UUID SA_UUID = UUID.fromString("b422a1a4-5ab5-aab6-7bb7-aaddbb8cc888");

    public StolenArmor() {
        super(MobEffectCategory.BENEFICIAL, 0x000000);

        this.addAttributeModifier(Attributes.ARMOR,
                SA_UUID.toString(),
                4,
                AttributeModifier.Operation.ADDITION);
    }
}
