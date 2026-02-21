package net.cold.coldsmod.menu_blessing;

import net.cold.coldsmod.ModMessages;
import net.cold.coldsmod.item.ModItems;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BlessingScreen extends AbstractContainerScreen<BlessingMenu> {

    private static final int SPACING = 20;
    private static final int ROW_GAP = 10;

    public BlessingScreen(BlessingMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 190;
        this.imageHeight = 240;
    }

    @Override
    protected void init() {
        super.init();
        this.clearWidgets();

        int y = 19;
        y = addCategoryButtons("combat", 10, y, 160);
        int row2Y = y + ROW_GAP;
        addCategoryButtons("presence", 10, row2Y, 80);
        y = addCategoryButtons("utility", 95, row2Y, 80);
        int row3Y = y + ROW_GAP;
        addCategoryButtons("sword", 10, row3Y, 80);
        y = addCategoryButtons("shield", 95, row3Y, 80);
        int row4Y = y + ROW_GAP;
        addCategoryButtons("bow", 10, row4Y, 80);
        addCategoryButtons("crossbow", 95, row4Y, 80);
    }

    private int addCategoryButtons(String cat, int xOffset, int y, int widthLimit) {
        int current = BlessingUpgradeHandler.getCountInCategory(minecraft.player, cat);
        int max = BlessingUpgradeHandler.getMaxForCategory(cat);
        boolean isFull = current >= max;

        int startY = y + 10;
        int col = 0;
        int currentY = startY;

        for (var entry : BlessingRegistry.MAP.entrySet()) {
            if (entry.getValue().category().equals(cat)) {
                String id = entry.getKey();
                int drawX = xOffset + (col * SPACING);

                boolean active = BlessingUpgradeHandler.isActive(minecraft.player, id);
                boolean hasItem = minecraft.player.getInventory().countItem(entry.getValue().item()) > 0;

                // --- ACTUAL MINUS BUTTON WIDGET ---
                Button btnMinus = Button.builder(Component.literal(""), b -> ModMessages.sendToServer(new BlessingPacket(id, false)))
                        .pos(leftPos + drawX - 3, topPos + currentY + 13)
                        .size(9, 7) // Your 8x7 size
                        .build();
                btnMinus.active = active;
                addRenderableWidget(btnMinus);

                // --- ACTUAL PLUS BUTTON WIDGET ---
                Button btnPlus = Button.builder(Component.literal(""), b -> ModMessages.sendToServer(new BlessingPacket(id, true)))
                        .pos(leftPos + drawX + 7, topPos + currentY + 13)
                        .size(9, 7) // Your 8x7 size
                        .build();
                btnPlus.active = !active && hasItem && !isFull;
                addRenderableWidget(btnPlus);

                col++;
                if ((col + 1) * SPACING > widthLimit) {
                    col = 0;
                    currentY += SPACING + 2;
                }
            }
        }
        return (col == 0) ? currentY : currentY + SPACING + 2;
    }

    @Override
    protected void renderLabels(GuiGraphics g, int mx, int my) {
        int y = 19;
        y = renderCategory(g, "combat", 10, y, 160, mx, my);
        int row2Y = y + ROW_GAP;
        renderCategory(g, "presence", 10, row2Y, 80, mx, my);
        y = renderCategory(g, "utility", 95, row2Y, 80, mx, my);
        int row3Y = y + ROW_GAP;
        renderCategory(g, "sword", 10, row3Y, 80, mx, my);
        y = renderCategory(g, "shield", 95, row3Y, 80, mx, my);
        int row4Y = y + ROW_GAP;
        renderCategory(g, "bow", 10, row4Y, 80, mx, my);
        renderCategory(g, "crossbow", 95, row4Y, 80, mx, my);
    }

    private int renderCategory(GuiGraphics g, String cat, int x, int y, int widthLimit, int mx, int my) {
        int current = BlessingUpgradeHandler.getCountInCategory(minecraft.player, cat);
        int max = BlessingUpgradeHandler.getMaxForCategory(cat);
        boolean isFull = (current >= max);

        g.pose().pushPose();
        g.pose().translate(x, y, 0);
        g.pose().scale(0.7f, 0.7f, 1f);

        // Header Text: "Category (0/4)"
        String headerText = cat.substring(0, 1).toUpperCase() + cat.substring(1) + " (" + current + "/" + max + ")";

        // COLOR CHANGE: Gold (0xFFAA00) usually, Orange (0xFF6600) when full
        int headerColor = isFull ? 0xFF6600 : 0xFFAA00;

        g.drawString(font, headerText, 0, 0, headerColor, false);
        g.pose().popPose();

        int startY = y + 10;
        int col = 0;
        int currentY = startY;

        for (var entry : BlessingRegistry.MAP.entrySet()) {
            if (entry.getValue().category().equals(cat)) {
                String id = entry.getKey();
                int drawX = x + (col * SPACING);

                boolean active = BlessingUpgradeHandler.isActive(minecraft.player, id);
                boolean hasItem = minecraft.player.getInventory().countItem(entry.getValue().item()) > 0;

                renderBlessingIcon(g, entry.getValue(), drawX, currentY, mx, my, hasItem, active, isFull);

                // +/- Text Colors
                int mColor = active ? 0xFFFFFF : 0x555555;
                int pColor = (!active && hasItem && !isFull) ? 0xFFFFFF : 0x555555;

                g.drawString(font, "-", drawX - 1, currentY + 13, mColor, false);
                g.drawString(font, "+", drawX + 9, currentY + 13, pColor, false);

                col++;
                if ((col + 1) * SPACING > widthLimit) {
                    col = 0;
                    currentY += SPACING + 2;
                }
            }
        }
        return (col == 0) ? currentY : currentY + SPACING + 2;
    }

    private void renderBlessingIcon(GuiGraphics g, BlessingRegistry.BlessingEntry data, int x, int y, int mx, int my, boolean hasItem, boolean active, boolean isFull) {
        ItemStack stack = new ItemStack(data.item());
        g.pose().pushPose();
        g.pose().translate(x, y, 0);
        g.pose().scale(0.8f, 0.8f, 0.8f);

        // --- NEW ICON BACKGROUND LOGIC ---
        if (active) {
            g.fill(0, 0, 16, 16, 0xAA006600); // Green (Always first priority)
        } else if (isFull) {
            g.fill(0, 0, 16, 16, 0xAA660000); // Red (Maxed category, can't add more)
        } else if (hasItem) {
            g.fill(0, 0, 16, 16, 0xAAFFFF00); // Yellow (In inventory & space available)
        } else {
            g.fill(0, 0, 16, 16, 0xAA333333); // Dark Gray (Missing item)
        }

        g.renderFakeItem(stack, 0, 0);
        g.pose().popPose();

        if (mx >= leftPos + x && mx < leftPos + x + 13 && my >= topPos + y && my < topPos + y + 13) {
            g.renderTooltip(this.font, stack, mx - leftPos, my - topPos);
        }
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float pt, int mx, int my) {
        graphics.fill(leftPos, topPos, leftPos + imageWidth, topPos + imageHeight, 0xFF111111);
        graphics.fill(leftPos + 2, topPos + 2, leftPos + imageWidth - 2, topPos + imageHeight - 2, 0xFF222222);

//        graphics.fill(leftPos, topPos, leftPos + imageWidth, topPos + imageHeight, 0xFF000000);
//
//        // 2. Draw the "Inner Box" (Standard Grey)
//        // 0xFFC6C6C6 is the standard Minecraft light grey
//        graphics.fill(leftPos + 2, topPos + 2, leftPos + imageWidth - 2, topPos + imageHeight - 2, 0xFFA0A0A0);
//
//        // 3. Draw a "Shadow" edge for depth (Optional)
//        graphics.fill(leftPos + 2, topPos + imageHeight - 4, leftPos + imageWidth - 2, topPos + imageHeight - 2, 0xFFC6C6C6);
//        graphics.fill(leftPos + imageWidth - 4, topPos + 2, leftPos + imageWidth - 2, topPos + imageHeight - 2, 0xFF555555);

    }

    public void refresh() {
        this.init(this.minecraft, this.width, this.height);
    }



    private static final ResourceLocation VANILLA_TABS = new ResourceLocation("minecraft", "textures/gui/container/creative_inventory/tabs.png");
    @SubscribeEvent
    public static void onScreenInit(ScreenEvent.Init.Post event) {
        if (event.getScreen() instanceof InventoryScreen inv) {
            event.addListener(new ImageButton(
                    inv.getGuiLeft() + 124,
                    inv.getGuiTop() - 25,
                    25, 28,
                    1, 0, 32,
                    VANILLA_TABS,
                    256, 256,
                    b -> ModMessages.sendToServer(new BlessingMenuPacket())
            ) {
                @Override
                public void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
                    super.renderWidget(graphics, mouseX, mouseY, partialTick);
                    graphics.renderFakeItem(new ItemStack(ModItems.ORB_ICON.get()), getX() + 4, getY() + 8);
                }
            });
        }
    }
}