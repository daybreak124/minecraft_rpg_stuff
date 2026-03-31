package net.cold.coldsmod.menu_blessing;

import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusCapabilityProvider;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusRegister;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.PlayerBonusCache;
import net.cold.coldsmod.capabilities_and_blessings.effects.SummoningStone;
import net.cold.coldsmod.capabilities_and_blessings.registry.ModEffects;
import net.cold.coldsmod.item.ModItems;
import net.cold.coldsmod.network.NetworkHandler;
import net.cold.coldsmod.network.QuantumLeapSync;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static net.cold.coldsmod.capabilities_and_blessings.effects.Hawkeye.HAWKEYE_UUID;

public class BlessingEffectRegistry {
    public static final Map<Item, Consumer<Player>> ON_APPLY = new HashMap<>();
    public static final Map<Item, Consumer<Player>> ON_REMOVE = new HashMap<>();
    public static final Map<Item, Predicate<Player>> CAN_REMOVE = new HashMap<>();

    public static final UUID FRENZY_ATTACK_DAMAGE = UUID.fromString("d739268d-e62f-4c9b-8301-2812343ab281");
    public static final UUID IMMOLATION_ARMOR = UUID.fromString("d739268d-e62f-4c9b-8301-2895473f3281");


    static {
        // --- WARLORD'S GAZE ---
        register(ModItems.WARLORDS_GAZE.get(),
                player -> {
                    if (!player.hasEffect(ModEffects.INTIMIDATING_PRESENCE_COOLDOWN.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.INTIMIDATING_PRESENCE_READY.get(), -1, 0, false, false, true));
                    }
                },
                player -> {
                    player.removeEffect(ModEffects.INTIMIDATING_PRESENCE_READY.get());
                    player.removeEffect(ModEffects.INTIMIDATING_PRESENCE_COOLDOWN.get());
                },
                player -> !player.hasEffect(ModEffects.INTIMIDATING_PRESENCE_COOLDOWN.get())
        );

        register(ModItems.HORN_OF_FEARMONGERING.get(),
                player -> {
                    if (!player.hasEffect(ModEffects.DARING_SHOUT_COOLDOWN.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.DARING_SHOUT_READY.get(), -1, 0, false, false, true));
                    }
                },
                player -> {
                    player.removeEffect(ModEffects.DARING_SHOUT_READY.get());
                    player.removeEffect(ModEffects.DARING_SHOUT_COOLDOWN.get());
                },
                player -> !player.hasEffect(ModEffects.DARING_SHOUT_COOLDOWN.get())
        );

        register(ModItems.HANKS_EYE.get(),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.HAWKEYE_CONSUME_BOW);
                        cache.unlock(BonusRegister.HAWKEYE_CONSUME_CROSSBOW);
                        cache.unlock(BonusRegister.HAWKEYE_STACK);
                    });
                },
                player -> {
                    player.removeEffect(ModEffects.HAWKEYE.get());

                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.HAWKEYE_CONSUME_BOW);
                        cache.remove(BonusRegister.HAWKEYE_CONSUME_CROSSBOW);
                        cache.remove(BonusRegister.HAWKEYE_STACK);
                    });

                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), HAWKEYE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), HAWKEYE_UUID);
                },
                player -> true
        );

        register(ModItems.SUNSTONE_GEM.get(),
                player -> {
                    PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
                    cache.setSolaraActive(true);
                    player.addEffect(new MobEffectInstance(ModEffects.SOLARA.get(), 24000, 0, false, false, true));
                },
                player -> {
                    PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
                    cache.setSolaraActive(false);
                },
                player -> true
        );

        register(ModItems.RAGE_AMPLIFIER.get(),
                player -> {
                    AttributeApplier.applyModifier(player, Attributes.ATTACK_DAMAGE, 1.0, FRENZY_ATTACK_DAMAGE);
                    AttributeApplier.applyPercentModifierAdditive(player, ModAttributes.INCOMING_DAMAGE_MULTIPLIER.get(), 0.05, FRENZY_ATTACK_DAMAGE);
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.FRENZY);
                    });

                },
                player -> {
                    player.removeEffect(ModEffects.FRENZY.get());
                    AttributeApplier.removeModifier(player, Attributes.ATTACK_DAMAGE, FRENZY_ATTACK_DAMAGE);
                    AttributeApplier.removeModifier(player, ModAttributes.INCOMING_DAMAGE_MULTIPLIER.get(), FRENZY_ATTACK_DAMAGE);
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.FRENZY);
                    });
                },
                player -> true
        );

//        register(ModItems.DROP_OF_SACRIFICIAL_BLOOD.get(),
//                player -> {
//                    if (!player.hasEffect(ModEffects.RECKONING_COOLDOWN.get()) && !player.hasEffect(ModEffects.RECKONING_ACTIVE.get())) {
//                        player.addEffect(new MobEffectInstance(ModEffects.RECKONING.get(), -1, 0, false, false, true));
//                    }
//                },
//                player -> {
//                    player.removeEffect(ModEffects.RECKONING.get());
//                    player.removeEffect(ModEffects.RECKONING_COOLDOWN.get());
//                    player.removeEffect(ModEffects.RECKONING_ACTIVE.get());
//                },
//                player -> !player.hasEffect(ModEffects.RECKONING_COOLDOWN.get()) && !player.hasEffect(ModEffects.RECKONING_ACTIVE.get())
//        );

        register(ModItems.HELL_ON_EARTH.get(),
                player -> {
                    if (!player.hasEffect(ModEffects.DIRECTED_HATRED_COOLDOWN.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.DIRECTED_HATRED_READY.get(), -1, 0, false, false, true));
                    }
                },
                player -> {
                    player.removeEffect(ModEffects.DIRECTED_HATRED_READY.get());
                    player.removeEffect(ModEffects.DIRECTED_HATRED_COOLDOWN.get());
                },
                player -> !player.hasEffect(ModEffects.DIRECTED_HATRED_COOLDOWN.get())
        );

        register(ModItems.BANNER_OF_DETERMINATION.get(),
                player -> player.addEffect(new MobEffectInstance(ModEffects.INTO_THE_FRAY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, false)),
                player -> {
                    player.removeEffect(ModEffects.INTO_THE_FRAY_COOLDOWN.get());
                    player.removeEffect(ModEffects.INTO_THE_FRAY.get());
                    player.removeEffect(ModEffects.INTO_THE_FRAY_COLLISION_CHECK.get());
                    player.removeEffect(ModEffects.INTO_THE_FRAY_ACTIVE.get());
                },
                player -> !player.hasEffect(ModEffects.INTO_THE_FRAY_ACTIVE.get()) && !player.hasEffect(ModEffects.INTO_THE_FRAY_COOLDOWN.get())
        );

        register(ModItems.WORMHOLE.get(),
                player -> {
                    NetworkHandler.sendToClient(new QuantumLeapSync.QuantumLeapFlagPacket(true), (ServerPlayer) player);
                    PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
                    cache.setDfaQuantumSynergy(true);
                    if (!player.hasEffect(ModEffects.QUANTUM_LEAP_ACTIVE.get()) && !player.hasEffect(ModEffects.QUANTUM_LEAP_COOLDOWN.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.QUANTUM_LEAP_READY.get(), -1, 0, false, false, true));
                    }
                    cache.unlock(BonusRegister.QUANTUM_LEAP_REMOVE);
                },
                player -> {
                    player.removeEffect(ModEffects.QUANTUM_LEAP_ACTIVE.get());
                    player.removeEffect(ModEffects.QUANTUM_LEAP_READY.get());
                    player.removeEffect(ModEffects.QUANTUM_LEAP_COOLDOWN.get());

                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.QUANTUM_LEAP_REMOVE);
                    });

                    PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
                    cache.setDfaQuantumSynergy(false);

                    NetworkHandler.sendToClient(new QuantumLeapSync.QuantumLeapFlagPacket(false), (ServerPlayer) player);
                },
                player -> !player.hasEffect(ModEffects.QUANTUM_LEAP_ACTIVE.get()) && !player.hasEffect(ModEffects.QUANTUM_LEAP_COOLDOWN.get())
        );

        register(ModItems.ORB_OF_WORLD_DESTRUCTION.get(),
                player -> {
                    if (!player.hasEffect(ModEffects.DEATH_FROM_ABOVE_COOLDOWN.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.DEATH_FROM_ABOVE_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
                    }
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.DFA_LAND);
                    });
                },
                player -> {
                    player.removeEffect(ModEffects.DEATH_FROM_ABOVE_READY.get());
                    player.removeEffect(ModEffects.DEATH_FROM_ABOVE_COOLDOWN.get());
                    player.removeEffect(ModEffects.ENHANCED_QUANTUM_LEAP.get());
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.QUANTUM_LEAP_REMOVE);
                    });
                },
                player -> !player.hasEffect(ModEffects.DEATH_FROM_ABOVE_COOLDOWN.get())

        );

        register(ModItems.SOUL_MAGNET.get(),
                player -> {
                    if (!player.hasEffect(ModEffects.SOUL_SEVERANCE_COOLDOWN.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.SOUL_SEVERANCE_READY.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, true));
                    }
                },
                player -> {
                    player.removeEffect(ModEffects.SOUL_SEVERANCE_READY.get());
                    player.removeEffect(ModEffects.SOUL_SEVERANCE_COOLDOWN.get());
                },
                player -> !player.hasEffect(ModEffects.SOUL_SEVERANCE_COOLDOWN.get())
        );

        register(ModItems.LIGHTNING_INFUSION.get(),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.CHAIN_LIGHTNING);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.CHAIN_LIGHTNING);
                    });
                },
                player -> true
        );

        register(ModItems.BLOODTHIRST.get(),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.BERSERK_HIT);
                        cache.unlock(BonusRegister.BERSERK_KILL);
                        cache.setBerserkStage0(true);
                    });
                },
                player -> {
                    player.removeEffect(ModEffects.BERSERK.get());
                    player.removeEffect(ModEffects.BERSERK_READY.get());
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.BERSERK_KILL);
                        cache.remove(BonusRegister.BERSERK_HIT);
                        cache.setBerserkStage0(false);
                        cache.setBerserkStage1(false);
                        cache.setBerserkEnraged(false);
                    });
                },
                player -> true
        );

        register(ModItems.BRANCH_OF_THE_WORLD_TREE.get(),
                player -> {
                    if (!player.hasEffect(ModEffects.BRONZEWOOD_COOLDOWN.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.BRONZEWOOD_READY.get(), -1, 0, false, false, true));
                    }
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.BRONZEWOODS_CURSE);
                        cache.unlock(BonusRegister.BRONZEWOODS_KILL);
                    });
                },
                player -> {
                    player.removeEffect(ModEffects.BRONZEWOOD_COOLDOWN.get());
                    player.removeEffect(ModEffects.BRONZEWOOD_READY.get());
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.BRONZEWOODS_CURSE);
                        cache.remove(BonusRegister.BRONZEWOODS_KILL);
                    });
                },
                player -> !player.hasEffect(ModEffects.BRONZEWOOD_COOLDOWN.get())
        );

        register(ModItems.HANKS_OTHER_EYE.get(),
                player -> {
                    if (!player.hasEffect(ModEffects.CLAIRVOYANCE_COOLDOWN.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.CLAIRVOYANCE_READY.get(), -1, 0, false, false, true));
                    }
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.CLAIRVOYANCE_HIT);
                        cache.unlock(BonusRegister.CLAIRVOYANCE_MISS);
                        cache.unlock(BonusRegister.CLAIRVOYANCE_START);
                        cache.unlock(BonusRegister.CLAIRVOYANCE_TICK);
                        cache.unlock(BonusRegister.CLAIRVOYANCE_END_RESET);
                    });
                },
                player -> {
                    player.removeEffect(ModEffects.CLAIRVOYANCE_READY.get());
                    player.removeEffect(ModEffects.CLAIRVOYANCE_COOLDOWN.get());
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.CLAIRVOYANCE_HIT);
                        cache.remove(BonusRegister.CLAIRVOYANCE_MISS);
                        cache.remove(BonusRegister.CLAIRVOYANCE_START);
                        cache.remove(BonusRegister.CLAIRVOYANCE_TICK);
                        cache.remove(BonusRegister.CLAIRVOYANCE_END_RESET);
                        cache.setClairvoyanceHit(false);
                    });
                },
                player -> !player.hasEffect(ModEffects.CLAIRVOYANCE_COOLDOWN.get())
        );

        register(ModItems.LIFE_TOUCH.get(),
                player -> {
                    PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
                    if (!player.hasEffect(ModEffects.LIFE_TOUCH_COOLDOWN.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.LIFE_TOUCH_READY.get(), -1, 0, false, false, true));
                    }
                    cache.unlock(BonusRegister.LIFE_TOUCH_FRIENDLY_FIRE);
                    cache.unlock(BonusRegister.LIFE_TOUCH_IMPACT);
                },
                player -> {
                    player.removeEffect(ModEffects.LIFE_TOUCH_READY.get());
                    player.removeEffect(ModEffects.LIFE_TOUCH_COOLDOWN.get());
                    PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
                    cache.remove(BonusRegister.LIFE_TOUCH_FRIENDLY_FIRE);
                    cache.remove(BonusRegister.LIFE_TOUCH_IMPACT);
                },
                player -> !player.hasEffect(ModEffects.LIFE_TOUCH_COOLDOWN.get())
        );

        register(ModItems.CUPIDS_ARROW.get(),
                player -> {
                    if (!player.hasEffect(ModEffects.DECEPTION_COOLDOWN.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.DECEPTION_READY.get(), -1, 0, false, false, true));
                    }
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.CUPID_ARROW);
                    });
                },
                player -> {
                    player.removeEffect(ModEffects.DECEPTION_COOLDOWN.get());
                    player.removeEffect(ModEffects.DECEPTION_READY.get());
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.CUPID_ARROW);
                    });
                },
                player -> !player.hasEffect(ModEffects.DECEPTION_COOLDOWN.get())
        );

        register(ModItems.IGNITION_MARK.get(),
                player -> {
                    if (!player.hasEffect(ModEffects.EXPLOSIVE_TENDENCY_TIMER.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.EXPLOSIVE_TENDENCY_TIMER.get(), 160, 0, false, false, true));
                    }
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.EXPLOSIVE_TENDENCIES);
                    });
                },
                player -> {
                    player.removeEffect(ModEffects.EXPLOSIVE_TENDENCY_TIMER.get());
                    player.removeEffect(ModEffects.EXPLOSIVE_TENDENCY_STACK.get());
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.EXPLOSIVE_TENDENCIES);
                    });
                },
                player -> true
        );

        register(ModItems.WEAK_POINT_STUDIES.get(),
                player -> {
                    if (!player.hasEffect(ModEffects.EXPLOIT_WEAKNESS_COOLDOWN.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.EXPLOIT_WEAKNESS_READY.get(), -1, 0, false, false, true));
                    }
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.EXPLOIT_WEAKNESS);
                    });
                },
                player -> {
                    player.removeEffect(ModEffects.EXPLOIT_WEAKNESS_COOLDOWN.get());
                    player.removeEffect(ModEffects.EXPLOIT_WEAKNESS_READY.get());
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.EXPLOIT_WEAKNESS);
                    });
                },
                player -> !player.hasEffect(ModEffects.EXPLOIT_WEAKNESS_COOLDOWN.get())
        );

        register(ModItems.ENDLESS_ADRENALINE_SYRINGE.get(),
                player -> {
                    if (!player.hasEffect(ModEffects.ADRENALINE_INJECTION_COOLDOWN.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.ADRENALINE_INJECTION_COOLDOWN.get(), 300, 0, false, false, true));
                    }
                },
                player -> {
                    player.removeEffect(ModEffects.ADRENALINE_INJECTION_COOLDOWN.get());
                    player.removeEffect(ModEffects.ADRENALINE_INJECTION_UP.get());
                },
                player -> true
        );

        register(ModItems.FORTRESS_OF_SOLITUDE.get(),
                player -> {
                    if (!player.hasEffect(ModEffects.RETALIATE_ACTIVE.get()) && !player.hasEffect(ModEffects.RETALIATE_COOLDOWN.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.RETALIATE_READY.get(), -1, 0, false, false, true));
                    }
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.RETALIATE_ACTIVATE);
                        cache.unlock(BonusRegister.RETALIATE_HURT);
                    });
                },
                player -> {
                    player.removeEffect(ModEffects.RETALIATE_COOLDOWN.get());
                    player.removeEffect(ModEffects.RETALIATE_READY.get());
                    player.removeEffect(ModEffects.RETALIATE_ACTIVE.get());
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.RETALIATE_ACTIVATE);
                        cache.remove(BonusRegister.RETALIATE_HURT);
                    });
                },
                player -> !player.hasEffect(ModEffects.RETALIATE_ACTIVE.get()) && !player.hasEffect(ModEffects.RETALIATE_COOLDOWN.get())
        );

        register(ModItems.GUARDIAN_ANGEL.get(),
                player -> {
                    if (!player.hasEffect(ModEffects.BASTION_COOLDOWN.get()) && !player.hasEffect(ModEffects.BASTION_ACTIVE.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.BASTION_READY.get(), -1, 0, false, false, true));
                    }
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.BASTION_ACTIVATE);
                    });
                },
                player -> {
                    player.removeEffect(ModEffects.BASTION_COOLDOWN.get());
                    player.removeEffect(ModEffects.BASTION_READY.get());
                    player.removeEffect(ModEffects.BASTION_ACTIVE.get());
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.BASTION_ACTIVATE);
                    });
                },
                player -> !player.hasEffect(ModEffects.BASTION_ACTIVE.get()) && !player.hasEffect(ModEffects.BASTION_COOLDOWN.get())
        );

        register(ModItems.PRIDE_INFUSED_AIGRETTE.get(),
                player -> {
                    if (!player.hasEffect(ModEffects.OVERCONFIDENCE_READY.get())
                            && !player.hasEffect(ModEffects.OVERCONFIDENCE_ACTIVE.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.OVERCONFIDENCE_READY.get(), -1, 0, false, false, true));
                    }
                },
                player -> {
                    player.removeEffect(ModEffects.OVERCONFIDENCE_ACTIVE.get());
                    player.removeEffect(ModEffects.OVERCONFIDENCE_READY.get());
                    player.removeEffect(ModEffects.OVERCONFIDENCE_COOLDOWN.get());
                },
                player -> !player.hasEffect(ModEffects.OVERCONFIDENCE_ACTIVE.get()) && !player.hasEffect(ModEffects.OVERCONFIDENCE_COOLDOWN.get())
        );


        register(ModItems.IMMOLATION_OF_HEART.get(),
                player -> {
                    AttributeApplier.applyModifier(player, Attributes.ARMOR, -10.0, IMMOLATION_ARMOR);
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.ENTWINED_OFFERING);
                    });
                },
                player -> {
                    AttributeApplier.removeModifier(player, Attributes.ARMOR, IMMOLATION_ARMOR);
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.ENTWINED_OFFERING);
                    });
                },
                player -> true
        );

        register(ModItems.FOX_EYE.get(),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.NIMBLE_GETAWAY);
                        cache.setNimbleEquipped(true);
                    });
                    if (!player.hasEffect(ModEffects.NIMBLE_GETAWAY_COOLDOWN.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.NIMBLE_GETAWAY_ACTIVE.get(), -1, 0, false, false, true));
                    }
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.NIMBLE_GETAWAY);
                        cache.setNimbleEquipped(false);
                    });
                    player.removeEffect(ModEffects.NIMBLE_GETAWAY_ACTIVE.get());
                    player.removeEffect(ModEffects.NIMBLE_GETAWAY_COOLDOWN.get());
                },
                player ->!player.hasEffect(ModEffects.NIMBLE_GETAWAY_COOLDOWN.get())
        );

        register(ModItems.BROKEN_HEALTH_POTION.get(),
                player -> {
                    if (!player.hasEffect(ModEffects.COMBATANTS_AID_CD.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.COMBATANTS_AID_READY.get(), -1, 0, false, false, true));
                    }
                },
                player -> {
                    player.removeEffect(ModEffects.COMBATANTS_AID_CD.get());
                    player.removeEffect(ModEffects.COMBATANTS_AID_READY.get());
                    player.getPersistentData().remove("dash_x");
                    player.getPersistentData().remove("dash_y");
                    player.getPersistentData().remove("dash_z");
                },
                player -> !player.hasEffect(ModEffects.COMBATANTS_AID_CD.get())
        );

        register(ModItems.RESTORING_AURA.get(),
                player -> {

                    if (!player.hasEffect(ModEffects.RADIATING_WARMTH.get())) {
                        int interval = (int) (400 / (1.0 + (AttributeApplier.getScaledValue(player, ModAttributes.AMPLIFICATION.get(), ModAttributes.AMPLIFICATION_MULTIPLIER.get()) / 100.0)));
                        player.addEffect(new MobEffectInstance(ModEffects.RADIATING_WARMTH.get(), interval, 0, false, false, true));
                    }
                },
                player -> {
                    player.removeEffect(ModEffects.RADIATING_WARMTH.get());
                },
                player -> true
        );

        register(ModItems.DIVINE_SHIELD.get(),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.SANCTUARY);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.SANCTUARY);
                    });
                },
                player -> true
        );

        register(ModItems.NATURES_BLESSING.get(),
                player -> {
                    player.addEffect(new MobEffectInstance(ModEffects.PULSATING_LOVE.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, false));
                },
                player -> {
                    player.removeEffect(ModEffects.PULSATING_LOVE.get());
                },
                player -> !player.hasEffect(ModEffects.SOLARA.get())
        );

        register(ModItems.SUMMONING_STONE.get(),
                player -> {player.addEffect(new MobEffectInstance(ModEffects.SBEVE.get(), MobEffectInstance.INFINITE_DURATION, 0, false, false, false));
            },
                player -> {
                    player.removeEffect(ModEffects.SBEVE.get());
                    if (player.level() instanceof ServerLevel serverLevel) {
                        SummoningStone.killSbeve(serverLevel, player);
                        player.getPersistentData().remove("active_sbeve_uuid");
                    }
                },
                player -> !player.hasEffect(ModEffects.SOLARA.get())
        );

        register(ModItems.WIND_WALKER_ARROW.get(),
                player -> {
                    if (!player.hasEffect(ModEffects.VORTEX_CD.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.VORTEX_READY.get(), -1, 0, false, false, true));
                    }
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.VORTEX);
                    });
                },
                player -> {
                    player.removeEffect(ModEffects.VORTEX_CD.get());
                    player.removeEffect(ModEffects.VORTEX_READY.get());
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.VORTEX);
                    });
                },
                player -> !player.hasEffect(ModEffects.VORTEX_CD.get())
        );

        register(ModItems.VIAL_OF_BURSTING_ENERGY.get(),
                player -> {
                    if (!player.hasEffect(ModEffects.FOCUSED_ENERGY_CD.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.FOCUSED_ENERGY_READY.get(), -1, 0, false, false, true));
                    }
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.FOCUSED_ENERGY);
                    });
                },
                player -> {
                    player.removeEffect(ModEffects.FOCUSED_ENERGY_CD.get());
                    player.removeEffect(ModEffects.FOCUSED_ENERGY_READY.get());
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.FOCUSED_ENERGY);
                    });
                },
                player -> !player.hasEffect(ModEffects.FOCUSED_ENERGY_CD.get())
        );

        register(ModItems.THORN_COVERED_FORCEFIELD.get(),
                player -> {
                    if (!player.hasEffect(ModEffects.THORNED_PARRY_CD.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.THORNED_PARRY_READY.get(), -1, 0, false, false, true));
                    }
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.THORN_BLOCK);
                        cache.unlock(BonusRegister.THORN_RELEASE);
                    });
                },
                player -> {
                    player.removeEffect(ModEffects.THORNED_PARRY_CD.get());
                    player.removeEffect(ModEffects.THORNED_PARRY_READY.get());
                    player.removeEffect(ModEffects.PARRY_ELIGIBLE.get());
                    player.getPersistentData().remove("last_attacker_uuid");
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.THORN_BLOCK);
                        cache.remove(BonusRegister.THORN_RELEASE);
                    });
                },
                player -> !player.hasEffect(ModEffects.THORNED_PARRY_CD.get())
        );

        register(ModItems.DIVINITY_EXTRACTION.get(),
                player -> {
                    if (!player.hasEffect(ModEffects.BLESSED_LAND_CD.get())) {
                        player.addEffect(new MobEffectInstance(ModEffects.BLESSED_LAND_READY.get(), -1, 0, false, false, true));
                    }
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.BLESSED_LAND);
                    });
                },
                player -> {
                    player.removeEffect(ModEffects.BLESSED_LAND_READY.get());
                    player.removeEffect(ModEffects.BLESSED_LAND_CD.get());
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.BLESSED_LAND);
                    });
                },
                player -> !player.hasEffect(ModEffects.BLESSED_LAND_CD.get())
        );
    }

    public static void register(Item item, Consumer<Player> onApply, Consumer<Player> onRemove, Predicate<Player> canRemove) {
        ON_APPLY.put(item, onApply);
        ON_REMOVE.put(item, onRemove);
        CAN_REMOVE.put(item, canRemove);
    }
}