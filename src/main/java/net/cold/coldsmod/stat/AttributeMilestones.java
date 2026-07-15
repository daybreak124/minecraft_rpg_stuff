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
            applyBonus(p, id + "_gen", ModAttributes.POTENCY.get(), 2.0, add);
            applyBonus(p, id + "_mel", ModAttributes.MELEE_POTENCY.get(), 2.0, add);
            applyBonus(p, id + "_prj", ModAttributes.PROJECTILE_POTENCY.get(), 2.0, add);
        });
        register(ModAttributes.STR, 40, (p, id, add) -> applyBonus(p, id, Attributes.ARMOR, 1.0, add));
        register(ModAttributes.STR, 50, (p, id, add) -> applyBonus(p, id, ModAttributes.MELEE_HASTE.get(), 2.5, add));
        register(ModAttributes.STR, 60, (p, id, add) -> {
            applyBonus(p, id + "_gen_prec", ModAttributes.PRECISION.get(), 6, add);
            applyBonus(p, id + "_mel_prec", ModAttributes.MELEE_PRECISION.get(), 6, add);
            applyBonus(p, id + "_prj_prec", ModAttributes.PROJECTILE_PRECISION.get(), 6, add);
            applyBonus(p, id + "_mel_acc", ModAttributes.MELEE_ACCURACY.get(), 6, add);
        });
        register(ModAttributes.STR, 70, (p, id, add) -> {
            applyBonus(p, id + "_gen_pm", ModAttributes.POTENCY.get(), 0.08, AttributeModifier.Operation.MULTIPLY_BASE, add);
            applyBonus(p, id + "_mel_pm", ModAttributes.MELEE_POTENCY.get(), 0.08, AttributeModifier.Operation.MULTIPLY_BASE, add);
            applyBonus(p, id + "_prj_pm", ModAttributes.PROJECTILE_POTENCY.get(), 0.08, AttributeModifier.Operation.MULTIPLY_BASE, add);
            applyBonus(p, id + "_arm", Attributes.ARMOR, 1.25, add);
        });
        register(ModAttributes.STR, 80, (p, id, add) -> applyBonus(p, id, Attributes.ATTACK_DAMAGE, 1.0, add));

        // --- FORT ---
        register(ModAttributes.FORT, 30, (p, id, add) -> applyBonus(p, id, Attributes.ARMOR, 1.25, add));
        register(ModAttributes.FORT, 40, (p, id, add) -> applyBonus(p, id, Attributes.MAX_HEALTH, 0.6, add));
        register(ModAttributes.FORT, 50, (p, id, add) -> applyBonus(p, id, Attributes.ARMOR, 0.06, AttributeModifier.Operation.MULTIPLY_BASE, add));
        register(ModAttributes.FORT, 60, (p, id, add) -> applyBonus(p, id, Attributes.ARMOR_TOUGHNESS, 2.5, add));
        register(ModAttributes.FORT, 70, (p, id, add) -> {
            applyBonus(p, id + "_hp", Attributes.MAX_HEALTH, 0.8, add);
            applyBonus(p, id + "_kb", Attributes.KNOCKBACK_RESISTANCE, 0.10, add);
        });
        register(ModAttributes.FORT, 80, (p, id, add) -> {
            applyBonus(p, id + "_tough", Attributes.ARMOR_TOUGHNESS, 1.5, add);
            applyBonus(p, id + "_armor", Attributes.ARMOR, 1.5, add);
        });

        // --- DEX ---
        register(ModAttributes.DEX, 30, (p, id, add) -> {
            applyBonus(p, id + "_gen", ModAttributes.ACCURACY.get(), 3.25, add);
            applyBonus(p, id + "_mel", ModAttributes.MELEE_ACCURACY.get(), 3.25, add);
            applyBonus(p, id + "_prj", ModAttributes.PROJECTILE_ACCURACY.get(), 3.25, add);
        });
        register(ModAttributes.DEX, 40, (p, id, add) -> {
            // UI: "+2.5 Precision"
            applyBonus(p, id + "_gen", ModAttributes.PRECISION.get(), 3.25, add);
            applyBonus(p, id + "_mel", ModAttributes.MELEE_PRECISION.get(), 3.25, add);
            applyBonus(p, id + "_prj", ModAttributes.PROJECTILE_PRECISION.get(), 3.25, add);
        });
        register(ModAttributes.DEX, 50, (p, id, add) -> {
            applyBonus(p, id + "_ms", Attributes.MOVEMENT_SPEED, 0.012, add);
            applyBonus(p, id + "_prec_gen", ModAttributes.PRECISION.get(), 3.125, add);
            applyBonus(p, id + "_prec_mel", ModAttributes.MELEE_PRECISION.get(), 3.125, add);
            applyBonus(p, id + "_prec_prj", ModAttributes.PROJECTILE_PRECISION.get(), 3.125, add);
        });
        register(ModAttributes.DEX, 60, (p, id, add) -> {
            applyBonus(p, id + "_nock", ModAttributes.NOCK_HASTE.get(), 3.25, add);
            applyBonus(p, id + "_acc_gen", ModAttributes.ACCURACY.get(), 3.25, add);
            applyBonus(p, id + "_acc_mel", ModAttributes.MELEE_ACCURACY.get(), 3.25, add);
            applyBonus(p, id + "_acc_prj", ModAttributes.PROJECTILE_ACCURACY.get(), 3.25, add);
        });
        register(ModAttributes.DEX, 70, (p, id, add) -> {
            applyBonus(p, id + "_mh", ModAttributes.MELEE_HASTE.get(), 0.04, AttributeModifier.Operation.MULTIPLY_BASE, add);
            applyBonus(p, id + "_pp", ModAttributes.PROJECTILE_POTENCY.get(), 0.075, AttributeModifier.Operation.MULTIPLY_BASE, add);
        });
        register(ModAttributes.DEX, 80, (p, id, add) -> {
            applyBonus(p, id + "_pot_gen", ModAttributes.POTENCY.get(), 4.0, add);
            applyBonus(p, id + "_pot_mel", ModAttributes.MELEE_POTENCY.get(), 4.0, add);
            applyBonus(p, id + "_pot_prj", ModAttributes.PROJECTILE_POTENCY.get(), 4.0, add);
            applyBonus(p, id + "_nock", ModAttributes.NOCK_HASTE.get(), 4.0, add);
        });

        // --- CON ---
        register(ModAttributes.CON, 30, (p, id, add) -> applyBonus(p, id, Attributes.MAX_HEALTH, 0.6, add));
        register(ModAttributes.CON, 40, (p, id, add) -> {
            applyBonus(p, id + "_gen", ModAttributes.POTENCY.get(), 2.0, add);
            applyBonus(p, id + "_mel", ModAttributes.MELEE_POTENCY.get(), 2.0, add);
            applyBonus(p, id + "_prj", ModAttributes.PROJECTILE_POTENCY.get(), 2.0, add);
        });
        register(ModAttributes.CON, 50, (p, id, add) -> {
            // UI: "+1 Toughness & +0.25 Health & +5 Rejuvenation"
            applyBonus(p, id + "_tough", Attributes.ARMOR_TOUGHNESS, 1.0, add);
            applyBonus(p, id + "_hp", Attributes.MAX_HEALTH, 0.25, add);
            applyBonus(p, id + "_rej", ModAttributes.REJUVENATION.get(), 5.0, add);
        });
        register(ModAttributes.CON, 60, (p, id, add) -> {
            applyBonus(p, id + "_rej", ModAttributes.REJUVENATION.get(), 4.0, add);
            applyBonus(p, id + "_hp", Attributes.MAX_HEALTH, 0.6, add);
        });
        register(ModAttributes.CON, 70, (p, id, add) -> applyBonus(p, id, Attributes.ARMOR, 0.08, AttributeModifier.Operation.MULTIPLY_BASE, add));
        register(ModAttributes.CON, 80, (p, id, add) -> {
            applyBonus(p, id + "_gen", ModAttributes.POTENCY.get(), 4.5, add);
            applyBonus(p, id + "_mel", ModAttributes.MELEE_POTENCY.get(), 4.5, add);
            applyBonus(p, id + "_prj", ModAttributes.PROJECTILE_POTENCY.get(), 4.5, add);
        });

        // --- PERC ---
        register(ModAttributes.PERC, 30, (p, id, add) -> applyBonus(p, id, Attributes.ARMOR, 1.0, add));
        register(ModAttributes.PERC, 40, (p, id, add) -> {
            applyBonus(p, id + "_gen", ModAttributes.POTENCY.get(), 0.075, AttributeModifier.Operation.MULTIPLY_BASE, add);
            applyBonus(p, id + "_mel", ModAttributes.MELEE_POTENCY.get(), 0.075, AttributeModifier.Operation.MULTIPLY_BASE, add);
            applyBonus(p, id + "_prj", ModAttributes.PROJECTILE_POTENCY.get(), 0.075, AttributeModifier.Operation.MULTIPLY_BASE, add);
        });
        register(ModAttributes.PERC, 50, (p, id, add) -> {
            applyBonus(p, id + "_gen", ModAttributes.PRECISION.get(), 5, add);
            applyBonus(p, id + "_mel", ModAttributes.MELEE_PRECISION.get(), 5, add);
            applyBonus(p, id + "_prj", ModAttributes.PROJECTILE_PRECISION.get(), 5, add);
        });
        register(ModAttributes.PERC, 60, (p, id, add) -> {
            applyBonus(p, id + "_reach", ForgeMod.ENTITY_REACH.get(), 1.0, add);
            applyBonus(p, id + "_gen_pm", ModAttributes.PRECISION.get(), 0.08, AttributeModifier.Operation.MULTIPLY_BASE, add);
            applyBonus(p, id + "_mel_pm", ModAttributes.MELEE_PRECISION.get(), 0.08, AttributeModifier.Operation.MULTIPLY_BASE, add);
            applyBonus(p, id + "_prj_pm", ModAttributes.PROJECTILE_PRECISION.get(), 0.08, AttributeModifier.Operation.MULTIPLY_BASE, add);
        });
        register(ModAttributes.PERC, 70, (p, id, add) ->
                applyBonus(p, id, Attributes.ARMOR, 0.07, AttributeModifier.Operation.MULTIPLY_BASE, add)
        );
        register(ModAttributes.PERC, 80, (p, id, add) -> {
            applyBonus(p, id + "_gen", ModAttributes.PRECISION.get(), 0.10, AttributeModifier.Operation.MULTIPLY_BASE, add);
            applyBonus(p, id + "_mel", ModAttributes.MELEE_PRECISION.get(), 0.10, AttributeModifier.Operation.MULTIPLY_BASE, add);
            applyBonus(p, id + "_prj", ModAttributes.PROJECTILE_PRECISION.get(), 0.10, AttributeModifier.Operation.MULTIPLY_BASE, add);
        });

        // --- WIS ---
        register(ModAttributes.WISDOM, 10, (p, id, add) -> applyBonus(p, id, ModAttributes.RESTORATION.get(), 3.0, add));
        register(ModAttributes.WISDOM, 20, (p, id, add) -> applyBonus(p, id, ModAttributes.AMPLIFICATION.get(), 4.5, add));
        register(ModAttributes.WISDOM, 30, (p, id, add) -> applyBonus(p, id, ModAttributes.DEBUFF_RESIST.get(), 10.0, add));
        register(ModAttributes.WISDOM, 40, (p, id, add) ->
                applyBonus(p, id, ModAttributes.RESTORATION.get(), 0.12, AttributeModifier.Operation.MULTIPLY_BASE, add)
        );
        register(ModAttributes.WISDOM, 50, (p, id, add) -> {
            applyBonus(p, id + "_armor", Attributes.ARMOR, 3.0, add);
            applyBonus(p, id + "_amp", ModAttributes.AMPLIFICATION.get(), 5.0, add);
        });
        register(ModAttributes.WISDOM, 60, (p, id, add) -> {
            applyBonus(p, id + "_rest", ModAttributes.RESTORATION.get(), 4.0, add);
            applyBonus(p, id + "_ampm", ModAttributes.AMPLIFICATION.get(), 0.12, AttributeModifier.Operation.MULTIPLY_BASE, add);
        });

        // --- INS ---
        register(ModAttributes.INSIGHT, 10, (p, id, add) -> applyBonus(p, id, ModAttributes.MINING_SPEED.get(), 0.1, add));
        register(ModAttributes.INSIGHT, 20, (p, id, add) -> applyBonus(p, id, ModAttributes.XP_GAIN.get(), 0.1, add));
        register(ModAttributes.INSIGHT, 30, (p, id, add) -> applyBonus(p, id, ForgeMod.BLOCK_REACH.get(), 0.5, add));
        register(ModAttributes.INSIGHT, 40, (p, id, add) -> {
            applyBonus(p, id + "_mine", ModAttributes.MINING_SPEED.get(), 0.2, add);
            applyBonus(p, id + "_xp", ModAttributes.XP_GAIN.get(), 0.2, add);
        });
    }

    private static void register(RegistryObject<Attribute> attr, int threshold, MilestoneAction action) {
        MILESTONES.computeIfAbsent(attr, k -> new TreeMap<>()).put(threshold, action);
    }

    // Keep this so your old 5-arg code doesn't break
    private static void applyBonus(Player player, String id, Attribute target, double value, boolean adding) {
        applyBonus(player, id, target, value, AttributeModifier.Operation.ADDITION, adding);
    }

    private static void applyBonus(Player player, String id, Attribute target, double value, AttributeModifier.Operation operation, boolean adding) {
        AttributeInstance inst = player.getAttribute(target);
        if (inst == null) return;

        UUID uuid = UUID.nameUUIDFromBytes(id.getBytes(StandardCharsets.UTF_8));
        inst.removeModifier(uuid);
        if (adding) {
            inst.addTransientModifier(new AttributeModifier(uuid, "Milestone Bonus", value, operation));
        }
    }
}
