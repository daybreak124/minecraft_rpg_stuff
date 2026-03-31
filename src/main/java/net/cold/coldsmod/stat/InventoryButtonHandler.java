package net.cold.coldsmod.stat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class InventoryButtonHandler {
    public static boolean showStats = false;
    private static int scrollOffset = 0;
    private static final int MAX_SCROLL = 750;
    private static final int PANEL_HEIGHT = 196;
    private static Button statsButton;
    private static final ResourceLocation TEXTURE = new ResourceLocation("coldsmod", "textures/gui/stats_button.png");
    private static final InventoryStatsCache C = InventoryStatsCache.CACHE;

    @SubscribeEvent
    public static void onInitScreen(ScreenEvent.Init.Post event) {
        if (!(event.getScreen() instanceof InventoryScreen invScreen)) return;
        int left = invScreen.getGuiLeft();
        int top = invScreen.getGuiTop();

        statsButton = new ImageButton(
                left + 127, top + 61,
                20, 18,
                0, 0,
                19,
                TEXTURE,
                20, 36,
                b -> {
                    showStats = !showStats;
                    if (showStats) {
                        C.lastUpdateTick = -1;
                    }
                }
        ) {
            @Override
            public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
                int vOffset = showStats ? 19 : 0;
                if (this.isHoveredOrFocused()) vOffset = 19;
                guiGraphics.blit(TEXTURE, this.getX(), this.getY(), 0, vOffset, this.width, this.height, 20, 36);
            }
        };
        event.addListener(statsButton);
    }

    @SubscribeEvent
    public static void onRender(ScreenEvent.Render.Post event) {
        if (!(event.getScreen() instanceof InventoryScreen invScreen)) return;
        if (statsButton != null) {
            statsButton.setX(invScreen.getGuiLeft() + 127);
            statsButton.setY(invScreen.getGuiTop() + 61);
        }
        if (!showStats) return;
        InventoryStatsCache.rebuildCache(Minecraft.getInstance());
        drawStatsPanel(event.getGuiGraphics(), Minecraft.getInstance(), invScreen.getGuiLeft(), invScreen.getGuiTop());
    }

    private static void drawStatsPanel(GuiGraphics guiGraphics, Minecraft mc, int left, int top) {
        // Draw Background
        guiGraphics.fill(left + 176, top, left + 276, top + PANEL_HEIGHT, 0xD3D3D3);

        int y = top + 5 - scrollOffset;
        int x = left + 182;

        // --- Core Attributes ---
        guiGraphics.drawString(mc.font, "Stats", x, y, 0xFFFF55); y += 15;
        guiGraphics.drawString(mc.font, "Attributes", x, y, 0x00AAAA); y += 10;
        guiGraphics.drawString(mc.font, "Strength: " + StatUtils.formatValue(C.str), x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Dexterity: " + StatUtils.formatValue(C.dex), x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Fortitude: " + StatUtils.formatValue(C.fort), x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Constitution: " + StatUtils.formatValue(C.con), x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Perception: " + StatUtils.formatValue(C.perc), x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Wisdom: " + StatUtils.formatValue(C.wis), x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Insight: " + StatUtils.formatValue(C.ins), x, y, 0xFFFFFF); y += 15;

        // --- Survivability ---
        guiGraphics.drawString(mc.font, "Survivability", x, y, 0x5555FF); y += 10;
        guiGraphics.drawString(mc.font, "Armor: " + StatUtils.formatValue(C.armor), x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Toughness: " + StatUtils.formatValue(C.tough), x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Health: " + StatUtils.formatValue(C.health), x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Inc. Damage Multiplier: " + StatUtils.formatValue(C.incDmg) + "x", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Total Damage Resist: " + StatUtils.formatValue(C.totalResist) + "%", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Effective HP: " + StatUtils.formatValue(C.eHP), x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Evasion: " + StatUtils.formatValue(C.fEva) + "%", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Knockback Resist: " + StatUtils.formatValue(C.knkRes) + "%", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Debuff Resist: " + StatUtils.formatValue(C.debuffRes) + "%", x, y, 0xFFFFFF); y += 15;

        // --- Melee ---
        guiGraphics.drawString(mc.font, "Melee", x, y, 0xE0701B); y += 10;
        guiGraphics.drawString(mc.font, "Potency: " + StatUtils.formatValue(C.fMeleePot) + " | (" + StatUtils.formatValue(C.mPot) + "%)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Haste: " + StatUtils.formatValue(C.fMeleeHaste) + " | (" + StatUtils.formatValue(C.mHaste) + "%)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Accuracy: " + StatUtils.formatValue(C.fMeleeAcc) + " | (" + StatUtils.formatValue(C.mAcc + 10.0) + "%)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Precision: " + StatUtils.formatValue(C.fMeleePre) + " | (" + StatUtils.formatValue(C.mPre + 50.0) + "%)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Damage Multiplier: " + StatUtils.formatValue(C.fMeleeMult) + "x", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Avg Increase: " + StatUtils.formatValue(C.mAvg) + "x", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Max Increase: " + StatUtils.formatValue(C.mMax) + "x", x, y, 0xFFFFFF); y += 15;

        // --- Projectile ---
        guiGraphics.drawString(mc.font, "Projectile", x, y, 0xE0701B); y += 10;
        guiGraphics.drawString(mc.font, "Potency: " + StatUtils.formatValue(C.fProjPot) + " | (" + StatUtils.formatValue(C.pPot) + "%)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Nock Haste: " + StatUtils.formatValue(C.fProjNock) + " | (" + StatUtils.formatValue(C.pNock) + "%)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Accuracy: " + StatUtils.formatValue(C.fProjAcc) + " | (" + StatUtils.formatValue(C.pAcc + 10.0) + "%)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Precision: " + StatUtils.formatValue(C.fProjPre) + " | (" + StatUtils.formatValue(C.pPre + 50.0) + "%)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Damage Multiplier: " + StatUtils.formatValue(C.fProjMult) + "x", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Increase: " + StatUtils.formatValue(C.pAvg) + "x", x, y, 0xFFFFFF); y += 15;

        // --- General ---
        guiGraphics.drawString(mc.font, "General", x, y, 0xE0701B); y += 10;
        guiGraphics.drawString(mc.font, "Potency: " + StatUtils.formatValue(C.fGenPot) + " | (" + StatUtils.formatValue(C.gPot) + "%)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Haste: " + StatUtils.formatValue(C.fGenHaste) + " | (" + StatUtils.formatValue(C.gHaste) + "%)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Accuracy: " + StatUtils.formatValue(C.fGenAcc) + " | (" + StatUtils.formatValue(C.gAcc + 10.0) + "%)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Precision: " + StatUtils.formatValue(C.fGenPre) + " | (" + StatUtils.formatValue(C.gPre + 50.0) + "%)", x, y, 0xFFFFFF); y += 15;
        guiGraphics.drawString(mc.font, "DoT Multiplier: " + StatUtils.formatValue(C.fDotMult) + "x", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Damage Multiplier: " + StatUtils.formatValue(C.fAllDmgMult) + "x", x, y, 0xFFFFFF); y += 10;

        // guiGraphics.drawString(mc.font, "Scaling: " + StatUtils.formatValue(C.gMult) + "x", x, y, 0xFFFFFF); y += 10;


        // --- Healing ---
        guiGraphics.drawString(mc.font, "Healing", x, y, 0x5BB450); y += 10;
        guiGraphics.drawString(mc.font, "Restoration: " + StatUtils.formatValue(C.fResto) + " | (" + StatUtils.formatValue(C.restoration) + "%)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Amplification: " + StatUtils.formatValue(C.fAmp) + " | (" + StatUtils.formatValue(C.amplification) + "%)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Rejuvenation: " + StatUtils.formatValue(C.fReju) + " | (" + StatUtils.formatValue(C.rejuvenation) + "%)", x, y, 0xFFFFFF); y += 15;

        // --- Movement ---
        guiGraphics.drawString(mc.font, "Movement", x, y, 0xD6C97A); y += 10;
        guiGraphics.drawString(mc.font, "Move Speed: " + StatUtils.formatValue(C.moveSpeed) + "%", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Swim Speed: " + StatUtils.formatValue(C.swimSpeed) + "%", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Step Height: " + StatUtils.formatValue(C.stepHeight), x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Jump Boost: " + StatUtils.formatValue(C.jumpBoost) + "%", x, y, 0xFFFFFF); y += 15;

        // --- Miscellaneous ---
        guiGraphics.drawString(mc.font, "Miscellaneous", x, y, 0xD6C97A); y += 10;
        guiGraphics.drawString(mc.font, "Block Reach: " + StatUtils.formatValue(C.blockReach), x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Entity Reach: " + StatUtils.formatValue(C.entityReach), x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Mining Speed: " + StatUtils.formatValue(C.mineSpeed) + "%", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "XP Gain: " + StatUtils.formatValue(C.xpGain) + "%", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Luck: " + StatUtils.formatValue(C.luck), x, y, 0xFFFFFF); y += 20;

        // --- Base and Multipliers ---
        guiGraphics.drawString(mc.font, "Base and Multipliers", x, y, 0xFFFF55); y += 15;

        // --- Defensive Multipliers ---
        guiGraphics.drawString(mc.font, "Survivability", x, y, 0x5555FF); y += 10;
        guiGraphics.drawString(mc.font, "Armor: " + StatUtils.formatValue(C.baseArmor) + " | (" + StatUtils.formatValue(C.multArmor) + "x)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Toughness: " + StatUtils.formatValue(C.baseTough) + " | (" + StatUtils.formatValue(C.multTough) + "x)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Health: " + StatUtils.formatValue(C.baseHealth) + " | (" + StatUtils.formatValue(C.multHealth) + "x)", x, y, 0xFFFFFF); y += 15;

        // --- Melee ---
        guiGraphics.drawString(mc.font, "Melee", x, y, 0xE0701B); y += 10;
        guiGraphics.drawString(mc.font, "Potency: " + StatUtils.formatValue(C.bMeleePot) + " | (" + StatUtils.formatValue(C.mMeleeMult) + "x)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Swing Haste: " + StatUtils.formatValue(C.bHaste) + " | (" + StatUtils.formatValue(C.mHasteMult) + "x)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Accuracy: " + StatUtils.formatValue(C.bMeleeAcc) + " | (" + StatUtils.formatValue(C.mMeleeAccMult) + "x)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Precision: " + StatUtils.formatValue(C.bMeleePre) + " | (" + StatUtils.formatValue(C.mMeleePreMult) + "x)", x, y, 0xFFFFFF); y += 15;

        // --- Projectile ---
        guiGraphics.drawString(mc.font, "Projectile", x, y, 0xE0701B); y += 10;
        guiGraphics.drawString(mc.font, "Potency: " + StatUtils.formatValue(C.bProjPot) + " | (" + StatUtils.formatValue(C.mProjMult) + "x)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Nock Haste: " + StatUtils.formatValue(C.bNock) + " | (" + StatUtils.formatValue(C.mNockMult) + "x)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Accuracy: " + StatUtils.formatValue(C.bProjAcc) + " | (" + StatUtils.formatValue(C.mProjAccMult) + "x)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Precision: " + StatUtils.formatValue(C.bProjPre) + " | (" + StatUtils.formatValue(C.mProjPreMult) + "x)", x, y, 0xFFFFFF); y += 15;

        // --- General ---
        guiGraphics.drawString(mc.font, "General", x, y, 0xE0701B); y += 10;
        guiGraphics.drawString(mc.font, "Potency: " + StatUtils.formatValue(C.bPot) + " | (" + StatUtils.formatValue(C.mPotMult) + "x)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Haste: " + StatUtils.formatValue(C.bOHaste) + " | (" + 1 + "x)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Accuracy: " + StatUtils.formatValue(C.bAcc) + " | (" + StatUtils.formatValue(C.mAccMult) + "x)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Precision: " + StatUtils.formatValue(C.bPre) + " | (" + StatUtils.formatValue(C.mPreMult) + "x)", x, y, 0xFFFFFF); y += 15;

        // --- Healing ---
        guiGraphics.drawString(mc.font, "Healing", x, y, 0x5BB450); y += 10;
        guiGraphics.drawString(mc.font, "Restoration: " + StatUtils.formatValue(C.bResto) + " | (" + StatUtils.formatValue(C.mRestoMult) + "x)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Amplification: " + StatUtils.formatValue(C.bAmp) + " | (" + StatUtils.formatValue(C.mAmpMult) + "x)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Rejuvenation: " + StatUtils.formatValue(C.bReju) + " | (" + StatUtils.formatValue(C.mRejuMult) + "x)", x, y, 0xFFFFFF);


        // Scrollbar logic
        int barHeight = 30;
        float scrollPercent = (float) scrollOffset / MAX_SCROLL;
        int barY = top + (int)(scrollPercent * (PANEL_HEIGHT - barHeight));
        guiGraphics.fill(left + 177, top, left + 179, top + PANEL_HEIGHT, 0xFF555555);
        guiGraphics.fill(left + 177, barY, left + 179, barY + barHeight, 0xFFAAAAAA);
    }

    @SubscribeEvent
    public static void onMouseScroll(ScreenEvent.MouseScrolled.Pre event) {
        if (!(event.getScreen() instanceof InventoryScreen)) return;
        if (!showStats) return;
        scrollOffset = Math.max(0, Math.min(scrollOffset - (int)(event.getScrollDelta() * 10), MAX_SCROLL));
        event.setCanceled(true);
    }
}