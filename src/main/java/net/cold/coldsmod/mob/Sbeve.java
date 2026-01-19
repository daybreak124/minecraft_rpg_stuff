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
                .add(Attributes.ATTACK_DAMAGE, 2.5)
                .add(Attributes.MOVEMENT_SPEED, 0.30)
                .add(Attributes.FOLLOW_RANGE, 32.0);
    }

    public Sbeve(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.5, true));

        this.goalSelector.addGoal(3,
                new FollowOwnerGoal(this, 1.1, 5f, 2f, true)
        );

        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));

        this.targetSelector.addGoal(3,
                new NearestAttackableTargetGoal<>(
                        this,
                        Monster.class,
                        true,
                        e -> !(e instanceof Player || e instanceof TamableAnimal t && t.isTame())
                )
        );
    }

    @Override
    public double getMeleeAttackRangeSqr(LivingEntity target) {
        // default: (this.getBbWidth() * 2.0F * this.getBbWidth() * 2.0F + target.getBbWidth())
        float extraReach = 1.0F;
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
        }

        this.playSound(SoundEvents.BEEHIVE_DRIP, 1.5F, 1F);


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
        double guardianHp = owner.getMaxHealth() * 3;

        this.getAttribute(Attributes.MAX_HEALTH)
                .setBaseValue(guardianHp);

        this.setHealth((float) guardianHp);
    }

    private static final double TP_DISTANCE_SQR = 30 * 30;

    private void applyGigaKnockback(LivingEntity target) {
        Vec3 dir = target.position().subtract(this.position()).normalize();

        double horizontal = 2.5;
        double vertical = 0.6;

        target.push(
                dir.x * horizontal,
                vertical,
                dir.z * horizontal
        );

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
        return 0.55f;
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return super.getDimensions(pose).scale(0.55f);
    }
}
