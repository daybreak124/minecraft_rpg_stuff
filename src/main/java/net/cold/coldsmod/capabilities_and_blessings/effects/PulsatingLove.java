package net.cold.coldsmod.capabilities_and_blessings.effects;

import net.cold.coldsmod.mob.Sbeve;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class PulsatingLove extends MobEffect {
    public PulsatingLove() {
        super(MobEffectCategory.NEUTRAL, 0x800080);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 200 == 0;
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide()) applyTamedAura(entity);
    }

    private static void applyTamedAura(LivingEntity player) {
        Level level = player.level();
        AABB area = player.getBoundingBox().inflate(4);

        List<TamableAnimal> animals = level.getEntitiesOfClass(
                TamableAnimal.class,
                area,
                t -> t.isTame() &&
                        player.getUUID().equals(t.getOwnerUUID()) &&
                        (!(t instanceof Sbeve))
        );

        for (TamableAnimal animal : animals) {
            animal.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 1, true, true));
            animal.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 600, 1, true, true));
        }
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}