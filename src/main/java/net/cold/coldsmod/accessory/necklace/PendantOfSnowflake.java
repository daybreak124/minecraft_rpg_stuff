package net.cold.coldsmod.accessory.necklace;

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

public class PendantOfSnowflake {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static final UUID SNOWFLAKE_UUID = UUID.fromString("d1e2f3a4-b5c6-4d7e-8f9a-0b1c2d3e4f5a");

// --- Registry ---

    public static final RegistryObject<Item> PENDANT_OF_FLOATING_SNOWFLAKE_RARE = ITEMS.register(
            "pendant_of_floating_snowflake_rare",
            () -> new PendantOfFloatingSnowflakeRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> PENDANT_OF_FLOATING_SNOWFLAKE_EPIC = ITEMS.register(
            "pendant_of_floating_snowflake_epic",
            () -> new PendantOfFloatingSnowflakeEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> PENDANT_OF_FLOATING_SNOWFLAKE_LEGENDARY = ITEMS.register(
            "pendant_of_floating_snowflake_legendary",
            () -> new PendantOfFloatingSnowflakeLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> PENDANT_OF_FLOATING_SNOWFLAKE_MYTHIC = ITEMS.register(
            "pendant_of_floating_snowflake_mythic",
            () -> new PendantOfFloatingSnowflakeMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class PendantOfFloatingSnowflakeRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public PendantOfFloatingSnowflakeRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 6.0, SNOWFLAKE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, SNOWFLAKE_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "pendant_of_floating_snowflake");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), SNOWFLAKE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SNOWFLAKE_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Pendant of Floating Snowflake").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+6 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Whiteout").withStyle(ChatFormatting.WHITE));
        }
    }

// --- Epic ---

    private static class PendantOfFloatingSnowflakeEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public PendantOfFloatingSnowflakeEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 10.0, SNOWFLAKE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, SNOWFLAKE_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "pendant_of_floating_snowflake");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), SNOWFLAKE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SNOWFLAKE_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Pendant of Floating Snowflake").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+10 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Whiteout").withStyle(ChatFormatting.WHITE));
        }
    }

// --- Legendary ---

    private static class PendantOfFloatingSnowflakeLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public PendantOfFloatingSnowflakeLegendary(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 16.0, SNOWFLAKE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, SNOWFLAKE_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "pendant_of_floating_snowflake");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), SNOWFLAKE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SNOWFLAKE_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Pendant of Floating Snowflake").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+16 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Whiteout").withStyle(ChatFormatting.WHITE));
        }
    }

// --- Mythic ---

    private static class PendantOfFloatingSnowflakeMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public PendantOfFloatingSnowflakeMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 24.0, SNOWFLAKE_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, SNOWFLAKE_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "pendant_of_floating_snowflake");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), SNOWFLAKE_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SNOWFLAKE_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Pendant of Floating Snowflake").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+24 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Whiteout").withStyle(ChatFormatting.WHITE));
        }
    }

}
