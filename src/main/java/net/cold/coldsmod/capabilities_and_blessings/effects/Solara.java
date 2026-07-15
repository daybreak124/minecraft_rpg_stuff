package net.cold.coldsmod.capabilities_and_blessings.effects;

import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusCapabilityProvider;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.PlayerBonusCache;
import net.cold.coldsmod.capabilities_and_blessings.registry.ModEffects;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static net.cold.coldsmod.stat.AttributeApplier.applyModifier;
import static net.cold.coldsmod.stat.AttributeApplier.removeModifier;

public class Solara extends MobEffect {
    public Solara() {
        super(MobEffectCategory.NEUTRAL, 0x800080);
    }

    public static final UUID SOLARA_UUID = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479");

    @Override
    public void removeAttributeModifiers(@NotNull LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        PlayerBonusCache cache = pLivingEntity.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
        boolean active = cache.isSolaraActive();

        if (!active) {
            removeModifier(pLivingEntity, ModAttributes.MELEE_POTENCY.get(), SOLARA_UUID);
            removeModifier(pLivingEntity, Attributes.ARMOR, SOLARA_UUID);
//            if (pLivingEntity instanceof Player player) {
//                recalcArmor(player);
//            }
        } else {
            pLivingEntity.addEffect(new MobEffectInstance(ModEffects.SOLARA.get(), 24000, 0, false, false, false));
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 300 == 0;
    }


    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level().isClientSide()) return;
        long time = entity.level().getDayTime() % 24000;
        int phase = (int) (time / 6000);

        double melee = 0;
        double armor = 0;
        double factor;

        switch (phase) {
            case 0 -> {
                factor = time / 6000.0;
                melee = 12.5 * factor;
                armor = 5d * factor;
            }
            case 1 -> {
                factor = 1.0 - (time - 6000) / 6000.0;
                melee = 12.5 * factor;
                armor = 5d * factor;
            }
            case 2 -> {
                factor = (time - 12000) / 6000.0;
                melee = -7.5 * factor;
                armor = -7.5 * factor;
            }
            case 3 -> {
                factor = 1.0 - (time - 18000) / 6000.0;
                melee = -7.5 * factor;
                armor = -7.5 * factor;
            }
        }

        applyModifier(entity, ModAttributes.MELEE_POTENCY.get(), melee, SOLARA_UUID);
        applyModifier(entity, Attributes.ARMOR, armor, SOLARA_UUID);
//        if (entity instanceof Player player) {
//            recalcArmor(player);
//        }
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}