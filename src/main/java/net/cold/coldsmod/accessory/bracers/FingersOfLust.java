package net.cold.coldsmod.accessory.bracers;

import net.cold.coldsmod.ColdsMod;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
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

public class FingersOfLust {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


    private static final UUID FINGERS_UUID = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479");

// --- Registry ---

    public static final RegistryObject<Item> FINGERS_OF_LUST_RARE = ITEMS.register(
            "fingers_of_lust_rare",
            () -> new FingersOfLustRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> FINGERS_OF_LUST_EPIC = ITEMS.register(
            "fingers_of_lust_epic",
            () -> new FingersOfLustEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> FINGERS_OF_LUST_LEGENDARY = ITEMS.register(
            "fingers_of_lust_legendary",
            () -> new FingersOfLustLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> FINGERS_OF_LUST_MYTHIC = ITEMS.register(
            "fingers_of_lust_mythic",
            () -> new FingersOfLustMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class FingersOfLustRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public FingersOfLustRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 5.0, FINGERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 3.0, FINGERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), -6.0, FINGERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 2.0, FINGERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), 2.0, FINGERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 4.0, FINGERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, FINGERS_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "fingers_of_lost");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), FINGERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), FINGERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), FINGERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), FINGERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), FINGERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), FINGERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), FINGERS_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Fingers of Lust").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+5 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-6 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Wisdom").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Melee Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+2 Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+2 Restoration").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Epic ---

    private static class FingersOfLustEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public FingersOfLustEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 6.0, FINGERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 4.0, FINGERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), -6.0, FINGERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 3.75, FINGERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), 3.75, FINGERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 6.75, FINGERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, FINGERS_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "fingers_of_lust");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), FINGERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), FINGERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), FINGERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), FINGERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), FINGERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), FINGERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), FINGERS_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Fingers of Lust").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+6 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-6 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Wisdom").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+6.75 Melee Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+3.75 Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+3.75 Restoration").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Legendary ---

    private static class FingersOfLustLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public FingersOfLustLegendary(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 7.0, FINGERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 5.0, FINGERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), -6.0, FINGERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 4.5, FINGERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), 7, FINGERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 12.0, FINGERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), 0.09, FINGERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, FINGERS_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "fingers_of_lust");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), FINGERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), FINGERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), FINGERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), FINGERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), FINGERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), FINGERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), FINGERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), FINGERS_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Fingers of Lust").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+7 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-6 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5 Wisdom").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+12 Melee Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+4.5 Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+9% Haste").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal("+7 Restoration").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Mythic ---

    private static class FingersOfLustMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public FingersOfLustMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 8.0, FINGERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 6.0, FINGERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), -6.0, FINGERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 6.0, FINGERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), 12, FINGERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 18.0, FINGERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), 0.18, FINGERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, FINGERS_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "fingers_of_lust");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), FINGERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), FINGERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), FINGERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), FINGERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), FINGERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), FINGERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), FINGERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), FINGERS_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Fingers of Lust").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+8 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-6 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+6 Wisdom").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+18 Melee Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+6 Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+18% Haste").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal("+12 Restoration").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }


}
