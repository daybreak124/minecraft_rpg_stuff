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

public class SunsGaze {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


// --- Registry ---

    public static final RegistryObject<Item> SUNS_GAZE_RARE = ITEMS.register(
            "suns_gaze_rare",
            () -> new SunsGazeRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> SUNS_GAZE_EPIC = ITEMS.register(
            "suns_gaze_epic",
            () -> new SunsGazeEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> SUNS_GAZE_LEGENDARY = ITEMS.register(
            "suns_gaze_legendary",
            () -> new SunsGazeLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> SUNS_GAZE_MYTHIC = ITEMS.register(
            "suns_gaze_mythic",
            () -> new SunsGazeMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class SunsGazeRare extends Item {
        public SunsGazeRare(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Sun's Gaze").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+3 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+8 Melee Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+5 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("-5% Swim Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Sun's Cruelty").withStyle(ChatFormatting.YELLOW));
        }
    }

// --- Epic ---

    private static class SunsGazeEpic extends Item {
        public SunsGazeEpic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Sun's Gaze").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+5 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+14 Melee Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+6 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("-10% Swim Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Sun's Cruelty").withStyle(ChatFormatting.YELLOW));
        }
    }

// --- Legendary ---

    private static class SunsGazeLegendary extends Item {
        public SunsGazeLegendary(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Sun's Gaze").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+9 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+10% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+18 Melee Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+9 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+8% Melee Potency").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal("-15% Swim Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Sun's Cruelty").withStyle(ChatFormatting.YELLOW));
        }
    }

// --- Mythic ---

    private static class SunsGazeMythic extends Item {
        public SunsGazeMythic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Sun's Gaze").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+13 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+15% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+22 Melee Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+12 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+12.5% Melee Potency").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal("-20% Swim Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Sun's Cruelty").withStyle(ChatFormatting.YELLOW));
        }
    }
}
