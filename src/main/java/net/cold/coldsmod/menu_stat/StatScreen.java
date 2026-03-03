package net.cold.coldsmod.menu_stat;

import net.cold.coldsmod.ModMessages;
import net.cold.coldsmod.accessory.UtilityAccessories;
import net.cold.coldsmod.stat.ModAttributes;
import net.cold.coldsmod.stat.StatUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

import static net.cold.coldsmod.menu_stat.StatUpgradeHandler.ATTRIBUTE_MAX_LEVEL;
import static net.cold.coldsmod.menu_stat.StatUpgradeHandler.BASE_POINTS;

public class StatScreen extends AbstractContainerScreen<StatMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("coldsmod", "textures/gui/attribute_background.png");

    private static final int START_Y = 35; // Moved down 10px
    private static final int SPACING = 11;

    private static final int INK_BLACK = 0x282828;
    private static final int INK_GOLD = 0xE6A800;
    private static final int INK_GREEN = 0x00CC00;
    private static final int INK_RED = 0xCC0000;
    private static final int INK_BLUE = 0x00AAAA;

    public StatScreen(StatMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 300;
        this.imageHeight = 210;
        this.titleLabelX = -1000;
        this.inventoryLabelX = -1000;
    }

    @Override
    protected void init() {
        super.init();
        this.clearWidgets();

        // --- LEFT COLUMN ---
        int leftX = 8;
        addStatRow(ModAttributes.STR.get(), START_Y, leftX, true);
        addStatRow(ModAttributes.DEX.get(), START_Y + SPACING, leftX, true);
        addStatRow(ModAttributes.FORT.get(), START_Y + (SPACING * 2), leftX, true);
        addStatRow(ModAttributes.CON.get(), START_Y + (SPACING * 3), leftX, true);
        addStatRow(ModAttributes.PERC.get(), START_Y + (SPACING * 4), leftX, true);
        addStatRow(ModAttributes.WISDOM.get(), START_Y + (SPACING * 5), leftX, true);

        // --- RIGHT COLUMN ---
        int rightX = (imageWidth / 2) + 8;
        addStatRow(ForgeMod.STEP_HEIGHT_ADDITION.get(), START_Y, rightX, false);
        addStatRow(ForgeMod.BLOCK_REACH.get(), START_Y + SPACING, rightX, false);
        addStatRow(ModAttributes.MINING_SPEED.get(), START_Y + (SPACING * 2), rightX, false);
        addStatRow(ModAttributes.XP_GAIN.get(), START_Y + (SPACING * 3), rightX, false);

        // --- BUTTONS SWAPPED ---
        int buttonY = imageHeight - 16;
        int btnW = 35;
        int gap = 2;
        int groupRight = imageWidth - 5;

        int nextX = groupRight - btnW;
        int invX = nextX - btnW - gap;

        this.addRenderableWidget(Button.builder(Component.literal("Inv"), b ->
                        this.minecraft.setScreen(new net.minecraft.client.gui.screens.inventory.InventoryScreen(this.minecraft.player)))
                .pos(leftPos + invX, topPos + buttonY).size(btnW, 12).build());

        this.addRenderableWidget(Button.builder(Component.literal("Next"), b ->
                        this.minecraft.setScreen(new StatScreenTwo(this.menu, this.minecraft.player.getInventory(), this.title)))
                .pos(leftPos + nextX, topPos + buttonY).size(btnW, 12).build());
    }

    @Override
    protected void renderLabels(GuiGraphics g, int mx, int my) {
        int half = imageWidth / 2;

        renderHeader(g, "Attributes", half / 2, START_Y - 8, INK_BLUE);
        renderHeader(g, "Utility Stats", half + (half / 2), START_Y - 8, 0x994400);

        // --- ATTRIBUTES ---
        renderAttributeLine(g, "Strength", ModAttributes.STR.get(), START_Y, Items.IRON_SWORD, mx, my, 0);
        renderAttributeLine(g, "Dexterity", ModAttributes.DEX.get(), START_Y + SPACING, Items.BOW, mx, my, 0);
        renderAttributeLine(g, "Fortitude", ModAttributes.FORT.get(), START_Y + (SPACING * 2), Items.SHIELD, mx, my, 0);
        renderAttributeLine(g, "Constitution", ModAttributes.CON.get(), START_Y + (SPACING * 3), MobEffects.REGENERATION, mx, my, 0);
        renderAttributeLine(g, "Perception", ModAttributes.PERC.get(), START_Y + (SPACING * 4), MobEffects.CONDUIT_POWER, mx, my, 0);
        renderAttributeLine(g, "Wisdom", ModAttributes.WISDOM.get(), START_Y + (SPACING * 5), Items.ENCHANTED_BOOK, mx, my, 0);

        // --- UTILITY ---
        renderUtilityLine(g, "Step Height", ForgeMod.STEP_HEIGHT_ADDITION.get(), START_Y, UtilityAccessories.CLOUDTREADER_BOOTS.get(), half);
        renderUtilityLine(g, "Block Reach", ForgeMod.BLOCK_REACH.get(), START_Y + SPACING, UtilityAccessories.ENDERMAN_FINGERS.get(), half);
        renderUtilityLine(g, "Mining Speed", ModAttributes.MINING_SPEED.get(), START_Y + (SPACING * 2), MobEffects.DIG_SPEED, half);
        renderUtilityLine(g, "XP Gain", ModAttributes.XP_GAIN.get(), START_Y + (SPACING * 3), Items.EXPERIENCE_BOTTLE, half);

        renderBottomInfo(g, mx, my);
    }

    private void renderAttributeLine(GuiGraphics g, String name, Attribute attr, int y, Object icon, int mx, int my, int xOff) {
        int val = (int) this.minecraft.player.getAttributeValue(attr);
        int spent = StatUpgradeHandler.getPointsSpent(this.minecraft.player, attr);
        String pointsStr = spent + "/" + ATTRIBUTE_MAX_LEVEL;

        // 1. Icon (Keep at 0.5x scale)
        g.pose().pushPose();
        g.pose().translate(xOff + 20, y + 1, 0);
        g.pose().scale(0.5f, 0.5f, 0.5f);
        renderIconGeneric(g, icon);
        g.pose().popPose();

        // 2. Scaled Text Block
        g.pose().pushPose();
        g.pose().scale(0.7f, 0.7f, 0.7f);

        // Convert Y to scaled space
        int textY = (int)((y + 3) / 0.7f);

        int valX = (int)((xOff + 35) / 0.7f);
        g.drawString(this.font, String.valueOf(val), valX, textY, INK_BLUE, false);

        // --- CENTERED NAME ---
        // The gap between buttons is roughly from xOff+30 to xOff+115.
        // We target the midpoint (approx xOff+72) and center the string on it.
        int nameWidth = this.font.width(name);
        int centerAnchorScaled = (int)((xOff + 72) / 0.7f);
        int nameX = centerAnchorScaled - (nameWidth / 2);
        g.drawString(this.font, name, nameX, textY, INK_BLACK, false);

        // --- BUTTON-ANCHORED POINTS ---
        // The + button is at xOff+115. We anchor to xOff+113 so it's flush.
        // We MUST divide the coordinate by 0.7 to fix the "floating" issue.
        int ptsWidth = this.font.width(pointsStr);
        int ptsX = (int)((xOff + 120) / 0.7f) - ptsWidth;

        int pColor = val >= ATTRIBUTE_MAX_LEVEL ? INK_GOLD : INK_BLACK;
        g.drawString(this.font, pointsStr, ptsX, textY, pColor, false);

        g.pose().popPose();

        int relMx = mx - leftPos;
        int relMy = my - topPos;
        if (relMx >= xOff + 18 && relMx <= xOff + 125 && relMy >= y && relMy <= y + 10) {
            renderAttributeTooltip(g, name, relMx, relMy - 10, val);
        }
    }

    private void renderUtilityLine(GuiGraphics g, String name, Attribute attr, int y, Object icon, int xOff) {
        int pts = StatUpgradeHandlerThree.getPointsSpent(this.minecraft.player, attr);
        int max = StatUpgradeHandlerThree.getMaxPointsFor(attr);
        double inc = StatUpgradeHandlerThree.getIncrementFor(attr);
        if (attr == ModAttributes.XP_GAIN.get() || attr == ModAttributes.MINING_SPEED.get()) inc *= 100;

        String incStr = "(+" + StatUtils.formatValue(inc, true) + ")";
        String pointsStr = pts + "/" + max;

        g.pose().pushPose();
        g.pose().translate(xOff + 20, y + 1, 0);
        g.pose().scale(0.5f, 0.5f, 0.5f);
        renderIconGeneric(g, icon);
        g.pose().popPose();

        g.pose().pushPose();
        g.pose().scale(0.7f, 0.7f, 0.7f);

        int textX = (int)((xOff + 32) / 0.7f); // Start name near icon
        int textY = (int)((y + 3) / 0.7f);
        g.drawString(this.font, name, textX, textY, INK_BLACK, false);

        // --- ANCHORED VALUES ---
        // Anchor the increment (+0.0X) to the + button at 115
        int incWidth = this.font.width(incStr);
        int incX = (int)((xOff + 120) / 0.7f) - incWidth;
        g.drawString(this.font, incStr, incX, textY, INK_GREEN, false);

        // Tuck points total 2 pixels to the left of the increment
        int ptsWidth = this.font.width(pointsStr);
        int ptsX = incX - ptsWidth - 2;
        int pColor = pts >= max ? INK_GOLD : INK_BLACK;
        g.drawString(this.font, pointsStr, ptsX, textY, pColor, false);

        g.pose().popPose();
    }

    private void addStatRow(Attribute attr, int y, int xOffset, boolean isAttribute) {
        String id = ForgeRegistries.ATTRIBUTES.getKey(attr).toString();
        this.addRenderableWidget(Button.builder(Component.literal("-"), b ->
                        ModMessages.sendToServer(isAttribute ? new StatUpgradePacket(id, false) : new StatUpgradePacketThree(id, false)))
                .pos(leftPos + xOffset, topPos + y + 2).size(10, 8).build());

        // Aligned to 115 to match StatScreenTwo logic
        this.addRenderableWidget(Button.builder(Component.literal("+"), b ->
                        ModMessages.sendToServer(isAttribute ? new StatUpgradePacket(id, true) : new StatUpgradePacketThree(id, true)))
                .pos(leftPos + xOffset + 115, topPos + y + 2).size(10, 8).build());
    }

    private void renderHeader(GuiGraphics g, String text, int x, int y, int color) {
        g.pose().pushPose();
        g.pose().scale(0.8f, 0.8f, 0.8f);
        g.drawString(this.font, text, (int)(x / 0.8f) - (this.font.width(text) / 2), (int)(y / 0.8f), color, false);
        g.pose().popPose();
    }

    private void renderBottomInfo(GuiGraphics g, int mx, int my) {
        int half = imageWidth / 2;
        int spent1 = StatUpgradeHandler.getTotalPointsSpent(this.minecraft.player);
        renderCostLogic(g, spent1, BASE_POINTS, StatUpgradeHandler.getRequiredShard(spent1), 6, mx, my);

        int spent2 = StatUpgradeHandlerThree.getTotalPointsSpent(this.minecraft.player);
        renderCostLogic(g, spent2, StatUpgradeHandlerThree.MAX_GLOBAL_POINTS, StatUpgradeHandlerThree.getRequiredScrap(spent2), half + 6, mx, my);
    }

    private void renderCostLogic(GuiGraphics g, int spent, int max, Item item, int x, int mx, int my) {
        int costY = imageHeight - 16;
        int pointsY = costY - 11;
        int amount = StatUpgradeHandler.getRequiredAmount(spent);
        if (max == StatUpgradeHandlerThree.MAX_GLOBAL_POINTS && amount == 3) amount = 4;

        boolean hasEnough = this.minecraft.player.getInventory().countItem(item) >= amount;
        g.pose().pushPose();
        g.pose().translate(x, pointsY, 0);
        g.pose().scale(0.8f, 0.8f, 0.8f);
        g.drawString(this.font, "Points: " + spent + "/" + max, 0, 0, spent < max ? INK_GREEN : INK_GOLD, false);
        g.pose().translate(0, 11, 0);
        g.drawString(this.font, "Cost: x" + amount, 0, 0, hasEnough ? INK_GREEN : INK_RED, false);
        g.pose().popPose();

        int itemX = x + 32 + (amount > 9 ? 5 : 0);
        g.renderFakeItem(new ItemStack(item), itemX + 1, costY - 5);

        int relMx = mx - leftPos;
        int relMy = my - topPos;
        if (relMx >= itemX && relMx <= itemX + 16 && relMy >= costY - 5 && relMy <= costY + 11) {
            g.renderTooltip(this.font, new ItemStack(item), relMx, relMy);
        }
    }

    private void renderIconGeneric(GuiGraphics g, Object icon) {
        if (icon instanceof Item item) {
            g.renderFakeItem(new ItemStack(item), 0, 0);
        } else if (icon instanceof net.minecraft.world.effect.MobEffect effect) {
            g.blit(0, 0, 0, 18, 18, this.minecraft.getMobEffectTextures().get(effect));
        }
    }

    private void renderAttributeTooltip(GuiGraphics graphics, String name, int x, int y, int currentVal) {
        List<Component> lines = new ArrayList<>();
        lines.add(Component.literal(name).withStyle(ChatFormatting.DARK_AQUA, ChatFormatting.BOLD));
        lines.add(Component.literal("Per Point:").withStyle(ChatFormatting.GOLD));

        switch(name) {
            case "Strength" -> {
                addScalingLine(lines, "Attack Damage", "+0.012", 0xE0701B);
                addScalingLine(lines, "Potency", "+0.1", 0xE0701B);
                addScalingLine(lines, "Melee Potency", "+0.25", 0xE0701B);
                addScalingLine(lines, "Armor", "+0.12", 0x5555FF);
            }
            case "Dexterity" -> {
                addScalingLine(lines, "Projectile Potency", "+0.125", 0xE0701B);
                addScalingLine(lines, "Accuracy", "+0.25", 0xE0701B);
                addScalingLine(lines, "Haste", "+0.15", 0xE0701B);
                addScalingLine(lines, "Nock Haste", "+0.1", 0xE0701B);
                addScalingLine(lines, "Movement Speed", "+0.3%", 0xD6C97A);
            }
            case "Fortitude" -> {
                addScalingLine(lines, "Armor", "+0.18", 0x5555FF);
                addScalingLine(lines, "Toughness", "+0.12", 0x5555FF);
                addScalingLine(lines, "Knockback Resist", "+0.2%", 0x5555FF);
            }
            case "Constitution" -> {
                addScalingLine(lines, "Potency", "+0.175", 0xE0701B);
                addScalingLine(lines, "Health", "+0.06", 0x5555FF);
                addScalingLine(lines, "Debuff Resist", "+0.05%", 0x5555FF);
                addScalingLine(lines, "Rejuvenation", "+0.125", 0x5BB450);
            }
            case "Perception" -> {
                addScalingLine(lines, "Precision", "+0.35", 0xE0701B);
                addScalingLine(lines, "Armor", "+0.04", 0x5555FF);
            }
            case "Wisdom" -> {
                addScalingLine(lines, "Debuff Resist", "+0.125", 0x5555FF);
                addScalingLine(lines, "Restoration", "+0.18", 0x5BB450);
                addScalingLine(lines, "Amplification", "+0.25", 0x5BB450);
            }
        }
        lines.add(Component.literal(" "));
        lines.add(Component.literal("Milestones:").withStyle(ChatFormatting.GOLD));

        if (name.equals("Strength")) {
            lines.add(getMilestoneComp(30, "+8 Potency", currentVal));
            lines.add(getMilestoneComp(40, "+3 Armor", currentVal));
            lines.add(getMilestoneComp(50, "+12 Haste", currentVal));
            lines.add(getMilestoneComp(60, "+5 Melee Accuracy", currentVal));
            lines.add(getMilestoneComp(60, "+7 Precision", currentVal));
            lines.add(getMilestoneComp(70, "+18% Potency & +5 Armor", currentVal));
            lines.add(getMilestoneComp(80, "+0.5 Attack Damage & +5 Armor", currentVal));
        } else if (name.equals("Fortitude")) {
            lines.add(getMilestoneComp(30, "+3.5 Armor", currentVal));
            lines.add(getMilestoneComp(40, "+1.75 Health", currentVal));
            lines.add(getMilestoneComp(50, "+10% Armor", currentVal));
            lines.add(getMilestoneComp(60, "+6 Toughness", currentVal));
            lines.add(getMilestoneComp(70, "+2 Health & +10% KB Res", currentVal));
            lines.add(getMilestoneComp(80, "+5 Armor & Toughness", currentVal));
        } else if (name.equals("Dexterity")) {
            lines.add(getMilestoneComp(30, "+8 Accuracy", currentVal));
            lines.add(getMilestoneComp(40, "+9 Precision", currentVal));
            lines.add(getMilestoneComp(50, "+8% Movement Speed & +8 Precision", currentVal));
            lines.add(getMilestoneComp(60, "+10 Nock Haste & +4 Accuracy", currentVal));
            lines.add(getMilestoneComp(70, "+8% Melee Haste & +15% Projectile Potency", currentVal));
            lines.add(getMilestoneComp(80, "+7.5 Potency & Nock Haste", currentVal));
        } else if (name.equals("Constitution")) {
            lines.add(getMilestoneComp(30, "+1.8 Max Health", currentVal));
            lines.add(getMilestoneComp(40, "+6.25 Potency", currentVal));
            lines.add(getMilestoneComp(50, "+2.5 Toughness & +1.25 Health", currentVal));
            lines.add(getMilestoneComp(50, "+12 Rejuvenation", currentVal));
            lines.add(getMilestoneComp(60, "+15 Rejuvenation & +2 Health", currentVal));
            lines.add(getMilestoneComp(70, "+13.5% Armor", currentVal));
            lines.add(getMilestoneComp(80, "+14 Potency", currentVal));
        } else if (name.equals("Perception")) {
            lines.add(getMilestoneComp(30, "+3 Armor", currentVal));
            lines.add(getMilestoneComp(40, "+12% Potency", currentVal));
            lines.add(getMilestoneComp(50, "+15 Precision", currentVal));
            lines.add(getMilestoneComp(60, "+1 Entity Reach & +10% Precision", currentVal));
            lines.add(getMilestoneComp(70, "+12% Armor", currentVal));
            lines.add(getMilestoneComp(80, "+16% Precision", currentVal));
        } else if (name.equals("Wisdom")) {
            lines.add(getMilestoneComp(10, "+8 Restoration", currentVal));
            lines.add(getMilestoneComp(20, "+10 Amplification", currentVal));
            lines.add(getMilestoneComp(30, "+10 Debuff Resist", currentVal));
            lines.add(getMilestoneComp(40, "+15% Restoration", currentVal));
            lines.add(getMilestoneComp(50, "+5 Armor & +10 Amplification", currentVal));
            lines.add(getMilestoneComp(60, "+5 Restoration & +16% Amplification", currentVal));
        }

        graphics.renderComponentTooltip(this.font, lines, x, y);
    }

    private Component getMilestoneComp(int level, String desc, int current) {
        boolean unlocked = current >= level;
        ChatFormatting color = unlocked ? ChatFormatting.GREEN : ChatFormatting.DARK_GRAY;
        String prefix = unlocked ? "✔ " : "○ ";
        return Component.literal(prefix + level + ": " + desc).withStyle(color);
    }

    private void addScalingLine(List<Component> lines, String statName, String value, int hexColor) {
        lines.add(Component.literal(value + " " + statName).withStyle(style -> style.withColor(hexColor)));
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