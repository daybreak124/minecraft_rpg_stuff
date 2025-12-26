package net.cold.coldsmod.gearbonuses.skills;

import net.cold.coldsmod.ModSounds;
import net.cold.coldsmod.gearbonuses.effects.ModEffects;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class DaringShoutSkill {

    private static final int COOLDOWN_TICKS = 20 * 15;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (player.level().isClientSide) return;
        if (!player.hasEffect(ModEffects.DARING_SHOUT.get())) return;
        if (!player.getPersistentData().getBoolean("daring_shout_eligible")) return;

        int crouchTicks = player.getPersistentData().getInt("daringShoutCrouchTicks");

        if (player.isCrouching()) {
            crouchTicks++;
            player.getPersistentData().putInt("daringShoutCrouchTicks", crouchTicks);

            if (crouchTicks >= 1) {
                player.addEffect(new MobEffectInstance(ModEffects.DARING_SHOUT_COOLDOWN.get(), COOLDOWN_TICKS, 0, false, false));
                player.getPersistentData().putInt("daringShoutCrouchTicks", 0);

                applySlowness(player);
                player.removeEffect(ModEffects.DARING_SHOUT.get());

                player.level().playSound(
                        null,
                        player.getX(), player.getY(), player.getZ(),
                        ModSounds.DARING_SHOUT.get(),
                        SoundSource.PLAYERS,
                        0.6F,
                        1.0F
                );
            }
        } else {
            player.getPersistentData().putInt("daringShoutCrouchTicks", 0);
        }
    }

    private static void applySlowness(Player player) {
        Level level = player.level();

        double fort = player.getPersistentData().getDouble("totalFort");
        double perc = player.getPersistentData().getDouble("totalPerc");

        double durationMultiplier = 1 + (fort * 0.02 + perc * 0.01);
        int durationTicks = (int) (3 * 20 * durationMultiplier); // 3 seconds base

        List<LivingEntity> entities = level.getEntitiesOfClass(
                LivingEntity.class,
                player.getBoundingBox().inflate(5),
                e -> (e instanceof Monster) && e.isAlive() && !e.isInvulnerable()
        );

        for (LivingEntity target : entities) {
            target.addEffect(new MobEffectInstance(
                    MobEffects.MOVEMENT_SLOWDOWN,
                    durationTicks,
                    4,
                    false,
                    true
            ));
        }
    }
}
