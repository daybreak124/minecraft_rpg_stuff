package net.cold.coldsmod.stat;

import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Multimap;
import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
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
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.event.CurioChangeEvent;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

import static net.cold.coldsmod.stat.AttributeMilestones.MILESTONES;
import static net.cold.coldsmod.stat.ItemRarityUtils.getItemType;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AttributeApplier {

    private static final UUID AS_UUID = UUID.fromString("e2225476-1234-5352-5454-113215411111");
    private static final UUID ARMOR_UUID = UUID.fromString("e2222376-1234-5352-5254-113225461111");
    private static final UUID TOUGHNESS_UUID = UUID.fromString("e2228876-1234-5352-5454-118115412111");
    private static final UUID HP_UUID = UUID.fromString("e2209476-1234-5352-5454-113995001111");

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

                    .put("attackSpeed", ModAttributes.HASTE)

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


                    .put("damageMultiplier", ModAttributes.POTENCY_MULTIPLIER)
                    .put("damageMultiplier", ModAttributes.MELEE_POTENCY_MULTIPLIER)
                    .put("damageMultiplier", ModAttributes.PROJECTILE_POTENCY_MULTIPLIER)

                    .put("critChanceMultiplier", ModAttributes.ACCURACY_MULTIPLIER)
                    .put("critChanceMultiplier", ModAttributes.MELEE_ACCURACY_MULTIPLIER)
                    .put("critChanceMultiplier", ModAttributes.PROJECTILE_ACCURACY_MULTIPLIER)

                    .put("critDamageMultiplier", ModAttributes.PRECISION_MULTIPLIER)
                    .put("critDamageMultiplier", ModAttributes.MELEE_PRECISION_MULTIPLIER)
                    .put("critDamageMultiplier", ModAttributes.PROJECTILE_PRECISION_MULTIPLIER)

                    .put("speedMultiplier", ModAttributes.HASTE_MULTIPLIER)


                    .put("meleeDamageMultiplier", ModAttributes.MELEE_POTENCY_MULTIPLIER)
                    .put("meleeCritChanceMultiplier", ModAttributes.MELEE_ACCURACY_MULTIPLIER)
                    .put("meleeCritDamageMultiplier", ModAttributes.MELEE_PRECISION_MULTIPLIER)
                    .put("projectileDamageMultiplier", ModAttributes.PROJECTILE_POTENCY_MULTIPLIER)
                    .put("projectileCritChanceMultiplier", ModAttributes.PROJECTILE_ACCURACY_MULTIPLIER)
                    .put("projectileCritDamageMultiplier", ModAttributes.PROJECTILE_PRECISION_MULTIPLIER)
                    .put("drawSpeedMultiplier", ModAttributes.NOCK_HASTE_MULTIPLIER)


                    .put("armorMultiplier", ModAttributes.ARMOR_MULTIPLIER)
                    .put("toughnessMultiplier", ModAttributes.TOUGHNESS_MULTIPLIER)
                    .put("healthMultiplier", ModAttributes.HEALTH_MULTIPLIER)
                    .put("debuffResist", ModAttributes.DEBUFF_RESIST)

                    .put("restorationMultiplier", ModAttributes.RESTORATION_MULTIPLIER)
                    .put("rejuvenationMultiplier", ModAttributes.REJUVENATION_MULTIPLIER)
                    .put("amplificationMultiplier", ModAttributes.AMPLIFICATION_MULTIPLIER)

                    .put("moveSpeed", () -> Attributes.MOVEMENT_SPEED)
                    .put("swimSpeed", () -> ForgeMod.SWIM_SPEED.get())
                    .put("stepHeight", () -> ForgeMod.STEP_HEIGHT_ADDITION.get())
                    .put("blockReach", () -> ForgeMod.BLOCK_REACH.get())
                    .put("entityReach", () -> ForgeMod.ENTITY_REACH.get())
                    .put("xpGain", ModAttributes.XP_GAIN)
                    .put("miningSpeed", ModAttributes.MINING_SPEED)
                    .put("jumpBoost", ModAttributes.JUMP_BOOST)
                    .build();

    private static void handleItemStats(Player player, ItemStack stack, EquipmentSlot slot, boolean adding) {
        if (stack.isEmpty() || !stack.hasTag()) return;

        CompoundTag statsTag = stack.getTag().getCompound("custom_stats");
        if (statsTag.isEmpty()) return;

        String type = getItemType(stack);
        if (slot.getType() == EquipmentSlot.Type.HAND) {
            boolean isValidWeaponOrTool = type.equals("sword") ||
                    type.equals("bow") ||
                    type.equals("crossbow") ||
                    type.equals("shield") ||
                    type.equals("tools");

            if (!isValidWeaponOrTool) return;
        }

        for (String key : statsTag.getAllKeys()) {
            Collection<Supplier<Attribute>> suppliers = ATTRIBUTE_LOOKUP.get(key);
            if (suppliers.isEmpty()) continue;

            double value = statsTag.getDouble(key);
            if (value == 0 && adding) continue;


            for (Supplier<Attribute> supplier : suppliers) {
                Attribute attribute = supplier.get();
                AttributeInstance instance = player.getAttribute(attribute);

                if (instance != null) {
                    String uniqueId = type + "_" + key + "_" + attribute.getDescriptionId();
                    UUID modifierUUID = UUID.nameUUIDFromBytes(uniqueId.getBytes(StandardCharsets.UTF_8));

                    instance.removeModifier(modifierUUID);

                    if (adding) {
                        instance.addTransientModifier(new AttributeModifier(modifierUUID,
                                "Gear Stat: " + key, value, AttributeModifier.Operation.ADDITION));
                    }
                }
            }
        }
    }


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


    private static void recalculateDynamicBonuses(Player player) {

        removeModifier(player, Attributes.ARMOR, ARMOR_UUID);
        removeModifier(player, Attributes.ARMOR_TOUGHNESS, TOUGHNESS_UUID);
        removeModifier(player, Attributes.MAX_HEALTH, HP_UUID);
        removeModifier(player, Attributes.ATTACK_SPEED, AS_UUID);


        double healthPercent = player.getAttributeValue(ModAttributes.HEALTH_MULTIPLIER.get());
        double armorPercent = player.getAttributeValue(ModAttributes.ARMOR_MULTIPLIER.get());
        double toughnessPercent = player.getAttributeValue(ModAttributes.TOUGHNESS_MULTIPLIER.get());
        double ASBonus = getScaledValue(player, ModAttributes.HASTE.get(), ModAttributes.HASTE_MULTIPLIER.get());

        applyPercentModifier(player, Attributes.MAX_HEALTH, healthPercent, HP_UUID);
        applyPercentModifier(player, Attributes.ARMOR, armorPercent, ARMOR_UUID);
        applyPercentModifier(player, Attributes.ARMOR_TOUGHNESS, toughnessPercent, TOUGHNESS_UUID);
        applyPercentModifier(player, Attributes.ATTACK_SPEED, ASBonus / 100.0, AS_UUID);

        if (player.getHealth() > player.getMaxHealth()) {
            player.setHealth(player.getMaxHealth());
        }
    }

    public record BaseStatSnapshot(double str, double dex, double fort, double con, double perc, double insight, double wisdom) {}

    private static final Map<Supplier<Attribute>, Function<BaseStatSnapshot, Double>> PER_POINT_FORMULAS = Map.ofEntries(
            Map.entry(ModAttributes.POTENCY, s -> s.str * 0.25 + s.con * 0.125),
            Map.entry(ModAttributes.MELEE_POTENCY, s -> s.str * 0.25 + s.con * 0.125),
            Map.entry(ModAttributes.PROJECTILE_POTENCY, s -> (s.str * 0.25 + s.con * 0.125 + s.dex * 0.175)),
            Map.entry(() -> Attributes.ATTACK_DAMAGE, s -> s.str * 0.020),

            Map.entry(ModAttributes.ACCURACY, s -> s.dex * 0.2),
            Map.entry(ModAttributes.MELEE_ACCURACY, s -> s.dex * 0.2),
            Map.entry(ModAttributes.PROJECTILE_ACCURACY, s -> s.dex * 0.2),

            Map.entry(ModAttributes.PRECISION, s -> s.perc * 0.15),
            Map.entry(ModAttributes.MELEE_PRECISION, s -> s.perc * 0.15),
            Map.entry(ModAttributes.PROJECTILE_PRECISION, s -> s.perc * 0.15),

            Map.entry(ModAttributes.HASTE, s -> s.dex * 0.125),
            Map.entry(ModAttributes.NOCK_HASTE, s -> s.dex * 0.15),
            Map.entry(() -> Attributes.MOVEMENT_SPEED, s -> s.dex * 0.00012),
            Map.entry(() -> Attributes.ARMOR, s -> s.con * 0.15 + s.perc * 0.1 + s.fort * 0.2 + s.str * 0.1),
            Map.entry(() -> Attributes.ARMOR_TOUGHNESS, s -> s.fort * 0.15),
            Map.entry(ModAttributes.DEBUFF_RESIST, s -> s.con * 0.05 + s.wisdom * 0.125),
            Map.entry(() -> Attributes.KNOCKBACK_RESISTANCE, s -> s.fort * 0.002),

            Map.entry(ModAttributes.RESTORATION, s -> s.wisdom * 0.15),
            Map.entry(ModAttributes.AMPLIFICATION, s -> s.wisdom * 0.2),
            Map.entry(ModAttributes.REJUVENATION, s -> s.con * 0.125),

            Map.entry(ModAttributes.XP_GAIN, s -> s.insight * 0.0025),
            Map.entry(ModAttributes.MINING_SPEED, s -> s.insight * 0.0025),
            Map.entry(() -> ForgeMod.BLOCK_REACH.get(), s -> s.insight * 0.05)
    );

    @SubscribeEvent
    public static void onEquipmentChange(LivingEquipmentChangeEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.level().isClientSide()) return;
        handleItemStats(player, event.getFrom(), event.getSlot(), false);
        handleItemStats(player, event.getTo(), event.getSlot(), true);

        player.level().getServer().tell(new net.minecraft.server.TickTask(
                player.level().getServer().getTickCount() + 1,
                () -> {
                    if (player.isAlive()) {
                        refreshPerPointStats(player);
                        refreshMilestones(player);
                        recalculateDynamicBonuses(player);
                        applyCrossbowTag(player);
                    }
                }
        ));
    }

    @SubscribeEvent
    public static void onCurioChange(CurioChangeEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.level().isClientSide()) return;
        player.level().getServer().tell(new net.minecraft.server.TickTask(
                player.level().getServer().getTickCount() + 1,
                () -> {
                    if (player.isAlive()) {
                        refreshPerPointStats(player);
                        refreshMilestones(player);
                        recalculateDynamicBonuses(player);
                        applyCrossbowTag(player);
                    }
                }
        ));
    }

    public static void applyCrossbowTag(Player player) {
        // For draw speed mixin, put draw speed increase data to item stack

        ItemStack mainHand = player.getMainHandItem();
        String mainType = getItemType(mainHand);

        ItemStack offHand = player.getOffhandItem();
        String offType = getItemType(player.getOffhandItem());

        if ("crossbow".equals(mainType)) {
            double prevDrawSpeed = mainHand.getOrCreateTag().getDouble("drawSpeedIncrease");
            double scaledHaste = getScaledValue(player, ModAttributes.NOCK_HASTE.get(), ModAttributes.NOCK_HASTE_MULTIPLIER.get());
            if (prevDrawSpeed != scaledHaste) {
                mainHand.getOrCreateTag().putDouble("drawSpeedIncrease", scaledHaste);
            }
            mainHand.getOrCreateTag().putBoolean("adrenalineInjection", player.hasEffect(ModEffects.ADRENALINE_INJECTION_UP.get()));
        }
        if ("crossbow".equals(offType)) {
            double prevDrawSpeed = offHand.getOrCreateTag().getDouble("drawSpeedIncrease");
            double scaledHaste = getScaledValue(player, ModAttributes.NOCK_HASTE.get(), ModAttributes.NOCK_HASTE_MULTIPLIER.get());
            if (prevDrawSpeed != scaledHaste) {
                offHand.getOrCreateTag().putDouble("drawSpeedIncrease", scaledHaste);
            }
            if (player.hasEffect(ModEffects.ADRENALINE_INJECTION_UP.get())) {
                offHand.getOrCreateTag().putBoolean("adrenalineInjection", true);
            } else {
                offHand.getOrCreateTag().remove("adrenalineInjection");
            }
        }
    }

    public static double getScaledValue(Player player, Attribute ratingAttr, Attribute multiplierAttr) {
        double rating = player.getAttributeValue(ratingAttr);
        double multiplierValue = player.getAttributeValue(multiplierAttr);

        double effectiveRating = rating * multiplierValue;

        if (500 + effectiveRating == 0) return 0;

        return (500 * effectiveRating) / (500 + Math.abs(effectiveRating));
    }

    public void removeCrossbowTag(Player player) {
        // For draw speed mixin, remove draw speed tag

        ItemStack mainHand = player.getMainHandItem();
        String mainType = getItemType(mainHand);

        ItemStack offHand = player.getOffhandItem();
        String offType = getItemType(player.getOffhandItem());

        if ("crossbow".equals(mainType)) {mainHand.getOrCreateTag().remove("adrenalineInjection");}
        if ("crossbow".equals(offType)) {offHand.getOrCreateTag().remove("adrenalineInjection");}
    }

    public void addCrossbowTag(Player player) {
        // For draw speed mixin, remove draw speed tag

        ItemStack mainHand = player.getMainHandItem();
        String mainType = getItemType(mainHand);

        ItemStack offHand = player.getOffhandItem();
        String offType = getItemType(player.getOffhandItem());

        if ("crossbow".equals(mainType)) {mainHand.getOrCreateTag().putBoolean("adrenalineInjection", true);}
        if ("crossbow".equals(offType)) {offHand.getOrCreateTag().putBoolean("adrenalineInjection", true);}
    }

    public static void applyPercentModifier(Player player, Attribute attribute, double percent, UUID uuid) {
        AttributeInstance inst = player.getAttribute(attribute);
        if (inst == null) return;

        AttributeModifier existing = inst.getModifier(uuid);
        if (existing != null && existing.getAmount() == percent) return;

        if (existing != null) inst.removeModifier(uuid);
        if (percent != 0) {
            inst.addTransientModifier(new AttributeModifier(uuid, attribute.getDescriptionId() + "_percent", percent, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }
    }

    public static void applyPercentModifierAdditive(LivingEntity entity, Attribute attribute, double percent, UUID uuid) {
        AttributeInstance inst = entity.getAttribute(attribute);
        if (inst == null) return;

        AttributeModifier existing = inst.getModifier(uuid);
        if (existing != null && existing.getAmount() == percent) return;

        if (existing != null) inst.removeModifier(uuid);
        if (percent != 0) {
            inst.addTransientModifier(new AttributeModifier(uuid, attribute.getDescriptionId() + "_percent", percent, AttributeModifier.Operation.MULTIPLY_BASE));
        }
    }


    public static void applyModifier(LivingEntity entity, Attribute attribute, double value, UUID uuid) {
        AttributeInstance instance = entity.getAttribute(attribute);
        if (instance == null) return;

        AttributeModifier existing = instance.getModifier(uuid);

        if (existing != null && existing.getAmount() == value) return;

        if (existing != null) instance.removeModifier(uuid);
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

    public static boolean isDuplicateAccessory(Player player, ItemStack stack, String baseName) {
        Optional<ICuriosItemHandler> handlerOpt = CuriosApi.getCuriosHelper().getCuriosHandler(player).resolve();

        if (handlerOpt.isPresent()) {
            ICuriosItemHandler handler = handlerOpt.get();

            return handler.getCurios().values().stream().anyMatch(slotHandler -> {
                for (int i = 0; i < slotHandler.getSlots(); i++) {
                    ItemStack equipped = slotHandler.getStacks().getStackInSlot(i);

                    if (!equipped.isEmpty()) {
                        String path = ForgeRegistries.ITEMS.getKey(equipped.getItem()).getPath();

                        if (path.contains(baseName) && equipped != stack) {
                            return true;
                        }
                    }
                }
                return false;
            });
        }
        return false;
    }

    // Randomized mobs
//    @SubscribeEvent
//    public static void onMonsterSpawn(EntityJoinLevelEvent event) {
//        if (event.getLevel().isClientSide()) return;
//
//        if (event.getEntity() instanceof Enemy && event.getEntity() instanceof LivingEntity living) {
//            RandomSource random = living.getRandom();
//
//            double outgoingVal = Math.max(0.75, Math.min(1.5, 1.0 + (random.nextGaussian() * 0.15)));
//            AttributeInstance outgoing = living.getAttribute(ModAttributes.OUTGOING_DAMAGE_MULTIPLIER.get());
//            if (outgoing != null) outgoing.setBaseValue(outgoingVal);
//
//            double incomingVal = Math.max(0.75, Math.min(1.5, 1.0 + (random.nextGaussian() * 0.15)));
//            AttributeInstance incoming = living.getAttribute(ModAttributes.INCOMING_DAMAGE_MULTIPLIER.get());
//            if (incoming != null) incoming.setBaseValue(incomingVal);
//        }
//    }
}