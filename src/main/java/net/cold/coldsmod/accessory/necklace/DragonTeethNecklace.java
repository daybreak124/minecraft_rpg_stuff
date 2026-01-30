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

public class DragonTeethNecklace {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static final UUID DRAGON_TEETH_UUID = UUID.fromString("e5d4c3b2-a1f0-4b9c-8d7e-6f5a4b3c2d1e");

// --- Registry ---

    public static final RegistryObject<Item> DRAGON_TEETH_NECKLACE_RARE = ITEMS.register(
            "dragon_teeth_necklace_rare",
            () -> new DragonTeethNecklaceRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> DRAGON_TEETH_NECKLACE_EPIC = ITEMS.register(
            "dragon_teeth_necklace_epic",
            () -> new DragonTeethNecklaceEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> DRAGON_TEETH_NECKLACE_LEGENDARY = ITEMS.register(
            "dragon_teeth_necklace_legendary",
            () -> new DragonTeethNecklaceLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> DRAGON_TEETH_NECKLACE_MYTHIC = ITEMS.register(
            "dragon_teeth_necklace_mythic",
            () -> new DragonTeethNecklaceMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class DragonTeethNecklaceRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public DragonTeethNecklaceRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, DRAGON_TEETH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 2.0, DRAGON_TEETH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.ACCURACY.get(), 6.0, DRAGON_TEETH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_ACCURACY.get(), 6.0, DRAGON_TEETH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), 6.0, DRAGON_TEETH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 2.0, DRAGON_TEETH_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "dragon_teeth_necklace");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGON_TEETH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGON_TEETH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.ACCURACY.get(), DRAGON_TEETH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), DRAGON_TEETH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_ACCURACY.get(), DRAGON_TEETH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), DRAGON_TEETH_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon Teeth Necklace").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+2 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+6 Accuracy").withStyle(style -> style.withColor(0xE0701B)));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Epic ---

    private static class DragonTeethNecklaceEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public DragonTeethNecklaceEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, DRAGON_TEETH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 3.0, DRAGON_TEETH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.ACCURACY.get(), 12.0, DRAGON_TEETH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_ACCURACY.get(), 12.0, DRAGON_TEETH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), 12.0, DRAGON_TEETH_UUID);
                AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 0.5, DRAGON_TEETH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 3.0, DRAGON_TEETH_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "dragon_teeth_necklace");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGON_TEETH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGON_TEETH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.ACCURACY.get(), DRAGON_TEETH_UUID);
                AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, DRAGON_TEETH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), DRAGON_TEETH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_ACCURACY.get(), DRAGON_TEETH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), DRAGON_TEETH_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon Teeth Necklace").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+3 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+0.5 Max Health").withStyle(ChatFormatting.BLUE));


            tooltip.add(Component.literal("+12 Accuracy").withStyle(style -> style.withColor(0xE0701B)));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Legendary ---

    private static class DragonTeethNecklaceLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public DragonTeethNecklaceLegendary(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, DRAGON_TEETH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 6.0, DRAGON_TEETH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.ACCURACY.get(), 15.0, DRAGON_TEETH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_ACCURACY.get(), 15.0, DRAGON_TEETH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), 15.0, DRAGON_TEETH_UUID);
                AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 1.0, DRAGON_TEETH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 6.0, DRAGON_TEETH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PRECISION_MULTIPLIER.get(), 0.04, DRAGON_TEETH_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "dragon_teeth_necklace");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGON_TEETH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGON_TEETH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.ACCURACY.get(), DRAGON_TEETH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_ACCURACY.get(), DRAGON_TEETH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), DRAGON_TEETH_UUID);
                AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, DRAGON_TEETH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), DRAGON_TEETH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PRECISION_MULTIPLIER.get(), DRAGON_TEETH_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon Teeth Necklace").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+6 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+6 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+1 Max Health").withStyle(ChatFormatting.BLUE));

            tooltip.add(Component.literal("+15 Accuracy").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+4% Precision").withStyle(style -> style.withColor(0xec3700)));


            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Mythic ---

    private static class DragonTeethNecklaceMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public DragonTeethNecklaceMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, DRAGON_TEETH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 8.0, DRAGON_TEETH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.ACCURACY.get(), 18.0, DRAGON_TEETH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_ACCURACY.get(), 18.0, DRAGON_TEETH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), 18.0, DRAGON_TEETH_UUID);
                AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 1.5, DRAGON_TEETH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 8.0, DRAGON_TEETH_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PRECISION_MULTIPLIER.get(), 0.08, DRAGON_TEETH_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "dragon_teeth_necklace");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGON_TEETH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGON_TEETH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.ACCURACY.get(), DRAGON_TEETH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_ACCURACY.get(), DRAGON_TEETH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), DRAGON_TEETH_UUID);
                AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, DRAGON_TEETH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), DRAGON_TEETH_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PRECISION_MULTIPLIER.get(), DRAGON_TEETH_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon Teeth Necklace").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+8 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+8 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+1.5 Max Health").withStyle(ChatFormatting.BLUE));

            tooltip.add(Component.literal("+18 Accuracy").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+8% Precision").withStyle(style -> style.withColor(0xec3700)));


            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }
}
