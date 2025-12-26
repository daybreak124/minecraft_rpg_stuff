package net.cold.coldsmod;

import com.mojang.logging.LogUtils;
import net.cold.coldsmod.LootModifiers.ModLootModifiers;
import net.cold.coldsmod.accessory.*;
import net.cold.coldsmod.bow.BowAnimHandler;
import net.cold.coldsmod.damage.CustomMeleeDamage;
import net.cold.coldsmod.damage.CustomMeleeDamageNoProcs;
import net.cold.coldsmod.formulas.DebuffResistHandler;
import net.cold.coldsmod.gearbonuses.effects.ModEffects;
import net.cold.coldsmod.gearbonuses.effects.PickaxeTorch;
import net.cold.coldsmod.gearbonuses.effects.SoulSeveranceActive;
import net.cold.coldsmod.gearbonuses.skills.*;
import net.cold.coldsmod.item.ModItems;
import net.cold.coldsmod.network.NetworkHandler;
import net.cold.coldsmod.stat.*;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
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

        ModItems.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(new ItemModifier());
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::enqueueIMC);
        ModLootModifiers.register();
        MinecraftForge.EVENT_BUS.register(new AttributeApplier());
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
        MinecraftForge.EVENT_BUS.register(DirectedHatredSkill.class);
        MinecraftForge.EVENT_BUS.register(SoulSeveranceActive.class);
        MinecraftForge.EVENT_BUS.register(Vitalization.class);
        MinecraftForge.EVENT_BUS.register(ExplosiveTendencies.class);

        MinecraftForge.EVENT_BUS.register(StatUtils.class);

        MinecraftForge.EVENT_BUS.register(CustomMeleeDamageNoProcs.class);
        MinecraftForge.EVENT_BUS.register(CustomMeleeDamage.class);

        MinecraftForge.EVENT_BUS.register(CrossbowChargeDrawSpeedTag.class);


        RingAccessories.register(modEventBus);
        BracerAccessories.register(modEventBus);
        HeadAccessories.register(modEventBus);
        NecklaceAccessories.register(modEventBus);
        UtilityAccessories.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(BowAnimHandler.class);

        CreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);

        ModSounds.SOUND_EVENTS.register(modEventBus);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        copyDefaultConfig("melee_weapons.json");
        copyDefaultConfig("bows.json");
        copyDefaultConfig("crossbows.json");
        copyDefaultConfig("shields.json");
        copyDefaultConfig("tools.json");
        LOGGER.info("HELLO FROM COMMON SETUP");

        ItemRarityUtils.init();
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


    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
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
                () -> new SlotTypeMessage.Builder("aafblessinghelm")
                        .size(1)
                        .icon(new ResourceLocation("coldsmod", "item/slot1"))
                        .build()
        );

        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> new SlotTypeMessage.Builder("aagblessingchest")
                        .size(1)
                        .icon(new ResourceLocation("coldsmod", "item/slot2"))
                        .build()
        );

        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> new SlotTypeMessage.Builder("aahblessinglegs")
                        .size(1)
                        .icon(new ResourceLocation("coldsmod", "item/slot3"))
                        .build()
        );

        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> new SlotTypeMessage.Builder("aaiblessingfeet")
                        .size(1)
                        .icon(new ResourceLocation("coldsmod", "item/slot4"))
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
                () -> new SlotTypeMessage.Builder("aaoblessingstatus")
                        .size(1)
                        .icon(new ResourceLocation("coldsmod", "item/slot10"))
                        .build()
        );

        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> new SlotTypeMessage.Builder("aanblessingtool")
                        .size(1)
                        .icon(new ResourceLocation("coldsmod", "item/slot9"))
                        .build()
        );
    }
}
