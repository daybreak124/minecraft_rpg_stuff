package net.cold.coldsmod.blessingbonuses.feat_tree;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class HelpingHand extends MobEffect {

    public HelpingHand() {
        super(MobEffectCategory.NEUTRAL, 0xFFA500); // category + color
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false; // only trigger via events
    }
}
