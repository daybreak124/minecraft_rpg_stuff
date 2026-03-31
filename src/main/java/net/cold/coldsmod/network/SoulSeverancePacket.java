package net.cold.coldsmod.network;

import net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils;
import net.cold.coldsmod.capabilities_and_blessings.registry.ModEffects;
import net.cold.coldsmod.damage_types.ModDamageTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils.spawnParticleBurst;
import static net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils.spawnParticleRing;

public class SoulSeverancePacket {

    public SoulSeverancePacket() {}

    public SoulSeverancePacket(FriendlyByteBuf buf) {}

    public void encode(FriendlyByteBuf buf) {}
    public static SoulSeverancePacket decode(FriendlyByteBuf buf) { return new SoulSeverancePacket(); }

    private static final ScheduledExecutorService EXECUTOR = Executors.newSingleThreadScheduledExecutor();

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;

            Level level = player.level();
            ServerLevel sLevel = (ServerLevel) player.level();

            player.addEffect(new MobEffectInstance(ModEffects.SOUL_SEVERANCE_COOLDOWN.get(), 320, 0, false, false, true));
            player.removeEffect(ModEffects.SOUL_SEVERANCE_READY.get());

            Holder<DamageType> meleeType = level.registryAccess()
                    .registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(ModDamageTypes.CUSTOM_MELEE_DAMAGE);
            DamageSource source = new DamageSource(meleeType, null, player);

            executeSS(sLevel, player, 0, source);
            executeSS(sLevel, player, 1, source);
            executeSS(sLevel, player, 2, source);
            executeSS(sLevel, player, 3, source);

        });
        return true;
    }

    public static void executeSS(ServerLevel level, Player player, int delay, DamageSource source) {
        EXECUTOR.schedule(() -> {
            level.getServer().execute(() -> {
                if (player != null && player.isAlive() && !player.isRemoved()) {

                    EffectUtils.playSound(player, SoundEvents.SOUL_ESCAPE, 7.0F, 1.0F);
                    spawnParticleRing(level, player, ParticleTypes.SOUL_FIRE_FLAME, 9, 180);

                    List<LivingEntity> nearby = level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(9));

                    for (LivingEntity mob : nearby) {
                        if (mob == player || mob instanceof Player) continue;

                        double dx = player.getX() - mob.getX();
                        double dy = player.getY() - mob.getY();
                        double dz = player.getZ() - mob.getZ();
                        double distSq = dx * dx + dy * dy + dz * dz;

                        if (distSq > 81.0D || distSq < 0.25D) continue;

                        double distance = Math.sqrt(distSq);

                        double pullFactor = (distance - 2.25D) / distance;
                        double targetX = mob.getX() + (dx * pullFactor);
                        double targetY = mob.getY() + (dy * pullFactor);
                        double targetZ = mob.getZ() + (dz * pullFactor);

                        mob.moveTo(targetX, targetY, targetZ, mob.getYRot(), mob.getXRot());


                        if (player.hasLineOfSight(mob)) {
                            mob.hurtMarked = true;
                            mob.hurt(source, 4.0f);
                            mob.setDeltaMovement(Vec3.ZERO);
                            spawnParticleBurst(mob, ParticleTypes.SOUL);
                        }
                    }
                }
            });
        }, delay, TimeUnit.SECONDS);
    }
}