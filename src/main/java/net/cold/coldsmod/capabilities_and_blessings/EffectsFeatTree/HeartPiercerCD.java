package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;

public class HeartPiercerCD extends MobEffect {

    public HeartPiercerCD() {
        super(MobEffectCategory.NEUTRAL, 0x000000);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}
