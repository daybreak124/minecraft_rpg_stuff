package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class AigretteBuff extends MobEffect {

    private static final UUID AIGRETTE_BUFF = UUID.fromString("cb22bbab-5ab5-bb66-7777-a28bbbbbcd88");


    public AigretteBuff() {
        super(MobEffectCategory.NEUTRAL, 0x00000);

        this.addAttributeModifier(ModAttributes.MELEE_DAMAGE_MULTIPLIER.get(),
                AIGRETTE_BUFF.toString(),
                0.2D,
                AttributeModifier.Operation.MULTIPLY_BASE);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}