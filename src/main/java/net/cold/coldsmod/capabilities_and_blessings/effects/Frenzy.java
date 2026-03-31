package net.cold.coldsmod.capabilities_and_blessings.effects;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static net.cold.coldsmod.stat.AttributeApplier.*;

public class Frenzy extends MobEffect {
    public static final UUID FRENZY_ATTACK_DAMAGE_UUID = UUID.fromString("f3e2b3c0-1728-5123-ab33-000008060446");

    public Frenzy() {
        super(MobEffectCategory.NEUTRAL, 0x000000);

        this.addAttributeModifier(Attributes.ATTACK_DAMAGE,
                FRENZY_ATTACK_DAMAGE_UUID.toString(), 0.1D, AttributeModifier.Operation.ADDITION);

        this.addAttributeModifier(ModAttributes.INCOMING_DAMAGE_MULTIPLIER.get(),
                FRENZY_ATTACK_DAMAGE_UUID.toString(), 0.01D, AttributeModifier.Operation.MULTIPLY_BASE);
    }
}