package net.cold.coldsmod.custom_attacks;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderArmEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderItemInFrameEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "coldsmod", value = Dist.CLIENT)
public class AttackAnimHandler {
    public enum AnimationType { NONE, POKE, JUMP_ATTACK, CLEAVE }

    private static AnimationType currentAnim = AnimationType.NONE;
    private static long startTime = 0;
    private static long durationMs = 300;

    public static void play(AnimationType type) {
        currentAnim = type;
        startTime = System.currentTimeMillis();
    }

    public static float getProgress() {
        if (currentAnim == AnimationType.NONE) return 0;
        long elapsed = System.currentTimeMillis() - startTime;
        float progress = (float) elapsed / durationMs;

        if (progress >= 1.0f) {
            currentAnim = AnimationType.NONE;
            return 0;
        }
        return progress;
    }

    public static AnimationType getCurrentAnim() { return currentAnim; }

//    @SubscribeEvent
//    public static void onRenderHand(RenderHandEvent event) {
//        AnimationType anim = getCurrentAnim();
//        if (anim == AnimationType.NONE) return;
//
//        InteractionHand activeSwingHand = InteractionHand.MAIN_HAND;
//
//        if (event.getHand() != activeSwingHand) return;
//
//        float p = getProgress();
//        PoseStack pose = event.getPoseStack();
//        Minecraft mc = Minecraft.getInstance();
//
//        pose.pushPose();
//
//        boolean isLeftHand = event.getHand() == InteractionHand.OFF_HAND;
//
//        float globalXShift = 0.7f;
//        float globalYShift = -0.9f;
//        float globalZShift = 0.0f;
//
//        if (isLeftHand) {
//            pose.translate(-globalXShift, globalYShift, globalZShift);
//        } else {
//            pose.translate(globalXShift, globalYShift, globalZShift);
//        }
//
//        switch (anim) {
//            case POKE -> {
//                float tx, ty, tz;
//                if (p < 0.35f) {
//                    tx = interpolate(p, 0f, 0.2f, 0f, -0.4f);
//                    ty = interpolate(p, 0f, 0.2f, 0f, 0.5f);
//                    tz = interpolate(p, 0f, 0.2f, 0f, -1.5f);
//                } else if (p < 0.8f) {
//                    tx = -0.4f; ty = 0.5f; tz = -1.5f;
//                } else {
//                    tx = interpolate(p, 0.8f, 1.0f, -0.4f, 0f);
//                    ty = interpolate(p, 0.8f, 1.0f, 0.5f, 0f);
//                    tz = interpolate(p, 0.8f, 1.0f, -1.5f, 0f);
//                }
//
//                float rx = interpolate(p, 0f, 0.2f, 0f, -75f);
//                float ry = interpolate(p, 0f, 0.2f, 0f, -15f);
//                if (p > 0.8f) {
//                    rx = interpolate(p, 0.8f, 1.0f, -75f, 0f);
//                    ry = interpolate(p, 0.8f, 1.0f, -15f, 0f);
//                } else if (p > 0.2f) {
//                    rx = -75f; ry = -15f;
//                }
//
//                // =========================================================================
//                // POSITION ADJUSTMENT OFFSETS
//                // =========================================================================
//                float rightShiftX = 0f; // Increase this to push the right hand further right
//                float closerZ = 0.4f;     // Increase this to pull the weapon closer to your face
//
//                if (isLeftHand) {
//                    // Mirrored offsets for left hand context (shifts left and brings closer)
//                    pose.translate(-tx - rightShiftX, ty, tz + closerZ);
//                    pose.mulPose(Axis.XP.rotationDegrees(rx));
//                    pose.mulPose(Axis.YP.rotationDegrees(-ry));
//                } else {
//                    // Right hand context: Adds rightShiftX to move right, adds closerZ to bring closer
//                    pose.translate(tx + rightShiftX, ty, tz + closerZ);
//                    pose.mulPose(Axis.XP.rotationDegrees(rx));
//                    pose.mulPose(Axis.YP.rotationDegrees(ry));
//                }
//            }
//
//            case CLEAVE -> {
//                float slowP = p * 0.45f;
//                float tx = (slowP < 0.5f) ? interpolate(slowP, 0f, 0.5f, 16.0f, -72.0f) : -72.0f;
//                float ty = (slowP < 0.5f) ? interpolate(slowP, 0f, 0.5f, 0.6f, -0.5f) : -0.2f;
//                float ry = (slowP < 0.5f) ? interpolate(slowP, 0f, 0.5f, -50f, 185f) : 185f;
//                float rx = -45f;
//
//                if (isLeftHand) {
//                    pose.translate(-(tx / 16f), ty, -0.8f);
//                    pose.mulPose(Axis.YP.rotationDegrees(-ry));
//                    pose.mulPose(Axis.XP.rotationDegrees(rx));
//                } else {
//                    pose.translate(tx / 16f, ty, -0.8f);
//                    pose.mulPose(Axis.YP.rotationDegrees(ry));
//                    pose.mulPose(Axis.XP.rotationDegrees(rx));
//                }
//            }
//
//            case JUMP_ATTACK -> {
//                float adjustedP = Math.min(p * 2f, 1.0f);
//
//                // 1. Keep horizontal position perfectly stationary on the arm line
//                float tx = 0.0f;
//
//                // 2. Keep your exact vertical rising curve (moves straight up from down)
//                float ty = interpolate(adjustedP, 0f, 1.0f, -0.4f, 0.9f);
//
//                // 3. Keep your exact forward tilt rotation arc
//                float rx = interpolate(adjustedP, 0f, 1.0f, -131.3f, 3.8f);
//
//                // 4. Lock these side-to-side rotations so the weapon doesn't slide left-to-right
//                float ry = -11.0f; // Static angle to keep the blade facing forward cleanly
//                float rz = 57.4f;  // Static tilt angle to keep the grip aligned with the arm socket
//
//                if (isLeftHand) {
//                    pose.translate(-tx, ty, -0.2);
//                    pose.mulPose(Axis.XP.rotationDegrees(rx));
//                    pose.mulPose(Axis.YP.rotationDegrees(-ry)); // Mirror side angle for off-hand
//                    pose.mulPose(Axis.ZP.rotationDegrees(-rz)); // Mirror roll angle for off-hand
//                } else {
//                    pose.translate(tx, ty, -0.2);
//                    pose.mulPose(Axis.XP.rotationDegrees(rx));
//                    pose.mulPose(Axis.YP.rotationDegrees(ry));
//                    pose.mulPose(Axis.ZP.rotationDegrees(rz));
//                }
//            }
//        }
//
//        ItemDisplayContext context = isLeftHand ? ItemDisplayContext.FIRST_PERSON_LEFT_HAND : ItemDisplayContext.FIRST_PERSON_RIGHT_HAND;
//        mc.gameRenderer.itemInHandRenderer.renderItem(
//                mc.player, event.getItemStack(), context,
//                isLeftHand, pose, event.getMultiBufferSource(), event.getPackedLight()
//        );
//
//        pose.popPose();
//        event.setCanceled(true);
//    }


    @SubscribeEvent
    public static void onRenderHand(RenderHandEvent event) {
        AnimationType anim = getCurrentAnim();
        if (anim == AnimationType.NONE) return;

        InteractionHand activeSwingHand = InteractionHand.MAIN_HAND;
        if (event.getHand() != activeSwingHand) return;

        float p = getProgress();
        PoseStack pose = event.getPoseStack();
        Minecraft mc = Minecraft.getInstance();

        pose.pushPose();

        float globalXShift = 0.7f;
        float globalYShift = -0.9f;
        float globalZShift = 0.0f;

        pose.translate(globalXShift, globalYShift, globalZShift);


        switch (anim) {
            case POKE -> {
                float tx, ty, tz;
                if (p < 0.35f) {
                    tx = interpolate(p, 0f, 0.2f, 0f, -0.4f);
                    ty = interpolate(p, 0f, 0.2f, 0f, 0.5f);
                    tz = interpolate(p, 0f, 0.2f, 0f, -1.5f);
                } else if (p < 0.8f) {
                    tx = -0.4f; ty = 0.5f; tz = -1.5f;
                } else {
                    tx = interpolate(p, 0.8f, 1.0f, -0.4f, 0f);
                    ty = interpolate(p, 0.8f, 1.0f, 0.5f, 0f);
                    tz = interpolate(p, 0.8f, 1.0f, -1.5f, 0f);
                }

                float rx = interpolate(p, 0f, 0.2f, 0f, -75f);
                float ry = interpolate(p, 0f, 0.2f, 0f, -15f);
                if (p > 0.8f) {
                    rx = interpolate(p, 0.8f, 1.0f, -75f, 0f);
                    ry = interpolate(p, 0.8f, 1.0f, -15f, 0f);
                } else if (p > 0.2f) {
                    rx = -75f; ry = -15f;
                }

                pose.translate(tx, ty, tz + 0.4f);
                pose.mulPose(Axis.XP.rotationDegrees(rx));
                pose.mulPose(Axis.YP.rotationDegrees(ry));
            }

            case CLEAVE -> {
                float slowP = p * 0.45f;
                float tx = (slowP < 0.5f) ? interpolate(slowP, 0f, 0.5f, 16.0f, -72.0f) : -72.0f;
                float ty = (slowP < 0.5f) ? interpolate(slowP, 0f, 0.5f, 0.6f, -0.5f) : -0.2f;
                float ry = (slowP < 0.5f) ? interpolate(slowP, 0f, 0.5f, -50f, 185f) : 185f;
                float rx = -45f;

                pose.translate(tx / 16f, ty, -0.8f);
                pose.mulPose(Axis.YP.rotationDegrees(ry));
                pose.mulPose(Axis.XP.rotationDegrees(rx));
            }

            case JUMP_ATTACK -> {
                float adjustedP = Math.min(p * 2f, 1.0f);

                // Horizontal
                float tx = 0.0f;

                // Vertical
                float ty = interpolate(adjustedP, 0f, 1.0f, -0.4f, 0.9f);

                // Forward
                float rx = interpolate(adjustedP, 0f, 1.0f, -131.3f, 3.8f);

                float ry = -11.0f;
                float rz = 57.4f;

                pose.translate(tx, ty, -0.2);
                pose.mulPose(Axis.XP.rotationDegrees(rx));
                pose.mulPose(Axis.YP.rotationDegrees(ry));
                pose.mulPose(Axis.ZP.rotationDegrees(rz));

            }
        }

        ItemDisplayContext context = ItemDisplayContext.FIRST_PERSON_LEFT_HAND;
        mc.gameRenderer.itemInHandRenderer.renderItem(
                mc.player, event.getItemStack(), context,
                true, pose, event.getMultiBufferSource(), event.getPackedLight()
        );

        pose.popPose();
        event.setCanceled(true);
    }

    private static float interpolate(float p, float startT, float endT, float startV, float endV) {
        if (p <= startT) return startV;
        if (p >= endT) return endV;
        return startV + (endV - startV) * ((p - startT) / (endT - startT));
    }
}