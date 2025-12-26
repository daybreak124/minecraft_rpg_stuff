package net.cold.coldsmod.gearbonuses.effects;

import net.cold.coldsmod.damage.CustomMeleeDamage;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class SoulSeveranceActive extends MobEffect {

    public SoulSeveranceActive() {
        super(MobEffectCategory.NEUTRAL, 0xFFD700); // gold color
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        if (player.level().isClientSide) return;
        if (!player.getPersistentData().getBoolean("soul_severance_eligible")) return;
        if (!player.hasEffect(ModEffects.SOUL_SEVERANCE_READY.get())) return;

        int ticks = player.getPersistentData().getInt("pull_ticks");

        if (player.isCrouching()) {
            double range = 6.0;
            double pullStrength = 0.05;

            List<LivingEntity> nearby = player.level().getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(range),
                    e -> !(e instanceof Player)
            );

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

                Holder<DamageType> meleeType = player.level().registryAccess()
                        .registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(DamageTypes.PLAYER_ATTACK);

                DamageSource source = new CustomMeleeDamage(meleeType, player);

                if (ticks % 20 == 0 && mob instanceof Monster) {
                    player.level().playSound(
                            null,
                            player.getX(), player.getY(), player.getZ(),
                            SoundEvents.SOUL_ESCAPE,
                            SoundSource.PLAYERS,
                            7F,
                            1.0F
                    );
                    mob.hurtMarked = true;
                    mob.hurt(source, 3.0f);
                }
            }

            ticks++;
            player.getPersistentData().putInt("pull_ticks", ticks);
            player.addEffect(new MobEffectInstance(ModEffects.SOUL_SEVERANCE_ACTIVE.get()));

            if (ticks >= 80) {
                player.removeEffect(ModEffects.SOUL_SEVERANCE_READY.get());
                player.addEffect(new MobEffectInstance(ModEffects.SOUL_SEVERANCE_COOLDOWN.get(), 20*9, 0, false, false));
                player.getPersistentData().remove("pull_ticks");
            }
        } else if (ticks > 0) {
            player.removeEffect(ModEffects.SOUL_SEVERANCE_READY.get());
            player.addEffect(new MobEffectInstance(ModEffects.SOUL_SEVERANCE_COOLDOWN.get(), 20*9, 0, false, false));
            player.getPersistentData().remove("pull_ticks");
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false; // Tick every game tick
    }
}
