package net.cold.coldsmod.menu_stat;

import net.cold.coldsmod.ModMessages;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class FeatScreen extends AbstractContainerScreen<FeatMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("coldsmod", "textures/gui/ab_background.png");
    private int hoveredFeatId = -1;
    private ItemStack hoveredCostStack = ItemStack.EMPTY;
    private boolean needsCacheRefresh = false;

    private static final int INK_GOLD = 0xE6A800;
    private static final int INK_GREEN = 0x00CC00;
    private static final int INK_RED = 0xCC0000;

    public FeatScreen(FeatMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 250;
        this.imageHeight = 240;
    }

    public void refreshFromPacket() {
        this.needsCacheRefresh = true;
    }

    @Override
    protected void init() {
        super.init();
        this.clearWidgets();

        String[] trees = {"LION", "NIGHT", "STEEL", "HERALD"};
        int startY = 40;

        for (int i = 0; i < trees.length; i++) {
            int xOff = 12 + (i * 58);
            setupTreeWidgets(trees[i], xOff, startY);
        }

        this.addRenderableWidget(Button.builder(Component.literal("Inv"), b -> {
            this.minecraft.setScreen(new InventoryScreen(this.minecraft.player));
        }).pos(leftPos + imageWidth - 45, topPos + imageHeight - 22).size(35, 9).build());
    }

    private void setupTreeWidgets(String treeKey, int x, int y) {
        for (int tier = 6; tier >= 1; tier--) {
            int displayRow = 6 - tier;
            int currentY = y + (displayRow * 26);

            boolean hasSlots = FeatUpgradeHandlerRegistry.getTotalPointsSpent(minecraft.player) < 9;

            for (int slot = 1; slot <= 3; slot++) {
                int featId = FeatUpgradeHandlerRegistry.getFeatId(treeKey, tier, slot);
                if (featId == -1) continue;

                boolean active = FeatUpgradeHandlerRegistry.isActive(minecraft.player, treeKey, featId);
                boolean canUnlock = FeatUpgradeHandlerRegistry.canUnlock(minecraft.player, treeKey, tier);

                boolean canRemove = true;
                for (int hTier = tier + 1; hTier <= 6; hTier++) {
                    if (FeatUpgradeHandlerRegistry.isAnyFeatActiveInTier(minecraft.player, treeKey, hTier)) {
                        if (FeatUpgradeHandlerRegistry.getActiveCountInTier(minecraft.player, treeKey, tier) <= 1) {
                            canRemove = false;
                            break;
                        }
                    }
                }

                int drawX = x + ((slot - 1) * 16);

                // -
                this.addRenderableWidget(Button.builder(Component.literal(""), b -> {
                            ModMessages.sendToServer(new FeatUnlockPacket(treeKey, featId, false));
                            this.needsCacheRefresh = true;
                        })
                        .pos(leftPos + drawX - 1, topPos + currentY + 12).size(7, 6).build())
                        .active = active && canRemove;

                // +
                this.addRenderableWidget(Button.builder(Component.literal(""), b -> {
                            ModMessages.sendToServer(new FeatUnlockPacket(treeKey, featId, true));
                            this.needsCacheRefresh = true;
                        })
                        .pos(leftPos + drawX + 7, topPos + currentY + 12).size(7, 6).build())
                        .active = !active && canUnlock && hasSlots;
            }
        }
    }

    @Override
    public void render(GuiGraphics g, int mx, int my, float pt) {
        this.hoveredFeatId = -1;
        this.hoveredCostStack = ItemStack.EMPTY;

        this.renderBackground(g);

        super.render(g, mx, my, pt);

        if (this.hoveredFeatId != -1) {
            g.renderComponentTooltip(this.font, FeatUpgradeHandlerRegistry.getTooltip(this.hoveredFeatId), mx, my);
        } else if (!this.hoveredCostStack.isEmpty()) {
            g.renderTooltip(this.font, this.hoveredCostStack, mx, my);
        }

        if (needsCacheRefresh) {
            this.init(minecraft, width, height);
            needsCacheRefresh = false;
        }
    }

    @Override
    protected void renderBg(GuiGraphics g, float pt, int mx, int my) {
        int x = leftPos - 6;
        int y = topPos - 6;
        int width = imageWidth + 12;
        int height = imageHeight + 12;
        g.blit(TEXTURE, x, y, width, height, 0, 0, 1248, 913, 1248, 913);
    }

    @Override
    protected void renderLabels(GuiGraphics g, int mx, int my) {
        g.drawString(this.font, "Identities", (imageWidth / 2) - (font.width("Identities") / 2), 12, 0xFFAA00, true);

        String[] treeKeys = {"LION", "NIGHT", "STEEL", "HERALD"};
        String[] displayNames = {"LIONCRUSHER", "NIGHTSTALKER", "STEELHEART", "HERALD OF LIGHT"};

        for (int i = 0; i < treeKeys.length; i++) {
            int xOff = 12 + (i * 58);
            g.pose().pushPose();
            float titleScale = 0.5f;
            float scaledTextWidth = font.width(displayNames[i]) * titleScale;
            float centeredX = xOff + (58f / 2f) - (scaledTextWidth / 2f) - 4f;

            g.pose().translate(centeredX, 32, 100);
            g.pose().scale(titleScale, titleScale, 1f);
            g.drawString(font, displayNames[i], 1, 1, 0x222222, false);
            g.drawString(font, displayNames[i], 0, 0, 0xD4AF37, false);
            g.pose().popPose();

            g.fill((int)centeredX, 38, (int)centeredX + (int)scaledTextWidth, 39, 0x8B4513);
            renderTreeIcons(g, treeKeys[i], xOff, 40, mx, my);
        }
        renderBottomInfo(g, mx, my);
    }

    private void renderTreeIcons(GuiGraphics g, String tree, int x, int y, int mx, int my) {
        for (int tier = 6; tier >= 1; tier--) {
            int displayRow = 6 - tier;
            int currentY = y + (displayRow * 26);

            for (int slot = 1; slot <= 3; slot++) {
                int featId = FeatUpgradeHandlerRegistry.getFeatId(tree, tier, slot);
                if (featId == -1) continue;

                int drawX = x + ((slot - 1) * 16);
                boolean active = FeatUpgradeHandlerRegistry.isActive(minecraft.player, tree, featId);
                boolean canUnlock = FeatUpgradeHandlerRegistry.canUnlock(minecraft.player, tree, tier);

                boolean canRemove = active;
                if (active) {
                    for (int hTier = tier + 1; hTier <= 6; hTier++) {
                        if (FeatUpgradeHandlerRegistry.isAnyFeatActiveInTier(minecraft.player, tree, hTier)) {
                            if (FeatUpgradeHandlerRegistry.getActiveCountInTier(minecraft.player, tree, tier) <= 1) {
                                canRemove = false;
                                break;
                            }
                        }
                    }
                }

                int bgColor = active ? ((tier == 6) ? 0xAA800080 : 0xAA006600) : (canUnlock ? 0xAAFFFF00 : 0xAA333333);
                g.fill(drawX, currentY - 1, drawX + 13, currentY + 12, bgColor);
                renderItemIcon(g, FeatUpgradeHandlerRegistry.getIcon(featId), drawX + 1, currentY, 0.65f);

                int pColor = (FeatUpgradeHandlerRegistry.getTotalPointsSpent(this.minecraft.player) == FeatUpgradeHandlerRegistry.MAX_FEAT_POINTS) ? 0x555555 : ((!active && canUnlock) ? 0xFFFFFF : 0x555555);
                int mColor = canRemove ? 0xFFFFFF : 0x555555;

                g.pose().pushPose();
                g.pose().translate(0, 0, 150);
                g.pose().scale(0.7f, 0.7f, 1.0f);
                g.drawString(font, "-", (int)((drawX + 1) / 0.7f), (int)((currentY + 13) / 0.7f), mColor, false);
                g.drawString(font, "+", (int)((drawX + 9) / 0.7f), (int)((currentY + 13) / 0.7f), pColor, false);
                g.pose().popPose();

                if (mx >= leftPos + drawX && mx < leftPos + drawX + 13 && my >= topPos + currentY && my < topPos + currentY + 11) {
                    this.hoveredFeatId = featId;
                }
            }
        }
    }

    private void renderItemIcon(GuiGraphics g, Object icon, int x, int y, float scale) {
        g.pose().pushPose();
        g.pose().translate(x, y, 100);
        g.pose().scale(scale, scale, 1.0f);
        if (icon instanceof ItemStack stack) {
            g.renderFakeItem(stack, 0, 0);
        } else if (icon instanceof net.minecraft.world.effect.MobEffect effect) {
            var sprite = net.minecraft.client.Minecraft.getInstance().getMobEffectTextures().get(effect);
            g.blit(0, 0, 0, 18, 18, sprite);
        }
        g.pose().popPose();
    }

    private void renderBottomInfo(GuiGraphics g, int mx, int my) {
        int totalSpent = 0;
        String[] trees = {"LION", "NIGHT", "STEEL", "HERALD"};
        for (String tree : trees) {
            totalSpent += FeatUpgradeHandlerRegistry.getPointsSpentInTree(this.minecraft.player, tree);
        }

        int maxGlobal = FeatUpgradeHandlerRegistry.MAX_FEAT_POINTS;
        int nextPoint = Math.min(totalSpent + 1, maxGlobal);

        int x = 6;
        int costY = imageHeight - 24;
        int pointsY = costY - 19;

        g.pose().pushPose();
        g.pose().translate(x, pointsY, 0);
        g.pose().scale(0.8f, 0.8f, 0.8f);

        g.drawString(this.font, "Points: " + totalSpent + "/" + maxGlobal, 0, 0, totalSpent < maxGlobal ? INK_GREEN : INK_GOLD, false);

        if (totalSpent < maxGlobal) {
            List<FeatCostRegistry.Cost> costs = FeatCostRegistry.getCostForPoint(nextPoint);

            boolean canAffordAll = true;
            for (FeatCostRegistry.Cost cost : costs) {
                if (this.minecraft.player.getInventory().countItem(cost.item()) < cost.count()) {
                    canAffordAll = false;
                    break;
                }
            }
            int costTextColor = canAffordAll ? INK_GREEN : INK_RED;

            g.pose().translate(0, 11, 0);
            g.drawString(this.font, "Cost:", 0, 0, costTextColor, false);
            g.pose().popPose();

            int itemX = x + 22;

            for (FeatCostRegistry.Cost cost : costs) {
                ItemStack stack = new ItemStack(cost.item());
                int currentAmount = this.minecraft.player.getInventory().countItem(cost.item());
                boolean enough = currentAmount >= cost.count();

                g.renderFakeItem(stack, itemX, costY - 12);

                g.pose().pushPose();
                g.pose().translate(itemX + 9, costY + 4, 200);
                g.pose().scale(0.6f, 0.6f, 1.0f);
                g.drawString(this.font, "x" + cost.count(), 0, 0, enough ? INK_GREEN : INK_RED, true);
                g.pose().popPose();

                int relMx = mx - leftPos;
                int relMy = my - topPos;
                if (relMx >= itemX && relMx <= itemX + 16 && relMy >= costY - 12 && relMy <= costY + 4) {
                    this.hoveredCostStack = stack;
                }

                itemX += 18;
            }
        } else {
            g.pose().popPose();
        }
    }
}