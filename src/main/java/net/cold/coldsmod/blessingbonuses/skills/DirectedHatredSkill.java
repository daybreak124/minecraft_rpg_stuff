package net.cold.coldsmod.blessingbonuses.skills;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
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

public class DirectedHatredSkill {

    @SubscribeEvent
    public static void onCritHit(CriticalHitEvent event) {
        if (!event.isVanillaCritical()) return;
        if (!event.getEntity().hasEffect(ModEffects.DIRECTED_HATRED_READY.get())) return;
        if (event.getEntity().level().isClientSide()) return;

        Player player = event.getEntity();

        double range = 10.0;

        List<LivingEntity> nearby = player.level().getEntitiesOfClass(
                LivingEntity.class,
                player.getBoundingBox().inflate(range),
                e -> e instanceof Enemy
        );

        for (LivingEntity entity : nearby) {
            if (entity instanceof Mob mob) {
                mob.setTarget(player);
            }

        player.level().playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.ANVIL_PLACE,
                SoundSource.PLAYERS,
                0.35F,
                1.0F
        );

        player.removeEffect(ModEffects.DIRECTED_HATRED_READY.get());
        player.addEffect(new MobEffectInstance(ModEffects.DIRECTED_HATRED_COOLDOWN.get(), 20 * 10, 0, false, false, true));
        }
    }
}
