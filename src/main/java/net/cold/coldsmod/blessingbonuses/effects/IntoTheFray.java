package net.cold.coldsmod.blessingbonuses.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class IntoTheFray extends MobEffect {
    public IntoTheFray() {
        super(MobEffectCategory.NEUTRAL, 0xEFBF04);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false; // Tick every game tick
    }
}
