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

public class FingersOfLust {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }



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

    private static class FingersOfLustRare extends Item {
        public FingersOfLustRare(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Fingers of Lust").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+1 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2.2 Accuracy").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+3.45 Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Epic ---

    private static class FingersOfLustEpic extends Item {
        public FingersOfLustEpic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Fingers of Lust").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+2 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3.3 Accuracy").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+7.5 Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Legendary ---

    private static class FingersOfLustLegendary extends Item {
        public FingersOfLustLegendary(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Fingers of Lust").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+3 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+6 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5.5 Accuracy").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+10.5 Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+5% Haste").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Mythic ---

    private static class FingersOfLustMythic extends Item {
        public FingersOfLustMythic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Fingers of Lust").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+4 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+8 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+7.7 Accuracy").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+13.75 Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+10% Haste").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }


}
