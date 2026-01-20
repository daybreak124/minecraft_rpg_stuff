package net.cold.coldsmod.accessory.necklace;

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

public class EnvyCollar {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
    private static final UUID COLLAR_ENVY_UUID = UUID.fromString("c011a7-e117-4b2c-8d3d-7e5f4a3b2c1d");

// --- Registry ---

    public static final RegistryObject<Item> COLLAR_OF_ENVY_RARE = ITEMS.register(
            "collar_of_envy_rare",
            () -> new CollarOfEnvyRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> COLLAR_OF_ENVY_EPIC = ITEMS.register(
            "collar_of_envy_epic",
            () -> new CollarOfEnvyEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> COLLAR_OF_ENVY_LEGENDARY = ITEMS.register(
            "collar_of_envy_legendary",
            () -> new CollarOfEnvyLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> COLLAR_OF_ENVY_MYTHIC = ITEMS.register(
            "collar_of_envy_mythic",
            () -> new CollarOfEnvyMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class CollarOfEnvyRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public CollarOfEnvyRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, COLLAR_ENVY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 3.0, COLLAR_ENVY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 4.0, COLLAR_ENVY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 4.0, COLLAR_ENVY_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 1.0, COLLAR_ENVY_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "collar_of_envy");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), COLLAR_ENVY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), COLLAR_ENVY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), COLLAR_ENVY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), COLLAR_ENVY_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, COLLAR_ENVY_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Collar of Envy").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+3 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+1 Armor Toughness").withStyle(ChatFormatting.BLUE));

            tooltip.add(Component.literal("+4 Melee Potency").withStyle(style -> style.withColor(0xE0701B)));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Epic ---

    private static class CollarOfEnvyEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public CollarOfEnvyEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, COLLAR_ENVY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 3.0, COLLAR_ENVY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 6.0, COLLAR_ENVY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 4.0, COLLAR_ENVY_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 2.0, COLLAR_ENVY_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "collar_of_envy");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), COLLAR_ENVY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), COLLAR_ENVY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), COLLAR_ENVY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), COLLAR_ENVY_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, COLLAR_ENVY_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Collar of Envy").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+3 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+2 Armor Toughness").withStyle(ChatFormatting.BLUE));

            tooltip.add(Component.literal("+6 Melee Potency").withStyle(style -> style.withColor(0xE0701B)));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Legendary ---

    private static class CollarOfEnvyLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public CollarOfEnvyLegendary(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, COLLAR_ENVY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 5.0, COLLAR_ENVY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 9.0, COLLAR_ENVY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 4.0, COLLAR_ENVY_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 4.0, COLLAR_ENVY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), 0.05, COLLAR_ENVY_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "collar_of_envy");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), COLLAR_ENVY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), COLLAR_ENVY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), COLLAR_ENVY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), COLLAR_ENVY_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, COLLAR_ENVY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), COLLAR_ENVY_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Collar of Envy").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+5 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+4 Armor Toughness").withStyle(ChatFormatting.BLUE));

            tooltip.add(Component.literal("+9 Melee Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+5% Melee Potency").withStyle(style -> style.withColor(0xec3700)));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Mythic ---

    private static class CollarOfEnvyMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public CollarOfEnvyMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, COLLAR_ENVY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 6.0, COLLAR_ENVY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 12.0, COLLAR_ENVY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 6.0, COLLAR_ENVY_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 6.0, COLLAR_ENVY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), 0.1, COLLAR_ENVY_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "collar_of_envy");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), COLLAR_ENVY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), COLLAR_ENVY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), COLLAR_ENVY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), COLLAR_ENVY_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, COLLAR_ENVY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), COLLAR_ENVY_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Collar of Envy").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+6 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+6 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+6 Armor Toughness").withStyle(ChatFormatting.BLUE));

            tooltip.add(Component.literal("+12 Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+10% Potency").withStyle(style -> style.withColor(0xec3700)));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }
}
