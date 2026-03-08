package net.cold.coldsmod.capabilities;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SkillCapability {
    private final Set<String> unlockedSkillIds = new HashSet<>();
    private boolean isDirty = true;

    // Cache Buckets
    public final List<ISkillEffect> universalCache = new ArrayList<>();
    public final List<ISkillEffect> meleeCache = new ArrayList<>();
    public final List<ISkillEffect> directMeleeCache = new ArrayList<>();
    public final List<ISkillEffect> projectileBowCache = new ArrayList<>();
    public final List<ISkillEffect> projectileCrossbowCache = new ArrayList<>();
    public final List<ISkillEffect> dotCache = new ArrayList<>();
    public final List<ISkillEffect> damageTakenCache = new ArrayList<>();
    public final List<ISkillEffect> killCache = new ArrayList<>();
    public final List<ISkillEffect> blessingUseCache = new ArrayList<>();
    public final List<ISkillEffect> anyHitCache = new ArrayList<>();
    public final List<ISkillEffect> tickCache = new ArrayList<>();
    public final List<ISkillEffect> impactCache = new ArrayList<>();
    public final List<ISkillEffect> arrowJoinCache = new ArrayList<>();
    public final List<ISkillEffect> itemUseCache = new ArrayList<>();
    public final List<ISkillEffect> healCache = new ArrayList<>();
    public final List<ISkillEffect> debuffExpireCache = new ArrayList<>();

    public void unlockSkill(String id) {
        unlockedSkillIds.add(id);
        isDirty = true;
    }

    public void checkCache() {
        if (isDirty) rebuildCache();
    }

    public Set<String> getUnlockedSkillIds() {
        return unlockedSkillIds;
    }

    public boolean hasSkill(String skillId) {
        return unlockedSkillIds.contains(skillId);
    }

    private void rebuildCache() {
        // Clear all buckets
        universalCache.clear(); meleeCache.clear(); directMeleeCache.clear();
        projectileBowCache.clear(); projectileCrossbowCache.clear(); dotCache.clear();
        damageTakenCache.clear(); killCache.clear(); blessingUseCache.clear();
        anyHitCache.clear(); tickCache.clear(); impactCache.clear();
        arrowJoinCache.clear(); itemUseCache.clear(); healCache.clear();
        debuffExpireCache.clear();

        for (String id : unlockedSkillIds) {
            ISkillEffect s = ModSkills.REGISTRY.get(id);
            if (s == null) continue;

            // Combat Math
            if (hasMethod(s, "onUniversalDamage", Player.class, LivingEntity.class, float.class)) universalCache.add(s);
            if (hasMethod(s, "onMeleeHit", Player.class, LivingEntity.class, float.class)) meleeCache.add(s);
            if (hasMethod(s, "onDirectMeleeHit", Player.class, LivingEntity.class, float.class) ||
                    hasMethod(s, "onDirectMeleeHit", Player.class, LivingEntity.class) ||
                    hasMethod(s, "onDirectMeleeHit", Player.class, LivingEntity.class, double.class)) directMeleeCache.add(s);

            // Ranged
            if (hasMethod(s, "onProjectileHitBow", Player.class, LivingEntity.class, float.class) ||
                    hasMethod(s, "onProjectileHitBow", Player.class, LivingEntity.class)) projectileBowCache.add(s);
            if (hasMethod(s, "onProjectileHitCrossbow", Player.class, LivingEntity.class, float.class) ||
                    hasMethod(s, "onProjectileHitCrossbow", Player.class, LivingEntity.class)) projectileCrossbowCache.add(s);

            // Impact & Join
            if (hasMethod(s, "onProjectileImpact", Player.class, Projectile.class, Vec3.class) ||
                    hasMethod(s, "onProjectileImpact", Player.class, Projectile.class, HitResult.class)) impactCache.add(s);
            if (hasMethod(s, "onArrowJoin", Player.class, AbstractArrow.class)) arrowJoinCache.add(s);

            // Item Use
            if (hasMethod(s, "useItemEventTick", Player.class, LivingEntity.class, ItemStack.class) ||
                    hasMethod(s, "useItemEventStart", Player.class, LivingEntity.class, ItemStack.class) ||
                    hasMethod(s, "useItemEventEnd", Player.class, LivingEntity.class, ItemStack.class)) itemUseCache.add(s);

            // Ticks & Utility
            if (hasMethod(s, "onTick", Player.class, LivingEntity.class) ||
                    hasMethod(s, "onTick", Player.class, LivingEntity.class, int.class)) tickCache.add(s);
            if (hasMethod(s, "onHeal", Player.class, LivingEntity.class) ||
                    hasMethod(s, "onHeal", Player.class, LivingEntity.class, float.class)) healCache.add(s);
            if (hasMethod(s, "onDamageTaken", Player.class, LivingEntity.class, float.class) ||
                    hasMethod(s, "onDamageTaken", Player.class, LivingEntity.class)) damageTakenCache.add(s);

            // Lifecycle
            if (hasMethod(s, "onKill", Player.class, LivingEntity.class)) killCache.add(s);
            if (hasMethod(s, "onBlessingUse", Player.class, String.class)) blessingUseCache.add(s);
            if (hasMethod(s, "anyHit", Player.class, LivingEntity.class)) anyHitCache.add(s);
            if (hasMethod(s, "debuffExpire", Player.class, LivingEntity.class)) debuffExpireCache.add(s);
        }
        isDirty = false;
    }

    /**
     * Helper to verify if the skill implemented a method instead of using the interface default.
     */
    private boolean hasMethod(Object obj, String name, Class<?>... parameterTypes) {
        try {
            return obj.getClass().getMethod(name, parameterTypes).getDeclaringClass() != ISkillEffect.class;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }
}