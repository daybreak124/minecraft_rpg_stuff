package net.cold.coldsmod.gearbonuses.skills;

import net.cold.coldsmod.damage.CustomMeleeDamage;
import net.cold.coldsmod.damage.ModDamageTypes;
import net.cold.coldsmod.gearbonuses.effects.ModEffects;
import net.cold.coldsmod.stat.AttributeApplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class IntoTheFraySkill {

    private static final float DAMAGE_PER_STACK = 4f;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        if (player.level().isClientSide) return;
        if (!player.getPersistentData().getBoolean("into_the_fray_eligible")) return;

        boolean sprinting = player.isSprinting();
        int sprintTicks = player.getPersistentData().getInt("sprintTicks");
        int stackCount = player.getPersistentData().getInt("itfStacks");

        if (sprinting && !player.hasEffect(ModEffects.INTO_THE_FRAY_COOLDOWN.get())) {
            sprintTicks++;
            player.getPersistentData().putInt("sprintTicks", sprintTicks);

            int itfAmplifier = 0;
            boolean giveAbsorption = false;

            if (sprintTicks >= 220) {      // 5th stack
                itfAmplifier = 4;
                giveAbsorption = true;     // Absorption I at 5 stacks
            } else if (sprintTicks >= 180) { // 4th stack
                itfAmplifier = 3;
            } else if (sprintTicks >= 140) { // 3rd stack
                itfAmplifier = 2;
            } else if (sprintTicks >= 100) {  // 2nd stack
                itfAmplifier = 1;
            } else if (sprintTicks >= 60) {  // 1st stack
                itfAmplifier = 0;
            }

            if (sprintTicks <= 60) return;

            player.getPersistentData().putInt("itfStacks", itfAmplifier + 1);

            MobEffectInstance currentItf = player.getEffect(ModEffects.INTO_THE_FRAY.get());
            if (currentItf == null || currentItf.getAmplifier() != itfAmplifier) {
                player.addEffect(new MobEffectInstance(ModEffects.INTO_THE_FRAY.get(), 40, itfAmplifier, true, true));
                new AttributeApplier().applyITF(player);
            }

            if (giveAbsorption) {
                MobEffectInstance currentAbs = player.getEffect(MobEffects.ABSORPTION);
                if (currentAbs == null || currentAbs.getAmplifier() != 0) { // Absorption I
                    player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 40, 0, true, true));
                }
            } else {
                player.removeEffect(MobEffects.ABSORPTION);
            }

            Level level = player.level();
            List<LivingEntity> entities = level.getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(0.6),
                    e -> !(e instanceof Player) && e.isAlive() && !e.isInvulnerable()
            );

            for (LivingEntity target : entities) {

                DamageSource source = new CustomMeleeDamage(
                        level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
                                .getHolderOrThrow(ModDamageTypes.EXPLOSION_DAMAGE),
                        player
                );

                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 8 * stackCount, 3));
                double dx = target.getX() - player.getX();
                double dz = target.getZ() - player.getZ();
                target.knockback(0.5f * stackCount, dx, dz);

                for (LivingEntity entity : level.getEntitiesOfClass(
                        LivingEntity.class,
                        player.getBoundingBox().inflate(4),
                        e -> e != player && e.isAlive() && !e.isInvulnerable()
                )) {
                    if (!(target instanceof Player)) {
                        entity.hurt(source, DAMAGE_PER_STACK * stackCount);
                    }
                }

                level.playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 0.6f, 1.0f);


                player.addEffect(new MobEffectInstance(ModEffects.INTO_THE_FRAY_COOLDOWN.get(), 20 * 9, 0, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 1, false, false));
                new AttributeApplier().applyBlessings(player);
                new AttributeApplier().removeITF(player);
            }
        } else {
            player.getPersistentData().putInt("sprintTicks", 0);
            player.getPersistentData().putInt("itfStacks", 0);
            player.removeEffect(ModEffects.INTO_THE_FRAY.get());
            player.removeEffect(MobEffects.ABSORPTION);
            new AttributeApplier().removeITF(player);
        }
    }
}
