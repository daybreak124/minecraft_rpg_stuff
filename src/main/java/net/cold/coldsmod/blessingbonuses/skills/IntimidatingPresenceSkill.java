package net.cold.coldsmod.blessingbonuses.skills;

import net.cold.coldsmod.ModSounds;
import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

import static net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils.spawnParticleRing;

public class IntimidatingPresenceSkill {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (event.player.level().isClientSide()) return;

        Player player = event.player;

        int crouchTicks = player.getPersistentData().getInt("crouchTicks");

        if (player.isCrouching() && player.hasEffect(ModEffects.INTIMIDATING_PRESENCE.get())) {
            crouchTicks++;
            player.getPersistentData().putInt("crouchTicks", crouchTicks);

            if (crouchTicks >= 20) {
                player.addEffect(new MobEffectInstance(ModEffects.INTIMIDATING_PRESENCE_COOLDOWN.get(), 20 * 15, 0, false, false, true));
                player.getPersistentData().putInt("crouchTicks", 0);
                player.removeEffect(ModEffects.INTIMIDATING_PRESENCE.get());
                player.level().playSound(
                        null, player.getX(), player.getY(), player.getZ(),
                        ModSounds.INTIMIDATING_PRESENCE.get(), SoundSource.PLAYERS,
                        0.6F, 1.0F
                );
                applyIntimidated(player);
            }
        } else {
            player.getPersistentData().putInt("crouchTicks", 0);
        }
    }

    private static void applyIntimidated(Player player) {
        if (player.level().isClientSide()) return;

        Level level = player.level();

        double str = player.getAttributeValue(ModAttributes.STR.get());
        double con = player.getAttributeValue(ModAttributes.CON.get());

        double debuffPercent = 20.0 + str * 0.1 + con * 0.05;
        int amplifier = (int)Math.min(255, Math.floor(debuffPercent));

        double radiusSq = 100.0;
        List<LivingEntity> entities = level.getEntitiesOfClass(
                LivingEntity.class,
                player.getBoundingBox().inflate(10),
                e -> {
                    if (e instanceof Player || !e.isAlive() || e.isInvulnerable() || !player.hasLineOfSight(e)) return false;
                    double dx = e.getX() - player.getX();
                    double dz = e.getZ() - player.getZ();
                    return (dx * dx + dz * dz) <= radiusSq;
                }
        );

        for (LivingEntity target : entities) {
            target.addEffect(new MobEffectInstance(ModEffects.INTIMIDATED.get(), 8 * 20, amplifier - 1, true, true, true));
            EffectUtils.spawnParticleBurst(target, ParticleTypes.ENCHANTED_HIT);
        }

        spawnParticleRing((ServerLevel) level, player, ParticleTypes.CRIT, 10.0, 200);
        EffectUtils.spawnParticleBurst(player, ParticleTypes.CRIT);
    }
}
