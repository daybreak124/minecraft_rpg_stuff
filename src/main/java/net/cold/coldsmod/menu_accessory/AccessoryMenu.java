package net.cold.coldsmod.menu_accessory;

import net.cold.coldsmod.ModMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class AccessoryMenu extends AbstractContainerMenu {

    public AccessoryMenu(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, FriendlyByteBuf.class.cast(null));
    }

    public AccessoryMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        super(ModMenu.ACCESSORY_MENU.get(), containerId);
        layoutPlayerInventorySlots(playerInventory, 8, 1000);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    private void layoutPlayerInventorySlots(Inventory inv, int x, int y) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(inv, j + i * 9 + 9, x + j * 18, y + i * 18));
            }
        }
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(inv, i, x + i * 18, y + 142));
        }
    }
}