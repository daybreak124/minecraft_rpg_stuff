package net.cold.coldsmod.blessingbonuses.neweffects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class RadiatingWarmthEffect extends MobEffect {

    public RadiatingWarmthEffect() {
        super(MobEffectCategory.NEUTRAL, 0xFF0000); // category + color
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false;
    }
}
