package net.cold.coldsmod.accessory.bracers;

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
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import top.theillusivec4.curios.api.SlotContext;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class Enderman {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static final UUID ENDERMAN_ARM_UUID = UUID.fromString("6a3d9382-7e21-4f1a-b034-295473f32812");

// --- Registry ---

    public static final RegistryObject<Item> ENDERMANS_SEVERED_ARM_RARE = ITEMS.register(
            "endermans_severed_arm_rare",
            () -> new EndermansSeveredArmRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> ENDERMANS_SEVERED_ARM_EPIC = ITEMS.register(
            "endermans_severed_arm_epic",
            () -> new EndermansSeveredArmEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> ENDERMANS_SEVERED_ARM_LEGENDARY = ITEMS.register(
            "endermans_severed_arm_legendary",
            () -> new EndermansSeveredArmLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> ENDERMANS_SEVERED_ARM_MYTHIC = ITEMS.register(
            "endermans_severed_arm_mythic",
            () -> new EndermansSeveredArmMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class EndermansSeveredArmRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public EndermansSeveredArmRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 1.0, ENDERMAN_ARM_UUID);
                AttributeApplier.applyModifier(player, ForgeMod.SWIM_SPEED.get(), -25.0, ENDERMAN_ARM_UUID);
                AttributeApplier.applyModifier(player, ForgeMod.ENTITY_REACH.get(), 0.125, ENDERMAN_ARM_UUID);
                AttributeApplier.applyModifier(player, ForgeMod.BLOCK_REACH.get(), 0.5, ENDERMAN_ARM_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, ENDERMAN_ARM_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MINING_SPEED.get(), 3.0, ENDERMAN_ARM_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "endermans_severed_arm");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), ENDERMAN_ARM_UUID);
                AttributeApplier.removeModifier(player, ForgeMod.SWIM_SPEED.get(), ENDERMAN_ARM_UUID);
                AttributeApplier.removeModifier(player, ForgeMod.ENTITY_REACH.get(), ENDERMAN_ARM_UUID);
                AttributeApplier.removeModifier(player, ForgeMod.BLOCK_REACH.get(), ENDERMAN_ARM_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), ENDERMAN_ARM_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MINING_SPEED.get(), ENDERMAN_ARM_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Enderman's Severed Arm").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+1 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-25% Swim Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal("+0.5 Block Reach").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal("+0.125 Entity Reach").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal("+3% Mining Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Epic ---

    private static class EndermansSeveredArmEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public EndermansSeveredArmEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 4.0, ENDERMAN_ARM_UUID);
                AttributeApplier.applyModifier(player, ForgeMod.SWIM_SPEED.get(), -25.0, ENDERMAN_ARM_UUID);
                AttributeApplier.applyModifier(player, ForgeMod.ENTITY_REACH.get(), 0.25, ENDERMAN_ARM_UUID);
                AttributeApplier.applyModifier(player, ForgeMod.BLOCK_REACH.get(), 1.0, ENDERMAN_ARM_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, ENDERMAN_ARM_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MINING_SPEED.get(), 6.0, ENDERMAN_ARM_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "endermans_severed_arm");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), ENDERMAN_ARM_UUID);
                AttributeApplier.removeModifier(player, ForgeMod.SWIM_SPEED.get(), ENDERMAN_ARM_UUID);
                AttributeApplier.removeModifier(player, ForgeMod.ENTITY_REACH.get(), ENDERMAN_ARM_UUID);
                AttributeApplier.removeModifier(player, ForgeMod.BLOCK_REACH.get(), ENDERMAN_ARM_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), ENDERMAN_ARM_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MINING_SPEED.get(), ENDERMAN_ARM_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Enderman's Severed Arm").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+4 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-25% Swim Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal("+1 Block Reach").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal("+0.25 Entity Reach").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal("+6% Mining Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Legendary ---

    private static class EndermansSeveredArmLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public EndermansSeveredArmLegendary(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 8.0, ENDERMAN_ARM_UUID);
                AttributeApplier.applyModifier(player, ForgeMod.SWIM_SPEED.get(), -25.0, ENDERMAN_ARM_UUID);
                AttributeApplier.applyModifier(player, ForgeMod.ENTITY_REACH.get(), 0.375, ENDERMAN_ARM_UUID);
                AttributeApplier.applyModifier(player, ForgeMod.BLOCK_REACH.get(), 1.5, ENDERMAN_ARM_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 6.0, ENDERMAN_ARM_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MINING_SPEED.get(), 9.0, ENDERMAN_ARM_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "endermans_severed_arm");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), ENDERMAN_ARM_UUID);
                AttributeApplier.removeModifier(player, ForgeMod.SWIM_SPEED.get(), ENDERMAN_ARM_UUID);
                AttributeApplier.removeModifier(player, ForgeMod.ENTITY_REACH.get(), ENDERMAN_ARM_UUID);
                AttributeApplier.removeModifier(player, ForgeMod.BLOCK_REACH.get(), ENDERMAN_ARM_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), ENDERMAN_ARM_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MINING_SPEED.get(), ENDERMAN_ARM_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Enderman's Severed Arm").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+8 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+6 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-25% Swim Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal("+1.5 Block Reach").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal("+0.375 Entity Reach").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal("+9% Mining Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Mythic ---

    private static class EndermansSeveredArmMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public EndermansSeveredArmMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 10.0, ENDERMAN_ARM_UUID);
                AttributeApplier.applyModifier(player, ForgeMod.SWIM_SPEED.get(), -25.0, ENDERMAN_ARM_UUID);
                AttributeApplier.applyModifier(player, ForgeMod.ENTITY_REACH.get(), 0.5, ENDERMAN_ARM_UUID);
                AttributeApplier.applyModifier(player, ForgeMod.BLOCK_REACH.get(), 2.0, ENDERMAN_ARM_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 8.0, ENDERMAN_ARM_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MINING_SPEED.get(), 12.0, ENDERMAN_ARM_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "endermans_severed_arm");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), ENDERMAN_ARM_UUID);
                AttributeApplier.removeModifier(player, ForgeMod.SWIM_SPEED.get(), ENDERMAN_ARM_UUID);
                AttributeApplier.removeModifier(player, ForgeMod.ENTITY_REACH.get(), ENDERMAN_ARM_UUID);
                AttributeApplier.removeModifier(player, ForgeMod.BLOCK_REACH.get(), ENDERMAN_ARM_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), ENDERMAN_ARM_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MINING_SPEED.get(), ENDERMAN_ARM_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Enderman's Severed Arm").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+10 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+8 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-25% Swim Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal("+2 Block Reach").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal("+0.5 Entity Reach").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal("+12% Mining Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

}
