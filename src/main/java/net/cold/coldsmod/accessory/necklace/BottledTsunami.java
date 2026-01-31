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
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import top.theillusivec4.curios.api.SlotContext;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class BottledTsunami {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static final UUID BOTTLED_TSUNAMI_UUID = UUID.fromString("a1b2c3d4-e5f6-4a5b-bc6d-7e8f9a0b1c2d");

// --- Registry ---

    public static final RegistryObject<Item> BOTTLED_TSUNAMI_SEA_RARE = ITEMS.register(
            "bottled_tsunami_sea_rare",
            () -> new BottledTsunamiSeaRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> BOTTLED_TSUNAMI_SEA_EPIC = ITEMS.register(
            "bottled_tsunami_sea_epic",
            () -> new BottledTsunamiSeaEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> BOTTLED_TSUNAMI_SEA_LEGENDARY = ITEMS.register(
            "bottled_tsunami_sea_legendary",
            () -> new BottledTsunamiSeaLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> BOTTLED_TSUNAMI_SEA_MYTHIC = ITEMS.register(
            "bottled_tsunami_sea_mythic",
            () -> new BottledTsunamiSeaMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class BottledTsunamiSeaRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public BottledTsunamiSeaRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, BOTTLED_TSUNAMI_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 6.0, BOTTLED_TSUNAMI_UUID);
                AttributeApplier.applyModifier(player, ForgeMod.SWIM_SPEED.get(), 0.10, BOTTLED_TSUNAMI_UUID);
                AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 0.75, BOTTLED_TSUNAMI_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "bottled_tsunami_sea");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), BOTTLED_TSUNAMI_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), BOTTLED_TSUNAMI_UUID);
                AttributeApplier.removeModifier(player, ForgeMod.SWIM_SPEED.get(), BOTTLED_TSUNAMI_UUID);
                AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, BOTTLED_TSUNAMI_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Bottled Tsunami Sea").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+6 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+0.75 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+10% Swim Speed").withStyle(style -> style.withColor(0x00AEEF)));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Tsunami Sea").withStyle(ChatFormatting.AQUA));
        }
    }

// --- Epic ---

    private static class BottledTsunamiSeaEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public BottledTsunamiSeaEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, BOTTLED_TSUNAMI_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 9.0, BOTTLED_TSUNAMI_UUID);
                AttributeApplier.applyModifier(player, ForgeMod.SWIM_SPEED.get(), 0.20, BOTTLED_TSUNAMI_UUID);
                AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 2.0, BOTTLED_TSUNAMI_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "bottled_tsunami_sea");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), BOTTLED_TSUNAMI_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), BOTTLED_TSUNAMI_UUID);
                AttributeApplier.removeModifier(player, ForgeMod.SWIM_SPEED.get(), BOTTLED_TSUNAMI_UUID);
                AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, BOTTLED_TSUNAMI_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Bottled Tsunami Sea").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+9 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+2 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+20% Swim Speed").withStyle(style -> style.withColor(0x00AEEF)));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Tsunami Sea").withStyle(ChatFormatting.AQUA));
        }
    }

// --- Legendary ---

    private static class BottledTsunamiSeaLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public BottledTsunamiSeaLegendary(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, BOTTLED_TSUNAMI_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 13.0, BOTTLED_TSUNAMI_UUID);
                AttributeApplier.applyModifier(player, ForgeMod.SWIM_SPEED.get(), 0.30, BOTTLED_TSUNAMI_UUID);
                AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 3.5, BOTTLED_TSUNAMI_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "bottled_tsunami_sea");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), BOTTLED_TSUNAMI_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), BOTTLED_TSUNAMI_UUID);
                AttributeApplier.removeModifier(player, ForgeMod.SWIM_SPEED.get(), BOTTLED_TSUNAMI_UUID);
                AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, BOTTLED_TSUNAMI_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Bottled Tsunami Sea").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+13 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+3.5 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+30% Swim Speed").withStyle(style -> style.withColor(0x00AEEF)));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Tsunami Sea").withStyle(ChatFormatting.AQUA));
        }
    }

// --- Mythic ---

    private static class BottledTsunamiSeaMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public BottledTsunamiSeaMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, BOTTLED_TSUNAMI_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 17.0, BOTTLED_TSUNAMI_UUID);
                AttributeApplier.applyModifier(player, ForgeMod.SWIM_SPEED.get(), 0.40, BOTTLED_TSUNAMI_UUID);
                AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 5.0, BOTTLED_TSUNAMI_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "bottled_tsunami_sea");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), BOTTLED_TSUNAMI_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), BOTTLED_TSUNAMI_UUID);
                AttributeApplier.removeModifier(player, ForgeMod.SWIM_SPEED.get(), BOTTLED_TSUNAMI_UUID);
                AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, BOTTLED_TSUNAMI_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Bottled Tsunami Sea").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+17 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal("+5 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+40% Swim Speed").withStyle(style -> style.withColor(0x00AEEF)));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Tsunami Sea").withStyle(ChatFormatting.AQUA));
        }
    }

}
