package net.cold.coldsmod.mob;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import static net.cold.coldsmod.stat.AttributeApplier.getScaledValue;

public class Sbeve extends TamableAnimal {

    private boolean scaled = false;

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 40.0)
                .add(Attributes.ATTACK_DAMAGE, 1.5)
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.FOLLOW_RANGE, 32.0);
    }

    public Sbeve(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SbeveAttackGoal(this, 1.1, true));

        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.0, 5f, 2f, true));

        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));

        this.targetSelector.addGoal(3,
                new NearestAttackableTargetGoal<>(
                        this,
                        Monster.class,
                        20,
                        true,
                        false,
                        e -> e instanceof Enemy && this.distanceToSqr(e) <= 100.0
                )
        );
    }

    @Override
    public double getMeleeAttackRangeSqr(LivingEntity target) {
        // default: (this.getBbWidth() * 2.0F * this.getBbWidth() * 2.0F + target.getBbWidth())
        float extraReach = 1.5F;
        return this.getBbWidth() * 2.0F * this.getBbWidth() * 2.0F + target.getBbWidth() + extraReach;
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        if (!(target instanceof LivingEntity living)) return false;
        if (target instanceof Player) return false;

        Player owner = (Player) this.getOwner();
        if (owner == null) return false;

        double scaledPotency = getScaledValue(owner,
                ModAttributes.POTENCY.get(),
                ModAttributes.POTENCY_MULTIPLIER.get());
        double damageIncrease = 1.0 + (scaledPotency / 100.0);

        float baseDamage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);

        float finalDamage = baseDamage * (float) damageIncrease;

        finalDamage = applyCrit(owner, finalDamage);

        if (living instanceof Creeper creeper) {
            applyGigaKnockback(creeper);
            creeper.setSwellDir(-1);
        }

        target.playSound(SoundEvents.BEEHIVE_DRIP, 15F, 1F);
        return living.hurt(damageSources().mobAttack(this), finalDamage);
    }

    private float applyCrit(Player owner, float damage) {
        double scaledAccuracy = getScaledValue(owner,
                ModAttributes.ACCURACY.get(),
                ModAttributes.ACCURACY_MULTIPLIER.get());

        double critChance = scaledAccuracy + 10.0;

        double scaledPrecision = getScaledValue(owner,
                ModAttributes.PRECISION.get(),
                ModAttributes.PRECISION_MULTIPLIER.get());


        if (owner.getRandom().nextDouble() < critChance) {
            double critMultiplier = 1.5 + scaledPrecision / 100.0;
            return (float) (damage * critMultiplier);
        }
        return damage;
    }


    public void applyOwnerScaling(Player owner) {
        double guardianHp = owner.getMaxHealth() * 2;

        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(guardianHp);
        this.setHealth((float) guardianHp);

        double playerArmor = owner.getAttributeValue(Attributes.ARMOR);
        this.getAttribute(Attributes.ARMOR).setBaseValue(playerArmor);

        double playerToughness = owner.getAttributeValue(Attributes.ARMOR_TOUGHNESS);
        this.getAttribute(Attributes.ARMOR_TOUGHNESS).setBaseValue(playerToughness);
    }

    private static final double TP_DISTANCE_SQR = 30 * 30;

    private void applyGigaKnockback(LivingEntity target) {
        Vec3 dir = target.position().subtract(this.position()).normalize();

        double horizontal = 15;
        double vertical = 0.6;

        target.push(dir.x * horizontal, vertical, dir.z * horizontal);
        target.hurtMarked = true;
    }


    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide) return;

        Player owner = (Player) this.getOwner();
        if (owner == null) return;

        if (!scaled) {
            applyOwnerScaling(owner);
            this.refreshDimensions();
            scaled = true;
        }

        if (this.distanceToSqr(owner) > TP_DISTANCE_SQR) {
            this.teleportTo(
                    owner.getX(),
                    owner.getY(),
                    owner.getZ()
            );
        }
    }


    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return null;
    }

    @Override
    public float getScale() {
        return 1f;
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return super.getDimensions(pose).scale(0.55f);
    }

    private static class SbeveAttackGoal extends MeleeAttackGoal {
        private int customCooldown = 0;
        private final Sbeve sbeve;

        public SbeveAttackGoal(Sbeve mob, double speed, boolean followingTargetEvenIfNotSeen) {
            super(mob, speed, followingTargetEvenIfNotSeen);
            this.sbeve = mob;
        }

        @Override
        public void tick() {
            super.tick();

            LivingEntity target = this.mob.getTarget();
            if (target != null) {
                this.mob.getLookControl().setLookAt(target, 30.0F, 30.0F);

                double distSq = this.mob.distanceToSqr(target.getX(), target.getY(), target.getZ());

                if (this.customCooldown > 0) {
                    this.customCooldown--;
                }
                this.checkAndPerformCustomAttack(target, distSq);
            }
        }

        @Override
        protected double getAttackReachSqr(LivingEntity attackTarget) {
            return this.sbeve.getMeleeAttackRangeSqr(attackTarget);
        }

        protected void checkAndPerformCustomAttack(LivingEntity enemy, double distToEnemySqr) {
            double reach = this.getAttackReachSqr(enemy);

            if (distToEnemySqr <= reach && this.customCooldown <= 0) {
                if (this.sbeve.getOwner() instanceof Player owner) {

                    double scaledHaste = getScaledValue(owner,
                            ModAttributes.HASTE.get(),
                            ModAttributes.HASTE_MULTIPLIER.get());

                    double cooldownBase = 30.0;
                    double divisor = 1.0 + (scaledHaste / 100.0);

                    this.customCooldown = (int) Math.max(4, cooldownBase / divisor);
                } else {
                    this.customCooldown = 40;
                }

                this.mob.swing(net.minecraft.world.InteractionHand.MAIN_HAND);
                this.mob.doHurtTarget(enemy);
            }
        }
    }
}
