package net.cold.coldsmod.accessory.bracers;

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

public class ReinforcedSteel {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static final UUID REINFORCED_STEEL_UUID = UUID.fromString("d2a1b3c4-e5f6-4a5b-8c9d-1e2f3a4b5c6d");

// --- Registry ---

    public static final RegistryObject<Item> REINFORCED_STEEL_BRACERS_RARE = ITEMS.register(
            "reinforced_steel_bracers_rare",
            () -> new ReinforcedSteelBracersRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> REINFORCED_STEEL_BRACERS_EPIC = ITEMS.register(
            "reinforced_steel_bracers_epic",
            () -> new ReinforcedSteelBracersEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> REINFORCED_STEEL_BRACERS_LEGENDARY = ITEMS.register(
            "reinforced_steel_bracers_legendary",
            () -> new ReinforcedSteelBracersLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> REINFORCED_STEEL_BRACERS_MYTHIC = ITEMS.register(
            "reinforced_steel_bracers_mythic",
            () -> new ReinforcedSteelBracersMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class ReinforcedSteelBracersRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public ReinforcedSteelBracersRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 4.0, REINFORCED_STEEL_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 1.25, REINFORCED_STEEL_UUID);
                AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 0.25, REINFORCED_STEEL_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, REINFORCED_STEEL_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "reinforced_steel_bracers");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), REINFORCED_STEEL_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR, REINFORCED_STEEL_UUID);
                AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, REINFORCED_STEEL_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), REINFORCED_STEEL_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Reinforced Steel Bracers").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1.25 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+0.25 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+4 Melee Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Miner's Fortune").withStyle(ChatFormatting.GRAY));
        }
    }

// --- Epic ---

    private static class ReinforcedSteelBracersEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public ReinforcedSteelBracersEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 7.5, REINFORCED_STEEL_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 2.0, REINFORCED_STEEL_UUID);
                AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 0.6, REINFORCED_STEEL_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, REINFORCED_STEEL_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "reinforced_steel_bracers");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), REINFORCED_STEEL_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR, REINFORCED_STEEL_UUID);
                AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, REINFORCED_STEEL_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), REINFORCED_STEEL_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Reinforced Steel Bracers").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+0.6 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+7.5 Melee Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Miner's Fortune").withStyle(ChatFormatting.GRAY));
        }
    }

// --- Legendary ---

    private static class ReinforcedSteelBracersLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public ReinforcedSteelBracersLegendary(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 12.0, REINFORCED_STEEL_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 3.5, REINFORCED_STEEL_UUID);
                AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 1.0, REINFORCED_STEEL_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.ACCURACY_MULTIPLIER.get(), 0.06, REINFORCED_STEEL_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, REINFORCED_STEEL_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "reinforced_steel_bracers");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), REINFORCED_STEEL_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR, REINFORCED_STEEL_UUID);
                AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, REINFORCED_STEEL_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.ACCURACY_MULTIPLIER.get(), REINFORCED_STEEL_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), REINFORCED_STEEL_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Reinforced Steel Bracers").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3.5 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+1 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+12 Melee Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+6% Accuracy").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Miner's Fortune").withStyle(ChatFormatting.GRAY));
        }
    }

// --- Mythic ---

    private static class ReinforcedSteelBracersMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public ReinforcedSteelBracersMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 16.0, REINFORCED_STEEL_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 3.5, REINFORCED_STEEL_UUID);
                AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 1.5, REINFORCED_STEEL_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.ACCURACY_MULTIPLIER.get(), 0.12, REINFORCED_STEEL_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, REINFORCED_STEEL_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "reinforced_steel_bracers");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), REINFORCED_STEEL_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR, REINFORCED_STEEL_UUID);
                AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, REINFORCED_STEEL_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.ACCURACY_MULTIPLIER.get(), REINFORCED_STEEL_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), REINFORCED_STEEL_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Reinforced Steel Bracers").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3.5 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+1.5 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+16 Melee Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+12% Accuracy").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Miner's Fortune").withStyle(ChatFormatting.GRAY));
        }
    }
}
