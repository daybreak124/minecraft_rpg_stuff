package net.cold.coldsmod.stat;

import net.cold.coldsmod.ModMessages;
import net.cold.coldsmod.item.ModItems;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.UUID;

import static net.cold.coldsmod.stat.AttributeApplier.applyModifier;
import static net.cold.coldsmod.stat.StatUpgradeHandler.getRequiredAmount;

public class StatUpgradeHandlerTwo {
    public static final int MAX_GLOBAL_POINTS = 120;
    private static final ResourceLocation VANILLA_TABS = new ResourceLocation("minecraft", "textures/gui/container/creative_inventory/tabs.png");
    protected static final UUID STAT_MODIFIER_UUID = UUID.fromString("6d6224-c01d-5374-a711-5557c0de");

    public static double getIncrementFor(Attribute attr) {
        if (attr == Attributes.ARMOR) return 1.25;
        if (attr == Attributes.ARMOR_TOUGHNESS) return 1.00;
        if (attr == Attributes.KNOCKBACK_RESISTANCE) return 0.05;
        if (attr == Attributes.MAX_HEALTH) return 0.6;
        if (attr == Attributes.MOVEMENT_SPEED) return 0.001;
        if (attr == ModAttributes.DEBUFF_RESIST.get()) return 4;

        if (attr == ModAttributes.POTENCY.get()) return 1;
        if (attr == ModAttributes.ACCURACY.get()) return 2.5;
        if (attr == ModAttributes.PRECISION.get()) return 2;

        if (attr == ModAttributes.MELEE_POTENCY.get()) return 1.5;
        if (attr == ModAttributes.HASTE.get()) return 1.25;
        if (attr == ModAttributes.MELEE_ACCURACY.get()) return 3.75;
        if (attr == ModAttributes.MELEE_PRECISION.get()) return 3;

        if (attr == ModAttributes.PROJECTILE_POTENCY.get()) return 1.5;
        if (attr == ModAttributes.NOCK_HASTE.get()) return 1.25;
        if (attr == ModAttributes.PROJECTILE_ACCURACY.get()) return 3.75;
        if (attr == ModAttributes.PROJECTILE_PRECISION.get()) return 3;

        if (attr == ModAttributes.REJUVENATION.get()) return 1.5;
        if (attr == ModAttributes.RESTORATION.get()) return 1.5;
        if (attr == ModAttributes.AMPLIFICATION.get()) return 2.25;

        return 1.0;
    }

    public static int getMaxPointsFor(Attribute attr) {
        if (attr == Attributes.ARMOR) return 24;
        if (attr == Attributes.ARMOR_TOUGHNESS) return 15;
        if (attr == Attributes.KNOCKBACK_RESISTANCE) return 10;
        if (attr == Attributes.MAX_HEALTH) return 30;
        if (attr == Attributes.MOVEMENT_SPEED) return 25;
        if (attr == ModAttributes.DEBUFF_RESIST.get()) return 10;

        if (attr == ModAttributes.POTENCY.get()) return 10;
        if (attr == ModAttributes.ACCURACY.get()) return 10;
        if (attr == ModAttributes.PRECISION.get()) return 10;

        if (attr == ModAttributes.MELEE_POTENCY.get()) return 10;
        if (attr == ModAttributes.HASTE.get()) return 10;
        if (attr == ModAttributes.MELEE_ACCURACY.get()) return 12;
        if (attr == ModAttributes.MELEE_PRECISION.get()) return 15;

        if (attr == ModAttributes.PROJECTILE_POTENCY.get()) return 10;
        if (attr == ModAttributes.NOCK_HASTE.get()) return 10;
        if (attr == ModAttributes.PROJECTILE_ACCURACY.get()) return 12;
        if (attr == ModAttributes.PROJECTILE_PRECISION.get()) return 15;

        if (attr == ModAttributes.REJUVENATION.get()) return 20;
        if (attr == ModAttributes.RESTORATION.get()) return 25;
        if (attr == ModAttributes.AMPLIFICATION.get()) return 30;

        return 20;
    }

    // This reads the actual "Spent Points" integer from NBT
    public static int getPointsSpent(Player player, Attribute attr) {
        String key = ForgeRegistries.ATTRIBUTES.getKey(attr).toString();
        CompoundTag data = player.getPersistentData().getCompound("SpentPoints");
        return data.getInt(key);
    }

    private static void setPointsSpent(Player player, Attribute attr, int points) {
        String key = ForgeRegistries.ATTRIBUTES.getKey(attr).toString();
        CompoundTag persistent = player.getPersistentData();
        if (!persistent.contains("SpentPoints")) {
            persistent.put("SpentPoints", new CompoundTag());
        }
        persistent.getCompound("SpentPoints").putInt(key, points);
    }

    public static int getTotalPointsSpent(Player player) {
        int total = 0;
        CompoundTag tag = player.getPersistentData().getCompound("SpentPoints");
        for (String key : tag.getAllKeys()) {
            total += tag.getInt(key);
        }
        return total;
    }

    public static Item getRequiredPearl(int level) {
        return ModItems.PEARL_OF_REVITALIZING.get();
    }

    public static void tryUpgrade(ServerPlayer player, Attribute attribute) {
        int points = getPointsSpent(player, attribute);
        if (points >= getMaxPointsFor(attribute)) return;

        int globalPoints = getTotalPointsSpent(player);
        if (globalPoints >= MAX_GLOBAL_POINTS) {
            player.sendSystemMessage(Component.literal("§cLimit reached!"));
            return;
        }

        Item pearl = getRequiredPearl(globalPoints);
        int amountNeeded = getRequiredAmount(globalPoints);

        if (!hasAndRemoveItem(player, pearl, amountNeeded)) {
            player.sendSystemMessage(Component.literal("§cNeed " + amountNeeded + "x " + pearl.getDescription().getString()));
            return;
        }

        int newPoints = points + 1;
        setPointsSpent(player, attribute, newPoints);

        double amount = newPoints * getIncrementFor(attribute);
        applyModifier(player, attribute, amount, STAT_MODIFIER_UUID);

        String attrId = ForgeRegistries.ATTRIBUTES.getKey(attribute).toString();
        ModMessages.sendToPlayer(new StatsSyncPacket(attrId, newPoints, false), player);
    }

    public static void tryDowngrade(ServerPlayer player, Attribute attribute) {
        int points = getPointsSpent(player, attribute);
        if (points <= 0) return;

        int globalPoints = getTotalPointsSpent(player);
        Item pearlToReturn = getRequiredPearl(globalPoints - 1);
        int amountToReturn = getRequiredAmount(globalPoints - 1);
        player.getInventory().add(new ItemStack(pearlToReturn, amountToReturn));

        int newPoints = points - 1;
        setPointsSpent(player, attribute, newPoints);

        double amount = newPoints * getIncrementFor(attribute);
        applyModifier(player, attribute, amount, STAT_MODIFIER_UUID);

        String attrId = ForgeRegistries.ATTRIBUTES.getKey(attribute).toString();
        ModMessages.sendToPlayer(new StatsSyncPacket(attrId, newPoints, false), player);
    }

    private static boolean hasAndRemoveItem(Player player, Item item, int count) {
        int found = 0;
        for (ItemStack s : player.getInventory().items) if (s.is(item)) found += s.getCount();
        if (found < count) return false;
        int toRemove = count;
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack s = player.getInventory().getItem(i);
            if (s.is(item)) {
                int take = Math.min(s.getCount(), toRemove);
                s.shrink(take);
                toRemove -= take;
                if (toRemove <= 0) break;
            }
        }
        return true;
    }

    @SubscribeEvent
    public static void onScreenInit(ScreenEvent.Init.Post event) {
        if (event.getScreen() instanceof InventoryScreen inv) {
            event.addListener(new ImageButton(inv.getGuiLeft() + 150, inv.getGuiTop() - 28, 28, 32, 0, 2, 32, VANILLA_TABS, 256, 256, (b) -> {
                ModMessages.sendToServer(new OpenStatMenuPacket());
            }));
        }
    }
}