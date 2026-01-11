package net.cold.coldsmod.mixin;

import net.minecraft.world.damagesource.CombatRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(CombatRules.class)
public class CombatRulesMixin {
    @Overwrite
    public static float getDamageAfterAbsorb(float pDamage, float pTotalArmor, float pToughnessAttribute) {
        double armor = pTotalArmor;
        double toughness = pToughnessAttribute;

        double armorReduction = armor / (80 + armor - 80 * (toughness / (toughness + 50.0)));
        double finalDamageDouble = pDamage * (1.0 - armorReduction);

        return (float) finalDamageDouble;

    }
}
