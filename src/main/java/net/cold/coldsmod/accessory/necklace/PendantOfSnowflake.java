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

public class PendantOfSnowflake {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


// --- Registry ---

    public static final RegistryObject<Item> PENDANT_OF_FLOATING_SNOWFLAKE_RARE = ITEMS.register(
            "pendant_of_floating_snowflake_rare",
            () -> new PendantOfFloatingSnowflakeRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> PENDANT_OF_FLOATING_SNOWFLAKE_EPIC = ITEMS.register(
            "pendant_of_floating_snowflake_epic",
            () -> new PendantOfFloatingSnowflakeEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> PENDANT_OF_FLOATING_SNOWFLAKE_LEGENDARY = ITEMS.register(
            "pendant_of_floating_snowflake_legendary",
            () -> new PendantOfFloatingSnowflakeLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> PENDANT_OF_FLOATING_SNOWFLAKE_MYTHIC = ITEMS.register(
            "pendant_of_floating_snowflake_mythic",
            () -> new PendantOfFloatingSnowflakeMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class PendantOfFloatingSnowflakeRare extends Item {
        public PendantOfFloatingSnowflakeRare(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Pendant of Floating Snowflake").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+8 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Rejuvenation").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Whiteout").withStyle(ChatFormatting.WHITE));
        }
    }

// --- Epic ---

    private static class PendantOfFloatingSnowflakeEpic extends Item {
        public PendantOfFloatingSnowflakeEpic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Pendant of Floating Snowflake").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+16 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Rejuvenation").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Whiteout").withStyle(ChatFormatting.WHITE));
        }
    }

// --- Legendary ---

    private static class PendantOfFloatingSnowflakeLegendary extends Item {
        public PendantOfFloatingSnowflakeLegendary(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Pendant of Floating Snowflake").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+24 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+6 Rejuvenation").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Whiteout").withStyle(ChatFormatting.WHITE));
        }
    }

// --- Mythic ---

    private static class PendantOfFloatingSnowflakeMythic extends Item {
        public PendantOfFloatingSnowflakeMythic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Pendant of Floating Snowflake").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+32 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+8 Rejuvenation").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Whiteout").withStyle(ChatFormatting.WHITE));
        }
    }

}
