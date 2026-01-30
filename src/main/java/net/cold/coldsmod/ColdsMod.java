package net.cold.coldsmod;

import com.mojang.logging.LogUtils;
import net.cold.coldsmod.LootModifiers.ModLootModifiers;
import net.cold.coldsmod.accessory.UtilityAccessories;
import net.cold.coldsmod.accessory.bracers.*;
import net.cold.coldsmod.accessory.mind.*;
import net.cold.coldsmod.accessory.necklace.*;
import net.cold.coldsmod.accessory.ring.*;
import net.cold.coldsmod.blessingbonuses.BowProcHandler;
import net.cold.coldsmod.blessingbonuses.CooldownCycle;
import net.cold.coldsmod.blessingbonuses.CrossbowProcHandler;
import net.cold.coldsmod.blessingbonuses.JumpCritHandler;
import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.blessingbonuses.effects.PickaxeTorch;
import net.cold.coldsmod.blessingbonuses.effects.SoulSeveranceActive;
import net.cold.coldsmod.blessingbonuses.neweffects.*;
import net.cold.coldsmod.blessingbonuses.skills.*;
import net.cold.coldsmod.bow.BowAnimHandler;
import net.cold.coldsmod.formulas.DebuffResistHandler;
import net.cold.coldsmod.item.ModItems;
import net.cold.coldsmod.mob.SbeveRenderer;
import net.cold.coldsmod.network.NetworkHandler;
import net.cold.coldsmod.stat.*;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.slf4j.Logger;
import top.theillusivec4.curios.api.SlotTypeMessage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@Mod(ColdsMod.MODID)
public class ColdsMod {
    public static final String MODID = "coldsmod";
    public static final Logger LOGGER = LogUtils.getLogger();


    public ColdsMod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        copyDefaultConfig("melee_weapons.json");
        copyDefaultConfig("bows.json");
        copyDefaultConfig("crossbows.json");
        copyDefaultConfig("shields.json");
        copyDefaultConfig("tools.json");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigs.SPEC, "coldsmod-drop_rates.toml");


        ModAttributes.ATTRIBUTES.register(modEventBus);
        ModItems.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(new ItemModifier());
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::enqueueIMC);
        ModLootModifiers.register();
        MinecraftForge.EVENT_BUS.register(new DebuffResistHandler());
        NetworkHandler.register();
        ModEffects.EFFECTS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(DeathFromAboveSkill.class);
        MinecraftForge.EVENT_BUS.register(IntoTheFraySkill.class);
        MinecraftForge.EVENT_BUS.register(IntimidatingPresenceSkill.class);
        MinecraftForge.EVENT_BUS.register(DaringShoutSkill.class);
        MinecraftForge.EVENT_BUS.register(ReckoningSkill.class);
        MinecraftForge.EVENT_BUS.register(BerserkAdditionalProcs.class);
        MinecraftForge.EVENT_BUS.register(ChainLightning.class);
        MinecraftForge.EVENT_BUS.register(BronzewoodApply.class);
        MinecraftForge.EVENT_BUS.register(ClairvoyanceSkill.class);
        MinecraftForge.EVENT_BUS.register(ExploitWeaknessApply.class);
        MinecraftForge.EVENT_BUS.register(RetaliateActive.class);
        MinecraftForge.EVENT_BUS.register(PickaxeTorch.class);
        MinecraftForge.EVENT_BUS.register(BastionActive.class);
        MinecraftForge.EVENT_BUS.register(DeceptionSkill.class);
        MinecraftForge.EVENT_BUS.register(SoulSeveranceActive.class);
        MinecraftForge.EVENT_BUS.register(Vitalization.class);
        MinecraftForge.EVENT_BUS.register(ExplosiveTendencies.class);
        MinecraftForge.EVENT_BUS.register(CombatantsAidReady.class);
        MinecraftForge.EVENT_BUS.register(EntwinedOfferingActive.class);
        MinecraftForge.EVENT_BUS.register(PulsatingLove.class);
        MinecraftForge.EVENT_BUS.register(RadiatingWarmthEffect.class);
        MinecraftForge.EVENT_BUS.register(Sanctuary.class);
        MinecraftForge.EVENT_BUS.register(SummoningStone.class);
        MinecraftForge.EVENT_BUS.register(VortexReady.class);
        MinecraftForge.EVENT_BUS.register(BlackenedHeart.class);
        MinecraftForge.EVENT_BUS.register(StatUtils.class);
        MinecraftForge.EVENT_BUS.register(EffectUtils.class);
        MinecraftForge.EVENT_BUS.register(FocusedEnergyReady.class);
        MinecraftForge.EVENT_BUS.register(ThornedParryReady.class);
        MinecraftForge.EVENT_BUS.register(BlessedLandReady.class);
        MinecraftForge.EVENT_BUS.register(Rod.class);
        MinecraftForge.EVENT_BUS.register(Regrowth.class);
        MinecraftForge.EVENT_BUS.register(Flameheart.class);
        MinecraftForge.EVENT_BUS.register(ForgedHeart.class);


        MinecraftForge.EVENT_BUS.register(BowProcHandler.class);
        MinecraftForge.EVENT_BUS.register(CrossbowProcHandler.class);
        MinecraftForge.EVENT_BUS.register(JumpCritHandler.class);



        MinecraftForge.EVENT_BUS.register(CrossbowChargeDrawSpeedTag.class);

        MinecraftForge.EVENT_BUS.register(new Formulas());


        BraceletOfPride.register(modEventBus);
        DragonClaw.register(modEventBus);
        Enderman.register(modEventBus);
        FingersOfLust.register(modEventBus);
        ReinforcedSteel.register(modEventBus);
        SerpentSkin.register(modEventBus);
        ThieveryWraps.register(modEventBus);
        WardenSkin.register(modEventBus);

        DragonRoar.register(modEventBus);
        EndlessWaves.register(modEventBus);
        Shrieks.register(modEventBus);
        SunsGaze.register(modEventBus);
        Tear.register(modEventBus);
        TemptingWhispers.register(modEventBus);

        BottledTsunami.register(modEventBus);
        DragonTeethNecklace.register(modEventBus);
        EnvyCollar.register(modEventBus);
        HangingTigerTooth.register(modEventBus);
        KeyOfTheUnknown.register(modEventBus);
        PendantOfSnowflake.register(modEventBus);
        StolenLegacies.register(modEventBus);

        BandOfUnknown.register(modEventBus);
        CoilOfWrath.register(modEventBus);
        ColdCoil.register(modEventBus);
        CorruptedLostRing.register(modEventBus);
        DragonEyeEmbeddedRing.register(modEventBus);
        GluttonySignet.register(modEventBus);
        SunstoneForged.register(modEventBus);

        UtilityAccessories.register(modEventBus);
        net.cold.coldsmod.accessory.TestItems.register(modEventBus);


        MinecraftForge.EVENT_BUS.register(BowAnimHandler.class);

        CreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);

        ModSounds.SOUND_EVENTS.register(modEventBus);
        modEventBus.addListener(ModAttributes::onModifyEntityAttributes);
        ModEntities.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");

        event.enqueueWork(ItemRarityUtils::init);

        CooldownCycle.init();
        MinecraftForge.EVENT_BUS.register(ModAttributes.class);
    }

    private void copyDefaultConfig(String fileName) {
        File target = new File(FMLPaths.CONFIGDIR.get().toFile(), "coldsmod/" + fileName);

        if (!target.exists()) {
            target.getParentFile().mkdirs();
            try (InputStream in = getClass().getResourceAsStream("/default_config/coldsmod/" + fileName)) {
                if (in != null) {
                    Files.copy(in, target.toPath());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }

        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(ModEntities.SBEVE.get(), SbeveRenderer::new);
        }
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("coldsmod")
                .then(Commands.literal("reload")
                        .requires(source -> source.hasPermission(2))
                        .executes(context -> {
                            ItemRarityUtils.init();
                            context.getSource().sendSuccess(
                                    () -> Component.literal("§6Weapon tags have been reloaded"),
                                    true
                            );
                            return 1;
                        })
                )
        );
    }

    public void enqueueIMC(final InterModEnqueueEvent event) {

        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> new SlotTypeMessage.Builder("aaaheads")
                        .size(2)
                        .icon(new ResourceLocation("coldsmod", "item/head_slot"))
                        .build()
        );

        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> new SlotTypeMessage.Builder("aabnecklaces")
                        .size(1)
                        .icon(new ResourceLocation("coldsmod", "item/necklace_slot"))
                        .build()
        );

        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> new SlotTypeMessage.Builder("aacbracelets")
                        .size(2)
                        .icon(new ResourceLocation("coldsmod", "item/bracer_slot"))
                        .build()
        );

        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> new SlotTypeMessage.Builder("aadrings")
                        .size(2)
                        .icon(new ResourceLocation("coldsmod", "item/ring_slot"))
                        .build()
        );

        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> new SlotTypeMessage.Builder("aaeutility")
                        .size(5)
                        .icon(new ResourceLocation("coldsmod", "item/utility_slot"))
                        .build()
        );

        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> new SlotTypeMessage.Builder("aafblessingcombat")
                        .size(4)
                        .icon(new ResourceLocation("coldsmod", "item/slot1"))
                        .build()
        );

        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> new SlotTypeMessage.Builder("aafblessingpresence")
                        .size(1)
                        .icon(new ResourceLocation("coldsmod", "item/slot10"))
                        .build()
        );

        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> new SlotTypeMessage.Builder("aajblessingsword")
                        .size(1)
                        .icon(new ResourceLocation("coldsmod", "item/slot5"))
                        .build()
        );
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> new SlotTypeMessage.Builder("aakblessingbow")
                        .size(1)
                        .icon(new ResourceLocation("coldsmod", "item/slot6"))
                        .build()
        );
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> new SlotTypeMessage.Builder("aalblessingcrossbow")
                        .size(1)
                        .icon(new ResourceLocation("coldsmod", "item/slot7"))
                        .build()
        );
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> new SlotTypeMessage.Builder("aamblessingshield")
                        .size(1)
                        .icon(new ResourceLocation("coldsmod", "item/slot8"))
                        .build()
        );

        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> new SlotTypeMessage.Builder("aanblessingtool")
                        .size(3)
                        .icon(new ResourceLocation("coldsmod", "item/slot9"))
                        .build()
        );
    }
}
