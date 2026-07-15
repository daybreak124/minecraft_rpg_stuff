package net.cold.coldsmod.network;

import net.cold.coldsmod.ModSounds;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusRegistry;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusTrigger;
import net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils;
import net.cold.coldsmod.capabilities_and_blessings.registry.ModEffects;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

import static net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils.spawnParticleRing;

public class DaringShoutPacket {

    public DaringShoutPacket() {}
    public DaringShoutPacket(FriendlyByteBuf buffer) {}
    public static DaringShoutPacket decode(FriendlyByteBuf buf) { return new DaringShoutPacket(); }
    public void encode(FriendlyByteBuf buffer) {}

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;

            applyNoAi(player);
            player.removeEffect(ModEffects.DARING_SHOUT_READY.get());
            player.addEffect(new MobEffectInstance(ModEffects.DARING_SHOUT_COOLDOWN.get(), 300, 0, false, false, true));
            EffectUtils.playSound(player, ModSounds.DARING_SHOUT.get(), 0.6F, 1.0F);
        });
        return true;
    }

    private static void applyNoAi(Player player) {
        Level level = player.level();

        double fort = player.getAttributeValue(ModAttributes.FORT.get());
        double perc = player.getAttributeValue(ModAttributes.PERC.get());

        double durationMultiplier = 1 + (fort * 0.02 + perc * 0.01);
        int durationTicks = (int) (60 * durationMultiplier);

        double radiusSq = 25.0;
        List<LivingEntity> entities = level.getEntitiesOfClass(
                LivingEntity.class,
                player.getBoundingBox().inflate(5),
                e -> {
                    if (!(e instanceof Enemy) || !e.isAlive() || e.isInvulnerable() || !player.hasLineOfSight(e)) return false;
                    double dx = e.getX() - player.getX();
                    double dz = e.getZ() - player.getZ();
                    return (dx * dx + dz * dz) <= radiusSq;
                }
        );

        for (LivingEntity entity : entities) {
            int finalDuration = entity.getType().is(Tags.EntityTypes.BOSSES) || entity instanceof Warden
                    ? durationTicks / 3
                    : durationTicks;

            if (entity instanceof Mob mob) {
                mob.addEffect(new MobEffectInstance(ModEffects.STUN.get(), finalDuration, 0, false, false, true));
                EffectUtils.spawnParticleBurst(entity, ParticleTypes.ASH);
            }
        }

        BonusRegistry.process(player, null, player.level(), BonusTrigger.BLESSING_ACTIVATION);
        spawnParticleRing((ServerLevel) level, player, ParticleTypes.LARGE_SMOKE, 5.0, 100);
        EffectUtils.spawnParticleBurst(player, ParticleTypes.LARGE_SMOKE);
    }
}