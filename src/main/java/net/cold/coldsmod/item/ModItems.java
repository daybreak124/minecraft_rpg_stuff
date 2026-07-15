package net.cold.coldsmod.item;

import com.mojang.serialization.Codec;
import net.cold.coldsmod.ColdsMod;
import net.cold.coldsmod.network.Keybinds;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.List;

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
                    tooltip.add(Component.literal("Used in Smithing Tables.").withStyle(ChatFormatting.GRAY));
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
                    tooltip.add(Component.literal("Used in Smithing Tables.").withStyle(ChatFormatting.GRAY));
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
                    tooltip.add(Component.literal("Used in Smithing Tables.").withStyle(ChatFormatting.GRAY));
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
                    tooltip.add(Component.literal("Used in Smithing Tables.").withStyle(ChatFormatting.GRAY));
                }
            }
    );

//    public static final RegistryObject<Item> COMMON_SCRAP_ESSENCE = ITEMS.register(
//            "common_scrap_essence",
//            () -> new Item(new Item.Properties().stacksTo(64)) {
//                @Override
//                public Component getName(ItemStack stack) {
//                    return Component.literal("Common Scrap Essence").withStyle(ChatFormatting.GRAY);
//                }
//
//                @Override
//                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
//                    super.appendHoverText(stack, level, tooltip, flag);
//                    tooltip.add(Component.literal(""));
//                    tooltip.add(
//                            Component.literal("Used to upgrade the rarity of ").withStyle(ChatFormatting.GRAY)
//                                    .append(Component.literal("COMMON").withStyle(ChatFormatting.GRAY))
//                                    .append(Component.literal(" items to ").withStyle(ChatFormatting.GRAY))
//                                    .append(Component.literal("UNCOMMON").withStyle(ChatFormatting.GREEN))
//                    );
//                    tooltip.add(Component.literal("Used in Anvils.").withStyle(ChatFormatting.GRAY));
//                }
//            }
//    );

//    public static final RegistryObject<Item> UNCOMMON_SCRAP_ESSENCE = ITEMS.register(
//            "uncommon_scrap_essence",
//            () -> new Item(new Item.Properties().stacksTo(64)) {
//                @Override
//                public Component getName(ItemStack stack) {
//                    return Component.literal("Uncommon Scrap Essence").withStyle(ChatFormatting.GREEN);
//                }
//
//                @Override
//                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
//                    super.appendHoverText(stack, level, tooltip, flag);
//                    tooltip.add(Component.literal(""));
//                    tooltip.add(
//                            Component.literal("Used to upgrade the rarity of ").withStyle(ChatFormatting.GRAY)
//                                    .append(Component.literal("UNCOMMON").withStyle(ChatFormatting.GREEN))
//                                    .append(Component.literal(" items to ").withStyle(ChatFormatting.GRAY))
//                                    .append(Component.literal("RARE").withStyle(ChatFormatting.BLUE))
//                    );
//                    tooltip.add(Component.literal("Used in Anvils.").withStyle(ChatFormatting.GRAY));
//                }
//            }
//    );
//
//    public static final RegistryObject<Item> RARE_SCRAP_ESSENCE = ITEMS.register(
//            "rare_scrap_essence",
//            () -> new Item(new Item.Properties().stacksTo(64)) {
//                @Override
//                public Component getName(ItemStack stack) {
//                    return Component.literal("Rare Scrap Essence").withStyle(ChatFormatting.BLUE);
//                }
//
//                @Override
//                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
//                    super.appendHoverText(stack, level, tooltip, flag);
//                    tooltip.add(Component.literal("")); //
//                    tooltip.add(
//                            Component.literal("Used to upgrade the rarity of ").withStyle(ChatFormatting.GRAY)
//                                    .append(Component.literal("RARE").withStyle(ChatFormatting.BLUE))
//                                    .append(Component.literal(" items to ").withStyle(ChatFormatting.GRAY))
//                                    .append(Component.literal("EPIC").withStyle(ChatFormatting.DARK_PURPLE))
//                    );
//                    tooltip.add(Component.literal("Used in Anvils.").withStyle(ChatFormatting.GRAY));
//                }
//            }
//    );

    public static final RegistryObject<Item> SCRAP_ESSENCE = ITEMS.register(
            "scrap_essence",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Scrap Essence").withStyle(ChatFormatting.RED);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal(""));
                    tooltip.add(
                            Component.literal("Used to upgrade utility stats.").withStyle(ChatFormatting.GRAY));
                }
            }
    );

//    public static final RegistryObject<Item> LEGENDARY_SCRAP_ESSENCE = ITEMS.register(
//            "legendary_scrap_essence",
//            () -> new Item(new Item.Properties().stacksTo(64)) {
//                @Override
//                public Component getName(ItemStack stack) {
//                    return Component.literal("Legendary Scrap Essence").withStyle(ChatFormatting.GOLD);
//                }
//
//                @Override
//                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
//                    super.appendHoverText(stack, level, tooltip, flag);
//                    tooltip.add(Component.literal(""));
//                    tooltip.add(
//                            Component.literal("Used to upgrade the rarity of ").withStyle(ChatFormatting.GRAY)
//                                    .append(Component.literal("LEGENDARY").withStyle(ChatFormatting.GOLD))
//                                    .append(Component.literal(" items to ").withStyle(ChatFormatting.GRAY))
//                                    .append(Component.literal("MYTHIC").withStyle(ChatFormatting.AQUA))
//                    );
//                    tooltip.add(Component.literal("Used in Anvils.").withStyle(ChatFormatting.GRAY));
//                }
//            }
//    );
//
//    public static final RegistryObject<Item> PEARL_OF_REPLENISHING = ITEMS.register(
//            "pearl_of_replenishing",
//            () -> new Item(new Item.Properties().stacksTo(64)) {
//                @Override
//                public Component getName(ItemStack stack) {
//                    return Component.literal("Pearl of Replenishing").withStyle(ChatFormatting.GRAY);
//                }
//
//                @Override
//                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
//                    super.appendHoverText(stack, level, tooltip, flag);
//                    tooltip.add(Component.literal(""));
//                    tooltip.add(
//                            Component.literal("Used to re-roll the stats of ").withStyle(ChatFormatting.GRAY)
//                                    .append(Component.literal("COMMON").withStyle(ChatFormatting.GRAY))
//                                    .append(Component.literal(" items").withStyle(ChatFormatting.GRAY))
//                    );
//                    tooltip.add(Component.literal("Used in Anvils.").withStyle(ChatFormatting.GRAY));
//                }
//            }
//    );
//
//    public static final RegistryObject<Item> PEARL_OF_RECHARGING = ITEMS.register(
//            "pearl_of_recharging",
//            () -> new Item(new Item.Properties().stacksTo(64)) {
//                @Override
//                public Component getName(ItemStack stack) {
//                    return Component.literal("Pearl of Recharging").withStyle(ChatFormatting.GREEN);
//                }
//
//                @Override
//                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
//                    super.appendHoverText(stack, level, tooltip, flag);
//                    tooltip.add(Component.literal("")); //
//                    tooltip.add(
//                            Component.literal("Used to re-roll the stats of ").withStyle(ChatFormatting.GRAY)
//                                    .append(Component.literal("UNCOMMON").withStyle(ChatFormatting.GREEN))
//                                    .append(Component.literal(" items").withStyle(ChatFormatting.GRAY))
//                    );
//                    tooltip.add(Component.literal("Used in Anvils.").withStyle(ChatFormatting.GRAY));
//                }
//            }
//    );
//
//    public static final RegistryObject<Item> PEARL_OF_RENEWING = ITEMS.register(
//            "pearl_of_renewing",
//            () -> new Item(new Item.Properties().stacksTo(64)) {
//                @Override
//                public Component getName(ItemStack stack) {
//                    return Component.literal("Pearl of Renewing").withStyle(ChatFormatting.BLUE);
//                }
//
//                @Override
//                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
//                    super.appendHoverText(stack, level, tooltip, flag);
//                    tooltip.add(Component.literal("")); //
//                    tooltip.add(
//                            Component.literal("Used to re-roll the stats of ").withStyle(ChatFormatting.GRAY)
//                                    .append(Component.literal("RARE").withStyle(ChatFormatting.BLUE))
//                                    .append(Component.literal(" items").withStyle(ChatFormatting.GRAY))
//                    );
//                    tooltip.add(Component.literal("Used in Anvils.").withStyle(ChatFormatting.GRAY));
//                }
//            }
//    );
//
//    public static final RegistryObject<Item> PEARL_OF_RESTORING = ITEMS.register(
//            "pearl_of_restoring",
//            () -> new Item(new Item.Properties().stacksTo(64)) {
//                @Override
//                public Component getName(ItemStack stack) {
//                    return Component.literal("Pearl of Restoring").withStyle(ChatFormatting.DARK_PURPLE);
//                }
//
//                @Override
//                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
//                    super.appendHoverText(stack, level, tooltip, flag);
//                    tooltip.add(Component.literal("")); //
//                    tooltip.add(
//                            Component.literal("Used to re-roll the stats of ").withStyle(ChatFormatting.GRAY)
//                                    .append(Component.literal("EPIC").withStyle(ChatFormatting.DARK_PURPLE))
//                                    .append(Component.literal(" items").withStyle(ChatFormatting.GRAY))
//                    );
//                    tooltip.add(Component.literal("Used in Anvils.").withStyle(ChatFormatting.GRAY));
//                }
//            }
//    );
//
//    public static final RegistryObject<Item> PEARL_OF_REJUVENATING = ITEMS.register(
//            "pearl_of_rejuvenating",
//            () -> new Item(new Item.Properties().stacksTo(64)) {
//                @Override
//                public Component getName(ItemStack stack) {
//                    return Component.literal("Pearl of Rejuvenating").withStyle(ChatFormatting.GOLD);
//                }
//
//                @Override
//                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
//                    super.appendHoverText(stack, level, tooltip, flag);
//                    tooltip.add(Component.literal("")); //
//                    tooltip.add(
//                            Component.literal("Used to re-roll the stats of ").withStyle(ChatFormatting.GRAY)
//                                    .append(Component.literal("LEGENDARY").withStyle(ChatFormatting.GOLD))
//                                    .append(Component.literal(" items").withStyle(ChatFormatting.GRAY))
//                    );
//                    tooltip.add(Component.literal("Used in Anvils.").withStyle(ChatFormatting.GRAY));
//                }
//            }
//    );

    public static final RegistryObject<Item> PEARL_OF_REVITALIZING = ITEMS.register(
            "pearl_of_revitalizing",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Pearl of Revitalizing").withStyle(ChatFormatting.RED);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal(""));
                    tooltip.add(
                            Component.literal("Used to upgrade stats ").withStyle(ChatFormatting.GRAY));
                }
            }
    );

//    public static final RegistryObject<Item> SHARD_OF_INFUSION = ITEMS.register(
//            "shard_of_infusion",
//            () -> new Item(new Item.Properties().stacksTo(64)) {
//                @Override
//                public Component getName(ItemStack stack) {
//                    return Component.literal("Shard of Infusion").withStyle(ChatFormatting.GRAY);
//                }
//
//                @Override
//                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
//                    super.appendHoverText(stack, level, tooltip, flag);
//                    tooltip.add(Component.literal("")); //
//                    tooltip.add(
//                            Component.literal("Used to re-roll the attributes of ").withStyle(ChatFormatting.GRAY)
//                                    .append(Component.literal("COMMON").withStyle(ChatFormatting.GRAY))
//                                    .append(Component.literal(" items").withStyle(ChatFormatting.GRAY))
//                    );
//                    tooltip.add(Component.literal("Used in Anvils.").withStyle(ChatFormatting.GRAY));
//                }
//            }
//    );
//
//    public static final RegistryObject<Item> SHARD_OF_AUGMENTATION = ITEMS.register(
//            "shard_of_augmentation",
//            () -> new Item(new Item.Properties().stacksTo(64)) {
//                @Override
//                public Component getName(ItemStack stack) {
//                    return Component.literal("Shard of Augmentation").withStyle(ChatFormatting.GREEN);
//                }
//
//                @Override
//                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
//                    super.appendHoverText(stack, level, tooltip, flag);
//                    tooltip.add(Component.literal("")); //
//                    tooltip.add(
//                            Component.literal("Used to re-roll the attributes of ").withStyle(ChatFormatting.GRAY)
//                                    .append(Component.literal("UNCOMMON").withStyle(ChatFormatting.GREEN))
//                                    .append(Component.literal(" items").withStyle(ChatFormatting.GRAY))
//                    );
//                    tooltip.add(Component.literal("Used in Anvils.").withStyle(ChatFormatting.GRAY));
//                }
//            }
//    );
//
//    public static final RegistryObject<Item> SHARD_OF_AMPLIFICATION = ITEMS.register(
//            "shard_of_amplification",
//            () -> new Item(new Item.Properties().stacksTo(64)) {
//                @Override
//                public Component getName(ItemStack stack) {
//                    return Component.literal("Shard of Amplification").withStyle(ChatFormatting.BLUE);
//                }
//
//                @Override
//                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
//                    super.appendHoverText(stack, level, tooltip, flag);
//                    tooltip.add(Component.literal("")); //
//                    tooltip.add(
//                            Component.literal("Used to re-roll the attributes of ").withStyle(ChatFormatting.GRAY)
//                                    .append(Component.literal("RARE").withStyle(ChatFormatting.BLUE))
//                                    .append(Component.literal(" items").withStyle(ChatFormatting.GRAY))
//                    );
//                    tooltip.add(Component.literal("Used in Anvils.").withStyle(ChatFormatting.GRAY));
//                }
//            }
//    );
//
//    public static final RegistryObject<Item> SHARD_OF_EMPOWERMENT = ITEMS.register(
//            "shard_of_empowerment",
//            () -> new Item(new Item.Properties().stacksTo(64)) {
//                @Override
//                public Component getName(ItemStack stack) {
//                    return Component.literal("Shard of Empowerment").withStyle(ChatFormatting.DARK_PURPLE);
//                }
//
//                @Override
//                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
//                    super.appendHoverText(stack, level, tooltip, flag);
//                    tooltip.add(Component.literal("")); //
//                    tooltip.add(
//                            Component.literal("Used to re-roll the attributes of ").withStyle(ChatFormatting.GRAY)
//                                    .append(Component.literal("EPIC").withStyle(ChatFormatting.DARK_PURPLE))
//                                    .append(Component.literal(" items").withStyle(ChatFormatting.GRAY))
//                    );
//                    tooltip.add(Component.literal("Used in Anvils.").withStyle(ChatFormatting.GRAY));
//                }
//            }
//    );
//
//    public static final RegistryObject<Item> SHARD_OF_ASCENDANCY = ITEMS.register(
//            "shard_of_ascendancy",
//            () -> new Item(new Item.Properties().stacksTo(64)) {
//                @Override
//                public Component getName(ItemStack stack) {
//                    return Component.literal("Shard of Ascendancy").withStyle(ChatFormatting.GOLD);
//                }
//
//                @Override
//                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
//                    super.appendHoverText(stack, level, tooltip, flag);
//                    tooltip.add(Component.literal("")); //
//                    tooltip.add(
//                            Component.literal("Used to re-roll the attributes of ").withStyle(ChatFormatting.GRAY)
//                                    .append(Component.literal("LEGENDARY").withStyle(ChatFormatting.GOLD))
//                                    .append(Component.literal(" items").withStyle(ChatFormatting.GRAY))
//                    );
//                    tooltip.add(Component.literal("Used in Anvils.").withStyle(ChatFormatting.GRAY));
//                }
//            }
//    );

    public static final RegistryObject<Item> PEARL_ICON = ITEMS.register("pearl_icon",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ORB_ICON = ITEMS.register("orb_icon",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SHARD_OF_TRANSCENDENCE = ITEMS.register(
            "shard_of_transcendence",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Shard of Transcendence").withStyle(ChatFormatting.RED);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal(""));
                    tooltip.add(
                            Component.literal("Used to upgrade attributes").withStyle(ChatFormatting.GRAY));
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
                    tooltip.add(Component.literal("Used in Smithing Tables.").withStyle(ChatFormatting.GRAY));
                }
            }
    );

    public static final RegistryObject<Item> WARLORDS_GAZE = ITEMS.register(
            "warlords_gaze",
            () -> new WarlordsGazeItem(new Item.Properties().stacksTo(64))
    );

    private static class WarlordsGazeItem extends Item {
        public WarlordsGazeItem(Properties properties) {
            super(properties);
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
            tooltip.add(Component.literal(" Activate to hang, then pull").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" monsters within 8 blocks to").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" you.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Shortly after, explode and").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" deal 8 Melee Damage and knock").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" them away.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" ").withStyle(ChatFormatting.GRAY));

            Component keyName = Keybinds.intimidateKey.getTranslatedKeyMessage();
            tooltip.add(Component.literal(" Activation: ").withStyle(ChatFormatting.GRAY)
                    .append(keyName.copy().withStyle(ChatFormatting.YELLOW)));
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("18s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> HORN_OF_FEARMONGERING = ITEMS.register(
            "horn_of_fearmongering",
            () -> new HornOfFearmongeringItem(new Item.Properties().stacksTo(64))
    );

    private static class HornOfFearmongeringItem extends Item {
        public HornOfFearmongeringItem(Properties properties) {
            super(properties);
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
            tooltip.add(Component.literal(" Activate to stun targets").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" within 5 blocks for").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 3 seconds.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Stun duration increased by").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 0.06s per Fortitude and").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 0.03s per Perception.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Duration reduces to 1/3").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" against Bosses.").withStyle(ChatFormatting.GRAY));
            Component keyName = Keybinds.daringShoutKey.getTranslatedKeyMessage();
            tooltip.add(Component.literal(" ").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal(" Activation: ").withStyle(ChatFormatting.GRAY)
                    .append(keyName.copy().withStyle(ChatFormatting.YELLOW)));
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

    private static class HanksEyeItem extends Item {
        public HanksEyeItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Hank's Eye").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Blessing: Hawkeye").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Weapon").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" hits increase your").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Projectile Potency by 5").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" and Nock Haste by 11").withStyle(ChatFormatting.GRAY));
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

    private static class SunstoneGemItem extends Item {
        public SunstoneGemItem(Properties properties) {
            super(properties);
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
            tooltip.add(Component.literal(" +12.5 Melee Potency and").withStyle(ChatFormatting.YELLOW));
            tooltip.add(Component.literal(" +5 Armor at noon.").withStyle(ChatFormatting.YELLOW));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal(" -7.5 Melee Potency and").withStyle(ChatFormatting.DARK_GRAY));
            tooltip.add(Component.literal(" -7.5 Armor at midnight.").withStyle(ChatFormatting.DARK_GRAY));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal(" Effect remains for at").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" least 1 Minecraft day.").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> RAGE_AMPLIFIER = ITEMS.register(
            "rage_amplifier",
            () -> new RageAmplifierItem(new Item.Properties().stacksTo(64))
    );

    private static class RageAmplifierItem extends Item {
        public RageAmplifierItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Rage Amplifier").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Blessing: Frenzy").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Increase Attack Damage by").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 1 but increase your damage").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" received by 5%. On hit,").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Attack Damage increases").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" by 0.1 and Damage taken by").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 1% per stack.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Max Stacks: ").withStyle(ChatFormatting.DARK_AQUA)
                            .append(Component.literal("20").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(
                    Component.literal("Duration: ").withStyle(ChatFormatting.GREEN)
                            .append(Component.literal("6s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> DROP_OF_SACRIFICIAL_BLOOD = ITEMS.register(
            "drop_of_sacrificial_blood",
            () -> new DropOfSacrificialBloodItem(new Item.Properties().stacksTo(64))
    );

    private static class DropOfSacrificialBloodItem extends Item {
        public DropOfSacrificialBloodItem(Properties properties) {
            super(properties);
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
            tooltip.add(Component.literal(" RIP").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> HELL_ON_EARTH = ITEMS.register(
            "hell_on_earth",
            () -> new HellOnEarthItem(new Item.Properties().stacksTo(64))
    );

    private static class HellOnEarthItem extends Item {
        public HellOnEarthItem(Properties properties) {
            super(properties);
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
            tooltip.add(Component.literal(" Activate to taunt enemies").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" within 6 blocks and increase").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" their damage taken by 6% for").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 6 seconds.").withStyle(ChatFormatting.GRAY));
            Component keyName = Keybinds.directedHatredKey.getTranslatedKeyMessage();
            tooltip.add(Component.literal(" ").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal(" Activation: ").withStyle(ChatFormatting.GRAY)
                    .append(keyName.copy().withStyle(ChatFormatting.YELLOW)));
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

    private static class BannerOfDeterminationItem extends Item {
        public BannerOfDeterminationItem(Properties properties) {
            super(properties);
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
            tooltip.add(Component.literal(" stack and deal 2 Melee Weapon").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Damage per stack within 4 blocks").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" when colliding with a monster.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Stack count increases").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" every 2 seconds and gain").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Resistance I when reaching").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 5 stacks as long as you are").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" sprinting. Targets collided").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" with are inflicted with").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Slowness 5 for 0.4s per stack").withStyle(ChatFormatting.GRAY));
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

    private static class WormholeItem extends Item {
        public WormholeItem(Properties properties) {
            super(properties);
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
            tooltip.add(Component.literal(" Activate to perform a 10").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" block long teleport and turn").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" invisible for 4 seconds. Gain").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" +30 Potency and +20% Movement").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Speed for 8 seconds until you").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" attack a target.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" If used 4 seconds within").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Death From Above; Potency").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" and Move Speed +50%.").withStyle(ChatFormatting.GRAY));
            Component keyName = Keybinds.quantumKey.getTranslatedKeyMessage();
            tooltip.add(Component.literal(" ").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal(" Activation: ").withStyle(ChatFormatting.GRAY)
                    .append(keyName.copy().withStyle(ChatFormatting.YELLOW)));
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

    private static class OrbOfWorldDestructionItem extends Item {
        public OrbOfWorldDestructionItem(Properties properties) {
            super(properties);
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
            tooltip.add(Component.literal(" Activate to shoot yourself").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" up 8 blocks and deal 5").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Melee Damage within 5 blocks.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" On landing, deal 7.5 Melee ").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Damage within 7 blocks and").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" apply a knock-up to enemies.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Cancels fall damage.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Crouching causes a normal jump,").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" halves the landing damage and").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" disables the knock-up.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Disabled while swimming and").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" while airborne.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Activate a second time to").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" dive down.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Reduces Quantum Leap Cooldown").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" by 5 seconds and enhances it").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" for 4 seconds.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" ").withStyle(ChatFormatting.GRAY));


            Component keyName = Keybinds.dfaKey.getTranslatedKeyMessage();

            tooltip.add(Component.literal(" Activation: ").withStyle(ChatFormatting.GRAY)
                    .append(keyName.copy().withStyle(ChatFormatting.YELLOW)));
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("21s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> SOUL_MAGNET = ITEMS.register(
            "soul_magnet",
            () -> new SoulMagnetItem(new Item.Properties().stacksTo(64))
    );

    private static class SoulMagnetItem extends Item {
        public SoulMagnetItem(Properties properties) {
            super(properties);
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
            tooltip.add(Component.literal(" Activate to pull monsters").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" within 9 blocks to you").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Deal 2.5 DoT Damage every").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" second to monsters pulled.").withStyle(ChatFormatting.GRAY));
            Component keyName = Keybinds.severanceKey.getTranslatedKeyMessage();
            tooltip.add(Component.literal(" ").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal(" Activation: ").withStyle(ChatFormatting.GRAY)
                    .append(keyName.copy().withStyle(ChatFormatting.YELLOW)));
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("20s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> LIGHTNING_INFUSION = ITEMS.register(
            "lightning_infusion",
            () -> new LightningInfusionItem(new Item.Properties().stacksTo(64))
    );

    private static class LightningInfusionItem extends Item {
        public LightningInfusionItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Lightning Infusion").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Weapon Attacks").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("Blessing: Chain Lightning").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" The damage bounces to the").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" closest monster within 4").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" blocks of the target for").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 30% of the damage.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" The effect keeps chaining").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" until the damage is less").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" than 1. The range is limited").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 4 blocks within the main target.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Damage halved for Cleave attacks.").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> BLOODTHIRST = ITEMS.register(
            "bloodthirst",
            () -> new BloodthirstItem(new Item.Properties().stacksTo(64))
    );

    private static class BloodthirstItem extends Item {
        public BloodthirstItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Handle of Bloodthirst").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("Blessing: Berserk").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" On kills and every 2nd weapon").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" wattack gain Berserk effect,").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" which causes your next weapon").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" attack to be increased again").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" by 40% of your Melee Potency stat.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Stack Duration: ").withStyle(ChatFormatting.DARK_AQUA)
                            .append(Component.literal("4s").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(
                    Component.literal("Berserk Duration: ").withStyle(ChatFormatting.GREEN)
                            .append(Component.literal("6s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> BRANCH_OF_THE_WORLD_TREE = ITEMS.register(
            "branch_of_the_world_tree",
            () -> new BranchOfTheWorldTreeItem(new Item.Properties().stacksTo(64))
    );

    private static class BranchOfTheWorldTreeItem extends Item {
        public BranchOfTheWorldTreeItem(Properties properties) {
            super(properties);
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
            tooltip.add(Component.literal(" Weapon Attacks").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("Blessing: Bronzewood's Curse").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Attacking a target curses").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" them, increasing their damage").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" damage taken by 7% & causes").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" them to take 0.7 DoT Damage").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" per second for 10 seconds.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Additionally, Deal 2 Melee").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Damage when cursing a target.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Cooldown is reset upon").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" killing a monster.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" The damage over time effect").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" does not proc bonuses.").withStyle(ChatFormatting.GRAY));
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

    private static class HanksOtherEyeItem extends Item {
        public HanksOtherEyeItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Hank's Other Eye").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Bows").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("Blessing: Clairvoyance").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Charging your bow over").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 4 seconds (scales with").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Nock Haste) causes your").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" next shot's damage to").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" double.").withStyle(ChatFormatting.GRAY));
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

    private static class LifeTouchItem extends Item {
        public LifeTouchItem(Properties properties) {
            super(properties);
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
            tooltip.add(Component.literal(" other players for 30% of").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" the Damage done and apply").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Regeneration I for 5 seconds.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Additionally, when shooting").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" a block, spawn Spirit Forest,").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" which applies Regeneration I").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" to targets within 3 blocks").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Initial heal is increased by").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Restoration stat.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Spirit Forest range increased").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" by Amplification stat.").withStyle(ChatFormatting.GRAY));
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

    private static class CupidsArrowItem extends Item {
        public CupidsArrowItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Cupid's Arrow").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Bows").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("Blessing: Deceptive Heart").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Shooting a target causes").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" monsters within 6 blocks").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" to attack the target shot").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Bosses cannot be deceived.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("30s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> IGNITION_MARK = ITEMS.register(
            "ignition_mark",
            () -> new IgnitionMarkItem(new Item.Properties().stacksTo(64))
    );

    private static class IgnitionMarkItem extends Item {
        public IgnitionMarkItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Ignition Mark").withStyle(ChatFormatting.GOLD);
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
            tooltip.add(Component.literal(" count increases every 12s.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" These Creepers only damage").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" monsters, don't break blocks").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" and don't apply knockback.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Creeper damage scales with").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" player's Projectile stats.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Base Damage: ").withStyle(ChatFormatting.DARK_AQUA)
                            .append(Component.literal("5").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> WEAK_POINT_STUDIES = ITEMS.register(
            "weak_point_studies",
            () -> new WeakPointStudiesItem(new Item.Properties().stacksTo(64))
    );

    private static class WeakPointStudiesItem extends Item {
        public WeakPointStudiesItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Weak Point Studies").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Crossbows").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("Blessing: Exploit Weakness").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Your Crossbow shots inflict").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" targets with a random").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" debuff effect.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" ").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Exploited: Target takes").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 12.5% + 0.125% per Dex more").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" damage for 6s").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Enhanced Poison II for 2s").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" + 0.066s per Dex.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Wither III for 3s + 0.1s").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" per Dex.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Weakness I for 2s + 0.02s").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" per Dex.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Slowness III for 3s + 0.03s").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" per Dex.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Instant Damage II (Heal if").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Undead).").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" ").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Effects spread to monsters").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" within 4 blocks with halved").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" duration.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" ").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("12s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> ENDLESS_ADRENALINE_SYRINGE = ITEMS.register(
            "endless_adrenaline_syringe",
            () -> new EndlessAdrenalineSyringeItem(new Item.Properties().stacksTo(64))
    );

    private static class EndlessAdrenalineSyringeItem extends Item {
        public EndlessAdrenalineSyringeItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Endless Adrenaline Syringe").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Crossbows").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("Blessing: Adrenaline Injection").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Every 15 seconds, your").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Crossbow draw time is").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" halved for 5 seconds.").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> FORTRESS_OF_SOLITUDE = ITEMS.register(
            "fortress_of_solitude",
            () -> new FortressOfSolitudeItem(new Item.Properties().stacksTo(64))
    );

    private static class FortressOfSolitudeItem extends Item {
        public FortressOfSolitudeItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Fortress of Solitude").withStyle(ChatFormatting.GOLD);
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
            tooltip.add(Component.literal(" 3 Melee Damage within 5 blocks").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" for every time you block an").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" attack. Damage increased by").withStyle(ChatFormatting.GRAY));
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

    private static class GuardianAngelItem extends Item {
        public GuardianAngelItem(Properties properties) {
            super(properties);
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
            tooltip.add(Component.literal(" evade all attacks for 1.5s.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Duration increased by").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 0.01s per Fortitude and 0.005s").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" per Perception & Constitution.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Fall Damage cannot be evaded.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("8s").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(Component.literal(" Cooldown starts after").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" the effect ends.").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> BOTTLED_LIGHT = ITEMS.register(
            "bottled_light",
            () -> new BottledLightItem(new Item.Properties().stacksTo(64))
    );

    private static class BottledLightItem extends Item {
        public BottledLightItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Bottled Light").withStyle(style -> style.withColor(0xD6C97A));
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Pickaxes").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal(" Right clicking with a").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Pickaxe places a torch").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" but costs 10 Durability.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Only works for 3 blocks").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" distance.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Disabled while crouching.").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> PRIDE_INFUSED_AIGRETTE = ITEMS.register(
            "pride_infused_aigrette",
            () -> new PrideInfusedAigretteItem(new Item.Properties().stacksTo(64))
    );

    private static class PrideInfusedAigretteItem extends Item {
        public PrideInfusedAigretteItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Pride Infused Aigrette").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Blessing: Overconfidence").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Activate to increase your").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" melee damage by 50% but").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" disable healing received.").withStyle(ChatFormatting.GRAY));
            Component keyName = Keybinds.overconfidenceKey.getTranslatedKeyMessage();
            tooltip.add(Component.literal(" ").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal(" Activation: ").withStyle(ChatFormatting.GRAY)
                    .append(keyName.copy().withStyle(ChatFormatting.YELLOW)));
            tooltip.add(
                    Component.literal("Doubled Damage Duration: ").withStyle(ChatFormatting.DARK_AQUA)
                            .append(Component.literal("8s").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(
                    Component.literal("Heal Disable Duration: ").withStyle(ChatFormatting.DARK_RED)
                            .append(Component.literal("15s").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("14s").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(Component.literal(" Cooldown starts after").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" the damage effect ends.").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> IMMOLATION_OF_HEART = ITEMS.register(
            "immolation_of_heart",
            () -> new ImmolationOfHeartItem(new Item.Properties().stacksTo(64))
    );

    private static class ImmolationOfHeartItem extends Item {
        public ImmolationOfHeartItem(Properties properties) {
            super(properties);
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
                            .append(Component.literal("3s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> FOX_EYE = ITEMS.register(
            "fox_eye",
            () -> new FoxEyeItem(new Item.Properties().stacksTo(64))
    );

    private static class FoxEyeItem extends Item {
        public FoxEyeItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Fox Eye").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Blessing: Nimble Getaway").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Every 20 seconds, gain an").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" effect that allows you to").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" evade the next attack.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Fall Damage cannot be evaded").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> BROKEN_HEALTH_POTION = ITEMS.register(
            "broken_health_potion",
            () -> new BrokenHealthPotionItem(new Item.Properties().stacksTo(64))
    );

    private static class BrokenHealthPotionItem extends Item {
        public BrokenHealthPotionItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Broken Health Potion").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Blessing: Combatant's Aid").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" When activated while").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" sprinting, perform an 8").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" block dash and heal allies").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" within 3 blocks for 3 HP and").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" apply Resistance I for 5s.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Activate a second time within").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 4 seconds of the dash to").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" return to original location.").withStyle(ChatFormatting.GRAY));
            Component keyName = Keybinds.combatantKey.getTranslatedKeyMessage();

            tooltip.add(Component.literal(" ").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Activation: ").withStyle(ChatFormatting.GRAY)
                    .append(keyName.copy().withStyle(ChatFormatting.YELLOW)));
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

    private static class RestoringAuraItem extends Item {
        public RestoringAuraItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Restoring Aura").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Blessing: Radiating Warmth").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Every 20 seconds, heal").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" allies within 8 blocks").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" for 2 health.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Interval speeds up with").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Amplification stat.").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> DIVINE_SHIELD = ITEMS.register(
            "divine_shield",
            () -> new DivineShieldItem(new Item.Properties().stacksTo(64))
    );

    private static class DivineShieldItem extends Item {
        public DivineShieldItem(Properties properties) {
            super(properties);
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
            tooltip.add(Component.literal(" second, heal allies").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" within 6 blocks for 1").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" every second and reduce").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" their damage taken by 10%").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" for 1 second. Healing").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" effect reduced by 20%").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" per tick, up to 80%.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Range increased by").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Amplification.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Heal Fatigue Duration: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("12s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> NATURES_BLESSING = ITEMS.register(
            "natures_blessing",
            () -> new NaturesBlessingItem(new Item.Properties().stacksTo(64))
    );

    private static class NaturesBlessingItem extends Item {
        public NaturesBlessingItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Nature's Blessing").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Blessing: Pulsating Love").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Every 10s, apply").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Regeneration 2 and").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Resistance 2 for 30s to").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" tamed pets within 5 blocks.").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> SUMMONING_STONE = ITEMS.register(
            "summoning_stone",
            () -> new SummoningStoneItem(new Item.Properties().stacksTo(64))
    );

    private static class SummoningStoneItem extends Item {
        public SummoningStoneItem(Properties properties) {
            super(properties);
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
            tooltip.add(Component.literal(" Sbeve scales with its owner's").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Potency, Accuracy, Precision,").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Haste, HP, Armor and").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Toughness stats.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Base Damage: ").withStyle(ChatFormatting.DARK_AQUA)
                            .append(Component.literal("1").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(
                    Component.literal("Base Attack Cooldown: ").withStyle(ChatFormatting.DARK_AQUA)
                            .append(Component.literal("1.5s").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(
                    Component.literal("Attack Range: ").withStyle(ChatFormatting.DARK_AQUA)
                            .append(Component.literal("1.6 Blocks").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(
                    Component.literal("HP: ").withStyle(ChatFormatting.DARK_AQUA)
                            .append(Component.literal("2x the Summoner's").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(
                    Component.literal("Detection Range: ").withStyle(ChatFormatting.DARK_AQUA)
                            .append(Component.literal("10 Blocks").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> WIND_WALKER_ARROW = ITEMS.register(
            "wind_walker_arrow",
            () -> new WindWalkerArrowItem(new Item.Properties().stacksTo(64))
    );

    private static class WindWalkerArrowItem extends Item {
        public WindWalkerArrowItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Wind Walker's Arrow").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Bows").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("Blessing: Vortex").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Upon shooting the ground or").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" a monster, pull monsters").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" within 5 blocks to the target").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" for 3 seconds and deal 2.75").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" DoT Damage every").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" second.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("24s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> VIAL_OF_BURSTING_ENERGY = ITEMS.register(
            "vial_of_bursting_energy",
            () -> new BurstingEnergyVialItem(new Item.Properties().stacksTo(64))
    );

    private static class BurstingEnergyVialItem extends Item {
        public BurstingEnergyVialItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Vial of Bursting Energy").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Crossbows").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("Blessing: Focused Energy").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Launch yourself up to").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 6 blocks on arrow impact.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Targets within 5 blocks").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" are rooted for 1s.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("20s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> THORN_COVERED_FORCEFIELD = ITEMS.register(
            "thorn_covered_forcefield",
            () -> new ThornCoveredForceFieldItem(new Item.Properties().stacksTo(64))
    );

    private static class ThornCoveredForceFieldItem extends Item {
        public ThornCoveredForceFieldItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Thorn Covered Force Field").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Shields").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("Blessing: Thorned Parry").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" When releasing your shield").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" 0.4s within a shield block,").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" deal 5 Melee Damage 3 blocks").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" around you and stun your").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" target for 2 seconds.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("7s").withStyle(ChatFormatting.GRAY))
            );
        }
    }

    public static final RegistryObject<Item> DIVINITY_EXTRACTION = ITEMS.register(
            "divinity_extraction",
            () -> new DivineExtractionItem(new Item.Properties().stacksTo(64))
    );

    private static class DivineExtractionItem extends Item {
        public DivineExtractionItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Divinity Extraction").withStyle(ChatFormatting.GOLD);
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Swords").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal("Blessing: Blessed Land").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.literal(" Melee hits spawn a zone").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" within 5 blocks of the").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" target that heals a player").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" for 3 when stepped on.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" The area disappears").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" when stepped on.").withStyle(ChatFormatting.GRAY));
            tooltip.add(
                    Component.literal("Blessed Land Duration: ").withStyle(ChatFormatting.DARK_AQUA)
                            .append(Component.literal("7.5s").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(
                    Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                            .append(Component.literal("15s").withStyle(ChatFormatting.GRAY))
            );
            tooltip.add(Component.literal(" Cooldown speed increased").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" by Amplification.").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> FAIRY_TEARDROP = ITEMS.register(
            "fairy_teardrop",
            () -> new FairyTeardropItem(new Item.Properties().stacksTo(64))
    );

    private static class FairyTeardropItem extends Item  {
        public FairyTeardropItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Fairy's Teardrop").withStyle(style -> style.withColor(0xD6C97A));
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Hoes").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal(" Right clicking a grown").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" seed harvests the seed").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" and re-plants it.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Loot is granted to").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" player's inventory.").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> HOOK_OF_THE_DEPTHS = ITEMS.register(
            "hook_of_the_depths",
            () -> new HookOfTheDepthsItem(new Item.Properties().stacksTo(64))
    );

    private static class HookOfTheDepthsItem extends Item {
        public HookOfTheDepthsItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Hook of the Depths").withStyle(style -> style.withColor(0xD6C97A));
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Fishing Rods").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal(" Fishing Rods act").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" as grappling hooks.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Take 4 Magic Damage").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" on use.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Only works for").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" moving upwards.").withStyle(ChatFormatting.GRAY));
        }
    }

    public static final RegistryObject<Item> HELLFORGED_PLATING = ITEMS.register(
            "hellforged_plating",
            () -> new HellforgedPlatingItem(new Item.Properties().stacksTo(64))
    );

    private static class HellforgedPlatingItem extends Item {
        public HellforgedPlatingItem(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Hell Forged Plating").withStyle(style -> style.withColor(0xD6C97A));
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Tools").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal(" Broken blocks are smelt").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" automatically.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Loot is granted to").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" player's inventory.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Disabled while crouching.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.empty());

            Component keyName = Keybinds.autoSmeltKey.getTranslatedKeyMessage();
            tooltip.add(Component.literal(" Turn on/off: ").withStyle(ChatFormatting.GRAY)
                    .append(keyName.copy().withStyle(ChatFormatting.YELLOW)));
        }
    }

    // -------------------------------

    public static final RegistryObject<Item> FLAMEFORGED_PLATING = ITEMS.register(
            "flameforged_plating",
            () -> new FlameforgedPlating(new Item.Properties().stacksTo(64))
    );

    private static class FlameforgedPlating extends Item {
        public FlameforgedPlating(Properties properties) {
            super(properties);
        }

        @Override
        public Component getName(ItemStack stack) {
            return Component.literal("Flame Forged Plating").withStyle(style -> style.withColor(0xD6C97A));
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, level, tooltip, flag);
            tooltip.add(Component.literal(""));
            tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("+2 Insight").withStyle(ChatFormatting.DARK_AQUA));
            tooltip.add(Component.empty());
            tooltip.add(Component.literal("Applies to:").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Tools").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.literal(" Broken ores are smelt").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" automatically.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Loot is granted to").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" player's inventory.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal(" Disabled while crouching.").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.empty());

            Component keyName = Keybinds.autoSmeltKey.getTranslatedKeyMessage();
            tooltip.add(Component.literal(" Turn on/off: ").withStyle(ChatFormatting.GRAY)
                    .append(keyName.copy().withStyle(ChatFormatting.YELLOW)));
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
}
