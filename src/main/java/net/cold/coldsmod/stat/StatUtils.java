package net.cold.coldsmod.stat;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.Map;

public class StatUtils {

    public static String formatValue(double value) {
        if (Math.abs(value - Math.round(value)) < 0.01) {
            return String.format("%d", (int)Math.round(value));
        }

        int decimals = 0;
        double temp = value;

        while (decimals < 2 && Math.floor(temp) != temp) {
            temp *= 10;
            decimals++;
        }

        switch (decimals) {
            case 0: return String.format("%.0f", value);
            case 1: return String.format("%.1f", value);
            // case 3: return String.format("%.3f", value);
            default: return String.format("%.2f", value);
        }
    }
}
