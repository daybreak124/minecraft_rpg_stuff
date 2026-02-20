package net.cold.coldsmod.stat;

import net.cold.coldsmod.ModMessages;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

public class StatScreenTwo extends AbstractContainerScreen<StatMenu> {
    private static final int START_Y = 25;
    private static final int SPACING = 11;
    private static final int COL_B = 152;

    public StatScreenTwo(StatMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 300;
        this.imageHeight = 210;
        this.titleLabelX = -1000;
        this.inventoryLabelX = -1000;
    }

    @Override
    protected void init() {
        super.init();

        // --- LEFT COLUMN ---
        int xL = 8; int y = START_Y;
        addStatRow(Attributes.ARMOR, xL, y); y += SPACING;
        addStatRow(Attributes.ARMOR_TOUGHNESS, xL, y); y += SPACING;
        addStatRow(Attributes.MAX_HEALTH, xL, y); y += SPACING;
        addStatRow(Attributes.KNOCKBACK_RESISTANCE, xL, y); y += SPACING;
        addStatRow(ModAttributes.DEBUFF_RESIST.get(), xL, y);

        y = START_Y + 75;
        addStatRow(ModAttributes.MELEE_POTENCY.get(), xL, y); y += SPACING;
        addStatRow(ModAttributes.HASTE.get(), xL, y); y += SPACING;
        addStatRow(ModAttributes.MELEE_ACCURACY.get(), xL, y); y += SPACING;
        addStatRow(ModAttributes.MELEE_PRECISION.get(), xL, y);

        // --- RIGHT COLUMN ---
        int xR = COL_B + 8; y = START_Y;
        addStatRow(ModAttributes.POTENCY.get(), xR, y); y += SPACING;
        addStatRow(ModAttributes.ACCURACY.get(), xR, y); y += SPACING;
        addStatRow(ModAttributes.PRECISION.get(), xR, y); y += SPACING;
        addStatRow(Attributes.MOVEMENT_SPEED, xR, y);

        y = START_Y + 75;
        addStatRow(ModAttributes.PROJECTILE_POTENCY.get(), xR, y); y += SPACING;
        addStatRow(ModAttributes.NOCK_HASTE.get(), xR, y); y += SPACING;
        addStatRow(ModAttributes.PROJECTILE_ACCURACY.get(), xR, y); y += SPACING;
        addStatRow(ModAttributes.PROJECTILE_PRECISION.get(), xR, y);

        // --- HEALING ---
        int xH = (imageWidth / 2) - 68; int yH = 155;
        addStatRow(ModAttributes.REJUVENATION.get(), xH, yH); yH += SPACING;
        addStatRow(ModAttributes.RESTORATION.get(), xH, yH); yH += SPACING;
        addStatRow(ModAttributes.AMPLIFICATION.get(), xH, yH);

        this.addRenderableWidget(Button.builder(Component.literal("Back"), b ->
                        this.minecraft.setScreen(new StatScreen(menu, this.minecraft.player.getInventory(), title)))
                .pos(leftPos + imageWidth - 35, topPos + imageHeight - 15).size(30, 10).build());
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        int totalSpent = StatUpgradeHandlerTwo.getTotalPointsSpent(this.minecraft.player);
        String ptsTxt = "Points: " + totalSpent + "/120";

        graphics.pose().pushPose();
        graphics.pose().scale(0.8f, 0.8f, 0.8f);
        int ptsX = (int)((imageWidth - 10) / 0.8f) - this.font.width(ptsTxt);
        graphics.drawString(this.font, ptsTxt, ptsX, 8, 0x55FF55, true);
        graphics.pose().popPose();

        renderHeader(graphics, "Survivability", 75, START_Y - 6, 0xFF5555FF);
        renderHeader(graphics, "General", COL_B + 75, START_Y - 6, 0xFFE0701B);
        renderHeader(graphics, "Melee", 75, START_Y + 69, 0xFFE0701B);
        renderHeader(graphics, "Projectile", COL_B + 75, START_Y + 69, 0xFFE0701B);
        renderHeader(graphics, "Healing", (imageWidth / 2), 147, 0xFF5BB450);

        // --- LEFT COLUMN RENDER ---
        int y = START_Y;
        renderStatLine(graphics, "Armor", Attributes.ARMOR, y, Items.IRON_CHESTPLATE, 0); y += SPACING;
        renderStatLine(graphics, "Toughness", Attributes.ARMOR_TOUGHNESS, y, Items.DIAMOND_CHESTPLATE, 0); y += SPACING;
        renderStatLine(graphics, "Health", Attributes.MAX_HEALTH, y, Items.IRON_HELMET, 0); y += SPACING;
        renderStatLine(graphics, "KB. Res", Attributes.KNOCKBACK_RESISTANCE, y, Items.NETHERITE_LEGGINGS, 0); y += SPACING;
        renderStatLine(graphics, "Debuff Res", ModAttributes.DEBUFF_RESIST.get(), y, Items.MILK_BUCKET, 0);

        y = START_Y + 75;
        renderStatLine(graphics, "Potency", ModAttributes.MELEE_POTENCY.get(), y, Items.IRON_SWORD, 0); y += SPACING;
        renderStatLine(graphics, "Haste", ModAttributes.HASTE.get(), y, Items.SUGAR, 0); y += SPACING;
        renderStatLine(graphics, "Accuracy", ModAttributes.MELEE_ACCURACY.get(), y, Items.FLINT, 0); y += SPACING;
        renderStatLine(graphics, "Precision", ModAttributes.MELEE_PRECISION.get(), y, Items.GOLDEN_SWORD, 0);

        // --- RIGHT COLUMN RENDER ---
        y = START_Y;
        renderStatLine(graphics, "Potency", ModAttributes.POTENCY.get(), y, Items.BLAZE_POWDER, COL_B); y += SPACING;
        renderStatLine(graphics, "Accuracy", ModAttributes.ACCURACY.get(), y, Items.SPYGLASS, COL_B); y += SPACING;
        renderStatLine(graphics, "Precision", ModAttributes.PRECISION.get(), y, Items.TARGET, COL_B); y += SPACING;
        renderStatLine(graphics, "Speed", Attributes.MOVEMENT_SPEED, y, Items.IRON_BOOTS, COL_B);


        y = START_Y + 75;
        renderStatLine(graphics, "Potency", ModAttributes.PROJECTILE_POTENCY.get(), y, Items.ARROW, COL_B); y += SPACING;
        renderStatLine(graphics, "Nock Haste", ModAttributes.NOCK_HASTE.get(), y, Items.STRING, COL_B); y += SPACING;
        renderStatLine(graphics, "Accuracy", ModAttributes.PROJECTILE_ACCURACY.get(), y, Items.FEATHER, COL_B); y += SPACING;
        renderStatLine(graphics, "Precision", ModAttributes.PROJECTILE_PRECISION.get(), y, Items.SPECTRAL_ARROW, COL_B);

        // --- HEALING RENDER ---
        int xH_Off = (imageWidth / 2) - 75;
        renderStatLine(graphics, "Rejuvenation", ModAttributes.REJUVENATION.get(), 155, Items.GOLDEN_APPLE, xH_Off);
        renderStatLine(graphics, "Restoration", ModAttributes.RESTORATION.get(), 155 + SPACING, Items.BONE_MEAL, xH_Off);
        renderStatLine(graphics, "Amplification", ModAttributes.AMPLIFICATION.get(), 155 + (SPACING * 2), Items.POTION, xH_Off);

        // --- NEW COST LOGIC ---
        int amountNeeded = StatUpgradeHandler.getRequiredAmount(totalSpent);
        Item pearl = StatUpgradeHandlerTwo.getRequiredPearl(totalSpent);
        ItemStack pearlStack = new ItemStack(pearl);

        // Check inventory for the color toggle
        boolean hasEnough = this.minecraft.player.getInventory().countItem(pearl) >= amountNeeded;
        int textColor = hasEnough ? 0xFFFFFF : 0xFF5555; // White if enough, Red if not

        // DRAW TEXT: "Cost: x4" (or x8, x12, etc.)
        String costText = "Cost: x" + amountNeeded;
        graphics.drawString(this.font, costText, 6, imageHeight - 16, textColor, true);

        // DRAW ITEM: Positioned relative to the text width
        // amountNeeded > 9 check ensures the item doesn't overlap when the cost is x12 or higher
        int itemX = 50 + (amountNeeded > 9 ? 6 : 0);
        graphics.renderFakeItem(pearlStack, itemX, imageHeight - 20);

        // DRAW PEARL NAME (Optional: Tiny label next to the item)
        graphics.pose().pushPose();
        graphics.pose().translate(itemX + 20, imageHeight - 15, 0);
        graphics.pose().scale(0.7f, 0.7f, 0.7f);
        graphics.pose().popPose();
    }

    private void renderStatLine(GuiGraphics g, String name, Attribute attr, int y, Item icon, int xOffset) {
        int pts = StatUpgradeHandlerTwo.getPointsSpent(this.minecraft.player, attr);
        int max = StatUpgradeHandlerTwo.getMaxPointsFor(attr);
        double inc = StatUpgradeHandlerTwo.getIncrementFor(attr);

        double displayInc = inc;
        if (attr == Attributes.KNOCKBACK_RESISTANCE) displayInc *= 100;
        if (attr == Attributes.MOVEMENT_SPEED) displayInc *= 1000;

        String incStr = "(+" + StatUtils.formatValue(displayInc) + ")";
        // Separate points from the name
        String pointsStr = pts + "/" + max;

        // 1. Icon - Fixed Position
        g.pose().pushPose();
        g.pose().translate(xOffset + 20, y + 1, 0);
        g.pose().scale(0.5f, 0.5f, 0.5f);
        g.renderFakeItem(new ItemStack(icon), 0, 0);
        g.pose().popPose();

        g.pose().pushPose();
        g.pose().scale(0.7f, 0.7f, 0.7f);

        // 2. Name - Fixed Column Alignment
        int textX = (int)((xOffset + 32) / 0.7f);
        g.drawString(this.font, name, textX, (int)((y + 3) / 0.7f), 0xFFFFFF, true);

        // 3. Increment (+x) - Fixed Position (Using your 120)
        int incWidth = this.font.width(incStr);
        int incX = (int)((xOffset + 120) / 0.7f) - incWidth;
        g.drawString(this.font, incStr, incX, (int)((y + 3) / 0.7f), 0xFFFFFF, true);

        // 4. Points/Max - Placed to the left of incX
        int ptsWidth = this.font.width(pointsStr);
        int ptsX = incX - ptsWidth - 3; // 3 pixel gap between points and (+x)
        int labelColor = pts >= max ? 0xFFAA00 : 0xFFFFFF;
        g.drawString(this.font, pointsStr, ptsX, (int)((y + 3) / 0.7f), labelColor, true);

        g.pose().popPose();
    }

    private void addStatRow(Attribute attr, int xOffset, int y) {
        String id = ForgeRegistries.ATTRIBUTES.getKey(attr).toString();
        // Minus Button - UNCHANGED
        this.addRenderableWidget(Button.builder(Component.literal("-"), b ->
                        ModMessages.sendToServer(new StatUpgradePacketTwo(id, false)))
                .pos(leftPos + xOffset, topPos + y + 2).size(10, 8).build());

        // Plus Button - UNCHANGED (Fixed 115px distance)
        this.addRenderableWidget(Button.builder(Component.literal("+"), b ->
                        ModMessages.sendToServer(new StatUpgradePacketTwo(id, true)))
                .pos(leftPos + xOffset + 115, topPos + y + 2).size(10, 8).build());
    }

    private void renderHeader(GuiGraphics g, String text, int x, int y, int color) {
        g.pose().pushPose();
        g.pose().scale(0.8f, 0.8f, 0.8f);
        g.drawString(this.font, text, (int)(x / 0.8f) - (this.font.width(text) / 2), (int)(y / 0.8f), color, true);
        g.pose().popPose();
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float pt, int mx, int my) {
        graphics.fill(leftPos, topPos, leftPos + imageWidth, topPos + imageHeight, 0xFF000000);
        graphics.fill(leftPos + 2, topPos + 2, leftPos + imageWidth - 2, topPos + imageHeight - 2, 0xFFA0A0A0);
        graphics.fill(leftPos + 2, topPos + imageHeight - 4, leftPos + imageWidth - 2, topPos + imageHeight - 2, 0xFFC6C6C6);
        graphics.fill(leftPos + imageWidth - 4, topPos + 2, leftPos + imageWidth - 2, topPos + imageHeight - 2, 0xFF555555);
    }
}