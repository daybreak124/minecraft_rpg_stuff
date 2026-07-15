package net.cold.coldsmod.custom_attacks.attacks;

import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusCapabilityProvider;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.PlayerBonusCache;
import net.cold.coldsmod.capabilities_and_blessings.registry.ModEffects;
import net.cold.coldsmod.custom_attacks.CustomAttack;
import net.cold.coldsmod.custom_attacks.AttackAnimHandler;
import net.cold.coldsmod.custom_attacks.AttackHandler;
import net.cold.coldsmod.damage_types.ModDamageTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;

import java.util.List;

import static net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils.isAlly;
import static net.cold.coldsmod.events.Formulas.applyConsecutiveAttackDebuff;

public class AoEAttack extends CustomAttack {
    public AoEAttack() {
        super(1.25, 0.5, 1, 1);
    }

    @Override
    public void executeClient(Player player, LivingEntity target) {
        AttackAnimHandler.play(AttackAnimHandler.AnimationType.CLEAVE);

        // FIRST PERSON
        var mc = net.minecraft.client.Minecraft.getInstance();
        if (!mc.options.getCameraType().isFirstPerson()) {
            player.swing(InteractionHand.MAIN_HAND, false);
        }
    }

    @Override
    public void executeServer(Player player, LivingEntity target) {
        double attackBar = player.getAttackStrengthScale(0);

        PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
        cache.setSecondLastAttack(cache.getLastUsedAttack());
        cache.setLastUsedAttack(1);
        applyConsecutiveAttackDebuff(player, cache);

        Level level = player.level();

        double range = player.getAttributeValue(ForgeMod.ENTITY_REACH.get()) * this.rangeMultiplier * attackBar;

        Vec3 lookDir = player.getLookAngle().normalize();

        List<LivingEntity> nearby = level.getEntitiesOfClass(
                LivingEntity.class,
                player.getBoundingBox().inflate(range),
                e -> {
                    if (!e.isAlive() || e == player || isAlly(e)) return false;
                    Vec3 toTarget = e.position().subtract(player.position()).normalize();
                    return lookDir.dot(toTarget) > 0.2 && player.distanceToSqr(e) <= range * range;
                }
        );

        for (LivingEntity entity : nearby) {
            player.attack(entity);
        }
    }
}