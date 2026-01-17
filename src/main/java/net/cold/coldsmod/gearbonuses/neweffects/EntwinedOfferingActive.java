package net.cold.coldsmod.gearbonuses.neweffects;

import net.cold.coldsmod.gearbonuses.effects.ModEffects;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class EntwinedOfferingActive extends MobEffect {

    public EntwinedOfferingActive() {
        super(MobEffectCategory.NEUTRAL, 0xFF0000); // category + color
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false; // only trigger via events
    }

    @SubscribeEvent
    public void onHeal(LivingHealEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.level().isClientSide) return;
        if (player.getHealth() == player.getMaxHealth()) return;
        if (!player.getPersistentData().getBoolean("entwined_offering_eligible")) return;

        Level level = player.level();

        float range = (float) (8.0f * (1.0f + (AttributeApplier.getScaledValue(player, ModAttributes.AMPLIFICATION.get(), ModAttributes.AMPLIFICATION_MULTIPLIER.get()) / 100.0f)));
        int armorDuration = (int) (20 * 5 * (1.0f + (AttributeApplier.getScaledValue(player, ModAttributes.AMPLIFICATION.get(), ModAttributes.AMPLIFICATION_MULTIPLIER.get()) / 100.0f)));

        List<LivingEntity> entities = level.getEntitiesOfClass(
                LivingEntity.class,
                player.getBoundingBox().inflate(range),
                e -> ((e != player) && e instanceof Player || e instanceof TamableAnimal tamable && tamable.isTame())
        );

        for (LivingEntity target : entities) {
            if (target.getHealth() < target.getMaxHealth() && !(target.hasEffect(ModEffects.BlACKENED_HEART.get()))) {
                target.heal((float) (event.getAmount() * 0.3));
                target.addEffect(new MobEffectInstance(ModEffects.ENTWINED_OFFERING_ACTIVE.get(), armorDuration, 0, false, false, true));
            }
        }
    }
}
