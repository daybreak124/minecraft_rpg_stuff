package net.cold.coldsmod.capabilities_and_blessings.effects;

import net.cold.coldsmod.capabilities_and_blessings.registry.ModEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class HookOfTheDepth extends MobEffect {
    public HookOfTheDepth() {
        super(MobEffectCategory.NEUTRAL, 0x800080);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }


    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level().isClientSide) return;

        CompoundTag data = entity.getPersistentData();

        if (entity.horizontalCollision || (entity.verticalCollision && entity.getDeltaMovement().y > 0) || entity.isCrouching()) {
            entity.removeEffect(ModEffects.DEPTHS_CURRENT.get());
            data.remove("last_y");
            entity.hurt(entity.damageSources().magic(), 6f);
            return;
        }

        Vec3 target = new Vec3(
                data.getDouble("hook_x"),
                data.getDouble("hook_y"),
                data.getDouble("hook_z")
        );

        double currentY = entity.getY();
        double lastY = data.contains("last_y") ? data.getDouble("last_y") : currentY;

        if (currentY < lastY) {
            return;
        } else {
            data.putDouble("last_y", currentY);
        }

        double distance = entity.position().distanceTo(target);
        if (distance < 1.4 || currentY >= target.y) {
            entity.removeEffect(ModEffects.DEPTHS_CURRENT.get());
            data.remove("last_y");
            entity.hurt(entity.damageSources().magic(), 6f);

            entity.push(0, 0.1, 0);
            return;
        }

        Vec3 direction = target.subtract(entity.position()).normalize();
        double speed = 0.5;

        double moveY = Math.max(0.1, direction.y * speed) + 0.15;

        entity.setDeltaMovement(
                direction.x * speed,
                moveY,
                direction.z * speed
        );

        entity.hurtMarked = true;
        entity.fallDistance = 0;
    }
}