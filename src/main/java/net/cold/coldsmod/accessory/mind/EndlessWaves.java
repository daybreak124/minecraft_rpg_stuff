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

public class EndlessWaves {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


// --- Registry ---

    public static final RegistryObject<Item> ENDLESS_WAVES_RARE = ITEMS.register(
            "endless_waves_rare",
            () -> new EndlessWavesRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> ENDLESS_WAVES_EPIC = ITEMS.register(
            "endless_waves_epic",
            () -> new EndlessWavesEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> ENDLESS_WAVES_LEGENDARY = ITEMS.register(
            "endless_waves_legendary",
            () -> new EndlessWavesLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> ENDLESS_WAVES_MYTHIC = ITEMS.register(
            "endless_waves_mythic",
            () -> new EndlessWavesMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class EndlessWavesRare extends Item {
        public EndlessWavesRare(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Endless Waves").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+2 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Wisdom").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+7 Melee Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+5 Restoration").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal("+7.5% Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Tsunami Sea").withStyle(ChatFormatting.AQUA));
        }
    }

// --- Epic ---

    private static class EndlessWavesEpic extends Item {
        public EndlessWavesEpic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Endless Waves").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+4 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5 Wisdom").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+9 Melee Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+7.5 Restoration").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal("+10% Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Tsunami Sea").withStyle(ChatFormatting.AQUA));
        }
    }

// --- Legendary ---

    private static class EndlessWavesLegendary extends Item {
        public EndlessWavesLegendary(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Endless Waves").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+5 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+9 Wisdom").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+14 Melee Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+10% Melee Haste").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal("+11 Restoration").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal("+5% Restoration").withStyle(style -> style.withColor(0x3B8132)));
            tooltip.add(Component.literal("+10% Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Tsunami Sea").withStyle(ChatFormatting.AQUA));
        }
    }

// --- Mythic ---

    private static class EndlessWavesMythic extends Item {
        public EndlessWavesMythic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Endless Waves").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+8 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+8 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+12 Wisdom").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+18 Melee Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+17.5% Melee Haste").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal("+15 Restoration").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal("+12.5% Restoration").withStyle(style -> style.withColor(0x3B8132)));
            tooltip.add(Component.literal("+12.5% Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Tsunami Sea").withStyle(ChatFormatting.AQUA));
        }
    }
}
