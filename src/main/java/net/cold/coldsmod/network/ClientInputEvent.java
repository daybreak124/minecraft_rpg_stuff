package net.cold.coldsmod.network;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = "coldsmod", value = Dist.CLIENT)
public class ClientInputEvent {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (event.getAction() != GLFW.GLFW_PRESS) return;

        Player player = Minecraft.getInstance().player;
        if (player == null) return;

        int pressedKey = event.getKey();

        if (pressedKey == ClientKeyInputHandler.quantumKey.getKey().getValue()) {
            if (player.hasEffect(ModEffects.QUANTUM_LEAP_READY.get()) ||
                    player.hasEffect(ModEffects.QUANTUM_LEAP_ACTIVE.get())) {
                NetworkHandler.CHANNEL.sendToServer(new QuantumLeapPacket());
            }
        }
        else if (pressedKey == ClientKeyInputHandler.combatantKey.getKey().getValue()) {
            if (player.isSprinting() && player.hasEffect(ModEffects.COMBATANTS_AID_READY.get())) {
                NetworkHandler.CHANNEL.sendToServer(new CombatantsAidPacket());
            }
        }
    }
}