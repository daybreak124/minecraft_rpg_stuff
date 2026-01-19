package net.cold.coldsmod.blessingbonuses.skills;

import net.cold.coldsmod.ModSounds;
import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ItemRarityUtils;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClairvoyanceSkill {

    private static final int CHARGE_TICKS_REQUIRED = 20 * 6;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (!event.player.hasEffect(ModEffects.CLAIRVOYANCE_READY.get())) return;
        if (event.player.level().isClientSide()) return;

        Player player = event.player;

        ItemStack stack = player.getUseItem();
        if (stack.isEmpty()) return;

        String type = ItemRarityUtils.getItemType(stack);
        if (!"bow".equals(type)) return;

        double drawSpeed = AttributeApplier.getScaledValue(player, ModAttributes.NOCK_HASTE.get(), ModAttributes.NOCK_HASTE_MULTIPLIER.get());

        double chargeReductionMultiplier = 1 - (drawSpeed / (drawSpeed + 100.0));
        int finalReqTicks = (int) (CHARGE_TICKS_REQUIRED * chargeReductionMultiplier);

        int chargeTime = player.getTicksUsingItem();

        if (chargeTime == finalReqTicks) {
            player.getPersistentData().putBoolean("Clairvoyance", true);

            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.playNotifySound(
                        ModSounds.CLAIRVOYANCE.get(),
                        SoundSource.PLAYERS,
                        1.2F,
                        1.0F
                );
            }
        }
    }

    @SubscribeEvent
    public static void onArrowSpawn(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof AbstractArrow arrow)) return;
        if (!(arrow.getOwner() instanceof Player player)) return;

        if (!player.getPersistentData().getBoolean("Clairvoyance")) return;

        if (player.level().isClientSide()) return;

        ItemStack main = player.getMainHandItem();
        ItemStack off  = player.getOffhandItem();

        boolean mainIsBow = "bow".equals(ItemRarityUtils.getItemType(main));
        boolean mainIsCrossbow = "crossbow".equals(ItemRarityUtils.getItemType(main));
        boolean offIsBow = "bow".equals(ItemRarityUtils.getItemType(off));
        boolean isBow = mainIsBow || (offIsBow && !mainIsCrossbow);
        if (!isBow) return;

        arrow.getPersistentData().putBoolean("clairvoyance_tagged", true);
        player.getPersistentData().putBoolean("Clairvoyance", false);
    }
}