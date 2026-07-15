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

public class SerpentSkin {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }



// --- Registry ---

    public static final RegistryObject<Item> SERPENT_SKIN_WRAP_RARE = ITEMS.register(
            "serpent_skin_wrap_rare",
            () -> new SerpentSkinWrapRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> SERPENT_SKIN_WRAP_EPIC = ITEMS.register(
            "serpent_skin_wrap_epic",
            () -> new SerpentSkinWrapEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> SERPENT_SKIN_WRAP_LEGENDARY = ITEMS.register(
            "serpent_skin_wrap_legendary",
            () -> new SerpentSkinWrapLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> SERPENT_SKIN_WRAP_MYTHIC = ITEMS.register(
            "serpent_skin_wrap_mythic",
            () -> new SerpentSkinWrapMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class SerpentSkinWrapRare extends Item {
        public SerpentSkinWrapRare(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Serpent Skin Wrap").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+3 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+3 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+1.5 Nock Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Nature's Blessing").withStyle(ChatFormatting.GREEN));
        }
    }

// --- Epic ---

    private static class SerpentSkinWrapEpic extends Item {
        public SerpentSkinWrapEpic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Serpent Skin Wrap").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+6 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4.5% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+7 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+2 Nock Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Nature's Blessing").withStyle(ChatFormatting.GREEN));
        }
    }

// --- Legendary ---

    private static class SerpentSkinWrapLegendary extends Item {
        public SerpentSkinWrapLegendary(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Serpent Skin Wrap").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+9 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+6% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+8,75 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+5 Nock Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+7% Projectile Potency").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Nature's Blessing").withStyle(ChatFormatting.GREEN));
        }
    }

// --- Mythic ---

    private static class SerpentSkinWrapMythic extends Item {
        public SerpentSkinWrapMythic(Properties properties) { super(properties); }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Serpent Skin Wrap").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+12 Dexterity").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+7.5% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+11 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+7.5 Nock Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+14% Projectile Potency").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Nature's Blessing").withStyle(ChatFormatting.GREEN));
        }
    }

}
