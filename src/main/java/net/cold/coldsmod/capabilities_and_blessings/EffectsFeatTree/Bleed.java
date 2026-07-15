package net.cold.coldsmod.capabilities_and_blessings.EffectsFeatTree;

import net.cold.coldsmod.damage_types.ModDamageTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import java.util.*;

public class Bleed extends MobEffect {
    public Bleed() {
        super(MobEffectCategory.HARMFUL, 0x800080);
    }

    public static final Map<UUID, Map<UUID, BleedData>> activeBleed = new HashMap<>();
    public record BleedData(int stacks, long expiryTick) {}

    private static final ResourceKey<DamageType> MELEE_DOT_KEY =
            ResourceKey.create(Registries.DAMAGE_TYPE, ModDamageTypes.DOT_DAMAGE.location());

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level().isClientSide()) return;
        MobEffectInstance effect = entity.getEffect(this);
        if (effect == null) return;

        Map<UUID, BleedData> sources = activeBleed.get(entity.getUUID());
        if (sources == null || sources.isEmpty()) return;

        Holder<DamageType> meleeDOT = entity.level().registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(MELEE_DOT_KEY);

        long currentTime = entity.level().getGameTime();
        sources.entrySet().removeIf(entry -> currentTime >= entry.getValue().expiryTick());

        sources.forEach((attackerUUID, stacks) -> {
            Player sourcePlayer = entity.level().getPlayerByUUID(attackerUUID);
            if (sourcePlayer == null) return;

            DamageSource source = new DamageSource(meleeDOT, null, sourcePlayer);

            float damage = 0.2f * stacks.stacks();

            // cancel knockback
            Vec3 motion = entity.getDeltaMovement();
            entity.hurt(source, damage);
            entity.setDeltaMovement(motion);
        });
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap map, int amplifier) {
        super.removeAttributeModifiers(entity, map, amplifier);
        activeBleed.remove(entity.getUUID());
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}