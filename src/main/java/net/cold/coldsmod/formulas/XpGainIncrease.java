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
        Player player = event.getEntity();
        int baseXp = event.getAmount();

        double xpMultiplier = player.getAttributeValue(ModAttributes.XP_GAIN.get());

        int finalXp = (int) Math.round(baseXp * (1.0 + (xpMultiplier / 100.0)));
        event.setAmount(finalXp);
    }
}
