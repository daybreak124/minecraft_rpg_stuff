package net.cold.coldsmod.blessingbonuses.neweffects;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.mob.Sbeve;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class PulsatingLove {

    private static final int TICK_INTERVAL = 20 * 5; // 5 seconds
    private static final int EFFECT_DURATION = 20 * 30; // 20 seconds
    private static final double RADIUS = 10.0;
    private static final String AURA_TICK = "tamed_aura_tick";


    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;
        if (player.level().isClientSide) return;

        if (player.hasEffect(ModEffects.SOLARA.get())) return;

        if (player.getPersistentData().getBoolean("pulsating_love_eligible")) {

            CompoundTag tag = player.getPersistentData();
            int ticks = tag.getInt(AURA_TICK) + 1;
            tag.putInt(AURA_TICK, ticks);

            if (ticks >= TICK_INTERVAL) {
                tag.putInt(AURA_TICK, 0);
                applyTamedAura(player);
            }
        }
    }

    private static void applyTamedAura(Player player) {
        Level level = player.level();

        AABB area = player.getBoundingBox().inflate(RADIUS);

        List<TamableAnimal> animals = level.getEntitiesOfClass(
                TamableAnimal.class,
                area,
                t -> t.isTame() && (!(t instanceof Sbeve))
        );

        for (TamableAnimal animal : animals) {

            animal.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, EFFECT_DURATION, 4,true, true));
            animal.addEffect(new MobEffectInstance(MobEffects.REGENERATION, EFFECT_DURATION, 5, true, true));
        }
    }



}
