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


public class CoilOfWrath {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


// --- Registry ---

    public static final RegistryObject<Item> COIL_OF_WRATH_RARE = ITEMS.register(
            "coil_of_wrath_rare",
            () -> new CoilOfWrathRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> COIL_OF_WRATH_EPIC = ITEMS.register(
            "coil_of_wrath_epic",
            () -> new CoilOfWrathEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> COIL_OF_WRATH_LEGENDARY = ITEMS.register(
            "coil_of_wrath_legendary",
            () -> new CoilOfWrathLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> COIL_OF_WRATH_MYTHIC = ITEMS.register(
            "coil_of_wrath_mythic",
            () -> new CoilOfWrathMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class CoilOfWrathRare extends Item {
        public CoilOfWrathRare(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Coil of Wrath").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+7 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-3 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+8 Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Epic ---

    private static class CoilOfWrathEpic extends Item {
        public CoilOfWrathEpic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Coil of Wrath").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+10 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-3 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+13 Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Legendary ---

    private static class CoilOfWrathLegendary extends Item {
        public CoilOfWrathLegendary(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Coil of Wrath").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+12 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-3 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+8 Melee Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+3 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+17 Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Mythic ---

    private static class CoilOfWrathMythic extends Item {
        public CoilOfWrathMythic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Coil of Wrath").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+14 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-3 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+15 Melee Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+7 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+22 Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }
}
