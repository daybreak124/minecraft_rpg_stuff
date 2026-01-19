package net.cold.coldsmod.blessingbonuses.neweffects;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
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
    public static void onHit(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player) || player.level().isClientSide()) return;
        if (!player.hasEffect(ModEffects.BLESSED_LAND_READY.get())) return;

        LivingEntity target = event.getEntity();
        Level level = player.level();
        Vec3 targetPos = target.position();

        double randomX = targetPos.x + (player.getRandom().nextDouble() * 10 - 5);
        double randomZ = targetPos.z + (player.getRandom().nextDouble() * 10 - 5);

        Vec3 finalSpawnPos = null;

        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos(randomX, targetPos.y + 1, randomZ);

        for (int i = 0; i <= 3; i++) {
            BlockState stateAt = level.getBlockState(checkPos);
            BlockState stateBelow = level.getBlockState(checkPos.below());

            if (stateAt.canBeReplaced() && stateBelow.isFaceSturdy(level, checkPos.below(), Direction.UP)) {
                finalSpawnPos = new Vec3(randomX, checkPos.getY() + 0.1, randomZ);
                break;
            }
            checkPos.move(Direction.DOWN);
        }

        if (finalSpawnPos == null) {
            finalSpawnPos = targetPos.add(0, 0.1, 0);
        }

        spawnBlessedLand(player, finalSpawnPos);
        level.playSound(null, finalSpawnPos.x, finalSpawnPos.y, finalSpawnPos.z,
                SoundEvents.BEACON_ACTIVATE, SoundSource.PLAYERS, 0.3F, 1.0F);

        double ampValue = AttributeApplier.getScaledValue(player, ModAttributes.AMPLIFICATION.get(), ModAttributes.AMPLIFICATION_MULTIPLIER.get());
        int cd = (int) (300 / (1.0 + (ampValue / 100.0)));
        player.removeEffect(ModEffects.BLESSED_LAND_READY.get());
        player.addEffect(new MobEffectInstance(ModEffects.BLESSED_LAND_CD.get(), cd, 0, false, false, true));
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
