//package net.cold.coldsmod.stat;
//
//import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket;
//import net.minecraft.server.level.ServerPlayer;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.entity.ai.attributes.Attribute;
//import net.minecraft.world.entity.ai.attributes.AttributeInstance;
//import net.minecraft.world.entity.ai.attributes.AttributeModifier;
//import net.minecraft.world.entity.ai.attributes.Attributes;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.ItemStack;
//import net.minecraftforge.common.ForgeMod;
//import net.minecraftforge.common.MinecraftForge;
//import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
//import net.minecraftforge.event.entity.player.PlayerEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.registries.RegistryObject;
//import top.theillusivec4.curios.api.CuriosApi;
//import top.theillusivec4.curios.api.event.CurioChangeEvent;
//
//import java.util.*;
//
//
//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
//public class AttributeApplier {
//    private static final String KEY = "coldsmod_item_uuid";
//    private static final UUID SCALING_UUID = UUID.fromString("f47171d3-a44d-4581-9f93-18963214722a");
//    private static final UUID STR_MILESTONE_UUID = UUID.fromString("f47171d3-a44d-4581-9f93-17763214722a");
//    private static final UUID STR_PERPOINT_UUID = UUID.fromString("f47171d3-a24d-4581-9f33-17763214722a");
//    private static final UUID AS_UUID = UUID.fromString("e2225476-1234-5352-5454-113215411111");
//
//    private static final UUID ARMOR_UUID = UUID.fromString("e2222376-1234-5352-5254-113225461111");
//    private static final UUID TOUGHNESS_UUID = UUID.fromString("e2228876-1234-5352-5454-118115412111");
//    private static final UUID HP_UUID = UUID.fromString("e2209476-1234-5352-5454-113995001111");
//
//    private static final Map<UUID, Map<Attribute, Double>> PLAYER_CACHE = new HashMap<>();
//
//
//
//    public static void register() {
//        MinecraftForge.EVENT_BUS.register(new AttributeApplier());
//    }
//
//    @SubscribeEvent
//    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
//        rebuildAll(event.getEntity());
//    }
//
//    @SubscribeEvent
//    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
//        rebuildAll(event.getEntity());
//    }
//
//    public static UUID get(ItemStack stack) {
//        CompoundTag tag = stack.getOrCreateTag();
//        if (!tag.hasUUID(KEY)) {
//            tag.putUUID(KEY, UUID.randomUUID());
//        }
//        return tag.getUUID(KEY);
//    }
//
//    public static UUID of(UUID itemUUID, Attribute attr) {
//        return UUID.nameUUIDFromBytes(
//                (itemUUID + "|" + attr.getDescriptionId()).getBytes()
//        );
//    }
//
//    public static void apply(Player player, Attribute attr, double value, UUID itemUUID) {
//        AttributeInstance inst = player.getAttribute(attr);
//        if (inst == null) return;
//
//        UUID uuid = of(itemUUID, attr);
//        AttributeModifier existing = inst.getModifier(uuid);
//
//        if (existing != null && existing.getAmount() == value) return;
//
//        if (existing != null) inst.removeModifier(uuid);
//        if (value != 0) {
//            inst.addTransientModifier(new AttributeModifier(uuid, "coldsmod_item_stat", value, AttributeModifier.Operation.ADDITION));
//        }
//    }
//
//
//
//    public static void remove(Player player, Attribute attr, UUID itemUUID) {
//        AttributeInstance inst = player.getAttribute(attr);
//        if (inst == null) return;
//
//        inst.removeModifier(of(itemUUID, attr));
//    }
//
//    @SubscribeEvent
//    public static void onEquipmentChange(LivingEquipmentChangeEvent event) {
//        if (event.getEntity() instanceof Player player) {
//            updateStats(player, event.getFrom(), false);
//            updateStats(player, event.getTo(), true);
//            applyAttributeScaling(player);
//            recalculateDynamicBonuses(player);
//            applyCrossbowTag(player);
//        }
//    }
//
//    @SubscribeEvent
//    public static void onCurioChange(CurioChangeEvent event) {
//        if (event.getEntity() instanceof Player player) {
//            updateStats(player, event.getFrom(), false);
//            updateStats(player, event.getTo(), true);
//            applyAttributeScaling(player);
//            recalculateDynamicBonuses(player);
//            applyCrossbowTag(player);
//        }
//    }
//    public static void updateStats(Player player, ItemStack stack, boolean equipped) {
//        if (player.level().isClientSide || stack.isEmpty() || !StatUtils.hasStats(stack)) return;
//
//        CustomStats s = StatUtils.readStatsFromNBT(stack);
//        UUID itemUUID = get(stack);
//
//
//        boolean isMainHand = (stack == player.getMainHandItem());
//        boolean isOffHand = (stack == player.getOffhandItem());
//
//        boolean isHeld = isMainHand || isOffHand;
//
//        String itemType = ItemRarityUtils.getItemType(stack);
//        boolean isValidCombatType = "crossbow".equals(itemType)
//                || "bow".equals(itemType)
//                || "sword".equals(itemType)
//                || "shield".equals(itemType)
//                || "tool".equals(itemType);
//
//        if (equipped) {
//            if (!isHeld || isValidCombatType) {
//                apply(player, ModAttributes.STR.get(), s.getStr(), itemUUID);
//                apply(player, ModAttributes.FORT.get(), s.getFort(), itemUUID);
//                apply(player, ModAttributes.DEX.get(), s.getDex(), itemUUID);
////                apply(player, ModAttributes.INTELLIGENCE.get(), s.getIntelligence(), itemUUID);
//                apply(player, ModAttributes.WISDOM.get(), s.getWisdom(), itemUUID);
//                apply(player, ModAttributes.CON.get(), s.getCon(), itemUUID);
//                apply(player, ModAttributes.PERC.get(), s.getPerc(), itemUUID);
//                apply(player, ModAttributes.INSIGHT.get(), s.getInsight(), itemUUID);
//
//                apply(player, ModAttributes.POTENCY.get(), s.getDamage(), itemUUID);
//                apply(player, ModAttributes.HASTE.get(), s.getAttackSpeed(), itemUUID);
//                apply(player, ModAttributes.ACCURACY.get(), s.getCritChance(), itemUUID);
//                apply(player, ModAttributes.PRECISION.get(), s.getCritDamage(), itemUUID);
//
//                apply(player, ModAttributes.MELEE_POTENCY.get(), s.getMeleeDamage() + s.getDamage(), itemUUID);
//                apply(player, ModAttributes.MELEE_ACCURACY.get(), s.getMeleeCritChance() + s.getCritChance(), itemUUID);
//                apply(player, ModAttributes.MELEE_PRECISION.get(), s.getMeleeCritDamage() + s.getCritDamage(), itemUUID);
//
//                apply(player, ModAttributes.PROJECTILE_POTENCY.get(), s.getProjectileDamage() + s.getDamage(), itemUUID);
//
//                apply(player, ModAttributes.NOCK_HASTE.get(), s.getDrawSpeed(), itemUUID);
//                apply(player, ModAttributes.PROJECTILE_ACCURACY.get(), s.getProjectileCritChance() + s.getCritChance(), itemUUID);
//                apply(player, ModAttributes.PROJECTILE_PRECISION.get(), s.getProjectileCritDamage() + s.getCritDamage(), itemUUID);
//
//
//                // apply(player, ModAttributes.PROTECTION.get(), s.getProtection(), itemUUID);
//
//                apply(player, ModAttributes.RESTORATION.get(), s.getRestoration(), itemUUID);
//                apply(player, ModAttributes.AMPLIFICATION.get(), s.getAmplification(), itemUUID);
//                apply(player, ModAttributes.DEBUFF_RESIST.get(), s.getDebuffResist(), itemUUID);
//
//                apply(player, ModAttributes.POTENCY_MULTIPLIER.get(), s.getDamageMultiplier(), itemUUID);
//                apply(player, ModAttributes.ACCURACY_MULTIPLIER.get(), s.getCritChanceMultiplier(), itemUUID);
//                apply(player, ModAttributes.PRECISION_MULTIPLIER.get(), s.getCritDamageMultiplier(), itemUUID);
//                apply(player, ModAttributes.HASTE_MULTIPLIER.get(), s.getAttackSpeedMultiplier(), itemUUID);
//
//                apply(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), s.getMeleeDamageMultiplier() + s.getDamageMultiplier(), itemUUID);
//                apply(player, ModAttributes.MELEE_ACCURACY_MULTIPLIER.get(), s.getMeleeCritChanceMultiplier() + s.getCritChanceMultiplier(), itemUUID);
//                apply(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), s.getMeleeCritDamageMultiplier() + s.getAttackSpeedMultiplier(), itemUUID);
//
//                apply(player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), s.getProjectileDamageMultiplier() + s.getDamageMultiplier(), itemUUID);
//                apply(player, ModAttributes.PROJECTILE_ACCURACY_MULTIPLIER.get(), s.getProjectileCritChanceMultiplier() + s.getCritChanceMultiplier(), itemUUID);
//                apply(player, ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get(), s.getProjectileCritDamageMultiplier() + s.getAttackSpeedMultiplier(), itemUUID);
//
//                apply(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), s.getDrawSpeedMultiplier(), itemUUID);
//                apply(player, ModAttributes.PROTECTION_MULTIPLIER.get(), s.getProtectionMultiplier(), itemUUID);
//                apply(player, ModAttributes.RESTORATION_MULTIPLIER.get(), s.getRestorationMultiplier(), itemUUID);
//                apply(player, ModAttributes.AMPLIFICATION_MULTIPLIER.get(), s.getAmplificationMultiplier(), itemUUID);
//                apply(player, ModAttributes.ARMOR_MULTIPLIER.get(), s.getArmorMultiplier(), itemUUID);
//                apply(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), s.getToughnessMultiplier(), itemUUID);
//                apply(player, ModAttributes.HEALTH_MULTIPLIER.get(), s.getHealthMultiplier(), itemUUID);
//
//                apply(player, Attributes.ARMOR, s.getArmor(), itemUUID);
//                apply(player, Attributes.ARMOR_TOUGHNESS, s.getArmorToughness(), itemUUID);
//                apply(player, Attributes.KNOCKBACK_RESISTANCE, s.getKnockbackResist() / 100, itemUUID);
//                apply(player, Attributes.MAX_HEALTH, s.getMaxHealth(), itemUUID);
//                apply(player, Attributes.LUCK, s.getLuck(), itemUUID);
//                apply(player, ForgeMod.BLOCK_REACH.get(), s.getBlockReach(), itemUUID);
//                apply(player, ForgeMod.ENTITY_REACH.get(), s.getEntityReach(), itemUUID);
//                apply(player, ForgeMod.STEP_HEIGHT_ADDITION.get(), s.getStepHeight(), itemUUID);
//                apply(player, ForgeMod.SWIM_SPEED.get(), s.getSwimSpeed() / 100, itemUUID);
//                apply(player, Attributes.MOVEMENT_SPEED, s.getMoveSpeed() / 1000, itemUUID);
//
//                apply(player, ModAttributes.JUMP_BOOST.get(), s.getJumpBoost(), itemUUID);
//                apply(player, ModAttributes.MINING_SPEED.get(), s.getMiningSpeed(), itemUUID);
//                apply(player, ModAttributes.XP_GAIN.get(), s.getXpGain(), itemUUID);
//            }
//        } else {
//            removeAllStats(player, itemUUID);
//        }
//    }
//
//    public static void applyAttributeScaling(Player player) {
//        if (player.level().isClientSide) return;
//
//        // --- STEP 1: Dirty Checking ---
//        // We capture all base attributes at once
//        Map<Attribute, Double> currentValues = new HashMap<>();
//        currentValues.put(ModAttributes.STR.get(), player.getAttributeValue(ModAttributes.STR.get()));
//        currentValues.put(ModAttributes.FORT.get(), player.getAttributeValue(ModAttributes.FORT.get()));
//        currentValues.put(ModAttributes.DEX.get(), player.getAttributeValue(ModAttributes.DEX.get()));
//        currentValues.put(ModAttributes.CON.get(), player.getAttributeValue(ModAttributes.CON.get()));
//        currentValues.put(ModAttributes.PERC.get(), player.getAttributeValue(ModAttributes.PERC.get()));
//        currentValues.put(ModAttributes.INSIGHT.get(), player.getAttributeValue(ModAttributes.INSIGHT.get()));
//        currentValues.put(ModAttributes.WISDOM.get(), player.getAttributeValue(ModAttributes.WISDOM.get()));
//
//        Map<Attribute, Double> cachedValues = PLAYER_CACHE.get(player.getUUID());
//
//        // If base stats haven't changed, skip the heavy milestone and modifier logic
//        if (cachedValues != null && cachedValues.equals(currentValues)) {
//            return;
//        }
//        PLAYER_CACHE.put(player.getUUID(), currentValues);
//
//        // --- STEP 2: Initialize Accumulator with Base Math ---
//        BonusAccumulator stats = new BonusAccumulator();
//
//        double tStr = currentValues.get(ModAttributes.STR.get());
//        double tFort = currentValues.get(ModAttributes.FORT.get());
//        double tDex = currentValues.get(ModAttributes.DEX.get());
//        double tCon = currentValues.get(ModAttributes.CON.get());
//        double tPerc = currentValues.get(ModAttributes.PERC.get());
//        double tInsight = currentValues.get(ModAttributes.INSIGHT.get());
//        double tWisdom = currentValues.get(ModAttributes.WISDOM.get());
//
//        stats.generalDamage = tStr * 0.35 + tCon * 0.125;
//        stats.armor = tCon * 0.15 + tPerc * 0.1 + tFort * 0.2;
//        stats.toughness = tFort * 0.15;
//        stats.debuffResist = tCon * 0.2 + tWisdom * 0.125;
//        stats.knockbackResist = tFort * 0.2;
//        stats.moveSpeed = tDex * 0.12;
//        stats.attackSpeed = tDex * 0.125;
//        stats.drawSpeed = tDex * 0.125;
//        stats.critChance = tDex * 0.2 + tPerc * 0.175;
//        stats.xpGain = tInsight * 0.25;
//        stats.blockReach = tInsight * 0.05;
//        stats.miningSpeed = tInsight * 0.25;
//        stats.restoration = tWisdom * 0.15;
//        stats.amplification = tWisdom * 0.2;
//
//        // --- STEP 3: Run the Milestone Registry ---
//        applyMilestones(player, stats);
//
//        // --- STEP 4: Apply Accumulated Ratings ---
//        applyModifier(player, ModAttributes.POTENCY.get(), stats.generalDamage, SCALING_UUID);
//        applyModifier(player, ModAttributes.ACCURACY.get(), stats.critChance, SCALING_UUID);
//        applyModifier(player, ModAttributes.PRECISION.get(), stats.critDamage, SCALING_UUID);
//        applyModifier(player, ModAttributes.HASTE.get(), stats.attackSpeed, SCALING_UUID);
//        applyModifier(player, ModAttributes.NOCK_HASTE.get(), stats.drawSpeed, SCALING_UUID);
//
//        // Specializations
//        applyModifier(player, ModAttributes.MELEE_POTENCY.get(), stats.generalDamage, SCALING_UUID);
//        applyModifier(player, ModAttributes.MELEE_ACCURACY.get(), stats.critChance, SCALING_UUID);
//        applyModifier(player, ModAttributes.MELEE_PRECISION.get(), stats.critDamage, SCALING_UUID);
//
//        double projPotency = stats.generalDamage + (tDex * 0.15);
//        applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), projPotency, SCALING_UUID);
//        applyModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), stats.critChance, SCALING_UUID);
//        applyModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), stats.critDamage, SCALING_UUID);
//
//        // Utilities
//        applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), stats.debuffResist, SCALING_UUID);
//        applyModifier(player, ModAttributes.XP_GAIN.get(), stats.xpGain, SCALING_UUID);
//        applyModifier(player, ModAttributes.MINING_SPEED.get(), stats.miningSpeed, SCALING_UUID);
//        applyModifier(player, ModAttributes.RESTORATION.get(), stats.restoration, SCALING_UUID);
//        applyModifier(player, ModAttributes.AMPLIFICATION.get(), stats.amplification, SCALING_UUID);
//
//        // --- STEP 5: Apply Multipliers ---
//        applyModifier(player, ModAttributes.POTENCY_MULTIPLIER.get(), stats.generalDamageMult, SCALING_UUID);
//        applyModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), stats.generalDamageMult, SCALING_UUID);
//        applyModifier(player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), stats.generalDamageMult, SCALING_UUID);
//
//        applyModifier(player, ModAttributes.PRECISION_MULTIPLIER.get(), stats.critDamageMult, SCALING_UUID);
//        applyModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), stats.critDamageMult, SCALING_UUID);
//        applyModifier(player, ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get(), stats.critDamageMult, SCALING_UUID);
//
//        applyModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), stats.armorMult, SCALING_UUID);
//        applyModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), stats.toughnessMult, SCALING_UUID);
//        applyModifier(player, ModAttributes.RESTORATION_MULTIPLIER.get(), stats.restorationMult, SCALING_UUID);
//        applyModifier(player, ModAttributes.AMPLIFICATION_MULTIPLIER.get(), stats.amplificationMult, SCALING_UUID);
//        applyModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), stats.attackSpeedMult, SCALING_UUID);
//
//        // --- STEP 6: Apply Vanilla Attributes ---
//        applyModifier(player, Attributes.ATTACK_DAMAGE, tStr * 0.025, STR_PERPOINT_UUID);
//        applyModifier(player, Attributes.ARMOR, stats.armor, SCALING_UUID);
//        applyModifier(player, Attributes.ARMOR_TOUGHNESS, stats.toughness, SCALING_UUID);
//        applyModifier(player, Attributes.KNOCKBACK_RESISTANCE, stats.knockbackResist * 0.01, SCALING_UUID);
//        applyModifier(player, Attributes.MOVEMENT_SPEED, stats.moveSpeed * 0.001, SCALING_UUID);
//        applyModifier(player, ForgeMod.BLOCK_REACH.get(), stats.blockReach, SCALING_UUID);
//        applyModifier(player, ForgeMod.ENTITY_REACH.get(), stats.entityReach, SCALING_UUID);
//
//        // Flat Vanilla Milestones
//        if (tStr >= 80) applyModifier(player, Attributes.ATTACK_DAMAGE, 2.0, STR_MILESTONE_UUID);
//        else removeModifier(player, Attributes.ATTACK_DAMAGE, STR_MILESTONE_UUID);
//
//        // --- STEP 7: Final Percentage Calcs ---
//
//        // --- STEP 8: Batch Sync to Client (Network Optimization) ---
//        if (player instanceof ServerPlayer serverPlayer) {
//            serverPlayer.connection.send(new ClientboundUpdateAttributesPacket(
//                    player.getId(),
//                    player.getAttributes().getSyncableAttributes()
//            ));
//        }
//    }
//
//    private static void recalculateDynamicBonuses(Player player) {
//
//        removeModifier(player, Attributes.ARMOR, ARMOR_UUID);
//        removeModifier(player, Attributes.ARMOR_TOUGHNESS, TOUGHNESS_UUID);
//        removeModifier(player, Attributes.MAX_HEALTH, HP_UUID);
//
//
//        double baseArmor = player.getAttributeValue(Attributes.ARMOR);
//        double baseToughness = player.getAttributeValue(Attributes.ARMOR_TOUGHNESS);
//        double baseMaxHealth = player.getAttributeValue(Attributes.MAX_HEALTH);
//
//
//        double totalArmorMult = player.getAttributeValue(ModAttributes.ARMOR_MULTIPLIER.get());
//        double armorBonus = baseArmor * (totalArmorMult - 1);
//        applyModifier(player, Attributes.ARMOR, armorBonus, ARMOR_UUID);
//
//        double totalToughnessMult = player.getAttributeValue(ModAttributes.TOUGHNESS_MULTIPLIER.get());
//        double toughnessBonus = baseToughness * (totalToughnessMult - 1);
//        applyModifier(player, Attributes.ARMOR_TOUGHNESS, toughnessBonus, TOUGHNESS_UUID);
//
//        double totalHealthMult = player.getAttributeValue(ModAttributes.HEALTH_MULTIPLIER.get());
//        double healthBonus = baseMaxHealth * (totalHealthMult - 1);
//        applyModifier(player, Attributes.MAX_HEALTH, healthBonus, HP_UUID);
//
//        removeModifier(player, Attributes.ATTACK_SPEED, AS_UUID);
//        double ASBonus = getScaledValue(player, ModAttributes.HASTE.get(), ModAttributes.HASTE_MULTIPLIER.get());
//        applyPercentModifier(player, Attributes.ATTACK_SPEED, ASBonus / 100.0, AS_UUID);
//
//        if (player.getHealth() > player.getMaxHealth()) {
//            player.setHealth(player.getMaxHealth());
//        }
//    }
//
//    private static final Map<RegistryObject<Attribute>, TreeMap<Integer, MilestoneAction>> MILESTONES = new HashMap<>();
//
//    static {
//        // --- STRENGTH ---
//        register(ModAttributes.STR, 30, s -> s.generalDamage += 7.5);
//        register(ModAttributes.STR, 40, s -> s.armor += 5);
//        register(ModAttributes.STR, 50, s -> s.attackSpeed += 8);
//        register(ModAttributes.STR, 60, s -> s.critDamageMult += 0.15);
//        register(ModAttributes.STR, 70, s -> s.generalDamageMult += 0.25);
//
//        // --- FORTITUDE ---
//        register(ModAttributes.FORT, 30, s -> s.armor += 4);
//        register(ModAttributes.FORT, 40, s -> s.armorMult += 0.08);
//        register(ModAttributes.FORT, 50, s -> s.knockbackResist += 10.0);
//        register(ModAttributes.FORT, 60, s -> s.toughnessMult += 0.10);
//        register(ModAttributes.FORT, 70, s -> { s.debuffResist += 15.0; s.knockbackResist += 10; });
//        register(ModAttributes.FORT, 80, s -> { s.toughness += 9.0; s.armor += 9.0; });
//
//        // --- DEXTERITY ---
//        register(ModAttributes.DEX, 30, s -> s.critChance += 6);
//        register(ModAttributes.DEX, 40, s -> s.critDamage += 6);
//        register(ModAttributes.DEX, 50, s -> s.drawSpeed += 8);
//        register(ModAttributes.DEX, 60, s -> s.moveSpeed += 8);
//        register(ModAttributes.DEX, 70, s -> { s.attackSpeedMult += 0.08; s.projectileDamageMult += 0.15; });
//        register(ModAttributes.DEX, 80, s -> {
//            s.generalDamage += 9; s.drawSpeed += 9; s.critChance += 9; s.critDamage += 9;
//        });
//
//        // --- CONSTITUTION ---
//        register(ModAttributes.CON, 30, s -> s.armor += 5.0);
//        register(ModAttributes.CON, 40, s -> s.generalDamage += 5);
//        register(ModAttributes.CON, 50, s -> { s.debuffResist += 12; s.toughness += 6; });
//        register(ModAttributes.CON, 60, s -> s.toughnessMult += 0.125);
//        register(ModAttributes.CON, 70, s -> s.armorMult += 0.125);
//        register(ModAttributes.CON, 80, s -> s.generalDamage += 10);
//
//        // --- PERCEPTION ---
//        register(ModAttributes.PERC, 30, s -> s.toughness += 3.0);
//        register(ModAttributes.PERC, 40, s -> s.generalDamageMult += 0.075);
//        register(ModAttributes.PERC, 50, s -> s.critDamage += 10.0);
//        register(ModAttributes.PERC, 60, s -> s.entityReach += 1);
//        register(ModAttributes.PERC, 70, s -> s.armorMult += 0.08);
//        register(ModAttributes.PERC, 80, s -> s.critDamageMult += 0.25);
//
//        // --- INSIGHT ---
//        register(ModAttributes.INSIGHT, 10, s -> s.miningSpeed += 10.0);
//        register(ModAttributes.INSIGHT, 20, s -> s.xpGain += 10);
//        register(ModAttributes.INSIGHT, 30, s -> s.blockReach += 0.5);
//        register(ModAttributes.INSIGHT, 40, s -> { s.miningSpeed += 25; s.xpGain += 25; });
//
//        // --- WISDOM ---
//        register(ModAttributes.WISDOM, 10, s -> s.restoration += 8);
//        register(ModAttributes.WISDOM, 20, s -> s.amplification += 10);
//        register(ModAttributes.WISDOM, 30, s -> s.debuffResist += 10.0);
//        register(ModAttributes.WISDOM, 40, s -> s.restorationMult += 0.10);
//        register(ModAttributes.WISDOM, 50, s -> { s.armor += 7.0; s.amplification += 10; });
//        register(ModAttributes.WISDOM, 60, s -> { s.restoration += 7.0; s.amplificationMult += 0.1; });
//    }
//
//    public static void applyMilestones(Player player, BonusAccumulator stats) {
//        Map<Attribute, Double> playerTotals = new HashMap<>();
//        playerTotals.put(ModAttributes.STR.get(), player.getAttributeValue(ModAttributes.STR.get()));
//        playerTotals.put(ModAttributes.FORT.get(), player.getAttributeValue(ModAttributes.FORT.get()));
//        playerTotals.put(ModAttributes.DEX.get(), player.getAttributeValue(ModAttributes.DEX.get()));
//        playerTotals.put(ModAttributes.CON.get(), player.getAttributeValue(ModAttributes.CON.get()));
//        playerTotals.put(ModAttributes.PERC.get(), player.getAttributeValue(ModAttributes.PERC.get()));
//        playerTotals.put(ModAttributes.INSIGHT.get(), player.getAttributeValue(ModAttributes.INSIGHT.get()));
//        playerTotals.put(ModAttributes.WISDOM.get(), player.getAttributeValue(ModAttributes.WISDOM.get()));
//
//        playerTotals.forEach((attr, value) -> {
//            MILESTONES.forEach((regObj, milestones) -> {
//                if (regObj.get() == attr) {
//                    milestones.headMap(value.intValue(), true)
//                            .values()
//                            .forEach(action -> action.apply(stats));
//                }
//            });
//        });
//    }
//
//    private static void removeAllStats(Player player, UUID itemUUID) {
//        for (AttributeInstance inst : player.getAttributes().getSyncableAttributes()) {
//            inst.removeModifier(of(itemUUID, inst.getAttribute()));
//        }
//        remove(player, Attributes.KNOCKBACK_RESISTANCE, itemUUID);
//    }
//
//
//    public static void rebuildAll(Player player) {
//        clearAllItemModifiers(player);
//
//        for (ItemStack stack : getAllEquippedItems(player)) {
//            updateStats(player, stack, true);
//        }
//    }
//
//    private static void clearAllItemModifiers(Player player) {
//        for (AttributeInstance inst : player.getAttributes().getSyncableAttributes()) {
//            for (AttributeModifier mod : inst.getModifiers()) {
//                if ("coldsmod_item_stat".equals(mod.getName())) {
//                    inst.removeModifier(mod);
//                }
//            }
//        }
//    }
//
//    private static Iterable<ItemStack> getAllEquippedItems(Player player) {
//        List<ItemStack> stacks = new ArrayList<>();
//
//        for (ItemStack armor : player.getArmorSlots()) {
//            stacks.add(armor);
//        }
//
//        stacks.add(player.getMainHandItem());
//        stacks.add(player.getOffhandItem());
//
//        CuriosApi.getCuriosInventory(player).ifPresent(inv -> {
//            inv.getCurios().values().forEach(handler -> {
//                for (int i = 0; i < handler.getSlots(); i++) {
//                    stacks.add(handler.getStacks().getStackInSlot(i));
//                }
//            });
//        });
//
//        return stacks;
//    }
//
//
//    public static void applyCrossbowTag(Player player) {
//        // For draw speed mixin, put draw speed increase data to item stack
//
//        ItemStack mainHand = player.getMainHandItem();
//        String mainType = ItemRarityUtils.getItemType(mainHand);
//
//        ItemStack offHand = player.getOffhandItem();
//        String offType = ItemRarityUtils.getItemType(player.getOffhandItem());
//
//        if ("crossbow".equals(mainType)) {
//            double prevDrawSpeed = mainHand.getOrCreateTag().getDouble("drawSpeedIncrease");
//            double scaledHaste = getScaledValue(player, ModAttributes.NOCK_HASTE.get(), ModAttributes.NOCK_HASTE_MULTIPLIER.get());
//            if (prevDrawSpeed != scaledHaste) {
//                mainHand.getOrCreateTag().putDouble("drawSpeedIncrease", scaledHaste);
//            }
//            mainHand.getOrCreateTag().putBoolean("adrenalineInjection", player.hasEffect(ModEffects.ADRENALINE_INJECTION_UP.get()));
//        }
//        if ("crossbow".equals(offType)) {
//            double prevDrawSpeed = offHand.getOrCreateTag().getDouble("drawSpeedIncrease");
//            double scaledHaste = getScaledValue(player, ModAttributes.NOCK_HASTE.get(), ModAttributes.NOCK_HASTE_MULTIPLIER.get());
//            if (prevDrawSpeed != scaledHaste) {
//                offHand.getOrCreateTag().putDouble("drawSpeedIncrease", scaledHaste);
//            }
//            if (player.hasEffect(ModEffects.ADRENALINE_INJECTION_UP.get())) {
//                offHand.getOrCreateTag().putBoolean("adrenalineInjection", true);
//            } else {
//                offHand.getOrCreateTag().remove("adrenalineInjection");
//            }
//        }
//    }
//
//    public static double getScaledValue(Player player, Attribute ratingAttr, Attribute multiplierAttr) {
//        double rating = player.getAttributeValue(ratingAttr);
//        double multiplierValue = player.getAttributeValue(multiplierAttr);
//
//        double effectiveRating = rating * multiplierValue;
//
//        if (500 + effectiveRating == 0) return 0;
//
//        return (500 * effectiveRating) / (500 + effectiveRating);
//    }
//
//    public void removeCrossbowTag(Player player) {
//        // For draw speed mixin, remove draw speed tag
//
//        ItemStack mainHand = player.getMainHandItem();
//        String mainType = ItemRarityUtils.getItemType(mainHand);
//
//        ItemStack offHand = player.getOffhandItem();
//        String offType = ItemRarityUtils.getItemType(player.getOffhandItem());
//
//        if ("crossbow".equals(mainType)) {mainHand.getOrCreateTag().remove("adrenalineInjection");}
//        if ("crossbow".equals(offType)) {offHand.getOrCreateTag().remove("adrenalineInjection");}
//    }
//
//    public void addCrossbowTag(Player player) {
//        // For draw speed mixin, remove draw speed tag
//
//        ItemStack mainHand = player.getMainHandItem();
//        String mainType = ItemRarityUtils.getItemType(mainHand);
//
//        ItemStack offHand = player.getOffhandItem();
//        String offType = ItemRarityUtils.getItemType(player.getOffhandItem());
//
//        if ("crossbow".equals(mainType)) {mainHand.getOrCreateTag().putBoolean("adrenalineInjection", true);}
//        if ("crossbow".equals(offType)) {offHand.getOrCreateTag().putBoolean("adrenalineInjection", true);}
//    }
//
//    private static void applyPercentModifier(Player player, Attribute attribute, double percent, UUID uuid) {
//        AttributeInstance inst = player.getAttribute(attribute);
//        if (inst == null) return;
//
//        AttributeModifier existing = inst.getModifier(uuid);
//        if (existing != null && existing.getAmount() == percent) return;
//
//        if (existing != null) inst.removeModifier(uuid);
//        if (percent != 0) {
//            inst.addTransientModifier(new AttributeModifier(uuid, attribute.getDescriptionId() + "_percent", percent, AttributeModifier.Operation.MULTIPLY_TOTAL));
//        }
//    }
//
//
//    public static void applyModifier(LivingEntity entity, Attribute attribute, double value, UUID uuid) {
//        AttributeInstance instance = entity.getAttribute(attribute);
//        if (instance == null) return;
//
//        AttributeModifier existing = instance.getModifier(uuid);
//
//        if (existing != null && existing.getAmount() == value) return;
//
//        if (existing != null) instance.removeModifier(uuid);
//        if (value != 0) {
//            AttributeModifier modifier = new AttributeModifier(uuid, "Custom Stat", value, AttributeModifier.Operation.ADDITION);
//            instance.addTransientModifier(modifier);
//        }
//
//        if (!entity.level().isClientSide && entity instanceof ServerPlayer serverPlayer) {
//            serverPlayer.connection.send(new ClientboundUpdateAttributesPacket(entity.getId(), Collections.singleton(instance)));
//        }
//    }
//
//
//    public static void removeModifier(LivingEntity entity, Attribute attribute, UUID uuid) {
//        AttributeInstance instance = entity.getAttribute(attribute);
//        if (instance == null) return;
//
//        if (instance.getModifier(uuid) != null) {
//            instance.removeModifier(uuid);
//
//            if (!entity.level().isClientSide && entity instanceof ServerPlayer serverPlayer) {
//                serverPlayer.connection.send(new ClientboundUpdateAttributesPacket(entity.getId(), Collections.singleton(instance)));
//            }
//        }
//    }
//
//    @FunctionalInterface
//    interface MilestoneAction {
//        void apply(BonusAccumulator stats);
//    }
//
//    private static class BonusAccumulator {
//        public double generalDamage, armor, toughness, knockbackResist, moveSpeed;
//        public double critChance, critDamage, drawSpeed, attackSpeed, entityReach;
//        public double debuffResist, xpGain, blockReach, miningSpeed;
//        public double restoration, amplification;
//        public double generalDamageMult, critDamageMult, armorMult, toughnessMult;
//        public double attackSpeedMult, projectileDamageMult, restorationMult, amplificationMult;
//    }
//
//    private static void register(RegistryObject<Attribute> attr, int threshold, MilestoneAction action) {
//        MILESTONES.computeIfAbsent(attr, k -> new TreeMap<>()).put(threshold, action);
//    }
//
//    @SubscribeEvent
//    public static void onPlayerQuit(PlayerEvent.PlayerLoggedOutEvent event) {
//        PLAYER_CACHE.remove(event.getEntity().getUUID());
//    }
//
//    // Randomized mobs
////    @SubscribeEvent
////    public static void onMonsterSpawn(EntityJoinLevelEvent event) {
////        if (event.getLevel().isClientSide()) return;
////
////        if (event.getEntity() instanceof Enemy && event.getEntity() instanceof LivingEntity living) {
////            RandomSource random = living.getRandom();
////
////            double outgoingVal = Math.max(0.75, Math.min(1.5, 1.0 + (random.nextGaussian() * 0.15)));
////            AttributeInstance outgoing = living.getAttribute(ModAttributes.OUTGOING_DAMAGE_MULTIPLIER.get());
////            if (outgoing != null) outgoing.setBaseValue(outgoingVal);
////
////            double incomingVal = Math.max(0.75, Math.min(1.5, 1.0 + (random.nextGaussian() * 0.15)));
////            AttributeInstance incoming = living.getAttribute(ModAttributes.INCOMING_DAMAGE_MULTIPLIER.get());
////            if (incoming != null) incoming.setBaseValue(incomingVal);
////        }
////    }
//}