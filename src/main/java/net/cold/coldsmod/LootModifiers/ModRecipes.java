package net.cold.coldsmod.LootModifiers;

import net.cold.coldsmod.ColdsMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ColdsMod.MODID);

    public static void register(IEventBus bus) {
        SERIALIZERS.register(bus);
    }
}



