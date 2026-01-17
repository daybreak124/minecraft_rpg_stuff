package net.cold.coldsmod.gearbonuses.neweffects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class BlackenedHeart extends MobEffect {

    public BlackenedHeart() {
        super(MobEffectCategory.NEUTRAL, 0x000000); // category + color
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false; // only trigger via events
    }
}
