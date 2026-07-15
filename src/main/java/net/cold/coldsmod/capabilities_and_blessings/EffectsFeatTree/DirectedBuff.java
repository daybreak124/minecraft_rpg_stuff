package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class DirectedBuff extends MobEffect {

    private static final UUID DB_UUID = UUID.fromString("7422ba44-5a12-bb66-7777-a28bb21bcd88");
    private static final UUID DB_UUID2 = UUID.fromString("cc22ba44-5a12-bb66-7777-a28bb21bcd88");

    public DirectedBuff() {
        super(MobEffectCategory.BENEFICIAL, 0xFF0000);

        this.addAttributeModifier(ModAttributes.PRECISION.get(),
                DB_UUID.toString(),
                10,
                AttributeModifier.Operation.ADDITION);

        this.addAttributeModifier(ModAttributes.MELEE_PRECISION.get(),
                DB_UUID.toString(),
                10,
                AttributeModifier.Operation.ADDITION);

        this.addAttributeModifier(ModAttributes.PROJECTILE_PRECISION.get(),
                DB_UUID.toString(),
                10,
                AttributeModifier.Operation.ADDITION);

        this.addAttributeModifier(ModAttributes.ACCURACY.get(),
                DB_UUID2.toString(),
                0.10,
                AttributeModifier.Operation.MULTIPLY_BASE);

        this.addAttributeModifier(ModAttributes.MELEE_ACCURACY.get(),
                DB_UUID2.toString(),
                10,
                AttributeModifier.Operation.ADDITION);

        this.addAttributeModifier(ModAttributes.PROJECTILE_ACCURACY.get(),
                DB_UUID2.toString(),
                10,
                AttributeModifier.Operation.ADDITION);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}
