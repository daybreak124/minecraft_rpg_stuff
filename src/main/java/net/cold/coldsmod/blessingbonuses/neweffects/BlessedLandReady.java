package net.cold.coldsmod.blessingbonuses.neweffects;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
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

        float finalHeal = (float) (2.0 * (1 + healIncrease/100));

        AreaEffectCloud cloud = new AreaEffectCloud(owner.level(), pos.x, pos.y, pos.z);
        cloud.setRadius(1.0f);
        cloud.setDuration(100);
        cloud.setWaitTime(0);
        cloud.setParticle(ParticleTypes.TOTEM_OF_UNDYING);
        cloud.addTag("blessed_land");

        cloud.getPersistentData().putFloat("finalHeal", finalHeal);

        owner.level().addFreshEntity(cloud);
    }

    @SubscribeEvent
    public static void onHit(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (player.hasEffect(ModEffects.BLESSED_LAND_READY.get())) {

                Vec3 targetPos = event.getEntity().position();

                double randomX = targetPos.x + (player.getRandom().nextDouble() * 10 - 5);
                double randomZ = targetPos.z + (player.getRandom().nextDouble() * 10 - 5);

                BlockPos topBlock = player.level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, BlockPos.containing(randomX, targetPos.y, randomZ));
                Vec3 spawnPos = new Vec3(randomX, topBlock.getY(), randomZ);

                spawnBlessedLand(player, spawnPos);

                int cd = (int) (20*15 / (1.0 + (AttributeApplier.getScaledValue(player, ModAttributes.AMPLIFICATION.get(), ModAttributes.AMPLIFICATION_MULTIPLIER.get()) / 100.0)));

                player.removeEffect(ModEffects.BLESSED_LAND_READY.get());
                player.addEffect(new MobEffectInstance(ModEffects.BLESSED_LAND_CD.get(), cd, 0, false, false, true));
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.player.level().isClientSide) return;

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
