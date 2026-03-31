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

public class StolenLegacies {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


// --- Registry ---

    public static final RegistryObject<Item> STOLEN_LEGACIES_CHOKER_RARE = ITEMS.register(
            "stolen_legacies_choker_rare",
            () -> new StolenLegaciesChokerRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> STOLEN_LEGACIES_CHOKER_EPIC = ITEMS.register(
            "stolen_legacies_choker_epic",
            () -> new StolenLegaciesChokerEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> STOLEN_LEGACIES_CHOKER_LEGENDARY = ITEMS.register(
            "stolen_legacies_choker_legendary",
            () -> new StolenLegaciesChokerLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> STOLEN_LEGACIES_CHOKER_MYTHIC = ITEMS.register(
            "stolen_legacies_choker_mythic",
            () -> new StolenLegaciesChokerMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class StolenLegaciesChokerRare extends Item {
        public StolenLegaciesChokerRare(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Stolen Legacies Choker").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+6 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+6 Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+4 Restoration").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal("+15% XP Gain").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Stolen Wealth").withStyle(ChatFormatting.DARK_GREEN));
        }
    }

// --- Epic ---

    private static class StolenLegaciesChokerEpic extends Item {
        public StolenLegaciesChokerEpic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Stolen Legacies Choker").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+10 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+10 Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+7 Restoration").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal("+20% XP Gain").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Stolen Wealth").withStyle(ChatFormatting.DARK_GREEN));
        }
    }

// --- Legendary ---

    private static class StolenLegaciesChokerLegendary extends Item {
        public StolenLegaciesChokerLegendary(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Stolen Legacies Choker").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+16 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+12 Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+10% Precision").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal("+12.5 Restoration").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal("+8% Restoration").withStyle(style -> style.withColor(0x3B8132)));
            tooltip.add(Component.literal("+25% XP Gain").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Stolen Wealth").withStyle(ChatFormatting.DARK_GREEN));
        }
    }

// --- Mythic ---

    private static class StolenLegaciesChokerMythic extends Item {
        public StolenLegaciesChokerMythic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Stolen Legacies Choker").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+20 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+16 Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+16% Precision").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal("+15 Restoration").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal("+14% Restoration").withStyle(style -> style.withColor(0x3B8132)));
            tooltip.add(Component.literal("+30% XP Gain").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Stolen Wealth").withStyle(ChatFormatting.DARK_GREEN));
        }
    }
}
