package net.cold.coldsmod.gearbonuses.neweffects;

import net.cold.coldsmod.gearbonuses.effects.ModEffects;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OverconfidenceActive extends MobEffect {

    public OverconfidenceActive() {
        super(MobEffectCategory.NEUTRAL, 0xFF0000); // category + color
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false; // only trigger via events
    }

    @SubscribeEvent
    public static void onCritHit(CriticalHitEvent event) {
        if (!event.isVanillaCritical()) return;

        Player player = event.getEntity();
        if (player.level().isClientSide) return;
        if (!player.hasEffect(ModEffects.OVERCONFIDENCE_READY.get()) || player.hasEffect(ModEffects.OVERCONFIDENCE_COOLDOWN.get()))
            return;

        player.playSound(SoundEvents.ARMOR_EQUIP_CHAIN, 0.4F, 1.0F);

        player.removeEffect(ModEffects.OVERCONFIDENCE_READY.get());
        player.addEffect(new MobEffectInstance(ModEffects.BlACKENED_HEART.get(), 20 * 22, 0, false, false, true));
        player.addEffect(new MobEffectInstance(ModEffects.OVERCONFIDENCE_ACTIVE.get(), 20 * 8, 0, false, false, true));
    }
}
