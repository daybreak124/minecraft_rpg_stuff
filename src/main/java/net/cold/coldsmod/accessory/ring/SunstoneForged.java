package net.cold.coldsmod.accessory.ring;

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

public class SunstoneForged {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


// --- Registry ---

    public static final RegistryObject<Item> SUNSTONE_FORGED_RING_RARE = ITEMS.register(
            "sunstone_forged_ring_rare",
            () -> new SunstoneRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> SUNSTONE_FORGED_RING_EPIC = ITEMS.register(
            "sunstone_forged_ring_epic",
            () -> new SunstoneEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> SUNSTONE_FORGED_RING_LEGENDARY = ITEMS.register(
            "sunstone_forged_ring_legendary",
            () -> new SunstoneLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> SUNSTONE_FORGED_RING_MYTHIC = ITEMS.register(
            "sunstone_forged_ring_mythic",
            () -> new SunstoneMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class SunstoneRare extends Item {
        public SunstoneRare(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Sunstone Forged Ring").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+2 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+8 Accuracy").withStyle(style -> style.withColor(0xE0701B)));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Sun's Cruelty").withStyle(ChatFormatting.YELLOW));
        }
    }

// --- Epic ---

    private static class SunstoneEpic extends Item {
        public SunstoneEpic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Sunstone Forged Ring").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+3 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+7 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+14 Accuracy").withStyle(style -> style.withColor(0xE0701B)));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Sun's Cruelty").withStyle(ChatFormatting.YELLOW));
        }
    }

// --- Legendary ---

    private static class SunstoneLegendary extends Item {
        public SunstoneLegendary(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Sunstone Forged Ring").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+4 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+10 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+8 Nock Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+16 Accuracy").withStyle(style -> style.withColor(0xE0701B)));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Sun's Cruelty").withStyle(ChatFormatting.YELLOW));
        }
    }

// --- Mythic ---

    private static class SunstoneMythic extends Item {
        public SunstoneMythic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Sunstone Forged Ring").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+6 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+13 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+12 Nock Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+21 Accuracy").withStyle(style -> style.withColor(0xE0701B)));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Sun's Cruelty").withStyle(ChatFormatting.YELLOW));
        }
    }
}