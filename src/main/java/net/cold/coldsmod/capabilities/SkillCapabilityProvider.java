package net.cold.coldsmod.capabilities;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class SkillCapabilityProvider {
    // This is the static hook used in your Events
    public static final Capability<SkillCapability> SKILL_CAP = CapabilityManager.get(new CapabilityToken<>() {});
}