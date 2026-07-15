package net.cold.coldsmod.network;

import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusCapabilityProvider;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusRegistry;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusTrigger;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.PlayerBonusCache;
import net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils;
import net.cold.coldsmod.capabilities_and_blessings.registry.ModEffects;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

import static net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils.spawnParticleRing;

public class DirectedHatredPacket {

    public DirectedHatredPacket() {}
    public DirectedHatredPacket(FriendlyByteBuf buffer) {}
    public static DirectedHatredPacket decode(FriendlyByteBuf buf) { return new DirectedHatredPacket(); }
    public void encode(FriendlyByteBuf buffer) {}

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;
            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
            executeDirectedHatred(player, cache);
            directedHatredCD(player, cache);
            BonusRegistry.process(player, null, player.level(), BonusTrigger.BLESSING_ACTIVATION);
        });
        return true;
    }

    public static void executeDirectedHatred(ServerPlayer player, PlayerBonusCache cache) {
        ServerLevel level = player.serverLevel();
        if (cache.isDirectedHatredHealSpec()) {
            Vec3 startPos = player.position();
            Vec3 look = player.getLookAngle();
            Vec3 direction = new Vec3(look.x, 0, look.z).normalize();
            Vec3 endPos = startPos.add(direction.scale(6.0));

            AABB dashLane = new AABB(startPos, endPos).inflate(1.0, 0.5, 1.0);

            List<LivingEntity> allies = level.getEntitiesOfClass(
                    LivingEntity.class,
                    dashLane,
                    e -> EffectUtils.isAlly(e) && player.hasLineOfSight(e) && e != player
            );

            drawLaneParticles(level, dashLane, player.getY());
            double healIncrease = AttributeApplier.getScaledValue(player,
                    ModAttributes.RESTORATION.get());
            float healAmount = (float) (4.0f * (1.0 + (healIncrease / 100.0)));

            double amp = AttributeApplier.getScaledValue(player,
                    ModAttributes.AMPLIFICATION.get());
            int duration = (int) (80 * (1.0 + (amp / 100.0)));

            for (LivingEntity ally : allies) {
                ally.heal(healAmount);
                ally.addEffect(new MobEffectInstance(ModEffects.DIRECTED_DIVINITY.get(), duration, 0, false, false, false));
                EffectUtils.playHealSound(ally);
                EffectUtils.spawnComposterBurst(ally);
            }
            player.heal(healAmount);
            player.addEffect(new MobEffectInstance(ModEffects.DIRECTED_DIVINITY.get(), duration, 0, false, false, false));
            EffectUtils.spawnComposterBurst(player);
            EffectUtils.playHealSound(player);

        } else {
            double rangeSq = 100.0;

            List<LivingEntity> nearby = level.getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(10.0),
                    e -> {
                        if (e.isInvulnerable() || !player.hasLineOfSight(e) || !((e instanceof Enemy && !(e instanceof NeutralMob)) || (e instanceof NeutralMob n && n.isAngryAt(player)) || (e instanceof Mob m && m.getTarget() != null))) return false;
                        return player.distanceToSqr(e) <= rangeSq;
                    }
            );

            for (LivingEntity entity : nearby) {
                if (entity instanceof Mob mob) {
                    mob.setTarget(player);
                    mob.addEffect(new MobEffectInstance(ModEffects.BLINDED_BY_HATRED.get(), 120, 0, false, true, false));
                }
            }

            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ANVIL_PLACE, SoundSource.PLAYERS, 0.35F, 1.0F);

            spawnParticleRing(level, player, ParticleTypes.ANGRY_VILLAGER, 6.0, 120);

            EffectUtils.spawnParticleBurst(player, ParticleTypes.WITCH);
        }
    }

    public static void directedHatredCD(ServerPlayer player, PlayerBonusCache cache) {
        int cd = 200;

        if (cache.isTankBlessingEnhanced()) {
            cd -= 120;
        }

        if (cache.isDirectedHatredHealSpec()) {
            cd += 120;
        }

        player.removeEffect(ModEffects.DIRECTED_HATRED_READY.get());
        player.addEffect(new MobEffectInstance(ModEffects.DIRECTED_HATRED_COOLDOWN.get(), cd, 0, false, false, true));
    }

    private static void drawLaneParticles(ServerLevel level, AABB box, double playerY) {
        // Top and Bottom edges of the rectangle
        for (double x = box.minX; x <= box.maxX; x += 0.5) {
            spawnBorderParticle(level, x, playerY, box.minZ);
            spawnBorderParticle(level, x, playerY, box.maxZ);
        }
        // Left and Right edges
        for (double z = box.minZ; z <= box.maxZ; z += 0.5) {
            spawnBorderParticle(level, box.minX, playerY, z);
            spawnBorderParticle(level, box.maxX, playerY, z);
        }
    }

    private static void spawnBorderParticle(ServerLevel level, double x, double y, double z) {
        BlockPos groundPos = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, BlockPos.containing(x, y, z));
        level.sendParticles(ParticleTypes.COMPOSTER, x, groundPos.getY() + 0.1, z, 1, 0, 0, 0, 0.0);
    }
}