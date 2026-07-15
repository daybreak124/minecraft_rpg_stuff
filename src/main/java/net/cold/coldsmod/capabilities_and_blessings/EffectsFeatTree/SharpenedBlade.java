package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class SharpenedBlade extends MobEffect {

    private static final UUID CRIT_ASC_UUID = UUID.fromString("ccddb6b1-3536-465b-8564-1b66a3bcf4f6");

    public SharpenedBlade() {
        super(MobEffectCategory.BENEFICIAL, 0xFF0000);

        this.addAttributeModifier(ModAttributes.ACCURACY.get(),
                CRIT_ASC_UUID.toString(),
                2.5,
                AttributeModifier.Operation.ADDITION);

        this.addAttributeModifier(ModAttributes.MELEE_ACCURACY.get(),
                CRIT_ASC_UUID.toString(),
                2.5,
                AttributeModifier.Operation.ADDITION);

        this.addAttributeModifier(ModAttributes.PROJECTILE_ACCURACY.get(),
                CRIT_ASC_UUID.toString(),
                2.5,
                AttributeModifier.Operation.ADDITION);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}
