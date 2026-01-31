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

public class WardenSkin {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static final UUID WARDEN_BRACERS_UUID = UUID.fromString("e32b4f91-7d1c-4b32-9015-38475f32a106");

// --- Registry ---

    public static final RegistryObject<Item> WARDEN_SKIN_FORGED_BRACERS_RARE = ITEMS.register(
            "warden_skin_forged_bracers_rare",
            () -> new WardenSkinBracersRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> WARDEN_SKIN_FORGED_BRACERS_EPIC = ITEMS.register(
            "warden_skin_forged_bracers_epic",
            () -> new WardenSkinBracersEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> WARDEN_SKIN_FORGED_BRACERS_LEGENDARY = ITEMS.register(
            "warden_skin_forged_bracers_legendary",
            () -> new WardenSkinBracersLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> WARDEN_SKIN_FORGED_BRACERS_MYTHIC = ITEMS.register(
            "warden_skin_forged_bracers_mythic",
            () -> new WardenSkinBracersMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class WardenSkinBracersRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public WardenSkinBracersRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 3.0, WARDEN_BRACERS_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 1.5, WARDEN_BRACERS_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 1.5, WARDEN_BRACERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, WARDEN_BRACERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 1.0, WARDEN_BRACERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 6.0, WARDEN_BRACERS_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "warden_skin_forged_bracers");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), WARDEN_BRACERS_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR, WARDEN_BRACERS_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, WARDEN_BRACERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), WARDEN_BRACERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), WARDEN_BRACERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), WARDEN_BRACERS_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Warden Skin Forged Bracers").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+3 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1.5 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+1.5 Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+6% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Otherworlds").withStyle(ChatFormatting.DARK_AQUA));
        }
    }

// --- Epic ---

    private static class WardenSkinBracersEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public WardenSkinBracersEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 4.0, WARDEN_BRACERS_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 3.0, WARDEN_BRACERS_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 2.5, WARDEN_BRACERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, WARDEN_BRACERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 2.0, WARDEN_BRACERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 8.0, WARDEN_BRACERS_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "warden_skin_forged_bracers");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), WARDEN_BRACERS_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR, WARDEN_BRACERS_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, WARDEN_BRACERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), WARDEN_BRACERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), WARDEN_BRACERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), WARDEN_BRACERS_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Warden Skin Forged Bracers").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+4 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+2.5 Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+8% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Otherworlds").withStyle(ChatFormatting.DARK_AQUA));
        }
    }

// --- Legendary ---

    private static class WardenSkinBracersLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public WardenSkinBracersLegendary(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 5.0, WARDEN_BRACERS_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 4.0, WARDEN_BRACERS_UUID);
                AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 1.0, WARDEN_BRACERS_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 4.0, WARDEN_BRACERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), 0.10, WARDEN_BRACERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, WARDEN_BRACERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 5.0, WARDEN_BRACERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 12.0, WARDEN_BRACERS_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "warden_skin_forged_bracers");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), WARDEN_BRACERS_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR, WARDEN_BRACERS_UUID);
                AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, WARDEN_BRACERS_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, WARDEN_BRACERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), WARDEN_BRACERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), WARDEN_BRACERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), WARDEN_BRACERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), WARDEN_BRACERS_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Warden Skin Forged Bracers").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+5 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+4 Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+1 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+12% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+10% Toughness").withStyle(style -> style.withColor(0x0F52BA)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Otherworlds").withStyle(ChatFormatting.DARK_AQUA));
        }
    }

// --- Mythic ---

    private static class WardenSkinBracersMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public WardenSkinBracersMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 9.0, WARDEN_BRACERS_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 5.0, WARDEN_BRACERS_UUID);
                AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 2.0, WARDEN_BRACERS_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 5.0, WARDEN_BRACERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), 0.15, WARDEN_BRACERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, WARDEN_BRACERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 8.0, WARDEN_BRACERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 15.0, WARDEN_BRACERS_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "warden_skin_forged_bracers");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), WARDEN_BRACERS_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR, WARDEN_BRACERS_UUID);
                AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, WARDEN_BRACERS_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, WARDEN_BRACERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), WARDEN_BRACERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), WARDEN_BRACERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), WARDEN_BRACERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), WARDEN_BRACERS_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Warden Skin Forged Bracers").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+9 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+8 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+5 Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+2 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+15% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+15% Toughness").withStyle(style -> style.withColor(0x0F52BA)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Otherworlds").withStyle(ChatFormatting.DARK_AQUA));
        }
    }


}
