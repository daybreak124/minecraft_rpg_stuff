package net.cold.coldsmod.accessory.ring;

import net.cold.coldsmod.ColdsMod;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
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

public class GluttonySignet {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static final UUID GLUTTONY_UUID = UUID.fromString("e7d6c5b4-a3f2-4109-8d7c-6e5b4a3f2d1c");

// --- Registry ---

    public static final RegistryObject<Item> GLUTTONY_SIGNET_RARE = ITEMS.register(
            "gluttony_signet_rare",
            () -> new GluttonySignetRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> GLUTTONY_SIGNET_EPIC = ITEMS.register(
            "gluttony_signet_epic",
            () -> new GluttonySignetEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> GLUTTONY_SIGNET_LEGENDARY = ITEMS.register(
            "gluttony_signet_legendary",
            () -> new GluttonySignetLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> GLUTTONY_SIGNET_MYTHIC = ITEMS.register(
            "gluttony_signet_mythic",
            () -> new GluttonySignetMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class GluttonySignetRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public GluttonySignetRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 1.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 1.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, Attributes.MOVEMENT_SPEED, -0.015, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), -20.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 1.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 1.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 1.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 1, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.POTENCY.get(), 2.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 2.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 2.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.REJUVENATION.get(), 2.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), -2.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 2, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 2.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 1.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 1.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 1.0, GLUTTONY_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "gluttony_signet");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, Attributes.MOVEMENT_SPEED, GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR, GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.POTENCY.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.REJUVENATION.get(), GLUTTONY_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Gluttony Signet").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+1 Attributes").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+1 Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+1 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("-20% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+2 Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+2 Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+2 Nock Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("-2 Restoration").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal("+2 Rejuvenation").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal("-15% Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));

        }
    }

// --- Epic ---

    private static class GluttonySignetEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public GluttonySignetEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 2.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 2.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, Attributes.MOVEMENT_SPEED, -0.015, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), -20.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 2.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 1.5, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 1.5, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 1.5, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.POTENCY.get(), 2.5, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 2.5, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 2.5, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 2.5, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.REJUVENATION.get(), 2.5, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), -2.5, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 2.5, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 2.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 2.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 2.0, GLUTTONY_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "gluttony_signet");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, Attributes.MOVEMENT_SPEED, GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR, GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.POTENCY.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.REJUVENATION.get(), GLUTTONY_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Gluttony Signet").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+2 Attributes").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1.5 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+1.5 Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+1.5 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("-20% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+2.5 Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+2.5 Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+2.5 Nock Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("-2.5 Restoration").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal("+2.5 Rejuvenation").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal("-15% Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Legendary ---

    private static class GluttonySignetLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public GluttonySignetLegendary(Properties properties) {
            super(properties);
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 3.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 3.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, Attributes.MOVEMENT_SPEED, -0.015, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), -20.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 3.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 2.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 2.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 2.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 3.25, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 3.25, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.POTENCY.get(), 3.25, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 3.25, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 3.25, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 3.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 3.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 3.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), 0.02, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), 0.02, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), 0.03, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.POTENCY_MULTIPLIER.get(), 0.03, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), 0.03, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), 0.03, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), 0.03, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.REJUVENATION.get(), 3.25, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), -3.25, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.REJUVENATION_MULTIPLIER.get(), 0.03, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.RESTORATION_MULTIPLIER.get(), -0.03, GLUTTONY_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "gluttony_signet");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, Attributes.MOVEMENT_SPEED, GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR, GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.POTENCY.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.POTENCY_MULTIPLIER.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.REJUVENATION.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.REJUVENATION_MULTIPLIER.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.RESTORATION_MULTIPLIER.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), GLUTTONY_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Gluttony Signet").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+3 Attributes").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+2 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+2 Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+2 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("-20% Debuff Resist").withStyle(ChatFormatting.BLUE));

            tooltip.add(Component.literal("+2% Armor").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0F52BA))));
            tooltip.add(Component.literal("+2% Toughness").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0F52BA))));

            tooltip.add(Component.literal("+3.25 Potency").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
            tooltip.add(Component.literal("+3.25 Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
            tooltip.add(Component.literal("+3.25 Nock Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));

            tooltip.add(Component.literal("+3% Potency").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));
            tooltip.add(Component.literal("+3% Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));
            tooltip.add(Component.literal("+3% Nock Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));

            tooltip.add(Component.literal("-3.25 Restoration").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal("+3.25 Rejuvenation").withStyle(style -> style.withColor(0x5BB450)));

            tooltip.add(Component.literal("-3% Restoration").withStyle(style -> style.withColor(0x3B8132)));
            tooltip.add(Component.literal("+3% Rejuvenation").withStyle(style -> style.withColor(0x3B8132)));


            tooltip.add(Component.literal("-15% Speed").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xD6C97A))));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Mythic ---

    private static class GluttonySignetMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public GluttonySignetMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 4.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 4.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, Attributes.MOVEMENT_SPEED, -0.015, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), -20.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 4.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 2.5, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 2.5, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 2.5, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.POTENCY.get(), 4.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 4.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 4.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 4, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 4.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 4.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 4.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 4.0, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), 0.04, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), 0.04, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), 0.06, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.POTENCY_MULTIPLIER.get(), 0.06, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), 0.06, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), 0.06, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), 0.06, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.REJUVENATION.get(), 4, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), -4, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.REJUVENATION_MULTIPLIER.get(), 0.04, GLUTTONY_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.RESTORATION_MULTIPLIER.get(), -0.04, GLUTTONY_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "gluttony_signet");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, Attributes.MOVEMENT_SPEED, GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR, GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.POTENCY.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.POTENCY_MULTIPLIER.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.REJUVENATION.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.REJUVENATION_MULTIPLIER.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.RESTORATION_MULTIPLIER.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), GLUTTONY_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), GLUTTONY_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Gluttony Signet").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+4 Attributes").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+2.5 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+2.5 Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+2.5 Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("-20% Debuff Resist").withStyle(ChatFormatting.BLUE));

            tooltip.add(Component.literal("+4% Armor").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0F52BA))));
            tooltip.add(Component.literal("+4% Toughness").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0F52BA))));

            tooltip.add(Component.literal("+4 Potency").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
            tooltip.add(Component.literal("+4 Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));
            tooltip.add(Component.literal("+4 Nock Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xE0701B))));

            tooltip.add(Component.literal("+6% Potency").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));
            tooltip.add(Component.literal("+6% Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));
            tooltip.add(Component.literal("+6% Nock Haste").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xec3700))));

            tooltip.add(Component.literal("-4 Restoration").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal("+4 Rejuvenation").withStyle(style -> style.withColor(0x5BB450)));

            tooltip.add(Component.literal("-4% Restoration").withStyle(style -> style.withColor(0x3B8132)));
            tooltip.add(Component.literal("+4% Rejuvenation").withStyle(style -> style.withColor(0x3B8132)));

            tooltip.add(Component.literal("-15% Speed").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xD6C97A))));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }
}