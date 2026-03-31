package net.cold.coldsmod.capabilities_and_blessings.effects;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static net.cold.coldsmod.stat.AttributeApplier.applyPercentModifierAdditive;
import static net.cold.coldsmod.stat.AttributeApplier.removeModifier;

public class DirectedHatredBlinded extends MobEffect {

    private static final UUID HATRED_UUID = UUID.fromString("f3e2b310-1738-5123-ab23-024331050446");

    public DirectedHatredBlinded() {
        super(MobEffectCategory.HARMFUL, 0xFF0000);

        this.addAttributeModifier(ModAttributes.INCOMING_DAMAGE_MULTIPLIER.get(),
                HATRED_UUID.toString(),
                0.06D,
                AttributeModifier.Operation.MULTIPLY_BASE);
    }
}