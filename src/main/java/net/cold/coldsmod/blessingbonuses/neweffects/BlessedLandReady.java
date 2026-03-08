package net.cold.coldsmod.blessingbonuses.neweffects;

import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

import static net.cold.coldsmod.stat.AttributeApplier.getScaledValue;

public class BlessedLandReady extends MobEffect {

    public BlessedLandReady() {
        super(MobEffectCategory.NEUTRAL, 0x000000); // category + color
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false; // only trigger via events
    }


    public static void spawnBlessedLand(Player owner, Vec3 pos) {
        if (owner.level().isClientSide) return;

        double healIncrease = getScaledValue(owner,
                ModAttributes.RESTORATION.get(),
                ModAttributes.RESTORATION_MULTIPLIER.get());

        float finalHeal = (float) (3.0 * (1 + healIncrease/100));

        AreaEffectCloud cloud = new AreaEffectCloud(owner.level(), pos.x, pos.y, pos.z);
        cloud.setRadius(1.0f);
        cloud.setDuration(150);
        cloud.setWaitTime(0);
        cloud.setParticle(ParticleTypes.TOTEM_OF_UNDYING);
        cloud.addTag("blessed_land");

        cloud.getPersistentData().putFloat("finalHeal", finalHeal);

        owner.level().addFreshEntity(cloud);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (event.player.level().isClientSide()) return;

        Player player = event.player;
        Level level = player.level();

        List<AreaEffectCloud> clouds = level.getEntitiesOfClass(AreaEffectCloud.class,
                player.getBoundingBox(),
                e -> e.getTags().contains("blessed_land"));

        for (AreaEffectCloud cloud : clouds) {
            float scaledHeal = cloud.getPersistentData().getFloat("finalHeal");

            player.heal(scaledHeal);

            EffectUtils.playHealSound(player);
            EffectUtils.spawnComposterBurst(player);

            cloud.discard();
        }
    }
}
