package net.cold.coldsmod.blessingbonuses.neweffects;

import net.cold.coldsmod.ModEntities;
import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.mob.Sbeve;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.UUID;

public class SummoningStone {

    private static final int SBEVE_INTERVAL = 5995;

    public static void runSbeveUpdateLogic(Player player) {
        if (player.hasEffect(ModEffects.SOLARA.get()) || player.hasEffect(ModEffects.SBEVE_CD.get())) return;

        int timer = player.getPersistentData().getInt("sbeve_timer") + 1200;
        if (timer >= SBEVE_INTERVAL) {
            timer = 0;
            handleSbeveLogic((ServerLevel) player.level(), player);
        }
        player.getPersistentData().putInt("sbeve_timer", timer);
    }

    private static void handleSbeveLogic(ServerLevel level, Player player) {
        Sbeve sbeve = getPlayerSbeve(level, player);

        if (sbeve == null) {
            summonSbeve(level, player);
            player.addEffect(new MobEffectInstance(ModEffects.SBEVE_CD.get(), 5995, 0, false, false, false));
        } else {
            sbeve.applyOwnerScaling(player);
            sbeve.setHealth(sbeve.getMaxHealth());
            player.addEffect(new MobEffectInstance(ModEffects.SBEVE_CD.get(), 5995, 0, false, false, false));

        }
    }

    private static Sbeve getPlayerSbeve(ServerLevel level, Player player) {
        CompoundTag tag = player.getPersistentData();

        if (tag.hasUUID("active_sbeve_uuid")) {
            UUID sbeveUUID = tag.getUUID("active_sbeve_uuid");
            if (level.getEntity(sbeveUUID) instanceof Sbeve sbeve && sbeve.isAlive()) {
                return sbeve;
            }
        }
        return null;
    }

    public static void summonSbeve(ServerLevel level, Player player) {
        Sbeve sbeve = ModEntities.SBEVE.get().create(level);
        if (sbeve == null) return;

        sbeve.moveTo(player.getX(), player.getY(), player.getZ(), player.getYRot(), player.getXRot());
        sbeve.tame(player);
        sbeve.setPersistenceRequired();
        sbeve.applyOwnerScaling(player);

        level.addFreshEntity(sbeve);

        player.getPersistentData().putUUID("active_sbeve_uuid", sbeve.getUUID());
        player.addEffect(new MobEffectInstance(ModEffects.SBEVE_CD.get(), 5995, 0, false, false, false));

        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.EVOKER_PREPARE_SUMMON, SoundSource.PLAYERS,
                1.0F, 0.8F
        );

    }

    public static void killSbeve(ServerLevel level, Player player) {
        CompoundTag tag = player.getPersistentData();
        if (tag.hasUUID("active_sbeve_uuid")) {
            UUID sbeveUuid = tag.getUUID("active_sbeve_uuid");
            if (level.getEntity(sbeveUuid) instanceof Sbeve sbeve) {
                sbeve.discard();
            }
            tag.remove("active_sbeve_uuid");
        }
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        if (event.getEntity() instanceof Player player) {
            if (player.level() instanceof ServerLevel serverLevel) {
                Sbeve activeSbeve = getPlayerSbeve(serverLevel, player);
                if (activeSbeve != null) {
                    activeSbeve.discard();
                    player.getPersistentData().remove("active_sbeve_uuid");
                    player.getPersistentData().putBoolean("summoning_stone_eligible", false);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.getEntity().level().isClientSide()) return;

        Player player = event.getEntity();

        if (player.level() instanceof ServerLevel serverLevel) {
            killSbeve(serverLevel, player);
            player.getPersistentData().remove("active_sbeve_uuid");
        }
    }
}
