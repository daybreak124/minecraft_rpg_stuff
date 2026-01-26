package net.cold.coldsmod.blessingbonuses.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class IntimidatedCooldown extends MobEffect {
    public IntimidatedCooldown() {
        super(MobEffectCategory.HARMFUL, 0xFFFFFF);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false; // Tick every game tick
    }
}
