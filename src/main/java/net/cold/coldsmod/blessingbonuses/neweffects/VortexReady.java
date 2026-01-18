package net.cold.coldsmod.blessingbonuses.neweffects;

import net.cold.coldsmod.damage.CustomRangedDamage;
import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.concurrent.CopyOnWriteArrayList;

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
        if (!(event.getProjectile() instanceof Arrow arrow) || arrow.level().isClientSide) return;
        if (!(arrow.getOwner() instanceof Player player) || !player.hasEffect(ModEffects.VORTEX_READY.get())) return;

        if (event.getRayTraceResult() != null) {
            Vec3 hitPos = event.getRayTraceResult().getLocation();
            activeVortices.add(new VortexInstance(hitPos, player, arrow.level()));

            player.removeEffect(ModEffects.VORTEX_READY.get());
            player.addEffect(new MobEffectInstance(ModEffects.VORTEX_CD.get(), 20 * 18, 0, false, false, true));
        }
    }

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.level.isClientSide) return;

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

        AABB area = new AABB(vPos, vPos).inflate(range);
        var nearby = level.getEntitiesOfClass(LivingEntity.class, area, e -> e instanceof Monster && e.isAlive());

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
                mob.hurt(source, 3.0f);
            }
        }

        if (vortex.ticks % 20 == 0) {
            level.playSound(null, vPos.x, vPos.y, vPos.z, SoundEvents.SOUL_ESCAPE, SoundSource.PLAYERS, 0.6f, 1.2f);
        }
    }
}