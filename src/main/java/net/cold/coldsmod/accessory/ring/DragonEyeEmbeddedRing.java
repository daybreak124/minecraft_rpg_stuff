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

public class DragonEyeEmbeddedRing {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


// --- Registry ---

    public static final RegistryObject<Item> DRAGON_EYE_EMBEDDED_RING_RARE = ITEMS.register(
            "dragon_eye_embedded_ring_rare",
            () -> new DragonEyeRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> DRAGON_EYE_EMBEDDED_RING_EPIC = ITEMS.register(
            "dragon_eye_embedded_ring_epic",
            () -> new DragonEyeEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> DRAGON_EYE_EMBEDDED_RING_LEGENDARY = ITEMS.register(
            "dragon_eye_embedded_ring_legendary",
            () -> new DragonEyeLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> DRAGON_EYE_EMBEDDED_RING_MYTHIC = ITEMS.register(
            "dragon_eye_embedded_ring_mythic",
            () -> new DragonEyeMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class DragonEyeRare extends Item {
        public DragonEyeRare(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon Eye Embedded Ring").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+2 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+2 Potency").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
            tooltip.add(Component.literal("+1.25 Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Epic ---

    private static class DragonEyeEpic extends Item {
        public DragonEyeEpic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon Eye Embedded Ring").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+3 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+4 Potency").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
            tooltip.add(Component.literal("+2.5 Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Legendary ---

    private static class DragonEyeLegendary extends Item {
        public DragonEyeLegendary(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon Eye Embedded Ring").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+5 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+6 Potency").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
            tooltip.add(Component.literal("+3.75 Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));

            tooltip.add(Component.literal("+3.75% Potency").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));
            tooltip.add(Component.literal("+1.25% Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Mythic ---

    private static class DragonEyeMythic extends Item {
        public DragonEyeMythic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon Eye Embedded Ring").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+6 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+6 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+8 Potency").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
            tooltip.add(Component.literal("+5 Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));

            tooltip.add(Component.literal("+7.5% Potency").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));
            tooltip.add(Component.literal("+2.5% Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }
}
