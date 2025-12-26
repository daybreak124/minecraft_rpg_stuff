package net.cold.coldsmod.gearbonuses.skills;

import net.cold.coldsmod.gearbonuses.effects.ModEffects;
import net.cold.coldsmod.stat.ItemRarityUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.UUID;

public class ExplosiveTendencies {

    @SubscribeEvent
    public static void onArrowSpawn(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof AbstractArrow arrow)) return;
        if (!(arrow.getOwner() instanceof Player player)) return;

        if (!player.getPersistentData().getBoolean("explosive_tendencies_applied")) return;

        if (!player.hasEffect(ModEffects.EXPLOSIVE_TENDENCY_STACK.get())) return;

        ItemStack main = player.getMainHandItem();
        ItemStack off  = player.getOffhandItem();

        boolean mainIsBow      = "bow".equals(ItemRarityUtils.getItemType(main));
        boolean mainIsCrossbow = "crossbow".equals(ItemRarityUtils.getItemType(main));
        boolean offIsCrossbow  = "crossbow".equals(ItemRarityUtils.getItemType(off));

        boolean isCrossbow = mainIsCrossbow || (offIsCrossbow && !mainIsBow);
        if (!isCrossbow) return;

        arrow.getPersistentData().putBoolean("explosive_tendency_tagged", true);
    }

    @SubscribeEvent
    public static void onArrowHitSpawnCreeper(LivingHurtEvent event) {
        if (!(event.getSource().getDirectEntity() instanceof Projectile proj)) return;
        if (!proj.getPersistentData().getBoolean("explosive_tendency_tagged")) return;
        Entity shooter = proj.getOwner();
        if (!(proj instanceof AbstractArrow)) return;
        Player player = (Player) shooter;
        LivingEntity target = event.getEntity();

        Level level = player.level();
        if (level.isClientSide()) return;
        ServerLevel server = (ServerLevel) level;

        Vec3 targetPos = target.position();
        Vec3 direction = shooter.position().subtract(targetPos).normalize();
        Vec3 spawnVec = targetPos.add(direction.scale(3));
        BlockPos spawnPos = BlockPos.containing(spawnVec);
        Creeper creeper = EntityType.CREEPER.spawn(server, spawnPos, MobSpawnType.TRIGGERED);
        if (creeper == null) { return; }

        creeper.getPersistentData().putBoolean("noBlockDamage", true);
        creeper.getPersistentData().putUUID("ownerPlayerUUID", player.getUUID());
        target.getPersistentData().putBoolean("customCreeperTarget", true);

        creeper.setHealth(40);
        creeper.setSilent(true);
        creeper.setAggressive(false);

        creeper.setAggressive(true);

        creeper.setTarget(target);
        double x = target.getX();
        double y = target.getY();
        double z = target.getZ();
        creeper.moveTo(x, y, z);

        MobEffectInstance stackEffect = player.getEffect(ModEffects.EXPLOSIVE_TENDENCY_STACK.get());
        if (stackEffect != null) {
            int currentStacks = stackEffect.getAmplifier();
            if (currentStacks <= 0) {
                player.removeEffect(ModEffects.EXPLOSIVE_TENDENCY_STACK.get());
            } else {
                player.removeEffect(ModEffects.EXPLOSIVE_TENDENCY_STACK.get());
                player.addEffect(new MobEffectInstance(
                        ModEffects.EXPLOSIVE_TENDENCY_STACK.get(),
                        MobEffectInstance.INFINITE_DURATION,
                        currentStacks - 1,
                        false,
                        false
                ));
            }
        }
        if (!player.hasEffect(ModEffects.EXPLOSIVE_TENDENCY_TIMER.get())) {
            player.addEffect(new MobEffectInstance(
                    ModEffects.EXPLOSIVE_TENDENCY_TIMER.get(),
                    20*8,
                    0,
                    false,
                    false
            ));
        }
    }

    @SubscribeEvent
    public static void onExplosion(ExplosionEvent.Detonate event) {
        if (!(event.getExplosion().getDirectSourceEntity() instanceof Creeper creeper)) return;

        if (!creeper.getPersistentData().getBoolean("noBlockDamage")) return;

        event.getAffectedBlocks().clear();
        event.getAffectedEntities().removeIf(e -> !(e instanceof Monster));
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        if (!(event.getSource().getEntity() instanceof Creeper creeper)) return;
        if (!creeper.getPersistentData().getBoolean("noBlockDamage")) return;
        if ((event.getEntity() instanceof Monster)) return;
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void onCreeperDrops(LivingDropsEvent event) {
        if (!(event.getEntity() instanceof Creeper creeper)) return;
        if (!creeper.getPersistentData().getBoolean("noBlockDamage")) return;

        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Creeper creeper)) return;

        if (!creeper.getPersistentData().getBoolean("noBlockDamage")) return;
        if (!creeper.getPersistentData().hasUUID("ownerPlayerUUID")) return;
        UUID ownerUUID = creeper.getPersistentData().getUUID("ownerPlayerUUID");

        if (!(creeper.level() instanceof ServerLevel serverLevel)) return;
        MinecraftServer server = serverLevel.getServer();
        if (server == null) return;

        Player owner = server.getPlayerList().getPlayer(ownerUUID);
        if (owner == null) return;

        CompoundTag data = owner.getPersistentData();

        double finalDamage = event.getAmount();

        if (owner.getRandom().nextDouble() < (data.getDouble("projectileCritChanceIncrease") + 10) / 100.0) {
            finalDamage *= 1.5 + data.getDouble("projectileCritDamageIncrease") / 100.0;
        }

        finalDamage *= 1 + data.getDouble("projectileDamageIncrease") / 100.0;

        event.setAmount((float) finalDamage);
    }
}
