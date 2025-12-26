package net.cold.coldsmod.gearbonuses.skills;

import net.cold.coldsmod.gearbonuses.effects.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BerserkAdditionalProcs {

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (player.level().isClientSide) return; // server only

        if (!player.getPersistentData().getBoolean("berserk_applied")) return;

        LivingEntity dead = event.getEntity();
        if (dead instanceof Player) return;

        player.removeEffect(ModEffects.BERSERK_READY.get());
        player.addEffect(new MobEffectInstance(ModEffects.BERSERK_READY.get(), 20*6, 0, false, false));
        player.removeEffect(ModEffects.BERSERK.get());
        player.getPersistentData().putInt("berserk", 0);
    }
}
