package net.cold.coldsmod.damage;

import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.player.Player;

public class CustomMeleeDamageNoProcs extends DamageSource {
    public CustomMeleeDamageNoProcs(Holder<DamageType> type, Player player) {
        super(type, player, player);
    }

    public boolean isCustomMeleeDamageNoProcs() {
        return true;
    }

    @Override
    public String getMsgId() {
        return "melee_curse";
    }
}
