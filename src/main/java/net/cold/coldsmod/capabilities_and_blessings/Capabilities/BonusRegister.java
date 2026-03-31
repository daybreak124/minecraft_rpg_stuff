package net.cold.coldsmod.capabilities_and_blessings.Capabilities;

import net.cold.coldsmod.ModMessages;
import net.cold.coldsmod.ModSounds;
import net.cold.coldsmod.capabilities_and_blessings.effects.BronzewoodCurse;
import net.cold.coldsmod.capabilities_and_blessings.effects.VortexReady;
import net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils;
import net.cold.coldsmod.capabilities_and_blessings.registry.ModEffects;
import net.cold.coldsmod.damage_types.ModDamageTypes;
import net.cold.coldsmod.network.DfaAirborneSync;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;

import java.util.*;

import static net.cold.coldsmod.capabilities_and_blessings.effects.BlessedLandReady.spawnBlessedLand;
import static net.cold.coldsmod.capabilities_and_blessings.effects.Exploited.applyRandomDebuff;
import static net.cold.coldsmod.capabilities_and_blessings.effects.Exploited.applyRandomDebuffHalved;
import static net.cold.coldsmod.capabilities_and_blessings.effects.SanctuaryShared.performDivinityPulse;
import static net.cold.coldsmod.capabilities_and_blessings.effects.ThornedParryReady.triggerParryExplosion;
import static net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils.*;
import static net.cold.coldsmod.events.Formulas.rollCrit;
import static net.cold.coldsmod.network.SoulSeverancePacket.executeSS;
import static net.cold.coldsmod.stat.AttributeApplier.getScaledValue;

public class BonusRegister {

    public static int BRONZEWOODS_CURSE;
    public static int BRONZEWOODS_KILL;
    public static int BERSERK_HIT;
    public static int BERSERK_KILL;
    public static int BLESSED_LAND;
    public static int HAWKEYE_STACK;
    public static int HAWKEYE_CONSUME_BOW;
    public static int HAWKEYE_CONSUME_CROSSBOW;
    public static int CHAIN_LIGHTNING;
    public static int CUPID_ARROW;
    public static int EXPLOSIVE_TENDENCIES;
    public static int EXPLOIT_WEAKNESS;
    public static int VORTEX;
    public static int FOCUSED_ENERGY;
    public static int ENTWINED_OFFERING;
    public static int THORN_BLOCK;
    public static int THORN_RELEASE;
    public static int RETALIATE_ACTIVATE;
    public static int RETALIATE_HURT;
    public static int BASTION_ACTIVATE;
    public static int SANCTUARY;
    public static int CLAIRVOYANCE_START;
    public static int CLAIRVOYANCE_TICK;
    public static int CLAIRVOYANCE_END_RESET;
    public static int CLAIRVOYANCE_MISS;
    public static int CLAIRVOYANCE_HIT;
    public static int QUANTUM_LEAP_REMOVE;
    public static int DFA_LAND;
    public static int LIFE_TOUCH_IMPACT;
    public static int LIFE_TOUCH_FRIENDLY_FIRE;
    public static int NIMBLE_GETAWAY;

    public static int EXECUTIONER;
    public static int VANGUARDIAN;
    public static int SURGING_BLOOD_DIRECT;
    public static int SURGING_BLOOD_INDIRECT;
    public static int ENRAGE;
    public static int COMMANDER;
    public static int FRENZY;
    public static int COURAGEOUS_BLOW_DIRECT;
    public static int COURAGEOUS_BLOW_INDIRECT;
    public static int ARMOR_BREAK_DIRECT;
    public static int ARMOR_BREAK_INDIRECT;
    public static int REVENGEANCE;
    public static int ABSORBED_EVIL;
    public static int SOUL_SEPARATION_DIRECT;
    public static int SOUL_SEPARATION_INDIRECT;
    public static int EXECUTE;
    public static int VAMPIRIC_TOUCH_DIRECT;
    public static int VAMPIRIC_TOUCH_INDIRECT;
    public static int BLOODWORM;


    public static void init() {
        BRONZEWOODS_CURSE = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data) -> {
            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
            if (!cache.isBronzewoodReady()) return;


            Holder<DamageType> meleeType = level.registryAccess()
                    .registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(ModDamageTypes.CUSTOM_MELEE_DAMAGE);
            DamageSource source = new DamageSource(meleeType, null, player);

            victim.hurt(source, 3.0f);

            victim.addEffect(new MobEffectInstance(ModEffects.BRONZEWOOD_CURSE.get(), 200, 0, false, false, true));

            if (cache.isBronzewoodEnhanced()) {
                victim.addEffect(new MobEffectInstance(ModEffects.ENHANCED_BRONZEWOODS_CURSE.get(), 200, 0, false, false, true));
            }

            player.level().playSound(
                    null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.SOUL_ESCAPE, SoundSource.PLAYERS,
                    7F, 1.0F
            );
            spawnParticleBurst(victim, ParticleTypes.SCULK_SOUL);

            Map<UUID, Integer> victimSources = BronzewoodCurse.activeCurses.computeIfAbsent(victim.getUUID(),
                    k -> new HashMap<>()
            );

            victimSources.compute(player.getUUID(), (uuid, currentStacks) -> (currentStacks == null) ? 1 : currentStacks + 1
            );

            player.addEffect(new MobEffectInstance(ModEffects.BRONZEWOOD_COOLDOWN.get(), 300, 0, false, false, true));
            player.removeEffect(ModEffects.BRONZEWOOD_READY.get());
        });

        BRONZEWOODS_KILL = BonusRegistry.register(BonusTrigger.KILL, (player, victim, level, data) -> {
            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
            if (!cache.isBronzewoodReady()) {
                player.removeEffect(ModEffects.BRONZEWOOD_COOLDOWN.get());
                player.addEffect(new MobEffectInstance(ModEffects.BRONZEWOOD_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
            }
        });

        BERSERK_HIT = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data) -> {
            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
            if (cache.isBerserkEnraged()) {
                player.removeEffect(ModEffects.BERSERK_READY.get());

                float multiplier = 0.006f;

                if (cache.isBloodthirstEnhanced()) {
                    multiplier += 0.0015f;
                }

                double statValue = getScaledValue(player, ModAttributes.MELEE_POTENCY.get(), ModAttributes.MELEE_POTENCY_MULTIPLIER.get());
                data[0] += (float) (statValue * multiplier);

                spawnParticleBurst(player, ParticleTypes.SMALL_FLAME);

                if (player instanceof ServerPlayer serverPlayer) {
                    serverPlayer.playNotifySound(
                        SoundEvents.ZOMBIE_ATTACK_WOODEN_DOOR, SoundSource.PLAYERS,
                        0.3F, 1.0F
                    );
                }
            } else {
                if (cache.isBerserkStage0()) {
                    player.addEffect(new MobEffectInstance(ModEffects.BERSERK.get(), 80, 0, false, false, true));
                } else if (cache.isBerserkStage1()) {
                    player.removeEffect(ModEffects.BERSERK.get());
                    player.addEffect(new MobEffectInstance(ModEffects.BERSERK_READY.get(), 120, 0, false, false, true));
                }
            }
        });

        BERSERK_KILL = BonusRegistry.register(BonusTrigger.KILL, (player, victim, level, data) -> {
            player.removeEffect(ModEffects.BERSERK_READY.get());
            player.removeEffect(ModEffects.BERSERK.get());
            player.addEffect(new MobEffectInstance(ModEffects.BERSERK_READY.get(), 120, 0, false, false,true));
        });

        BLESSED_LAND = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data) -> {
            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
            if (!cache.isBlessedLandReady()) return;

            Vec3 targetPos = victim.position();

            double randomX = targetPos.x + (player.getRandom().nextDouble() * 10 - 5);
            double randomZ = targetPos.z + (player.getRandom().nextDouble() * 10 - 5);

            Vec3 finalSpawnPos = null;

            BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos(randomX, targetPos.y + 1, randomZ);

            for (int i = 0; i <= 3; i++) {
                BlockState stateAt = level.getBlockState(checkPos);
                BlockState stateBelow = level.getBlockState(checkPos.below());

                if (stateAt.canBeReplaced() && stateBelow.isFaceSturdy(level, checkPos.below(), Direction.UP)) {
                    finalSpawnPos = new Vec3(randomX, checkPos.getY() + 0.1, randomZ);
                    break;
                }
                checkPos.move(Direction.DOWN);
            }

            if (finalSpawnPos == null) {
                finalSpawnPos = targetPos.add(0, 0.1, 0);
            }

            spawnBlessedLand(player, finalSpawnPos);
            level.playSound(null, finalSpawnPos.x, finalSpawnPos.y, finalSpawnPos.z,
                    SoundEvents.BEACON_ACTIVATE, SoundSource.PLAYERS, 0.3F, 1.0F);

            double ampValue = getScaledValue(player, ModAttributes.AMPLIFICATION.get(), ModAttributes.AMPLIFICATION_MULTIPLIER.get());
            int cd = (int) (300 / (1.0 + (ampValue / 100.0)));
            player.removeEffect(ModEffects.BLESSED_LAND_READY.get());
            player.addEffect(new MobEffectInstance(ModEffects.BLESSED_LAND_CD.get(), cd, 0, false, false, true));
        });

        HAWKEYE_STACK = BonusRegistry.register(BonusTrigger.MELEE_CRIT, (player, victim, level, data) -> {
            MobEffectInstance effectInstance = player.getEffect(ModEffects.HAWKEYE.get());
            int currentStacks = (effectInstance != null) ? effectInstance.getAmplifier() : -1;

            if (effectInstance != null) {
                currentStacks = Math.min(2, currentStacks);
                if (effectInstance.getAmplifier() >= 2) {
                    if (player instanceof ServerPlayer serverPlayer) {
                        serverPlayer.playNotifySound(
                                SoundEvents.CROSSBOW_QUICK_CHARGE_3, SoundSource.PLAYERS,
                                1.3F, 1.0F
                        );
                    }
                    spawnParticleBurst(player, ParticleTypes.FALLING_HONEY);

                }
            }
            player.addEffect(new MobEffectInstance(ModEffects.HAWKEYE.get(), 120, currentStacks + 1, false, false, true
            ));
        });

        HAWKEYE_CONSUME_BOW = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_BOW, (player, victim, level, data) -> {
            if (player.hasEffect(ModEffects.HAWKEYE.get())) player.removeEffect(ModEffects.HAWKEYE.get());
        });

        HAWKEYE_CONSUME_CROSSBOW = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_CROSSBOW, (player, victim, level, data) -> {
            if (player.hasEffect(ModEffects.HAWKEYE.get())) player.removeEffect(ModEffects.HAWKEYE.get());
        });

        CUPID_ARROW = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_BOW, (player, victim, level, data) -> {
            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
            if (!cache.isDeceptionReady()) return;
            spawnParticleBurstLow(player, ParticleTypes.DAMAGE_INDICATOR);
            spawnParticleRingHigh((ServerLevel) victim.level(), victim , ParticleTypes.DAMAGE_INDICATOR, 9.0, 180);

            double rangeSq = 36.0;

            List<LivingEntity> nearby = victim.level().getEntitiesOfClass(
                    LivingEntity.class,
                    victim.getBoundingBox().inflate(6.0),
                    e -> {
                        if (!(e instanceof Enemy) || e == victim || e.getType().is(Tags.EntityTypes.BOSSES)
                                || e instanceof Warden || !player.hasLineOfSight(e)) return false;
                        double dx = e.getX() - victim.getX();
                        double dz = e.getZ() - victim.getZ();
                        return (dx * dx + dz * dz) <= rangeSq;
                    }
            );

            for (LivingEntity entity : nearby) {
                if (entity instanceof Mob mob) {
                    mob.setTarget(victim);
                    spawnParticleBurst(entity, ParticleTypes.HEART);
                }
            }
            spawnParticleBurst(victim, ParticleTypes.DAMAGE_INDICATOR);

            player.level().playSound(
                    null, victim.getX(), victim.getY(), victim.getZ(),
                    SoundEvents.AMETHYST_BLOCK_RESONATE, SoundSource.PLAYERS,
                    3.0F, 1.0F
            );

            player.removeEffect(ModEffects.DECEPTION_READY.get());
            player.addEffect(new MobEffectInstance(ModEffects.DECEPTION_COOLDOWN.get(), 600, 0, false, false, true));
        });

        EXPLOSIVE_TENDENCIES = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_CROSSBOW, (player, victim, level, data) -> {
            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
            ServerLevel server = (ServerLevel) level;

            Vec3 targetPos = victim.position();
            Vec3 direction = player.position().subtract(targetPos).normalize();
            Vec3 spawnVec = targetPos.add(direction.scale(3));

            BlockPos spawnPos = BlockPos.containing(spawnVec);

            Creeper creeper = EntityType.CREEPER.spawn(server, spawnPos, MobSpawnType.TRIGGERED);
            if (creeper == null) { return; }

            creeper.getPersistentData().putBoolean("etCre", true);
            creeper.getPersistentData().putUUID("pUUID", player.getUUID());

            creeper.setHealth(40);
            creeper.setSilent(true);


//            creeper.setAggressive(false);
//            creeper.setAggressive(true);

            creeper.setTarget(victim);
            creeper.ignite();

            double x = victim.getX();
            double y = victim.getY();
            double z = victim.getZ();
            creeper.moveTo(x, y, z);

            int stacks = cache.getExplosiveStack();
            cache.setExplosiveStack(stacks - 1);
            player.removeEffect(ModEffects.EXPLOSIVE_TENDENCY_STACK.get());

            if (stacks != 1) {
                player.addEffect(new MobEffectInstance(ModEffects.EXPLOSIVE_TENDENCY_STACK.get(), MobEffectInstance.INFINITE_DURATION, stacks - 2, false, false, true));
            }

            if (!cache.isExplosiveTimerActive()) {
                level.getServer().tell(new net.minecraft.server.TickTask(
                        level.getServer().getTickCount() + 1, () -> {
                            if (player.isAlive()) {
                                player.addEffect(new MobEffectInstance(ModEffects.EXPLOSIVE_TENDENCY_TIMER.get(), 159, 0, false, false, true));
                            }
                }));
            }
        });

        EXPLOIT_WEAKNESS = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_CROSSBOW, (player, victim, level, data) -> {
            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
            if (!cache.isExploitReady()) return;
            Level level_t = victim.level();

            Random random = new Random();
            int debuff = random.nextInt(6);
            int dex = (int) player.getAttributeValue(ModAttributes.DEX.get());

            // 3.3% per
            double dexDuration33 = 1.0 + (dex / 30f);

            // 1% per
            double dexDuration1 = 1.0 + (dex / 100f);

            double rangeSq = 16.0;
            List<LivingEntity> nearby = level.getEntitiesOfClass(
                    LivingEntity.class,
                    victim.getBoundingBox().inflate(4.0f),
                    e -> {
                        if (e == victim || e.isInvulnerable() || !player.hasLineOfSight(e) || !((e instanceof Enemy && !(e instanceof NeutralMob)) || (e instanceof NeutralMob n && n.isAngry()) || (e instanceof Mob m && m.getTarget() != null))) return false;
                        double dx = e.getX() - victim.getX();
                        double dz = e.getZ() - victim.getZ();
                        return (dx * dx + dz * dz) <= rangeSq;
                    }
            );

            applyRandomDebuff(victim, player, debuff, dex, dexDuration33, dexDuration1, (ServerLevel) level_t);

            for (LivingEntity target : nearby) {
                applyRandomDebuffHalved(target, debuff, dex, dexDuration33, dexDuration1);
            }

            player.removeEffect(ModEffects.EXPLOIT_WEAKNESS_READY.get());
            player.addEffect(new MobEffectInstance(ModEffects.EXPLOIT_WEAKNESS_COOLDOWN.get(), 160, 0, true, true, true));

            player.level().playSound(
                    null, player.getX(), player.getY(), player.getZ(),
                    ModSounds.EXPLOIT_WEAKNESS.get(), SoundSource.PLAYERS,
                    2.5F, 1.0F);
        });

        VORTEX = BonusRegistry.register(BonusTrigger.PROJECTILE_LAND_BOW, (player, victim, level, data) -> {
            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
            if (!cache.isVortexReady()) return;

            ServerLevel sLevel = (ServerLevel) level;

            Vec3 hitPos = new Vec3(data[0], data[1], data[2]);

            Holder<DamageType> rangedType = level.registryAccess()
                    .registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(ModDamageTypes.CUSTOM_RANGED_DAMAGE);
            DamageSource source = new DamageSource(rangedType, null, player);

            VortexReady.executeVortex(sLevel, player, 0, source, hitPos);
            VortexReady.executeVortex(sLevel, player, 1, source, hitPos);
            VortexReady.executeVortex(sLevel, player, 2, source, hitPos);
            VortexReady.executeVortex(sLevel, player, 3, source, hitPos);

            player.removeEffect(ModEffects.VORTEX_READY.get());
            player.addEffect(new MobEffectInstance(ModEffects.VORTEX_CD.get(), 20 * 18, 0, false, false, true));
        });

        FOCUSED_ENERGY = BonusRegistry.register(BonusTrigger.PROJECTILE_LAND_CROSSBOW, (player, victim, level, data) -> {
            if (!player.hasEffect(ModEffects.FOCUSED_ENERGY_READY.get())) return;
            Vec3 hitVec = new Vec3(data[0], data[1], data[2]);

            double explosionRadius = 5.0;
            double distance = player.position().distanceTo(hitVec);

            if (distance <= explosionRadius) {
                Vec3 playerVec = player.position().add(0, 0.5, 0);
                Vec3 rawDir = playerVec.subtract(hitVec);
                double ratio = 1.0 - (distance / explosionRadius);

                double horizontalPower = 2.0;
                double verticalPower = 1.5;

                Vec3 launchDir = rawDir.normalize();
                player.setDeltaMovement(
                        launchDir.x * horizontalPower * ratio,
                        verticalPower * ratio,
                        launchDir.z * horizontalPower * ratio
                );

                player.fallDistance = 0;
                player.hurtMarked = true;
            }

            if (level instanceof ServerLevel slevel) {
                EffectUtils.spawnExplosionAt(slevel, hitVec);
                EffectUtils.playExplosionSoundAt(slevel, hitVec, 0.5F);
                spawnParticleRing(slevel, hitVec, ParticleTypes.POOF, 5.0, 100);

                double searchRadius = 5.0;
                AABB explosionBox = player.getBoundingBox().inflate(searchRadius).move(hitVec.subtract(player.position()));

                List<LivingEntity> entities = slevel.getEntitiesOfClass(LivingEntity.class, explosionBox, e ->
                        e.isAlive() && !e.isInvulnerable() && e != player && (e instanceof Enemy)
                );

                for (LivingEntity target : entities) {
                    if (slevel.clip(new ClipContext(hitVec, target.getEyePosition(), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, target)).getType() == HitResult.Type.MISS) {
                        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 6));
                    }
                }
            }
            player.removeEffect(ModEffects.FOCUSED_ENERGY_READY.get());
            player.addEffect(new MobEffectInstance(ModEffects.FOCUSED_ENERGY_CD.get(), 400, 0, false, false, true));
        });

        ENTWINED_OFFERING = BonusRegistry.register(BonusTrigger.HEAL, (player, victim, level, data) -> {
            if (player.getHealth() >= player.getMaxHealth()) return;

            float range = (float) (8.0f * (1.0f + (getScaledValue(player, ModAttributes.AMPLIFICATION.get(), ModAttributes.AMPLIFICATION_MULTIPLIER.get()) / 100.0f)));
            int armorDuration = (int) (20 * 3 * (1.0f + (getScaledValue(player, ModAttributes.AMPLIFICATION.get(), ModAttributes.AMPLIFICATION_MULTIPLIER.get()) / 100.0f)));

            double rangeSq = range * range;
            List<LivingEntity> entities = level.getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(range),
                    e -> {
                        if (e == player || !isAlly(e) || !player.hasLineOfSight(e)) return false;
                        double dx = e.getX() - player.getX();
                        double dz = e.getZ() - player.getZ();
                        return (dx * dx + dz * dz) <= rangeSq;
                    }
            );

            for (LivingEntity target : entities) {
                if (target.getHealth() < target.getMaxHealth()) {
                    target.heal((float) (data[0] * 0.3));
                    target.addEffect(new MobEffectInstance(ModEffects.ENTWINED_OFFERING_ACTIVE.get(), armorDuration, 0, false, false, true));
                }
            }
        });

        THORN_BLOCK = BonusRegistry.register(BonusTrigger.DAMAGE_TAKE_ATTACK_EVENT, (player, attacker, level, data) -> {
            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
            if (!cache.isParryReady()) return;

            if (player.isBlocking()) {
                player.addEffect(new MobEffectInstance (ModEffects.PARRY_ELIGIBLE.get(), 8, 0, false, false, true));
                if (attacker != null) {
                    player.getPersistentData().putUUID("last_attacker_uuid", attacker.getUUID());
                }
            }
        });

        THORN_RELEASE = BonusRegistry.register(BonusTrigger.ITEM_USE_END_SHIELD, (player, attacker, level, data) -> {
            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);

            if (cache.isParryEligible()) {
                triggerParryExplosion(player);
                player.getPersistentData().remove("last_attacker_uuid");
                player.removeEffect(ModEffects.THORNED_PARRY_READY.get());
            }
        });

        RETALIATE_ACTIVATE = BonusRegistry.register(BonusTrigger.ITEM_USE_START_SHIELD, (player, victim, level, data) -> {
            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
            if (cache.isRetaliateReady()) {
                player.removeEffect(ModEffects.RETALIATE_READY.get());
                player.level().playSound(
                        null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.HORSE_SADDLE, SoundSource.PLAYERS,
                        0.4F, 1.0F
                );
                player.addEffect(new MobEffectInstance(ModEffects.RETALIATE_ACTIVE.get(), 80, 0, false, false, true));
            }
        });

        RETALIATE_HURT = BonusRegistry.register(BonusTrigger.DAMAGE_TAKE_DAMAGE_EVENT, (player, victim, level, data) -> {
            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
            if (cache.isRetaliateActive()) {
                int stacks = cache.getRetaliateStack();
                cache.setRetaliateStack(stacks + 1);
            }
        });

        BASTION_ACTIVATE = BonusRegistry.register(BonusTrigger.ITEM_USE_END_SHIELD, (player, victim, level, data) -> {
            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
            if (!cache.isBastionReady()) return;

            double fort = player.getAttributeValue(ModAttributes.FORT.get());
            double perc = player.getAttributeValue(ModAttributes.PERC.get());
            double con = player.getAttributeValue(ModAttributes.CON.get());

            double seconds = 1.5 + 0.0075 * fort + 0.005 * perc + 0.005 * con;
            int ticks = (int)(seconds * 20);

            player.addEffect(new MobEffectInstance(ModEffects.BASTION_ACTIVE.get(), ticks, 0, false, false, true));
            player.removeEffect(ModEffects.BASTION_READY.get());
        });

        SANCTUARY = BonusRegistry.register(BonusTrigger.ITEM_USE_TICK_SHIELD, (player, victim, level, data) -> {
            // Item tick
            int ticksHeld = (int) data[0];

            if (ticksHeld % 20 == 0) {
                performDivinityPulse(player);
            }
        });

        CLAIRVOYANCE_START = BonusRegistry.register(BonusTrigger.ITEM_USE_START_BOW, (player, victim, level, data) -> {
            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
            if (!cache.isClairvoyanceReady()) return;
            if (cache.isClairvoyanceHit()) cache.setClairvoyanceHit(false);

            double drawSpeed = getScaledValue(player, ModAttributes.NOCK_HASTE.get(), ModAttributes.NOCK_HASTE_MULTIPLIER.get());
            double chargeReductionMultiplier = 1 - (drawSpeed / (drawSpeed + 100.0));
            int finalReqTicks = (int) (80 * chargeReductionMultiplier);

            cache.setClairvoyanceTarget(finalReqTicks);
        });

        CLAIRVOYANCE_TICK = BonusRegistry.register(BonusTrigger.ITEM_USE_TICK_BOW, (player, victim, level, data) -> {
            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
            if (!cache.isClairvoyanceReady()) return;

            // Item tick
            int current = (int) data[0];
            double target = cache.getClairvoyanceTarget();

            boolean ready = current >= target;

            if (ready && !cache.isClairvoyanceHit()) {
                cache.setClairvoyanceHit(true);
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                        ModSounds.CLAIRVOYANCE.get(), SoundSource.PLAYERS, 1.2F, 1.0F);
            }
        });

        CLAIRVOYANCE_MISS = BonusRegistry.register(BonusTrigger.PROJECTILE_LAND_BOW, (player, victim, level, data) -> {
            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
            if (cache.isClairvoyanceHit()) {
                cache.setClairvoyanceHit(false);
            }
        });

//        ITF = BonusRegistry.register(BonusTrigger.TICK_SPRINT, (player, victim, level, data) -> {
//            int ticks = (int) data[0];
//
//            if (ticks <= 60) return;
//
//            int amplifier = Math.min((ticks - 60) / 40, 4);
//            int stackCount = amplifier + 1;
//
//            MobEffectInstance current = player.getEffect(ModEffects.INTO_THE_FRAY.get());
//            if (current == null || current.getAmplifier() != amplifier) {
//                player.addEffect(new MobEffectInstance(ModEffects.INTO_THE_FRAY.get(), 40, amplifier, true, false, true));
//            }
//
//            if (ticks >= 220 && !player.hasEffect(MobEffects.ABSORPTION)) {
//                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 40, 0, true, false, true));
//            }
//
//            checkCollisions(player, stackCount);
//        });

        QUANTUM_LEAP_REMOVE = BonusRegistry.register(BonusTrigger.HURT, (player, victim, level, data) -> {
            if (!player.hasEffect(ModEffects.QUANTUM_LEAP_ACTIVE.get())) return;
            player.removeEffect(ModEffects.QUANTUM_LEAP_ACTIVE.get());
        });

        DFA_LAND = BonusRegistry.register(BonusTrigger.LAND, (player, victim, level, data) -> {
            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
            if (!cache.isDFAAirborne()) return;
            cache.setDFAAirborne(false);

            float damage = 6.25f;
            if (cache.isDfaJump()) damage *= 2;

            cache.setDfaJump(false);

            // Fall distance
            data[0] = 0;

            List<LivingEntity> jumpTargets = level.getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(7),
                    e -> {
                        if (e.isInvulnerable() || !player.hasLineOfSight(e) || !((e instanceof Enemy && !(e instanceof NeutralMob)) || (e instanceof NeutralMob n && n.isAngry()) || (e instanceof Mob m && m.getTarget() != null))) return false;
                        return e.distanceToSqr(player) <= 49;
                    }
            );

            Holder<DamageType> meleeType = level.registryAccess()
                    .registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(ModDamageTypes.CUSTOM_MELEE_DAMAGE);
            DamageSource source = new DamageSource(meleeType, null, player);

            for (LivingEntity target : jumpTargets) {

                if (target != null) {
                    target.hurt(source, damage);

                    if (level instanceof ServerLevel serverLevel) {
                        spawnParticleRing(serverLevel, player, ParticleTypes.POOF, 7, 140);
                        spawnExplosionOnFeet(player);
                        playExplosionSound(player, 0.6F);
                    }
                    target.setDeltaMovement(target.getDeltaMovement().x, 0.8, target.getDeltaMovement().z);
                    target.hurtMarked = true;
                }
            }
            player.hurtMarked = true;
            ModMessages.sendToPlayer(new DfaAirborneSync.DfaAirborneFlagPacket(false), (ServerPlayer) player);
        });

        LIFE_TOUCH_IMPACT = BonusRegistry.register(BonusTrigger.PROJECTILE_LAND_BOW, (player, victim, level, data) -> {
            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
            if (!cache.isLifeTouchReady()) return;

            AreaEffectCloud cloud = new AreaEffectCloud(level, data[0], data[1], data[2]);

            float range = (float) (3.0f * (1.0f + (getScaledValue(player, ModAttributes.AMPLIFICATION.get(), ModAttributes.AMPLIFICATION_MULTIPLIER.get()) / 100.0f)));

            cloud.setRadius(range);
            cloud.setDuration(200);
            cloud.setRadiusPerTick(-0.010f);
            cloud.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 21, 0));
            cloud.setWaitTime(0);
            cloud.setFixedColor(0x008000);

            cloud.setOwner(player);

            level.addFreshEntity(cloud);

            player.level().playSound(
                    null, data[0], data[1], data[2],
                    SoundEvents.SPLASH_POTION_BREAK, SoundSource.PLAYERS,
                    0.6F, 1.0F
            );
            player.removeEffect(ModEffects.LIFE_TOUCH_READY.get());
            player.addEffect(new MobEffectInstance(ModEffects.LIFE_TOUCH_COOLDOWN.get(), 440, 0, false, false, true));
        });

        LIFE_TOUCH_FRIENDLY_FIRE = BonusRegistry.register(BonusTrigger.FRIENDLY_FIRE_LIFE_TOUCH, (player, victim, level, data) -> {
            if (isAlly(victim)) {

                double genPot = getScaledValue(player, ModAttributes.PROJECTILE_POTENCY.get(), ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get());

                double healInc = 0.175 * (1 + genPot) * (1 + (getScaledValue(player, ModAttributes.RESTORATION.get(), ModAttributes.RESTORATION_MULTIPLIER.get())));

                if (rollCrit(player, getScaledValue(player, ModAttributes.PROJECTILE_ACCURACY.get(), ModAttributes.PROJECTILE_ACCURACY_MULTIPLIER.get()))) {
                    healInc *= (float) (1.5 + getScaledValue(player, ModAttributes.PROJECTILE_PRECISION.get(), ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get()) / 100.0);
                }

                victim.heal((float) (data[1] * data[0] * healInc));

                EffectUtils.spawnComposterBurst(victim);
                EffectUtils.playHealSound(victim);
                victim.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0, false, false, true));

                data[0] = -1f;
            }
        });

        NIMBLE_GETAWAY = BonusRegistry.register(BonusTrigger.DAMAGE_TAKE_ATTACK_EVENT, (player, victim, level, data) -> {
            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
            if (cache.isEvadeActive()) {
                player.removeEffect(ModEffects.NIMBLE_GETAWAY_ACTIVE.get());
                player.addEffect(new MobEffectInstance(ModEffects.NIMBLE_GETAWAY_COOLDOWN.get(), 400, 0, false, false, true));
            }
        });

        CLAIRVOYANCE_HIT = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_BOW, (player, victim, level, data) -> {
            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
            if (cache.isClairvoyanceHit()) {
                cache.setClairvoyanceHit(false);

                // Double the damage
                data[0] *= 2;
                player.removeEffect(ModEffects.CLAIRVOYANCE_READY.get());
                player.addEffect(new MobEffectInstance(ModEffects.CLAIRVOYANCE_COOLDOWN.get(), 400, 0, false, false, true));
            }
        });

        CHAIN_LIGHTNING = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data) -> {
            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);

            float multiplier = 0.35f;

            if (cache.isChainLightningEnhanced()) {
                multiplier += 0.1f;
            }

            LivingEntity currentSource = victim;
            double bounceDamage = data[1] * multiplier;


            Holder<DamageType> meleeType = level.registryAccess()
                    .registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(ModDamageTypes.TRUE_DAMAGE);
            DamageSource source = new DamageSource(meleeType, null, player);

            List<LivingEntity> nearby = level.getEntitiesOfClass(
                    LivingEntity.class,
                    victim.getBoundingBox().inflate(4.0),
                    e -> e != null && e.isAlive() && e != victim && (
                            e instanceof Enemy ||
                                    (e instanceof NeutralMob n && n.isAngry()) ||
                                    (e instanceof Mob m && m.getTarget() != null)
                    )
            );

            for (LivingEntity next : nearby) {
                if (bounceDamage < 1.0) break;
                next.hurt(source, (float) (bounceDamage));

                if (level instanceof ServerLevel serverLevel) {
                    double startX = currentSource.getX();
                    double startY = currentSource.getY() + 1.2;
                    double startZ = currentSource.getZ();

                    double dx = next.getX() - startX;
                    double dy = (next.getY() + 1.2) - startY;
                    double dz = next.getZ() - startZ;

                    double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
                    int particleCount = (int) (distance * 4);

                    for (int i = 0; i < particleCount; i++) {
                        double ratio = (double) i / particleCount;
                        serverLevel.sendParticles(ParticleTypes.ELECTRIC_SPARK,
                                startX + (dx * ratio), startY + (dy * ratio), startZ + (dz * ratio),
                                1, 0, 0, 0, 0.0);
                    }
                }
                currentSource = next;
                bounceDamage *= 0.35;
            }
        });

        EXECUTIONER = BonusRegistry.register(BonusTrigger.HURT, (player, victim, level, data) -> {
            if (victim.getHealth() <= victim.getMaxHealth()/2) data[0] += 0.1f;
        });

        VANGUARDIAN = BonusRegistry.register(BonusTrigger.BLESSING_ACTIVATION, (player, victim, level, data) -> {
            MobEffectInstance existingInstance = player.getEffect(ModEffects.BLESSED.get());

            int newAmplifier = 0;
            if (existingInstance != null) {
                newAmplifier = Math.min(2, existingInstance.getAmplifier() + 1);
            }

            player.addEffect(new MobEffectInstance(ModEffects.BLESSED.get(), 80, newAmplifier, false, false, false));
         });

        SURGING_BLOOD_DIRECT = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data) -> {
            float health = player.getHealth();
            float maxHealth = Math.max(20, player.getMaxHealth());

            float damageBuff = Math.max(0, 2 * (maxHealth - health)/100f);
            data[0] += damageBuff;
        });

        SURGING_BLOOD_INDIRECT = BonusRegistry.register(BonusTrigger.INDIRECT_MELEE_HURT, (player, victim, level, data) -> {
            float health = player.getHealth();
            float maxHealth = Math.max(20, player.getMaxHealth());

            float damageBuff = Math.max(0, 2 * (maxHealth - health)/100f);
            data[0] += damageBuff;
        });

        ENRAGE = BonusRegistry.register(BonusTrigger.BLESSING_ACTIVATION, (player, victim, level, data) -> {
            player.addEffect(new MobEffectInstance(ModEffects.ENRAGED.get(), 60, 0, false, false, false));
        });


        COMMANDER = BonusRegistry.register(BonusTrigger.BLESSING_ACTIVATION, (player, victim, level, data) -> {
            double radiusSq = 64.0;
            List<LivingEntity> entities = level.getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(8.0),
                    e -> {
                        if (!isAlly(e) || !player.hasLineOfSight(e)) return false;
                        double dx = e.getX() - player.getX();
                        double dz = e.getZ() - player.getZ();
                        return (dx * dx + dz * dz) <= radiusSq;
                    }
            );

            for (LivingEntity target : entities) {
                spawnParticleBurst(target, ParticleTypes.HAPPY_VILLAGER);
                player.addEffect(new MobEffectInstance(ModEffects.COMMANDERS_MARCH.get(), 100, 0, false, false, false));
            }

        });

        FRENZY = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data) -> {
            var effectInstance = player.getEffect(ModEffects.FRENZY.get());
            int currentStacks = (effectInstance != null) ? effectInstance.getAmplifier() + 1 : 0;

            int nextStacks = Math.min(40, currentStacks + 1);

            player.addEffect(new MobEffectInstance(ModEffects.FRENZY.get(), 40, nextStacks - 1, false, false, true
            ));
        });

        COURAGEOUS_BLOW_DIRECT = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data) -> {
            float damage = (float) (player.getHealth() * 0.08);

            Holder<DamageType> meleeType = level.registryAccess()
                    .registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(ModDamageTypes.TRUE_DAMAGE);
            DamageSource source = new DamageSource(meleeType, null, player);

            victim.hurt(source,damage);
        });

        COURAGEOUS_BLOW_INDIRECT = BonusRegistry.register(BonusTrigger.INDIRECT_MELEE_HURT, (player, victim, level, data) -> {
            float damage = (player.getHealth() * 0.02f);

            Holder<DamageType> trueType = level.registryAccess()
                    .registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(ModDamageTypes.TRUE_DAMAGE);
            DamageSource source = new DamageSource(trueType, null, player);

            victim.hurt(source, damage);
        });

        ARMOR_BREAK_DIRECT = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data) -> {
            player.addEffect(new MobEffectInstance(ModEffects.STOLEN_ARMOR.get(), 60, 0, false, false,false));
            victim.addEffect(new MobEffectInstance(ModEffects.ARMOR_BREAK.get(), 60, 0, false, true,true));
        });

        ARMOR_BREAK_INDIRECT = BonusRegistry.register(BonusTrigger.INDIRECT_MELEE_HURT, (player, victim, level, data) -> {
            player.addEffect(new MobEffectInstance(ModEffects.STOLEN_ARMOR.get(), 60, 0, false, false,false));
            victim.addEffect(new MobEffectInstance(ModEffects.ARMOR_BREAK.get(), 60, 0, false, true,true));
        });

        REVENGEANCE = BonusRegistry.register(BonusTrigger.DAMAGE_TAKE_ATTACK_EVENT, (player, victim, level, data) -> {
            boolean isSuccess = player.getRandom().nextDouble() < 0.3;
            if (isSuccess) {
                float damage = (data[0] * 2);

                Holder<DamageType> trueType = level.registryAccess()
                        .registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(ModDamageTypes.TRUE_DAMAGE);
                DamageSource source = new DamageSource(trueType, null, player);

                victim.hurt(source, damage);
            }
        });

        ABSORBED_EVIL = BonusRegistry.register(BonusTrigger.DEBUFF_APPLIED, (player, victim, level, data) -> {
            player.addEffect(new MobEffectInstance(ModEffects.ABSORBED_EVIL.get(), 120, 0, false, false,false));
        });


        SOUL_SEPARATION_DIRECT = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data) -> {
            boolean isSuccess = player.getRandom().nextDouble() < 0.1;
            if (isSuccess) {
                ServerLevel sLevel = (ServerLevel) player.level();

                player.addEffect(new MobEffectInstance(ModEffects.SOUL_SEVERANCE_COOLDOWN.get(), 320, 0, false, false, true));
                player.removeEffect(ModEffects.SOUL_SEVERANCE_READY.get());

                Holder<DamageType> meleeType = level.registryAccess()
                        .registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(ModDamageTypes.CUSTOM_MELEE_DAMAGE);
                DamageSource source = new DamageSource(meleeType, null, player);

                executeSS(sLevel, player, 0, source);
            }
        });

        SOUL_SEPARATION_INDIRECT = BonusRegistry.register(BonusTrigger.INDIRECT_MELEE_HURT, (player, victim, level, data) -> {
            boolean isSuccess = player.getRandom().nextDouble() < 0.05;
            if (isSuccess) {
                ServerLevel sLevel = (ServerLevel) player.level();

                player.addEffect(new MobEffectInstance(ModEffects.SOUL_SEVERANCE_COOLDOWN.get(), 320, 0, false, false, true));
                player.removeEffect(ModEffects.SOUL_SEVERANCE_READY.get());

                Holder<DamageType> meleeType = level.registryAccess()
                        .registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(ModDamageTypes.CUSTOM_MELEE_DAMAGE);
                DamageSource source = new DamageSource(meleeType, null, player);

                executeSS(sLevel, player, 0, source);
            }
        });

        EXECUTE = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data) -> {
            float baseThreshold = 0.07f;
            float attributePercent = (float) getScaledValue(player, ModAttributes.MELEE_PRECISION.get(), ModAttributes.MELEE_PRECISION_MULTIPLIER.get()) / 100f;
            float finalThreshold = baseThreshold * (1.0f + attributePercent);

            float healthPercent = victim.getHealth() / victim.getMaxHealth();

            if (healthPercent <= finalThreshold) {
                Holder<DamageType> trueType = level.registryAccess()
                        .registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(ModDamageTypes.TRUE_DAMAGE);
                DamageSource source = new DamageSource(trueType, null, player);

                victim.hurt(source, 1000);
            }
        });

        // TODO: MOVE LIFE STEAL TO THE END
        VAMPIRIC_TOUCH_DIRECT = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data) -> {
            float healRatio = 0.03f;
            player.heal(data[0] * data[1] * healRatio);
        });

        VAMPIRIC_TOUCH_INDIRECT = BonusRegistry.register(BonusTrigger.INDIRECT_MELEE_HURT, (player, victim, level, data) -> {
            float healRatio = 0.015f;
            player.heal(data[0] * data[1] * healRatio);
        });

        BLOODWORM = BonusRegistry.register(BonusTrigger.KILL, (player, victim, level, data) -> {
            MobEffectInstance effectInstance = player.getEffect(ModEffects.HAWKEYE.get());
            int currentStacks = (effectInstance != null) ? effectInstance.getAmplifier() : -1;

            player.addEffect(new MobEffectInstance(ModEffects.BLOODWORM.get(), 72000, Math.min(99, currentStacks+1), false, false, false));
        });
    }
}
