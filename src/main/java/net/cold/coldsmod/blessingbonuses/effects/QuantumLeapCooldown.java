package net.cold.coldsmod.blessingbonuses.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class QuantumLeapCooldown extends MobEffect {
    public QuantumLeapCooldown() {
        super(MobEffectCategory.NEUTRAL, 0xFFD700); // gold color
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false; // Tick every game tick
    }
}
