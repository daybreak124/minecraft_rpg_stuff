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

public class WardenSkin {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


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

    private static class WardenSkinBracersRare extends Item {
        public WardenSkinBracersRare(Properties properties) { super(properties); }

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

    private static class WardenSkinBracersEpic extends Item {
        public WardenSkinBracersEpic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Warden Skin Forged Bracers").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+4 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+2.25 Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+8% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Otherworlds").withStyle(ChatFormatting.DARK_AQUA));
        }
    }

// --- Legendary ---

    private static class WardenSkinBracersLegendary extends Item {
        public WardenSkinBracersLegendary(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Warden Skin Forged Bracers").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+5 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2.5 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+2.75 Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+0.5 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+12% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+10% Toughness").withStyle(style -> style.withColor(0x0F52BA)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Otherworlds").withStyle(ChatFormatting.DARK_AQUA));
        }
    }

// --- Mythic ---

    private static class WardenSkinBracersMythic extends Item {
        public WardenSkinBracersMythic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Warden Skin Forged Bracers").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+8 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+7 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+3.5 Toughness").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+1 Max Health").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+15% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+15% Toughness").withStyle(style -> style.withColor(0x0F52BA)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Otherworlds").withStyle(ChatFormatting.DARK_AQUA));
        }
    }


}
