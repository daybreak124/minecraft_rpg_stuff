package net.cold.coldsmod.gearbonuses.skills;

import net.cold.coldsmod.gearbonuses.effects.ModEffects;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class DirectedHatredSkill {

    @SubscribeEvent
    public static void onCritHit(CriticalHitEvent event) {
        if (!event.isVanillaCritical()) return;

        Player player = event.getEntity();
        if (!player.getPersistentData().getBoolean("directed_hatred_eligible")) return;
        if (!player.hasEffect(ModEffects.DIRECTED_HATRED_READY.get()) || player.hasEffect(ModEffects.RECKONING_COOLDOWN.get()))
            return;
        if (player.level().isClientSide) return;
        double range = 10.0;

        List<LivingEntity> nearby = player.level().getEntitiesOfClass(
                LivingEntity.class,
                player.getBoundingBox().inflate(range),
                e -> e instanceof Monster
        );

        for (LivingEntity entity : nearby) {
            Monster monster = (Monster) entity;
            monster.setTarget(player);
            monster.addEffect(new MobEffectInstance(ModEffects.BLINDED_BY_HATRED.get(), 20 * 6, 0, false, true));
        }

        player.level().playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.ANVIL_PLACE,
                SoundSource.PLAYERS,
                0.4F,
                1.0F
        );

        player.removeEffect(ModEffects.DIRECTED_HATRED_READY.get());
        player.addEffect(new MobEffectInstance(ModEffects.DIRECTED_HATRED_COOLDOWN.get(), 20 * 10, 0, false, false));
    }
}
