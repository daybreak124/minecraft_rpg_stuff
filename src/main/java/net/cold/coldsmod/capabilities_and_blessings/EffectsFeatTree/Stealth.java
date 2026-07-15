package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Stealth extends MobEffect {

    private static final UUID STEALTH_UUID = UUID.fromString("a422a1a4-5ac5-6b66-7b77-ab8dbb8cc888");

    public Stealth() {
        super(MobEffectCategory.BENEFICIAL, 0x000000);

        this.addAttributeModifier(Attributes.MOVEMENT_SPEED,
                STEALTH_UUID.toString(),
                0.03D,
                AttributeModifier.Operation.ADDITION);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}
