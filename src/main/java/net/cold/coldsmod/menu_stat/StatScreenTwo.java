package net.cold.coldsmod.menu_stat;

import net.cold.coldsmod.ModMessages;
import net.cold.coldsmod.stat.ModAttributes;
import net.cold.coldsmod.stat.StatUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

import static net.cold.coldsmod.menu_stat.StatUpgradeHandlerTwo.MAX_GLOBAL_POINTS;

public class StatScreenTwo extends AbstractContainerScreen<StatMenu> {
    private static final int START_Y = 25;
    private static final int SPACING = 11;
    private static final int COL_B = 152;
    private static final ResourceLocation TEXTURE = new ResourceLocation("coldsmod", "textures/gui/stat_background.png");

    private static final int INK_BLACK = 0x282828;
    private static final int INK_GOLD = 0xE6A800;
    private static final int INK_GREEN = 0x00CC00;
    private static final int INK_RED = 0xCC0000;

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
        addStatRow(ModAttributes.MELEE_HASTE.get(), xL, y); y += SPACING;
        addStatRow(ModAttributes.MELEE_ACCURACY.get(), xL, y); y += SPACING;
        addStatRow(ModAttributes.MELEE_PRECISION.get(), xL, y);

        // --- RIGHT COLUMN ---
        int xR = COL_B + 8; y = START_Y;
        addStatRow(ModAttributes.POTENCY.get(), xR, y); y += SPACING;
        addStatRow(ModAttributes.HASTE.get(), xR, y); y += SPACING;
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

        int buttonY = imageHeight - 16;
        int groupRightEdge = imageWidth - 5;
        int btnW = 35;
        int gap = 2;

        int invX = groupRightEdge - btnW;
        int backX = invX - btnW - gap;

        this.addRenderableWidget(Button.builder(Component.literal("Back"), b ->
                        this.minecraft.setScreen(new StatScreen(menu, this.minecraft.player.getInventory(), title)))
                .pos(leftPos + backX, topPos + buttonY).size(btnW, 12).build());

        this.addRenderableWidget(Button.builder(Component.literal("Inv"), b ->
                        this.minecraft.setScreen(new net.minecraft.client.gui.screens.inventory.InventoryScreen(this.minecraft.player)))
                .pos(leftPos + invX, topPos + buttonY).size(btnW, 12).build());
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        String titleText = "Stats";
        int titleWidth = this.font.width(titleText);
        int centerX = this.imageWidth / 4;
        graphics.drawString(this.font, titleText, centerX - (titleWidth / 2), 7, INK_BLACK, false);

        renderHeader(graphics, "Survivability", 75, START_Y - 6, 0x333399); // Deep Blue
        renderHeader(graphics, "General", COL_B + 75, START_Y - 6, 0x994400); // Deep Orange
        renderHeader(graphics, "Melee", 75, START_Y + 69, 0x994400);
        renderHeader(graphics, "Projectile", COL_B + 75, START_Y + 69, 0x994400);
        renderHeader(graphics, "Healing", (imageWidth / 2), 147, INK_GREEN);

        // --- LEFT COLUMN RENDER ---
        int y = START_Y;
        renderStatLine(graphics, "Armor", Attributes.ARMOR, y, MobEffects.ABSORPTION, 0); y += SPACING;
        renderStatLine(graphics, "Toughness", Attributes.ARMOR_TOUGHNESS, y, MobEffects.DAMAGE_RESISTANCE, 0); y += SPACING;
        renderStatLine(graphics, "Health", Attributes.MAX_HEALTH, y, MobEffects.REGENERATION, 0); y += SPACING;
        renderStatLine(graphics, "KB. Res", Attributes.KNOCKBACK_RESISTANCE, y, Items.NETHERITE_CHESTPLATE, 0); y += SPACING;
        renderStatLine(graphics, "Debuff Res", ModAttributes.DEBUFF_RESIST.get(), y, Items.MILK_BUCKET, 0);

        y = START_Y + 75;
        renderStatLine(graphics, "Potency", ModAttributes.MELEE_POTENCY.get(), y, MobEffects.DAMAGE_BOOST, 0); y += SPACING;
        renderStatLine(graphics, "Melee Haste", ModAttributes.MELEE_HASTE.get(), y, Items.SUGAR, 0); y += SPACING;
        renderStatLine(graphics, "Accuracy", ModAttributes.MELEE_ACCURACY.get(), y, Items.FLINT, 0); y += SPACING;
        renderStatLine(graphics, "Precision", ModAttributes.MELEE_PRECISION.get(), y, Items.GOLDEN_SWORD, 0);

        // --- RIGHT COLUMN RENDER ---
        y = START_Y;
        renderStatLine(graphics, "Potency", ModAttributes.POTENCY.get(), y, Items.AMETHYST_SHARD, COL_B); y += SPACING;
        renderStatLine(graphics, "Haste", ModAttributes.HASTE.get(), y, Items.BLAZE_POWDER, COL_B); y += SPACING;
        renderStatLine(graphics, "Accuracy", ModAttributes.ACCURACY.get(), y, Items.ENDER_EYE, COL_B); y += SPACING;
        renderStatLine(graphics, "Precision", ModAttributes.PRECISION.get(), y, Items.GOLDEN_AXE, COL_B); y += SPACING;
        renderStatLine(graphics, "Speed", Attributes.MOVEMENT_SPEED, y, MobEffects.MOVEMENT_SPEED, COL_B);

        y = START_Y + 75;
        renderStatLine(graphics, "Potency", ModAttributes.PROJECTILE_POTENCY.get(), y, Items.ARROW, COL_B); y += SPACING;
        renderStatLine(graphics, "Nock Haste", ModAttributes.NOCK_HASTE.get(), y, Items.STRING, COL_B); y += SPACING;
        renderStatLine(graphics, "Accuracy", ModAttributes.PROJECTILE_ACCURACY.get(), y, Items.FEATHER, COL_B); y += SPACING;
        renderStatLine(graphics, "Precision", ModAttributes.PROJECTILE_PRECISION.get(), y, Items.SPECTRAL_ARROW, COL_B);

        // --- HEALING RENDER ---
        int xH_Off = (imageWidth / 2) - 75;
        renderStatLine(graphics, "Rejuvenation", ModAttributes.REJUVENATION.get(), 155, Items.GOLDEN_APPLE, xH_Off);
        renderStatLine(graphics, "Restoration", ModAttributes.RESTORATION.get(), 155 + SPACING, MobEffects.HEALTH_BOOST, xH_Off);
        renderStatLine(graphics, "Amplification", ModAttributes.AMPLIFICATION.get(), 155 + (SPACING * 2), Items.POTION, xH_Off);

        renderBottomInfo(graphics, mouseX, mouseY);
    }

    private void renderBottomInfo(GuiGraphics graphics, int mouseX, int mouseY) {
        int x = 6;
        int costY = imageHeight - 16;
        int pointsY = costY - 11;

        int totalSpent = StatUpgradeHandlerTwo.getTotalPointsSpent(this.minecraft.player);
        int amountNeeded = StatUpgradeHandler.getRequiredAmount(totalSpent);
        Item pearl = StatUpgradeHandlerTwo.getRequiredPearl(totalSpent);
        ItemStack pearlStack = new ItemStack(pearl);
        boolean hasEnough = this.minecraft.player.getInventory().countItem(pearl) >= amountNeeded;

        int activeColor = hasEnough ? INK_GREEN : INK_RED;
        int hasPointColor = totalSpent >= MAX_GLOBAL_POINTS ? INK_GOLD : INK_GREEN;

        graphics.pose().pushPose();
        float textScale = 0.8f;

        graphics.pose().translate(x, pointsY, 0);
        graphics.pose().scale(textScale, textScale, textScale);

        graphics.drawString(this.font, "Points: " + totalSpent + "/" + MAX_GLOBAL_POINTS, 0, 0, hasPointColor, false);

        graphics.pose().translate(0, 11, 0);
        graphics.drawString(this.font, "Cost: x" + amountNeeded, 0, 0, activeColor, false);

        graphics.pose().popPose();

        int itemX = x + 34 + (amountNeeded > 9 ? 5 : 0);
        int finalX = itemX + 1;
        int finalY = costY - 5;
        graphics.renderFakeItem(pearlStack, finalX, finalY);

        // Tooltip Logic
        int relMouseX = mouseX - leftPos;
        int relMouseY = mouseY - topPos;
        if (relMouseX >= finalX && relMouseX <= finalX + 16 && relMouseY >= finalY && relMouseY <= finalY + 16) {
            graphics.renderTooltip(this.font, pearlStack, relMouseX, relMouseY);
        }
    }

    private void renderStatLine(GuiGraphics g, String name, Attribute attr, int y, Object icon, int xOffset) {
        int pts = StatUpgradeHandlerTwo.getPointsSpent(this.minecraft.player, attr);
        int max = StatUpgradeHandlerTwo.getMaxPointsFor(attr);
        double inc = StatUpgradeHandlerTwo.getIncrementFor(attr);

        double displayInc = inc;
        if (attr == Attributes.KNOCKBACK_RESISTANCE) displayInc *= 100;
        if (attr == Attributes.MOVEMENT_SPEED) displayInc *= 1000;

        String incStr = "(+" + StatUtils.formatValue(displayInc, true) + ")";
        String pointsStr = pts + "/" + max;

        g.pose().pushPose();
        g.pose().translate(xOffset + 20, y + 1, 0);
        g.pose().scale(0.5f, 0.5f, 0.5f);
        if (icon instanceof Item item) {
            g.renderFakeItem(new ItemStack(item), 0, 0);
        } else if (icon instanceof net.minecraft.world.effect.MobEffect effect) {
            net.minecraft.client.renderer.texture.TextureAtlasSprite sprite = this.minecraft.getMobEffectTextures().get(effect);
            g.blit(0, 0, 0, 18, 18, sprite);
        }
        g.pose().popPose();

        g.pose().pushPose();
        g.pose().scale(0.7f, 0.7f, 0.7f);
        int textX = (int)((xOffset + 32) / 0.7f);
        int textY = (int)((y + 3) / 0.7f);

        g.drawString(this.font, name, textX, textY, INK_BLACK, false);

        int incWidth = this.font.width(incStr);
        int incX = (int)((xOffset + 120) / 0.7f) - incWidth;
        g.drawString(this.font, incStr, incX, textY, INK_GREEN, false);

        int ptsWidth = this.font.width(pointsStr);
        int ptsX = incX - ptsWidth - 3;
        int labelColor = pts >= max ? INK_GOLD : INK_BLACK;
        g.drawString(this.font, pointsStr, ptsX, textY, labelColor, false);

        g.pose().popPose();
    }

    private void addStatRow(Attribute attr, int xOffset, int y) {
        String id = ForgeRegistries.ATTRIBUTES.getKey(attr).toString();
        this.addRenderableWidget(Button.builder(Component.literal("-"), b -> {
            int amount = hasShiftDown() ? 10:1;

            for (int i = 0; i < amount; i++) {
                ModMessages.sendToServer(new StatUpgradePacketTwo(id, false));
            }
        }).pos(leftPos + xOffset, topPos + y + 2).size(10, 8).build());



        this.addRenderableWidget(Button.builder(Component.literal("+"), b -> {
            int amount = hasShiftDown() ? 10:1;

            for (int i = 0; i < amount; i++) {
                ModMessages.sendToServer(new StatUpgradePacketTwo(id, true));
            }
        }).pos(leftPos + xOffset + 115, topPos + y + 2).size(10, 8).build());
    }

    private void renderHeader(GuiGraphics g, String text, int x, int y, int color) {
        g.pose().pushPose();
        g.pose().scale(0.8f, 0.8f, 0.8f);
        g.drawString(this.font, text, (int)(x / 0.8f) - (this.font.width(text) / 2), (int)(y / 0.8f), color, false);
        g.pose().popPose();
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