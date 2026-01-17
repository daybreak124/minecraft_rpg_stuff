package net.cold.coldsmod.damage;

import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.player.Player;

public class CustomRangedDamage extends DamageSource {
    public CustomRangedDamage(Holder<DamageType> type, Player player) {
        super(type, player, player);
    }

    public boolean isCustomRanged() {
        return true;
    }

    @Override
    public String getMsgId() {
        return "ranged_damage";
    }
}
