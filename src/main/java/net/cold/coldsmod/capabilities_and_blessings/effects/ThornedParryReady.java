package net.cold.coldsmod.capabilities_and_blessings.effects;

import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusCapabilityProvider;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.PlayerBonusCache;
import net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils;
import net.cold.coldsmod.capabilities_and_blessings.registry.ModEffects;
import net.cold.coldsmod.damage_types.ModDamageTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils.spawnParticleRing;

public class ThornedParryReady extends MobEffect {
    public ThornedParryReady() {
        super(MobEffectCategory.NEUTRAL, 0x00000);
    }

    @Override
    public void addAttributeModifiers(@NotNull LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        PlayerBonusCache cache = pLivingEntity.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
        if (!cache.isParryReady()) {
            cache.setParryReady(true);
        }
    }

    @Override
    public void removeAttributeModifiers(@NotNull LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        PlayerBonusCache cache = pLivingEntity.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
        cache.setParryReady(false);
    }

    public static void triggerParryExplosion(Player player) {
        ServerLevel level = (ServerLevel) player.level();
        if (player.getPersistentData().hasUUID("last_attacker_uuid")) {
            UUID attackerUUID = player.getPersistentData().getUUID("last_attacker_uuid");
            Entity entity = level.getEntity(attackerUUID);

            if (entity instanceof Mob enemy) {
                enemy.addEffect(new MobEffectInstance(ModEffects.STUN.get(), 40, 0, false, false, true));
            }
        }

        Holder<DamageType> meleeType = level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(ModDamageTypes.CUSTOM_MELEE_DAMAGE);
        DamageSource source = new DamageSource(meleeType, null, player);

        double radiusSq = 9.0;
        List<LivingEntity> enemies = level.getEntitiesOfClass(
                LivingEntity.class,
                player.getBoundingBox().inflate(3.0),
                e -> {
                    if (!e.isAlive() || !player.hasLineOfSight(e) || !((e instanceof Enemy && !(e instanceof NeutralMob)) || (e instanceof NeutralMob n && n.isAngry()) || (e instanceof Mob m && m.getTarget() != null))) return false;
                    double dx = e.getX() - player.getX();
                    double dz = e.getZ() - player.getZ();
                    return (dx * dx + dz * dz) <= radiusSq;
                }
        );

        float damage = 5f;

        PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
        if (cache.isShieldBlessingEnhanced()) {
            damage *= 1.5f;
        }

        if (cache.isShieldBlessingHungerEnhanced()) {
            if (player.getRandom().nextDouble() < 0.5) {
                player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() + 2);
            }
        }

        for (LivingEntity target : enemies) {
            target.hurt(source, damage);
            target.hurtMarked = true;
        }

        spawnParticleRing(level, player, ParticleTypes.SNEEZE, 3.0, 60);

        player.removeEffect(ModEffects.THORNED_PARRY_READY.get());
        player.addEffect(new MobEffectInstance(ModEffects.THORNED_PARRY_CD.get(), 20*7, 0, false, false, true));

        EffectUtils.spawnParticleBurst(player, ParticleTypes.CRIT);
        EffectUtils.playSound(player, SoundEvents.TRIDENT_HIT_GROUND, 1F, 1F);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}