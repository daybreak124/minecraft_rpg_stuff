package net.cold.coldsmod.blessingbonuses.skills;

import net.cold.coldsmod.damage.ModDamageTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

import static net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils.spawnParticleBurst;

public class ChainLightning {

    @SubscribeEvent
    public static void onChainLightning(LivingDamageEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (player.level().isClientSide()) return;
        if (!player.getPersistentData().getBoolean("procChainLightning")) return;
        player.getPersistentData().putBoolean("procChainLightning", false);


        Level level = player.level();
        LivingEntity originalTarget = event.getEntity();
        double bounceDamage = event.getAmount() / 2.0;

        Holder<DamageType> lightningType = level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(ModDamageTypes.LIGHTNING_DAMAGE);

        double radiusSq = 16.0;
        List<LivingEntity> nearby = level.getEntitiesOfClass(
                LivingEntity.class,
                originalTarget.getBoundingBox().inflate(4.0),
                e -> {
                    if (!(e instanceof Enemy) || e == originalTarget || !e.isAlive()) return false;
                    double dx = e.getX() - originalTarget.getX();
                    double dz = e.getZ() - originalTarget.getZ();
                    return (dx * dx + dz * dz) <= radiusSq;
                }
        );

        for (LivingEntity next : nearby) {
            if (bounceDamage < 1) break;

            DamageSource source = new DamageSource(lightningType, null, null);

            next.hurt(source, (float) bounceDamage);
            spawnParticleBurst(next, ParticleTypes.ELECTRIC_SPARK);


            bounceDamage /= 2;
        }
    }
}
