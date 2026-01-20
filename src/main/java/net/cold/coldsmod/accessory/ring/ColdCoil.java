package net.cold.coldsmod.accessory.ring;

import net.cold.coldsmod.ColdsMod;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import top.theillusivec4.curios.api.SlotContext;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class ColdCoil {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static final UUID COLD_COIL_UUID = UUID.fromString("b1c2d3e4-a5b6-7890-c1d2-e3f4a5b6c7d8");

// --- Registry ---

    public static final RegistryObject<Item> COLDYS_COLD_COIL_OF_COLD_RARE = ITEMS.register(
            "coldys_cold_coil_of_cold_rare",
            () -> new ColdCoilRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> COLDYS_COLD_COIL_OF_COLD_EPIC = ITEMS.register(
            "coldys_cold_coil_of_cold_epic",
            () -> new ColdCoilEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> COLDYS_COLD_COIL_OF_COLD_LEGENDARY = ITEMS.register(
            "coldys_cold_coil_of_cold_legendary",
            () -> new ColdCoilLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> COLDYS_COLD_COIL_OF_COLD_MYTHIC = ITEMS.register(
            "coldys_cold_coil_of_cold_mythic",
            () -> new ColdCoilMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class ColdCoilRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public ColdCoilRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, COLD_COIL_UUID);
                AttributeApplier.applyModifier(player, Attributes.MOVEMENT_SPEED, 0.009, COLD_COIL_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 3.0, COLD_COIL_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 3, COLD_COIL_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 4.0, COLD_COIL_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "coldys_cold_coil_of_cold");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), COLD_COIL_UUID);
                AttributeApplier.removeModifier(player, Attributes.MOVEMENT_SPEED, COLD_COIL_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), COLD_COIL_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), COLD_COIL_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), COLD_COIL_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Coldy's Cold Coil of Cold").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+4 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));
            tooltip.add(Component.literal("+3 Nock Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
            tooltip.add(Component.literal("+9% Speed").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xD6C97A))));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Whiteout").withStyle(ChatFormatting.WHITE));
        }
    }

// --- Epic ---

    private static class ColdCoilEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public ColdCoilEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, COLD_COIL_UUID);
                AttributeApplier.applyModifier(player, Attributes.MOVEMENT_SPEED, 0.012, COLD_COIL_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 5.0, COLD_COIL_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 7.5, COLD_COIL_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 6.0, COLD_COIL_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "coldys_cold_coil_of_cold");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), COLD_COIL_UUID);
                AttributeApplier.removeModifier(player, Attributes.MOVEMENT_SPEED, COLD_COIL_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), COLD_COIL_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), COLD_COIL_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), COLD_COIL_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Coldy's Colder Coil of Cold").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+6 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+7.5 Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));
            tooltip.add(Component.literal("+5 Nock Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
            tooltip.add(Component.literal("+12% Speed").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xD6C97A))));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Whiteout").withStyle(ChatFormatting.WHITE));
        }
    }

// --- Legendary ---

    private static class ColdCoilLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public ColdCoilLegendary(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, COLD_COIL_UUID);
                AttributeApplier.applyModifier(player, Attributes.MOVEMENT_SPEED, 0.015, COLD_COIL_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 15.0, COLD_COIL_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 12, COLD_COIL_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), 0.05, COLD_COIL_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 9.0, COLD_COIL_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "coldys_cold_coil_of_cold");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), COLD_COIL_UUID);
                AttributeApplier.removeModifier(player, Attributes.MOVEMENT_SPEED, COLD_COIL_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), COLD_COIL_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), COLD_COIL_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), COLD_COIL_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), COLD_COIL_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Coldy's Colder Coil of Cold").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+9 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+12 Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));
            tooltip.add(Component.literal("+15 Nock Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
            tooltip.add(Component.literal("+5% Nock Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));
            tooltip.add(Component.literal("+15% Speed").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xD6C97A))));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Whiteout").withStyle(ChatFormatting.WHITE));
        }
    }

// --- Mythic ---

    private static class ColdCoilMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public ColdCoilMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, COLD_COIL_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), 0.05, COLD_COIL_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 20.0, COLD_COIL_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 15, COLD_COIL_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), 0.05, COLD_COIL_UUID);
                AttributeApplier.applyModifier(player, Attributes.MOVEMENT_SPEED, 0.015, COLD_COIL_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 13.0, COLD_COIL_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "coldys_cold_coil_of_cold");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), COLD_COIL_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), COLD_COIL_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), COLD_COIL_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), COLD_COIL_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), COLD_COIL_UUID);
                AttributeApplier.removeModifier(player, Attributes.MOVEMENT_SPEED, COLD_COIL_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), COLD_COIL_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Coldy's Coldest Coil of Cold").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+13 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+15 Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));
            tooltip.add(Component.literal("+20 Nock Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
            tooltip.add(Component.literal("+5% Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
            tooltip.add(Component.literal("+5% Nock Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));

            tooltip.add(Component.literal("+15% Speed").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xD6C97A))));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Whiteout").withStyle(ChatFormatting.WHITE));
        }
    }
}