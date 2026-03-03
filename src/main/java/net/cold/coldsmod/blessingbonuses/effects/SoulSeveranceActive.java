package net.cold.coldsmod.blessingbonuses.effects;

import net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils;
import net.cold.coldsmod.damage.ModDamageTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

import static net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils.spawnParticleBurst;
import static net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils.spawnParticleRing;

public class SoulSeveranceActive extends MobEffect {

    public SoulSeveranceActive() {
        super(MobEffectCategory.NEUTRAL, 0xFFD700); // gold color
    }

    private static final ResourceKey<DamageType> MELEE_DAMAGE_KEY =
            ResourceKey.create(Registries.DAMAGE_TYPE, ModDamageTypes.CUSTOM_MELEE_DAMAGE.location());

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.player.level().isClientSide()) return;

        MobEffectInstance readyEffect = event.player.getEffect(ModEffects.SOUL_SEVERANCE_READY.get());
        if (readyEffect == null) return;

        Player player = event.player;
        Level level = player.level();
        CompoundTag data = player.getPersistentData();
        int ticks = data.getInt("pull_ticks");

        if (!player.isCrouching()) {
            if (ticks > 0) resetSoulSeverance(player, data);
            return;
        }

        double range = 6.0;
        double radiusSq = range * range;
        double pullStrength = 0.05;
        boolean isPulseTick = ticks % 20 == 0;

        DamageSource source = null;
        if (isPulseTick) {
            Holder<DamageType> meleeType = level.registryAccess()
                    .registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(MELEE_DAMAGE_KEY);

            source = new DamageSource(meleeType, player, player);

            EffectUtils.playSound(player, SoundEvents.SOUL_ESCAPE, 7.0F, 1.0F);
            spawnParticleRing((ServerLevel) level, player, ParticleTypes.SOUL_FIRE_FLAME, range, 120);
        }

        List<LivingEntity> nearby = level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(range));

        for (LivingEntity mob : nearby) {
            if (mob == player || mob instanceof Player) continue;

            double dx = player.getX() - mob.getX();
            double dy = player.getY() - mob.getY();
            double dz = player.getZ() - mob.getZ();
            double distSq = dx * dx + dy * dy + dz * dz;

            if (distSq > radiusSq || distSq < 0.01) continue;

            double distance = Math.sqrt(distSq);
            mob.setDeltaMovement(mob.getDeltaMovement().add(
                    (dx / distance) * pullStrength,
                    (dy / distance) * pullStrength,
                    (dz / distance) * pullStrength
            ));

            if (isPulseTick && isValidSoulTarget(player, mob)) {
                if (player.hasLineOfSight(mob)) {
                    mob.hurtMarked = true;
                    mob.hurt(source, 4.0f);
                    spawnParticleBurst(mob, ParticleTypes.SOUL);
                }
            }
        }

        ticks++;
        data.putInt("pull_ticks", ticks);
        player.addEffect(new MobEffectInstance(ModEffects.SOUL_SEVERANCE_ACTIVE.get(), 10, 0, true, false, true));

        if (ticks >= 80) {
            resetSoulSeverance(player, data);
        }
    }

    private static void resetSoulSeverance(Player player, CompoundTag data) {
        player.removeEffect(ModEffects.SOUL_SEVERANCE_READY.get());
        data.remove("pull_ticks");
        player.addEffect(new MobEffectInstance(ModEffects.SOUL_SEVERANCE_COOLDOWN.get(), 180, 0, false, false, true));
    }

    private static boolean isValidSoulTarget(Player source, LivingEntity target) {
        return target.isAlive() && !target.isInvulnerable() &&
                ((target instanceof Enemy && !(target instanceof NeutralMob)) ||
                        (target instanceof NeutralMob n && n.isAngry()) ||
                        (target instanceof Mob m && m.getTarget() != null));
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false; // Tick every game tick
    }
}
