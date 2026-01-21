package net.cold.coldsmod.blessingbonuses.neweffects;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

import static net.cold.coldsmod.stat.AttributeApplier.getScaledValue;

public class CombatantsAidReady extends MobEffect {
    private static final int RECALL_WINDOW_TICKS = 20 * 4;
    private static final int CROUCH_FOR_RECALL_TICKS = 20;

    public CombatantsAidReady() {
        super(MobEffectCategory.NEUTRAL, 0xFF0000);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false;
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (event.player.level().isClientSide()) return;

        Player player = event.player;
        CompoundTag tag = player.getPersistentData();

        if (player.hasEffect(ModEffects.COMBATANTS_AID_READY.get())) {

            boolean wasSprinting = tag.getBoolean("dash_was_sprinting");
            boolean isSprinting = player.isSprinting();
            boolean isCrouching = player.isCrouching();

            tag.putBoolean("dash_was_sprinting", isSprinting);

            if (wasSprinting && isCrouching && !tag.getBoolean("dash_active")) {
                startDash(player);

                int cd = (int) (20*40 / (1.0 + (AttributeApplier.getScaledValue(player, ModAttributes.AMPLIFICATION.get(), ModAttributes.AMPLIFICATION_MULTIPLIER.get()) / 100.0)));

                player.removeEffect(ModEffects.COMBATANTS_AID_READY.get());
                player.addEffect(new MobEffectInstance(ModEffects.COMBATANTS_AID_CD.get(), cd, 0, false, false, true));
            }
        }
        handleDashProcess(player);
    }

    private static void startDash(Player player) {
        CompoundTag tag = player.getPersistentData();
        tag.putBoolean("dash_active", true);
        tag.putInt("dash_timer", 0);
        tag.putInt("dash_crouch_ticks", 0);

        tag.putDouble("dash_x", player.getX());
        tag.putDouble("dash_y", player.getY());
        tag.putDouble("dash_z", player.getZ());

        Vec3 look = player.getLookAngle();
        double dashPower = 5;
        player.setDeltaMovement(look.x * dashPower, -0.1, look.z * dashPower);
        player.hurtMarked = true;

        EffectUtils.playSound(player, SoundEvents.ARMOR_EQUIP_ELYTRA, 0.5F, 1.0F);
        applyDashSupport(player, look);
    }

    private static void handleDashProcess(Player player) {
        CompoundTag tag = player.getPersistentData();
        if (!tag.getBoolean("dash_active")) return;

        int timer = tag.getInt("dash_timer");
        timer++;
        tag.putInt("dash_timer", timer);

        if (timer < 10) {
            Vec3 movement = player.getDeltaMovement();
            player.setDeltaMovement(movement.x * 1.1, movement.y, movement.z * 1.1);
        }

        if (timer <= RECALL_WINDOW_TICKS) {
            if (player.isCrouching()) {
                int crouchTicks = tag.getInt("dash_crouch_ticks") + 1;
                tag.putInt("dash_crouch_ticks", crouchTicks);

                if (crouchTicks >= CROUCH_FOR_RECALL_TICKS) {
                    returnToOrigin(player);
                }
            } else {
                tag.putInt("dash_crouch_ticks", 0);
            }
        } else {
            tag.putBoolean("dash_active", false);
            tag.remove("dash_timer");
        }
    }

    private static void applyDashSupport(Player player, Vec3 direction) {
        Level level = player.level();

        AABB dashBox = player.getBoundingBox().inflate(4);

        List<LivingEntity> allies = level.getEntitiesOfClass(
                LivingEntity.class,
                dashBox,
                EffectUtils::isAlly
        );

        double healIncrease = getScaledValue(player,
                ModAttributes.RESTORATION.get(),
                ModAttributes.RESTORATION_MULTIPLIER.get());

        float healAmount = (float) (4f * (1.0 + (healIncrease / 100.0)));

        for (LivingEntity ally : allies) {
            ally.heal(healAmount);
            ally.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20 * 5, 0, false, false, true));
            EffectUtils.playHealSound(ally);
            EffectUtils.spawnComposterBurst(ally);
        }
    }

    private static void returnToOrigin(Player player) {
        CompoundTag tag = player.getPersistentData();

        player.teleportTo(
                tag.getDouble("dash_x"),
                tag.getDouble("dash_y"),
                tag.getDouble("dash_z")
        );

        EffectUtils.playSound(player, SoundEvents.ARMOR_EQUIP_ELYTRA, 0.5F, 1.0F);


        tag.putBoolean("dash_active", false);
        tag.remove("dash_timer");
        tag.remove("dash_crouch_ticks");
    }
}