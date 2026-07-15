package net.cold.coldsmod.menu_stat;

import net.cold.coldsmod.ModMessages;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusCapabilityProvider;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusRegister;
import net.cold.coldsmod.capabilities_and_blessings.registry.ModEffects;
import net.cold.coldsmod.item.ModItems;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static net.cold.coldsmod.capabilities_and_blessings.effects.Hawkeye.HAWKEYE_UUID;
import static net.cold.coldsmod.stat.AttributeApplier.*;

public class FeatUpgradeHandlerRegistry {
    private static final Map<String, FeatEntry> FEAT_REGISTRY = new HashMap<>();
    public static final Map<FeatEntry, Predicate<Player>> CAN_REMOVE = new HashMap<>();
    private static final Map<Integer, FeatEntry> ID_TO_ENTRY = new HashMap<>();
    private static final Map<Integer, Integer> ID_TO_TIER = new HashMap<>();

    public static final int MAX_FEAT_POINTS = 9;

    public static final UUID FRENZY_ATTACK_DAMAGE = UUID.fromString("d739268d-e62f-4c9b-8301-2812343ab281");


    public record FeatEntry(int id, String name, List<Component> tooltip, Consumer<Player> onApply, Consumer<Player> onRemove, Object icon) {}

    public static void register(String tree, int tier, int slot, FeatEntry entry, Predicate<Player> canRemove) {
        FEAT_REGISTRY.put(tree + "_" + tier + "_" + slot, entry);
        ID_TO_ENTRY.put(entry.id(), entry);
        ID_TO_TIER.put(entry.id(), tier);
        CAN_REMOVE.put(entry, canRemove);
    }

    public static void register(String tree, int tier, int slot, FeatEntry entry) {
        FEAT_REGISTRY.put(tree + "_" + tier + "_" + slot, entry);
        ID_TO_ENTRY.put(entry.id(), entry);
        ID_TO_TIER.put(entry.id(), tier);
    }

    static {
        FeatUpgradeHandlerRegistry.register("LION", 1, 1, new FeatEntry(
                1,
                "Executioner",
                List.of(Component.literal("Executioner").withStyle(ChatFormatting.BLUE), Component.literal("+10% Damage to targets with 50% or less health")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.EXECUTIONER);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.EXECUTIONER);
                    });
                },
        new ItemStack(Items.WITHER_ROSE)
        ));

        FeatUpgradeHandlerRegistry.register("LION", 1, 2, new FeatEntry(
                2,
                "Vanguardian",
                List.of(Component.literal("Vanguardian").withStyle(ChatFormatting.BLUE), Component.literal("When using an active blessing, gain +3 armor and 2% damage. Stacks up to 3x. Lasts 8s")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.VANGUARDIAN);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.VANGUARDIAN);
                    });
                },
                PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER)
        ));

        FeatUpgradeHandlerRegistry.register("LION", 1, 3, new FeatEntry(
                3,
                "Surging Blood",
                List.of(Component.literal("Surging Blood").withStyle(ChatFormatting.BLUE), Component.literal("For every health you are below 20, gain 2% damage")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.SURGING_BLOOD_DIRECT);
                        cache.unlock(BonusRegister.SURGING_BLOOD_INDIRECT);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.SURGING_BLOOD_DIRECT);
                        cache.remove(BonusRegister.SURGING_BLOOD_INDIRECT);
                    });
                },
                new ItemStack(Items.REDSTONE_BLOCK)
        ));

        FeatUpgradeHandlerRegistry.register("LION", 2, 1, new FeatEntry(
                4,
                "Destroyer",
                List.of(Component.literal("Destroyer").withStyle(ChatFormatting.DARK_PURPLE), Component.literal("Activating blessings increases your damage by 12.5% for 6s")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.ENRAGE);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.ENRAGE);
                    });
                },
                PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.HARMING)
        ));

        FeatUpgradeHandlerRegistry.register("LION", 2, 2, new FeatEntry(
                5,
                "Bravery",
                List.of(Component.literal("Bravery").withStyle(ChatFormatting.DARK_PURPLE), Component.literal("When using a blessing, grant yourself and allies within 8 blocks 13% move speed for 10s")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.COMMANDER);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.COMMANDER);
                    });
                },
                PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.SWIFTNESS)
        ));

        FeatUpgradeHandlerRegistry.register("LION", 2, 3, new FeatEntry(
                6,
                "Frenzy",
                List.of(Component.literal("Frenzy").withStyle(ChatFormatting.DARK_PURPLE), Component.literal("+1 Attack Damage but +5% Damage Taken Increase. On every weapon attack, Attack Damage +0.1 but Damage Taken +1% for 6s, up to 20 stacks")),
                player -> {
                    AttributeApplier.applyModifier(player, Attributes.ATTACK_DAMAGE, 1.0, FRENZY_ATTACK_DAMAGE);
                    AttributeApplier.applyModifier(player,  ModAttributes.INCOMING_DAMAGE_MULTIPLIER.get(), FRENZY_ATTACK_DAMAGE.toString(), 0.05, AttributeModifier.Operation.MULTIPLY_BASE);

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
                new ItemStack(ModItems.RAGE_AMPLIFIER.get())
        ));

        FeatUpgradeHandlerRegistry.register("LION", 3, 1, new FeatEntry(
                7,
                "Courageous Blow",
                List.of(Component.literal("Courageous Blow").withStyle(ChatFormatting.GOLD), Component.literal("Weapon attacks now deal 6% of your Health as additional damage. Damage reduced to 2% for Indirect melee hits.")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.COURAGEOUS_BLOW_DIRECT);
                        cache.unlock(BonusRegister.COURAGEOUS_BLOW_INDIRECT);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.COURAGEOUS_BLOW_DIRECT);
                        cache.remove(BonusRegister.COURAGEOUS_BLOW_INDIRECT);
                    });
                },
                MobEffects.HEALTH_BOOST
        ));

        FeatUpgradeHandlerRegistry.register("LION", 3, 2, new FeatEntry(
                8,
                "Armor Break",
                List.of(Component.literal("Armor Break").withStyle(ChatFormatting.GOLD), Component.literal("Melee hits reduce your enemy's armor by 8 and increase yours by 4 for 4s")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.ARMOR_BREAK_DIRECT);
                        cache.unlock(BonusRegister.ARMOR_BREAK_INDIRECT);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.ARMOR_BREAK_DIRECT);
                        cache.remove(BonusRegister.ARMOR_BREAK_INDIRECT);
                    });
                },
                MobEffects.DAMAGE_RESISTANCE
        ));

        FeatUpgradeHandlerRegistry.register("LION", 3, 3, new FeatEntry(
                9,
                "Retaliation",
                List.of(Component.literal("Retaliation").withStyle(ChatFormatting.GOLD), Component.literal("20% chance on damage taken to deal 300% of the pre-mitigated damage to the attacker")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.REVENGEANCE);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.REVENGEANCE);
                    });
                },
                new ItemStack(Items.RECOVERY_COMPASS)
        ));

        FeatUpgradeHandlerRegistry.register("LION", 4, 1, new FeatEntry(
                10,
                "Enchanted Blade",
                List.of(Component.literal("Enchanted Blade").withStyle(ChatFormatting.AQUA), Component.literal("Chain Lightning bounce damage increases by 12.5%, Bronzewood Curse debuff increases by 3% and reduces enemy damage by 5%, Handle of Bloodthirst effect increases by 15%")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setBloodthirstEnhanced(true);
                        cache.setBronzewoodEnhanced(true);
                        cache.setChainLightningEnhanced(true);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setBloodthirstEnhanced(false);
                        cache.setBronzewoodEnhanced(false);
                        cache.setChainLightningEnhanced(false);
                    });
                },
                new ItemStack(Items.GOLDEN_SWORD)
        ));

        FeatUpgradeHandlerRegistry.register("LION", 4, 2, new FeatEntry(
                11,
                "Opportunist",
                List.of(Component.literal("Opportunist").withStyle(ChatFormatting.AQUA), Component.literal("Whenever a negative effect is applied, gain 13% damage. Lasts 10s")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.ABSORBED_EVIL);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.ABSORBED_EVIL);
                    });
                },
                new ItemStack(Items.GLISTERING_MELON_SLICE)
        ));

        FeatUpgradeHandlerRegistry.register("LION", 4, 3, new FeatEntry(
                12,
                "Soul Devourer",
                List.of(Component.literal("Soul Devourer").withStyle(ChatFormatting.AQUA), Component.literal("Your weapon attacks have a 15% chance and non-direct melee hits have a 7.5% chance to proc a tick of Soul Severance")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.SOUL_SEPARATION_DIRECT);
                        cache.unlock(BonusRegister.SOUL_SEPARATION_INDIRECT);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.SOUL_SEPARATION_DIRECT);
                        cache.remove(BonusRegister.SOUL_SEPARATION_INDIRECT);
                    });
                },
                MobEffects.WITHER
        ));

        FeatUpgradeHandlerRegistry.register("LION", 5, 1, new FeatEntry(
                13,
                "Head Hunter",
                List.of(Component.literal("Head Hunter").withStyle(ChatFormatting.LIGHT_PURPLE), Component.literal("Your weapon attacks execute targets below 7% health. Threshold increases with Melee Accuracy stat")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.EXECUTE);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.EXECUTE);
                    });
                },
                new ItemStack(Items.DAMAGED_ANVIL)
        ));

        FeatUpgradeHandlerRegistry.register("LION", 5, 2, new FeatEntry(
                14,
                "Vampiric Touch",
                List.of(Component.literal("Vampiric Touch").withStyle(ChatFormatting.LIGHT_PURPLE), Component.literal("Weapon attacks heal you for 4% of the damage done, halved for indirect melee hits. Affected by rejuvenation")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.VAMPIRIC_TOUCH_DIRECT);
                        cache.unlock(BonusRegister.VAMPIRIC_TOUCH_INDIRECT);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.VAMPIRIC_TOUCH_DIRECT);
                        cache.remove(BonusRegister.VAMPIRIC_TOUCH_INDIRECT);
                    });
                },
                new ItemStack(Items.EXPERIENCE_BOTTLE)
        ));

        FeatUpgradeHandlerRegistry.register("LION", 5, 3, new FeatEntry(
                15,
                "Incubus",
                List.of(Component.literal("Incubus").withStyle(ChatFormatting.LIGHT_PURPLE), Component.literal("Multiplies the negative health effect of Tempting Whispers accessory by 0.8x")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setTemptingBuff(true);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setTemptingBuff(false);
                    });
                },
                MobEffects.ABSORPTION
        ));

        FeatUpgradeHandlerRegistry.register("LION", 6, 2, new FeatEntry(
                16,
                "Bloodworm",
                List.of(Component.literal("Bloodworm").withStyle(ChatFormatting.RED), Component.literal("Damage +0.2% for 1 hour on killing a monster, stacks up to 100 times")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.BLOODWORM);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.BLOODWORM);
                    });
                },
                new ItemStack(Items.DRAGON_HEAD)
        ));

        // ------------------------------------------------------------------------------------------------------
        // ROGUE
        // ------------------------------------------------------------------------------------------------------


        FeatUpgradeHandlerRegistry.register("NIGHT", 1, 1, new FeatEntry(
                21,
                "Hank's Eye",
                List.of(Component.literal("Hank's Eye").withStyle(ChatFormatting.BLUE), Component.literal("Weapon attacks increase your Projectile Potency by 4 and Nock Haste by 13.5 for 8s up to 4 times. Effects are increased by 0.75% per Dexterity and 0.4% per Perception. Stacks consumed upon landing a shot.")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.HAWKEYE_STACK);
                        cache.unlock(BonusRegister.HAWKEYE_CONSUME_CROSSBOW);
                        cache.unlock(BonusRegister.HAWKEYE_CONSUME_BOW);
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
                new ItemStack(ModItems.HANKS_EYE.get())
        ));

        FeatUpgradeHandlerRegistry.register("NIGHT", 1, 2, new FeatEntry(
                22,
                "Sharpened Blade",
                List.of(Component.literal("Sharpened Blade").withStyle(ChatFormatting.BLUE), Component.literal("+2.5 Accuracy for 4s on every melee or ranged weapon hit, up to 6 stacks")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.CRITICAL_ASCENSION_MELEE);
                        cache.unlock(BonusRegister.CRITICAL_ASCENSION_RANGED);
                        cache.unlock(BonusRegister.CRITICAL_ASCENSION_RANGED_2);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.CRITICAL_ASCENSION_MELEE);
                        cache.remove(BonusRegister.CRITICAL_ASCENSION_RANGED);
                        cache.remove(BonusRegister.CRITICAL_ASCENSION_RANGED_2);
                    });
                },
                new ItemStack(Items.GOLDEN_AXE)
        ));

        FeatUpgradeHandlerRegistry.register("NIGHT", 1, 3, new FeatEntry(
                23,
                "Snipe",
                List.of(Component.literal("Snipe").withStyle(ChatFormatting.BLUE), Component.literal("Projectile shot damage increased by 1% per block away from the target, up to 25%")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.SNIPE_BOW);
                        cache.unlock(BonusRegister.SNIPE_CROSSBOW);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.SNIPE_BOW);
                        cache.remove(BonusRegister.SNIPE_CROSSBOW);
                    });
                },
                new ItemStack(Items.SPECTRAL_ARROW)
        ));

        FeatUpgradeHandlerRegistry.register("NIGHT", 2, 1, new FeatEntry(
                24,
                "Stealth",
                List.of(Component.literal("Stealth").withStyle(ChatFormatting.DARK_PURPLE), Component.literal("After a kill, movement speed +30% and invisibility for 3 seconds")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.STEALTH);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.STEALTH);
                    });
                },
                new ItemStack(Items.NETHERITE_HELMET)
        ));

        FeatUpgradeHandlerRegistry.register("NIGHT", 2, 2, new FeatEntry(
                25,
                "Marksman",
                List.of(Component.literal("Marksman").withStyle(ChatFormatting.DARK_PURPLE), Component.literal("Bow/Crossbow charge slowdown reduced from 80% to 30%")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setBowSlowdownCancel(true);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setBowSlowdownCancel(false);
                    });
                },
                new ItemStack(Items.BOW)
        ));

        FeatUpgradeHandlerRegistry.register("NIGHT", 2, 3, new FeatEntry(
                26,
                "Serpent Fang Blade",
                List.of(Component.literal("Serpent Fang Blade").withStyle(ChatFormatting.DARK_PURPLE), Component.literal("Your weapon attacks and projectile hits apply 2 stacks, indirect melee hits apply 1 stack of Bleed for 4s. Bleed deals 0.2 DoT damage/sec per stack. Stacks up to 10x")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.BLEED_BOW);
                        cache.unlock(BonusRegister.BLEED_CROSSBOW);
                        cache.unlock(BonusRegister.BLEED_MELEE);
                        cache.unlock(BonusRegister.BLEED_INDIRECT_MELEE);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.BLEED_BOW);
                        cache.remove(BonusRegister.BLEED_CROSSBOW);
                        cache.remove(BonusRegister.BLEED_MELEE);
                        cache.remove(BonusRegister.BLEED_INDIRECT_MELEE);
                    });
                },
                new ItemStack(Items.REDSTONE)
        ));

        FeatUpgradeHandlerRegistry.register("NIGHT", 3, 1, new FeatEntry(
                27,
                "Gravity Bender",
                List.of(Component.literal("Gravity Bender").withStyle(ChatFormatting.GOLD), Component.literal("Your arrows are no longer affected by gravity")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setBowGravityCancel(true);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setBowGravityCancel(false);
                    });
                },
                new ItemStack(Items.ARROW)
        ));

        FeatUpgradeHandlerRegistry.register("NIGHT", 3, 2, new FeatEntry(
                28,
                "Fox Eye",
                List.of(Component.literal("Fox Eye").withStyle(ChatFormatting.GOLD), Component.literal("Every 20s, gain an effect that allows you to evade the next incoming attack. Fall Damage cannot be evaded")),
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
                new ItemStack(ModItems.FOX_EYE.get())),
                player -> !player.hasEffect(ModEffects.NIMBLE_GETAWAY_COOLDOWN.get())
        );

        FeatUpgradeHandlerRegistry.register("NIGHT", 3, 3, new FeatEntry(
                29,
                "Lightning Infusion",
                List.of(Component.literal("Lightning Infusion").withStyle(ChatFormatting.GOLD), Component.literal("Your arrow shots now apply Chain Lightning effect but only for 20%. +10% if Chain Lightning is equipped. +10% if Enchanted Blade Identity is active")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.CHAIN_LIGHTNING_BOW);
                        cache.unlock(BonusRegister.CHAIN_LIGHTNING_CROSSBOW);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.CHAIN_LIGHTNING_BOW);
                        cache.remove(BonusRegister.CHAIN_LIGHTNING_CROSSBOW);
                    });
                },
                new ItemStack(ModItems.LIGHTNING_INFUSION.get())
        ));

        FeatUpgradeHandlerRegistry.register("NIGHT", 4, 1, new FeatEntry(
                30,
                "Bladedancer",
                List.of(Component.literal("Bladedancer").withStyle(ChatFormatting.AQUA), Component.literal("Attack speed +0.05 per hit for 10s. Up to 5 stacks")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.BLADEDANCER_BOW);
                        cache.unlock(BonusRegister.BLADEDANCER_MELEE);
                        cache.unlock(BonusRegister.BLADEDANCER_CROSSBOW);
                        cache.unlock(BonusRegister.BLADEDANCER_INDIRECT_MELEE);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.BLADEDANCER_BOW);
                        cache.remove(BonusRegister.BLADEDANCER_MELEE);
                        cache.remove(BonusRegister.BLADEDANCER_CROSSBOW);
                        cache.remove(BonusRegister.BLADEDANCER_INDIRECT_MELEE);
                    });
                },
                new ItemStack(Items.MAGMA_CREAM)
        ));

        FeatUpgradeHandlerRegistry.register("NIGHT", 4, 2, new FeatEntry(
                31,
                "Evasion Boost",
                List.of(Component.literal("Evasion Boost").withStyle(ChatFormatting.AQUA), Component.literal("+10% Evasion")),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.EVASION.get(), 0.1d, UUID.fromString("792d39bc-810f-4002-9bbf-78cd3907acee"));
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.EVASION.get(), UUID.fromString("792d39bc-810f-4002-9bbf-78cd3907acee"));
                },
                new ItemStack(Items.HONEYCOMB)
        ));

        FeatUpgradeHandlerRegistry.register("NIGHT", 4, 3, new FeatEntry(
                32,
                "Hurricane",
                List.of(Component.literal("Hurricane").withStyle(ChatFormatting.AQUA), Component.literal("Targets within Vortex area are inflicted with a 12% increased damage taken debuff")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setVortexEnhanced(true);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setVortexEnhanced(false);
                    });
                },
                new ItemStack(Items.KELP)
        ));

        FeatUpgradeHandlerRegistry.register("NIGHT", 5, 1, new FeatEntry(
                33,
                "Soul Collector",
                List.of(Component.literal("Soul Collector").withStyle(ChatFormatting.LIGHT_PURPLE), Component.literal("Gain 2% damage for 2.5s for every target your Soul Severance or Vortex damages. Up to 20 stacks")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setVortexSeveranceEnhanced(true);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setVortexSeveranceEnhanced(false);
                    });
                },
                new ItemStack(Items.SOUL_LANTERN)
        ));

        FeatUpgradeHandlerRegistry.register("NIGHT", 5, 2, new FeatEntry(
                34,
                "Soulpiercer",
                List.of(Component.literal("Soulpiercer").withStyle(ChatFormatting.LIGHT_PURPLE), Component.literal("Your projectile shots have a 15% chance to activate a tick of Vortex on the enemy")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.VORTEX_CROSSBOW);
                        cache.unlock(BonusRegister.VORTEX_BOW);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.VORTEX_CROSSBOW);
                        cache.remove(BonusRegister.VORTEX_BOW);
                    });
                },
                new ItemStack(Items.SOUL_CAMPFIRE)
        ));

        FeatUpgradeHandlerRegistry.register("NIGHT", 5, 3, new FeatEntry(
                35,
                "Critical Boost",
                List.of(Component.literal("Critical Boost").withStyle(ChatFormatting.LIGHT_PURPLE), Component.literal("When your attacks dont crit, increase all accuracy by 20% for 10s")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.GAMBIT_MELEE_NONCRIT);
                        cache.unlock(BonusRegister.GAMBIT_MELEE_CRIT);
                        cache.unlock(BonusRegister.GAMBIT_MELEE_NONCRIT_INDIRECT);
                        cache.unlock(BonusRegister.GAMBIT_MELEE_CRIT_INDIRECT);
                        cache.unlock(BonusRegister.GAMBIT_BOW_NONCRIT);
                        cache.unlock(BonusRegister.GAMBIT_BOW_CRIT);
                        cache.unlock(BonusRegister.GAMBIT_CROSSBOW_NONCRIT);
                        cache.unlock(BonusRegister.GAMBIT_CROSSBOW_CRIT);
                        cache.unlock(BonusRegister.GAMBIT_RANGE_INDIRECT_NONCRIT);
                        cache.unlock(BonusRegister.GAMBIT_RANGE_INDIRECT_CRIT);
                        cache.unlock(BonusRegister.GAMBIT_OTHER_CRIT);
                        cache.unlock(BonusRegister.GAMBIT_OTHER_NONCRIT);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.GAMBIT_MELEE_NONCRIT);
                        cache.remove(BonusRegister.GAMBIT_MELEE_CRIT);
                        cache.remove(BonusRegister.GAMBIT_MELEE_NONCRIT_INDIRECT);
                        cache.remove(BonusRegister.GAMBIT_MELEE_CRIT_INDIRECT);
                        cache.remove(BonusRegister.GAMBIT_BOW_NONCRIT);
                        cache.remove(BonusRegister.GAMBIT_BOW_CRIT);
                        cache.remove(BonusRegister.GAMBIT_CROSSBOW_NONCRIT);
                        cache.remove(BonusRegister.GAMBIT_CROSSBOW_CRIT);
                        cache.remove(BonusRegister.GAMBIT_RANGE_INDIRECT_NONCRIT);
                        cache.remove(BonusRegister.GAMBIT_RANGE_INDIRECT_CRIT);
                        cache.remove(BonusRegister.GAMBIT_OTHER_CRIT);
                        cache.remove(BonusRegister.GAMBIT_OTHER_NONCRIT);
                    });
                },
                new ItemStack(Items.BLAZE_POWDER)
        ));

        FeatUpgradeHandlerRegistry.register("NIGHT", 6, 2, new FeatEntry(
                36,
                "Heartpiercer",
                List.of(Component.literal("Heartpiercer").withStyle(ChatFormatting.RED), Component.literal("Your projectile shots make the target glow for 7s. While glowing, target takes 13.5% more damage and 32% more damage from DoTs. A target can only be affected every 12s")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.HEARTPIERCER_BOW);
                        cache.unlock(BonusRegister.HEARTPIERCER_CROSSBOW);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.HEARTPIERCER_BOW);
                        cache.remove(BonusRegister.HEARTPIERCER_CROSSBOW);
                    });
                },
                MobEffects.BAD_OMEN
        ));

        // ------------------------------------------------------------------------------------------------------
        // TANK
        // ------------------------------------------------------------------------------------------------------



        FeatUpgradeHandlerRegistry.register("STEEL", 1, 1, new FeatEntry(
                41,
                "Undying Heart",
                List.of(Component.literal("Undying Heart").withStyle(ChatFormatting.BLUE), Component.literal("Every time you are damaged when under half health, heal for 0.15 health")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.STEEL_DEFENSE);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.STEEL_DEFENSE);
                    });
                },
                PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRONG_HEALING)
        ));

        FeatUpgradeHandlerRegistry.register("STEEL", 1, 2, new FeatEntry(
                42,
                "Brimstone Shield",
                List.of(Component.literal("Brimstone Shield").withStyle(ChatFormatting.BLUE), Component.literal("When using a blessing, grant allies within 8 blocks +4 Armor for 6s")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.BRIMSTONE_SHIELD);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.BRIMSTONE_SHIELD);
                    });
                },
                MobEffects.FIRE_RESISTANCE
        ));

        FeatUpgradeHandlerRegistry.register("STEEL", 1, 3, new FeatEntry(
                43,
                "Neuron Crusher",
                List.of(Component.literal("Neuron Crusher").withStyle(ChatFormatting.BLUE), Component.literal("Your weapon attacks have a 10% chance to stun your target for 2s and increase their damage taken by 7% for 5s")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.ARMOR_PIERCE);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.ARMOR_PIERCE);
                    });
                },
                MobEffects.WEAKNESS
        ));

        FeatUpgradeHandlerRegistry.register("STEEL", 2, 1, new FeatEntry(
                44,
                "Phalanx",
                List.of(Component.literal("Phalanx").withStyle(ChatFormatting.DARK_PURPLE), Component.literal("Movement reduction effect from shield blocking is removed")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setShieldSlowdownCancel(true);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setShieldSlowdownCancel(false);
                    });
                },
                new ItemStack(Items.SHIELD)
        ));

        FeatUpgradeHandlerRegistry.register("STEEL", 2, 2, new FeatEntry(
                45,
                "Absorption",
                List.of(Component.literal("Absorption").withStyle(ChatFormatting.DARK_PURPLE), Component.literal("Whenever you take damage, restore 1% of your health")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.ABSORPTION);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.ABSORPTION);
                    });
                },
                PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.HEALING)
        ));

        FeatUpgradeHandlerRegistry.register("STEEL", 2, 3, new FeatEntry(
                46,
                "Revival",
                List.of(Component.literal("Revival").withStyle(ChatFormatting.DARK_PURPLE), Component.literal("When you drop to 2 or less health, consume hunger to heal yourself at a 4:1 ratio. Hunger cant drop below 2. 10m cd")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.ASCENSION);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.ASCENSION);
                    });
                },
                MobEffects.SATURATION),
                player -> !player.hasEffect(ModEffects.ASCENSION_CD.get())
        );

        FeatUpgradeHandlerRegistry.register("STEEL", 3, 1, new FeatEntry(
                47,
                "Deflection",
                List.of(Component.literal("Deflection").withStyle(ChatFormatting.GOLD), Component.literal("Damage taken reduced by 1-8% randomly")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.DEFLECT);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.DEFLECT);
                    });
                },
                new ItemStack(Items.PUFFERFISH)
        ));

        FeatUpgradeHandlerRegistry.register("STEEL", 3, 2, new FeatEntry(
                48,
                "Shield Bash",
                List.of(Component.literal("Shield Bash").withStyle(ChatFormatting.GOLD), Component.literal("Whenever you block an attack, target takes +10% damage for 4s")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.RETALIATING_BLOW);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.RETALIATING_BLOW);
                    });
                },
                MobEffects.DIG_SPEED
        ));

        FeatUpgradeHandlerRegistry.register("STEEL", 3, 3, new FeatEntry(
                49,
                "Curse of Levitation",
                List.of(Component.literal("Curse of Levitation").withStyle(ChatFormatting.GOLD), Component.literal("10% chance on receiving damage to levitate the target for 3s then crash them down, causing them to take 4.5 extra Fall Damage")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.FLOATING_CURSE);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.FLOATING_CURSE);
                    });
                },
                MobEffects.LEVITATION
        ));

        FeatUpgradeHandlerRegistry.register("STEEL", 4, 1, new FeatEntry(
                50,
                "Precise Blow",
                List.of(Component.literal("Precise Blow").withStyle(ChatFormatting.AQUA), Component.literal("When using a blessing, grant yourself and allies within 5 blocks +10 Precision & +10% Precision for 4s")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.FIREWIELDER);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.FIREWIELDER);
                    });
                },
                new ItemStack(Items.ENDER_EYE)
        ));

        FeatUpgradeHandlerRegistry.register("STEEL", 4, 2, new FeatEntry(
                51,
                "Menace",
                List.of(Component.literal("Menace").withStyle(ChatFormatting.AQUA), Component.literal("Directed Hatred & Bronzewood cooldown -6s")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setTankBlessingEnhanced(true);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setTankBlessingEnhanced(false);
                    });
                },
                MobEffects.DIG_SLOWDOWN
        ));

        FeatUpgradeHandlerRegistry.register("STEEL", 4, 3, new FeatEntry(
                52,
                "Shieldbearer",
                List.of(Component.literal("Shieldbearer").withStyle(ChatFormatting.AQUA), Component.literal("Bastion duration increases by 50%, Retaliate damage increases by 35%, Thorned Parry damage increases by 50%")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setShieldBlessingEnhanced(true);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setShieldBlessingEnhanced(false);
                    });
                },
                new ItemStack(Items.SHULKER_SHELL)
        ));

        FeatUpgradeHandlerRegistry.register("STEEL", 5, 1, new FeatEntry(
                53,
                "Shieldeater",
                List.of(Component.literal("Shieldeater").withStyle(ChatFormatting.LIGHT_PURPLE), Component.literal("Parry has a 50% chance to restore 2 hunger, Retaliate restores 1 hunger for every 3 stacks")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setShieldBlessingHungerEnhanced(true);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setShieldBlessingHungerEnhanced(false);
                    });
                },
                new ItemStack(Items.GOLDEN_CARROT)
        ));

        FeatUpgradeHandlerRegistry.register("STEEL", 5, 2, new FeatEntry(
                54,
                "Soulpiercer",
                List.of(Component.literal("Soulpiercer").withStyle(ChatFormatting.LIGHT_PURPLE), Component.literal("25% chance to evade incoming projectile hits")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setArrowEvasion(true);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setArrowEvasion(false);
                    });
                },
                new ItemStack(Items.TIPPED_ARROW)
        ));

        FeatUpgradeHandlerRegistry.register("STEEL", 5, 3, new FeatEntry(
                55,
                "Swordmaster's Challenge",
                List.of(Component.literal("Swordmaster's Challenge").withStyle(ChatFormatting.LIGHT_PURPLE), Component.literal("Targets that attack you have a 30% chance to take damage equal to 15% of your Fortitude or Constitution")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.DESTROYER);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.DESTROYER);
                    });
                },
                new ItemStack(Items.GOAT_HORN)
        ));

        FeatUpgradeHandlerRegistry.register("STEEL", 6, 2, new FeatEntry(
                56,
                "Immortal's Pride",
                List.of(Component.literal("Immortal's Pride").withStyle(ChatFormatting.RED), Component.literal("Taking damage has a 20% chance to grant you Bastion for 1s, Directed Hatred or Sanctuary, or apply Vortex or Exploit Weakness to the enemy")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.IMMORTAL);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.IMMORTAL);
                    });
                },
                new ItemStack(Items.DRAGON_BREATH)
        ));

        // ------------------------------------------------------------------------------------------------------
        // HEALER
        // ------------------------------------------------------------------------------------------------------


        FeatUpgradeHandlerRegistry.register("HERALD", 1, 1, new FeatEntry(
                61,
                "Revitalize",
                List.of(Component.literal("Revitalize").withStyle(ChatFormatting.BLUE), Component.literal("On kills, heal allies within 6 blocks for 2 health")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.REVITALIZE);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.REVITALIZE);
                    });
                },
                new ItemStack(Items.SKELETON_SKULL)
        ));

        FeatUpgradeHandlerRegistry.register("HERALD", 1, 2, new FeatEntry(
                62,
                "Enhanced Sanctuary",
                List.of(Component.literal("Enhanced Sanctuary").withStyle(ChatFormatting.BLUE), Component.literal("Sanctuary Shared Armor effect duration increases by 5s")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setSanctuaryEnhance(true);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setSanctuaryEnhance(false);
                    });
                },
                new ItemStack(ModItems.DIVINE_SHIELD.get())
        ));

        FeatUpgradeHandlerRegistry.register("HERALD", 1, 3, new FeatEntry(
                63,
                "Holy Avenger",
                List.of(Component.literal("Holy Avenger").withStyle(ChatFormatting.BLUE), Component.literal("On active blessing use, allies within 5 blocks gain +15 Haste for 7s")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.HOLY_AVENGER);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.HOLY_AVENGER);
                    });
                },
                PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), Potions.FIRE_RESISTANCE)
        ));

        FeatUpgradeHandlerRegistry.register("HERALD", 2, 1, new FeatEntry(
                64,
                "Radiating Strike",
                List.of(Component.literal("Radiating Strike").withStyle(ChatFormatting.DARK_PURPLE), Component.literal("On hit, 20% chance to heal the ally with the lowest health within 6 blocks for 15% of the damage")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.RADIATING_STRIKE);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.RADIATING_STRIKE);
                    });
                },
                new ItemStack(Items.GOLDEN_APPLE)
        ));

        FeatUpgradeHandlerRegistry.register("HERALD", 2, 2, new FeatEntry(
                65,
                "Recovery",
                List.of(Component.literal("Recovery").withStyle(ChatFormatting.DARK_PURPLE), Component.literal("When a negative effect is applied, grant +6 Armor and Rejuvenation to yourself and allies within 6 blocks for 13s")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.RECOVERY);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.RECOVERY);
                    });
                },
                new ItemStack(Items.TURTLE_HELMET)
        ));

        FeatUpgradeHandlerRegistry.register("HERALD", 2, 3, new FeatEntry(
                66,
                "Regeneration",
                List.of(Component.literal("Regeneration").withStyle(ChatFormatting.DARK_PURPLE), Component.literal("On active blessing use, apply Regeneration I for 5s to allies within 8 blocks")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.REGENERATION);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.REGENERATION);
                    });
                },
                MobEffects.REGENERATION
        ));

        FeatUpgradeHandlerRegistry.register("HERALD", 3, 1, new FeatEntry(
                67,
                "Immolation of Heart",
                List.of(Component.literal("Immolation of Heart").withStyle(ChatFormatting.GOLD), Component.literal("30% of healing received when not full health is applied to allies within 4 blocks. Affected allies gain 8 Armor for 3s. Armor buff duration is amplified. Does not apply to self")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.ENTWINED_OFFERING);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.ENTWINED_OFFERING);
                    });
                },
                new ItemStack(ModItems.IMMOLATION_OF_HEART.get())
        ));

        FeatUpgradeHandlerRegistry.register("HERALD", 3, 2, new FeatEntry(
                68,
                "Divine Integration",
                List.of(Component.literal("Divine Integration").withStyle(ChatFormatting.GOLD), Component.literal("Soul Severance now heals allies for 1 health every tick but no longer deals damage or pulls monsters. Cooldown increases to 30s. Cooldown speed is increased by Amplification.")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setHealSeverance(true);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setHealSeverance(false);
                    });
                },
                new ItemStack(ModItems.SOUL_MAGNET.get())
        ));

        FeatUpgradeHandlerRegistry.register("HERALD", 3, 3, new FeatEntry(
                69,
                "Purify",
                List.of(Component.literal("Purify").withStyle(ChatFormatting.GOLD), Component.literal("Using blessings removes 1 debuff from allies within 5 blocks")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.PURIFY);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.PURIFY);
                    });
                },
                new ItemStack(Items.MILK_BUCKET)
        ));

        FeatUpgradeHandlerRegistry.register("HERALD", 4, 1, new FeatEntry(
                70,
                "Enhanced Confidence",
                List.of(Component.literal("Enhanced Confidence").withStyle(ChatFormatting.AQUA), Component.literal("Overconfidence grants an additional 20% melee damage to you and allies within 5 blocks for 4s. The duration is amplified")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setOverconfidenceHealSpecBuff(true);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setOverconfidenceHealSpecBuff(false);
                    });
                },
                new ItemStack(ModItems.PRIDE_INFUSED_AIGRETTE.get())
        ));

        FeatUpgradeHandlerRegistry.register("HERALD", 4, 2, new FeatEntry(
                71,
                "Opportunist",
                List.of(Component.literal("Opportunist").withStyle(ChatFormatting.AQUA), Component.literal("Every 10s, apply an effect for 10s to allies within 5 blocks depending on your health; over 80% Health: +7.5% Evasion, below 80% health: +10% damage")),
                player -> {
                    player.addEffect(new MobEffectInstance(ModEffects.GRACE_TIMER.get(), -1, 0, false, false, false));
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        player.removeEffect(ModEffects.GRACE_TIMER.get());
                    });
                },
                new ItemStack(Items.NETHER_STAR)
        ));

        FeatUpgradeHandlerRegistry.register("HERALD", 4, 3, new FeatEntry(
                72,
                "Soul Harvest",
                List.of(Component.literal("Soul Harvest").withStyle(ChatFormatting.AQUA), Component.literal("Kills grant 1 Restoration for 30 minutes, up to 30 stacks")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.SOUL_HARVEST);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.SOUL_HARVEST);
                    });
                },
                new ItemStack(Items.ZOMBIE_HEAD)
        ));

        FeatUpgradeHandlerRegistry.register("HERALD", 5, 1, new FeatEntry(
                73,
                "Focused Divinity",
                List.of(Component.literal("Focused Divinity").withStyle(ChatFormatting.LIGHT_PURPLE), Component.literal("Directed Hatred now heals allies in a 2x6 line for 2 health and increases their Precision and Accuracy by 10 for 4s. Cooldown increases by 6 seconds. Damage buff duration is amplified")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setDirectedHatredHealSpec(true);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.setDirectedHatredHealSpec(true);
                    });
                },
                new ItemStack(ModItems.HELL_ON_EARTH.get())
        ));

        FeatUpgradeHandlerRegistry.register("HERALD", 5, 2, new FeatEntry(
                74,
                "Souleater",
                List.of(Component.literal("Souleater").withStyle(ChatFormatting.LIGHT_PURPLE), Component.literal("Weapon attacks have a 15% chance and indirect melee hits have a 7.5% chance to do damage equal to 6% of the sum of your Restoration and Amplification stats")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.SOULEATER);
                        cache.unlock(BonusRegister.SOULEATER_INDIRECT);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.SOULEATER);
                        cache.remove(BonusRegister.SOULEATER_INDIRECT);
                    });
                },
                new ItemStack(Items.FIREWORK_ROCKET)
        ));

        FeatUpgradeHandlerRegistry.register("HERALD", 5, 3, new FeatEntry(
                75,
                "Light From Above",
                List.of(Component.literal("Light From Above").withStyle(ChatFormatting.LIGHT_PURPLE), Component.literal("Taking damage has a 25% chance to spawn Blessed Land")),
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.unlock(BonusRegister.LAND_SPAWN);
                    });
                },
                player -> {
                    player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).ifPresent(cache -> {
                        cache.remove(BonusRegister.LAND_SPAWN);
                    });
                },
                new ItemStack(ModItems.DIVINITY_EXTRACTION.get())
        ));

        FeatUpgradeHandlerRegistry.register("HERALD", 6, 2, new FeatEntry(
                76,
                "Restoring Aura",
                List.of(Component.literal("Restoring Aura").withStyle(ChatFormatting.RED), Component.literal("Every 20 seconds, heal allies within 5 blocks for 2 health. Interval speeds up with Amplification stat")),
                player -> {
                    if (!player.hasEffect(ModEffects.RADIATING_WARMTH.get())) {
                        int interval = (int) (400 / (1.0 + (AttributeApplier.getScaledValue(player, ModAttributes.AMPLIFICATION.get()) / 100.0)));
                        player.addEffect(new MobEffectInstance(ModEffects.RADIATING_WARMTH.get(), interval, 0, false, false, true));
                    }
                },
                player -> {
                    player.removeEffect(ModEffects.RADIATING_WARMTH.get());
                },
                new ItemStack(ModItems.RESTORING_AURA.get())
        ));
    }


    public static void remove(Player player, int featId) {
        if (ID_TO_ENTRY.containsKey(featId)) {
            ID_TO_ENTRY.get(featId).onRemove().accept(player);
        }
    }

    public static List<Component> getTooltip(int featId) {
        return ID_TO_ENTRY.containsKey(featId) ? ID_TO_ENTRY.get(featId).tooltip() : List.of(Component.literal("Unknown Feat"));
    }

    public static int getFeatId(String tree, int tier, int slot) {
        FeatEntry entry = FEAT_REGISTRY.get(tree + "_" + tier + "_" + slot);
        return entry != null ? entry.id() : -1;
    }

//    public static int getFeatId(ServerPlayer player, int featId) {
//        FeatEntry entry = ID_TO_ENTRY.get(featId);
//        return entry != null ? entry.id() : -1;
//    }

    public static Object getIcon(int featId) {
        if (ID_TO_ENTRY.containsKey(featId)) {
            return ID_TO_ENTRY.get(featId).icon();
        }
        return ItemStack.EMPTY;
    }

    public static boolean isActive(Player player, String treeKey, int featId) {
        return player.getPersistentData()
                .getCompound("ActiveFeats_" + treeKey)
                .getBoolean("feat_" + featId);
    }

    public static int getTierOfFeat(int featId) {
        return ID_TO_TIER.getOrDefault(featId, 1);
    }

    public static void tryUpgrade(ServerPlayer player, String treeKey, int featId) {
        if (isActive(player, treeKey, featId)) return;

        int totalPoints = getTotalPointsSpent(player);
        if (totalPoints >= 9) {
            return;
        }

        int nextPointIndex = totalPoints + 1;
        List<FeatCostRegistry.Cost> requiredCosts = FeatCostRegistry.getCostForPoint(nextPointIndex);

        if (!hasAllItems(player, requiredCosts)) {
            return;
        }

        consumeAllItems(player, requiredCosts);
        setFeatState(player, treeKey, featId, true);


        reapplyFeat(player, featId);

        ModMessages.sendToPlayer(new FeatSyncPacket(player.getPersistentData()), player);

        player.containerMenu.broadcastChanges();
        player.inventoryMenu.slotsChanged(player.getInventory());

        refreshPerPointStats(player);
        refreshMilestones(player);
        recalculateDynamicBonuses(player);
        // applyCrossbowTag(player);
        recalcAS(player);
    }

    public static void tryDowngrade(ServerPlayer player, String treeKey, int featId) {
        if (!isActive(player, treeKey, featId)) return;

        int currentTier = getTierOfFeat(featId);
        for (int higherTier = currentTier + 1; higherTier <= 6; higherTier++) {
            if (isAnyFeatActiveInTier(player, treeKey, higherTier)) {
                if (getActiveCountInTier(player, treeKey, currentTier) <= 1) {
                    return;
                }
            }
        }

        int totalGlobalPoints = getTotalPointsSpent(player);
        List<FeatCostRegistry.Cost> refundItems = FeatCostRegistry.getCostForPoint(totalGlobalPoints);

        for (FeatCostRegistry.Cost cost : refundItems) {
            ItemStack stackToReturn = new ItemStack(cost.item(), cost.count());
            if (!player.getInventory().add(stackToReturn)) {
                player.drop(stackToReturn, false);
            }
        }

        setFeatState(player, treeKey, featId, false);

        var entry = ID_TO_ENTRY.get(featId);
        if (entry != null && entry.onRemove() != null) {
            entry.onRemove().accept(player);
        }

        ModMessages.sendToPlayer(new FeatSyncPacket(player.getPersistentData()), player);

        player.containerMenu.broadcastChanges();
        player.inventoryMenu.slotsChanged(player.getInventory());

        refreshPerPointStats(player);
        refreshMilestones(player);
        recalculateDynamicBonuses(player);
        // applyCrossbowTag(player);
        recalcAS(player);
    }

    private static void setFeatState(Player player, String treeKey, int featId, boolean active) {
        CompoundTag persistent = player.getPersistentData();
        String tagName = "ActiveFeats_" + treeKey;
        if (!persistent.contains(tagName)) {
            persistent.put(tagName, new CompoundTag());
        }
        persistent.getCompound(tagName).putBoolean("feat_" + featId, active);
    }

    public static boolean isAnyFeatActiveInTier(Player player, String treeKey, int tier) {
        for (int slot = 1; slot <= 3; slot++) {
            int featIdInTier = getFeatId(treeKey, tier, slot);
            if (featIdInTier != -1 && isActive(player, treeKey, featIdInTier)) {
                return true;
            }
        }
        return false;
    }

    public static int getActiveCountInTier(Player player, String treeKey, int tier) {
        int count = 0;
        for (int slot = 1; slot <= 3; slot++) {
            int id = getFeatId(treeKey, tier, slot);
            if (id != -1 && isActive(player, treeKey, id)) {
                count++;
            }
        }
        return count;
    }

    public static void reapplyFeat(ServerPlayer player, int featId) {
        var entry = ID_TO_ENTRY.get(featId);

        if (entry != null && entry.onApply() != null) {
            entry.onApply().accept(player);
        }
    }

    private static boolean hasAllItems(Player player, List<FeatCostRegistry.Cost> costs) {
        for (FeatCostRegistry.Cost cost : costs) {
            int found = 0;
            for (ItemStack stack : player.getInventory().items) {
                if (stack.is(cost.item())) found += stack.getCount();
            }
            if (found < cost.count()) return false;
        }
        return true;
    }

    private static void consumeAllItems(Player player, List<FeatCostRegistry.Cost> costs) {
        for (FeatCostRegistry.Cost cost : costs) {
            int toRemove = cost.count();
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                ItemStack stack = player.getInventory().getItem(i);
                if (stack.is(cost.item())) {
                    int take = Math.min(stack.getCount(), toRemove);
                    stack.shrink(take);
                    toRemove -= take;
                    if (toRemove <= 0) break;
                }
            }
        }
    }

    public static int getTotalPointsSpent(Player player) {
        int total = 0;
        String[] trees = {"LION", "NIGHT", "STEEL", "HERALD"};
        for (String tree : trees) {
            total += getPointsSpentInTree(player, tree);
        }
        return total;
    }

    public static int getPointsSpentInTree(Player player, String treeKey) {
        int count = 0;
        for (int t = 1; t <= 6; t++) {
            count += getActiveCountInTier(player, treeKey, t);
        }
        return count;
    }

    public static boolean canUnlock(Player player, String treeKey, int tier) {
        if (tier <= 1) return true;
        CompoundTag data = player.getPersistentData().getCompound("ActiveFeats_" + treeKey);
        for (int slot = 1; slot <= 3; slot++) {
            int featId = FeatUpgradeHandlerRegistry.getFeatId(treeKey, tier - 1, slot);
            if (featId != -1 && data.getBoolean("feat_" + featId)) {
                return true;
            }
        }
        return false;
    }
}