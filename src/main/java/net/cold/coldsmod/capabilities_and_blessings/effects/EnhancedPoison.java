package net.cold.coldsmod.capabilities_and_blessings.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;

public class EnhancedPoison extends MobEffect {

    public EnhancedPoison() {
        super(MobEffectCategory.HARMFUL, 0x87A363);
    }

    @Override
    public void applyEffectTick(LivingEntity target, int amplifier) {
        if (target.level().isClientSide()) return;
        float totalDamage = 0.625f + (float) amplifier/2;
        target.hurt(target.level().damageSources().magic(), totalDamage);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 10 == 0;
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}
