package net.cold.coldsmod.blessingbonuses.neweffects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class EnhancedPoison extends MobEffect {

    public EnhancedPoison() {
        super(MobEffectCategory.HARMFUL, 0x4E9331); // category + color
    }

    @Override
    public void applyEffectTick(LivingEntity target, int amplifier) {
        float totalDamage = 1.0f + (float) amplifier;
        target.hurt(target.level().damageSources().magic(), totalDamage);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }
}
