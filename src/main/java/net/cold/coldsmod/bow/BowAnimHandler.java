package net.cold.coldsmod.bow;

import net.cold.coldsmod.stat.ItemRarityUtils;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BowAnimHandler {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {

        event.enqueueWork(() -> {
            for (Item item : ForgeRegistries.ITEMS) {
                ItemStack stack = new ItemStack(item);
                if ("bow".equals(ItemRarityUtils.getItemType(stack))) {
                    ItemProperties.register(item, new ResourceLocation("pull"), (stack2, level, entity, seed) -> {
                        if (!(entity instanceof Player player)) return 0.0F;
                        if (entity.getUseItem().getItem() != item) return 0.0F;

                        float drawSpeed = (float) player.getPersistentData().getDouble("drawSpeedIncrease");
                        float speedMultiplier = 1.0F + (drawSpeed / 100.0F);
                        if (speedMultiplier < 0.01F) speedMultiplier = 0.01F;

                        return (entity.getUseItem().getUseDuration() - entity.getUseItemRemainingTicks()) / (20.0F / speedMultiplier);
                    });

                    ItemProperties.register(item, new ResourceLocation("pulling"), (stack2, level, entity, seed) ->
                            entity != null && entity.isUsingItem() && entity.getUseItem().getItem() == item ? 1.0F : 0.0F
                    );
                }
            }
        });
    }
}
