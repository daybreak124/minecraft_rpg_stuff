package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Retaliated extends MobEffect {

    private static final UUID RE_UUID = UUID.fromString("4422bbbb-51c5-6556-bbbb-a12b55c3288b");

    public Retaliated() {
        super(MobEffectCategory.HARMFUL, 0xFFFF00);

        this.addAttributeModifier(ModAttributes.INCOMING_DAMAGE_MULTIPLIER.get(),
                RE_UUID.toString(),
                0.1d,
                AttributeModifier.Operation.MULTIPLY_BASE);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}