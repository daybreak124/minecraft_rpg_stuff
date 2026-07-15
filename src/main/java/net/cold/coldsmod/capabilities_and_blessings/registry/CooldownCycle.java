package net.cold.coldsmod.capabilities_and_blessings.registry;

import net.cold.coldsmod.ModSounds;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusCapabilityProvider;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.PlayerBonusCache;
import net.cold.coldsmod.capabilities_and_blessings.effects.AdrenalineInjectionUp;
import net.cold.coldsmod.capabilities_and_blessings.effects.RadiatingWarmthTimer;
import net.cold.coldsmod.damage_types.ModDamageTypes;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.function.BiConsumer;

import static net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils.spawnParticleRing;

@Mod.EventBusSubscriber(modid = "coldsmod", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CooldownCycle {

    public static final HashMap<MobEffect, BiConsumer<Player, MobEffectInstance>> EXPIRE_HANDLERS = new HashMap<>();

    public static void init() {
        EXPIRE_HANDLERS.put(ModEffects.RETALIATE_ACTIVE.get(), (player, instance) -> {

            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
            cache.setRetaliateActive(false);

            int hits = cache.getRetaliateStack();
            if (hits <= 0) {
                player.addEffect(new MobEffectInstance(ModEffects.RETALIATE_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
                return;
            }

            double fort = player.getAttributeValue(ModAttributes.FORT.get());
            float damage = (float) (hits * 3.0 * (1 + fort / 100.0));

            if (cache.isShieldBlessingEnhanced()) {
                damage *= 1.35f;
            }

            if (cache.isShieldBlessingHungerEnhanced()) {
                player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() + hits/3);
            }

            Level level = player.level();

            Holder<DamageType> meleeType = level.registryAccess()
                    .registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(ModDamageTypes.CUSTOM_MELEE_DAMAGE);

            DamageSource source = new DamageSource(meleeType, null, player);

            double radiusSq = 25.0;
            float finalDamage = damage;

            level.getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(5.0),
                    e -> {
                        if (e == null || !e.isAlive() || e.isInvulnerable() || !player.hasLineOfSight(e) || !((e instanceof Enemy && !(e instanceof NeutralMob)) || (e instanceof NeutralMob n && n.isAngry()) || (e instanceof Mob m && m.getTarget() != null))) return false;
                        double dx = e.getX() - player.getX();
                        double dz = e.getZ() - player.getZ();
                        return (dx * dx + dz * dz) <= radiusSq;
                    }
            ).forEach(target -> target.hurt(source, finalDamage));

            level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.RETALIATE.get(), SoundSource.PLAYERS, 1.0F, 1.0F);

            EffectUtils.spawnExplosionEffect(player);
            cache.setRetaliateStack(0);

            if (level instanceof ServerLevel serverLevel) {
                spawnParticleRing(serverLevel, player, ParticleTypes.POOF, 5.0, 100);
            }

            player.addEffect(new MobEffectInstance(ModEffects.RETALIATE_COOLDOWN.get(), 20 * 11, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.ADRENALINE_INJECTION_COOLDOWN.get(), (player, instance) -> {
            player.addEffect(new MobEffectInstance(ModEffects.ADRENALINE_INJECTION_UP.get(), 100, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.ADRENALINE_INJECTION_UP.get(), (player, instance) -> {
            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
            cache.setInjection(false);

            player.addEffect(new MobEffectInstance(ModEffects.ADRENALINE_INJECTION_COOLDOWN.get(), 300, 0, false, false, true));
            AdrenalineInjectionUp.removeCrossbowNBT(player);
        });

        EXPIRE_HANDLERS.put(ModEffects.BASTION_ACTIVE.get(), (player, instance) -> {
            player.addEffect(new MobEffectInstance(ModEffects.BASTION_COOLDOWN.get(), 160, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.BASTION_COOLDOWN.get(), (player, instance) -> {
            player.addEffect(new MobEffectInstance(ModEffects.BASTION_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.BLESSED_LAND_CD.get(), (player, instance) -> {
            player.addEffect(new MobEffectInstance(ModEffects.BLESSED_LAND_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.BRONZEWOOD_COOLDOWN.get(), (player, instance) -> {
            player.addEffect(new MobEffectInstance(ModEffects.BRONZEWOOD_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.CLAIRVOYANCE_COOLDOWN.get(), (player, instance) -> {
            player.addEffect(new MobEffectInstance(ModEffects.CLAIRVOYANCE_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));

        });

        EXPIRE_HANDLERS.put(ModEffects.COMBATANTS_AID_CD.get(), (player, instance) -> {
            player.addEffect(new MobEffectInstance(ModEffects.COMBATANTS_AID_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.DARING_SHOUT_COOLDOWN.get(), (player, instance) -> {
            player.addEffect(new MobEffectInstance(ModEffects.DARING_SHOUT_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });


        EXPIRE_HANDLERS.put(ModEffects.DEATH_FROM_ABOVE_COOLDOWN.get(), (player, instance) -> {
            player.addEffect(new MobEffectInstance(ModEffects.DEATH_FROM_ABOVE_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.DECEPTION_COOLDOWN.get(), (player, instance) -> {
            player.addEffect(new MobEffectInstance(ModEffects.DECEPTION_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.DIRECTED_HATRED_COOLDOWN.get(), (player, instance) -> {
            player.addEffect(new MobEffectInstance(ModEffects.DIRECTED_HATRED_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.EXPLOIT_WEAKNESS_COOLDOWN.get(), (player, instance) -> {
            player.addEffect(new MobEffectInstance(ModEffects.EXPLOIT_WEAKNESS_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });


        EXPIRE_HANDLERS.put(ModEffects.FOCUSED_ENERGY_CD.get(), (player, instance) -> {
            player.addEffect(new MobEffectInstance(ModEffects.FOCUSED_ENERGY_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));

        });


        EXPIRE_HANDLERS.put(ModEffects.INTIMIDATING_PRESENCE_COOLDOWN.get(), (player, instance) -> {
            player.addEffect(new MobEffectInstance(ModEffects.INTIMIDATING_PRESENCE_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.LIFE_TOUCH_COOLDOWN.get(), (player, instance) -> {
            player.addEffect(new MobEffectInstance(ModEffects.LIFE_TOUCH_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.NIMBLE_GETAWAY_ACTIVE.get(), (player, instance) -> {
            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
            cache.setEvadeActive(false);

            if (cache.isNimbleEquipped()) {
                player.addEffect(new MobEffectInstance(ModEffects.NIMBLE_GETAWAY_COOLDOWN.get(), 400, 0, false, false, true));
            }
        });

        EXPIRE_HANDLERS.put(ModEffects.NIMBLE_GETAWAY_COOLDOWN.get(), (player, instance) -> {
            player.addEffect(new MobEffectInstance(ModEffects.NIMBLE_GETAWAY_ACTIVE.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.OVERCONFIDENCE_ACTIVE.get(), (player, instance) -> {
            player.addEffect(new MobEffectInstance(ModEffects.OVERCONFIDENCE_COOLDOWN.get(), 280, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.OVERCONFIDENCE_COOLDOWN.get(), (player, instance) -> {
            player.addEffect(new MobEffectInstance(ModEffects.OVERCONFIDENCE_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.QUANTUM_LEAP_COOLDOWN.get(), (player, instance) -> {
            player.addEffect(new MobEffectInstance(ModEffects.QUANTUM_LEAP_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.RADIATING_WARMTH.get(), (player, instance) -> {
            RadiatingWarmthTimer.radiate(player);
        });

        EXPIRE_HANDLERS.put(ModEffects.RETALIATE_COOLDOWN.get(), (player, instance) -> {
            player.addEffect(new MobEffectInstance(ModEffects.RETALIATE_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.SOUL_SEVERANCE_COOLDOWN.get(), (player, instance) -> {
            player.addEffect(new MobEffectInstance(ModEffects.SOUL_SEVERANCE_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.THORNED_PARRY_CD.get(), (player, instance) -> {
            player.addEffect(new MobEffectInstance(ModEffects.THORNED_PARRY_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.VORTEX_CD.get(), (player, instance) -> {
            player.addEffect(new MobEffectInstance(ModEffects.VORTEX_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.INTO_THE_FRAY_COOLDOWN.get(), (player, instance) -> {
            player.addEffect(new MobEffectInstance(ModEffects.INTO_THE_FRAY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, false));
        });

    }
}
