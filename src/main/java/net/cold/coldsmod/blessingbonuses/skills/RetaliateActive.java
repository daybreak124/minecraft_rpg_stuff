package net.cold.coldsmod.blessingbonuses.skills;

import net.cold.coldsmod.ModSounds;
import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.stat.ItemRarityUtils;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class RetaliateActive extends MobEffect {

    public RetaliateActive() {
        super(MobEffectCategory.NEUTRAL, 0xFFD700);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false;
    }

    @SubscribeEvent
    public static void onShieldUseStart(LivingEntityUseItemEvent.Start event) {
        if (event.getEntity().level().isClientSide() || !(event.getEntity() instanceof Player player)) return;
        if (!"shield".equals(ItemRarityUtils.getItemType(event.getItem()))) return;

        if (player.hasEffect(ModEffects.RETALIATE_READY.get())) {
            player.addEffect(new MobEffectInstance(ModEffects.RETALIATE_ACTIVE.get(), 20 * 4, 0, false, false, true));
            player.removeEffect(ModEffects.RETALIATE_READY.get());
            player.level().playSound(
                    null, player.getX(), player.getY(), player.getZ(),
                    ModSounds.RETALIATE_ACTIVATE.get(), SoundSource.PLAYERS,
                    0.4F, 1.0F
            );
            player.getPersistentData().putInt("retaliateHits", 0);
        }
    }


    @SubscribeEvent
    public static void onPlayerHurt(LivingHurtEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        if (!(event.getEntity() instanceof Player player)) return;
        if (event.getSource().getEntity() instanceof Player) return;

        if (player.hasEffect(ModEffects.RETALIATE_ACTIVE.get()) && player.isBlocking()) {
            int hits = player.getPersistentData().getInt("retaliateHits");
            player.getPersistentData().putInt("retaliateHits", hits + 1);
        }
    }
}
