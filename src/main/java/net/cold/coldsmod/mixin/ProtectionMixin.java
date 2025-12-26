package net.cold.coldsmod.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ProtectionEnchantment.class)
public class ProtectionMixin {

    @Inject(
            method = "getDamageProtection",
            at = @At("HEAD"),
            cancellable = true
    )
    private void halveProtection(int level, DamageSource source, CallbackInfoReturnable<Integer> cir) {
        int value;
        ProtectionEnchantment self = (ProtectionEnchantment) (Object) this;

        switch (self.type) {
            case ALL -> value = level;
            case FIRE -> value = source.is(net.minecraft.tags.DamageTypeTags.IS_FIRE) ? level * 2 : 0;
            case FALL -> value = source.is(net.minecraft.tags.DamageTypeTags.IS_FALL) ? level * 3 : 0;
            case EXPLOSION -> value = source.is(net.minecraft.tags.DamageTypeTags.IS_EXPLOSION) ? level * 2 : 0;
            case PROJECTILE -> value = source.is(net.minecraft.tags.DamageTypeTags.IS_PROJECTILE) ? level * 2 : 0;
            default -> value = 0;
        }

        cir.setReturnValue(value / 2);
    }
}
