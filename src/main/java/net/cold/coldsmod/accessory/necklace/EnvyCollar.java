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

public class EnvyCollar {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

// --- Registry ---

    public static final RegistryObject<Item> COLLAR_OF_ENVY_RARE = ITEMS.register(
            "collar_of_envy_rare",
            () -> new CollarOfEnvyRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> COLLAR_OF_ENVY_EPIC = ITEMS.register(
            "collar_of_envy_epic",
            () -> new CollarOfEnvyEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> COLLAR_OF_ENVY_LEGENDARY = ITEMS.register(
            "collar_of_envy_legendary",
            () -> new CollarOfEnvyLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> COLLAR_OF_ENVY_MYTHIC = ITEMS.register(
            "collar_of_envy_mythic",
            () -> new CollarOfEnvyMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class CollarOfEnvyRare extends Item {
        public CollarOfEnvyRare(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Collar of Envy").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+2 to all Attributes").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));


            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Epic ---

    private static class CollarOfEnvyEpic extends Item {
        public CollarOfEnvyEpic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Collar of Envy").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+4 to all Attributes").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));


            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Legendary ---

    private static class CollarOfEnvyLegendary extends Item {
        public CollarOfEnvyLegendary(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Collar of Envy").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+6 to all Attributes").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Mythic ---

    private static class CollarOfEnvyMythic extends Item {
        public CollarOfEnvyMythic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Collar of Envy").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal("+8 to all Attributes").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }
}
