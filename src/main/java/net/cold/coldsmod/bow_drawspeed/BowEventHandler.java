package net.cold.coldsmod.bow_drawspeed;

import net.cold.coldsmod.stat.ItemRarityUtils;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.cold.coldsmod.stat.AttributeApplier.getScaledValue;

@Mod.EventBusSubscriber
public class BowEventHandler {

    @SubscribeEvent
    public static void onArrowLoose(ArrowLooseEvent event) {
        Player player = event.getEntity();
        ItemStack bow = event.getBow();

        if (!"bow".equals(ItemRarityUtils.getItemType(bow))) return;

        float drawSpeed = (float) getScaledValue(player, ModAttributes.NOCK_HASTE.get());

        float speedMultiplier = 1.0F + (drawSpeed / 100.0F);

        int charge = event.getCharge();
        int adjustedCharge = (int)(charge * speedMultiplier);
        float power = BowItem.getPowerForTime(adjustedCharge);

        if (power < 0.1F) {event.setCanceled(true);return;}
        event.setCharge(adjustedCharge);
    }
}
