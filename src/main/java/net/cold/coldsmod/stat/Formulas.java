package net.cold.coldsmod.stat;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils;
import net.cold.coldsmod.capabilities.ISkillEffect;
import net.cold.coldsmod.capabilities.SkillCapability;
import net.cold.coldsmod.capabilities.SkillCapabilityProvider;
import net.cold.coldsmod.damage.ModDamageTypes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.cold.coldsmod.blessingbonuses.CooldownCycle.HAWKEYE_UUID;
import static net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils.spawnExplosionOnFeet;
import static net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils.spawnParticleBurst;
import static net.cold.coldsmod.stat.AttributeApplier.getScaledValue;
import static net.cold.coldsmod.stat.AttributeApplier.removeModifier;
import static net.cold.coldsmod.stat.FormulasHelpers.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Formulas {

    public static void register() {
        MinecraftForge.EVENT_BUS.register(new AttributeApplier());
    }

    private double getSharpnessBonus(Player player, InteractionHand hand) {
        InteractionHand actualHand = (hand == null) ? InteractionHand.MAIN_HAND : hand;
        ItemStack stack = player.getItemInHand(actualHand);
        if (stack.isEmpty()) return 0.0;
        if (!stack.isEmpty() && "sword".equals(ItemRarityUtils.getItemType(stack))) {
            return getSharpnessLevel(stack);
        }
        return 0.0;
    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (event.getEntity() == player) return;
        if (event.getSource().is(ModDamageTypes.LIGHTNING_DAMAGE) || event.getSource().is(ModDamageTypes.RECKONING_DAMAGE)) return;

        LivingEntity victim = event.getEntity();
        float finalDamage = event.getAmount();

        var capOptional = player.getCapability(SkillCapabilityProvider.SKILL_CAP);
        if (!capOptional.isPresent()) return;
        SkillCapability cap = capOptional.orElseThrow(IllegalStateException::new);

        boolean isDirectMelee = event.getSource().getDirectEntity() == player;
        boolean isProjectile = event.getSource().getDirectEntity() instanceof Projectile;
        boolean isDoT = event.getSource().is(ModDamageTypes.DOT_DAMAGE);
        boolean isCustomMelee = event.getSource().is(ModDamageTypes.CUSTOM_MELEE_DAMAGE);

        if (isDoT) {
            for (ISkillEffect s : cap.dotCache) {
                finalDamage += s.onDOTHiT(player, victim, finalDamage);
            }
            double melPot = getScaledValue(player, ModAttributes.POTENCY.get(), ModAttributes.POTENCY_MULTIPLIER.get());
            if (rollCrit(player, getScaledValue(player, ModAttributes.ACCURACY.get(), ModAttributes.ACCURACY_MULTIPLIER.get()))) {
                finalDamage *= (float) (1.5 + getScaledValue(player, ModAttributes.PRECISION.get(), ModAttributes.PRECISION_MULTIPLIER.get()) / 100.0);
            }
            finalDamage *= (float) (1.0 + melPot / 100.0) * (float) player.getAttributeValue(ModAttributes.MELEE_DAMAGE_MULTIPLIER.get());
        }

        else if (isProjectile || event.getSource().is(ModDamageTypes.CUSTOM_RANGED_DAMAGE)) {
            if (event.getSource().getDirectEntity() instanceof Projectile proj) {
                finalDamage /= 2.0f;

                for (ISkillEffect s : cap.projectileBowCache) {
                    finalDamage += s.onProjectileHitBow(player, victim, finalDamage);
                }

                if (!proj.getPersistentData().contains("ScaledDamage")) {
                    double pPot = getScaledValue(player, ModAttributes.PROJECTILE_POTENCY.get(), ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get());
                    proj.getPersistentData().putDouble("ScaledDamage", finalDamage * (1.0 + pPot / 100.0));
                }
                finalDamage = (float) proj.getPersistentData().getDouble("ScaledDamage");
            } else {
                double pPot = getScaledValue(player, ModAttributes.PROJECTILE_POTENCY.get(), ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get());
                finalDamage *= (float) (1.0 + pPot / 100.0);
            }

            if (rollCrit(player, getScaledValue(player, ModAttributes.PROJECTILE_ACCURACY.get(), ModAttributes.PROJECTILE_ACCURACY_MULTIPLIER.get()))) {
                finalDamage *= (float) (1.5 + getScaledValue(player, ModAttributes.PROJECTILE_PRECISION.get(), ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get()) / 100.0);
                playCritSound(player);
            }

            finalDamage *= (float) player.getAttributeValue(ModAttributes.PROJECTILE_DAMAGE_MULTIPLIER.get());
        } else if (isDirectMelee) {
            for (ISkillEffect s : cap.directMeleeCache) {
                s.onDirectMeleeHit(player, victim);
                finalDamage += s.onDirectMeleeHit(player, victim, finalDamage);
            }

            double melPot = getScaledValue(player, ModAttributes.MELEE_POTENCY.get(), ModAttributes.MELEE_POTENCY_MULTIPLIER.get());
            if (rollCrit(player, getScaledValue(player, ModAttributes.MELEE_ACCURACY.get(), ModAttributes.MELEE_ACCURACY_MULTIPLIER.get()))) {
                finalDamage *= (float) (1.5 + getScaledValue(player, ModAttributes.MELEE_PRECISION.get(), ModAttributes.MELEE_PRECISION_MULTIPLIER.get()) / 100.0);
                playCritSound(player);
            }

            finalDamage *= (float) (1.0 + melPot / 100.0) * (float) player.getAttributeValue(ModAttributes.MELEE_DAMAGE_MULTIPLIER.get());
        } else if (isCustomMelee) {
            for (ISkillEffect s : cap.meleeCache) {
                s.onDirectMeleeHit(player, victim);
                finalDamage += s.onDirectMeleeHit(player, victim, finalDamage);
            }

            double melPot = getScaledValue(player, ModAttributes.MELEE_POTENCY.get(), ModAttributes.MELEE_POTENCY_MULTIPLIER.get());
            if (rollCrit(player, getScaledValue(player, ModAttributes.MELEE_ACCURACY.get(), ModAttributes.MELEE_ACCURACY_MULTIPLIER.get()))) {
                finalDamage *= (float) (1.5 + getScaledValue(player, ModAttributes.MELEE_PRECISION.get(), ModAttributes.MELEE_PRECISION_MULTIPLIER.get()) / 100.0);
                playCritSound(player);
            }
            finalDamage *= (float) (1.0 + melPot / 100.0) * (float) player.getAttributeValue(ModAttributes.MELEE_DAMAGE_MULTIPLIER.get());

        } else {
            for (ISkillEffect s : cap.universalCache) {
                finalDamage += s.onUniversalDamage(player, victim, finalDamage);
            }
            double genPot = getScaledValue(player, ModAttributes.POTENCY.get(), ModAttributes.POTENCY_MULTIPLIER.get());
            if (rollCrit(player, getScaledValue(player, ModAttributes.ACCURACY.get(), ModAttributes.ACCURACY_MULTIPLIER.get()))) {
                finalDamage *= (float) (1.5 + getScaledValue(player, ModAttributes.PRECISION.get(), ModAttributes.PRECISION_MULTIPLIER.get()) / 100.0);
            }
            finalDamage *= (float) (1.0 + genPot / 100.0) * (float) player.getAttributeValue(ModAttributes.UNCATEGORIZED_DAMAGE_MULTIPLIER.get());
        }

        for (ISkillEffect s : cap.universalCache) {
            s.onUniversalDamage(player, victim, finalDamage);
        }

        event.setAmount(finalDamage);
    }

    @SubscribeEvent
    public void onLivingDamage(LivingDamageEvent event) {
        if (event.getEntity().level().isClientSide()) return;

        LivingEntity victim = event.getEntity();
        boolean isIgnoringMultipliers = event.getSource().is(ModDamageTypes.TRUE_DAMAGE) || event.getSource().is(ModDamageTypes.RECKONING_DAMAGE);

        if (!isIgnoringMultipliers) {
            double incDamageMultiplier = 1.0;
            var incAttr = victim.getAttribute(ModAttributes.INCOMING_DAMAGE_MULTIPLIER.get());
            if (incAttr != null) incDamageMultiplier = incAttr.getValue();

            double attackerDamageMultiplier = 1.0;
            float damage = event.getAmount();
            if (event.getSource().getEntity() instanceof LivingEntity attacker) {

                attackerDamageMultiplier = attacker.getAttributeValue(ModAttributes.OUTGOING_DAMAGE_MULTIPLIER.get());
            }
            event.setAmount((float) (damage * incDamageMultiplier * attackerDamageMultiplier));
        }
    }


    private double getSharpnessLevel(ItemStack stack) {
        if (stack.isEmpty() || !"sword".equals(ItemRarityUtils.getItemType(stack))) return 0.0;
        int level = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, stack);
        if (level == 0) return 0.0;
        return 1.0 + (level - 1) * 0.5;
    }

    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        double miningSpeedBonus = event.getEntity().getAttributeValue(ModAttributes.MINING_SPEED.get());
        float original = event.getNewSpeed();
        float boosted = (float) (original * ((miningSpeedBonus)));
        event.setNewSpeed(boosted);
    }

    @SubscribeEvent
    public void onLivingFall(LivingFallEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        float jumpBoost = (float) player.getAttributeValue(ModAttributes.JUMP_BOOST.get());
        float fallThreshold = 3.0f + (3.0f * (jumpBoost - 1.0f));
        float fallDistance = event.getDistance();
        if (fallDistance <= fallThreshold) {
            if (jumpBoost > 1.5 && fallDistance > 3) { spawnExplosionOnFeet(player); }
            event.setDistance(0);
            event.setCanceled(true);
            player.fallDistance = 0;
        }
    }

    @SubscribeEvent
    public void onLivingAttack(LivingAttackEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.level().isClientSide()) return;
        if (event.getSource().is(DamageTypes.FALL)) {return;}

        if (player.hasEffect(ModEffects.BASTION_ACTIVE.get())) {
            event.setCanceled(true);
            EffectUtils.spawnParticleBurst(player, ParticleTypes.SNEEZE);
            if (player instanceof ServerPlayer serverPlayer) {serverPlayer.playNotifySound(SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 0.75F, 1.5F);}
            return;
        }

        if (player.hasEffect(ModEffects.NIMBLE_GETAWAY_ACTIVE.get())) {
            event.setCanceled(true);
            EffectUtils.spawnParticleBurst(player, ParticleTypes.SNEEZE);


            player.removeEffect(ModEffects.NIMBLE_GETAWAY_ACTIVE.get());
            player.addEffect(new MobEffectInstance(ModEffects.NIMBLE_GETAWAY_COOLDOWN.get(), 20*20, 0, false, false, true));

            if (player instanceof ServerPlayer serverPlayer) {serverPlayer.playNotifySound(SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 0.75F, 1.5F);}
        }
    }

    @SubscribeEvent
    public void onHeal(LivingHealEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.level().isClientSide) return;
        if (player.getPersistentData().contains("IgnoreRejuvenation")) return;

        double incHeal = getScaledValue(player, ModAttributes.REJUVENATION.get(), ModAttributes.REJUVENATION_MULTIPLIER.get());
        event.setAmount((float) (event.getAmount() * (1 + incHeal/100)));
    }

    @SubscribeEvent
    public void onCrit(CriticalHitEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        Player player = event.getEntity();

        double scaledChance = getScaledValue(player,
                ModAttributes.MELEE_ACCURACY.get(),
                ModAttributes.MELEE_ACCURACY_MULTIPLIER.get());

        double scaledDamage = getScaledValue(player,
                ModAttributes.MELEE_PRECISION.get(),
                ModAttributes.MELEE_PRECISION_MULTIPLIER.get());

        boolean isVanillaCrit = event.isVanillaCritical();
        boolean isCustomCrit = player.getRandom().nextDouble() < (scaledChance + 10.0) / 100.0;

        if (isVanillaCrit || isCustomCrit) {
            if (isCustomCrit) event.setResult(Event.Result.ALLOW);

            float critBonus = (float) (1.5 + (scaledDamage / 100.0));
            event.setDamageModifier(critBonus);

            player.getPersistentData().putBoolean("adjustSharpness", true);


            if (player.getPersistentData().getBoolean("chain_lightning_applied")) {
                player.getPersistentData().putBoolean("procChainLightning", true);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingJump(LivingEvent.LivingJumpEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        double jumpBonus = player.getAttributeValue(ModAttributes.JUMP_BOOST.get());
        if (jumpBonus != 0) {
            double baseJumpHeight = 1.252;
            double gravity = 0.08;

            double newHeight = baseJumpHeight * jumpBonus;

            double requiredMotionY = Math.sqrt(2 * gravity * newHeight);

            player.setDeltaMovement(player.getDeltaMovement().x, requiredMotionY, player.getDeltaMovement().z);
        }
    }

    // --- 2. PROJECTILES ---
    @SubscribeEvent
    public static void onArrowJoin(EntityJoinLevelEvent event) {
        if (event.getLevel().isClientSide()) return;
        if (event.getEntity() instanceof AbstractArrow arrow && arrow.getOwner() instanceof Player player) {
            player.getCapability(SkillCapabilityProvider.SKILL_CAP).ifPresent(cap -> {
                cap.checkCache();
                for (ISkillEffect s : cap.arrowJoinCache) s.onArrowJoin(player, arrow);
            });
        }
    }

    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        if (event.getProjectile().level().isClientSide()) return;
        if (event.getProjectile().getOwner() instanceof Player player) {
            player.getCapability(SkillCapabilityProvider.SKILL_CAP).ifPresent(cap -> {
                cap.checkCache();
                for (ISkillEffect s : cap.impactCache) {
                    // if bow/crossbow arrow
                    s.onProjectileImpactCrossbow(player, event.getProjectile(), event.getRayTraceResult());
                    s.onProjectileImpactBow(player, event.getProjectile(), event.getRayTraceResult().getLocation());
                }
            });
        }
    }

    // --- 3. ITEM USAGE (Bows/Shields) ---
    @SubscribeEvent
    public static void onUseItemStart(LivingEntityUseItemEvent.Start event) {
        if (event.getEntity() instanceof Player player) {
            player.getCapability(SkillCapabilityProvider.SKILL_CAP).ifPresent(cap -> {
                cap.checkCache();
                for (ISkillEffect s : cap.itemUseCache) s.useItemEventStart(player, null, event.getItem());
            });
        }
    }

    @SubscribeEvent
    public static void onUseItemTick(LivingEntityUseItemEvent.Tick event) {
        if (event.getEntity() instanceof Player player) {
            player.getCapability(SkillCapabilityProvider.SKILL_CAP).ifPresent(cap -> {
                cap.checkCache();
                for (ISkillEffect s : cap.itemUseCache) s.useItemEventTick(player, null, event.getItem());
            });
        }
    }

    // --- 4. LIFECYCLE ---
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && !event.player.level().isClientSide()) {
            event.player.getCapability(SkillCapabilityProvider.SKILL_CAP).ifPresent(cap -> {
                cap.checkCache();
                for (ISkillEffect s : cap.tickCache) s.onTick(event.player, null);
            });
        }
    }

//    @SubscribeEvent
//    public static void onHeal(LivingHealEvent event) {
//        if (event.getEntity() instanceof Player player) {
//            player.getCapability(SkillCapabilityProvider.SKILL_CAP).ifPresent(cap -> {
//                cap.checkCache();
//                for (ISkillEffect s : cap.healCache) {
//                    s.onHeal(player, null);
//                    s.onHeal(player, null, event.getAmount());
//                }
//            });
//        }
//    }
}
