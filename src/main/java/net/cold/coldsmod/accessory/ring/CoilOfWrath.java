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


public class CoilOfWrath {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static final UUID COIL_OF_WRATH_UUID = UUID.fromString("c9b8a7d6-e5f4-4321-b1a2-c3d4e5f6a7b8");

// --- Registry ---

    public static final RegistryObject<Item> COIL_OF_WRATH_RARE = ITEMS.register(
            "coil_of_wrath_rare",
            () -> new CoilOfWrathRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> COIL_OF_WRATH_EPIC = ITEMS.register(
            "coil_of_wrath_epic",
            () -> new CoilOfWrathEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> COIL_OF_WRATH_LEGENDARY = ITEMS.register(
            "coil_of_wrath_legendary",
            () -> new CoilOfWrathLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> COIL_OF_WRATH_MYTHIC = ITEMS.register(
            "coil_of_wrath_mythic",
            () -> new CoilOfWrathMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class CoilOfWrathRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public CoilOfWrathRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, COIL_OF_WRATH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 7.0, COIL_OF_WRATH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 8.0, COIL_OF_WRATH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION.get(), 8.0, COIL_OF_WRATH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), 8.0, COIL_OF_WRATH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), -3.0, COIL_OF_WRATH_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "coil_of_wrath");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), COIL_OF_WRATH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), COIL_OF_WRATH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), COIL_OF_WRATH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION.get(), COIL_OF_WRATH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), COIL_OF_WRATH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), COIL_OF_WRATH_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Coil of Wrath").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+7 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-3 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+8 Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Epic ---

    private static class CoilOfWrathEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public CoilOfWrathEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, COIL_OF_WRATH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 10.0, COIL_OF_WRATH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 14.0, COIL_OF_WRATH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION.get(), 14.0, COIL_OF_WRATH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), 14.0, COIL_OF_WRATH_UUID);

                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), -3.0, COIL_OF_WRATH_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "coil_of_wrath");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), COIL_OF_WRATH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), COIL_OF_WRATH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), COIL_OF_WRATH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION.get(), COIL_OF_WRATH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), COIL_OF_WRATH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), COIL_OF_WRATH_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Coil of Wrath").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+10 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-3 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+14 Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Legendary ---

    private static class CoilOfWrathLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public CoilOfWrathLegendary(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, COIL_OF_WRATH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 12.0, COIL_OF_WRATH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 20.0, COIL_OF_WRATH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION.get(), 20.0, COIL_OF_WRATH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), 20.0, COIL_OF_WRATH_UUID);

                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), -3.0, COIL_OF_WRATH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 7, COIL_OF_WRATH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 4.5, COIL_OF_WRATH_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "coil_of_wrath");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), COIL_OF_WRATH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), COIL_OF_WRATH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), COIL_OF_WRATH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), COIL_OF_WRATH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), COIL_OF_WRATH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION.get(), COIL_OF_WRATH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), COIL_OF_WRATH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), COIL_OF_WRATH_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Coil of Wrath").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+12 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-3 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+7 Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+4.5 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+20 Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Mythic ---

    private static class CoilOfWrathMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public CoilOfWrathMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, COIL_OF_WRATH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 14.0, COIL_OF_WRATH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 24.0, COIL_OF_WRATH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION.get(), 24.0, COIL_OF_WRATH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), 24.0, COIL_OF_WRATH_UUID);

                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), -3.0, COIL_OF_WRATH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 12.0, COIL_OF_WRATH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 10.0, COIL_OF_WRATH_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "coil_of_wrath");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), COIL_OF_WRATH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), COIL_OF_WRATH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), COIL_OF_WRATH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), COIL_OF_WRATH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), COIL_OF_WRATH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION.get(), COIL_OF_WRATH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), COIL_OF_WRATH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), COIL_OF_WRATH_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Coil of Wrath").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+14 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-3 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+12 Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+10 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+24 Precision").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }
}
