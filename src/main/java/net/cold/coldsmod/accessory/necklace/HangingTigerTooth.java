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

public class HangingTigerTooth {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


// --- Registry ---

    public static final RegistryObject<Item> HANGING_TIGER_TOOTH_RARE = ITEMS.register(
            "hanging_tiger_tooth_rare",
            () -> new HangingTigerToothRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> HANGING_TIGER_TOOTH_EPIC = ITEMS.register(
            "hanging_tiger_tooth_epic",
            () -> new HangingTigerToothEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> HANGING_TIGER_TOOTH_LEGENDARY = ITEMS.register(
            "hanging_tiger_tooth_legendary",
            () -> new HangingTigerToothLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> HANGING_TIGER_TOOTH_MYTHIC = ITEMS.register(
            "hanging_tiger_tooth_mythic",
            () -> new HangingTigerToothMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class HangingTigerToothRare extends Item {
        public HangingTigerToothRare(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Hanging Tiger Tooth").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+8 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2% Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Nature's Blessing").withStyle(ChatFormatting.GREEN));
        }
    }

// --- Epic ---

    private static class HangingTigerToothEpic extends Item {
        public HangingTigerToothEpic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Hanging Tiger Tooth").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+16 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4% Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Nature's Blessing").withStyle(ChatFormatting.GREEN));
        }
    }

// --- Legendary ---

    private static class HangingTigerToothLegendary extends Item {
        public HangingTigerToothLegendary(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Hanging Tiger Tooth").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+24 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+6% Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Nature's Blessing").withStyle(ChatFormatting.GREEN));
        }
    }

// --- Mythic ---

    private static class HangingTigerToothMythic extends Item {
        public HangingTigerToothMythic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Hanging Tiger Tooth").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+32 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+8% Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Nature's Blessing").withStyle(ChatFormatting.GREEN));
        }
    }
}
