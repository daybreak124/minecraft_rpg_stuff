package net.cold.coldsmod.mob;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SbeveRenderer extends MobRenderer<Sbeve, PlayerModel<Sbeve>> {

    private static final ResourceLocation STEVE_LOCATION = new ResourceLocation("minecraft", "textures/entity/player/wide/steve.png");

    public SbeveRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new PlayerModel<>(ctx.bakeLayer(ModelLayers.PLAYER), false), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Sbeve entity) {
        return STEVE_LOCATION;
    }

    @Override
    protected void scale(Sbeve entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(1.5f, 0.75f, 1.5f);
    }

}
