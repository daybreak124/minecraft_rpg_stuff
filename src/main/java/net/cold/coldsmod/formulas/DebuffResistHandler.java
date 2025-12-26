package net.cold.coldsmod.formulas;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


// I dont even know
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DebuffResistHandler {

    private static final String SEP = ":";

    // per-player map: player UUID -> (effectKey -> ReducedInfo)
    private static final ConcurrentHashMap<UUID, ConcurrentHashMap<String, ReducedInfo>> REDUCED = new ConcurrentHashMap<>();

    // small holder for what we reduced to and when
    private static class ReducedInfo {
        int remainingAfterReduction; // ticks remaining we set when we reduced
        long tickWhenReduced;        // world game time when we reduced

        ReducedInfo(int remainingAfterReduction, long tickWhenReduced) {
            this.remainingAfterReduction = remainingAfterReduction;
            this.tickWhenReduced = tickWhenReduced;
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent event) {
        // run once per tick, at END phase
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;
        Level world = player.getCommandSenderWorld();
        if (world.isClientSide) return; // server only

        double debuffResist = Math.min(player.getPersistentData().getDouble("totalDebuffResist"), 100);
        if (debuffResist <= 0) {
            REDUCED.remove(player.getUUID()); // cleanup if player has no resist
            return;
        }

        UUID pu = player.getUUID();
        ConcurrentHashMap<String, ReducedInfo> playerMap = REDUCED.computeIfAbsent(pu, k -> new ConcurrentHashMap<>());

        // collect currently-present keys for cleanup
        Set<String> currentKeys = new HashSet<>();

        // iterate over a copy (safe when we remove/add effects below)
        for (MobEffectInstance inst : new ArrayList<>(player.getActiveEffects())) {
            MobEffectCategory category = inst.getEffect().getCategory();
            if (category == MobEffectCategory.BENEFICIAL || category == MobEffectCategory.NEUTRAL) continue;


            ResourceLocation id = ForgeRegistries.MOB_EFFECTS.getKey(inst.getEffect());
            if (id == null) continue;
            String key = id.toString() + SEP + inst.getAmplifier();
            currentKeys.add(key);

            int currentDuration = inst.getDuration();

            ReducedInfo prev = playerMap.get(key);

            // compute desired one-time reduced duration
            int reduced = (int) Math.max(1, Math.round(currentDuration * (1.0 - debuffResist / 100.0)));

            if (prev != null) {
                // If currentDuration <= prev.remainingAfterReduction => same instance we already handled
                if (currentDuration <= prev.remainingAfterReduction) {
                    continue; // same instance, skip
                }

                // Otherwise currentDuration > prev.remainingAfterReduction -> likely a fresh reapplication
                // Extra check: if tick advanced since prev reduction, it's probably a new application too.
                long now = world.getGameTime();
                boolean isNewApplication = currentDuration > prev.remainingAfterReduction
                        || (now - prev.tickWhenReduced) > 1L;

                if (!isNewApplication) {
                    continue;
                }

                // If reduced would not actually shorten, just update stored info and skip
                if (reduced >= currentDuration) {
                    playerMap.put(key, new ReducedInfo(currentDuration, world.getGameTime()));
                    continue;
                }

                // Mark BEFORE reapply so we don't process the new instance in the same tick
                playerMap.put(key, new ReducedInfo(reduced, world.getGameTime()));

                // Reapply shortened effect (we are on server thread in PlayerTickEvent)
                player.removeEffect(inst.getEffect());
                player.addEffect(new MobEffectInstance(
                        inst.getEffect(),
                        reduced,
                        inst.getAmplifier(),
                        inst.isAmbient(),
                        inst.isVisible(),
                        inst.showIcon()
                ));
            } else {
                // first time seeing this effect for this player (no previous reduced info)
                if (reduced >= currentDuration) {
                    // nothing to shorten — do not mark so future reapplication can be reduced
                    continue;
                }

                // mark and reapply
                playerMap.put(key, new ReducedInfo(reduced, world.getGameTime()));
                player.removeEffect(inst.getEffect());
                player.addEffect(new MobEffectInstance(
                        inst.getEffect(),
                        reduced,
                        inst.getAmplifier(),
                        inst.isAmbient(),
                        inst.isVisible(),
                        inst.showIcon()
                ));
            }
        }

        // cleanup: remove entries for effects no longer present
        Iterator<Map.Entry<String, ReducedInfo>> it = playerMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, ReducedInfo> e = it.next();
            if (!currentKeys.contains(e.getKey())) {
                it.remove();
            }
        }

        // final cleanup: remove player map if empty
        if (playerMap.isEmpty()) REDUCED.remove(pu);
    }
}
