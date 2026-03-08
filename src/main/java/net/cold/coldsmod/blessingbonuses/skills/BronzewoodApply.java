package net.cold.coldsmod.blessingbonuses.skills;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BronzewoodApply {

    public static final Map<LivingEntity, UUID> curseSources = new HashMap<>();

    public static Player getCurseSource(LivingEntity target) {
        UUID uuid = curseSources.get(target);
        if (uuid == null) return null;
        return target.level().getPlayerByUUID(uuid);
    }

//    public static void removeCurseSource(LivingEntity target) {
//        curseSources.remove(target);
//    }


}
