package net.cold.coldsmod.capabilities_and_blessings.Capabilities;

import java.util.*;

public class PlayerBonusCache {
    private final Map<BonusTrigger, List<ProcInstance>> buckets = new EnumMap<>(BonusTrigger.class);
    private final Set<Integer> persistentIds = new HashSet<>(); // Track IDs for saving

    public record ProcInstance(IBonusLogic logic, boolean flag, float[] data) {}

    public void unlock(int id, float... params) {
        this.unlock(id, false, params);
    }

    public void unlock(int id, boolean flag, float... params) {
        if (persistentIds.contains(id)) return;
        BonusEntry entry = BonusRegistry.ALL_BONUSES.get(id);
        buckets.computeIfAbsent(entry.trigger(), k -> new ArrayList<>())
                .add(new ProcInstance(entry.logic(), flag, params));
        persistentIds.add(id);
    }

    public void remove(int id) {
        if (!persistentIds.remove(id)) return;

        BonusEntry entry = BonusRegistry.ALL_BONUSES.get(id);
        if (entry == null) return;

        List<ProcInstance> bucket = buckets.get(entry.trigger());
        if (bucket != null) {
            bucket.removeIf(instance -> instance.logic() == entry.logic());
            if (bucket.isEmpty()) {
                buckets.remove(entry.trigger());
            }
        }
    }

    public Set<Integer> getPersistentIds() { return persistentIds; }
    public List<ProcInstance> get(BonusTrigger trigger) {
        return buckets.getOrDefault(trigger, Collections.emptyList());
    }

    public void clearAll() {
        this.buckets.clear();
        this.persistentIds.clear();
    }

    public void copyFrom(PlayerBonusCache other) {
        this.clearAll();
        for (Integer id : other.getPersistentIds()) {
            this.unlock(id);
        }
        this.dfaAirborne = other.dfaAirborne;
        this.dfaJump = other.dfaJump;
        this.berserkStage1 = other.berserkStage1;
        this.berserkStage0 = other.berserkStage0;
        this.berserkEnraged = other.berserkEnraged;
        this.bronzewoodReady = other.bronzewoodReady;
        this.clairvoyanceReady = other.clairvoyanceReady;
        this.clairvoyanceTarget = other.clairvoyanceTarget;
        this.clairvoyanceHit = other.clairvoyanceHit;
        this.exploitReady = other.exploitReady;
        this.retaliateReady = other.retaliateReady;
        this.retaliateActive = other.retaliateActive;
        this.retaliateStack = other.retaliateStack;
        this.bastionReady = other.bastionReady;
        this.injection = other.injection;
        this.deceptionReady = other.deceptionReady;
        this.dfaQuantumSynergy = other.dfaQuantumSynergy;
        this.dfaQuantumSynergized = other.dfaQuantumSynergized;
        this.lifeTouchReady = other.lifeTouchReady;
        this.explosiveStack = other.explosiveStack;
        this.explosiveTimerActive = other.explosiveTimerActive;
        this.solaraActive = other.solaraActive;
        this.evadeActive = other.evadeActive;
        this.nimbleEquipped = other.nimbleEquipped;
        this.combatantsAidRecall = other.combatantsAidRecall;
        this.vortexReady = other.vortexReady;
        this.parryReady = other.parryReady;
        this.parryEligible = other.parryEligible;
        this.blessedLandReady = other.blessedLandReady;
        this.hookEligible = other.hookEligible;
        this.flameEligible = other.flameEligible;
        this.forgeEligible = other.forgeEligible;
        this.lightEligible = other.lightEligible;
        this.regrowEligible = other.regrowEligible;
        this.bloodthirstEnhanced = other.bloodthirstEnhanced;
        this.chainLightningEnhanced = other.chainLightningEnhanced;
        this.bronzewoodEnhanced = other.bronzewoodEnhanced;
        this.temptingBuff = other.temptingBuff;
    }

    private boolean temptingBuff = false;
    public void setTemptingBuff(boolean active) { this.temptingBuff = active; }
    public boolean isTemptingBuff() { return this.temptingBuff; }

    private boolean hookEligible = false;
    public void setHookEligible(boolean active) { this.hookEligible = active; }
    public boolean isHookEligible() { return this.hookEligible; }

    private boolean flameEligible = false;
    public void setFlameEligible(boolean active) { this.flameEligible = active; }
    public boolean isFlameEligible() { return this.flameEligible; }

    private boolean forgeEligible = false;
    public void setForgeEligible(boolean active) { this.forgeEligible = active; }
    public boolean isForgeEligible() { return this.forgeEligible; }

    private boolean lightEligible = false;
    public void setLightEligible(boolean active) { this.lightEligible = active; }
    public boolean isLightEligible() { return this.lightEligible; }

    private boolean regrowEligible = false;
    public void setRegrowEligible(boolean active) { this.regrowEligible = active; }
    public boolean isRegrowEligible() { return this.regrowEligible; }

    private boolean dfaAirborne = false;
    public void setDFAAirborne(boolean active) { this.dfaAirborne = active; }
    public boolean isDFAAirborne() { return this.dfaAirborne; }

    private boolean dfaJump = false;
    public void setDfaJump(boolean active) { this.dfaJump = active; }
    public boolean isDfaJump() { return this.dfaJump; }

    private boolean berserkStage1 = false;
    public void setBerserkStage1(boolean active) { this.berserkStage1 = active; }
    public boolean isBerserkStage1() { return this.berserkStage1; }

    private boolean berserkStage0 = false;
    public void setBerserkStage0(boolean active) { this.berserkStage0 = active; }
    public boolean isBerserkStage0() { return this.berserkStage0; }

    private boolean berserkEnraged = false;
    public void setBerserkEnraged(boolean active) { this.berserkEnraged = active; }
    public boolean isBerserkEnraged() { return this.berserkEnraged; }

    private boolean bronzewoodReady = false;
    public void setBronzewoodReady(boolean active) { this.bronzewoodReady = active; }
    public boolean isBronzewoodReady() { return this.bronzewoodReady; }

    private boolean clairvoyanceReady = false;
    public void setClairvoyanceReady(boolean active) { this.clairvoyanceReady = active; }
    public boolean isClairvoyanceReady() { return this.clairvoyanceReady; }

    private double clairvoyanceTarget = 0;
    public void setClairvoyanceTarget(double target) { this.clairvoyanceTarget = target; }
    public double getClairvoyanceTarget() { return this.clairvoyanceTarget; }

    private boolean clairvoyanceHit = false;
    public void setClairvoyanceHit(boolean active) { this.clairvoyanceHit = active; }
    public boolean isClairvoyanceHit() { return this.clairvoyanceHit; }

    private boolean exploitReady = false;
    public void setExploitReady(boolean active) { this.exploitReady = active; }
    public boolean isExploitReady() { return this.exploitReady; }

    private boolean retaliateReady = false;
    public void setRetaliateReady(boolean active) { this.retaliateReady = active; }
    public boolean isRetaliateReady() { return this.retaliateReady; }

    private boolean retaliateActive = false;
    public void setRetaliateActive(boolean active) { this.retaliateActive = active; }
    public boolean isRetaliateActive() { return this.retaliateActive; }

    private int retaliateStack = 0;
    public void setRetaliateStack(int stack) { this.retaliateStack = stack; }
    public int getRetaliateStack() { return this.retaliateStack; }

    private boolean bastionReady = false;
    public void setBastionReady(boolean active) { this.bastionReady = active; }
    public boolean isBastionReady() { return this.bastionReady; }

    private boolean injection = false;
    public void setInjection(boolean active) { this.injection = active; }
    public boolean isInjection() { return this.injection; }

    private boolean deceptionReady = false;
    public void setDeceptionReady(boolean active) { this.deceptionReady = active; }
    public boolean isDeceptionReady() { return this.deceptionReady; }

    // on equip
    private boolean dfaQuantumSynergy = false;
    public void setDfaQuantumSynergy(boolean active) { this.dfaQuantumSynergy = active; }
    public boolean isDfaQuantumSynergy() { return this.dfaQuantumSynergy; }

    private boolean dfaQuantumSynergized = false;
    public void setDfaQuantumSynergized(boolean active) { this.dfaQuantumSynergized = active; }
    public boolean isDfaQuantumSynergized() { return this.dfaQuantumSynergized; }

    private boolean lifeTouchReady = false;
    public void setLifeTouchReady(boolean active) { this.lifeTouchReady = active; }
    public boolean isLifeTouchReady() { return this.lifeTouchReady; }

    private int explosiveStack = 0;
    public void setExplosiveStack(int stack) { this.explosiveStack = stack; }
    public int getExplosiveStack() { return this.explosiveStack; }

    private boolean explosiveTimerActive = false;
    public void setExplosiveTimerActive(boolean active) { this.explosiveTimerActive = active; }
    public boolean isExplosiveTimerActive() { return this.explosiveTimerActive; }

    // on equip
    private boolean solaraActive = false;
    public void setSolaraActive(boolean active) { this.solaraActive = active; }
    public boolean isSolaraActive() { return this.solaraActive; }

    private boolean evadeActive = false;
    public void setEvadeActive(boolean active) { this.evadeActive = active; }
    public boolean isEvadeActive() { return this.evadeActive; }

    private boolean nimbleEquipped = false;
    public void setNimbleEquipped(boolean active) { this.nimbleEquipped = active; }
    public boolean isNimbleEquipped() { return this.nimbleEquipped; }

    private boolean combatantsAidRecall = false;
    public void setCombatantsAidRecall(boolean active) { this.combatantsAidRecall = active; }
    public boolean isCombatantsAidRecall() { return this.combatantsAidRecall; }

    private boolean vortexReady = false;
    public void setVortexReady(boolean active) { this.vortexReady = active; }
    public boolean isVortexReady() { return this.vortexReady; }

    private boolean parryReady = false;
    public void setParryReady(boolean active) { this.parryReady = active; }
    public boolean isParryReady() { return this.parryReady; }

    private boolean parryEligible = false;
    public void setParryEligible(boolean active) { this.parryEligible = active; }
    public boolean isParryEligible() { return this.parryEligible; }

    private boolean blessedLandReady = false;
    public void setBlessedLandReady(boolean active) { this.blessedLandReady = active; }
    public boolean isBlessedLandReady() { return this.blessedLandReady; }

    private boolean bronzewoodEnhanced = false;
    public void setBronzewoodEnhanced(boolean active) { this.bronzewoodEnhanced = active; }
    public boolean isBronzewoodEnhanced() { return this.bronzewoodEnhanced; }

    private boolean chainLightningEnhanced = false;
    public void setChainLightningEnhanced(boolean active) { this.chainLightningEnhanced = active; }
    public boolean isChainLightningEnhanced() { return this.chainLightningEnhanced; }

    private boolean bloodthirstEnhanced = false;
    public void setBloodthirstEnhanced(boolean active) { this.bloodthirstEnhanced = active; }
    public boolean isBloodthirstEnhanced() { return this.bloodthirstEnhanced; }

}