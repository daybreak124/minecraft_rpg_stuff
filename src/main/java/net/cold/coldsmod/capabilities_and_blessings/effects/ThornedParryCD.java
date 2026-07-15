package net.cold.coldsmod.capabilities_and_blessings.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;

public class ThornedParryCD extends MobEffect {
    public ThornedParryCD() {
        super(MobEffectCategory.NEUTRAL, 0x00000);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}