package net.cold.coldsmod.custom_attacks.attacks;

import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusCapabilityProvider;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.PlayerBonusCache;
import net.cold.coldsmod.capabilities_and_blessings.registry.ModEffects;
import net.cold.coldsmod.custom_attacks.AttackHandler;
import net.cold.coldsmod.custom_attacks.CustomAttack;
import net.cold.coldsmod.damage_types.ModDamageTypes;
import net.cold.coldsmod.stat.AttributeApplier;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.common.ToolAction;

import static net.cold.coldsmod.events.Formulas.applyConsecutiveAttackDebuff;


public class HeavyAttack extends CustomAttack {
    public HeavyAttack() {
        super(1.0, 1.4, 2, 1.0);
    }

    @Override
    public void executeClient(Player player, LivingEntity target) {

    }

    @Override
    public void executeServer(Player player, LivingEntity target) {

        PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
        cache.setSecondLastAttack(cache.getLastUsedAttack());
        cache.setLastUsedAttack(2);
        applyConsecutiveAttackDebuff(player, cache);

        player.attack(target);

        AttributeApplier.removeModifier(player, Attributes.ATTACK_SPEED, CUSTOM_ATTACK_UUID);

        player.resetAttackStrengthTicker();

        player.swing(InteractionHand.MAIN_HAND, true);

        if (target instanceof Player targetPlayer) {
            if (targetPlayer.isBlocking() && targetPlayer.getUseItem().canPerformAction(ToolAction.get("shield_block"))) {
                ItemStack shieldStack = targetPlayer.getUseItem();
                targetPlayer.getCooldowns().addCooldown(shieldStack.getItem(), 100);
                targetPlayer.stopUsingItem();
                player.level().broadcastEntityEvent(targetPlayer, (byte) 30);
            }
        }
    }
}