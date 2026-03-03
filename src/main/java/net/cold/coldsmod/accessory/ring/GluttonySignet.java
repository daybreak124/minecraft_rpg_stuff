package net.cold.coldsmod.accessory.ring;

import net.cold.coldsmod.ColdsMod;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
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

public class GluttonySignet {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


// --- Registry ---

    public static final RegistryObject<Item> GLUTTONY_SIGNET_RARE = ITEMS.register(
            "gluttony_signet_rare",
            () -> new GluttonySignetRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> GLUTTONY_SIGNET_EPIC = ITEMS.register(
            "gluttony_signet_epic",
            () -> new GluttonySignetEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> GLUTTONY_SIGNET_LEGENDARY = ITEMS.register(
            "gluttony_signet_legendary",
            () -> new GluttonySignetLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> GLUTTONY_SIGNET_MYTHIC = ITEMS.register(
            "gluttony_signet_mythic",
            () -> new GluttonySignetMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class GluttonySignetRare extends Item {
        public GluttonySignetRare(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Gluttony Signet").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+1 Attributes").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+0.5 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+0.5 Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+0.2 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("-20% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+2 Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+2 Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("-2 Restoration").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal("+2 Rejuvenation").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal("-15% Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));

        }
    }

// --- Epic ---

    private static class GluttonySignetEpic extends Item {
        public GluttonySignetEpic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Gluttony Signet").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+2 Attributes").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+1 Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+0.4 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("-20% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+2.5 Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+2.5 Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("-2.5 Restoration").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal("+2.5 Rejuvenation").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal("-15% Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Legendary ---

    private static class GluttonySignetLegendary extends Item {
        public GluttonySignetLegendary(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Gluttony Signet").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+3 Attributes").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+1.5 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+1.5 Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+0.6 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("-20% Debuff Resist").withStyle(ChatFormatting.BLUE));

            tooltip.add(Component.literal("+2% Armor").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0F52BA))));
            tooltip.add(Component.literal("+2% Toughness").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0F52BA))));

            tooltip.add(Component.literal("+3.25 Potency").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
            tooltip.add(Component.literal("+3.25 Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));

            tooltip.add(Component.literal("+3% Potency").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));
            tooltip.add(Component.literal("+3% Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));

            tooltip.add(Component.literal("-3.25 Restoration").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal("+3.25 Rejuvenation").withStyle(style -> style.withColor(0x5BB450)));

            tooltip.add(Component.literal("-3% Restoration").withStyle(style -> style.withColor(0x3B8132)));
            tooltip.add(Component.literal("+3% Rejuvenation").withStyle(style -> style.withColor(0x3B8132)));


            tooltip.add(Component.literal("-15% Speed").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xD6C97A))));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Mythic ---

    private static class GluttonySignetMythic extends Item {
        public GluttonySignetMythic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Gluttony Signet").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+4 Attributes").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+2 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+2 Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+0.8 Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("-20% Debuff Resist").withStyle(ChatFormatting.BLUE));

            tooltip.add(Component.literal("+4% Armor").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0F52BA))));
            tooltip.add(Component.literal("+4% Toughness").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0F52BA))));

            tooltip.add(Component.literal("+4 Potency").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
            tooltip.add(Component.literal("+4 Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));

            tooltip.add(Component.literal("+6% Potency").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));
            tooltip.add(Component.literal("+6% Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));

            tooltip.add(Component.literal("-4 Restoration").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal("+4 Rejuvenation").withStyle(style -> style.withColor(0x5BB450)));

            tooltip.add(Component.literal("-4% Restoration").withStyle(style -> style.withColor(0x3B8132)));
            tooltip.add(Component.literal("+4% Rejuvenation").withStyle(style -> style.withColor(0x3B8132)));

            tooltip.add(Component.literal("-15% Speed").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xD6C97A))));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }
}