package net.cold.coldsmod.mixin;

import net.minecraft.world.damagesource.CombatRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(CombatRules.class)
public class CombatRulesMixin {
    @Overwrite
    public static float getDamageAfterAbsorb(float pDamage, float pTotalArmor, float pToughnessAttribute) {

        double armorReduction = (double) pTotalArmor / (80 + (double) pTotalArmor - 80 * ((double) pToughnessAttribute / ((double) pToughnessAttribute + 50.0)));
        double finalDamageDouble = pDamage * (1.0 - armorReduction);

        return (float) finalDamageDouble;

    }
}
