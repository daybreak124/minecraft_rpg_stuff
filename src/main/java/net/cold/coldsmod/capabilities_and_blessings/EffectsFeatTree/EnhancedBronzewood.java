package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.cold.coldsmod.damage_types.ModDamageTypes;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EnhancedBronzewood extends MobEffect {

    private static final UUID EB_UUID = UUID.fromString("4422baa4-55c5-6666-7dd7-aa8ffcccb888");

    public EnhancedBronzewood() {
        super(MobEffectCategory.HARMFUL, 0x800080);

        this.addAttributeModifier(ModAttributes.OUTGOING_DAMAGE_MULTIPLIER.get(),
                EB_UUID.toString(),
                0.05d,
                AttributeModifier.Operation.MULTIPLY_BASE);

        this.addAttributeModifier(ModAttributes.INCOMING_DAMAGE_MULTIPLIER.get(),
                EB_UUID.toString(),
                0.03d,
                AttributeModifier.Operation.MULTIPLY_BASE);
    }
}