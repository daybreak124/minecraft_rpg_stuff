package net.cold.coldsmod.blessingbonuses;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

import static net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils.spawnParticleRingLow;

public class JumpCritHandler {

    @SubscribeEvent
    public static void onCritHit(CriticalHitEvent event) {
        if (!event.isVanillaCritical()) return;
        if (event.getEntity().level().isClientSide()) return;

        Player player = event.getEntity();

        if (player.hasEffect(ModEffects.DIRECTED_HATRED_READY.get())) {
            double rangeSq = 100.0;
            List<LivingEntity> nearby = player.level().getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(10.0),
                    e -> {
                        if (!(e instanceof Enemy)) return false;
                        double dx = e.getX() - player.getX();
                        double dz = e.getZ() - player.getZ();
                        return (dx * dx + dz * dz) <= rangeSq;
                    }
            );

            for (LivingEntity entity : nearby) {
                if (entity instanceof Mob mob) {
                    mob.setTarget(player);
                    mob.addEffect(new MobEffectInstance(ModEffects.BLINDED_BY_HATRED.get(), 120, 0, false, true, false));
                }
            }

            player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ANVIL_PLACE, SoundSource.PLAYERS,
                    0.35F, 1.0F);

            if (player.level() instanceof ServerLevel serverLevel) {
                spawnParticleRingLow(serverLevel, player, ParticleTypes.ANGRY_VILLAGER, 6.0, 120);
            }
            EffectUtils.spawnParticleBurst(player, ParticleTypes.WITCH);

            player.removeEffect(ModEffects.DIRECTED_HATRED_READY.get());
            player.addEffect(new MobEffectInstance(ModEffects.DIRECTED_HATRED_COOLDOWN.get(), 20 * 10, 0, false, false, true));
        }

        if (player.hasEffect(ModEffects.OVERCONFIDENCE_READY.get())) {

            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.playNotifySound(
                        SoundEvents.ARMOR_EQUIP_CHAIN, SoundSource.PLAYERS,
                        4F, 1.0F);
            }

            player.removeEffect(ModEffects.OVERCONFIDENCE_READY.get());
            player.addEffect(new MobEffectInstance(ModEffects.BLACKENED_HEART.get(), 300, 0, false, false, true));
            player.addEffect(new MobEffectInstance(ModEffects.OVERCONFIDENCE_ACTIVE.get(), 160, 0, false, false, true));
        }
    }


}
