package net.cold.coldsmod.capabilities_and_blessings.Capabilities;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BonusRegistry {
    public static final List<BonusEntry> ALL_BONUSES = new ArrayList<>();

    public static int register(BonusTrigger trigger, IBonusLogic logic) {
        int id = ALL_BONUSES.size();
        ALL_BONUSES.add(new BonusEntry(id, trigger, logic));
        return id;
    }

    public static void process(Player player, @Nullable LivingEntity victim, Level level, BonusTrigger trigger, float[] data) {
        player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
            List<PlayerBonusCache.ProcInstance> procs = cache.get(trigger);
            for (PlayerBonusCache.ProcInstance proc : procs) {
                proc.logic().execute(player, victim, level, data);
            }
        });
    }

    public static void process(Player player, LivingEntity victim, Level level, BonusTrigger trigger) {
        process(player, victim, level, trigger, new float[0]);
    }
}