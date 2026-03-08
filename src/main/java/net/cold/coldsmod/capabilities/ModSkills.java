package net.cold.coldsmod.capabilities;

import net.cold.coldsmod.ModSounds;
import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils;
import net.cold.coldsmod.blessingbonuses.neweffects.VortexReady;
import net.cold.coldsmod.damage.ModDamageTypes;
import net.cold.coldsmod.stat.ItemRarityUtils;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static net.cold.coldsmod.blessingbonuses.CooldownCycle.HAWKEYE_UUID;
import static net.cold.coldsmod.blessingbonuses.effects.SoulSeveranceActive.*;
import static net.cold.coldsmod.blessingbonuses.neweffects.BlessedLandReady.spawnBlessedLand;
import static net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils.*;
import static net.cold.coldsmod.blessingbonuses.neweffects.PulsatingLove.applyTamedAura;
import static net.cold.coldsmod.blessingbonuses.neweffects.Sanctuary.*;
import static net.cold.coldsmod.blessingbonuses.neweffects.SummoningStone.runSbeveUpdateLogic;
import static net.cold.coldsmod.blessingbonuses.neweffects.ThornedParryReady.triggerParryExplosion;
import static net.cold.coldsmod.blessingbonuses.neweffects.VortexReady.activeVortices;
import static net.cold.coldsmod.blessingbonuses.skills.BronzewoodApply.curseSources;
import static net.cold.coldsmod.blessingbonuses.skills.ExploitWeaknessApply.applyRandomDebuff;
import static net.cold.coldsmod.blessingbonuses.skills.ExploitWeaknessApply.applyRandomDebuffHalved;
import static net.cold.coldsmod.blessingbonuses.skills.IntoTheFraySkill.*;
import static net.cold.coldsmod.stat.AttributeApplier.getScaledValue;
import static net.cold.coldsmod.stat.AttributeApplier.removeModifier;
import static net.cold.coldsmod.stat.ItemRarityUtils.getItemType;

public class ModSkills {
    public static final Map<String, ISkillEffect> REGISTRY = new HashMap<>();

    public static void init() {
        register("quantumLeap", new ISkillEffect() {
            @Override
            public void anyHit(Player player, LivingEntity target) {
                if (!player.hasEffect(ModEffects.QUANTUM_LEAP_ACTIVE.get())) return;

                CompoundTag tag = player.getPersistentData();
                player.removeEffect(ModEffects.QUANTUM_LEAP_ACTIVE.get());

                if (tag.getBoolean("invis_added")) {
                    player.removeEffect(MobEffects.INVISIBILITY);
                    tag.putBoolean("invis_added", false);
                }
            }
            @Override public String getName() { return "QuantumLeap"; }
        });

        register("vortex", new ISkillEffect() {
            @Override
            public void onProjectileImpactBow(Player player, Projectile projectile, Vec3 hitPos) {
                if (!(projectile instanceof Arrow arrow)) return;
                if (!player.hasEffect(ModEffects.VORTEX_READY.get())) return;
                activeVortices.add(new VortexReady.VortexInstance(hitPos, player, arrow.level()));
                player.removeEffect(ModEffects.VORTEX_READY.get());
                player.addEffect(new MobEffectInstance(ModEffects.VORTEX_CD.get(), 360, 0, false, false, true));
            }

            @Override public String getName() { return "Vortex"; }
        });

        register("bastion", new ISkillEffect() {
            @Override
            public void useItemEventEnd(Player player, LivingEntity mob, ItemStack item) {
                if (!"shield".equals(getItemType(item))) return;
                if (!player.hasEffect(ModEffects.BASTION_READY.get())) return;

                double fort = player.getAttributeValue(ModAttributes.FORT.get());
                double perc = player.getAttributeValue(ModAttributes.PERC.get());
                double con = player.getAttributeValue(ModAttributes.CON.get());

                double seconds = 1.5 + 0.0075 * fort + 0.005 * perc + 0.005 * con;
                int ticks = (int)(seconds * 20);

                player.addEffect(new MobEffectInstance(ModEffects.BASTION_ACTIVE.get(), ticks, 0, false, false, true));
                player.removeEffect(ModEffects.BASTION_READY.get());

                if (player instanceof ServerPlayer serverPlayer) {
                    serverPlayer.playNotifySound(
                            ModSounds.GUARDIAN_ANGEL.get(), SoundSource.PLAYERS,
                            0.4F, 1.0F);
                }
            }

            @Override public String getName() { return "Bastion"; }
        });

        register("focused_energy", new ISkillEffect() {
            @Override
            public void onProjectileImpactCrossbow(Player player, Projectile projectile, HitResult hit) {
                if (!(projectile instanceof AbstractArrow arrow)) return;
                if (!player.hasEffect(ModEffects.FOCUSED_ENERGY_READY.get())) return;

                Level level = arrow.level();
                Vec3 hitVec = hit.getLocation();
                double radius = 5.0;

                // --- PLAYER LAUNCH LOGIC ---
                double distance = player.position().distanceTo(hitVec);
                if (distance <= radius) {
                    Vec3 playerVec = player.position().add(0, 0.5, 0);
                    Vec3 rawDir = playerVec.subtract(hitVec);
                    double ratio = 1.0 - (distance / radius);

                    double horizontalPower = 2.0;
                    double verticalPower = 1.5;

                    Vec3 launchDir = rawDir.normalize();
                    player.setDeltaMovement(new Vec3(
                            launchDir.x * horizontalPower * ratio,
                            verticalPower * ratio,
                            launchDir.z * horizontalPower * ratio
                    ));
                    player.fallDistance = 0;
                    player.hurtMarked = true;
                }

                // --- EFFECTS & PARTICLES ---
                if (level instanceof ServerLevel slevel) {
                    EffectUtils.spawnExplosionAt(slevel, hitVec);
                    EffectUtils.playExplosionSoundAt(level, hitVec, 0.5F);
                    spawnParticleRing(slevel, hitVec, ParticleTypes.POOF, 5.0, 100);
                }

                double searchRadius = 5.0;
                AABB explosionBox = new AABB(
                        hitVec.x - searchRadius, hitVec.y - searchRadius, hitVec.z - searchRadius,
                        hitVec.x + searchRadius, hitVec.y + searchRadius, hitVec.z + searchRadius
                );
                double radiusSq = 25.0;

                List<LivingEntity> entities = level.getEntitiesOfClass(
                        LivingEntity.class,
                        explosionBox,
                        e -> {
                            if (!(e instanceof Enemy) || !e.isAlive() || e.isInvulnerable()) return false;

                            if (level.clip(new ClipContext(hitVec, e.getEyePosition(), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, e)).getType().equals(HitResult.Type.MISS)) {
                                double dx = e.getX() - hitVec.x;
                                double dy = e.getY() - hitVec.y;
                                double dz = e.getZ() - hitVec.z;

                                return (dx * dx + dy * dy + dz * dz) <= radiusSq;
                            }
                            return false;
                        }
                );

                for (LivingEntity target : entities) {
                    target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 6));
                }

                // --- STATUS UPDATES ---
                player.removeEffect(ModEffects.FOCUSED_ENERGY_READY.get());
                player.addEffect(new MobEffectInstance(ModEffects.FOCUSED_ENERGY_CD.get(), 400, 0, false, false, true));
            }

            @Override public String getName() { return "Focused Energy"; }
        });

        register("sbeve", new ISkillEffect() {
            @Override
            public void onTick(Player player, LivingEntity target) {
                if (player.tickCount % 1200 != 0) return;

                runSbeveUpdateLogic(player);
            }
            @Override public String getName() { return "Sbeve"; }
        });

        register("thornedParry", new ISkillEffect() {
            @Override
            public void onDamageTaken(Player player, LivingEntity mob) {
                if (player.tickCount % 1200 != 0) return;

                if (!player.hasEffect(ModEffects.THORNED_PARRY_READY.get())) return;

                if (player.isBlocking() && mob instanceof Monster monster) {
                    player.getPersistentData().putInt("parry_time", 8);
                    player.getPersistentData().putUUID("last_attacker_uuid", monster.getUUID());
                }
            }

            @Override
            public void useItemEventEnd(Player player, LivingEntity mob, ItemStack item) {
                if (!player.hasEffect(ModEffects.THORNED_PARRY_READY.get())) return;
                if (!"shield".equals(getItemType(item))) return;

                int parryTimer = player.getPersistentData().getInt("parry_time");
                if (parryTimer > 0) {
                    triggerParryExplosion(player);
                    player.getPersistentData().putInt("parry_time", 0);
                    player.removeEffect(ModEffects.THORNED_PARRY_READY.get());
                }
            }

            @Override
            public void onTick(Player player, LivingEntity mob) {
                CompoundTag data = player.getPersistentData();
                int parryTimer = data.getInt("parry_time");
                if (parryTimer > 0) {
                    data.putInt("parry_time", parryTimer - 1);
                }
            }

            @Override public String getName() { return "thornedParry"; }
        });

        register("pulsatingLove", new ISkillEffect() {
            @Override
            public void onTick(Player player, LivingEntity target) {
                if (player.hasEffect(ModEffects.SOLARA.get())) return;

                int playerOffset = Math.abs(player.getUUID().hashCode() % 200);
                if ((player.tickCount + playerOffset) % 200 == 0) {
                    applyTamedAura(player);
                }
            }
            @Override public String getName() { return "PulsatingLove"; }
        });

        register("sanctuary", new ISkillEffect() {
            @Override
            public void useItemEventStart(Player player, LivingEntity target, ItemStack item) {
                if (!"shield".equals(ItemRarityUtils.getItemType(item))) return;

                CompoundTag tag = player.getPersistentData();
                tag.putInt(BLOCK_TICKS, 0);
                tag.putInt(PULSE_TICKS, 0);
            }

            @Override
            public void useItemEventTick(Player player, LivingEntity target, ItemStack item) {
                CompoundTag tag = player.getPersistentData();
                if (!"shield".equals(ItemRarityUtils.getItemType(item))) return;

                int blockTicks = player.getTicksUsingItem();

                if (blockTicks >= CHANNEL_DELAY) {
                    int pulseTicks = tag.getInt(PULSE_TICKS) + 1;

                    if (pulseTicks >= PULSE_INTERVAL) {
                        performDivinityPulse(player);
                        tag.putInt(PULSE_TICKS, 0);
                    } else {
                        tag.putInt(PULSE_TICKS, pulseTicks);
                    }
                }
            }

            @Override
            public void useItemEventEnd(Player player, LivingEntity target, ItemStack item) {
                if (!"shield".equals(ItemRarityUtils.getItemType(item))) return;

                CompoundTag tag = player.getPersistentData();
                tag.putInt(BLOCK_TICKS, 0);
                tag.putInt(PULSE_TICKS, 0);
            }
            @Override public String getName() { return "Sanctuary"; }
        });

        register("entwinedOffering", new ISkillEffect() {
            @Override
            public void onHeal(Player player, LivingEntity target, float amount) {
                if (player.getHealth() >= player.getMaxHealth()) return;

                Level level = player.level();

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

                for (LivingEntity targetToHeal : entities) {
                    if (targetToHeal.getHealth() < targetToHeal.getMaxHealth()) {
                        targetToHeal.heal((float) (amount * 0.3));
                        targetToHeal.addEffect(new MobEffectInstance(ModEffects.ENTWINED_OFFERING_ACTIVE.get(), armorDuration, 0, false, false, true));
                    }
                }
            }
            @Override public String getName() { return "EntwinedOffering"; }
        });

        register("blessedLand", new ISkillEffect() {
            @Override
            public void onDirectMeleeHit(Player player, LivingEntity target) {
                if (!player.hasEffect(ModEffects.BLESSED_LAND_READY.get())) return;

                Level level = player.level();
                Vec3 targetPos = target.position();

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
            }
            @Override public String getName() { return "BlessedLand"; }
        });

        register("bronzewood", new ISkillEffect() {
            @Override
            public void onDirectMeleeHit(Player player, LivingEntity target) {
                if (player.hasEffect(ModEffects.BRONZEWOOD_READY.get())) {
                    Level level = player.level();
                    Holder<DamageType> meleeType = level.registryAccess()
                            .registryOrThrow(Registries.DAMAGE_TYPE)
                            .getHolderOrThrow(ModDamageTypes.DOT_DAMAGE);

                    DamageSource source = new DamageSource(meleeType, player, player);
                    target.hurt(source, 3.0f);

                    target.addEffect(new MobEffectInstance(ModEffects.BRONZEWOOD_CURSE.get(), 20 * 10, 0, false, true, false));
                    if (player.getPersistentData().contains("enchanted_blade")) target.addEffect(new MobEffectInstance(ModEffects.ENCHANTED_BRONZEWOOD.get(), 20 * 10, 0, false, false, true));

                    player.level().playSound(
                            null, player.getX(), player.getY(), player.getZ(),
                            SoundEvents.SOUL_ESCAPE, SoundSource.PLAYERS,
                            7F, 1.0F
                    );

                    EffectUtils.spawnParticleBurst(target, ParticleTypes.SCULK_SOUL);

                    curseSources.put(target, player.getUUID());

                    player.addEffect(new MobEffectInstance(ModEffects.BRONZEWOOD_COOLDOWN.get(), 300, 0, false, false, true));
                    player.removeEffect(ModEffects.BRONZEWOOD_READY.get());
                }
            }

            @Override
            public void onKill(Player player, LivingEntity target) {
                if (player.hasEffect(ModEffects.BRONZEWOOD_COOLDOWN.get())) {

                    player.removeEffect(ModEffects.BRONZEWOOD_COOLDOWN.get());
                    player.addEffect(new MobEffectInstance(ModEffects.BRONZEWOOD_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
                }
            }
            @Override public String getName() { return "Bronzewood"; }
        });

        register("chainLightning", new ISkillEffect() {
            @Override
            public void onDirectMeleeHit(Player player, LivingEntity target, double damage) {
                if (!player.getPersistentData().getBoolean("procChainLightning")) return;
                player.getPersistentData().putBoolean("procChainLightning", false);


                Level level = player.level();
                LivingEntity originalTarget = target;
                double bounceDamage = damage * 0.35;

                Holder<DamageType> lightningType = level.registryAccess()
                        .registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(ModDamageTypes.LIGHTNING_DAMAGE);

                LivingEntity finalOriginalTarget = originalTarget;
                List<LivingEntity> nearby = level.getEntitiesOfClass(
                        LivingEntity.class,
                        originalTarget.getBoundingBox().inflate(4.0),
                        e -> e != null && e.isAlive() && e != finalOriginalTarget && (
                                (e instanceof Enemy && !(e instanceof NeutralMob)) ||
                                        (e instanceof NeutralMob n && n.isAngry()) ||
                                        (e instanceof Mob m && m.getTarget() != null)
                        )
                );

                for (LivingEntity next : nearby) {
                    if (bounceDamage < 1) break;

                    DamageSource source = new DamageSource(lightningType, player, player);
                    next.hurt(source, (float) bounceDamage);

                    double startX = originalTarget.getX();
                    double startY = originalTarget.getY() + 1.2;
                    double startZ = originalTarget.getZ();

                    double dx = next.getX() - startX;
                    double dy = (next.getY() + 1.2) - startY;
                    double dz = next.getZ() - startZ;

                    double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
                    int particleCount = (int) (distance * 4);

                    for (int i = 0; i < particleCount; i++) {
                        double ratio = (double) i / particleCount;
                        ((ServerLevel)level).sendParticles(ParticleTypes.ELECTRIC_SPARK,
                                startX + (dx * ratio), startY + (dy * ratio), startZ + (dz * ratio),
                                1, 0, 0, 0, 0.0);
                    }

                    originalTarget = next;
                    double mult = 0.35;
                    if (player.getPersistentData().contains("enchanted_blade")) mult += 0.1;
                    bounceDamage *= mult;
                }
            }
            @Override public String getName() { return "ChainLightning"; }
        });

        register("soulSeverance", new ISkillEffect() {
            @Override
            public void onTick(Player player, LivingEntity target) {
                MobEffectInstance readyEffect = player.getEffect(ModEffects.SOUL_SEVERANCE_READY.get());
                if (readyEffect == null) return;

                Level level = player.level();
                CompoundTag data = player.getPersistentData();
                int ticks = data.getInt("pull_ticks");

                if (!player.isCrouching()) {
                    if (ticks > 0) resetSoulSeverance(player, data);
                    return;
                }

                double range = 6.0;
                double radiusSq = range * range;
                double pullStrength = 0.05;
                boolean isPulseTick = ticks % 20 == 0;

                DamageSource source = null;
                if (isPulseTick) {
                    Holder<DamageType> meleeType = level.registryAccess()
                            .registryOrThrow(Registries.DAMAGE_TYPE)
                            .getHolderOrThrow(MELEE_DAMAGE_KEY);

                    source = new DamageSource(meleeType, null, player);

                    EffectUtils.playSound(player, SoundEvents.SOUL_ESCAPE, 7.0F, 1.0F);
                    spawnParticleRing((ServerLevel) level, player, ParticleTypes.SOUL_FIRE_FLAME, range, 120);
                }

                List<LivingEntity> nearby = level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(range));

                for (LivingEntity mob : nearby) {
                    if (mob == player || mob instanceof Player) continue;

                    double dx = player.getX() - mob.getX();
                    double dy = player.getY() - mob.getY();
                    double dz = player.getZ() - mob.getZ();
                    double distSq = dx * dx + dy * dy + dz * dz;

                    if (distSq > radiusSq || distSq < 0.01) continue;

                    double distance = Math.sqrt(distSq);
                    mob.setDeltaMovement(mob.getDeltaMovement().add(
                            (dx / distance) * pullStrength,
                            (dy / distance) * pullStrength,
                            (dz / distance) * pullStrength
                    ));

                    if (isPulseTick && isValidSoulTarget(player, mob)) {
                        if (player.hasLineOfSight(mob)) {
                            mob.hurtMarked = true;
                            mob.hurt(source, 4.0f);
                            spawnParticleBurst(mob, ParticleTypes.SOUL);
                        }
                    }
                }

                ticks++;
                data.putInt("pull_ticks", ticks);
                player.addEffect(new MobEffectInstance(ModEffects.SOUL_SEVERANCE_ACTIVE.get(), 10, 0, true, false, true));

                if (ticks >= 80) {
                    resetSoulSeverance(player, data);
                }
            }
            @Override public String getName() { return "SoulSeverance"; }
        });

        register("deception", new ISkillEffect() {
            @Override
            public void onProjectileHitBow(Player player, LivingEntity target) {
                if (!(target instanceof Enemy) || !player.hasEffect(ModEffects.DECEPTION_READY.get())) return;

                spawnParticleBurstLow(player, ParticleTypes.DAMAGE_INDICATOR);
                if (target.level() instanceof ServerLevel slevel) {
                    spawnParticleRingHigh(slevel, target, ParticleTypes.DAMAGE_INDICATOR, 9.0, 180);
                    spawnParticleBurst(target, ParticleTypes.DAMAGE_INDICATOR);
                }

                player.level().playSound(null, target.getX(), target.getY(), target.getZ(),
                        SoundEvents.AMETHYST_BLOCK_RESONATE, SoundSource.PLAYERS, 3.0F, 1.0F);

                double rangeSq = 36.0;
                List<LivingEntity> nearby = target.level().getEntitiesOfClass(
                        LivingEntity.class,
                        target.getBoundingBox().inflate(6.0),
                        e -> {
                            if (!(e instanceof Enemy) || e == target || e.getType().is(Tags.EntityTypes.BOSSES)
                                    || e instanceof Warden || !player.hasLineOfSight(e)) return false;
                            double dx = e.getX() - target.getX();
                            double dz = e.getZ() - target.getZ();
                            return (dx * dx + dz * dz) <= rangeSq;
                        }
                );

                for (LivingEntity entity : nearby) {
                    if (entity instanceof Mob mob) {
                        mob.setTarget(target);
                        spawnParticleBurst(entity, ParticleTypes.HEART);
                    }
                }

                player.removeEffect(ModEffects.DECEPTION_READY.get());
                player.addEffect(new MobEffectInstance(ModEffects.DECEPTION_COOLDOWN.get(), 600, 0, false, false, true));

            }

            @Override public String getName() { return "Deception"; }
        });

        register("exploit_weakness", new ISkillEffect() {
            @Override
            public void onProjectileHitCrossbow(Player player, LivingEntity target) {
                if (!player.hasEffect(ModEffects.EXPLOIT_WEAKNESS_READY.get())) return;

                Level level = target.level();
                Random random = new Random();
                int debuff = random.nextInt(6);

                int dex = (int) player.getAttributeValue(ModAttributes.DEX.get());
                double dexDuration33 = 1.0 + (dex / 30.0);
                double dexDuration1 = 1.0 + (dex / 100.0);

                double rangeSq = 16.0;
                List<LivingEntity> nearby = level.getEntitiesOfClass(
                        LivingEntity.class,
                        target.getBoundingBox().inflate(4.0f),
                        e -> {
                            if (e == target || e.isInvulnerable() || !player.hasLineOfSight(e)) return false;
                            boolean isValid = (e instanceof Enemy && !(e instanceof NeutralMob)) ||
                                    (e instanceof NeutralMob n && n.isAngry()) ||
                                    (e instanceof Mob m && m.getTarget() != null);
                            if (!isValid) return false;

                            double dx = e.getX() - target.getX();
                            double dz = e.getZ() - target.getZ();
                            return (dx * dx + dz * dz) <= rangeSq;
                        }
                );

                // 4. Apply Debuffs
                if (level instanceof ServerLevel sLevel) {
                    // Main Target gets full debuff
                    applyRandomDebuff(target, player, debuff, dex, dexDuration33, dexDuration1, sLevel);

                    // Nearby victims get halved debuff
                    for (LivingEntity victim : nearby) {
                        applyRandomDebuffHalved(victim, player, debuff, dex, dexDuration33, dexDuration1);
                    }
                }

                // 5. Sound & Cleanup
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                        ModSounds.EXPLOIT_WEAKNESS.get(), SoundSource.PLAYERS, 2.5F, 1.0F);

                player.removeEffect(ModEffects.EXPLOIT_WEAKNESS_READY.get());
                player.addEffect(new MobEffectInstance(ModEffects.EXPLOIT_WEAKNESS_COOLDOWN.get(), 160, 0, true, true, true));
            }

            @Override public String getName() { return "Exploit Weakness"; }
        });

        register("explosive_tendencies", new ISkillEffect() {
            @Override
            public void onProjectileHitCrossbow(Player player, LivingEntity target) {
                Level level = player.level();
                if (level.isClientSide()) return;
                ServerLevel server = (ServerLevel) level;

                Vec3 targetPos = target.position();
                Vec3 direction = player.position().subtract(targetPos).normalize();
                Vec3 spawnVec = targetPos.add(direction.scale(3));

                BlockPos spawnPos = BlockPos.containing(spawnVec);

                Creeper creeper = EntityType.CREEPER.spawn(server, spawnPos, MobSpawnType.TRIGGERED);
                if (creeper == null) { return; }

                creeper.getPersistentData().putBoolean("noBlockDamage", true);
                creeper.getPersistentData().putUUID("ownerPlayerUUID", player.getUUID());
                target.getPersistentData().putBoolean("customCreeperTarget", true);

                creeper.setHealth(40);
                creeper.setSilent(true);
                creeper.setAggressive(false);

                creeper.setAggressive(true);

                creeper.setTarget(target);

                double x = target.getX();
                double y = target.getY();
                double z = target.getZ();
                creeper.moveTo(x, y, z);

                MobEffectInstance stackEffect = player.getEffect(ModEffects.EXPLOSIVE_TENDENCY_STACK.get());
                if (stackEffect != null) {
                    int currentStacks = stackEffect.getAmplifier();
                    if (currentStacks <= 0) {
                        player.removeEffect(ModEffects.EXPLOSIVE_TENDENCY_STACK.get());
                    } else {
                        player.removeEffect(ModEffects.EXPLOSIVE_TENDENCY_STACK.get());
                        player.addEffect(new MobEffectInstance(ModEffects.EXPLOSIVE_TENDENCY_STACK.get(), MobEffectInstance.INFINITE_DURATION, currentStacks - 1, false, false, true));
                    }
                }
                if (!player.hasEffect(ModEffects.EXPLOSIVE_TENDENCY_TIMER.get())) {
                    player.addEffect(new MobEffectInstance(ModEffects.EXPLOSIVE_TENDENCY_TIMER.get(), 20*8, 0, false, false));
                }
            }

            @Override public String getName() { return "Explosive Tendencies"; }
        });

        register("into_the_fray", new ISkillEffect() {
            @Override
            public void onTick(Player player, LivingEntity mob) {
                CompoundTag data = player.getPersistentData();

                if (!player.isSprinting() || player.hasEffect(ModEffects.INTO_THE_FRAY_COOLDOWN.get())) {
                    resetFray(player, data);
                    return;
                }

                int sprintTicks = data.getInt("fray_sprint_ticks") + 1;
                data.putInt("fray_sprint_ticks", sprintTicks);

                if (sprintTicks < 60) return;

                int amplifier = Math.min((sprintTicks - 60) / 40, 4);
                int stackCount = amplifier + 1;

                updateSprintingBuffs(player, amplifier, sprintTicks >= 220);
                checkCollisions(player, data, stackCount);
            }

            @Override public String getName() { return "Into the Fray"; }
        });

        register("reckoning", new ISkillEffect() {
            private static final int ACTIVE_DURATION = 20 * 10;
            private static final double HEAL_PERCENT = 0.4;
            private static final String HEALED_NBT = "reckoningHealed";

            @Override
            public void onDamageTaken(Player player, LivingEntity mob, float amount) {

                if (player.hasEffect(ModEffects.RECKONING.get())) {
                    player.removeEffect(ModEffects.RECKONING.get());
                    player.addEffect(new MobEffectInstance(ModEffects.RECKONING_ACTIVE.get(), ACTIVE_DURATION, 0, false, false, true));

                    if (player instanceof ServerPlayer serverPlayer) {
                        serverPlayer.playNotifySound(ModSounds.RECKONING_ACTIVE.get(), SoundSource.PLAYERS, 0.3F, 1.0F);
                    }
                    player.getPersistentData().putDouble(HEALED_NBT, 0);
                }
            }

            @Override
            public float onUniversalDamage(Player player, LivingEntity victim, float amount) {
                if (player.hasEffect(ModEffects.RECKONING_ACTIVE.get())) {
                    double healed = amount * HEAL_PERCENT;

                    // Heal the player while avoiding recursion/modifier loops
                    player.getPersistentData().putBoolean("IgnoreRejuvenation", true);
                    player.heal((float) healed);
                    player.getPersistentData().remove("IgnoreRejuvenation");

                    double totalHealed = player.getPersistentData().getDouble(HEALED_NBT);
                    player.getPersistentData().putDouble(HEALED_NBT, totalHealed + healed);

                    EffectUtils.spawnParticleBurst(player, ParticleTypes.DAMAGE_INDICATOR);
                }
                return 0;
            }

            @Override public String getName() { return "Reckoning"; }
        });

        register("retaliate", new ISkillEffect() {
            @Override
            public void useItemEventStart(Player player, LivingEntity mob, ItemStack item) {
                // Triggered when the player starts using the shield
                if (!"shield".equals(ItemRarityUtils.getItemType(item))) return;

                if (player.hasEffect(ModEffects.RETALIATE_READY.get())) {
                    player.addEffect(new MobEffectInstance(ModEffects.RETALIATE_ACTIVE.get(), 80, 0, false, false, true));
                    player.removeEffect(ModEffects.RETALIATE_READY.get());

                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                            ModSounds.RETALIATE_ACTIVATE.get(), SoundSource.PLAYERS, 0.4F, 1.0F);

                    player.getPersistentData().putInt("retaliateHits", 0);
                }
            }

            @Override
            public void onDamageTaken(Player player, LivingEntity sourceMob, float amount) {
                // Triggered when the player is hit while the effect is active
                if (player.hasEffect(ModEffects.RETALIATE_ACTIVE.get()) && player.isBlocking()) {
                    int hits = player.getPersistentData().getInt("retaliateHits");
                    player.getPersistentData().putInt("retaliateHits", hits + 1);
                }
            }

            @Override public String getName() { return "Retaliate"; }
        });

        register("berserk", new ISkillEffect() {
            @Override public String getName() { return "berserk"; }

            @Override
            public float onDirectMeleeHit(Player player, LivingEntity victim, float damage) {
                // --- 1. THE "READY" STATE (Triggering the Big Hit) ---
                if (player.hasEffect(ModEffects.BERSERK_READY.get())) {
                    double melDmg = getScaledValue(player, ModAttributes.MELEE_POTENCY.get(), ModAttributes.MELEE_POTENCY_MULTIPLIER.get());

                    SkillCapability cap = player.getCapability(SkillCapabilityProvider.SKILL_CAP).orElse(null);
                    boolean hasEnchantedBlade = cap.hasSkill("enchanted_blade");

                    double mult = 0.006 + (hasEnchantedBlade ? 0.0015 : 0);

                    // Cleanup state (Sound, Particles, Remove Effect, Add Cooldown/Timer)
                    if (player instanceof ServerPlayer serverPlayer) {
                        serverPlayer.playNotifySound(
                                SoundEvents.ZOMBIE_ATTACK_WOODEN_DOOR,
                                SoundSource.PLAYERS,
                                0.3F,
                                1.0F
                        );
                    }
                    player.removeEffect(ModEffects.BERSERK_READY.get());
                    spawnParticleBurst(player, ParticleTypes.SMALL_FLAME);
                    if (!player.hasEffect(ModEffects.BERSERK_TIMER.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.BERSERK_TIMER.get(), 300, 0, false, false, true));
                    }

                    // Return the ADDED damage
                    return (float) (damage * (melDmg * mult));
                }

                // --- 2. THE "STACKING" STATE (Building up to Ready) ---
                // We use the BERSERK effect amplifier as our stack counter
                MobEffectInstance currentBerserk = player.getEffect(ModEffects.BERSERK.get());

                if (currentBerserk != null) {
                    int stacks = currentBerserk.getAmplifier() + 1; // Level 1 is Amp 0

                    if (stacks < 1) { // If you want exactly 2 hits to trigger Ready
                        // Increment Stacks
                        player.removeEffect(ModEffects.BERSERK.get());
                        player.addEffect(new MobEffectInstance(ModEffects.BERSERK.get(), 80, stacks, false, false, true));
                        spawnParticleBurst(player, ParticleTypes.FLAME);
                    } else {
                        // Max stacks reached -> Transition to READY
                        player.removeEffect(ModEffects.BERSERK.get());
                        player.addEffect(new MobEffectInstance(ModEffects.BERSERK_READY.get(), 120, 0, false, false, true));
                        // Optional: Play a "Charged" sound here
                    }
                }

                return 0;
            }
        });



        register("clairvoyance", new ISkillEffect() {
            private static final int BASE_CHARGE_TICKS = 80;

            @Override
            public void useItemEventStart(Player player, LivingEntity mob, ItemStack item) {
                if (!"bow".equals(ItemRarityUtils.getItemType(item))) return;
                if (!player.hasEffect(ModEffects.CLAIRVOYANCE_READY.get())) return;

                // Scaling logic using your custom attributes
                double drawSpeed = getScaledValue(player, ModAttributes.NOCK_HASTE.get(), ModAttributes.NOCK_HASTE_MULTIPLIER.get());
                double chargeReductionMultiplier = 1 - (drawSpeed / (drawSpeed + 100.0));
                int finalReqTicks = (int) (BASE_CHARGE_TICKS * chargeReductionMultiplier);

                player.getPersistentData().putInt("ClairvoyanceTarget", finalReqTicks);
            }

            @Override
            public void useItemEventTick(Player player, LivingEntity mob, ItemStack item) {
                CompoundTag data = player.getPersistentData();
                if (!data.contains("ClairvoyanceTarget")) return;

                int targetTicks = data.getInt("ClairvoyanceTarget");

                // If player has held the bow long enough
                if (player.getTicksUsingItem() >= targetTicks) {
                    data.putBoolean("ClairvoyanceActive", true);
                    data.remove("ClairvoyanceTarget");

                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                            ModSounds.CLAIRVOYANCE.get(), SoundSource.PLAYERS, 1.2F, 1.0F);
                }
            }

            @Override
            public void useItemEventEnd(Player player, LivingEntity mob, ItemStack item) {
                CompoundTag data = player.getPersistentData();
                player.getPersistentData().remove("ClairvoyanceTarget");
                data.putBoolean("Clairvoyance", false);
            }

            @Override
            public void onArrowJoin(Player player, AbstractArrow arrow) {
                CompoundTag data = player.getPersistentData();

                if (data.getBoolean("ClairvoyanceActive")) {

                    data.putBoolean("ClairvoyanceActive", false);
                    data.remove("ClairvoyanceTarget");
                }
            }

            @Override public String getName() { return "Clairvoyance";
            }
        });

        register("courageous_blow", new ISkillEffect() {
            @Override
            public void onDirectMeleeHit(Player player, LivingEntity victim) {
                Level level = player.level();
                Holder<DamageType> trueDamage = level.registryAccess()
                        .registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(ModDamageTypes.TRUE_DAMAGE);

                DamageSource source = new DamageSource(trueDamage, null, player);
                victim.hurt(source, (float) (player.getHealth() * 0.08));
            }
            @Override public String getName() { return "Courageous Blow";
            }
        });

        register("penetrating_will", new ISkillEffect() {
            @Override
            public void onDirectMeleeHit(Player player, LivingEntity victim) {
                player.addEffect(new MobEffectInstance(ModEffects.PENETRATOR.get(), 60, 0, false, false, true));
                victim.addEffect(new MobEffectInstance(ModEffects.PENETRATED.get(), 60, 0, false, true));
            }
            @Override public String getName() { return "Penetrating Will";
            }
        });

        register("hawkeye", new ISkillEffect() {
            @Override public String getName() { return "hawkeye"; }

            // --- STACK ON MELEE ---
            @Override
            public void onDirectMeleeHit(Player player, LivingEntity mob) {
                MobEffectInstance currentEffect = player.getEffect(ModEffects.HAWKEYE.get());
                int currentStacks = currentEffect != null ? currentEffect.getAmplifier() + 1 : 0;
                int newStacks = Math.min(4, currentStacks + 1);
                player.addEffect(new MobEffectInstance(ModEffects.HAWKEYE.get(), 160, newStacks - 1, false, false, true));
            }

            // --- RESET ON PROJECTILE ---
            @Override
            public float onProjectileHitBow(Player player, LivingEntity mob, float damageBonus) {
                player.removeEffect(ModEffects.HAWKEYE.get());
                removeModifier(player, ModAttributes.NOCK_HASTE.get(), HAWKEYE_UUID);
                removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), HAWKEYE_UUID);
                return 0;
            }
        });

        register("frenzy", new ISkillEffect() {
            @Override public String getName() { return "frenzy"; }

            @Override
            public float onUniversalDamage(Player player, LivingEntity mob, float damageBonus) {
                MobEffectInstance currentEffect = player.getEffect(ModEffects.FRENZY.get());
                int currentStacks = currentEffect != null ? currentEffect.getAmplifier() + 1 : 0;
                int newStacks = Math.min(40, currentStacks + 1);

                player.removeEffect(ModEffects.FRENZY.get());
                player.addEffect(new MobEffectInstance(ModEffects.FRENZY.get(), 40, newStacks - 1, false, false, true));
                return 0;
            }
        });

        // --- 3. PREDATOR'S INSTINCT ---
        register("predators_instinct", new ISkillEffect() {
            @Override public String getName() { return "predators_instinct"; }

            @Override
            public float onUniversalDamage(Player player, LivingEntity mob, float damageBonus) {
                if (mob.getHealth() <= mob.getMaxHealth() / 2.0f) {
                    return damageBonus + 0.1f;
                }
                return 0;
            }
        });

        // --- 4. AVALANCHING STRIKE ---
        register("avalanching_strike", new ISkillEffect() {
            @Override public String getName() { return "avalanching_strike"; }

            @Override
            public float onDirectMeleeHit(Player player, LivingEntity mob, float damageBonus) {
                if (player.getRandom().nextInt(10) < 1) {
                    return damageBonus * 0.5f;
                }
                return 0;
            }
        });

        // --- 5. RETALIATORY ---
        register("retaliatory", new ISkillEffect() {
            @Override public String getName() { return "retaliatory"; }

            @Override
            public void onDamageTaken(Player player, LivingEntity mob, float amount) {
                if (player.getRandom().nextDouble() <= 0.3) {
                    Level level = player.level();
                    Holder<DamageType> trueDamage = level.registryAccess()
                            .registryOrThrow(Registries.DAMAGE_TYPE)
                            .getHolderOrThrow(ModDamageTypes.TRUE_DAMAGE);
                    DamageSource source = new DamageSource(trueDamage, null, player);
                    if (mob != null) mob.hurt(source, amount * 0.3f);
                }
            }
        });

    }

    private static void register(String id, ISkillEffect effect) {
        REGISTRY.put(id, effect);
    }
}