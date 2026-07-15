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

public class DragonClaw {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

// --- Registry ---

    public static final RegistryObject<Item> DRAGON_CLAW_GLOVES_RARE = ITEMS.register(
            "dragon_claw_gloves_rare",
            () -> new DragonClawGlovesRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> DRAGON_CLAW_GLOVES_EPIC = ITEMS.register(
            "dragon_claw_gloves_epic",
            () -> new DragonClawGlovesEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> DRAGON_CLAW_GLOVES_LEGENDARY = ITEMS.register(
            "dragon_claw_gloves_legendary",
            () -> new DragonClawGlovesLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> DRAGON_CLAW_GLOVES_MYTHIC = ITEMS.register(
            "dragon_claw_gloves_mythic",
            () -> new DragonClawGlovesMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class DragonClawGlovesRare extends Item {
        public DragonClawGlovesRare(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon Claw Gloves").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+3 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5.5 Accuracy").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Epic ---

    private static class DragonClawGlovesEpic extends Item {
        public DragonClawGlovesEpic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon Claw Gloves").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+6 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+8.75 Accuracy").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Legendary ---

    private static class DragonClawGlovesLegendary extends Item {
        public DragonClawGlovesLegendary(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon Claw Gloves").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+9 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3.5 Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+11 Accuracy").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+5% Accuracy").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Mythic ---

    private static class DragonClawGlovesMythic extends Item {
        public DragonClawGlovesMythic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon Claw Gloves").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+12 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+7 Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+11 Accuracy").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+10% Accuracy").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }
}
