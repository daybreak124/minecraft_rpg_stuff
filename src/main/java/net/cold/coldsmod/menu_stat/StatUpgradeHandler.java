package net.cold.coldsmod.menu_stat;

import net.cold.coldsmod.ModMessages;
import net.cold.coldsmod.item.ModItems;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.UUID;

import static net.cold.coldsmod.stat.AttributeApplier.*;

public class StatUpgradeHandler {
    public static final int BASE_POINTS = 120;
    public static final int ATTRIBUTE_MAX_LEVEL = 50;
    private static final ResourceLocation VANILLA_TABS = new ResourceLocation("minecraft", "textures/gui/container/creative_inventory/tabs.png");
    public static final UUID ATTRIBUTE_UPGRADE = UUID.fromString("4a221b-221d-5374-a711-998760de");

    public static int getPointsSpent(Player player, Attribute attr) {
        String key = ForgeRegistries.ATTRIBUTES.getKey(attr).toString();
        return player.getPersistentData().getCompound("SpentPointsOne").getInt(key);
    }

    private static void setPointsSpent(Player player, Attribute attr, int points) {
        String key = ForgeRegistries.ATTRIBUTES.getKey(attr).toString();
        CompoundTag persistent = player.getPersistentData();
        if (!persistent.contains("SpentPointsOne")) {
            persistent.put("SpentPointsOne", new CompoundTag());
        }
        persistent.getCompound("SpentPointsOne").putInt(key, points);
    }

    public static int getTotalPointsSpent(Player player) {
        int total = 0;
        CompoundTag tag = player.getPersistentData().getCompound("SpentPointsOne");
        for (String key : tag.getAllKeys()) {
            total += tag.getInt(key);
        }
        return total;
    }

    public static void tryUpgrade(ServerPlayer player, Attribute attribute) {
        int currentLevel = getPointsSpent(player, attribute);
        if (currentLevel >= ATTRIBUTE_MAX_LEVEL) return;

        int totalPoints = getTotalPointsSpent(player);
        if (totalPoints >= BASE_POINTS) {
            player.sendSystemMessage(Component.literal("§cLimit reached."));
            return;
        }

        Item shard = getRequiredShard(totalPoints);
        int amountNeeded = getRequiredAmount(currentLevel);
        if (!hasAndRemoveItem(player, shard, amountNeeded)) {
            player.sendSystemMessage(Component.literal("§cMissing items."));
            return;
        }

        int newLevel = currentLevel + 1;
        setPointsSpent(player, attribute, newLevel);

        // APPLY AS MODIFIER (Not Base Value)
        applyModifier(player, attribute, newLevel, ATTRIBUTE_UPGRADE);

        String attrId = ForgeRegistries.ATTRIBUTES.getKey(attribute).toString();
        ModMessages.sendToPlayer(new StatsSyncPacket(attrId, newLevel, true, false), player);


        refreshPerPointStats(player);
        refreshMilestones(player);
        recalculateDynamicBonuses(player);
        applyCrossbowTag(player);
    }

    public static void tryDowngrade(ServerPlayer player, Attribute attribute) {
        int currentLevel = getPointsSpent(player, attribute);
        if (currentLevel <= 0) return;

        int totalPoints = getTotalPointsSpent(player);
        int amountToReturn = getRequiredAmount(totalPoints - 1);
        Item shardToReturn = getRequiredShard(totalPoints - 1);

        ItemStack stackToReturn = new ItemStack(shardToReturn, amountToReturn);
        player.getInventory().add(stackToReturn);
        if (!player.getInventory().add(stackToReturn)) {
            player.drop(stackToReturn, false);
        }

        int newLevel = currentLevel - 1;
        setPointsSpent(player, attribute, newLevel);

        applyModifier(player, attribute, newLevel, ATTRIBUTE_UPGRADE);

        String attrId = ForgeRegistries.ATTRIBUTES.getKey(attribute).toString();
        ModMessages.sendToPlayer(new StatsSyncPacket(attrId, newLevel, true, false), player);

        refreshPerPointStats(player);
        refreshMilestones(player);
        recalculateDynamicBonuses(player);
        applyCrossbowTag(player);
    }

    public static Item getRequiredShard(int level) {
        return ModItems.SHARD_OF_TRANSCENDENCE.get();
    }

    public static int getRequiredAmount(int level) {
        if (level < 10) return 1;
        if (level < 30) return 2;
        if (level < 55) return 3;
        if (level < 85) return 5;
        return 8;
    }

    private static boolean hasAndRemoveItem(Player player, Item item, int count) {
        int totalFound = 0;
        for (ItemStack stack : player.getInventory().items) {
            if (stack.is(item)) totalFound += stack.getCount();
        }
        if (totalFound < count) return false;
        int leftToRemove = count;
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (stack.is(item)) {
                int take = Math.min(stack.getCount(), leftToRemove);
                stack.shrink(take);
                leftToRemove -= take;
                if (leftToRemove <= 0) break;
            }
        }
        return true;
    }

    @SubscribeEvent
    public static void onScreenInit(ScreenEvent.Init.Post event) {
        if (event.getScreen() instanceof InventoryScreen inv) {
            event.addListener(new ImageButton(
                    inv.getGuiLeft() + 151,
                    inv.getGuiTop() - 25,
                    25, 28,
                    1, 0,
                    32,
                    VANILLA_TABS, 256, 256, (b) -> {
                ModMessages.sendToServer(new OpenStatMenuPacket());
            }
            ) {
                @Override
                public void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
                    super.renderWidget(graphics, mouseX, mouseY, partialTick);
                    graphics.renderFakeItem(new ItemStack(ModItems.PEARL_OF_REVITALIZING.get()), getX() + 4, getY() + 8);
                }
            });
        }
    }
}