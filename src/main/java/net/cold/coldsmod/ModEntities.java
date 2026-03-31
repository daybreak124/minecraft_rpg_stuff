package net.cold.coldsmod;

import net.cold.coldsmod.mob.Sbeve;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ColdsMod.MODID);

    public static final RegistryObject<EntityType<Sbeve>> SBEVE =
            ENTITIES.register("sbeve",
                    () -> EntityType.Builder
                            .of(Sbeve::new, MobCategory.MISC)
                            .sized(0.75f, 0.75f)
                            .build("sbeve")
            );

    public static void register(IEventBus bus) {
        ENTITIES.register(bus);
    }
}
