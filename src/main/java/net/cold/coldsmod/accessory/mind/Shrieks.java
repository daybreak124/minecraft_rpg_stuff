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

public class Shrieks {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


// --- Registry ---

    public static final RegistryObject<Item> SHRIEKS_OF_UNSEEING_RARE = ITEMS.register(
            "shrieks_of_unseeing_rare",
            () -> new ShrieksOfUnseeingRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> SHRIEKS_OF_UNSEEING_EPIC = ITEMS.register(
            "shrieks_of_unseeing_epic",
            () -> new ShrieksOfUnseeingEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> SHRIEKS_OF_UNSEEING_LEGENDARY = ITEMS.register(
            "shrieks_of_unseeing_legendary",
            () -> new ShrieksOfUnseeingLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> SHRIEKS_OF_UNSEEING_MYTHIC = ITEMS.register(
            "shrieks_of_unseeing_mythic",
            () -> new ShrieksOfUnseeingMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class ShrieksOfUnseeingRare extends Item {
        public ShrieksOfUnseeingRare(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Shrieks of Unseeing").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+6 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-3 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2.5 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+5% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("-8 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Otherworlds").withStyle(ChatFormatting.DARK_AQUA));
        }
    }

// --- Epic ---

    private static class ShrieksOfUnseeingEpic extends Item {
        public ShrieksOfUnseeingEpic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Shrieks of Unseeing").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+10 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-3 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3.75 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+7.5% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("-8 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Otherworlds").withStyle(ChatFormatting.DARK_AQUA));
        }
    }

// --- Legendary ---

    private static class ShrieksOfUnseeingLegendary extends Item {
        public ShrieksOfUnseeingLegendary(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Shrieks of Unseeing").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+13 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-3 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+10% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+7.5% Armor").withStyle(style -> style.withColor(0x0F52BA)));
            tooltip.add(Component.literal("-8 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Otherworlds").withStyle(ChatFormatting.DARK_AQUA));
        }
    }

// --- Mythic ---

    private static class ShrieksOfUnseeingMythic extends Item {
        public ShrieksOfUnseeingMythic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Shrieks of Unseeing").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+17 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-3 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+7.5 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+12.5% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+12% Armor").withStyle(style -> style.withColor(0x0F52BA)));
            tooltip.add(Component.literal("-8 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Otherworlds").withStyle(ChatFormatting.DARK_AQUA));
        }
    }
}
