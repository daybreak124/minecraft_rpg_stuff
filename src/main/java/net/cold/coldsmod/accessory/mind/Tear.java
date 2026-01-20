package net.cold.coldsmod.accessory.mind;

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

public class Tear {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static final UUID TEAR_FORGOTTEN_UUID = UUID.fromString("f5a4e3d2-c1b0-4a9b-8c7d-6e5f4a3b2c1d");

// --- Registry ---

    public static final RegistryObject<Item> TEAR_OF_THE_FORGOTTEN_RARE = ITEMS.register(
            "tear_of_the_forgotten_rare",
            () -> new TearOfTheForgottenRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> TEAR_OF_THE_FORGOTTEN_EPIC = ITEMS.register(
            "tear_of_the_forgotten_epic",
            () -> new TearOfTheForgottenEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> TEAR_OF_THE_FORGOTTEN_LEGENDARY = ITEMS.register(
            "tear_of_the_forgotten_legendary",
            () -> new TearOfTheForgottenLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> TEAR_OF_THE_FORGOTTEN_MYTHIC = ITEMS.register(
            "tear_of_the_forgotten_mythic",
            () -> new TearOfTheForgottenMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class TearOfTheForgottenRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public TearOfTheForgottenRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, TEAR_FORGOTTEN_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 4.5, TEAR_FORGOTTEN_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 3.0, TEAR_FORGOTTEN_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "tear_of_the_forgotten");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), TEAR_FORGOTTEN_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, TEAR_FORGOTTEN_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), TEAR_FORGOTTEN_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Tear of the Forgotten").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+3 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4.5 Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Miner's Fortune").withStyle(ChatFormatting.GRAY));
        }
    }

// --- Epic ---

    private static class TearOfTheForgottenEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public TearOfTheForgottenEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, TEAR_FORGOTTEN_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 6.0, TEAR_FORGOTTEN_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 5.0, TEAR_FORGOTTEN_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "tear_of_the_forgotten");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), TEAR_FORGOTTEN_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, TEAR_FORGOTTEN_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), TEAR_FORGOTTEN_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Tear of the Forgotten").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+5 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+6 Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Miner's Fortune").withStyle(ChatFormatting.GRAY));
        }
    }

// --- Legendary ---

    private static class TearOfTheForgottenLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public TearOfTheForgottenLegendary(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, TEAR_FORGOTTEN_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 8.0, TEAR_FORGOTTEN_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 2.0, TEAR_FORGOTTEN_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 5.0, TEAR_FORGOTTEN_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), 0.05, TEAR_FORGOTTEN_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "tear_of_the_forgotten");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), TEAR_FORGOTTEN_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, TEAR_FORGOTTEN_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), TEAR_FORGOTTEN_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), TEAR_FORGOTTEN_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), TEAR_FORGOTTEN_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Tear of the Forgotten").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+5 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+8 Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+5% Toughness").withStyle(style -> style.withColor(0x0F52BA)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Miner's Fortune").withStyle(ChatFormatting.GRAY));
        }
    }

// --- Mythic ---

    private static class TearOfTheForgottenMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public TearOfTheForgottenMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, TEAR_FORGOTTEN_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 10.0, TEAR_FORGOTTEN_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 5.0, TEAR_FORGOTTEN_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 5.0, TEAR_FORGOTTEN_UUID);
                AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 4.0, TEAR_FORGOTTEN_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), 0.075, TEAR_FORGOTTEN_UUID);
                AttributeApplier.applyModifier(player, Attributes.KNOCKBACK_RESISTANCE, 0.15, TEAR_FORGOTTEN_UUID); // 15 stats = 0.15 attribute
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "tear_of_the_forgotten");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), TEAR_FORGOTTEN_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, TEAR_FORGOTTEN_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), TEAR_FORGOTTEN_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), TEAR_FORGOTTEN_UUID);
                AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, TEAR_FORGOTTEN_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), TEAR_FORGOTTEN_UUID);
                AttributeApplier.removeModifier(player, Attributes.KNOCKBACK_RESISTANCE, TEAR_FORGOTTEN_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Tear of the Forgotten").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+5 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+10 Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+4 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+15 Knockback Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+7.5% Toughness").withStyle(style -> style.withColor(0x0F52BA)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Miner's Fortune").withStyle(ChatFormatting.GRAY));
        }
    }

}
