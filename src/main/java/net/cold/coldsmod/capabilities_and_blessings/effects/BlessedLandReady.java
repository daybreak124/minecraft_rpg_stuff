package net.cold.coldsmod.capabilities_and_blessings.effects;

import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusCapabilityProvider;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.PlayerBonusCache;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import static net.cold.coldsmod.stat.AttributeApplier.getScaledValue;

public class BlessedLandReady extends MobEffect {
    public BlessedLandReady() {
        super(MobEffectCategory.NEUTRAL, 0x00000);
    }

    @Override
    public void addAttributeModifiers(@NotNull LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        PlayerBonusCache cache = pLivingEntity.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
        cache.setBlessedLandReady(true);
    }

    @Override
    public void removeAttributeModifiers(@NotNull LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        PlayerBonusCache cache = pLivingEntity.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
        cache.setBlessedLandReady(false);
    }

    public static void spawnBlessedLand(Player owner, Vec3 pos) {
        if (owner.level().isClientSide) return;

        double healIncrease = getScaledValue(owner,
                ModAttributes.RESTORATION.get());

        float finalHeal = (float) (3.0 * (1 + healIncrease/100));

        BlessedLandEntity cloud = new BlessedLandEntity(owner.level(), pos.x, pos.y, pos.z, finalHeal);
        cloud.setRadius(1.0f);
        cloud.setDuration(150);
        cloud.setWaitTime(0);
        cloud.healAmount(finalHeal);
        cloud.setParticle(ParticleTypes.TOTEM_OF_UNDYING);

        owner.level().addFreshEntity(cloud);
    }

    public static class BlessedLandEntity extends AreaEffectCloud {

        public BlessedLandEntity(EntityType<? extends AreaEffectCloud> type, Level level) {
            super(type, level);
        }

        public BlessedLandEntity(Level level, double x, double y, double z, float heal) {
            super(level, x, y, z);
            this.healAmount = heal;
        }
        private float healAmount = 0f;

        public void healAmount(float amount) {
            this.healAmount = amount;
        }

        public float getHealAmount() {
            return this.healAmount;
        }

        @Override
        public void playerTouch(Player player) {
            if (!this.level().isClientSide() && player.isAlive()) {
                player.heal(this.getHealAmount());
                this.discard();
            }
        }
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}