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

public class BottledTsunami {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


// --- Registry ---

    public static final RegistryObject<Item> BOTTLED_TSUNAMI_SEA_RARE = ITEMS.register(
            "bottled_tsunami_sea_rare",
            () -> new BottledTsunamiSeaRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> BOTTLED_TSUNAMI_SEA_EPIC = ITEMS.register(
            "bottled_tsunami_sea_epic",
            () -> new BottledTsunamiSeaEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> BOTTLED_TSUNAMI_SEA_LEGENDARY = ITEMS.register(
            "bottled_tsunami_sea_legendary",
            () -> new BottledTsunamiSeaLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> BOTTLED_TSUNAMI_SEA_MYTHIC = ITEMS.register(
            "bottled_tsunami_sea_mythic",
            () -> new BottledTsunamiSeaMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class BottledTsunamiSeaRare extends Item {
        public BottledTsunamiSeaRare(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Bottled Tsunami Sea").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+8 Wisdom").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+1% Restoration").withStyle(style -> style.withColor(0x3B8132)));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Tsunami Sea").withStyle(ChatFormatting.AQUA));
        }
    }

// --- Epic ---

    private static class BottledTsunamiSeaEpic extends Item {
        public BottledTsunamiSeaEpic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Bottled Tsunami Sea").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+16 Wisdom").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+2% Restoration").withStyle(style -> style.withColor(0x3B8132)));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Tsunami Sea").withStyle(ChatFormatting.AQUA));
        }
    }

// --- Legendary ---

    private static class BottledTsunamiSeaLegendary extends Item {
        public BottledTsunamiSeaLegendary(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Bottled Tsunami Sea").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+24 Wisdom").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+3% Restoration").withStyle(style -> style.withColor(0x3B8132)));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Tsunami Sea").withStyle(ChatFormatting.AQUA));
        }
    }

// --- Mythic ---

    private static class BottledTsunamiSeaMythic extends Item {
        public BottledTsunamiSeaMythic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Bottled Tsunami Sea").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+32 Wisdom").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+4% Restoration").withStyle(style -> style.withColor(0x3B8132)));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Tsunami Sea").withStyle(ChatFormatting.AQUA));
        }
    }

}
