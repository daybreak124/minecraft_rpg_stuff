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

import static net.cold.coldsmod.stat.AttributeApplier.applyModifier;

public class StatUpgradeHandlerThree {
    public static final int MAX_GLOBAL_POINTS = 160;
    public static final UUID UTIL_STAT_MODIFIER_UUID = UUID.fromString("6a1224-c01d-5374-a711-3257c0de");

    public static double getIncrementFor(Attribute attr) {
        if (attr == ForgeMod.STEP_HEIGHT_ADDITION.get()) return 0.025;
        if (attr == ForgeMod.BLOCK_REACH.get()) return 0.05;
        if (attr == ModAttributes.MINING_SPEED.get()) return 0.01;
        if (attr == ModAttributes.XP_GAIN.get()) return 0.01;
        return 0.1;
    }

    public static int getMaxPointsFor(Attribute attr) {
        if (attr == ForgeMod.STEP_HEIGHT_ADDITION.get()) return 40;
        if (attr == ForgeMod.BLOCK_REACH.get()) return 40;
        if (attr == ModAttributes.MINING_SPEED.get()) return 40;
        if (attr == ModAttributes.XP_GAIN.get()) return 40;

        return 30;
    }

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

    public static int getRequiredAmount(int level) {
        if (level < 10) return 1;
        if (level < 30) return 2;
        if (level < 55) return 3;
        if (level < 85) return 5;
        if (level < 120) return 8;
        return 12;
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

        ItemStack stackToReturn = new ItemStack(pearlToReturn, amountToReturn);
        player.getInventory().add(stackToReturn);
        if (!player.getInventory().add(stackToReturn)) {
            player.drop(stackToReturn, false);
        }

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