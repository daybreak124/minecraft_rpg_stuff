package net.cold.coldsmod.blessingbonuses.effects;

import net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils;
import net.cold.coldsmod.damage.ModDamageTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

import static net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils.spawnParticleBurst;
import static net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils.spawnParticleRing;

public class SoulSeveranceActive extends MobEffect {

    public SoulSeveranceActive() {
        super(MobEffectCategory.NEUTRAL, 0xFFD700); // gold color
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (event.player.level().isClientSide()) return;

        if (!event.player.hasEffect(ModEffects.SOUL_SEVERANCE_READY.get())) return;

        Player player = event.player;
        Level level = player.level();

        int ticks = player.getPersistentData().getInt("pull_ticks");

        if (player.isCrouching()) {
            double range = 6.0;
            double pullStrength = 0.05;

            double radiusSq = 36.0;
            List<LivingEntity> nearby = player.level().getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(range),
                    e -> {
                        if (e instanceof Player || !player.hasLineOfSight(e)) return false;
                        double dx = e.getX() - player.getX();
                        double dz = e.getZ() - player.getZ();
                        return (dx * dx + dz * dz) <= radiusSq;
                    }
            );

            if (ticks%20 == 0) {
                EffectUtils.playSound(player, SoundEvents.SOUL_ESCAPE, 7.0F, 1.0F);
                spawnParticleRing((ServerLevel) level, player, ParticleTypes.SOUL_FIRE_FLAME, 6.0, 120);
            }

            for (LivingEntity mob : nearby) {
                double dx = player.getX() - mob.getX();
                double dy = player.getY() - mob.getY();
                double dz = player.getZ() - mob.getZ();
                double distance = Math.sqrt(dx*dx + dy*dy + dz*dz);
                if (distance < 0.1) continue;

                mob.setDeltaMovement(
                        mob.getDeltaMovement().add(
                                dx / distance * pullStrength,
                                dy / distance * pullStrength,
                                dz / distance * pullStrength
                        )
                );

                Holder<DamageType> meleeType = level.registryAccess()
                        .registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(ModDamageTypes.CUSTOM_MELEE_DAMAGE);

                DamageSource source = new DamageSource(meleeType, player);

                if (ticks % 20 == 0) {
                    if (mob instanceof Enemy) {
                        mob.hurtMarked = true;
                        mob.hurt(source, 4.0f);
                        spawnParticleBurst(mob, ParticleTypes.SOUL);
                    }
                }
            }

            ticks++;
            player.getPersistentData().putInt("pull_ticks", ticks);
            player.addEffect(new MobEffectInstance(ModEffects.SOUL_SEVERANCE_ACTIVE.get()));

            if (ticks >= 80) {
                player.removeEffect(ModEffects.SOUL_SEVERANCE_READY.get());
                player.addEffect(new MobEffectInstance(ModEffects.SOUL_SEVERANCE_COOLDOWN.get(), 20*9, 0, false, false, true));
                player.getPersistentData().remove("pull_ticks");
            }
        } else if (ticks > 0) {
            player.removeEffect(ModEffects.SOUL_SEVERANCE_READY.get());
            player.addEffect(new MobEffectInstance(ModEffects.SOUL_SEVERANCE_COOLDOWN.get(), 20*9, 0, false, false, true));
            player.getPersistentData().remove("pull_ticks");
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false; // Tick every game tick
    }
}
