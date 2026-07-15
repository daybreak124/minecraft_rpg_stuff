package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static net.cold.coldsmod.stat.AttributeApplier.recalcAS;

public class BladeDancer extends MobEffect {

    private static final UUID BLADEDANCER_UUID = UUID.fromString("4411a144-5225-6666-7777-aa88118cc888");

    public BladeDancer() {
        super(MobEffectCategory.BENEFICIAL, 0xFF0000);

        this.addAttributeModifier(Attributes.ATTACK_SPEED,
                BLADEDANCER_UUID.toString(),
                0.05D,
                AttributeModifier.Operation.ADDITION);
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        super.addAttributeModifiers(entity, attributeMap, amplifier);
        if (entity instanceof Player player) {
            recalcAS(player);
        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        super.removeAttributeModifiers(entity, attributeMap, amplifier);
        if (entity instanceof Player player) {
            recalcAS(player);
        }
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}
