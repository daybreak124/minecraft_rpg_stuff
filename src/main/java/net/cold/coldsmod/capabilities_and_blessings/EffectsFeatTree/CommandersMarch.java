package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CommandersMarch extends MobEffect {

    private static final UUID COMMANDER_UUID = UUID.fromString("a422a1a4-5ac5-6666-7777-aa8ddb8cc888");

    public CommandersMarch() {
        super(MobEffectCategory.BENEFICIAL, 0x000000);

        this.addAttributeModifier(Attributes.MOVEMENT_SPEED,
                COMMANDER_UUID.toString(),
                0.013D,
                AttributeModifier.Operation.ADDITION);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}
