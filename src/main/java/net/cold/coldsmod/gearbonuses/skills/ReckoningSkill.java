package net.cold.coldsmod.gearbonuses.skills;

import net.cold.coldsmod.ModSounds;
import net.cold.coldsmod.damage.ModDamageTypes;
import net.cold.coldsmod.gearbonuses.effects.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ReckoningSkill {

    private static final int ACTIVE_DURATION = 20 * 10; // 10 seconds
    private static final double HEAL_PERCENT = 0.4; // 40%

    private static final String HEALED_NBT = "reckoningHealed";

    @SubscribeEvent
    public static void onPlayerDamage(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.level().isClientSide) return;
        if (!player.getPersistentData().getBoolean("reckoning_eligible")) return;

        if (!player.hasEffect(ModEffects.RECKONING.get())) return;

        player.removeEffect(ModEffects.RECKONING.get());
        player.addEffect(new MobEffectInstance(ModEffects.RECKONING_ACTIVE.get(), ACTIVE_DURATION, 0, false, false));

        player.level().playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                ModSounds.RECKONING_ACTIVE.get(),
                SoundSource.PLAYERS,
                0.3F,
                1.0F
        );

        player.getPersistentData().putDouble(HEALED_NBT, 0);
    }

    @SubscribeEvent
    public static void onReckoningHeal(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.level().isClientSide) return;


        if (player.hasEffect(ModEffects.RECKONING_ACTIVE.get())) {
            double healed = event.getAmount() * HEAL_PERCENT;

            player.heal((float) healed);

            double totalHealed = player.getPersistentData().getDouble(HEALED_NBT);
            totalHealed += healed;
            player.getPersistentData().putDouble(HEALED_NBT, totalHealed);
        }
    }

    @SubscribeEvent
    public static void onCooldownApplied(MobEffectEvent.Added event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.level().isClientSide) return;

        if (event.getEffectInstance().getEffect() != ModEffects.RECKONING_COOLDOWN.get()) return;

        double healed = player.getPersistentData().getDouble(HEALED_NBT);
        if (healed <= 0) return;

        double damageBack = healed * 0.5;

        Holder<DamageType> reckoningType = player.level().registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolder(ModDamageTypes.RECKONING)
                .orElseThrow();

        DamageSource reckoningSource = new DamageSource(reckoningType);
        player.hurt(reckoningSource, (float) damageBack);


        player.level().playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                ModSounds.RECKONING_BOOM.get(),
                SoundSource.PLAYERS,
                0.6F,
                1.0F
        );

        player.getPersistentData().putDouble(HEALED_NBT, 0);
    }
}
