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

public class SerpentSkin {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


    private static final UUID SERPENT_WRAP_UUID = UUID.fromString("c3e4d5a6-b7c8-4a5b-9d0e-1f2a3b4c5d6e");

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

    private static class SerpentSkinWrapRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public SerpentSkinWrapRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 6.0, SERPENT_WRAP_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 12.0, SERPENT_WRAP_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, SERPENT_WRAP_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 5.0, SERPENT_WRAP_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "serpent_skin_wrap");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), SERPENT_WRAP_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), SERPENT_WRAP_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SERPENT_WRAP_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), SERPENT_WRAP_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Serpent Skin Wrap").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+5% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+12 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+6 Nock Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Nature's Blessing").withStyle(ChatFormatting.GREEN));
        }
    }

// --- Epic ---

    private static class SerpentSkinWrapEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public SerpentSkinWrapEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 8.0, SERPENT_WRAP_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 16.0, SERPENT_WRAP_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, SERPENT_WRAP_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 6.0, SERPENT_WRAP_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "serpent_skin_wrap");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), SERPENT_WRAP_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), SERPENT_WRAP_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SERPENT_WRAP_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), SERPENT_WRAP_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Serpent Skin Wrap").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+6% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+16 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+8 Nock Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Nature's Blessing").withStyle(ChatFormatting.GREEN));
        }
    }

// --- Legendary ---

    private static class SerpentSkinWrapLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public SerpentSkinWrapLegendary(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 10.0, SERPENT_WRAP_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 20.0, SERPENT_WRAP_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), 0.04, SERPENT_WRAP_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), 0.0675, SERPENT_WRAP_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, SERPENT_WRAP_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 8.0, SERPENT_WRAP_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "serpent_skin_wrap");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), SERPENT_WRAP_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), SERPENT_WRAP_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), SERPENT_WRAP_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), SERPENT_WRAP_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SERPENT_WRAP_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), SERPENT_WRAP_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Serpent Skin Wrap").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+8% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+20 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+10 Nock Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+6.75% Projectile Potency").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal("+4% Nock Haste").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Nature's Blessing").withStyle(ChatFormatting.GREEN));
        }
    }

// --- Mythic ---

    private static class SerpentSkinWrapMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public SerpentSkinWrapMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 12.5, SERPENT_WRAP_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 25.0, SERPENT_WRAP_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), 0.0675, SERPENT_WRAP_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), 0.1175, SERPENT_WRAP_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, SERPENT_WRAP_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 10.0, SERPENT_WRAP_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "serpent_skin_wrap");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), SERPENT_WRAP_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), SERPENT_WRAP_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), SERPENT_WRAP_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), SERPENT_WRAP_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SERPENT_WRAP_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), SERPENT_WRAP_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Serpent Skin Wrap").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+10% Debuff Resist").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("+25 Projectile Potency").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+12.5 Nock Haste").withStyle(style -> style.withColor(0xE0701B)));
            tooltip.add(Component.literal("+6.75% Projectile Potency").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal("+11.75% Nock Haste").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Nature's Blessing").withStyle(ChatFormatting.GREEN));
        }
    }

}
