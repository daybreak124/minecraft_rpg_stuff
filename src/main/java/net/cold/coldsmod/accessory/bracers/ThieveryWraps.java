package net.cold.coldsmod.accessory.bracers;

import net.cold.coldsmod.ColdsMod;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
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

public class ThieveryWraps {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }



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

    private static class WrapsOfThieveryRare extends Item {
        public WrapsOfThieveryRare(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Wraps of Thievery").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+1 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5 Melee Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+2% Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Stolen Wealth").withStyle(ChatFormatting.DARK_GREEN));
        }
    }

// --- Epic ---

    private static class WrapsOfThieveryEpic extends Item {
        public WrapsOfThieveryEpic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Wraps of Thievery").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+3 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+10 Melee Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+4% Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Stolen Wealth").withStyle(ChatFormatting.DARK_GREEN));
        }
    }

// --- Legendary ---

    private static class WrapsOfThieveryLegendary extends Item {
        public WrapsOfThieveryLegendary(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Wraps of Thievery").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+4 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+15 Melee Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+7% Melee Precision").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal("+6% Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Stolen Wealth").withStyle(ChatFormatting.DARK_GREEN));
        }
    }

// --- Mythic ---

    private static class WrapsOfThieveryMythic extends Item {
        public WrapsOfThieveryMythic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Wraps of Thievery").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+6 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+6 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+15 Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+14% Melee Precision").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal("+8% Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Stolen Wealth").withStyle(ChatFormatting.DARK_GREEN));
        }
    }


}
