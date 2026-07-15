package net.cold.coldsmod.custom_attacks;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record ExecuteAttackPacket(int targetId, String attackType) {
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(targetId);
        buffer.writeUtf(attackType);
    }

    public static ExecuteAttackPacket decode(FriendlyByteBuf buffer) {
        return new ExecuteAttackPacket(buffer.readInt(), buffer.readUtf());
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;

            LivingEntity target = null;
            if (this.targetId != -1) {
                Entity entity = player.level().getEntity(this.targetId);
                if (entity instanceof LivingEntity living) {
                    target = living;
                }
            }

            CustomAttack attack = AttackRegistry.get(this.attackType);
            if (attack != null) {
                attack.executeClient(player, target);
                attack.executeServer(player, target);
                // SWING HAND FOR OTHER PLAYERS, REQUIRES TESTING
                player.swing(InteractionHand.MAIN_HAND, false);
            }
        });
        context.setPacketHandled(true);
    }
}