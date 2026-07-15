package net.cold.coldsmod.custom_attacks;

import net.cold.coldsmod.stat.AttributeApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public abstract class CustomAttack {

    // MOVED TO EVENTS/FORMULAS

    public final UUID CUSTOM_ATTACK_UUID = UUID.fromString("ccb2c3d4-e5f6-4a5b-8c9d-0e1f2a3b215d");


    public int priority;
    public double cooldownMultiplier;
    public double damageMultiplier;
    public double rangeMultiplier;


    public CustomAttack(double cooldownMultiplier, double damageMultiplier, int priority, double rangeMultiplier) {
        this.cooldownMultiplier = cooldownMultiplier;
        this.damageMultiplier = damageMultiplier;
        this.priority = priority;
        this.rangeMultiplier = rangeMultiplier;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setCooldownMultiplier(double cooldownMultiplier) {
        this.cooldownMultiplier = cooldownMultiplier;
    }

    public double getCooldownMultiplier() {
        return cooldownMultiplier;
    }

    public void setDamageMultiplier(double damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }

    public double getDamageMultiplier() {
        return damageMultiplier;
    }

    public void setRangeMultiplier(double rangeMultiplier) {
        this.rangeMultiplier = rangeMultiplier;
    }

    public double getRangeMultiplier() {
        return rangeMultiplier;
    }

    public abstract void executeClient(Player player, LivingEntity target);

    public void executeServer(Player player, LivingEntity target) {
        AttributeApplier.removeModifier(player, Attributes.ATTACK_SPEED, CUSTOM_ATTACK_UUID);

    }
}
