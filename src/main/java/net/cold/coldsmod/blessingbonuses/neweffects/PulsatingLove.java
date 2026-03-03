package net.cold.coldsmod.blessingbonuses.neweffects;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.mob.Sbeve;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PulsatingLove {

    private static final int TICK_INTERVAL = 200;
    private static final int EFFECT_DURATION = 20 * 30;
    private static final double RADIUS = 10.0;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.player.level().isClientSide()) return;

        Player player = event.player;

        if (!player.getPersistentData().getBoolean("pulsating_love_eligible")) return;
        if (player.hasEffect(ModEffects.SOLARA.get())) return;

        int playerOffset = Math.abs(player.getUUID().hashCode() % TICK_INTERVAL);
        if ((player.tickCount + playerOffset) % TICK_INTERVAL == 0) {
            applyTamedAura(player);
        }
    }

    private static void applyTamedAura(Player player) {
        Level level = player.level();
        AABB area = player.getBoundingBox().inflate(RADIUS);

        List<TamableAnimal> animals = level.getEntitiesOfClass(
                TamableAnimal.class,
                area,
                t -> t.isTame() &&
                        player.getUUID().equals(t.getOwnerUUID()) &&
                        (!(t instanceof Sbeve))
        );

        for (TamableAnimal animal : animals) {
            animal.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, EFFECT_DURATION, 1, true, true));
            animal.addEffect(new MobEffectInstance(MobEffects.REGENERATION, EFFECT_DURATION, 1, true, true));
        }
    }
}