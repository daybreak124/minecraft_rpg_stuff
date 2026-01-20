package net.cold.coldsmod.accessory.mind;

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

public class TemptingWhispers {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static final UUID TEMPTING_WHISPERS_UUID = UUID.fromString("a1b2c3d4-e5f6-4a5b-8c9d-0e1f2a3b4c5d");

// --- Registry ---

    public static final RegistryObject<Item> TEMPTING_WHISPERS_RARE = ITEMS.register(
            "tempting_whispers_rare",
            () -> new TemptingWhispersRare(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> TEMPTING_WHISPERS_EPIC = ITEMS.register(
            "tempting_whispers_epic",
            () -> new TemptingWhispersEpic(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> TEMPTING_WHISPERS_LEGENDARY = ITEMS.register(
            "tempting_whispers_legendary",
            () -> new TemptingWhispersLegendary(new Item.Properties().stacksTo(64))
    );

    public static final RegistryObject<Item> TEMPTING_WHISPERS_MYTHIC = ITEMS.register(
            "tempting_whispers_mythic",
            () -> new TemptingWhispersMythic(new Item.Properties().stacksTo(64))
    );

// --- Rare ---

    private static class TemptingWhispersRare extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public TemptingWhispersRare(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 3.0, TEMPTING_WHISPERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, TEMPTING_WHISPERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), 0.3, TEMPTING_WHISPERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HEALTH_MULTIPLIER.get(), -0.25, TEMPTING_WHISPERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), -4.0, TEMPTING_WHISPERS_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "tempting_whispers");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), TEMPTING_WHISPERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), TEMPTING_WHISPERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), TEMPTING_WHISPERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HEALTH_MULTIPLIER.get(), TEMPTING_WHISPERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), TEMPTING_WHISPERS_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Tempting Whispers").withStyle(ChatFormatting.BLUE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+3 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-4 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+1 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-25% Max Health").withStyle(style -> style.withColor(0x0F52BA)));
            tooltip.add(Component.literal("+30% Melee Precision").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Epic ---

    private static class TemptingWhispersEpic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public TemptingWhispersEpic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 5.0, TEMPTING_WHISPERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, TEMPTING_WHISPERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), 0.6, TEMPTING_WHISPERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HEALTH_MULTIPLIER.get(), -0.40, TEMPTING_WHISPERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), -4.0, TEMPTING_WHISPERS_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "tempting_whispers");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), TEMPTING_WHISPERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), TEMPTING_WHISPERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), TEMPTING_WHISPERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HEALTH_MULTIPLIER.get(), TEMPTING_WHISPERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), TEMPTING_WHISPERS_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Tempting Whispers").withStyle(ChatFormatting.DARK_PURPLE); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+5 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-4 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-40% Max Health").withStyle(style -> style.withColor(0x0F52BA)));
            tooltip.add(Component.literal("+60% Melee Precision").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Legendary ---

    private static class TemptingWhispersLegendary extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public TemptingWhispersLegendary(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 7.0, TEMPTING_WHISPERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, TEMPTING_WHISPERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), 1.2, TEMPTING_WHISPERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HEALTH_MULTIPLIER.get(), -0.5, TEMPTING_WHISPERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), -4.0, TEMPTING_WHISPERS_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "tempting_whispers");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), TEMPTING_WHISPERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), TEMPTING_WHISPERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), TEMPTING_WHISPERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HEALTH_MULTIPLIER.get(), TEMPTING_WHISPERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), TEMPTING_WHISPERS_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Tempting Whispers").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+7 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-4 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+3 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-50% Max Health").withStyle(style -> style.withColor(0x0F52BA)));
            tooltip.add(Component.literal("+120% Melee Precision").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

// --- Mythic ---

    private static class TemptingWhispersMythic extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public TemptingWhispersMythic(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 9.0, TEMPTING_WHISPERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, TEMPTING_WHISPERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), 1.5, TEMPTING_WHISPERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HEALTH_MULTIPLIER.get(), -0.5, TEMPTING_WHISPERS_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), -4.0, TEMPTING_WHISPERS_UUID);
            }
        }

        @Override
        public boolean canEquip(SlotContext slotContext, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                return !AttributeApplier.isDuplicateAccessory(player, stack, "tempting_whispers");
            }
            return true;
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), TEMPTING_WHISPERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), TEMPTING_WHISPERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), TEMPTING_WHISPERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HEALTH_MULTIPLIER.get(), TEMPTING_WHISPERS_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), TEMPTING_WHISPERS_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Tempting Whispers").withStyle(ChatFormatting.AQUA); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+9 Fortitude").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-4 Perception").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("+4 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal("-50% Max Health").withStyle(style -> style.withColor(0x0F52BA)));
            tooltip.add(Component.literal("+150% Melee Precision").withStyle(style -> style.withColor(0xEC3700)));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Infernal Pact").withStyle(ChatFormatting.DARK_RED));
        }
    }

}
