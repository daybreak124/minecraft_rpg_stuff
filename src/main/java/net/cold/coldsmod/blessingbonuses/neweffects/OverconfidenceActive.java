package net.cold.coldsmod.blessingbonuses.neweffects;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
        if (event.getEntity().level().isClientSide()) return;
        Player player = event.getEntity();

        if (!player.hasEffect(ModEffects.OVERCONFIDENCE_READY.get()))
            return;

        if (player instanceof ServerPlayer serverPlayer) {serverPlayer.playNotifySound(SoundEvents.ARMOR_EQUIP_CHAIN, SoundSource.PLAYERS, 4F, 1.0F);}

        player.removeEffect(ModEffects.OVERCONFIDENCE_READY.get());
        player.addEffect(new MobEffectInstance(ModEffects.BLACKENED_HEART.get(), 20 * 15, 0, false, false, true));
        player.addEffect(new MobEffectInstance(ModEffects.OVERCONFIDENCE_ACTIVE.get(), 20 * 8, 0, false, false, true));
    }
}
