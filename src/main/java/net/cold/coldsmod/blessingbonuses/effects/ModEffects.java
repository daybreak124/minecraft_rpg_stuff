package net.cold.coldsmod.blessingbonuses.effects;

import net.cold.coldsmod.blessingbonuses.neweffects.*;
import net.cold.coldsmod.blessingbonuses.skills.BastionActive;
import net.cold.coldsmod.blessingbonuses.skills.BronzewoodCooldown;
import net.cold.coldsmod.blessingbonuses.skills.RetaliateActive;
import net.cold.coldsmod.stat.ItemModifier;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {

    public static void register() {
        MinecraftForge.EVENT_BUS.register(new ItemModifier());
    }

    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, "coldsmod");

    public static final RegistryObject<MobEffect> HAWKEYE =
            EFFECTS.register("hawkeye_stack", Hawkeye::new);

    public static final RegistryObject<MobEffect> FRENZY =
            EFFECTS.register("frenzy_stack", Frenzy::new);

    public static final RegistryObject<MobEffect> DEATH_FROM_ABOVE =
            EFFECTS.register("dfa_up", DeathFromAbove::new);

    public static final RegistryObject<MobEffect> DEATH_FROM_ABOVE_COOLDOWN =
            EFFECTS.register("dfa_cd", DeathFromAboveCooldown::new);

    public static final RegistryObject<MobEffect> INTO_THE_FRAY =
            EFFECTS.register("itf_stack", IntoTheFray::new);

    public static final RegistryObject<MobEffect> INTO_THE_FRAY_COOLDOWN =
            EFFECTS.register("itf_cd", IntoTheFrayCooldown::new);

    public static final RegistryObject<MobEffect> INTIMIDATING_PRESENCE =
            EFFECTS.register("intimidating_presence_up", IntimidatingPresence::new);

    public static final RegistryObject<MobEffect> INTIMIDATING_PRESENCE_COOLDOWN =
            EFFECTS.register("intimidating_presence_cd", IntimidatingPresenceCooldown::new);

    public static final RegistryObject<MobEffect> INTIMIDATED =
            EFFECTS.register("intimidated", Intimidated::new);

    public static final RegistryObject<MobEffect> DARING_SHOUT =
            EFFECTS.register("daring_shout_up", DaringShout::new);

    public static final RegistryObject<MobEffect> DARING_SHOUT_COOLDOWN =
            EFFECTS.register("daring_shout_cd", DaringShoutCooldown::new);

    public static final RegistryObject<MobEffect> RECKONING =
            EFFECTS.register("reckoning_up", Reckoning::new);

    public static final RegistryObject<MobEffect> RECKONING_COOLDOWN =
            EFFECTS.register("reckoning_cd", ReckoningCooldown::new);

    public static final RegistryObject<MobEffect> RECKONING_ACTIVE =
            EFFECTS.register("reckoning_active", ReckoningActive::new);

    public static final RegistryObject<MobEffect> BERSERK =
            EFFECTS.register("berserk_stack", Berserk::new);

    public static final RegistryObject<MobEffect> BERSERK_READY =
            EFFECTS.register("berserk_up", BerserkReady::new);

    public static final RegistryObject<MobEffect> BERSERK_TIMER =
            EFFECTS.register("berserk_timer", BerserkTimer::new);

    public static final RegistryObject<MobEffect> BRONZEWOOD_READY =
            EFFECTS.register("bronzewood_up", BronzewoodReady::new);

    public static final RegistryObject<MobEffect> BRONZEWOOD_COOLDOWN =
            EFFECTS.register("bronzewood_cooldown", BronzewoodCooldown::new);

    public static final RegistryObject<MobEffect> BRONZEWOOD_CURSE =
            EFFECTS.register("bronzewoods_curse", BronzewoodCurse::new);

    public static final RegistryObject<MobEffect> CLAIRVOYANCE_READY =
            EFFECTS.register("clairvoyance_up", ClairvoyanceReady::new);

    public static final RegistryObject<MobEffect> CLAIRVOYANCE_COOLDOWN =
            EFFECTS.register("clairvoyance_cd", ClairvoyanceCooldown::new);

    public static final RegistryObject<MobEffect> EXPLOIT_WEAKNESS_READY =
            EFFECTS.register("exploit_weakness_up", ExploitWeaknessReady::new);

    public static final RegistryObject<MobEffect> EXPLOIT_WEAKNESS_COOLDOWN =
            EFFECTS.register("exploit_weakness_cd", ExploitWeaknessCooldown::new);

    public static final RegistryObject<MobEffect> EXPLOIT_WEAKNESS_DEBUFF =
            EFFECTS.register("exploit_weakness_debuff", ExploitWeaknessDebuff::new);

    public static final RegistryObject<MobEffect> RETALIATE_READY =
            EFFECTS.register("retaliate_up", RetaliateReady::new);

    public static final RegistryObject<MobEffect> RETALIATE_COOLDOWN =
            EFFECTS.register("retaliate_cd", RetaliateCooldown::new);

    public static final RegistryObject<MobEffect> RETALIATE_ACTIVE =
            EFFECTS.register("retaliate_active", RetaliateActive::new);

    public static final RegistryObject<MobEffect> BASTION_ACTIVE =
            EFFECTS.register("bastion_active", BastionActive::new);

    public static final RegistryObject<MobEffect> BASTION_COOLDOWN =
            EFFECTS.register("bastion_cd", BastionCooldown::new);

    public static final RegistryObject<MobEffect> BASTION_READY =
            EFFECTS.register("bastion_up", BastionReady::new);

    public static final RegistryObject<MobEffect> ADRENALINE_INJECTION_COOLDOWN =
            EFFECTS.register("adrenaline_injection_cd", AdrenalineInjectionCooldown::new);

    public static final RegistryObject<MobEffect> ADRENALINE_INJECTION_UP =
            EFFECTS.register("adrenaline_injection_up", AdrenalineInjectionUp::new);

    public static final RegistryObject<MobEffect> DECEPTION_READY =
            EFFECTS.register("deceptive_heart_ready", DeceptionReady::new);

    public static final RegistryObject<MobEffect> DECEPTION_COOLDOWN =
            EFFECTS.register("deceptive_heart_cd", DeceptionCooldown::new);

    public static final RegistryObject<MobEffect> DIRECTED_HATRED_READY =
            EFFECTS.register("directed_hatred_up", DirectedHatredReady::new);

    public static final RegistryObject<MobEffect> DIRECTED_HATRED_COOLDOWN =
            EFFECTS.register("directed_hatred_cd", DirectedHatredCooldown::new);

    public static final RegistryObject<MobEffect> BLINDED_BY_HATRED =
            EFFECTS.register("blinded_by_hatred", DirectedHatredBlinded::new);

    public static final RegistryObject<MobEffect> SOUL_SEVERANCE_READY =
            EFFECTS.register("soul_severance_ready", SoulSeveranceReady::new);

    public static final RegistryObject<MobEffect> SOUL_SEVERANCE_COOLDOWN =
            EFFECTS.register("soul_severance_cd", SoulSeveranceCooldown::new);

    public static final RegistryObject<MobEffect> SOUL_SEVERANCE_ACTIVE =
            EFFECTS.register("soul_severance_up", SoulSeveranceActive::new);

    public static final RegistryObject<MobEffect> QUANTUM_LEAP_READY =
            EFFECTS.register("quantum_leap_ready", QuantumLeapReady::new);

    public static final RegistryObject<MobEffect> QUANTUM_LEAP_COOLDOWN =
            EFFECTS.register("quantum_leap_cd", QuantumLeapCooldown::new);

    public static final RegistryObject<MobEffect> QUANTUM_LEAP_ACTIVE =
            EFFECTS.register("quantum_leap_up", QuantumLeapActive::new);

    public static final RegistryObject<MobEffect> ENHANCED_QUANTUM_LEAP =
            EFFECTS.register("enhanced_quantum_leap", EnhancedQuantumLeap::new);

    public static final RegistryObject<MobEffect> LIFE_TOUCH_READY =
            EFFECTS.register("spirit_forest_ready", VitalizationReady::new);

    public static final RegistryObject<MobEffect> LIFE_TOUCH_COOLDOWN =
            EFFECTS.register("spirit_forest_cd", VitalizationCooldown::new);

    public static final RegistryObject<MobEffect> EXPLOSIVE_TENDENCY_STACK =
            EFFECTS.register("explosive_tendencies_stack", ExplosiveTendencyStack::new);

    public static final RegistryObject<MobEffect> EXPLOSIVE_TENDENCY_TIMER =
            EFFECTS.register("explosive_tendencies_timer", ExplosiveTendencyTimer::new);

    public static final RegistryObject<MobEffect> SOLARA =
            EFFECTS.register("solara", Solara::new);

    public static final RegistryObject<MobEffect> OVERCONFIDENCE_READY =
            EFFECTS.register("overconfidence_ready", OverconfidenceReady::new);

    public static final RegistryObject<MobEffect> OVERCONFIDENCE_COOLDOWN =
            EFFECTS.register("overconfidence_cd", OverconfidenceCD::new);

    public static final RegistryObject<MobEffect> OVERCONFIDENCE_ACTIVE =
            EFFECTS.register("overconfidence_active", OverconfidenceActive::new);

    public static final RegistryObject<MobEffect> ENTWINED_OFFERING_ACTIVE =
            EFFECTS.register("entwined_offering_active", () -> new EntwinedOfferingActive()
                    .addAttributeModifier(Attributes.ARMOR, "f3e2b6c0-1934-5678-9abc-000600770084", 8.0, AttributeModifier.Operation.ADDITION));

    public static final RegistryObject<MobEffect> NIMBLE_GETAWAY_COOLDOWN =
            EFFECTS.register("nimble_getaway_cd", NimbleGetawayCD::new);

    public static final RegistryObject<MobEffect> NIMBLE_GETAWAY_ACTIVE =
            EFFECTS.register("nimble_getaway_active", NimbleGetawayActive::new);

    public static final RegistryObject<MobEffect> BLACKENED_HEART =
            EFFECTS.register("blackened_heart", () -> new BlackenedHeart()
                    .addAttributeModifier(ModAttributes.REJUVENATION.get(), "f3e2b5c0-1138-5123-a223-024233360446", -2000.0, AttributeModifier.Operation.ADDITION));

    public static final RegistryObject<MobEffect> COMBATANTS_AID_CD =
            EFFECTS.register("combatants_aid_cd", CombatantsAidCD::new);

    public static final RegistryObject<MobEffect> COMBATANTS_AID_READY =
            EFFECTS.register("combatants_aid_ready", CombatantsAidReady::new);

    public static final RegistryObject<MobEffect> SANCTUARY_FATIGUE =
            EFFECTS.register("sanctuary_fatigue", SanctuaryFatigue::new);

    public static final RegistryObject<MobEffect> SANCTUARY_SHARED =
            EFFECTS.register("sanctuary_shared", SanctuaryShared::new);

    public static final RegistryObject<MobEffect> VORTEX_CD =
            EFFECTS.register("vortex_cd", VortexCD::new);

    public static final RegistryObject<MobEffect> VORTEX_READY =
            EFFECTS.register("vortex_ready", VortexReady::new);

    public static final RegistryObject<MobEffect> RADIATING_WARMTH =
            EFFECTS.register("radiating_warmth", RadiatingWarmthTimer::new);

    public static final RegistryObject<MobEffect> SBEVE_CD =
            EFFECTS.register("sbeve_cd", SbeveCD::new);

    public static final RegistryObject<MobEffect> FOCUSED_ENERGY_READY =
            EFFECTS.register("focused_energy_ready", FocusedEnergyReady::new);

    public static final RegistryObject<MobEffect> FOCUSED_ENERGY_CD =
            EFFECTS.register("focused_energy_cd", FocusedEnergyCD::new);

    public static final RegistryObject<MobEffect> THORNED_PARRY_READY =
            EFFECTS.register("thorned_parry_ready", ThornedParryReady::new);

    public static final RegistryObject<MobEffect> THORNED_PARRY_CD =
            EFFECTS.register("thorned_parry_cd", ThornedParryCD::new);

    public static final RegistryObject<MobEffect> BLESSED_LAND_READY =
            EFFECTS.register("blessed_land_ready", BlessedLandReady::new);

    public static final RegistryObject<MobEffect> BLESSED_LAND_CD =
            EFFECTS.register("blessed_land_cd", BlessedLandCD::new);
}