package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class PreciseBlow extends MobEffect {

    private static final UUID CRIT_FW_UUID = UUID.fromString("7422ba44-5ab5-bb66-7777-a28bbbbbcd88");
    private static final UUID CRIT_FW_UUID2 = UUID.fromString("cc22ba44-5ab5-bb66-7777-a28bbbbbcd88");

    public PreciseBlow() {
        super(MobEffectCategory.BENEFICIAL, 0xFF0000);

        this.addAttributeModifier(ModAttributes.PRECISION.get(),
                CRIT_FW_UUID.toString(),
                10,
                AttributeModifier.Operation.ADDITION);

        this.addAttributeModifier(ModAttributes.MELEE_PRECISION.get(),
                CRIT_FW_UUID.toString(),
                10,
                AttributeModifier.Operation.ADDITION);

        this.addAttributeModifier(ModAttributes.PROJECTILE_PRECISION.get(),
                CRIT_FW_UUID.toString(),
                10,
                AttributeModifier.Operation.ADDITION);

        this.addAttributeModifier(ModAttributes.PRECISION.get(),
                CRIT_FW_UUID2.toString(),
                0.1,
                AttributeModifier.Operation.MULTIPLY_BASE);

        this.addAttributeModifier(ModAttributes.MELEE_PRECISION.get(),
                CRIT_FW_UUID2.toString(),
                0.1,
                AttributeModifier.Operation.MULTIPLY_BASE);

        this.addAttributeModifier(ModAttributes.PROJECTILE_PRECISION.get(),
                CRIT_FW_UUID2.toString(),
                0.1,
                AttributeModifier.Operation.MULTIPLY_BASE);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}
