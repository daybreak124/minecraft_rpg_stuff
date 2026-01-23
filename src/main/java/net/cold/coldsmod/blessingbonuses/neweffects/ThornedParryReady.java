package net.cold.coldsmod.blessingbonuses.neweffects;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.damage.CustomMeleeDamage;
import net.cold.coldsmod.damage.ModDamageTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.UUID;

import static net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils.spawnParticleRing;

public class ThornedParryReady extends MobEffect {

    public ThornedParryReady() {
        super(MobEffectCategory.NEUTRAL, 0xFF0000); // category + color
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false; // only trigger via events
    }


    @SubscribeEvent
    public static void onShieldHit(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (event.getEntity().level().isClientSide()) return;
        if (!player.hasEffect(ModEffects.THORNED_PARRY_READY.get())) return;

        if (player.isBlocking() && event.getSource().getEntity() instanceof Monster monster) {
            player.getPersistentData().putInt("parry_time", 8);
            player.getPersistentData().putUUID("last_attacker_uuid", monster.getUUID());
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (event.player.level().isClientSide()) return;

        Player player = event.player;

        if (!(player.getPersistentData().getBoolean("thorn_eligible"))) return;

        int parryTimer = player.getPersistentData().getInt("parry_time");

        if (parryTimer > 0) {
            player.getPersistentData().putInt("parry_time", parryTimer - 1);
        }

        boolean isBlocking = player.isBlocking();
        boolean wasBlocking = player.getPersistentData().getBoolean("was_blocking_last_tick");

        if (wasBlocking && !isBlocking && parryTimer > 0) {
            triggerParryExplosion(player);
            player.getPersistentData().putInt("parry_time", 0);
        }
        player.getPersistentData().putBoolean("was_blocking_last_tick", isBlocking);
    }

    private static void triggerParryExplosion(Player player) {
        ServerLevel level = (ServerLevel) player.level();
        if (player.getPersistentData().hasUUID("last_attacker_uuid")) {
            UUID attackerUUID = player.getPersistentData().getUUID("last_attacker_uuid");
            Entity entity = level.getEntity(attackerUUID);

            if (entity instanceof Mob enemy) {
                enemy.setNoAi(true);
                enemy.getPersistentData().putInt("freeze_timer", 40);
            }
        }

        Holder<DamageType> explosionType = level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(ModDamageTypes.EXPLOSION_DAMAGE);
        DamageSource source = new CustomMeleeDamage(explosionType, player);

        double radiusSq = 9.0;
        List<LivingEntity> enemies = level.getEntitiesOfClass(
                LivingEntity.class,
                player.getBoundingBox().inflate(3.0),
                e -> {
                    if (!(e instanceof Enemy) || !e.isAlive() || !player.hasLineOfSight(e)) return false;
                    double dx = e.getX() - player.getX();
                    double dz = e.getZ() - player.getZ();
                    return (dx * dx + dz * dz) <= radiusSq;
                }
        );

        for (LivingEntity target : enemies) {
            target.hurt(source, 5.0f);
            target.hurtMarked = true;
        }

        spawnParticleRing(level, player, ParticleTypes.SNEEZE, 3.0, 60);

        player.removeEffect(ModEffects.THORNED_PARRY_READY.get());
        player.addEffect(new MobEffectInstance(ModEffects.THORNED_PARRY_CD.get(), 20*7, 0, false, false, true));

        EffectUtils.spawnParticleBurst(player, ParticleTypes.CRIT);
        EffectUtils.playSound(player, SoundEvents.TRIDENT_HIT_GROUND, 1F, 1F);
    }
}
