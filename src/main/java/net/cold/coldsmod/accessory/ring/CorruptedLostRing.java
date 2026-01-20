package net.cold.coldsmod.accessory.ring;

import net.cold.coldsmod.ColdsMod;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
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

public class CorruptedLostRing {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static final UUID CORRUPTED_LOST_UUID = UUID.fromString("c1b2a3d4-e5f6-4789-9d8c-7b6a5f4e3d2c");

// --- Registry ---

    public static final RegistryObject<Item> CORRUPTED_RING_OF_THE_LOST_RARE = ITEMS.register(
            "corrupted_ring_of_the_lost_rare",
            () -> new CorruptedRingRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> CORRUPTED_RING_OF_THE_LOST_EPIC = ITEMS.register(
            "corrupted_ring_of_the_lost_epic",
            () -> new CorruptedRingEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> CORRUPTED_RING_OF_THE_LOST_LEGENDARY = ITEMS.register(
            "corrupted_ring_of_the_lost_legendary",
            () -> new CorruptedRingLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> CORRUPTED_RING_OF_THE_LOST_MYTHIC = ITEMS.register(
            "corrupted_ring_of_the_lost_mythic",
            () -> new CorruptedRingMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class CorruptedRingRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public CorruptedRingRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, CORRUPTED_LOST_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 4.0, CORRUPTED_LOST_UUID);
                AttributeApplier.applyModifier(player, Attributes.KNOCKBACK_RESISTANCE, 0.05, CORRUPTED_LOST_UUID); // 5 scaled to 0.1 per 1
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 2.25, CORRUPTED_LOST_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "corrupted_ring_of_the_lost");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), CORRUPTED_LOST_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), CORRUPTED_LOST_UUID);
                AttributeApplier.removeModifier(player, Attributes.KNOCKBACK_RESISTANCE, CORRUPTED_LOST_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR, CORRUPTED_LOST_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Corrupted Ring of the Lost").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+4 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+2.25 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+5% Knockback Resist").withStyle(ChatFormatting.BLUE));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Epic ---

    private static class CorruptedRingEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public CorruptedRingEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, CORRUPTED_LOST_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 5.0, CORRUPTED_LOST_UUID);
                AttributeApplier.applyModifier(player, Attributes.KNOCKBACK_RESISTANCE, 0.1, CORRUPTED_LOST_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 3.75, CORRUPTED_LOST_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "corrupted_ring_of_the_lost");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), CORRUPTED_LOST_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), CORRUPTED_LOST_UUID);
                AttributeApplier.removeModifier(player, Attributes.KNOCKBACK_RESISTANCE, CORRUPTED_LOST_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR, CORRUPTED_LOST_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Corrupted Ring of the Lost").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+5 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+3.75 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+10% Knockback Resist").withStyle(ChatFormatting.BLUE));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Legendary ---

    private static class CorruptedRingLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public CorruptedRingLegendary(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, CORRUPTED_LOST_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 6.0, CORRUPTED_LOST_UUID);
                AttributeApplier.applyModifier(player, Attributes.KNOCKBACK_RESISTANCE, 0.15, CORRUPTED_LOST_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 5.5, CORRUPTED_LOST_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 3.0, CORRUPTED_LOST_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), 0.04, CORRUPTED_LOST_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "corrupted_ring_of_the_lost");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), CORRUPTED_LOST_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), CORRUPTED_LOST_UUID);
                AttributeApplier.removeModifier(player, Attributes.KNOCKBACK_RESISTANCE, CORRUPTED_LOST_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR, CORRUPTED_LOST_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, CORRUPTED_LOST_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), CORRUPTED_LOST_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Corrupted Ring of the Lost").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+6 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+5.5 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+3 Armor Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+15% Knockback Resist").withStyle(ChatFormatting.BLUE));

            tooltip.add(Component.literal("+4% Armor").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0F52BA))));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Mythic ---

    private static class CorruptedRingMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public CorruptedRingMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, CORRUPTED_LOST_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 7.0, CORRUPTED_LOST_UUID);
                AttributeApplier.applyModifier(player, Attributes.KNOCKBACK_RESISTANCE, 0.2, CORRUPTED_LOST_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 7.0, CORRUPTED_LOST_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 5.0, CORRUPTED_LOST_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), 0.08, CORRUPTED_LOST_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "corrupted_ring_of_the_lost");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), CORRUPTED_LOST_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), CORRUPTED_LOST_UUID);
                AttributeApplier.removeModifier(player, Attributes.KNOCKBACK_RESISTANCE, CORRUPTED_LOST_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR, CORRUPTED_LOST_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, CORRUPTED_LOST_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), CORRUPTED_LOST_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Corrupted Ring of the Lost").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+7 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+7 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+5 Armor Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+20% Knockback Resist").withStyle(ChatFormatting.BLUE));

            tooltip.add(Component.literal("+8% Armor").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0F52BA))));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }
}