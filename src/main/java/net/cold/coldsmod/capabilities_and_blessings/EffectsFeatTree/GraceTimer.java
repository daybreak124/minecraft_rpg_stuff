package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.cold.coldsmod.capabilities_and_blessings.registry.ModEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import static net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils.isAlly;

public class GraceTimer  extends MobEffect {
    public GraceTimer() {
        super(MobEffectCategory.NEUTRAL, 0x00000);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 200 == 0;
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide()) grace((Player) entity);
    }

    public static void grace(Player player) {

        Level level = player.level();

        double radiusSq = 5d;
        List<LivingEntity> entities = level.getEntitiesOfClass(
                LivingEntity.class,
                player.getBoundingBox().inflate(25d),
                e -> {
                    if (!isAlly(e) || !player.hasLineOfSight(e)) return false;
                    double dx = e.getX() - player.getX();
                    double dz = e.getZ() - player.getZ();
                    return (dx * dx + dz * dz) <= radiusSq;
                }
        );

        if (player.getHealth() <= player.getMaxHealth() * 0.8) {
            for (LivingEntity target : entities) {
                target.addEffect(new MobEffectInstance(ModEffects.GRACE_EVASION.get(), 200, 0, false, false, false));
            }
        } else {
            for (LivingEntity target : entities) {
                target.addEffect(new MobEffectInstance(ModEffects.GRACE_DAMAGE.get(), 200, 0, false, false, false));
            }
        }


    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}