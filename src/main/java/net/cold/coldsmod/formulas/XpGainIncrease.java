package net.cold.coldsmod.formulas;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class XpGainIncrease {

    @SubscribeEvent
    public static void onXpGain(PlayerXpEvent.XpChange event) {
        if (event.getEntity().level().isClientSide()) return;
        Player player = event.getEntity();

        double xpMultiplier = player.getAttributeValue(ModAttributes.XP_GAIN.get());
        if (xpMultiplier == 0) return;

        int baseXp = event.getAmount();

        int finalXp = (int) Math.round(baseXp * ((xpMultiplier)));
        event.setAmount(finalXp);
    }
}
