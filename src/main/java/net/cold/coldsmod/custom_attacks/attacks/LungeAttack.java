package net.cold.coldsmod.custom_attacks.attacks;

import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusCapabilityProvider;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.PlayerBonusCache;
import net.cold.coldsmod.capabilities_and_blessings.registry.ModEffects;
import net.cold.coldsmod.custom_attacks.AttackHandler;
import net.cold.coldsmod.custom_attacks.CustomAttack;
import net.cold.coldsmod.custom_attacks.AttackAnimHandler;
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
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.phys.Vec3;

import static net.cold.coldsmod.events.Formulas.applyConsecutiveAttackDebuff;


public class LungeAttack extends CustomAttack {
    public LungeAttack() {
        super(1.5, 1.4, 2, 1.0);
    }

    @Override
    public void executeClient(Player player, LivingEntity target) {
        if (target == null) return;

        // FIRST PERSON
        var mc = net.minecraft.client.Minecraft.getInstance();
        if (!mc.options.getCameraType().isFirstPerson()) {
            player.swing(InteractionHand.MAIN_HAND, false);
        }

        AttackAnimHandler.play(AttackAnimHandler.AnimationType.POKE);
    }

    @Override
    public void executeServer(Player player, LivingEntity target) {
        double penalty = (1.0 / this.cooldownMultiplier) - 1.0;
        AttributeApplier.applyModifier(player, Attributes.ATTACK_SPEED, CUSTOM_ATTACK_UUID.toString(), penalty, AttributeModifier.Operation.MULTIPLY_TOTAL);

        PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
        cache.setSecondLastAttack(cache.getLastUsedAttack());
        cache.setLastUsedAttack(4);
        applyConsecutiveAttackDebuff(player, cache);

        player.attack(target);
    }
}