package net.cold.coldsmod.accessory.mind;

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
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import top.theillusivec4.curios.api.SlotContext;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class EndlessWaves {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static final UUID ENDLESS_WAVES_UUID = UUID.fromString("a1b2c3d4-e5f6-4a5b-8c9d-1e2f3a4b5c6d");

// --- Registry ---

    public static final RegistryObject<Item> ENDLESS_WAVES_RARE = ITEMS.register(
            "endless_waves_rare",
            () -> new EndlessWavesRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> ENDLESS_WAVES_EPIC = ITEMS.register(
            "endless_waves_epic",
            () -> new EndlessWavesEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> ENDLESS_WAVES_LEGENDARY = ITEMS.register(
            "endless_waves_legendary",
            () -> new EndlessWavesLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> ENDLESS_WAVES_MYTHIC = ITEMS.register(
            "endless_waves_mythic",
            () -> new EndlessWavesMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class EndlessWavesRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public EndlessWavesRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 1.0, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 2.0, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 2.0, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 7.0, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), 5.0, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, Attributes.MOVEMENT_SPEED, 0.0075, ENDLESS_WAVES_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "endless_waves");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, Attributes.MOVEMENT_SPEED, ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), ENDLESS_WAVES_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Endless Waves").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+2 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Wisdom").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+7 Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+5 Restoration").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal("+7.5% Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Tsunami Sea").withStyle(ChatFormatting.AQUA));
        }
    }

// --- Epic ---

    private static class EndlessWavesEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public EndlessWavesEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 3.0, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 5.0, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 4.0, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 9.0, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), 7.5, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, Attributes.MOVEMENT_SPEED, 0.01, ENDLESS_WAVES_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "endless_waves");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, Attributes.MOVEMENT_SPEED, ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), ENDLESS_WAVES_UUID);

            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Endless Waves").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+4 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5 Wisdom").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+9 Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+7.5 Restoration").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal("+10% Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Tsunami Sea").withStyle(ChatFormatting.AQUA));
        }
    }

// --- Legendary ---

    private static class EndlessWavesLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public EndlessWavesLegendary(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 5.0, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 9.0, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 5.0, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 14.0, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), 11, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), 0.1, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, Attributes.MOVEMENT_SPEED, 0.01, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.RESTORATION_MULTIPLIER.get(), 0.05, ENDLESS_WAVES_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "endless_waves");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, Attributes.MOVEMENT_SPEED, ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.RESTORATION_MULTIPLIER.get(), ENDLESS_WAVES_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Endless Waves").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+5 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+9 Wisdom").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+14 Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+10% Haste").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal("+11 Restoration").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal("+5% Restoration").withStyle(style -> style.withColor(0x3B8132)));
            tooltip.add(Component.literal("+10% Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Tsunami Sea").withStyle(ChatFormatting.AQUA));
        }
    }

// --- Mythic ---

    private static class EndlessWavesMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public EndlessWavesMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 8.0, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 12, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 8.0, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 18.0, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), 15, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), 0.0175, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, Attributes.MOVEMENT_SPEED, 0.0125, ENDLESS_WAVES_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.RESTORATION_MULTIPLIER.get(), 0.125, ENDLESS_WAVES_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "endless_waves");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, Attributes.MOVEMENT_SPEED, ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), ENDLESS_WAVES_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.RESTORATION_MULTIPLIER.get(), ENDLESS_WAVES_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Endless Waves").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+8 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+8 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+12 Wisdom").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+18 Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+17.5% Haste").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal("+15 Restoration").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal("+12.5% Restoration").withStyle(style -> style.withColor(0x3B8132)));
            tooltip.add(Component.literal("+12.5% Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Tsunami Sea").withStyle(ChatFormatting.AQUA));
        }
    }
}
