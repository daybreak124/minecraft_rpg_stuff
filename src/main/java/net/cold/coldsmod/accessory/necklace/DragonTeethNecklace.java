package net.cold.coldsmod.accessory.necklace;

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

public class DragonTeethNecklace {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


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

    private static class DragonTeethNecklaceRare extends Item {
        public DragonTeethNecklaceRare(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon Teeth Necklace").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+8 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1% Potency").withStyle(style -> style.withColor(0xEC3700)));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Epic ---

    private static class DragonTeethNecklaceEpic extends Item {
        public DragonTeethNecklaceEpic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon Teeth Necklace").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+16 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2% Potency").withStyle(style -> style.withColor(0xEC3700)));


            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Legendary ---

    private static class DragonTeethNecklaceLegendary extends Item {
        public DragonTeethNecklaceLegendary(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon Teeth Necklace").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+24 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3% Potency").withStyle(style -> style.withColor(0xEC3700)));


            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

// --- Mythic ---

    private static class DragonTeethNecklaceMythic extends Item {
        public DragonTeethNecklaceMythic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Dragon Teeth Necklace").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+32 Strength").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4% Potency").withStyle(style -> style.withColor(0xEC3700)));


            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("End of Time").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }
}
