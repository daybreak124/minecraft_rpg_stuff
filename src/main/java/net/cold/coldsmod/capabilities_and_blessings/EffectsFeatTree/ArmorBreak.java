package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ArmorBreak extends MobEffect {

    private static final UUID AB_UUID = UUID.fromString("b422a1a4-5ab5-6bb6-7bb7-aa8cbb8cc888");

    public ArmorBreak() {
        super(MobEffectCategory.HARMFUL, 0x310047);

        this.addAttributeModifier(Attributes.ARMOR,
                AB_UUID.toString(),
                -8,
                AttributeModifier.Operation.ADDITION);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}
