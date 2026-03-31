package net.cold.coldsmod.capabilities_and_blessings.effects;

import net.cold.coldsmod.capabilities_and_blessings.registry.ModEffects;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class IntoTheFrayActive extends MobEffect {
    public IntoTheFrayActive() {
        super(MobEffectCategory.BENEFICIAL, 0x000000);

        this.addAttributeModifier(Attributes.MOVEMENT_SPEED,
                ITF_UUID.toString(), 0.008D, AttributeModifier.Operation.ADDITION);
    }

    public static final UUID ITF_UUID = UUID.fromString("d3e2b3c2-1738-5823-ab23-0b23310a214c");

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 40 == 0;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level().isClientSide()) return;
        if (entity.isSprinting()) {
            int newAmplifier = Math.min(4, amplifier + 1);
            entity.addEffect(new MobEffectInstance(ModEffects.INTO_THE_FRAY_ACTIVE.get(), MobEffectInstance.INFINITE_DURATION, newAmplifier, false, false, true));
            entity.addEffect(new MobEffectInstance(ModEffects.INTO_THE_FRAY_COLLISION_CHECK.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, false));
            if (amplifier >= 4) entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 41, 0, false, false, false));
        } else {
            entity.addEffect(new MobEffectInstance(ModEffects.INTO_THE_FRAY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, false));
            entity.removeEffect(this);
            entity.removeEffect(ModEffects.INTO_THE_FRAY_COLLISION_CHECK.get());
        }
    }
}
