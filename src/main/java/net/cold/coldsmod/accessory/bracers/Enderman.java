package net.cold.coldsmod.accessory.bracers;

import net.cold.coldsmod.ColdsMod;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.List;

public class Enderman {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


// --- Registry ---

    public static final RegistryObject<Item> ENDERMANS_SEVERED_ARM_RARE = ITEMS.register(
            "endermans_severed_arm_rare",
            () -> new EndermansSeveredArmRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> ENDERMANS_SEVERED_ARM_EPIC = ITEMS.register(
            "endermans_severed_arm_epic",
            () -> new EndermansSeveredArmEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> ENDERMANS_SEVERED_ARM_LEGENDARY = ITEMS.register(
            "endermans_severed_arm_legendary",
            () -> new EndermansSeveredArmLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> ENDERMANS_SEVERED_ARM_MYTHIC = ITEMS.register(
            "endermans_severed_arm_mythic",
            () -> new EndermansSeveredArmMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class EndermansSeveredArmRare extends Item {
        public EndermansSeveredArmRare(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Enderman's Severed Arm").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+1 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-25% Swim Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal("+0.5 Block Reach").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal("+0.125 Entity Reach").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal("+3% Mining Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Epic ---

    private static class EndermansSeveredArmEpic extends Item {
        public EndermansSeveredArmEpic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Enderman's Severed Arm").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+4 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-25% Swim Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal("+1 Block Reach").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal("+0.25 Entity Reach").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal("+6% Mining Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Legendary ---

    private static class EndermansSeveredArmLegendary extends Item {
        public EndermansSeveredArmLegendary(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Enderman's Severed Arm").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+8 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+6 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-25% Swim Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal("+1.5 Block Reach").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal("+0.375 Entity Reach").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal("+9% Mining Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Mythic ---

    private static class EndermansSeveredArmMythic extends Item {
        public EndermansSeveredArmMythic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Enderman's Severed Arm").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+10 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+8 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-25% Swim Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal("+2 Block Reach").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal("+0.5 Entity Reach").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal("+12% Mining Speed").withStyle(style -> style.withColor(0xD6C97A)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

}
