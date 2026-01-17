package net.cold.coldsmod.item;

import com.mojang.serialization.Codec;
import net.cold.coldsmod.ColdsMod;
import net.cold.coldsmod.gearbonuses.effects.ModEffects;
import net.cold.coldsmod.gearbonuses.neweffects.SummoningStone;
import net.cold.coldsmod.network.DFASync;
import net.cold.coldsmod.network.NetworkHandler;
import net.cold.coldsmod.network.QuantumLeapSync;
import net.cold.coldsmod.stat.ArmorRarityModifier;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import top.theillusivec4.curios.api.SlotContext;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

import static net.cold.coldsmod.gearbonuses.CooldownCycle.HAWKEYE_UUID;
import static net.cold.coldsmod.stat.AttributeApplier.removeModifier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ColdsMod.MODID);

    public static final RegistryObject<Item> GEM_CLUSTER = ITEMS.register(
            "gem_cluster",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Gem Cluster").withStyle(ChatFormatting.GREEN);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal(""));
                    tooltip.add(
                            Component.literal("Obtained from crushing accessories").withStyle(ChatFormatting.GRAY)
                    );
                    tooltip.add(
                            Component.literal("Combine with other ").withStyle(ChatFormatting.GRAY)
                                    .append(Component.literal("Gem Clusters").withStyle(ChatFormatting.GREEN))
                                    .append(Component.literal(" to upgrade accessories.").withStyle(ChatFormatting.GRAY))
                    );
                }
            }
    );

    public static final RegistryObject<Item> FOCUSED_GEM_CLUSTER = ITEMS.register(
            "focused_gem_cluster",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Focused Gem Cluster").withStyle(ChatFormatting.BLUE);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal(""));
                    tooltip.add(
                            Component.literal("Used to upgrade the rarity of").withStyle(ChatFormatting.GRAY)
                                    .append(Component.literal(" RARE").withStyle(ChatFormatting.BLUE))
                                    .append(Component.literal(" accessories to").withStyle(ChatFormatting.GRAY))
                                    .append(Component.literal(" EPIC").withStyle(ChatFormatting.DARK_PURPLE))
                    );
                }
            }
    );

    public static final RegistryObject<Item> REINFORCED_GEM_CLUSTER = ITEMS.register(
            "reinforced_gem_cluster",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Reinforced Gem Cluster").withStyle(ChatFormatting.DARK_PURPLE);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal(""));
                    tooltip.add(
                            Component.literal("Used to upgrade the rarity of").withStyle(ChatFormatting.GRAY)
                                    .append(Component.literal(" EPIC").withStyle(ChatFormatting.DARK_PURPLE))
                                    .append(Component.literal(" accessories to").withStyle(ChatFormatting.GRAY))
                                    .append(Component.literal(" LEGENDARY").withStyle(ChatFormatting.GOLD))
                    );
                }
            }
    );

    public static final RegistryObject<Item> PERFECTED_GEM_CLUSTER = ITEMS.register(
            "perfect_gem_cluster",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Perfected Gem Cluster").withStyle(ChatFormatting.GOLD);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal(""));
                    tooltip.add(
                            Component.literal("Used to upgrade the rarity of").withStyle(ChatFormatting.GRAY)
                                    .append(Component.literal(" LEGENDARY").withStyle(ChatFormatting.GOLD))
                                    .append(Component.literal(" accessories to").withStyle(ChatFormatting.GRAY))
                                    .append(Component.literal(" MYTHIC").withStyle(ChatFormatting.AQUA))
                    );
                }
            }
    );

    public static final RegistryObject<Item> COMMON_SCRAP_ESSENCE = ITEMS.register(
            "common_scrap_essence",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Common Scrap Essence").withStyle(ChatFormatting.GRAY);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal(""));
                    tooltip.add(
                            Component.literal("Used to upgrade the rarity of ").withStyle(ChatFormatting.GRAY)
                                    .append(Component.literal("COMMON").withStyle(ChatFormatting.GRAY))
                                    .append(Component.literal(" items to ").withStyle(ChatFormatting.GRAY))
                                    .append(Component.literal("UNCOMMON").withStyle(ChatFormatting.GREEN))
                    );
                }
            }
    );

    public static final RegistryObject<Item> UNCOMMON_SCRAP_ESSENCE = ITEMS.register(
            "uncommon_scrap_essence",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Uncommon Scrap Essence").withStyle(ChatFormatting.GREEN);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal(""));
                    tooltip.add(
                            Component.literal("Used to upgrade the rarity of ").withStyle(ChatFormatting.GRAY)
                                    .append(Component.literal("UNCOMMON").withStyle(ChatFormatting.GREEN))
                                    .append(Component.literal(" items to ").withStyle(ChatFormatting.GRAY))
                                    .append(Component.literal("RARE").withStyle(ChatFormatting.BLUE))
                    );
                }
            }
    );

    public static final RegistryObject<Item> RARE_SCRAP_ESSENCE = ITEMS.register(
            "rare_scrap_essence",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Rare Scrap Essence").withStyle(ChatFormatting.BLUE);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal("")); // empty line
                    tooltip.add(
                            Component.literal("Used to upgrade the rarity of ").withStyle(ChatFormatting.GRAY)
                                    .append(Component.literal("RARE").withStyle(ChatFormatting.BLUE))
                                    .append(Component.literal(" items to ").withStyle(ChatFormatting.GRAY))
                                    .append(Component.literal("EPIC").withStyle(ChatFormatting.DARK_PURPLE))
                    );
                }
            }
    );

    public static final RegistryObject<Item> EPIC_SCRAP_ESSENCE = ITEMS.register(
            "epic_scrap_essence",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Epic Scrap Essence").withStyle(ChatFormatting.DARK_PURPLE);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal("")); // empty line
                    tooltip.add(
                            Component.literal("Used to upgrade the rarity of ").withStyle(ChatFormatting.GRAY)
                                    .append(Component.literal("EPIC").withStyle(ChatFormatting.DARK_PURPLE))
                                    .append(Component.literal(" items to ").withStyle(ChatFormatting.GRAY))
                                    .append(Component.literal("LEGENDARY").withStyle(ChatFormatting.GOLD))
                    );
                }
            }
    );

    public static final RegistryObject<Item> LEGENDARY_SCRAP_ESSENCE = ITEMS.register(
            "legendary_scrap_essence",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Legendary Scrap Essence").withStyle(ChatFormatting.GOLD);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal("")); // empty line
                    tooltip.add(
                            Component.literal("Used to upgrade the rarity of ").withStyle(ChatFormatting.GRAY)
                                    .append(Component.literal("LEGENDARY").withStyle(ChatFormatting.GOLD))
                                    .append(Component.literal(" items to ").withStyle(ChatFormatting.GRAY))
                                    .append(Component.literal("MYTHIC").withStyle(ChatFormatting.AQUA))
                    );
                }
            }
    );

    public static final RegistryObject<Item> PEARL_OF_REPLENISHING = ITEMS.register(
            "pearl_of_replenishing",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Pearl of Replenishing").withStyle(ChatFormatting.GRAY);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal("")); // empty line
                    tooltip.add(
                            Component.literal("Used to re-roll the stats of ").withStyle(ChatFormatting.GRAY)
                                    .append(Component.literal("COMMON").withStyle(ChatFormatting.GRAY))
                                    .append(Component.literal(" items").withStyle(ChatFormatting.GRAY))
                    );
                }
            }
    );

    public static final RegistryObject<Item> PEARL_OF_RECHARGING = ITEMS.register(
            "pearl_of_recharging",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Pearl of Recharging").withStyle(ChatFormatting.GREEN);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal("")); // empty line
                    tooltip.add(
                            Component.literal("Used to re-roll the stats of ").withStyle(ChatFormatting.GRAY)
                                    .append(Component.literal("UNCOMMON").withStyle(ChatFormatting.GREEN))
                                    .append(Component.literal(" items").withStyle(ChatFormatting.GRAY))
                    );
                }
            }
    );

    public static final RegistryObject<Item> PEARL_OF_RENEWING = ITEMS.register(
            "pearl_of_renewing",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Pearl of Renewing").withStyle(ChatFormatting.BLUE);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal("")); // empty line
                    tooltip.add(
                            Component.literal("Used to re-roll the stats of ").withStyle(ChatFormatting.GRAY)
                                    .append(Component.literal("RARE").withStyle(ChatFormatting.BLUE))
                                    .append(Component.literal(" items").withStyle(ChatFormatting.GRAY))
                    );
                }
            }
    );

    public static final RegistryObject<Item> PEARL_OF_RESTORING = ITEMS.register(
            "pearl_of_restoring",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Pearl of Restoring").withStyle(ChatFormatting.DARK_PURPLE);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal("")); // empty line
                    tooltip.add(
                            Component.literal("Used to re-roll the stats of ").withStyle(ChatFormatting.GRAY)
                                    .append(Component.literal("EPIC").withStyle(ChatFormatting.DARK_PURPLE))
                                    .append(Component.literal(" items").withStyle(ChatFormatting.GRAY))
                    );
                }
            }
    );

    public static final RegistryObject<Item> PEARL_OF_REJUVENATING = ITEMS.register(
            "pearl_of_rejuvenating",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Pearl of Rejuvenating").withStyle(ChatFormatting.GOLD);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal("")); // empty line
                    tooltip.add(
                            Component.literal("Used to re-roll the stats of ").withStyle(ChatFormatting.GRAY)
                                    .append(Component.literal("LEGENDARY").withStyle(ChatFormatting.GOLD))
                                    .append(Component.literal(" items").withStyle(ChatFormatting.GRAY))
                    );
                }
            }
    );

    public static final RegistryObject<Item> PEARL_OF_REVITALIZING = ITEMS.register(
            "pearl_of_revitalizing",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Pearl of Revitalizing").withStyle(ChatFormatting.AQUA);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal("")); // empty line
                    tooltip.add(
                            Component.literal("Used to re-roll the stats of ").withStyle(ChatFormatting.GRAY)
                                    .append(Component.literal("MYTHIC").withStyle(ChatFormatting.AQUA))
                                    .append(Component.literal(" items").withStyle(ChatFormatting.GRAY))
                    );
                }
            }
    );

    public static final RegistryObject<Item> SHARD_OF_INFUSION = ITEMS.register(
            "shard_of_infusion",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Shard of Infusion").withStyle(ChatFormatting.GRAY);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal("")); // empty line
                    tooltip.add(
                            Component.literal("Used to re-roll the attributes of ").withStyle(ChatFormatting.GRAY)
                                    .append(Component.literal("COMMON").withStyle(ChatFormatting.GRAY))
                                    .append(Component.literal(" items").withStyle(ChatFormatting.GRAY))
                    );
                }
            }
    );

    public static final RegistryObject<Item> SHARD_OF_AUGMENTATION = ITEMS.register(
            "shard_of_augmentation",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Shard of Augmentation").withStyle(ChatFormatting.GREEN);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal("")); // empty line
                    tooltip.add(
                            Component.literal("Used to re-roll the attributes of ").withStyle(ChatFormatting.GRAY)
                                    .append(Component.literal("UNCOMMON").withStyle(ChatFormatting.GREEN))
                                    .append(Component.literal(" items").withStyle(ChatFormatting.GRAY))
                    );
                }
            }
    );

    public static final RegistryObject<Item> SHARD_OF_AMPLIFICATION = ITEMS.register(
            "shard_of_amplification",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Shard of Amplification").withStyle(ChatFormatting.BLUE);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal("")); // empty line
                    tooltip.add(
                            Component.literal("Used to re-roll the attributes of ").withStyle(ChatFormatting.GRAY)
                                    .append(Component.literal("RARE").withStyle(ChatFormatting.BLUE))
                                    .append(Component.literal(" items").withStyle(ChatFormatting.GRAY))
                    );
                }
            }
    );

    public static final RegistryObject<Item> SHARD_OF_EMPOWERMENT = ITEMS.register(
            "shard_of_empowerment",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Shard of Empowerment").withStyle(ChatFormatting.DARK_PURPLE);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal("")); // empty line
                    tooltip.add(
                            Component.literal("Used to re-roll the attributes of ").withStyle(ChatFormatting.GRAY)
                                    .append(Component.literal("EPIC").withStyle(ChatFormatting.DARK_PURPLE))
                                    .append(Component.literal(" items").withStyle(ChatFormatting.GRAY))
                    );
                }
            }
    );

    public static final RegistryObject<Item> SHARD_OF_ASCENDANCY = ITEMS.register(
            "shard_of_ascendancy",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Shard of Ascendancy").withStyle(ChatFormatting.GOLD);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal("")); // empty line
                    tooltip.add(
                            Component.literal("Used to re-roll the attributes of ").withStyle(ChatFormatting.GRAY)
                                    .append(Component.literal("LEGENDARY").withStyle(ChatFormatting.GOLD))
                                    .append(Component.literal(" items").withStyle(ChatFormatting.GRAY))
                    );
                }
            }
    );

    public static final RegistryObject<Item> PEARL_ICON = ITEMS.register("pearl_icon",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ORB_ICON = ITEMS.register("orb_icon",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SHARD_OF_TRANSCENDENCE = ITEMS.register(
            "shard_of_transcendence",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Shard of Transcendence").withStyle(ChatFormatting.AQUA);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal("")); // empty line
                    tooltip.add(
                            Component.literal("Used to re-roll the attributes of ").withStyle(ChatFormatting.GRAY)
                                    .append(Component.literal("MYTHIC").withStyle(ChatFormatting.AQUA))
                                    .append(Component.literal(" items").withStyle(ChatFormatting.GRAY))
                    );
                }
            }
    );


    public static final RegistryObject<Item> ACCESSORY_UPGRADE_SMITHING_TEMPLATE = ITEMS.register(
            "accessory_upgrade_smithing_template",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Accessory Upgrade Template").withStyle(ChatFormatting.RED);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal(""));
                    tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" Accessories").withStyle(ChatFormatting.BLUE));
                    tooltip.add(Component.literal("Ingredients:").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" Gem Clusters").withStyle(ChatFormatting.BLUE));
                }
            }
    );

    public static final RegistryObject<Item> WARLORDS_GAZE = ITEMS.register(
            "warlords_gaze",
            () -> new WarlordsGazeItem(new Item.Properties().stacksTo(64))
    );

    private static class WarlordsGazeItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public WarlordsGazeItem(Properties properties) {
            super(properties);
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player && !(player.hasEffect(ModEffects.INTIMIDATING_PRESENCE_COOLDOWN.get()))) {
                player.addEffect(new MobEffectInstance(ModEffects.INTIMIDATING_PRESENCE.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.removeEffect(ModEffects.INTIMIDATING_PRESENCE.get());
                player.removeEffect(ModEffects.INTIMIDATING_PRESENCE_COOLDOWN.get());
            }
        }

        @Override
        public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
            if (!(slotContext.entity() instanceof Player player)) return true;
            return !player.hasEffect(ModEffects.INTIMIDATING_PRESENCE_COOLDOWN.get());
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Warlord's Gaze").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Blessing: Intimidating Presence").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Crouching for a second").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" marks monsters within a").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 10 block radius, increasing").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" their damage taken by 20%").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" for 8 seconds.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Debuff amount increased by").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 0.1% per Strength and").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 0.05% per Constitution.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.DARK_AQUA)
                            .append(Component.literal("15s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> HORN_OF_FEARMONGERING = ITEMS.register(
            "horn_of_fearmongering",
            () -> new HornOfFearmongeringItem(new Item.Properties().stacksTo(64))
    );

    private static class HornOfFearmongeringItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public HornOfFearmongeringItem(Properties properties) {
            super(properties);
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player && !(player.hasEffect(ModEffects.DARING_SHOUT_COOLDOWN.get()))) {
                player.addEffect(new MobEffectInstance(ModEffects.DARING_SHOUT.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.removeEffect(ModEffects.DARING_SHOUT.get());
                player.removeEffect(ModEffects.DARING_SHOUT_COOLDOWN.get());
            }
        }

        @Override
        public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
            if (!(slotContext.entity() instanceof Player player)) return true;
            return !player.hasEffect(ModEffects.DARING_SHOUT_COOLDOWN.get());
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Horn of Fearmongering").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Blessing: Daring Shout").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Crouching roots targets").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" within a 5 block radius").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" for 3 seconds.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Stun duration increased by").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 0.06s per Fortitude and").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 0.03s per Perception.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Duration reduce to 1/3").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" against Bosses.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("15s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> HANKS_EYE = ITEMS.register(
            "hanks_eye",
            () -> new HanksEyeItem(new Item.Properties().stacksTo(64))
    );

    private static class HanksEyeItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public HanksEyeItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Hank's Eye").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.getPersistentData().putBoolean("hawkeye_eligible", true);
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.removeEffect(ModEffects.HAWKEYE.get());
                player.getPersistentData().remove("hawkeye_eligible");
                player.getPersistentData().remove("hawkeye");

                removeModifier(player, ModAttributes.NOCK_HASTE.get(), HAWKEYE_UUID);
                removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), HAWKEYE_UUID);
            }
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Blessing: Hawkeye").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Critical hits with melee").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" weapons increase your").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Projectile Potency by 5").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" and Nock Haste by 9").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" for 8 seconds. Stacks").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" consumed upon landing").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" a projectile shot.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Effect increased by").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 0.75% per Dexterity and").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 0.4% per Perception.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Max Stacks: ").withStyle(ChatFormatting.DARK_AQUA)
                            .append(Component.literal("4").withStyle(ChatFormatting.GRAY)));
        }
    }

    public static final RegistryObject<Item> SUNSTONE_GEM = ITEMS.register(
            "sunstone_gem",
            () -> new SunstoneGemItem(new Item.Properties().stacksTo(64))
    );

    private static class SunstoneGemItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public SunstoneGemItem(Properties properties) {
            super(properties);
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.addEffect(new MobEffectInstance(ModEffects.SOLARA.get(), 24000, 0, false, false, true));
                player.getPersistentData().putBoolean("solara_eligible", true);
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.getPersistentData().remove("solara_eligible");
            }
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Sunstone Gem").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Blessing: Solara").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Gain or lose Melee Potency").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" and Armor depending on the ").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" time of day.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal(" +25 Melee Potency and").withStyle(ChatFormatting.YELLOW));
            tooltip.add(Component.literal(" +10 Armor at noon.").withStyle(ChatFormatting.YELLOW));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal(" -15 Melee Potency and").withStyle(ChatFormatting.DARK_GRAY));
            tooltip.add(Component.literal(" -15 Armor at midnight.").withStyle(ChatFormatting.DARK_GRAY));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal(" Effect remains for at").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" least 1 Minecraft day.").withStyle(ChatFormatting.GRAY));
        }
    }

    private static final UUID FRENZY_ATTACK_DAMAGE = UUID.fromString("d739268d-e62f-4c9b-8301-2812343ab281");

    public static final RegistryObject<Item> RAGE_AMPLIFIER = ITEMS.register(
            "rage_amplifier",
            () -> new RageAmplifierItem(new Item.Properties().stacksTo(64))
    );

    private static class RageAmplifierItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public RageAmplifierItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Rage Amplifier").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.getPersistentData().putBoolean("frenzy_eligible", true);
                AttributeApplier.applyModifier(player, Attributes.ATTACK_DAMAGE, 1.0, FRENZY_ATTACK_DAMAGE);
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.removeEffect(ModEffects.FRENZY.get());
                player.getPersistentData().remove("frenzy_eligible");
                player.getPersistentData().remove("frenzy");
                AttributeApplier.removeModifier(player, Attributes.ATTACK_DAMAGE, FRENZY_ATTACK_DAMAGE);
            }
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Blessing: Frenzy").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Increase Attack Damage by").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 1 but increases your damage").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" received by 5%. On hit,").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Attack Damage increases").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" by 0.1 and Damage taken by").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 1% per stack.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Max Stacks: ").withStyle(ChatFormatting.DARK_AQUA)
                            .append(Component.literal("40").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(
                    Component.literal("Duration: ").withStyle(ChatFormatting.GREEN)
                            .append(Component.literal("2s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> DROP_OF_SACRIFICIAL_BLOOD = ITEMS.register(
            "drop_of_sacrificial_blood",
            () -> new DropOfSacrificialBloodItem(new Item.Properties().stacksTo(64))
    );

    private static class DropOfSacrificialBloodItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public DropOfSacrificialBloodItem(Properties properties) {
            super(properties);
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player
                    && !player.hasEffect(ModEffects.RECKONING_COOLDOWN.get())
                    && !(player.hasEffect(ModEffects.RECKONING_ACTIVE.get()))) {
                player.addEffect(new MobEffectInstance(ModEffects.RECKONING.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.removeEffect(ModEffects.RECKONING.get());
                player.removeEffect(ModEffects.RECKONING_COOLDOWN.get());
                player.removeEffect(ModEffects.RECKONING_ACTIVE.get());
            }
        }

        @Override
        public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
            if (!(slotContext.entity() instanceof Player player)) return true;
            return !player.hasEffect(ModEffects.RECKONING_ACTIVE.get()) && !player.hasEffect(ModEffects.RECKONING_COOLDOWN.get());
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Drop of Sacrificial Blood").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Blessing: Reckoning").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Upon taking damage, apply").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Reckoning, which restores").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 40% of your incoming damage").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" while the effect is active.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Take half of the amount").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" healed as damage after the").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" effect ends.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Duration: ").withStyle(ChatFormatting.GREEN)
                            .append(Component.literal("10s").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("10s").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(Component.literal(" Cooldown starts after").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" the effect ends.").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> HELL_ON_EARTH = ITEMS.register(
            "hell_on_earth",
            () -> new HellOnEarthItem(new Item.Properties().stacksTo(64))
    );

    private static class HellOnEarthItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public HellOnEarthItem(Properties properties) {
            super(properties);
        }

        @Override
        public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
            if (!(slotContext.entity() instanceof Player player)) return true;
            return !player.hasEffect(ModEffects.DIRECTED_HATRED_COOLDOWN.get());
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player && !(player.hasEffect(ModEffects.DIRECTED_HATRED_COOLDOWN.get()))) {
                player.addEffect(new MobEffectInstance(ModEffects.DIRECTED_HATRED_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.removeEffect(ModEffects.DIRECTED_HATRED_READY.get());
                player.removeEffect(ModEffects.DIRECTED_HATRED_COOLDOWN.get());
            }
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Hell on Earth").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Blessing: Directed Hatred").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Jump critting taunts enemies").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" within 6 blocks and increases").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" their damage taken by 6% for").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 6 seconds.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("10s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> BANNER_OF_DETERMINATION = ITEMS.register(
            "banner_of_determination",
            () -> new BannerOfDeterminationItem(new Item.Properties().stacksTo(64))
    );

    private static class BannerOfDeterminationItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public BannerOfDeterminationItem(Properties properties) {
            super(properties);
        }

        @Override
        public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
            if (!(slotContext.entity() instanceof Player player)) return true;
                return !(player.hasEffect(ModEffects.INTO_THE_FRAY.get()) && player.hasEffect(ModEffects.INTO_THE_FRAY_COOLDOWN.get()));
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.getPersistentData().putBoolean("into_the_fray_eligible", true);

            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.removeEffect(ModEffects.INTO_THE_FRAY_COOLDOWN.get());
                player.removeEffect(ModEffects.INTO_THE_FRAY.get());
                player.getPersistentData().remove("into_the_fray_eligible");
                player.getPersistentData().remove("sprintTicks");
                player.getPersistentData().remove("itfStacks");
            }
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Banner of Marching Armies").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Blessing: Into the Fray").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Activated upon sprinting").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" for 3 seconds. Increase").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" movement speed by 8% per").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" stack and deal 4 Melee").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Damage per stack in a 4 block").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" radius when colliding with a").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" monster. Stack count increased").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" every 2 seconds and gain").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Absorption 1 when reaching").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 5 stacks as long as you are").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" sprinting. Targets collided").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" with are inflicted with").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Slowness 4 for 0.4s per stack").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Cooldown applied and movement").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" speed reduced briefly on").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" collision.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Max Stacks: ").withStyle(ChatFormatting.DARK_AQUA)
                            .append(Component.literal("5").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("9s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> WORMHOLE = ITEMS.register(
            "wormhole",
            () -> new WormholeItem(new Item.Properties().stacksTo(64))
    );

    private static class WormholeItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public WormholeItem(Properties properties) {
            super(properties);
        }

        @Override
        public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
            if (!(slotContext.entity() instanceof Player player)) return true;
            return !player.hasEffect(ModEffects.QUANTUM_LEAP_COOLDOWN.get());
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {

                if (player instanceof ServerPlayer sp) {
                    NetworkHandler.sendToClient(new QuantumLeapSync.QuantumLeapFlagPacket(true), sp);
                }

                if (!player.hasEffect(ModEffects.QUANTUM_LEAP_ACTIVE.get())
                    && !player.hasEffect(ModEffects.QUANTUM_LEAP_COOLDOWN.get())) {
                    player.addEffect(new MobEffectInstance(ModEffects.QUANTUM_LEAP_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
                }

            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.removeEffect(ModEffects.QUANTUM_LEAP_ACTIVE.get());
                player.removeEffect(ModEffects.QUANTUM_LEAP_READY.get());
                player.removeEffect(ModEffects.QUANTUM_LEAP_COOLDOWN.get());

                if (player instanceof ServerPlayer sp) {
                    NetworkHandler.sendToClient(new QuantumLeapSync.QuantumLeapFlagPacket(false), sp);
                }
            }
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Wormhole").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Blessing: Quantum Leap").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Upon crouch jumping, perform").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" a teleport and turn invisible for").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 4 seconds after landing and gain").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" +15 Potency and +20% Movement").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Speed for 8 seconds until you.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" attack a target. Takes priority").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" over Death From Above.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" If used 4 seconds within").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Death From Above; duration,").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Damage and Move Speed +50%.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Cancels fall damage.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("35s").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(Component.literal(" Cooldown starts after the").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" initial leap.").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> ORB_OF_WORLD_DESTRUCTION = ITEMS.register(
            "orb_of_world_destruction",
            () -> new OrbOfWorldDestructionItem(new Item.Properties().stacksTo(64))
    );

    private static class OrbOfWorldDestructionItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public OrbOfWorldDestructionItem(Properties properties) {
            super(properties);
        }

        @Override
        public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
            if (!(slotContext.entity() instanceof Player player)) return true;
            return !player.hasEffect(ModEffects.DEATH_FROM_ABOVE_COOLDOWN.get());
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {

                if (player instanceof ServerPlayer sp) {
                    NetworkHandler.sendToClient(new DFASync.DFAFlagPacket(true), sp);
                }
                if (!player.hasEffect(ModEffects.DEATH_FROM_ABOVE_COOLDOWN.get())) {
                    player.addEffect(new MobEffectInstance(ModEffects.DEATH_FROM_ABOVE.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
                }
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.removeEffect(ModEffects.DEATH_FROM_ABOVE.get());
                player.removeEffect(ModEffects.DEATH_FROM_ABOVE_COOLDOWN.get());
                player.removeEffect(ModEffects.ENHANCED_QUANTUM_LEAP.get());
                player.getPersistentData().remove("DFA_fall_damage_cancel");


                if (player instanceof ServerPlayer sp) {
                    NetworkHandler.sendToClient(new DFASync.DFAFlagPacket(false), sp);
                }

            }
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Orb of World Destruction").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Blessing: Death From Above").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Upon jumping, shoot").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" yourself up and deal").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 5 Melee damage within").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" a 5 block radius and").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 15 Melee damage on landing").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" within a 7 block radius.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Reduces Quantum Leap").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" cooldown by 5 seconds and").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" enhances it for 4 seconds.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Disabled while swimming.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Cancels fall damage.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("15s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> SOUL_MAGNET = ITEMS.register(
            "soul_magnet",
            () -> new SoulMagnetItem(new Item.Properties().stacksTo(64))
    );

    private static class SoulMagnetItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public SoulMagnetItem(Properties properties) {
            super(properties);
        }

        @Override
        public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
            if (!(slotContext.entity() instanceof Player player)) return true;
            return !player.hasEffect(ModEffects.SOUL_SEVERANCE_ACTIVE.get()) && !player.hasEffect(ModEffects.SOUL_SEVERANCE_COOLDOWN.get());
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {

                if (!player.hasEffect(ModEffects.SOUL_SEVERANCE_COOLDOWN.get())
                && !player.hasEffect(ModEffects.SOUL_SEVERANCE_ACTIVE.get())) {
                    player.addEffect(new MobEffectInstance(ModEffects.SOUL_SEVERANCE_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
                }
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.removeEffect(ModEffects.SOUL_SEVERANCE_ACTIVE.get());
                player.removeEffect(ModEffects.SOUL_SEVERANCE_READY.get());
                player.removeEffect(ModEffects.SOUL_SEVERANCE_COOLDOWN.get());
            }
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Soul Magnet").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Blessing: Soul Severance").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Crouching pulls entities").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" within 6 blocks to you").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" as long as you are").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" crouching, up to 4 seconds.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Deal 3 Melee Damage per").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" second to monsters pulled.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("9s").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(Component.literal(" Cooldown starts after").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" the effect ends.").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> LIGHTNING_INFUSION = ITEMS.register(
            "lightning_infusion",
            () -> new LightningInfusionItem(new Item.Properties().stacksTo(64))
    );

    private static class LightningInfusionItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public LightningInfusionItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Lightning Infusion").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.getPersistentData().putBoolean("chain_lightning_applied", true);
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.getPersistentData().remove("chain_lightning_applied");
                player.getPersistentData().remove("procChainLightning");
            }
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Melee Weapons").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("Blessing: Chain Lightning").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" On crits, the damage").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" bounces to the closest").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" monster within a 4 block").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" radius of the target for").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" half the main damage.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" The damage keeps bouncing").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" until the damage is less").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" than 1 and the range doesn't").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" expand upon bounce.").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> BLOODTHIRST = ITEMS.register(
            "bloodthirst",
            () -> new BloodthirstItem(new Item.Properties().stacksTo(64))
    );

    private static class BloodthirstItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public BloodthirstItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Handle of Bloodthirst").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.getPersistentData().putBoolean("berserk_applied", true);
                if (!player.hasEffect(ModEffects.BERSERK_TIMER.get())) {
                    player.addEffect(new MobEffectInstance(ModEffects.BERSERK_TIMER.get(), 20*15, 0, false, false, true));
                }
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.removeEffect(ModEffects.BERSERK.get());
                player.removeEffect(ModEffects.BERSERK_TIMER.get());
                player.removeEffect(ModEffects.BERSERK_READY.get());
                player.getPersistentData().remove("berserk_applied");
            }
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Melee Weapons").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("Blessing: Berserk").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" On kills, every 2nd hit,").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" and every 15 seconds, gain").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Berserk effect which causes").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" your next melee attack to be").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" increased again by 60% of").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" your Melee Potency stat.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Stack Duration: ").withStyle(ChatFormatting.DARK_AQUA)
                            .append(Component.literal("4s").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(
                    Component.literal("Berserk Duration: ").withStyle(ChatFormatting.GREEN)
                            .append(Component.literal("6s").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(Component.literal(" Berserk does not expire when").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" gained from the 15s timer.").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> BRANCH_OF_THE_WORLD_TREE = ITEMS.register(
            "branch_of_the_world_tree",
            () -> new BranchOfTheWorldTreeItem(new Item.Properties().stacksTo(64))
    );

    private static class BranchOfTheWorldTreeItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public BranchOfTheWorldTreeItem(Properties properties) {
            super(properties);
        }

        @Override
        public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
            if (!(slotContext.entity() instanceof Player player)) return true;
            return !player.hasEffect(ModEffects.BRONZEWOOD_COOLDOWN.get());
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player && !player.hasEffect(ModEffects.BRONZEWOOD_COOLDOWN.get())) {
                player.addEffect(new MobEffectInstance(ModEffects.BRONZEWOOD_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.removeEffect(ModEffects.BRONZEWOOD_COOLDOWN.get());
                player.removeEffect(ModEffects.BRONZEWOOD_READY.get());

                player.getPersistentData().remove("bronzewood_proc");
            }
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Branch of the World Tree").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Melee Weapons").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("Blessing: Bronzewood's Curse").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Attacking a target curses").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" them, increasing their damage").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" damage taken by 10% & causes").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" them to take 1 Melee Damage").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" per second for 10 seconds.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Additionally, Damage +3 when").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" cursing a target.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Cooldown is reset upon").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" killing a monster.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("20s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> HANKS_OTHER_EYE = ITEMS.register(
            "hanks_other_eye",
            () -> new HanksOtherEyeItem(new Item.Properties().stacksTo(64))
    );

    private static class HanksOtherEyeItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public HanksOtherEyeItem(Properties properties) {
            super(properties);
        }

        @Override
        public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
            if (!(slotContext.entity() instanceof Player player)) return true;
            return !player.hasEffect(ModEffects.CLAIRVOYANCE_COOLDOWN.get());
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Hank's Other Eye").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player && !player.hasEffect(ModEffects.CLAIRVOYANCE_COOLDOWN.get())) {
                player.addEffect(new MobEffectInstance(ModEffects.CLAIRVOYANCE_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.removeEffect(ModEffects.CLAIRVOYANCE_READY.get());
                player.removeEffect(ModEffects.CLAIRVOYANCE_COOLDOWN.get());
                player.getPersistentData().remove("clairvoyance_sound_played");
            }
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Bows").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("Blessing: Clairvoyance").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Charging your bow over").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 3 seconds (scales with").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Nock Haste) causes your").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" next shot to be increased").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" by Projectile Potency 4").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" more times.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("20s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> LIFE_TOUCH = ITEMS.register(
            "life_touch",
            () -> new LifeTouchItem(new Item.Properties().stacksTo(64))
    );

    private static class LifeTouchItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public LifeTouchItem(Properties properties) {
            super(properties);
        }

        @Override
        public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
            if (!(slotContext.entity() instanceof Player player)) return true;
            return !player.hasEffect(ModEffects.LIFE_TOUCH_COOLDOWN.get());
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.getPersistentData().putBoolean("life_touch_applied", true);

                if (!player.hasEffect(ModEffects.LIFE_TOUCH_COOLDOWN.get())) {
                    player.addEffect(new MobEffectInstance(ModEffects.LIFE_TOUCH_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
                }
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.removeEffect(ModEffects.LIFE_TOUCH_READY.get());
                player.removeEffect(ModEffects.LIFE_TOUCH_COOLDOWN.get());
                player.getPersistentData().remove("life_touch_applied");
            }
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Life Touch").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Bows").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("Blessing: Spirit Grove").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Your arrows now heal").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" other players for 40% of").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" the Damage done and apply").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Regeneration I for 5 seconds.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Additionally, when shooting.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" a block, spawn Spirit Forest,").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" which applies Regeneration I").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" to targets within a 3 block").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" radius. Spirit Forest range").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" increased by Amplification.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Initial heal is increased by").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Restoration stat.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Spirit Forest Duration: ").withStyle(ChatFormatting.DARK_AQUA)
                            .append(Component.literal("10s").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(
                    Component.literal("Spirit Forest Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("22s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> CUPIDS_ARROW = ITEMS.register(
            "cupids_arrow",
            () -> new CupidsArrowItem(new Item.Properties().stacksTo(64))
    );

    private static class CupidsArrowItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public CupidsArrowItem(Properties properties) {
            super(properties);
        }

        @Override
        public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
            if (!(slotContext.entity() instanceof Player player)) return true;
            return !player.hasEffect(ModEffects.DECEPTION_COOLDOWN.get());
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Cupid's Arrow").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player && !player.hasEffect(ModEffects.DECEPTION_COOLDOWN.get())) {
                player.addEffect(new MobEffectInstance(ModEffects.DECEPTION_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.removeEffect(ModEffects.DECEPTION_COOLDOWN.get());
                player.removeEffect(ModEffects.DECEPTION_READY.get());
            }
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Bows").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("Blessing: Deceptive Heart").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Shooting a target causes").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" the monsters within 9").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" blocks to attack the").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" target shot. Bosses can").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" not be deceived.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("14s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> IGNITION_MARK = ITEMS.register(
            "ignition_mark",
            () -> new IgnitionMarkItem(new Item.Properties().stacksTo(64))
    );

    private static class IgnitionMarkItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public IgnitionMarkItem(Properties properties) {
            super(properties);
        }

        @Override
        public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
            return true;
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Ignition Mark").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player && !player.hasEffect(ModEffects.EXPLOSIVE_TENDENCY_TIMER.get())) {
                player.addEffect(new MobEffectInstance(ModEffects.EXPLOSIVE_TENDENCY_TIMER.get(), 20*8, 0, false, false, true));
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.removeEffect(ModEffects.EXPLOSIVE_TENDENCY_TIMER.get());
                player.removeEffect(ModEffects.EXPLOSIVE_TENDENCY_STACK.get());
            }
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Crossbows").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("Blessing: Explosive Tendencies").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Shooting a target causes").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" a Creeper to spawn around").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" it and explode. You may").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" gain up to 3 stacks, stack").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" count increases every 8s.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" These Creepers only damage").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" monsters, don't break blocks").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" and don't apply knockback.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Creeper damage scales with").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" player's Projectile stats.").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> WEAK_POINT_STUDIES = ITEMS.register(
            "weak_point_studies",
            () -> new WeakPointStudiesItem(new Item.Properties().stacksTo(64))
    );

    private static class WeakPointStudiesItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public WeakPointStudiesItem(Properties properties) {
            super(properties);
        }

        @Override
        public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
            if (!(slotContext.entity() instanceof Player player)) return true;
            return !player.hasEffect(ModEffects.EXPLOIT_WEAKNESS_COOLDOWN.get());
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Weak Point Studies").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player && !player.hasEffect(ModEffects.EXPLOIT_WEAKNESS_COOLDOWN.get())) {
                player.addEffect(new MobEffectInstance(ModEffects.EXPLOIT_WEAKNESS_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.removeEffect(ModEffects.EXPLOIT_WEAKNESS_COOLDOWN.get());
                player.removeEffect(ModEffects.EXPLOIT_WEAKNESS_READY.get());
            }
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Crossbows").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("Blessing: Exploit Weakness").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Shooting a target with").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" a Crossbow increases").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" their damage taken by").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 20% for 10s.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Debuff amount increased").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" by 0.2% per Dexterity.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("20s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> ENDLESS_ADRENALINE_SYRINGE = ITEMS.register(
            "endless_adrenaline_syringe",
            () -> new EndlessAdrenalineSyringeItem(new Item.Properties().stacksTo(64))
    );

    private static class EndlessAdrenalineSyringeItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public EndlessAdrenalineSyringeItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Endless Adrenaline Syringe").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player && !player.hasEffect(ModEffects.ADRENALINE_INJECTION_COOLDOWN.get())) {
                player.addEffect(new MobEffectInstance(ModEffects.ADRENALINE_INJECTION_COOLDOWN.get(), 20*15, 0, false, false, true));
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.removeEffect(ModEffects.ADRENALINE_INJECTION_COOLDOWN.get());
                player.removeEffect(ModEffects.ADRENALINE_INJECTION_UP.get());
            }
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Crossbows").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("Blessing: Adrenaline Injection").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Every 20 seconds, increase").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" your Crossbow draw time is").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" halved for 5 seconds.").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> FORTRESS_OF_SOLITUDE = ITEMS.register(
            "fortress_of_solitude",
            () -> new FortressOfSolitudeItem(new Item.Properties().stacksTo(64))
    );

    private static class FortressOfSolitudeItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public FortressOfSolitudeItem(Properties properties) {
            super(properties);
        }

        @Override
        public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
            if (!(slotContext.entity() instanceof Player player)) return true;
            return !player.hasEffect(ModEffects.RETALIATE_ACTIVE.get()) && !player.hasEffect(ModEffects.RETALIATE_COOLDOWN.get());
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Fortress of Solitude").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player && !player.hasEffect(ModEffects.RETALIATE_ACTIVE.get())
                && !player.hasEffect(ModEffects.RETALIATE_ACTIVE.get())) {
                player.addEffect(new MobEffectInstance(ModEffects.RETALIATE_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.removeEffect(ModEffects.RETALIATE_COOLDOWN.get());
                player.removeEffect(ModEffects.RETALIATE_READY.get());
                player.removeEffect(ModEffects.RETALIATE_ACTIVE.get());
            }
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Shields").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("Blessing: Retaliate").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" On raising a shield, apply").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Retaliate effect for 4 seconds.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" When the effect ends, deal").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 2 Melee Damage within a 5 block").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" radius for every time you block").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" an attack. Damage increased by").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 1% per Fortitude ").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("11s").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(Component.literal(" Cooldown starts after").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" the effect ends.").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> GUARDIAN_ANGEL = ITEMS.register(
            "guardian_angel",
            () -> new GuardianAngelItem(new Item.Properties().stacksTo(64))
    );

    private static class GuardianAngelItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public GuardianAngelItem(Properties properties) {
            super(properties);
        }

        @Override
        public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
            if (!(slotContext.entity() instanceof Player player)) return true;
            return !player.hasEffect(ModEffects.BASTION_ACTIVE.get()) && !player.hasEffect(ModEffects.BASTION_COOLDOWN.get());
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player && !player.hasEffect(ModEffects.BASTION_COOLDOWN.get())
                    && !player.hasEffect(ModEffects.BASTION_ACTIVE.get())) {
                player.addEffect(new MobEffectInstance(ModEffects.BASTION_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.removeEffect(ModEffects.BASTION_ACTIVE.get());
                player.removeEffect(ModEffects.BASTION_READY.get());
                player.removeEffect(ModEffects.BASTION_COOLDOWN.get());
            }
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Guardian Angel").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Shields").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("Blessing: Bastion").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" When releasing your shield,").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" take no damage for 1s.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Duration increased by").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 1% per Fortitude and 0.66%").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" per Perception & Constitution.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("10s").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(Component.literal(" Cooldown starts after").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" the effect ends.").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> BOTTLED_LIGHT = ITEMS.register(
            "bottled_light",
            () -> new BottledLightItem(new Item.Properties().stacksTo(64))
    );

    private static class BottledLightItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public BottledLightItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Bottled Light").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.getPersistentData().putBoolean("lightbringer_applied", true);
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.getPersistentData().remove("lightbringer_applied");
            }
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Pickaxes").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("Blessing: Lightbringer").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Right clicking with a").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Pickaxe places a torch").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" but deals 3 Magic Damage.").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> PRIDE_INFUSED_AIGRETTE = ITEMS.register(
            "pride_infused_aigrette",
            () -> new PrideInfusedAigretteItem(new Item.Properties().stacksTo(64))
    );

    private static class PrideInfusedAigretteItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public PrideInfusedAigretteItem(Properties properties) {
            super(properties);
        }

        @Override
        public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
            if (!(slotContext.entity() instanceof Player player)) return true;
            return !player.hasEffect(ModEffects.OVERCONFIDENCE_ACTIVE.get()) && !player.hasEffect(ModEffects.OVERCONFIDENCE_COOLDOWN.get());
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Pride Infused Aigrette").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player && !player.hasEffect(ModEffects.OVERCONFIDENCE_READY.get())
                    && !player.hasEffect(ModEffects.OVERCONFIDENCE_ACTIVE.get())) {
                player.addEffect(new MobEffectInstance(ModEffects.OVERCONFIDENCE_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.removeEffect(ModEffects.OVERCONFIDENCE_ACTIVE.get());
                player.removeEffect(ModEffects.OVERCONFIDENCE_READY.get());
                player.removeEffect(ModEffects.OVERCONFIDENCE_COOLDOWN.get());
            }
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Blessing: Overconfidence").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" On jump crit, double your").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" damage but disable healing").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" received.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Doubled Damage Duration: ").withStyle(ChatFormatting.DARK_AQUA)
                            .append(Component.literal("8s").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(
                    Component.literal("Heal Disable Duration: ").withStyle(ChatFormatting.DARK_RED)
                            .append(Component.literal("24s").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("7s").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(Component.literal(" Cooldown starts after").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" the damage effect ends.").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> IMMOLATION_OF_HEART = ITEMS.register(
            "immolation_of_heart",
            () -> new ImmolationOfHeartItem(new Item.Properties().stacksTo(64))
    );
    private static final UUID IMMOLATION_ARMOR = UUID.fromString("d739268d-e62f-4c9b-8301-2895473f3281");

    private static class ImmolationOfHeartItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public ImmolationOfHeartItem(Properties properties) {
            super(properties);
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.applyModifier(player, Attributes.ARMOR, -10.0, IMMOLATION_ARMOR);
                player.getPersistentData().putBoolean("entwined_offering_eligible", true);

            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                AttributeApplier.removeModifier(player, Attributes.ARMOR, IMMOLATION_ARMOR);
                player.getPersistentData().remove("entwined_offering_eligible");
            }
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Immolation of Heart").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("-10 Armor").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Blessing: Entwined Offering").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" 30% of healing received").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" when not full HP is applied").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" to allies within 8 blocks.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Allies healed by this effect").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" gain +8 Armor.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Does not apply to self.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Range and buff duration").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" increased by Amplification.").withStyle(ChatFormatting.GRAY));

            tooltip.add(
                    Component.literal("Armor Buff Duration: ").withStyle(ChatFormatting.DARK_AQUA)
                            .append(Component.literal("5s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> FOX_EYE = ITEMS.register(
            "fox_eye",
            () -> new FoxEyeItem(new Item.Properties().stacksTo(64))
    );

    private static class FoxEyeItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public FoxEyeItem(Properties properties) {
            super(properties);
        }

        @Override
        public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
            if (!(slotContext.entity() instanceof Player player)) return true;
            return !player.hasEffect(ModEffects.NIMBLE_GETAWAY_COOLDOWN.get());
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Fox Eye").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player && !player.hasEffect(ModEffects.NIMBLE_GETAWAY_COOLDOWN.get())) {
                player.addEffect(new MobEffectInstance(ModEffects.NIMBLE_GETAWAY_ACTIVE.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.removeEffect(ModEffects.NIMBLE_GETAWAY_ACTIVE.get());
                player.removeEffect(ModEffects.NIMBLE_GETAWAY_COOLDOWN.get());
            }
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Blessing: Nimble Getaway").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Every 30 seconds, gain an").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" effect that allows you to").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" dodge the next attack.").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> BROKEN_HEALTH_POTION = ITEMS.register(
            "broken_health_potion",
            () -> new BrokenHealthPotionItem(new Item.Properties().stacksTo(64))
    );

    private static class BrokenHealthPotionItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public BrokenHealthPotionItem(Properties properties) {
            super(properties);
        }

        @Override
        public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
            if (!(slotContext.entity() instanceof Player player)) return true;
            return !player.hasEffect(ModEffects.COMBATANTS_AID_CD.get());
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Broken Health Potion").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player && !player.hasEffect(ModEffects.COMBATANTS_AID_CD.get())) {
                player.addEffect(new MobEffectInstance(ModEffects.COMBATANTS_AID_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.removeEffect(ModEffects.COMBATANTS_AID_CD.get());
                player.removeEffect(ModEffects.COMBATANTS_AID_READY.get());
            }
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Blessing: Combatant's Aid").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" When you crouch while").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" sprinting, perform an 8").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" block dash and heal allies").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" within 4 blocks by 4 and").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" apply Resistance I.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Crouch for a second within").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 4 seconds of the dash to").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" return to original location.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Resistance Buff Duration: ").withStyle(ChatFormatting.DARK_AQUA)
                            .append(Component.literal("5s").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("40s").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(Component.literal(" Cooldown speed increased").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" by Amplification.").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> RESTORING_AURA = ITEMS.register(
            "restoring_aura",
            () -> new RestoringAuraItem(new Item.Properties().stacksTo(64))
    );

    private static class RestoringAuraItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public RestoringAuraItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Restoring Aura").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player && !player.hasEffect(ModEffects.RADIATING_WARMTH.get())) {

                int interval = (int) (20*20 / (1.0 + (AttributeApplier.getScaledValue(player, ModAttributes.AMPLIFICATION.get(), ModAttributes.AMPLIFICATION_MULTIPLIER.get()) / 100.0)));
                player.addEffect(new MobEffectInstance(ModEffects.RADIATING_WARMTH.get(), interval, 0, false, false, true));
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.removeEffect(ModEffects.RADIATING_WARMTH.get());
            }
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Blessing: Radiating Warmth").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Every 20 seconds, heal").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" allies within 8 blocks").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" for 2.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Interval reduced by").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Amplification.").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> DIVINE_SHIELD = ITEMS.register(
            "divine_shield",
            () -> new DivineShieldItem(new Item.Properties().stacksTo(64))
    );

    private static class DivineShieldItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public DivineShieldItem(Properties properties) {
            super(properties);
        }

        @Override
        public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
            if (!(slotContext.entity() instanceof Player player)) return true;
            return !player.hasEffect(ModEffects.SANCTUARY_FATIGUE.get());
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.getPersistentData().putBoolean("sanctuary_eligible", true);
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.getPersistentData().remove("sanctuary_eligible");
            }
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Divine Shield").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Shields").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("Blessing: Sanctuary").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" After blocking for 1").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" second and heal allies ").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" within 6 blocks for 1.25").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" every second and reduce").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" their damage taken by 10%").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" for 1 second. Healing").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" effect reduced by 10%").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" per tick, up to 90%.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Range increased by").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Amplification.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Heal Fatigue Duration: ").withStyle(ChatFormatting.DARK_RED)
                            .append(Component.literal("10s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> NATURES_BLESSING = ITEMS.register(
            "natures_blessing",
            () -> new NaturesBlessingItem(new Item.Properties().stacksTo(64))
    );

    private static class NaturesBlessingItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public NaturesBlessingItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Nature's Blessing").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.getPersistentData().putBoolean("pulsating_love_eligible", true);
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.getPersistentData().remove("pulsating_love_eligible");
            }
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Blessing: Pulsating Love").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Every 5s, apply").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Regeneration 2 and").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Resistance 2 for 30s to").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" tamed pets within 10 blocks.").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> SUMMONING_STONE = ITEMS.register(
            "summoning_stone",
            () -> new SummoningStoneItem(new Item.Properties().stacksTo(64))
    );

    private static class SummoningStoneItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public SummoningStoneItem(Properties properties) {
            super(properties);
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.getPersistentData().putBoolean("summoning_stone_eligible", true);

                if (player.level() instanceof ServerLevel serverLevel
                        && !player.hasEffect(ModEffects.SOLARA.get())
                        && !player.hasEffect(ModEffects.SBEVE_CD.get())) {
                    SummoningStone.summonSbeve(serverLevel, player);
                }
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.getPersistentData().remove("summoning_stone_eligible");

                if (player.level() instanceof ServerLevel serverLevel) {
                    SummoningStone.killSbeve(serverLevel, player);
                }
            }
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Summoning Stone").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Blessing: Sbeve").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Every 5 minutes, summon").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" a companion to fight for you.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Sbeve's damage scales with").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" owner's Potency, Accuracy").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" and Precision stats and").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" HP scales with owner's HP.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Base Damage: ").withStyle(ChatFormatting.DARK_AQUA)
                            .append(Component.literal("2.5").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(
                    Component.literal("HP: ").withStyle(ChatFormatting.DARK_AQUA)
                            .append(Component.literal("3x").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(Component.literal("Note: He is incredibly dumb").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
    }

    public static final RegistryObject<Item> WIND_WALKER_ARROW = ITEMS.register(
            "wind_walker_arrow",
            () -> new WindWalkerArrowItem(new Item.Properties().stacksTo(64))
    );

    private static class WindWalkerArrowItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public WindWalkerArrowItem(Properties properties) {
            super(properties);
        }

        @Override
        public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
            if (!(slotContext.entity() instanceof Player player)) return true;
            return !player.hasEffect(ModEffects.VORTEX_CD.get());
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Wind Walker's Arrow").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player && !player.hasEffect(ModEffects.VORTEX_CD.get())) {
                player.addEffect(new MobEffectInstance(ModEffects.VORTEX_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.removeEffect(ModEffects.VORTEX_CD.get());
                player.removeEffect(ModEffects.VORTEX_READY.get());
            }
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Bows").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("Blessing: Vortex").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Upon shooting the ground,").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" pull monsters within 4").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" blocks to the arrow for").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 4 seconds and deal 3").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Ranged Damage per second.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("18s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> VIAL_OF_BURSTING_ENERGY = ITEMS.register(
            "vial_of_bursting_energy",
            () -> new BurstingEnergyVialItem(new Item.Properties().stacksTo(64))
    );

    private static class BurstingEnergyVialItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public BurstingEnergyVialItem(Properties properties) {
            super(properties);
        }

        @Override
        public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
            if (!(slotContext.entity() instanceof Player player)) return true;
            return !player.hasEffect(ModEffects.FOCUSED_ENERGY_CD.get());
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Vial of Bursting Energy").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player && !player.hasEffect(ModEffects.FOCUSED_ENERGY_CD.get())) {
                player.addEffect(new MobEffectInstance(ModEffects.FOCUSED_ENERGY_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.removeEffect(ModEffects.FOCUSED_ENERGY_CD.get());
                player.removeEffect(ModEffects.FOCUSED_ENERGY_READY.get());
            }
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Crossbows").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("Blessing: Focused Energy").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Launch yourself when").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" shooting the ground.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("10s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> THORN_COVERED_FORCEFIELD = ITEMS.register(
            "thorn_covered_forcefield",
            () -> new ThornCoveredForceFieldItem(new Item.Properties().stacksTo(64))
    );

    private static class ThornCoveredForceFieldItem extends Item implements top.theillusivec4.curios.api.type.capability.ICurioItem {
        public ThornCoveredForceFieldItem(Properties properties) {
            super(properties);
        }

        @Override
        public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
            if (!(slotContext.entity() instanceof Player player)) return true;
            return !player.hasEffect(ModEffects.THORNED_PARRY_CD.get());
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Thorn Covered Force Field").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.getPersistentData().putBoolean("thorn_eligible", true);

                if (!player.hasEffect(ModEffects.THORNED_PARRY_CD.get())) {
                    player.addEffect(new MobEffectInstance(ModEffects.THORNED_PARRY_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
                }
            }
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
            if (slotContext.entity() instanceof Player player) {
                player.removeEffect(ModEffects.THORNED_PARRY_CD.get());
                player.removeEffect(ModEffects.THORNED_PARRY_READY.get());
                player.getPersistentData().remove("thorn_eligible");
            }
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Shields").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("Blessing: Thorned Parry").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" When releasing your shield").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 0.4s within a block, deal").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 3 Melee Damage around you").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" and stun your target for").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 2 seconds.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("7s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

//    public static final RegistryObject<Item> HOLLOW_STONE = ITEMS.register(
//            "hollow_stone",
//            () -> new Item(new Item.Properties().stacksTo(64)) {
//                @Override
//                public Component getName(ItemStack stack) {
//                    return Component.literal("Hollow Stone").withStyle(ChatFormatting.RED);
//                }
//
//                @Override
//                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
//                    super.appendHoverText(stack, level, tooltip, flag);
//                    tooltip.add(Component.literal(""));
//                    tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
//                    tooltip.add(Component.literal(" Armors & Tools").withStyle(ChatFormatting.BLUE));
//                    tooltip.add(Component.literal(" An empty stone that is").withStyle(ChatFormatting.GRAY));
//                    tooltip.add(Component.literal(" absolutely useless rn.").withStyle(ChatFormatting.GRAY));
//                }
//            }
//    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, "coldsmod");

    public static final RegistryObject<Codec<ArmorRarityModifier>> ARMOR_RARITY =
            LOOT_MODIFIERS.register("armor_rarity", () -> ArmorRarityModifier.CODEC);
}
