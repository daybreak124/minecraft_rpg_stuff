package net.cold.coldsmod.stat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import static net.cold.coldsmod.stat.AttributeApplier.getScaledValue;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class InventoryButtonHandler {

    public static boolean showStats = false;
    private static int scrollOffset = 0;
    private static final int MAX_SCROLL = 750;
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

    public static double attr(Player player, RegistryObject<Attribute> attr) {
        if (player == null) return 0.0;
        var inst = player.getAttribute(attr.get());
        return inst == null ? 0.0 : inst.getValue();
    }



    private static void drawStatsPanel(GuiGraphics guiGraphics, Minecraft mc, int left, int top) {
        guiGraphics.fill(left + 176, top, left + 276, top + PANEL_HEIGHT, 0xD3D3D3);

        double meleePot = getScaledValue(mc.player, ModAttributes.MELEE_POTENCY.get(), ModAttributes.MELEE_POTENCY_MULTIPLIER.get());
        double meleeAcc = getScaledValue(mc.player, ModAttributes.MELEE_ACCURACY.get(), ModAttributes.MELEE_ACCURACY_MULTIPLIER.get());
        double meleePre = getScaledValue(mc.player, ModAttributes.MELEE_PRECISION.get(), ModAttributes.MELEE_PRECISION_MULTIPLIER.get());

        double projPot = getScaledValue(mc.player, ModAttributes.PROJECTILE_POTENCY.get(), ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get());
        double projAcc = getScaledValue(mc.player, ModAttributes.PROJECTILE_ACCURACY.get(), ModAttributes.PROJECTILE_ACCURACY_MULTIPLIER.get());
        double projPre = getScaledValue(mc.player, ModAttributes.PROJECTILE_PRECISION.get(), ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get());

        double haste = getScaledValue(mc.player, ModAttributes.HASTE.get(), ModAttributes.HASTE_MULTIPLIER.get());
        double nockHaste = getScaledValue(mc.player, ModAttributes.NOCK_HASTE.get(), ModAttributes.NOCK_HASTE_MULTIPLIER.get());

        double effectiveMeleeCritChance = Math.min(10.0 + meleeAcc, 100.0) / 100.0;
        double effectiveProjectileCritChance = Math.min(10.0 + projAcc, 100.0) / 100.0;

        double meleeCritBonus = 0.5 + (meleePre / 100.0);
        double projCritBonus = 0.5 + (projPre / 100.0);

        double avgMeleeDamage = ((1.0 + meleePot / 100.0)
                * (1.0 + (effectiveMeleeCritChance * meleeCritBonus))
                * (1.0 + haste / 100.0)) / 1.05;

        double maxMeleeDamage = ((1.0 + meleePot / 100.0)
                * (1.0 + meleeCritBonus)
                * (1.0 + haste / 100.0)) / 1.05;

        double avgBowDamage = ((1.0 + projPot / 100.0)
                * (1.0 + (effectiveProjectileCritChance * projCritBonus))
                * (1.0 + nockHaste / 100.0)) / 1.05;

        // --- Armor and survivability ---
        double armorVal = mc.player.getAttributeValue(Attributes.ARMOR);
        double toughnessVal = mc.player.getAttributeValue(Attributes.ARMOR_TOUGHNESS);

        double armorReduction = armorVal / (80.0 + armorVal - 80.0 * (toughnessVal / (toughnessVal + 50.0)));

        double incDamageMultiplier = mc.player.getAttributeValue(ModAttributes.INCOMING_DAMAGE_MULTIPLIER.get());

        double resistance = 0.0;
        if (mc.player.hasEffect(MobEffects.DAMAGE_RESISTANCE)) {
            resistance = (mc.player.getEffect(MobEffects.DAMAGE_RESISTANCE).getAmplifier() + 1) * 0.2;
        }

        int totalProtLevel = 0;
        for (ItemStack armorPiece : mc.player.getArmorSlots()) {
            totalProtLevel += EnchantmentHelper.getItemEnchantmentLevel(Enchantments.ALL_DAMAGE_PROTECTION, armorPiece);
        }
        double protReduction = totalProtLevel * 0.02;
        double totalDamageMultiplier = (1.0 - armorReduction) * (1.0 - protReduction) * (1.0 - resistance) * incDamageMultiplier;
        // PROT: 1 - ((1 - 100/(100 + value*3)) * 100) for modpacks where you can get a lot of prot
        double totalReduction = (1.0 - totalDamageMultiplier) * 100.0;
        // ---------------------------------------------------------------------------------------------------

        double finalSpeed = 1000 * mc.player.getAttribute(Attributes.MOVEMENT_SPEED).getValue() - 100;
        double finalSwimSpeed = 100 * mc.player.getAttribute(ForgeMod.SWIM_SPEED.get()).getValue() - 100;

        int y = top - scrollOffset;

        guiGraphics.drawString(mc.font, "Stats", left + 182, y, 0xFFFF55);
        y += 15;

        guiGraphics.drawString(mc.font, "Attributes", left + 182, y, 0xAAAAFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Strength: " + StatUtils.formatValue(attr(mc.player, ModAttributes.STR)), left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Dexterity: " + StatUtils.formatValue(attr(mc.player, ModAttributes.DEX)), left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Fortitude: " + StatUtils.formatValue(attr(mc.player, ModAttributes.FORT)), left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Constitution: " + StatUtils.formatValue(attr(mc.player, ModAttributes.CON)), left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Perception: " + StatUtils.formatValue(attr(mc.player, ModAttributes.PERC)), left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Intelligence: " + StatUtils.formatValue(attr(mc.player, ModAttributes.INTELLIGENCE)), left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Wisdom: " + StatUtils.formatValue(attr(mc.player, ModAttributes.WISDOM)), left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Insight: " + StatUtils.formatValue(attr(mc.player, ModAttributes.INSIGHT)), left + 182, y, 0xFFFFFF);
        y += 15;

        // --- Survivability ---
        guiGraphics.drawString(mc.font, "Survivability", left + 182, y, 0xAAAAFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Armor: " + StatUtils.formatValue(mc.player.getAttribute(Attributes.ARMOR).getValue()), left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Toughness: " + StatUtils.formatValue(mc.player.getAttribute(Attributes.ARMOR_TOUGHNESS).getValue()), left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Damage Multiplier: " +
                        StatUtils.formatValue((mc.player.getAttributeValue(ModAttributes.INCOMING_DAMAGE_MULTIPLIER.get()))) + "x",
                left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Total Damage Resist: " + StatUtils.formatValue(totalReduction) + "%", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Health: " + StatUtils.formatValue(mc.player.getAttribute(Attributes.MAX_HEALTH).getValue()), left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Knockback Resist: " +
                StatUtils.formatValue(mc.player.getAttribute(Attributes.KNOCKBACK_RESISTANCE).getValue() * 100) + "%", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Debuff Resist: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.DEBUFF_RESIST)) + "%", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Incoming Healing: " +
                        StatUtils.formatValue(mc.player.getAttributeValue(ModAttributes.INCOMING_DAMAGE_MULTIPLIER.get())) + "x",
                left + 182, y, 0xFFFFFF);
        y += 15;

        // --- Melee ---
        guiGraphics.drawString(mc.font, "Melee", left + 182, y, 0xAAAAFF);
        y += 10;
        // Potency: Total | (Scaled%)
        guiGraphics.drawString(mc.font, "Potency: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.MELEE_POTENCY) * attr(mc.player, ModAttributes.MELEE_POTENCY_MULTIPLIER)) +
                " | (" + StatUtils.formatValue(meleePot) + "%)", left + 182, y, 0xFFFFFF);
        y += 10;
        // Haste: Total | (Scaled%)
        guiGraphics.drawString(mc.font, "Haste: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.HASTE) * attr(mc.player, ModAttributes.HASTE_MULTIPLIER)) +
                " | (" + StatUtils.formatValue(haste) + "%)", left + 182, y, 0xFFFFFF);
        y += 10;
        // Accuracy: Total | (Scaled + 10%)
        guiGraphics.drawString(mc.font, "Accuracy: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.MELEE_ACCURACY) * attr(mc.player, ModAttributes.MELEE_ACCURACY_MULTIPLIER)) +
                " | (" + StatUtils.formatValue(meleeAcc + 10.0) + "%)", left + 182, y, 0xFFFFFF);
        y += 10;
        // Precision: Total | (Scaled + 50%)
        guiGraphics.drawString(mc.font, "Precision: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.MELEE_PRECISION) * attr(mc.player, ModAttributes.MELEE_PRECISION_MULTIPLIER)) +
                " | (" + StatUtils.formatValue(meleePre + 50.0) + "%)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Avg Increase: " + StatUtils.formatValue(avgMeleeDamage) + "x", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Max Increase: " + StatUtils.formatValue(maxMeleeDamage) + "x", left + 182, y, 0xFFFFFF);
        y += 15;

        // --- Ranged ---
        guiGraphics.drawString(mc.font, "Ranged", left + 182, y, 0xAAAAFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Potency: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.PROJECTILE_POTENCY) * attr(mc.player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER)) +
                " | (" + StatUtils.formatValue(projPot) + "%)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Nock Haste: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.NOCK_HASTE) * attr(mc.player, ModAttributes.NOCK_HASTE_MULTIPLIER)) +
                " | (" + StatUtils.formatValue(nockHaste) + "%)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Accuracy: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.PROJECTILE_ACCURACY) * attr(mc.player, ModAttributes.PROJECTILE_ACCURACY_MULTIPLIER)) +
                " | (" + StatUtils.formatValue(projAcc + 10.0) + "%)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Precision: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.PROJECTILE_PRECISION) * attr(mc.player, ModAttributes.PROJECTILE_PRECISION_MULTIPLIER)) +
                " | (" + StatUtils.formatValue(projPre + 50.0) + "%)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Increase: " + StatUtils.formatValue(avgBowDamage) + "x", left + 182, y, 0xFFFFFF);
        y += 15;

        // --- General ---
        guiGraphics.drawString(mc.font, "General", left + 182, y, 0xAAAAFF);
        y += 10;

        guiGraphics.drawString(mc.font, "Final Damage Multiplier: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.OUTGOING_DAMAGE_MULTIPLIER)) +
                "x", left + 182, y, 0xFFFFFF);
        y += 10;

        guiGraphics.drawString(mc.font, "Potency: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.POTENCY) * attr(mc.player, ModAttributes.POTENCY_MULTIPLIER)) +
                " | (" + StatUtils.formatValue(projPot) + "%)", left + 182, y, 0xFFFFFF);
        y += 10;

        guiGraphics.drawString(mc.font, "Potency: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.POTENCY) * attr(mc.player, ModAttributes.POTENCY_MULTIPLIER)) +
                " | (" + StatUtils.formatValue(projPot) + "%)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Accuracy: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.ACCURACY) * attr(mc.player, ModAttributes.ACCURACY_MULTIPLIER)) +
                " | (" + StatUtils.formatValue(projAcc + 10.0) + "%)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Precision: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.PRECISION) * attr(mc.player, ModAttributes.PRECISION_MULTIPLIER)) +
                " | (" + StatUtils.formatValue(projPre + 50.0) + "%)", left + 182, y, 0xFFFFFF);
        y += 15;

        // --- Healing ---
        guiGraphics.drawString(mc.font, "Healing", left + 182, y, 0xAAAAFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Restoration: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.RESTORATION) * attr(mc.player, ModAttributes.RESTORATION_MULTIPLIER)) +
                " | (" + StatUtils.formatValue(getScaledValue(mc.player, ModAttributes.RESTORATION.get(), ModAttributes.RESTORATION_MULTIPLIER.get())) + "%)", left + 182, y, 0xFFFFFF);
        y += 10;
//        guiGraphics.drawString(mc.font, "Protection: " +
//                StatUtils.formatValue(attr(mc.player, ModAttributes.PROTECTION) * attr(mc.player, ModAttributes.PROTECTION_MULTIPLIER)) +
//                " | (" + StatUtils.formatValue(getScaledValue(mc.player, ModAttributes.PROTECTION.get(), ModAttributes.PROTECTION_MULTIPLIER.get())) + "%)", left + 182, y, 0xFFFFFF);
//        y += 10;
        guiGraphics.drawString(mc.font, "Amplification: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.AMPLIFICATION) * attr(mc.player, ModAttributes.AMPLIFICATION_MULTIPLIER)) +
                " | (" + StatUtils.formatValue(getScaledValue(mc.player, ModAttributes.AMPLIFICATION.get(), ModAttributes.AMPLIFICATION_MULTIPLIER.get())) + "%)", left + 182, y, 0xFFFFFF);
        y += 15;

        // --- Movement ---
        guiGraphics.drawString(mc.font, "Movement", left + 182, y, 0xAAAAFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Move Speed: " + StatUtils.formatValue(finalSpeed) + "%", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Swim Speed: " + StatUtils.formatValue(finalSwimSpeed) + "%", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Step Height: " +
                StatUtils.formatValue(mc.player.getAttribute(ForgeMod.STEP_HEIGHT_ADDITION.get()).getValue()), left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Jump Boost: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.JUMP_BOOST)) + "%", left + 182, y, 0xFFFFFF);
        y += 15;

        // --- Misc ---
        guiGraphics.drawString(mc.font, "Miscellaneous", left + 182, y, 0xAAAAFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Block Reach: " +
                StatUtils.formatValue(mc.player.getAttribute(ForgeMod.BLOCK_REACH.get()).getValue()), left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Entity Reach: " +
                StatUtils.formatValue(mc.player.getAttribute(ForgeMod.ENTITY_REACH.get()).getValue()), left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Mining Speed: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.MINING_SPEED)) + "%", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "XP Gain: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.XP_GAIN)) + "%", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Luck: " +
                StatUtils.formatValue(mc.player.getAttribute(Attributes.LUCK).getValue()), left + 182, y, 0xFFFFFF);
        y += 15;


        // --- Base and Multipliers ---
        guiGraphics.drawString(mc.font, "Base and Multipliers", left + 182, y, 0xFFFF55);
        y += 15;
        guiGraphics.drawString(mc.font, "Melee", left + 182, y, 0xAAAAFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Potency: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.MELEE_POTENCY)) +
                " | (" + StatUtils.formatValue(attr(mc.player, ModAttributes.MELEE_POTENCY_MULTIPLIER)) + "x)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Haste: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.HASTE)) +
                " | (" + StatUtils.formatValue(attr(mc.player, ModAttributes.HASTE_MULTIPLIER)) + "x)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Accuracy: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.MELEE_ACCURACY)) +
                " | (" + StatUtils.formatValue(attr(mc.player, ModAttributes.MELEE_ACCURACY_MULTIPLIER)) + "x)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Precision: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.MELEE_PRECISION)) +
                " | (" + StatUtils.formatValue(attr(mc.player, ModAttributes.MELEE_PRECISION_MULTIPLIER)) + "x)", left + 182, y, 0xFFFFFF);
        y += 15;

        // --- Ranged ---
        guiGraphics.drawString(mc.font, "Ranged", left + 182, y, 0xAAAAFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Potency: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.PROJECTILE_POTENCY)) +
                " | (" + StatUtils.formatValue(attr(mc.player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER)) + "x)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Nock Haste: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.NOCK_HASTE)) +
                " | (" + StatUtils.formatValue(attr(mc.player, ModAttributes.NOCK_HASTE_MULTIPLIER)) + "x)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Accuracy: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.PROJECTILE_ACCURACY)) +
                " | (" + StatUtils.formatValue(attr(mc.player, ModAttributes.PROJECTILE_ACCURACY_MULTIPLIER)) + "x)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Precision: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.PROJECTILE_PRECISION)) +
                " | (" + StatUtils.formatValue(attr(mc.player, ModAttributes.PROJECTILE_PRECISION_MULTIPLIER)) + "x)", left + 182, y, 0xFFFFFF);
        y += 15;

        // --- General ---
        guiGraphics.drawString(mc.font, "General", left + 182, y, 0xAAAAFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Potency: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.POTENCY)) +
                " | (" + StatUtils.formatValue(attr(mc.player, ModAttributes.POTENCY_MULTIPLIER)) + "x)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Accuracy: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.ACCURACY)) +
                " | (" + StatUtils.formatValue(attr(mc.player, ModAttributes.ACCURACY_MULTIPLIER)) + "x)", left + 182, y, 0xFFFFFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Precision: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.PRECISION)) +
                " | (" + StatUtils.formatValue(attr(mc.player, ModAttributes.PRECISION_MULTIPLIER)) + "x)", left + 182, y, 0xFFFFFF);
        y += 15;

        // --- Healing ---
        guiGraphics.drawString(mc.font, "Healing", left + 182, y, 0xAAAAFF);
        y += 10;
        guiGraphics.drawString(mc.font, "Restoration: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.RESTORATION)) +
                " | (" + StatUtils.formatValue(attr(mc.player, ModAttributes.RESTORATION_MULTIPLIER)) + "x)", left + 182, y, 0xFFFFFF);
        y += 10;
//        guiGraphics.drawString(mc.font, "Protection: " +
//                StatUtils.formatValue(attr(mc.player, ModAttributes.PROTECTION)) +
//                " | (" + StatUtils.formatValue(attr(mc.player, ModAttributes.PROTECTION_MULTIPLIER)) + "x)", left + 182, y, 0xFFFFFF);
//        y += 10;
        guiGraphics.drawString(mc.font, "Amplification: " +
                StatUtils.formatValue(attr(mc.player, ModAttributes.AMPLIFICATION)) +
                " | (" + StatUtils.formatValue(attr(mc.player, ModAttributes.AMPLIFICATION_MULTIPLIER)) + "x)", left + 182, y, 0xFFFFFF);
        y += 15;
        int barHeight = 30;
        int barY = top + (scrollOffset / MAX_SCROLL * (PANEL_HEIGHT - barHeight));
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
