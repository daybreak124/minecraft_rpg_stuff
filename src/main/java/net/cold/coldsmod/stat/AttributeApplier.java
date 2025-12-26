package net.cold.coldsmod.stat;

import com.google.common.collect.Multimap;
import net.cold.coldsmod.damage.CustomMeleeDamage;
import net.cold.coldsmod.damage.CustomMeleeDamageNoProcs;
import net.cold.coldsmod.gearbonuses.effects.ModEffects;
import net.cold.coldsmod.item.ModItems;
import net.cold.coldsmod.network.DFASync;
import net.cold.coldsmod.network.NetworkHandler;
import net.cold.coldsmod.network.QuantumLeapSync;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.event.CurioChangeEvent;

import java.util.UUID;

// Custom formulas & stat calculation

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AttributeApplier {
    private static final UUID ATTACK_DAMAGE_UUID = UUID.fromString("b8f5d2f0-6c3e-4d9c-9f25-1b2c3a7d4e5f");
    private static final UUID STR_PER_POINT_UUID = UUID.fromString("b8f5d2f0-6c3e-4d9c-9f25-000000000016");
    private static final UUID MAX_HEALTH_UUID = UUID.fromString("f3e2b6c0-1234-5678-9abc-000000000001");
    private static final UUID MOVE_SPEED_UUID = UUID.fromString("f3e2b6c0-1234-5678-9abc-000000000004");
    private static final UUID KNOCKBACK_RESIST_UUID = UUID.fromString("f3e2b6c0-1234-5678-9abc-000000000005");
    private static final UUID SWIM_SPEED_UUID = UUID.fromString("f3e2b6c0-1234-5678-9abc-000000000006");
    private static final UUID ATTACK_SPEED_UUID = UUID.fromString("f3e2b6c0-1234-5678-9abc-000000000008");
    private static final UUID BLOCK_REACH_UUID = UUID.fromString("f3e2b6c0-1234-5678-9abc-000000000009");
    private static final UUID ENTITY_REACH_UUID = UUID.fromString("f3e2b6c0-1234-5678-9abc-000000000010");
    private static final UUID LUCK_UUID = UUID.fromString("f3e2b6c0-1234-5678-9abc-000000000011");
    private static final UUID STEP_HEIGHT_UUID = UUID.fromString("f3e2b6c0-1234-5678-9abc-000000000012");
    private static final UUID ARMOR_UUID = UUID.fromString("f3e2b6c0-1234-5678-9abc-000000000013");
    private static final UUID ARMOR_TOUGHNESS_UUID = UUID.fromString("f3e2b6c0-1234-5678-9abc-000000000014");
    private static final UUID BLESSING_MOVE_SPEED_UUID = UUID.fromString("f3e2b6c0-1234-5678-9abc-000000000015");
    private static final UUID FRENZY_ATTACK_DAMAGE_UUID = UUID.fromString("f3e2b6c0-1234-5678-9abc-000000000016");


    public static void register() {
        MinecraftForge.EVENT_BUS.register(new AttributeApplier());
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        new AttributeApplier().recalcStats(player);
        new AttributeApplier().checkGearBonus(player);
    }

    @SubscribeEvent
    public void onEquipmentChange(LivingEquipmentChangeEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        checkGearBonus(player);
        recalcStats(player);
    }

    @SubscribeEvent
    public void onCurioChange(CurioChangeEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        checkGearBonus(player);
        recalcStats(player);
    }


    public void checkGearBonus(Player player) {

        CompoundTag data = player.getPersistentData();

        data.remove("intimidating_presence_eligible");
        data.remove("daring_shout_eligible");
        data.remove("hawkeye_eligible");
        data.remove("solara_eligible");
//        if (!player.level().isClientSide && player instanceof ServerPlayer serverPlayer) {
//            NetworkHandler.sendToClient(new SolaraSync.SolaraFlagPacket(false), serverPlayer);
//        }

        data.remove("frenzy_eligible");
        data.remove("reckoning_eligible");
        data.remove("directed_hatred_eligible");

        data.remove("into_the_fray_eligible");
        data.remove("quantum_leap_eligible");
        if (!player.level().isClientSide && player instanceof ServerPlayer serverPlayer) {
            NetworkHandler.sendToClient(new QuantumLeapSync.QuantumLeapFlagPacket(false), serverPlayer);
        }

        data.remove("death_from_above_eligible");
        if (!player.level().isClientSide && player instanceof ServerPlayer serverPlayer) {
            NetworkHandler.sendToClient(new DFASync.DFAFlagPacket(false), serverPlayer);
        }
        data.remove("soul_severance_eligible");

        data.remove("bronzewoods_curse_applied");
        data.remove("berserk_applied");
        data.remove("chain_lightning_applied");

        data.remove("clairvoyance_applied");
        data.remove("deception_applied");
        data.remove("life_touch_applied");

        data.remove("exploit_weakness_applied");
        data.remove("adrenaline_applied");
        data.remove("explosive_tendencies_applied");


        data.remove("retaliate_applied");
        data.remove("bastion_applied");

        data.remove("lightbringer_applied");


        CuriosApi.getCuriosHelper().getEquippedCurios(player).ifPresent(handler -> {
            for (int i = 0; i < handler.getSlots() + 1; i++) {
                ItemStack stack = handler.getStackInSlot(i);
                if (stack.isEmpty()) continue;

                Item item = stack.getItem();

                if (item == ModItems.WARLORDS_GAZE.get()) {
                    data.putBoolean("intimidating_presence_eligible", true);

                    if (!player.hasEffect(ModEffects.INTIMIDATING_PRESENCE.get()) &&
                            !player.hasEffect(ModEffects.INTIMIDATING_PRESENCE_COOLDOWN.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.INTIMIDATING_PRESENCE_COOLDOWN.get(), 20*15, 0, false, false));
                    }
                }

                if (item == ModItems.HORN_OF_FEARMONGERING.get()) {
                    data.putBoolean("daring_shout_eligible", true);

                    if (!player.hasEffect(ModEffects.DARING_SHOUT.get()) &&
                            !player.hasEffect(ModEffects.DARING_SHOUT_COOLDOWN.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.DARING_SHOUT_COOLDOWN.get(), 20*15, 0, false, false));
                    }
                }

                if (item == ModItems.HANKS_EYE.get()) {
                    data.putBoolean("hawkeye_eligible", true);
                }

                if (item == ModItems.RAGE_AMPLIFIER.get()) {
                    data.putBoolean("frenzy_eligible", true);
                }

                if (item == ModItems.DROP_OF_SACRIFICIAL_BLOOD.get()) {
                    data.putBoolean("reckoning_eligible", true);
                    if (!player.hasEffect(ModEffects.RECKONING.get()) &&
                            !player.hasEffect(ModEffects.RECKONING_COOLDOWN.get()) &&
                            !player.hasEffect(ModEffects.RECKONING_ACTIVE.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.RECKONING_COOLDOWN.get(), 20*10, 0, false, false));
                    }
                }

                if (item == ModItems.HELL_ON_EARTH.get()) {
                    data.putBoolean("directed_hatred_eligible", true);
                    if (!player.hasEffect(ModEffects.DIRECTED_HATRED_READY.get()) &&
                            !player.hasEffect(ModEffects.DIRECTED_HATRED_COOLDOWN.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.DIRECTED_HATRED_COOLDOWN.get(), 20*10, 0, false, false));
                    }
                }

                if (item == ModItems.BANNER_OF_DETERMINATION.get()) {
                    data.putBoolean("into_the_fray_eligible", true);
                    if (player.hasEffect(ModEffects.QUANTUM_LEAP_COOLDOWN.get())) {
                        int duration = player.getEffect(ModEffects.QUANTUM_LEAP_COOLDOWN.get()).getDuration();
                        player.addEffect(new MobEffectInstance(ModEffects.INTO_THE_FRAY_COOLDOWN.get(), duration, 0, false, false));
                    }
                }

                if (item == ModItems.WORMHOLE.get()) {
                    data.putBoolean("quantum_leap_eligible", true);
                    if (!player.level().isClientSide && player instanceof ServerPlayer serverPlayer) {
                        NetworkHandler.sendToClient(new QuantumLeapSync.QuantumLeapFlagPacket(true), serverPlayer);
                    }
                    if (!player.hasEffect(ModEffects.QUANTUM_LEAP_READY.get()) &&
                            !player.hasEffect(ModEffects.QUANTUM_LEAP_COOLDOWN.get()) &&
                            !player.hasEffect(ModEffects.QUANTUM_LEAP_ACTIVE.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.QUANTUM_LEAP_COOLDOWN.get(), 20*35, 0, false, false));
                    }
                }

                if (item == ModItems.ORB_OF_WORLD_DESTRUCTION.get()) {
                    data.putBoolean("death_from_above_eligible", true);
                    if (!player.level().isClientSide && player instanceof ServerPlayer serverPlayer) {
                        NetworkHandler.sendToClient(new DFASync.DFAFlagPacket(true), serverPlayer);
                    }
                    if (!player.hasEffect(ModEffects.DEATH_FROM_ABOVE.get()) &&
                            !player.hasEffect(ModEffects.DEATH_FROM_ABOVE_COOLDOWN.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.DEATH_FROM_ABOVE_COOLDOWN.get(), 20*15, 0, false, false));
                    }
                }

                if (item == ModItems.SOUL_MAGNET.get()) {
                    data.putBoolean("soul_severance_eligible", true);
                    if (!player.hasEffect(ModEffects.SOUL_SEVERANCE_READY.get()) &&
                            !player.hasEffect(ModEffects.SOUL_SEVERANCE_COOLDOWN.get()) &&
                            !player.hasEffect(ModEffects.SOUL_SEVERANCE_ACTIVE.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.SOUL_SEVERANCE_COOLDOWN.get(), 20 * 15, 0, false, false));
                    }
                }

                if (item == ModItems.BRANCH_OF_THE_WORLD_TREE.get()) {
                    data.putBoolean("bronzewoods_curse_applied", true);

                    if (!player.hasEffect(ModEffects.BRONZEWOOD_COOLDOWN.get()) &&
                            !player.hasEffect(ModEffects.BRONZEWOOD_READY.get())) {

                        player.addEffect(new MobEffectInstance(ModEffects.BRONZEWOOD_COOLDOWN.get(), 20 * 20, 0, false, false));
                    }
                }

                if (!data.getBoolean("berserk_applied") &&
                        item == ModItems.BLOODTHIRST.get()) {
                    data.putBoolean("berserk_applied", true);

                    if (!player.hasEffect(ModEffects.BERSERK_TIMER.get())) {

                        player.addEffect(new MobEffectInstance(
                                ModEffects.BERSERK_TIMER.get(), 20 * 15, 0, false, false));
                    }
                }


                if (!data.getBoolean("chain_lightning_applied")) {
                    if (stack.getItem() == ModItems.LIGHTNING_INFUSION.get()) {

                        data.putBoolean("chain_lightning_applied", true);
                    }
                }

                if (!data.getBoolean("clairvoyance_applied") &&
                        item == ModItems.HANKS_OTHER_EYE.get()) {

                    if (!player.hasEffect(ModEffects.CLAIRVOYANCE_READY.get()) &&
                            !player.hasEffect(ModEffects.CLAIRVOYANCE_COOLDOWN.get())) {

                        player.addEffect(new MobEffectInstance(ModEffects.CLAIRVOYANCE_COOLDOWN.get(), 20 * 20, 0, false, false));
                    }
                    data.putBoolean("clairvoyance_applied", true);
                }

                if (!data.getBoolean("deception_applied") &&
                        item == ModItems.CUPIDS_ARROW.get()) {

                    data.putBoolean("deception_applied", true);

                    if (!player.hasEffect(ModEffects.DECEPTION_READY.get()) &&
                            !player.hasEffect(ModEffects.DECEPTION_COOLDOWN.get())) {

                        player.addEffect(new MobEffectInstance(ModEffects.DECEPTION_COOLDOWN.get(), 20 * 20, 0, false, false));
                    }
                }


                if (!data.getBoolean("life_touch_applied") &&
                        item == ModItems.LIFE_TOUCH.get()) {

                    data.putBoolean("life_touch_applied", true);

                    if (!player.hasEffect(ModEffects.LIFE_TOUCH_READY.get()) &&
                            !player.hasEffect(ModEffects.LIFE_TOUCH_COOLDOWN.get())) {

                        player.addEffect(new MobEffectInstance(ModEffects.LIFE_TOUCH_COOLDOWN.get(), 20 * 20, 0, false, false));
                    }
                }


                if (!data.getBoolean("exploit_weakness_applied") &&
                        item == ModItems.WEAK_POINT_STUDIES.get()) {

                    if (!player.hasEffect(ModEffects.EXPLOIT_WEAKNESS_READY.get()) &&
                            !player.hasEffect(ModEffects.EXPLOIT_WEAKNESS_COOLDOWN.get())) {

                        player.addEffect(new MobEffectInstance(ModEffects.EXPLOIT_WEAKNESS_COOLDOWN.get(), 20 * 20, 0, false, false));
                    }
                    data.putBoolean("exploit_weakness_applied", true);
                }


                if (!data.getBoolean("adrenaline_applied") &&
                        item == ModItems.ENDLESS_ADRENALINE_SYRINGE.get()) {

                    if (!player.hasEffect(ModEffects.ADRENALINE_INJECTION_UP.get()) &&
                            !player.hasEffect(ModEffects.ADRENALINE_INJECTION_COOLDOWN.get())) {

                        player.addEffect(new MobEffectInstance(ModEffects.ADRENALINE_INJECTION_COOLDOWN.get(), 20 * 30, 0, false, false));
                    }
                    data.putBoolean("adrenaline_applied", true);
                }


                if (!data.getBoolean("explosive_tendencies_applied") &&
                        item == ModItems.IGNITION_MARK.get()) {

                    if (!player.hasEffect(ModEffects.EXPLOSIVE_TENDENCY_TIMER.get())
                            && !player.hasEffect(ModEffects.EXPLOSIVE_TENDENCY_STACK.get())) {

                        player.addEffect(new MobEffectInstance(ModEffects.EXPLOSIVE_TENDENCY_TIMER.get(), 20 * 8, 0, false, false));
                    }
                    data.putBoolean("explosive_tendencies_applied", true);
                }


                if (!data.getBoolean("retaliate_applied") &&
                        item == ModItems.FORTRESS_OF_SOLITUDE.get()) {

                    if (!player.hasEffect(ModEffects.RETALIATE_COOLDOWN.get()) &&
                            !player.hasEffect(ModEffects.RETALIATE_READY.get()) &&
                            !player.hasEffect(ModEffects.RETALIATE_ACTIVE.get())) {

                        player.addEffect(new MobEffectInstance(
                                ModEffects.RETALIATE_COOLDOWN.get(), 20 * 15, 0, false, false
                        ));
                    }

                    data.putBoolean("retaliate_applied", true);
                }


                if (!data.getBoolean("bastion_applied") &&
                        item == ModItems.GUARDIAN_ANGEL.get()) {

                    if (!player.hasEffect(ModEffects.BASTION_COOLDOWN.get()) &&
                            !player.hasEffect(ModEffects.BASTION_READY.get()) &&
                            !player.hasEffect(ModEffects.BASTION_ACTIVE.get())) {

                        player.addEffect(new MobEffectInstance(
                                ModEffects.BASTION_COOLDOWN.get(), 20 * 15, 0, false, false
                        ));
                    }

                    data.putBoolean("bastion_applied", true);
                }

                if (!data.getBoolean("lightbringer_applied")) {
                    if (stack.getItem() == ModItems.BOTTLED_LIGHT.get()) {

                        data.putBoolean("lightbringer_applied", true);
                    }
                }

                if (item == ModItems.SUNSTONE_GEM.get()) {
                    data.putBoolean("solara_eligible", true);
//                    if (!player.level().isClientSide && player instanceof ServerPlayer serverPlayer) {
//                        NetworkHandler.sendToClient(new SolaraSync.SolaraFlagPacket(true), serverPlayer);
//                    }
                }
            }
        });

        if (!data.getBoolean("intimidating_presence_eligible")) {
            player.removeEffect(ModEffects.INTIMIDATING_PRESENCE.get());
        }

        if (!data.getBoolean("daring_shout_eligible")) {
            player.removeEffect(ModEffects.DARING_SHOUT.get());
        }

        if (!data.getBoolean("hawkeye_eligible")) {
            player.removeEffect(ModEffects.HAWKEYE.get());
        }

        if (!data.getBoolean("frenzy_eligible")) {
            player.removeEffect(ModEffects.FRENZY.get());
        }

        if (!data.getBoolean("reckoning_eligible")) {
            player.removeEffect(ModEffects.RECKONING.get());
            player.removeEffect(ModEffects.RECKONING_ACTIVE.get());
        }

        if (!data.getBoolean("directed_hatred_eligible")) {
            player.removeEffect(ModEffects.DIRECTED_HATRED_READY.get());
        }

        if (!data.getBoolean("quantum_leap_eligible")) {
            player.removeEffect(ModEffects.QUANTUM_LEAP_ACTIVE.get());
            player.removeEffect(ModEffects.QUANTUM_LEAP_READY.get());
        }

        if (!data.getBoolean("death_from_above_eligible")) {
            player.removeEffect(ModEffects.DEATH_FROM_ABOVE.get());
        }

        if (!data.getBoolean("soul_severance_eligible")) {
            player.removeEffect(ModEffects.SOUL_SEVERANCE_READY.get());
            player.removeEffect(ModEffects.SOUL_SEVERANCE_ACTIVE.get());
        }

        if (!data.getBoolean("bronzewoods_curse_applied")) {
            player.removeEffect(ModEffects.BRONZEWOOD_READY.get());
        }

        if (!data.getBoolean("berserk_applied")) {
            player.removeEffect(ModEffects.BERSERK.get());
            player.removeEffect(ModEffects.BERSERK_TIMER.get());
            player.removeEffect(ModEffects.BERSERK_READY.get());
        }

        if (!data.getBoolean("clairvoyance_applied")) {
            player.removeEffect(ModEffects.CLAIRVOYANCE_READY.get());
        }

        if (!data.getBoolean("deception_applied")) {
            player.removeEffect(ModEffects.DECEPTION_READY.get());
        }

        if (!data.getBoolean("life_touch_applied")) {
            player.removeEffect(ModEffects.LIFE_TOUCH_READY.get());
        }

        if (!data.getBoolean("exploit_weakness_applied")) {
            player.removeEffect(ModEffects.EXPLOIT_WEAKNESS_READY.get());
        }

        if (!data.getBoolean("adrenaline_applied")) {
            player.removeEffect(ModEffects.ADRENALINE_INJECTION_UP.get());
        }

        if (!data.getBoolean("explosive_tendencies_applied")) {
            player.removeEffect(ModEffects.EXPLOSIVE_TENDENCY_STACK.get());
            player.removeEffect(ModEffects.EXPLOSIVE_TENDENCY_TIMER.get());
        }

        if (!data.getBoolean("retaliate_applied")) {
            player.removeEffect(ModEffects.RETALIATE_READY.get());
        }

        if (!data.getBoolean("bastion_applied")) {
            player.removeEffect(ModEffects.BASTION_READY.get());
        }

        if (data.getBoolean("solara_eligible")) {
            player.addEffect(new MobEffectInstance(ModEffects.SOLARA.get(), 20*1200, 0, false, false));
        }
    }

    public void recalcStats(Player player) {
        collectStats(player);
        multiplyStatsAndAddDiminishingReturns(player);
        applyBlessings(player);
        applyCrossbowTag(player);
    }

    // Idk why stat calculation is like this, this is honestly cursed.
    public void collectStats(Player player) {

        CompoundTag data = player.getPersistentData();


        double totalMaxHealth = 0;
        double armorRating = 0;
        double toughnessRating = 0;
        double totalMoveSpeed = 0;
        double totalKnockbackResist = 0;
        double totalSwimSpeed = 0;
        double totalDebuffResist = 0;
        double attackSpeedRating = 0;
        double projectileDamageRating = 0;
        double drawSpeedRating = 0;
        double generalCritChanceRating = 0;
        double generalCritDamageRating = 0;
        double generalDamageRating = 0;
        double totalXpGain = 0;
        double totalBlockReach = 0;
        double totalEntityReach = 0;
        double totalLuck = 0;
        double totalStepHeight = 0;
        double totalJumpBoost = 0;
        double totalMiningSpeed = 0;
        double projectileCritChanceRating = 0;
        double projectileCritDamageRating = 0;
        double meleeDamageRating = 0;
        double meleeCritChanceRating = 0;
        double meleeCritDamageRating = 0;
        int totalStr = 0;
        int totalDex = 0;
        int totalFort = 0;
        int totalPerc = 0;
        int totalCon = 0;
        int totalInt = 0;
        int totalWis = 0;
        double totalArmorMultiplier = 0.0;
        double totalToughnessMultiplier = 0.0;
        double totalHealthMultiplier = 0.0;
        double totalGeneralDamageMultiplier = 0.0;
        double totalAttackSpeedMultiplier = 0.0;
        double totalGeneralCritChanceMultiplier = 0.0;
        double totalGeneralCritDamageMultiplier = 0.0;
        double totalProjectileDamageMultiplier = 0.0;
        double totalDrawSpeedMultiplier = 0.0;
        double totalMeleeDamageMultiplier = 0.0;
        double totalMeleeCritChanceMultiplier = 0.0;
        double totalMeleeCritDamageMultiplier = 0.0;
        double totalProjectileCritChanceMultiplier = 0.0;
        double totalProjectileCritDamageMultiplier = 0.0;

        CustomStats totalStats = new CustomStats();

        for (ItemStack stack : player.getArmorSlots()) {

            if (stack.getItem() instanceof ArmorItem armor) {

                double vanillaKB = 0;
                EquipmentSlot slot = armor.getType().getSlot();

                Multimap<Attribute, AttributeModifier> mods = stack.getAttributeModifiers(slot);

                for (Attribute attr : mods.keySet()) {
                    if (attr == Attributes.KNOCKBACK_RESISTANCE) {
                        for (AttributeModifier mod : mods.get(attr)) {
                            vanillaKB += mod.getAmount();
                        }
                    }
                }
                data.putDouble("vanillaKnockbackResist", 100 * vanillaKB);
            }
            if (StatUtils.hasStats(stack)) {
                CustomStats stats = StatUtils.readStatsFromNBT(stack);
                totalStats.add(stats);
            }
        }

        // --- Curios ---
        CuriosApi.getCuriosHelper().getEquippedCurios(player).ifPresent(handler -> {
            for (int i = 0; i < handler.getSlots(); i++) {
                ItemStack stack = handler.getStackInSlot(i);
                if (!stack.isEmpty() && StatUtils.hasStats(stack)) {
                    CustomStats stats = StatUtils.readStatsFromNBT(stack);
                    totalStats.add(stats);
                }
            }
        });

        // --- Main hand ---
        ItemStack mainHand = player.getMainHandItem();
        String mainType = ItemRarityUtils.getItemType(mainHand);
        if (!mainHand.isEmpty() && StatUtils.hasStats(mainHand)) {
            CustomStats stats = StatUtils.readStatsFromNBT(mainHand);
            switch (mainType) {
                case "sword", "bow", "crossbow", "shield", "tools" -> totalStats.add(stats);
            }
        }

        // --- Off hand ---
        ItemStack offHand = player.getOffhandItem();
        String offType = ItemRarityUtils.getItemType(offHand);
        if (!offHand.isEmpty() && StatUtils.hasStats(offHand)) {
            CustomStats stats = StatUtils.readStatsFromNBT(offHand);
            switch (offType) {
                case "sword", "shield", "tools" -> totalStats.add(stats);
                case "bow", "crossbow" -> {
                    if (!(mainType.equals("bow") || mainType.equals("crossbow"))) {
                        totalStats.add(stats);
                    }
                }
            }
        }

        // --- Apply totals to running variables ---
        totalStr += totalStats.getStr();
        totalDex += totalStats.getDex();
        totalCon += totalStats.getCon();
        totalPerc += totalStats.getPerc();
        totalFort += totalStats.getFort();
        totalInt += totalStats.getIntelligence();
        totalWis += totalStats.getWis();

        armorRating += totalStats.getArmor();
        toughnessRating += totalStats.getArmorToughness();
        totalMaxHealth += totalStats.getMaxHealth();
        totalKnockbackResist += totalStats.getKnockbackResist();
        totalDebuffResist += totalStats.getDebuffResist();
        totalMoveSpeed += totalStats.getMoveSpeed();
        totalSwimSpeed += totalStats.getSwimSpeed();
        totalLuck += totalStats.getLuck();
        totalStepHeight += totalStats.getStepHeight();
        totalJumpBoost += totalStats.getJumpBoost();
        totalMiningSpeed += totalStats.getMiningSpeed();

        generalDamageRating += totalStats.getDamage();
        generalCritChanceRating += totalStats.getCritChance();
        generalCritDamageRating += totalStats.getCritDamage();
        meleeDamageRating += totalStats.getMeleeDamage();
        attackSpeedRating += totalStats.getAttackSpeed();
        meleeCritChanceRating += totalStats.getMeleeCritChance();
        meleeCritDamageRating += totalStats.getMeleeCritDamage();
        projectileDamageRating += totalStats.getProjectileDamage();
        drawSpeedRating += totalStats.getDrawSpeed();
        projectileCritChanceRating += totalStats.getProjectileCritChance();
        projectileCritDamageRating += totalStats.getProjectileCritDamage();
        totalXpGain += totalStats.getXpGain();
        totalBlockReach += totalStats.getBlockReach();
        totalEntityReach += totalStats.getEntityReach();

        // --- Multipliers ---
        totalArmorMultiplier += totalStats.getArmorMultiplier();
        totalToughnessMultiplier += totalStats.getToughnessMultiplier();
        totalHealthMultiplier += totalStats.getHealthMultiplier();
        totalGeneralDamageMultiplier += totalStats.getDamageMultiplier();
        totalAttackSpeedMultiplier += totalStats.getAttackSpeedMultiplier();
        totalGeneralCritChanceMultiplier += totalStats.getCritChanceMultiplier();
        totalGeneralCritDamageMultiplier += totalStats.getCritDamageMultiplier();
        totalMeleeCritChanceMultiplier += totalStats.getMeleeCritChanceMultiplier();
        totalMeleeCritDamageMultiplier += totalStats.getMeleeCritDamageMultiplier();
        totalProjectileCritChanceMultiplier += totalStats.getProjectileCritChanceMultiplier();
        totalProjectileCritDamageMultiplier += totalStats.getProjectileCritDamageMultiplier();
        totalProjectileDamageMultiplier += totalStats.getProjectileDamageMultiplier();
        totalDrawSpeedMultiplier += totalStats.getDrawSpeedMultiplier();
        totalMeleeDamageMultiplier += totalStats.getMeleeDamageMultiplier();

        // --- Per Point Stats ---
        applyModifier(player, Attributes.ATTACK_DAMAGE, totalStr * 0.025, STR_PER_POINT_UUID);
        armorRating += totalCon * 0.15 + totalPerc * 0.1 + totalFort * 0.2;
        toughnessRating += totalFort * 0.15;
        totalDebuffResist += totalCon * 0.2;
        totalKnockbackResist += totalFort * 0.2;
        totalMoveSpeed += totalDex * 0.12;
        projectileDamageRating += totalDex * 0.15;
        drawSpeedRating += totalDex * 0.125;
        attackSpeedRating += totalDex * 0.125;
        generalCritChanceRating += totalDex * 0.2 + totalPerc * 0.175;
        generalDamageRating += totalStr * 0.35 + totalCon * 0.125;

        totalXpGain += totalWis * 0.25;
        totalBlockReach += totalWis * 0.05;
        totalMiningSpeed += totalWis * 0.25;

        // --- Milestones ---
        // --- Strength milestone bonuses ---
        if (totalStr >= 30) generalDamageRating += 7.5;
        if (totalStr >= 40) armorRating += 5;
        if (totalStr >= 50) attackSpeedRating += 8;
        if (totalStr >= 60) {
            totalGeneralCritDamageMultiplier += 15;
        }
        if (totalStr >= 70) {
            totalGeneralDamageMultiplier += 25;
        }
        if (totalStr >= 80) { applyModifier(player, Attributes.ATTACK_DAMAGE, 2.0, ATTACK_DAMAGE_UUID);
        } else {
            removeModifier(player, Attributes.ATTACK_DAMAGE, ATTACK_DAMAGE_UUID);
        }

        // --- Fortitude milestone bonuses ---
        if (totalFort >= 30) armorRating += 4;
        if (totalFort >= 40) totalArmorMultiplier += 8;
        if (totalFort >= 50) totalKnockbackResist += 10.0;
        if (totalFort >= 60) totalToughnessMultiplier += 10;
        if (totalFort >= 70) {
            totalDebuffResist += 15.0;
            totalKnockbackResist += 10;
        }
        if (totalFort >= 80) {
            toughnessRating += 9.0;
            armorRating += 9.0;
        }

        // --- Dexterity milestone bonuses ---
        if (totalDex >= 30) generalCritChanceRating += 6;
        if (totalDex >= 40) generalCritDamageRating += 6;
        if (totalDex >= 50) drawSpeedRating += 8;
        if (totalDex >= 60) totalMoveSpeed += 8;
        if (totalDex >= 70) {
            totalAttackSpeedMultiplier += 8;
            totalProjectileDamageMultiplier += 15;
        }
        if (totalDex >= 80) {
            projectileDamageRating += 9;
            drawSpeedRating += 9;
            generalCritChanceRating += 9;
            generalCritDamageRating += 9;
        }

        // --- Constitution milestone bonuses ---
        if (totalCon >= 30) armorRating += 5.0;
        if (totalCon >= 40) generalDamageRating += 5;
        if (totalCon >= 50) {
            totalDebuffResist += 12;
            toughnessRating += 6;
        }
        if (totalCon >= 60) {
            totalToughnessMultiplier += 12.5;
        }
        if (totalCon >= 70) {
            totalArmorMultiplier += 12.5;
        }
        if (totalCon >= 80) generalDamageRating += 10;

        // --- Perception milestone bonuses ---
        if (totalPerc >= 30) toughnessRating += 3.0;
        if (totalPerc >= 40) totalGeneralDamageMultiplier += 7.5;
        if (totalPerc >= 50) generalCritDamageRating += 10.0;
        if (totalPerc >= 60) {
            totalEntityReach += 1;
        }
        if (totalPerc >= 70) totalArmorMultiplier += 8;
        if (totalPerc >= 80) {
            totalGeneralCritDamageMultiplier += 25;
        }

        // --- Wisdom milestone bonuses ---
        if (totalWis >= 10) totalMiningSpeed += 10.0;
        if (totalWis >= 20) totalXpGain += 10;
        if (totalWis >= 30) totalBlockReach += 0.5;
        if (totalWis >= 40) {
            totalMiningSpeed += 25;
            totalXpGain += 25;
        }

        if (player.hasEffect(ModEffects.SOLARA.get())) {
            long time = player.level().getDayTime() % 24000;
            double meleeBonus, armorBonus;

            if (time <= 6000) {
                double factor = (double) time / 6000.0;
                meleeBonus = 25.0 * factor;
                armorBonus = 10.0 * factor;
            } else if (time <= 12000) {
                double factor = 1.0 - (double)(time - 6000) / 6000.0;
                meleeBonus = 25.0 * factor;
                armorBonus = 10.0 * factor;
            } else if (time <= 18000) {
                double factor = (double)(time - 12000) / 6000.0;
                meleeBonus = -15.0 * factor;
                armorBonus = -15.0 * factor;
            } else {
                double factor = 1.0 - (double)(time - 18000) / 6000.0;
                meleeBonus = -15.0 * factor;
                armorBonus = -15.0 * factor;
            }

            generalDamageRating += meleeBonus;
            armorRating += armorBonus;
        }


        meleeDamageRating += generalDamageRating;
        meleeCritChanceRating += generalCritChanceRating;
        meleeCritDamageRating += generalCritDamageRating;

        projectileDamageRating += generalDamageRating;
        projectileCritChanceRating += generalCritChanceRating;
        projectileCritDamageRating += generalCritDamageRating;

        totalMeleeDamageMultiplier += totalGeneralDamageMultiplier;
        totalMeleeCritChanceMultiplier += totalGeneralCritChanceMultiplier;
        totalMeleeCritDamageMultiplier += totalGeneralCritDamageMultiplier;
        totalProjectileDamageMultiplier += totalGeneralDamageMultiplier;
        totalProjectileCritChanceMultiplier += totalGeneralCritChanceMultiplier;
        totalProjectileCritDamageMultiplier += totalGeneralCritDamageMultiplier;

        data.putDouble("generalDamageRating", generalDamageRating);
        data.putDouble("generalDamageRatingB", generalDamageRating);

        data.putDouble("generalCritChanceRating", generalCritChanceRating);
        data.putDouble("generalCritDamageRating", generalCritDamageRating);

        data.putDouble("meleeDamageRating", meleeDamageRating);
        data.putDouble("meleeDamageRatingB", meleeDamageRating);

        data.putDouble("meleeCritChanceRating", meleeCritChanceRating);
        data.putDouble("meleeCritDamageRating", meleeCritDamageRating);

        data.putDouble("projectileDamageRating", projectileDamageRating);
        data.putDouble("projectileDamageRatingB", projectileDamageRating);

        data.putDouble("projectileCritChanceRating", projectileCritChanceRating);
        data.putDouble("projectileCritDamageRating", projectileCritDamageRating);

        data.putDouble("attackSpeedRating", attackSpeedRating);
        data.putDouble("drawSpeedRating", drawSpeedRating);
        data.putDouble("drawSpeedRatingB", drawSpeedRating);

        data.putDouble("totalGeneralDamageMultiplier", totalGeneralDamageMultiplier);
        data.putDouble("totalGeneralDamageMultiplierB", totalGeneralDamageMultiplier);

        data.putDouble("totalGeneralCritChanceMultiplier", totalGeneralCritChanceMultiplier);
        data.putDouble("totalGeneralCritDamageMultiplier", totalGeneralCritDamageMultiplier);

        data.putDouble("totalMeleeDamageMultiplier", totalMeleeDamageMultiplier);
        data.putDouble("totalMeleeDamageMultiplierB", totalMeleeDamageMultiplier);

        data.putDouble("totalMeleeCritChanceMultiplier", totalMeleeCritChanceMultiplier);
        data.putDouble("totalMeleeCritDamageMultiplier", totalMeleeCritDamageMultiplier);

        data.putDouble("totalProjectileDamageMultiplier", totalProjectileDamageMultiplier);
        data.putDouble("totalProjectileDamageMultiplierB", totalProjectileDamageMultiplier);

        data.putDouble("totalProjectileCritChanceMultiplier", totalProjectileCritChanceMultiplier);
        data.putDouble("totalProjectileCritDamageMultiplier", totalProjectileCritDamageMultiplier);

        data.putDouble("totalAttackSpeedMultiplier", totalAttackSpeedMultiplier);
        data.putDouble("totalDrawSpeedMultiplier", totalDrawSpeedMultiplier);

        data.putDouble("totalArmorMultiplier", totalArmorMultiplier);
        data.putDouble("totalToughnessMultiplier", totalToughnessMultiplier);
        data.putDouble("totalHealthMultiplier", totalHealthMultiplier);

        data.putDouble("baseMoveSpeed", totalMoveSpeed);
        data.putDouble("totalMoveSpeed", totalMoveSpeed);
        data.putDouble("totalKnockbackResist", totalKnockbackResist);
        data.putDouble("totalSwimSpeed", totalSwimSpeed);
        data.putDouble("totalDebuffResist", totalDebuffResist);

        data.putDouble("totalXpGain", totalXpGain);
        data.putDouble("totalJumpBoost", totalJumpBoost);
        data.putDouble("totalMiningSpeed", totalMiningSpeed);

        data.putInt("totalStr", totalStr);
        data.putInt("totalDex", totalDex);
        data.putInt("totalFort", totalFort);
        data.putInt("totalPerc", totalPerc);
        data.putInt("totalCon", totalCon);
        data.putInt("totalInt", totalInt);
        data.putInt("totalWis", totalWis);

        player.getAttribute(Attributes.ARMOR).removeModifier(ARMOR_UUID);
        double baseArmor = player.getAttribute(Attributes.ARMOR).getValue();
        double armorToAdd = (baseArmor + armorRating) * (1 + totalArmorMultiplier / 100);
        applyModifier(player, Attributes.ARMOR, armorToAdd - baseArmor, ARMOR_UUID);

        player.getAttribute(Attributes.ARMOR_TOUGHNESS).removeModifier(ARMOR_TOUGHNESS_UUID);
        double baseToughness = player.getAttribute(Attributes.ARMOR_TOUGHNESS).getValue();
        double toughnessToAdd = (baseToughness + toughnessRating) * (1 + totalToughnessMultiplier / 100);
        applyModifier(player, Attributes.ARMOR_TOUGHNESS, toughnessToAdd - baseToughness, ARMOR_TOUGHNESS_UUID);

        double baseHealth = player.getAttribute(Attributes.MAX_HEALTH).getBaseValue();
        double healthToAdd = (baseHealth + totalMaxHealth) * (1 + totalHealthMultiplier / 100) - baseHealth;
        applyModifier(player, Attributes.MAX_HEALTH, healthToAdd, MAX_HEALTH_UUID);

        applyModifier(player, ForgeMod.BLOCK_REACH.get(), totalBlockReach, BLOCK_REACH_UUID);
        applyModifier(player, ForgeMod.ENTITY_REACH.get(), totalEntityReach, ENTITY_REACH_UUID);
        applyModifier(player, Attributes.LUCK, totalLuck, LUCK_UUID);
        applyModifier(player, ForgeMod.STEP_HEIGHT_ADDITION.get(), totalStepHeight, STEP_HEIGHT_UUID);
        applyModifier(player, Attributes.KNOCKBACK_RESISTANCE, totalKnockbackResist / 100, KNOCKBACK_RESIST_UUID);

        applyPercentModifier(player, Attributes.MOVEMENT_SPEED, totalMoveSpeed / 100.0, MOVE_SPEED_UUID);
        applyPercentModifier(player, ForgeMod.SWIM_SPEED.get(), totalSwimSpeed / 100.0, SWIM_SPEED_UUID);

        if (player.getHealth() > player.getMaxHealth()) {
            player.setHealth(player.getMaxHealth());
        }
    }

    public void applyBlessings(Player player) {
        CompoundTag data = player.getPersistentData();

        boolean generalChanged = false;
        boolean meleeChanged = false;
        boolean projectileChanged = false;
        boolean drawChanged = false;
        boolean moveChanged = false;

        double generalDamage = data.getDouble("generalDamageRatingB");
        double meleeDamage = data.getDouble("meleeDamageRatingB");
        double projectileDamage = data.getDouble("projectileDamageRatingB");
        double draw = data.getDouble("drawSpeedRatingB");
        double move = data.getDouble("baseMoveSpeed");

        double generalDamageMultiplier = data.getDouble("totalGeneralDamageMultiplierB");
        double meleeDamageMultiplier = data.getDouble("totalMeleeDamageMultiplierB");
        double projectileDamageMultiplier = data.getDouble("totalProjectileDamageMultiplierB");

        if (player.hasEffect(ModEffects.FRENZY.get())) {
            int stacks = player.getEffect(ModEffects.FRENZY.get()).getAmplifier() + 1;

            if (!player.hasEffect(ModEffects.RECKONING_COOLDOWN.get())) {
                applyModifier(player, Attributes.ATTACK_DAMAGE, 1.25 * stacks, FRENZY_ATTACK_DAMAGE_UUID);
                meleeDamageMultiplier += 7.5 * stacks;
            }

            meleeChanged = true;
        } else {
            removeModifier(player, Attributes.ATTACK_DAMAGE, FRENZY_ATTACK_DAMAGE_UUID);
        }

        if (player.hasEffect(ModEffects.HAWKEYE.get())) {
            int stacks = player.getEffect(ModEffects.HAWKEYE.get()).getAmplifier() + 1;
            int totalDex = data.getInt("totalDex");
            int totalPerc = data.getInt("totalPerc");
            double scale = 1 + (totalDex * 0.75 + totalPerc * 0.4) / 100;

            draw += 9 * scale * stacks;
            projectileDamage += 5 * scale * stacks;

            drawChanged = true;
            projectileChanged = true;
        }

        ItemStack mainHand = player.getMainHandItem();
        String mainType = ItemRarityUtils.getItemType(mainHand);
        ItemStack offHand = player.getOffhandItem();
        String offType = ItemRarityUtils.getItemType(offHand);

        if (player.hasEffect(ModEffects.ADRENALINE_INJECTION_UP.get())) {

            boolean mainIsCrossbow = "crossbow".equals(mainType);
            boolean offIsCrossbow = "crossbow".equals(offType);
            boolean mainIsBow = "bow".equals(mainType);

            if ((mainIsCrossbow || (offIsCrossbow && !mainIsBow))) {
                draw *= 2;
                drawChanged = true;
            }
        }

        if (player.hasEffect(ModEffects.INTO_THE_FRAY.get())) {
            int stacks = player.getEffect(ModEffects.INTO_THE_FRAY.get()).getAmplifier() + 1;
            move += 8 * stacks;
            moveChanged = true;
        }

        if (player.hasEffect(ModEffects.QUANTUM_LEAP_ACTIVE.get())) {
            generalDamage += 15;
            meleeDamage += 15;
            projectileDamage += 15;
            move += 20;
            generalChanged = true;
            meleeChanged = true;
            projectileChanged = true;
            moveChanged = true;

            if (player.hasEffect(ModEffects.ENHANCED_QUANTUM_LEAP.get())) {
                generalDamage += 7.5;
                move += 10;
            }
        }

        if (generalChanged) {
            data.putDouble("generalDamageRating", generalDamage);
            data.putDouble("totalGeneralDamageMultiplier", generalDamageMultiplier);

            double totalGeneralDamage = generalDamage * (1 + generalDamageMultiplier / 100);
            data.putDouble("totalGeneralDamage", totalGeneralDamage);
            data.putDouble("generalDamageIncrease", 500 * totalGeneralDamage / (500 + totalGeneralDamage));
        }

        if (meleeChanged) {
            data.putDouble("meleeDamageRating", meleeDamage);
            data.putDouble("totalMeleeDamageMultiplier", meleeDamageMultiplier);

            double totalMeleeDamage = meleeDamage * (1 + meleeDamageMultiplier / 100);
            data.putDouble("totalMeleeDamage", totalMeleeDamage);
            data.putDouble("meleeDamageIncrease", 500 * totalMeleeDamage / (500 + totalMeleeDamage));
        }

        if (projectileChanged) {
            data.putDouble("projectileDamageRating", projectileDamage);
            data.putDouble("totalProjectileDamageMultiplier", projectileDamageMultiplier);

            double totalProjectileDamage = projectileDamage * (1 + projectileDamageMultiplier / 100);
            data.putDouble("totalProjectileDamage", totalProjectileDamage);
            data.putDouble("projectileDamageIncrease", 500 * totalProjectileDamage / (500 + totalProjectileDamage));
        }

        if (drawChanged) {
            data.putDouble("drawSpeedRating", draw);

            double totalDrawSpeed = draw * (1 + data.getDouble("totalDrawSpeedMultiplier") / 100);
            data.putDouble("totalDrawSpeed", totalDrawSpeed);

            double drawSpeedIncrease = 500 * totalDrawSpeed / (500 + totalDrawSpeed);
            data.putDouble("drawSpeedIncrease", drawSpeedIncrease);

            LocalPlayer client = Minecraft.getInstance().player;
            if (client != null) {
                client.getPersistentData().putDouble("drawSpeedIncrease", drawSpeedIncrease);
            }

            applyCrossbowTag(player);
        }

        if (moveChanged) {
            applyPercentModifier(player, Attributes.MOVEMENT_SPEED, move / 100.0, BLESSING_MOVE_SPEED_UUID);
        }
    }

    public void applyITF(Player player) {

        if (player.hasEffect(ModEffects.INTO_THE_FRAY.get())) {
            int stacks = player.getEffect(ModEffects.INTO_THE_FRAY.get()).getAmplifier() + 1;
            double move = player.getPersistentData().getDouble("baseMoveSpeed");
            move += 8 * stacks;
            applyPercentModifier(player, Attributes.MOVEMENT_SPEED, move / 100.0, BLESSING_MOVE_SPEED_UUID);
        }
    }

    public void removeITF(Player player) {
        removeModifier(player, Attributes.MOVEMENT_SPEED, BLESSING_MOVE_SPEED_UUID);
    }

    public void multiplyStatsAndAddDiminishingReturns(Player player) {

        CompoundTag data = player.getPersistentData();

        double totalGeneralDamage = data.getDouble("generalDamageRating")
                * (1 + data.getDouble("totalGeneralDamageMultiplier") / 100);

        double totalGeneralCritChance = data.getDouble("generalCritChanceRating")
                * (1 + data.getDouble("totalGeneralCritChanceMultiplier") / 100);

        double totalGeneralCritDamage = data.getDouble("generalCritDamageRating")
                * (1 + data.getDouble("totalGeneralCritDamageMultiplier") / 100);


        double totalMeleeDamage = data.getDouble("meleeDamageRating")
                * (1 + data.getDouble("totalMeleeDamageMultiplier") / 100);

        double totalMeleeCritChance = data.getDouble("meleeCritChanceRating")
                * (1 + data.getDouble("totalMeleeCritChanceMultiplier") / 100);

        double totalMeleeCritDamage = data.getDouble("meleeCritDamageRating")
                * (1 + data.getDouble("totalMeleeCritDamageMultiplier") / 100);


        double totalProjectileDamage = data.getDouble("projectileDamageRating")
                * (1 + data.getDouble("totalProjectileDamageMultiplier") / 100);

        double totalDrawSpeed = data.getDouble("drawSpeedRating")
                * (1 + data.getDouble("totalDrawSpeedMultiplier") / 100);

        double totalProjectileCritChance = data.getDouble("projectileCritChanceRating")
                * (1 + data.getDouble("totalProjectileCritChanceMultiplier") / 100);

        double totalProjectileCritDamage = data.getDouble("projectileCritDamageRating")
                * (1 + data.getDouble("totalProjectileCritDamageMultiplier") / 100);


        double totalAttackSpeed = data.getDouble("attackSpeedRating")
                * (1 + data.getDouble("totalAttackSpeedMultiplier") / 100);


        data.putDouble("totalGeneralDamage", totalGeneralDamage);
        data.putDouble("totalGeneralCritChance", totalGeneralCritChance);
        data.putDouble("totalGeneralCritDamage", totalGeneralCritDamage);

        data.putDouble("totalMeleeDamage", totalMeleeDamage);
        data.putDouble("totalMeleeCritChance", totalMeleeCritChance);
        data.putDouble("totalMeleeCritDamage", totalMeleeCritDamage);

        data.putDouble("totalProjectileDamage", totalProjectileDamage);
        data.putDouble("totalDrawSpeed", totalDrawSpeed);

        data.putDouble("totalProjectileCritChance", totalProjectileCritChance);
        data.putDouble("totalProjectileCritDamage", totalProjectileCritDamage);

        data.putDouble("totalAttackSpeed", totalAttackSpeed);

        // --- Apply Diminishing Returns ---
        double generalDamageIncrease =
                500 * data.getDouble("totalGeneralDamage") /
                        (500 + data.getDouble("totalGeneralDamage"));

        double generalCritChanceIncrease =
                500 * data.getDouble("totalGeneralCritChance") /
                        (500 + data.getDouble("totalGeneralCritChance"));

        double generalCritDamageIncrease =
                500 * data.getDouble("totalGeneralCritDamage") /
                        (500 + data.getDouble("totalGeneralCritDamage"));


        double meleeDamageIncrease =
                500 * data.getDouble("totalMeleeDamage") /
                        (500 + data.getDouble("totalMeleeDamage"));

        double meleeCritChanceIncrease =
                500 * data.getDouble("totalMeleeCritChance") /
                        (500 + data.getDouble("totalMeleeCritChance"));

        double meleeCritDamageIncrease =
                500 * data.getDouble("totalMeleeCritDamage") /
                        (500 + data.getDouble("totalMeleeCritDamage"));


        double projectileDamageIncrease =
                500 * data.getDouble("totalProjectileDamage") /
                        (500 + data.getDouble("totalProjectileDamage"));

        double drawSpeedIncrease =
                500 * data.getDouble("totalDrawSpeed") /
                        (500 + data.getDouble("totalDrawSpeed"));

        double projectileCritChanceIncrease =
                500 * data.getDouble("totalProjectileCritChance") /
                        (500 + data.getDouble("totalProjectileCritChance"));

        double projectileCritDamageIncrease =
                500 * data.getDouble("totalProjectileCritDamage") /
                        (500 + data.getDouble("totalProjectileCritDamage"));


        double attackSpeedIncrease =
                500 * data.getDouble("totalAttackSpeed") /
                        (500 + data.getDouble("totalAttackSpeed"));

        data.putDouble("generalDamageIncrease", generalDamageIncrease);
        data.putDouble("generalCritChanceIncrease", generalCritChanceIncrease);
        data.putDouble("generalCritDamageIncrease", generalCritDamageIncrease);

        data.putDouble("meleeDamageIncrease", meleeDamageIncrease);
        data.putDouble("meleeCritChanceIncrease", meleeCritChanceIncrease);
        data.putDouble("meleeCritDamageIncrease", meleeCritDamageIncrease);

        data.putDouble("projectileDamageIncrease", projectileDamageIncrease);
        data.putDouble("drawSpeedIncrease", drawSpeedIncrease);

        LocalPlayer client = Minecraft.getInstance().player;
        if (client != null) {
            client.getPersistentData().putDouble("drawSpeedIncrease", drawSpeedIncrease);
        }

        data.putDouble("projectileCritChanceIncrease", projectileCritChanceIncrease);
        data.putDouble("projectileCritDamageIncrease", projectileCritDamageIncrease);

        data.putDouble("attackSpeedIncrease", attackSpeedIncrease);
        data.putDouble("drawSpeedIncrease", drawSpeedIncrease);

        applyPercentModifier(player, Attributes.ATTACK_SPEED, attackSpeedIncrease / 100.0, ATTACK_SPEED_UUID);
    }

    public void applyCrossbowTag(Player player) {
        // For draw speed mixin, put draw speed increase data to item stack

        ItemStack mainHand = player.getMainHandItem();
        String mainType = ItemRarityUtils.getItemType(mainHand);

        ItemStack offHand = player.getOffhandItem();
        String offType = ItemRarityUtils.getItemType(player.getOffhandItem());

        if ("crossbow".equals(mainType)) {
            mainHand.getOrCreateTag().putDouble("drawSpeedIncrease", player.getPersistentData().getDouble("drawSpeedIncrease"));
        } else if ("crossbow".equals(offType) && !"bow".equals(mainType)) {
            offHand.getOrCreateTag().putDouble("drawSpeedIncrease", player.getPersistentData().getDouble("drawSpeedIncrease"));
        }
    }

    private void applyPercentModifier(Player player, Attribute attribute, double percent, UUID uuid) {
        AttributeModifier modifier = new AttributeModifier(uuid, attribute.getDescriptionId() + "_percent", percent, AttributeModifier.Operation.MULTIPLY_TOTAL);
        var attr = player.getAttribute(attribute);
        if (attr != null) {
            attr.removeModifier(uuid);
            attr.addTransientModifier(modifier);
        }
    }

    private void applyModifier(LivingEntity entity, Attribute attribute, double value, UUID uuid) {
        if (entity.getAttribute(attribute) == null) return;

        AttributeModifier oldMod = entity.getAttribute(attribute).getModifier(uuid);
        if (oldMod != null) {
            entity.getAttribute(attribute).removeModifier(uuid);
        }


        if (value != 0) {
            AttributeModifier modifier = new AttributeModifier(uuid, "Custom Stat", value, AttributeModifier.Operation.ADDITION);
            entity.getAttribute(attribute).addPermanentModifier(modifier);
        }
    }

    @SubscribeEvent
    public void onCrit(CriticalHitEvent event) {
        Player player = event.getEntity();

        if (event.isVanillaCritical()) {
            float critBonus = (float) (1.5 + player.getPersistentData().getDouble("meleeCritDamageIncrease") / 100.0);
            event.setDamageModifier(critBonus);
            player.getPersistentData().putBoolean("isVanillaCrit", true);
            if (!player.hasEffect(ModEffects.BRONZEWOOD_COOLDOWN.get())) player.getPersistentData().putBoolean("procChainLightning", true);
        } else {
            if (player.getRandom().nextDouble() < (player.getPersistentData().getDouble("meleeCritChanceIncrease") + 10) / 100.0) {
                event.setResult(Event.Result.ALLOW);
                float critBonus = (float) (1.5 + player.getPersistentData().getDouble("meleeCritDamageIncrease") / 100.0);
                event.setDamageModifier(critBonus);
                player.getPersistentData().putBoolean("isCustomCrit", true);
                if (!player.hasEffect(ModEffects.BRONZEWOOD_COOLDOWN.get())) player.getPersistentData().putBoolean("procChainLightning", true);
            }
        }
    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        LivingEntity entity = event.getEntity();

        CompoundTag data = player.getPersistentData();

        int hawkeyeStacks = data.getInt("hawkeye");
        int frenzyStacks = data.getInt("frenzy");
        int berserkStacks = data.getInt("berserk");

        double finalDamageAmount = event.getAmount();
        double incomingDamageMultiplier = 1.0;

        boolean isProjectile = event.getSource().getDirectEntity() instanceof Projectile;
        InteractionHand hand = player.swingingArm;
        String mainType = ItemRarityUtils.getItemType(player.getMainHandItem());
        String offType = ItemRarityUtils.getItemType(player.getOffhandItem());


        boolean isMelee = (hand == InteractionHand.MAIN_HAND && ((mainType.equals("sword")) || mainType.isEmpty())) ||
                (hand == InteractionHand.OFF_HAND && (offType.equals("sword") || offType.isEmpty()));

        if (event.getSource() instanceof CustomMeleeDamageNoProcs) {
            if (player.getRandom().nextDouble() < (data.getDouble("meleeCritChanceIncrease") + 10) / 100.0) {
                finalDamageAmount *= 1.5 + data.getDouble("meleeCritDamageIncrease") / 100.0;
            }
            finalDamageAmount *= 1 + data.getDouble("meleeDamageIncrease") / 100.0;
        }
        else if (isMelee || event.getSource() instanceof CustomMeleeDamage) {

            double sharpnessBonus = 0;
            if (isMelee) sharpnessBonus = getSharpnessBonus(player, hand);

            if ((player.hasEffect(ModEffects.BRONZEWOOD_READY.get())
                    && player.getPersistentData().getBoolean("bronzewoods_curse_applied"))) {
                finalDamageAmount += 3;
            }

            if ((player.hasEffect(ModEffects.BERSERK_READY.get())
                    && player.getPersistentData().getBoolean("berserk_applied"))
                    && !player.hasEffect(ModEffects.BRONZEWOOD_COOLDOWN.get())) {
                finalDamageAmount *= 1 + data.getDouble("meleeDamageIncrease") * 0.6 / 100.0;
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ZOMBIE_ATTACK_WOODEN_DOOR, SoundSource.PLAYERS, 0.3F, 1.0F);
                player.removeEffect(ModEffects.BERSERK_READY.get());
                berserkStacks = 0;
                data.putInt("berserk", berserkStacks);
            } else if (data.getInt("berserk") < 1
                    && player.getPersistentData().getBoolean("berserk_applied")
                    && !player.hasEffect(ModEffects.BRONZEWOOD_COOLDOWN.get())) {

                berserkStacks += 1;
                data.putInt("berserk", berserkStacks);
                player.removeEffect(ModEffects.BERSERK.get());
                player.addEffect(new MobEffectInstance(ModEffects.BERSERK.get(), 20 * 4, Math.max(0, berserkStacks - 1), false, false));
            } else if (data.getInt("berserk") >= 1
                    && player.getPersistentData().getBoolean("berserk_applied")
                    && !player.hasEffect(ModEffects.BRONZEWOOD_COOLDOWN.get())) {

                berserkStacks = 0;
                data.putInt("berserk", berserkStacks);
                player.removeEffect(ModEffects.BERSERK.get());
                player.addEffect(new MobEffectInstance(ModEffects.BERSERK_READY.get(), 20 * 6, 0, false, false));
            }

            finalDamageAmount -= sharpnessBonus;

            if (player.getPersistentData().getBoolean("isVanillaCrit") ||
                    player.getPersistentData().getBoolean("isCustomCrit")) {
                if (data.getBoolean("hawkeye_eligible")) {
                    if (data.getInt("hawkeye") < 4 && (!(player.hasEffect(ModEffects.INTIMIDATING_PRESENCE_COOLDOWN.get()) || player.hasEffect(ModEffects.DARING_SHOUT_COOLDOWN.get()))))
                    { hawkeyeStacks += 1; }
                    data.putInt("hawkeye", hawkeyeStacks);
                    player.removeEffect(ModEffects.HAWKEYE.get());
                    player.addEffect(new MobEffectInstance(ModEffects.HAWKEYE.get(), 20 * 8, hawkeyeStacks - 1, false, false));
                }
                player.getPersistentData().remove("isVanillaCrit");
                player.getPersistentData().remove("isCustomCrit");

                finalDamageAmount += sharpnessBonus * (1.5 + data.getDouble("meleeCritDamageIncrease") / 100.0);
            }

            if (!isMelee) {
                if (player.getRandom().nextDouble() < (data.getDouble("meleeCritChanceIncrease") + 10) / 100.0) {
                    finalDamageAmount *= 1.5 + data.getDouble("meleeCritDamageIncrease") / 100.0;

                    // Play crit sound
                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                            SoundEvents.PLAYER_ATTACK_CRIT, SoundSource.PLAYERS, 1.0F, 1.0F);
                }
            }

        } else if (isProjectile) {
            Projectile proj = (Projectile) event.getSource().getDirectEntity();

            if (!proj.getPersistentData().contains("ScaledDamage")) {
                double boosted = finalDamageAmount * (1 + (data.getDouble("projectileDamageIncrease")) / 100.0);
                proj.getPersistentData().putDouble("ScaledDamage", boosted);
            }

            finalDamageAmount = proj.getPersistentData().getDouble("ScaledDamage");

            if (data.getBoolean("Clairvoyance")) {
                finalDamageAmount *= Math.pow(((1 + (data.getDouble("projectileDamageIncrease")) / 100.0)), 4);
                data.putBoolean("Clairvoyance", false);
                data.remove("Clairvoyance_sound_played");
                player.removeEffect(ModEffects.CLAIRVOYANCE_READY.get());
                player.addEffect(new MobEffectInstance(ModEffects.CLAIRVOYANCE_COOLDOWN.get(), 20*20, 0, false, false));
            }

            if (player.getRandom().nextDouble() < (data.getDouble("projectileCritChanceIncrease") + 10) / 100.0) {
                finalDamageAmount *= 1.5 + data.getDouble("projectileCritDamageIncrease") / 100.0;

                // Play crit sound
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.PLAYER_ATTACK_CRIT, SoundSource.PLAYERS, 1.0F, 1.0F);
            }

            hawkeyeStacks = 0;
            data.putInt("hawkeye", 0);
            player.removeEffect(ModEffects.HAWKEYE.get());
        } else {
            finalDamageAmount *= 1 + data.getDouble("generalDamageIncrease") / 100.0;
            // Non-melee & non-projectile hits
            if (player.getPersistentData().getBoolean("isVanillaCrit")) {
                finalDamageAmount /= 1.5;
                finalDamageAmount *= 1.5 + data.getDouble("generalCritDamageIncrease") / 100.0;
                player.getPersistentData().remove("isVanillaCrit");
            } else if (player.getRandom().nextDouble() < (data.getDouble("generalCritChanceIncrease") + 10) / 100.0) {
                finalDamageAmount *= 1.5 + data.getDouble("generalCritDamageIncrease") / 100.0;

                player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.PLAYER_ATTACK_CRIT, SoundSource.PLAYERS, 1.0F, 1.0F);
            }
        }

        if (entity.hasEffect(ModEffects.BRONZEWOOD_CURSE.get())) {
            incomingDamageMultiplier += 0.1;
        }

        if (entity.hasEffect(ModEffects.BLINDED_BY_HATRED.get())) {
            incomingDamageMultiplier += 0.06;
        }

        MobEffectInstance intimidated = entity.getEffect(ModEffects.INTIMIDATED.get());
        if (intimidated != null) {
            int stacks = intimidated.getAmplifier() + 1;
            incomingDamageMultiplier += stacks / 100.0;
        }

        MobEffectInstance weakened = entity.getEffect(ModEffects.EXPLOIT_WEAKNESS_DEBUFF.get());
        if (weakened != null) {
            int stacks = weakened.getAmplifier() + 1;
            incomingDamageMultiplier += stacks / 100.0;
        }

        event.setAmount((float) (finalDamageAmount * incomingDamageMultiplier));
        data.putInt("hawkeye", hawkeyeStacks);

        if (data.getBoolean("frenzy_eligible") && !(event.getSource() instanceof CustomMeleeDamageNoProcs)) {
             if (data.getInt("frenzy") < 4) { frenzyStacks += 1; }
            data.putInt("frenzy", frenzyStacks);
            player.removeEffect(ModEffects.FRENZY.get());
            player.addEffect(new MobEffectInstance(ModEffects.FRENZY.get(), 120, frenzyStacks - 1, false, false));
        }
    }

    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        double miningSpeedBonus = event.getEntity().getPersistentData().getDouble("totalMiningSpeed"); // your % bonus
        if (miningSpeedBonus != 0) {
            float original = event.getNewSpeed();
            float boosted = (float) (original * (1.0 + (miningSpeedBonus / 100.0)));
            event.setNewSpeed(boosted);
        }
    }

    @SubscribeEvent
    public static void onLivingJump(LivingEvent.LivingJumpEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        double jumpBonus = player.getPersistentData().getDouble("totalJumpBoost"); // % bonus
        if (jumpBonus != 0) {
            double baseJumpHeight = 1.252;
            double gravity = 0.08;

            double newHeight = baseJumpHeight * (1.0 + (jumpBonus / 100.0));

            double requiredMotionY = Math.sqrt(2 * gravity * newHeight);

            player.setDeltaMovement(player.getDeltaMovement().x, requiredMotionY, player.getDeltaMovement().z);
        }
    }

    @SubscribeEvent
    public void onLivingFall(LivingFallEvent event) {
        if (event.getEntity() instanceof Player player) {

            float jumpBoost = (float) player.getPersistentData().getDouble("totalJumpBoost"); // % bonus
            float fallThreshold = 3.0f * (1.0f + jumpBoost / 100.0f);
            float fallDistance = event.getDistance();

            if (fallDistance <= fallThreshold) {
                event.setCanceled(true);
                return;
            }

            float damageMultiplier = 100.0f / (100.0f + jumpBoost);
            damageMultiplier = Math.max(0.0f, Math.min(1.0f, damageMultiplier));

            event.setDamageMultiplier(damageMultiplier);
        }
    }

    @SubscribeEvent
    public static void onEffectExpired(MobEffectEvent.Expired event) {
        if (event.getEntity() instanceof Player player) {

            CompoundTag data = player.getPersistentData();

            if (event.getEffectInstance().getEffect() == ModEffects.FRENZY.get()) {
                data.putInt("frenzy", 0); // Reset Frenzy stacks
                new AttributeApplier().applyBlessings(player);
            }
            else if (event.getEffectInstance().getEffect() == ModEffects.HAWKEYE.get()) {
                data.putInt("hawkeye", 0);
                new AttributeApplier().applyBlessings(player);
            }
            else if (event.getEffectInstance().getEffect() == ModEffects.INTO_THE_FRAY.get()) {
                new AttributeApplier().applyBlessings(player);
                removeModifier(player, Attributes.MOVEMENT_SPEED, BLESSING_MOVE_SPEED_UUID);
            }
            else if (event.getEffectInstance().getEffect() == ModEffects.BERSERK.get() && !player.hasEffect(ModEffects.BERSERK_READY.get())) {
                data.putInt("berserk", 0);
            }
            else if (event.getEffectInstance().getEffect() == ModEffects.BERSERK_READY.get()) {
                data.putInt("berserk", 0);
                if (!(player.hasEffect(ModEffects.BERSERK_TIMER.get())) && data.getBoolean("berserk_applied")) player.addEffect(new MobEffectInstance(ModEffects.BERSERK_TIMER.get(), 20*15, 0, false, false));
            }
            else if (event.getEffectInstance().getEffect() == ModEffects.BERSERK_TIMER.get() && data.getBoolean("berserk_applied")) {
                if (player.hasEffect(ModEffects.BRONZEWOOD_COOLDOWN.get())) {
                    player.addEffect(new MobEffectInstance(ModEffects.BERSERK_TIMER.get(), 20*15, 0, false, false));
                } else {
                    player.addEffect(new MobEffectInstance(ModEffects.BERSERK_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false));
                    data.putInt("berserk", 0);
                }
            }
            else if (event.getEffectInstance().getEffect() == ModEffects.BRONZEWOOD_COOLDOWN.get() && data.getBoolean("bronzewoods_curse_applied")) {
                player.addEffect(new MobEffectInstance(ModEffects.BRONZEWOOD_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false));
            }
            else if (event.getEffectInstance().getEffect() == ModEffects.DEATH_FROM_ABOVE_COOLDOWN.get() && data.getBoolean("death_from_above_eligible")) {
                player.addEffect(new MobEffectInstance(ModEffects.DEATH_FROM_ABOVE.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false));
            }
            else if (event.getEffectInstance().getEffect() == ModEffects.BASTION_COOLDOWN.get() && data.getBoolean("bastion_applied")) {
                player.addEffect(new MobEffectInstance(ModEffects.BASTION_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false));
            }
            else if (event.getEffectInstance().getEffect() == ModEffects.BASTION_ACTIVE.get() && data.getBoolean("bastion_applied")) {
                player.addEffect(new MobEffectInstance(ModEffects.BASTION_COOLDOWN.get(), 20*10, 0, false, false));
            }
            else if (event.getEffectInstance().getEffect() == ModEffects.RETALIATE_COOLDOWN.get() && data.getBoolean("retaliate_applied")) {
                player.addEffect(new MobEffectInstance(ModEffects.RETALIATE_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false));
            }
            else if (event.getEffectInstance().getEffect() == ModEffects.INTIMIDATING_PRESENCE_COOLDOWN.get() && data.getBoolean("intimidating_presence_eligible")) {
                player.addEffect(new MobEffectInstance(ModEffects.INTIMIDATING_PRESENCE.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false));
            }
            else if (event.getEffectInstance().getEffect() == ModEffects.DARING_SHOUT_COOLDOWN.get() && data.getBoolean("daring_shout_eligible")) {
                player.addEffect(new MobEffectInstance(ModEffects.DARING_SHOUT.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false));
            }
            else if (event.getEffectInstance().getEffect() == ModEffects.RECKONING_COOLDOWN.get() && data.getBoolean("reckoning_eligible")) {
                player.addEffect(new MobEffectInstance(ModEffects.RECKONING.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false));
            }
            else if (event.getEffectInstance().getEffect() == ModEffects.RECKONING_ACTIVE.get() && data.getBoolean("reckoning_eligible")) {
                player.addEffect(new MobEffectInstance(ModEffects.RECKONING_COOLDOWN.get(), 20*10, 0, false, false));
            }
            else if (event.getEffectInstance().getEffect() == ModEffects.CLAIRVOYANCE_COOLDOWN.get() && data.getBoolean("clairvoyance_applied") ) {
                player.addEffect(new MobEffectInstance(ModEffects.CLAIRVOYANCE_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false));
            }
            else if (event.getEffectInstance().getEffect() == ModEffects.EXPLOSIVE_TENDENCY_TIMER.get()) {

                MobEffectInstance currentStack = player.getEffect(ModEffects.EXPLOSIVE_TENDENCY_STACK.get());
                int currentAmp = (currentStack != null) ? currentStack.getAmplifier() : -1;
                int newAmplifier = Math.min(currentAmp + 1, 2);

                if (currentAmp < newAmplifier) {
                    if (currentStack != null) player.removeEffect(ModEffects.EXPLOSIVE_TENDENCY_STACK.get());
                    player.addEffect(new MobEffectInstance(ModEffects.EXPLOSIVE_TENDENCY_STACK.get(), MobEffectInstance.INFINITE_DURATION, newAmplifier, false, false));
                }
            }
            else if (event.getEffectInstance().getEffect() == ModEffects.EXPLOIT_WEAKNESS_COOLDOWN.get() && data.getBoolean("exploit_weakness_applied")) {
                player.addEffect(new MobEffectInstance(ModEffects.EXPLOIT_WEAKNESS_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false));
            }
            else if (event.getEffectInstance().getEffect() == ModEffects.ADRENALINE_INJECTION_COOLDOWN.get() && data.getBoolean("adrenaline_applied")) {
                    player.addEffect(new MobEffectInstance(ModEffects.ADRENALINE_INJECTION_UP.get(), 20*6, 0));
                new AttributeApplier().applyBlessings(player);
            }
            else if (event.getEffectInstance().getEffect() == ModEffects.ADRENALINE_INJECTION_UP.get()) {
                player.addEffect(new MobEffectInstance(ModEffects.ADRENALINE_INJECTION_COOLDOWN.get(), 20*24, 0, false, false));
                new AttributeApplier().applyBlessings(player);
            }
            else if (event.getEffectInstance().getEffect() == ModEffects.LIFE_TOUCH_COOLDOWN.get() && data.getBoolean("life_touch_applied")) {
                player.addEffect(new MobEffectInstance(ModEffects.LIFE_TOUCH_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false));
                new AttributeApplier().applyBlessings(player);
            }
            else if (event.getEffectInstance().getEffect() == ModEffects.SOUL_SEVERANCE_COOLDOWN.get() && data.getBoolean("soul_severance_eligible")) {
                player.addEffect(new MobEffectInstance(ModEffects.SOUL_SEVERANCE_READY  .get(), MobEffectInstance.INFINITE_DURATION, 0, false, false));
            }
            else if (event.getEffectInstance().getEffect() == ModEffects.DECEPTION_COOLDOWN.get() && data.getBoolean("deception_applied")) {
                player.addEffect(new MobEffectInstance(ModEffects.DECEPTION_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false));
            }
            else if (event.getEffectInstance().getEffect() == ModEffects.DIRECTED_HATRED_COOLDOWN.get() && data.getBoolean("directed_hatred_eligible")) {
                player.addEffect(new MobEffectInstance(ModEffects.DIRECTED_HATRED_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false));
            }
            else if (event.getEffectInstance().getEffect() == ModEffects.QUANTUM_LEAP_COOLDOWN.get() && data.getBoolean("quantum_leap_eligible")) {
                player.addEffect(new MobEffectInstance(ModEffects.QUANTUM_LEAP_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false));
                new AttributeApplier().applyBlessings(player);
            }
            else if (event.getEffectInstance().getEffect() == ModEffects.QUANTUM_LEAP_ACTIVE.get()) {
                new AttributeApplier().applyBlessings(player);
            }
            else if (event.getEffectInstance().getEffect() == ModEffects.SOLARA.get()) {
                player.addEffect(new MobEffectInstance(ModEffects.SOLARA.get()));
            }
        }
    }

    @SubscribeEvent
    public static void onEffectAdded(MobEffectEvent.Added event) {
        if (!(event.getEntity() instanceof Player player)) return;

        if (event.getEffectInstance().getEffect() == ModEffects.HAWKEYE.get()) {
            new AttributeApplier().applyBlessings(player);
            if (event.getEffectInstance().getAmplifier() >= 3) {
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.CROSSBOW_QUICK_CHARGE_3, SoundSource.PLAYERS, 1.3F, 1.0F
                );
            }
        } else if (event.getEffectInstance().getEffect() == ModEffects.FRENZY.get())
            new AttributeApplier().applyBlessings(player);

        else if (event.getEffectInstance().getEffect() == ModEffects.EXPLOSIVE_TENDENCY_STACK.get()) {
            if (event.getEffectInstance().getAmplifier() < 2) {
                player.addEffect(new MobEffectInstance(ModEffects.EXPLOSIVE_TENDENCY_TIMER.get(), 20 * 8, 0, false, false));
            }
        }

        else if (event.getEffectInstance().getEffect() == ModEffects.QUANTUM_LEAP_ACTIVE.get())
            new AttributeApplier().applyBlessings(player);
    }


    private double getSharpnessBonus(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!stack.isEmpty() && "sword".equals(ItemRarityUtils.getItemType(stack))) {
            return getSharpnessLevel(stack);
        }
        return 0.0;
    }


    private double getSharpnessLevel(ItemStack stack) {
        if (stack.isEmpty() || !"sword".equals(ItemRarityUtils.getItemType(stack))) return 0.0;
        int level = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, stack);
        if (level == 0) return 0.0;
        return 1.0 + (level - 1) * 0.5;
    }

    // For testing incoming damage
    @SubscribeEvent
    public void onLivingDamage(LivingDamageEvent event) {

        if (!(event.getEntity() instanceof Player player)) return;

        MobEffectInstance frenzy = player.getEffect(ModEffects.FRENZY.get());
        int frenzyStacks = frenzy != null ? frenzy.getAmplifier() + 1 : 0;

        event.setAmount(event.getAmount() * (1 + (float) (8 * frenzyStacks) /100));

        if (player.hasEffect(ModEffects.BASTION_ACTIVE.get())) {
            event.setCanceled(true);
        }
    }

    public static void removeModifier(Player player, Attribute attribute, UUID uuid) {
        AttributeInstance instance = player.getAttribute(attribute);
        if (instance == null) return;

        if (instance.getModifier(uuid) != null) {
            instance.removeModifier(uuid);
        }
    }

    @SubscribeEvent
    public static void onKnockback(LivingKnockBackEvent event) {
        if (event.getEntity().getPersistentData().getBoolean("spirit_grove_knockback_cancel")) {
            event.setStrength(0);
            event.setCanceled(true);
            event.getEntity().getPersistentData().remove("spirit_grove_knockback_cancel");
        }
    }
}