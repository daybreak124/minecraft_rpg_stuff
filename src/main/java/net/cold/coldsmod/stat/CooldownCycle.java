package net.cold.coldsmod.stat;

import net.cold.coldsmod.ModSounds;
import net.cold.coldsmod.damage.CustomMeleeDamage;
import net.cold.coldsmod.damage.ModDamageTypes;
import net.cold.coldsmod.gearbonuses.effects.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.function.BiConsumer;

import static net.cold.coldsmod.stat.AttributeApplier.BLESSING_MOVE_SPEED_UUID;
import static net.cold.coldsmod.stat.AttributeApplier.removeModifier;

@Mod.EventBusSubscriber(modid = "coldsmod", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CooldownCycle {

    private static final HashMap<MobEffect, BiConsumer<Player, MobEffectInstance>> EXPIRE_HANDLERS = new HashMap<>();
    private static final HashMap<MobEffect, BiConsumer<Player, MobEffectInstance>> APPLY_HANDLERS = new HashMap<>();
    private static final String HEALED_NBT = "reckoningHealed";


    @SubscribeEvent
    public static void onEffectExpired(MobEffectEvent.Expired event) {
        if (event.getEntity() instanceof Player player) {
            var handler = EXPIRE_HANDLERS.get(event.getEffectInstance().getEffect());
            if (handler != null) {
                handler.accept(player, event.getEffectInstance());
            }
        }
    }

    @SubscribeEvent
    public static void onEffectAdded(MobEffectEvent.Added event) {
        if (!(event.getEntity() instanceof Player player)) return;

        var handler = APPLY_HANDLERS.get(event.getEffectInstance().getEffect());
        if (handler != null) {
            handler.accept(player, event.getEffectInstance());
        }
    }

    public static void init() {
        EXPIRE_HANDLERS.put(ModEffects.FRENZY.get(), (player, instance) ->
        {
            player.getPersistentData().putInt("frenzy", 0);
            new AttributeApplier().applyBlessings(player);
        });
            EXPIRE_HANDLERS.put(ModEffects.RETALIATE_ACTIVE.get(), (player, instance) -> {

                CompoundTag data = player.getPersistentData();

                if (!data.getBoolean("retaliate_applied")) return;

                int hits = data.getInt("retaliateHits");
                if (hits <= 0) return;

                int fort = data.getInt("totalFort");
                double damage = hits * 3.0 * (1 + fort / 100.0);

                Level level = player.level();

                Holder<DamageType> explosionType = level.registryAccess()
                        .registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(ModDamageTypes.EXPLOSION_DAMAGE);

                DamageSource source = new CustomMeleeDamage(explosionType, player);

                level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(5.0),
                        e -> e != player && e.isAlive() && !e.isInvulnerable()
                ).forEach(target -> target.hurt(source, (float) damage));

                level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.RETALIATE.get(), SoundSource.PLAYERS, 1.0F, 1.0F);

                data.putInt("retaliateHits", 0);

                player.addEffect(new MobEffectInstance(ModEffects.RETALIATE_COOLDOWN.get(), 20 * 11, 0, false, false));
            });

        EXPIRE_HANDLERS.put(ModEffects.HAWKEYE.get(), (player, inst) -> {
            player.getPersistentData().putInt("hawkeye", 0);
            new AttributeApplier().applyBlessings(player);
        });

        EXPIRE_HANDLERS.put(ModEffects.INTO_THE_FRAY.get(), (player, inst) -> {
            new AttributeApplier().applyBlessings(player);
            removeModifier(player, Attributes.MOVEMENT_SPEED, BLESSING_MOVE_SPEED_UUID);
        });

        EXPIRE_HANDLERS.put(ModEffects.BERSERK.get(), (player, inst) -> {
            if (!player.hasEffect(ModEffects.BERSERK_READY.get())) {
                player.getPersistentData().putInt("berserk", 0);
            }
        });

        EXPIRE_HANDLERS.put(ModEffects.BERSERK_READY.get(), (player, inst) -> {
            var data = player.getPersistentData();
            data.putInt("berserk", 0);

            if (!player.hasEffect(ModEffects.BERSERK_TIMER.get()) && data.getBoolean("berserk_applied")) {
                player.addEffect(new MobEffectInstance(ModEffects.BERSERK_TIMER.get(), 20 * 15, 0, false, false));
            }
        });

        EXPIRE_HANDLERS.put(ModEffects.BERSERK_TIMER.get(), (player, inst) -> {
            var data = player.getPersistentData();
            if (!data.getBoolean("berserk_applied")) return;

            if (player.hasEffect(ModEffects.BRONZEWOOD_COOLDOWN.get())) {
                player.addEffect(new MobEffectInstance(ModEffects.BERSERK_TIMER.get(), 20 * 15, 0, false, false));
            } else {
                player.addEffect(new MobEffectInstance(ModEffects.BERSERK_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false));
                data.putInt("berserk", 0);
            }
        });

        EXPIRE_HANDLERS.put(ModEffects.BRONZEWOOD_COOLDOWN.get(), (player, inst) -> {
            if (player.getPersistentData().getBoolean("bronzewoods_curse_applied")) {
                player.addEffect(new MobEffectInstance(ModEffects.BRONZEWOOD_READY.get(), MobEffectInstance.INFINITE_DURATION));
            }
        });

        EXPIRE_HANDLERS.put(ModEffects.DEATH_FROM_ABOVE_COOLDOWN.get(), (player, inst) -> {
            if (player.getPersistentData().getBoolean("death_from_above_eligible")) {
                player.addEffect(new MobEffectInstance(ModEffects.DEATH_FROM_ABOVE.get(), MobEffectInstance.INFINITE_DURATION));
            }
        });

        EXPIRE_HANDLERS.put(ModEffects.BASTION_COOLDOWN.get(), (player, inst) -> {
            if (player.getPersistentData().getBoolean("bastion_applied")) {
                player.addEffect(new MobEffectInstance(ModEffects.BASTION_READY.get(), MobEffectInstance.INFINITE_DURATION));
            }
        });

        EXPIRE_HANDLERS.put(ModEffects.BASTION_ACTIVE.get(), (player, inst) -> {
            if (player.getPersistentData().getBoolean("bastion_applied")) {
                player.addEffect(new MobEffectInstance(ModEffects.BASTION_COOLDOWN.get(), 20 * 10));
            }
        });

        EXPIRE_HANDLERS.put(ModEffects.RETALIATE_COOLDOWN.get(), (player, inst) -> {
            if (player.getPersistentData().getBoolean("retaliate_applied")) {
                player.addEffect(new MobEffectInstance(ModEffects.RETALIATE_READY.get(), MobEffectInstance.INFINITE_DURATION));
            }
        });

        EXPIRE_HANDLERS.put(ModEffects.INTIMIDATING_PRESENCE_COOLDOWN.get(), (player, inst) -> {
            if (player.getPersistentData().getBoolean("intimidating_presence_eligible")) {
                player.addEffect(new MobEffectInstance(ModEffects.INTIMIDATING_PRESENCE.get(), MobEffectInstance.INFINITE_DURATION));
            }
        });

        EXPIRE_HANDLERS.put(ModEffects.DARING_SHOUT_COOLDOWN.get(), (player, inst) -> {
            if (player.getPersistentData().getBoolean("daring_shout_eligible")) {
                player.addEffect(new MobEffectInstance(ModEffects.DARING_SHOUT.get(), MobEffectInstance.INFINITE_DURATION));
            }
        });

        EXPIRE_HANDLERS.put(ModEffects.RECKONING_COOLDOWN.get(), (player, inst) -> {
            if (player.getPersistentData().getBoolean("reckoning_eligible")) {
                player.addEffect(new MobEffectInstance(ModEffects.RECKONING.get(), MobEffectInstance.INFINITE_DURATION));
            }
        });

        EXPIRE_HANDLERS.put(ModEffects.RECKONING_ACTIVE.get(), (player, inst) -> {
            if (player.getPersistentData().getBoolean("reckoning_eligible")) {
                player.addEffect(new MobEffectInstance(ModEffects.RECKONING_COOLDOWN.get(), 20 * 10));
            }
        });

        EXPIRE_HANDLERS.put(ModEffects.CLAIRVOYANCE_COOLDOWN.get(), (player, inst) -> {
            if (player.getPersistentData().getBoolean("clairvoyance_applied")) {
                player.addEffect(new MobEffectInstance(ModEffects.CLAIRVOYANCE_READY.get(), MobEffectInstance.INFINITE_DURATION));
            }
        });

        EXPIRE_HANDLERS.put(ModEffects.EXPLOSIVE_TENDENCY_TIMER.get(), (player, inst) -> {
            MobEffectInstance stack = player.getEffect(ModEffects.EXPLOSIVE_TENDENCY_STACK.get());
            int amp = stack != null ? stack.getAmplifier() : -1;
            int next = Math.min(amp + 1, 2);

            if (amp < next && player.getPersistentData().getBoolean("explosive_tendencies_applied")) {
                if (stack != null) player.removeEffect(ModEffects.EXPLOSIVE_TENDENCY_STACK.get());
                player.addEffect(new MobEffectInstance(ModEffects.EXPLOSIVE_TENDENCY_STACK.get(), MobEffectInstance.INFINITE_DURATION, next));
            }
        });

        EXPIRE_HANDLERS.put(ModEffects.EXPLOIT_WEAKNESS_COOLDOWN.get(), (player, inst) -> {
            if (player.getPersistentData().getBoolean("exploit_weakness_applied")) {
                player.addEffect(new MobEffectInstance(ModEffects.EXPLOIT_WEAKNESS_READY.get(), MobEffectInstance.INFINITE_DURATION));
            }
        });

        EXPIRE_HANDLERS.put(ModEffects.ADRENALINE_INJECTION_COOLDOWN.get(), (player, inst) -> {
            if (player.getPersistentData().getBoolean("adrenaline_applied")) {
                player.addEffect(new MobEffectInstance(ModEffects.ADRENALINE_INJECTION_UP.get(), 20 * 5));
            }
        });

        EXPIRE_HANDLERS.put(ModEffects.ADRENALINE_INJECTION_UP.get(), (player, inst) -> {
            new AttributeApplier().removeCrossbowTag(player);
            if (player.getPersistentData().getBoolean("adrenaline_applied")) {
                player.addEffect(new MobEffectInstance(ModEffects.ADRENALINE_INJECTION_COOLDOWN.get(), 20 * 15));
            }
        });

        EXPIRE_HANDLERS.put(ModEffects.LIFE_TOUCH_COOLDOWN.get(), (player, inst) -> {
            if (player.getPersistentData().getBoolean("life_touch_applied")) {
                player.addEffect(new MobEffectInstance(ModEffects.LIFE_TOUCH_READY.get(), MobEffectInstance.INFINITE_DURATION));
                new AttributeApplier().applyBlessings(player);
            }
        });

        EXPIRE_HANDLERS.put(ModEffects.SOUL_SEVERANCE_COOLDOWN.get(), (player, inst) -> {
            if (player.getPersistentData().getBoolean("soul_severance_eligible")) {
                player.addEffect(new MobEffectInstance(ModEffects.SOUL_SEVERANCE_READY.get(), MobEffectInstance.INFINITE_DURATION));
            }
        });

        EXPIRE_HANDLERS.put(ModEffects.DECEPTION_COOLDOWN.get(), (player, inst) -> {
            if (player.getPersistentData().getBoolean("deception_applied")) {
                player.addEffect(new MobEffectInstance(ModEffects.DECEPTION_READY.get(), MobEffectInstance.INFINITE_DURATION));
            }
        });

        EXPIRE_HANDLERS.put(ModEffects.DIRECTED_HATRED_COOLDOWN.get(), (player, inst) -> {
            if (player.getPersistentData().getBoolean("directed_hatred_eligible")) {
                player.addEffect(new MobEffectInstance(ModEffects.DIRECTED_HATRED_READY.get(), MobEffectInstance.INFINITE_DURATION));
            }
        });

        EXPIRE_HANDLERS.put(ModEffects.QUANTUM_LEAP_COOLDOWN.get(), (player, inst) -> {
            if (player.getPersistentData().getBoolean("quantum_leap_eligible")) {
                player.addEffect(new MobEffectInstance(ModEffects.QUANTUM_LEAP_READY.get(), MobEffectInstance.INFINITE_DURATION));
                new AttributeApplier().applyBlessings(player);
            }
        });

        EXPIRE_HANDLERS.put(ModEffects.QUANTUM_LEAP_ACTIVE.get(), (player, inst) ->
                new AttributeApplier().applyBlessings(player)
        );

        EXPIRE_HANDLERS.put(ModEffects.SOLARA.get(), (player, inst) -> {
            if (player.getPersistentData().getBoolean("solara_eligible")) player.addEffect(new MobEffectInstance(ModEffects.SOLARA.get()));
            }
        );

        APPLY_HANDLERS.put(ModEffects.HAWKEYE.get(), (player, effect) -> {
            new AttributeApplier().applyBlessings(player);

            if (effect.getAmplifier() >= 3) {
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.CROSSBOW_QUICK_CHARGE_3, SoundSource.PLAYERS, 1.3F, 1.0F);
            }
        });

        APPLY_HANDLERS.put(ModEffects.FRENZY.get(), (player, effect) ->
                new AttributeApplier().applyBlessings(player)
        );

        APPLY_HANDLERS.put(ModEffects.EXPLOSIVE_TENDENCY_STACK.get(), (player, effect) -> {
            if (player.getPersistentData().getBoolean("explosive_tendencies_eligible") && effect.getAmplifier() < 2) {
                player.addEffect(new MobEffectInstance(ModEffects.EXPLOSIVE_TENDENCY_TIMER.get(), 20 * 8, 0, false, false));
            }
        });

        APPLY_HANDLERS.put(ModEffects.ADRENALINE_INJECTION_UP.get(), (player, effect) -> {
                new AttributeApplier().addCrossbowTag(player);
        });

        APPLY_HANDLERS.put(ModEffects.QUANTUM_LEAP_ACTIVE.get(), (player, effect) ->
                new AttributeApplier().applyBlessings(player)
        );

        APPLY_HANDLERS.put(ModEffects.RECKONING_COOLDOWN.get(), (player, instance) -> {
            if (player.level().isClientSide) return;

            CompoundTag data = player.getPersistentData();

            double healed = data.getDouble(HEALED_NBT);
            if (healed <= 0) return;

            double damageBack = healed * 0.5;

            Level level = player.level();

            Holder<DamageType> reckoningType = level.registryAccess()
                    .registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(ModDamageTypes.RECKONING);

            DamageSource reckoning = new DamageSource(reckoningType, (Entity) null);

            player.hurt(reckoning, (float) damageBack);

            level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.RECKONING_BOOM.get(), SoundSource.PLAYERS, 0.6F, 1.0F);

            data.remove(HEALED_NBT);
        });
    }
}
