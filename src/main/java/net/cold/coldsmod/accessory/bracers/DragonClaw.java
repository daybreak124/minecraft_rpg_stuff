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
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import top.theillusivec4.curios.api.SlotContext;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class DragonClaw {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static final UUID DRAGON_CLAW_UUID = UUID.fromString("b72a4e1d-8c3b-4f92-a106-928475f32104");

// --- Registry ---

    public static final RegistryObject<Item> DRAGON_CLAW_GLOVES_RARE = ITEMS.register(
            "dragon_claw_gloves_rare",
            () -> new DragonClawGlovesRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> DRAGON_CLAW_GLOVES_EPIC = ITEMS.register(
            "dragon_claw_gloves_epic",
            () -> new DragonClawGlovesEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> DRAGON_CLAW_GLOVES_LEGENDARY = ITEMS.register(
            "dragon_claw_gloves_legendary",
            () -> new DragonClawGlovesLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> DRAGON_CLAW_GLOVES_MYTHIC = ITEMS.register(
            "dragon_claw_gloves_mythic",
            () -> new DragonClawGlovesMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class DragonClawGlovesRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public DragonClawGlovesRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 3.0, DRAGON_CLAW_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.ACCURACY.get(), 6.0, DRAGON_CLAW_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_ACCURACY.get(), 6.0, DRAGON_CLAW_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), 6.0, DRAGON_CLAW_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, DRAGON_CLAW_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "dragon_claw_gloves");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGON_CLAW_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.ACCURACY.get(), DRAGON_CLAW_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_ACCURACY.get(), DRAGON_CLAW_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), DRAGON_CLAW_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGON_CLAW_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon Claw Gloves").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+3 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+6 Accuracy").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Epic ---

    private static class DragonClawGlovesEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public DragonClawGlovesEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 5.0, DRAGON_CLAW_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.ACCURACY.get(), 13.5, DRAGON_CLAW_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_ACCURACY.get(), 13.5, DRAGON_CLAW_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), 13.5, DRAGON_CLAW_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, DRAGON_CLAW_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "dragon_claw_gloves");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGON_CLAW_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.ACCURACY.get(), DRAGON_CLAW_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGON_CLAW_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_ACCURACY.get(), DRAGON_CLAW_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), DRAGON_CLAW_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon Claw Gloves").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+5 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+13.5 Accuracy").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Legendary ---

    private static class DragonClawGlovesLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public DragonClawGlovesLegendary(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 7.0, DRAGON_CLAW_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.ACCURACY.get(), 18.0, DRAGON_CLAW_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), 0.075, DRAGON_CLAW_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, DRAGON_CLAW_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_ACCURACY.get(), 18.0, DRAGON_CLAW_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), 18.0, DRAGON_CLAW_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "dragon_claw_gloves");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGON_CLAW_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.ACCURACY.get(), DRAGON_CLAW_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), DRAGON_CLAW_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGON_CLAW_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_ACCURACY.get(), DRAGON_CLAW_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), DRAGON_CLAW_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon Claw Gloves").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+7 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+7.5% Armor Toughness").withStyle(style -> style.withColor(0x0F52BA)));
            tooltip.add(Component.literal("+18 Accuracy").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Mythic ---

    private static class DragonClawGlovesMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public DragonClawGlovesMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 9.0, DRAGON_CLAW_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.ACCURACY.get(), 24.0, DRAGON_CLAW_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), 0.15, DRAGON_CLAW_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, DRAGON_CLAW_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_ACCURACY.get(), 24.0, DRAGON_CLAW_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), 24.0, DRAGON_CLAW_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "dragon_claw_gloves");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGON_CLAW_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.ACCURACY.get(), DRAGON_CLAW_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), DRAGON_CLAW_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGON_CLAW_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_ACCURACY.get(), DRAGON_CLAW_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), DRAGON_CLAW_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon Claw Gloves").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+9 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+15% Armor Toughness").withStyle(style -> style.withColor(0x0F52BA)));
            tooltip.add(Component.literal("+24 Accuracy").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }
}
