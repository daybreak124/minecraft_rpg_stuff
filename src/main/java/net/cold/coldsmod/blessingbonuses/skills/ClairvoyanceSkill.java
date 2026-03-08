package net.cold.coldsmod.blessingbonuses.skills;

public class ClairvoyanceSkill {

//    private static final int CHARGE_TICKS_REQUIRED = 80;
//
//    @SubscribeEvent
//    public static void onBowStart(LivingEntityUseItemEvent.Start event) {
//        if (event.getEntity().level().isClientSide() || !(event.getEntity() instanceof Player player)) return;
//        if (!player.hasEffect(ModEffects.CLAIRVOYANCE_READY.get())) return;
//        if (!"bow".equals(ItemRarityUtils.getItemType(event.getItem()))) return;
//        double drawSpeed = AttributeApplier.getScaledValue(player, ModAttributes.NOCK_HASTE.get(), ModAttributes.NOCK_HASTE_MULTIPLIER.get());
//
//        double chargeReductionMultiplier = 1 - (drawSpeed / (drawSpeed + 100.0));
//        int finalReqTicks = (int) (CHARGE_TICKS_REQUIRED * chargeReductionMultiplier);
//
//        player.getPersistentData().putInt("ClairvoyanceTarget", finalReqTicks);
//    }
//
//    @SubscribeEvent
//    public static void onBowTick(LivingEntityUseItemEvent.Tick event) {
//        if (event.getEntity().level().isClientSide() || !(event.getEntity() instanceof Player player)) return;
//
//        CompoundTag data = player.getPersistentData();
//        if (!data.contains("ClairvoyanceTarget")) return;
//
//        int targetTicks = data.getInt("ClairvoyanceTarget");
//
//        if (player.getTicksUsingItem() >= targetTicks) {
//            data.putBoolean("Clairvoyance", true);
//            data.remove("ClairvoyanceTarget");
//
//            player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
//                    ModSounds.CLAIRVOYANCE.get(), SoundSource.PLAYERS, 1.2F, 1.0F);
//        }
//    }
//
//    @SubscribeEvent
//    public static void onArrowSpawn(EntityJoinLevelEvent event) {
//        if (event.getLevel().isClientSide() || !(event.getEntity() instanceof AbstractArrow arrow)) return;
//        if (!(arrow.getOwner() instanceof Player player)) return;
//
//        CompoundTag data = player.getPersistentData();
//        if (data.getBoolean("Clairvoyance")) {
//
//            data.putBoolean("Clairvoyance", false);
//
//            data.remove("ClairvoyanceTarget");
//        }
//    }
//
//    @SubscribeEvent
//    public static void onBowFinish(LivingEntityUseItemEvent.Finish event) {
//        if (!"bow".equals(ItemRarityUtils.getItemType(event.getItem()))) return;
//        event.getEntity().getPersistentData().remove("ClairvoyanceTarget");
//    }
}