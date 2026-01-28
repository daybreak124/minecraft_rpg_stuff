package net.cold.coldsmod.stat;

import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.common.ForgeMod;

import static net.cold.coldsmod.stat.AttributeApplier.getScaledValue;

public class InventoryStatsCache {
    public static final InventoryStatsCache CACHE = new InventoryStatsCache();

    // Data Fields
    public double mPot, mAcc, mPre, mHaste, mAvg, mMax;
    public double pPot, pAcc, pPre, pNock, pAvg;
    public double gPot, gAcc, gPre, gMult;
    public double armor, tough, incDmg, totalResist, health, knkRes, debuffRes;
    public double restoration, amplification, rejuvenation;
    public double moveSpeed, swimSpeed, stepHeight, jumpBoost;
    public double blockReach, entityReach, mineSpeed, xpGain, luck;
    public double bMeleePot, mMeleeMult, bHaste, mHasteMult, bMeleeAcc, mMeleeAccMult, bMeleePre, mMeleePreMult;
    public double bProjPot, mProjMult, bNock, mNockMult, bProjAcc, mProjAccMult, bProjPre, mProjPreMult;
    public double bPot, mPotMult, bAcc, mAccMult, bPre, mPreMult;
    public double bResto, mRestoMult, bAmp, mAmpMult, bReju, mRejuMult;
    public double baseArmor, multArmor, baseTough, multTough, baseHealth, multHealth;
    public double str, dex, fort, con, perc, wis, ins;
    public double fMeleePot, fMeleeHaste, fMeleeAcc, fMeleePre;
    public double fProjPot, fProjNock, fProjAcc, fProjPre;
    public double fGenPot, fGenAcc, fGenPre;
    public double fResto, fAmp, fReju;

    public long lastUpdateTick = -1;

    private InventoryStatsCache() {}

    public static void rebuildCache(Minecraft mc) {
        if (mc.player == null || mc.player.tickCount < CACHE.lastUpdateTick + 20) return;
        var p = mc.player;

        // --- Melee ---
        CACHE.bMeleePot = p.getAttributeValue(ModAttributes.MELEE_POTENCY.get());
        CACHE.mMeleeMult = p.getAttributeValue(ModAttributes.MELEE_POTENCY_MULTIPLIER.get());
        CACHE.bHaste = p.getAttributeValue(ModAttributes.HASTE.get());
        CACHE.mHasteMult = p.getAttributeValue(ModAttributes.HASTE_MULTIPLIER.get());
        CACHE.bMeleeAcc = p.getAttributeValue(ModAttributes.MELEE_ACCURACY.get());
        CACHE.mMeleeAccMult = p.getAttributeValue(ModAttributes.MELEE_ACCURACY_MULTIPLIER.get());
        CACHE.bMeleePre = p.getAttributeValue(ModAttributes.MELEE_PRECISION.get());
        CACHE.mMeleePreMult = p.getAttributeValue(ModAttributes.MELEE_PRECISION_MULTIPLIER.get());

        CACHE.str  = p.getAttributeValue(ModAttributes.STR.get());
        CACHE.dex  = p.getAttributeValue(ModAttributes.DEX.get());
        CACHE.fort = p.getAttributeValue(ModAttributes.FORT.get());
        CACHE.con  = p.getAttributeValue(ModAttributes.CON.get());
        CACHE.perc = p.getAttributeValue(ModAttributes.PERC.get());
        CACHE.wis  = p.getAttributeValue(ModAttributes.WISDOM.get());
        CACHE.ins  = p.getAttributeValue(ModAttributes.INSIGHT.get());

        // --- Projectile ---
        CACHE.bProjPot = p.getAttributeValue(ModAttributes.PROJECTILE_POTENCY.get());
        CACHE.mProjMult = p.getAttributeValue(ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get());
        CACHE.bNock = p.getAttributeValue(ModAttributes.NOCK_HASTE.get());
        CACHE.mNockMult = p.getAttributeValue(ModAttributes.NOCK_HASTE_MULTIPLIER.get());
        CACHE.bProjAcc = p.getAttributeValue(ModAttributes.PROJECTILE_ACCURACY.get());
        CACHE.mProjAccMult = p.getAttributeValue(ModAttributes.PROJECTILE_ACCURACY_MULTIPLIER.get());
        CACHE.bProjPre = p.getAttributeValue(ModAttributes.PROJECTILE_PRECISION.get());
        CACHE.mProjPreMult = p.getAttributeValue(ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get());

        // --- General ---
        CACHE.bPot = p.getAttributeValue(ModAttributes.POTENCY.get());
        CACHE.mPotMult = p.getAttributeValue(ModAttributes.POTENCY_MULTIPLIER.get());
        CACHE.bAcc = p.getAttributeValue(ModAttributes.ACCURACY.get());
        CACHE.mAccMult = p.getAttributeValue(ModAttributes.ACCURACY_MULTIPLIER.get());
        CACHE.bPre = p.getAttributeValue(ModAttributes.PRECISION.get());
        CACHE.mPreMult = p.getAttributeValue(ModAttributes.PRECISION_MULTIPLIER.get());

        // --- Healing ---
        CACHE.bResto = p.getAttributeValue(ModAttributes.RESTORATION.get());
        CACHE.mRestoMult = p.getAttributeValue(ModAttributes.RESTORATION_MULTIPLIER.get());
        CACHE.bAmp = p.getAttributeValue(ModAttributes.AMPLIFICATION.get());
        CACHE.mAmpMult = p.getAttributeValue(ModAttributes.AMPLIFICATION_MULTIPLIER.get());
        CACHE.bReju = p.getAttributeValue(ModAttributes.REJUVENATION.get());
        CACHE.mRejuMult = p.getAttributeValue(ModAttributes.REJUVENATION_MULTIPLIER.get());

        // --- Scaling ---
        CACHE.mPot = getScaledValue(p, ModAttributes.MELEE_POTENCY.get(), ModAttributes.MELEE_POTENCY_MULTIPLIER.get());
        CACHE.mAcc = getScaledValue(p, ModAttributes.MELEE_ACCURACY.get(), ModAttributes.MELEE_ACCURACY_MULTIPLIER.get());
        CACHE.mPre = getScaledValue(p, ModAttributes.MELEE_PRECISION.get(), ModAttributes.MELEE_PRECISION_MULTIPLIER.get());
        CACHE.mHaste = getScaledValue(p, ModAttributes.HASTE.get(), ModAttributes.HASTE_MULTIPLIER.get());

        CACHE.pPot = getScaledValue(p, ModAttributes.PROJECTILE_POTENCY.get(), ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get());
        CACHE.pAcc = getScaledValue(p, ModAttributes.PROJECTILE_ACCURACY.get(), ModAttributes.PROJECTILE_ACCURACY_MULTIPLIER.get());
        CACHE.pPre = getScaledValue(p, ModAttributes.PROJECTILE_PRECISION.get(), ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get());
        CACHE.pNock = getScaledValue(p, ModAttributes.NOCK_HASTE.get(), ModAttributes.NOCK_HASTE_MULTIPLIER.get());

        // --- Melee Math ---
        double mCritChance = Math.min(10.0 + CACHE.mAcc, 100.0) / 100.0;
        double mCritBonus = 0.5 + (CACHE.mPre / 100.0);
        CACHE.mAvg = ((1.0 + CACHE.mPot / 100.0) * (1.0 + (mCritChance * mCritBonus)) * (1.0 + CACHE.mHaste / 100.0)) / 1.05;
        CACHE.mMax = ((1.0 + CACHE.mPot / 100.0) * (1.0 + mCritBonus) * (1.0 + CACHE.mHaste / 100.0)) / 1.05;

        // --- Projectile Math ---
        double pCritChance = Math.min(10.0 + CACHE.pAcc, 100.0) / 100.0;
        double pCritBonus = 0.5 + (CACHE.pPre / 100.0);
        CACHE.pAvg = ((1.0 + CACHE.pPot / 100.0) * (1.0 + (pCritChance * pCritBonus)) * (1.0 + CACHE.pNock / 100.0)) / 1.05;

        // --- General ---
        CACHE.gMult = p.getAttributeValue(ModAttributes.OUTGOING_DAMAGE_MULTIPLIER.get());
        CACHE.gPot = getScaledValue(p, ModAttributes.POTENCY.get(), ModAttributes.POTENCY_MULTIPLIER.get());
        CACHE.gAcc = getScaledValue(p, ModAttributes.ACCURACY.get(), ModAttributes.ACCURACY_MULTIPLIER.get());
        CACHE.gPre = getScaledValue(p, ModAttributes.PRECISION.get(), ModAttributes.PRECISION_MULTIPLIER.get());

        CACHE.fMeleePot = CACHE.bMeleePot * CACHE.mMeleeMult;
        CACHE.fMeleeHaste = CACHE.bHaste * CACHE.mHasteMult;
        CACHE.fMeleeAcc = CACHE.bMeleeAcc * CACHE.mMeleeAccMult;
        CACHE.fMeleePre = CACHE.bMeleePre * CACHE.mMeleePreMult;

        CACHE.fProjPot = CACHE.bProjPot * CACHE.mProjMult;
        CACHE.fProjNock = CACHE.bNock * CACHE.mNockMult;
        CACHE.fProjAcc = CACHE.bProjAcc * CACHE.mProjAccMult;
        CACHE.fProjPre = CACHE.bProjPre * CACHE.mProjPreMult;

        CACHE.fGenPot = CACHE.bPot * CACHE.mPotMult;
        CACHE.fGenAcc = CACHE.bAcc * CACHE.mAccMult;
        CACHE.fGenPre = CACHE.bPre * CACHE.mPreMult;

        CACHE.fResto = CACHE.bResto * CACHE.mRestoMult;
        CACHE.fAmp   = CACHE.bAmp   * CACHE.mAmpMult;
        CACHE.fReju  = CACHE.bReju  * CACHE.mRejuMult;

        // --- Survivability ---
        CACHE.armor = p.getAttributeValue(Attributes.ARMOR);
        CACHE.tough = p.getAttributeValue(Attributes.ARMOR_TOUGHNESS);
        CACHE.health = p.getMaxHealth();
        CACHE.incDmg = p.getAttributeValue(ModAttributes.INCOMING_DAMAGE_MULTIPLIER.get());
        CACHE.knkRes = p.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE) * 100.0;
        CACHE.debuffRes = p.getAttributeValue(ModAttributes.DEBUFF_RESIST.get());

        double armorRed = CACHE.armor / (80.0 + CACHE.armor - 80.0 * (CACHE.tough / (CACHE.tough + 50.0)));
        double res = p.hasEffect(MobEffects.DAMAGE_RESISTANCE) ? (p.getEffect(MobEffects.DAMAGE_RESISTANCE).getAmplifier() + 1) * 0.2 : 0.0;
        int prot = 0;
        for (ItemStack s : p.getArmorSlots()) prot += EnchantmentHelper.getItemEnchantmentLevel(Enchantments.ALL_DAMAGE_PROTECTION, s);
        double protRed = prot * 0.02;
        CACHE.totalResist = (1.0 - ((1.0 - armorRed) * (1.0 - protRed) * (1.0 - res) * CACHE.incDmg)) * 100.0;

        // --- Base/Mult splits ---
        CACHE.multArmor = 1 + p.getAttributeValue(ModAttributes.ARMOR_MULTIPLIER.get());
        CACHE.baseArmor  = CACHE.multArmor  == 0 ? 0 : CACHE.armor  / CACHE.multArmor;
        CACHE.multTough = p.getAttributeValue(ModAttributes.TOUGHNESS_MULTIPLIER.get());
        CACHE.baseTough  = 1 + CACHE.multTough  == 0 ? 0 : CACHE.tough  / CACHE.multTough;
        CACHE.multHealth = p.getAttributeValue(ModAttributes.HEALTH_MULTIPLIER.get());
        CACHE.baseHealth = 1 + CACHE.multHealth == 0 ? 0 : CACHE.health / CACHE.multHealth;

        // --- Misc ---
        CACHE.restoration = getScaledValue(p, ModAttributes.RESTORATION.get(), ModAttributes.RESTORATION_MULTIPLIER.get());
        CACHE.amplification = getScaledValue(p, ModAttributes.AMPLIFICATION.get(), ModAttributes.AMPLIFICATION_MULTIPLIER.get());
        CACHE.rejuvenation = getScaledValue(p, ModAttributes.REJUVENATION.get(), ModAttributes.REJUVENATION_MULTIPLIER.get());

        CACHE.moveSpeed = 1000 * p.getAttributeValue(Attributes.MOVEMENT_SPEED) - 100;
        CACHE.swimSpeed = 100 * p.getAttributeValue(ForgeMod.SWIM_SPEED.get()) - 100;
        CACHE.stepHeight = p.getAttributeValue(ForgeMod.STEP_HEIGHT_ADDITION.get());
        CACHE.jumpBoost = 100 * p.getAttributeValue(ModAttributes.JUMP_BOOST.get()) -100;
        CACHE.blockReach = p.getAttributeValue(ForgeMod.BLOCK_REACH.get());
        CACHE.entityReach = p.getAttributeValue(ForgeMod.ENTITY_REACH.get());
        CACHE.mineSpeed = 100 * p.getAttributeValue(ModAttributes.MINING_SPEED.get()) -100;
        CACHE.xpGain = 100 * p.getAttributeValue(ModAttributes.XP_GAIN.get()) -100;
        CACHE.luck = p.getAttributeValue(Attributes.LUCK);

        CACHE.lastUpdateTick = p.tickCount;
    }
}