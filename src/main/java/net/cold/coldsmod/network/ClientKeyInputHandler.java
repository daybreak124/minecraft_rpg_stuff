package net.cold.coldsmod.network;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

public class ClientKeyInputHandler {
    public static final String KEY_CATEGORY = "key.categories.coldsmod";
    public static final String KEY_COLDSMOD_QUANTUM_LEAP = "key.coldsmod.quantum_leap";
    public static final String KEY_COMBATANTS_DASH = "key.coldsmod.combatants_aid";


    public static KeyMapping quantumKey;
    public static KeyMapping combatantKey;

    public static void register(RegisterKeyMappingsEvent event) {
        quantumKey = new KeyMapping(KEY_COLDSMOD_QUANTUM_LEAP, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, KEY_CATEGORY);
        combatantKey = new KeyMapping(KEY_COMBATANTS_DASH, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, KEY_CATEGORY);

        event.register(quantumKey);
        event.register(combatantKey);
    }
}
