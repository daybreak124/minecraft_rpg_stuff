package net.cold.coldsmod.menu_stat;

import net.cold.coldsmod.ModMessages;
import net.cold.coldsmod.item.ModItems;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.UUID;

import static net.cold.coldsmod.stat.AttributeApplier.*;
import static net.cold.coldsmod.menu_stat.StatUpgradeHandler.getRequiredAmount;

public class StatUpgradeHandlerThree {
    public static final int MAX_GLOBAL_POINTS = 120;
    public static final UUID UTIL_STAT_MODIFIER_UUID = UUID.fromString("6a1224-c01d-5374-a711-3257c0de");

    public static double getIncrementFor(Attribute attr) {
        if (attr == ForgeMod.STEP_HEIGHT_ADDITION.get()) return 0.03333;
        if (attr == ForgeMod.BLOCK_REACH.get()) return 0.1;
        if (attr == ModAttributes.MINING_SPEED.get()) return 0.015;
        if (attr == ModAttributes.XP_GAIN.get()) return 0.015;
        return 0.1;
    }

    public static int getMaxPointsFor(Attribute attr) {
//        if (attr == ForgeMod.STEP_HEIGHT_ADDITION.get()) return 30;
//        if (attr == ForgeMod.BLOCK_REACH.get()) return 30;
//        if (attr == ModAttributes.MINING_SPEED.get()) return 30;
//        if (attr == ModAttributes.XP_GAIN.get()) return 30;

        return 30;
    }

    // This reads the actual "Spent Points" integer from NBT
    public static int getPointsSpent(Player player, Attribute attr) {
        String key = ForgeRegistries.ATTRIBUTES.getKey(attr).toString();
        CompoundTag data = player.getPersistentData().getCompound("SpentPointsUtil");
        return data.getInt(key);
    }

    private static void setPointsSpent(Player player, Attribute attr, int points) {
        String key = ForgeRegistries.ATTRIBUTES.getKey(attr).toString();
        CompoundTag persistent = player.getPersistentData();
        if (!persistent.contains("SpentPointsUtil")) {
            persistent.put("SpentPointsUtil", new CompoundTag());
        }
        persistent.getCompound("SpentPointsUtil").putInt(key, points);
    }

    public static int getTotalPointsSpent(Player player) {
        int total = 0;
        CompoundTag tag = player.getPersistentData().getCompound("SpentPointsUtil");
        for (String key : tag.getAllKeys()) {
            total += tag.getInt(key);
        }
        return total;
    }

    public static Item getRequiredScrap(int level) {
        return ModItems.SCRAP_ESSENCE.get();
    }

    public static void tryUpgrade(ServerPlayer player, Attribute attribute) {
        int points = getPointsSpent(player, attribute);
        if (points >= getMaxPointsFor(attribute)) return;

        int globalPoints = getTotalPointsSpent(player);
        if (globalPoints >= MAX_GLOBAL_POINTS) {
            player.sendSystemMessage(Component.literal("§cLimit reached."));
            return;
        }

        Item pearl = getRequiredScrap(globalPoints);
        int amountNeeded = getRequiredAmount(globalPoints);

        if (!hasAndRemoveItem(player, pearl, amountNeeded)) {
            player.sendSystemMessage(Component.literal("§cMissing items."));
            return;
        }

        int newPoints = points + 1;
        setPointsSpent(player, attribute, newPoints);

        double amount = newPoints * getIncrementFor(attribute);
        applyModifier(player, attribute, amount, UTIL_STAT_MODIFIER_UUID);

        String attrId = ForgeRegistries.ATTRIBUTES.getKey(attribute).toString();
        ModMessages.sendToPlayer(new StatsSyncPacket(attrId, newPoints, false, true), player);
    }

    public static void tryDowngrade(ServerPlayer player, Attribute attribute) {
        int points = getPointsSpent(player, attribute);
        if (points <= 0) return;

        int globalPoints = getTotalPointsSpent(player);
        Item pearlToReturn = getRequiredScrap(globalPoints - 1);
        int amountToReturn = getRequiredAmount(globalPoints - 1);
        player.getInventory().add(new ItemStack(pearlToReturn, amountToReturn));

        int newPoints = points - 1;
        setPointsSpent(player, attribute, newPoints);

        double amount = newPoints * getIncrementFor(attribute);
        applyModifier(player, attribute, amount, UTIL_STAT_MODIFIER_UUID);

        String attrId = ForgeRegistries.ATTRIBUTES.getKey(attribute).toString();
        ModMessages.sendToPlayer(new StatsSyncPacket(attrId, newPoints, false, true), player);
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