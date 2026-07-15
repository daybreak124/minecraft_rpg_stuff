package net.cold.coldsmod.capabilities_and_blessings.Capabilities;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

@FunctionalInterface
public interface IBonusLogic {
    void execute(Player player, @Nullable LivingEntity victim, Level level, float[] data, PlayerBonusCache cache);
}