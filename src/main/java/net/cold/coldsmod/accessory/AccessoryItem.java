package net.cold.coldsmod.accessory;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.cold.coldsmod.stat.ItemRarity;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import java.util.List;
import net.cold.coldsmod.stat.CustomStats;
import net.cold.coldsmod.stat.StatUtils;

public class AccessoryItem extends Item implements ICurioItem {

    public enum AccessoryType {
        Ring,
        Necklace,
        Bracer,
        Mind,
        Utility
    }

    public enum AccessoryLocation {
        Hell("Infernal Pact", ChatFormatting.RED),
        Otherworlds("Otherworlds", ChatFormatting.DARK_AQUA),
        End("End of Time", ChatFormatting.LIGHT_PURPLE),
        Desert("Sun's Cruelty", ChatFormatting.YELLOW),
        Ocean("Tsunami Sea", ChatFormatting.AQUA),
        Cold("Whiteout", ChatFormatting.WHITE),
        Undergrounds("Miner's Fortune", ChatFormatting.GRAY),
        Jungle("Nature's Blessing", ChatFormatting.GREEN),
        Pillage("Stolen Wealth", ChatFormatting.DARK_GREEN),
        LostArtifact("Lost Artifacts", ChatFormatting.BLUE);

        private final String displayName;
        private final ChatFormatting chatColor;
        private final TextColor hexColor;

        AccessoryLocation(String displayName, ChatFormatting chatColor) {
            this.displayName = displayName;
            this.chatColor = chatColor;
            this.hexColor = null;
        }

        public Component getColoredName() {
            if (hexColor != null) {
                return Component.literal(displayName).withStyle(Style.EMPTY.withColor(hexColor));
            } else if (chatColor != null) {
                return Component.literal(displayName).withStyle(chatColor);
            } else {
                return Component.literal(displayName);
            }
        }

    }


    private final CustomStats stats;
    private final ItemRarity rarity;
    private final AccessoryType type;
    private final String displayName;
    private final AccessoryLocation location;

    private AccessoryItem(Builder builder) {
        super(builder.properties);
        this.stats = builder.stats;
        this.rarity = builder.rarity;
        this.type = builder.type;
        this.displayName = builder.displayName;
        this.location = builder.location;
    }

    public static Builder builder(Properties properties) {
        return new Builder(properties);
    }

    public static class Builder {
        private final Properties properties;
        private CustomStats stats = new CustomStats();
        private ItemRarity rarity = ItemRarity.RARE;
        private AccessoryType type = AccessoryType.Ring;
        private String displayName = "Unnamed Accessory";
        private AccessoryLocation location = AccessoryLocation.Hell;

        public Builder(Properties properties) {
            this.properties = properties;
        }

        public Builder withStats(CustomStats stats) {
            this.stats = stats;
            return this;
        }

        public Builder withRarity(ItemRarity rarity) {
            this.rarity = rarity;
            return this;
        }

        public Builder withType(AccessoryType type) {
            this.type = type;
            return this;
        }

        public Builder withLocation(AccessoryLocation location) {
            this.location = location;
            return this;
        }

        public Builder withDisplayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public AccessoryItem build() {
            return new AccessoryItem(this);
        }
    }

    public AccessoryType getType() {
        return type;
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (!slotContext.getWearer().level().isClientSide) {
            StatUtils.writeStatsToNBT(stack, this.stats);
        }
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            var handler = top.theillusivec4.curios.api.CuriosApi.getCuriosInventory(player).orElse(null);
            if (handler != null) {
                for (var slot : handler.getCurios().values()) {
                    var stacks = slot.getStacks();
                    for (int i = 0; i < stacks.getSlots(); i++) {
                        ItemStack equipped = stacks.getStackInSlot(i);
                        if (!equipped.isEmpty() && equipped.getItem() == this) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.literal(displayName).withStyle(rarity.color);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal(""));
        tooltip.add(Component.literal("When Equipped:").withStyle(ChatFormatting.GRAY));

        StatUtils.formatStatsTooltip(stats, rarity, tooltip, stack);

        tooltip.add(Component.literal(""));
        tooltip.add(location.getColoredName());
        tooltip.add(Component.literal(rarity.displayName).withStyle(rarity.color));
    }
}
