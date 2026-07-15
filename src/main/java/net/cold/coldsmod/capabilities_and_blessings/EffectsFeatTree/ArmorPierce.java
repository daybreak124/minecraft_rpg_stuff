package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ArmorPierce extends MobEffect {

    private static final UUID AP_UUID = UUID.fromString("44a11bbb-51c5-6666-bbbb-a128b7c3288b");

    public ArmorPierce() {
        super(MobEffectCategory.HARMFUL, 0xFF0000);

        this.addAttributeModifier(ModAttributes.INCOMING_DAMAGE_MULTIPLIER.get(),
                AP_UUID.toString(),
                0.07d,
                AttributeModifier.Operation.MULTIPLY_BASE);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}