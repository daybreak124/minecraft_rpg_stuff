package net.cold.coldsmod.blessingbonuses.skills;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.UUID;

import static net.cold.coldsmod.stat.AttributeApplier.getScaledValue;

public class ExplosiveTendencies {

    @SubscribeEvent
    public static void onExplosion(ExplosionEvent.Detonate event) {
        if (!(event.getExplosion().getDirectSourceEntity() instanceof Creeper creeper)) return;

        if (!creeper.getPersistentData().getBoolean("noBlockDamage")) return;

        event.getAffectedBlocks().clear();
        event.getAffectedEntities().removeIf(e -> !(e instanceof Enemy));
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        if (!(event.getSource().getEntity() instanceof Creeper creeper)) return;
        if (!creeper.getPersistentData().getBoolean("noBlockDamage")) return;
        if (event.getEntity() instanceof Enemy) return;
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void onCreeperDrops(LivingDropsEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        if (!(event.getEntity() instanceof Creeper creeper)) return;
        if (!creeper.getPersistentData().getBoolean("noBlockDamage")) return;

        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        if (!(event.getSource().getEntity() instanceof Creeper creeper)) return;

        if (!creeper.getPersistentData().getBoolean("noBlockDamage")) return;
        if (!creeper.getPersistentData().hasUUID("ownerPlayerUUID")) return;
        UUID ownerUUID = creeper.getPersistentData().getUUID("ownerPlayerUUID");

        if (!(creeper.level() instanceof ServerLevel serverLevel)) return;
        MinecraftServer server = serverLevel.getServer();
        if (server == null) return;

        Player owner = server.getPlayerList().getPlayer(ownerUUID);
        if (owner == null) return;

        double finalDamage = 5;

        double totalProjDamage = getScaledValue(owner,
                ModAttributes.PROJECTILE_POTENCY.get(),
                ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get());

        double totalCritChance = getScaledValue(owner,
                ModAttributes.PROJECTILE_ACCURACY.get(),
                ModAttributes.PROJECTILE_ACCURACY_MULTIPLIER.get()) + 10.0;

        double totalCritDamage = getScaledValue(owner,
                ModAttributes.PROJECTILE_PRECISION.get(),
                ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get());

        if (owner.getRandom().nextDouble() < (totalCritChance / 100.0)) {
            finalDamage *= (1.5 + (totalCritDamage / 100.0));
        }

        finalDamage *= (1.0 + (totalProjDamage / 100.0));

        event.setAmount((float) finalDamage);
    }
}
