package net.cold.coldsmod.blessingbonuses.skills;

import net.cold.coldsmod.ModSounds;
import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

import static net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils.spawnParticleRing;

public class DaringShoutSkill {

    private static final int COOLDOWN_TICKS = 20 * 15;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (event.player.level().isClientSide()) return;
        if (!event.player.hasEffect(ModEffects.DARING_SHOUT.get())) return;

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

        double radiusSq = 25.0;
        List<LivingEntity> entities = level.getEntitiesOfClass(
                LivingEntity.class,
                player.getBoundingBox().inflate(5),
                e -> {
                    if (!(e instanceof Enemy) || !e.isAlive() || e.isInvulnerable() || !player.hasLineOfSight(e)) return false;
                    double dx = e.getX() - player.getX();
                    double dz = e.getZ() - player.getZ();
                    return (dx * dx + dz * dz) <= radiusSq;
                }
        );

        for (LivingEntity entity : entities) {
            int finalDuration = entity.getType().is(Tags.EntityTypes.BOSSES) || entity instanceof Warden
                    ? durationTicks / 3
                    : durationTicks;

            if (entity instanceof Mob mob) {
                mob.setNoAi(true);
                mob.getPersistentData().putInt("freeze_timer", finalDuration);
                EffectUtils.spawnParticleBurst(entity, ParticleTypes.ASH);
            }
        }

        spawnParticleRing((ServerLevel) level, player, ParticleTypes.LARGE_SMOKE, 5.0, 100);
        EffectUtils.spawnParticleBurst(player, ParticleTypes.LARGE_SMOKE);
    }
}
