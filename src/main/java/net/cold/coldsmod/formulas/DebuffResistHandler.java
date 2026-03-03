package net.cold.coldsmod.formulas;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DebuffResistHandler {

    @SubscribeEvent
    public static void onEffectApplicable(MobEffectEvent.Applicable event) {
        if (event.getEntity().level().isClientSide() || !(event.getEntity() instanceof Player player)) return;

        MobEffectInstance inst = event.getEffectInstance();
        MobEffect effect = inst.getEffect();

        if (effect.getCategory() != MobEffectCategory.HARMFUL) return;

        double resist = player.getAttributeValue(ModAttributes.DEBUFF_RESIST.get());

        if (isProcessing()) return;

        double multiplier = 1.0 - (resist / 100.0);
        int reducedDur = (int) Math.round(inst.getDuration() * multiplier);

        event.setResult(net.minecraftforge.eventbus.api.Event.Result.DENY);

        if (reducedDur > 0) {
            startProcessing();
            try {
                player.addEffect(new MobEffectInstance(effect, reducedDur, inst.getAmplifier(), inst.isAmbient(), inst.isVisible(), inst.showIcon()));
            } finally {
                endProcessing();
            }
        }
    }

    // Logic to prevent the manual addEffect from re-triggering this event
    private static final ThreadLocal<Boolean> PROCESSING = ThreadLocal.withInitial(() -> false);
    private static boolean isProcessing() { return PROCESSING.get(); }
    private static void startProcessing() { PROCESSING.set(true); }
    private static void endProcessing() { PROCESSING.set(false); }
}
