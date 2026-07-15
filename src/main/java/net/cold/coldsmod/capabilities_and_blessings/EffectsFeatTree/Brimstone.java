package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Brimstone extends MobEffect {

    private static final UUID BR_UUID = UUID.fromString("b422a1a4-5ab5-6bb6-abab-aa8cbb8cc888");

    public Brimstone() {
        super(MobEffectCategory.HARMFUL, 0x310047);

        this.addAttributeModifier(Attributes.ARMOR,
                BR_UUID.toString(),
                4,
                AttributeModifier.Operation.ADDITION);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}
