package net.cold.coldsmod.menu_accessory;

import net.cold.coldsmod.accessory.UtilityAccessories;
import net.cold.coldsmod.accessory.bracers.*;
import net.cold.coldsmod.accessory.mind.*;
import net.cold.coldsmod.accessory.necklace.*;
import net.cold.coldsmod.accessory.ring.*;
import net.cold.coldsmod.item.ModItems;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeMod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;


public class AccessoryStatRegistry {
    public static final Map<Item, Consumer<Player>> ON_APPLY_ACC = new HashMap<>();
    public static final Map<Item, Consumer<Player>> ON_REMOVE_ACC = new HashMap<>();

    private static final UUID BRACELET_PRIDE_UUID = UUID.fromString("a1b2c3d4-e5f6-4a5b-8c9d-0e1f2a3b4c5d");
    private static final UUID DRAGON_CLAW_UUID = UUID.fromString("b72a4e1d-8c3b-4f92-a106-928475f32104");
    private static final UUID ENDERMAN_ARM_UUID = UUID.fromString("6a3d9382-7e21-4f1a-b034-295473f32812");
    private static final UUID FINGERS_UUID = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479");
    private static final UUID REINFORCED_STEEL_UUID = UUID.fromString("d2a1b3c4-e5f6-4a5b-8c9d-1e2f3a4b5c6d");
    private static final UUID SERPENT_WRAP_UUID = UUID.fromString("c3e4d5a6-b7c8-4a5b-9d0e-1f2a3b4c5d6e");
    private static final UUID THIEVERY_UUID = UUID.fromString("f1e2d3c4-b5a6-4987-8c7d-6e5f4a3b2c1d");
    private static final UUID WARDEN_BRACERS_UUID = UUID.fromString("e32b4f91-7d1c-4b32-9015-38475f32a106");

    private static final UUID DRAGONS_ROAR_UUID = UUID.fromString("e4d5c6b7-a8b9-4c0d-1e2f-3a4b5c6d7e8f");
    private static final UUID ENDLESS_WAVES_UUID = UUID.fromString("a1b2c3d4-e5f6-4a5b-8c9d-1e2f3a4b5c6d");
    private static final UUID SHRIEKS_UNSEEING_UUID = UUID.fromString("b2c3d4e5-f6a7-4b8c-9d0e-1f2a3b4c5d6e");
    private static final UUID SUNS_GAZE_UUID = UUID.fromString("d5e6f7a8-b9c0-4d1e-2f3a-4b5c6d7e8f9a");
    private static final UUID TEAR_FORGOTTEN_UUID = UUID.fromString("f5a4e3d2-c1b0-4a9b-8c7d-6e5f4a3b2c1d");
    private static final UUID TEMPTING_WHISPERS_UUID = UUID.fromString("a1b2c3d4-e5f6-4a5b-8c9d-0e1f2a3b4c5d");

    private static final UUID BOTTLED_TSUNAMI_UUID = UUID.fromString("a1b2c3d4-e5f6-4a5b-bc6d-7e8f9a0b1c2d");
    private static final UUID DRAGON_TEETH_UUID = UUID.fromString("e5d4c3b2-a1f0-4b9c-8d7e-6f5a4b3c2d1e");
    private static final UUID COLLAR_ENVY_UUID = UUID.fromString("c011a7-e117-4b2c-8d3d-7e564a3b2c1d");
    private static final UUID TIGER_TOOTH_UUID = UUID.fromString("f6a5b4c3-d2e1-4f0a-9b8c-7d6e5f4a3b2c");
    private static final UUID KEY_UNKNOWN_UUID = UUID.fromString("d4e3f2a1-b0c9-4a8b-7d6e-5f4a3b2c1d0e");
    private static final UUID SNOWFLAKE_UUID = UUID.fromString("d1e2f3a4-b5c6-4d7e-8f9a-0b1c2d3e4f5a");
    private static final UUID STOLEN_LEGACIES_UUID = UUID.fromString("b7d6c5a4-e3f2-4109-8d7c-6e5b4a3f2d1c");

    private static final UUID UNKNOWN_BAND_UUID = UUID.fromString("f1e2d3c4-b5a6-4789-8d7c-6e5b4a3f2d1c");
    private static final UUID COIL_OF_WRATH_UUID = UUID.fromString("c9b8a7d6-e5f4-4321-b1a2-c3d4e5f6a7b8");
    private static final UUID COLD_COIL_UUID = UUID.fromString("b1c2d3e4-a5b6-7890-c1d2-e3f4a5b6c7d8");
    private static final UUID CORRUPTED_LOST_UUID = UUID.fromString("c1b2a3d4-e5f6-4789-9d8c-7b6a5f4e3d2c");
    private static final UUID DRAGON_EYE_UUID = UUID.fromString("d7e8f9a0-b1c2-4d3e-5f6a-7b8c9d0e1f2a");
    private static final UUID GLUTTONY_UUID = UUID.fromString("e7d6c5b4-a3f2-4109-8d7c-6e5b4a3f2d1c");
    private static final UUID SUNSTONE_UUID = UUID.fromString("a1b2c3d4-e5f6-4789-d1e2-f3a4b5c6d7e8");

    private static final UUID CLOUDTREADER_UUID = UUID.fromString("15555111-2222-3333-4444-55abc5555555");
    private static final UUID MONIS_LUCKY_UUID = UUID.fromString("22782222-3333-4444-5555-662216666666");
    private static final UUID ENDERMAN_FINGERS_UUID = UUID.fromString("35433333-4444-5555-6666-7abb77777777");
    private static final UUID ANTIQUE_WATCH_UUID = UUID.fromString("444aa444-5555-6666-7777-8888bb888888");
    private static final UUID REINFORCED_DIAMOND_UUID = UUID.fromString("55ee5555-6666-7777-8888-999ff9999999");
    private static final UUID CLOUDSPIRE_GEM_UUID = UUID.fromString("66633366-7777-8888-9999-aaaa00aaaaaa");
    private static final UUID FAIRY_INSIGHT_UUID = UUID.fromString("6d7f8a9b-0c1d-4e2f-8a5b-6c7d8e9f0a1b");
    private static final UUID HOOK_INSIGHT_UUID = UUID.fromString("a1b2c3d4-e5f6-125b-b6c7-d8e9f0a1b2c3");
    private static final UUID HELLFORGED_INSIGHT_UUID = UUID.fromString("f0e1d2c3-b4a5-4967-8b7a-6d884b3a2a1b");
    private static final UUID SELECTIVE_INSIGHT_UUID = UUID.fromString("1a2b3c4d-aa6f-47a8-9b0c-1d2e3f4a5b6c");
    private static final UUID BOTTLED_INSIGHT_UUID = UUID.fromString("9f8e7d6c-5b4a-4321-a1b2-c3d4cccca7b8");



    static {
        register(BraceletOfPride.BRACELET_OF_PRIDE_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 4.0, BRACELET_PRIDE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 4.0, BRACELET_PRIDE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 4.0, BRACELET_PRIDE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, BRACELET_PRIDE_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), BRACELET_PRIDE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), BRACELET_PRIDE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), BRACELET_PRIDE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), BRACELET_PRIDE_UUID);
                }
        );

        register(BraceletOfPride.BRACELET_OF_PRIDE_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 8.0, BRACELET_PRIDE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 4.0, BRACELET_PRIDE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 4.0, BRACELET_PRIDE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, BRACELET_PRIDE_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), BRACELET_PRIDE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), BRACELET_PRIDE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), BRACELET_PRIDE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), BRACELET_PRIDE_UUID);
                }
        );

        register(BraceletOfPride.BRACELET_OF_PRIDE_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 8.0, BRACELET_PRIDE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 5.0, BRACELET_PRIDE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 5.0, BRACELET_PRIDE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 4.0, BRACELET_PRIDE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, BRACELET_PRIDE_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), BRACELET_PRIDE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), BRACELET_PRIDE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), BRACELET_PRIDE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), BRACELET_PRIDE_UUID);
                }
        );

        register(BraceletOfPride.BRACELET_OF_PRIDE_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 10.0, BRACELET_PRIDE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 7.0, BRACELET_PRIDE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 7.0, BRACELET_PRIDE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 6.0, BRACELET_PRIDE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, BRACELET_PRIDE_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), BRACELET_PRIDE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), BRACELET_PRIDE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), BRACELET_PRIDE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), BRACELET_PRIDE_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------

        register(DragonClaw.DRAGON_CLAW_GLOVES_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 3.0, DRAGON_CLAW_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.ACCURACY.get(), 6.0, DRAGON_CLAW_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_ACCURACY.get(), 6.0, DRAGON_CLAW_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), 6.0, DRAGON_CLAW_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0,DRAGON_CLAW_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGON_CLAW_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.ACCURACY.get(), DRAGON_CLAW_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_ACCURACY.get(), DRAGON_CLAW_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), DRAGON_CLAW_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGON_CLAW_UUID);
                }
        );

        register(DragonClaw.DRAGON_CLAW_GLOVES_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 5.0, DRAGON_CLAW_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.ACCURACY.get(), 13.5, DRAGON_CLAW_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_ACCURACY.get(), 13.5, DRAGON_CLAW_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), 13.5, DRAGON_CLAW_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, DRAGON_CLAW_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGON_CLAW_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.ACCURACY.get(), DRAGON_CLAW_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGON_CLAW_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_ACCURACY.get(), DRAGON_CLAW_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), DRAGON_CLAW_UUID);
                }
        );

        register(DragonClaw.DRAGON_CLAW_GLOVES_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 7.0, DRAGON_CLAW_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.ACCURACY.get(), 18.0, DRAGON_CLAW_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 8, DRAGON_CLAW_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE.get(), 8, DRAGON_CLAW_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 8, DRAGON_CLAW_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, DRAGON_CLAW_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_ACCURACY.get(), 18.0, DRAGON_CLAW_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), 18.0, DRAGON_CLAW_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGON_CLAW_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.ACCURACY.get(), DRAGON_CLAW_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), DRAGON_CLAW_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE.get(), DRAGON_CLAW_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), DRAGON_CLAW_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGON_CLAW_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_ACCURACY.get(), DRAGON_CLAW_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), DRAGON_CLAW_UUID);
                }
        );

        register(DragonClaw.DRAGON_CLAW_GLOVES_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 9.0, DRAGON_CLAW_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.ACCURACY.get(), 24.0, DRAGON_CLAW_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 11, DRAGON_CLAW_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE.get(), 11, DRAGON_CLAW_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 11, DRAGON_CLAW_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, DRAGON_CLAW_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_ACCURACY.get(), 24.0, DRAGON_CLAW_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), 24.0, DRAGON_CLAW_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGON_CLAW_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.ACCURACY.get(), DRAGON_CLAW_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), DRAGON_CLAW_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE.get(), DRAGON_CLAW_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), DRAGON_CLAW_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGON_CLAW_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_ACCURACY.get(), DRAGON_CLAW_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), DRAGON_CLAW_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------

        register(Enderman.ENDERMANS_SEVERED_ARM_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 1.0, ENDERMAN_ARM_UUID);
                    AttributeApplier.applyModifier(player, ForgeMod.SWIM_SPEED.get(), -0.25, ENDERMAN_ARM_UUID);
                    AttributeApplier.applyModifier(player, ForgeMod.ENTITY_REACH.get(), 0.125, ENDERMAN_ARM_UUID);
                    AttributeApplier.applyModifier(player, ForgeMod.BLOCK_REACH.get(), 0.5, ENDERMAN_ARM_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, ENDERMAN_ARM_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MINING_SPEED.get(), 0.03, ENDERMAN_ARM_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), ENDERMAN_ARM_UUID);
                    AttributeApplier.removeModifier(player, ForgeMod.SWIM_SPEED.get(), ENDERMAN_ARM_UUID);
                    AttributeApplier.removeModifier(player, ForgeMod.ENTITY_REACH.get(), ENDERMAN_ARM_UUID);
                    AttributeApplier.removeModifier(player, ForgeMod.BLOCK_REACH.get(), ENDERMAN_ARM_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), ENDERMAN_ARM_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MINING_SPEED.get(), ENDERMAN_ARM_UUID);
                }
        );

        register(Enderman.ENDERMANS_SEVERED_ARM_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 4.0, ENDERMAN_ARM_UUID);
                    AttributeApplier.applyModifier(player, ForgeMod.SWIM_SPEED.get(), -0.25, ENDERMAN_ARM_UUID);
                    AttributeApplier.applyModifier(player, ForgeMod.ENTITY_REACH.get(), 0.25, ENDERMAN_ARM_UUID);
                    AttributeApplier.applyModifier(player, ForgeMod.BLOCK_REACH.get(), 1.0, ENDERMAN_ARM_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, ENDERMAN_ARM_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MINING_SPEED.get(), 0.06, ENDERMAN_ARM_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), ENDERMAN_ARM_UUID);
                    AttributeApplier.removeModifier(player, ForgeMod.SWIM_SPEED.get(), ENDERMAN_ARM_UUID);
                    AttributeApplier.removeModifier(player, ForgeMod.ENTITY_REACH.get(), ENDERMAN_ARM_UUID);
                    AttributeApplier.removeModifier(player, ForgeMod.BLOCK_REACH.get(), ENDERMAN_ARM_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), ENDERMAN_ARM_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MINING_SPEED.get(), ENDERMAN_ARM_UUID);
                }
        );

        register(Enderman.ENDERMANS_SEVERED_ARM_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 8.0, ENDERMAN_ARM_UUID);
                    AttributeApplier.applyModifier(player, ForgeMod.SWIM_SPEED.get(), -0.25, ENDERMAN_ARM_UUID);
                    AttributeApplier.applyModifier(player, ForgeMod.ENTITY_REACH.get(), 0.375, ENDERMAN_ARM_UUID);
                    AttributeApplier.applyModifier(player, ForgeMod.BLOCK_REACH.get(), 1.5, ENDERMAN_ARM_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 6.0, ENDERMAN_ARM_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MINING_SPEED.get(), 0.09, ENDERMAN_ARM_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), ENDERMAN_ARM_UUID);
                    AttributeApplier.removeModifier(player, ForgeMod.SWIM_SPEED.get(), ENDERMAN_ARM_UUID);
                    AttributeApplier.removeModifier(player, ForgeMod.ENTITY_REACH.get(), ENDERMAN_ARM_UUID);
                    AttributeApplier.removeModifier(player, ForgeMod.BLOCK_REACH.get(), ENDERMAN_ARM_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), ENDERMAN_ARM_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MINING_SPEED.get(), ENDERMAN_ARM_UUID);
                }
        );

        register(Enderman.ENDERMANS_SEVERED_ARM_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 10.0, ENDERMAN_ARM_UUID);
                    AttributeApplier.applyModifier(player, ForgeMod.SWIM_SPEED.get(), -0.25, ENDERMAN_ARM_UUID);
                    AttributeApplier.applyModifier(player, ForgeMod.ENTITY_REACH.get(), 0.5, ENDERMAN_ARM_UUID);
                    AttributeApplier.applyModifier(player, ForgeMod.BLOCK_REACH.get(), 2.0, ENDERMAN_ARM_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 8.0, ENDERMAN_ARM_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MINING_SPEED.get(), 0.12, ENDERMAN_ARM_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), ENDERMAN_ARM_UUID);
                    AttributeApplier.removeModifier(player, ForgeMod.SWIM_SPEED.get(), ENDERMAN_ARM_UUID);
                    AttributeApplier.removeModifier(player, ForgeMod.ENTITY_REACH.get(), ENDERMAN_ARM_UUID);
                    AttributeApplier.removeModifier(player, ForgeMod.BLOCK_REACH.get(), ENDERMAN_ARM_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), ENDERMAN_ARM_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MINING_SPEED.get(), ENDERMAN_ARM_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------

        register(FingersOfLust.FINGERS_OF_LUST_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 5.0, FINGERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 3.0, FINGERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), -6.0, FINGERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE.get(), 2.0, FINGERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), 2.0, FINGERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 4.0, FINGERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, FINGERS_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), FINGERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), FINGERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), FINGERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE.get(), FINGERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), FINGERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), FINGERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), FINGERS_UUID);
                }
        );

        register(FingersOfLust.FINGERS_OF_LUST_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 6.0, FINGERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 4.0, FINGERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), -6.0, FINGERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE.get(), 3.75, FINGERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), 3.75, FINGERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 6.75, FINGERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, FINGERS_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), FINGERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), FINGERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), FINGERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE.get(), FINGERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), FINGERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), FINGERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), FINGERS_UUID);
                }
        );

        register(FingersOfLust.FINGERS_OF_LUST_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 7.0, FINGERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 5.0, FINGERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), -6.0, FINGERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE.get(), 4.5, FINGERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), 7.0, FINGERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 12.0, FINGERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE_MULTIPLIER.get(), 0.09, FINGERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, FINGERS_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), FINGERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), FINGERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), FINGERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), FINGERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE.get(), FINGERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), FINGERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE_MULTIPLIER.get(), FINGERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), FINGERS_UUID);
                }
        );

        register(FingersOfLust.FINGERS_OF_LUST_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 8.0, FINGERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 6.0, FINGERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), -6.0, FINGERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE.get(), 6.0, FINGERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), 12.0, FINGERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 18.0, FINGERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE_MULTIPLIER.get(), 0.18, FINGERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, FINGERS_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), FINGERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), FINGERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), FINGERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE.get(), FINGERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), FINGERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), FINGERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE_MULTIPLIER.get(), FINGERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), FINGERS_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------

        register(ReinforcedSteel.REINFORCED_STEEL_BRACERS_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 4.0, REINFORCED_STEEL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 3.0, REINFORCED_STEEL_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR, 1, REINFORCED_STEEL_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 0.25, REINFORCED_STEEL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, REINFORCED_STEEL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.REJUVENATION.get(), 5.0, REINFORCED_STEEL_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), REINFORCED_STEEL_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR, REINFORCED_STEEL_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, REINFORCED_STEEL_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), REINFORCED_STEEL_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.REJUVENATION.get(), REINFORCED_STEEL_UUID);
                }
        );

        register(ReinforcedSteel.REINFORCED_STEEL_BRACERS_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 7.5, REINFORCED_STEEL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 5, REINFORCED_STEEL_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR, 1.5, REINFORCED_STEEL_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 0.5, REINFORCED_STEEL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, REINFORCED_STEEL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.REJUVENATION.get(), 7.0, REINFORCED_STEEL_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), REINFORCED_STEEL_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR, REINFORCED_STEEL_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, REINFORCED_STEEL_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), REINFORCED_STEEL_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.REJUVENATION.get(), REINFORCED_STEEL_UUID);
                }
        );

        register(ReinforcedSteel.REINFORCED_STEEL_BRACERS_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 12.0, REINFORCED_STEEL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 8.0, REINFORCED_STEEL_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR, 2.0, REINFORCED_STEEL_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 0.75, REINFORCED_STEEL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.ACCURACY_MULTIPLIER.get(), 0.08, REINFORCED_STEEL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, REINFORCED_STEEL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.REJUVENATION.get(), 10.0, REINFORCED_STEEL_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), REINFORCED_STEEL_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR, REINFORCED_STEEL_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, REINFORCED_STEEL_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.ACCURACY_MULTIPLIER.get(), REINFORCED_STEEL_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), REINFORCED_STEEL_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.REJUVENATION.get(), REINFORCED_STEEL_UUID);
                }
        );

        register(ReinforcedSteel.REINFORCED_STEEL_BRACERS_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 16.0, REINFORCED_STEEL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 10.0, REINFORCED_STEEL_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR, 2.75, REINFORCED_STEEL_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 1.0, REINFORCED_STEEL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.ACCURACY_MULTIPLIER.get(), 0.15, REINFORCED_STEEL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, REINFORCED_STEEL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.REJUVENATION.get(), 12.0, REINFORCED_STEEL_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), REINFORCED_STEEL_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR, REINFORCED_STEEL_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, REINFORCED_STEEL_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.ACCURACY_MULTIPLIER.get(), REINFORCED_STEEL_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), REINFORCED_STEEL_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.REJUVENATION.get(), REINFORCED_STEEL_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------

        register(SerpentSkin.SERPENT_SKIN_WRAP_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 6.0, SERPENT_WRAP_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 12.0, SERPENT_WRAP_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, SERPENT_WRAP_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 5.0, SERPENT_WRAP_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), SERPENT_WRAP_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), SERPENT_WRAP_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SERPENT_WRAP_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), SERPENT_WRAP_UUID);
                }
        );

        register(SerpentSkin.SERPENT_SKIN_WRAP_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 6.0, SERPENT_WRAP_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 2.0, SERPENT_WRAP_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 11.0, SERPENT_WRAP_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, SERPENT_WRAP_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 6.0, SERPENT_WRAP_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), SERPENT_WRAP_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), SERPENT_WRAP_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SERPENT_WRAP_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), SERPENT_WRAP_UUID);
                }
        );

        register(SerpentSkin.SERPENT_SKIN_WRAP_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 9.0, SERPENT_WRAP_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 4.0, SERPENT_WRAP_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 16.0, SERPENT_WRAP_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), 0.04, SERPENT_WRAP_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), 0.0675, SERPENT_WRAP_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, SERPENT_WRAP_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 8.0, SERPENT_WRAP_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), SERPENT_WRAP_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), SERPENT_WRAP_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), SERPENT_WRAP_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), SERPENT_WRAP_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SERPENT_WRAP_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), SERPENT_WRAP_UUID);
                }
        );

        register(SerpentSkin.SERPENT_SKIN_WRAP_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 11.0, SERPENT_WRAP_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 6.0, SERPENT_WRAP_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 21.0, SERPENT_WRAP_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), 0.0675, SERPENT_WRAP_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), 0.1175, SERPENT_WRAP_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, SERPENT_WRAP_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 10.0, SERPENT_WRAP_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), SERPENT_WRAP_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), SERPENT_WRAP_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), SERPENT_WRAP_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), SERPENT_WRAP_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SERPENT_WRAP_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), SERPENT_WRAP_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------

        register(ThieveryWraps.WRAPS_OF_THIEVERY_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE_MULTIPLIER.get(), -0.06, THIEVERY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 13.0, THIEVERY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION.get(), 13.0, THIEVERY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), 13.0, THIEVERY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, THIEVERY_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE_MULTIPLIER.get(), THIEVERY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), THIEVERY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION.get(), THIEVERY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), THIEVERY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), THIEVERY_UUID);
                }
        );

        register(ThieveryWraps.WRAPS_OF_THIEVERY_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE_MULTIPLIER.get(), -0.1, THIEVERY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 19.0, THIEVERY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION.get(), 19.0, THIEVERY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), 19.0, THIEVERY_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 0.5, THIEVERY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, THIEVERY_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE_MULTIPLIER.get(), THIEVERY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), THIEVERY_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, THIEVERY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), THIEVERY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION.get(), THIEVERY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), THIEVERY_UUID);
                }
        );

        register(ThieveryWraps.WRAPS_OF_THIEVERY_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE_MULTIPLIER.get(), -0.15, THIEVERY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 22.0, THIEVERY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION.get(), 22.0, THIEVERY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), 22.0, THIEVERY_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 1.0, THIEVERY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), 0.135, THIEVERY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, THIEVERY_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE_MULTIPLIER.get(), THIEVERY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), THIEVERY_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, THIEVERY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), THIEVERY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), THIEVERY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION.get(), THIEVERY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), THIEVERY_UUID);
                }
        );

        register(ThieveryWraps.WRAPS_OF_THIEVERY_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE_MULTIPLIER.get(), -0.15, THIEVERY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 27.5, THIEVERY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION.get(), 27.5, THIEVERY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), 27.5, THIEVERY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), 0.2, THIEVERY_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 1.2, THIEVERY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, THIEVERY_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE_MULTIPLIER.get(), THIEVERY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), THIEVERY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), THIEVERY_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, THIEVERY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), THIEVERY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION.get(), THIEVERY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), THIEVERY_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------

        register(WardenSkin.WARDEN_SKIN_FORGED_BRACERS_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 3.0, WARDEN_BRACERS_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR, 1.5, WARDEN_BRACERS_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 1.5, WARDEN_BRACERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, WARDEN_BRACERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 1.0, WARDEN_BRACERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 6.0, WARDEN_BRACERS_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), WARDEN_BRACERS_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR, WARDEN_BRACERS_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, WARDEN_BRACERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), WARDEN_BRACERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), WARDEN_BRACERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), WARDEN_BRACERS_UUID);
                }
        );

        register(WardenSkin.WARDEN_SKIN_FORGED_BRACERS_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 4.0, WARDEN_BRACERS_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR, 2, WARDEN_BRACERS_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 2.25, WARDEN_BRACERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, WARDEN_BRACERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 2.0, WARDEN_BRACERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 8.0, WARDEN_BRACERS_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), WARDEN_BRACERS_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR, WARDEN_BRACERS_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, WARDEN_BRACERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), WARDEN_BRACERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), WARDEN_BRACERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), WARDEN_BRACERS_UUID);
                }
        );

        register(WardenSkin.WARDEN_SKIN_FORGED_BRACERS_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 5.0, WARDEN_BRACERS_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR, 2.5, WARDEN_BRACERS_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 0.5, WARDEN_BRACERS_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 2.75, WARDEN_BRACERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), 0.10, WARDEN_BRACERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, WARDEN_BRACERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 4.0, WARDEN_BRACERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 12.0, WARDEN_BRACERS_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), WARDEN_BRACERS_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR, WARDEN_BRACERS_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, WARDEN_BRACERS_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, WARDEN_BRACERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), WARDEN_BRACERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), WARDEN_BRACERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), WARDEN_BRACERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), WARDEN_BRACERS_UUID);
                }
        );

        register(WardenSkin.WARDEN_SKIN_FORGED_BRACERS_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 8.0, WARDEN_BRACERS_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR, 3.0, WARDEN_BRACERS_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 1.0, WARDEN_BRACERS_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 3.5, WARDEN_BRACERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), 0.15, WARDEN_BRACERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, WARDEN_BRACERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 7.0, WARDEN_BRACERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 15.0, WARDEN_BRACERS_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), WARDEN_BRACERS_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR, WARDEN_BRACERS_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, WARDEN_BRACERS_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, WARDEN_BRACERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), WARDEN_BRACERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), WARDEN_BRACERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), WARDEN_BRACERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), WARDEN_BRACERS_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------

        register(DragonRoar.DRAGONS_ROAR_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 2.0, DRAGONS_ROAR_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, DRAGONS_ROAR_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 5.0, DRAGONS_ROAR_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGONS_ROAR_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGONS_ROAR_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, DRAGONS_ROAR_UUID);
                }
        );

        register(DragonRoar.DRAGONS_ROAR_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 5.0, DRAGONS_ROAR_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, DRAGONS_ROAR_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 9.0, DRAGONS_ROAR_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGONS_ROAR_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGONS_ROAR_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, DRAGONS_ROAR_UUID);
                }
        );

        register(DragonRoar.DRAGONS_ROAR_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 8.0, DRAGONS_ROAR_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, DRAGONS_ROAR_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 6.0, DRAGONS_ROAR_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), 0.22, DRAGONS_ROAR_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGONS_ROAR_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGONS_ROAR_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, DRAGONS_ROAR_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), DRAGONS_ROAR_UUID);
                }
        );

        register(DragonRoar.DRAGONS_ROAR_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 12.0, DRAGONS_ROAR_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, DRAGONS_ROAR_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 9.0, DRAGONS_ROAR_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), 0.30, DRAGONS_ROAR_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGONS_ROAR_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGONS_ROAR_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, DRAGONS_ROAR_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), DRAGONS_ROAR_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------

        register(EndlessWaves.ENDLESS_WAVES_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 1.0, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 2.0, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 2.0, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE.get(), 7.0, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), 5.0, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MOVEMENT_SPEED, 0.0075, ENDLESS_WAVES_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE.get(), ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MOVEMENT_SPEED, ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), ENDLESS_WAVES_UUID);
                }
        );

        register(EndlessWaves.ENDLESS_WAVES_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 3.0, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 5.0, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 4.0, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE.get(), 9.0, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), 7.5, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MOVEMENT_SPEED, 0.01, ENDLESS_WAVES_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE.get(), ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MOVEMENT_SPEED, ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), ENDLESS_WAVES_UUID);
                }
        );

        register(EndlessWaves.ENDLESS_WAVES_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 5.0, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 9.0, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 5.0, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE.get(), 14.0, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), 11.0, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE_MULTIPLIER.get(), 0.1, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MOVEMENT_SPEED, 0.01, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.RESTORATION_MULTIPLIER.get(), 0.05, ENDLESS_WAVES_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE.get(), ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE_MULTIPLIER.get(), ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MOVEMENT_SPEED, ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.RESTORATION_MULTIPLIER.get(), ENDLESS_WAVES_UUID);
                }
        );

        register(EndlessWaves.ENDLESS_WAVES_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 8.0, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 12.0, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 8.0, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE.get(), 18.0, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), 15.0, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE_MULTIPLIER.get(), 0.175, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MOVEMENT_SPEED, 0.0125, ENDLESS_WAVES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.RESTORATION_MULTIPLIER.get(), 0.125, ENDLESS_WAVES_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE.get(), ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE_MULTIPLIER.get(), ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MOVEMENT_SPEED, ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), ENDLESS_WAVES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.RESTORATION_MULTIPLIER.get(), ENDLESS_WAVES_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------

        register(Shrieks.SHRIEKS_OF_UNSEEING_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 6.0, SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR, 2.5, SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), -3.0, SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), -8, SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 5.0, SHRIEKS_UNSEEING_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR, SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), SHRIEKS_UNSEEING_UUID);
                }
        );

        register(Shrieks.SHRIEKS_OF_UNSEEING_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 10.0, SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR, 3.75, SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), -3.0, SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), -8, SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 7.5, SHRIEKS_UNSEEING_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR, SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), SHRIEKS_UNSEEING_UUID);
                }
        );

        register(Shrieks.SHRIEKS_OF_UNSEEING_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 13.0, SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR, 5.0, SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), 0.075, SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), -3.0, SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), -8, SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 10.0, SHRIEKS_UNSEEING_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR, SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), SHRIEKS_UNSEEING_UUID);
                }
        );

        register(Shrieks.SHRIEKS_OF_UNSEEING_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 17.0, SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR, 7.5, SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), 0.12, SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), -3.0, SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), -8, SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 12.5, SHRIEKS_UNSEEING_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR, SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), SHRIEKS_UNSEEING_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), SHRIEKS_UNSEEING_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------

        register(SunsGaze.SUNS_GAZE_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 3.0, SUNS_GAZE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, SUNS_GAZE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 8.0, SUNS_GAZE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 5.0, SUNS_GAZE_UUID);
                    AttributeApplier.applyModifier(player, ForgeMod.SWIM_SPEED.get(), -0.05, SUNS_GAZE_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), SUNS_GAZE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SUNS_GAZE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), SUNS_GAZE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), SUNS_GAZE_UUID);
                    AttributeApplier.removeModifier(player, ForgeMod.SWIM_SPEED.get(), SUNS_GAZE_UUID);
                }
        );

        register(SunsGaze.SUNS_GAZE_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 5.0, SUNS_GAZE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, SUNS_GAZE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 14.0, SUNS_GAZE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 6, SUNS_GAZE_UUID);
                    AttributeApplier.applyModifier(player, ForgeMod.SWIM_SPEED.get(), -0.10, SUNS_GAZE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 5.0, SUNS_GAZE_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), SUNS_GAZE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SUNS_GAZE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), SUNS_GAZE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), SUNS_GAZE_UUID);
                    AttributeApplier.removeModifier(player, ForgeMod.SWIM_SPEED.get(), SUNS_GAZE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), SUNS_GAZE_UUID);
                }
        );

        register(SunsGaze.SUNS_GAZE_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 9.0, SUNS_GAZE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, SUNS_GAZE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 18.0, SUNS_GAZE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 9.0, SUNS_GAZE_UUID);
                    AttributeApplier.applyModifier(player, ForgeMod.SWIM_SPEED.get(), -0.15, SUNS_GAZE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), 0.08, SUNS_GAZE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 10.0, SUNS_GAZE_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), SUNS_GAZE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SUNS_GAZE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), SUNS_GAZE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), SUNS_GAZE_UUID);
                    AttributeApplier.removeModifier(player, ForgeMod.SWIM_SPEED.get(), SUNS_GAZE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), SUNS_GAZE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), SUNS_GAZE_UUID);
                }
        );

        register(SunsGaze.SUNS_GAZE_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 13.0, SUNS_GAZE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, SUNS_GAZE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 22.0, SUNS_GAZE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 12.0, SUNS_GAZE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), 0.125, SUNS_GAZE_UUID);
                    AttributeApplier.applyModifier(player, ForgeMod.SWIM_SPEED.get(), -0.20, SUNS_GAZE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 15.0, SUNS_GAZE_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), SUNS_GAZE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SUNS_GAZE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), SUNS_GAZE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), SUNS_GAZE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), SUNS_GAZE_UUID);
                    AttributeApplier.removeModifier(player, ForgeMod.SWIM_SPEED.get(), SUNS_GAZE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), SUNS_GAZE_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------

        register(Tear.TEAR_OF_THE_FORGOTTEN_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, TEAR_FORGOTTEN_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 2.5, TEAR_FORGOTTEN_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 3.0, TEAR_FORGOTTEN_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), TEAR_FORGOTTEN_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, TEAR_FORGOTTEN_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), TEAR_FORGOTTEN_UUID);
                }
        );

        register(Tear.TEAR_OF_THE_FORGOTTEN_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, TEAR_FORGOTTEN_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 4.5, TEAR_FORGOTTEN_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 5.0, TEAR_FORGOTTEN_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), TEAR_FORGOTTEN_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, TEAR_FORGOTTEN_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), TEAR_FORGOTTEN_UUID);
                }
        );

        register(Tear.TEAR_OF_THE_FORGOTTEN_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, TEAR_FORGOTTEN_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 6, TEAR_FORGOTTEN_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 3.0, TEAR_FORGOTTEN_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 5.0, TEAR_FORGOTTEN_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), 0.8, TEAR_FORGOTTEN_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), TEAR_FORGOTTEN_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, TEAR_FORGOTTEN_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), TEAR_FORGOTTEN_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), TEAR_FORGOTTEN_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), TEAR_FORGOTTEN_UUID);
                }
        );

        register(Tear.TEAR_OF_THE_FORGOTTEN_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, TEAR_FORGOTTEN_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 7.5, TEAR_FORGOTTEN_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 6.0, TEAR_FORGOTTEN_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 6.0, TEAR_FORGOTTEN_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 1.5, TEAR_FORGOTTEN_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), 0.12, TEAR_FORGOTTEN_UUID);
                    AttributeApplier.applyModifier(player, Attributes.KNOCKBACK_RESISTANCE, 0.15, TEAR_FORGOTTEN_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), TEAR_FORGOTTEN_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, TEAR_FORGOTTEN_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), TEAR_FORGOTTEN_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), TEAR_FORGOTTEN_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, TEAR_FORGOTTEN_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), TEAR_FORGOTTEN_UUID);
                    AttributeApplier.removeModifier(player, Attributes.KNOCKBACK_RESISTANCE, TEAR_FORGOTTEN_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------

        register(TemptingWhispers.TEMPTING_WHISPERS_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 3.0, TEMPTING_WHISPERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, TEMPTING_WHISPERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), 0.3, TEMPTING_WHISPERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.HEALTH_MULTIPLIER.get(), -0.25, TEMPTING_WHISPERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), -4.0, TEMPTING_WHISPERS_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), TEMPTING_WHISPERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), TEMPTING_WHISPERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), TEMPTING_WHISPERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.HEALTH_MULTIPLIER.get(), TEMPTING_WHISPERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), TEMPTING_WHISPERS_UUID);
                }
        );

        register(TemptingWhispers.TEMPTING_WHISPERS_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 5.0, TEMPTING_WHISPERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, TEMPTING_WHISPERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), 0.6, TEMPTING_WHISPERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.HEALTH_MULTIPLIER.get(), -0.40, TEMPTING_WHISPERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), -4.0, TEMPTING_WHISPERS_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), TEMPTING_WHISPERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), TEMPTING_WHISPERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), TEMPTING_WHISPERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.HEALTH_MULTIPLIER.get(), TEMPTING_WHISPERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), TEMPTING_WHISPERS_UUID);
                }
        );

        register(TemptingWhispers.TEMPTING_WHISPERS_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 7.0, TEMPTING_WHISPERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, TEMPTING_WHISPERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), 1.2, TEMPTING_WHISPERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.HEALTH_MULTIPLIER.get(), -0.5, TEMPTING_WHISPERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), -4.0, TEMPTING_WHISPERS_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), TEMPTING_WHISPERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), TEMPTING_WHISPERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), TEMPTING_WHISPERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.HEALTH_MULTIPLIER.get(), TEMPTING_WHISPERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), TEMPTING_WHISPERS_UUID);
                }
        );

        register(TemptingWhispers.TEMPTING_WHISPERS_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 9.0, TEMPTING_WHISPERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, TEMPTING_WHISPERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), 1.5, TEMPTING_WHISPERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.HEALTH_MULTIPLIER.get(), -0.5, TEMPTING_WHISPERS_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), -4.0, TEMPTING_WHISPERS_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), TEMPTING_WHISPERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), TEMPTING_WHISPERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), TEMPTING_WHISPERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.HEALTH_MULTIPLIER.get(), TEMPTING_WHISPERS_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), TEMPTING_WHISPERS_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------

        register(BottledTsunami.BOTTLED_TSUNAMI_SEA_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, BOTTLED_TSUNAMI_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 6.0, BOTTLED_TSUNAMI_UUID);
                    AttributeApplier.applyModifier(player, ForgeMod.SWIM_SPEED.get(), 0.10, BOTTLED_TSUNAMI_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 1.2, BOTTLED_TSUNAMI_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), BOTTLED_TSUNAMI_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), BOTTLED_TSUNAMI_UUID);
                    AttributeApplier.removeModifier(player, ForgeMod.SWIM_SPEED.get(), BOTTLED_TSUNAMI_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, BOTTLED_TSUNAMI_UUID);
                }
        );

        register(BottledTsunami.BOTTLED_TSUNAMI_SEA_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, BOTTLED_TSUNAMI_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 9.0, BOTTLED_TSUNAMI_UUID);
                    AttributeApplier.applyModifier(player, ForgeMod.SWIM_SPEED.get(), 0.20, BOTTLED_TSUNAMI_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 1.75, BOTTLED_TSUNAMI_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), BOTTLED_TSUNAMI_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), BOTTLED_TSUNAMI_UUID);
                    AttributeApplier.removeModifier(player, ForgeMod.SWIM_SPEED.get(), BOTTLED_TSUNAMI_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, BOTTLED_TSUNAMI_UUID);
                }
        );

        register(BottledTsunami.BOTTLED_TSUNAMI_SEA_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, BOTTLED_TSUNAMI_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 13.0, BOTTLED_TSUNAMI_UUID);
                    AttributeApplier.applyModifier(player, ForgeMod.SWIM_SPEED.get(), 0.30, BOTTLED_TSUNAMI_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 2.3, BOTTLED_TSUNAMI_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), BOTTLED_TSUNAMI_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), BOTTLED_TSUNAMI_UUID);
                    AttributeApplier.removeModifier(player, ForgeMod.SWIM_SPEED.get(), BOTTLED_TSUNAMI_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, BOTTLED_TSUNAMI_UUID);
                }
        );

        register(BottledTsunami.BOTTLED_TSUNAMI_SEA_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, BOTTLED_TSUNAMI_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 17.0, BOTTLED_TSUNAMI_UUID);
                    AttributeApplier.applyModifier(player, ForgeMod.SWIM_SPEED.get(), 0.40, BOTTLED_TSUNAMI_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 3.0, BOTTLED_TSUNAMI_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), BOTTLED_TSUNAMI_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), BOTTLED_TSUNAMI_UUID);
                    AttributeApplier.removeModifier(player, ForgeMod.SWIM_SPEED.get(), BOTTLED_TSUNAMI_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, BOTTLED_TSUNAMI_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------

        register(DragonTeethNecklace.DRAGON_TEETH_NECKLACE_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 2.0, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.ACCURACY.get(), 8.0, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_ACCURACY.get(), 8.0, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), 8.0, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 2.0, DRAGON_TEETH_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.ACCURACY.get(), DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_ACCURACY.get(), DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), DRAGON_TEETH_UUID);
                }
        );

        register(DragonTeethNecklace.DRAGON_TEETH_NECKLACE_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 3.0, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.ACCURACY.get(), 14.0, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_ACCURACY.get(), 14.0, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), 14.0, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 0.3, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 3.0, DRAGON_TEETH_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.ACCURACY.get(), DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_ACCURACY.get(), DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), DRAGON_TEETH_UUID);
                }
        );

        register(DragonTeethNecklace.DRAGON_TEETH_NECKLACE_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 7.0, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.ACCURACY.get(), 15.0, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_ACCURACY.get(), 15.0, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), 15.0, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 0.5, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 7.0, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PRECISION_MULTIPLIER.get(), 0.1, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), 0.1, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get(), 0.1, DRAGON_TEETH_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.ACCURACY.get(), DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_ACCURACY.get(), DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PRECISION_MULTIPLIER.get(), DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get(), DRAGON_TEETH_UUID);
                }
        );

        register(DragonTeethNecklace.DRAGON_TEETH_NECKLACE_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 9.0, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.ACCURACY.get(), 18.0, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_ACCURACY.get(), 18.0, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), 18.0, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 0.7, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 9.0, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PRECISION_MULTIPLIER.get(), 0.15, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), 0.15, DRAGON_TEETH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get(), 0.15, DRAGON_TEETH_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.ACCURACY.get(), DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_ACCURACY.get(), DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PRECISION_MULTIPLIER.get(), DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), DRAGON_TEETH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get(), DRAGON_TEETH_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------

        register(EnvyCollar.COLLAR_OF_ENVY_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 3.0, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 4.0, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 4.0, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 1.5, COLLAR_ENVY_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, COLLAR_ENVY_UUID);
                }
        );

        register(EnvyCollar.COLLAR_OF_ENVY_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 4.0, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 6.0, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 5.0, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 4.0, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION.get(), 4.0, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), 4.0, COLLAR_ENVY_UUID);
                    },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), COLLAR_ENVY_UUID);
                }
        );

        register(EnvyCollar.COLLAR_OF_ENVY_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 5.0, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 8.0, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 8.0, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.POTENCY.get(), 8.0, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 7.0, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), 0.075, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 8.0, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION.get(), 8.0, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), 8.0, COLLAR_ENVY_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.POTENCY.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), COLLAR_ENVY_UUID);
                }
        );

        register(EnvyCollar.COLLAR_OF_ENVY_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 7.0, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 14.0, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 14.0, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.POTENCY.get(), 14.0, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 8.0, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), 0.15, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 12.0, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION.get(), 12.0, COLLAR_ENVY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), 12.0, COLLAR_ENVY_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.POTENCY.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), COLLAR_ENVY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), COLLAR_ENVY_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------

        register(HangingTigerTooth.HANGING_TIGER_TOOTH_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, TIGER_TOOTH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 6.0, TIGER_TOOTH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 4.5, TIGER_TOOTH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 3.0, TIGER_TOOTH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE.get(), 2.0, TIGER_TOOTH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 5.0, TIGER_TOOTH_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), TIGER_TOOTH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), TIGER_TOOTH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), TIGER_TOOTH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), TIGER_TOOTH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE.get(), TIGER_TOOTH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), TIGER_TOOTH_UUID);
                }
        );

        register(HangingTigerTooth.HANGING_TIGER_TOOTH_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, TIGER_TOOTH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 9.0, TIGER_TOOTH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 7.0, TIGER_TOOTH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 6.0, TIGER_TOOTH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE.get(), 2.5, TIGER_TOOTH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 7.5, TIGER_TOOTH_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), TIGER_TOOTH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), TIGER_TOOTH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), TIGER_TOOTH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), TIGER_TOOTH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE.get(), TIGER_TOOTH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), TIGER_TOOTH_UUID);
                }
        );

        register(HangingTigerTooth.HANGING_TIGER_TOOTH_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, TIGER_TOOTH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 12.0, TIGER_TOOTH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 11.0, TIGER_TOOTH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 8.5, TIGER_TOOTH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE.get(), 4.0, TIGER_TOOTH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 10.0, TIGER_TOOTH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), 0.1, TIGER_TOOTH_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), TIGER_TOOTH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), TIGER_TOOTH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), TIGER_TOOTH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), TIGER_TOOTH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE.get(), TIGER_TOOTH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), TIGER_TOOTH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), TIGER_TOOTH_UUID);
                }
        );

        register(HangingTigerTooth.HANGING_TIGER_TOOTH_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, TIGER_TOOTH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 16.0, TIGER_TOOTH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 17.0, TIGER_TOOTH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 15.5, TIGER_TOOTH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE.get(), 6.0, TIGER_TOOTH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 12.5, TIGER_TOOTH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), 0.18, TIGER_TOOTH_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), TIGER_TOOTH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), TIGER_TOOTH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), TIGER_TOOTH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), TIGER_TOOTH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE.get(), TIGER_TOOTH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), TIGER_TOOTH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), TIGER_TOOTH_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------

        register(KeyOfTheUnknown.KEY_OF_THE_UNKNOWN_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, KEY_UNKNOWN_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 4.0, KEY_UNKNOWN_UUID);
                    AttributeApplier.applyModifier(player, Attributes.KNOCKBACK_RESISTANCE, 0.05, KEY_UNKNOWN_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR, 2.0, KEY_UNKNOWN_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.REJUVENATION.get(), 6.0, KEY_UNKNOWN_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), KEY_UNKNOWN_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), KEY_UNKNOWN_UUID);
                    AttributeApplier.removeModifier(player, Attributes.KNOCKBACK_RESISTANCE, KEY_UNKNOWN_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR, KEY_UNKNOWN_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.REJUVENATION.get(), KEY_UNKNOWN_UUID);
                }
        );

        register(KeyOfTheUnknown.KEY_OF_THE_UNKNOWN_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, KEY_UNKNOWN_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 7.0, KEY_UNKNOWN_UUID);
                    AttributeApplier.applyModifier(player, Attributes.KNOCKBACK_RESISTANCE, 0.10, KEY_UNKNOWN_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR, 3.0, KEY_UNKNOWN_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.REJUVENATION.get(), 9.0, KEY_UNKNOWN_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), KEY_UNKNOWN_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), KEY_UNKNOWN_UUID);
                    AttributeApplier.removeModifier(player, Attributes.KNOCKBACK_RESISTANCE, KEY_UNKNOWN_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR, KEY_UNKNOWN_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.REJUVENATION.get(), KEY_UNKNOWN_UUID);
                }
        );

        register(KeyOfTheUnknown.KEY_OF_THE_UNKNOWN_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, KEY_UNKNOWN_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 8.0, KEY_UNKNOWN_UUID);
                    AttributeApplier.applyModifier(player, Attributes.KNOCKBACK_RESISTANCE, 0.15, KEY_UNKNOWN_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR, 4.0, KEY_UNKNOWN_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 4.0, KEY_UNKNOWN_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), 0.1, KEY_UNKNOWN_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 5.0, KEY_UNKNOWN_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.REJUVENATION.get(), 12.0, KEY_UNKNOWN_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), KEY_UNKNOWN_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), KEY_UNKNOWN_UUID);
                    AttributeApplier.removeModifier(player, Attributes.KNOCKBACK_RESISTANCE, KEY_UNKNOWN_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR, KEY_UNKNOWN_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), KEY_UNKNOWN_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), KEY_UNKNOWN_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), KEY_UNKNOWN_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.REJUVENATION.get(), KEY_UNKNOWN_UUID);
                }
        );

        register(KeyOfTheUnknown.KEY_OF_THE_UNKNOWN_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, KEY_UNKNOWN_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 9.0, KEY_UNKNOWN_UUID);
                    AttributeApplier.applyModifier(player, Attributes.KNOCKBACK_RESISTANCE, 0.15, KEY_UNKNOWN_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR, 5.0, KEY_UNKNOWN_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), 0.18, KEY_UNKNOWN_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 8.0, KEY_UNKNOWN_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), 12.0, KEY_UNKNOWN_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.REJUVENATION.get(), 15.0, KEY_UNKNOWN_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), KEY_UNKNOWN_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), KEY_UNKNOWN_UUID);
                    AttributeApplier.removeModifier(player, Attributes.KNOCKBACK_RESISTANCE, KEY_UNKNOWN_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR, KEY_UNKNOWN_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), KEY_UNKNOWN_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), KEY_UNKNOWN_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), KEY_UNKNOWN_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.REJUVENATION.get(), KEY_UNKNOWN_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------

        register(StolenLegacies.STOLEN_LEGACIES_CHOKER_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.XP_GAIN.get(), 0.15, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 6.0, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 6.0, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION.get(), 6.0, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), 6.0, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), 4.0, STOLEN_LEGACIES_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.XP_GAIN.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), STOLEN_LEGACIES_UUID);
                }
        );

        register(StolenLegacies.STOLEN_LEGACIES_CHOKER_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.XP_GAIN.get(), 0.20, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 10.0, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 10.0, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION.get(), 10.0, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), 10.0, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), 7.0, STOLEN_LEGACIES_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.XP_GAIN.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), STOLEN_LEGACIES_UUID);
                }
        );

        register(StolenLegacies.STOLEN_LEGACIES_CHOKER_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.XP_GAIN.get(), 0.25, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 16.0, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 12.0, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION.get(), 12.0, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), 12.0, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PRECISION_MULTIPLIER.get(), 0.1, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), 0.1, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get(), 0.1, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), 12.5, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.RESTORATION_MULTIPLIER.get(), 0.08, STOLEN_LEGACIES_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.XP_GAIN.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PRECISION_MULTIPLIER.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.RESTORATION_MULTIPLIER.get(), STOLEN_LEGACIES_UUID);
                }
        );

        register(StolenLegacies.STOLEN_LEGACIES_CHOKER_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.XP_GAIN.get(), 0.3, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 20.0, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 16.0, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION.get(), 16.0, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), 16.0, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PRECISION_MULTIPLIER.get(), 0.16, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), 0.16, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get(), 0.16, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), 15.0, STOLEN_LEGACIES_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.RESTORATION_MULTIPLIER.get(), 0.14, STOLEN_LEGACIES_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.XP_GAIN.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PRECISION_MULTIPLIER.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION_MULTIPLIER.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), STOLEN_LEGACIES_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.RESTORATION_MULTIPLIER.get(), STOLEN_LEGACIES_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------

        register(BandOfUnknown.BAND_OF_THE_UNKNOWN_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, UNKNOWN_BAND_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 5.0, UNKNOWN_BAND_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 5.0, UNKNOWN_BAND_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 3.0, UNKNOWN_BAND_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 3.0, UNKNOWN_BAND_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), UNKNOWN_BAND_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), UNKNOWN_BAND_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), UNKNOWN_BAND_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), UNKNOWN_BAND_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), UNKNOWN_BAND_UUID);
                }
        );

        register(BandOfUnknown.BAND_OF_THE_UNKNOWN_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, UNKNOWN_BAND_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 8.0, UNKNOWN_BAND_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 8.0, UNKNOWN_BAND_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 4.0, UNKNOWN_BAND_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 4.0, UNKNOWN_BAND_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), UNKNOWN_BAND_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), UNKNOWN_BAND_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), UNKNOWN_BAND_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), UNKNOWN_BAND_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), UNKNOWN_BAND_UUID);
                }
        );

        register(BandOfUnknown.BAND_OF_THE_UNKNOWN_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, UNKNOWN_BAND_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 10.0, UNKNOWN_BAND_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 10.0, UNKNOWN_BAND_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 5.0, UNKNOWN_BAND_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 5.0, UNKNOWN_BAND_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), UNKNOWN_BAND_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), UNKNOWN_BAND_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), UNKNOWN_BAND_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), UNKNOWN_BAND_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), UNKNOWN_BAND_UUID);
                }
        );

        register(BandOfUnknown.BAND_OF_THE_UNKNOWN_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, UNKNOWN_BAND_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 15.0, UNKNOWN_BAND_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 15.0, UNKNOWN_BAND_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 7.0, UNKNOWN_BAND_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 7.0, UNKNOWN_BAND_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), UNKNOWN_BAND_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), UNKNOWN_BAND_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), UNKNOWN_BAND_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), UNKNOWN_BAND_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), UNKNOWN_BAND_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------

        register(CoilOfWrath.COIL_OF_WRATH_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, COIL_OF_WRATH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 7.0, COIL_OF_WRATH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 8.0, COIL_OF_WRATH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION.get(), 8.0, COIL_OF_WRATH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), 8.0, COIL_OF_WRATH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), -3.0, COIL_OF_WRATH_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), COIL_OF_WRATH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), COIL_OF_WRATH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), COIL_OF_WRATH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION.get(), COIL_OF_WRATH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), COIL_OF_WRATH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), COIL_OF_WRATH_UUID);
                }
        );

        register(CoilOfWrath.COIL_OF_WRATH_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, COIL_OF_WRATH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 10.0, COIL_OF_WRATH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 13.0, COIL_OF_WRATH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION.get(), 13.0, COIL_OF_WRATH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), 13.0, COIL_OF_WRATH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), -3.0, COIL_OF_WRATH_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), COIL_OF_WRATH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), COIL_OF_WRATH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), COIL_OF_WRATH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION.get(), COIL_OF_WRATH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), COIL_OF_WRATH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), COIL_OF_WRATH_UUID);
                }
        );

        register(CoilOfWrath.COIL_OF_WRATH_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, COIL_OF_WRATH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 12.0, COIL_OF_WRATH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 17.0, COIL_OF_WRATH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION.get(), 17.0, COIL_OF_WRATH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), 17.0, COIL_OF_WRATH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), -3.0, COIL_OF_WRATH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE.get(), 8.0, COIL_OF_WRATH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 3, COIL_OF_WRATH_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), COIL_OF_WRATH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), COIL_OF_WRATH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), COIL_OF_WRATH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), COIL_OF_WRATH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE.get(), COIL_OF_WRATH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION.get(), COIL_OF_WRATH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), COIL_OF_WRATH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), COIL_OF_WRATH_UUID);
                }
        );

        register(CoilOfWrath.COIL_OF_WRATH_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, COIL_OF_WRATH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 14.0, COIL_OF_WRATH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PRECISION.get(), 22.0, COIL_OF_WRATH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_PRECISION.get(), 22.0, COIL_OF_WRATH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), 22.0, COIL_OF_WRATH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), -3.0, COIL_OF_WRATH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE.get(), 15.0, COIL_OF_WRATH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 7.0, COIL_OF_WRATH_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), COIL_OF_WRATH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), COIL_OF_WRATH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PRECISION.get(), COIL_OF_WRATH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), COIL_OF_WRATH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE.get(), COIL_OF_WRATH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_PRECISION.get(), COIL_OF_WRATH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_PRECISION.get(), COIL_OF_WRATH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), COIL_OF_WRATH_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------

        register(ColdCoil.COLDYS_COLD_COIL_OF_COLD_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, COLD_COIL_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MOVEMENT_SPEED, 0.009, COLD_COIL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 5.0, COLD_COIL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE.get(), 5.0, COLD_COIL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 4.0, COLD_COIL_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), COLD_COIL_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MOVEMENT_SPEED, COLD_COIL_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), COLD_COIL_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE.get(), COLD_COIL_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), COLD_COIL_UUID);
                }
        );

        register(ColdCoil.COLDYS_COLD_COIL_OF_COLD_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, COLD_COIL_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MOVEMENT_SPEED, 0.012, COLD_COIL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 7.5, COLD_COIL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE.get(), 8.0, COLD_COIL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 6.0, COLD_COIL_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), COLD_COIL_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MOVEMENT_SPEED, COLD_COIL_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), COLD_COIL_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE.get(), COLD_COIL_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), COLD_COIL_UUID);
                }
        );

        register(ColdCoil.COLDYS_COLD_COIL_OF_COLD_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, COLD_COIL_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MOVEMENT_SPEED, 0.015, COLD_COIL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 13.0, COLD_COIL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE.get(), 15.0, COLD_COIL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), 0.08, COLD_COIL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 9.0, COLD_COIL_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), COLD_COIL_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MOVEMENT_SPEED, COLD_COIL_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), COLD_COIL_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE.get(), COLD_COIL_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), COLD_COIL_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), COLD_COIL_UUID);
                }
        );

        register(ColdCoil.COLDYS_COLD_COIL_OF_COLD_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, COLD_COIL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), 0.08, COLD_COIL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 16.0, COLD_COIL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE.get(), 21.0, COLD_COIL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE_MULTIPLIER.get(), 0.08, COLD_COIL_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MOVEMENT_SPEED, 0.015, COLD_COIL_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 13.0, COLD_COIL_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), COLD_COIL_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE.get(), COLD_COIL_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE_MULTIPLIER.get(), COLD_COIL_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), COLD_COIL_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), COLD_COIL_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MOVEMENT_SPEED, COLD_COIL_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), COLD_COIL_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------

        register(CorruptedLostRing.CORRUPTED_RING_OF_THE_LOST_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, CORRUPTED_LOST_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 4.0, CORRUPTED_LOST_UUID);
                    AttributeApplier.applyModifier(player, Attributes.KNOCKBACK_RESISTANCE, 0.05, CORRUPTED_LOST_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR, 2.0, CORRUPTED_LOST_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), CORRUPTED_LOST_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), CORRUPTED_LOST_UUID);
                    AttributeApplier.removeModifier(player, Attributes.KNOCKBACK_RESISTANCE, CORRUPTED_LOST_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR, CORRUPTED_LOST_UUID);
                }
        );

        register(CorruptedLostRing.CORRUPTED_RING_OF_THE_LOST_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, CORRUPTED_LOST_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 5.0, CORRUPTED_LOST_UUID);
                    AttributeApplier.applyModifier(player, Attributes.KNOCKBACK_RESISTANCE, 0.1, CORRUPTED_LOST_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR, 3, CORRUPTED_LOST_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), CORRUPTED_LOST_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), CORRUPTED_LOST_UUID);
                    AttributeApplier.removeModifier(player, Attributes.KNOCKBACK_RESISTANCE, CORRUPTED_LOST_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR, CORRUPTED_LOST_UUID);
                }
        );

        register(CorruptedLostRing.CORRUPTED_RING_OF_THE_LOST_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, CORRUPTED_LOST_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 7.0, CORRUPTED_LOST_UUID);
                    AttributeApplier.applyModifier(player, Attributes.KNOCKBACK_RESISTANCE, 0.15, CORRUPTED_LOST_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR, 4.0, CORRUPTED_LOST_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 3.0, CORRUPTED_LOST_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), 0.08, CORRUPTED_LOST_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), CORRUPTED_LOST_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), CORRUPTED_LOST_UUID);
                    AttributeApplier.removeModifier(player, Attributes.KNOCKBACK_RESISTANCE, CORRUPTED_LOST_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR, CORRUPTED_LOST_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, CORRUPTED_LOST_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), CORRUPTED_LOST_UUID);
                }
        );

        register(CorruptedLostRing.CORRUPTED_RING_OF_THE_LOST_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, CORRUPTED_LOST_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 10.0, CORRUPTED_LOST_UUID);
                    AttributeApplier.applyModifier(player, Attributes.KNOCKBACK_RESISTANCE, 0.2, CORRUPTED_LOST_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR, 6.0, CORRUPTED_LOST_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 4.0, CORRUPTED_LOST_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), 0.15, CORRUPTED_LOST_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), CORRUPTED_LOST_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), CORRUPTED_LOST_UUID);
                    AttributeApplier.removeModifier(player, Attributes.KNOCKBACK_RESISTANCE, CORRUPTED_LOST_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR, CORRUPTED_LOST_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, CORRUPTED_LOST_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), CORRUPTED_LOST_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------

        register(DragonEyeEmbeddedRing.DRAGON_EYE_EMBEDDED_RING_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, DRAGON_EYE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 4.0, DRAGON_EYE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 7.0, DRAGON_EYE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 5.0, DRAGON_EYE_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGON_EYE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGON_EYE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), DRAGON_EYE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), DRAGON_EYE_UUID);
                }
        );

        register(DragonEyeEmbeddedRing.DRAGON_EYE_EMBEDDED_RING_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, DRAGON_EYE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 6.0, DRAGON_EYE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 12.0, DRAGON_EYE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 7.5, DRAGON_EYE_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGON_EYE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGON_EYE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), DRAGON_EYE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), DRAGON_EYE_UUID);
                }
        );

        register(DragonEyeEmbeddedRing.DRAGON_EYE_EMBEDDED_RING_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, DRAGON_EYE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 8.0, DRAGON_EYE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 6.0, DRAGON_EYE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 16.0, DRAGON_EYE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 9.0, DRAGON_EYE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), 0.06, DRAGON_EYE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), 0.04, DRAGON_EYE_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGON_EYE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGON_EYE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), DRAGON_EYE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), DRAGON_EYE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), DRAGON_EYE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), DRAGON_EYE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), DRAGON_EYE_UUID);
                }
        );

        register(DragonEyeEmbeddedRing.DRAGON_EYE_EMBEDDED_RING_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, DRAGON_EYE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 10.0, DRAGON_EYE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 10.0, DRAGON_EYE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 20.0, DRAGON_EYE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 12.5, DRAGON_EYE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), 0.12, DRAGON_EYE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), 0.08, DRAGON_EYE_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), DRAGON_EYE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), DRAGON_EYE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), DRAGON_EYE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), DRAGON_EYE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), DRAGON_EYE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), DRAGON_EYE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), DRAGON_EYE_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------

        register(GluttonySignet.GLUTTONY_SIGNET_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 1.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 1.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MOVEMENT_SPEED, -0.015, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), -20.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 1.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR, 0.5, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 0.5, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 0.2, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.POTENCY.get(), 2.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 2.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 2.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.REJUVENATION.get(), 2.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), -2.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE.get(), 2.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 2.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 2.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 1.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 1.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 1.0, GLUTTONY_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MOVEMENT_SPEED, GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR, GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.POTENCY.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.REJUVENATION.get(), GLUTTONY_UUID);
                }
        );

        register(GluttonySignet.GLUTTONY_SIGNET_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 2.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 2.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MOVEMENT_SPEED, -0.015, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), -20.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 2.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR, 1.5, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 1.5, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 0.4, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.POTENCY.get(), 2.5, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 2.5, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 2.5, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE.get(), 2.5, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 2.5, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 2.5, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.REJUVENATION.get(), 2.5, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), -2.5, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 2.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 2.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 2.0, GLUTTONY_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MOVEMENT_SPEED, GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR, GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.POTENCY.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.REJUVENATION.get(), GLUTTONY_UUID);
                }
        );

        register(GluttonySignet.GLUTTONY_SIGNET_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 3.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 3.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MOVEMENT_SPEED, -0.015, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), -20.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 3.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR, 1.5, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 1.5, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 0.6, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE.get(), 3.25, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 3.25, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 3.25, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.POTENCY.get(), 3.25, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 3.25, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 3.25, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 3.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 3.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 3.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), 0.02, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), 0.02, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE_MULTIPLIER.get(), 0.03, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.POTENCY_MULTIPLIER.get(), 0.03, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), 0.03, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), 0.03, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), 0.03, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), 0.03, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.REJUVENATION.get(), 3.25, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), -3.25, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.REJUVENATION_MULTIPLIER.get(), 0.03, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.RESTORATION_MULTIPLIER.get(), -0.03, GLUTTONY_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MOVEMENT_SPEED, GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR, GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.POTENCY.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.POTENCY_MULTIPLIER.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE_MULTIPLIER.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.REJUVENATION.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.REJUVENATION_MULTIPLIER.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.RESTORATION_MULTIPLIER.get(), GLUTTONY_UUID);
                }
        );

        register(GluttonySignet.GLUTTONY_SIGNET_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.STR.get(), 4.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 4.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MOVEMENT_SPEED, -0.015, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEBUFF_RESIST.get(), -20.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.FORT.get(), 4.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR, 2, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, Attributes.ARMOR_TOUGHNESS, 2, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, Attributes.MAX_HEALTH, 0.8, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.POTENCY.get(), 4.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY.get(), 4.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), 4.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE.get(), 4.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.HASTE.get(), 4.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 4.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 4.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 4.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.WISDOM.get(), 4.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), 0.04, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), 0.04, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_HASTE_MULTIPLIER.get(), 0.06, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), 0.06, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.POTENCY_MULTIPLIER.get(), 0.06, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), 0.06, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), 0.06, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), 0.06, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.REJUVENATION.get(), 4.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.RESTORATION.get(), -4.0, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.REJUVENATION_MULTIPLIER.get(), 0.04, GLUTTONY_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.RESTORATION_MULTIPLIER.get(), -0.04, GLUTTONY_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.STR.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MOVEMENT_SPEED, GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEBUFF_RESIST.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.FORT.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR, GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, Attributes.MAX_HEALTH, GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, Attributes.ARMOR_TOUGHNESS, GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.POTENCY.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.HASTE.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.HASTE_MULTIPLIER.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.WISDOM.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.ARMOR_MULTIPLIER.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.TOUGHNESS_MULTIPLIER.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_HASTE_MULTIPLIER.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.POTENCY_MULTIPLIER.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_POTENCY_MULTIPLIER.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE_MULTIPLIER.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.REJUVENATION.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.REJUVENATION_MULTIPLIER.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.RESTORATION.get(), GLUTTONY_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.RESTORATION_MULTIPLIER.get(), GLUTTONY_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------

        register(SunstoneForged.SUNSTONE_FORGED_RING_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, SUNSTONE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 2.0, SUNSTONE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 4.0, SUNSTONE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.ACCURACY.get(), 8.0, SUNSTONE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_ACCURACY.get(), 8.0, SUNSTONE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), 8.0, SUNSTONE_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SUNSTONE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), SUNSTONE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), SUNSTONE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.ACCURACY.get(), SUNSTONE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_ACCURACY.get(), SUNSTONE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), SUNSTONE_UUID);
                }
        );

        register(SunstoneForged.SUNSTONE_FORGED_RING_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, SUNSTONE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 3.0, SUNSTONE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 7.0, SUNSTONE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.ACCURACY.get(), 14.0, SUNSTONE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_ACCURACY.get(), 14.0, SUNSTONE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), 14.0, SUNSTONE_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SUNSTONE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), SUNSTONE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), SUNSTONE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.ACCURACY.get(), SUNSTONE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_ACCURACY.get(), SUNSTONE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), SUNSTONE_UUID);
                }
        );

        register(SunstoneForged.SUNSTONE_FORGED_RING_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, SUNSTONE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 4.0, SUNSTONE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 10.0, SUNSTONE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.ACCURACY.get(), 16.0, SUNSTONE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_ACCURACY.get(), 16.0, SUNSTONE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), 16.0, SUNSTONE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 8.0, SUNSTONE_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SUNSTONE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), SUNSTONE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), SUNSTONE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.ACCURACY.get(), SUNSTONE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_ACCURACY.get(), SUNSTONE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), SUNSTONE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), SUNSTONE_UUID);
                }
        );

        register(SunstoneForged.SUNSTONE_FORGED_RING_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, SUNSTONE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 6.0, SUNSTONE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PERC.get(), 13.0, SUNSTONE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.ACCURACY.get(), 21.0, SUNSTONE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MELEE_ACCURACY.get(), 21.0, SUNSTONE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), 21.0, SUNSTONE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.NOCK_HASTE.get(), 12.0, SUNSTONE_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SUNSTONE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), SUNSTONE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PERC.get(), SUNSTONE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.ACCURACY.get(), SUNSTONE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MELEE_ACCURACY.get(), SUNSTONE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.PROJECTILE_ACCURACY.get(), SUNSTONE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.NOCK_HASTE.get(), SUNSTONE_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------

        // Cloudtreader Boots
        register(UtilityAccessories.CLOUDTREADER_BOOTS.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, CLOUDTREADER_UUID);
                    AttributeApplier.applyModifier(player, ForgeMod.STEP_HEIGHT_ADDITION.get(), 1.0, CLOUDTREADER_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), CLOUDTREADER_UUID);
                    AttributeApplier.removeModifier(player, ForgeMod.STEP_HEIGHT_ADDITION.get(), CLOUDTREADER_UUID);
                }
        );

// Moni's Lucky Charm
        register(UtilityAccessories.MONIS_LUCKY_CHARM.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, MONIS_LUCKY_UUID);
                    AttributeApplier.applyModifier(player, Attributes.LUCK, 2.0, MONIS_LUCKY_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), MONIS_LUCKY_UUID);
                    AttributeApplier.removeModifier(player, Attributes.LUCK, MONIS_LUCKY_UUID);
                }
        );

// Enderman's Fingers
        register(UtilityAccessories.ENDERMAN_FINGERS.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, ENDERMAN_FINGERS_UUID);
                    AttributeApplier.applyModifier(player, ForgeMod.BLOCK_REACH.get(), 1.5, ENDERMAN_FINGERS_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), ENDERMAN_FINGERS_UUID);
                    AttributeApplier.removeModifier(player, ForgeMod.BLOCK_REACH.get(), ENDERMAN_FINGERS_UUID);
                }
        );

// Antique Pocket Watch
        register(UtilityAccessories.ANTIQUE_POCKET_WATCH.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, ANTIQUE_WATCH_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.XP_GAIN.get(), 0.3, ANTIQUE_WATCH_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), ANTIQUE_WATCH_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.XP_GAIN.get(), ANTIQUE_WATCH_UUID);
                }
        );

// Reinforced Diamond Plating
        register(UtilityAccessories.REINFORCED_DIAMOND_PLATING.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, REINFORCED_DIAMOND_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.MINING_SPEED.get(), 0.3, REINFORCED_DIAMOND_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), REINFORCED_DIAMOND_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.MINING_SPEED.get(), REINFORCED_DIAMOND_UUID);
                }
        );

// Cloudspire Gem
        register(UtilityAccessories.CLOUDSPIRE_GEM.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, CLOUDSPIRE_GEM_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.JUMP_BOOST.get(), 1.0, CLOUDSPIRE_GEM_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), CLOUDSPIRE_GEM_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.JUMP_BOOST.get(), CLOUDSPIRE_GEM_UUID);
                }
        );

        // --------------------------------------------------------------------------------------------


        register(PendantOfSnowflake.PENDANT_OF_FLOATING_SNOWFLAKE_RARE.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 11.0, SNOWFLAKE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 1.0, SNOWFLAKE_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), SNOWFLAKE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SNOWFLAKE_UUID);
                }
        );

        register(PendantOfSnowflake.PENDANT_OF_FLOATING_SNOWFLAKE_EPIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 17.0, SNOWFLAKE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, SNOWFLAKE_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), SNOWFLAKE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SNOWFLAKE_UUID);
                }
        );

        register(PendantOfSnowflake.PENDANT_OF_FLOATING_SNOWFLAKE_LEGENDARY.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 24.0, SNOWFLAKE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 3.0, SNOWFLAKE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 2.0, SNOWFLAKE_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), SNOWFLAKE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SNOWFLAKE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), SNOWFLAKE_UUID);
                }
        );

        register(PendantOfSnowflake.PENDANT_OF_FLOATING_SNOWFLAKE_MYTHIC.get(),
                player -> {
                    AttributeApplier.applyModifier(player, ModAttributes.CON.get(), 32.0, SNOWFLAKE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 4.0, SNOWFLAKE_UUID);
                    AttributeApplier.applyModifier(player, ModAttributes.DEX.get(), 5.0, SNOWFLAKE_UUID);
                },
                player -> {
                    AttributeApplier.removeModifier(player, ModAttributes.CON.get(), SNOWFLAKE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SNOWFLAKE_UUID);
                    AttributeApplier.removeModifier(player, ModAttributes.DEX.get(), SNOWFLAKE_UUID);
                }
        );

        register(ModItems.FAIRY_TEARDROP.get(),
                player -> {
                    player.getPersistentData().putBoolean("regrowth_eligible", true);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, FAIRY_INSIGHT_UUID);
                },
                player -> {
                    player.getPersistentData().remove("regrowth_eligible");
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), FAIRY_INSIGHT_UUID);
                }
        );

        register(ModItems.HOOK_OF_THE_DEPTHS.get(),
                player -> {
                    player.getPersistentData().putBoolean("grapple_eligible", true);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, HOOK_INSIGHT_UUID);
                },
                player -> {
                    player.getPersistentData().remove("grapple_eligible");
                    // Clear grapple state data
                    player.getPersistentData().remove("grapple_target_x");
                    player.getPersistentData().remove("grapple_target_y");
                    player.getPersistentData().remove("grapple_target_z");
                    player.getPersistentData().remove("is_grappling");
                    player.getPersistentData().remove("last_y");
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), HOOK_INSIGHT_UUID);
                }
        );

        register(ModItems.HELLFORGED_PLATING.get(),
                player -> {
                    player.getPersistentData().putBoolean("smelt_eligible", true);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, HELLFORGED_INSIGHT_UUID);
                },
                player -> {
                    player.getPersistentData().remove("smelt_eligible");
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), HELLFORGED_INSIGHT_UUID);
                }
        );

        register(ModItems.FLAMEFORGED_PLATING.get(),
                player -> {
                    player.getPersistentData().putBoolean("smelt2_eligible", true);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, SELECTIVE_INSIGHT_UUID);
                },
                player -> {
                    player.getPersistentData().remove("smelt2_eligible");
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), SELECTIVE_INSIGHT_UUID);
                }
        );

        register(ModItems.BOTTLED_LIGHT.get(),
                player -> {
                    player.getPersistentData().putBoolean("lightbringer_applied", true);
                    AttributeApplier.applyModifier(player, ModAttributes.INSIGHT.get(), 2.0, BOTTLED_INSIGHT_UUID);
                },
                player -> {
                    player.getPersistentData().remove("lightbringer_applied");
                    AttributeApplier.removeModifier(player, ModAttributes.INSIGHT.get(), BOTTLED_INSIGHT_UUID);
                }
        );

    }

    public static void register(Item item, Consumer<Player> onApply, Consumer<Player> onRemove) {
        ON_APPLY_ACC.put(item, onApply);
        ON_REMOVE_ACC.put(item, onRemove);
    }
}