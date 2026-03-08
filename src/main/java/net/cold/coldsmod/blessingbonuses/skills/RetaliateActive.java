package net.cold.coldsmod.blessingbonuses.skills;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.common.MinecraftForge;


public class RetaliateActive extends MobEffect {

    public RetaliateActive() {
        super(MobEffectCategory.NEUTRAL, 0xFFD700);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false;
    }
}
