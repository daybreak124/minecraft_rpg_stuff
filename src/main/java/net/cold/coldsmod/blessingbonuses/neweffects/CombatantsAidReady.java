package net.cold.coldsmod.blessingbonuses.neweffects;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class CombatantsAidReady extends MobEffect {

    public CombatantsAidReady() {
        super(MobEffectCategory.NEUTRAL, 0xFF0000);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false;
    }

    public static void startDash(Player player) {
        CompoundTag tag = player.getPersistentData();

        tag.putDouble("dash_x", player.getX());
        tag.putDouble("dash_y", player.getY());
        tag.putDouble("dash_z", player.getZ());

        Vec3 look = player.getLookAngle();
        Vec3 direction = new Vec3(look.x, 0, look.z).normalize();

        double speed = 2.0;
        player.setDeltaMovement(direction.x * speed, 0.1, direction.z * speed);

        player.addEffect(new MobEffectInstance(ModEffects.COMBATANTS_AID_RECALL.get(), 100, 0, false, false, true));
        player.level().playSound(player, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ARMOR_EQUIP_ELYTRA, SoundSource.PLAYERS,
                0.5F, 1.0F);
        applyDashSupport(player, direction);
    }

    private static void applyDashSupport(Player player, Vec3 direction) {
        Level level = player.level();
        ServerLevel serverLevel = (ServerLevel) level;

        AABB startBox = player.getBoundingBox().inflate(3.0);

        double dashDist = 11.0;
        AABB dashLane = startBox.expandTowards(
                direction.x * dashDist,
                direction.y * dashDist,
                direction.z * dashDist
        );

        List<LivingEntity> allies = level.getEntitiesOfClass(
                LivingEntity.class,
                dashLane,
                e -> EffectUtils.isAlly(e) && player.hasLineOfSight(e)
        );

        double minX = dashLane.minX;
        double maxX = dashLane.maxX;
        double minZ = dashLane.minZ;
        double maxZ = dashLane.maxZ;

        // side lengths to keep particle density consistent
        double lenX = maxX - minX;
        double lenZ = maxZ - minZ;

        // roughly 1 particle every 0.5 blocks along the perimeter
        int particlesX = (int) (lenX * 2);
        int particlesZ = (int) (lenZ * 2);

        // X
        for (int i = 0; i <= particlesX; i++) {
            double pct = (double) i / particlesX;
            spawnBorderParticle(serverLevel, player, minX + (lenX * pct), minZ);
            spawnBorderParticle(serverLevel, player, minX + (lenX * pct), maxZ);
        }
        // Z
        for (int i = 0; i <= particlesZ; i++) {
            double pct = (double) i / particlesZ;
            spawnBorderParticle(serverLevel, player, minX, minZ + (lenZ * pct));
            spawnBorderParticle(serverLevel, player, maxX, minZ + (lenZ * pct));
        }

        double healIncrease = AttributeApplier.getScaledValue(player,
                ModAttributes.RESTORATION.get(),
                ModAttributes.RESTORATION_MULTIPLIER.get());

        float healAmount = (float) (4.0f * (1.0 + (healIncrease / 100.0)));

        for (LivingEntity ally : allies) {
            ally.heal(healAmount);
            ally.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 0, false, false, true));
            EffectUtils.playHealSound(ally);
            EffectUtils.spawnComposterBurst(ally);
        }
    }

    public static void returnToOrigin(ServerPlayer player) {
        CompoundTag tag = player.getPersistentData();
        if (!tag.contains("dash_x")) return;

        player.teleportTo(tag.getDouble("dash_x"), tag.getDouble("dash_y"), tag.getDouble("dash_z"));
        player.hurtMarked = true;

        EffectUtils.playSound(player, SoundEvents.ENDERMAN_TELEPORT, 0.5F, 1.0F);
        EffectUtils.spawnParticleBurst(player, ParticleTypes.REVERSE_PORTAL);

        tag.remove("dash_x");
        tag.remove("dash_y");
        tag.remove("dash_z");
        player.removeEffect(ModEffects.COMBATANTS_AID_RECALL.get());
    }

    private static void spawnBorderParticle(ServerLevel level, Player player, double x, double z) {
        BlockPos groundPos = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, BlockPos.containing(x, player.getY(), z));
        level.sendParticles(ParticleTypes.COMPOSTER, x, groundPos.getY() + 0.1, z, 1, 0, 0, 0, 0.0);
    }
}