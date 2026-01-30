package net.cold.coldsmod.stat;

import net.cold.coldsmod.blessingbonuses.CooldownCycle;
import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils;
import net.cold.coldsmod.damage.ModDamageTypes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.common.MinecraftForge;
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
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (player.level().isClientSide()) return;
        if (event.getSource().is(ModDamageTypes.LIGHTNING_DAMAGE)) return;

        CompoundTag data = player.getPersistentData();
        float finalDamage = event.getAmount();
        boolean isDirectMelee = event.getSource().getDirectEntity() == player;

        boolean isProjectile = event.getSource().getDirectEntity() instanceof Projectile;
        InteractionHand hand = player.swingingArm;

        if (event.getSource().is(ModDamageTypes.MELEE_DOT_DAMAGE)) {
            double meleeDmg = getScaledValue(player, ModAttributes.MELEE_POTENCY.get(), ModAttributes.MELEE_POTENCY_MULTIPLIER.get());
            finalDamage *= (1.0 + meleeDmg / 100.0);

            double melCritDmg = getScaledValue(player, ModAttributes.MELEE_PRECISION.get(), ModAttributes.MELEE_PRECISION_MULTIPLIER.get());
            double melCritCh = getScaledValue(player, ModAttributes.MELEE_ACCURACY.get(), ModAttributes.MELEE_ACCURACY_MULTIPLIER.get());

            if (rollCrit(player, melCritCh)) {
                finalDamage *= (1.5 + melCritDmg / 100.0);
            }
        } else if (isProjectile || event.getSource().is(ModDamageTypes.CUSTOM_RANGED_DAMAGE)) {
            double projDmg = getScaledValue(player, ModAttributes.PROJECTILE_POTENCY.get(), ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get());
            double projCritCh = getScaledValue(player, ModAttributes.PROJECTILE_ACCURACY.get(), ModAttributes.PROJECTILE_ACCURACY_MULTIPLIER.get());
            double projCritDmg = getScaledValue(player, ModAttributes.PROJECTILE_PRECISION.get(), ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get());

            if (event.getSource().getDirectEntity() instanceof Projectile proj) {

                finalDamage *= 0.7f;

                if (proj.getPersistentData().getBoolean("clairvoyance_tagged")) {
                    finalDamage *= 3;
                    resetClairvoyance(player, data);
                    spawnParticleBurst(event.getEntity(), ParticleTypes.SONIC_BOOM);
                }

                if (!proj.getPersistentData().contains("ScaledDamage")) {
                    proj.getPersistentData().putDouble("ScaledDamage", finalDamage * (1.0 + projDmg / 100.0));
                }
                finalDamage = (float) proj.getPersistentData().getDouble("ScaledDamage");
            } else {
                finalDamage *= (1.0 + projDmg / 100.0);
            }

            if (rollCrit(player, projCritCh)) {
                finalDamage *= (1.5 + projCritDmg / 100.0);
                playCritSound(player);
            }
            resetHawkeye(player, data);
            handleFrenzy(player, data);
        } else if (isDirectMelee || event.getSource().is(ModDamageTypes.CUSTOM_MELEE_DAMAGE)) {
            double melDmg = getScaledValue(player, ModAttributes.MELEE_POTENCY.get(), ModAttributes.MELEE_POTENCY_MULTIPLIER.get());
            double melCritDmg = getScaledValue(player, ModAttributes.MELEE_PRECISION.get(), ModAttributes.MELEE_PRECISION_MULTIPLIER.get());
            double melCritCh = getScaledValue(player, ModAttributes.MELEE_ACCURACY.get(), ModAttributes.MELEE_ACCURACY_MULTIPLIER.get());

            if (player.hasEffect(ModEffects.BERSERK_READY.get()) && data.getBoolean("berserk_applied")) {
                finalDamage *= (1.0 + melDmg * 0.006);
                handleBerserkReset(player, data);
            } else if (data.getBoolean("berserk_applied")) {
                handleBerserkStacking(player, data);
            }

            if (data.getBoolean("bronzewood_proc")) {
                finalDamage += 3;
                data.putBoolean("bronzewood_proc", false);
            }

            if (event.getSource().is(ModDamageTypes.CUSTOM_MELEE_DAMAGE) && rollCrit(player, melCritCh)) {
                finalDamage *= (1.5 + melCritDmg / 100.0);
                playCritSound(player);
            } else if (!event.getSource().is(ModDamageTypes.CUSTOM_MELEE_DAMAGE) && player.getPersistentData().getBoolean("adjustSharpness")) {
                finalDamage += getSharpnessBonus(player, hand) * (0.5 + melCritDmg / 100.0);
                player.getPersistentData().putBoolean("adjustSharpness", false);
            }

            finalDamage *= (float) (1.0 + melDmg / 100.0);
            handleFrenzy(player, data);
        }
        else {
            double genDmg = getScaledValue(player, ModAttributes.POTENCY.get(), ModAttributes.POTENCY_MULTIPLIER.get());
            double genCritDmg = getScaledValue(player, ModAttributes.PRECISION.get(), ModAttributes.PRECISION_MULTIPLIER.get());
            double genCritCh = getScaledValue(player, ModAttributes.ACCURACY.get(), ModAttributes.ACCURACY_MULTIPLIER.get());

            finalDamage *= (1.0 + genDmg / 100.0);

            if (rollCrit(player, genCritCh)) {
                finalDamage *= (1.5 + genCritDmg / 100.0);
            }
            handleFrenzy(player, data);
        }
        event.setAmount(finalDamage);
    }

    @SubscribeEvent
    public void onLivingDamage(LivingDamageEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        if (event.getSource().is(ModDamageTypes.RECKONING_DAMAGE)) {
            // System.out.println(event.getAmount());
            return;
        }

        LivingEntity victim = event.getEntity();
        boolean isIntimidating = event.getSource().is(ModDamageTypes.TRUE_DAMAGE);

        if (!isIntimidating) {
            double incDamageMultiplier = 1.0;
            var incAttr = victim.getAttribute(ModAttributes.INCOMING_DAMAGE_MULTIPLIER.get());
            if (incAttr != null) incDamageMultiplier = incAttr.getValue();

            double attackerDamageMultiplier = 1.0;
            if (event.getSource().getEntity() instanceof LivingEntity attacker) {
                attackerDamageMultiplier = attacker.getAttributeValue(ModAttributes.OUTGOING_DAMAGE_MULTIPLIER.get());
            }

            event.setAmount((float) (event.getAmount() * incDamageMultiplier * attackerDamageMultiplier));
        }

        MobEffectInstance intimidated = victim.getEffect(ModEffects.INTIMIDATED.get());
        if (intimidated != null && !isIntimidating) {
            CompoundTag data = victim.getPersistentData();
            float scaledAmount = event.getAmount();

            float currentStored = data.getFloat("stored_temporal_damage");
            float newTotal = currentStored + scaledAmount;
            data.putFloat("stored_temporal_damage", newTotal);

            float multiplier = (intimidated.getAmplifier() + 1) / 100f;

            if ((victim.getHealth()) <= newTotal * (1.0f + multiplier)) {
                CooldownCycle.triggerSnapKill(victim, intimidated.getAmplifier());
            }
            event.setCanceled(true);
        }
        // System.out.println(event.getAmount());
    }

    private boolean rollCrit(Player player, double chance) {
        return player.getRandom().nextDouble() < (chance + 10.0) / 100.0;
    }

    private void playCritSound(Player player) {
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.PLAYER_ATTACK_CRIT, SoundSource.PLAYERS, 1.0F, 1.0F);
    }

    private void handleBerserkReset(Player player, CompoundTag data) {
        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.playNotifySound(
                    SoundEvents.ZOMBIE_ATTACK_WOODEN_DOOR,
                    SoundSource.PLAYERS,
                    0.3F,
                    1.0F
            );
        }        player.removeEffect(ModEffects.BERSERK_READY.get());
        data.putInt("berserk", 0);
        spawnParticleBurst(player, ParticleTypes.SMALL_FLAME);
        if (!player.hasEffect(ModEffects.BERSERK_TIMER.get())) {
            player.addEffect(new MobEffectInstance(ModEffects.BERSERK_TIMER.get(), 300, 0, false, false, true));
        }
    }

    private void handleBerserkStacking(Player player, CompoundTag data) {
        int stacks = data.getInt("berserk");
        if (stacks < 1) {
            stacks++;
            data.putInt("berserk", stacks);
            player.removeEffect(ModEffects.BERSERK.get());
            player.addEffect(new MobEffectInstance(ModEffects.BERSERK.get(), 80, stacks - 1, false, false, true));
            spawnParticleBurst(player, ParticleTypes.FLAME);
        } else {
            data.putInt("berserk", 0);
            player.removeEffect(ModEffects.BERSERK.get());
            player.addEffect(new MobEffectInstance(ModEffects.BERSERK_READY.get(), 120, 0, false, false, true));
        }
    }

    private void handleHawkeyeStacking(Player player, CompoundTag data) {
        if (data.getBoolean("hawkeye_eligible")) {
            int stacks = Math.min(4, data.getInt("hawkeye") + 1);
            data.putInt("hawkeye", stacks);
            player.removeEffect(ModEffects.HAWKEYE.get());
            player.addEffect(new MobEffectInstance(ModEffects.HAWKEYE.get(), 160, stacks - 1, false, false, true));
        }
    }

    private void resetHawkeye(Player player, CompoundTag data) {
        if (!(player.getPersistentData().getBoolean("hawkeye_eligible"))) return;
        data.putInt("hawkeye", 0);
        player.removeEffect(ModEffects.HAWKEYE.get());

        removeModifier(player, ModAttributes.NOCK_HASTE.get(), HAWKEYE_UUID);
        removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), HAWKEYE_UUID);
    }

    private void resetClairvoyance(Player player, CompoundTag data) {
        data.putBoolean("Clairvoyance", false);
        player.getPersistentData().putBoolean("clairvoyance_sound_played", false);
        player.removeEffect(ModEffects.CLAIRVOYANCE_READY.get());
        player.addEffect(new MobEffectInstance(ModEffects.CLAIRVOYANCE_COOLDOWN.get(), 400, 0, false, false, true));
    }

    private void handleFrenzy(Player player, CompoundTag data) {
        if (!(player.getPersistentData().getBoolean("frenzy_eligible"))) return;
        int stacks = Math.min(40, data.getInt("frenzy") + 1);
        data.putInt("frenzy", stacks);
        player.removeEffect(ModEffects.FRENZY.get());
        player.addEffect(new MobEffectInstance(ModEffects.FRENZY.get(), 40, stacks - 1, false, false, true));
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
        if (jumpBoost <= 0) return;
        float fallThreshold = 3.0f * jumpBoost;
        float fallDistance = event.getDistance();
        if (fallDistance <= fallThreshold) {
            event.setDistance(0);
            event.setCanceled(true);
            player.fallDistance = 0;
            if (jumpBoost > 1.5) { spawnExplosionOnFeet(player); }
            return;
        }

        if (!player.level().isClientSide()) {
            float damageMultiplier = 1.0f / jumpBoost;
            event.setDamageMultiplier(Math.max(0.0f, damageMultiplier));
        }
    }

    @SubscribeEvent
    public void onLivingAttack(LivingAttackEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.level().isClientSide()) return;

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

            handleHawkeyeStacking(player, player.getPersistentData());

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
}
