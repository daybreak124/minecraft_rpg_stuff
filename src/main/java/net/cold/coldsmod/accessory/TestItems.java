package net.cold.coldsmod.accessory;

import net.cold.coldsmod.ColdsMod;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attributes;
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

public class TestItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static final UUID ALL_ARTIFACT_UUID = UUID.fromString("f9e8d7c8-b5a4-4321-8765-43210fedcba9");
    private static final UUID STR_ITEM_UUID = UUID.fromString("f9e8d7c6-b014-4321-8765-43510fedcba9");
    private static final UUID DEX_ITEM_UUID = UUID.fromString("f9e8d7c6-58a4-4321-8765-43210fedcba9");
    private static final UUID FORT_ITEM_UUID = UUID.fromString("f9e8d7c6-75a4-4321-8765-43123fedcba9");
    private static final UUID CON_ITEM_UUID = UUID.fromString("f9e8d7c6-55a4-4321-8765-43215fedcba9");
    private static final UUID PERC_ITEM_UUID = UUID.fromString("f9e8d7c6-35a4-4321-8765-432104444ba9");
    private static final UUID WIS_ITEM_UUID = UUID.fromString("f9e8d7c6-25a4-4321-8765-4321022dcba9");
    private static final UUID INS_ITEM_UUID = UUID.fromString("f9e8d7c6-15a4-4321-8765-43123fedcba9");
    private static final UUID DEFENSE_TIER_1_UUID = UUID.fromString("e1a1b1c1-d1e1-4121-a1b1-c1d132f1a1b1");
    private static final UUID DEFENSE_TIER_2_UUID = UUID.fromString("e2a2b2c2-d2e2-4222-a2b2-c2d2e322a2b2");
    private static final UUID DEFENSE_TIER_3_UUID = UUID.fromString("e3a3b3c3-d3e3-4323-a3b3-c3d3e332a3b3");


    public static final RegistryObject<Item> ALL = ITEMS.register("all",
            () -> new AllArtifact(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> PERC = ITEMS.register("perc",
            () -> new PercItem(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> CON = ITEMS.register("con",
            () -> new ConItem(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> FORT = ITEMS.register("fort",
            () -> new FortItem(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> WISDOM = ITEMS.register("wisdom",
            () -> new WisItem(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> INSIGHT = ITEMS.register("insight",
            () -> new InsightItem(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> STR = ITEMS.register("str",
            () -> new STRItem(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> DEX = ITEMS.register("dex",
            () -> new DexItem(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> DEFENSE_1 = ITEMS.register("defense_ring_1",
            () -> new DefenseItem1(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> DEFENSE_2 = ITEMS.register("defense_ring_2",
            () -> new DefenseItem2(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> DEFENSE_3 = ITEMS.register("defense_ring_3",
            () -> new DefenseItem3(new Item.Properties().stacksTo(64)));

    private static class AllArtifact extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public AllArtifact(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                // --- Defensive ---
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 100.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 100.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 100.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, Attributes.KNOCKBACK_RESISTANCE, 1.0, ALL_ARTIFACT_UUID); // 1.0 = 100%
                AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 100.0, ALL_ARTIFACT_UUID);

                // --- Offensive (Flat) ---
                AttributeApplier.applyModifier(player, ModAttributes.POTENCY.get(), 125.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 125.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.ACCURACY.get(), 125.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 125.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 125.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_ACCURACY.get(), 125.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION.get(), 125.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 125.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), 125.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), 125.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 125.0, ALL_ARTIFACT_UUID);

                // --- Multipliers (Applied as 1.0 / +100%) ---
                AttributeApplier.applyModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), 1.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), 1.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.HEALTH_MULTIPLIER.get(), 1.0, ALL_ARTIFACT_UUID);

                AttributeApplier.applyModifier(player, ModAttributes.POTENCY_MULTIPLIER.get(), 1.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), 1.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), 1.0, ALL_ARTIFACT_UUID);

                AttributeApplier.applyModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), 1.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), 1.0, ALL_ARTIFACT_UUID);

                AttributeApplier.applyModifier(player, ModAttributes.ACCURACY_MULTIPLIER.get(), 1.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_ACCURACY_MULTIPLIER.get(), 1.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_ACCURACY_MULTIPLIER.get(), 1.0, ALL_ARTIFACT_UUID);

                AttributeApplier.applyModifier(player, ModAttributes.PRECISION_MULTIPLIER.get(), 1.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), 1.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get(), 1.0, ALL_ARTIFACT_UUID);

                // --- Utility ---
                AttributeApplier.applyModifier(player, Attributes.MOVEMENT_SPEED, 0.1, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, net.minecraftforge.common.ForgeMod.SWIM_SPEED.get(), 1.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.XP_GAIN.get(), 1.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, net.minecraftforge.common.ForgeMod.BLOCK_REACH.get(), 5.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, net.minecraftforge.common.ForgeMod.ENTITY_REACH.get(), 5.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, Attributes.LUCK, 5.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.MINING_SPEED.get(), 1.0, ALL_ARTIFACT_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.JUMP_BOOST.get(), 1.0, ALL_ARTIFACT_UUID);
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {

                AttributeApplier.removeModifier(player, Attributes.ARMOR, ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, Attributes.KNOCKBACK_RESISTANCE, ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), ALL_ARTIFACT_UUID);

                // --- Offensive (Flat) ---
                AttributeApplier.removeModifier(player, ModAttributes.POTENCY.get(), ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.ACCURACY.get(), ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_ACCURACY.get(), ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION.get(), ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), ALL_ARTIFACT_UUID);


                // --- Multipliers ---
                AttributeApplier.removeModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.HEALTH_MULTIPLIER.get(), ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.POTENCY_MULTIPLIER.get(), ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), ALL_ARTIFACT_UUID);

                AttributeApplier.removeModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.ACCURACY_MULTIPLIER.get(), ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_ACCURACY_MULTIPLIER.get(), ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_ACCURACY_MULTIPLIER.get(), ALL_ARTIFACT_UUID);

                AttributeApplier.removeModifier(player, ModAttributes.PRECISION_MULTIPLIER.get(), ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get(), ALL_ARTIFACT_UUID);


                // --- Utility ---
                AttributeApplier.removeModifier(player, Attributes.MOVEMENT_SPEED, ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, net.minecraftforge.common.ForgeMod.SWIM_SPEED.get(), ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.XP_GAIN.get(), ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, net.minecraftforge.common.ForgeMod.BLOCK_REACH.get(), ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, net.minecraftforge.common.ForgeMod.ENTITY_REACH.get(), ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, Attributes.LUCK, ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.MINING_SPEED.get(), ALL_ARTIFACT_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.JUMP_BOOST.get(), ALL_ARTIFACT_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("All Stats").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+100 to all stats.").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal("+100% to all stats").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal("Except resto and ampl cos im lazy").withStyle(ChatFormatting.GRAY));
        }
    }

    private static class STRItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public STRItem(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 80.0, STR_ITEM_UUID);
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.STR.get(), STR_ITEM_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Strength Item").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+80 Str").withStyle(ChatFormatting.GOLD));
        }
    }

    private static class DexItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public DexItem(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 80.0, DEX_ITEM_UUID);
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), DEX_ITEM_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Dexterity Item").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+80 Dex").withStyle(ChatFormatting.GOLD));
        }
    }


    private static class PercItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public PercItem(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 80.0, PERC_ITEM_UUID);
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), PERC_ITEM_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Perception Item").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+80 Perc").withStyle(ChatFormatting.GOLD));
        }
    }

    private static class ConItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public ConItem(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 80.0, CON_ITEM_UUID);
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.CON.get(), CON_ITEM_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Constitution Item").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+80 Con").withStyle(ChatFormatting.GOLD));
        }
    }

    private static class FortItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public FortItem(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 80.0, FORT_ITEM_UUID);
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), FORT_ITEM_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Fortitude Item").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+80 Fort").withStyle(ChatFormatting.GOLD));
        }
    }

    private static class WisItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public WisItem(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 60.0, WIS_ITEM_UUID);
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), WIS_ITEM_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Wisdom Item").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+50 Wisdom").withStyle(ChatFormatting.GOLD));
        }
    }

    private static class InsightItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public InsightItem(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 50.0, INS_ITEM_UUID);
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), INS_ITEM_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Insight Item").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+50 Insight").withStyle(ChatFormatting.GOLD));
        }
    }

    private static class DefenseItem1 extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public DefenseItem1(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 10.0, DEFENSE_TIER_1_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 10.0, DEFENSE_TIER_1_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), 0.10, DEFENSE_TIER_1_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), 0.10, DEFENSE_TIER_1_UUID);
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, Attributes.ARMOR, DEFENSE_TIER_1_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, DEFENSE_TIER_1_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), DEFENSE_TIER_1_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), DEFENSE_TIER_1_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Defense Item 1").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+10 Armor").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal("+10 Armor Toughness").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal("+10% Armor Multiplier").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal("+10% Toughness Multiplier").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal("Equip if the game is unbalanced").withStyle(ChatFormatting.GRAY));
        }
    }

    private static class DefenseItem2 extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public DefenseItem2(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 20.0, DEFENSE_TIER_2_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 20.0, DEFENSE_TIER_2_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), 0.20, DEFENSE_TIER_2_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), 0.20, DEFENSE_TIER_2_UUID);
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, Attributes.ARMOR, DEFENSE_TIER_2_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, DEFENSE_TIER_2_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), DEFENSE_TIER_2_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), DEFENSE_TIER_2_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Defense Item 2").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+20 Armor").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal("+20 Armor Toughness").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal("+20% Armor Multiplier").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal("+20% Toughness Multiplier").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal("Equip if the game is too unbalanced").withStyle(ChatFormatting.GRAY));
        }
    }

    private static class DefenseItem3 extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public DefenseItem3(Properties properties) { super(properties); }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, Attributes.ARMOR, 30.0, DEFENSE_TIER_3_UUID);
                AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 30.0, DEFENSE_TIER_3_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), 0.30, DEFENSE_TIER_3_UUID);
                AttributeApplier.applyModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), 0.30, DEFENSE_TIER_3_UUID);
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, Attributes.ARMOR, DEFENSE_TIER_3_UUID);
                AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, DEFENSE_TIER_3_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), DEFENSE_TIER_3_UUID);
                AttributeApplier.removeModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), DEFENSE_TIER_3_UUID);
            }
        }

        @Override
        public Component getName(ItemStack stack) { return Component.literal("Defense Item 3").withStyle(ChatFormatting.GOLD); }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+30 Armor").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal("+30 Armor Toughness").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal("+30% Armor Multiplier").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal("+30% Toughness Multiplier").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Equip if the game is way too unbalanced").withStyle(ChatFormatting.GRAY));
        }
    }
}