package net.cold.coldsmod.network;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Mod.EventBusSubscriber(modid = "coldsmod", value = Dist.CLIENT)
public class ClientInputEvent {

    private static final Map<KeyMapping, Consumer<Player>> KEY_ACTIONS = new HashMap<>();

    public static void initKeyActions() {

        KEY_ACTIONS.put(Keybinds.quantumKey, player -> {
            if (QuantumLeapSync.QuantumLeapClientData.quantumLeapEligible) {
                NetworkHandler.CHANNEL.sendToServer(new QuantumLeapPacket());
            }
        });

        KEY_ACTIONS.put(Keybinds.combatantKey, player -> {
            if (player.isSprinting() && CombatantSync.CombatantClientData.combatantEligible) {

                Vec3 look = player.getLookAngle();
                Vec3 direction = new Vec3(look.x, 0, look.z).normalize();

                double speed = 2.0;
                player.setDeltaMovement(direction.x * speed, 0.1, direction.z * speed);

                player.level().playSound(player, player.blockPosition(),
                        SoundEvents.ARMOR_EQUIP_ELYTRA, SoundSource.PLAYERS,
                        0.5F, 1.0F);

                NetworkHandler.CHANNEL.sendToServer(new CombatantsAidPacket());

            } else if (CombatantRecallSync.CombatantRecallClientData.combatantRecallEligible) {
                NetworkHandler.CHANNEL.sendToServer(new CombatantsRecallPacket());
            }
        });

        KEY_ACTIONS.put(Keybinds.overconfidenceKey, player -> {
            if (OverconfidenceSync.OverconfidenceData.OverconfidenceEligible) {
                NetworkHandler.CHANNEL.sendToServer(new OverconfidencePacket());
            }
        });

        KEY_ACTIONS.put(Keybinds.directedHatredKey, player -> {
            if (HatredSync.HatredData.HatredEligible) {
                NetworkHandler.CHANNEL.sendToServer(new DirectedHatredPacket());
            }
        });

        KEY_ACTIONS.put(Keybinds.daringShoutKey, player -> {
            if (DaringShoutSync.DaringClientData.DaringEligible) {
                NetworkHandler.CHANNEL.sendToServer(new DaringShoutPacket());
            }
        });

        KEY_ACTIONS.put(Keybinds.intimidateKey, player -> {
            if (IntimidatingPresenceSync.IntimidatingSync.IntimidatingPresenceEligible) {
                NetworkHandler.CHANNEL.sendToServer(new IntimidatePacket());
            }
        });

        KEY_ACTIONS.put(Keybinds.severanceKey, player -> {
            if (SeveranceSync.SeveranceClientData.severanceEligible) {
                NetworkHandler.CHANNEL.sendToServer(new SoulSeverancePacket());
            }
        });

        KEY_ACTIONS.put(Keybinds.dfaKey, player -> {
            if (DFASync.DFAClientData.DFAEligible) {
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

                player.level().playSound(player, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 0.5F, 1.0F);

                NetworkHandler.CHANNEL.sendToServer(new DFAPacket(mX, mZ, player.isCrouching()));

            } else if (DfaAirborneSync.DfaAirborneClientData.dfaAirborneEligible) {

                double diveSpeed = -2.5;

                player.setDeltaMovement(
                        player.getDeltaMovement().x * 0.1,
                        diveSpeed,
                        player.getDeltaMovement().z * 0.1
                );

                player.setOnGround(false);

                player.level().playSound(player, player.blockPosition(),
                        SoundEvents.TRIDENT_RIPTIDE_3,
                        SoundSource.PLAYERS, 1.0F, 0.45F);

                NetworkHandler.CHANNEL.sendToServer(new DFADivePacket());
            }
        });
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();

        if (mc.screen != null) return;

        Player player = mc.player;
        if (player == null) return;

        for (Map.Entry<KeyMapping, Consumer<Player>> entry : KEY_ACTIONS.entrySet()) {
            if (entry.getKey().consumeClick()) {
                entry.getValue().accept(player);
            }
        }
    }
}