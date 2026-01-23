package net.cold.coldsmod.blessingbonuses.neweffects;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.damage.CustomRangedDamage;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils.spawnParticleBurst;
import static net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils.spawnParticleRing;

public class VortexReady extends MobEffect {

    private static final CopyOnWriteArrayList<VortexInstance> activeVortices = new CopyOnWriteArrayList<>();

    private static class VortexInstance {
        final Vec3 pos;
        final Player owner;
        final String levelDim;
        int ticks = 80;

        VortexInstance(Vec3 pos, Player owner, Level level) {
            this.pos = pos;
            this.owner = owner;
            this.levelDim = level.dimension().location().toString();
        }
    }

    public VortexReady() {
        super(MobEffectCategory.NEUTRAL, 0xFF0000);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) { return false; }

    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        if (!(event.getProjectile() instanceof Arrow arrow)) return;
        if (!(arrow.getOwner() instanceof Player player)) return;
        if (arrow.level().isClientSide()) return;
        if (!arrow.getPersistentData().getBoolean("vortex_tagged")) return;
        if (!player.hasEffect(ModEffects.VORTEX_READY.get())) return;

        if (event.getRayTraceResult() != null) {
            Vec3 hitPos = event.getRayTraceResult().getLocation();
            activeVortices.add(new VortexInstance(hitPos, player, arrow.level()));

            player.removeEffect(ModEffects.VORTEX_READY.get());
            player.addEffect(new MobEffectInstance(ModEffects.VORTEX_CD.get(), 20 * 18, 0, false, false, true));
        }
    }

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (event.level.isClientSide()) return;

        String currentDim = event.level.dimension().location().toString();

        for (VortexInstance vortex : activeVortices) {
            if (!vortex.levelDim.equals(currentDim)) continue;

            processVortexEffect(event.level, vortex);
            vortex.ticks--;

            if (vortex.ticks <= 0) {
                activeVortices.remove(vortex);
            }
        }
    }

    private static void processVortexEffect(Level level, VortexInstance vortex) {
        double range = 4.0;
        double pullStrength = 0.12;
        Vec3 vPos = vortex.pos;
        Player player = vortex.owner;

        if (player == null) {
            activeVortices.remove(vortex);
            return;
        }

        double rangeSq = 16;
        List<LivingEntity> nearby = level.getEntitiesOfClass(
                LivingEntity.class,
                new AABB(vPos, vPos).inflate(range),
                e -> {
                    if (!(e instanceof Enemy) || !e.isAlive()) return false;

                    double dx = e.getX() - vPos.x;
                    double dz = e.getZ() - vPos.z;
                    if ((dx * dx + dz * dz) > rangeSq) return false;

                    Vec3 start = vPos;
                    Vec3 end = e.getEyePosition();
                    BlockHitResult result = level.clip(new ClipContext(
                            start, end,
                            ClipContext.Block.COLLIDER,
                            ClipContext.Fluid.NONE,
                            e));

                    return result.getType() == HitResult.Type.MISS;
                }
        );

        Holder<DamageType> rangedType = level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(DamageTypes.PLAYER_ATTACK);
        DamageSource source = new CustomRangedDamage(rangedType, player);

        for (LivingEntity mob : nearby) {
            double dx = vPos.x - mob.getX();
            double dy = vPos.y - mob.getY();
            double dz = vPos.z - mob.getZ();
            double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);

            if (distance > 0.1) {
                mob.setDeltaMovement(mob.getDeltaMovement().add(
                        dx / distance * pullStrength,
                        dy / distance * pullStrength,
                        dz / distance * pullStrength
                ));
                mob.hurtMarked = true;
            }

            if (vortex.ticks % 20 == 0) {
                mob.hurt(source, 4.0f);
                spawnParticleBurst(mob, ParticleTypes.SOUL);
            }
        }

        if (vortex.ticks % 20 == 0) {
            level.playSound(null, vPos.x, vPos.y, vPos.z, SoundEvents.SOUL_ESCAPE, SoundSource.PLAYERS, 7f, 1.0f);
            spawnParticleRing((ServerLevel) level, vPos, ParticleTypes.SOUL_FIRE_FLAME, 6.0, 120);
        }
    }
}