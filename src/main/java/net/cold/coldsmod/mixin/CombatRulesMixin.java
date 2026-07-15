package net.cold.coldsmod.mixin;

import net.minecraft.world.damagesource.CombatRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(CombatRules.class)
public class CombatRulesMixin {

    @Overwrite
    public static float getDamageAfterAbsorb(float pDamage, float pTotalArmor, float pToughnessAttribute) {

        double armorReduction = pTotalArmor / (40 + pTotalArmor - (28 + pTotalArmor * 0.005) * ((25 + pToughnessAttribute) / (pToughnessAttribute + 35)));

        double finalDamageDouble = pDamage * (1.0 - armorReduction);


        return (float) finalDamageDouble;
    }
}
