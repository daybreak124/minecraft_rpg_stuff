package net.cold.coldsmod.stat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class InventoryStatsTabScreen extends Screen {
    private final InventoryScreen parent;
    private final Minecraft mc = Minecraft.getInstance();

    public InventoryStatsTabScreen(InventoryScreen parent) {
        super(Component.literal("Player Stats"));
        this.parent = parent;
    }

    private int tickCounter = 0;

    @Override
    protected void init() {
        super.init();

        addRenderableWidget(
                Button.builder(Component.literal("X"), btn -> mc.setScreen(parent))
                        .pos(this.width / 2 + 80, this.height / 2 - 90)
                        .size(20, 20)
                        .build()
        );
    }

    @Override
    public void tick() {
        super.tick();

        if (mc.player != null) {
            tickCounter++;
            if (tickCounter >= 20) {
                new AttributeApplier().recalcStats(mc.player);
                tickCounter = 0;
            }
        }
    }
}