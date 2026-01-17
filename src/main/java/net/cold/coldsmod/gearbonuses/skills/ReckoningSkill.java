package net.cold.coldsmod.gearbonuses.skills;

import net.cold.coldsmod.ModSounds;
import net.cold.coldsmod.damage.ModDamageTypes;
import net.cold.coldsmod.gearbonuses.effects.ModEffects;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ReckoningSkill {

    private static final int ACTIVE_DURATION = 20 * 10; // 10 seconds
    private static final double HEAL_PERCENT = 0.4; // 40%

    private static final String HEALED_NBT = "reckoningHealed";

    @SubscribeEvent
    public static void onPlayerDamage(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.level().isClientSide) return;

        if (!player.hasEffect(ModEffects.RECKONING.get())) return;
        if (event.getSource().is(DamageTypes.FALL)) return;

        player.removeEffect(ModEffects.RECKONING.get());
        player.addEffect(new MobEffectInstance(ModEffects.RECKONING_ACTIVE.get(), ACTIVE_DURATION, 0, false, false, true));

        player.playSound(ModSounds.RECKONING_ACTIVE.get(), 0.3F, 1.0F);

        player.getPersistentData().putDouble(HEALED_NBT, 0);
    }

    @SubscribeEvent
    public static void onReckoningHeal(LivingDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.level().isClientSide) return;
        if (event.getSource().is(ModDamageTypes.RECKONING)) return;
        if (event.getSource().is(DamageTypes.FALL)) return;


        if (player.hasEffect(ModEffects.RECKONING_ACTIVE.get())) {
            double healed = event.getAmount() * HEAL_PERCENT;

            player.heal((float) healed);

            double totalHealed = player.getPersistentData().getDouble(HEALED_NBT);
            totalHealed += healed;
            player.getPersistentData().putDouble(HEALED_NBT, totalHealed);
        }
    }
}
