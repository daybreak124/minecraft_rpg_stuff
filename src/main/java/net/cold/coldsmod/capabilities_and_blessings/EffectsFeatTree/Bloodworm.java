package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Bloodworm extends MobEffect {

    private static final UUID BLOODWORM_UUID = UUID.fromString("7422aa44-5525-6666-7777-a288ca8cc888");

    public Bloodworm() {
        super(MobEffectCategory.BENEFICIAL, 0xFF0000);

        this.addAttributeModifier(ModAttributes.MELEE_DAMAGE_MULTIPLIER.get(),
                BLOODWORM_UUID.toString(),
                0.0020D,
                AttributeModifier.Operation.MULTIPLY_BASE);

        this.addAttributeModifier(ModAttributes.PROJECTILE_DAMAGE_MULTIPLIER.get(),
                BLOODWORM_UUID.toString(),
                0.0020D,
                AttributeModifier.Operation.MULTIPLY_BASE);

        this.addAttributeModifier(ModAttributes.DOT_DAMAGE_MULTIPLIER.get(),
                BLOODWORM_UUID.toString(),
                0.0020D,
                AttributeModifier.Operation.MULTIPLY_BASE);

        this.addAttributeModifier(ModAttributes.ALL_DAMAGE_MULTIPLIER.get(),
                BLOODWORM_UUID.toString(),
                0.0020D,
                AttributeModifier.Operation.MULTIPLY_BASE);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}
