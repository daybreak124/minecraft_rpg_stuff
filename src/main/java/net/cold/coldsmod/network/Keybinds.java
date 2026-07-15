package net.cold.coldsmod.network;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;

public class Keybinds {

    public static final String KEY_CATEGORY = "key.categories.coldsmod";

    public static final String KEY_COLDSMOD_QUANTUM_LEAP = "key.coldsmod.quantum_leap";
    public static final String KEY_COMBATANTS_DASH = "key.coldsmod.combatants_aid";
    public static final String KEY_OVERCONFIDENCE = "key.coldsmod.overconfidence";
    public static final String KEY_DIRECTED_HATRED = "key.coldsmod.directed_hatred";
    public static final String KEY_DARING_SHOUT = "key.coldsmod.daring_shout";
    public static final String KEY_INTIMIDATE = "key.coldsmod.intimidate";
    public static final String KEY_DFA = "key.coldsmod.dfa";
    public static final String KEY_SEVERANCE = "key.coldsmod.soul_severance";
    public static final String KEY_AUTOSMELT = "key.coldsmod.auto_smelt";
    public static final String KEY_JUMPBOOST = "key.coldsmod.jump_boost";

    public static KeyMapping quantumKey;
    public static KeyMapping combatantKey;
    public static KeyMapping overconfidenceKey;
    public static KeyMapping directedHatredKey;
    public static KeyMapping daringShoutKey;
    public static KeyMapping intimidateKey;
    public static KeyMapping dfaKey;
    public static KeyMapping severanceKey;
    public static KeyMapping autoSmeltKey;
    public static KeyMapping jumpBoostKey;

    public static void register(RegisterKeyMappingsEvent event) {
        quantumKey = new KeyMapping(KEY_COLDSMOD_QUANTUM_LEAP, InputConstants.Type.KEYSYM, InputConstants.UNKNOWN.getValue(), KEY_CATEGORY);
        combatantKey = new KeyMapping(KEY_COMBATANTS_DASH, InputConstants.Type.KEYSYM, InputConstants.UNKNOWN.getValue(), KEY_CATEGORY);
        overconfidenceKey = new KeyMapping(KEY_OVERCONFIDENCE, InputConstants.Type.KEYSYM, InputConstants.UNKNOWN.getValue(), KEY_CATEGORY);
        directedHatredKey = new KeyMapping(KEY_DIRECTED_HATRED, InputConstants.Type.KEYSYM, InputConstants.UNKNOWN.getValue(), KEY_CATEGORY);
        daringShoutKey = new KeyMapping(KEY_DARING_SHOUT, InputConstants.Type.KEYSYM, InputConstants.UNKNOWN.getValue(), KEY_CATEGORY);
        intimidateKey = new KeyMapping(KEY_INTIMIDATE, InputConstants.Type.KEYSYM, InputConstants.UNKNOWN.getValue(), KEY_CATEGORY);
        dfaKey = new KeyMapping(KEY_DFA, InputConstants.Type.KEYSYM, InputConstants.UNKNOWN.getValue(), KEY_CATEGORY);
        severanceKey = new KeyMapping(KEY_SEVERANCE, InputConstants.Type.KEYSYM, InputConstants.UNKNOWN.getValue(), KEY_CATEGORY);
        autoSmeltKey = new KeyMapping(KEY_AUTOSMELT, InputConstants.Type.KEYSYM, InputConstants.UNKNOWN.getValue(), KEY_CATEGORY);
        jumpBoostKey = new KeyMapping(KEY_JUMPBOOST, InputConstants.Type.KEYSYM, InputConstants.UNKNOWN.getValue(), KEY_CATEGORY);

        event.register(quantumKey);
        event.register(combatantKey);
        event.register(overconfidenceKey);
        event.register(directedHatredKey);
        event.register(daringShoutKey);
        event.register(intimidateKey);
        event.register(dfaKey);
        event.register(severanceKey);
        event.register(autoSmeltKey);
        event.register(jumpBoostKey);
    }
}