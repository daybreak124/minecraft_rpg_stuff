package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class EnhancedBronzewood extends MobEffect {

    private static final UUID EB_UUID = UUID.fromString("4422baa4-55c5-6666-7dd7-aa8ffcccb888");

    public EnhancedBronzewood() {
        super(MobEffectCategory.HARMFUL, 0x800080);

        this.addAttributeModifier(ModAttributes.OUTGOING_DAMAGE_MULTIPLIER.get(),
                EB_UUID.toString(),
                -0.05d,
                AttributeModifier.Operation.MULTIPLY_BASE);

        this.addAttributeModifier(ModAttributes.INCOMING_DAMAGE_MULTIPLIER.get(),
                EB_UUID.toString(),
                0.03d,
                AttributeModifier.Operation.MULTIPLY_BASE);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}