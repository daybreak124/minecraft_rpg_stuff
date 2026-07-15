package net.cold.coldsmod.capabilities_and_blessings.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;

public class LifeTouchCooldown extends MobEffect {
    public LifeTouchCooldown() {
        super(MobEffectCategory.NEUTRAL, 0x00000);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}