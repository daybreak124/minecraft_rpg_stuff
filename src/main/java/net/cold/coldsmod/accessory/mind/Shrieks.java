package net.cold.coldsmod.accessory.mind;

import net.cold.coldsmod.ColdsMod;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import top.theillusivec4.curios.api.SlotContext;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class Shrieks {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static final UUID SHRIEKS_UNSEEING_UUID = UUID.fromString("b2c3d4e5-f6a7-4b8c-9d0e-1f2a3b4c5d6e");

// --- Registry ---

    public static final RegistryObject<Item> SHRIEKS_OF_UNSEEING_RARE = ITEMS.register(
            "shrieks_of_unseeing_rare",
            () -> new ShrieksOfUnseeingRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> SHRIEKS_OF_UNSEEING_EPIC = ITEMS.register(
            "shrieks_of_unseeing_epic",
            () -> new ShrieksOfUnseeingEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> SHRIEKS_OF_UNSEEING_LEGENDARY = ITEMS.register(
            "shrieks_of_unseeing_legendary",
            () -> new ShrieksOfUnseeingLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> SHRIEKS_OF_UNSEEING_MYTHIC = ITEMS.register(
            "shrieks_of_unseeing_mythic",
            () -> new ShrieksOfUnseeingMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class ShrieksOfUnseeingRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public ShrieksOfUnseeingRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 8.0, SHRIEKS_UNSEEING_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, SHRIEKS_UNSEEING_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 3.0, SHRIEKS_UNSEEING_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), -3.0, SHRIEKS_UNSEEING_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), -15.0, SHRIEKS_UNSEEING_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 5.0, SHRIEKS_UNSEEING_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "shrieks_of_unseeing");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), SHRIEKS_UNSEEING_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SHRIEKS_UNSEEING_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR, SHRIEKS_UNSEEING_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), SHRIEKS_UNSEEING_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), SHRIEKS_UNSEEING_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), SHRIEKS_UNSEEING_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Shrieks of Unseeing").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+8 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-3 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+5% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("-15 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Otherworlds").withStyle(ChatFormatting.DARK_AQUA));
        }
    }

// --- Epic ---

    private static class ShrieksOfUnseeingEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public ShrieksOfUnseeingEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 12.0, SHRIEKS_UNSEEING_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, SHRIEKS_UNSEEING_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 5.0, SHRIEKS_UNSEEING_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), -3.0, SHRIEKS_UNSEEING_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), -15.0, SHRIEKS_UNSEEING_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 7.5, SHRIEKS_UNSEEING_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "shrieks_of_unseeing");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), SHRIEKS_UNSEEING_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SHRIEKS_UNSEEING_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR, SHRIEKS_UNSEEING_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), SHRIEKS_UNSEEING_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), SHRIEKS_UNSEEING_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), SHRIEKS_UNSEEING_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Shrieks of Unseeing").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+12 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-3 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+7.5% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("-15 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Otherworlds").withStyle(ChatFormatting.DARK_AQUA));
        }
    }

// --- Legendary ---

    private static class ShrieksOfUnseeingLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public ShrieksOfUnseeingLegendary(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 15.0, SHRIEKS_UNSEEING_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, SHRIEKS_UNSEEING_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 8.0, SHRIEKS_UNSEEING_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), 0.075, SHRIEKS_UNSEEING_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), -3.0, SHRIEKS_UNSEEING_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), -15.0, SHRIEKS_UNSEEING_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 10.0, SHRIEKS_UNSEEING_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "shrieks_of_unseeing");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), SHRIEKS_UNSEEING_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SHRIEKS_UNSEEING_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR, SHRIEKS_UNSEEING_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), SHRIEKS_UNSEEING_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), SHRIEKS_UNSEEING_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), SHRIEKS_UNSEEING_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), SHRIEKS_UNSEEING_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Shrieks of Unseeing").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+15 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-3 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+8 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+10% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+7.5% Armor").withStyle(style -> style.withColor(0x0F52BA)));
            tooltip.add(Component.literal("-15 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Otherworlds").withStyle(ChatFormatting.DARK_AQUA));
        }
    }

// --- Mythic ---

    private static class ShrieksOfUnseeingMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public ShrieksOfUnseeingMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 21.0, SHRIEKS_UNSEEING_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, SHRIEKS_UNSEEING_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 10.0, SHRIEKS_UNSEEING_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), 0.15, SHRIEKS_UNSEEING_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), -3.0, SHRIEKS_UNSEEING_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), -15.0, SHRIEKS_UNSEEING_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 12.5, SHRIEKS_UNSEEING_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "shrieks_of_unseeing");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), SHRIEKS_UNSEEING_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SHRIEKS_UNSEEING_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR, SHRIEKS_UNSEEING_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), SHRIEKS_UNSEEING_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), SHRIEKS_UNSEEING_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), SHRIEKS_UNSEEING_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), SHRIEKS_UNSEEING_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Shrieks of Unseeing").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+21 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-3 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+10 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+12.5% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+15% Armor").withStyle(style -> style.withColor(0x0F52BA)));
            tooltip.add(Component.literal("-15 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Otherworlds").withStyle(ChatFormatting.DARK_AQUA));
        }
    }
}
