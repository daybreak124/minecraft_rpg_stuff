package net.cold.coldsmod.blessingbonuses.skills;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils;
import net.cold.coldsmod.damage.ModDamageTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

import static net.cold.coldsmod.blessingbonuses.CooldownCycle.FRAY_SPEED_UUID;
import static net.cold.coldsmod.stat.AttributeApplier.removeModifier;

public class IntoTheFraySkill {

    private static final double DAMAGE_PER_STACK = 2.5; // Example value, adjust as needed
    private static final String SPRINT_TICKS_KEY = "sprintTicks";
    private static final String ELIGIBLE_KEY = "into_the_fray_eligible";
    private static final ResourceKey<DamageType> MELEE_DAMAGE_KEY =
            ResourceKey.create(Registries.DAMAGE_TYPE, ModDamageTypes.CUSTOM_MELEE_DAMAGE.location());

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.player.level().isClientSide()) return;

        Player player = event.player;
        CompoundTag data = player.getPersistentData();

        if (!data.getBoolean(ELIGIBLE_KEY)) return;

        if (!player.isSprinting() || player.hasEffect(ModEffects.INTO_THE_FRAY_COOLDOWN.get())) {
            resetFray(player, data);
            return;
        }

        int sprintTicks = data.getInt(SPRINT_TICKS_KEY) + 1;
        data.putInt(SPRINT_TICKS_KEY, sprintTicks);

        if (sprintTicks < 60) return;


        int amplifier = Math.min((sprintTicks - 60) / 40, 4);
        int stackCount = amplifier + 1;

        updateSprintingBuffs(player, amplifier, sprintTicks >= 220);

        // 7. Collision & Explosion Logic
        checkCollisions(player, data, stackCount);
    }

    private static void updateSprintingBuffs(Player player, int amplifier, boolean giveAbsorption) {
        MobEffectInstance current = player.getEffect(ModEffects.INTO_THE_FRAY.get());
        if (current == null || current.getAmplifier() != amplifier) {
            player.addEffect(new MobEffectInstance(ModEffects.INTO_THE_FRAY.get(), 40, amplifier, true, false, true));
        }

        if (giveAbsorption) {
            if (!player.hasEffect(MobEffects.ABSORPTION)) {
                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 40, 0, true, false, true));
            }
        }
    }

    private static void checkCollisions(Player player, CompoundTag data, int stackCount) {
        Level level = player.level();
        double collisionRadiusSq = 0.49;

        List<LivingEntity> nearby = level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(4.0));
        boolean hasCollided = false;

        for (LivingEntity entity : nearby) {
            if (entity == player || !isValidTarget(player, entity)) continue;

            if (entity.distanceToSqr(player) <= collisionRadiusSq) {
                hasCollided = true;
                break;
            }
        }

        if (hasCollided) {
            triggerExplosion(player, nearby, stackCount);
            resetFray(player, data);

            player.addEffect(new MobEffectInstance(ModEffects.INTO_THE_FRAY_COOLDOWN.get(), 180, 0));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 1));
        }
    }

    private static void triggerExplosion(Player player, List<LivingEntity> nearby, int stackCount) {
        Level level = player.level();
        Holder<DamageType> meleeType = level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(MELEE_DAMAGE_KEY);
        DamageSource source = new DamageSource(meleeType, player);


        for (LivingEntity target : nearby) {
            if (target == player || !isValidTarget(player, target)) continue;

            double distSq = target.distanceToSqr(player);
            if (distSq <= 16.0 && player.hasLineOfSight(target)) {
                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10 * stackCount, 3));

                target.hurt(source, (float) (DAMAGE_PER_STACK * stackCount));

                // Knockback
                double dx = target.getX() - player.getX();
                double dz = target.getZ() - player.getZ();
                target.knockback(0.4f * stackCount, dx, dz);
            }
        }

        if (level instanceof ServerLevel serverLevel) {
            EffectUtils.spawnExplosionEffect(player);
            EffectUtils.spawnParticleRing(serverLevel, player, ParticleTypes.POOF, 4.0, 60);
            EffectUtils.playExplosionSound(player, 0.7F);
        }
    }

    private static boolean isValidTarget(Player player, LivingEntity target) {
        if (!target.isAlive() || target.isInvulnerable()) return false;

        return (target instanceof Enemy && !(target instanceof NeutralMob)) ||
                (target instanceof NeutralMob n && n.isAngry()) ||
                (target instanceof Mob m && m.getTarget() != null);
    }

    private static void resetFray(Player player, CompoundTag data) {
        if (data.getInt(SPRINT_TICKS_KEY) > 0) {
            data.putInt(SPRINT_TICKS_KEY, 0);
        }

        if (player.hasEffect(ModEffects.INTO_THE_FRAY.get())) {
            player.removeEffect(ModEffects.INTO_THE_FRAY.get());
        }

        player.removeEffect(MobEffects.ABSORPTION);
        removeModifier(player, Attributes.MOVEMENT_SPEED, FRAY_SPEED_UUID);
    }
}