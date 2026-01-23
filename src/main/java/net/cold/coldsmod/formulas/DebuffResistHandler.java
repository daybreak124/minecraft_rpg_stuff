package net.cold.coldsmod.formulas;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DebuffResistHandler {

    // Use MobEffect directly as key to avoid String overhead
    private static final ConcurrentHashMap<UUID, Map<MobEffect, ReducedInfo>> REDUCED = new ConcurrentHashMap<>();

    private static class ReducedInfo {
        int lastDuration;
        int amplifier;

        ReducedInfo(int lastDuration, int amplifier) {
            this.lastDuration = lastDuration;
            this.amplifier = amplifier;
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent event) {
        // High-level gates (Keep these exactly as you had them)
        if (event.phase != TickEvent.Phase.END || event.player.level().isClientSide()) return;

        Player player = event.player;
        double resist = player.getAttributeValue(ModAttributes.DEBUFF_RESIST.get());

        // 1. Exit early if no resist is present
        if (resist <= 0) {
            if (!REDUCED.isEmpty()) REDUCED.remove(player.getUUID());
            return;
        }

        UUID pu = player.getUUID();
        Map<MobEffect, ReducedInfo> playerMap = REDUCED.computeIfAbsent(pu, k -> new HashMap<>());
        Collection<MobEffectInstance> activeEffects = player.getActiveEffects();

        // 2. Identify effects to modify (To avoid ConcurrentModificationException)
        List<MobEffectInstance> toReduce = new ArrayList<>();

        for (MobEffectInstance inst : activeEffects) {
            MobEffect effect = inst.getEffect();

            // Fast Category Check
            if (effect.getCategory() != MobEffectCategory.HARMFUL) continue;

            int currentDur = inst.getDuration();
            int currentAmp = inst.getAmplifier();
            ReducedInfo prev = playerMap.get(effect);

            // Logic: Reduce if it's a new effect OR the duration increased (re-applied) OR amplifier changed
            if (prev == null || currentDur > prev.lastDuration || currentAmp != prev.amplifier) {
                toReduce.add(inst);
            } else {
                // Just update the duration to track the countdown
                prev.lastDuration = currentDur;
            }
        }

        // 3. Apply Reductions
        if (!toReduce.isEmpty()) {
            double multiplier = 1.0 - (Math.min(resist, 100) / 100.0);

            for (MobEffectInstance inst : toReduce) {
                MobEffect effect = inst.getEffect();
                int reducedDur = (int) Math.max(1, Math.round(inst.getDuration() * multiplier));

                // Only re-apply if the reduction is significant
                if (reducedDur < inst.getDuration()) {
                    playerMap.put(effect, new ReducedInfo(reducedDur, inst.getAmplifier()));

                    // Note: removeEffect and addEffect trigger internal updates, so we do this last
                    player.removeEffect(effect);
                    player.addEffect(new MobEffectInstance(
                            effect, reducedDur, inst.getAmplifier(),
                            inst.isAmbient(), inst.isVisible(), inst.showIcon()
                    ));
                } else {
                    playerMap.put(effect, new ReducedInfo(inst.getDuration(), inst.getAmplifier()));
                }
            }
        }

        // 4. Efficient Cleanup
        if (playerMap.size() > activeEffects.size()) {
            playerMap.keySet().removeIf(effect -> !player.hasEffect(effect));
        }
    }
}
