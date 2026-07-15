package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class HeartPiercer extends MobEffect {

    private static final UUID HP_UUID = UUID.fromString("4422bbbb-51c5-6666-bbbb-a12bbbc3288b");

    public HeartPiercer() {
        super(MobEffectCategory.HARMFUL, 0xFF0000);

        this.addAttributeModifier(ModAttributes.INCOMING_DAMAGE_MULTIPLIER.get(),
                HP_UUID.toString(),
                0.135d,
                AttributeModifier.Operation.MULTIPLY_BASE);

        this.addAttributeModifier(ModAttributes.DOT_INCOMING_DAMAGE_MULTIPLIER.get(),
                HP_UUID.toString(),
                0.32d,
                AttributeModifier.Operation.MULTIPLY_BASE);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}