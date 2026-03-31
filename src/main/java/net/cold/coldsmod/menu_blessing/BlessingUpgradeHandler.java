package net.cold.coldsmod.menu_blessing;

import net.cold.coldsmod.ModMessages;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

public class BlessingUpgradeHandler {

    public static boolean isActive(Player player, String id) {
        return player.getPersistentData().getCompound("ActiveBlessings").getBoolean(id);
    }

    private static void setBlessingState(Player player, String id, boolean active) {
        CompoundTag persistent = player.getPersistentData();
        if (!persistent.contains("ActiveBlessings")) {
            persistent.put("ActiveBlessings", new CompoundTag());
        }
        persistent.getCompound("ActiveBlessings").putBoolean(id, active);
    }

    public static int getCountInCategory(Player player, String category) {
        int count = 0;
        CompoundTag blessings = player.getPersistentData().getCompound("ActiveBlessings");
        for (var entry : BlessingRegistry.MAP.entrySet()) {
            if (entry.getValue().category().equals(category)) {
                if (blessings.getBoolean(entry.getKey())) count++;
            }
        }
        return count;
    }

    public static int getMaxForCategory(String category) {
        return switch (category) {
            case "combat" -> 4;
            case "utility" -> 5;
            default -> 1;
        };
    }

    public static void tryUpgrade(ServerPlayer player, String id) {
        if (isActive(player, id)) return;
        var entry = BlessingRegistry.MAP.get(id);
        if (entry == null) return;

        String cat = entry.category();
        if (getCountInCategory(player, cat) >= getMaxForCategory(cat)) {
            player.sendSystemMessage(Component.literal("§cLimit reached. "));
            return;
        }

        if (hasAndRemoveItem(player, entry.item(), 1)) {
            setBlessingState(player, id, true);
            Consumer<Player> apply = BlessingEffectRegistry.ON_APPLY.get(entry.item());
            if (apply != null) apply.accept(player);

            ModMessages.sendToPlayer(new BlessingUnlockSyncPacket(player.getPersistentData()), player);
        }
    }

    public static void tryDowngrade(ServerPlayer player, String id) {
        if (!isActive(player, id)) return;
        var entry = BlessingRegistry.MAP.get(id);
        if (entry == null) return;

        setBlessingState(player, id, false);

        ItemStack stackToReturn = new ItemStack(entry.item(), 1);
        player.getInventory().add(stackToReturn);
        if (!player.getInventory().add(stackToReturn)) {
            player.drop(stackToReturn, false);
        }

        Consumer<Player> remove = BlessingEffectRegistry.ON_REMOVE.get(entry.item());
        if (remove != null) remove.accept(player);

        ModMessages.sendToPlayer(new BlessingUnlockSyncPacket(player.getPersistentData()), player);
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