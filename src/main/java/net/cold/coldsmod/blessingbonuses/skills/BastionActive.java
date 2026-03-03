package net.cold.coldsmod.blessingbonuses.skills;

import net.cold.coldsmod.ModSounds;
import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static net.cold.coldsmod.stat.ItemRarityUtils.getItemType;

public class BastionActive extends MobEffect {
    public BastionActive() {
        super(MobEffectCategory.NEUTRAL, 0xFF0000); // category + color
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false; // manually handle tick
    }

    @SubscribeEvent
    public static void onGuardDown(LivingEntityUseItemEvent.Stop event) {
        if (event.getEntity().level().isClientSide) return;
        if (!(event.getEntity() instanceof Player player)) return;
        if (!"shield".equals(getItemType(event.getItem()))) return;
        if (!event.getEntity().hasEffect(ModEffects.BASTION_READY.get())) return;

        double fort = player.getAttributeValue(ModAttributes.FORT.get());
        double perc = player.getAttributeValue(ModAttributes.PERC.get());
        double con = player.getAttributeValue(ModAttributes.CON.get());

        double seconds = 1.5 + 0.0075 * fort + 0.005 * perc + 0.005 * con;
        int ticks = (int)(seconds * 20);

        player.addEffect(new MobEffectInstance(ModEffects.BASTION_ACTIVE.get(), ticks, 0, false, false, true));
        player.removeEffect(ModEffects.BASTION_READY.get());

        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.playNotifySound(
                    ModSounds.GUARDIAN_ANGEL.get(), SoundSource.PLAYERS,
                    0.4F, 1.0F);
        }
    }
}
