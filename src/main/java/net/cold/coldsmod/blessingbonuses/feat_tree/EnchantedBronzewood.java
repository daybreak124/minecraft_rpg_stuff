package net.cold.coldsmod.blessingbonuses.feat_tree;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class EnchantedBronzewood extends MobEffect {

    public EnchantedBronzewood() {
        super(MobEffectCategory.HARMFUL, 0x000000);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false; // only trigger via events
    }
}
