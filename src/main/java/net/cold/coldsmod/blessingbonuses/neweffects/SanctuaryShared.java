package net.cold.coldsmod.blessingbonuses.neweffects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class SanctuaryShared extends MobEffect {

    public SanctuaryShared() {
        super(MobEffectCategory.NEUTRAL, 0xFF0000); // category + color
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false; // only trigger via events
    }
}
