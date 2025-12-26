package net.cold.coldsmod.gearbonuses.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ClairvoyanceReady extends MobEffect {
    public ClairvoyanceReady() {
        super(MobEffectCategory.NEUTRAL, 0xADD8E6); // light blue color
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false; // Tick every game tick
    }
}
