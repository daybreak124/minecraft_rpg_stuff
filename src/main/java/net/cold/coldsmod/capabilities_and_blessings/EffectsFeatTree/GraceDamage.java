package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class GraceDamage extends MobEffect {

    private static final UUID GD_UUID = UUID.fromString("4422b444-55c5-6666-7dd7-aa88ababc888");


    public GraceDamage() {
        super(MobEffectCategory.BENEFICIAL, 0xFF0000);

        this.addAttributeModifier(ModAttributes.MELEE_DAMAGE_MULTIPLIER.get(),
                GD_UUID.toString(),
                0.010D,
                AttributeModifier.Operation.MULTIPLY_BASE);

        this.addAttributeModifier(ModAttributes.PROJECTILE_DAMAGE_MULTIPLIER.get(),
                GD_UUID.toString(),
                0.010D,
                AttributeModifier.Operation.MULTIPLY_BASE);

        this.addAttributeModifier(ModAttributes.DOT_DAMAGE_MULTIPLIER.get(),
                GD_UUID.toString(),
                0.010D,
                AttributeModifier.Operation.MULTIPLY_BASE);

        this.addAttributeModifier(ModAttributes.ALL_DAMAGE_MULTIPLIER.get(),
                GD_UUID.toString(),
                0.010D,
                AttributeModifier.Operation.MULTIPLY_BASE);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}
