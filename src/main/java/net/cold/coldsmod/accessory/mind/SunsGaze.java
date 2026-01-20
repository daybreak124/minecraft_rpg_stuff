package net.cold.coldsmod.accessory.mind;

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

public class SunsGaze {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static final UUID SUNS_GAZE_UUID = UUID.fromString("d5e6f7a8-b9c0-4d1e-2f3a-4b5c6d7e8f9a");

// --- Registry ---

    public static final RegistryObject<Item> SUNS_GAZE_RARE = ITEMS.register(
            "suns_gaze_rare",
            () -> new SunsGazeRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> SUNS_GAZE_EPIC = ITEMS.register(
            "suns_gaze_epic",
            () -> new SunsGazeEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> SUNS_GAZE_LEGENDARY = ITEMS.register(
            "suns_gaze_legendary",
            () -> new SunsGazeLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> SUNS_GAZE_MYTHIC = ITEMS.register(
            "suns_gaze_mythic",
            () -> new SunsGazeMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class SunsGazeRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public SunsGazeRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 1.0, SUNS_GAZE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, SUNS_GAZE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 6.0, SUNS_GAZE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 4.0, SUNS_GAZE_UUID);
                AttributeApplier.applyModifier(player, ForgeMod.SWIM_SPEED.get(), -0.05, SUNS_GAZE_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "suns_gaze");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), SUNS_GAZE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SUNS_GAZE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), SUNS_GAZE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), SUNS_GAZE_UUID);
                AttributeApplier.removeModifier(player, ForgeMod.SWIM_SPEED.get(), SUNS_GAZE_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Sun's Gaze").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+1 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+6 Melee Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+4 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("-5% Swim Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Sun's Cruelty").withStyle(ChatFormatting.YELLOW));
        }
    }

// --- Epic ---

    private static class SunsGazeEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public SunsGazeEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 3.0, SUNS_GAZE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, SUNS_GAZE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 9.0, SUNS_GAZE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 6.0, SUNS_GAZE_UUID);
                AttributeApplier.applyModifier(player, ForgeMod.SWIM_SPEED.get(), -0.10, SUNS_GAZE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 5.0, SUNS_GAZE_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "suns_gaze");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), SUNS_GAZE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SUNS_GAZE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), SUNS_GAZE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), SUNS_GAZE_UUID);
                AttributeApplier.removeModifier(player, ForgeMod.SWIM_SPEED.get(), SUNS_GAZE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), SUNS_GAZE_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Sun's Gaze").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+3 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+9 Melee Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+6 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("-10% Swim Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Sun's Cruelty").withStyle(ChatFormatting.YELLOW));
        }
    }

// --- Legendary ---

    private static class SunsGazeLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public SunsGazeLegendary(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 6.0, SUNS_GAZE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, SUNS_GAZE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 12.0, SUNS_GAZE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 9.0, SUNS_GAZE_UUID);
                AttributeApplier.applyModifier(player, ForgeMod.SWIM_SPEED.get(), -0.15, SUNS_GAZE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), 0.025, SUNS_GAZE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 10.0, SUNS_GAZE_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "suns_gaze");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), SUNS_GAZE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SUNS_GAZE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), SUNS_GAZE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), SUNS_GAZE_UUID);
                AttributeApplier.removeModifier(player, ForgeMod.SWIM_SPEED.get(), SUNS_GAZE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), SUNS_GAZE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), SUNS_GAZE_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Sun's Gaze").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+6 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+10% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+12 Melee Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+9 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+2.5% Melee Potency").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal("-15% Swim Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Sun's Cruelty").withStyle(ChatFormatting.YELLOW));
        }
    }

// --- Mythic ---

    private static class SunsGazeMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public SunsGazeMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 9.0, SUNS_GAZE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, SUNS_GAZE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 16.0, SUNS_GAZE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 9.0, SUNS_GAZE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), 0.125, SUNS_GAZE_UUID);
                AttributeApplier.applyModifier(player, ForgeMod.SWIM_SPEED.get(), -0.20, SUNS_GAZE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 15.0, SUNS_GAZE_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "suns_gaze");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), SUNS_GAZE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SUNS_GAZE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), SUNS_GAZE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), SUNS_GAZE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), SUNS_GAZE_UUID);
                AttributeApplier.removeModifier(player, ForgeMod.SWIM_SPEED.get(), SUNS_GAZE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), SUNS_GAZE_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Sun's Gaze").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+9 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+15% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+16 Melee Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+9 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+12.5% Melee Potency").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal("-20% Swim Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Sun's Cruelty").withStyle(ChatFormatting.YELLOW));
        }
    }
}
