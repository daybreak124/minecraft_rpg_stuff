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

public class DragonRoar {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static final UUID DRAGONS_ROAR_UUID = UUID.fromString("e4d5c6b7-a8b9-4c0d-1e2f-3a4b5c6d7e8f");

// --- Registry ---

    public static final RegistryObject<Item> DRAGONS_ROAR_RARE = ITEMS.register(
            "dragons_roar_rare",
            () -> new DragonsRoarRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> DRAGONS_ROAR_EPIC = ITEMS.register(
            "dragons_roar_epic",
            () -> new DragonsRoarEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> DRAGONS_ROAR_LEGENDARY = ITEMS.register(
            "dragons_roar_legendary",
            () -> new DragonsRoarLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> DRAGONS_ROAR_MYTHIC = ITEMS.register(
            "dragons_roar_mythic",
            () -> new DragonsRoarMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class DragonsRoarRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public DragonsRoarRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 2.0, DRAGONS_ROAR_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, DRAGONS_ROAR_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 4.0, DRAGONS_ROAR_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "dragons_roar");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGONS_ROAR_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGONS_ROAR_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, DRAGONS_ROAR_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon's Roar").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+2 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Epic ---

    private static class DragonsRoarEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public DragonsRoarEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 4.0, DRAGONS_ROAR_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, DRAGONS_ROAR_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 6.75, DRAGONS_ROAR_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "dragons_roar");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGONS_ROAR_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGONS_ROAR_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, DRAGONS_ROAR_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon's Roar").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+4 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+6.75 Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Legendary ---

    private static class DragonsRoarLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public DragonsRoarLegendary(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 6.0, DRAGONS_ROAR_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, DRAGONS_ROAR_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), 0.2, DRAGONS_ROAR_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "dragons_roar");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGONS_ROAR_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGONS_ROAR_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), DRAGONS_ROAR_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon's Roar").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+6 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+20% Toughness").withStyle(style -> style.withColor(0x0F52BA)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Mythic ---

    private static class DragonsRoarMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public DragonsRoarMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 8.0, DRAGONS_ROAR_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, DRAGONS_ROAR_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), 0.27, DRAGONS_ROAR_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "dragons_roar");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGONS_ROAR_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGONS_ROAR_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), DRAGONS_ROAR_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon's Roar").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+8 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+27% Toughness").withStyle(style -> style.withColor(0x0F52BA)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }
}
