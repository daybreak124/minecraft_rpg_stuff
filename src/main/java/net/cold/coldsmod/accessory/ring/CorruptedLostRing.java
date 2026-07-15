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

public class CorruptedLostRing {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


// --- Registry ---

    public static final RegistryObject<Item> CORRUPTED_RING_OF_THE_LOST_RARE = ITEMS.register(
            "corrupted_ring_of_the_lost_rare",
            () -> new CorruptedRingRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> CORRUPTED_RING_OF_THE_LOST_EPIC = ITEMS.register(
            "corrupted_ring_of_the_lost_epic",
            () -> new CorruptedRingEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> CORRUPTED_RING_OF_THE_LOST_LEGENDARY = ITEMS.register(
            "corrupted_ring_of_the_lost_legendary",
            () -> new CorruptedRingLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> CORRUPTED_RING_OF_THE_LOST_MYTHIC = ITEMS.register(
            "corrupted_ring_of_the_lost_mythic",
            () -> new CorruptedRingMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class CorruptedRingRare extends Item {
        public CorruptedRingRare(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Corrupted Ring of the Lost").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+3 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+1.5 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+3% Knockback Resist").withStyle(ChatFormatting.BLUE));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Epic ---

    private static class CorruptedRingEpic extends Item {
        public CorruptedRingEpic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Corrupted Ring of the Lost").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+6 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+3 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+6% Knockback Resist").withStyle(ChatFormatting.BLUE));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Legendary ---

    private static class CorruptedRingLegendary extends Item {
        public CorruptedRingLegendary(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Corrupted Ring of the Lost").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+9 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+3 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+2 Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+9% Knockback Resist").withStyle(ChatFormatting.BLUE));

            tooltip.add(Component.literal("+5% Armor").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0F52BA))));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Mythic ---

    private static class CorruptedRingMythic extends Item {
        public CorruptedRingMythic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Corrupted Ring of the Lost").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+12 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+3.5 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+3 Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+12% Knockback Resist").withStyle(ChatFormatting.BLUE));

            tooltip.add(Component.literal("+10% Armor").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0F52BA))));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }
}