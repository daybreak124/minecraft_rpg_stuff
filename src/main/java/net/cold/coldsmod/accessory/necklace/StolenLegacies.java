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

public class StolenLegacies {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static final UUID STOLEN_LEGACIES_UUID = UUID.fromString("b7d6c5a4-e3f2-4109-8d7c-6e5b4a3f2d1c");

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

    private static class StolenLegaciesChokerRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public StolenLegaciesChokerRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, STOLEN_LEGACIES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.XP_GAIN.get(), 15.0, STOLEN_LEGACIES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 6.0, STOLEN_LEGACIES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 6.0, STOLEN_LEGACIES_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "stolen_legacies_choker");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), STOLEN_LEGACIES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.XP_GAIN.get(), STOLEN_LEGACIES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), STOLEN_LEGACIES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), STOLEN_LEGACIES_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Stolen Legacies Choker").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+6 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+6 Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+15% XP Gain").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Stolen Wealth").withStyle(ChatFormatting.DARK_GREEN));
        }
    }

// --- Epic ---

    private static class StolenLegaciesChokerEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public StolenLegaciesChokerEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, STOLEN_LEGACIES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.XP_GAIN.get(), 20.0, STOLEN_LEGACIES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 10.0, STOLEN_LEGACIES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 10.0, STOLEN_LEGACIES_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "stolen_legacies_choker");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), STOLEN_LEGACIES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.XP_GAIN.get(), STOLEN_LEGACIES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), STOLEN_LEGACIES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), STOLEN_LEGACIES_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Stolen Legacies Choker").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+10 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+10 Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+20% XP Gain").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Stolen Wealth").withStyle(ChatFormatting.DARK_GREEN));
        }
    }

// --- Legendary ---

    private static class StolenLegaciesChokerLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public StolenLegaciesChokerLegendary(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, STOLEN_LEGACIES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.XP_GAIN.get(), 25.0, STOLEN_LEGACIES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 16.0, STOLEN_LEGACIES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 12.0, STOLEN_LEGACIES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PRECISION_MULTIPLIER.get(), 0.05, STOLEN_LEGACIES_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "stolen_legacies_choker");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), STOLEN_LEGACIES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.XP_GAIN.get(), STOLEN_LEGACIES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), STOLEN_LEGACIES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), STOLEN_LEGACIES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PRECISION_MULTIPLIER.get(), STOLEN_LEGACIES_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Stolen Legacies Choker").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+16 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+12 Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+5% Precision").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal("+25% XP Gain").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Stolen Wealth").withStyle(ChatFormatting.DARK_GREEN));
        }
    }

// --- Mythic ---

    private static class StolenLegaciesChokerMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public StolenLegaciesChokerMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, STOLEN_LEGACIES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.XP_GAIN.get(), 30.0, STOLEN_LEGACIES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 20.0, STOLEN_LEGACIES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 16.0, STOLEN_LEGACIES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PRECISION_MULTIPLIER.get(), 0.1, STOLEN_LEGACIES_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "stolen_legacies_choker");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), STOLEN_LEGACIES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.XP_GAIN.get(), STOLEN_LEGACIES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), STOLEN_LEGACIES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), STOLEN_LEGACIES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PRECISION_MULTIPLIER.get(), STOLEN_LEGACIES_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Stolen Legacies Choker").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+20 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+16 Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+10% Precision").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal("+30% XP Gain").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Stolen Wealth").withStyle(ChatFormatting.DARK_GREEN));
        }
    }
}
