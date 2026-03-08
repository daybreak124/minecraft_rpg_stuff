package net.cold.coldsmod.blessingbonuses.effects;

import net.cold.coldsmod.damage.ModDamageTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;

public class SoulSeveranceActive extends MobEffect {

    public SoulSeveranceActive() {
        super(MobEffectCategory.NEUTRAL, 0xFFD700); // gold color
    }

    public static final ResourceKey<DamageType> MELEE_DAMAGE_KEY =
            ResourceKey.create(Registries.DAMAGE_TYPE, ModDamageTypes.CUSTOM_MELEE_DAMAGE.location());

    public static void resetSoulSeverance(Player player, CompoundTag data) {
        player.removeEffect(ModEffects.SOUL_SEVERANCE_READY.get());
        data.remove("pull_ticks");
        player.addEffect(new MobEffectInstance(ModEffects.SOUL_SEVERANCE_COOLDOWN.get(), 180, 0, false, false, true));
    }

    public static boolean isValidSoulTarget(Player source, LivingEntity target) {
        return target.isAlive() && !target.isInvulnerable() &&
                ((target instanceof Enemy && !(target instanceof NeutralMob)) ||
                        (target instanceof NeutralMob n && n.isAngry()) ||
                        (target instanceof Mob m && m.getTarget() != null));
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false; // Tick every game tick
    }
}
