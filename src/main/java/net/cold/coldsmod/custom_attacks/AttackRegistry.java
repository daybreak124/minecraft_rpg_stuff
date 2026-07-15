package net.cold.coldsmod.custom_attacks;

import net.cold.coldsmod.custom_attacks.attacks.*;

import java.util.HashMap;
import java.util.Map;

public class AttackRegistry {
    private static final Map<String, CustomAttack> ATTACKS = new HashMap<>();

    static {
        register(new LungeAttack());
        register(new JumpingAttack());
        register(new AoEAttack());
        register(new HeavyAttack());
        register(new SwingAttack());
    }

    private static void register(CustomAttack attack) {
        ATTACKS.put(attack.getClass().getSimpleName(), attack);
    }

    public static CustomAttack get(String name) {
        return ATTACKS.get(name);
    }
}