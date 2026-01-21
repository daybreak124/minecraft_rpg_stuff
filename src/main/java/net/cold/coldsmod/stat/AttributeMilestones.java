package net.cold.coldsmod.stat;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.registries.RegistryObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class AttributeMilestones {
    @FunctionalInterface
    public interface MilestoneAction {
        void apply(Player player, String id, boolean adding);
    }

    protected static final Map<RegistryObject<Attribute>, TreeMap<Integer, MilestoneAction>> MILESTONES = new HashMap<>();

    static {
        // --- STR ---
        register(ModAttributes.STR, 30, (p, id, add) -> {
            applyBonus(p, id + "_gen", ModAttributes.POTENCY.get(), 7.5, add);
            applyBonus(p, id + "_mel", ModAttributes.MELEE_POTENCY.get(), 7.5, add);
            applyBonus(p, id + "_prj", ModAttributes.PROJECTILE_POTENCY.get(), 7.5, add);
        });
        register(ModAttributes.STR, 40, (p, id, add) -> applyBonus(p, id, Attributes.ARMOR, 5.0, add));
        register(ModAttributes.STR, 50, (p, id, add) -> applyBonus(p, id, ModAttributes.HASTE.get(), 8.0, add));
        register(ModAttributes.STR, 60, (p, id, add) -> {
            applyBonus(p, id + "_gen", ModAttributes.PRECISION_MULTIPLIER.get(), 0.15, add);
            applyBonus(p, id + "_mel", ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), 0.15, add);
            applyBonus(p, id + "_prj", ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get(), 0.15, add);
        });
        register(ModAttributes.STR, 70, (p, id, add) -> {
            applyBonus(p, id + "_gen", ModAttributes.POTENCY_MULTIPLIER.get(), 0.25, add);
            applyBonus(p, id + "_mel", ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), 0.25, add);
            applyBonus(p, id + "_prj", ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), 0.25, add);
        });
        register(ModAttributes.STR, 80, (p, id, add) -> applyBonus(p, id, Attributes.ATTACK_DAMAGE, 2.0, add));


        // --- FORT ---
        register(ModAttributes.FORT, 30, (p, id, add) -> applyBonus(p, id, Attributes.ARMOR, 4.0, add));
        register(ModAttributes.FORT, 40, (p, id, add) -> applyBonus(p, id, ModAttributes.ARMOR_MULTIPLIER.get(), 0.08, add));
        register(ModAttributes.FORT, 50, (p, id, add) -> applyBonus(p, id, ModAttributes.REJUVENATION.get(), 7.5, add));
        register(ModAttributes.FORT, 60, (p, id, add) -> applyBonus(p, id, ModAttributes.TOUGHNESS_MULTIPLIER.get(), 0.10, add));
        register(ModAttributes.FORT, 70, (p, id, add) -> {
            applyBonus(p, id + "_dr", ModAttributes.DEBUFF_RESIST.get(), 15.0, add);
            applyBonus(p, id + "_kb", Attributes.KNOCKBACK_RESISTANCE, 0.10, add);
        });
        register(ModAttributes.FORT, 80, (p, id, add) -> {
            applyBonus(p, id + "_tough", Attributes.ARMOR_TOUGHNESS, 9.0, add);
            applyBonus(p, id + "_armor", Attributes.ARMOR, 9.0, add);
        });

        // --- DEX ---
        register(ModAttributes.DEX, 30, (p, id, add) -> {
            applyBonus(p, id + "_gen", ModAttributes.ACCURACY.get(), 6.0, add);
            applyBonus(p, id + "_mel", ModAttributes.MELEE_ACCURACY.get(), 6.0, add);
            applyBonus(p, id + "_prj", ModAttributes.PROJECTILE_ACCURACY.get(), 6.0, add);
        });
        register(ModAttributes.DEX, 40, (p, id, add) -> {
            applyBonus(p, id + "_gen", ModAttributes.PRECISION.get(), 6.0, add);
            applyBonus(p, id + "_mel", ModAttributes.MELEE_PRECISION.get(), 6.0, add);
            applyBonus(p, id + "_prj", ModAttributes.PROJECTILE_PRECISION.get(), 6.0, add);
        });
        register(ModAttributes.DEX, 50, (p, id, add) -> applyBonus(p, id, ModAttributes.NOCK_HASTE.get(), 8.0, add));
        register(ModAttributes.DEX, 60, (p, id, add) -> applyBonus(p, id, Attributes.MOVEMENT_SPEED, 0.008, add));
        register(ModAttributes.DEX, 70, (p, id, add) -> {
            applyBonus(p, id + "_hs", ModAttributes.HASTE_MULTIPLIER.get(), 0.08, add);
            applyBonus(p, id + "_pm", ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), 0.15, add);
        });
        register(ModAttributes.DEX, 80, (p, id, add) -> {
            applyBonus(p, id + "_dmg_gen", ModAttributes.POTENCY.get(), 9.0, add);
            applyBonus(p, id + "_dmg_mel", ModAttributes.MELEE_POTENCY.get(), 9.0, add);
            applyBonus(p, id + "_dmg_prj", ModAttributes.PROJECTILE_POTENCY.get(), 9.0, add);
            applyBonus(p, id + "_nock", ModAttributes.NOCK_HASTE.get(), 9.0, add);
            applyBonus(p, id + "_crit_gen", ModAttributes.ACCURACY.get(), 9.0, add);
            applyBonus(p, id + "_crit_mel", ModAttributes.MELEE_ACCURACY.get(), 9.0, add);
            applyBonus(p, id + "_crit_prj", ModAttributes.PROJECTILE_ACCURACY.get(), 9.0, add);
            applyBonus(p, id + "_prec_gen", ModAttributes.PRECISION.get(), 9.0, add);
            applyBonus(p, id + "_prec_mel", ModAttributes.MELEE_PRECISION.get(), 9.0, add);
            applyBonus(p, id + "_prec_prj", ModAttributes.PROJECTILE_PRECISION.get(), 9.0, add);
        });

        // --- CON ---
        register(ModAttributes.CON, 30, (p, id, add) -> applyBonus(p, id, Attributes.ARMOR, 5.0, add));
        register(ModAttributes.CON, 40, (p, id, add) -> {
            applyBonus(p, id + "_gen", ModAttributes.POTENCY.get(), 5.0, add);
            applyBonus(p, id + "_mel", ModAttributes.MELEE_POTENCY.get(), 5.0, add);
            applyBonus(p, id + "_prj", ModAttributes.PROJECTILE_POTENCY.get(), 5.0, add);
        });
        register(ModAttributes.CON, 50, (p, id, add) -> {
            applyBonus(p, id + "_dr", ModAttributes.DEBUFF_RESIST.get(), 8.0, add);
            applyBonus(p, id + "_rej", ModAttributes.REJUVENATION.get(), 8.0, add);
            applyBonus(p, id + "_tough", Attributes.ARMOR_TOUGHNESS, 6.0, add);
        });
        register(ModAttributes.CON, 60, (p, id, add) -> applyBonus(p, id, ModAttributes.REJUVENATION_MULTIPLIER.get(), 0.125, add));
        register(ModAttributes.CON, 70, (p, id, add) -> applyBonus(p, id, ModAttributes.ARMOR_MULTIPLIER.get(), 0.125, add));
        register(ModAttributes.CON, 80, (p, id, add) -> {
            applyBonus(p, id + "_gen", ModAttributes.POTENCY.get(), 10.0, add);
            applyBonus(p, id + "_mel", ModAttributes.MELEE_POTENCY.get(), 10.0, add);
            applyBonus(p, id + "_prj", ModAttributes.PROJECTILE_POTENCY.get(), 10.0, add);
        });

        // --- PERC ---
        register(ModAttributes.PERC, 30, (p, id, add) -> applyBonus(p, id, Attributes.ARMOR_TOUGHNESS, 3.0, add));
        register(ModAttributes.PERC, 40, (p, id, add) -> {
            applyBonus(p, id + "_gen", ModAttributes.POTENCY_MULTIPLIER.get(), 0.075, add);
            applyBonus(p, id + "_mel", ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), 0.075, add);
            applyBonus(p, id + "_prj", ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), 0.075, add);
        });
        register(ModAttributes.PERC, 50, (p, id, add) -> {
            applyBonus(p, id + "_gen", ModAttributes.PRECISION.get(), 10.0, add);
            applyBonus(p, id + "_mel", ModAttributes.MELEE_PRECISION.get(), 10.0, add);
            applyBonus(p, id + "_prj", ModAttributes.PROJECTILE_PRECISION.get(), 10.0, add);
        });
        register(ModAttributes.PERC, 60, (p, id, add) -> applyBonus(p, id, ForgeMod.ENTITY_REACH.get(), 1.0, add));
        register(ModAttributes.PERC, 70, (p, id, add) -> applyBonus(p, id, ModAttributes.ARMOR_MULTIPLIER.get(), 0.08, add));
        register(ModAttributes.PERC, 80, (p, id, add) -> {
            applyBonus(p, id + "_gen", ModAttributes.PRECISION_MULTIPLIER.get(), 0.25, add);
            applyBonus(p, id + "_mel", ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), 0.25, add);
            applyBonus(p, id + "_prj", ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get(), 0.25, add);
        });

        // --- INS ---
        register(ModAttributes.INSIGHT, 10, (p, id, add) -> applyBonus(p, id, ModAttributes.MINING_SPEED.get(), 10.0, add));
        register(ModAttributes.INSIGHT, 20, (p, id, add) -> applyBonus(p, id, ModAttributes.XP_GAIN.get(), 10.0, add));
        register(ModAttributes.INSIGHT, 30, (p, id, add) -> applyBonus(p, id, ForgeMod.BLOCK_REACH.get(), 0.5, add));
        register(ModAttributes.INSIGHT, 40, (p, id, add) -> {
            applyBonus(p, id + "_mine", ModAttributes.MINING_SPEED.get(), 25.0, add);
            applyBonus(p, id + "_xp", ModAttributes.XP_GAIN.get(), 25.0, add);
        });

        // --- WIS ---
        register(ModAttributes.WISDOM, 10, (p, id, add) -> applyBonus(p, id, ModAttributes.RESTORATION.get(), 8.0, add));
        register(ModAttributes.WISDOM, 20, (p, id, add) -> applyBonus(p, id, ModAttributes.AMPLIFICATION.get(), 10.0, add));
        register(ModAttributes.WISDOM, 30, (p, id, add) -> applyBonus(p, id, ModAttributes.DEBUFF_RESIST.get(), 10.0, add));
        register(ModAttributes.WISDOM, 40, (p, id, add) -> applyBonus(p, id, ModAttributes.RESTORATION_MULTIPLIER.get(), 0.1, add));
        register(ModAttributes.WISDOM, 50, (p, id, add) -> {
            applyBonus(p, id + "_armor", Attributes.ARMOR, 7.0, add);
            applyBonus(p, id + "_amp", ModAttributes.AMPLIFICATION.get(), 10.0, add);
        });
        register(ModAttributes.WISDOM, 60, (p, id, add) -> {
            applyBonus(p, id + "_rest", ModAttributes.RESTORATION.get(), 7.0, add);
            applyBonus(p, id + "_ampm", ModAttributes.AMPLIFICATION_MULTIPLIER.get(), 0.1, add);
        });
    }

    private static void register(RegistryObject<Attribute> attr, int threshold, MilestoneAction action) {
        MILESTONES.computeIfAbsent(attr, k -> new TreeMap<>()).put(threshold, action);
    }

    private static void applyBonus(Player player, String id, Attribute target, double value, boolean adding) {
        AttributeInstance inst = player.getAttribute(target);
        if (inst == null) return;

        UUID uuid = UUID.nameUUIDFromBytes(id.getBytes(StandardCharsets.UTF_8));
        inst.removeModifier(uuid);
        if (adding) {
            inst.addTransientModifier(new AttributeModifier(uuid, "Milestone Bonus", value, AttributeModifier.Operation.ADDITION));
        }
    }
}
