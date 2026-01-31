package net.cold.coldsmod.accessory.necklace;

import net.cold.coldsmod.ColdsMod;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
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

public class HangingTigerTooth {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static final UUID TIGER_TOOTH_UUID = UUID.fromString("f6a5b4c3-d2e1-4f0a-9b8c-7d6e5f4a3b2c");

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

    private static class HangingTigerToothRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public HangingTigerToothRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, TIGER_TOOTH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 6.0, TIGER_TOOTH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 4.5, TIGER_TOOTH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 3.0, TIGER_TOOTH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 2, TIGER_TOOTH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 5.0, TIGER_TOOTH_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "hanging_tiger_tooth");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), TIGER_TOOTH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), TIGER_TOOTH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), TIGER_TOOTH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), TIGER_TOOTH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), TIGER_TOOTH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), TIGER_TOOTH_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Hanging Tiger Tooth").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+6 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+2 Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+4.5 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+3 Nock Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Nature's Blessing").withStyle(ChatFormatting.GREEN));
        }
    }

// --- Epic ---

    private static class HangingTigerToothEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public HangingTigerToothEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, TIGER_TOOTH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 9.0, TIGER_TOOTH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 7.0, TIGER_TOOTH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 5.0, TIGER_TOOTH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 3.5, TIGER_TOOTH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 7.5, TIGER_TOOTH_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "hanging_tiger_tooth");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), TIGER_TOOTH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), TIGER_TOOTH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), TIGER_TOOTH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), TIGER_TOOTH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), TIGER_TOOTH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), TIGER_TOOTH_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Hanging Tiger Tooth").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+9 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+7.5% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+3.5 Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+7 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+5 Nock Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Nature's Blessing").withStyle(ChatFormatting.GREEN));
        }
    }

// --- Legendary ---

    private static class HangingTigerToothLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public HangingTigerToothLegendary(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, TIGER_TOOTH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 12.0, TIGER_TOOTH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 10.0, TIGER_TOOTH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 7.5, TIGER_TOOTH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 6, TIGER_TOOTH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 10.0, TIGER_TOOTH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), 0.1, TIGER_TOOTH_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "hanging_tiger_tooth");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), TIGER_TOOTH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), TIGER_TOOTH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), TIGER_TOOTH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), TIGER_TOOTH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), TIGER_TOOTH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), TIGER_TOOTH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), TIGER_TOOTH_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Hanging Tiger Tooth").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+12 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+10% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+6 Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+10 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+7.5 Nock Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+10% Nock Haste").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Nature's Blessing").withStyle(ChatFormatting.GREEN));
        }
    }

// --- Mythic ---

    private static class HangingTigerToothMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public HangingTigerToothMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, TIGER_TOOTH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 16.0, TIGER_TOOTH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 15.0, TIGER_TOOTH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 13.5, TIGER_TOOTH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 10, TIGER_TOOTH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 12.5, TIGER_TOOTH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), 0.18, TIGER_TOOTH_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "hanging_tiger_tooth");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), TIGER_TOOTH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), TIGER_TOOTH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), TIGER_TOOTH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), TIGER_TOOTH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), TIGER_TOOTH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), TIGER_TOOTH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), TIGER_TOOTH_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Hanging Tiger Tooth").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+16 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+12.5 Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+10 Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+15 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+13.5 Nock Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+18% Nock Haste ").withStyle(style -> style.withColor(0xec3700)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Nature's Blessing").withStyle(ChatFormatting.GREEN));
        }
    }
}
