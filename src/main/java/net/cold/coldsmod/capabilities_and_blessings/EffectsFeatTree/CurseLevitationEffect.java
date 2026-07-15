package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class CurseLevitationEffect extends MobEffect {
    public CurseLevitationEffect() {
        super(MobEffectCategory.HARMFUL, 0x4B0082);
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap map, int amplifier) {
        super.removeAttributeModifiers(entity, map, amplifier);
        entity.setDeltaMovement(entity.getDeltaMovement().x, -3.0D, entity.getDeltaMovement().z);
        entity.hurtMarked = true;
        entity.fallDistance += 7.5f;
    }
}
