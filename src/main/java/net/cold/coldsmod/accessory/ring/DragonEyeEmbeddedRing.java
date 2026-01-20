package net.cold.coldsmod.accessory.ring;


import net.cold.coldsmod.ColdsMod;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
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

public class DragonEyeEmbeddedRing {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static final UUID DRAGON_EYE_UUID = UUID.fromString("d7e8f9a0-b1c2-4d3e-5f6a-7b8c9d0e1f2a");

// --- Registry ---

    public static final RegistryObject<Item> DRAGON_EYE_EMBEDDED_RING_RARE = ITEMS.register(
            "dragon_eye_embedded_ring_rare",
            () -> new DragonEyeRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> DRAGON_EYE_EMBEDDED_RING_EPIC = ITEMS.register(
            "dragon_eye_embedded_ring_epic",
            () -> new DragonEyeEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> DRAGON_EYE_EMBEDDED_RING_LEGENDARY = ITEMS.register(
            "dragon_eye_embedded_ring_legendary",
            () -> new DragonEyeLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> DRAGON_EYE_EMBEDDED_RING_MYTHIC = ITEMS.register(
            "dragon_eye_embedded_ring_mythic",
            () -> new DragonEyeMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class DragonEyeRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public DragonEyeRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, DRAGON_EYE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 4.0, DRAGON_EYE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.POTENCY.get(), 12.0, DRAGON_EYE_UUID); // 7 Melee + 5 Projectile
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "dragon_eye_embedded_ring");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGON_EYE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGON_EYE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.POTENCY.get(), DRAGON_EYE_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon Eye Embedded Ring").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+4 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+7 Melee Potency").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
            tooltip.add(Component.literal("+5 Projectile Potency").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Epic ---

    private static class DragonEyeEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public DragonEyeEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, DRAGON_EYE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 6.0, DRAGON_EYE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.POTENCY.get(), 19.5, DRAGON_EYE_UUID); // 12 + 7.5
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "dragon_eye_embedded_ring");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGON_EYE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGON_EYE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.POTENCY.get(), DRAGON_EYE_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon Eye Embedded Ring").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+6 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+12 Melee Potency").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
            tooltip.add(Component.literal("+7.5 Projectile Potency").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Legendary ---

    private static class DragonEyeLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public DragonEyeLegendary(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, DRAGON_EYE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 8.0, DRAGON_EYE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.POTENCY.get(), 25.0, DRAGON_EYE_UUID); // 16 + 9
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 4.0, DRAGON_EYE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.POTENCY_MULTIPLIER.get(), 0.0625, DRAGON_EYE_UUID); // 0.04 + 0.0225
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "dragon_eye_embedded_ring");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGON_EYE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGON_EYE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.POTENCY.get(), DRAGON_EYE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), DRAGON_EYE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.POTENCY_MULTIPLIER.get(), DRAGON_EYE_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon Eye Embedded Ring").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+8 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+16 Melee Potency").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
            tooltip.add(Component.literal("+9 Projectile Potency").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));

            tooltip.add(Component.literal("+4% Melee Potency").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));
            tooltip.add(Component.literal("+2.25% Projectile Potency").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Mythic ---

    private static class DragonEyeMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public DragonEyeMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, DRAGON_EYE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 9.0, DRAGON_EYE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.POTENCY.get(), 32.5, DRAGON_EYE_UUID); // 20 + 12.5
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 6.0, DRAGON_EYE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.POTENCY_MULTIPLIER.get(), 0.125, DRAGON_EYE_UUID); // 0.08 + 0.045
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "dragon_eye_embedded_ring");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGON_EYE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGON_EYE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.POTENCY.get(), DRAGON_EYE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), DRAGON_EYE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.POTENCY_MULTIPLIER.get(), DRAGON_EYE_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon Eye Embedded Ring").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+9 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+6 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+20 Melee Potency").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
            tooltip.add(Component.literal("+12.5 Projectile Potency").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));

            tooltip.add(Component.literal("+8% Melee Potency").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));
            tooltip.add(Component.literal("+4.5% Projectile Potency").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }
}
