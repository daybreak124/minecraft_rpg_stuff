package net.cold.coldsmod.gearbonuses.skills;

import net.cold.coldsmod.ModSounds;
import net.cold.coldsmod.gearbonuses.effects.ModEffects;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BastionActive extends MobEffect {
    public BastionActive() {
        super(MobEffectCategory.NEUTRAL, 0xFF0000); // category + color
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false; // manually handle tick
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (player.level().isClientSide) return;
        if (event.phase != TickEvent.Phase.END) return;
        if (!player.getPersistentData().getBoolean("bastion_applied")) return;
        if (!player.hasEffect(ModEffects.BASTION_READY.get())) return;

        boolean isBlocking = player.isBlocking();
        boolean wasBlocking = player.getPersistentData().getBoolean("wasBlocking");
        player.getPersistentData().putBoolean("wasBlocking", isBlocking);

        if (isBlocking || !wasBlocking) return;

        int fort = player.getPersistentData().getInt("totalFort");
        int perc = player.getPersistentData().getInt("totalPerc");
        int con = player.getPersistentData().getInt("totalCon");

        double seconds = 0.75 + 0.0075 * fort + 0.005 * perc + 0.0033 * con;
        int ticks = (int)(seconds * 20);

        player.addEffect(new MobEffectInstance(ModEffects.BASTION_ACTIVE.get(), ticks, 0, false, false));
        player.removeEffect(ModEffects.BASTION_READY.get());

        player.level().playSound(
                null,                                  // Player = global sound
                player.getX(), player.getY(), player.getZ(),
                ModSounds.GUARDIAN_ANGEL.get(),        // Your sound event
                SoundSource.PLAYERS,                   // Category
                0.6F,                                  // Volume
                1.0F                                   // Pitch
        );
    }
}
