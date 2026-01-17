package net.cold.coldsmod.gearbonuses;

import net.cold.coldsmod.ModSounds;
import net.cold.coldsmod.damage.CustomMeleeDamage;
import net.cold.coldsmod.damage.ModDamageTypes;
import net.cold.coldsmod.gearbonuses.effects.ModEffects;
import net.cold.coldsmod.gearbonuses.neweffects.RadiatingWarmthTimer;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ModAttributes;
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
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.BiConsumer;

import static net.cold.coldsmod.stat.AttributeApplier.applyModifier;
import static net.cold.coldsmod.stat.AttributeApplier.removeModifier;

@Mod.EventBusSubscriber(modid = "coldsmod", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CooldownCycle {

    private static final HashMap<MobEffect, BiConsumer<Player, MobEffectInstance>> EXPIRE_HANDLERS = new HashMap<>();
    private static final HashMap<MobEffect, BiConsumer<Player, MobEffectInstance>> APPLY_HANDLERS = new HashMap<>();
    private static final String HEALED_NBT = "reckoningHealed";
    private static final UUID OFFERING_ARMOR_UUID = UUID.fromString("f3e2b6c0-1934-5678-9abc-000000000084");
    private static final UUID FRENZY_ATTACK_DAMAGE_UUID = UUID.fromString("f3e2b3c0-1728-5123-ab33-000008060446");
    public static final UUID FRAY_SPEED_UUID = UUID.fromString("f3e2b6c0-1234-5178-9abc-000032602016");
    private static final UUID QUANTUM_SPEED_UUID = UUID.fromString("f3e2b6c0-1734-5678-9abc-002032502016");
    private static final UUID QUANTUM_DAMAGE_UUID = UUID.fromString("f3e2b6c0-1334-5678-9abc-002032322016");
    public static final UUID HAWKEYE_UUID = UUID.fromString("d5553476-1234-5254-5454-113215411111");

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
            removeModifier(player, Attributes.ATTACK_DAMAGE, FRENZY_ATTACK_DAMAGE_UUID);
        });
        EXPIRE_HANDLERS.put(ModEffects.RETALIATE_ACTIVE.get(), (player, instance) -> {

            CompoundTag data = player.getPersistentData();

            int hits = data.getInt("retaliateHits");
            if (hits <= 0) return;

            double fort = player.getAttributeValue(ModAttributes.FORT.get());
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

            player.addEffect(new MobEffectInstance(ModEffects.RETALIATE_COOLDOWN.get(), 20 * 11, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.HAWKEYE.get(), (player, inst) -> {
            player.getPersistentData().putInt("hawkeye", 0);

            removeModifier(player, ModAttributes.NOCK_HASTE.get(), HAWKEYE_UUID);
            removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), HAWKEYE_UUID);
        });

        EXPIRE_HANDLERS.put(ModEffects.INTO_THE_FRAY.get(), (player, inst) -> {
            removeModifier(player, Attributes.MOVEMENT_SPEED, FRAY_SPEED_UUID);
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
                player.addEffect(new MobEffectInstance(ModEffects.BERSERK_TIMER.get(), 20 * 15, 0, false, false, true));
            }
        });

        EXPIRE_HANDLERS.put(ModEffects.BERSERK_TIMER.get(), (player, inst) -> {
            var data = player.getPersistentData();
            if (!data.getBoolean("berserk_applied")) return;

            player.addEffect(new MobEffectInstance(ModEffects.BERSERK_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
            data.putInt("berserk", 0);
        });

        EXPIRE_HANDLERS.put(ModEffects.BRONZEWOOD_COOLDOWN.get(), (player, inst) -> {
            player.addEffect(new MobEffectInstance(ModEffects.BRONZEWOOD_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });


        EXPIRE_HANDLERS.put(ModEffects.DEATH_FROM_ABOVE_COOLDOWN.get(), (player, inst) -> {
            player.addEffect(new MobEffectInstance(ModEffects.DEATH_FROM_ABOVE.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));

        });

        EXPIRE_HANDLERS.put(ModEffects.BASTION_COOLDOWN.get(), (player, inst) -> {
            player.addEffect(new MobEffectInstance(ModEffects.BASTION_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.BASTION_ACTIVE.get(), (player, inst) -> {
            player.addEffect(new MobEffectInstance(ModEffects.BASTION_COOLDOWN.get(), 20 * 10, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.RETALIATE_COOLDOWN.get(), (player, inst) -> {
            player.addEffect(new MobEffectInstance(ModEffects.RETALIATE_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));

        });

        EXPIRE_HANDLERS.put(ModEffects.INTIMIDATING_PRESENCE_COOLDOWN.get(), (player, inst) -> {
            player.addEffect(new MobEffectInstance(ModEffects.INTIMIDATING_PRESENCE.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.DARING_SHOUT_COOLDOWN.get(), (player, inst) -> {
            player.addEffect(new MobEffectInstance(ModEffects.DARING_SHOUT.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));

        });

        EXPIRE_HANDLERS.put(ModEffects.RECKONING_COOLDOWN.get(), (player, inst) -> {
            player.addEffect(new MobEffectInstance(ModEffects.RECKONING.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.RECKONING_ACTIVE.get(), (player, inst) -> {
            player.addEffect(new MobEffectInstance(ModEffects.RECKONING_COOLDOWN.get(), 20 * 10, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.CLAIRVOYANCE_COOLDOWN.get(), (player, inst) -> {
            player.addEffect(new MobEffectInstance(ModEffects.CLAIRVOYANCE_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));

        });

        EXPIRE_HANDLERS.put(ModEffects.EXPLOSIVE_TENDENCY_TIMER.get(), (player, inst) -> {
            MobEffectInstance currentStack =
                    player.getEffect(ModEffects.EXPLOSIVE_TENDENCY_STACK.get());

            int currentAmp = (currentStack != null)
                    ? currentStack.getAmplifier()
                    : -1;

            int newAmplifier = Math.min(currentAmp + 1, 2);

            if (currentStack != null) {
                player.removeEffect(ModEffects.EXPLOSIVE_TENDENCY_STACK.get());
            }
            player.removeEffect(ModEffects.EXPLOSIVE_TENDENCY_TIMER.get());
            player.addEffect(new MobEffectInstance(ModEffects.EXPLOSIVE_TENDENCY_STACK.get(), MobEffectInstance.INFINITE_DURATION, newAmplifier, false, false, true));
            if (player.hasEffect(ModEffects.EXPLOSIVE_TENDENCY_STACK.get())
                    && player.getEffect(ModEffects.EXPLOSIVE_TENDENCY_STACK.get()).getAmplifier() < 2) {

                player.getPersistentData().putBoolean("refresh_et", true);
            }
        });

        EXPIRE_HANDLERS.put(ModEffects.EXPLOIT_WEAKNESS_COOLDOWN.get(), (player, inst) -> {
            player.addEffect(new MobEffectInstance(ModEffects.EXPLOIT_WEAKNESS_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));

        });

        EXPIRE_HANDLERS.put(ModEffects.ADRENALINE_INJECTION_COOLDOWN.get(), (player, inst) -> {
            player.addEffect(new MobEffectInstance(ModEffects.ADRENALINE_INJECTION_UP.get(), 20 * 5, 0, false, false, true));

        });

        EXPIRE_HANDLERS.put(ModEffects.ADRENALINE_INJECTION_UP.get(), (player, inst) -> {
            new AttributeApplier().removeCrossbowTag(player);
            player.addEffect(new MobEffectInstance(ModEffects.ADRENALINE_INJECTION_COOLDOWN.get(), 20 * 15, 0, false, false, true));

        });

        EXPIRE_HANDLERS.put(ModEffects.LIFE_TOUCH_COOLDOWN.get(), (player, inst) -> {
            if (player.getPersistentData().getBoolean("life_touch_applied")) {
                player.addEffect(new MobEffectInstance(ModEffects.LIFE_TOUCH_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
            }
        });

        EXPIRE_HANDLERS.put(ModEffects.SOUL_SEVERANCE_COOLDOWN.get(), (player, inst) -> {
            player.addEffect(new MobEffectInstance(ModEffects.SOUL_SEVERANCE_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.DECEPTION_COOLDOWN.get(), (player, inst) -> {
            player.addEffect(new MobEffectInstance(ModEffects.DECEPTION_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));

        });

        EXPIRE_HANDLERS.put(ModEffects.DIRECTED_HATRED_COOLDOWN.get(), (player, inst) -> {
            player.addEffect(new MobEffectInstance(ModEffects.DIRECTED_HATRED_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.QUANTUM_LEAP_COOLDOWN.get(), (player, inst) -> {
            player.addEffect(new MobEffectInstance(ModEffects.QUANTUM_LEAP_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.QUANTUM_LEAP_ACTIVE.get(), (player, inst) -> {
            removeModifier(player, Attributes.MOVEMENT_SPEED, QUANTUM_SPEED_UUID);
            removeModifier(player, ModAttributes.POTENCY.get(), QUANTUM_DAMAGE_UUID);
            removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), QUANTUM_DAMAGE_UUID);
            removeModifier(player, ModAttributes.MELEE_POTENCY.get(), QUANTUM_DAMAGE_UUID);
        });

        EXPIRE_HANDLERS.put(ModEffects.SOLARA.get(), (player, inst) -> {
            removeModifier(player, ModAttributes.POTENCY.get(), SOLARA_UUID);
            removeModifier(player, Attributes.ARMOR, SOLARA_UUID);

            if (player.getPersistentData().getBoolean("solara_eligible")) player.getPersistentData().putBoolean("refresh_solara", true);
        });

        EXPIRE_HANDLERS.put(ModEffects.COMBATANTS_AID_CD.get(), (player, inst) -> {
            player.addEffect(new MobEffectInstance(ModEffects.COMBATANTS_AID_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });


        EXPIRE_HANDLERS.put(ModEffects.ENTWINED_OFFERING_ACTIVE.get(), (player, inst) -> {
            removeModifier(player, Attributes.ARMOR, OFFERING_ARMOR_UUID);
        });

        EXPIRE_HANDLERS.put(ModEffects.OVERCONFIDENCE_COOLDOWN.get(), (player, inst) -> {
            player.addEffect(new MobEffectInstance(ModEffects.OVERCONFIDENCE_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.OVERCONFIDENCE_ACTIVE.get(), (player, inst) -> {
            player.addEffect(new MobEffectInstance(ModEffects.OVERCONFIDENCE_COOLDOWN.get(), 20*7, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.RADIATING_WARMTH.get(), (player, inst) -> {
            player.removeEffect(ModEffects.RADIATING_WARMTH.get());
            player.getPersistentData().putBoolean("refresh_rw", true);
            RadiatingWarmthTimer.radiate(player);
        });

        APPLY_HANDLERS.put(ModEffects.ENTWINED_OFFERING_ACTIVE.get(), (player, effect) -> {
            applyModifier(player, Attributes.ARMOR, 8.0, OFFERING_ARMOR_UUID);
        });

        EXPIRE_HANDLERS.put(ModEffects.VORTEX_CD.get(), (player, inst) -> {
            player.addEffect(new MobEffectInstance(ModEffects.VORTEX_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.FOCUSED_ENERGY_CD.get(), (player, inst) -> {
            player.addEffect(new MobEffectInstance(ModEffects.FOCUSED_ENERGY_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });

        EXPIRE_HANDLERS.put(ModEffects.THORNED_PARRY_CD.get(), (player, inst) -> {
            player.addEffect(new MobEffectInstance(ModEffects.THORNED_PARRY_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
        });


        APPLY_HANDLERS.put(ModEffects.HAWKEYE.get(), (player, effect) -> {

            if (effect.getAmplifier() >= 3) {
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.CROSSBOW_QUICK_CHARGE_3, SoundSource.PLAYERS, 1.3F, 1.0F);
            }

            double dex = player.getAttributeValue(ModAttributes.DEX.get());
            double perc = player.getAttributeValue(ModAttributes.PERC.get());
            double scalingMultiplier = 1.0 + ((dex * 0.75 + perc * 0.4) / 100.0);
            int stacks = effect.getAmplifier() + 1;

            double finalPotency = (5.0 * stacks) * scalingMultiplier;
            double finalNockHaste = (9.0 * stacks) * scalingMultiplier;

            applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), finalPotency, HAWKEYE_UUID);
            applyModifier(player, ModAttributes.NOCK_HASTE.get(), finalNockHaste, HAWKEYE_UUID);
        });

        APPLY_HANDLERS.put(ModEffects.FRENZY.get(), (player, effect) -> {

            int frenzyStacks = effect.getAmplifier() + 1;
            removeModifier(player, Attributes.ATTACK_DAMAGE, FRENZY_ATTACK_DAMAGE_UUID);
            double amount = frenzyStacks * 0.1;
            applyModifier(player, Attributes.ATTACK_DAMAGE, amount, FRENZY_ATTACK_DAMAGE_UUID);
        });

        APPLY_HANDLERS.put(ModEffects.INTO_THE_FRAY.get(), (player, effect) -> {
            double speedBonus = (effect.getAmplifier() + 1) * 0.008;
            removeModifier(player, Attributes.MOVEMENT_SPEED, FRAY_SPEED_UUID);
            applyModifier(player, Attributes.MOVEMENT_SPEED, speedBonus, FRAY_SPEED_UUID);
        });

//        APPLY_HANDLERS.put(ModEffects.EXPLOSIVE_TENDENCY_STACK.get(), (player, effect) -> {
//            if (player.getPersistentData().getBoolean("explosive_tendencies_eligible") && effect.getAmplifier() < 2) {
//            }
//        });

        APPLY_HANDLERS.put(ModEffects.ADRENALINE_INJECTION_UP.get(), (player, effect) -> {
            new AttributeApplier().addCrossbowTag(player);
        });

        APPLY_HANDLERS.put(ModEffects.QUANTUM_LEAP_ACTIVE.get(), (player, effect) -> {
            double speedBonus = 0.02;
            double damageBonus = 15;
            if (player.hasEffect(ModEffects.ENHANCED_QUANTUM_LEAP.get())) {
                speedBonus += 0.01;
                damageBonus += 7.5;
            }
            applyModifier(player, ModAttributes.POTENCY.get(), damageBonus, QUANTUM_DAMAGE_UUID);
            applyModifier(player, ModAttributes.MELEE_POTENCY.get(), damageBonus, QUANTUM_DAMAGE_UUID);
            applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), damageBonus, QUANTUM_DAMAGE_UUID);

            applyModifier(player, Attributes.MOVEMENT_SPEED, speedBonus, QUANTUM_SPEED_UUID);
        });

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

            player.playSound(ModSounds.RECKONING_BOOM.get(), 0.6F, 1.0F);

            data.remove(HEALED_NBT);
        });
    }


    public static final UUID SOLARA_UUID = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479");


    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.player.level().isClientSide) return;

        Player player = event.player;

        if (player.tickCount % 20 == 0) {
            CompoundTag data = player.getPersistentData();

            if (data.getBoolean("refresh_rw")) {
                data.putBoolean("refresh_rw", false);
                int cd = (int) ((20*20 - 10) / (1.0 + (AttributeApplier.getScaledValue(player, ModAttributes.AMPLIFICATION.get(), ModAttributes.AMPLIFICATION_MULTIPLIER.get()) / 100.0)));

                player.addEffect(new MobEffectInstance(ModEffects.RADIATING_WARMTH.get(), cd, 0, false, false, true));
            }

            if (data.getBoolean("refresh_et")) {
                data.putBoolean("refresh_et", false);
                player.addEffect(new MobEffectInstance(ModEffects.EXPLOSIVE_TENDENCY_TIMER.get(), 150, 0, false, false, true));
            }

            if (data.getBoolean("refresh_solara")) {
                data.putBoolean("refresh_solara", false);
                player.addEffect(new MobEffectInstance(ModEffects.SOLARA.get(), 23990, 0, false, false, true));
            }
        }
        if (player.tickCount % 20*15 == 0) {
            if (player.hasEffect(ModEffects.SOLARA.get())) {
                long time = player.level().getDayTime() % 24000;
                int phase = (int) (time / 6000);

                double melee = 0;
                double armor = 0;
                double factor;

                switch (phase) {
                    case 0 -> {
                        factor = time / 6000.0;
                        melee = 25.0 * factor;
                        armor = 10.0 * factor;
                    }
                    case 1 -> {
                        factor = 1.0 - (time - 6000) / 6000.0;
                        melee = 25.0 * factor;
                        armor = 10.0 * factor;
                    }
                    case 2 -> {
                        factor = (time - 12000) / 6000.0;
                        melee = -15.0 * factor;
                        armor = -15.0 * factor;
                    }
                    case 3 -> {
                        factor = 1.0 - (time - 18000) / 6000.0;
                        melee = -15.0 * factor;
                        armor = -15.0 * factor;
                    }
                }
                applyModifier(player, ModAttributes.MELEE_POTENCY.get(), melee, SOLARA_UUID);
                applyModifier(player, Attributes.ARMOR, armor, SOLARA_UUID);
            }
        }
    }
}
