package net.cold.coldsmod.capabilities;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public interface ISkillEffect {
    // Combat Bonuses (Return a float to add to damage)
    default float onUniversalDamage(Player player, LivingEntity mob, float damageBonus) { return 0; }
    default float onDirectMeleeHit(Player player, LivingEntity mob, float damageBonus) { return 0; }
    default void onDirectMeleeHit(Player player, LivingEntity mob) {}
    default void onDirectMeleeHit(Player player, LivingEntity mob, double damage) {}
    default float onMeleeHit(Player player, LivingEntity mob, float damageBonus) { return 0; }
    default float onProjectileHitCrossbow(Player player, LivingEntity mob, float damageBonus) { return 0; }
    default void onProjectileHitCrossbow(Player player, LivingEntity mob) {}
    default float onProjectileHitBow(Player player, LivingEntity mob, float damageBonus) { return 0; }
    default void onProjectileHitBow(Player player, LivingEntity mob) {}
    default float onDOTHiT(Player player, LivingEntity mob, float damageBonus) { return 0; }
    default void anyHit(Player player, LivingEntity mob) {}

    default void onTick(Player player, LivingEntity mob) {}

    default void onTick(Player player, LivingEntity mob, int interval) {}
    default void onProjectileImpactBow(Player player, Projectile projectile, Vec3 hitPos) {}
    default void onProjectileImpactCrossbow(Player player, Projectile projectile, HitResult hit) {}

    default void onArrowJoin(Player player, AbstractArrow arrow) {}

    default void useItemEventTick(Player player, LivingEntity mob, ItemStack item) {}
    default void useItemEventStart(Player player, LivingEntity mob, ItemStack item) {}
    default void useItemEventEnd(Player player, LivingEntity mob, ItemStack item) {}

    default void onHeal(Player player, LivingEntity target) {}
    default void onHeal(Player player, LivingEntity target, float amount) {}

    // Reactive Triggers (Void methods for effects/buffs)
    default void onDamageTaken(Player player, LivingEntity mob, float amount) {}
    default void onDamageTaken(Player player, LivingEntity mob) {}
    default void onKill(Player player, LivingEntity mob) {}
    default void onBlessingUse(Player player, String blessingId) {}

    default void enEffectExpired(Player player) {}

    String getName();
}