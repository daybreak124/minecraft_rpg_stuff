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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccessoryScreen extends AbstractContainerScreen<AccessoryMenu> {

    private static final int SPACING_X = 12;
    private static final int ROW_H = 18;

    private static final int Y_START_TOP = 32;
    private static final int Y_START_BOTTOM = 157;
    private boolean needsCacheRefresh = false;
    private ItemStack hoveredStack = ItemStack.EMPTY;

    private static final ResourceLocation TEXTURE = new ResourceLocation("coldsmod", "textures/gui/ab_background.png");

    private final java.util.Map<net.minecraft.world.item.Item, ItemStack> stackCache = new java.util.HashMap<>();
    private final Map<String, List<Map.Entry<String, AccessoryRegistry.AccessoryEntry>>> categoryCache = new java.util.HashMap<>();
    private final Map<Item, Boolean> hasItemCache = new HashMap<>();
    private final Map<String, Boolean> activeCache = new HashMap<>();
    private final Map<String, Boolean> versionCache = new HashMap<>();
    private final Map<String, Integer> categoryCountCache = new HashMap<>();

    public AccessoryScreen(AccessoryMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 240;
        this.imageHeight = 240;
    }

    private void updateInventoryCache() {
        hasItemCache.clear();

        var inv = minecraft.player.getInventory();

        // Build owned item set once
        java.util.HashSet<Item> owned = new java.util.HashSet<>();
        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack s = inv.getItem(i);
            if (!s.isEmpty()) {
                owned.add(s.getItem());
            }
        }

        for (Item item : stackCache.keySet()) {
            hasItemCache.put(item, owned.contains(item));
        }
    }

    private void updateStateCache() {
        activeCache.clear();
        versionCache.clear();
        categoryCountCache.clear();

        for (var e : AccessoryRegistry.MAP.entrySet()) {
            String id = e.getKey();
            activeCache.put(id, AccessoryUpgradeHandler.isActive(minecraft.player, id));
            versionCache.put(id, AccessoryUpgradeHandler.isAnyVersionActive(minecraft.player, id));
        }

        for (String cat : categoryCache.keySet()) {
            categoryCountCache.put(cat, AccessoryUpgradeHandler.getCountInCategory(minecraft.player, cat));
        }
    }

    @Override
    protected void init() {
        super.init();
        this.clearWidgets();

        AccessoryRegistry.MAP.values().forEach(entry -> {
            stackCache.putIfAbsent(entry.item(), new ItemStack(entry.item()));
        });

        categoryCache.clear();
        for (var e : AccessoryRegistry.MAP.entrySet()) {
            categoryCache.computeIfAbsent(e.getValue().category(), k -> new ArrayList<>()).add(e);
        }

        updateInventoryCache();
        updateStateCache();

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
        int current = categoryCountCache.getOrDefault(cat, 0);
        int max = AccessoryUpgradeHandler.getMaxForCategory(cat);
        boolean isFull = current >= max;

        int col = 0;
        int currentY = y + 10;

        for (var entry : categoryCache.getOrDefault(cat, List.of())) {
            String id = entry.getKey();
            int drawX = xOffset + (col * SPACING_X);

            boolean active = activeCache.getOrDefault(id, false);
            boolean hasItem = hasItemCache.getOrDefault(entry.getValue().item(), false);
            boolean anotherVersionActive = AccessoryUpgradeHandler.isAnyVersionActive(minecraft.player, id) && !active;

            // Minus
            addRenderableWidget(Button.builder(Component.empty(), b -> {
                ModMessages.sendToServer(new AccessoryPacket(id, false));
                needsCacheRefresh = true;}).pos(leftPos + drawX - 2, topPos + currentY + 9).size(6, 5).build()).active = active;

            // Plus
            addRenderableWidget(Button.builder(Component.empty(), b -> {
                ModMessages.sendToServer(new AccessoryPacket(id, true));
                needsCacheRefresh = true;}).pos(leftPos + drawX + 4, topPos + currentY + 9).size(6, 5).build()).active = !active && hasItem && !isFull && !anotherVersionActive;

            col++;
            if (col >= 4) { col = 0; currentY += ROW_H; }
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
        int current = categoryCountCache.getOrDefault(cat, 0);
        int max = AccessoryUpgradeHandler.getMaxForCategory(cat);
        boolean isFull = current >= max;

        // Header (Scale 0.5)
        g.pose().pushPose();
        g.pose().translate(x, y, 0);
        g.pose().scale(0.5f, 0.5f, 1f);
        g.drawString(font, cat.toUpperCase() + " (" + current + "/" + max + ")", 0, 0, isFull ? 0xFF6600 : 0x8B4513, false);
        g.pose().popPose();

        int col = 0;
        int currentY = y + 10;

        for (var entry : categoryCache.getOrDefault(cat, List.of())) {
            String id = entry.getKey();
            int dx = x + (col * SPACING_X);

            boolean active = activeCache.getOrDefault(id, false);
            boolean versionActive = versionCache.getOrDefault(id, false);
            boolean anotherVersionActive = versionActive && !active;
            boolean hasItem = hasItemCache.getOrDefault(entry.getValue().item(), false);
            ItemStack stack = stackCache.get(entry.getValue().item());

            int bgColor = active ? 0xAA006600 : ((anotherVersionActive || isFull) ? 0xAA660000 : (hasItem ? 0xAAFFFF00 : 0xAA333333));
            int plusColor = (!active && hasItem && !isFull && !anotherVersionActive) ? 0xFFFFFF : 0x555555;
            int minusColor = active ? 0xFFFFFF : 0x555555;

            g.fill(dx, currentY, dx + 8, currentY + 8, bgColor);

            renderItemIcon(g, entry.getValue().item(), dx, currentY, 0.5f);

            g.pose().pushPose();
            g.pose().translate(dx, currentY, 200);
            g.pose().scale(0.5f, 0.5f, 1f);
            g.drawString(font, "-", -1, 19, minusColor, false); // 18 pixels down at 0.5 scale = 9 pixels
            g.drawString(font, "+", 11, 19, plusColor, false);
            g.pose().popPose();

            if (mx >= leftPos + dx && mx < leftPos + dx + 8 && my >= topPos + currentY && my < topPos + currentY + 8) {
                this.hoveredStack = stack;
            }

            col++;
            if (col >= 4) { col = 0; currentY += ROW_H; }
        }
    }

    private void renderItemIcon(GuiGraphics g, Item item, int x, int y, float scale) {
        var model = minecraft.getItemRenderer().getItemModelShaper().getItemModel(new ItemStack(item));
        var sprite = model.getParticleIcon();

        if (sprite != null) {
            g.pose().pushPose();
            g.pose().translate(x, y, 150);
            g.pose().scale(scale, scale, 1.0f);
            g.blit(0, 0, 0, 16, 16, sprite);
            g.pose().popPose();
        }
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
            updateInventoryCache();
            updateStateCache();
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
        graphics.blit(TEXTURE, x, y, 0, 0, width, height, width, height);
    }
}