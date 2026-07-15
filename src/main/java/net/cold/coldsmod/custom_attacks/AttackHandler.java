package net.cold.coldsmod.custom_attacks;

import net.cold.coldsmod.ModMessages;
import net.cold.coldsmod.custom_attacks.attacks.*;
import net.cold.coldsmod.stat.ItemRarityUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "coldsmod", value = Dist.CLIENT)
public class AttackHandler {

    @SubscribeEvent
    public static void onClientAttack(InputEvent.InteractionKeyMappingTriggered event) {
        if (event.isAttack()) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.level != null && !mc.level.isClientSide()) return;
            if (mc.player == null) return;

            if (mc.hitResult.getType() == HitResult.Type.BLOCK) return;

            LivingEntity target = null;
            if (mc.hitResult instanceof EntityHitResult hit && hit.getEntity() instanceof LivingEntity living) {
                target = living;
            }

            CustomAttack attack = determineAttackType(mc.player, target);

            if (attack != null) {
                attack.executeClient(mc.player, target);
                mc.player.resetAttackStrengthTicker();

                int targetId = (target != null) ? target.getId() : -1;
                ModMessages.sendToServer(new ExecuteAttackPacket(targetId, attack.getClass().getSimpleName()));

                event.setCanceled(true);
                event.setSwingHand(false);
            }
        }
    }

    public static CustomAttack determineAttackType(Player player, LivingEntity target) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return null;
        if (!("sword".equals(ItemRarityUtils.getItemType(mc.player.getMainHandItem())))) return null;


        if (player.getAttackStrengthScale(0) >= 0.5f) {
            if (!player.onGround()) {
                if (player.getDeltaMovement().y < 0) {
                    return new HeavyAttack();
                }
                else if (player.getDeltaMovement().y > 0) {
                    return new JumpingAttack();
                }
            } else {
                if (mc.options.keyUp.isDown() && player.isSprinting() && player.getAttackStrengthScale(0) == 1f) {
                    return new LungeAttack();
                }

                else if (mc.options.keyLeft.isDown() || mc.options.keyRight.isDown()) {
                    return new AoEAttack();
                }
            }
        }

        return new SwingAttack();
    }
}