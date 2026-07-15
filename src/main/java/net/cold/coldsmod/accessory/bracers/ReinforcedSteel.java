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

public class ReinforcedSteel {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


// --- Registry ---

    public static final RegistryObject<Item> REINFORCED_STEEL_BRACERS_RARE = ITEMS.register(
            "reinforced_steel_bracers_rare",
            () -> new ReinforcedSteelBracersRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> REINFORCED_STEEL_BRACERS_EPIC = ITEMS.register(
            "reinforced_steel_bracers_epic",
            () -> new ReinforcedSteelBracersEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> REINFORCED_STEEL_BRACERS_LEGENDARY = ITEMS.register(
            "reinforced_steel_bracers_legendary",
            () -> new ReinforcedSteelBracersLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> REINFORCED_STEEL_BRACERS_MYTHIC = ITEMS.register(
            "reinforced_steel_bracers_mythic",
            () -> new ReinforcedSteelBracersMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class ReinforcedSteelBracersRare extends Item {
        public ReinforcedSteelBracersRare(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Reinforced Steel Bracers").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+3 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4.2 Melee Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Miner's Fortune").withStyle(ChatFormatting.GRAY));
        }
    }

// --- Epic ---

    private static class ReinforcedSteelBracersEpic extends Item {
        public ReinforcedSteelBracersEpic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Reinforced Steel Bracers").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+6 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+6.5 Melee Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+2 Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Miner's Fortune").withStyle(ChatFormatting.GRAY));
        }
    }

// --- Legendary ---

    private static class ReinforcedSteelBracersLegendary extends Item {
        public ReinforcedSteelBracersLegendary(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Reinforced Steel Bracers").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+9 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+9.5 Melee Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+3 Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+7% Melee Potency").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Miner's Fortune").withStyle(ChatFormatting.GRAY));
        }
    }

// --- Mythic ---

    private static class ReinforcedSteelBracersMythic extends Item {
        public ReinforcedSteelBracersMythic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Reinforced Steel Bracers").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+12 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+12.5 Melee Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+4 Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+14% Melee Potency").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Miner's Fortune").withStyle(ChatFormatting.GRAY));
        }
    }
}
