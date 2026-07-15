package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Gambit extends MobEffect {

    private static final UUID GAMBIT_UUID = UUID.fromString("4422ab44-5bb5-ba66-777d-aa88aa8cc8aa");


    public Gambit() {
        super(MobEffectCategory.BENEFICIAL, 0xFF0000);

        this.addAttributeModifier(ModAttributes.MELEE_ACCURACY.get(),
                GAMBIT_UUID.toString(),
                0.2D,
                AttributeModifier.Operation.MULTIPLY_BASE);

        this.addAttributeModifier(ModAttributes.PROJECTILE_ACCURACY.get(),
                GAMBIT_UUID.toString(),
                0.2D,
                AttributeModifier.Operation.MULTIPLY_BASE);

        this.addAttributeModifier(ModAttributes.ACCURACY.get(),
                GAMBIT_UUID.toString(),
                0.2D,
                AttributeModifier.Operation.MULTIPLY_BASE);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}
