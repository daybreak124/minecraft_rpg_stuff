package net.cold.coldsmod.menu_stat;

import net.cold.coldsmod.ModMessages;
import net.cold.coldsmod.item.ModItems;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.UUID;

import static net.cold.coldsmod.menu_stat.StatUpgradeHandler.getRequiredAmount;
import static net.cold.coldsmod.stat.AttributeApplier.*;

public class StatUpgradeHandlerTwo {
    public static final int MAX_GLOBAL_POINTS = 120;
    public static final UUID STAT_MODIFIER_UUID = UUID.fromString("6d6224-c01d-5374-a711-5557c0de");

    public static double getIncrementFor(Attribute attr) {
        if (attr == Attributes.ARMOR) return 0.6;
        if (attr == Attributes.ARMOR_TOUGHNESS) return 0.75;
        if (attr == Attributes.KNOCKBACK_RESISTANCE) return 0.05;
        if (attr == Attributes.MAX_HEALTH) return 0.3;
        if (attr == Attributes.MOVEMENT_SPEED) return 0.002;
        if (attr == ModAttributes.DEBUFF_RESIST.get()) return 4;

        if (attr == ModAttributes.POTENCY.get()) return 1.25;
        if (attr == ModAttributes.HASTE.get()) return 1.5;
        if (attr == ModAttributes.ACCURACY.get()) return 2;
        if (attr == ModAttributes.PRECISION.get()) return 1.75;

        if (attr == ModAttributes.MELEE_POTENCY.get()) return 1.75;
        if (attr == ModAttributes.MELEE_HASTE.get()) return 2.1;
        if (attr == ModAttributes.MELEE_ACCURACY.get()) return 2.75;
        if (attr == ModAttributes.MELEE_PRECISION.get()) return 2.45;

        if (attr == ModAttributes.PROJECTILE_POTENCY.get()) return 1.75;
        if (attr == ModAttributes.NOCK_HASTE.get()) return 2.1;
        if (attr == ModAttributes.PROJECTILE_ACCURACY.get()) return 2.75;
        if (attr == ModAttributes.PROJECTILE_PRECISION.get()) return 2.45;

        if (attr == ModAttributes.REJUVENATION.get()) return 2.0;
        if (attr == ModAttributes.RESTORATION.get()) return 1.75;
        if (attr == ModAttributes.AMPLIFICATION.get()) return 2.0;

        return 1.0;
    }

    public static int getMaxPointsFor(Attribute attr) {
        if (attr == Attributes.ARMOR) return 21;
        if (attr == Attributes.ARMOR_TOUGHNESS) return 16;
        if (attr == Attributes.KNOCKBACK_RESISTANCE) return 10;
        if (attr == Attributes.MAX_HEALTH) return 21;
        if (attr == Attributes.MOVEMENT_SPEED) return 15;
        if (attr == ModAttributes.DEBUFF_RESIST.get()) return 12;

        if (attr == ModAttributes.POTENCY.get()) return 8;
        if (attr == ModAttributes.HASTE.get()) return 6;
        if (attr == ModAttributes.ACCURACY.get()) return 12;
        if (attr == ModAttributes.PRECISION.get()) return 10;

        if (attr == ModAttributes.MELEE_POTENCY.get()) return 15;
        if (attr == ModAttributes.MELEE_HASTE.get()) return 12;
        if (attr == ModAttributes.MELEE_ACCURACY.get()) return 22;
        if (attr == ModAttributes.MELEE_PRECISION.get()) return 18;

        if (attr == ModAttributes.PROJECTILE_POTENCY.get()) return 15;
        if (attr == ModAttributes.NOCK_HASTE.get()) return 12;
        if (attr == ModAttributes.PROJECTILE_ACCURACY.get()) return 22;
        if (attr == ModAttributes.PROJECTILE_PRECISION.get()) return 18;

        if (attr == ModAttributes.REJUVENATION.get()) return 25;
        if (attr == ModAttributes.RESTORATION.get()) return 15;
        if (attr == ModAttributes.AMPLIFICATION.get()) return 20;

        return 20;
    }

    public static int getPointsSpent(Player player, Attribute attr) {
        String key = ForgeRegistries.ATTRIBUTES.getKey(attr).toString();
        CompoundTag data = player.getPersistentData().getCompound("SpentPoints");
        return data.getInt(key);
    }

    private static void setPointsSpent(Player player, Attribute attr, int points) {
        String key = ForgeRegistries.ATTRIBUTES.getKey(attr).toString();
        CompoundTag persistent = player.getPersistentData();
        if (!persistent.contains("SpentPoints")) {
            persistent.put("SpentPoints", new CompoundTag());
        }
        persistent.getCompound("SpentPoints").putInt(key, points);
    }

    public static int getTotalPointsSpent(Player player) {
        int total = 0;
        CompoundTag tag = player.getPersistentData().getCompound("SpentPoints");
        for (String key : tag.getAllKeys()) {
            total += tag.getInt(key);
        }
        return total;
    }

    public static Item getRequiredPearl(int level) {
        return ModItems.PEARL_OF_REVITALIZING.get();
    }

    public static void tryUpgrade(ServerPlayer player, Attribute attribute) {
        int points = getPointsSpent(player, attribute);
        if (points >= getMaxPointsFor(attribute)) return;

        int globalPoints = getTotalPointsSpent(player);
        if (globalPoints >= MAX_GLOBAL_POINTS) {
            player.sendSystemMessage(Component.literal("§cLimit reached."));
            return;
        }

        Item pearl = getRequiredPearl(globalPoints);
        int amountNeeded = getRequiredAmount(globalPoints);

        if (!hasAndRemoveItem(player, pearl, amountNeeded)) {
            player.sendSystemMessage(Component.literal("§cMissing items."));
            return;
        }

        int newPoints = points + 1;
        setPointsSpent(player, attribute, newPoints);

        double amount = newPoints * getIncrementFor(attribute);
        applyModifier(player, attribute, amount, STAT_MODIFIER_UUID);

        String attrId = ForgeRegistries.ATTRIBUTES.getKey(attribute).toString();
        ModMessages.sendToPlayer(new StatsSyncPacket(attrId, newPoints, false, false), player);

        if (attribute == ModAttributes.POTENCY.get()) {
            updateSubStat(player, ModAttributes.MELEE_POTENCY.get(), ModAttributes.POTENCY.get());
            updateSubStat(player, ModAttributes.PROJECTILE_POTENCY.get(), ModAttributes.POTENCY.get());
        }
        else if (attribute == ModAttributes.ACCURACY.get()) {
            updateSubStat(player, ModAttributes.MELEE_ACCURACY.get(), ModAttributes.ACCURACY.get());
            updateSubStat(player, ModAttributes.PROJECTILE_ACCURACY.get(), ModAttributes.ACCURACY.get());
        }
        else if (attribute == ModAttributes.PRECISION.get()) {
            updateSubStat(player, ModAttributes.MELEE_PRECISION.get(), ModAttributes.PRECISION.get());
            updateSubStat(player, ModAttributes.PROJECTILE_PRECISION.get(), ModAttributes.PRECISION.get());
        } else if (attribute == ModAttributes.HASTE.get()) {
            updateSubStat(player, ModAttributes.MELEE_HASTE.get(), ModAttributes.HASTE.get());
            updateSubStat(player, ModAttributes.NOCK_HASTE.get(), ModAttributes.HASTE.get());
        }

        refreshPerPointStats(player);
        refreshMilestones(player);
        recalculateDynamicBonuses(player);
        applyCrossbowTag(player);
    }

    public static void tryDowngrade(ServerPlayer player, Attribute attribute) {
        int points = getPointsSpent(player, attribute);
        if (points <= 0) return;

        int globalPoints = getTotalPointsSpent(player);
        Item pearlToReturn = getRequiredPearl(globalPoints - 1);
        int amountToReturn = getRequiredAmount(globalPoints - 1);

        ItemStack stackToReturn = new ItemStack(pearlToReturn, amountToReturn);
        player.getInventory().add(stackToReturn);
        if (!player.getInventory().add(stackToReturn)) {
            player.drop(stackToReturn, false);
        }

        int newPoints = points - 1;
        setPointsSpent(player, attribute, newPoints);

        double amount = newPoints * getIncrementFor(attribute);
        applyModifier(player, attribute, amount, STAT_MODIFIER_UUID);

        String attrId = ForgeRegistries.ATTRIBUTES.getKey(attribute).toString();
        ModMessages.sendToPlayer(new StatsSyncPacket(attrId, newPoints, false, false), player);

        if (attribute == ModAttributes.POTENCY.get()) {
            updateSubStat(player, ModAttributes.MELEE_POTENCY.get(), ModAttributes.POTENCY.get());
            updateSubStat(player, ModAttributes.PROJECTILE_POTENCY.get(), ModAttributes.POTENCY.get());
        }
        else if (attribute == ModAttributes.ACCURACY.get()) {
            updateSubStat(player, ModAttributes.MELEE_ACCURACY.get(), ModAttributes.ACCURACY.get());
            updateSubStat(player, ModAttributes.PROJECTILE_ACCURACY.get(), ModAttributes.ACCURACY.get());
        }
        else if (attribute == ModAttributes.PRECISION.get()) {
            updateSubStat(player, ModAttributes.MELEE_PRECISION.get(), ModAttributes.PRECISION.get());
            updateSubStat(player, ModAttributes.PROJECTILE_PRECISION.get(), ModAttributes.PRECISION.get());
        }

        refreshPerPointStats(player);
        refreshMilestones(player);
        recalculateDynamicBonuses(player);
        applyCrossbowTag(player);
    }

    private static void updateSubStat(ServerPlayer player, Attribute subAttr, Attribute baseAttr) {
        int subPoints = getPointsSpent(player, subAttr);
        int basePoints = getPointsSpent(player, baseAttr);

        double totalValue = (subPoints * getIncrementFor(subAttr)) + (basePoints * getIncrementFor(baseAttr));

        applyModifier(player, subAttr, totalValue, STAT_MODIFIER_UUID);
    }

    private static boolean hasAndRemoveItem(Player player, Item item, int count) {
        int found = 0;
        for (ItemStack s : player.getInventory().items) if (s.is(item)) found += s.getCount();
        if (found < count) return false;
        int toRemove = count;
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack s = player.getInventory().getItem(i);
            if (s.is(item)) {
                int take = Math.min(s.getCount(), toRemove);
                s.shrink(take);
                toRemove -= take;
                if (toRemove <= 0) break;
            }
        }
        return true;
    }
}