package net.cold.coldsmod.blessingbonuses.skills;

import net.cold.coldsmod.ModSounds;
import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class DaringShoutSkill {

    private static final int COOLDOWN_TICKS = 20 * 15;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (!event.player.hasEffect(ModEffects.DARING_SHOUT.get())) return;
        if (event.player.level().isClientSide()) return;

        Player player = event.player;

        int crouchTicks = player.getPersistentData().getInt("daringShoutCrouchTicks");

        if (player.isCrouching()) {
            crouchTicks++;
            player.getPersistentData().putInt("daringShoutCrouchTicks", crouchTicks);

            if (crouchTicks >= 1) {
                player.addEffect(new MobEffectInstance(ModEffects.DARING_SHOUT_COOLDOWN.get(), COOLDOWN_TICKS, 0, true, true, true));
                player.getPersistentData().putInt("daringShoutCrouchTicks", 0);

                applyNoAi(player);
                player.removeEffect(ModEffects.DARING_SHOUT.get());

                EffectUtils.playSound(player, ModSounds.DARING_SHOUT.get(), 0.6F, 1.0F);
            }
        } else {
            player.getPersistentData().putInt("daringShoutCrouchTicks", 0);
        }
    }

    private static void applyNoAi(Player player) {
        Level level = player.level();

        double fort = player.getAttributeValue(ModAttributes.FORT.get());
        double perc = player.getAttributeValue(ModAttributes.PERC.get());

        double durationMultiplier = 1 + (fort * 0.02 + perc * 0.01);
        int durationTicks = (int) (3 * 20 * durationMultiplier);

        List<Monster> entities = level.getEntitiesOfClass(
                Monster.class,
                player.getBoundingBox().inflate(5),
                e -> e.isAlive() && !e.isInvulnerable()
        );

        for (Monster target : entities) {
            int finalDuration = target.getType().is(Tags.EntityTypes.BOSSES)
                    ? durationTicks / 3
                    : durationTicks;

            target.setNoAi(true);
            target.getPersistentData().putInt("freeze_timer", finalDuration);
        }
    }
}
