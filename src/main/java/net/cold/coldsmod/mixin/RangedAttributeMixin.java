package net.cold.coldsmod.mixin;

import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(RangedAttribute.class)
public interface RangedAttributeMixin {
    @Mutable
    @Accessor("minValue")
    void setMinValue(double value);

    @Mutable
    @Accessor("maxValue")
    void setMaxValue(double value);
}






