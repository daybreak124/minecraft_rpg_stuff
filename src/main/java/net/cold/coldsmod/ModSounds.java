package net.cold.coldsmod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "coldsmod");

    public static final RegistryObject<SoundEvent> DARING_SHOUT = registerSound("daring_shout");
    public static final RegistryObject<SoundEvent> INTIMIDATING_PRESENCE = registerSound("intimidating_presence");
    public static final RegistryObject<SoundEvent> RETALIATE = registerSound("retaliate");
    public static final RegistryObject<SoundEvent> CLAIRVOYANCE = registerSound("clairvoyance");
    public static final RegistryObject<SoundEvent> EXPLOIT_WEAKNESS = registerSound("exploit_weakness");

    private static RegistryObject<SoundEvent> registerSound(String name) {
        return SOUND_EVENTS.register(name,
                () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("coldsmod", name))
        );
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
