package net.cold.coldsmod.blessingbonuses.skills;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils;
import net.cold.coldsmod.damage.ModDamageTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BronzewoodApply {

    private static final Map<LivingEntity, UUID> curseSources = new HashMap<>();

    @SubscribeEvent
    public static void onHitApplyBronzewoodCurse(LivingHurtEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (event.getEntity() instanceof Player) return;
        if (event.getSource().getDirectEntity() != player) return;
        if (!(event.getSource().getDirectEntity() instanceof Player)) return;
        if (event.getSource().is(ModDamageTypes.MELEE_DOT_DAMAGE)) return;

        LivingEntity target = event.getEntity();

        if (player.hasEffect(ModEffects.BRONZEWOOD_READY.get())) {
            Level level = player.level();
            Holder<DamageType> meleeType = level.registryAccess()
                    .registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(ModDamageTypes.MELEE_DOT_DAMAGE);

            DamageSource source = new DamageSource(meleeType, player, player);
            target.hurt(source, 3.0f);

            target.addEffect(new MobEffectInstance(ModEffects.BRONZEWOOD_CURSE.get(), 20 * 10, 0, false, false, true));

            player.level().playSound(
                    null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.SOUL_ESCAPE, SoundSource.PLAYERS,
                    7F, 1.0F
            );

            EffectUtils.spawnParticleBurst(target, ParticleTypes.SCULK_SOUL);

            curseSources.put(target, player.getUUID());

            player.addEffect(new MobEffectInstance(ModEffects.BRONZEWOOD_COOLDOWN.get(), 300, 0, false, false, true));
            player.removeEffect(ModEffects.BRONZEWOOD_READY.get());
        }
    }

    public static Player getCurseSource(LivingEntity target) {
        UUID uuid = curseSources.get(target);
        if (uuid == null) return null;
        return target.level().getPlayerByUUID(uuid);
    }

//    public static void removeCurseSource(LivingEntity target) {
//        curseSources.remove(target);
//    }

    @SubscribeEvent
    public static void onKillRemoveBronzewoodCooldown(LivingDeathEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (player.hasEffect(ModEffects.BRONZEWOOD_COOLDOWN.get())) {
            if (player.level().isClientSide()) return;

            player.removeEffect(ModEffects.BRONZEWOOD_COOLDOWN.get());
            player.addEffect(new MobEffectInstance(ModEffects.BRONZEWOOD_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        }
    }
}
