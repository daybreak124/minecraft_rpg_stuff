package net.cold.coldsmod.blessingbonuses.neweffects;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.damage.ModDamageTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
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
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;

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

    private static final ResourceKey<DamageType> MELEE_DAMAGE_KEY =
            ResourceKey.create(Registries.DAMAGE_TYPE, ModDamageTypes.CUSTOM_MELEE_DAMAGE.location());


    public static void triggerParryExplosion(Player player) {
        ServerLevel level = (ServerLevel) player.level();
        if (player.getPersistentData().hasUUID("last_attacker_uuid")) {
            UUID attackerUUID = player.getPersistentData().getUUID("last_attacker_uuid");
            Entity entity = level.getEntity(attackerUUID);

            if (entity instanceof Mob enemy) {
                enemy.setNoAi(true);
                enemy.getPersistentData().putInt("freeze_timer", 40);
            }
        }

        Holder<DamageType> meleeType = level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(MELEE_DAMAGE_KEY);
        DamageSource source = new DamageSource(meleeType, null, player);

        double radiusSq = 9.0;
        List<LivingEntity> enemies = level.getEntitiesOfClass(
                LivingEntity.class,
                player.getBoundingBox().inflate(3.0),
                e -> {
                    if (!e.isAlive() || !player.hasLineOfSight(e) || !((e instanceof Enemy && !(e instanceof NeutralMob)) || (e instanceof NeutralMob n && n.isAngry()) || (e instanceof Mob m && m.getTarget() != null))) return false;
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
