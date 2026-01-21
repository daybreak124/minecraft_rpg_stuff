package net.cold.coldsmod.accessory.bracers;

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

public class ThieveryWraps {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


    private static final UUID THIEVERY_UUID = UUID.fromString("f1e2d3c4-b5a6-4987-8c7d-6e5f4a3b2c1d");

// --- Registry ---

    public static final RegistryObject<Item> WRAPS_OF_THIEVERY_RARE = ITEMS.register(
            "wraps_of_thievery_rare",
            () -> new WrapsOfThieveryRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> WRAPS_OF_THIEVERY_EPIC = ITEMS.register(
            "wraps_of_thievery_epic",
            () -> new WrapsOfThieveryEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> WRAPS_OF_THIEVERY_LEGENDARY = ITEMS.register(
            "wraps_of_thievery_legendary",
            () -> new WrapsOfThieveryLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> WRAPS_OF_THIEVERY_MYTHIC = ITEMS.register(
            "wraps_of_thievery_mythic",
            () -> new WrapsOfThieveryMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class WrapsOfThieveryRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public WrapsOfThieveryRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), -0.06, THIEVERY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 11.0, THIEVERY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, THIEVERY_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "wraps_of_thievery");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), THIEVERY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), THIEVERY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), THIEVERY_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Wraps of Thievery").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+11 Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("-6% Haste").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Stolen Wealth").withStyle(ChatFormatting.DARK_GREEN));
        }
    }

// --- Epic ---

    private static class WrapsOfThieveryEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public WrapsOfThieveryEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), -0.1, THIEVERY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 16.0, THIEVERY_UUID);
                AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 1.0, THIEVERY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, THIEVERY_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "wraps_of_thievery");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), THIEVERY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), THIEVERY_UUID);
                AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, THIEVERY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), THIEVERY_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Wraps of Thievery").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+16 Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("-10% Haste").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Stolen Wealth").withStyle(ChatFormatting.DARK_GREEN));
        }
    }

// --- Legendary ---

    private static class WrapsOfThieveryLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public WrapsOfThieveryLegendary(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), -0.15, THIEVERY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 22.0, THIEVERY_UUID);
                AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 2.0, THIEVERY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), 0.09, THIEVERY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, THIEVERY_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "wraps_of_thievery");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), THIEVERY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), THIEVERY_UUID);
                AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, THIEVERY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), THIEVERY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), THIEVERY_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Wraps of Thievery").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+22 Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+9% Melee Potency").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal("-15% Haste").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Stolen Wealth").withStyle(ChatFormatting.DARK_GREEN));
        }
    }

// --- Mythic ---

    private static class WrapsOfThieveryMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public WrapsOfThieveryMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), -0.15, THIEVERY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 27.5, THIEVERY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), 0.15, THIEVERY_UUID);
                AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 2.5, THIEVERY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, THIEVERY_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "wraps_of_thievery");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), THIEVERY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), THIEVERY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), THIEVERY_UUID);
                AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, THIEVERY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), THIEVERY_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Wraps of Thievery").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2.5 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+27.5 Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+15% Melee Potency").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal("-15% Haste").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Stolen Wealth").withStyle(ChatFormatting.DARK_GREEN));
        }
    }


}
