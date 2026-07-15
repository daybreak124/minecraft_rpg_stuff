package net.cold.coldsmod.stat;

import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Multimap;
import net.cold.coldsmod.ModMessages;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusCapabilityProvider;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.PlayerBonusCache;
import net.cold.coldsmod.menu_accessory.AccessoryRegistry;
import net.cold.coldsmod.menu_accessory.AccessoryStatRegistry;
import net.cold.coldsmod.menu_accessory.AccessoryUnlockSyncPacket;
import net.cold.coldsmod.menu_blessing.BlessingEffectRegistry;
import net.cold.coldsmod.menu_blessing.BlessingRegistry;
import net.cold.coldsmod.menu_blessing.BlessingUnlockSyncPacket;
import net.cold.coldsmod.menu_stat.*;
import net.cold.coldsmod.mob.Sbeve;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

import static net.cold.coldsmod.capabilities_and_blessings.effects.SummoningStone.getPlayerSbeve;
import static net.cold.coldsmod.capabilities_and_blessings.effects.SummoningStone.killSbeve;
import static net.cold.coldsmod.stat.AttributeMilestones.MILESTONES;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AttributeApplier {

    private static final UUID AS_UUID = UUID.fromString("e2225476-1234-5352-5454-113215411111");

    private static final Multimap<String, Supplier<Attribute>> ATTRIBUTE_LOOKUP =
            ImmutableListMultimap.<String, Supplier<Attribute>>builder()
                    .put("str", ModAttributes.STR)
                    .put("dex", ModAttributes.DEX)
                    .put("fort", ModAttributes.FORT)
                    .put("con", ModAttributes.CON)
                    .put("perc", ModAttributes.PERC)
                    .put("insight", ModAttributes.INSIGHT)
                    .put("wisdom", ModAttributes.WISDOM)
                    .put("intelligence", ModAttributes.INTELLIGENCE)

                    .put("armor", () -> Attributes.ARMOR)
                    .put("armorToughness", () -> Attributes.ARMOR_TOUGHNESS)
                    .put("maxHealth", () -> Attributes.MAX_HEALTH)
                    .put("knockbackResist", () -> Attributes.KNOCKBACK_RESISTANCE)
                    .put("luck", () -> Attributes.LUCK)

                    .put("damage", ModAttributes.POTENCY)
                    .put("damage", ModAttributes.MELEE_POTENCY)
                    .put("damage", ModAttributes.PROJECTILE_POTENCY)

                    .put("critChance", ModAttributes.ACCURACY)
                    .put("critChance", ModAttributes.MELEE_ACCURACY)
                    .put("critChance", ModAttributes.PROJECTILE_ACCURACY)

                    .put("critDamage", ModAttributes.PRECISION)
                    .put("critDamage", ModAttributes.MELEE_PRECISION)
                    .put("critDamage", ModAttributes.PROJECTILE_PRECISION)

                    .put("attackSpeed", ModAttributes.MELEE_HASTE)

                    .put("meleeDamage", ModAttributes.MELEE_POTENCY)
                    .put("meleeCritChance", ModAttributes.MELEE_ACCURACY)
                    .put("meleeCritDamage", ModAttributes.MELEE_PRECISION)

                    .put("projectileDamage", ModAttributes.PROJECTILE_POTENCY)
                    .put("projectileCritChance", ModAttributes.PROJECTILE_ACCURACY)
                    .put("projectileCritDamage", ModAttributes.PROJECTILE_PRECISION)
                    .put("drawSpeed", ModAttributes.NOCK_HASTE)


                    .put("restoration", ModAttributes.RESTORATION)
                    .put("rejuvenation", ModAttributes.REJUVENATION)
                    .put("amplification", ModAttributes.AMPLIFICATION)

                    .put("debuffResist", ModAttributes.DEBUFF_RESIST)

                    .put("moveSpeed", () -> Attributes.MOVEMENT_SPEED)
                    .put("swimSpeed", ForgeMod.SWIM_SPEED)
                    .put("stepHeight", ForgeMod.STEP_HEIGHT_ADDITION)
                    .put("blockReach", ForgeMod.BLOCK_REACH)
                    .put("entityReach", ForgeMod.ENTITY_REACH)
                    .put("xpGain", ModAttributes.XP_GAIN)
                    .put("miningSpeed", ModAttributes.MINING_SPEED)
                    .put("jumpBoost", ModAttributes.JUMP_BOOST)
                    .build();


    public static void register() {
        MinecraftForge.EVENT_BUS.register(new AttributeApplier());
    }

//    @SubscribeEvent
//    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
//    }
//
//    @SubscribeEvent
//    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
//    }

    public static void refreshMilestones(Player player) {
        MILESTONES.forEach((attrReg, map) -> {
            double currentValue = player.getAttributeValue(attrReg.get());
            map.forEach((threshold, action) -> {
                String uniqueId = "milestone_" + attrReg.getId().getPath() + "_" + threshold;
                boolean hasReached = currentValue >= threshold;
                action.apply(player, uniqueId, hasReached);
            });
        });
    }

    public static void refreshPerPointStats(Player player) {
        BaseStatSnapshot snapshot = new BaseStatSnapshot(
                player.getAttributeValue(ModAttributes.STR.get()),
                player.getAttributeValue(ModAttributes.DEX.get()),
                player.getAttributeValue(ModAttributes.FORT.get()),
                player.getAttributeValue(ModAttributes.CON.get()),
                player.getAttributeValue(ModAttributes.PERC.get()),
                player.getAttributeValue(ModAttributes.INSIGHT.get()),
                player.getAttributeValue(ModAttributes.WISDOM.get())
        );

        PER_POINT_FORMULAS.forEach((attrSupplier, formula) -> {
            Attribute targetAttr = attrSupplier.get();
            AttributeInstance instance = player.getAttribute(targetAttr);

            if (instance != null) {
                UUID uuid = UUID.nameUUIDFromBytes(("per_point_" + targetAttr.getDescriptionId()).getBytes(StandardCharsets.UTF_8));

                instance.removeModifier(uuid);
                double value = formula.apply(snapshot);

                if (value != 0) {
                    instance.addTransientModifier(new AttributeModifier(uuid, "Per-Point Scaling", value, AttributeModifier.Operation.ADDITION));
                }
            }
        });
    }


    public static void recalculateDynamicBonuses(Player player) {
//        removeModifier(player, Attributes.ARMOR, ARMOR_UUID);
//        removeModifier(player, Attributes.ARMOR_TOUGHNESS, TOUGHNESS_UUID);
//        removeModifier(player, Attributes.MAX_HEALTH, HP_UUID);
//        removeModifier(player, Attributes.ATTACK_SPEED, AS_UUID);
//
//        double healthPercent = player.getAttributeValue(ModAttributes.HEALTH_MULTIPLIER.get());
//        double armorPercent = player.getAttributeValue(ModAttributes.ARMOR_MULTIPLIER.get());
//        double toughnessPercent = player.getAttributeValue(ModAttributes.TOUGHNESS_MULTIPLIER.get());
//        double ASBonus = getScaledValue(player, ModAttributes.MELEE_HASTE.get(), ModAttributes.MELEE_HASTE_MULTIPLIER.get());
//
//        applyPercentModifier(player, Attributes.MAX_HEALTH, healthPercent, HP_UUID);
//        applyPercentModifier(player, Attributes.ARMOR, armorPercent, ARMOR_UUID);
//        applyPercentModifier(player, Attributes.ARMOR_TOUGHNESS, toughnessPercent, TOUGHNESS_UUID);
//        applyPercentModifier(player, Attributes.ATTACK_SPEED, ASBonus / 100.0, AS_UUID);


        if (player.getHealth() > player.getMaxHealth()) {
            player.setHealth(player.getMaxHealth());
        }
    }

//    public static void recalcArmor(Player player) {
//        removeModifier(player, Attributes.ARMOR, ARMOR_UUID);
//        double armorPercent = player.getAttributeValue(ModAttributes.ARMOR_MULTIPLIER.get());
//        applyPercentModifier(player, Attributes.ARMOR, armorPercent, ARMOR_UUID);
//
//    }
//
//    public static void recalcToughness(Player player) {
//        removeModifier(player, Attributes.ARMOR_TOUGHNESS, TOUGHNESS_UUID);
//        double toughnessPercent = player.getAttributeValue(ModAttributes.TOUGHNESS_MULTIPLIER.get());
//        applyPercentModifier(player, Attributes.ARMOR_TOUGHNESS, toughnessPercent, TOUGHNESS_UUID);
//    }
//
    public static void recalcAS(Player player) {
        removeModifier(player, Attributes.ATTACK_SPEED, AS_UUID);
        double ASBonus = getScaledValue(player, ModAttributes.MELEE_HASTE.get());
        applyModifier(player, Attributes.ATTACK_SPEED, AS_UUID.toString(), ASBonus / 100.0, AttributeModifier.Operation.MULTIPLY_BASE);
    }

    public record BaseStatSnapshot(double str, double dex, double fort, double con, double perc, double insight, double wisdom) {}

    private static final Map<Supplier<Attribute>, Function<BaseStatSnapshot, Double>> PER_POINT_FORMULAS = Map.ofEntries(
            Map.entry(ModAttributes.POTENCY, s -> s.str * 0.06 + s.con * 0.04),
            Map.entry(ModAttributes.MELEE_POTENCY, s -> s.str * 0.06 + s.str * 0.12 + s.con * 0.04),
            Map.entry(ModAttributes.PROJECTILE_POTENCY, s -> (s.str * 0.06 + s.con * 0.04 + s.dex * 0.075)),
            Map.entry(() -> Attributes.ATTACK_DAMAGE, s -> s.str * 0.006),

            Map.entry(ModAttributes.ACCURACY, s -> s.dex * 0.11),
            Map.entry(ModAttributes.MELEE_ACCURACY, s -> s.dex * 0.11),
            Map.entry(ModAttributes.PROJECTILE_ACCURACY, s -> s.dex * 0.11),

            Map.entry(ModAttributes.PRECISION, s -> s.perc * 0.32),
            Map.entry(ModAttributes.MELEE_PRECISION, s -> s.perc * 0.32),
            Map.entry(ModAttributes.PROJECTILE_PRECISION, s -> s.perc * 0.32),

            Map.entry(ModAttributes.HASTE, s -> s.dex * 0.05),
            Map.entry(ModAttributes.MELEE_HASTE, s -> s.dex * 0.05),
            Map.entry(ModAttributes.NOCK_HASTE, s -> s.dex * 0.1),
            Map.entry(() -> Attributes.MOVEMENT_SPEED, s -> s.dex * 0.000125),

            Map.entry(() -> Attributes.ARMOR, s -> s.perc * 0.05 + s.fort * 0.1 + s.str * 0.075),
            Map.entry(() -> Attributes.MAX_HEALTH, s -> s.con * 0.035),
            Map.entry(() -> Attributes.ARMOR_TOUGHNESS, s -> s.fort * 0.05),
            Map.entry(ModAttributes.DEBUFF_RESIST, s -> s.con * 0.05 + s.wisdom * 0.1),
            Map.entry(() -> Attributes.KNOCKBACK_RESISTANCE, s -> s.fort * 0.0015),

            Map.entry(ModAttributes.RESTORATION, s -> s.wisdom * 0.25),
            Map.entry(ModAttributes.AMPLIFICATION, s -> s.wisdom * 0.25),
            Map.entry(ModAttributes.REJUVENATION, s -> s.con * 0.2),

            Map.entry(ModAttributes.XP_GAIN, s -> s.insight * 0.0025),
            Map.entry(ModAttributes.MINING_SPEED, s -> s.insight * 0.0025),
            Map.entry(() -> ForgeMod.BLOCK_REACH.get(), s -> s.insight * 0.05)
    );

//    @SubscribeEvent
//    public static void onEquipmentChange(LivingEquipmentChangeEvent event) {
//        if (!(event.getEntity() instanceof Player player)) return;
//        if (player.level().isClientSide()) return;
//        handleItemStats(player, event.getFrom(), event.getSlot(), false);
//        handleItemStats(player, event.getTo(), event.getSlot(), true);
//
//        player.level().getServer().tell(new net.minecraft.server.TickTask(
//                player.level().getServer().getTickCount() + 1,
//                () -> {
//                    if (player.isAlive()) {
//                        refreshPerPointStats(player);
//                        refreshMilestones(player);
//                        recalculateDynamicBonuses(player);
//                        applyCrossbowTag(player);
//                    }
//                }
//        ));
//    }

//    @SubscribeEvent
//    public static void onCurioChange(CurioChangeEvent event) {
//        if (!(event.getEntity() instanceof Player player)) return;
//        if (player.level().isClientSide()) return;
//        player.level().getServer().tell(new net.minecraft.server.TickTask(
//                player.level().getServer().getTickCount() + 1,
//                () -> {
//                    if (player.isAlive()) {
//                        refreshPerPointStats(player);
//                        refreshMilestones(player);
//                        recalculateDynamicBonuses(player);
//                        applyCrossbowTag(player);
//                    }
//                }
//        ));
//    }
//
//        public static boolean isDuplicateAccessory(Player player, ItemStack stack, String baseName) {
//        Optional<ICuriosItemHandler> handlerOpt = CuriosApi.getCuriosHelper().getCuriosHandler(player).resolve();
//
//        if (handlerOpt.isPresent()) {
//            ICuriosItemHandler handler = handlerOpt.get();
//
//            return handler.getCurios().values().stream().anyMatch(slotHandler -> {
//                for (int i = 0; i < slotHandler.getSlots(); i++) {
//                    ItemStack equipped = slotHandler.getStacks().getStackInSlot(i);
//
//                    if (!equipped.isEmpty()) {
//                        String path = ForgeRegistries.ITEMS.getKey(equipped.getItem()).getPath();
//
//                        if (path.contains(baseName) && equipped != stack) {
//                            return true;
//                        }
//                    }
//                }
//                return false;
//            });
//        }
//        return false;
//    }


    @SubscribeEvent
    public static void onEquipmentChange(LivingEquipmentChangeEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.level().isClientSide()) return;
        if (event.getSlot().getType() == EquipmentSlot.Type.HAND) {
            ItemStack newStack = event.getTo();

            if (newStack.getItem() instanceof CrossbowItem) {

                CompoundTag nbt = newStack.getOrCreateTag();

                crossbowDrawSpeedUpdate(player, newStack, nbt);

                PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
                boolean hasAdrenaline = cache.isInjection();
                if (hasAdrenaline) {
                    nbt.putBoolean("adr", true);
                } else {
                    nbt.remove("adr");
                }
            }
        }
    }

    public static void crossbowDrawSpeedUpdate(Player player, ItemStack stack, CompoundTag nbt) {

        double currentHaste = getScaledValue(player, ModAttributes.NOCK_HASTE.get());

        if (nbt.getDouble("draw") != currentHaste) {
            nbt.putDouble("draw", currentHaste);
        }
    }

//    @SubscribeEvent
//    public static void onUseItemStart(LivingEntityUseItemEvent.Start event) {
//        if (!(event.getEntity() instanceof Player player) || player.level().isClientSide()) return;
//
//        ItemStack stack = event.getItem();
//        String itemType = ItemRarityUtils.getItemType(stack);
//    }

    public static double getScaledValue(Player player, Attribute ratingAttr) {
        double effectiveRating = player.getAttributeValue(ratingAttr);
        int constant = 200;
        if (constant + effectiveRating == 0) return 0;
        return (constant * effectiveRating) / (constant + Math.abs(effectiveRating));
    }


    public static void applyModifier(LivingEntity entity, Attribute attribute, double value, UUID uuid) {
        AttributeInstance instance = entity.getAttribute(attribute);
        if (instance == null) return;

        AttributeModifier existing = instance.getModifier(uuid);

        if (existing != null && existing.getAmount() == value) return;

        if (existing != null) instance.removeModifier(uuid);
        if (value != 0) {
            AttributeModifier modifier = new AttributeModifier(uuid, "stat", value, AttributeModifier.Operation.ADDITION);
            instance.addTransientModifier(modifier);
        }
    }

    public static void applyModifier(Player player, Attribute attribute, String uuidString, double amount, AttributeModifier.Operation operation) {
        AttributeInstance instance = player.getAttribute(attribute);
        if (instance != null) {
            UUID uuid = UUID.fromString(uuidString);
            instance.removeModifier(uuid);
            AttributeModifier modifier = new AttributeModifier(uuid, "stat", amount, operation);
            instance.addTransientModifier(modifier);
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

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        CompoundTag oldData = event.getOriginal().getPersistentData();
        CompoundTag newData = event.getEntity().getPersistentData();
        Player player = event.getEntity();

        event.getOriginal().getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(oldStore -> {
            event.getEntity().getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(newStore -> {
                newStore.copyFrom(oldStore);
            });
        });

        // Move Screen 1
        if (oldData.contains("SpentPointsOne")) {
            newData.put("SpentPointsOne", oldData.getCompound("SpentPointsOne").copy());
        }

        // Move Screen 2
        if (oldData.contains("SpentPoints")) {
            newData.put("SpentPoints", oldData.getCompound("SpentPoints").copy());
        }

        // Move Screen 3
        if (oldData.contains("SpentPointsUtil")) {
            newData.put("SpentPointsUtil", oldData.getCompound("SpentPointsUtil").copy());
        }

        if (oldData.contains("ActiveBlessings")) {
            newData.put("ActiveBlessings", oldData.getCompound("ActiveBlessings").copy());
        }

        if (oldData.contains("ActiveAccessories")) {
            newData.put("ActiveAccessories", oldData.getCompound("ActiveAccessories").copy());
        }

        String[] trees = {"LION", "NIGHT", "STEEL", "HERALD"};
        for (String tree : trees) {
            String key = "ActiveFeats_" + tree;
            if (oldData.contains(key)) {
                newData.put(key, oldData.getCompound(key).copy());
            }
        }

        player.getServer().tell(new net.minecraft.server.TickTask(
                player.getServer().getTickCount() + 1,
                () -> {
                    if (player.isAlive()) {
                        syncAndApplyAttributes((ServerPlayer) player);
                        refreshPerPointStats(player);
                        refreshMilestones(player);
                        recalculateDynamicBonuses(player);
                    }
                }
        ));
    }

    @SubscribeEvent
    public static void onDimensionChange(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.getEntity() instanceof ServerPlayer sp) {
            sp.getServer().tell(new net.minecraft.server.TickTask(
                    sp.getServer().getTickCount() + 1,
                    () -> {
                            if (sp.isAlive()) {
                                syncAndApplyAttributes(sp);
                                refreshPerPointStats(sp);
                                refreshMilestones(sp);
                                recalculateDynamicBonuses(sp);
                            }
                        }
            ));
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer sp) {

            sp.getServer().tell(new net.minecraft.server.TickTask(
                    sp.getServer().getTickCount() + 1,
                    () -> {
                        if (sp.isAlive()) {
                            syncAndApplyAttributes(sp);
                            refreshPerPointStats(sp);
                            refreshMilestones(sp);
                            recalculateDynamicBonuses(sp);
                        }
                    }
            ));
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            List<MobEffectInstance> effectsToRefresh = new ArrayList<>(player.getActiveEffects());

            for (MobEffectInstance instance : effectsToRefresh) {
                MobEffect type = instance.getEffect();

                player.removeEffectNoUpdate(type);

                player.addEffect(new MobEffectInstance(type, instance.getDuration(), instance.getAmplifier(), instance.isAmbient(), instance.isVisible(), instance.showIcon()));
            }

            player.getServer().tell(new net.minecraft.server.TickTask(
                    player.getServer().getTickCount() + 1,
                    () -> {
                        if (player.isAlive()) {
                            syncAndApplyAttributes(player);
                            refreshPerPointStats(player);
                            refreshMilestones(player);
                            recalculateDynamicBonuses(player);
                        }
                    }
            ));
        }
    }

    public static void syncAndApplyAttributes(ServerPlayer player) {
        CompoundTag data = player.getPersistentData();
        List<AttributeInstance> toSync = new ArrayList<>();

        if (data.contains("SpentPointsOne")) {
            CompoundTag spent1 = data.getCompound("SpentPointsOne");
            for (String key : spent1.getAllKeys()) {
                Attribute attr = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(key));
                if (attr == null) continue;
                AttributeApplier.applyModifier(player, attr, spent1.getInt(key), StatUpgradeHandler.ATTRIBUTE_UPGRADE);
                if (player.getAttribute(attr) != null) toSync.add(player.getAttribute(attr));
            }
            ModMessages.sendToPlayer(new StatsSyncPacket(spent1, true, false), player);
        }

        if (data.contains("SpentPoints")) {
            CompoundTag spent2 = data.getCompound("SpentPoints");
            for (String key : spent2.getAllKeys()) {
                Attribute attr = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(key));
                if (attr == null) continue;
                double inc = StatUpgradeHandlerTwo.getIncrementFor(attr);
                AttributeApplier.applyModifier(player, attr, spent2.getInt(key) * inc, StatUpgradeHandlerTwo.STAT_MODIFIER_UUID);
                if (player.getAttribute(attr) != null) toSync.add(player.getAttribute(attr));
            }
            ModMessages.sendToPlayer(new StatsSyncPacket(spent2, false, false), player);
        }

        if (data.contains("SpentPointsUtil")) {
            CompoundTag spent3 = data.getCompound("SpentPointsUtil");
            for (String key : spent3.getAllKeys()) {
                Attribute attr = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(key));
                if (attr == null) continue;
                double inc = StatUpgradeHandlerThree.getIncrementFor(attr);
                AttributeApplier.applyModifier(player, attr, spent3.getInt(key) * inc, StatUpgradeHandlerThree.UTIL_STAT_MODIFIER_UUID);
                if (player.getAttribute(attr) != null) toSync.add(player.getAttribute(attr));
            }
            ModMessages.sendToPlayer(new StatsSyncPacket(spent3, false, true), player);
        }

        if (!toSync.isEmpty()) {
            player.connection.send(new ClientboundUpdateAttributesPacket(player.getId(), toSync));
        }

        if (data.contains("ActiveBlessings")) {
            CompoundTag blessings = data.getCompound("ActiveBlessings");

            for (String id : blessings.getAllKeys()) {
                if (blessings.getBoolean(id)) {
                    var entry = BlessingRegistry.MAP.get(id);
                    if (entry != null) {
                        var onApply = BlessingEffectRegistry.ON_APPLY.get(entry.item());
                        if (onApply != null) onApply.accept(player);
                    }
                }
            }
            ModMessages.sendToPlayer(new BlessingUnlockSyncPacket(data), player);
        }

        if (data.contains("ActiveAccessories")) {
            CompoundTag accessories = data.getCompound("ActiveAccessories");

            for (String id : accessories.getAllKeys()) {
                if (accessories.getBoolean(id)) {
                    var entry = AccessoryRegistry.MAP.get(id);
                    if (entry != null) {
                        var onEquip = AccessoryStatRegistry.ON_APPLY_ACC.get(entry.item());
                        if (onEquip != null) {
                            onEquip.accept(player);
                        }
                    }
                }
            }
            ModMessages.sendToPlayer(new AccessoryUnlockSyncPacket(accessories), player);
        }

        String[] trees = {"LION", "NIGHT", "STEEL", "HERALD"};
        for (String tree : trees) {
            String key = "ActiveFeats_" + tree;
            if (data.contains(key)) {
                CompoundTag treeFeats = data.getCompound(key);
                for (String featKey : treeFeats.getAllKeys()) {
                    try {
                        int featId = Integer.parseInt(featKey.replace("feat_", ""));
                        if (treeFeats.getBoolean(featKey)) {
                            FeatUpgradeHandlerRegistry.reapplyFeat(player, featId);
                        }
                    } catch (Exception ignored) {}
                }
            }
        }
        ModMessages.sendToPlayer(new FeatSyncPacket(data), player);

        player.level().getServer().tell(new net.minecraft.server.TickTask(
                player.level().getServer().getTickCount() + 10,
                () -> {
                    if (player.isAlive()) {
                        recalcAS(player);
                    }
                }
        ));
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        if (event.getEntity() instanceof Player player) {
            if (player.level() instanceof ServerLevel serverLevel) {
                Sbeve activeSbeve = getPlayerSbeve(serverLevel, player);
                if (activeSbeve != null) {
                    activeSbeve.discard();
                    player.getPersistentData().remove("active_sbeve_uuid");
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.getEntity().level().isClientSide()) return;

        Player player = event.getEntity();

        if (player.level() instanceof ServerLevel serverLevel) {
            killSbeve(serverLevel, player);
            player.getPersistentData().remove("active_sbeve_uuid");
        }
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            BonusCapabilityProvider provider = new BonusCapabilityProvider();
            event.addCapability(new ResourceLocation("coldsmod", "bonuses"), provider);
        }
    }


    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        if (event.getFlags().isAdvanced()) {
            ItemStack stack = event.getItemStack();
            String type = ItemRarityUtils.getItemType(stack);

            if (type.equals("sword")) {
                boolean holdingAlt = Screen.hasAltDown();
                boolean holdingCtrl = Screen.hasControlDown();
                boolean holdingShift = Screen.hasShiftDown();

                if (!holdingAlt && !holdingCtrl && !holdingShift) {
                    event.getToolTip().add(Component.literal("Hold [ALT]   -> Basic Attack").withStyle(ChatFormatting.YELLOW));
                    event.getToolTip().add(Component.literal("Hold [CTRL]  -> Jump Attacks").withStyle(ChatFormatting.YELLOW));
                    event.getToolTip().add(Component.literal("Hold [SHIFT] -> Directional Attacks").withStyle(ChatFormatting.YELLOW));

                    event.getToolTip().add(Component.empty());
                    event.getToolTip().add(Component.literal("Using the same type of").withStyle(ChatFormatting.WHITE));
                    event.getToolTip().add(Component.literal("attack consecutively applies").withStyle(ChatFormatting.WHITE));
                    event.getToolTip().add(Component.literal("a -30% Attack Speed penalty").withStyle(ChatFormatting.WHITE));
                }

                else if (holdingAlt) {
                    event.getToolTip().add(Component.literal("BASIC ATTACKS").withStyle(ChatFormatting.GOLD));
                    event.getToolTip().add(Component.empty());

                    event.getToolTip().add(Component.literal("Attack: ").withStyle(ChatFormatting.GOLD)
                            .append(Component.literal("Swing").withStyle(ChatFormatting.WHITE)));

                    event.getToolTip().add(Component.literal("Condition: ").withStyle(ChatFormatting.GRAY)
                            .append(Component.literal("Attack").withStyle(ChatFormatting.WHITE)));

                    event.getToolTip().add(Component.literal("Damage: ").withStyle(ChatFormatting.GRAY)
                            .append(Component.literal("100% (+15%)").withStyle(ChatFormatting.RED)));

                    event.getToolTip().add(Component.literal("Attack Speed: ").withStyle(ChatFormatting.GRAY)
                            .append(Component.literal("-0%").withStyle(ChatFormatting.BLUE)));
                }

                else if (holdingCtrl) {
                    event.getToolTip().add(Component.literal("JUMP ATTACKS").withStyle(ChatFormatting.GOLD));
                    event.getToolTip().add(Component.empty());

                    // Heavy Attack
                    event.getToolTip().add(Component.literal("Attack: ").withStyle(ChatFormatting.GOLD)
                            .append(Component.literal("Heavy Attack").withStyle(ChatFormatting.WHITE)));

                    event.getToolTip().add(Component.literal("Condition: ").withStyle(ChatFormatting.GRAY)
                            .append(Component.literal("Falling + Attack + >=50% Attack Bar").withStyle(ChatFormatting.WHITE)));

                    event.getToolTip().add(Component.literal("Damage: ").withStyle(ChatFormatting.GRAY)
                            .append(Component.literal("130% (+30%)").withStyle(ChatFormatting.RED)));

                    event.getToolTip().add(Component.literal("Attack Speed: ").withStyle(ChatFormatting.GRAY)
                            .append(Component.literal("-0%").withStyle(ChatFormatting.BLUE)));

                    event.getToolTip().add(Component.literal("Effect: ").withStyle(ChatFormatting.GRAY)
                            .append(Component.literal("Disable target (player) shields for 5s").withStyle(ChatFormatting.WHITE)));

                    event.getToolTip().add(Component.empty());

                    // Upward Attack
                    event.getToolTip().add(Component.literal("Attack: ").withStyle(ChatFormatting.GOLD)
                            .append(Component.literal("Upward Attack").withStyle(ChatFormatting.WHITE)));

                    event.getToolTip().add(Component.literal("Condition: ").withStyle(ChatFormatting.GRAY)
                            .append(Component.literal("Ascending + Attack + >=50% Attack Bar").withStyle(ChatFormatting.WHITE)));

                    event.getToolTip().add(Component.literal("Damage: ").withStyle(ChatFormatting.GRAY)
                            .append(Component.literal("100%").withStyle(ChatFormatting.RED)));

                    event.getToolTip().add(Component.literal("Attack Speed: ").withStyle(ChatFormatting.GRAY)
                            .append(Component.literal("-20%").withStyle(ChatFormatting.BLUE)));

                    event.getToolTip().add(Component.literal("Effect: ").withStyle(ChatFormatting.GRAY)
                            .append(Component.literal("Throw target in the air.").withStyle(ChatFormatting.WHITE)));
                    event.getToolTip().add(Component.literal("Apply Melee Vulnerability to target").withStyle(ChatFormatting.WHITE));
                    event.getToolTip().add(Component.literal("causing other attacks to have extra").withStyle(ChatFormatting.WHITE));
                    event.getToolTip().add(Component.literal("benefits on them.").withStyle(ChatFormatting.WHITE));
                }

                else if (holdingShift) {
                    event.getToolTip().add(Component.literal("DIRECTIONAL ATTACKS").withStyle(ChatFormatting.GOLD));
                    event.getToolTip().add(Component.empty());

                    event.getToolTip().add(Component.literal("Attack: ").withStyle(ChatFormatting.GOLD)
                            .append(Component.literal("Poke").withStyle(ChatFormatting.WHITE)));

                    event.getToolTip().add(Component.literal("Condition: ").withStyle(ChatFormatting.GRAY)
                            .append(Component.literal("Sprint + Attack + >=90% Attack Bar").withStyle(ChatFormatting.WHITE)));

                    event.getToolTip().add(Component.literal("Damage: ").withStyle(ChatFormatting.GRAY)
                            .append(Component.literal("125% (+25%)").withStyle(ChatFormatting.RED)));

                    event.getToolTip().add(Component.literal("Attack Speed: ").withStyle(ChatFormatting.GRAY)
                            .append(Component.literal("-33.3%").withStyle(ChatFormatting.BLUE)));

                    event.getToolTip().add(Component.literal("Effect: ").withStyle(ChatFormatting.GRAY)
                            .append(Component.literal("Pushes target 5 (+2) blocks").withStyle(ChatFormatting.WHITE)));

                    event.getToolTip().add(Component.empty());

                    // Cleave
                    event.getToolTip().add(Component.literal("Attack: ").withStyle(ChatFormatting.GOLD)
                            .append(Component.literal("Cleave").withStyle(ChatFormatting.WHITE)));

                    event.getToolTip().add(Component.literal("Condition: ").withStyle(ChatFormatting.GRAY)
                            .append(Component.literal("Move Sideways + Attack + >=50% Attack Bar").withStyle(ChatFormatting.WHITE)));

                    event.getToolTip().add(Component.literal("Damage: ").withStyle(ChatFormatting.GRAY)
                            .append(Component.literal("60% (+10%) (+10%*S)").withStyle(ChatFormatting.RED)));

                    event.getToolTip().add(Component.literal("Attack Speed: ").withStyle(ChatFormatting.GRAY)
                            .append(Component.literal("-20%").withStyle(ChatFormatting.BLUE)));

                    event.getToolTip().add(Component.literal("Effect: ").withStyle(ChatFormatting.GRAY)
                            .append(Component.literal("Strikes all enemies in a 120° arc").withStyle(ChatFormatting.WHITE)));

                    event.getToolTip().add(Component.literal("Range scale with attack speed").withStyle(ChatFormatting.WHITE));
                    event.getToolTip().add(Component.literal("Damage +10% additively per Sweeping Edge level").withStyle(ChatFormatting.WHITE));
                }
            }

            if (!type.equals("unknown")) {
                String formattedType = type.substring(0, 1).toUpperCase() + type.substring(1);

                event.getToolTip().add(Component.empty());

                event.getToolTip().add(Component.literal("Cold's Mod Item Type: ").withStyle(ChatFormatting.DARK_AQUA)
                        .append(Component.literal(formattedType).withStyle(ChatFormatting.WHITE)));
            }
        }
    }
}