package net.cold.coldsmod.accessory.necklace;

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

public class KeyOfTheUnknown {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


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

    private static class KeyOfTheUnknownRare extends Item {
        public KeyOfTheUnknownRare(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Key of the Unknown").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+8 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+10% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Otherworlds").withStyle(ChatFormatting.DARK_AQUA));
        }
    }

// --- Epic ---

    private static class KeyOfTheUnknownEpic extends Item {
        public KeyOfTheUnknownEpic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Key of the Unknown").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+16 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+20% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Otherworlds").withStyle(ChatFormatting.DARK_AQUA));
        }
    }

// --- Legendary ---

    private static class KeyOfTheUnknownLegendary extends Item {
        public KeyOfTheUnknownLegendary(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Key of the Unknown").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+24 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+30% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Otherworlds").withStyle(ChatFormatting.DARK_AQUA));
        }
    }

// --- Mythic ---

    private static class KeyOfTheUnknownMythic extends Item {
        public KeyOfTheUnknownMythic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Key of the Unknown").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+32 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+40% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Otherworlds").withStyle(ChatFormatting.DARK_AQUA));
        }
    }
}
