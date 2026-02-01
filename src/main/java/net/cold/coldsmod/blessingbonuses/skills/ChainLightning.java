package net.cold.coldsmod.blessingbonuses.skills;

import net.cold.coldsmod.damage.ModDamageTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class ChainLightning {

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onChainLightning(LivingHurtEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!player.getPersistentData().getBoolean("procChainLightning")) return;
        player.getPersistentData().putBoolean("procChainLightning", false);


        Level level = player.level();
        LivingEntity originalTarget = event.getEntity();
        double bounceDamage = event.getAmount() / 2.0;

        Holder<DamageType> lightningType = level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(ModDamageTypes.LIGHTNING_DAMAGE);

        LivingEntity finalOriginalTarget = originalTarget;
        List<LivingEntity> nearby = level.getEntitiesOfClass(
                LivingEntity.class,
                originalTarget.getBoundingBox().inflate(4.0),
                e -> e != null && e.isAlive() && e != finalOriginalTarget && (
                        (e instanceof Enemy && !(e instanceof NeutralMob)) ||
                                (e instanceof NeutralMob n && n.isAngry()) ||
                                (e instanceof Mob m && m.getTarget() != null)
                )
        );

        for (LivingEntity next : nearby) {
            if (bounceDamage < 1) break;

            DamageSource source = new DamageSource(lightningType, player, player);
            next.hurt(source, (float) bounceDamage);

            double startX = originalTarget.getX();
            double startY = originalTarget.getY() + 1.2;
            double startZ = originalTarget.getZ();

            double dx = next.getX() - startX;
            double dy = (next.getY() + 1.2) - startY;
            double dz = next.getZ() - startZ;

            double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
            int particleCount = (int) (distance * 4);

            for (int i = 0; i < particleCount; i++) {
                double ratio = (double) i / particleCount;
                ((ServerLevel)level).sendParticles(ParticleTypes.ELECTRIC_SPARK,
                        startX + (dx * ratio), startY + (dy * ratio), startZ + (dz * ratio),
                        1, 0, 0, 0, 0.0);
            }

            originalTarget = next;
            bounceDamage *= 0.35;
        }
    }
}
