package net.cold.coldsmod.stat;

import net.cold.coldsmod.ModMessages;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Items;

import static net.cold.coldsmod.stat.StatUpgradeHandler.ATTRIBUTE_MAX_LEVEL;
import static net.cold.coldsmod.stat.StatUpgradeHandler.BASE_POINTS;

public class StatScreen extends AbstractContainerScreen<StatMenu> {

    private static final int START_Y = 44;  // Where the first row starts
    private static final int SPACING = 13;  // Distance between rows

    public StatScreen(StatMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 176;
        this.imageHeight = 180;

        this.titleLabelX = -1000;
        this.inventoryLabelX = -1000;
    }

    @Override
    protected void init() {
        super.init();
        // Line these up exactly with the renderLabels spacing
        addStatRow(ModAttributes.STR.get(), START_Y);
        addStatRow(ModAttributes.DEX.get(), START_Y + SPACING);
        addStatRow(ModAttributes.FORT.get(), START_Y + (SPACING * 2));
        addStatRow(ModAttributes.CON.get(), START_Y + (SPACING * 3));
        addStatRow(ModAttributes.PERC.get(), START_Y + (SPACING * 4));
        addStatRow(ModAttributes.WISDOM.get(), START_Y + (SPACING * 5));

        int buttonY = imageHeight - 22; // Near the bottom edge
        int rightEdge = imageWidth - 10; // Right side margin

        this.addRenderableWidget(Button.builder(Component.literal("Inv"), b -> {
            // Standard way to return to the player's inventory
            this.minecraft.setScreen(new net.minecraft.client.gui.screens.inventory.InventoryScreen(this.minecraft.player));
        }).pos(leftPos + rightEdge - 75, topPos + buttonY).size(35, 12).build());

        // 2. Next Page Button
        this.addRenderableWidget(Button.builder(Component.literal("Next"), b -> {
            // We use the player's inventory directly from the minecraft instance
            this.minecraft.setScreen(new StatScreenTwo(this.menu, this.minecraft.player.getInventory(), this.title));
        }).pos(leftPos + imageWidth - 42, topPos + imageHeight - 22).size(35, 12).build());
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        // 1. Blue Title (Unchanged)
        String title = "Attributes";
        int titleWidth = this.font.width(title);
        int centerX = this.imageWidth / 2;
        graphics.drawString(this.font, title, centerX - (titleWidth / 2), START_Y - 12, 0x00AAAA, true);

        // 2. Points Display - Shows "Spent/Total" in the top right
        int spent = StatUpgradeHandler.getTotalPointsSpent(this.minecraft.player);
        String pointsText = "Points: " + spent + "/" + BASE_POINTS;

        // Calculate X position so it's always flush against the right edge
        int pointsX = this.imageWidth - this.font.width(pointsText) - 10;
        graphics.drawString(this.font, pointsText, pointsX, 8, 0x55FF55, true);

        // 3. Render Rows (Rest of your existing code...)
        renderStatLine(graphics, "Strength", ModAttributes.STR.get(), START_Y, Items.IRON_SWORD);
        renderStatLine(graphics, "Dexterity", ModAttributes.DEX.get(), START_Y + SPACING, Items.BOW);
        renderStatLine(graphics, "Fortitude", ModAttributes.FORT.get(), START_Y + (SPACING * 2), Items.SHIELD);
        renderStatLine(graphics, "Constitution", ModAttributes.CON.get(), START_Y + (SPACING * 3), Items.GOLDEN_APPLE);
        renderStatLine(graphics, "Perception", ModAttributes.PERC.get(), START_Y + (SPACING * 4), Items.SPYGLASS);
        renderStatLine(graphics, "Wisdom", ModAttributes.WISDOM.get(), START_Y + (SPACING * 5), Items.ENCHANTED_BOOK);

        renderRequiredItem(graphics);
    }

    private void renderRequiredItem(GuiGraphics graphics) {
        int x = 8;
        int y = this.imageHeight - 20;

        int totalPoints = StatUpgradeHandler.getTotalPointsSpent(this.minecraft.player);

        // 1. Get the Shard (now always Ascendancy) and the specific amount (4, 8, 12, etc.)
        net.minecraft.world.item.Item requiredItem = StatUpgradeHandler.getRequiredShard(totalPoints);
        int amountNeeded = StatUpgradeHandler.getRequiredAmount(totalPoints);
        net.minecraft.world.item.ItemStack stack = new net.minecraft.world.item.ItemStack(requiredItem);

        // 2. Check if player has enough to color the text red/white
        boolean hasEnough = this.minecraft.player.getInventory().countItem(requiredItem) >= amountNeeded;
        int textColor = hasEnough ? 0xFFFFFF : 0xFF5555; // White if okay, Red if poor

        // 3. DRAW TEXT: "Cost: x4"
        String costText = "Cost: x" + amountNeeded;
        graphics.drawString(this.font, costText, x, y, textColor, true);

        // 4. DRAW ITEM: Shifted based on the width of the cost text
        // x + 35 + (extra space for 2-digit numbers)
        int itemXOffset = x + 46 + (amountNeeded > 9 ? 6 : 0);
        graphics.renderFakeItem(stack, itemXOffset, y - 4);

        // 5. DRAW ITEM NAME (Optional, removed the empty push/pop logic)
        graphics.pose().pushPose();
        graphics.pose().translate(itemXOffset + 20, y + 1, 0);
        graphics.pose().scale(0.7f, 0.7f, 0.7f);
        graphics.pose().popPose();
    }

    private void renderStatLine(GuiGraphics graphics, String name, net.minecraft.world.entity.ai.attributes.Attribute attr, int y, net.minecraft.world.item.Item icon) {
        int currentVal = StatUpgradeHandler.getPointsSpent(this.minecraft.player, attr);

        // Split the strings: Name for center, Points for the right side
        String nameStr = name;
        String pointsStr = "(" + currentVal + "/" + ATTRIBUTE_MAX_LEVEL + ")";

        // 1. Icon Alignment (Fixed column at X=32)
        int iconX = 32;
        graphics.pose().pushPose();
        graphics.pose().translate(iconX, y + 3, 0);
        graphics.pose().scale(0.6f, 0.6f, 0.6f);
        graphics.renderFakeItem(new net.minecraft.world.item.ItemStack(icon), 0, 0);
        graphics.pose().popPose();

        // 2. Centered Name
        int centerX = this.imageWidth / 2;
        int nameWidth = this.font.width(nameStr);
        graphics.drawString(this.font, nameStr, centerX - (nameWidth / 2), y + 4, 0xFFFFFF, true);

        // 3. Invested Amount - Flushed against the [+] button
        // Your [+] button is at imageWidth - 22.
        // We anchor the text right to that edge (at -24 for a small gap).
        int pointsWidth = this.font.width(pointsStr);
        int pointsX = (this.imageWidth - 24) - pointsWidth;

        int color = currentVal >= ATTRIBUTE_MAX_LEVEL ? 0xFFAA00 : 0xFFFFFF;
        graphics.drawString(this.font, pointsStr, pointsX, y + 4, color, true);
    }

    private void addStatRow(net.minecraft.world.entity.ai.attributes.Attribute attr, int yOffset) {
        String id = net.minecraftforge.registries.ForgeRegistries.ATTRIBUTES.getKey(attr).toString();

        // Minus Button - Far Left
        this.addRenderableWidget(Button.builder(Component.literal("-"), b ->
                        ModMessages.sendToServer(new StatUpgradePacket(id, false)))
                .pos(leftPos + 10, topPos + yOffset + 4).size(12, 10).build());

        // Plus Button - Far Right
        this.addRenderableWidget(Button.builder(Component.literal("+"), b ->
                        ModMessages.sendToServer(new StatUpgradePacket(id, true)))
                .pos(leftPos + imageWidth - 22, topPos + yOffset + 4).size(12, 10).build());
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        // 1. Draw the "Outer Border" (Darker)
        graphics.fill(leftPos, topPos, leftPos + imageWidth, topPos + imageHeight, 0xFF000000);

        // 2. Draw the "Inner Box" (Standard Grey)
        // 0xFFC6C6C6 is the standard Minecraft light grey
        graphics.fill(leftPos + 2, topPos + 2, leftPos + imageWidth - 2, topPos + imageHeight - 2, 0xFFA0A0A0);

        // 3. Draw a "Shadow" edge for depth (Optional)
        graphics.fill(leftPos + 2, topPos + imageHeight - 4, leftPos + imageWidth - 2, topPos + imageHeight - 2, 0xFFC6C6C6);
        graphics.fill(leftPos + imageWidth - 4, topPos + 2, leftPos + imageWidth - 2, topPos + imageHeight - 2, 0xFF555555);
    }
}