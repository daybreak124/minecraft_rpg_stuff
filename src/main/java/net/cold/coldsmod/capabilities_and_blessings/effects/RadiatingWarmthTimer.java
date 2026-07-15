package net.cold.coldsmod.capabilities_and_blessings.effects;

import net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils;
import net.cold.coldsmod.capabilities_and_blessings.registry.ModEffects;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Collections;
import java.util.List;

import static net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils.isAlly;
import static net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils.spawnParticleRing;
import static net.cold.coldsmod.stat.AttributeApplier.getScaledValue;

public class RadiatingWarmthTimer extends MobEffect {
    public RadiatingWarmthTimer() {
        super(MobEffectCategory.NEUTRAL, 0x00000);
    }

    public static void radiate(Player player) {
        double healIncrease = getScaledValue(player,
                ModAttributes.RESTORATION.get());

        Level level = player.level();

        if (level instanceof ServerLevel serverLevel) {
            spawnParticleRing(serverLevel, player, ParticleTypes.COMPOSTER, 8, 100);
        }

        double radiusSq = 25.0;
        List<LivingEntity> entities = level.getEntitiesOfClass(
                LivingEntity.class,
                player.getBoundingBox().inflate(5.0),
                e -> {
                    if (!isAlly(e) || !player.hasLineOfSight(e)) return false;
                    double dx = e.getX() - player.getX();
                    double dz = e.getZ() - player.getZ();
                    return (dx * dx + dz * dz) <= radiusSq;
                }
        );

        // player.heal((float) (1.25 * (1.0 + (healIncrease / 100.0))));

        // EffectUtils.spawnComposterBurst(player);

        for (LivingEntity target : entities) {

            target.heal((float) (1.25 * (1.0 + (healIncrease / 100.0))));
            EffectUtils.playHealSound(target);
            EffectUtils.spawnComposterBurst(target);
        }

        int cd = (int) ((400) / (1.0 + (AttributeApplier.getScaledValue(player, ModAttributes.AMPLIFICATION.get()) / 100.0)));
        player.addEffect(new MobEffectInstance(ModEffects.RADIATING_WARMTH.get(), cd, 0, false, false, true));

    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}