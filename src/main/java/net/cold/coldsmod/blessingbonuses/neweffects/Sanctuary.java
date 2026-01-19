package net.cold.coldsmod.blessingbonuses.neweffects;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

import static net.cold.coldsmod.blessingbonuses.neweffects.CombatantsAidReady.isAlly;
import static net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils.spawnParticleRing;
import static net.cold.coldsmod.stat.AttributeApplier.getScaledValue;

public class Sanctuary {

    private static final int CHANNEL_DELAY = 20; // 1s
    private static final int PULSE_INTERVAL = 20; // 1s
    private static final float BASE_HEAL = 1.25f;
    private static final float FATIGUE_REDUCTION_PER_STACK = 0.10f;

    private static final String BLOCK_TICKS = "divinity_block_ticks";
    private static final String PULSE_TICKS = "divinity_pulse_ticks";


    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (event.player.level().isClientSide()) return;

        Player player = event.player;
        CompoundTag tag = player.getPersistentData();

        if (!tag.getBoolean("sanctuary_eligible")) return;

        if (!player.isBlocking()) {
            if (tag.getInt(BLOCK_TICKS) != 0) {
                tag.putInt(BLOCK_TICKS, 0);
                tag.putInt(PULSE_TICKS, 0);
            }
            return;
        }

        int blockTicks = tag.getInt(BLOCK_TICKS) + 1;
        tag.putInt(BLOCK_TICKS, blockTicks);

        if (blockTicks >= CHANNEL_DELAY) {
            int pulseTicks = tag.getInt(PULSE_TICKS) + 1;

            if (pulseTicks >= PULSE_INTERVAL) {
                performDivinityPulse(player);
                tag.putInt(PULSE_TICKS, 0);
            } else {
                tag.putInt(PULSE_TICKS, pulseTicks);
            }
        }
    }

    private static void performDivinityPulse(Player source) {
        Level level = source.level();
        double radius = 6.0 * (1.0 + (AttributeApplier.getScaledValue(source, ModAttributes.AMPLIFICATION.get(), ModAttributes.AMPLIFICATION_MULTIPLIER.get()) / 100.0));
        AABB area = source.getBoundingBox().inflate(radius);

        if (level instanceof ServerLevel serverLevel) {
            spawnParticleRing(serverLevel, source, ParticleTypes.COMPOSTER, radius, (int) (radius*20));
        }

        List<LivingEntity> allies = level.getEntitiesOfClass(
                LivingEntity.class,
                area,
                e -> isAlly(source, e) && e != source
        );

        EffectUtils.playHealSound(source);
        applyDivinityHealing(source, source);
        EffectUtils.spawnComposterBurst(source);

        for (LivingEntity ally : allies) {
            applyDivinityHealing(source, ally);
            EffectUtils.spawnComposterBurst((Player) allies);
        }
    }

    private static void applyDivinityHealing(Player source, LivingEntity target) {
        MobEffectInstance fatigue = target.getEffect(ModEffects.SANCTUARY_FATIGUE.get());
        int stacks = fatigue != null ? fatigue.getAmplifier() + 1 : 0;

        double healIncrease = getScaledValue(source,
                ModAttributes.RESTORATION.get(),
                ModAttributes.RESTORATION_MULTIPLIER.get());

        float reduction = stacks * FATIGUE_REDUCTION_PER_STACK;
        reduction = Math.min(reduction, 0.9f);
        float finalHeal = (float) (BASE_HEAL * ((1 + (healIncrease/100))) * (1f - reduction));

        target.heal(finalHeal);

        target.addEffect(new MobEffectInstance(ModEffects.SANCTUARY_FATIGUE.get(), 20 * 10, stacks, false, false, true));
        target.addEffect(new MobEffectInstance(ModEffects.SANCTUARY_SHARED.get(), 20 * 5, 0, false, false, true));
        source.addEffect(new MobEffectInstance(ModEffects.SANCTUARY_FATIGUE.get(), 20 * 10, stacks, false, false, true));
        source.addEffect(new MobEffectInstance(ModEffects.SANCTUARY_SHARED.get(), 20 * 5, 0, false, false, true));
    }
}
