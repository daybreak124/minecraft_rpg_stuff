package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.cold.coldsmod.stat.ModAttributes;
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


public class Recovery extends MobEffect {

    private static final UUID REC_UUID = UUID.fromString("7422ba44-5ab5-bb6a-7777-a28bcdcdcd88");

    public Recovery() {
        super(MobEffectCategory.BENEFICIAL, 0xFF0000);

        this.addAttributeModifier(Attributes.ARMOR,
                REC_UUID.toString(),
                6,
                AttributeModifier.Operation.ADDITION);

        this.addAttributeModifier(ModAttributes.REJUVENATION.get(),
                REC_UUID.toString(),
                6,
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
