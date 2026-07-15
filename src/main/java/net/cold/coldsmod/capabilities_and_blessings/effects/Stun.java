package net.cold.coldsmod.capabilities_and_blessings.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class Stun extends MobEffect {
    public Stun() {
        super(MobEffectCategory.NEUTRAL, 0x00000);
    }

    @Override
    public void addAttributeModifiers(@NotNull LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        if (pLivingEntity instanceof Mob mob && !(mob.getType().is(Tags.EntityTypes.BOSSES) || mob instanceof Warden)) {
            mob.setNoAi(true);
        }
    }

    @Override
    public void removeAttributeModifiers(@NotNull LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        if (pLivingEntity instanceof Mob mob && !(mob.getType().is(Tags.EntityTypes.BOSSES) || mob instanceof Warden)) {
            mob.setNoAi(false);
        }
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}