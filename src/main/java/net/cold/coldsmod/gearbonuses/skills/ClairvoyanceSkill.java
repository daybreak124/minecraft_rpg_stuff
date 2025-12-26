package net.cold.coldsmod.gearbonuses.skills;

import net.cold.coldsmod.ModSounds;
import net.cold.coldsmod.gearbonuses.effects.ModEffects;
import net.cold.coldsmod.stat.ItemRarityUtils;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClairvoyanceSkill {

    private static final int CHARGE_TICKS_REQUIRED = 20 * 3;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (player.level().isClientSide) return;

        if (!player.hasEffect(ModEffects.CLAIRVOYANCE_READY.get())) return;

        ItemStack stack = player.getUseItem();
        if (stack.isEmpty()) return;

        String type = ItemRarityUtils.getItemType(stack);
        if (!"bow".equals(type)) return;

        if (!player.getPersistentData().getBoolean("clairvoyance_applied")) return;

        double drawSpeed = player.getPersistentData().getDouble("drawSpeedIncrease");
        double chargeReductionMultiplier = 1 - (drawSpeed / 100.0);
        int adjustedChargeTicks = (int) (CHARGE_TICKS_REQUIRED * chargeReductionMultiplier);

        int chargeTime = player.getTicksUsingItem();

        boolean soundPlayed = player.getPersistentData().getBoolean("clairvoyance_sound_played");

        if (chargeTime >= adjustedChargeTicks && !soundPlayed) {
            player.getPersistentData().putBoolean("Clairvoyance", true);

            // Play sound once
            player.level().playSound(
                    null,
                    player.getX(), player.getY(), player.getZ(),
                    ModSounds.CLAIRVOYANCE.get(),
                    SoundSource.PLAYERS,
                    1.2F,
                    1.0F
            );

            player.getPersistentData().putBoolean("clairvoyance_sound_played", true);
        }

        // So on misses and cancels, it plays again
        if (chargeTime == 0) {
            player.getPersistentData().putBoolean("clairvoyance_sound_played", false);
        }
    }
}
