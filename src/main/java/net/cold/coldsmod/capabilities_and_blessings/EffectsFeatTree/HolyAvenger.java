package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static net.cold.coldsmod.stat.AttributeApplier.crossbowDrawSpeedUpdate;
import static net.cold.coldsmod.stat.AttributeApplier.recalcAS;

public class HolyAvenger extends MobEffect {

    private static final UUID HA_UUID = UUID.fromString("4411a144-5225-6676-7777-aa12348cc888");

    public HolyAvenger() {
        super(MobEffectCategory.BENEFICIAL, 0xFF0000);

        this.addAttributeModifier(ModAttributes.HASTE.get(),
                HA_UUID.toString(),
                15D,
                AttributeModifier.Operation.ADDITION);

        this.addAttributeModifier(ModAttributes.MELEE_HASTE.get(),
                HA_UUID.toString(),
                15D,
                AttributeModifier.Operation.ADDITION);

        this.addAttributeModifier(ModAttributes.NOCK_HASTE.get(),
                HA_UUID.toString(),
                15D,
                AttributeModifier.Operation.ADDITION);
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        super.addAttributeModifiers(entity, attributeMap, amplifier);
        if (entity instanceof Player player) {
            recalcAS(player);

            for (InteractionHand hand : InteractionHand.values()) {
                ItemStack stack = player.getItemInHand(hand);
                if (stack.getItem() instanceof CrossbowItem) {
                    crossbowDrawSpeedUpdate(player, stack, stack.getOrCreateTag());
                }
            }
        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        super.removeAttributeModifiers(entity, attributeMap, amplifier);
        if (entity instanceof Player player) {
            recalcAS(player);

            for (InteractionHand hand : InteractionHand.values()) {
                ItemStack stack = player.getItemInHand(hand);
                if (stack.getItem() instanceof CrossbowItem) {
                    crossbowDrawSpeedUpdate(player, stack, stack.getOrCreateTag());
                }

//                if (ItemRarityUtils.getItemType(stack).equals("crossbow")) {
//                    crossbowDrawSpeedUpdate(player, stack, stack.getOrCreateTag());
//                }
            }
        }
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}
