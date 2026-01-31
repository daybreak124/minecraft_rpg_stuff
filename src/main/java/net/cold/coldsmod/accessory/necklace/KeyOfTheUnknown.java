package net.cold.coldsmod.accessory.necklace;

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

public class KeyOfTheUnknown {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static final UUID KEY_UNKNOWN_UUID = UUID.fromString("d4e3f2a1-b0c9-4a8b-7d6e-5f4a3b2c1d0e");

// --- Registry ---

    public static final RegistryObject<Item> KEY_OF_THE_UNKNOWN_RARE = ITEMS.register(
            "key_of_the_unknown_rare",
            () -> new KeyOfTheUnknownRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> KEY_OF_THE_UNKNOWN_EPIC = ITEMS.register(
            "key_of_the_unknown_epic",
            () -> new KeyOfTheUnknownEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> KEY_OF_THE_UNKNOWN_LEGENDARY = ITEMS.register(
            "key_of_the_unknown_legendary",
            () -> new KeyOfTheUnknownLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> KEY_OF_THE_UNKNOWN_MYTHIC = ITEMS.register(
            "key_of_the_unknown_mythic",
            () -> new KeyOfTheUnknownMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class KeyOfTheUnknownRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public KeyOfTheUnknownRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, KEY_UNKNOWN_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 5.0, KEY_UNKNOWN_UUID);
                AttributeApplier.applyModifier(player, Attributes.KNOCKBACK_RESISTANCE, 0.05, KEY_UNKNOWN_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 2.0, KEY_UNKNOWN_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.REJUVENATION.get(), 6.0, KEY_UNKNOWN_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "key_of_the_unknown");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), KEY_UNKNOWN_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), KEY_UNKNOWN_UUID);
                AttributeApplier.removeModifier(player, Attributes.KNOCKBACK_RESISTANCE, KEY_UNKNOWN_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR, KEY_UNKNOWN_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.REJUVENATION.get(), KEY_UNKNOWN_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Key of the Unknown").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+5 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+5% Knockback Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+6 Rejuvenation").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Otherworlds").withStyle(ChatFormatting.DARK_AQUA));
        }
    }

// --- Epic ---

    private static class KeyOfTheUnknownEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public KeyOfTheUnknownEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, KEY_UNKNOWN_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 8.0, KEY_UNKNOWN_UUID);
                AttributeApplier.applyModifier(player, Attributes.KNOCKBACK_RESISTANCE, 0.10, KEY_UNKNOWN_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 4.0, KEY_UNKNOWN_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.REJUVENATION.get(), 9.0, KEY_UNKNOWN_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "key_of_the_unknown");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), KEY_UNKNOWN_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), KEY_UNKNOWN_UUID);
                AttributeApplier.removeModifier(player, Attributes.KNOCKBACK_RESISTANCE, KEY_UNKNOWN_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR, KEY_UNKNOWN_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.REJUVENATION.get(), KEY_UNKNOWN_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Key of the Unknown").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+8 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+10% Knockback Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+9 Rejuvenation").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Otherworlds").withStyle(ChatFormatting.DARK_AQUA));
        }
    }

// --- Legendary ---

    private static class KeyOfTheUnknownLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public KeyOfTheUnknownLegendary(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, KEY_UNKNOWN_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 8.0, KEY_UNKNOWN_UUID);
                AttributeApplier.applyModifier(player, Attributes.KNOCKBACK_RESISTANCE, 0.15, KEY_UNKNOWN_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 5.0, KEY_UNKNOWN_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 6.0, KEY_UNKNOWN_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), 0.1, KEY_UNKNOWN_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 5.0, KEY_UNKNOWN_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.REJUVENATION.get(), 12, KEY_UNKNOWN_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "key_of_the_unknown");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), KEY_UNKNOWN_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), KEY_UNKNOWN_UUID);
                AttributeApplier.removeModifier(player, Attributes.KNOCKBACK_RESISTANCE, KEY_UNKNOWN_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR, KEY_UNKNOWN_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), KEY_UNKNOWN_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), KEY_UNKNOWN_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), KEY_UNKNOWN_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.REJUVENATION.get(), KEY_UNKNOWN_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Key of the Unknown").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+8 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+6 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+15% Knockback Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+5% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+10% Armor").withStyle(style -> style.withColor(0x0F52BA)));
            tooltip.add(Component.literal("+12 Rejuvenation").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Otherworlds").withStyle(ChatFormatting.DARK_AQUA));
        }
    }

// --- Mythic ---

    private static class KeyOfTheUnknownMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public KeyOfTheUnknownMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, KEY_UNKNOWN_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 11.0, KEY_UNKNOWN_UUID);
                AttributeApplier.applyModifier(player, Attributes.KNOCKBACK_RESISTANCE, 0.15, KEY_UNKNOWN_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 7.0, KEY_UNKNOWN_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), 0.18, KEY_UNKNOWN_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 8.0, KEY_UNKNOWN_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 12.0, KEY_UNKNOWN_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.REJUVENATION.get(), 15, KEY_UNKNOWN_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "key_of_the_unknown");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), KEY_UNKNOWN_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), KEY_UNKNOWN_UUID);
                AttributeApplier.removeModifier(player, Attributes.KNOCKBACK_RESISTANCE, KEY_UNKNOWN_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR, KEY_UNKNOWN_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), KEY_UNKNOWN_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), KEY_UNKNOWN_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), KEY_UNKNOWN_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.REJUVENATION.get(), KEY_UNKNOWN_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Key of the Unknown").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+11 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+8 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+7 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+15% Knockback Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+12% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+18% Armor").withStyle(style -> style.withColor(0x0F52BA)));
            tooltip.add(Component.literal("+15 Rejuvenation").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Otherworlds").withStyle(ChatFormatting.DARK_AQUA));
        }
    }
}
