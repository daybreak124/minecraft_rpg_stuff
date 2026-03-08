package net.cold.coldsmod.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerSkillProvider implements ICapabilitySerializable<CompoundTag> {
    public static final ResourceLocation IDENTIFIER = new ResourceLocation("coldsmod", "skills");

    private final SkillCapability backend = new SkillCapability();
    private final LazyOptional<SkillCapability> optional = LazyOptional.of(() -> backend);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return SkillCapabilityProvider.SKILL_CAP.orEmpty(cap, optional);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        ListTag list = new ListTag();
        for (String id : backend.getUnlockedSkillIds()) {
            list.add(StringTag.valueOf(id));
        }
        nbt.put("UnlockedSkills", list);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        backend.getUnlockedSkillIds().clear();
        ListTag list = nbt.getList("UnlockedSkills", Tag.TAG_STRING);
        for (int i = 0; i < list.size(); i++) {
            backend.unlockSkill(list.getString(i));
        }
    }
}