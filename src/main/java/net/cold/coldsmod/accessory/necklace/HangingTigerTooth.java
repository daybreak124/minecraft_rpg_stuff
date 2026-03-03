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

public class HangingTigerTooth {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


// --- Registry ---

    public static final RegistryObject<Item> HANGING_TIGER_TOOTH_RARE = ITEMS.register(
            "hanging_tiger_tooth_rare",
            () -> new HangingTigerToothRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> HANGING_TIGER_TOOTH_EPIC = ITEMS.register(
            "hanging_tiger_tooth_epic",
            () -> new HangingTigerToothEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> HANGING_TIGER_TOOTH_LEGENDARY = ITEMS.register(
            "hanging_tiger_tooth_legendary",
            () -> new HangingTigerToothLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> HANGING_TIGER_TOOTH_MYTHIC = ITEMS.register(
            "hanging_tiger_tooth_mythic",
            () -> new HangingTigerToothMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class HangingTigerToothRare extends Item {
        public HangingTigerToothRare(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Hanging Tiger Tooth").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+6 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+2 Melee Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+4.5 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+3 Nock Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Nature's Blessing").withStyle(ChatFormatting.GREEN));
        }
    }

// --- Epic ---

    private static class HangingTigerToothEpic extends Item {
        public HangingTigerToothEpic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Hanging Tiger Tooth").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+9 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+7.5% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+2.5 Melee Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+7 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+6 Nock Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Nature's Blessing").withStyle(ChatFormatting.GREEN));
        }
    }

// --- Legendary ---

    private static class HangingTigerToothLegendary extends Item {
        public HangingTigerToothLegendary(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Hanging Tiger Tooth").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+12 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+10% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+4 Melee Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+11 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+8.5 Nock Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+10% Nock Haste").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Nature's Blessing").withStyle(ChatFormatting.GREEN));
        }
    }

// --- Mythic ---

    private static class HangingTigerToothMythic extends Item {
        public HangingTigerToothMythic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Hanging Tiger Tooth").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+16 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+12.5 Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+6 Melee Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+17 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+15.5 Nock Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+18% Nock Haste ").withStyle(style -> style.withColor(0xec3700)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Nature's Blessing").withStyle(ChatFormatting.GREEN));
        }
    }
}
