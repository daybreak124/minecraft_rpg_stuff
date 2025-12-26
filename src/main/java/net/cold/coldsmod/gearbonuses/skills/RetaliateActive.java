package net.cold.coldsmod.gearbonuses.skills;

import net.cold.coldsmod.ModSounds;
import net.cold.coldsmod.damage.CustomMeleeDamage;
import net.cold.coldsmod.damage.ModDamageTypes;
import net.cold.coldsmod.gearbonuses.effects.ModEffects;
import net.cold.coldsmod.stat.ItemRarityUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class RetaliateActive extends MobEffect {

    public RetaliateActive() {
        super(MobEffectCategory.NEUTRAL, 0xFFD700);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false;
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (player.level().isClientSide) return;
        if (event.phase != TickEvent.Phase.END) return;
        if (!player.getPersistentData().getBoolean("retaliate_applied")) return;

        ItemStack main = player.getMainHandItem();
        ItemStack off = player.getOffhandItem();
        if (!("shield".equals(ItemRarityUtils.getItemType(main)) ||
                "shield".equals(ItemRarityUtils.getItemType(off)))) return;

        MobEffectInstance ready = player.getEffect(ModEffects.RETALIATE_READY.get());
        if (ready != null && player.isBlocking()) {

            player.addEffect(new MobEffectInstance(ModEffects.RETALIATE_ACTIVE.get(), 20 * 4, 0, false, false));
            player.removeEffect(ModEffects.RETALIATE_READY.get());
            player.level().playSound(
                    null,
                    player.getX(), player.getY(), player.getZ(),
                    ModSounds.RETALIATE_ACTIVATE.get(),
                    SoundSource.PLAYERS,
                    0.6F,
                    1.0F
            );
            player.getPersistentData().putInt("retaliateHits", 0);
        }
    }


    @SubscribeEvent
    public static void onPlayerHurt(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.level().isClientSide) return;
        if (event.getSource().getEntity() instanceof Player) return;
        if (!player.getPersistentData().getBoolean("retaliate_applied")) return;

        if (player.hasEffect(ModEffects.RETALIATE_ACTIVE.get()) && player.isBlocking()) {
            int hits = player.getPersistentData().getInt("retaliateHits");
            player.getPersistentData().putInt("retaliateHits", hits + 1);
        }
    }


    @SubscribeEvent
    public static void onRetaliateExpire(MobEffectEvent.Expired event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (event.getEffectInstance().getEffect() != ModEffects.RETALIATE_ACTIVE.get()) return;
        if (!player.getPersistentData().getBoolean("retaliate_applied")) return;

        int hits = player.getPersistentData().getInt("retaliateHits");
        if (hits <= 0) return;

        int fort = player.getPersistentData().getInt("totalFort");
        double damage = hits * 3.0 * (1 + fort / 100.0);

        Level level = player.level();
        Holder<DamageType> explosionType = level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(ModDamageTypes.EXPLOSION_DAMAGE);
        DamageSource source = new CustomMeleeDamage(explosionType, player);

        level.getEntitiesOfClass(
                LivingEntity.class,
                player.getBoundingBox().inflate(5.0),
                e -> e != player && e.isAlive() && !e.isInvulnerable()
        ).forEach(target -> target.hurt(source, (float) damage));

        player.level().playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                ModSounds.RETALIATE.get(),
                SoundSource.PLAYERS,
                1.0F,
                1.0F
        );

        player.getPersistentData().putInt("retaliateHits", 0);
        player.addEffect(new MobEffectInstance(ModEffects.RETALIATE_COOLDOWN.get(), 20*11, 0, false, false));
    }
}
