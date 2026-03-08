package net.cold.coldsmod.stat;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;

public class FormulasHelpers {



    public static boolean rollCrit(Player player, double chance) {
        return player.getRandom().nextDouble() < (chance + 10.0) / 100.0;
    }

    public static void playCritSound(Player player) {
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.PLAYER_ATTACK_CRIT, SoundSource.PLAYERS, 1.0F, 1.0F);
    }





}
