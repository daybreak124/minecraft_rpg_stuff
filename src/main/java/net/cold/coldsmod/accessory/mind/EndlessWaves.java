package net.cold.coldsmod.accessory.mind;

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

public class EndlessWaves {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


// --- Registry ---

    public static final RegistryObject<Item> ENDLESS_WAVES_RARE = ITEMS.register(
            "endless_waves_rare",
            () -> new EndlessWavesRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> ENDLESS_WAVES_EPIC = ITEMS.register(
            "endless_waves_epic",
            () -> new EndlessWavesEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> ENDLESS_WAVES_LEGENDARY = ITEMS.register(
            "endless_waves_legendary",
            () -> new EndlessWavesLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> ENDLESS_WAVES_MYTHIC = ITEMS.register(
            "endless_waves_mythic",
            () -> new EndlessWavesMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class EndlessWavesRare extends Item {
        public EndlessWavesRare(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Endless Waves").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+3 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+0.5 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+0.5 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Tsunami Sea").withStyle(ChatFormatting.AQUA));
        }
    }

// --- Epic ---

    private static class EndlessWavesEpic extends Item {
        public EndlessWavesEpic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Endless Waves").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+6 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+1 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Tsunami Sea").withStyle(ChatFormatting.AQUA));
        }
    }

// --- Legendary ---

    private static class EndlessWavesLegendary extends Item {
        public EndlessWavesLegendary(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Endless Waves").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+6 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Wisdom").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+1 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+5 Restoration").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal("+5% Armor").withStyle(style -> style.withColor(0x0F52BA)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Tsunami Sea").withStyle(ChatFormatting.AQUA));
        }
    }

// --- Mythic ---

    private static class EndlessWavesMythic extends Item {
        public EndlessWavesMythic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Endless Waves").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+6 Constitution").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+6 Wisdom").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+1 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+5 Restoration").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal("+5 Amplification").withStyle(style -> style.withColor(0x5BB450)));
            tooltip.add(Component.literal("+5% Armor").withStyle(style -> style.withColor(0x0F52BA)));
            tooltip.add(Component.literal("+5% Restoration").withStyle(style -> style.withColor(0x3B8132)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Tsunami Sea").withStyle(ChatFormatting.AQUA));
        }
    }
}
