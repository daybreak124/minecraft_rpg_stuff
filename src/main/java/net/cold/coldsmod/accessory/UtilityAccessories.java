package net.cold.coldsmod.accessory;

import net.cold.coldsmod.ColdsMod;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import top.theillusivec4.curios.api.SlotContext;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class UtilityAccessories {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    // UUIDs
    private static final UUID CLOUDTREADER_UUID = UUID.fromString("11111111-2222-3333-4444-555555555555");
    private static final UUID MONIS_LUCKY_UUID = UUID.fromString("22222222-3333-4444-5555-666666666666");
    private static final UUID ENDERMAN_FINGERS_UUID = UUID.fromString("33333333-4444-5555-6666-777777777777");
    private static final UUID ANTIQUE_WATCH_UUID = UUID.fromString("44444444-5555-6666-7777-888888888888");
    private static final UUID REINFORCED_DIAMOND_UUID = UUID.fromString("55555555-6666-7777-8888-999999999999");
    private static final UUID CLOUDSPIRE_GEM_UUID = UUID.fromString("66666666-7777-8888-9999-aaaaaaaaaaaa");

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

    private static class CloudtreaderBoots extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public CloudtreaderBoots(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, CLOUDTREADER_UUID);
                AttributeApplier.applyModifier(player, ForgeMod.STEP_HEIGHT_ADDITION.get(), 1.0, CLOUDTREADER_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "cloudtreader_boots");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), CLOUDTREADER_UUID);
                AttributeApplier.removeModifier(player, ForgeMod.STEP_HEIGHT_ADDITION.get(), CLOUDTREADER_UUID);
            }
        }

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

    private static class MonisLuckyCharm extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public MonisLuckyCharm(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, MONIS_LUCKY_UUID);
                AttributeApplier.applyModifier(player, Attributes.LUCK, 3.0, MONIS_LUCKY_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "monis_lucky_charm");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), MONIS_LUCKY_UUID);
                AttributeApplier.removeModifier(player, Attributes.LUCK, MONIS_LUCKY_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Moni's Lucky Charm").withStyle(style -> style.withColor(0xD6C97A)); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Luck").withStyle(style -> style.withColor(0xD6C97A)));
        }
    }

    // -----------------------------
    // --- Enderman's Fingers -----
    // -----------------------------

    private static class EndermanFingers extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public EndermanFingers(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, ENDERMAN_FINGERS_UUID);
                AttributeApplier.applyModifier(player, ForgeMod.BLOCK_REACH.get(), 3.0, ENDERMAN_FINGERS_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "enderman_fingers");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), ENDERMAN_FINGERS_UUID);
                AttributeApplier.removeModifier(player, ForgeMod.BLOCK_REACH.get(), ENDERMAN_FINGERS_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Enderman's Fingers").withStyle(style -> style.withColor(0xD6C97A)); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Block Reach").withStyle(style -> style.withColor(0xD6C97A)));
        }
    }

    // -----------------------------
    // --- Antique Pocket Watch -----
    // -----------------------------

    private static class AntiquePocketWatch extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public AntiquePocketWatch(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, ANTIQUE_WATCH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.XP_GAIN.get(), 30.0, ANTIQUE_WATCH_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "antique_pocket_watch");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), ANTIQUE_WATCH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.XP_GAIN.get(), ANTIQUE_WATCH_UUID);
            }
        }

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

    private static class ReinforcedDiamondPlating extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public ReinforcedDiamondPlating(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, REINFORCED_DIAMOND_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MINING_SPEED.get(), 30.0, REINFORCED_DIAMOND_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "reinforced_diamond_plating");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), REINFORCED_DIAMOND_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MINING_SPEED.get(), REINFORCED_DIAMOND_UUID);
            }
        }

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

    private static class CloudspireGem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public CloudspireGem(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, CLOUDSPIRE_GEM_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.JUMP_BOOST.get(), 1.0, CLOUDSPIRE_GEM_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "cloudspire_gem");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), CLOUDSPIRE_GEM_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.JUMP_BOOST.get(), CLOUDSPIRE_GEM_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Cloudspire Gem").withStyle(style -> style.withColor(0xD6C97A)); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+100% Jump Boost").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal("-50% Fall Damage").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal("+100% Fall Damage Distance").withStyle(style -> style.withColor(0xD6C97A)));

        }
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
