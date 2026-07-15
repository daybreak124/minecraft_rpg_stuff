package net.cold.coldsmod.accessory;

import net.cold.coldsmod.ColdsMod;
import net.cold.coldsmod.network.Keybinds;
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

public class UtilityAccessories {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    // --- Registry ---

    public static final RegistryObject<Item> CLOUDTREADER_BOOTS = ITEMS.register(
            "cloudtreader_boots",
            () -> new CloudtreaderBoots(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> MONIS_LUCKY_CHARM = ITEMS.register(
            "monis_lucky_charm",
            () -> new MonisLuckyCharm(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> ENDERMAN_FINGERS = ITEMS.register(
            "enderman_fingers",
            () -> new EndermanFingers(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> ANTIQUE_POCKET_WATCH = ITEMS.register(
            "antique_pocket_watch",
            () -> new AntiquePocketWatch(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> REINFORCED_DIAMOND_PLATING = ITEMS.register(
            "reinforced_diamond_plating",
            () -> new ReinforcedDiamondPlating(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> CLOUDSPIRE_GEM = ITEMS.register(
            "cloudspire_gem",
            () -> new CloudspireGem(new Item.Properties().stacksTo(64))
    );

    // -----------------------------
    // --- Cloudtreader Boots -----
    // -----------------------------

    private static class CloudtreaderBoots extends Item {
        public CloudtreaderBoots(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Cloudtreader Boots").withStyle(style -> style.withColor(0xD6C97A)); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Step Height").withStyle(style -> style.withColor(0xD6C97A)));
        }
    }

    // -----------------------------
    // --- Moni's Lucky Charm -----
    // -----------------------------

    private static class MonisLuckyCharm extends Item {
        public MonisLuckyCharm(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Moni's Lucky Charm").withStyle(style -> style.withColor(0xD6C97A)); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Luck").withStyle(style -> style.withColor(0xD6C97A)));
        }
    }

    // -----------------------------
    // --- Enderman's Fingers -----
    // -----------------------------

    private static class EndermanFingers extends Item {
        public EndermanFingers(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Enderman's Fingers").withStyle(style -> style.withColor(0xD6C97A)); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1.5 Block Reach").withStyle(style -> style.withColor(0xD6C97A)));
        }
    }

    // -----------------------------
    // --- Antique Pocket Watch -----
    // -----------------------------

    private static class AntiquePocketWatch extends Item {
        public AntiquePocketWatch(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Antique Pocket Watch").withStyle(style -> style.withColor(0xD6C97A)); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+30% XP Gain").withStyle(style -> style.withColor(0xD6C97A)));
        }
    }

    // -----------------------------
    // --- Reinforced Diamond Plating -----
    // -----------------------------

    private static class ReinforcedDiamondPlating extends Item {
        public ReinforcedDiamondPlating(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Reinforced Diamond Plating").withStyle(style -> style.withColor(0xD6C97A)); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+30% Mining Speed").withStyle(style -> style.withColor(0xD6C97A)));
        }
    }

    // -----------------------------
    // --- Cloudspire Gem -----
    // -----------------------------

    private static class CloudspireGem extends Item {
        public CloudspireGem(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Cloudspire Gem").withStyle(style -> style.withColor(0xD6C97A)); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+100% Jump Boost").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal("+100% Fall Damage Distance Threshold").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.empty());

            Component keyName = Keybinds.jumpBoostKey.getTranslatedKeyMessage();
            tooltip.add(Component.literal(" Turn on/off: ").withStyle(ChatFormatting.GRAY)
                    .append(keyName.copy().withStyle(ChatFormatting.YELLOW)));
        }
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
