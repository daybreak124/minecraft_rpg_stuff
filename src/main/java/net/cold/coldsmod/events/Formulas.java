package net.cold.coldsmod.events;

import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusCapabilityProvider;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusRegistry;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.BonusTrigger;
import net.cold.coldsmod.capabilities_and_blessings.Capabilities.PlayerBonusCache;
import net.cold.coldsmod.capabilities_and_blessings.effects.BlessedLandReady;
import net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils;
import net.cold.coldsmod.capabilities_and_blessings.registry.ModEffects;
import net.cold.coldsmod.damage_types.ModDamageTypes;
import net.cold.coldsmod.stat.ItemRarityUtils;
import net.cold.coldsmod.stat.ModAttributes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static net.cold.coldsmod.capabilities_and_blessings.effects.IntoTheFrayCollisionCheck.isValidTarget;
import static net.cold.coldsmod.capabilities_and_blessings.registry.CooldownCycle.EXPIRE_HANDLERS;
import static net.cold.coldsmod.capabilities_and_blessings.registry.EffectUtils.spawnExplosionOnFeet;
import static net.cold.coldsmod.stat.AttributeApplier.getScaledValue;
import static net.cold.coldsmod.stat.ModAttributes.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Formulas {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onLivingAttack(LivingAttackEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        if (event.getSource().is(DamageTypes.FALL)) return;

        // Player takes damage
        if (event.getEntity() instanceof Player victim) {

            // Player deals damage
            if (event.getSource().getEntity() instanceof Player attacker && event.getSource().getDirectEntity() instanceof AbstractArrow arrow) {
                float[] data = { (float) attacker.getAttributeValue(ModAttributes.PROJECTILE_DAMAGE_MULTIPLIER.get()), event.getAmount() };

                BonusRegistry.process(attacker, null, attacker.level(), BonusTrigger.PROJECTILE_HURT_BOW, data);
                BonusRegistry.process(attacker, event.getEntity(), attacker.level(), BonusTrigger.FRIENDLY_FIRE_LIFE_TOUCH, data);

                if (data[0] == -1f) {
                    event.setCanceled(true);
                    arrow.discard();
                    return;
                }
            }

            double evasion = victim.getAttributeValue(ModAttributes.EVASION.get());
            LivingEntity attacker = event.getSource().getEntity() instanceof LivingEntity lentity ? lentity : null;


            if (victim.getRandom().nextDouble() < evasion) {
                event.setCanceled(true);

                if (victim instanceof ServerPlayer serverPlayer) {
                    serverPlayer.playNotifySound(SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 0.75F, 1.5F);
                }
                return;
            }

            float[] data = {event.getAmount()};
            BonusRegistry.process(victim, attacker, victim.level(), BonusTrigger.DAMAGE_TAKE_ATTACK_EVENT, data);
        }
    }


    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        if (event.getEntity().level().isClientSide()) return;

        float finalDamage = event.getAmount();

        Entity source = event.getSource().getEntity();
        LivingEntity victim = event.getEntity();

        // PLAYER TAKES DAMAGE
        if (victim instanceof Player player) {

            // Snapshot incoming damage
            float DR = (float) player.getAttributeValue(INCOMING_DAMAGE_MULTIPLIER.get());

            // Additive DR, damage
            float[] data = {DR, finalDamage};

            LivingEntity attacker = (source instanceof LivingEntity living) ? living : null;

            BonusRegistry.process(player, attacker, player.level(), BonusTrigger.DAMAGE_TAKE_DAMAGE_EVENT, data);

            if (player.isBlocking()) {
                BonusRegistry.process(player, attacker, player.level(), BonusTrigger.DEFEND, data);
            }

            finalDamage *= data[0];
        } else {
            finalDamage *= (float) victim.getAttributeValue(INCOMING_DAMAGE_MULTIPLIER.get());
        }

        // PLAYER DEALS DAMAGE
        if (source instanceof Player player) {
            if (!(victim instanceof Player)) {
                if (event.getSource().is(ModDamageTypes.TRUE_DAMAGE)) return;

                // Additive multipliers, damage
                float[] data = { 0.0f, finalDamage};

                if (event.getSource().getDirectEntity() == player) {

                    double melPot = getScaledValue(player, ModAttributes.MELEE_POTENCY.get(), ModAttributes.MELEE_POTENCY_MULTIPLIER.get());

                    data[0] += (float) player.getAttributeValue(ModAttributes.MELEE_DAMAGE_MULTIPLIER.get());
                    BonusRegistry.process(player, victim, player.level(), BonusTrigger.MELEE_HURT, data);
                    finalDamage *= (float) (1.0 + melPot / 100.0);

                } else if (event.getSource().is(ModDamageTypes.CUSTOM_MELEE_DAMAGE)) {

                    double melPot = getScaledValue(player, ModAttributes.MELEE_POTENCY.get(), ModAttributes.MELEE_POTENCY_MULTIPLIER.get());

                    if (rollCrit(player, getScaledValue(player, ModAttributes.MELEE_ACCURACY.get(), ModAttributes.MELEE_ACCURACY_MULTIPLIER.get()))) {
                        finalDamage *= (float) (1.5 + getScaledValue(player, ModAttributes.MELEE_PRECISION.get(), ModAttributes.MELEE_PRECISION_MULTIPLIER.get()) / 100.0);
                        playCritSound(player);

                    }
                    data[0] += (float) player.getAttributeValue(ModAttributes.MELEE_DAMAGE_MULTIPLIER.get());
                    BonusRegistry.process(player, victim, player.level(), BonusTrigger.INDIRECT_MELEE_HURT, data);
                    finalDamage *= (float) (1.0 + melPot / 100.0);

                } else if (event.getSource().getDirectEntity() instanceof AbstractArrow arrow) {

                    double projPot = getScaledValue(player, ModAttributes.PROJECTILE_POTENCY.get(), ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get());

                    if (rollCrit(player, getScaledValue(player, ModAttributes.PROJECTILE_ACCURACY.get(), ModAttributes.PROJECTILE_ACCURACY_MULTIPLIER.get()))) {
                        finalDamage *= (float) (1.5 + getScaledValue(player, ModAttributes.PROJECTILE_PRECISION.get(), ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get()) / 100.0);
                        playCritSound(player);
                    }

                    data[0] += (float) player.getAttributeValue(ModAttributes.PROJECTILE_DAMAGE_MULTIPLIER.get());
                    if (arrow.getPersistentData().getBoolean("arr_bow")) {
                        BonusRegistry.process(player, victim, player.level(), BonusTrigger.PROJECTILE_HURT_BOW, data);
                    }
                    else if (arrow.getPersistentData().getBoolean("arr_cbow")) {
                        BonusRegistry.process(player, victim, player.level(), BonusTrigger.PROJECTILE_HURT_CROSSBOW, data);
                    }
                    finalDamage *= (float) (1.0 + projPot / 100.0)/2;

                } else if (event.getSource().is(ModDamageTypes.CUSTOM_RANGED_DAMAGE)) {
                    double genPot = getScaledValue(player, ModAttributes.PROJECTILE_POTENCY.get(), ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get());
                    if (rollCrit(player, getScaledValue(player, ModAttributes.PROJECTILE_ACCURACY.get(), ModAttributes.PROJECTILE_ACCURACY_MULTIPLIER.get()))) {
                        finalDamage *= (float) (1.5 + getScaledValue(player, ModAttributes.PROJECTILE_PRECISION.get(), ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get()) / 100.0);
                        playCritSound(player);
                    }
                    data[0] += (float) player.getAttributeValue(ModAttributes.PROJECTILE_DAMAGE_MULTIPLIER.get());
                    finalDamage *= (float) (1.0 + genPot / 100.0);
                } else if (event.getSource().is(ModDamageTypes.DOT_DAMAGE)) {
                    double dotPot = getScaledValue(player, ModAttributes.POTENCY.get(), ModAttributes.POTENCY_MULTIPLIER.get());
                    if (rollCrit(player, getScaledValue(player, ModAttributes.ACCURACY.get(), ModAttributes.ACCURACY_MULTIPLIER.get()))) {
                        finalDamage *= (float) (1.5 + getScaledValue(player, ModAttributes.PRECISION.get(), ModAttributes.PRECISION_MULTIPLIER.get()) / 100.0);
                    }
                    data[0] += (float) player.getAttributeValue(ModAttributes.DOT_DAMAGE_MULTIPLIER.get());
                    finalDamage *= (float) (1.0 + dotPot / 100.0);
                } else {
                    double genPot = getScaledValue(player, ModAttributes.POTENCY.get(), ModAttributes.POTENCY_MULTIPLIER.get());
                    if (rollCrit(player, getScaledValue(player, ModAttributes.ACCURACY.get(), ModAttributes.ACCURACY_MULTIPLIER.get()))) {
                        finalDamage *= (float) (1.5 + getScaledValue(player, ModAttributes.PRECISION.get(), ModAttributes.PRECISION_MULTIPLIER.get()) / 100.0);
                    }
                    data[0] += (float) player.getAttributeValue(ModAttributes.ALL_DAMAGE_MULTIPLIER.get());
                    finalDamage *= (float) (1.0 + genPot / 100.0) * (float) player.getAttributeValue(ALL_DAMAGE_MULTIPLIER.get());
                }

                BonusRegistry.process(player, victim, player.level(), BonusTrigger.HURT, data);

                finalDamage *= data[0];
            }
        } else if (source instanceof LivingEntity attacker) {

            double outgoingMult = attacker.getAttributeValue(OUTGOING_DAMAGE_MULTIPLIER.get());
            finalDamage *= (float) outgoingMult;
        }
        event.setAmount(finalDamage);
    }

    @SubscribeEvent
    public void onCrit(CriticalHitEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        Player player = event.getEntity();

        boolean isVanillaCrit = event.isVanillaCritical();

        double scaledDamage = getScaledValue(player,
                ModAttributes.MELEE_PRECISION.get(),
                ModAttributes.MELEE_PRECISION_MULTIPLIER.get());
        float critBonus = (float) (1.5 + (scaledDamage / 100.0));

        if (isVanillaCrit) {
            BonusRegistry.process(player, (LivingEntity) event.getTarget(), player.level(), BonusTrigger.MELEE_CRIT);
            event.setDamageModifier(critBonus);
        } else {
            double scaledChance = getScaledValue(player,
                    ModAttributes.MELEE_ACCURACY.get(),
                    ModAttributes.MELEE_ACCURACY_MULTIPLIER.get());

            boolean isCustomCrit = player.getRandom().nextDouble() < (scaledChance + 10.0) / 100.0;

            if (isCustomCrit) {
                event.setResult(Event.Result.ALLOW);
                BonusRegistry.process(player, (LivingEntity) event.getTarget(), player.level(), BonusTrigger.MELEE_CRIT);
                event.setDamageModifier(critBonus);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingHeal(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player && !player.level().isClientSide()) {
            float[] data = { 1.0f };
            BonusRegistry.process(player, null, player.level(), BonusTrigger.HEAL, data);

            double incHeal = getScaledValue(player, ModAttributes.REJUVENATION.get(), ModAttributes.REJUVENATION_MULTIPLIER.get());
            event.setAmount((float) (event.getAmount() * (1 + incHeal/100) * data[0]));
        }
    }

    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        if (event.getProjectile() instanceof AbstractArrow arrow && arrow.getOwner() instanceof Player player) {
            HitResult result = event.getRayTraceResult();

            if (result.getType() == HitResult.Type.BLOCK) {

                Vec3 pos = event.getProjectile().position();
                float[] data = { (float)pos.x, (float)pos.y, (float)pos.z };

                if (arrow.getPersistentData().getBoolean("arr_bow")) {
                    BonusRegistry.process(player, null, player.level(), BonusTrigger.PROJECTILE_LAND_BOW, data);
                } else if (arrow.getPersistentData().getBoolean("arr_cbow")) {
                    BonusRegistry.process(player, null, player.level(), BonusTrigger.PROJECTILE_LAND_CROSSBOW, data);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        double miningSpeedBonus = event.getEntity().getAttributeValue(ModAttributes.MINING_SPEED.get());
        float original = event.getNewSpeed();
        float boosted = (float) (original * ((miningSpeedBonus)));
        event.setNewSpeed(boosted);
    }

    @SubscribeEvent
    public void onLivingFall(LivingFallEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        float jumpBoost = (float) player.getAttributeValue(ModAttributes.JUMP_BOOST.get());
        float fallThreshold = 3.0f + (3.0f * (jumpBoost - 1.0f));
        float fallDistance = event.getDistance();
        if (fallDistance <= fallThreshold) {
            if (jumpBoost > 1.5 && fallDistance > 3) { spawnExplosionOnFeet(player); }
            event.setDistance(0);
            event.setCanceled(true);
            player.fallDistance = 0;
        }

        float[] data = { event.getDistance() };
        BonusRegistry.process(player, event.getEntity(), player.level(), BonusTrigger.LAND, data);
        event.setDistance(data[0]);
    }

    @SubscribeEvent
    public static void onItemUseStart(LivingEntityUseItemEvent.Start event) {
        if (!(event.getEntity() instanceof Player player)) return;
        String type = ItemRarityUtils.getItemType(event.getItem());

        switch (type) {
            case "shield" -> BonusRegistry.process(player, null, player.level(), BonusTrigger.ITEM_USE_START_SHIELD);
            case "bow"    -> BonusRegistry.process(player, null, player.level(), BonusTrigger.ITEM_USE_START_BOW);
            case "crossbow" -> BonusRegistry.process(player, null, player.level(), BonusTrigger.ITEM_USE_START_BOW); // If they share logic
        }
    }

    @SubscribeEvent
    public static void onItemUseTick(LivingEntityUseItemEvent.Tick event) {
        if (!(event.getEntity() instanceof Player player) || player.level().isClientSide()) return;

        int ticksHeld = event.getItem().getUseDuration() - event.getDuration();

        if (ticksHeld % 2 == 0 && ticksHeld != 0) {
            String type = ItemRarityUtils.getItemType(event.getItem());
            float[] data = { (float) ticksHeld };

            switch (type) {
                case "shield" -> BonusRegistry.process(player, null, player.level(), BonusTrigger.ITEM_USE_TICK_SHIELD, data);
                case "bow"    -> BonusRegistry.process(player, null, player.level(), BonusTrigger.ITEM_USE_TICK_BOW, data);
            }
        }
    }

    @SubscribeEvent
    public static void onItemUseStop(LivingEntityUseItemEvent.Stop event) {
        if (!(event.getEntity() instanceof Player player)) return;
        String type = ItemRarityUtils.getItemType(event.getItem());

        switch (type) {
            case "shield" -> BonusRegistry.process(player, null, player.level(), BonusTrigger.ITEM_USE_END_SHIELD);
            case "bow"    -> BonusRegistry.process(player, null, player.level(), BonusTrigger.ITEM_USE_END_BOW);
        }
    }

    @SubscribeEvent
    public static void onLivingJump(LivingEvent.LivingJumpEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        double jumpBonus = player.getAttributeValue(ModAttributes.JUMP_BOOST.get());
        if (jumpBonus != 0) {
            double baseJumpHeight = 1.252;
            double gravity = 0.08;

            double newHeight = baseJumpHeight * jumpBonus;

            double requiredMotionY = Math.sqrt(2 * gravity * newHeight);

            player.setDeltaMovement(player.getDeltaMovement().x, requiredMotionY, player.getDeltaMovement().z);
        }
    }

    @SubscribeEvent
    public static void onExplosion(ExplosionEvent.Detonate event) {
        if (!(event.getExplosion().getDirectSourceEntity() instanceof Creeper creeper)) return;

        if (!creeper.getPersistentData().getBoolean("etCre")) return;

        event.getAffectedBlocks().clear();
        event.getAffectedEntities().removeIf(entity -> {
            if (!(entity instanceof LivingEntity living)) return true;

            return !isValidTarget(living);
        });
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (event.getEntity().level().isClientSide()) return;

        if (event.getSource().getEntity() instanceof Player player) {
            BonusRegistry.process(player, event.getEntity(), player.level(), BonusTrigger.KILL, new float[0]);
        }
    }

    public static boolean rollCrit(Player player, double chance) {
        return player.getRandom().nextDouble() < (chance + 10.0) / 100.0;
    }

    public static void playCritSound(Player player) {
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.PLAYER_ATTACK_CRIT, SoundSource.PLAYERS, 1.0F, 1.0F);
    }


    private double getSharpnessLevel(ItemStack stack) {
        if (stack.isEmpty() || !"sword".equals(ItemRarityUtils.getItemType(stack))) return 0.0;
        int level = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, stack);
        if (level == 0) return 0.0;
        return 1.0 + (level - 1) * 0.5;
    }

    private double getSharpnessBonus(Player player, InteractionHand hand) {
        InteractionHand actualHand = (hand == null) ? InteractionHand.MAIN_HAND : hand;
        ItemStack stack = player.getItemInHand(actualHand);
        if (stack.isEmpty()) return 0.0;
        if (!stack.isEmpty() && "sword".equals(ItemRarityUtils.getItemType(stack))) {
            return getSharpnessLevel(stack);
        }
        return 0.0;
    }

    @SubscribeEvent
    public static void onCreeperDrops(LivingDropsEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        if (!(event.getEntity() instanceof Creeper creeper)) return;
        if (!creeper.getPersistentData().getBoolean("etCre")) return;
        event.setCanceled(true);
    }

    // Explosive tendency
    @SubscribeEvent
    public static void onLivingHurtCreeper(LivingDamageEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        if (!(event.getSource().getEntity() instanceof Creeper creeper)) return;

        if (!creeper.getPersistentData().getBoolean("etCre")) return;
        if (!creeper.getPersistentData().hasUUID("pUUID")) return;
        UUID ownerUUID = creeper.getPersistentData().getUUID("pUUID");

        if (!(creeper.level() instanceof ServerLevel serverLevel)) return;
        MinecraftServer server = serverLevel.getServer();
        if (server == null) return;

        Player owner = server.getPlayerList().getPlayer(ownerUUID);
        if (owner == null) return;

        double projDmg = owner.getAttributeValue(ModAttributes.PROJECTILE_DAMAGE_MULTIPLIER.get());

        double finalDamage = 5 * projDmg;

        double totalProjDamage = getScaledValue(owner,
                ModAttributes.PROJECTILE_POTENCY.get(),
                ModAttributes.PROJECTILE_POTENCY_MULTIPLIER.get());


        double totalCritChance = getScaledValue(owner,
                ModAttributes.PROJECTILE_ACCURACY.get(),
                ModAttributes.PROJECTILE_ACCURACY_MULTIPLIER.get()) + 10.0;

        double totalCritDamage = getScaledValue(owner,
                ModAttributes.PROJECTILE_PRECISION.get(),
                ModAttributes.PROJECTILE_PRECISION_MULTIPLIER.get());

        if (owner.getRandom().nextDouble() < (totalCritChance / 100.0)) {
            finalDamage *= (1.5 + (totalCritDamage / 100.0));
        }

        finalDamage *= (1.0 + (totalProjDamage / 100.0));

        event.setAmount((float) finalDamage);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (event.player.level().isClientSide()) return;

        Player player = event.player;
        if (!(player.tickCount % 10 == 0)) return;
        if (player.getHealth() >= player.getMaxHealth()) return;
        Level level = player.level();

        List<BlessedLandReady.BlessedLandEntity> clouds = level.getEntitiesOfClass(BlessedLandReady.BlessedLandEntity.class,
                player.getBoundingBox());

        for (BlessedLandReady.BlessedLandEntity cloud : clouds) {
            player.heal(cloud.getHealAmount());
            cloud.discard();
        }
    }

    @SubscribeEvent
    public static void onArrowSpawn(EntityJoinLevelEvent event) {
        if (event.getLevel().isClientSide() || !(event.getEntity() instanceof AbstractArrow arrow)) return;
        if (!(arrow.getOwner() instanceof Player)) return;

        if (arrow.shotFromCrossbow()) {
            arrow.getPersistentData().putBoolean("arr_cbow", true);
            return;
        }


        if (arrow.getOwner() instanceof Player player) {
            if ("bow".equals(ItemRarityUtils.getItemType(player.getUseItem()))) {
                arrow.getPersistentData().putBoolean("arr_bow", true);
            }
        }
    }

    @SubscribeEvent
    public static void onEffectExpired(MobEffectEvent.Expired event) {

        var handler = EXPIRE_HANDLERS.get(event.getEffectInstance().getEffect());
        if (handler == null) return;


        if (event.getEntity() instanceof Player player) {
            handler.accept(player, event.getEffectInstance());
        }
    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getLevel().isClientSide()) return;
        Player player = event.getEntity();
        if (player.isCrouching()) return;

        PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
        if (!cache.isLightEligible()) return;
        Level world = event.getLevel();

        BlockPos clickedPos = event.getPos();
        BlockState clickedState = world.getBlockState(clickedPos);
        if (player.position().distanceToSqr(clickedPos.getX(), clickedPos.getY(), clickedPos.getZ()) > 9.0) return;

        if (!player.isSecondaryUseActive()) {
            if (clickedState.hasBlockEntity()) return;
            if (clickedState.is(net.minecraft.tags.BlockTags.DOORS) ||
                    clickedState.is(net.minecraft.tags.BlockTags.BUTTONS) ||
                    clickedState.is(net.minecraft.tags.BlockTags.TRAPDOORS)) {
                return;
            }
        }

        ItemStack main = player.getMainHandItem();

        ItemStack off = player.getOffhandItem();
        boolean isShield = "shield".equals(ItemRarityUtils.getItemType(main)) ||
                "shield".equals(ItemRarityUtils.getItemType(off));

        ItemStack stack = event.getItemStack();

        if ((!(stack.getItem() instanceof PickaxeItem)) || isShield) return;

        var pos = event.getPos().relative(event.getFace());
        BlockState torchState;

        if (event.getFace() == net.minecraft.core.Direction.UP) {
            torchState = Blocks.TORCH.defaultBlockState();
        } else if (event.getFace() == net.minecraft.core.Direction.DOWN) {
            return;
        } else {
            torchState = Blocks.WALL_TORCH.defaultBlockState()
                    .setValue(net.minecraft.world.level.block.WallTorchBlock.FACING, event.getFace());
        }

        if (world.getBlockState(pos).canBeReplaced() && torchState.canSurvive(world, pos)) {
            world.setBlockAndUpdate(pos, torchState);

            stack.hurtAndBreak(10, player, (p) -> p.broadcastBreakEvent(event.getHand()));
            world.playSound(null, pos, SoundEvents.WOOD_PLACE, player.getSoundSource(), 1.0f, 1.0f);

            player.swing(event.getHand(), true);
            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.SUCCESS);
        }
    }

    @SubscribeEvent
    public static void onRightClickCrop(PlayerInteractEvent.RightClickBlock event) {
        if (event.getEntity().level().isClientSide) return;

        Player player = event.getEntity();

        PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
        if (!cache.isRegrowEligible()) return;

        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        ItemStack heldItem = event.getItemStack();

        if (!(heldItem.getItem() instanceof HoeItem)) return;

        Block block = state.getBlock();
        boolean handled = false;
        BlockState newState = null;

        if (block instanceof CropBlock crop && crop.isMaxAge(state)) {
            newState = crop.getStateForAge(0);
            handled = true;
        }
        else if (block instanceof NetherWartBlock && state.getValue(NetherWartBlock.AGE) >= 3) {
            newState = state.setValue(NetherWartBlock.AGE, 0);
            handled = true;
        }
        else if (block instanceof CocoaBlock && state.getValue(CocoaBlock.AGE) >= 2) {
            newState = state.setValue(CocoaBlock.AGE, 0);
            handled = true;
        }

        if (handled && !level.isClientSide) {
            ServerLevel serverLevel = (ServerLevel) level;

            Block.getDrops(state, serverLevel, pos, null, player, heldItem).forEach(stack -> {
                player.getInventory().add(stack);
                if (!stack.isEmpty()) {
                    Block.popResource(level, pos, stack);
                }
            });

            level.setBlock(pos, newState, 3);
            level.playSound(null, pos, SoundEvents.CROP_BREAK, SoundSource.BLOCKS, 1.0f, 1.0f);
            heldItem.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(event.getHand()));

            player.swing(event.getHand(), true);
            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.SUCCESS);
        }
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.getPlayer().level().isClientSide) return;
        Player player = event.getPlayer();
        PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
        if (!(cache.isFlameEligible() || cache.isForgeEligible())) return;

        if (player.isCrouching() || player.isCreative()) return;

        BlockState state = event.getState();

        if (!player.hasCorrectToolForDrops(state)) return;

        if (cache.isForgeEligible()) {
            if (!state.is(net.minecraftforge.common.Tags.Blocks.ORES)) return;
            if (state.is(net.minecraft.world.level.block.Blocks.NETHER_QUARTZ_ORE)) return;
        }

        Level level = (Level) event.getLevel();
        BlockPos pos = event.getPos();
        ItemStack tool = player.getMainHandItem();

        if (player.isCreative() || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, tool) > 0) return;
        List<ItemStack> drops = Block.getDrops(state, (ServerLevel) level, pos, null, player, tool);

        boolean smeltedAny = false;
        List<ItemStack> finalDrops = new ArrayList<>();

        for (ItemStack stack : drops) {
            ItemStack smeltedResult = getSmeltingResult(level, stack);

            if (!smeltedResult.isEmpty()) {
                ItemStack result = smeltedResult.copy();
                result.setCount(stack.getCount());
                finalDrops.add(result);
                smeltedAny = true;
            } else {
                finalDrops.add(stack);
            }
        }

        if (smeltedAny) {
            event.setCanceled(true);
            level.destroyBlock(pos, false);

            level.removeBlock(pos, false);

            for (ItemStack drop : finalDrops) {
                if (!player.getInventory().add(drop)) {
                    Block.popResource(level, pos, drop);
                }
            }
            ((ServerLevel)level).sendParticles(ParticleTypes.FLAME, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 5, 0.2, 0.2, 0.2, 0.05);
        }
    }

    private static ItemStack getSmeltingResult(Level level, ItemStack stack) {
        return level.getRecipeManager()
                .getRecipeFor(RecipeType.SMELTING, new SimpleContainer(stack), level)
                .map(recipe -> recipe.getResultItem(level.registryAccess()))
                .orElse(ItemStack.EMPTY);
    }

    @SubscribeEvent
    public static void onGrapple(PlayerInteractEvent.RightClickItem event) {
        if (event.getEntity().level().isClientSide) return;
        Player player = event.getEntity();
        PlayerBonusCache cache = player.getCapability(BonusCapabilityProvider.PLAYER_BONUS_CACHE).orElse(null);
        if (!cache.isHookEligible()) return;
        if ((player.getHealth() <= 6.0F || (player.fishing == null))) return;

        FishingHook hook = player.fishing;
        Level level = player.level();

        boolean hitEntity = hook.getHookedIn() != null;

        Vec3 hookPos = hook.position();
        BlockHitResult hitResult = level.clip(new ClipContext(
                hookPos.add(0, 0.2, 0),
                hookPos.subtract(0, 0.2, 0),
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,
                hook
        ));

        boolean hitBlock = hitResult.getType() == HitResult.Type.BLOCK;

        if (hitEntity || hitBlock) {

            if (hook.getY() > player.getY() + 0.5) {

                CompoundTag data = player.getPersistentData();

                data.putDouble("hook_x", hook.getX());
                data.putDouble("hook_y", hook.getY() + 1.2);
                data.putDouble("hook_z", hook.getZ());
                player.addEffect(new MobEffectInstance(ModEffects.DEPTHS_CURRENT.get(), 140, 0, false, false, false));

                ItemStack rod = player.getItemInHand(event.getHand());

                if (rod.getItem() instanceof net.minecraft.world.item.FishingRodItem) {
                    int refundAmount = hitEntity ? 3 : 2;
                    int currentDamage = rod.getDamageValue();
                    rod.setDamageValue(Math.max(0, currentDamage - refundAmount));
                }

                EffectUtils.playSound(player, SoundEvents.FISHING_BOBBER_RETRIEVE, 1f, 1f);
            }
        }
    }
}
