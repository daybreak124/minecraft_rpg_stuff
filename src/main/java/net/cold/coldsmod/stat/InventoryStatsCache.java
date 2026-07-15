package net.cold.coldsmod.stat;

import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.common.ForgeMod;

public class InventoryStatsCache {
    public static final InventoryStatsCache CACHE = new InventoryStatsCache();

    // Final Engine-Calculated Values
    public double str, dex, fort, con, perc, wis, ins;
    public double armor, tough, health, incDmg, totalResist, eHP, fEva, knkRes, debuffRes;
    public double fMeleePot, fMeleeHaste, fMeleeAcc, fMeleePre, mAvg, mMax;
    public double fProjPot, fProjNock, fProjAcc, fProjPre, pAvg;
    public double fGenPot, fGenHaste, fGenAcc, fGenPre, fDotMult, fAllDmgMult, fMeleeMult, fProjMult;
    public double fResto, fAmp, fReju;
    public double moveSpeed, swimSpeed, stepHeight, jumpBoost, blockReach, entityReach, mineSpeed, xpGain, luck;

    // Values WITHOUT Operation 2 (Multiply Total) for comparison
    public double bArmor, bTough, bHealth, bMeleePot, bHaste, bMeleeAcc, bMeleePre;
    public double bProjPot, bNock, bProjAcc, bProjPre, bPot, bAcc, bPre, bOHaste;
    public double bResto, bAmp, bReju;

    public long lastUpdateTick = -1;

    private InventoryStatsCache() {}

    public static void rebuildCache(Minecraft mc) {
        if (mc.player == null) return;

        if (mc.player.tickCount % 20 == 0) {
            Player p = mc.player;

            // --- Core Attributes ---
            CACHE.str = p.getAttributeValue(ModAttributes.STR.get());
            CACHE.dex = p.getAttributeValue(ModAttributes.DEX.get());
            CACHE.fort = p.getAttributeValue(ModAttributes.FORT.get());
            CACHE.con = p.getAttributeValue(ModAttributes.CON.get());
            CACHE.perc = p.getAttributeValue(ModAttributes.PERC.get());
            CACHE.wis = p.getAttributeValue(ModAttributes.WISDOM.get());
            CACHE.ins = p.getAttributeValue(ModAttributes.INSIGHT.get());

            // --- Final Values (Includes all Operations) ---
            CACHE.fMeleePot = p.getAttributeValue(ModAttributes.MELEE_POTENCY.get());
            CACHE.fMeleeHaste = p.getAttributeValue(ModAttributes.MELEE_HASTE.get());
            CACHE.fMeleeAcc = p.getAttributeValue(ModAttributes.MELEE_ACCURACY.get());
            CACHE.fMeleePre = p.getAttributeValue(ModAttributes.MELEE_PRECISION.get());

            CACHE.fProjPot = p.getAttributeValue(ModAttributes.PROJECTILE_POTENCY.get());
            CACHE.fProjNock = p.getAttributeValue(ModAttributes.NOCK_HASTE.get());
            CACHE.fProjAcc = p.getAttributeValue(ModAttributes.PROJECTILE_ACCURACY.get());
            CACHE.fProjPre = p.getAttributeValue(ModAttributes.PROJECTILE_PRECISION.get());

            CACHE.fGenPot = p.getAttributeValue(ModAttributes.POTENCY.get());
            CACHE.fGenHaste = p.getAttributeValue(ModAttributes.HASTE.get());
            CACHE.fGenAcc = p.getAttributeValue(ModAttributes.ACCURACY.get());
            CACHE.fGenPre = p.getAttributeValue(ModAttributes.PRECISION.get());

            CACHE.fResto = p.getAttributeValue(ModAttributes.RESTORATION.get());
            CACHE.fAmp = p.getAttributeValue(ModAttributes.AMPLIFICATION.get());
            CACHE.fReju = p.getAttributeValue(ModAttributes.REJUVENATION.get());

            // --- Survivability & Damage ---
            CACHE.armor = p.getAttributeValue(Attributes.ARMOR);
            CACHE.tough = p.getAttributeValue(Attributes.ARMOR_TOUGHNESS);
            CACHE.health = p.getMaxHealth();
            CACHE.incDmg = p.getAttributeValue(ModAttributes.INCOMING_DAMAGE_MULTIPLIER.get());
            CACHE.fEva = p.getAttributeValue(ModAttributes.EVASION.get()) * 100.0;
            CACHE.knkRes = p.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE) * 100.0;
            CACHE.debuffRes = p.getAttributeValue(ModAttributes.DEBUFF_RESIST.get());
            CACHE.fAllDmgMult = p.getAttributeValue(ModAttributes.ALL_DAMAGE_MULTIPLIER.get());
            CACHE.fDotMult = p.getAttributeValue(ModAttributes.DOT_DAMAGE_MULTIPLIER.get());
            CACHE.fMeleeMult = p.getAttributeValue(ModAttributes.MELEE_DAMAGE_MULTIPLIER.get());
            CACHE.fProjMult = p.getAttributeValue(ModAttributes.PROJECTILE_DAMAGE_MULTIPLIER.get());

            // -----------------------------
            // --- Melee Calculations ---
            double mCritChance = (10.0 + AttributeApplier.getScaledValue(p, ModAttributes.MELEE_ACCURACY.get())) / 100.0;
            double mCritBonus = (25.0 + AttributeApplier.getScaledValue(p, ModAttributes.MELEE_PRECISION.get())) / 100.0;

            double mPotMult = 1.0 + (AttributeApplier.getScaledValue(p, ModAttributes.MELEE_POTENCY.get()) / 100.0);
            double mHasteMult = 1.0 + (AttributeApplier.getScaledValue(p, ModAttributes.MELEE_HASTE.get()) / 100.0);

            CACHE.mAvg = (mPotMult * (1.0 + (Math.min(1.0, mCritChance - 0.10) * mCritBonus - 0.25)) * mHasteMult) * CACHE.fMeleeMult;
            CACHE.mMax = (mPotMult * (1.0 + mCritBonus) * mHasteMult) * CACHE.fMeleeMult / 1.025;

            // --- Projectile Calculations ---
            double pCritChance = (10.0 + AttributeApplier.getScaledValue(p, ModAttributes.PROJECTILE_ACCURACY.get())) / 100.0;
            double pCritBonus = (25.0 + AttributeApplier.getScaledValue(p, ModAttributes.PROJECTILE_PRECISION.get())) / 100.0;

            double pPotMult = 1.0 + (AttributeApplier.getScaledValue(p, ModAttributes.PROJECTILE_POTENCY.get()) / 100.0);
            double pNockMult = 1.0 + (AttributeApplier.getScaledValue(p, ModAttributes.NOCK_HASTE.get()) / 100.0);

            CACHE.pAvg = (pPotMult * (1.0 + (Math.min(1.0, pCritChance) * pCritBonus)) * pNockMult) * CACHE.fProjMult / 1.025;
            // -----------------------------

            CACHE.moveSpeed = (p.getAttributeValue(Attributes.MOVEMENT_SPEED) / 0.1) * 100 - 100;
            CACHE.swimSpeed = (p.getAttributeValue(ForgeMod.SWIM_SPEED.get()) * 100) - 100;
            CACHE.stepHeight = p.getAttributeValue(ForgeMod.STEP_HEIGHT_ADDITION.get());
            CACHE.jumpBoost = (p.getAttributeValue(ModAttributes.JUMP_BOOST.get()) * 100) - 100;
            CACHE.blockReach = p.getAttributeValue(ForgeMod.BLOCK_REACH.get());
            CACHE.entityReach = p.getAttributeValue(ForgeMod.ENTITY_REACH.get());
            CACHE.mineSpeed = (p.getAttributeValue(ModAttributes.MINING_SPEED.get()) * 100) - 100;
            CACHE.xpGain = (p.getAttributeValue(ModAttributes.XP_GAIN.get()) * 100) - 100;
            CACHE.luck = p.getAttributeValue(Attributes.LUCK);

            // -----------------------------
            double armorRed = CACHE.armor / (40 + CACHE.armor - (28+ CACHE.armor*0.005) * ((CACHE.tough + 25) / (CACHE.tough + 35)));
            double res = p.hasEffect(MobEffects.DAMAGE_RESISTANCE) ? (p.getEffect(MobEffects.DAMAGE_RESISTANCE).getAmplifier() + 1) * 0.2 : 0.0;
            int prot = 0;
            for (ItemStack s : p.getArmorSlots()) prot += EnchantmentHelper.getItemEnchantmentLevel(Enchantments.ALL_DAMAGE_PROTECTION, s);
            double protRed = prot * 0.02;
            CACHE.totalResist = (1.0 - ((1.0 - armorRed) * (1.0 - protRed) * (1.0 - res) * CACHE.incDmg)) * 100.0;
            CACHE.eHP = CACHE.health / (1.0 - (CACHE.totalResist / 100.0));

            CACHE.bArmor = getBaseValue(p, Attributes.ARMOR);
            CACHE.bTough = getBaseValue(p, Attributes.ARMOR_TOUGHNESS);
            CACHE.bHealth = getBaseValue(p, Attributes.MAX_HEALTH);
            CACHE.bMeleePot = getBaseValue(p, ModAttributes.MELEE_POTENCY.get());
            CACHE.bHaste = getBaseValue(p, ModAttributes.MELEE_HASTE.get());
            CACHE.bMeleeAcc = getBaseValue(p, ModAttributes.MELEE_ACCURACY.get());
            CACHE.bMeleePre = getBaseValue(p, ModAttributes.MELEE_PRECISION.get());
            CACHE.bProjPot = getBaseValue(p, ModAttributes.PROJECTILE_POTENCY.get());
            CACHE.bNock = getBaseValue(p, ModAttributes.NOCK_HASTE.get());
            CACHE.bProjAcc = getBaseValue(p, ModAttributes.PROJECTILE_ACCURACY.get());
            CACHE.bProjPre = getBaseValue(p, ModAttributes.PROJECTILE_PRECISION.get());
            CACHE.bPot = getBaseValue(p, ModAttributes.POTENCY.get());
            CACHE.bAcc = getBaseValue(p, ModAttributes.ACCURACY.get());
            CACHE.bPre = getBaseValue(p, ModAttributes.PRECISION.get());
            CACHE.bOHaste = getBaseValue(p, ModAttributes.HASTE.get());
            CACHE.bResto = getBaseValue(p, ModAttributes.RESTORATION.get());
            CACHE.bAmp = getBaseValue(p, ModAttributes.AMPLIFICATION.get());
            CACHE.bReju = getBaseValue(p, ModAttributes.REJUVENATION.get());

            CACHE.lastUpdateTick = p.tickCount;
        }
    }

    private static double getBaseValue(Player player, net.minecraft.world.entity.ai.attributes.Attribute attribute) {
        AttributeInstance instance = player.getAttribute(attribute);
        if (instance == null) return 0.0;
        double val = instance.getBaseValue();
        for (AttributeModifier modifier : instance.getModifiers(AttributeModifier.Operation.ADDITION)) {
            val += modifier.getAmount();
        }
        double multiplier = 0.0;
        for (AttributeModifier modifier : instance.getModifiers(AttributeModifier.Operation.MULTIPLY_BASE)) {
            multiplier += modifier.getAmount();
        }
        return val * (1.0 + multiplier);
    }
}