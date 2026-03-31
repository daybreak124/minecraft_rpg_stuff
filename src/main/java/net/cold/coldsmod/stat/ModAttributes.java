package net.cold.coldsmod.stat;

import net.cold.coldsmod.ColdsMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = ColdsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModAttributes {

    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, "coldsmod");

    public static final RegistryObject<Attribute> STR = ATTRIBUTES.register("str",
            () -> new RangedAttribute("str", 0.0D, -2048.0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> FORT = ATTRIBUTES.register("fort",
            () -> new RangedAttribute("fort", 0.0D, -2048.0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> DEX = ATTRIBUTES.register("dex",
            () -> new RangedAttribute("dex", 0.0D, -2048.0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> INTELLIGENCE = ATTRIBUTES.register("intelligence",
            () -> new RangedAttribute("intelligence", 0.0D, -2048.0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> WISDOM = ATTRIBUTES.register("wisdom",
            () -> new RangedAttribute("wisdom", 0.0D, -2048.0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> CON = ATTRIBUTES.register("con",
            () -> new RangedAttribute("con", 0.0D, -2048.0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> PERC = ATTRIBUTES.register("perc",
            () -> new RangedAttribute("perc", 0.0D, -2048.0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> INSIGHT = ATTRIBUTES.register("insight",
            () -> new RangedAttribute("insight", 0.0D, -2048.0D, 2048.0D).setSyncable(true));

    public static final RegistryObject<Attribute> DEBUFF_RESIST = ATTRIBUTES.register("debuff_resist",
            () -> new RangedAttribute("debuff_resist", 0.0D, -2048.0D, 100.0D).setSyncable(true));

    public static final RegistryObject<Attribute> POTENCY = ATTRIBUTES.register("potency",
            () -> new RangedAttribute("potency", 0.0D, -2048.0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> ACCURACY = ATTRIBUTES.register("accuracy",
            () -> new RangedAttribute("accuracy", 0.0D, -2048.0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> PRECISION = ATTRIBUTES.register("precision",
            () -> new RangedAttribute("precision", 0.0D, -2048.0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> HASTE = ATTRIBUTES.register("haste",
            () -> new RangedAttribute("haste", 0.0D, -2048.0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> HASTE_MULTIPLIER = ATTRIBUTES.register("haste_multiplier",
            () -> new RangedAttribute("haste_multiplier", 0.0D, -2048.0D, 2048.0D).setSyncable(true));


    public static final RegistryObject<Attribute> MELEE_POTENCY = ATTRIBUTES.register("melee_potency",
            () -> new RangedAttribute("melee_potency", 0.0D, -2048.0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> MELEE_HASTE = ATTRIBUTES.register("melee_haste",
            () -> new RangedAttribute("melee_haste", 0.0D, -2048.0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> MELEE_ACCURACY = ATTRIBUTES.register("melee_accuracy",
            () -> new RangedAttribute("melee_accuracy", 0.0D, -2048.0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> MELEE_PRECISION = ATTRIBUTES.register("melee_precision",
            () -> new RangedAttribute("melee_precision", 0.0D, -2048.0D, 2048.0D).setSyncable(true));

    public static final RegistryObject<Attribute> PROJECTILE_POTENCY = ATTRIBUTES.register("projectile_potency",
            () -> new RangedAttribute("projectile_potency", 0.0D, -2048.0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> NOCK_HASTE = ATTRIBUTES.register("nock_haste",
            () -> new RangedAttribute("nock_haste", 0.0D, -2048.0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> PROJECTILE_ACCURACY = ATTRIBUTES.register("projectile_accuracy",
            () -> new RangedAttribute("projectile_accuracy", 0.0D, -2048.0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> PROJECTILE_PRECISION = ATTRIBUTES.register("projectile_precision",
            () -> new RangedAttribute("projectile_precision", 0.0D, -2048.0D, 2048.0D).setSyncable(true));

    public static final RegistryObject<Attribute> REJUVENATION = ATTRIBUTES.register("incoming_healing",
            () -> new RangedAttribute("incoming_healing", 0.0D, -2048.0D, 2048.0D).setSyncable(true));

    public static final RegistryObject<Attribute> RESTORATION = ATTRIBUTES.register("restoration",
            () -> new RangedAttribute("restoration", 0.0D, -2048.0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> AMPLIFICATION = ATTRIBUTES.register("amplification",
            () -> new RangedAttribute("amplification", 0.0D, -2048.0D, 2048.0D).setSyncable(true));

    public static final RegistryObject<Attribute> POTENCY_MULTIPLIER = ATTRIBUTES.register("potency_multiplier",
            () -> new RangedAttribute("potency_multiplier", 1.0D, 0.0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> ACCURACY_MULTIPLIER = ATTRIBUTES.register("accuracy_multiplier",
            () -> new RangedAttribute("accuracy_multiplier", 1.0D, 0.0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> PRECISION_MULTIPLIER = ATTRIBUTES.register("precision_multiplier",
            () -> new RangedAttribute("precision_multiplier", 1.0D, 0.0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> MELEE_HASTE_MULTIPLIER = ATTRIBUTES.register("melee_haste_multiplier",
            () -> new RangedAttribute("melee_haste_multiplier", 1.0D, 0.0D, 2048.0D).setSyncable(true));

    public static final RegistryObject<Attribute> MELEE_POTENCY_MULTIPLIER = ATTRIBUTES.register("melee_potency_multiplier",
            () -> new RangedAttribute("melee_potency_multiplier", 1.0D, 0.0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> MELEE_ACCURACY_MULTIPLIER = ATTRIBUTES.register("melee_accuracy_multiplier",
            () -> new RangedAttribute("melee_accuracy_multiplier", 1.0D, 0.0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> MELEE_PRECISION_MULTIPLIER = ATTRIBUTES.register("melee_precision_multiplier",
            () -> new RangedAttribute("melee_precision_multiplier", 1.0D, 0.0D, 2048.0D).setSyncable(true));

    public static final RegistryObject<Attribute> PROJECTILE_POTENCY_MULTIPLIER = ATTRIBUTES.register("projectile_potency_multiplier",
            () -> new RangedAttribute("projectile_potency_multiplier", 1.0D, 0.0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> PROJECTILE_ACCURACY_MULTIPLIER = ATTRIBUTES.register("projectile_accuracy_multiplier",
            () -> new RangedAttribute("projectile_accuracy_multiplier", 1.0D, 0.0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> PROJECTILE_PRECISION_MULTIPLIER = ATTRIBUTES.register("projectile_precision_multiplier",
            () -> new RangedAttribute("projectile_precision_multiplier", 1.0D, 0.0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> NOCK_HASTE_MULTIPLIER = ATTRIBUTES.register("nock_haste_multiplier",
            () -> new RangedAttribute("name.nock_haste_multiplier", 1.0D, 0.0D, 2048.0D).setSyncable(true));

    public static final RegistryObject<Attribute> RESTORATION_MULTIPLIER = ATTRIBUTES.register("restoration_multiplier",
            () -> new RangedAttribute("restoration_multiplier", 1.0D, 0.0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> AMPLIFICATION_MULTIPLIER = ATTRIBUTES.register("amplification_multiplier",
            () -> new RangedAttribute("amplification_multiplier", 1.0D, 0.0D, 2048.0D).setSyncable(true));

    public static final RegistryObject<Attribute> JUMP_BOOST = ATTRIBUTES.register("jump_boost",
            () -> new RangedAttribute("jump_boost", 1.0D, 0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> MINING_SPEED = ATTRIBUTES.register("mining_speed",
            () -> new RangedAttribute("mining_speed", 1.0D, 0D, 2048.0D).setSyncable(true));
    public static final RegistryObject<Attribute> XP_GAIN = ATTRIBUTES.register("xp_gain",
            () -> new RangedAttribute("xp_gain", 1.0D, 0D, 2048.0D).setSyncable(true));

    public static final RegistryObject<Attribute> ARMOR_MULTIPLIER = ATTRIBUTES.register("armor_multiplier",
            () -> new RangedAttribute("armor_multiplier", 0.0D, -0.99D, 2048.0D).setSyncable(true));

    public static final RegistryObject<Attribute> TOUGHNESS_MULTIPLIER = ATTRIBUTES.register("toughness_multiplier",
            () -> new RangedAttribute("toughness_multiplier", 0.0D, -0.99D, 2048.0D).setSyncable(true));

    public static final RegistryObject<Attribute> HEALTH_MULTIPLIER = ATTRIBUTES.register("health_multiplier",
            () -> new RangedAttribute("health_multiplier", 0.0D, -0.99D, 2048.0D).setSyncable(true));

    public static final RegistryObject<Attribute> OUTGOING_DAMAGE_MULTIPLIER = ATTRIBUTES.register("damage_multiplier",
            () -> new RangedAttribute("damage_multiplier", 1.0D, 0.0D, 2048.0D).setSyncable(true));

    public static final RegistryObject<Attribute> INCOMING_DAMAGE_MULTIPLIER = ATTRIBUTES.register("incoming_damage_multiplier",
            () -> new RangedAttribute("incoming_damage_multiplier", 1.0D,   0.0D, 2048.0D).setSyncable(true));

    public static final RegistryObject<Attribute> REJUVENATION_MULTIPLIER = ATTRIBUTES.register("incoming_healing_multiplier",
            () -> new RangedAttribute("incoming_healing_multiplier", 1.0D, 0.0D, 2048.0D).setSyncable(true));

    public static final RegistryObject<Attribute> MELEE_DAMAGE_MULTIPLIER = ATTRIBUTES.register("melee_damage_multiplier",
            () -> new RangedAttribute("melee_damage_multiplier", 1.0D, 0.0D, 2048.0D).setSyncable(true));

    public static final RegistryObject<Attribute> ALL_DAMAGE_MULTIPLIER = ATTRIBUTES.register("all_damage_multiplier",
            () -> new RangedAttribute("all_damage_multiplier", 1.0D, 0.0D, 2048.0D).setSyncable(true));

    public static final RegistryObject<Attribute> DOT_DAMAGE_MULTIPLIER = ATTRIBUTES.register("dot_damage_multiplier",
            () -> new RangedAttribute("dot_damage_multiplier", 1.0D, 0.0D, 2048.0D).setSyncable(true));

    public static final RegistryObject<Attribute> PROJECTILE_DAMAGE_MULTIPLIER = ATTRIBUTES.register("projectile_damage_multiplier",
            () -> new RangedAttribute("projectile_damage_multiplier", 1.0D, 0.0D, 2048.0D).setSyncable(true));

    public static final RegistryObject<Attribute> EVASION = ATTRIBUTES.register("evasion",
            () -> new RangedAttribute("evasion", 0.0D, 0.0D, 5.0D).setSyncable(true));


    @SubscribeEvent
    public static void onModifyEntityAttributes(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, ModAttributes.STR.get());
        event.add(EntityType.PLAYER, ModAttributes.FORT.get());
        event.add(EntityType.PLAYER, ModAttributes.DEX.get());
        event.add(EntityType.PLAYER, ModAttributes.INTELLIGENCE.get());
        event.add(EntityType.PLAYER, ModAttributes.WISDOM.get());
        event.add(EntityType.PLAYER, ModAttributes.CON.get());
        event.add(EntityType.PLAYER, ModAttributes.PERC.get());
        event.add(EntityType.PLAYER, ModAttributes.INSIGHT.get());

        event.add(EntityType.PLAYER, ModAttributes.POTENCY.get());
        event.add(EntityType.PLAYER, ModAttributes.HASTE.get());
        event.add(EntityType.PLAYER, ModAttributes.ACCURACY.get());
        event.add(EntityType.PLAYER, ModAttributes.PRECISION.get());
        event.add(EntityType.PLAYER, ModAttributes.MELEE_HASTE.get());

        event.add(EntityType.PLAYER, ModAttributes.MELEE_POTENCY.get());
        event.add(EntityType.PLAYER, ModAttributes.MELEE_ACCURACY.get());
        event.add(EntityType.PLAYER, ModAttributes.MELEE_PRECISION.get());

        event.add(EntityType.PLAYER, ModAttributes.PROJECTILE_POTENCY.get());
        event.add(EntityType.PLAYER, ModAttributes.NOCK_HASTE.get());
        event.add(EntityType.PLAYER, ModAttributes.PROJECTILE_ACCURACY.get());
        event.add(EntityType.PLAYER, ModAttributes.PROJECTILE_PRECISION.get());

        event.add(EntityType.PLAYER, ModAttributes.RESTORATION.get());
        event.add(EntityType.PLAYER, ModAttributes.AMPLIFICATION.get());
        event.add(EntityType.PLAYER, ModAttributes.DEBUFF_RESIST.get());

        event.add(EntityType.PLAYER, ModAttributes.POTENCY_MULTIPLIER.get());
        event.add(EntityType.PLAYER, ModAttributes.HASTE_MULTIPLIER.get());
        event.add(EntityType.PLAYER, ModAttributes.ACCURACY_MULTIPLIER.get());
        event.add(EntityType.PLAYER, ModAttributes.PRECISION_MULTIPLIER.get());

        event.add(EntityType.PLAYER, ModAttributes.MELEE_POTENCY_MULTIPLIER.get());
        event.add(EntityType.PLAYER, ModAttributes.MELEE_HASTE_MULTIPLIER.get());
        event.add(EntityType.PLAYER, ModAttributes.MELEE_ACCURACY_MULTIPLIER.get());
        event.add(EntityType.PLAYER, ModAttributes.MELEE_PRECISION_MULTIPLIER.get());

        event.add(EntityType.PLAYER, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get());
        event.add(EntityType.PLAYER, ModAttributes.PROJECTILE_ACCURACY_MULTIPLIER.get());
        event.add(EntityType.PLAYER, ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get());
        event.add(EntityType.PLAYER, ModAttributes.NOCK_HASTE_MULTIPLIER.get());

        event.add(EntityType.PLAYER, ModAttributes.RESTORATION_MULTIPLIER.get());
        event.add(EntityType.PLAYER, ModAttributes.AMPLIFICATION_MULTIPLIER.get());

        event.add(EntityType.PLAYER, ModAttributes.JUMP_BOOST.get());
        event.add(EntityType.PLAYER, ModAttributes.MINING_SPEED.get());
        event.add(EntityType.PLAYER, ModAttributes.XP_GAIN.get());

        event.add(EntityType.PLAYER, ModAttributes.ARMOR_MULTIPLIER.get());
        event.add(EntityType.PLAYER, ModAttributes.TOUGHNESS_MULTIPLIER.get());
        event.add(EntityType.PLAYER, ModAttributes.HEALTH_MULTIPLIER.get());
        event.add(EntityType.PLAYER, ModAttributes.EVASION.get());

        event.add(EntityType.PLAYER, ModAttributes.REJUVENATION_MULTIPLIER.get());
        event.add(EntityType.PLAYER, ModAttributes.REJUVENATION.get());

        event.add(EntityType.PLAYER, ModAttributes.MELEE_DAMAGE_MULTIPLIER.get());
        event.add(EntityType.PLAYER, ModAttributes.DOT_DAMAGE_MULTIPLIER.get());
        event.add(EntityType.PLAYER, ModAttributes.PROJECTILE_DAMAGE_MULTIPLIER.get());
        event.add(EntityType.PLAYER, ModAttributes.OUTGOING_DAMAGE_MULTIPLIER.get());
        event.add(EntityType.PLAYER, ModAttributes.INCOMING_DAMAGE_MULTIPLIER.get());
        event.add(EntityType.PLAYER, ModAttributes.ALL_DAMAGE_MULTIPLIER.get());


        for (EntityType<? extends LivingEntity> type : event.getTypes()) {
            event.add(type, ModAttributes.OUTGOING_DAMAGE_MULTIPLIER.get());
            event.add(type, ModAttributes.INCOMING_DAMAGE_MULTIPLIER.get());
        }
    }
}