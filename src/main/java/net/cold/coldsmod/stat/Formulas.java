package net.cold.coldsmod.stat;

import net.cold.coldsmod.damage.CustomMeleeDamage;
import net.cold.coldsmod.damage.CustomMeleeDamageNoProcs;
import net.cold.coldsmod.damage.CustomRangedDamage;
import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
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

import static net.cold.coldsmod.blessingbonuses.CooldownCycle.HAWKEYE_UUID;
import static net.cold.coldsmod.stat.AttributeApplier.getScaledValue;
import static net.cold.coldsmod.stat.AttributeApplier.removeModifier;

public class Formulas {

    public static void register() {
        MinecraftForge.EVENT_BUS.register(new AttributeApplier());
    }

    private double getSharpnessBonus(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!stack.isEmpty() && "sword".equals(ItemRarityUtils.getItemType(stack))) {
            return getSharpnessLevel(stack);
        }
        return 0.0;
    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player) || player.level().isClientSide) return;

        LivingEntity victim = event.getEntity();
        CompoundTag data = player.getPersistentData();
        float finalDamage = event.getAmount();

        boolean isProjectile = event.getSource().getDirectEntity() instanceof Projectile;
        InteractionHand hand = player.swingingArm;
        String mainType = ItemRarityUtils.getItemType(player.getMainHandItem());
        String offType = ItemRarityUtils.getItemType(player.getOffhandItem());

        boolean isMelee = (hand == InteractionHand.MAIN_HAND && (mainType.equals("sword") || mainType.isEmpty())) ||
                (hand == InteractionHand.OFF_HAND && (offType.equals("sword") || offType.isEmpty()));

        if (event.getSource() instanceof CustomMeleeDamageNoProcs) {
            double meleeDmg = getScaledValue(player, ModAttributes.MELEE_POTENCY.get(), ModAttributes.MELEE_POTENCY_MULTIPLIER.get());
            finalDamage *= (1.0 + meleeDmg / 100.0);

            double melCritDmg = getScaledValue(player, ModAttributes.MELEE_PRECISION.get(), ModAttributes.MELEE_PRECISION_MULTIPLIER.get());
            double melCritCh = getScaledValue(player, ModAttributes.MELEE_ACCURACY.get(), ModAttributes.MELEE_ACCURACY_MULTIPLIER.get());

            if (rollCrit(player, melCritCh)) {
                finalDamage *= (1.5 + melCritDmg / 100.0);
            }
        }
        else if (isMelee || event.getSource() instanceof CustomMeleeDamage) {
            double melDmg = getScaledValue(player, ModAttributes.MELEE_POTENCY.get(), ModAttributes.MELEE_POTENCY_MULTIPLIER.get());
            double melCritDmg = getScaledValue(player, ModAttributes.MELEE_PRECISION.get(), ModAttributes.MELEE_PRECISION_MULTIPLIER.get());
            double melCritCh = getScaledValue(player, ModAttributes.MELEE_ACCURACY.get(), ModAttributes.MELEE_ACCURACY_MULTIPLIER.get());

            if (player.hasEffect(ModEffects.BERSERK_READY.get()) && data.getBoolean("berserk_applied")) {
                finalDamage *= (1.0 + melDmg * 0.6 / 100.0);
                handleBerserkReset(player, data);
            } else if (data.getBoolean("berserk_applied")) {
                handleBerserkStacking(player, data);
            }

            if (data.getBoolean("bronzewood_proc")) {
                finalDamage += 3;
                data.putBoolean("bronzewood_proc", false);
            }

            if (event.getSource() instanceof CustomMeleeDamage && rollCrit(player, melCritCh)) {
                finalDamage *= (1.5 + melCritDmg / 100.0);
                playCritSound(player);
            }
            if (isMelee && (player.getPersistentData().getBoolean("adjustSharpness"))) {
                player.getPersistentData().putBoolean("adjustSharpness", false);
                finalDamage += getSharpnessBonus(player, hand) * (0.5 + melCritDmg / 100.0);

            }
            finalDamage *= (float) (1.0 + melDmg / 100.0);
            handleFrenzy(player, data);
        }
        else if (isProjectile || event.getSource() instanceof CustomRangedDamage) {
            double projDmg = getScaledValue(player, ModAttributes.PROJECTILE_POTENCY.get(), ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get());
            double projCritCh = getScaledValue(player, ModAttributes.PROJECTILE_ACCURACY.get(), ModAttributes.PROJECTILE_ACCURACY_MULTIPLIER.get());
            double projCritDmg = getScaledValue(player, ModAttributes.PROJECTILE_PRECISION.get(), ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get());

            if (event.getSource().getDirectEntity() instanceof Projectile proj) {
                if (!proj.getPersistentData().contains("ScaledDamage")) {
                    proj.getPersistentData().putDouble("ScaledDamage", finalDamage * (1.0 + projDmg / 100.0));
                }
                finalDamage = (float) proj.getPersistentData().getDouble("ScaledDamage");
            } else {
                finalDamage *= (1.0 + projDmg / 100.0);
            }

            if (data.getBoolean("Clairvoyance")) {
                finalDamage *= Math.pow((1.0 + projDmg / 100.0), 4);
                resetClairvoyance(player, data);
            }

            if (rollCrit(player, projCritCh)) {
                finalDamage *= (1.5 + projCritDmg / 100.0);
                playCritSound(player);
            }
            resetHawkeye(player, data);
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

        LivingEntity victim = event.getEntity();
        double incDamageMultiplier = victim.getAttributeValue(ModAttributes.INCOMING_DAMAGE_MULTIPLIER.get());
        double attackerDamageMultiplier = 1.0;

        if (event.getSource().getEntity() instanceof LivingEntity attacker) {
            attackerDamageMultiplier = attacker.getAttributeValue(ModAttributes.OUTGOING_DAMAGE_MULTIPLIER.get());
        }
        event.setAmount((float) (event.getAmount() * incDamageMultiplier * attackerDamageMultiplier));
        System.out.println(event.getAmount());
    }

    private boolean rollCrit(Player player, double chance) {
        return player.getRandom().nextDouble() < (chance + 10.0) / 100.0;
    }

    private void playCritSound(Player player) {
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.PLAYER_ATTACK_CRIT, SoundSource.PLAYERS, 1.0F, 1.0F);
    }

    private void handleBerserkReset(Player player, CompoundTag data) {
        player.playSound(SoundEvents.ZOMBIE_ATTACK_WOODEN_DOOR, 0.3F, 1.0F);
        player.removeEffect(ModEffects.BERSERK_READY.get());
        data.putInt("berserk", 0);
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
        if (miningSpeedBonus != 0) {
            float original = event.getNewSpeed();
            float boosted = (float) (original * (1.0 + (miningSpeedBonus / 100.0)));
            event.setNewSpeed(boosted);
        }
    }

    @SubscribeEvent
    public static void onLivingJump(LivingEvent.LivingJumpEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        double jumpBonus = player.getAttributeValue(ModAttributes.JUMP_BOOST.get());
        if (jumpBonus != 0) {
            double baseJumpHeight = 1.252;
            double gravity = 0.08;

            double newHeight = baseJumpHeight * (1.0 + (jumpBonus / 100.0));

            double requiredMotionY = Math.sqrt(2 * gravity * newHeight);

            player.setDeltaMovement(player.getDeltaMovement().x, requiredMotionY, player.getDeltaMovement().z);
        }
    }

    @SubscribeEvent
    public void onLivingFall(LivingFallEvent event) {
        if (event.getEntity() instanceof Player player) {

            float jumpBoost = (float) player.getAttributeValue(ModAttributes.JUMP_BOOST.get());
            float fallThreshold = 3.0f * (1.0f + jumpBoost / 100.0f);
            float fallDistance = event.getDistance();

            if (fallDistance <= fallThreshold) {
                event.setCanceled(true);
                return;
            }
            float damageMultiplier = 100.0f / (100.0f + jumpBoost);
            damageMultiplier = Math.max(0.0f, Math.min(1.0f, damageMultiplier));

            event.setDamageMultiplier(damageMultiplier);
        }
    }

    @SubscribeEvent
    public void onLivingAttack(LivingAttackEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        if (player.hasEffect(ModEffects.BASTION_ACTIVE.get())) {
            event.setCanceled(true);
            return;
        }

        if (player.hasEffect(ModEffects.NIMBLE_GETAWAY_ACTIVE.get())) {
            event.setCanceled(true);

            player.removeEffect(ModEffects.NIMBLE_GETAWAY_ACTIVE.get());
            player.addEffect(new MobEffectInstance(ModEffects.NIMBLE_GETAWAY_COOLDOWN.get(), 20*30, 0, false, false, true));

            player.playSound(SoundEvents.PLAYER_ATTACK_SWEEP, 1.0F, 1.5F);
        }

        if (event.getSource().getDirectEntity() instanceof AbstractArrow arrow) {
            CompoundTag nbt = arrow.getPersistentData();

            if (nbt.getBoolean("spirit_grove_knockback_cancel")) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onKnockback(LivingKnockBackEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.getLastDamageSource() != null &&
                player.getLastDamageSource().getDirectEntity() instanceof AbstractArrow arrow) {

            if (arrow.getPersistentData().getBoolean("spirit_grove_knockback_cancel")) {
                event.setStrength(0);
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void onHeal(LivingHealEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.level().isClientSide) return;
        if (player.hasEffect(ModEffects.BlACKENED_HEART.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onCrit(CriticalHitEvent event) {
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
}
