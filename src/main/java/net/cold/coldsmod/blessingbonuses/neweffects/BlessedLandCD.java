package net.cold.coldsmod.blessingbonuses.neweffects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class BlessedLandCD extends MobEffect {

    public BlessedLandCD() {
        super(MobEffectCategory.NEUTRAL, 0x000000); // category + color
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false; // only trigger via events
    }
}
