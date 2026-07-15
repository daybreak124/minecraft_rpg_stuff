package net.cold.coldsmod.menu_accessory;

import net.cold.coldsmod.ModMessages;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class AccessoryScreen extends AbstractContainerScreen<AccessoryMenu> {

    private static final int SPACING_X = 12;
    private static final int ROW_H = 18;
    private static final int Y_START_TOP = 32;
    private static final int Y_START_BOTTOM = 157;

    private static final ResourceLocation TEXTURE = new ResourceLocation("coldsmod", "textures/gui/ab_background.png");
    private final java.util.Map<Item, ItemStack> stackCache = new java.util.HashMap<>();
    private ItemStack hoveredStack = ItemStack.EMPTY;
    private boolean needsCacheRefresh = false;

    public AccessoryScreen(AccessoryMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 240; // Keeping your specific dimensions
        this.imageHeight = 240;
    }

    public void refreshFromPacket() {
        this.needsCacheRefresh = true;
    }

    @Override
    protected void init() {
        super.init();
        this.clearWidgets();

        AccessoryRegistry.MAP.values().forEach(entry -> {
            stackCache.putIfAbsent(entry.item(), new ItemStack(entry.item()));
        });

        // Setup UI Rows/Categories
        setupCategoryButtons("head", 10, Y_START_TOP);
        setupCategoryButtons("necklace", 65, Y_START_TOP);
        setupCategoryButtons("bracelet", 120, Y_START_TOP);
        setupCategoryButtons("ring", 175, Y_START_TOP);
        setupCategoryButtons("utility", 10, Y_START_BOTTOM);

        this.addRenderableWidget(Button.builder(Component.literal("Inv"), b -> {
            this.minecraft.setScreen(new net.minecraft.client.gui.screens.inventory.InventoryScreen(this.minecraft.player));
        }).pos(leftPos + imageWidth - 42, topPos + imageHeight - 22).size(35, 9).build());
    }

    private void setupCategoryButtons(String cat, int xOffset, int y) {
        int current = AccessoryUpgradeHandler.getCountInCategory(minecraft.player, cat);
        int max = AccessoryUpgradeHandler.getMaxForCategory(cat);
        boolean isFull = current >= max;

        int col = 0;
        int currentY = y + 10;

        for (var entry : AccessoryRegistry.MAP.entrySet()) {
            if (entry.getValue().category().equals(cat)) {
                String id = entry.getKey();
                int drawX = xOffset + (col * SPACING_X);

                boolean active = AccessoryUpgradeHandler.isActive(minecraft.player, id);
                boolean hasItem = minecraft.player.getInventory().countItem(entry.getValue().item()) > 0;
                boolean anotherVersionActive = AccessoryUpgradeHandler.isAnyVersionActive(minecraft.player, id) && !active;

                // --- MINUS BUTTON ---
                Button btnMinus = Button.builder(Component.literal(""), b -> {
                            ModMessages.sendToServer(new AccessoryPacket(id, false));
                        })
                        .pos(leftPos + drawX - 2, topPos + currentY + 9)
                        .size(6, 5)
                        .build();
                btnMinus.active = active;
                addRenderableWidget(btnMinus);

                // --- PLUS BUTTON ---
                Button btnPlus = Button.builder(Component.literal(""), b -> {
                            ModMessages.sendToServer(new AccessoryPacket(id, true));
                        })
                        .pos(leftPos + drawX + 4, topPos + currentY + 9)
                        .size(6, 5)
                        .build();
                btnPlus.active = !active && hasItem && !isFull && !anotherVersionActive;
                addRenderableWidget(btnPlus);

                col++;
                if (col >= 4) { col = 0; currentY += ROW_H; }
            }
        }
    }

    @Override
    protected void renderLabels(GuiGraphics g, int mx, int my) {
        String title = "Accessories";
        int titleWidth = this.font.width(title);
        int centerX = this.imageWidth / 2;
        g.drawString(this.font, title, centerX - (titleWidth / 2), 14, 0xFFAA00, true);

        renderCategory(g, "head", 10, Y_START_TOP, mx, my);
        renderCategory(g, "necklace", 65, Y_START_TOP, mx, my);
        renderCategory(g, "bracelet", 120, Y_START_TOP, mx, my);
        renderCategory(g, "ring", 175, Y_START_TOP, mx, my);
        renderCategory(g, "utility", 10, Y_START_BOTTOM, mx, my);
    }

    private void renderCategory(GuiGraphics g, String cat, int x, int y, int mx, int my) {
        int current = AccessoryUpgradeHandler.getCountInCategory(minecraft.player, cat);
        int max = AccessoryUpgradeHandler.getMaxForCategory(cat);
        boolean isFull = current >= max;

        g.pose().pushPose();
        g.pose().translate(x, y, 0);
        g.pose().scale(0.5f, 0.5f, 1f);
        g.drawString(font, cat.toUpperCase() + " (" + current + "/" + max + ")", 0, 0, isFull ? 0xFF6600 : 0x8B4513, false);
        g.pose().popPose();

        int col = 0;
        int currentY = y + 10;

        for (var entry : AccessoryRegistry.MAP.entrySet()) {
            if (entry.getValue().category().equals(cat)) {
                String id = entry.getKey();
                int dx = x + (col * SPACING_X);

                boolean active = AccessoryUpgradeHandler.isActive(minecraft.player, id);
                boolean versionActive = AccessoryUpgradeHandler.isAnyVersionActive(minecraft.player, id);
                boolean anotherVersionActive = versionActive && !active;
                boolean hasItem = minecraft.player.getInventory().countItem(entry.getValue().item()) > 0;

                // Color Logic
                int bgColor = active ? 0xAA006600 : ((anotherVersionActive || isFull) ? 0xAA660000 : (hasItem ? 0xAAFFFF00 : 0xAA333333));
                int plusColor = (!active && hasItem && !isFull && !anotherVersionActive) ? 0xFFFFFF : 0x555555;
                int minusColor = active ? 0xFFFFFF : 0x555555;

                g.fill(dx, currentY, dx + 8, currentY + 8, bgColor);
                renderItemIcon(g, entry.getValue().item(), dx, currentY, 0.5f);

                g.pose().pushPose();
                g.pose().translate(dx, currentY, 200);
                g.pose().scale(0.5f, 0.5f, 1f);
                g.drawString(font, "-", -1, 19, minusColor, false);
                g.drawString(font, "+", 11, 19, plusColor, false);
                g.pose().popPose();

                if (mx >= leftPos + dx && mx < leftPos + dx + 8 && my >= topPos + currentY && my < topPos + currentY + 8) {
                    this.hoveredStack = stackCache.getOrDefault(entry.getValue().item(), ItemStack.EMPTY);
                }

                col++;
                if (col >= 4) { col = 0; currentY += ROW_H; }
            }
        }
    }

    private void renderItemIcon(GuiGraphics g, Item item, int x, int y, float scale) {
        var model = minecraft.getItemRenderer().getItemModelShaper().getItemModel(new ItemStack(item));
        var sprite = model.getParticleIcon();
        g.pose().pushPose();
        g.pose().translate(x, y, 150);
        g.pose().scale(scale, scale, 1.0f);
        g.blit(0, 0, 0, 16, 16, sprite);
        g.pose().popPose();
    }

    @Override
    public void render(GuiGraphics g, int mx, int my, float pt) {
        this.hoveredStack = ItemStack.EMPTY;
        this.renderBackground(g);
        super.render(g, mx, my, pt);

        if (!hoveredStack.isEmpty()) {
            g.renderTooltip(this.font, hoveredStack, mx, my);
        }

        if (needsCacheRefresh) {
            this.init(minecraft, width, height);
            needsCacheRefresh = false;
        }
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float pt, int mx, int my) {
        this.renderBackground(graphics);
        int x = leftPos - 6;
        int y = topPos - 6;
        int width = imageWidth + 12;
        int height = imageHeight + 12;
        graphics.blit(TEXTURE, x, y, width, height, 0, 0, 1248, 913, 1248, 913);
    }
}