package net.cold.coldsmod.blessingbonuses.neweffects;

import net.cold.coldsmod.ModEntities;
import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.mob.Sbeve;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.UUID;

public class SummoningStone {

    private static final int SBEVE_INTERVAL = 599;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.player.level().isClientSide) return;

        Player player = event.player;

        if (player.tickCount % 20 * 60 != 0) return;

        CompoundTag tag = player.getPersistentData();
        if (!tag.getBoolean("summoning_stone_eligible")
                || player.hasEffect(ModEffects.SOLARA.get())
                || player.hasEffect(ModEffects.SBEVE_CD.get())) return;

        int timer = tag.getInt("sbeve_timer") + 20;

        if (timer >= SBEVE_INTERVAL) {
            timer = 0;
            handleSbeveLogic((ServerLevel) player.level(), player);
        }

        tag.putInt("sbeve_timer", timer);
    }

    private static void handleSbeveLogic(ServerLevel level, Player player) {
        Sbeve sbeve = getPlayerSbeve(level, player);

        if (sbeve == null) {
            summonSbeve(level, player);
        } else {
            sbeve.applyOwnerScaling(player);
            sbeve.setHealth(sbeve.getMaxHealth());
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
        player.addEffect(new MobEffectInstance(ModEffects.SBEVE_CD.get(), 20*60*5, 0, false, false, false));
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
}
