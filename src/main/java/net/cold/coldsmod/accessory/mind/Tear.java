package net.cold.coldsmod.accessory.mind;

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

public class Tear {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


// --- Registry ---

    public static final RegistryObject<Item> TEAR_OF_THE_FORGOTTEN_RARE = ITEMS.register(
            "tear_of_the_forgotten_rare",
            () -> new TearOfTheForgottenRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> TEAR_OF_THE_FORGOTTEN_EPIC = ITEMS.register(
            "tear_of_the_forgotten_epic",
            () -> new TearOfTheForgottenEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> TEAR_OF_THE_FORGOTTEN_LEGENDARY = ITEMS.register(
            "tear_of_the_forgotten_legendary",
            () -> new TearOfTheForgottenLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> TEAR_OF_THE_FORGOTTEN_MYTHIC = ITEMS.register(
            "tear_of_the_forgotten_mythic",
            () -> new TearOfTheForgottenMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class TearOfTheForgottenRare extends Item {
        public TearOfTheForgottenRare(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Tear of the Forgotten").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+1 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+2.4 Accuracy").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Miner's Fortune").withStyle(ChatFormatting.GRAY));
        }
    }

// --- Epic ---

    private static class TearOfTheForgottenEpic extends Item {
        public TearOfTheForgottenEpic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Tear of the Forgotten").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+2 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+4.8  Accuracy").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Miner's Fortune").withStyle(ChatFormatting.GRAY));
        }
    }

// --- Legendary ---

    private static class TearOfTheForgottenLegendary extends Item {
        public TearOfTheForgottenLegendary(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Tear of the Forgotten").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+3 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+6 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+7.2 Accuracy").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+1% Toughness").withStyle(style -> style.withColor(0x0F52BA)));
            tooltip.add(Component.literal("+4% Accuracy").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Miner's Fortune").withStyle(ChatFormatting.GRAY));
        }
    }

// --- Mythic ---

    private static class TearOfTheForgottenMythic extends Item {
        public TearOfTheForgottenMythic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Tear of the Forgotten").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+4 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+8 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+9.6 Accuracy").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+2% Toughness").withStyle(style -> style.withColor(0x0F52BA)));
            tooltip.add(Component.literal("+8% Accuracy").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Miner's Fortune").withStyle(ChatFormatting.GRAY));
        }
    }

}
