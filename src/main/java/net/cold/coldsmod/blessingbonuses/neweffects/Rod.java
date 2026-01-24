package net.cold.coldsmod.blessingbonuses.neweffects;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Rod {

    @SubscribeEvent
    public static void onGrapple(PlayerInteractEvent.RightClickItem event) {
        if (event.getEntity().level().isClientSide) return;
        if (!(event.getEntity().getPersistentData().getBoolean("grapple_eligible"))) return;
        if ((event.getEntity().getHealth() <= 6.0F || (event.getEntity().fishing == null))) return;
        Player player = event.getEntity();

        FishingHook hook = player.fishing;
        Level level = player.level();

        boolean hitEntity = hook.getHookedIn() != null;

        Vec3 hookPos = hook.position();
        BlockHitResult hitResult = level.clip(new ClipContext(
                hookPos.add(0, 0.2, 0),
                hookPos.subtract(0, 0.2, 0),
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,
                hook
        ));

        boolean hitBlock = hitResult.getType() == HitResult.Type.BLOCK;

        if (hitEntity || hitBlock) {

            if (hook.getY() > player.getY() + 0.5) {

                CompoundTag data = player.getPersistentData();

                data.putDouble("grapple_target_x", hook.getX());
                data.putDouble("grapple_target_y", hook.getY() + 1.2);
                data.putDouble("grapple_target_z", hook.getZ());
                data.putBoolean("is_grappling", true);

                EffectUtils.playSound(player, SoundEvents.FISHING_BOBBER_RETRIEVE, 1f, 1f);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.player.level().isClientSide) return;
        if (!event.player.getPersistentData().getBoolean("is_grappling")) return;

        Player player = event.player;
        CompoundTag data = player.getPersistentData();

        if (player.horizontalCollision || (player.verticalCollision && player.getDeltaMovement().y > 0) || player.isCrouching()) {
            data.putBoolean("is_grappling", false);
            data.remove("last_y");
            player.hurt(player.damageSources().magic(), 6f);
            return;
        }

        Vec3 target = new Vec3(
                data.getDouble("grapple_target_x"),
                data.getDouble("grapple_target_y"),
                data.getDouble("grapple_target_z")
        );

        double currentY = player.getY();
        double lastY = data.contains("last_y") ? data.getDouble("last_y") : currentY;

        if (currentY < lastY) {
            return;
        } else {
            data.putDouble("last_y", currentY);
        }

        double distance = player.position().distanceTo(target);
        if (distance < 1.4 || currentY >= target.y) {
            data.putBoolean("is_grappling", false);
            player.hurt(player.damageSources().magic(), 4f);
            data.remove("last_y");

            player.push(0, 0.1, 0);
            return;
        }

        Vec3 direction = target.subtract(player.position()).normalize();
        double speed = 0.5;

        double moveY = Math.max(0.1, direction.y * speed) + 0.15;

        player.setDeltaMovement(
                direction.x * speed,
                moveY,
                direction.z * speed
        );

        player.hurtMarked = true;
        player.fallDistance = 0;
    }
}