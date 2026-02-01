package net.cold.coldsmod.network;

import net.cold.coldsmod.blessingbonuses.effects.ModEffects;
import net.cold.coldsmod.blessingbonuses.neweffects.CombatantsAidReady;
import net.cold.coldsmod.stat.AttributeApplier;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CombatantsAidPacket {
    public CombatantsAidPacket() {}

    public static CombatantsAidPacket decode(FriendlyByteBuf buf) {
        return new CombatantsAidPacket();
    }

    public void encode(FriendlyByteBuf buf) {
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            CombatantsAidReady.startDash(player);

            double amp = AttributeApplier.getScaledValue(player,
                    ModAttributes.AMPLIFICATION.get(),
                    ModAttributes.AMPLIFICATION_MULTIPLIER.get());
            int cdTicks = (int) (800 / (1.0 + (amp / 100.0)));

            player.removeEffect(ModEffects.COMBATANTS_AID_READY.get());
            player.addEffect(new MobEffectInstance(ModEffects.COMBATANTS_AID_CD.get(), cdTicks, 0, false, false, true));

        });
        ctx.get().setPacketHandled(true);
    }
}