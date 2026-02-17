package net.cold.coldsmod.network;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Mod.EventBusSubscriber(modid = "coldsmod", value = Dist.CLIENT)
public class ClientInputEvent {

    private static final Map<Integer, Consumer<Player>> KEY_ACTIONS = new HashMap<>();
    private static long aidKeyPressTime = 0;

    public static void initializeKeys(RegisterKeyMappingsEvent event) {
        KEY_ACTIONS.put(ClientKeyInputHandler.quantumKey.getKey().getValue(), player -> {
            if (player.hasEffect(ModEffects.QUANTUM_LEAP_READY.get())) {
                NetworkHandler.CHANNEL.sendToServer(new QuantumLeapPacket());
            }
        });

        KEY_ACTIONS.put(ClientKeyInputHandler.combatantKey.getKey().getValue(), player -> {
            if (player.isSprinting() && player.hasEffect(ModEffects.COMBATANTS_AID_READY.get())) {

                Vec3 look = player.getLookAngle();
                Vec3 direction = new Vec3(look.x, 0, look.z).normalize();

                double speed = 2.0;
                player.setDeltaMovement(direction.x * speed, 0.1, direction.z * speed);

                player.level().playSound(player, player.blockPosition(),
                        SoundEvents.ARMOR_EQUIP_ELYTRA, SoundSource.PLAYERS,
                        0.5F, 1.0F);

                NetworkHandler.CHANNEL.sendToServer(new CombatantsAidPacket());
            }
            aidKeyPressTime = System.currentTimeMillis();
        });

        KEY_ACTIONS.put(ClientKeyInputHandler.overconfidenceKey.getKey().getValue(), player -> {
            if (player.hasEffect(ModEffects.OVERCONFIDENCE_READY.get())) {
                NetworkHandler.CHANNEL.sendToServer(new OverconfidencePacket());
            }
        });

        KEY_ACTIONS.put(ClientKeyInputHandler.directedHatredKey.getKey().getValue(), player -> {
            if (player.hasEffect(ModEffects.DIRECTED_HATRED_READY.get())) {
                NetworkHandler.CHANNEL.sendToServer(new DirectedHatredPacket());
            }
        });

        KEY_ACTIONS.put(ClientKeyInputHandler.daringShoutKey.getKey().getValue(), player -> {
            if (player.hasEffect(ModEffects.DARING_SHOUT.get())) {
                NetworkHandler.CHANNEL.sendToServer(new DaringShoutPacket());
            }
        });

        KEY_ACTIONS.put(ClientKeyInputHandler.intimidateKey.getKey().getValue(), player -> {
            if (player.hasEffect(ModEffects.INTIMIDATING_PRESENCE.get())) {
                NetworkHandler.CHANNEL.sendToServer(new IntimidatePacket());
            }
        });

        KEY_ACTIONS.put(ClientKeyInputHandler.dfaKey.getKey().getValue(), player -> {
            if (player.hasEffect(ModEffects.DEATH_FROM_ABOVE.get())) {
                if (!player.onGround()) return;

                double mX = player.getDeltaMovement().x;
                double mZ = player.getDeltaMovement().z;

                if (Math.abs(mX) > 0.01 || Math.abs(mZ) > 0.01) {
                    double dashMultiplier = 3.0;
                    mX *= dashMultiplier;
                    mZ *= dashMultiplier;
                }

                double jumpBoost = player.isCrouching() ? 0.42 : 1.1;

                player.setDeltaMovement(mX, jumpBoost, mZ);
                player.setOnGround(false);

                player.getPersistentData().putFloat("dfaFallDamage", player.isCrouching() ? 6.25f : 12.5f);
                player.level().playSound(player, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 0.5F, 1.0F);
                player.getPersistentData().putBoolean("DFA_Airborne", true);
                NetworkHandler.CHANNEL.sendToServer(new DFAPacket(mX, mZ, player.isCrouching()));
            } else if (player.getPersistentData().getBoolean("DFA_Airborne") && !player.onGround()) {
                if (player.getPersistentData().getFloat("dfaFallDamage") < 10f) return;
                double diveSpeed = -2.5;

                player.setDeltaMovement(player.getDeltaMovement().x * 0.1, diveSpeed, player.getDeltaMovement().z * 0.1);
                player.setOnGround(false);

                player.level().playSound(player, player.blockPosition(), SoundEvents.TRIDENT_RIPTIDE_3, SoundSource.PLAYERS, 1.0F, 0.45F);
                player.getPersistentData().putBoolean("DFA_Airborne", false);

                NetworkHandler.CHANNEL.sendToServer(new DFADivePacket());
            }
        });
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (event.getAction() != GLFW.GLFW_PRESS) return;
        Minecraft mc = Minecraft.getInstance();
        if (mc.screen != null) return;

        Player player = Minecraft.getInstance().player;
        if (player == null || KEY_ACTIONS.isEmpty()) return;
        Consumer<Player> action = KEY_ACTIONS.get(event.getKey());
        if (action != null) {
            action.accept(player);
        }
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END || aidKeyPressTime == 0) return;

        if (!ClientKeyInputHandler.combatantKey.isDown()) {
            aidKeyPressTime = 0;
            return;
        }

        Player player = Minecraft.getInstance().player;
        if (player == null) return;

        if (!player.hasEffect(ModEffects.COMBATANTS_AID_RECALL.get())) {
            aidKeyPressTime = 0;
            return;
        }

        long duration = System.currentTimeMillis() - aidKeyPressTime;
        if (duration < 1000) return;

        NetworkHandler.CHANNEL.sendToServer(new CombatantsRecallPacket());
        aidKeyPressTime = 0;
    }
}