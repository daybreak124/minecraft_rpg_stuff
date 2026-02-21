package net.cold.coldsmod.menu_stat;

import net.cold.coldsmod.ModMessages;
import net.cold.coldsmod.accessory.UtilityAccessories;
import net.cold.coldsmod.stat.ModAttributes;
import net.cold.coldsmod.stat.StatUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.ForgeMod;

public class StatScreenThree extends AbstractContainerScreen<StatMenu> {

    private static final int START_Y = 30;  // Where the first row starts
    private static final int SPACING = 13;  // Distance between rows

    public StatScreenThree(StatMenu menu, Inventory inv, Component title) {
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
        addStatRow(ForgeMod.STEP_HEIGHT_ADDITION.get(), START_Y);
        addStatRow(ForgeMod.BLOCK_REACH.get(), START_Y + SPACING);
        addStatRow(ModAttributes.MINING_SPEED.get(), START_Y + (SPACING * 2));
        addStatRow(ModAttributes.XP_GAIN.get(), START_Y + (SPACING * 3));

        int buttonY = imageHeight - 18; // Near the bottom edge
        int groupRightEdge = imageWidth - 5;
        int btnW = 32; // Slightly smaller to fit 3 buttons on the 176-wide screen
        int gap = 2;

        int nextX = groupRightEdge - btnW;
        int invX = nextX - btnW - gap;
        int backX = invX - btnW - gap;

        this.addRenderableWidget(Button.builder(Component.literal("Back"), b ->
                        this.minecraft.setScreen(new StatScreenTwo(menu, this.minecraft.player.getInventory(), title)))
                .pos(leftPos + backX, topPos + buttonY).size(btnW, 12).build());

        // 2. Inv Button (Player Inventory)
        this.addRenderableWidget(Button.builder(Component.literal("Inv"), b ->
                        this.minecraft.setScreen(new net.minecraft.client.gui.screens.inventory.InventoryScreen(this.minecraft.player)))
                .pos(leftPos + invX, topPos + buttonY).size(btnW, 12).build());

        // 3. Next Button (Loop back to Screen One)
        this.addRenderableWidget(Button.builder(Component.literal("Next"), b ->
                        this.minecraft.setScreen(new StatScreen(menu, this.minecraft.player.getInventory(), title)))
                .pos(leftPos + nextX, topPos + buttonY).size(btnW, 12).build());
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        // 1. Title
        String title = "Utility Stats";
        int titleWidth = this.font.width(title);
        int centerX = this.imageWidth / 2;
        graphics.drawString(this.font, title, centerX - (titleWidth / 2), START_Y - 12, 0xD6C97A, true);

        // 2. Render Rows
        renderStatLine(graphics, "Step Height", ForgeMod.STEP_HEIGHT_ADDITION.get(), START_Y, UtilityAccessories.CLOUDTREADER_BOOTS.get());
        renderStatLine(graphics, "Block Reach", ForgeMod.BLOCK_REACH.get(), START_Y + SPACING, UtilityAccessories.ENDERMAN_FINGERS.get());
        renderStatLine(graphics, "Mining Speed", ModAttributes.MINING_SPEED.get(), START_Y + (SPACING * 2), Items.IRON_PICKAXE);
        renderStatLine(graphics, "XP Gain", ModAttributes.XP_GAIN.get(), START_Y + (SPACING * 3), Items.EXPERIENCE_BOTTLE);

        // 3. New Bottom Info call
        renderBottomInfo(graphics);
    }

    private void renderBottomInfo(GuiGraphics graphics) {
        int x = 8;
        int costY = this.imageHeight - 18;
        int pointsY = costY - 14; // Stacks Points exactly above Cost

        // 1. Points Spent
        int spent = StatUpgradeHandlerThree.getTotalPointsSpent(this.minecraft.player);
        String pointsText = "Points: " + spent + "/" + StatUpgradeHandlerThree.MAX_GLOBAL_POINTS;
        graphics.drawString(this.font, pointsText, x, pointsY, 0x55FF55, true);

        // 2. Cost Calculation
        net.minecraft.world.item.Item requiredItem = StatUpgradeHandlerThree.getRequiredScrap(spent);
        int amountNeeded = StatUpgradeHandler.getRequiredAmount(spent);
        if (amountNeeded == 3) amountNeeded = 4; // Force it to 4

        boolean hasEnough = this.minecraft.player.getInventory().countItem(requiredItem) >= amountNeeded;
        int textColor = hasEnough ? 0xFFFFFF : 0xFF5555;

        // 3. Draw Cost
        String costText = "Cost: x" + amountNeeded;
        graphics.drawString(this.font, costText, x, costY, textColor, true);

        // 4. Draw Scrap Icon
        int itemXOffset = x + 46 + (amountNeeded > 9 ? 6 : 0);
        graphics.renderFakeItem(new ItemStack(requiredItem), itemXOffset - 5, costY - 4);
    }

    private void renderStatLine(GuiGraphics graphics, String name, net.minecraft.world.entity.ai.attributes.Attribute attr, int y, net.minecraft.world.item.Item icon) {
        int currentVal = StatUpgradeHandlerThree.getPointsSpent(this.minecraft.player, attr);
        int maxVal = StatUpgradeHandlerThree.getMaxPointsFor(attr);
        double inc = StatUpgradeHandlerThree.getIncrementFor(attr);

        double displayInc = inc;
        if (attr == ModAttributes.XP_GAIN.get()) displayInc *= 100;
        if (attr == ModAttributes.MINING_SPEED.get()) displayInc *= 100;

        String incStr = "(+" + StatUtils.formatValue(displayInc) + ")";
        String pointsStr = "(" + currentVal + "/" + maxVal + ")";

        // 1. Icon Rendering (Isolated Stack)
        graphics.pose().pushPose();
        // Using floating point translate for smoother sub-pixel placement
        graphics.pose().translate(28.0f, (float)y + 3.0f, 0.0f);
        graphics.pose().scale(0.6f, 0.6f, 0.6f);
        graphics.renderFakeItem(new net.minecraft.world.item.ItemStack(icon), 0, 0);
        graphics.pose().popPose();

        // 2. Vectoral Text Scaling (Isolated Stack)
        graphics.pose().pushPose();
        float scale = 0.8f;
        float invScale = 1.0f / scale; // Calculate inverse for coordinate correction

        graphics.pose().scale(scale, scale, scale);

        // Coordinate Math: Multiply original screen position by inverse scale
        // This ensures the text "thinks" it is drawing at a higher resolution
        int textX = Math.round(42.0f * invScale);
        int textY = Math.round(((float)y + 4.5f) * invScale);
        int rightEdgeX = Math.round(((float)this.imageWidth - 25.0f) * invScale);

        // Draw Name
        graphics.drawString(this.font, name, textX, textY, 0xFFFFFF, true);

        // Draw Increment (Green) - Aligned to Right
        int incWidth = this.font.width(incStr);
        int incX = rightEdgeX - incWidth;
        graphics.drawString(this.font, incStr, incX, textY, 0x55FF55, true);

        // Draw Points - Aligned to the left of the Increment
        int pointsWidth = this.font.width(pointsStr);
        int pointsX = incX - pointsWidth - 2;
        int color = currentVal >= maxVal ? 0xFFAA00 : 0xFFFFFF;
        graphics.drawString(this.font, pointsStr, pointsX, textY, color, true);

        graphics.pose().popPose();
    }

    private void addStatRow(net.minecraft.world.entity.ai.attributes.Attribute attr, int yOffset) {
        String id = net.minecraftforge.registries.ForgeRegistries.ATTRIBUTES.getKey(attr).toString();

        // Minus Button - Far Left
        this.addRenderableWidget(Button.builder(Component.literal("-"), b ->
                        ModMessages.sendToServer(new StatUpgradePacketThree(id, false)))
                .pos(leftPos + 10, topPos + yOffset + 4).size(12, 10).build());

        // Plus Button - Far Right
        this.addRenderableWidget(Button.builder(Component.literal("+"), b ->
                        ModMessages.sendToServer(new StatUpgradePacketThree(id, true)))
                .pos(leftPos + imageWidth - 22, topPos + yOffset + 4).size(12, 10).build());
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        graphics.fill(leftPos, topPos, leftPos + imageWidth, topPos + imageHeight, 0xFF111111);
        graphics.fill(leftPos + 2, topPos + 2, leftPos + imageWidth - 2, topPos + imageHeight - 2, 0xFF222222);
    }
}