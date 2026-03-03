package net.cold.coldsmod.accessory.ring;

import net.cold.coldsmod.ColdsMod;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.List;

public class ColdCoil {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


// --- Registry ---

    public static final RegistryObject<Item> COLDYS_COLD_COIL_OF_COLD_RARE = ITEMS.register(
            "coldys_cold_coil_of_cold_rare",
            () -> new ColdCoilRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> COLDYS_COLD_COIL_OF_COLD_EPIC = ITEMS.register(
            "coldys_cold_coil_of_cold_epic",
            () -> new ColdCoilEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> COLDYS_COLD_COIL_OF_COLD_LEGENDARY = ITEMS.register(
            "coldys_cold_coil_of_cold_legendary",
            () -> new ColdCoilLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> COLDYS_COLD_COIL_OF_COLD_MYTHIC = ITEMS.register(
            "coldys_cold_coil_of_cold_mythic",
            () -> new ColdCoilMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class ColdCoilRare extends Item {
        public ColdCoilRare(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Coldy's Cold Coil of Cold").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+4 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5 Melee Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
            tooltip.add(Component.literal("+5 Nock Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
            tooltip.add(Component.literal("+9% Speed").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xD6C97A))));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Whiteout").withStyle(ChatFormatting.WHITE));
        }
    }

// --- Epic ---

    private static class ColdCoilEpic extends Item {
        public ColdCoilEpic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Coldy's Colder Coil of Cold").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+6 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+8 Melee Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
            tooltip.add(Component.literal("+7.5 Nock Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
            tooltip.add(Component.literal("+12% Speed").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xD6C97A))));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Whiteout").withStyle(ChatFormatting.WHITE));
        }
    }

// --- Legendary ---

    private static class ColdCoilLegendary extends Item {
        public ColdCoilLegendary(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Coldy's Colder Coil of Cold").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+9 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+15 Melee Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
            tooltip.add(Component.literal("+13 Nock Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
            tooltip.add(Component.literal("+8% Nock Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));
            tooltip.add(Component.literal("+15% Speed").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xD6C97A))));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Whiteout").withStyle(ChatFormatting.WHITE));
        }
    }

// --- Mythic ---

    private static class ColdCoilMythic extends Item {
        public ColdCoilMythic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Coldy's Coldest Coil of Cold").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+13 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+21 Melee Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
            tooltip.add(Component.literal("+16 Nock Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
            tooltip.add(Component.literal("+8% Melee Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));
            tooltip.add(Component.literal("+8% Nock Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));

            tooltip.add(Component.literal("+15% Speed").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xD6C97A))));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Whiteout").withStyle(ChatFormatting.WHITE));
        }
    }
}