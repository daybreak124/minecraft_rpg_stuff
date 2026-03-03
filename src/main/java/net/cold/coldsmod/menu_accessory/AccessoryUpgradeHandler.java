package net.cold.coldsmod.menu_accessory;

import net.cold.coldsmod.ModMessages;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

public class AccessoryUpgradeHandler {

    public static boolean isActive(Player player, String id) {
        return player.getPersistentData().getCompound("ActiveAccessories").getBoolean(id);
    }

    private static void setAccessoryState(Player player, String id, boolean active) {
        CompoundTag persistent = player.getPersistentData();
        if (!persistent.contains("ActiveAccessories")) {
            persistent.put("ActiveAccessories", new CompoundTag());
        }
        persistent.getCompound("ActiveAccessories").putBoolean(id, active);
    }

    public static int getCountInCategory(Player player, String category) {
        int count = 0;
        CompoundTag active = player.getPersistentData().getCompound("ActiveAccessories");
        for (var entry : AccessoryRegistry.MAP.entrySet()) {
            if (entry.getValue().category().equals(category)) {
                if (active.getBoolean(entry.getKey())) count++;
            }
        }
        return count;
    }

    public static int getMaxForCategory(String category) {
        return switch (category) {
            case "ring", "bracelet", "head" -> 2;
            case "utility" -> 10;
            default -> 1;
        };
    }

    // AccessoryUpgradeHandler.java

    public static String getBaseName(String id) {
        var entry = AccessoryRegistry.MAP.get(id);
        if (entry == null) return id;

        String path = net.minecraftforge.registries.ForgeRegistries.ITEMS.getKey(entry.item()).getPath();
        if (path.contains("_")) {
            return path.substring(0, path.lastIndexOf("_"));
        }
        return path;
    }

    public static boolean isAnyVersionActive(Player player, String id) {
        String targetBase = getBaseName(id);
        CompoundTag activeData = player.getPersistentData().getCompound("ActiveAccessories");

        for (String activeId : activeData.getAllKeys()) {
            if (activeData.getBoolean(activeId)) {
                if (getBaseName(activeId).equals(targetBase)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void tryUpgrade(ServerPlayer player, String id) {
        if (isActive(player, id)) return;
        var entry = AccessoryRegistry.MAP.get(id);
        if (entry == null) return;

        // USE THE UNIFIED CHECK HERE
        if (isAnyVersionActive(player, id)) {
            player.sendSystemMessage(Component.literal("§cAlready using a version of this item!"));
            return;
        }

        String cat = entry.category();
        if (getCountInCategory(player, cat) >= getMaxForCategory(cat)) {
            player.sendSystemMessage(Component.literal("§cLimit reached."));
            return;
        }

        if (hasAndRemoveItem(player, entry.item(), 1)) {
            setAccessoryState(player, id, true);
            Consumer<Player> apply = AccessoryStatRegistry.ON_APPLY_ACC.get(entry.item());
            if (apply != null) apply.accept(player);

            ModMessages.sendToPlayer(new AccessoryUnlockSyncPacket(player.getPersistentData()), player);

        }
    }

    public static void tryDowngrade(ServerPlayer player, String id) {
        if (!isActive(player, id)) return;
        var entry = AccessoryRegistry.MAP.get(id);
        if (entry == null) return;

        setAccessoryState(player, id, false);

        ItemStack stackToReturn = new ItemStack(entry.item(), 1);
        player.getInventory().add(stackToReturn);
        if (!player.getInventory().add(stackToReturn)) {
            player.drop(stackToReturn, false);
        }

        Consumer<Player> remove = AccessoryStatRegistry.ON_REMOVE_ACC.get(entry.item());
        if (remove != null) remove.accept(player);

        ModMessages.sendToPlayer(new AccessoryUnlockSyncPacket(player.getPersistentData()), player);

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