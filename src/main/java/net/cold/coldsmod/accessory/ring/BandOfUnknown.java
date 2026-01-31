package net.cold.coldsmod.accessory.ring;

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

public class BandOfUnknown {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static final UUID UNKNOWN_BAND_UUID = UUID.fromString("f1e2d3c4-b5a6-4789-8d7c-6e5b4a3f2d1c");

// --- Registry ---

    public static final RegistryObject<Item> BAND_OF_THE_UNKNOWN_RARE = ITEMS.register(
            "band_of_the_unknown_rare",
            () -> new BandOfTheUnknownRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> BAND_OF_THE_UNKNOWN_EPIC = ITEMS.register(
            "band_of_the_unknown_epic",
            () -> new BandOfTheUnknownEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> BAND_OF_THE_UNKNOWN_LEGENDARY = ITEMS.register(
            "band_of_the_unknown_legendary",
            () -> new BandOfTheUnknownLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> BAND_OF_THE_UNKNOWN_MYTHIC = ITEMS.register(
            "band_of_the_unknown_mythic",
            () -> new BandOfTheUnknownMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class BandOfTheUnknownRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public BandOfTheUnknownRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, UNKNOWN_BAND_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 5.0, UNKNOWN_BAND_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 5.0, UNKNOWN_BAND_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 3.0, UNKNOWN_BAND_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 3.0, UNKNOWN_BAND_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "band_of_the_unknown");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), UNKNOWN_BAND_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), UNKNOWN_BAND_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), UNKNOWN_BAND_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), UNKNOWN_BAND_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), UNKNOWN_BAND_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Band of the Unknown").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+5 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5 Wisdom").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Otherworlds").withStyle(ChatFormatting.DARK_AQUA));
        }
    }

// --- Epic ---

    private static class BandOfTheUnknownEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public BandOfTheUnknownEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, UNKNOWN_BAND_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 8.0, UNKNOWN_BAND_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 8.0, UNKNOWN_BAND_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 4.0, UNKNOWN_BAND_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 4.0, UNKNOWN_BAND_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "band_of_the_unknown");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), UNKNOWN_BAND_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), UNKNOWN_BAND_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), UNKNOWN_BAND_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), UNKNOWN_BAND_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), UNKNOWN_BAND_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Band of the Unknown").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+8 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+8 Wisdom").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Otherworlds").withStyle(ChatFormatting.DARK_AQUA));

        }
    }

// --- Legendary ---

    private static class BandOfTheUnknownLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public BandOfTheUnknownLegendary(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, UNKNOWN_BAND_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 10.0, UNKNOWN_BAND_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 10.0, UNKNOWN_BAND_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 5.0, UNKNOWN_BAND_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 5.0, UNKNOWN_BAND_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "band_of_the_unknown");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), UNKNOWN_BAND_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), UNKNOWN_BAND_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), UNKNOWN_BAND_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), UNKNOWN_BAND_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), UNKNOWN_BAND_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Band of the Unknown").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+10 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+10 Wisdom").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Otherworlds").withStyle(ChatFormatting.DARK_AQUA));
        }
    }

// --- Mythic ---

    private static class BandOfTheUnknownMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public BandOfTheUnknownMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, UNKNOWN_BAND_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 15.0, UNKNOWN_BAND_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 15.0, UNKNOWN_BAND_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 7.0, UNKNOWN_BAND_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 7.0, UNKNOWN_BAND_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "band_of_the_unknown");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), UNKNOWN_BAND_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), UNKNOWN_BAND_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), UNKNOWN_BAND_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), UNKNOWN_BAND_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), UNKNOWN_BAND_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Band of the Unknown").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+15 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+7 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+7 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+15 Wisdom").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Otherworlds").withStyle(ChatFormatting.DARK_AQUA));

        }
    }
}