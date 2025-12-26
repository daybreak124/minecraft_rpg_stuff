package net.cold.coldsmod.item;

import net.minecraft.world.item.Item;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class ItemHelper extends Item {
    private final String displayName;

    public ItemHelper(Properties properties, String displayName) {
        super(properties);
        this.displayName = displayName;
    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.literal(displayName);
    }
}
