package net.cold.coldsmod.gearbonuses.effects;

import net.cold.coldsmod.damage.CustomMeleeDamageNoProcs;
import net.cold.coldsmod.damage.ModDamageTypes;
import net.cold.coldsmod.gearbonuses.skills.BronzewoodApply;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class BronzewoodCurse extends MobEffect {

    public BronzewoodCurse() {
        super(MobEffectCategory.HARMFUL, 0x000000);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level().isClientSide()) return;

        MobEffectInstance effect = entity.getEffect(this);
        if (effect == null) return;

        Player sourcePlayer = BronzewoodApply.getCurseSource(entity);
        if (sourcePlayer == null) return;

        Holder<DamageType> curseType = entity.level().registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(ModDamageTypes.CURSE_DAMAGE);

        DamageSource source = new CustomMeleeDamageNoProcs(curseType, sourcePlayer);

        entity.hurt(source, 1f);
        entity.setDeltaMovement(entity.getDeltaMovement().scale(0));

        entity.level().playSound(
                null,
                entity.getX(), entity.getY(), entity.getZ(),
                SoundEvents.SOUL_ESCAPE,
                SoundSource.PLAYERS,
                4F, 1.0F
        );
    }
}
