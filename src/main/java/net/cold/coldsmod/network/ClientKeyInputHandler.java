package net.cold.coldsmod.network;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

public class ClientKeyInputHandler {
    public static final String KEY_CATEGORY = "key.categories.coldsmod";
    public static final String KEY_COLDSMOD_QUANTUM_LEAP = "key.coldsmod.quantum_leap";
    public static final String KEY_COMBATANTS_DASH = "key.coldsmod.combatants_aid";
    public static final String KEY_OVERCONFIDENCE = "key.coldsmod.overconfidence";
    public static final String KEY_DIRECTED_HATRED = "key.coldsmod.directed_hatred";
    public static final String KEY_DARING_SHOUT = "key.coldsmod.daring_shout";
    public static final String KEY_INTIMIDATE = "key.coldsmod.intimidate";
    public static final String KEY_DFA = "key.coldsmod.DFA";


    public static KeyMapping quantumKey;
    public static KeyMapping combatantKey;
    public static KeyMapping overconfidenceKey;
    public static KeyMapping directedHatredKey;
    public static KeyMapping daringShoutKey;
    public static KeyMapping intimidateKey;
    public static KeyMapping dfaKey;

    public static void register(RegisterKeyMappingsEvent event) {
        quantumKey = new KeyMapping(KEY_COLDSMOD_QUANTUM_LEAP, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, KEY_CATEGORY);
        combatantKey = new KeyMapping(KEY_COMBATANTS_DASH, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_GRAVE_ACCENT, KEY_CATEGORY);
        overconfidenceKey = new KeyMapping(KEY_OVERCONFIDENCE, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_H, KEY_CATEGORY);
        directedHatredKey = new KeyMapping(KEY_DIRECTED_HATRED, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_X, KEY_CATEGORY);
        daringShoutKey = new KeyMapping(KEY_DARING_SHOUT, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_Z, KEY_CATEGORY);
        intimidateKey = new KeyMapping(KEY_INTIMIDATE, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_TAB, KEY_CATEGORY);
        dfaKey = new KeyMapping(KEY_DFA, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, KEY_CATEGORY);

        event.register(quantumKey);
        event.register(combatantKey);
        event.register(overconfidenceKey);
        event.register(directedHatredKey);
        event.register(daringShoutKey);
        event.register(intimidateKey);
        event.register(dfaKey);
    }
}
