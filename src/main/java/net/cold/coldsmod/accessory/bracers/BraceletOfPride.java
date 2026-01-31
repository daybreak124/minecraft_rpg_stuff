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

public class BraceletOfPride {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static final UUID BRACELET_PRIDE_UUID = UUID.fromString("a1b2c3d4-e5f6-4a5b-8c9d-0e1f2a3b4c5d");

// --- Registry ---

    public static final RegistryObject<Item> BRACELET_OF_PRIDE_RARE = ITEMS.register(
            "bracelet_of_pride_rare",
            () -> new BraceletOfPrideRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> BRACELET_OF_PRIDE_EPIC = ITEMS.register(
            "bracelet_of_pride_epic",
            () -> new BraceletOfPrideEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> BRACELET_OF_PRIDE_LEGENDARY = ITEMS.register(
            "bracelet_of_pride_legendary",
            () -> new BraceletOfPrideLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> BRACELET_OF_PRIDE_MYTHIC = ITEMS.register(
            "bracelet_of_pride_mythic",
            () -> new BraceletOfPrideMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class BraceletOfPrideRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public BraceletOfPrideRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 4.0, BRACELET_PRIDE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 4.0, BRACELET_PRIDE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 4.0, BRACELET_PRIDE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, BRACELET_PRIDE_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "bracelet_of_pride");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), BRACELET_PRIDE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), BRACELET_PRIDE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), BRACELET_PRIDE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), BRACELET_PRIDE_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Bracelet of Pride").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+4 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Epic ---

    private static class BraceletOfPrideEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public BraceletOfPrideEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 8.0, BRACELET_PRIDE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 4.0, BRACELET_PRIDE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 4.0, BRACELET_PRIDE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, BRACELET_PRIDE_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "bracelet_of_pride");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), BRACELET_PRIDE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), BRACELET_PRIDE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), BRACELET_PRIDE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), BRACELET_PRIDE_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Bracelet of Pride").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+8 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Legendary ---

    private static class BraceletOfPrideLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public BraceletOfPrideLegendary(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 8.0, BRACELET_PRIDE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 5.0, BRACELET_PRIDE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 5.0, BRACELET_PRIDE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 4.0, BRACELET_PRIDE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, BRACELET_PRIDE_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "bracelet_of_pride");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), BRACELET_PRIDE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), BRACELET_PRIDE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), BRACELET_PRIDE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), BRACELET_PRIDE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), BRACELET_PRIDE_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Bracelet of Pride").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+8 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Mythic ---

    private static class BraceletOfPrideMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public BraceletOfPrideMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 10.0, BRACELET_PRIDE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 7.0, BRACELET_PRIDE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 7.0, BRACELET_PRIDE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 6.0, BRACELET_PRIDE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, BRACELET_PRIDE_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "bracelet_of_pride");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), BRACELET_PRIDE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), BRACELET_PRIDE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), BRACELET_PRIDE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), BRACELET_PRIDE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), BRACELET_PRIDE_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Bracelet of Pride").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+10 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+6 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+7 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+7 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }
}
