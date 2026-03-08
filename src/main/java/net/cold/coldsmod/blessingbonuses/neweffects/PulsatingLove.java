package net.cold.coldsmod.blessingbonuses.neweffects;

import net.cold.coldsmod.mob.Sbeve;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PulsatingLove {

    private static final int EFFECT_DURATION = 20 * 30;
    private static final double RADIUS = 10.0;

    public static void applyTamedAura(Player player) {
        Level level = player.level();
        AABB area = player.getBoundingBox().inflate(RADIUS);

        List<TamableAnimal> animals = level.getEntitiesOfClass(
                TamableAnimal.class,
                area,
                t -> t.isTame() &&
                        player.getUUID().equals(t.getOwnerUUID()) &&
                        (!(t instanceof Sbeve))
        );

        for (TamableAnimal animal : animals) {
            animal.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, EFFECT_DURATION, 1, true, true));
            animal.addEffect(new MobEffectInstance(MobEffects.REGENERATION, EFFECT_DURATION, 1, true, true));
        }
    }
}