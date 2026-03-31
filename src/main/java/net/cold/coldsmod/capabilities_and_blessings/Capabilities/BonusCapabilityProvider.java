package net.cold.coldsmod.capabilities_and_blessings.Capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BonusCapabilityProvider implements ICapabilitySerializable<CompoundTag> {
    public static Capability<PlayerBonusCache> PLAYER_BONUS_CACHE = CapabilityManager.get(new CapabilityToken<>() {});

    private final PlayerBonusCache cache = new PlayerBonusCache();
    private final LazyOptional<PlayerBonusCache> optional = LazyOptional.of(() -> cache);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return PLAYER_BONUS_CACHE.orEmpty(cap, optional);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        ListTag list = new ListTag();
        // Save only the IDs. We can't save the Lambdas from BonusRegister.
        for (Integer id : cache.getPersistentIds()) {
            CompoundTag tag = new CompoundTag();
            tag.putInt("id", id);
            list.add(tag);
        }
        nbt.put("UnlockedBonuses", list);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        cache.clearAll();
        ListTag list = nbt.getList("UnlockedBonuses", Tag.TAG_COMPOUND);
        for (int i = 0; i < list.size(); i++) {
            int id = list.getCompound(i).getInt("id");
            cache.unlock(id);
        }
    }
}