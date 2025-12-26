package net.cold.coldsmod.formulas;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraft.world.entity.player.Player;

@Mod.EventBusSubscriber
public class XpGainIncrease {

    @SubscribeEvent
    public static void onXpGain(PlayerXpEvent.XpChange event) {
        Player player = event.getEntity(); // get player
        int baseXp = event.getAmount();    // original XP

        double totalXpGain = player.getPersistentData().getDouble("totalXpGain");

        int finalXp = (int) Math.round(baseXp * (1.0 + totalXpGain / 100.0));
        event.setAmount(finalXp);
    }
}
