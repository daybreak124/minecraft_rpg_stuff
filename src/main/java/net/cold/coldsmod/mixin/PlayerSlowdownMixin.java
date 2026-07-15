package net.cold.coldsmod.mixin;

import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusCapabilityProvider;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.PlayerBonusCache;
import net.cold.coldsmod.stat.ItemRarityUtils;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(LocalPlayer.class)
abstract class PlayerSlowdownMixin {

    @ModifyConstant(method = "aiStep", constant = @Constant(floatValue = 0.2F))
    private float coldsmod$reduceSlowdown(float constant) {
        Player player = (Player) (Object) this;
        if (player.isUsingItem() && !player.getUseItem().isEmpty()) {
            String type = ItemRarityUtils.getItemType(player.getUseItem());
            PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);

            switch (type) {
                case "shield" -> {
                    if (cache.isShieldSlowdownCancel()) return 1f;
                }
                case "bow", "crossbow" -> {
                    if (cache.isBowSlowdownCancel()) return 0.7f;
                }
            }

        }
        return constant;
    }
}