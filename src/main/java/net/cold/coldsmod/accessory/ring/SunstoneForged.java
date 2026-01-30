package net.cold.coldsmod.accessory.ring;

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

public class SunstoneForged {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static final UUID SUNSTONE_UUID = UUID.fromString("a1b2c3d4-e5f6-4789-d1e2-f3a4b5c6d7e8");

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

    private static class SunstoneRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public SunstoneRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, SUNSTONE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 2.0, SUNSTONE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 4.0, SUNSTONE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.ACCURACY.get(), 8.0, SUNSTONE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_ACCURACY.get(), 8.0, SUNSTONE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), 8.0, SUNSTONE_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "sunstone_forged_ring");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SUNSTONE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), SUNSTONE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), SUNSTONE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.ACCURACY.get(), SUNSTONE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_ACCURACY.get(), SUNSTONE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), SUNSTONE_UUID);
            }
        }

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

    private static class SunstoneEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public SunstoneEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, SUNSTONE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 3.0, SUNSTONE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 7.0, SUNSTONE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.ACCURACY.get(), 12.0, SUNSTONE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_ACCURACY.get(), 12.0, SUNSTONE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), 12.0, SUNSTONE_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "sunstone_forged_ring");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SUNSTONE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), SUNSTONE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), SUNSTONE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.ACCURACY.get(), SUNSTONE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_ACCURACY.get(), SUNSTONE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), SUNSTONE_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Sunstone Forged Ring").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+3 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+7 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+12 Accuracy").withStyle(style -> style.withColor(0xE0701B)));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Sun's Cruelty").withStyle(ChatFormatting.YELLOW));
        }
    }

// --- Legendary ---

    private static class SunstoneLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public SunstoneLegendary(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, SUNSTONE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 4.0, SUNSTONE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 10.0, SUNSTONE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.ACCURACY.get(), 16.0, SUNSTONE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_ACCURACY.get(), 16.0, SUNSTONE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), 16.0, SUNSTONE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 8.0, SUNSTONE_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "sunstone_forged_ring");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SUNSTONE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), SUNSTONE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), SUNSTONE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.ACCURACY.get(), SUNSTONE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_ACCURACY.get(), SUNSTONE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), SUNSTONE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), SUNSTONE_UUID);
            }
        }

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

    private static class SunstoneMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public SunstoneMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, SUNSTONE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 6.0, SUNSTONE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 13.0, SUNSTONE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.ACCURACY.get(), 21.0, SUNSTONE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_ACCURACY.get(), 21.0, SUNSTONE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), 21.0, SUNSTONE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 12.0, SUNSTONE_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "sunstone_forged_ring");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SUNSTONE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), SUNSTONE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), SUNSTONE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.ACCURACY.get(), SUNSTONE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_ACCURACY.get(), SUNSTONE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), SUNSTONE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), SUNSTONE_UUID);
            }
        }

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