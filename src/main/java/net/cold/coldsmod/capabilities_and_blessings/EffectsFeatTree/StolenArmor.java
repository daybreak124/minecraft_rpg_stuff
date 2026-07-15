package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class StolenArmor extends MobEffect {

    private static final UUID SA_UUID = UUID.fromString("b422a1a4-5ab5-aab6-7bb7-aaddbb8cc888");

    public StolenArmor() {
        super(MobEffectCategory.BENEFICIAL, 0x000000);

        this.addAttributeModifier(Attributes.ARMOR,
                SA_UUID.toString(),
                4,
                AttributeModifier.Operation.ADDITION);
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        super.addAttributeModifiers(entity, attributeMap, amplifier);
//        if (entity instanceof Player player) {
//            recalcArmor(player);
//        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        super.removeAttributeModifiers(entity, attributeMap, amplifier);
//        if (entity instanceof Player player) {
//            recalcArmor(player);
//        }
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}
