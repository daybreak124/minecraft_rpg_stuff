package net.cold.coldsmod.blessingbonuses.skills;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.blessingbonuses.neweffects.EffectUtils;
import net.cold.coldsmod.damage.CustomMeleeDamageNoProcs;
import net.cold.coldsmod.stat.ItemRarityUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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

        LivingEntity target = event.getEntity();


        InteractionHand hand = player.swingingArm;
        String mainType = ItemRarityUtils.getItemType(player.getMainHandItem());
        String offType = ItemRarityUtils.getItemType(player.getOffhandItem());

        boolean isMelee = (hand == InteractionHand.MAIN_HAND && ((mainType.equals("sword")))) ||
                (hand == InteractionHand.OFF_HAND && (offType.equals("sword")));

        if (!isMelee) return;


        if (event.getSource() instanceof CustomMeleeDamageNoProcs) return;

        if (event.getSource().getEntity() instanceof Player p) {
            player = p;
        } else if ("player".equals(event.getSource().getMsgId()) && event.getSource().getEntity() != null) {
            if (event.getSource().getEntity() instanceof Player p2) player = p2;
        }

        if (player == null) return;

        if (player.hasEffect(ModEffects.BRONZEWOOD_READY.get())) {

            target.addEffect(new MobEffectInstance(ModEffects.BRONZEWOOD_CURSE.get(), 20 * 10, 0, false, false, true));

            player.level().playSound(
                    null,
                    player.getX(), player.getY(), player.getZ(),
                    SoundEvents.SOUL_ESCAPE,
                    SoundSource.PLAYERS,
                    7F,
                    1.0F
            );

            EffectUtils.spawnParticleBurst(target, ParticleTypes.SCULK_SOUL);

            curseSources.put(target, player.getUUID());

            player.addEffect(new MobEffectInstance(ModEffects.BRONZEWOOD_COOLDOWN.get(), 20 * 20, 0, false, false, true));
            player.getPersistentData().putBoolean("bronzewood_proc", true);
            player.removeEffect(ModEffects.BRONZEWOOD_READY.get());
        }
    }

    public static Player getCurseSource(LivingEntity target) {
        UUID uuid = curseSources.get(target);
        if (uuid == null) return null;
        return target.level().getPlayerByUUID(uuid);
    }

    public static void removeCurseSource(LivingEntity target) {
        curseSources.remove(target);
    }

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
