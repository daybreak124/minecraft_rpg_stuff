package net.cold.coldsmod.item;

import com.mojang.serialization.Codec;
import net.cold.coldsmod.ColdsMod;
import net.cold.coldsmod.stat.ArmorRarityModifier;
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
            () -> new Item(new Item.Properties().stacksTo(64)) {
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
                            Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                                    .append(Component.literal("15s").withStyle(ChatFormatting.GRAY))
                    );
                }
            }
    );

    public static final RegistryObject<Item> HORN_OF_FEARMONGERING = ITEMS.register(
            "horn_of_fearmongering",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Horn of Fearmongering").withStyle(ChatFormatting.GOLD);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal(""));
                    tooltip.add(Component.literal("Blessing: Daring Shout").withStyle(ChatFormatting.GOLD));
                    tooltip.add(Component.literal(" Crouching stuns targets").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" within a 5 block radius").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" for 3 seconds.").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" Stun duration increased by").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" 0.06s per Fortitude and").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" 0.03s per Perception.").withStyle(ChatFormatting.GRAY));
                    tooltip.add(
                            Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                                    .append(Component.literal("15s").withStyle(ChatFormatting.GRAY))
                    );
                }
            }
    );

    public static final RegistryObject<Item> HANKS_EYE = ITEMS.register(
            "hanks_eye",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Hank's Eye").withStyle(ChatFormatting.GOLD);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal(""));
                    tooltip.add(Component.literal("Blessing: Hawkeye").withStyle(ChatFormatting.GOLD));
                    tooltip.add(Component.literal(" Critical hits with melee").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" weapons increase your").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" Projectile Damage by 5").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" and Draw Speed by 9").withStyle(ChatFormatting.GRAY));
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
    );

    public static final RegistryObject<Item> SUNSTONE_GEM = ITEMS.register(
            "sunstone_gem",
            () -> new Item(new Item.Properties().stacksTo(64)) {

                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Sunstone Gem").withStyle(ChatFormatting.GOLD);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal(""));
                    tooltip.add(Component.literal("Blessing: Solara").withStyle(ChatFormatting.GOLD));
                    tooltip.add(Component.literal(" Gain or lose Melee Damage").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" and Armor depending on ").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" the time of day.").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(""));
                    tooltip.add(Component.literal(" +25 Melee Damage and").withStyle(ChatFormatting.YELLOW));
                    tooltip.add(Component.literal(" +10 Armor at noon.").withStyle(ChatFormatting.YELLOW));
                    tooltip.add(Component.literal(""));
                    tooltip.add(Component.literal(" -15 Melee Damage and").withStyle(ChatFormatting.DARK_GRAY));
                    tooltip.add(Component.literal(" -15 Armor at midnight.").withStyle(ChatFormatting.DARK_GRAY));
                    tooltip.add(Component.literal("").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" Effect remains for at").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" least 1 Minecraft day.").withStyle(ChatFormatting.GRAY));
                }
            }
    );

    public static final RegistryObject<Item> RAGE_AMPLIFIER = ITEMS.register(
            "rage_amplifier",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Rage Amplifier").withStyle(ChatFormatting.GOLD);
                }

                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
                    super.appendHoverText(stack, level, tooltip, flag);
                    tooltip.add(Component.literal(""));
                    tooltip.add(Component.literal("Blessing: Frenzy").withStyle(ChatFormatting.GOLD));
                    tooltip.add(Component.literal(" Attacking a target").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" increases your Attack").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" Damage by 1.25 and Melee").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" Damage stat by 7.5% but").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" increases your damage").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" taken by 8% per stack").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" for 6 seconds").withStyle(ChatFormatting.GRAY));
                    tooltip.add(
                            Component.literal("Max Stacks: ").withStyle(ChatFormatting.DARK_AQUA)
                                    .append(Component.literal("4").withStyle(ChatFormatting.GRAY))
                    );
                }
            }
    );

    public static final RegistryObject<Item> DROP_OF_SACRIFICIAL_BLOOD = ITEMS.register(
            "drop_of_sacrificial_blood",
            () -> new Item(new Item.Properties().stacksTo(64)) {
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
    );

    public static final RegistryObject<Item> HELL_ON_EARTH = ITEMS.register(
            "hell_on_earth",
            () -> new Item(new Item.Properties().stacksTo(64)) {
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
    );

    public static final RegistryObject<Item> BANNER_OF_DETERMINATION = ITEMS.register(
            "banner_of_determination",
            () -> new Item(new Item.Properties().stacksTo(64)) {
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
    );

    public static final RegistryObject<Item> WORMHOLE = ITEMS.register(
            "wormhole",
            () -> new Item(new Item.Properties().stacksTo(64)) {
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
                    tooltip.add(Component.literal(" a leap and turn invisible for").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" 4 seconds after landing and gain").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" +15 Damage and +20% Movement").withStyle(ChatFormatting.GRAY));
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
    );

    public static final RegistryObject<Item> ORB_OF_WORLD_DESTRUCTION = ITEMS.register(
            "orb_of_world_destruction",
            () -> new Item(new Item.Properties().stacksTo(64)) {
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
    );

    public static final RegistryObject<Item> SOUL_MAGNET = ITEMS.register(
            "soul_magnet",
            () -> new Item(new Item.Properties().stacksTo(64)) {
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
    );

    public static final RegistryObject<Item> LIGHTNING_INFUSION = ITEMS.register(
            "lightning_infusion",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Lightning Infusion").withStyle(ChatFormatting.GOLD);
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
    );

    public static final RegistryObject<Item> BLOODTHIRST = ITEMS.register(
            "bloodthirst",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Handle of Bloodthirst").withStyle(ChatFormatting.GOLD);
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
                    tooltip.add(Component.literal(" your Melee Damage stat.").withStyle(ChatFormatting.GRAY));
                    tooltip.add(
                            Component.literal("Stack Duration: ").withStyle(ChatFormatting.DARK_AQUA)
                                    .append(Component.literal("4s").withStyle(ChatFormatting.GRAY))
                    );
                    tooltip.add(
                            Component.literal("Berserk Duration: ").withStyle(ChatFormatting.GREEN)
                                    .append(Component.literal("6s").withStyle(ChatFormatting.GRAY))
                    );
                    tooltip.add(Component.literal(" Berserk doesn't expire when").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" gained from the 15s timer.").withStyle(ChatFormatting.GRAY));
                }
            }
    );

    public static final RegistryObject<Item> BRANCH_OF_THE_WORLD_TREE = ITEMS.register(
            "branch_of_the_world_tree",
            () -> new Item(new Item.Properties().stacksTo(64)) {
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
                    tooltip.add(Component.literal(" damage taken by 10% and causes").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" them to take 1 Melee Damage").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" per second for 10 seconds.").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" Additionally, Attack Damage").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" +3 when cursing a target.").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" Cooldown is reset upon").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" killing a monster.").withStyle(ChatFormatting.GRAY));
                    tooltip.add(
                            Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                                    .append(Component.literal("20s").withStyle(ChatFormatting.GRAY))
                    );
                }
            }
    );

    public static final RegistryObject<Item> HANKS_OTHER_EYE = ITEMS.register(
            "hanks_other_eye",
            () -> new Item(new Item.Properties().stacksTo(64)) {
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
                    tooltip.add(Component.literal(" 3 seconds (scales with").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" Draw Speed) causes your").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" next shot to be increased").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" by Projectile Damage 4").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" more times.").withStyle(ChatFormatting.GRAY));
                    tooltip.add(
                            Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                                    .append(Component.literal("20s").withStyle(ChatFormatting.GRAY))
                    );
                }
            }
    );

    public static final RegistryObject<Item> LIFE_TOUCH = ITEMS.register(
            "life_touch",
            () -> new Item(new Item.Properties().stacksTo(64)) {
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
                    tooltip.add(Component.literal(" Regeneration I for 4 seconds.").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" Additionally, when shooting.").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" a block, spawn Spirit Forest,").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" which applies Regeneration I").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" to targets inside.").withStyle(ChatFormatting.GRAY));
                    tooltip.add(
                            Component.literal("Spirit Forest Duration: ").withStyle(ChatFormatting.DARK_AQUA)
                                    .append(Component.literal("6s").withStyle(ChatFormatting.GRAY))
                    );
                    tooltip.add(
                            Component.literal("Spirit Forest Cooldown: ").withStyle(ChatFormatting.RED)
                                    .append(Component.literal("15s").withStyle(ChatFormatting.GRAY))
                    );
                }
            }
    );

    public static final RegistryObject<Item> CUPIDS_ARROW = ITEMS.register(
            "cupids_arrow",
            () -> new Item(new Item.Properties().stacksTo(64)) {
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
                    tooltip.add(Component.literal(" the monsters within 9").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" blocks to attack the").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" target shot.").withStyle(ChatFormatting.GRAY));
                    tooltip.add(
                            Component.literal("Cooldown: ").withStyle(ChatFormatting.RED)
                                    .append(Component.literal("14s").withStyle(ChatFormatting.GRAY))
                    );
                }
            }
    );

    public static final RegistryObject<Item> IGNITION_MARK = ITEMS.register(
            "ignition_mark",
            () -> new Item(new Item.Properties().stacksTo(64)) {
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
                    tooltip.add(Component.literal(" count increases every 8s.").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" These Creepers only damage").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" monsters, don't break blocks").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" and don't apply knockback.").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" Creeper damage scales with").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" player's Projectile stats.").withStyle(ChatFormatting.GRAY));
                }
            }
    );

    public static final RegistryObject<Item> WEAK_POINT_STUDIES = ITEMS.register(
            "weak_point_studies",
            () -> new Item(new Item.Properties().stacksTo(64)) {
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
    );

    public static final RegistryObject<Item> ENDLESS_ADRENALINE_SYRINGE = ITEMS.register(
            "endless_adrenaline_syringe",
            () -> new Item(new Item.Properties().stacksTo(64)) {
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
                    tooltip.add(Component.literal(" Every 30 seconds, increase").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" your Draw Speed stat by 100%").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" for 6 seconds. Only applies").withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal(" to Crossbows").withStyle(ChatFormatting.GRAY));
                }
            }
    );

    public static final RegistryObject<Item> FORTRESS_OF_SOLITUDE = ITEMS.register(
            "fortress_of_solitude",
            () -> new Item(new Item.Properties().stacksTo(64)) {
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
    );

    public static final RegistryObject<Item> GUARDIAN_ANGEL = ITEMS.register(
            "guardian_angel",
            () -> new Item(new Item.Properties().stacksTo(64)) {
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
                    tooltip.add(Component.literal(" take no damage for 0.75s.").withStyle(ChatFormatting.GRAY));
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
    );

    public static final RegistryObject<Item> BOTTLED_LIGHT = ITEMS.register(
            "bottled_light",
            () -> new Item(new Item.Properties().stacksTo(64)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Bottled Light").withStyle(ChatFormatting.GOLD);
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
    );

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
