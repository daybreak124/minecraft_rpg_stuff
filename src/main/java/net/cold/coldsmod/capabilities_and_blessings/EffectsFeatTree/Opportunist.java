package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Opportunist extends MobEffect {

    private static final UUID ABSORBED_EVIL_UUID = UUID.fromString("4422ab44-5bb5-b666-777d-aa88fb8cc888");


    public Opportunist() {
        super(MobEffectCategory.BENEFICIAL, 0xFF0000);

        this.addAttributeModifier(ModAttributes.MELEE_DAMAGE_MULTIPLIER.get(),
                ABSORBED_EVIL_UUID.toString(),
                0.013D,
                AttributeModifier.Operation.MULTIPLY_BASE);

        this.addAttributeModifier(ModAttributes.PROJECTILE_DAMAGE_MULTIPLIER.get(),
                ABSORBED_EVIL_UUID.toString(),
                0.013D,
                AttributeModifier.Operation.MULTIPLY_BASE);

        this.addAttributeModifier(ModAttributes.DOT_DAMAGE_MULTIPLIER.get(),
                ABSORBED_EVIL_UUID.toString(),
                0.013D,
                AttributeModifier.Operation.MULTIPLY_BASE);

        this.addAttributeModifier(ModAttributes.ALL_DAMAGE_MULTIPLIER.get(),
                ABSORBED_EVIL_UUID.toString(),
                0.013D,
                AttributeModifier.Operation.MULTIPLY_BASE);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}
