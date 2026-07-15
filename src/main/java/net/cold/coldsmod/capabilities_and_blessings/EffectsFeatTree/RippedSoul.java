package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class RippedSoul extends MobEffect {

    private static final UUID RS_UUID = UUID.fromString("4422bbbb-55c5-6666-bbbb-aabbbbccb88b");

    public RippedSoul() {
        super(MobEffectCategory.HARMFUL, 0x00FFFF);

        this.addAttributeModifier(ModAttributes.INCOMING_DAMAGE_MULTIPLIER.get(),
                RS_UUID.toString(),
                0.12d,
                AttributeModifier.Operation.MULTIPLY_BASE);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}