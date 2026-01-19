package net.cold.coldsmod.blessingbonuses.skills;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils;
import net.cold.coldsmod.damage.CustomMeleeDamage;
import net.cold.coldsmod.damage.ModDamageTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

import static net.cold.coldsmod.blessingbonuses.CooldownCycle.FRAY_SPEED_UUID;
import static net.cold.coldsmod.stat.AttributeApplier.removeModifier;

public class IntoTheFraySkill {

    private static final float DAMAGE_PER_STACK = 4f;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (!event.player.getPersistentData().getBoolean("into_the_fray_eligible")) return;
        if (event.player.level().isClientSide()) return;

        Player player = event.player;

        boolean sprinting = player.isSprinting();
        int sprintTicks = player.getPersistentData().getInt("sprintTicks");
        int stackCount = player.getPersistentData().getInt("itfStacks");

        if (sprinting && !player.hasEffect(ModEffects.INTO_THE_FRAY_COOLDOWN.get())) {
            sprintTicks++;
            player.getPersistentData().putInt("sprintTicks", sprintTicks);

            int itfAmplifier = 0;
            boolean giveAbsorption = false;

            if (sprintTicks >= 220) {
                itfAmplifier = 4;
                giveAbsorption = true;
            } else if (sprintTicks >= 180) {
                itfAmplifier = 3;
            } else if (sprintTicks >= 140) {
                itfAmplifier = 2;
            } else if (sprintTicks >= 100) {
                itfAmplifier = 1;
            } else if (sprintTicks >= 60) {
                itfAmplifier = 0;
            }

            if (sprintTicks <= 60) return;

            player.getPersistentData().putInt("itfStacks", itfAmplifier + 1);

            MobEffectInstance currentItf = player.getEffect(ModEffects.INTO_THE_FRAY.get());
            if (currentItf == null || currentItf.getAmplifier() != itfAmplifier) {
                player.addEffect(new MobEffectInstance(ModEffects.INTO_THE_FRAY.get(), 40, itfAmplifier, true, false, true));
            }

            if (giveAbsorption) {
                MobEffectInstance currentAbs = player.getEffect(MobEffects.ABSORPTION);
                if (currentAbs == null || currentAbs.getAmplifier() != 0) {
                    player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 40, 0, true, false, true));
                }
            } else {
                player.removeEffect(MobEffects.ABSORPTION);
            }

            Level level = player.level();
            List<LivingEntity> targetsHit = level.getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(0.6),
                    e -> e instanceof Enemy && e.isAlive() && !e.isInvulnerable()
            );

            if (!targetsHit.isEmpty()) {
                DamageSource source = new CustomMeleeDamage(
                        level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
                                .getHolderOrThrow(ModDamageTypes.EXPLOSION_DAMAGE),
                        player
                );

                for (LivingEntity target : targetsHit) {
                    target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 8 * stackCount, 3));
                    double dx = target.getX() - player.getX();
                    double dz = target.getZ() - player.getZ();
                    target.knockback(0.5f * stackCount, dx, dz);
                }

                List<LivingEntity> nearbyEntities = level.getEntitiesOfClass(
                        LivingEntity.class,
                        player.getBoundingBox().inflate(4.0),
                        e -> e instanceof Enemy && e.isAlive() && !e.isInvulnerable()
                );

                for (LivingEntity aoeTarget : nearbyEntities) {
                    aoeTarget.hurt(source, DAMAGE_PER_STACK * stackCount);
                }

                player.addEffect(new MobEffectInstance(ModEffects.INTO_THE_FRAY_COOLDOWN.get(), 180, 0, false, false, true));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 1, false, false, true));

                removeModifier(player, Attributes.MOVEMENT_SPEED, FRAY_SPEED_UUID);

                player.getPersistentData().putInt("sprintTicks", 0);
                player.getPersistentData().putInt("itfStacks", 0);

                EffectUtils.playExplosionSound(player, 0.5F);
                EffectUtils.spawnExplosionOnFeet(player);
            }
        } else {
            player.getPersistentData().putInt("sprintTicks", 0);
            player.getPersistentData().putInt("itfStacks", 0);
            player.removeEffect(ModEffects.INTO_THE_FRAY.get());
            player.removeEffect(MobEffects.ABSORPTION);

            if (!player.hasEffect(ModEffects.INTO_THE_FRAY.get())) {
                removeModifier(player, Attributes.MOVEMENT_SPEED, FRAY_SPEED_UUID);
            }
        }
    }
}
