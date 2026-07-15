package net.cold.coldsmod.menu_stat;

import net.cold.coldsmod.ModMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class FeatMenu extends AbstractContainerMenu {

    public FeatMenu(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, FriendlyByteBuf.class.cast(null));
    }

    public FeatMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        super(ModMenu.FEAT_MENU.get(), containerId);
        // layoutPlayerInventorySlots(playerInventory, 8, 1000);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return true; // Keeps the menu open
    }

//    private void layoutPlayerInventorySlots(Inventory inv, int x, int y) {
//        // Standard code to add the 3x9 inventory and 1x9 hotbar slots
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 9; j++) {
//                this.addSlot(new Slot(inv, j + i * 9 + 9, x + j * 18, y + i * 18));
//            }
//        }
//        for (int i = 0; i < 9; i++) {
//            this.addSlot(new Slot(inv, i, x + i * 18, y + 142));
//        }
//    }
}