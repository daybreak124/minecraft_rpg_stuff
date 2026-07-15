package net.cold.coldsmod.capabilities_and_blessings.effects;

import net.cold.coldsmod.capabilities_and_blessings.registry.ModEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;

public class IntoTheFray extends MobEffect {
    public IntoTheFray() {
        super(MobEffectCategory.NEUTRAL, 0x00000);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 60 == 0;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level().isClientSide()) return;
        if (entity.isSprinting()) {
            entity.addEffect(new MobEffectInstance(ModEffects.INTO_THE_FRAY_ACTIVE.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
            entity.addEffect(new MobEffectInstance(ModEffects.INTO_THE_FRAY_COLLISION_CHECK.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
            entity.removeEffect(ModEffects.INTO_THE_FRAY.get());
        }
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}