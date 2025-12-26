package net.cold.coldsmod.stat;

import net.cold.coldsmod.mixin.RangedAttributeMixin;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

@Mod.EventBusSubscriber(modid = "coldsmod", bus = Mod.EventBusSubscriber.Bus.MOD)
public class AttributeOverrides {

    @SubscribeEvent
    public static void onLoadComplete(FMLLoadCompleteEvent event) {
        ((RangedAttributeMixin) (Object) Attributes.ARMOR).setMaxValue(1024.0);
        ((RangedAttributeMixin) (Object) Attributes.ARMOR_TOUGHNESS).setMaxValue(1024.0);
        ((RangedAttributeMixin) (Object) Attributes.MAX_HEALTH).setMaxValue(4096.0);
        ((RangedAttributeMixin) (Object) Attributes.ATTACK_DAMAGE).setMaxValue(4096.0);
        ((RangedAttributeMixin) (Object) Attributes.ATTACK_KNOCKBACK).setMaxValue(1024.0);

        ((RangedAttributeMixin) (Object) Attributes.ARMOR).setMinValue(-1024.0);
        ((RangedAttributeMixin) (Object) Attributes.ARMOR_TOUGHNESS).setMinValue(-1024.0);
    }
}
