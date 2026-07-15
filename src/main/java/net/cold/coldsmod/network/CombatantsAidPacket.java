package net.cold.coldsmod.network;

import net.cold.coldsmod.ModMessages;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusRegistry;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusTrigger;
import net.cold.coldsmod.capabilities_and_blessings.effects.CombatantsAidReady;
import net.cold.coldsmod.capabilities_and_blessings.registry.ModEffects;
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
                    ModAttributes.AMPLIFICATION.get());
            int cdTicks = (int) (800 / (1.0 + (amp / 100.0)));

            BonusRegistry.process(player, null, player.level(), BonusTrigger.BLESSING_ACTIVATION);
            player.removeEffect(ModEffects.COMBATANTS_AID_READY.get());
            player.addEffect(new MobEffectInstance(ModEffects.COMBATANTS_AID_CD.get(), cdTicks, 0, false, false, true));
            ModMessages.sendToPlayer(new CombatantRecallSync.CombatantRecallFlagPacket(true), player);
        });
        ctx.get().setPacketHandled(true);
    }
}