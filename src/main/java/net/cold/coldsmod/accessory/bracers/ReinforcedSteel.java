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
            tooltip.add(Component.literal("+3 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+0.25 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+4 Melee Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+5 Rejuvenation").withStyle(style -> style.withColor(0x5BB450)));
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
            tooltip.add(Component.literal("+5 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1.5 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+0.5 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+7.5 Melee Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+7 Rejuvenation").withStyle(style -> style.withColor(0x5BB450)));
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
            tooltip.add(Component.literal("+8 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+0.75 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+12 Melee Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+8% Accuracy").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal("+10 Rejuvenation").withStyle(style -> style.withColor(0x5BB450)));
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
            tooltip.add(Component.literal("+10 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2.75 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+1 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+16 Melee Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+15% Accuracy").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal("+12 Rejuvenation").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Miner's Fortune").withStyle(ChatFormatting.GRAY));
        }
    }
}
