package net.cold.coldsmod.capabilities_and_blessings.Capabilities;

import net.cold.coldsmod.ModMessages;
import net.cold.coldsmod.ModSounds;
import net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree.Bleed;
import net.cold.coldsmod.capabilities_and_blessings.effects.BronzewoodCurse;
import net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils;
import net.cold.coldsmod.capabilities_and_blessings.registry.ModEffects;
import net.cold.coldsmod.damage_types.ModDamageTypes;
import net.cold.coldsmod.network.DfaAirborneSync;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
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
import static net.cold.coldsmod.capabilities_and_blessings.effects.VortexReady.executeVortex;
import static net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils.*;
import static net.cold.coldsmod.events.Formulas.rollCrit;
import static net.cold.coldsmod.network.DirectedHatredPacket.executeDirectedHatred;
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


    public static int CRITICAL_ASCENSION_MELEE;
    public static int CRITICAL_ASCENSION_RANGED;
    public static int CRITICAL_ASCENSION_RANGED_2;
    public static int SNIPE_BOW;
    public static int SNIPE_CROSSBOW;
    public static int STEALTH;
    public static int BLEED_MELEE;
    public static int BLEED_INDIRECT_MELEE;
    public static int BLEED_BOW;
    public static int BLEED_CROSSBOW;
    public static int CHAIN_LIGHTNING_BOW;
    public static int CHAIN_LIGHTNING_CROSSBOW;
    public static int BLADEDANCER_MELEE;
    public static int BLADEDANCER_INDIRECT_MELEE;
    public static int BLADEDANCER_BOW;
    public static int BLADEDANCER_CROSSBOW;
    public static int VORTEX_BOW;
    public static int VORTEX_BOW_GUARANTEE;
    public static int VORTEX_CROSSBOW;
    public static int GAMBIT_MELEE_NONCRIT;
    public static int GAMBIT_MELEE_CRIT;
    public static int GAMBIT_MELEE_NONCRIT_INDIRECT;
    public static int GAMBIT_MELEE_CRIT_INDIRECT;
    public static int GAMBIT_BOW_NONCRIT;
    public static int GAMBIT_BOW_CRIT;
    public static int GAMBIT_CROSSBOW_NONCRIT;
    public static int GAMBIT_CROSSBOW_CRIT;
    public static int GAMBIT_RANGE_INDIRECT_NONCRIT;
    public static int GAMBIT_RANGE_INDIRECT_CRIT;
    public static int GAMBIT_OTHER_CRIT;
    public static int GAMBIT_OTHER_NONCRIT;
    public static int HEARTPIERCER_BOW;
    public static int HEARTPIERCER_CROSSBOW;


    public static int STEEL_DEFENSE;
    public static int BRIMSTONE_SHIELD;
    public static int ARMOR_PIERCE;
    public static int ABSORPTION;
    public static int ASCENSION;
    public static int DEFLECT;
    public static int RETALIATING_BLOW;
    public static int FLOATING_CURSE;
    public static int FIREWIELDER;
    public static int DESTROYER;
    public static int IMMORTAL;


    public static int REVITALIZE;
    public static int HOLY_AVENGER;
    public static int REGENERATION;
    public static int RADIATING_STRIKE;
    public static int RECOVERY;
    public static int PURIFY;
    public static int SOUL_HARVEST;
    public static int SOULEATER;
    public static int SOULEATER_INDIRECT;
    public static int LAND_SPAWN;


    public static void init() {
        BRONZEWOODS_CURSE = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data, cache) -> {
            if (!cache.isBronzewoodReady()) return;

            int cd = 400;

            Holder<DamageType> meleeType = level.registryAccess()
                    .registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(ModDamageTypes.CUSTOM_MELEE_DAMAGE);
            DamageSource source = new DamageSource(meleeType, null, player);

            victim.hurt(source, 2.0f);

            victim.addEffect(new MobEffectInstance(ModEffects.BRONZEWOOD_CURSE.get(), 200, 0, false, false, true));

            if (cache.isBronzewoodEnhanced()) {
                victim.addEffect(new MobEffectInstance(ModEffects.ENHANCED_BRONZEWOODS_CURSE.get(), 200, 0, false, false, true));
            }

            if (cache.isTankBlessingEnhanced()) {
                cd -= 120;
            }

            player.level().playSound(
                    null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.SOUL_ESCAPE, SoundSource.PLAYERS,
                    7F, 1.0F
            );
            spawnParticleBurst(victim, ParticleTypes.SCULK_SOUL);

            long expiry = level.getGameTime() + 200L;

            Map<UUID, BronzewoodCurse.CurseData> victimSources = BronzewoodCurse.activeCurses.computeIfAbsent(
                    victim.getUUID(), k -> new HashMap<>()
            );

            victimSources.put(player.getUUID(), new BronzewoodCurse.CurseData(1, expiry));

            player.addEffect(new MobEffectInstance(ModEffects.BRONZEWOOD_COOLDOWN.get(), cd, 0, false, false, true));
            player.removeEffect(ModEffects.BRONZEWOOD_READY.get());
        });

        BRONZEWOODS_KILL = BonusRegistry.register(BonusTrigger.KILL, (player, victim, level, data, cache) -> {
            if (!cache.isBronzewoodReady()) {
                player.removeEffect(ModEffects.BRONZEWOOD_COOLDOWN.get());
                player.addEffect(new MobEffectInstance(ModEffects.BRONZEWOOD_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
            }
        });

        BERSERK_HIT = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data, cache) -> {
            if (cache.isBerserkEnraged()) {
                player.removeEffect(ModEffects.BERSERK_READY.get());

                float multiplier = 0.004f;

                if (cache.isBloodthirstEnhanced()) {
                    multiplier += 0.0015f;
                }

                double statValue = getScaledValue(player, ModAttributes.MELEE_POTENCY.get());
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

        BERSERK_KILL = BonusRegistry.register(BonusTrigger.KILL, (player, victim, level, data, cache) -> {
            player.removeEffect(ModEffects.BERSERK_READY.get());
            player.removeEffect(ModEffects.BERSERK.get());
            player.addEffect(new MobEffectInstance(ModEffects.BERSERK_READY.get(), 120, 0, false, false,true));
        });

        BLESSED_LAND = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data, cache) -> {
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

            double ampValue = getScaledValue(player, ModAttributes.AMPLIFICATION.get());
            int cd = (int) (300 / (1.0 + (ampValue / 100.0)));
            player.removeEffect(ModEffects.BLESSED_LAND_READY.get());
            player.addEffect(new MobEffectInstance(ModEffects.BLESSED_LAND_CD.get(), cd, 0, false, false, true));
        });

        CUPID_ARROW = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_BOW, (player, victim, level, data, cache) -> {
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

        EXPLOSIVE_TENDENCIES = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_CROSSBOW, (player, victim, level, data, cache) -> {

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
                                player.addEffect(new MobEffectInstance(ModEffects.EXPLOSIVE_TENDENCY_TIMER.get(), 239, 0, false, false, false));
                            }
                }));
            }
        });

        EXPLOIT_WEAKNESS = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_CROSSBOW, (player, victim, level, data, cache) -> {

            if (!cache.isExploitReady()) return;

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

            applyRandomDebuff(victim, player, debuff, dex, dexDuration33, dexDuration1, (ServerLevel) level);

            for (LivingEntity target : nearby) {
                applyRandomDebuffHalved(target, debuff, dex, dexDuration33, dexDuration1);
            }

            player.removeEffect(ModEffects.EXPLOIT_WEAKNESS_READY.get());
            player.addEffect(new MobEffectInstance(ModEffects.EXPLOIT_WEAKNESS_COOLDOWN.get(), 240, 0, true, true, true));

            player.level().playSound(
                    null, player.getX(), player.getY(), player.getZ(),
                    ModSounds.EXPLOIT_WEAKNESS.get(), SoundSource.PLAYERS,
                    2.5F, 1.0F);
        });

        VORTEX = BonusRegistry.register(BonusTrigger.PROJECTILE_LAND_BOW, (player, victim, level, data, cache) -> {

            if (!cache.isVortexReady()) return;

            ServerLevel sLevel = (ServerLevel) level;

            Vec3 hitPos = new Vec3(data[0], data[1], data[2]);

            Holder<DamageType> rangedType = level.registryAccess()
                    .registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(ModDamageTypes.DOT_DAMAGE);
            DamageSource source = new DamageSource(rangedType, null, player);

            boolean debuff = cache.isVortexEnhanced();
            boolean buff = cache.isVortexSeveranceEnhanced();

            executeVortex(sLevel, player, 4000, 4000, source, hitPos, debuff, buff);

            player.removeEffect(ModEffects.VORTEX_READY.get());
            player.addEffect(new MobEffectInstance(ModEffects.VORTEX_CD.get(), 480, 0, false, false, true));
        });

        FOCUSED_ENERGY = BonusRegistry.register(BonusTrigger.PROJECTILE_LAND_CROSSBOW, (player, victim, level, data, cache) -> {
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

        THORN_BLOCK = BonusRegistry.register(BonusTrigger.DEFEND, (player, attacker, level, data, cache) -> {

            if (!cache.isParryReady()) return;

            if (player.isBlocking()) {
                player.addEffect(new MobEffectInstance (ModEffects.PARRY_ELIGIBLE.get(), 8, 0, false, false, true));
                if (attacker != null) {
                    player.getPersistentData().putUUID("last_attacker_uuid", attacker.getUUID());
                }
            }
        });

        THORN_RELEASE = BonusRegistry.register(BonusTrigger.ITEM_USE_END_SHIELD, (player, attacker, level, data, cache) -> {


            if (cache.isParryEligible()) {
                triggerParryExplosion(player);
                player.getPersistentData().remove("last_attacker_uuid");
                player.removeEffect(ModEffects.THORNED_PARRY_READY.get());
            }
        });

        RETALIATE_ACTIVATE = BonusRegistry.register(BonusTrigger.ITEM_USE_START_SHIELD, (player, victim, level, data, cache) -> {

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

        RETALIATE_HURT = BonusRegistry.register(BonusTrigger.DAMAGE_TAKE_DAMAGE_EVENT, (player, victim, level, data, cache) -> {

            if (cache.isRetaliateActive()) {
                int stacks = cache.getRetaliateStack();
                cache.setRetaliateStack(stacks + 1);
            }
        });

        BASTION_ACTIVATE = BonusRegistry.register(BonusTrigger.ITEM_USE_END_SHIELD, (player, victim, level, data, cache) -> {

            if (!cache.isBastionReady()) return;

            double fort = player.getAttributeValue(ModAttributes.FORT.get());
            double perc = player.getAttributeValue(ModAttributes.PERC.get());
            double con = player.getAttributeValue(ModAttributes.CON.get());

            double seconds = 1.5 + 0.0075 * fort + 0.005 * perc + 0.005 * con;
            if (cache.isShieldBlessingEnhanced()) seconds *= 1.5;
            int ticks = (int)(seconds * 20);

            player.addEffect(new MobEffectInstance(ModEffects.BASTION_ACTIVE.get(), ticks, 0, false, false, true));
            player.removeEffect(ModEffects.BASTION_READY.get());
        });

        SANCTUARY = BonusRegistry.register(BonusTrigger.ITEM_USE_TICK_SHIELD, (player, victim, level, data, cache) -> {
            // Item tick
            int ticksHeld = (int) data[0];

            if (ticksHeld % 20 == 0) {
                performDivinityPulse(player);
            }
        });

        CLAIRVOYANCE_START = BonusRegistry.register(BonusTrigger.ITEM_USE_START_BOW, (player, victim, level, data, cache) -> {

            if (!cache.isClairvoyanceReady()) return;
            if (cache.isClairvoyanceHit()) cache.setClairvoyanceHit(false);

            double drawSpeed = getScaledValue(player, ModAttributes.NOCK_HASTE.get());
            double chargeReductionMultiplier = 1 - (drawSpeed / (drawSpeed + 100.0));
            int finalReqTicks = (int) (80 * chargeReductionMultiplier);

            cache.setClairvoyanceTarget(finalReqTicks);
        });

        CLAIRVOYANCE_TICK = BonusRegistry.register(BonusTrigger.ITEM_USE_TICK_BOW, (player, victim, level, data, cache) -> {

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

        CLAIRVOYANCE_MISS = BonusRegistry.register(BonusTrigger.PROJECTILE_LAND_BOW, (player, victim, level, data, cache) -> {

            if (cache.isClairvoyanceHit()) {
                cache.setClairvoyanceHit(false);
            }
        });

//        ITF = BonusRegistry.register(BonusTrigger.TICK_SPRINT, (player, victim, level, data, cache) -> {
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

        QUANTUM_LEAP_REMOVE = BonusRegistry.register(BonusTrigger.HURT, (player, victim, level, data, cache) -> {
            if (!player.hasEffect(ModEffects.QUANTUM_LEAP_ACTIVE.get())) return;
            player.removeEffect(ModEffects.QUANTUM_LEAP_ACTIVE.get());
        });

        DFA_LAND = BonusRegistry.register(BonusTrigger.LAND, (player, victim, level, data, cache) -> {

            if (!cache.isDFAAirborne()) return;
            cache.setDFAAirborne(false);

            float damage = 3.75f;
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

        LIFE_TOUCH_IMPACT = BonusRegistry.register(BonusTrigger.PROJECTILE_LAND_BOW, (player, victim, level, data, cache) -> {

            if (!cache.isLifeTouchReady()) return;

            AreaEffectCloud cloud = new AreaEffectCloud(level, data[0], data[1], data[2]);

            float range = (float) (3.0f * (1.0f + (getScaledValue(player, ModAttributes.AMPLIFICATION.get()) / 100.0f)));

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

        LIFE_TOUCH_FRIENDLY_FIRE = BonusRegistry.register(BonusTrigger.FRIENDLY_FIRE_LIFE_TOUCH, (player, victim, level, data, cache) -> {
            if (isAlly(victim)) {

                double genPot = getScaledValue(player, ModAttributes.PROJECTILE_POTENCY.get());

                double healInc = 0.3 * (1 + genPot) * (1 + (getScaledValue(player, ModAttributes.RESTORATION.get())));

                if (rollCrit(player, getScaledValue(player, ModAttributes.PROJECTILE_ACCURACY.get()))) {
                    healInc *= (float) (1.25 + getScaledValue(player, ModAttributes.PROJECTILE_PRECISION.get()) / 100.0);
                }

                victim.heal((float) (data[1] * data[0] * healInc));

                EffectUtils.spawnComposterBurst(victim);
                EffectUtils.playHealSound(victim);
                victim.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0, false, false, false));

                data[0] = -1f;
            }
        });

        NIMBLE_GETAWAY = BonusRegistry.register(BonusTrigger.DAMAGE_TAKE_ATTACK_EVENT_PRE, (player, victim, level, data, cache) -> {

            if (cache.isEvadeActive()) {
                player.removeEffect(ModEffects.NIMBLE_GETAWAY_ACTIVE.get());
                player.addEffect(new MobEffectInstance(ModEffects.NIMBLE_GETAWAY_COOLDOWN.get(), 400, 0, false, false, true));
            }
        });

        CLAIRVOYANCE_HIT = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_BOW, (player, victim, level, data, cache) -> {

            if (cache.isClairvoyanceHit()) {
                cache.setClairvoyanceHit(false);

                data[0] *= 2;
                player.removeEffect(ModEffects.CLAIRVOYANCE_READY.get());
                player.addEffect(new MobEffectInstance(ModEffects.CLAIRVOYANCE_COOLDOWN.get(), 400, 0, false, false, true));
            }
        });

        EXECUTIONER = BonusRegistry.register(BonusTrigger.HURT, (player, victim, level, data, cache) -> {
            if (victim.getHealth() <= victim.getMaxHealth()/2) data[0] += 0.1f;
        });

        VANGUARDIAN = BonusRegistry.register(BonusTrigger.BLESSING_ACTIVATION, (player, victim, level, data, cache) -> {
            MobEffectInstance existingInstance = player.getEffect(ModEffects.BLESSED.get());

            int newAmplifier = 0;
            if (existingInstance != null) {
                newAmplifier = Math.min(2, existingInstance.getAmplifier() + 1);
            }

            player.addEffect(new MobEffectInstance(ModEffects.BLESSED.get(), 160, newAmplifier, false, false, false));
         });

        SURGING_BLOOD_DIRECT = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data, cache) -> {
            float health = player.getHealth();
            float maxHealth = Math.max(20, player.getMaxHealth());

            float damageBuff = Math.max(0, 2 * (maxHealth - health)/100f);
            data[0] += damageBuff;
        });

        SURGING_BLOOD_INDIRECT = BonusRegistry.register(BonusTrigger.MELEE_HURT_INDIRECT, (player, victim, level, data, cache) -> {
            float health = player.getHealth();
            float maxHealth = Math.max(20, player.getMaxHealth());

            float damageBuff = Math.max(0, 2 * (maxHealth - health)/100f);
            data[0] += damageBuff;
        });

        ENRAGE = BonusRegistry.register(BonusTrigger.BLESSING_ACTIVATION, (player, victim, level, data, cache) -> {
            player.addEffect(new MobEffectInstance(ModEffects.ENRAGED.get(), 120, 0, false, false, false));
        });


        COMMANDER = BonusRegistry.register(BonusTrigger.BLESSING_ACTIVATION, (player, victim, level, data, cache) -> {
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
                player.addEffect(new MobEffectInstance(ModEffects.COMMANDERS_MARCH.get(), 200, 0, false, false, false));
            }
        });

        FRENZY = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data, cache) -> {
            var effectInstance = player.getEffect(ModEffects.FRENZY.get());
            int currentStacks = (effectInstance != null) ? effectInstance.getAmplifier() + 1 : 0;

            int nextStacks = Math.min(20, currentStacks + 1);

            player.addEffect(new MobEffectInstance(ModEffects.FRENZY.get(), 120, nextStacks - 1, false, false, false
            ));
        });

        COURAGEOUS_BLOW_DIRECT = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data, cache) -> {
            float damage = (float) (player.getHealth() * 0.06);

            Holder<DamageType> meleeType = level.registryAccess()
                    .registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(ModDamageTypes.TRUE_DAMAGE);
            DamageSource source = new DamageSource(meleeType, null, player);

            victim.hurt(source,damage);
        });

        COURAGEOUS_BLOW_INDIRECT = BonusRegistry.register(BonusTrigger.MELEE_HURT_INDIRECT, (player, victim, level, data, cache) -> {
            float damage = (player.getHealth() * 0.02f);

            Holder<DamageType> trueType = level.registryAccess()
                    .registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(ModDamageTypes.TRUE_DAMAGE);
            DamageSource source = new DamageSource(trueType, null, player);

            victim.hurt(source, damage);
        });

        ARMOR_BREAK_DIRECT = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data, cache) -> {
            player.addEffect(new MobEffectInstance(ModEffects.STOLEN_ARMOR.get(), 80, 0, false, false,false));
            victim.addEffect(new MobEffectInstance(ModEffects.ARMOR_BREAK.get(), 80, 0, false, true,true));
        });

        ARMOR_BREAK_INDIRECT = BonusRegistry.register(BonusTrigger.MELEE_HURT_INDIRECT, (player, victim, level, data, cache) -> {
            player.addEffect(new MobEffectInstance(ModEffects.STOLEN_ARMOR.get(), 80, 0, false, false,false));
            victim.addEffect(new MobEffectInstance(ModEffects.ARMOR_BREAK.get(), 80, 0, false, true,true));
        });

        REVENGEANCE = BonusRegistry.register(BonusTrigger.DAMAGE_TAKE_ATTACK_EVENT, (player, victim, level, data, cache) -> {
            boolean isSuccess = player.getRandom().nextDouble() < 0.2;
            if (isSuccess) {
                float damage = (data[0] * 3);

                Holder<DamageType> trueType = level.registryAccess()
                        .registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(ModDamageTypes.TRUE_DAMAGE);
                DamageSource source = new DamageSource(trueType, null, player);

                victim.hurt(source, damage);
            }
        });

        ABSORBED_EVIL = BonusRegistry.register(BonusTrigger.DEBUFF_APPLIED, (player, victim, level, data, cache) -> {
            player.addEffect(new MobEffectInstance(ModEffects.OPPORTUNIST.get(), 200, 0, false, false,false));
        });


        SOUL_SEPARATION_DIRECT = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data, cache) -> {
            boolean isSuccess = player.getRandom().nextDouble() < 0.15;
            if (isSuccess) {
                ServerLevel sLevel = (ServerLevel) player.level();

                Holder<DamageType> meleeType = level.registryAccess()
                        .registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(ModDamageTypes.CUSTOM_MELEE_DAMAGE);
                DamageSource source = new DamageSource(meleeType, null, player);


                boolean buff = cache.isVortexSeveranceEnhanced();

                executeSS(sLevel, (ServerPlayer) player, 1000, 1000, source, buff, cache.isHealSeverance());
            }
        });

        SOUL_SEPARATION_INDIRECT = BonusRegistry.register(BonusTrigger.MELEE_HURT_INDIRECT, (player, victim, level, data, cache) -> {
            boolean isSuccess = player.getRandom().nextDouble() < 0.075;
            if (isSuccess) {
                ServerLevel sLevel = (ServerLevel) player.level();

                Holder<DamageType> meleeType = level.registryAccess()
                        .registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(ModDamageTypes.CUSTOM_MELEE_DAMAGE);
                DamageSource source = new DamageSource(meleeType, null, player);


                boolean buff = cache.isVortexSeveranceEnhanced();

                executeSS(sLevel, (ServerPlayer) player, 1000, 1000, source, buff, cache.isHealSeverance());
            }
        });

        EXECUTE = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data, cache) -> {
            float baseThreshold = 0.07f;
            float attributePercent = (float) getScaledValue(player, ModAttributes.MELEE_ACCURACY.get()) / 100f;
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

        BLOODWORM = BonusRegistry.register(BonusTrigger.KILL, (player, victim, level, data, cache) -> {
            MobEffectInstance effectInstance = player.getEffect(ModEffects.BLOODWORM.get());
            int currentStacks = (effectInstance != null) ? effectInstance.getAmplifier() : -1;

            player.addEffect(new MobEffectInstance(ModEffects.BLOODWORM.get(), 72000, Math.min(99, currentStacks+1), false, false, false));
        });

        HAWKEYE_STACK = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data, cache) -> {
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

        HAWKEYE_CONSUME_BOW = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_BOW, (player, victim, level, data, cache) -> {
            if (player.hasEffect(ModEffects.HAWKEYE.get())) player.removeEffect(ModEffects.HAWKEYE.get());
        });

        HAWKEYE_CONSUME_CROSSBOW = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_CROSSBOW, (player, victim, level, data, cache) -> {
            if (player.hasEffect(ModEffects.HAWKEYE.get())) player.removeEffect(ModEffects.HAWKEYE.get());
        });


        CRITICAL_ASCENSION_MELEE = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data, cache) -> {
            MobEffectInstance effectInstance = player.getEffect(ModEffects.SHARPENED_BLADE.get());
            int currentStacks = (effectInstance != null) ? effectInstance.getAmplifier() : -1;

            if (effectInstance != null) {
                currentStacks = Math.min(4, currentStacks);
            }
            player.addEffect(new MobEffectInstance(ModEffects.SHARPENED_BLADE.get(), 80, currentStacks + 1, false, false, false
            ));
        });

        CRITICAL_ASCENSION_RANGED = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_BOW, (player, victim, level, data, cache) -> {
            MobEffectInstance effectInstance = player.getEffect(ModEffects.SHARPENED_BLADE.get());
            int currentStacks = (effectInstance != null) ? effectInstance.getAmplifier() : -1;

            if (effectInstance != null) {
                currentStacks = Math.min(7, currentStacks);
            }
            player.addEffect(new MobEffectInstance(ModEffects.SHARPENED_BLADE.get(), 80, currentStacks + 1, false, false, false
            ));
        });

        CRITICAL_ASCENSION_RANGED_2 = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_CROSSBOW, (player, victim, level, data, cache) -> {
            MobEffectInstance effectInstance = player.getEffect(ModEffects.SHARPENED_BLADE.get());
            int currentStacks = (effectInstance != null) ? effectInstance.getAmplifier() : -1;

            if (effectInstance != null) {
                currentStacks = Math.min(6, currentStacks);
            }
            player.addEffect(new MobEffectInstance(ModEffects.SHARPENED_BLADE.get(), 80, currentStacks + 1, false, false, false
            ));
        });

        SNIPE_BOW = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_BOW_CRIT, (player, victim, level, data, cache) -> {
            double distance = player.distanceTo(victim);
            data[0] += (float) Math.min(distance/100, 0.25f);
        });

        SNIPE_CROSSBOW = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_CROSSBOW_CRIT, (player, victim, level, data, cache) -> {
            double distance = player.distanceTo(victim);
            data[0] += (float) Math.min(distance/100, 0.25f);
        });

        STEALTH = BonusRegistry.register(BonusTrigger.KILL, (player, victim, level, data, cache) -> {
            player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 60, 0, false, false, false));
            player.addEffect(new MobEffectInstance(ModEffects.STEALTH.get(), 60, 0, false, false, false));
        });

        BLEED_MELEE = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data, cache) -> {
            long expiry = level.getGameTime() + 80L;

            Map<UUID, Bleed.BleedData> victimSources = Bleed.activeBleed.computeIfAbsent(
                    victim.getUUID(), k -> new HashMap<>()
            );
            victim.addEffect(new MobEffectInstance(ModEffects.BLEED.get(), 80, 0, false, false, false));

            victimSources.compute(player.getUUID(), (uuid, oldData) -> {
                int newStacks = (oldData == null) ? 2 : oldData.stacks() + 2;
                return new Bleed.BleedData(Math.min(newStacks, 15), expiry);
            });
        });

        BLEED_INDIRECT_MELEE = BonusRegistry.register(BonusTrigger.MELEE_HURT_INDIRECT, (player, victim, level, data, cache) -> {
            long expiry = level.getGameTime() + 80L;

            Map<UUID, Bleed.BleedData> victimSources = Bleed.activeBleed.computeIfAbsent(
                    victim.getUUID(), k -> new HashMap<>()
            );
            victim.addEffect(new MobEffectInstance(ModEffects.BLEED.get(), 80, 0, false, false, false));

            victimSources.compute(player.getUUID(), (uuid, oldData) -> {
                int newStacks = (oldData == null) ? 1 : oldData.stacks() + 1;
                return new Bleed.BleedData(Math.min(newStacks, 15), expiry);
            });
        });

        BLEED_BOW = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_BOW, (player, victim, level, data, cache) -> {
            if (victim instanceof Player) return;

            long expiry = level.getGameTime() + 80L;

            Map<UUID, Bleed.BleedData> victimSources = Bleed.activeBleed.computeIfAbsent(
                    victim.getUUID(), k -> new HashMap<>()
            );
            victim.addEffect(new MobEffectInstance(ModEffects.BLEED.get(), 80, 0, false, false, false));

            victimSources.compute(player.getUUID(), (uuid, oldData) -> {
                int newStacks = (oldData == null) ? 1 : oldData.stacks() + 1;
                return new Bleed.BleedData(Math.min(newStacks, 15), expiry);
            });
        });

        BLEED_CROSSBOW = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_CROSSBOW, (player, victim, level, data, cache) -> {
            long expiry = level.getGameTime() + 80L;

            Map<UUID, Bleed.BleedData> victimSources = Bleed.activeBleed.computeIfAbsent(
                    victim.getUUID(), k -> new HashMap<>()
            );
            victim.addEffect(new MobEffectInstance(ModEffects.BLEED.get(), 80, 0, false, false, false));

            victimSources.compute(player.getUUID(), (uuid, oldData) -> {
                int newStacks = (oldData == null) ? 1 : oldData.stacks() + 1;
                return new Bleed.BleedData(Math.min(newStacks, 15), expiry);
            });
        });

        CHAIN_LIGHTNING_BOW = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_BOW, (player, victim, level, data, cache) -> {
            if (victim instanceof Player) return;



            float multiplier = 0.2f;

            if (cache.isChainLightningEnhanced()) {
                multiplier += 0.1f;
            }

            if (cache.isChainLightningActive()) {
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
                bounceDamage *= multiplier;
            }
        });

        CHAIN_LIGHTNING_CROSSBOW = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_CROSSBOW, (player, victim, level, data, cache) -> {


            float multiplier = 0.2f;

            if (cache.isChainLightningEnhanced()) {
                multiplier += 0.1f;
            }

            if (cache.isChainLightningActive()) {
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
                bounceDamage *= multiplier;
            }
        });

        BLADEDANCER_MELEE = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data, cache) -> {
            MobEffectInstance effectInstance = player.getEffect(ModEffects.BLADEDANCER.get());
            int currentStacks = (effectInstance != null) ? effectInstance.getAmplifier() : -1;

            if (effectInstance != null) {
                currentStacks = Math.min(3, currentStacks);
            }
            player.addEffect(new MobEffectInstance(ModEffects.BLADEDANCER.get(), 120, currentStacks + 1, false, false, false
            ));
        });

        BLADEDANCER_INDIRECT_MELEE = BonusRegistry.register(BonusTrigger.MELEE_HURT_INDIRECT, (player, victim, level, data, cache) -> {
            MobEffectInstance effectInstance = player.getEffect(ModEffects.BLADEDANCER.get());
            int currentStacks = (effectInstance != null) ? effectInstance.getAmplifier() : -1;

            if (effectInstance != null) {
                currentStacks = Math.min(3, currentStacks);
            }
            player.addEffect(new MobEffectInstance(ModEffects.BLADEDANCER.get(), 120, currentStacks + 1, false, false, false
            ));
        });

        BLADEDANCER_BOW = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_BOW, (player, victim, level, data, cache) -> {
            MobEffectInstance effectInstance = player.getEffect(ModEffects.BLADEDANCER.get());
            int currentStacks = (effectInstance != null) ? effectInstance.getAmplifier() : -1;

            if (effectInstance != null) {
                currentStacks = Math.min(3, currentStacks);
            }
            player.addEffect(new MobEffectInstance(ModEffects.BLADEDANCER.get(), 120, currentStacks + 1, false, false, false
            ));
        });

        BLADEDANCER_CROSSBOW = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_CROSSBOW, (player, victim, level, data, cache) -> {
            MobEffectInstance effectInstance = player.getEffect(ModEffects.BLADEDANCER.get());
            int currentStacks = (effectInstance != null) ? effectInstance.getAmplifier() : -1;

            if (effectInstance != null) {
                currentStacks = Math.min(3, currentStacks);
            }
            player.addEffect(new MobEffectInstance(ModEffects.BLADEDANCER.get(), 120, currentStacks + 1, false, false, false
            ));
        });

        VORTEX_BOW = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_BOW, (player, victim, level, data, cache) -> {
            boolean isSuccess = player.getRandom().nextDouble() < 0.15;
            if (isSuccess) {
                ServerLevel sLevel = (ServerLevel) player.level();

                Holder<DamageType> meleeType = level.registryAccess()
                        .registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(ModDamageTypes.DOT_DAMAGE);
                DamageSource source = new DamageSource(meleeType, null, player);


                boolean buff = cache.isVortexSeveranceEnhanced();
                boolean debuff = cache.isVortexEnhanced();

                executeVortex(sLevel, player, 1000, 1000, source, victim.position(), debuff, buff);
            }
        });

        VORTEX_BOW_GUARANTEE = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_BOW, (player, victim, level, data, cache) -> {

            if (!cache.isVortexReady()) return;

            ServerLevel sLevel = (ServerLevel) player.level();

            Holder<DamageType> rangedType = level.registryAccess()
                    .registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(ModDamageTypes.DOT_DAMAGE);
            DamageSource source = new DamageSource(rangedType, null, player);

            boolean debuff = cache.isVortexEnhanced();
            boolean buff = cache.isVortexSeveranceEnhanced();

            executeVortex(sLevel, player, 4000, 4000, source, victim.position(), debuff, buff);

            player.removeEffect(ModEffects.VORTEX_READY.get());
            player.addEffect(new MobEffectInstance(ModEffects.VORTEX_CD.get(), 480, 0, false, false, true));

        });

        VORTEX_CROSSBOW = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_CROSSBOW, (player, victim, level, data, cache) -> {
            boolean isSuccess = player.getRandom().nextDouble() < 0.05;
            if (isSuccess) {
                ServerLevel sLevel = (ServerLevel) player.level();

                Holder<DamageType> meleeType = level.registryAccess()
                        .registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(ModDamageTypes.DOT_DAMAGE);
                DamageSource source = new DamageSource(meleeType, null, player);


                boolean buff = cache.isVortexSeveranceEnhanced();
                boolean debuff = cache.isVortexEnhanced();

                executeVortex(sLevel, player, 1000, 1000, source, victim.position(), debuff, buff);
            }
        });

        GAMBIT_MELEE_NONCRIT = BonusRegistry.register(BonusTrigger.MELEE_NON_CRIT_DIRECT, (player, victim, level, data, cache) -> {
            MobEffectInstance effectInstance = player.getEffect(ModEffects.GAMBIT.get());
            int currentStacks = (effectInstance != null) ? effectInstance.getAmplifier() : -1;

            if (effectInstance != null) {
                currentStacks = Math.min(4, currentStacks);
            }
            player.addEffect(new MobEffectInstance(ModEffects.GAMBIT.get(), 200, currentStacks + 1, false, false, false
            ));
        });

        GAMBIT_MELEE_NONCRIT_INDIRECT = BonusRegistry.register(BonusTrigger.MELEE_NON_CRIT_INDIRECT, (player, victim, level, data, cache) -> {
            MobEffectInstance effectInstance = player.getEffect(ModEffects.GAMBIT.get());
            int currentStacks = (effectInstance != null) ? effectInstance.getAmplifier() : -1;

            if (effectInstance != null) {
                currentStacks = Math.min(4, currentStacks);
            }
            player.addEffect(new MobEffectInstance(ModEffects.GAMBIT.get(), 200, currentStacks + 1, false, false, false
            ));
        });

        GAMBIT_BOW_NONCRIT = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_BOW_NON_CRIT, (player, victim, level, data, cache) -> {
            MobEffectInstance effectInstance = player.getEffect(ModEffects.GAMBIT.get());
            int currentStacks = (effectInstance != null) ? effectInstance.getAmplifier() : -1;

            if (effectInstance != null) {
                currentStacks = Math.min(4, currentStacks);
            }
            player.addEffect(new MobEffectInstance(ModEffects.GAMBIT.get(), 200, currentStacks + 1, false, false, false
            ));
        });

        GAMBIT_CROSSBOW_NONCRIT = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_CROSSBOW_NON_CRIT, (player, victim, level, data, cache) -> {
            MobEffectInstance effectInstance = player.getEffect(ModEffects.GAMBIT.get());
            int currentStacks = (effectInstance != null) ? effectInstance.getAmplifier() : -1;

            if (effectInstance != null) {
                currentStacks = Math.min(4, currentStacks);
            }
            player.addEffect(new MobEffectInstance(ModEffects.GAMBIT.get(), 200, currentStacks + 1, false, false, false));
        });

        GAMBIT_RANGE_INDIRECT_NONCRIT = BonusRegistry.register(BonusTrigger.INDIRECT_RANGE_NONCRIT, (player, victim, level, data, cache) -> {
            MobEffectInstance effectInstance = player.getEffect(ModEffects.GAMBIT.get());
            int currentStacks = (effectInstance != null) ? effectInstance.getAmplifier() : -1;

            if (effectInstance != null) {
                currentStacks = Math.min(4, currentStacks);
            }
            player.addEffect(new MobEffectInstance(ModEffects.GAMBIT.get(), 200, currentStacks + 1, false, false, false
            ));
        });

        GAMBIT_OTHER_CRIT = BonusRegistry.register(BonusTrigger.OTHER_CRIT, (player, victim, level, data, cache) -> {
            player.removeEffect(ModEffects.GAMBIT.get());
        });

        GAMBIT_OTHER_NONCRIT = BonusRegistry.register(BonusTrigger.OTHER_NONCRIT, (player, victim, level, data, cache) -> {
            MobEffectInstance effectInstance = player.getEffect(ModEffects.GAMBIT.get());
            int currentStacks = (effectInstance != null) ? effectInstance.getAmplifier() : -1;

            if (effectInstance != null) {
                currentStacks = Math.min(4, currentStacks);
            }
            player.addEffect(new MobEffectInstance(ModEffects.GAMBIT.get(), 200, currentStacks + 1, false, false, false
            ));
        });

        GAMBIT_RANGE_INDIRECT_CRIT = BonusRegistry.register(BonusTrigger.INDIRECT_RANGE_CRIT, (player, victim, level, data, cache) -> {
            player.removeEffect(ModEffects.GAMBIT.get());
        });

        GAMBIT_MELEE_CRIT = BonusRegistry.register(BonusTrigger.MELEE_CRIT_DIRECT, (player, victim, level, data, cache) -> {
            player.removeEffect(ModEffects.GAMBIT.get());
        });

        GAMBIT_MELEE_CRIT_INDIRECT = BonusRegistry.register(BonusTrigger.MELEE_CRIT_INDIRECT, (player, victim, level, data, cache) -> {
            player.removeEffect(ModEffects.GAMBIT.get());
        });

        GAMBIT_BOW_CRIT = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_BOW_CRIT, (player, victim, level, data, cache) -> {
            player.removeEffect(ModEffects.GAMBIT.get());
        });

        GAMBIT_CROSSBOW_CRIT = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_CROSSBOW_CRIT, (player, victim, level, data, cache) -> {
            player.removeEffect(ModEffects.GAMBIT.get());
        });

        HEARTPIERCER_BOW = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_BOW, (player, victim, level, data, cache) -> {
            if (!victim.hasEffect(ModEffects.HEARTPIERCER_CD.get())) {
                victim.addEffect(new MobEffectInstance(ModEffects.HEARTPIERCER.get(), 140, 0, true, true, false));
                victim.addEffect(new MobEffectInstance(ModEffects.HEARTPIERCER_CD.get(), 240, 0, true, true, false));
                victim.addEffect(new MobEffectInstance(MobEffects.GLOWING, 140, 0, true, true, false));
            }
        });

        HEARTPIERCER_CROSSBOW = BonusRegistry.register(BonusTrigger.PROJECTILE_HURT_CROSSBOW, (player, victim, level, data, cache) -> {
            if (!victim.hasEffect(ModEffects.HEARTPIERCER_CD.get())) {
                victim.addEffect(new MobEffectInstance(ModEffects.HEARTPIERCER.get(), 140, 0, true, true, false));
                victim.addEffect(new MobEffectInstance(ModEffects.HEARTPIERCER_CD.get(), 240, 0, true, true, false));
                victim.addEffect(new MobEffectInstance(MobEffects.GLOWING, 140, 0, true, true, false));
            }
        });

        STEEL_DEFENSE = BonusRegistry.register(BonusTrigger.DAMAGE_TAKE_DAMAGE_EVENT_POST, (player, victim, level, data, cache) -> {
            if ((player.getHealth() - data[1]) <= player.getMaxHealth()/2) {
                player.heal(0.15f);
            }
        });

        BRIMSTONE_SHIELD = BonusRegistry.register(BonusTrigger.BLESSING_ACTIVATION, (player, victim, level, data, cache) -> {
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
                player.addEffect(new MobEffectInstance(ModEffects.BRIMSTONE.get(), 120, 0, false, false, false));
            }
        });

        ARMOR_PIERCE = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data, cache) -> {
            boolean isSuccess = player.getRandom().nextDouble() < 0.10;
            if (!isSuccess) return;
            victim.addEffect(new MobEffectInstance(ModEffects.ARMOR_PIERCE.get(), 100, 0, true, true, false));
            victim.addEffect(new MobEffectInstance(ModEffects.STUN.get(), 40, 0, true, true, false));
        });

        ABSORPTION = BonusRegistry.register(BonusTrigger.DAMAGE_TAKE_DAMAGE_EVENT_POST, (player, victim, level, data, cache) -> {
            float maxHealth = player.getMaxHealth();
            if ((player.getHealth() - data[1]) <= maxHealth) {
                player.heal((float) (maxHealth * 0.02));
            }
        });

        DEFLECT = BonusRegistry.register(BonusTrigger.DAMAGE_TAKE_DAMAGE_EVENT, (player, victim, level, data, cache) -> {
            float randomDeflection = 0.01f + (player.getRandom().nextFloat() * 0.07f);
            data[0] -= randomDeflection;
        });

        RETALIATING_BLOW = BonusRegistry.register(BonusTrigger.DEFEND, (player, victim, level, data, cache) -> {
            player.addEffect(new MobEffectInstance(ModEffects.RETALIATED.get(), 80, 0, false, true, false));
        });

        FLOATING_CURSE = BonusRegistry.register(BonusTrigger.DAMAGE_TAKE_DAMAGE_EVENT, (player, victim, level, data, cache) -> {
            boolean isSuccess = player.getRandom().nextDouble() < 0.1;
            if (isSuccess) {
                victim.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 60, 0, false, false, false));
                victim.addEffect(new MobEffectInstance(ModEffects.LEVITATION_CURSE.get(), 61, 0, false, false, false));
            }
        });

        FIREWIELDER = BonusRegistry.register(BonusTrigger.BLESSING_ACTIVATION, (player, victim, level, data, cache) -> {
            double radiusSq = 36.0;
            List<LivingEntity> entities = level.getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(6.0),
                    e -> {
                        if (!isAlly(e) || !player.hasLineOfSight(e)) return false;
                        double dx = e.getX() - player.getX();
                        double dz = e.getZ() - player.getZ();
                        return (dx * dx + dz * dz) <= radiusSq;
                    }
            );

            for (LivingEntity target : entities) {
                spawnParticleBurst(target, ParticleTypes.HAPPY_VILLAGER);
                player.addEffect(new MobEffectInstance(ModEffects.PRECISE_BLOW.get(), 80, 0, false, false, false));
            }
        });

        DESTROYER = BonusRegistry.register(BonusTrigger.DAMAGE_TAKE_DAMAGE_EVENT, (player, victim, level, data, cache) -> {
            boolean isSuccess = player.getRandom().nextDouble() < 0.3;

            if (isSuccess) {

                Holder<DamageType> meleeType = level.registryAccess()
                        .registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(ModDamageTypes.TRUE_DAMAGE);
                DamageSource source = new DamageSource(meleeType, null, player);

                if (player.getRandom().nextBoolean()) {
                    double fort = player.getAttributeValue(ModAttributes.FORT.get());
                    victim.hurt(source, (float) (fort * 0.15f));
                } else {
                    double con = player.getAttributeValue(ModAttributes.CON.get());
                    victim.hurt(source, (float) (con * 0.15f));
                }
            }
        });

        IMMORTAL = BonusRegistry.register(BonusTrigger.DAMAGE_TAKE_DAMAGE_EVENT_POST, (player, victim, level, data, cache) -> {
            boolean isSuccess = player.getRandom().nextDouble() < 0.2;
            if (isSuccess) {
                int randomDebuff = player.getRandom().nextInt(5) + 1;
                Component message;

                switch (randomDebuff) {
                    case 1:
                        int debuff = player.getRandom().nextInt(6);
                        int dex = (int) player.getAttributeValue(ModAttributes.DEX.get());

                        // 3.3% per
                        double dexDuration33 = 1.0 + (dex / 30f);

                        // 1% per
                        double dexDuration1 = 1.0 + (dex / 100f);

                        applyRandomDebuff(victim, player, debuff, dex, dexDuration33, dexDuration1, (ServerLevel) level);
                        break;
                    case 2:
                        player.addEffect(new MobEffectInstance(ModEffects.BASTION_ACTIVE.get(), 20, 0, false, false, true));
                        message = Component.literal("Bastion").withStyle(ChatFormatting.GOLD);
                        player.displayClientMessage(message, true);
                        break;
                    case 3:
                        executeDirectedHatred((ServerPlayer) player, cache);
                        message = Component.literal("Hatred").withStyle(ChatFormatting.DARK_RED);
                        player.displayClientMessage(message, true);
                        break;
                    case 4:

                        boolean buff = cache.isVortexSeveranceEnhanced();
                        boolean debuffVortex = cache.isVortexEnhanced();

                        Holder<DamageType> rangedType = level.registryAccess()
                                .registryOrThrow(Registries.DAMAGE_TYPE)
                                .getHolderOrThrow(ModDamageTypes.DOT_DAMAGE);
                        DamageSource source = new DamageSource(rangedType, null, player);

                        executeVortex((ServerLevel) level, player, 4000, 4000, source, victim.position(), debuffVortex, buff);
                        message = Component.literal("Vortex").withStyle(ChatFormatting.BLUE);
                        player.displayClientMessage(message, true);
                        break;
                    case 5:
                        performDivinityPulse(player);
                        message = Component.literal("Sanctuary").withStyle(ChatFormatting.GREEN);
                        player.displayClientMessage(message, true);
                        break;
                }
            }
        });

        REVITALIZE = BonusRegistry.register(BonusTrigger.KILL, (player, victim, level, data, cache) -> {
            double radiusSq = 36.0;
            List<LivingEntity> entities = level.getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(6.0),
                    e -> {
                        if (!isAlly(e) || !player.hasLineOfSight(e)) return false;
                        double dx = e.getX() - player.getX();
                        double dz = e.getZ() - player.getZ();
                        return (dx * dx + dz * dz) <= radiusSq;
                    }
            );

            for (LivingEntity target : entities) {
                target.heal(2f);
                EffectUtils.playHealSound(target);
                EffectUtils.spawnComposterBurst(target);
            }
        });

        HOLY_AVENGER = BonusRegistry.register(BonusTrigger.BLESSING_ACTIVATION, (player, victim, level, data, cache) -> {
            double radiusSq = 25.0;
            List<LivingEntity> entities = level.getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(5.0),
                    e -> {
                        if (!isAlly(e) || !player.hasLineOfSight(e)) return false;
                        double dx = e.getX() - player.getX();
                        double dz = e.getZ() - player.getZ();
                        return (dx * dx + dz * dz) <= radiusSq;
                    }
            );

            for (LivingEntity target : entities) {
                spawnParticleBurst(target, ParticleTypes.DRIPPING_HONEY);
                target.addEffect(new MobEffectInstance(ModEffects.HOLY_AVENGER.get(), 140, 0, false, false, false));
            }
        });



        RECOVERY = BonusRegistry.register(BonusTrigger.DEBUFF_APPLIED, (player, victim, level, data, cache) -> {
            double radiusSq = 36.0;
            List<LivingEntity> entities = level.getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(6.0),
                    e -> {
                        if (!isAlly(e) || !player.hasLineOfSight(e)) return false;
                        double dx = e.getX() - player.getX();
                        double dz = e.getZ() - player.getZ();
                        return (dx * dx + dz * dz) <= radiusSq;
                    }
            );

            for (LivingEntity target : entities) {
                target.addEffect(new MobEffectInstance(ModEffects.RECOVERY.get(), 260, 0, false, false, false));
            }
        });

        REGENERATION = BonusRegistry.register(BonusTrigger.BLESSING_ACTIVATION, (player, victim, level, data, cache) -> {
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
                target.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0, false, false, false));
            }
        });

        PURIFY = BonusRegistry.register(BonusTrigger.BLESSING_ACTIVATION, (player, victim, level, data, cache) -> {
            level.getEntitiesOfClass(Player.class, player.getBoundingBox().inflate(5),
                    target -> target != player && target.isAlive()).forEach(ally -> {

                ally.getActiveEffects().stream()
                        .filter(effect -> !effect.getEffect().isBeneficial())
                        .findFirst()
                        .ifPresent(effect -> {
                            ally.removeEffect(effect.getEffect());
                        });
            });
        });

        SOUL_HARVEST = BonusRegistry.register(BonusTrigger.KILL, (player, victim, level, data, cache) -> {
            MobEffectInstance effectInstance = player.getEffect(ModEffects.SOUL_HARVEST.get());
            int currentStacks = (effectInstance != null) ? effectInstance.getAmplifier() : -1;

            player.addEffect(new MobEffectInstance(ModEffects.SOUL_HARVEST.get(), 36000, Math.min(29, currentStacks+1), false, false, false));
        });

        SOULEATER = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data, cache) -> {
            boolean isSuccess = player.getRandom().nextDouble() < 0.15;

            if (isSuccess) {

                Holder<DamageType> meleeType = level.registryAccess()
                        .registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(ModDamageTypes.TRUE_DAMAGE);
                DamageSource source = new DamageSource(meleeType, null, player);

                double rest = player.getAttributeValue(ModAttributes.RESTORATION.get()) * 0.06;
                double amp = player.getAttributeValue(ModAttributes.AMPLIFICATION.get()) * 0.06;
                if (victim != null) victim.hurt(source, (float) (rest + amp));

            }
        });

        SOULEATER_INDIRECT = BonusRegistry.register(BonusTrigger.MELEE_HURT_INDIRECT, (player, victim, level, data, cache) -> {
            boolean isSuccess = player.getRandom().nextDouble() < 0.075;

            if (isSuccess) {

                Holder<DamageType> meleeType = level.registryAccess()
                        .registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(ModDamageTypes.TRUE_DAMAGE);
                DamageSource source = new DamageSource(meleeType, null, player);

                double rest = player.getAttributeValue(ModAttributes.RESTORATION.get()) * 0.06;
                double amp = player.getAttributeValue(ModAttributes.AMPLIFICATION.get()) * 0.06;
                if (victim != null) victim.hurt(source, (float) (rest + amp));

            }
        });

        LAND_SPAWN = BonusRegistry.register(BonusTrigger.DAMAGE_TAKE_DAMAGE_EVENT_POST, (player, victim, level, data, cache) -> {
            boolean isSuccess = player.getRandom().nextDouble() < 0.25;

            if (isSuccess && victim != null) {
                Vec3 targetPos = player.position();

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
            }
        });

        VAMPIRIC_TOUCH_DIRECT = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data, cache) -> {
            float healRatio = 0.04f;
            player.heal(data[0] * data[1] * healRatio);
        });

        VAMPIRIC_TOUCH_INDIRECT = BonusRegistry.register(BonusTrigger.MELEE_HURT_INDIRECT, (player, victim, level, data, cache) -> {
            float healRatio = 0.02f;
            player.heal(data[0] * data[1] * healRatio);
        });

        ENTWINED_OFFERING = BonusRegistry.register(BonusTrigger.HEAL, (player, victim, level, data, cache) -> {
            if (player.getHealth() >= player.getMaxHealth()) return;

            float range = 4f;
            int armorDuration = (int) (20 * 3 * (1.0f + (getScaledValue(player, ModAttributes.AMPLIFICATION.get()) / 100.0f)));

            double rangeSq = 16f;
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
                    target.addEffect(new MobEffectInstance(ModEffects.ENTWINED_OFFERING_ACTIVE.get(), armorDuration, 0, false, false, false));
                }
            }
        });

        CHAIN_LIGHTNING = BonusRegistry.register(BonusTrigger.MELEE_HURT, (player, victim, level, data, cache) -> {
            float multiplier = 0.3f;

            if (cache.isChainLightningEnhanced()) {
                multiplier += 0.125f;
            }

            if (cache.getLastUsedAttack() == 1) multiplier /= 2;

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
                bounceDamage *= multiplier;
            }
        });

        RADIATING_STRIKE = BonusRegistry.register(BonusTrigger.HURT, (player, victim, level, data, cache) -> {
            boolean isSuccess = player.getRandom().nextDouble() < 0.2;
            if (!isSuccess) return;

            List<Player> allies = level.getEntitiesOfClass(Player.class, player.getBoundingBox().inflate(8.0));

            Player target = allies.stream()
                    .filter(player::hasLineOfSight)
                    .filter(player::hasLineOfSight)
                    .filter(p -> p.distanceToSqr(player) < 36.0)
                    .min(Comparator.comparingDouble(Player::getHealth))
                    .orElse(null);

            if (target != null) {
                float healAmount = data[0] * data[1] * 0.15f;
                target.heal(healAmount);
                EffectUtils.spawnComposterBurst(target);
            }
        });

        ASCENSION = BonusRegistry.register(BonusTrigger.DAMAGE_TAKE_DAMAGE_EVENT_POST, (player, victim, level, data, cache) -> {
            float hunger = player.getFoodData().getFoodLevel() - 2;
            float saturation = player.getFoodData().getSaturationLevel();

            if ((player.getHealth() - data[1]) <= 2f && !player.hasEffect(ModEffects.ASCENSION_CD.get())) {
                player.heal((hunger/4 + saturation/4));
                player.getFoodData().setFoodLevel(2);
                player.getFoodData().setSaturation(0);

                player.addEffect(new MobEffectInstance(ModEffects.ASCENSION_CD.get(), 12000, 0, false, false, false));
            }
        });
    }
}
