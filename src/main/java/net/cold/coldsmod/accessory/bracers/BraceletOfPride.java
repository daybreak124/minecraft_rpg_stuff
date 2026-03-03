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

public class BraceletOfPride {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


// --- Registry ---

    public static final RegistryObject<Item> BRACELET_OF_PRIDE_RARE = ITEMS.register(
            "bracelet_of_pride_rare",
            () -> new BraceletOfPrideRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> BRACELET_OF_PRIDE_EPIC = ITEMS.register(
            "bracelet_of_pride_epic",
            () -> new BraceletOfPrideEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> BRACELET_OF_PRIDE_LEGENDARY = ITEMS.register(
            "bracelet_of_pride_legendary",
            () -> new BraceletOfPrideLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> BRACELET_OF_PRIDE_MYTHIC = ITEMS.register(
            "bracelet_of_pride_mythic",
            () -> new BraceletOfPrideMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class BraceletOfPrideRare extends Item {
        public BraceletOfPrideRare(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Bracelet of Pride").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+4 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Epic ---

    private static class BraceletOfPrideEpic extends Item {
        public BraceletOfPrideEpic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Bracelet of Pride").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+8 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Legendary ---

    private static class BraceletOfPrideLegendary extends Item {
        public BraceletOfPrideLegendary(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Bracelet of Pride").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+8 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Mythic ---

    private static class BraceletOfPrideMythic extends Item {
        public BraceletOfPrideMythic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Bracelet of Pride").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+10 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+6 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+7 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+7 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }
}
