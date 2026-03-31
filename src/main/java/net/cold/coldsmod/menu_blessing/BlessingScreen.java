package net.cold.coldsmod.menu_blessing;

import net.cold.coldsmod.ModMessages;
import net.cold.coldsmod.accessory.UtilityAccessories;
import net.cold.coldsmod.item.ModItems;
import net.cold.coldsmod.menu_accessory.AccessoryMenuPacket;
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

    private static final ResourceLocation TEXTURE = new ResourceLocation("coldsmod", "textures/gui/ab_background.png");
    private final java.util.Map<net.minecraft.world.item.Item, ItemStack> stackCache = new java.util.HashMap<>();
    private ItemStack hoveredStack = ItemStack.EMPTY;

    public BlessingScreen(BlessingMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 190;
        this.imageHeight = 240;
    }

    @Override
    protected void init() {
        super.init();
        this.clearWidgets();

        BlessingRegistry.MAP.values().forEach(entry -> {
            stackCache.putIfAbsent(entry.item(), new ItemStack(entry.item()));
        });

        int y = 29;
        // Row 1
        y = addCategoryButtons("combat", 10, y, 160);

        // Row 2
        y += ROW_GAP; // Add gap after Row 1
        addCategoryButtons("sword", 10, y, 80);
        y = addCategoryButtons("shield", 99, y, 80);

        // Row 3
        y += ROW_GAP; // Add gap after Row 2
        addCategoryButtons("bow", 10, y, 80);
        y = addCategoryButtons("crossbow", 99, y, 80);

        // Row 4
        y += ROW_GAP; // Add gap after Row 3
        addCategoryButtons("presence", 10, y, 80);


        this.addRenderableWidget(Button.builder(Component.literal("Inv"), b -> {
            this.minecraft.setScreen(new net.minecraft.client.gui.screens.inventory.InventoryScreen(this.minecraft.player));
        }).pos(leftPos + imageWidth - 42, topPos + imageHeight - 22).size(35, 9).build());
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
                boolean canRemove = BlessingEffectRegistry.CAN_REMOVE.getOrDefault(entry.getValue().item(), p -> true).test(minecraft.player);

                // --- ACTUAL MINUS BUTTON WIDGET ---
                Button btnMinus = Button.builder(Component.literal(""), b -> ModMessages.sendToServer(new BlessingPacket(id, false)))
                        .pos(leftPos + drawX - 3, topPos + currentY + 13)
                        .size(9, 7)
                        .build();
                btnMinus.active = active && canRemove;
                addRenderableWidget(btnMinus);

                // --- ACTUAL PLUS BUTTON WIDGET ---
                Button btnPlus = Button.builder(Component.literal(""), b -> ModMessages.sendToServer(new BlessingPacket(id, true)))
                        .pos(leftPos + drawX + 7, topPos + currentY + 13)
                        .size(9, 7)
                        .build();
                btnPlus.active = !active && hasItem && !isFull;
                addRenderableWidget(btnPlus);

                col++;
                if ((col + 1) * SPACING > widthLimit) {
                    col = 0;
                    currentY += SPACING + 5;
                }
            }
        }
        return (col == 0) ? currentY : currentY + SPACING + 2;
    }

    @Override
    protected void renderLabels(GuiGraphics g, int mx, int my) {
        String title = "Blessings";
        int titleWidth = this.font.width(title);
        int centerX = this.imageWidth / 2;
        g.drawString(this.font, title, centerX - (titleWidth / 2), 14, 0xFFAA00, true);

        int y = 27;

        // Row 1: Combat
        y = renderCategory(g, "combat", 10, y, 160, mx, my);

        // Row 2: Sword and Shield
        int row2Y = y + ROW_GAP;
        renderCategory(g, "sword", 10, row2Y, 80, mx, my);
        y = renderCategory(g, "shield", 99, row2Y, 80, mx, my);

        // Row 3: Bow and Crossbow
        int row3Y = y + ROW_GAP;
        renderCategory(g, "bow", 10, row3Y, 80, mx, my);
        y = renderCategory(g, "crossbow", 99, row3Y, 80, mx, my);

        // Row 4: Presence
        int row4Y = y + ROW_GAP;
        renderCategory(g, "presence", 10, row4Y, 80, mx, my);
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

        int headerColor = isFull ? 0xFF6600 : 0x8B4513;

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
                boolean canRemove = BlessingEffectRegistry.CAN_REMOVE.getOrDefault(entry.getValue().item(), p -> true).test(minecraft.player);


                renderBlessingIcon(g, entry.getValue(), drawX, currentY, mx, my, hasItem, active, isFull);

                // +/- Text Colors
                int mColor = (active && canRemove) ? 0xFFFFFF : 0x555555;
                int pColor = (!active && hasItem && !isFull) ? 0xFFFFFF : 0x555555;

                g.drawString(font, "-", drawX - 1, currentY + 15, mColor, false);
                g.drawString(font, "+", drawX + 9, currentY + 15, pColor, false);

                col++;
                if ((col + 1) * SPACING > widthLimit) {
                    col = 0;
                    currentY += SPACING + 5;
                }
            }
        }
        return (col == 0) ? currentY : currentY + SPACING + 2;
    }


    private void renderBlessingIcon(GuiGraphics g, BlessingRegistry.BlessingEntry data, int x, int y, int mx, int my, boolean hasItem, boolean active, boolean isFull) {
        // 1. Determine Background Color
        int bgColor = active ? 0xAA006600 : (isFull ? 0xAA660000 : (hasItem ? 0xAAFFFF00 : 0xAA333333));

        // 2. Draw Background (using screen-space coordinates)
        // 0.8f scale of 16x16 is ~13x13
        g.fill(x, y, x + 13, y + 13, bgColor);

        // 3. Draw the Icon via Sprite Blitting
        renderItemIcon(g, data.item(), x, y, 0.8f);

        // 4. Set tooltip target (Check against 13x13 area)
        if (mx >= leftPos + x && mx < leftPos + x + 13 && my >= topPos + y && my < topPos + y + 13) {
            this.hoveredStack = stackCache.getOrDefault(data.item(), ItemStack.EMPTY);
        }
    }

    private void renderItemIcon(GuiGraphics g, net.minecraft.world.item.Item item, int x, int y, float scale) {
        var model = minecraft.getItemRenderer().getItemModelShaper().getItemModel(new ItemStack(item));
        var sprite = model.getParticleIcon();

        if (sprite != null) {
            g.pose().pushPose();
            g.pose().translate(x, y, 100);
            g.pose().scale(scale, scale, 1.0f);
            g.blit(0, 0, 0, 16, 16, sprite);
            g.pose().popPose();
        }
    }

    @Override
    public void render(GuiGraphics g, int mx, int my, float pt) {
        this.hoveredStack = ItemStack.EMPTY; // Reset
        this.renderBackground(g);
        super.render(g, mx, my, pt);

        // Draw tooltip ONCE at the very end
        if (!hoveredStack.isEmpty()) {
            g.renderTooltip(this.font, hoveredStack, mx, my);
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
                    b -> ModMessages.sendToServer(new BlessingMenuPacket()))
            {
                @Override
                public void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
                    super.renderWidget(graphics, mouseX, mouseY, partialTick);
                    graphics.renderFakeItem(new ItemStack(ModItems.ORB_ICON.get()), getX() + 4, getY() + 8);
                }
            });

            event.addListener(new ImageButton(
                    inv.getGuiLeft() + 97,
                    inv.getGuiTop() - 25,
                    25, 28,
                    1, 0, 32,
                    VANILLA_TABS,
                    256, 256,
                    b -> ModMessages.sendToServer(new AccessoryMenuPacket())
            ) {
                @Override
                public void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
                    super.renderWidget(graphics, mouseX, mouseY, partialTick);
                    graphics.renderFakeItem(new ItemStack(UtilityAccessories.MONIS_LUCKY_CHARM.get()), getX() + 4, getY() + 8);
                }
            });
        }
    }
}