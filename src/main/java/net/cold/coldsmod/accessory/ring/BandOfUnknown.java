package net.cold.coldsmod.accessory.ring;

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

public class BandOfUnknown {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


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

    private static class BandOfTheUnknownRare extends Item {
        public BandOfTheUnknownRare(Properties properties) { super(properties); }

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

    private static class BandOfTheUnknownEpic extends Item {
        public BandOfTheUnknownEpic(Properties properties) { super(properties); }

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

    private static class BandOfTheUnknownLegendary extends Item {
        public BandOfTheUnknownLegendary(Properties properties) { super(properties); }

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

    private static class BandOfTheUnknownMythic extends Item {
        public BandOfTheUnknownMythic(Properties properties) { super(properties); }

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