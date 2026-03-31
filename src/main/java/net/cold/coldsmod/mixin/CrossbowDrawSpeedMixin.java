package net.cold.coldsmod.mixin;

import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CrossbowItem.class)
public abstract class CrossbowDrawSpeedMixin {

    @Inject(method = "getChargeDuration", at = @At("RETURN"), cancellable = true)
    private static void modifyCrossbowChargeDuration(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (stack == null || !stack.hasTag()) return;

        double drawSpeed = stack.getOrCreateTag().getDouble("draw");

        double modifiedTime = cir.getReturnValue();

        if (drawSpeed != 0) {
            double multiplier = 1.0 / (1.0 + (drawSpeed / 100.0));
            modifiedTime *= multiplier;
        }

        if (stack.getOrCreateTag().getBoolean("adr")) modifiedTime /= 2;

        cir.setReturnValue((int) modifiedTime);
    }
}
