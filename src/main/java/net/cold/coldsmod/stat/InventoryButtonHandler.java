package net.cold.coldsmod.stat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
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
        net.minecraft.world.entity.player.Player player = mc.player;
        if (player == null) return;

        // --- Core Attributes ---
        guiGraphics.drawString(mc.font, "Stats", x, y, 0xFFFF55); y += 15;
        guiGraphics.drawString(mc.font, "Attributes", x, y, 0x00AAAA); y += 10;
        guiGraphics.drawString(mc.font, "Strength: " + StatUtils.formatValue(C.str), x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Dexterity: " + StatUtils.formatValue(C.dex), x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Fortitude: " + StatUtils.formatValue(C.fort), x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Constitution: " + StatUtils.formatValue(C.con), x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Perception: " + StatUtils.formatValue(C.perc), x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Wisdom: " + StatUtils.formatValue(C.wis), x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Insight: " + StatUtils.formatValue(C.ins), x, y, 0xFFFFFF); y += 20;

        // --- Survivability ---
        guiGraphics.drawString(mc.font, "Survivability", x, y, 0x5555FF); y += 10;
        guiGraphics.drawString(mc.font, "Armor: " + StatUtils.formatValue(player.getAttributeValue(net.minecraft.world.entity.ai.attributes.Attributes.ARMOR)), x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Toughness: " + StatUtils.formatValue(player.getAttributeValue(net.minecraft.world.entity.ai.attributes.Attributes.ARMOR_TOUGHNESS)), x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Health: " + StatUtils.formatValue(player.getMaxHealth()), x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Evasion: " + StatUtils.formatValue(C.fEva) + "%", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Knockback Resist: " + StatUtils.formatValue(C.knkRes) + "%", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Debuff Resist: " + StatUtils.formatValue(C.debuffRes) + "%", x, y, 0xFFFFFF); y += 15;

        guiGraphics.drawString(mc.font, "Inc. Damage Resist: " + StatUtils.formatValue(C.incDmg) + "x", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Total Damage Resist: " + StatUtils.formatValue(C.totalResist) + "%", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Effective HP: " + StatUtils.formatValue(C.eHP), x, y, 0xFFFFFF); y += 20;

        // --- Melee ---
        guiGraphics.drawString(mc.font, "Melee", x, y, 0xE0701B); y += 10;
        guiGraphics.drawString(mc.font, "Potency: " + StatUtils.formatValue(player.getAttributeValue(ModAttributes.MELEE_POTENCY.get())) + " (" + StatUtils.formatValue(AttributeApplier.getScaledValue(player, ModAttributes.MELEE_POTENCY.get())) + "%)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Melee Haste: " + StatUtils.formatValue(player.getAttributeValue(ModAttributes.MELEE_HASTE.get())) + " (" + StatUtils.formatValue(AttributeApplier.getScaledValue(player, ModAttributes.MELEE_HASTE.get())) + "%)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Accuracy: " + StatUtils.formatValue(player.getAttributeValue(ModAttributes.MELEE_ACCURACY.get())) + " (" + StatUtils.formatValue(10 + AttributeApplier.getScaledValue(player, ModAttributes.MELEE_ACCURACY.get())) + "%)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Precision: " + StatUtils.formatValue(player.getAttributeValue(ModAttributes.MELEE_PRECISION.get())) + " (" + StatUtils.formatValue(25 + AttributeApplier.getScaledValue(player, ModAttributes.MELEE_PRECISION.get())) + "%)", x, y, 0xFFFFFF); y += 15;

        guiGraphics.drawString(mc.font, "Damage Boost: " + StatUtils.formatValue(C.fMeleeMult) + "x", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Avg Increase: " + StatUtils.formatValue(C.mAvg) + "x", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Max Increase: " + StatUtils.formatValue(C.mMax) + "x", x, y, 0xFFFFFF); y += 20;

        // --- Projectile ---
        guiGraphics.drawString(mc.font, "Projectile", x, y, 0xE0701B); y += 10;
        guiGraphics.drawString(mc.font, "Potency: " + StatUtils.formatValue(player.getAttributeValue(ModAttributes.PROJECTILE_POTENCY.get())) + " (" + StatUtils.formatValue(AttributeApplier.getScaledValue(player, ModAttributes.PROJECTILE_POTENCY.get())) + "%)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Nock Haste: " + StatUtils.formatValue(player.getAttributeValue(ModAttributes.NOCK_HASTE.get())) + " (" + StatUtils.formatValue(AttributeApplier.getScaledValue(player, ModAttributes.NOCK_HASTE.get())) + "%)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Accuracy: " + StatUtils.formatValue(player.getAttributeValue(ModAttributes.PROJECTILE_ACCURACY.get())) + " (" + StatUtils.formatValue(10 + AttributeApplier.getScaledValue(player, ModAttributes.PROJECTILE_ACCURACY.get())) + "%)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Precision: " + StatUtils.formatValue(player.getAttributeValue(ModAttributes.PROJECTILE_PRECISION.get())) + " (" + StatUtils.formatValue(25 + AttributeApplier.getScaledValue(player, ModAttributes.PROJECTILE_PRECISION.get())) + "%)", x, y, 0xFFFFFF); y += 15;

        guiGraphics.drawString(mc.font, "Damage Boost: " + StatUtils.formatValue(C.fProjMult) + "x", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Increase: " + StatUtils.formatValue(C.pAvg) + "x", x, y, 0xFFFFFF); y += 20;

        // --- General ---
        guiGraphics.drawString(mc.font, "General", x, y, 0xE0701B); y += 10;
        guiGraphics.drawString(mc.font, "Potency: " + StatUtils.formatValue(player.getAttributeValue(ModAttributes.POTENCY.get())) + " (" + StatUtils.formatValue(AttributeApplier.getScaledValue(player, ModAttributes.POTENCY.get())) + "%)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Haste: " + StatUtils.formatValue(player.getAttributeValue(ModAttributes.HASTE.get())) + " (" + StatUtils.formatValue(AttributeApplier.getScaledValue(player, ModAttributes.HASTE.get())) + "%)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Accuracy: " + StatUtils.formatValue(player.getAttributeValue(ModAttributes.ACCURACY.get())) + " (" + StatUtils.formatValue(10 + AttributeApplier.getScaledValue(player, ModAttributes.ACCURACY.get())) + "%)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Precision: " + StatUtils.formatValue(player.getAttributeValue(ModAttributes.PRECISION.get())) + " (" + StatUtils.formatValue(25 + AttributeApplier.getScaledValue(player, ModAttributes.PRECISION.get())) + "%)", x, y, 0xFFFFFF); y += 15;

        guiGraphics.drawString(mc.font, "DoT Multiplier: " + StatUtils.formatValue(C.fDotMult) + "x", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Damage Multiplier: " + StatUtils.formatValue(C.fAllDmgMult) + "x", x, y, 0xFFFFFF); y += 20;

        // --- Healing ---
        guiGraphics.drawString(mc.font, "Healing", x, y, 0x5BB450); y += 10;
        guiGraphics.drawString(mc.font, "Restoration: " + StatUtils.formatValue(player.getAttributeValue(ModAttributes.RESTORATION.get())) + " (" + StatUtils.formatValue(AttributeApplier.getScaledValue(player, ModAttributes.RESTORATION.get())) + "%)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Amplification: " + StatUtils.formatValue(player.getAttributeValue(ModAttributes.AMPLIFICATION.get())) + " (" + StatUtils.formatValue(AttributeApplier.getScaledValue(player, ModAttributes.AMPLIFICATION.get())) + "%)", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Rejuvenation: " + StatUtils.formatValue(player.getAttributeValue(ModAttributes.REJUVENATION.get())) + " (" + StatUtils.formatValue(AttributeApplier.getScaledValue(player, ModAttributes.REJUVENATION.get())) + "%)", x, y, 0xFFFFFF); y += 15;

        // --- Movement ---
        guiGraphics.drawString(mc.font, "Movement", x, y, 0xD6C97A); y += 10;
        guiGraphics.drawString(mc.font, "Move Speed: " + StatUtils.formatValue(C.moveSpeed) + "%", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Swim Speed: " + StatUtils.formatValue(C.swimSpeed) + "%", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Step Height: " + StatUtils.formatValue(C.stepHeight), x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Jump Boost: " + StatUtils.formatValue(C.jumpBoost) + "%", x, y, 0xFFFFFF); y += 20;

        // --- Miscellaneous ---
        guiGraphics.drawString(mc.font, "Miscellaneous", x, y, 0xD6C97A); y += 10;
        guiGraphics.drawString(mc.font, "Block Reach: " + StatUtils.formatValue(C.blockReach), x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Entity Reach: " + StatUtils.formatValue(C.entityReach), x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Mining Speed: " + StatUtils.formatValue(C.mineSpeed) + "%", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "XP Gain: " + StatUtils.formatValue(C.xpGain) + "%", x, y, 0xFFFFFF); y += 10;
        guiGraphics.drawString(mc.font, "Luck: " + StatUtils.formatValue(C.luck), x, y, 0xFFFFFF); y += 25;

        // --- Base and Multipliers ---
        guiGraphics.drawString(mc.font, "Base and Multipliers", x, y, 0xFFFF55); y += 15;

        // --- Defensive Multipliers ---
        guiGraphics.drawString(mc.font, "Survivability", x, y, 0x5555FF); y += 10;

        // Armor
        var instArmor = player.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ARMOR);
        double baseArmor = instArmor.getBaseValue() + instArmor.getModifiers(AttributeModifier.Operation.ADDITION).stream().mapToDouble(m -> m.getAmount()).sum();
        guiGraphics.drawString(mc.font, "Armor: " + StatUtils.formatValue(baseArmor) + " | (" + StatUtils.formatValue(instArmor.getValue() / Math.max(1, baseArmor)) + "x)", x, y, 0xFFFFFF); y += 10;

        // Toughness
        var instTough = player.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ARMOR_TOUGHNESS);
        double baseTough = instTough.getBaseValue() + instTough.getModifiers(AttributeModifier.Operation.ADDITION).stream().mapToDouble(m -> m.getAmount()).sum();
        guiGraphics.drawString(mc.font, "Toughness: " + StatUtils.formatValue(baseTough) + " | (" + StatUtils.formatValue(instTough.getValue() / Math.max(1, baseTough)) + "x)", x, y, 0xFFFFFF); y += 10;

        // Health
        var instHealth = player.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH);
        double baseHealth = instHealth.getBaseValue() + instHealth.getModifiers(AttributeModifier.Operation.ADDITION).stream().mapToDouble(m -> m.getAmount()).sum();
        guiGraphics.drawString(mc.font, "Health: " + StatUtils.formatValue(baseHealth) + " | (" + StatUtils.formatValue(instHealth.getValue() / Math.max(1, baseHealth)) + "x)", x, y, 0xFFFFFF); y += 20;

        // --- Melee Multipliers ---
        guiGraphics.drawString(mc.font, "Melee", x, y, 0xE0701B); y += 10;

        var instMPot = player.getAttribute(ModAttributes.MELEE_POTENCY.get());
        double baseMPot = instMPot.getBaseValue() + instMPot.getModifiers(AttributeModifier.Operation.ADDITION).stream().mapToDouble(m -> m.getAmount()).sum();
        guiGraphics.drawString(mc.font, "Potency: " + StatUtils.formatValue(baseMPot) + " | (" + StatUtils.formatValue(instMPot.getValue() / Math.max(0.01, baseMPot)) + "x)", x, y, 0xFFFFFF); y += 10;

        var instMHaste = player.getAttribute(ModAttributes.MELEE_HASTE.get());
        double baseMHaste = instMHaste.getBaseValue() + instMHaste.getModifiers(AttributeModifier.Operation.ADDITION).stream().mapToDouble(m -> m.getAmount()).sum();
        guiGraphics.drawString(mc.font, "Swing Haste: " + StatUtils.formatValue(baseMHaste) + " | (" + StatUtils.formatValue(instMHaste.getValue() / Math.max(0.01, baseMHaste)) + "x)", x, y, 0xFFFFFF); y += 10;

        var instMAcc = player.getAttribute(ModAttributes.MELEE_ACCURACY.get());
        double baseMAcc = instMAcc.getBaseValue() + instMAcc.getModifiers(AttributeModifier.Operation.ADDITION).stream().mapToDouble(m -> m.getAmount()).sum();
        guiGraphics.drawString(mc.font, "Accuracy: " + StatUtils.formatValue(baseMAcc) + " | (" + StatUtils.formatValue(instMAcc.getValue() / Math.max(0.01, baseMAcc)) + "x)", x, y, 0xFFFFFF); y += 10;

        var instMPre = player.getAttribute(ModAttributes.MELEE_PRECISION.get());
        double baseMPre = instMPre.getBaseValue() + instMPre.getModifiers(AttributeModifier.Operation.ADDITION).stream().mapToDouble(m -> m.getAmount()).sum();
        guiGraphics.drawString(mc.font, "Precision: " + StatUtils.formatValue(baseMPre) + " | (" + StatUtils.formatValue(instMPre.getValue() / Math.max(0.01, baseMPre)) + "x)", x, y, 0xFFFFFF); y += 20;

        // --- Projectile Multipliers ---
        guiGraphics.drawString(mc.font, "Projectile", x, y, 0xE0701B); y += 10;

        var instPPot = player.getAttribute(ModAttributes.PROJECTILE_POTENCY.get());
        double basePPot = instPPot.getBaseValue() + instPPot.getModifiers(AttributeModifier.Operation.ADDITION).stream().mapToDouble(m -> m.getAmount()).sum();
        guiGraphics.drawString(mc.font, "Potency: " + StatUtils.formatValue(basePPot) + " | (" + StatUtils.formatValue(instPPot.getValue() / Math.max(0.01, basePPot)) + "x)", x, y, 0xFFFFFF); y += 10;

        var instPNock = player.getAttribute(ModAttributes.NOCK_HASTE.get());
        double basePNock = instPNock.getBaseValue() + instPNock.getModifiers(AttributeModifier.Operation.ADDITION).stream().mapToDouble(m -> m.getAmount()).sum();
        guiGraphics.drawString(mc.font, "Nock Haste: " + StatUtils.formatValue(basePNock) + " | (" + StatUtils.formatValue(instPNock.getValue() / Math.max(0.01, basePNock)) + "x)", x, y, 0xFFFFFF); y += 10;

        var instPAcc = player.getAttribute(ModAttributes.PROJECTILE_ACCURACY.get());
        double basePAcc = instPAcc.getBaseValue() + instPAcc.getModifiers(AttributeModifier.Operation.ADDITION).stream().mapToDouble(m -> m.getAmount()).sum();
        guiGraphics.drawString(mc.font, "Accuracy: " + StatUtils.formatValue(basePAcc) + " | (" + StatUtils.formatValue(instPAcc.getValue() / Math.max(0.01, basePAcc)) + "x)", x, y, 0xFFFFFF); y += 10;

        var instPPre = player.getAttribute(ModAttributes.PROJECTILE_PRECISION.get());
        double basePPre = instPPre.getBaseValue() + instPPre.getModifiers(AttributeModifier.Operation.ADDITION).stream().mapToDouble(m -> m.getAmount()).sum();
        guiGraphics.drawString(mc.font, "Precision: " + StatUtils.formatValue(basePPre) + " | (" + StatUtils.formatValue(instPPre.getValue() / Math.max(0.01, basePPre)) + "x)", x, y, 0xFFFFFF); y += 20;

        // --- General Multipliers ---
        guiGraphics.drawString(mc.font, "General", x, y, 0xE0701B); y += 10;

        var instGPot = player.getAttribute(ModAttributes.POTENCY.get());
        double baseGPot = instGPot.getBaseValue() + instGPot.getModifiers(AttributeModifier.Operation.ADDITION).stream().mapToDouble(m -> m.getAmount()).sum();
        guiGraphics.drawString(mc.font, "Potency: " + StatUtils.formatValue(baseGPot) + " | (" + StatUtils.formatValue(instGPot.getValue() / Math.max(0.01, baseGPot)) + "x)", x, y, 0xFFFFFF); y += 10;

        var instGHaste = player.getAttribute(ModAttributes.HASTE.get());
        double baseGHaste = instGHaste.getBaseValue() + instGHaste.getModifiers(AttributeModifier.Operation.ADDITION).stream().mapToDouble(m -> m.getAmount()).sum();
        guiGraphics.drawString(mc.font, "Haste: " + StatUtils.formatValue(baseGHaste) + " | (" + StatUtils.formatValue(instGHaste.getValue() / Math.max(0.01, baseGHaste)) + "x)", x, y, 0xFFFFFF); y += 10;

        var instGAcc = player.getAttribute(ModAttributes.ACCURACY.get());
        double baseGAcc = instGAcc.getBaseValue() + instGAcc.getModifiers(AttributeModifier.Operation.ADDITION).stream().mapToDouble(m -> m.getAmount()).sum();
        guiGraphics.drawString(mc.font, "Accuracy: " + StatUtils.formatValue(baseGAcc) + " | (" + StatUtils.formatValue(instGAcc.getValue() / Math.max(0.01, baseGAcc)) + "x)", x, y, 0xFFFFFF); y += 10;

        var instGPre = player.getAttribute(ModAttributes.PRECISION.get());
        double baseGPre = instGPre.getBaseValue() + instGPre.getModifiers(AttributeModifier.Operation.ADDITION).stream().mapToDouble(m -> m.getAmount()).sum();
        guiGraphics.drawString(mc.font, "Precision: " + StatUtils.formatValue(baseGPre) + " | (" + StatUtils.formatValue(instGPre.getValue() / Math.max(0.01, baseGPre)) + "x)", x, y, 0xFFFFFF); y += 20;

        // --- Healing Multipliers ---
        guiGraphics.drawString(mc.font, "Healing", x, y, 0x5BB450); y += 10;

        var instResto = player.getAttribute(ModAttributes.RESTORATION.get());
        double baseResto = instResto.getBaseValue() + instResto.getModifiers(AttributeModifier.Operation.ADDITION).stream().mapToDouble(m -> m.getAmount()).sum();
        guiGraphics.drawString(mc.font, "Restoration: " + StatUtils.formatValue(baseResto) + " | (" + StatUtils.formatValue(instResto.getValue() / Math.max(0.01, baseResto)) + "x)", x, y, 0xFFFFFF); y += 10;

        var instAmp = player.getAttribute(ModAttributes.AMPLIFICATION.get());
        double baseAmp = instAmp.getBaseValue() + instAmp.getModifiers(AttributeModifier.Operation.ADDITION).stream().mapToDouble(m -> m.getAmount()).sum();
        guiGraphics.drawString(mc.font, "Amplification: " + StatUtils.formatValue(baseAmp) + " | (" + StatUtils.formatValue(instAmp.getValue() / Math.max(0.01, baseAmp)) + "x)", x, y, 0xFFFFFF); y += 10;

        var instReju = player.getAttribute(ModAttributes.REJUVENATION.get());
        double baseReju = instReju.getBaseValue() + instReju.getModifiers(AttributeModifier.Operation.ADDITION).stream().mapToDouble(m -> m.getAmount()).sum();
        guiGraphics.drawString(mc.font, "Rejuvenation: " + StatUtils.formatValue(baseReju) + " | (" + StatUtils.formatValue(instReju.getValue() / Math.max(0.01, baseReju)) + "x)", x, y, 0xFFFFFF);

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