package net.cold.coldsmod.stat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class InventoryButtonHandler {

    public static boolean showStats = false;
    private static int scrollOffset = 0;
    private static final int MAX_SCROLL = 600;
    private static final int PANEL_HEIGHT = 196;

    private static Button statsButton;

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
                new ResourceLocation("coldsmod", "textures/gui/stats_button.png"),
                20, 36,
                b -> showStats = !showStats
        ) {
            @Override
            public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
                boolean hovered = mouseX >= this.getX() && mouseX < this.getX() + this.width &&
                        mouseY >= this.getY() && mouseY < this.getY() + this.height;

                int vOffset = showStats ? 19 : 0;
                if (hovered) {
                    vOffset = 19;
                }

                guiGraphics.blit(
                        new ResourceLocation("coldsmod", "textures/gui/stats_button.png"),
                        this.getX(), this.getY(),
                        0, vOffset,
                        this.width, this.height,
                        20, 36
                );
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

        Minecraft mc = Minecraft.getInstance();
        GuiGraphics gui = event.getGuiGraphics();

        drawStatsPanel(gui, mc, invScreen.getGuiLeft(), invScreen.getGuiTop());
    }


    private static void drawStatsPanel(GuiGraphics guiGraphics, Minecraft mc, int left, int top) {
        guiGraphics.fill(left + 176, top, left + 276, top + PANEL_HEIGHT, 0xD3D3D3);

        CompoundTag data = mc.player.getPersistentData();

        AttributeApplier applier = new AttributeApplier();
        applier.recalcStats(mc.player);

        // --- Effective crit chances ---
        double effectiveMeleeCritChance = Math.min(10 + data.getDouble("meleeCritChanceIncrease"), 100) / 100.0;
        double effectiveProjectileCritChance = Math.min(10 + data.getDouble("projectileCritChanceIncrease"), 100) / 100.0;

        double avgMeleeDamage = ((1 + data.getDouble("meleeDamageIncrease") / 100.0)
                * (1 + effectiveMeleeCritChance * (50 + data.getDouble("meleeCritDamageIncrease")) / 100.0)
                * (1 + data.getDouble("attackSpeedIncrease") / 100.0))/1.05;

        double maxMeleeDamage = ((1 + data.getDouble("meleeDamageIncrease") / 100.0)
                * (1 + (50 + data.getDouble("meleeCritDamageIncrease")) / 100.0)
                * (1 + data.getDouble("attackSpeedIncrease") / 100.0))/1.05;

        double avgBowDamage = ((1 + data.getDouble("projectileDamageIncrease") / 100.0)
                * (1 + effectiveProjectileCritChance * (50 + data.getDouble("projectileCritDamageIncrease")) / 100.0)
                * (1 + data.getDouble("drawSpeedIncrease") / 100.0))/1.05;

        // --- Armor and survivability ---
        double armorReduction = mc.player.getAttribute(Attributes.ARMOR).getValue() /
                (120.0 + mc.player.getAttribute(Attributes.ARMOR).getValue() - 120.0 *
                        (mc.player.getAttribute(Attributes.ARMOR_TOUGHNESS).getValue() /
                                (mc.player.getAttribute(Attributes.ARMOR_TOUGHNESS).getValue() + 80.0)));

        int totalProtLevel = 0;
        for (ItemStack armorPiece : mc.player.getArmorSlots()) {
            totalProtLevel += EnchantmentHelper.getItemEnchantmentLevel(Enchantments.ALL_DAMAGE_PROTECTION, armorPiece);
        }

        double resistance = 0.0;
        if (mc.player.hasEffect(MobEffects.DAMAGE_RESISTANCE)) {
            MobEffectInstance effect = mc.player.getEffect(MobEffects.DAMAGE_RESISTANCE);
            resistance = effect.getAmplifier() * 0.2;
        }

        double protReduction = totalProtLevel * 0.02;
        double totalReduction = (1.0 - (1.0 - armorReduction) * (1.0 - protReduction) * (1.0 - resistance)) * 100.0;

        double finalSpeed = 1000 * mc.player.getAttribute(Attributes.MOVEMENT_SPEED).getValue() - 100;
        double finalSwimSpeed = 100 * mc.player.getAttribute(ForgeMod.SWIM_SPEED.get()).getValue() - 100;

        int y = top - scrollOffset;

        // --- Attributes Panel ---
        guiGraphics.drawString(mc.font, "Stats", left + 182, y, 0xFFFF55);
        y += 15;
        guiGraphics.drawString(mc.font, "Attributes", left + 182, y, 0xAAAAFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Strength: " + data.getInt("totalStr"), left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Dexterity: " + data.getInt("totalDex"), left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Fortitude: " + data.getInt("totalFort"), left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Constitution: " + data.getInt("totalCon"), left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Perception: " + data.getInt("totalPerc"), left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Intelligence: " + data.getInt("totalInt"), left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Wisdom: " + data.getInt("totalWis"), left + 182, y, 0xFFFFFF);
        y += 15;

        // --- Survivability Panel ---
        guiGraphics.drawString(mc.font, "Survivability", left + 182, y, 0xAAAAFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Armor: " + StatUtils.formatValue(mc.player.getAttribute(Attributes.ARMOR).getValue()), left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Toughness: " + StatUtils.formatValue(mc.player.getAttribute(Attributes.ARMOR_TOUGHNESS).getValue()), left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Damage Resist: " + StatUtils.formatValue(totalReduction) + "%", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Health: " + StatUtils.formatValue(mc.player.getAttribute(Attributes.MAX_HEALTH).getValue()), left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Knockback Resist: " + StatUtils.formatValue(data.getDouble("totalKnockbackResist") + data.getDouble("vanillaKnockbackResist")) + "%", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Debuff Resist: " + StatUtils.formatValue(data.getDouble("totalDebuffResist")) + "%", left + 182, y, 0xFFFFFF);
        y += 15;

        // --- Melee Panel ---
        guiGraphics.drawString(mc.font, "Melee", left + 182, y, 0xAAAAFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Attack Damage: " + StatUtils.formatValue(mc.player.getAttribute(Attributes.ATTACK_DAMAGE).getValue()), left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Damage: " + StatUtils.formatValue(data.getDouble("totalMeleeDamage")) +
                " | (" + StatUtils.formatValue(data.getDouble("meleeDamageIncrease")) + "%)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Attack Speed: " + StatUtils.formatValue(data.getDouble("totalAttackSpeed")) +
                " | (" + StatUtils.formatValue(data.getDouble("attackSpeedIncrease")) + "%)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Crit Chance: " + StatUtils.formatValue(data.getDouble("totalMeleeCritChance")) +
                " | (" + StatUtils.formatValue(10 + data.getDouble("meleeCritChanceIncrease")) + "%)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Crit Damage: " + StatUtils.formatValue(data.getDouble("totalMeleeCritDamage")) +
                " | (" + StatUtils.formatValue(50 + data.getDouble("meleeCritDamageIncrease")) + "%)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Avg Damage Increase: " + StatUtils.formatValue(avgMeleeDamage) + "x", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Max Damage Increase: " + StatUtils.formatValue(maxMeleeDamage) + "x", left + 182, y, 0xFFFFFF);
        y += 15;

        // --- Ranged Panel ---
        guiGraphics.drawString(mc.font, "Ranged", left + 182, y, 0xAAAAFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Projectile Damage: " + StatUtils.formatValue(data.getDouble("totalProjectileDamage")) +
                " | (" + StatUtils.formatValue(data.getDouble("projectileDamageIncrease")) + "%)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Draw Speed: " + StatUtils.formatValue(data.getDouble("totalDrawSpeed")) +
                " | (" + StatUtils.formatValue(data.getDouble("drawSpeedIncrease")) + "%)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Crit Chance: " + StatUtils.formatValue(data.getDouble("totalProjectileCritChance")) +
                " | (" + StatUtils.formatValue(10 + data.getDouble("projectileCritChanceIncrease")) + "%)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Crit Damage: " + StatUtils.formatValue(data.getDouble("totalProjectileCritDamage")) +
                " | (" + StatUtils.formatValue(50 + data.getDouble("projectileCritDamageIncrease")) + "%)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Damage Increase: " + StatUtils.formatValue(avgBowDamage) + "x", left + 182, y, 0xFFFFFF);
        y += 15;

        // --- Movement Panel ---
        guiGraphics.drawString(mc.font, "Movement", left + 182, y, 0xAAAAFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Move Speed: " + StatUtils.formatValue(finalSpeed) + "%", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Swim Speed: " + StatUtils.formatValue(finalSwimSpeed) + "%", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Step Height: " + StatUtils.formatValue(mc.player.getAttribute(ForgeMod.STEP_HEIGHT_ADDITION.get()).getValue()), left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Jump Boost: " + StatUtils.formatValue(data.getDouble("totalJumpBoost")) + "%", left + 182, y, 0xFFFFFF);
        y += 15;

        // --- Miscellaneous Panel ---
        guiGraphics.drawString(mc.font, "Miscellaneous", left + 182, y, 0xAAAAFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Block Reach: " + StatUtils.formatValue(mc.player.getAttribute(ForgeMod.BLOCK_REACH.get()).getValue()), left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Entity Reach: " + StatUtils.formatValue(mc.player.getAttribute(ForgeMod.ENTITY_REACH.get()).getValue()), left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Mining Speed: " + StatUtils.formatValue(data.getDouble("totalMiningSpeed")) + "%", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "XP Gain: " + StatUtils.formatValue(data.getDouble("totalXpGain")) + "%", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Luck: " + StatUtils.formatValue(mc.player.getAttribute(Attributes.LUCK).getValue()), left + 182, y, 0xFFFFFF);
        y += 15;


        // Base Stats & Multipliers
        guiGraphics.drawString(mc.font, "Base Stats & Multipliers", left + 182, y, 0xAAAAFF);
        y += 10;

        // Melee Base Stats & Multipliers
        guiGraphics.drawString(mc.font, "Melee Damage: " + StatUtils.formatValue(data.getDouble("meleeDamageRating")) +
                " | (" + StatUtils.formatValue(1 + data.getDouble("totalMeleeDamageMultiplier") / 100.0) + "x)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Attack Speed: " + StatUtils.formatValue(data.getDouble("attackSpeedRating")) +
                " | (" + StatUtils.formatValue(1 + data.getDouble("totalAttackSpeedMultiplier") / 100.0) + "x)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Melee Crit Chance: " + StatUtils.formatValue(data.getDouble("meleeCritChanceRating")) +
                " | (" + StatUtils.formatValue(1 + data.getDouble("totalMeleeCritChanceMultiplier") / 100.0) + "x)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Melee Crit Damage: " + StatUtils.formatValue(data.getDouble("meleeCritDamageRating")) +
                " | (" + StatUtils.formatValue(1 + data.getDouble("totalMeleeCritDamageMultiplier") / 100.0) + "x)", left + 182, y, 0xFFFFFF);
        y += 15;

        // Projectile Base Stats & Multipliers
        guiGraphics.drawString(mc.font, "Projectile Damage: " + StatUtils.formatValue(data.getDouble("projectileDamageRating")) +
                " | (" + StatUtils.formatValue(1 + data.getDouble("totalProjectileDamageMultiplier") / 100.0) + "x)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Draw Speed: " + StatUtils.formatValue(data.getDouble("drawSpeedRating")) +
                " | (" + StatUtils.formatValue(1 + data.getDouble("totalDrawSpeedMultiplier") / 100.0) + "x)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Projectile Crit Chance: " + StatUtils.formatValue(data.getDouble("projectileCritChanceRating")) +
                " | (" + StatUtils.formatValue(1 + data.getDouble("totalProjectileCritChanceMultiplier") / 100.0) + "x)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Projectile Crit Damage: " + StatUtils.formatValue(data.getDouble("projectileCritDamageRating")) +
                " | (" + StatUtils.formatValue(1 + data.getDouble("totalProjectileCritDamageMultiplier") / 100.0) + "x)", left + 182, y, 0xFFFFFF);
        y += 15;

        // General Base Stats & Multipliers
        guiGraphics.drawString(mc.font, "Damage: " + StatUtils.formatValue(data.getDouble("generalDamageRating")) +
                " | (" + StatUtils.formatValue(1 + data.getDouble("totalGeneralDamageMultiplier") / 100.0) + "x)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Crit Chance: " + StatUtils.formatValue(data.getDouble("generalCritChanceRating")) +
                " | (" + StatUtils.formatValue(1 + data.getDouble("totalGeneralCritChanceMultiplier") / 100.0) + "x)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Crit Damage: " + StatUtils.formatValue(data.getDouble("generalCritDamageRating")) +
                " | (" + StatUtils.formatValue(1 + data.getDouble("totalGeneralCritDamageMultiplier") / 100.0) + "x)", left + 182, y, 0xFFFFFF);
        y += 15;

        guiGraphics.drawString(
                mc.font,
                "Armor: " + StatUtils.formatValue(
                        mc.player.getAttribute(Attributes.ARMOR).getValue() / (1 + data.getDouble("totalArmorMultiplier") / 100)
                ) +
                        " | (" + StatUtils.formatValue(1 + data.getDouble("totalArmorMultiplier") / 100.0) + "x)",
                left + 182, y, 0xFFFFFF
        );
        y += 10;

        guiGraphics.drawString(
                mc.font,
                "Armor Toughness: " + StatUtils.formatValue(
                        mc.player.getAttribute(Attributes.ARMOR_TOUGHNESS).getValue() / (1 + data.getDouble("totalToughnessMultiplier") / 100)
                ) +
                        " | (" + StatUtils.formatValue(1 + data.getDouble("totalToughnessMultiplier") / 100.0) + "x)",
                left + 182, y, 0xFFFFFF
        );
        y += 10;

        guiGraphics.drawString(
                mc.font,
                "Health: " + StatUtils.formatValue(
                        mc.player.getAttribute(Attributes.MAX_HEALTH).getValue() / (1 + data.getDouble("totalHealthMultiplier") / 100)
                ) +
                        " | (" + StatUtils.formatValue(1 + data.getDouble("totalHealthMultiplier") / 100.0) + "x)",
                left + 182, y, 0xFFFFFF
        );
        y += 10;


        int barHeight = 30;
        int barY = top + (int) ((double) scrollOffset / MAX_SCROLL * (PANEL_HEIGHT - barHeight));
        guiGraphics.fill(left + 177, top, left + 179, top + PANEL_HEIGHT, 0xFF555555); // track
        guiGraphics.fill(left + 177, barY, left + 179, barY + barHeight, 0xFFAAAAAA); // handle
    }

    @SubscribeEvent
    public static void onMouseScroll(net.minecraftforge.client.event.ScreenEvent.MouseScrolled event) {
        if (!(event.getScreen() instanceof InventoryScreen)) return;
        if (!showStats) return;

        scrollOffset -= event.getScrollDelta() * 5;
        scrollOffset = Math.max(0, Math.min(scrollOffset, MAX_SCROLL));
        event.setCanceled(true);
    }
}
