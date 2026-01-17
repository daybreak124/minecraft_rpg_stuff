package net.cold.coldsmod.gearbonuses.skills;

import net.cold.coldsmod.damage.ModDamageTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class ChainLightning {

    @SubscribeEvent
    public static void onChainLightning(LivingDamageEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (player.level().isClientSide) return;
        if (!(player.getPersistentData().getBoolean("procChainLightning"))) return;

        player.getPersistentData().putBoolean("procChainLightning", false);


        Level level = player.level();
        LivingEntity originalTarget = event.getEntity();
        double bounceDamage = event.getAmount() / 2.0;

        Holder<DamageType> lightningType = level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(ModDamageTypes.LIGHTNING_DAMAGE);

        List<LivingEntity> nearby = level.getEntitiesOfClass(
                LivingEntity.class,
                originalTarget.getBoundingBox().inflate(4.0),
                e -> e != originalTarget && e != player && e.isAlive()
        );

        for (LivingEntity next : nearby) {
            if (bounceDamage < 1) break;

            DamageSource source = new DamageSource(lightningType, null, null);

            next.hurt(source, (float) bounceDamage);

            bounceDamage /= 2;
        }
    }
}
