package net.cold.coldsmod.capabilities_and_blessings.effects;

import net.cold.coldsmod.ModEntities;
import net.cold.coldsmod.mob.Sbeve;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.UUID;


public class SummoningStone extends MobEffect {
    public SummoningStone() {
        super(MobEffectCategory.NEUTRAL, 0x800080);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 6000 == 0;
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide() && entity instanceof Player player) {
            handleSbeveLogic((ServerLevel) player.level(), player);
        }
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

    public static Sbeve getPlayerSbeve(ServerLevel level, Player player) {
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

        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.EVOKER_PREPARE_SUMMON, SoundSource.PLAYERS,
                1.0F, 0.8F
        );

        sbeve.addEffect(new MobEffectInstance(MobEffects.REGENERATION, MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
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

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}
