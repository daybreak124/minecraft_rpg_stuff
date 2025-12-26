package net.cold.coldsmod.gearbonuses.skills;

import net.cold.coldsmod.ModSounds;
import net.cold.coldsmod.gearbonuses.effects.ModEffects;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class IntimidatingPresenceSkill {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (player.level().isClientSide) return;
        if (!player.getPersistentData().getBoolean("intimidating_presence_eligible")) return;

        int crouchTicks = player.getPersistentData().getInt("crouchTicks");

        if (player.isCrouching() && player.hasEffect(ModEffects.INTIMIDATING_PRESENCE.get())) {
            crouchTicks++;
            player.getPersistentData().putInt("crouchTicks", crouchTicks);

            if (crouchTicks >= 20) {
                player.addEffect(new MobEffectInstance(ModEffects.INTIMIDATING_PRESENCE_COOLDOWN.get(), 20 * 15, 0, false, false));
                player.getPersistentData().putInt("crouchTicks", 0);
                player.removeEffect(ModEffects.INTIMIDATING_PRESENCE.get());
                player.level().playSound(
                        null,
                        player.getX(), player.getY(), player.getZ(),
                        ModSounds.INTIMIDATING_PRESENCE.get(),
                        SoundSource.PLAYERS,
                        0.6F,
                        1.0F
                );
                applyIntimidated(player);
            }
        } else {
            player.getPersistentData().putInt("crouchTicks", 0);
        }
    }

    private static void applyIntimidated(Player player) {
        Level level = player.level();

        double str = player.getPersistentData().getDouble("totalStr");
        double con = player.getPersistentData().getDouble("totalCon");

        double debuffPercent = 20.0 + str * 0.1 + con * 0.05; // e.g., 10 STR, 0 CON = 21%
        int amplifier = (int)Math.min(255, Math.floor(debuffPercent));

        List<LivingEntity> entities = level.getEntitiesOfClass(
                LivingEntity.class,
                player.getBoundingBox().inflate(10), // 10 block radius
                e -> !(e instanceof Player) && e.isAlive() && !e.isInvulnerable()
        );

        for (LivingEntity target : entities) {
            target.addEffect(new MobEffectInstance(
                    ModEffects.INTIMIDATED.get(),
                    8 * 20,
                    amplifier - 1,
                    false,
                    true
            ));
        }
    }
}
