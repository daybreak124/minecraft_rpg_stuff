package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Collector extends MobEffect {

    private static final UUID COLLECTOR_UUID = UUID.fromString("4c22ac44-5c25-666c-7777-ba88ab8cc888");

    public Collector() {
        super(MobEffectCategory.BENEFICIAL, 0xFF0000);

        this.addAttributeModifier(ModAttributes.MELEE_DAMAGE_MULTIPLIER.get(),
                COLLECTOR_UUID.toString(),
                0.02D,
                AttributeModifier.Operation.MULTIPLY_BASE);

        this.addAttributeModifier(ModAttributes.PROJECTILE_DAMAGE_MULTIPLIER.get(),
                COLLECTOR_UUID.toString(),
                0.02D,
                AttributeModifier.Operation.MULTIPLY_BASE);

        this.addAttributeModifier(ModAttributes.DOT_DAMAGE_MULTIPLIER.get(),
                COLLECTOR_UUID.toString(),
                0.02D,
                AttributeModifier.Operation.MULTIPLY_BASE);

        this.addAttributeModifier(ModAttributes.ALL_DAMAGE_MULTIPLIER.get(),
                COLLECTOR_UUID.toString(),
                0.02D,
                AttributeModifier.Operation.MULTIPLY_BASE);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}
