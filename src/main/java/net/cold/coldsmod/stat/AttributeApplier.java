package net.cold.coldsmod.stat;

import net.cold.coldsmod.gearbonuses.effects.ModEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.event.CurioChangeEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AttributeApplier {
    private static final String KEY = "coldsmod_item_uuid";
    private static final UUID SCALING_UUID = UUID.fromString("f47171d3-a44d-4581-9f93-18963214722a");
    private static final UUID STR_MILESTONE_UUID = UUID.fromString("f47171d3-a44d-4581-9f93-17763214722a");


    public static void register() {
        MinecraftForge.EVENT_BUS.register(new AttributeApplier());
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        rebuildAll(event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        rebuildAll(event.getEntity());
    }

    public static UUID get(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.hasUUID(KEY)) {
            tag.putUUID(KEY, UUID.randomUUID());
        }
        return tag.getUUID(KEY);
    }

    public static UUID of(UUID itemUUID, Attribute attr) {
        return UUID.nameUUIDFromBytes(
                (itemUUID + "|" + attr.getDescriptionId()).getBytes()
        );
    }

    public static void apply(Player player, Attribute attr, double value, UUID itemUUID) {
        AttributeInstance inst = player.getAttribute(attr);
        if (inst == null) return;

        UUID uuid = of(itemUUID, attr);

        inst.removeModifier(uuid);

        if (value != 0) {
            inst.addTransientModifier(new AttributeModifier(uuid, "coldsmod_item_stat", value, AttributeModifier.Operation.ADDITION));
        }
    }

    public static void remove(Player player, Attribute attr, UUID itemUUID) {
        AttributeInstance inst = player.getAttribute(attr);
        if (inst == null) return;

        inst.removeModifier(of(itemUUID, attr));
    }

    @SubscribeEvent
    public static void onEquipmentChange(LivingEquipmentChangeEvent event) {
        if (event.getEntity() instanceof Player player) {
            updateStats(player, event.getFrom(), false);
            updateStats(player, event.getTo(), true);
            applyCrossbowTag(player);
        }
    }

    @SubscribeEvent
    public static void onCurioChange(CurioChangeEvent event) {
        if (event.getEntity() instanceof Player player) {
            updateStats(player, event.getFrom(), false);
            updateStats(player, event.getTo(), true);
            applyCrossbowTag(player);
        }
    }
    public static void updateStats(Player player, ItemStack stack, boolean equipped) {
        if (player.level().isClientSide || stack.isEmpty() || !StatUtils.hasStats(stack)) return;

        CustomStats s = StatUtils.readStatsFromNBT(stack);
        UUID itemUUID = get(stack);

        boolean isMainHand = (stack == player.getMainHandItem());
        boolean isOffHand = (stack == player.getOffhandItem());

        boolean isHeld = isMainHand || isOffHand;

        String itemType = ItemRarityUtils.getItemType(stack);
        boolean isValidCombatType = "crossbow".equals(itemType)
                || "bow".equals(itemType)
                || "sword".equals(itemType)
                || "shield".equals(itemType)
                || "tool".equals(itemType);

        if (equipped) {
            if (!isHeld || isValidCombatType) {
                apply(player, ModAttributes.STR.get(), s.getStr(), itemUUID);
                apply(player, ModAttributes.FORT.get(), s.getFort(), itemUUID);
                apply(player, ModAttributes.DEX.get(), s.getDex(), itemUUID);
                apply(player, ModAttributes.INTELLIGENCE.get(), s.getIntelligence(), itemUUID);
                apply(player, ModAttributes.WISDOM.get(), s.getWisdom(), itemUUID);
                apply(player, ModAttributes.CON.get(), s.getCon(), itemUUID);
                apply(player, ModAttributes.PERC.get(), s.getPerc(), itemUUID);
                apply(player, ModAttributes.INSIGHT.get(), s.getInsight(), itemUUID);

                apply(player, ModAttributes.POTENCY.get(), s.getDamage(), itemUUID);
                apply(player, ModAttributes.HASTE.get(), s.getAttackSpeed(), itemUUID);
                apply(player, ModAttributes.ACCURACY.get(), s.getCritChance(), itemUUID);
                apply(player, ModAttributes.PRECISION.get(), s.getCritDamage(), itemUUID);

                apply(player, ModAttributes.MELEE_POTENCY.get(), s.getMeleeDamage() + s.getDamage(), itemUUID);
                apply(player, ModAttributes.MELEE_ACCURACY.get(), s.getMeleeCritChance() + s.getCritChance(), itemUUID);
                apply(player, ModAttributes.MELEE_PRECISION.get(), s.getMeleeCritDamage() + s.getCritDamage(), itemUUID);

                apply(player, ModAttributes.PROJECTILE_POTENCY.get(), s.getProjectileDamage() + s.getDamage(), itemUUID);
                apply(player, ModAttributes.NOCK_HASTE.get(), s.getDrawSpeed(), itemUUID);
                apply(player, ModAttributes.PROJECTILE_ACCURACY.get(), s.getProjectileCritChance() + s.getCritChance(), itemUUID);
                apply(player, ModAttributes.PROJECTILE_PRECISION.get(), s.getProjectileCritDamage() + s.getCritDamage(), itemUUID);

                apply(player, ModAttributes.PROTECTION.get(), s.getProtection(), itemUUID);
                apply(player, ModAttributes.RESTORATION.get(), s.getRestoration(), itemUUID);
                apply(player, ModAttributes.AMPLIFICATION.get(), s.getAmplification(), itemUUID);
                apply(player, ModAttributes.DEBUFF_RESIST.get(), s.getDebuffResist(), itemUUID);

                apply(player, ModAttributes.POTENCY_MULTIPLIER.get(), s.getDamageMultiplier(), itemUUID);
                apply(player, ModAttributes.ACCURACY_MULTIPLIER.get(), s.getCritChanceMultiplier(), itemUUID);
                apply(player, ModAttributes.PRECISION_MULTIPLIER.get(), s.getCritDamageMultiplier(), itemUUID);
                apply(player, ModAttributes.HASTE_MULTIPLIER.get(), s.getAttackSpeedMultiplier(), itemUUID);

                apply(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), s.getMeleeDamageMultiplier() + s.getDamageMultiplier(), itemUUID);
                apply(player, ModAttributes.MELEE_ACCURACY_MULTIPLIER.get(), s.getMeleeCritChanceMultiplier() + s.getCritChanceMultiplier(), itemUUID);
                apply(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), s.getMeleeCritDamageMultiplier() + s.getAttackSpeedMultiplier(), itemUUID);

                apply(player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), s.getProjectileDamageMultiplier() + s.getDamageMultiplier(), itemUUID);
                apply(player, ModAttributes.PROJECTILE_ACCURACY_MULTIPLIER.get(), s.getProjectileCritChanceMultiplier() + s.getCritChanceMultiplier(), itemUUID);
                apply(player, ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get(), s.getProjectileCritDamageMultiplier() + s.getAttackSpeedMultiplier(), itemUUID);

                apply(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), s.getDrawSpeedMultiplier(), itemUUID);
                apply(player, ModAttributes.PROTECTION_MULTIPLIER.get(), s.getProtectionMultiplier(), itemUUID);
                apply(player, ModAttributes.RESTORATION_MULTIPLIER.get(), s.getRestorationMultiplier(), itemUUID);
                apply(player, ModAttributes.AMPLIFICATION_MULTIPLIER.get(), s.getAmplificationMultiplier(), itemUUID);
                apply(player, ModAttributes.ARMOR_MULTIPLIER.get(), s.getArmorMultiplier(), itemUUID);
                apply(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), s.getToughnessMultiplier(), itemUUID);
                apply(player, ModAttributes.HEALTH_MULTIPLIER.get(), s.getHealthMultiplier(), itemUUID);

                apply(player, Attributes.ARMOR, s.getArmor(), itemUUID);
                apply(player, Attributes.ARMOR_TOUGHNESS, s.getArmorToughness(), itemUUID);
                apply(player, Attributes.KNOCKBACK_RESISTANCE, s.getKnockbackResist() / 100, itemUUID);
                apply(player, Attributes.MAX_HEALTH, s.getMaxHealth(), itemUUID);
                apply(player, Attributes.LUCK, s.getLuck(), itemUUID);
                apply(player, ForgeMod.BLOCK_REACH.get(), s.getBlockReach(), itemUUID);
                apply(player, ForgeMod.ENTITY_REACH.get(), s.getEntityReach(), itemUUID);
                apply(player, ForgeMod.SWIM_SPEED.get(), s.getSwimSpeed() / 1000, itemUUID);
                apply(player, Attributes.MOVEMENT_SPEED, s.getMoveSpeed() / 1000, itemUUID);

                apply(player, ModAttributes.JUMP_BOOST.get(), s.getJumpBoost(), itemUUID);
                apply(player, ModAttributes.MINING_SPEED.get(), s.getMiningSpeed(), itemUUID);
                apply(player, ModAttributes.XP_GAIN.get(), s.getXpGain(), itemUUID);
            }
        } else {
            removeAllStats(player, itemUUID);
        }
        applyAttributeScaling(player);
    }

    public static void applyAttributeScaling(Player player) {

        player.getAttribute(ModAttributes.POTENCY.get()).removeModifier(SCALING_UUID);
        player.getAttribute(ModAttributes.ACCURACY.get()).removeModifier(SCALING_UUID);
        player.getAttribute(ModAttributes.PRECISION.get()).removeModifier(SCALING_UUID);
        player.getAttribute(ModAttributes.HASTE.get()).removeModifier(SCALING_UUID);
        player.getAttribute(ModAttributes.PROJECTILE_POTENCY.get()).removeModifier(SCALING_UUID);
        player.getAttribute(ModAttributes.NOCK_HASTE.get()).removeModifier(SCALING_UUID);
        player.getAttribute(ModAttributes.DEBUFF_RESIST.get()).removeModifier(SCALING_UUID);
        player.getAttribute(ModAttributes.XP_GAIN.get()).removeModifier(SCALING_UUID);
        player.getAttribute(ModAttributes.MINING_SPEED.get()).removeModifier(SCALING_UUID);

        player.getAttribute(ModAttributes.POTENCY_MULTIPLIER.get()).removeModifier(SCALING_UUID);
        player.getAttribute(ModAttributes.PRECISION_MULTIPLIER.get()).removeModifier(SCALING_UUID);
        player.getAttribute(ModAttributes.ARMOR_MULTIPLIER.get()).removeModifier(SCALING_UUID);
        player.getAttribute(ModAttributes.TOUGHNESS_MULTIPLIER.get()).removeModifier(SCALING_UUID);
        player.getAttribute(ModAttributes.HASTE_MULTIPLIER.get()).removeModifier(SCALING_UUID);
        player.getAttribute(ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get()).removeModifier(SCALING_UUID);

        player.getAttribute(Attributes.ARMOR).removeModifier(SCALING_UUID);
        player.getAttribute(Attributes.ARMOR_TOUGHNESS).removeModifier(SCALING_UUID);
        player.getAttribute(Attributes.KNOCKBACK_RESISTANCE).removeModifier(SCALING_UUID);
        player.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(SCALING_UUID);
        player.getAttribute(ForgeMod.BLOCK_REACH.get()).removeModifier(SCALING_UUID);
        player.getAttribute(ForgeMod.ENTITY_REACH.get()).removeModifier(SCALING_UUID);

        player.getAttribute(Attributes.ATTACK_SPEED).removeModifier(UUID.fromString("e2225476-1234-5352-5454-113215411111"));
        player.getAttribute(Attributes.ARMOR).removeModifier(UUID.fromString("e1125476-1234-5454-5454-113215411111"));
        player.getAttribute(Attributes.ARMOR_TOUGHNESS).removeModifier(UUID.fromString("e2225476-1234-5454-5454-113215411111"));
        player.getAttribute(Attributes.MAX_HEALTH).removeModifier(UUID.fromString("e3325476-1234-5454-5454-113215411111"));

        double totalStr = player.getAttributeValue(ModAttributes.STR.get());
        double totalFort = player.getAttributeValue(ModAttributes.FORT.get());
        double totalDex = player.getAttributeValue(ModAttributes.DEX.get());
        double totalCon = player.getAttributeValue(ModAttributes.CON.get());
        double totalPerc = player.getAttributeValue(ModAttributes.PERC.get());
        double totalInsight = player.getAttributeValue(ModAttributes.INSIGHT.get());

        double generalDamageRating = totalStr * 0.35 + totalCon * 0.125;
        double armorRating = totalCon * 0.15 + totalPerc * 0.1 + totalFort * 0.2;
        double toughnessRating = totalFort * 0.15;
        double totalDebuffResist = totalCon * 0.2;
        double totalKnockbackResist = totalFort * 0.2;
        double totalMoveSpeed = totalDex * 0.12;

        double projectileDamageRating = totalDex * 0.15;
        double drawSpeedRating = totalDex * 0.125;
        double attackSpeedRating = totalDex * 0.125;

        double generalCritChanceRating = totalDex * 0.2 + totalPerc * 0.175;
        double generalCritDamageRating = 0;

        double totalXpGain = totalInsight * 0.25;
        double totalBlockReach = totalInsight * 0.05;
        double totalMiningSpeed = totalInsight * 0.25;
        double totalEntityReach = 0;

        double totalGeneralDamageMultiplier = 0;
        double totalGeneralCritDamageMultiplier = 0;
        double totalArmorMultiplier = 0;
        double totalToughnessMultiplier = 0;
        double totalAttackSpeedMultiplier = 0;
        double totalProjectileDamageMultiplier = 0;

        // --- Strength ---
        if (totalStr >= 30) generalDamageRating += 7.5;
        if (totalStr >= 40) armorRating += 5;
        if (totalStr >= 50) attackSpeedRating += 8;
        if (totalStr >= 60) totalGeneralCritDamageMultiplier += 0.15;
        if (totalStr >= 70) totalGeneralDamageMultiplier += 0.25;
        if (totalStr >= 80) applyModifier(player, Attributes.ATTACK_DAMAGE, 2.0, STR_MILESTONE_UUID);
        else removeModifier(player, Attributes.ATTACK_DAMAGE, STR_MILESTONE_UUID);

        // --- Fortitude ---
        if (totalFort >= 30) armorRating += 4;
        if (totalFort >= 40) totalArmorMultiplier += 0.08;
        if (totalFort >= 50) totalKnockbackResist += 10.0;
        if (totalFort >= 60) totalToughnessMultiplier += 0.10;
        if (totalFort >= 70) { totalDebuffResist += 15.0; totalKnockbackResist += 10; }
        if (totalFort >= 80) { toughnessRating += 9.0; armorRating += 9.0; }

        // --- Dexterity ---
        if (totalDex >= 30) generalCritChanceRating += 6;
        if (totalDex >= 40) generalCritDamageRating += 6;
        if (totalDex >= 50) drawSpeedRating += 8;
        if (totalDex >= 60) totalMoveSpeed += 8;
        if (totalDex >= 70) { totalAttackSpeedMultiplier += 0.08; totalProjectileDamageMultiplier += 0.15; }
        if (totalDex >= 80) { projectileDamageRating += 9; drawSpeedRating += 9; generalCritChanceRating += 9; generalCritDamageRating += 9; }

        // --- Constitution ---
        if (totalCon >= 30) armorRating += 5.0;
        if (totalCon >= 40) generalDamageRating += 5;
        if (totalCon >= 50) { totalDebuffResist += 12; toughnessRating += 6; }
        if (totalCon >= 60) totalToughnessMultiplier += 0.125;
        if (totalCon >= 70) totalArmorMultiplier += 0.125;
        if (totalCon >= 80) generalDamageRating += 10;

        // --- Perception ---
        if (totalPerc >= 30) toughnessRating += 3.0;
        if (totalPerc >= 40) totalGeneralDamageMultiplier += 0.075;
        if (totalPerc >= 50) generalCritDamageRating += 10.0;
        if (totalPerc >= 60) totalEntityReach += 1;
        if (totalPerc >= 70) totalArmorMultiplier += 0.08;
        if (totalPerc >= 80) totalGeneralCritDamageMultiplier += 0.25;

        // --- Insight ---
        if (totalInsight >= 10) totalMiningSpeed += 10.0;
        if (totalInsight >= 20) totalXpGain += 10;
        if (totalInsight >= 30) totalBlockReach += 0.5;
        if (totalInsight >= 40) { totalMiningSpeed += 25; totalXpGain += 25; }

        // Ratings
        applyModifier(player, ModAttributes.POTENCY.get(), generalDamageRating, SCALING_UUID);
        applyModifier(player, ModAttributes.ACCURACY.get(), generalCritChanceRating, SCALING_UUID);
        applyModifier(player, ModAttributes.PRECISION.get(), generalCritDamageRating, SCALING_UUID);

        applyModifier(player, ModAttributes.MELEE_POTENCY.get(), generalDamageRating, SCALING_UUID);
        applyModifier(player, ModAttributes.MELEE_ACCURACY.get(), generalCritChanceRating, SCALING_UUID);
        applyModifier(player, ModAttributes.MELEE_PRECISION.get(), generalCritDamageRating, SCALING_UUID);

        applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), generalDamageRating + projectileDamageRating, SCALING_UUID);
        applyModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), generalCritChanceRating, SCALING_UUID);
        applyModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), generalCritDamageRating, SCALING_UUID);


        applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), totalDebuffResist, SCALING_UUID);
        applyModifier(player, ModAttributes.XP_GAIN.get(), totalXpGain, SCALING_UUID);
        applyModifier(player, ModAttributes.MINING_SPEED.get(), totalMiningSpeed, SCALING_UUID);

        applyModifier(player, ModAttributes.HASTE.get(), attackSpeedRating, SCALING_UUID);
        applyModifier(player, ModAttributes.NOCK_HASTE.get(), drawSpeedRating, SCALING_UUID);

        // Multipliers
        applyModifier(player, ModAttributes.POTENCY_MULTIPLIER.get(), totalGeneralDamageMultiplier, SCALING_UUID);
        applyModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), totalGeneralDamageMultiplier, SCALING_UUID);
        applyModifier(player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), totalGeneralDamageMultiplier + totalProjectileDamageMultiplier, SCALING_UUID);


        applyModifier(player, ModAttributes.PRECISION_MULTIPLIER.get(), totalGeneralCritDamageMultiplier, SCALING_UUID);
        applyModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), totalGeneralCritDamageMultiplier, SCALING_UUID);
        applyModifier(player, ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get(), totalGeneralCritDamageMultiplier, SCALING_UUID);


        applyModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), totalArmorMultiplier, SCALING_UUID);
        applyModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), totalToughnessMultiplier, SCALING_UUID);
        applyModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), totalAttackSpeedMultiplier, SCALING_UUID);

        applyModifier(player, Attributes.ATTACK_DAMAGE, totalStr * 0.025, UUID.fromString("32825476-1234-5454-5454-113215411111"));
        applyModifier(player, Attributes.ARMOR, armorRating, SCALING_UUID);
        applyModifier(player, Attributes.ARMOR_TOUGHNESS, toughnessRating, SCALING_UUID);
        applyModifier(player, Attributes.KNOCKBACK_RESISTANCE, totalKnockbackResist * 0.01, SCALING_UUID);
        applyModifier(player, Attributes.MOVEMENT_SPEED, totalMoveSpeed * 0.0001, SCALING_UUID);
        applyModifier(player, ForgeMod.BLOCK_REACH.get(), totalBlockReach, SCALING_UUID);
        applyModifier(player, ForgeMod.ENTITY_REACH.get(), totalEntityReach, SCALING_UUID);

        player.getAttribute(Attributes.ARMOR).removeModifier(UUID.fromString("e1125476-1234-5454-5454-113215411111"));
        player.getAttribute(Attributes.ARMOR_TOUGHNESS).removeModifier(UUID.fromString("e2225476-1234-5454-5454-113215411111"));
        player.getAttribute(Attributes.MAX_HEALTH).removeModifier(UUID.fromString("e3325476-1234-5454-5454-113215411111"));

        double totalArmorMult = player.getAttributeValue(ModAttributes.ARMOR_MULTIPLIER.get());
        double totalToughnessMult = player.getAttributeValue(ModAttributes.TOUGHNESS_MULTIPLIER.get());
        double totalHealthMult = player.getAttributeValue(ModAttributes.HEALTH_MULTIPLIER.get());

        double currentArmor = player.getAttributeValue(Attributes.ARMOR);
        double armorBonus = currentArmor * (totalArmorMult - 1);
        applyModifier(player, Attributes.ARMOR, armorBonus, UUID.fromString("e1125476-1234-5454-5454-113215411111"));

        double currentToughness = player.getAttributeValue(Attributes.ARMOR_TOUGHNESS);
        double toughnessBonus = currentToughness * (totalToughnessMult - 1);
        applyModifier(player, Attributes.ARMOR_TOUGHNESS, toughnessBonus, UUID.fromString("e2225476-1234-5454-5454-113215411111"));


        double currentMaxHealth = player.getAttributeValue(Attributes.MAX_HEALTH);
        double healthBonus = currentMaxHealth * (totalHealthMult - 1);
        applyModifier(player, Attributes.MAX_HEALTH, healthBonus, UUID.fromString("e3325476-1234-5454-5454-113215411111"));


        UUID AS_UUID = UUID.fromString("e2225476-1234-5352-5454-113215411111");
        removeModifier(player, Attributes.ATTACK_SPEED, AS_UUID);
        double ASBonus = getScaledValue(player, ModAttributes.HASTE.get(), ModAttributes.HASTE_MULTIPLIER.get());
        applyPercentModifier(player, Attributes.ATTACK_SPEED, ASBonus / 100.0, AS_UUID);

        if (player.getHealth() > player.getMaxHealth()) {
            player.setHealth(player.getMaxHealth());
        }
    }

    private static void removeAllStats(Player player, UUID itemUUID) {
        for (AttributeInstance inst : player.getAttributes().getSyncableAttributes()) {
            inst.removeModifier(of(itemUUID, inst.getAttribute()));
        }
        remove(player, Attributes.KNOCKBACK_RESISTANCE, itemUUID);
    }

    public static void rebuildAll(Player player) {
        clearAllItemModifiers(player);

        for (ItemStack stack : getAllEquippedItems(player)) {
            updateStats(player, stack, true);
        }
    }

    private static void clearAllItemModifiers(Player player) {
        for (AttributeInstance inst : player.getAttributes().getSyncableAttributes()) {
            for (AttributeModifier mod : inst.getModifiers()) {
                if ("coldsmod_item_stat".equals(mod.getName())) {
                    inst.removeModifier(mod);
                }
            }
        }
    }

    private static Iterable<ItemStack> getAllEquippedItems(Player player) {
        List<ItemStack> stacks = new ArrayList<>();

        for (ItemStack armor : player.getArmorSlots()) {
            stacks.add(armor);
        }

        stacks.add(player.getMainHandItem());
        stacks.add(player.getOffhandItem());

        CuriosApi.getCuriosInventory(player).ifPresent(inv -> {
            inv.getCurios().values().forEach(handler -> {
                for (int i = 0; i < handler.getSlots(); i++) {
                    stacks.add(handler.getStacks().getStackInSlot(i));
                }
            });
        });

        return stacks;
    }


    public static void applyCrossbowTag(Player player) {
        // For draw speed mixin, put draw speed increase data to item stack

        ItemStack mainHand = player.getMainHandItem();
        String mainType = ItemRarityUtils.getItemType(mainHand);

        ItemStack offHand = player.getOffhandItem();
        String offType = ItemRarityUtils.getItemType(player.getOffhandItem());

        if ("crossbow".equals(mainType)) {
            double scaledHaste = getScaledValue(player, ModAttributes.NOCK_HASTE.get(), ModAttributes.NOCK_HASTE_MULTIPLIER.get());
            mainHand.getOrCreateTag().putDouble("drawSpeedIncrease", scaledHaste);
            mainHand.getOrCreateTag().putBoolean("adrenalineInjection", player.hasEffect(ModEffects.ADRENALINE_INJECTION_UP.get()));
        }
        if ("crossbow".equals(offType)) {
            double scaledHaste = getScaledValue(player, ModAttributes.NOCK_HASTE.get(), ModAttributes.NOCK_HASTE_MULTIPLIER.get());
            mainHand.getOrCreateTag().putDouble("drawSpeedIncrease", scaledHaste);
            if (player.hasEffect(ModEffects.ADRENALINE_INJECTION_UP.get())) {
                offHand.getOrCreateTag().putBoolean("adrenalineInjection", true);
            } else {
                mainHand.getOrCreateTag().remove("adrenalineInjection");
            }
        }
    }

    public static double getScaledValue(Player player, Attribute ratingAttr, Attribute multiplierAttr) {
        double rating = player.getAttributeValue(ratingAttr);
        double multiplierValue = player.getAttributeValue(multiplierAttr);

        double effectiveRating = rating * multiplierValue;

        if (500 + effectiveRating == 0) return 0;

        return (500 * effectiveRating) / (500 + effectiveRating);
    }

    public void removeCrossbowTag(Player player) {
        // For draw speed mixin, remove draw speed tag

        ItemStack mainHand = player.getMainHandItem();
        String mainType = ItemRarityUtils.getItemType(mainHand);

        ItemStack offHand = player.getOffhandItem();
        String offType = ItemRarityUtils.getItemType(player.getOffhandItem());

        if ("crossbow".equals(mainType)) {mainHand.getOrCreateTag().remove("adrenalineInjection");}
        if ("crossbow".equals(offType)) {offHand.getOrCreateTag().remove("adrenalineInjection");}
    }

    public void addCrossbowTag(Player player) {
        // For draw speed mixin, remove draw speed tag

        ItemStack mainHand = player.getMainHandItem();
        String mainType = ItemRarityUtils.getItemType(mainHand);

        ItemStack offHand = player.getOffhandItem();
        String offType = ItemRarityUtils.getItemType(player.getOffhandItem());

        if ("crossbow".equals(mainType)) {mainHand.getOrCreateTag().putBoolean("adrenalineInjection", true);}
        if ("crossbow".equals(offType)) {offHand.getOrCreateTag().putBoolean("adrenalineInjection", true);}
    }

    private static void applyPercentModifier(Player player, Attribute attribute, double percent, UUID uuid) {
        AttributeModifier modifier = new AttributeModifier(uuid, attribute.getDescriptionId() + "_percent", percent, AttributeModifier.Operation.MULTIPLY_TOTAL);
        var attr = player.getAttribute(attribute);
        if (attr != null) {
            attr.removeModifier(uuid);
            attr.addTransientModifier(modifier);
        }
    }

    public static void applyModifier(LivingEntity entity, Attribute attribute, double value, UUID uuid) {
        AttributeInstance instance = entity.getAttribute(attribute);
        if (instance == null) return;

        if (instance.getModifier(uuid) != null) {
            instance.removeModifier(uuid);
        }

        if (value != 0) {
            AttributeModifier modifier = new AttributeModifier(uuid, "Custom Stat", value, AttributeModifier.Operation.ADDITION);
            instance.addTransientModifier(modifier);
        }

        if (!entity.level().isClientSide && entity instanceof ServerPlayer serverPlayer) {
            serverPlayer.connection.send(new ClientboundUpdateAttributesPacket(entity.getId(), Collections.singleton(instance)));
        }
    }

    public static void removeModifier(LivingEntity entity, Attribute attribute, UUID uuid) {
        AttributeInstance instance = entity.getAttribute(attribute);
        if (instance == null) return;

        if (instance.getModifier(uuid) != null) {
            instance.removeModifier(uuid);

            if (!entity.level().isClientSide && entity instanceof ServerPlayer serverPlayer) {
                serverPlayer.connection.send(new ClientboundUpdateAttributesPacket(entity.getId(), Collections.singleton(instance)));
            }
        }
    }
}