package net.cold.coldsmod.stat;

public class CustomStats {
    public int str;
    public int fort;
    public int dex;
    public int con;
    public int perc;
    public int intelligence;
    public int wis;
    public double armor;
    public double armorToughness;
    public double maxHealth;
    public double knockbackResist;
    public double debuffResist;
    public double damage;
    public double attackSpeed;
    public double critChance;
    public double critDamage;
    public double meleeCritChance;
    public double meleeCritDamage;
    public double projectileCritChance;
    public double projectileCritDamage;
    public double moveSpeed;
    public double swimSpeed;
    public double xpGain;
    public double projectileDamage;
    public double drawSpeed;
    public double blockReach;
    public double entityReach;
    public double luck;
    public double stepHeight;
    public double jumpBoost;
    public double miningSpeed;
    public double armorMultiplier;
    public double toughnessMultiplier;
    public double healthMultiplier;
    public double damageMultiplier;
    public double attackSpeedMultiplier;
    public double critChanceMultiplier;
    public double critDamageMultiplier;
    public double meleeCritChanceMultiplier;
    public double meleeCritDamageMultiplier;
    public double projectileCritChanceMultiplier;
    public double projectileCritDamageMultiplier;
    public double projectileDamageMultiplier;
    public double drawSpeedMultiplier;
    public double meleeDamage;
    public double meleeDamageMultiplier;

    public CustomStats() {
        this.str = 0;
        this.fort = 0;
        this.dex = 0;
        this.con = 0;
        this.perc = 0;
        this.intelligence = 0;
        this.wis = 0;
        this.armor = 0.0;
        this.armorToughness = 0.0;
        this.maxHealth = 0.0;
        this.knockbackResist = 0.0;
        this.debuffResist = 0.0;
        this.damage = 0.0;
        this.meleeDamage = 0.0;
        this.attackSpeed = 0.0;
        this.critChance = 0.0;
        this.critDamage = 0.0;
        this.meleeCritChance = 0.0;
        this.meleeCritDamage = 0.0;
        this.projectileCritChance = 0.0;
        this.projectileCritDamage = 0.0;
        this.moveSpeed = 0.0;
        this.swimSpeed = 0.0;
        this.xpGain = 0.0;
        this.projectileDamage = 0.0;
        this.drawSpeed = 0.0;
        this.blockReach = 0.0;
        this.entityReach = 0.0;
        this.luck = 0.0;
        this.stepHeight = 0.0;
        this.jumpBoost = 0.0;
        this.miningSpeed = 0.0;
        this.armorMultiplier = 0.0;
        this.toughnessMultiplier = 0.0;
        this.healthMultiplier = 0.0;
        this.damageMultiplier = 0.0;
        this.attackSpeedMultiplier = 0.0;
        this.critChanceMultiplier = 0.0;
        this.critDamageMultiplier = 0.0;
        this.meleeCritChanceMultiplier = 0.0;
        this.meleeCritDamageMultiplier = 0.0;
        this.projectileCritChanceMultiplier = 0.0;
        this.projectileCritDamageMultiplier = 0.0;
        this.projectileDamageMultiplier = 0.0;
        this.drawSpeedMultiplier = 0.0;
        this.meleeDamageMultiplier = 0.0;
    }

    public CustomStats(int str, int fort, int dex, int intelligence, int perc, int con, int wis,
                       double armor, double armorToughness, double maxHealth,
                       double knockbackResist, double damage, double debuffResist,
                       double attackSpeed, double critChance, double critDamage,
                       double meleeCritChance, double meleeCritDamage,
                       double projectileCritChance, double projectileCritDamage,
                       double moveSpeed, double swimSpeed, double xpGain,
                       double projectileDamage, double drawSpeed,
                       double blockReach, double entityReach, double luck,
                       double stepHeight, double jumpBoost, double miningSpeed,
                       double armorMultiplier, double toughnessMultiplier,
                       double healthMultiplier, double damageMultiplier, double attackSpeedMultiplier,
                       double critChanceMultiplier, double critDamageMultiplier,
                       double meleeCritChanceMultiplier, double meleeCritDamageMultiplier,
                       double projectileCritChanceMultiplier, double projectileCritDamageMultiplier,
                       double projectileDamageMultiplier,
                       double drawSpeedMultiplier,
                       double meleeDamage, double meleeDamageMultiplier) {
        this.str = str;
        this.fort = fort;
        this.dex = dex;
        this.intelligence = intelligence;
        this.con = con;
        this.perc = perc;
        this.wis = wis;
        this.armor = armor;
        this.armorToughness = armorToughness;
        this.maxHealth = maxHealth;
        this.knockbackResist = knockbackResist;
        this.debuffResist = debuffResist;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.critChance = critChance;
        this.critDamage = critDamage;
        this.meleeCritChance = meleeCritChance;
        this.meleeCritDamage = meleeCritDamage;
        this.projectileCritChance = projectileCritChance;
        this.projectileCritDamage = projectileCritDamage;
        this.moveSpeed = moveSpeed;
        this.swimSpeed = swimSpeed;
        this.xpGain = xpGain;
        this.projectileDamage = projectileDamage;
        this.drawSpeed = drawSpeed;
        this.blockReach = blockReach;
        this.entityReach = entityReach;
        this.luck = luck;
        this.stepHeight = stepHeight;
        this.jumpBoost = jumpBoost;
        this.miningSpeed = miningSpeed;
        this.toughnessMultiplier = toughnessMultiplier;
        this.healthMultiplier = healthMultiplier;
        this.damageMultiplier = damageMultiplier;
        this.attackSpeedMultiplier = attackSpeedMultiplier;
        this.critChanceMultiplier = critChanceMultiplier;
        this.critDamageMultiplier = critDamageMultiplier;
        this.meleeCritChanceMultiplier = meleeCritChanceMultiplier;
        this.meleeCritDamageMultiplier = meleeCritDamageMultiplier;
        this.projectileCritChanceMultiplier = projectileCritChanceMultiplier;
        this.projectileCritDamageMultiplier = projectileCritDamageMultiplier;
        this.projectileDamageMultiplier = projectileDamageMultiplier;
        this.drawSpeedMultiplier = drawSpeedMultiplier;
        this.armorMultiplier = armorMultiplier;
        this.meleeDamage = meleeDamage;
        this.meleeDamageMultiplier = meleeDamageMultiplier;
    }

    public int getStr() { return str; }
    public int getFort() { return fort; }
    public int getDex() { return dex; }
    public int getPerc() { return perc; }
    public int getCon() { return con; }
    public int getIntelligence() { return intelligence; }
    public int getWis() { return wis; }
    public double getArmor() { return armor; }
    public double getArmorToughness() { return armorToughness; }
    public double getMaxHealth() { return maxHealth; }
    public double getKnockbackResist() { return knockbackResist; }
    public double getDebuffResist() { return debuffResist; }
    public double getDamage() { return damage; }
    public double getAttackSpeed() { return attackSpeed; }
    public double getCritChance() { return critChance; }
    public double getCritDamage() { return critDamage; }
    public double getMeleeCritChance() { return meleeCritChance; }
    public double getMeleeCritDamage() { return meleeCritDamage; }
    public double getProjectileCritChance() { return projectileCritChance; }
    public double getProjectileCritDamage() { return projectileCritDamage; }
    public double getMoveSpeed() { return moveSpeed; }
    public double getSwimSpeed() { return swimSpeed; }
    public double getXpGain() { return xpGain; }
    public double getProjectileDamage() { return projectileDamage; }
    public double getDrawSpeed() { return drawSpeed; }
    public double getBlockReach() { return blockReach; }
    public double getEntityReach() { return entityReach; }
    public double getLuck() { return luck; }
    public double getStepHeight() { return stepHeight; }
    public double getJumpBoost() { return jumpBoost; }
    public double getMiningSpeed() { return miningSpeed; }
    public double getArmorMultiplier() { return armorMultiplier; }
    public double getToughnessMultiplier() { return toughnessMultiplier; }
    public double getHealthMultiplier() { return healthMultiplier; }
    public double getMeleeDamageMultiplier() { return meleeDamageMultiplier; }
    public double getDamageMultiplier() { return damageMultiplier; }
    public double getAttackSpeedMultiplier() { return attackSpeedMultiplier; }
    public double getCritChanceMultiplier() { return critChanceMultiplier; }
    public double getCritDamageMultiplier() { return critDamageMultiplier; }
    public double getMeleeCritChanceMultiplier() { return meleeCritChanceMultiplier; }
    public double getMeleeCritDamageMultiplier() { return meleeCritDamageMultiplier; }
    public double getProjectileCritChanceMultiplier() { return projectileCritChanceMultiplier; }
    public double getProjectileCritDamageMultiplier() { return projectileCritDamageMultiplier; }
    public double getProjectileDamageMultiplier() { return projectileDamageMultiplier; }
    public double getDrawSpeedMultiplier() { return drawSpeedMultiplier; }
    public double getMeleeDamage() { return meleeDamage; }



    public void setStr(int str) { this.str = str; }
    public void setFort(int fort) { this.fort = fort; }
    public void setDex(int dex) { this.dex = dex; }
    public void setCon(int con) { this.con = con; }
    public void setPerc(int perc) { this.perc = perc; }
    public void setIntelligence(int intelligence) { this.intelligence = intelligence; }
    public void setWis(int wis) { this.wis = wis; }
    public void setArmor(double armor) { this.armor = armor; }
    public void setArmorToughness(double armorToughness) { this.armorToughness = armorToughness; }
    public void setKnockbackResist(double knockbackResist) { this.knockbackResist = knockbackResist; }
    public void setDebuffResist(double debuffResist) { this.debuffResist = debuffResist; }
    public void setMaxHealth(double maxHealth) { this.maxHealth = maxHealth; }
    public void setDamage(double damage) { this.damage = damage; }
    public void setAttackSpeed(double attackSpeed) { this.attackSpeed = attackSpeed; }
    public void setCritChance(double critChance) { this.critChance = critChance; }
    public void setCritDamage(double critDamage) { this.critDamage = critDamage; }
    public void setMeleeCritChance(double meleeCritChance) { this.meleeCritChance = meleeCritChance; }
    public void setMeleeCritDamage(double meleeCritDamage) { this.meleeCritDamage = meleeCritDamage; }
    public void setProjectileCritChance(double projectileCritChance) { this.projectileCritChance = projectileCritChance; }
    public void setProjectileCritDamage(double projectileCritDamage) { this.projectileCritDamage = projectileCritDamage; }
    public void setMoveSpeed(double moveSpeed) { this.moveSpeed = moveSpeed; }
    public void setSwimSpeed(double swimSpeed) { this.swimSpeed = swimSpeed; }
    public void setXpGain(double xpGain) { this.xpGain = xpGain; }
    public void setProjectileDamage(double projectileDamage) { this.projectileDamage = projectileDamage; }
    public void setDrawSpeed(double drawSpeed) { this.drawSpeed = drawSpeed; }
    public void setBlockReach(double blockReach) { this.blockReach = blockReach; }
    public void setEntityReach(double entityReach) { this.entityReach = entityReach; }
    public void setLuck(double luck) { this.luck = luck; }
    public void setStepHeight(double stepHeight) { this.stepHeight = stepHeight; }
    public void setJumpBoost(double jumpBoost) { this.jumpBoost = jumpBoost; }
    public void setMiningSpeed(double miningSpeed) { this.miningSpeed = miningSpeed; }
    public void setArmorMultiplier(double armorMultiplier) { this.armorMultiplier = armorMultiplier; }
    public void setToughnessMultiplier(double toughnessMultiplier) { this.toughnessMultiplier = toughnessMultiplier; }
    public void setHealthMultiplier(double healthMultiplier) { this.healthMultiplier = healthMultiplier; }
    public void setDamageMultiplier(double damageMultiplier) { this.damageMultiplier = damageMultiplier; }
    public void setMeleeDamageMultiplier(double meleeDamageMultiplier) { this.meleeDamageMultiplier = meleeDamageMultiplier; }
    public void setAttackSpeedMultiplier(double attackSpeedMultiplier) { this.attackSpeedMultiplier = attackSpeedMultiplier; }
    public void setCritChanceMultiplier(double critChanceMultiplier) { this.critChanceMultiplier = critChanceMultiplier; }
    public void setCritDamageMultiplier(double critDamageMultiplier) { this.critDamageMultiplier = critDamageMultiplier; }
    public void setMeleeCritChanceMultiplier(double meleeCritChanceMultiplier) { this.meleeCritChanceMultiplier = meleeCritChanceMultiplier; }
    public void setMeleeCritDamageMultiplier(double meleeCritDamageMultiplier) { this.meleeCritDamageMultiplier = meleeCritDamageMultiplier; }
    public void setProjectileCritChanceMultiplier(double projectileCritChanceMultiplier) { this.projectileCritChanceMultiplier = projectileCritChanceMultiplier; }
    public void setProjectileCritDamageMultiplier(double projectileCritDamageMultiplier) { this.projectileCritDamageMultiplier = projectileCritDamageMultiplier; }
    public void setProjectileDamageMultiplier(double projectileDamageMultiplier) { this.projectileDamageMultiplier = projectileDamageMultiplier; }
    public void setDrawSpeedMultiplier(double drawSpeedMultiplier) { this.drawSpeedMultiplier = drawSpeedMultiplier; }
    public void setMeleeDamage(double meleeDamage) { this.meleeDamage = meleeDamage; }


    public void add(CustomStats other) {
        this.str += other.str;
        this.fort += other.fort;
        this.dex += other.dex;
        this.perc += other.perc;
        this.con += other.con;
        this.intelligence += other.intelligence;
        this.wis += other.wis;

        this.armor += other.armor;
        this.armorToughness += other.armorToughness;
        this.maxHealth += other.maxHealth;

        this.knockbackResist += other.knockbackResist;
        this.debuffResist += other.debuffResist;

        this.damage += other.damage;
        this.critChance += other.critChance;
        this.critDamage += other.critDamage;

        this.meleeDamage += other.meleeDamage;
        this.attackSpeed += other.attackSpeed;
        this.meleeCritChance += other.meleeCritChance;
        this.meleeCritDamage += other.meleeCritDamage;

        this.projectileDamage += other.projectileDamage;
        this.projectileCritChance += other.projectileCritChance;
        this.projectileCritDamage += other.projectileCritDamage;

        this.moveSpeed += other.moveSpeed;
        this.swimSpeed += other.swimSpeed;
        this.drawSpeed += other.drawSpeed;

        this.xpGain += other.xpGain;
        this.blockReach += other.blockReach;
        this.entityReach += other.entityReach;
        this.luck += other.luck;
        this.stepHeight += other.stepHeight;
        this.jumpBoost += other.jumpBoost;
        this.miningSpeed += other.miningSpeed;

        this.armorMultiplier += other.armorMultiplier;
        this.toughnessMultiplier += other.toughnessMultiplier;
        this.healthMultiplier += other.healthMultiplier;
        this.damageMultiplier += other.damageMultiplier;
        this.meleeDamageMultiplier += other.meleeDamageMultiplier;
        this.attackSpeedMultiplier += other.attackSpeedMultiplier;
        this.critChanceMultiplier += other.critChanceMultiplier;
        this.critDamageMultiplier += other.critDamageMultiplier;
        this.meleeCritChanceMultiplier += other.meleeCritChanceMultiplier;
        this.meleeCritDamageMultiplier += other.meleeCritDamageMultiplier;
        this.projectileCritChanceMultiplier += other.projectileCritChanceMultiplier;
        this.projectileCritDamageMultiplier += other.projectileCritDamageMultiplier;
        this.projectileDamageMultiplier += other.projectileDamageMultiplier;
        this.drawSpeedMultiplier += other.drawSpeedMultiplier;
    }

    private CustomStats(Builder builder) {
        this.damage = builder.damage;
        this.meleeDamage = builder.meleeDamage;
        this.critChance = builder.critChance;
        this.critDamage = builder.critDamage;
        this.meleeCritChance = builder.meleeCritChance;
        this.meleeCritDamage = builder.meleeCritDamage;
        this.projectileCritChance = builder.projectileCritChance;
        this.projectileCritDamage = builder.projectileCritDamage;
        this.moveSpeed = builder.moveSpeed;
        this.attackSpeed = builder.attackSpeed;
        this.fort = builder.fort;
        this.perc = builder.perc;
        this.con = builder.con;
        this.armor = builder.armor;
        this.maxHealth = builder.maxHealth;
        this.str = builder.str;
        this.dex = builder.dex;
        this.wis = builder.wis;
        this.knockbackResist = builder.knockbackResist;
        this.drawSpeed = builder.drawSpeed;
        this.armorToughness = builder.armorToughness;
        this.projectileDamage = builder.projectileDamage;
        this.swimSpeed = builder.swimSpeed;
        this.blockReach = builder.blockReach;
        this.entityReach = builder.entityReach;
        this.debuffResist = builder.debuffResist;
        this.luck = builder.luck;
        this.stepHeight = builder.stepHeight;
        this.jumpBoost = builder.jumpBoost;
        this.xpGain = builder.xpGain;
        this.miningSpeed = builder.miningSpeed;
        this.armorMultiplier = builder.armorMultiplier;
        this.toughnessMultiplier = builder.toughnessMultiplier;
        this.healthMultiplier = builder.healthMultiplier;
        this.damageMultiplier = builder.damageMultiplier;
        this.attackSpeedMultiplier = builder.speedMultiplier;
        this.critChanceMultiplier = builder.critChanceMultiplier;
        this.critDamageMultiplier = builder.critDamageMultiplier;
        this.meleeCritChanceMultiplier = builder.meleeCritChanceMultiplier;
        this.meleeCritDamageMultiplier = builder.meleeCritDamageMultiplier;
        this.projectileCritChanceMultiplier = builder.projectileCritChanceMultiplier;
        this.projectileCritDamageMultiplier = builder.projectileCritDamageMultiplier;
        this.projectileDamageMultiplier = builder.projectileDamageMultiplier;
        this.drawSpeedMultiplier = builder.drawSpeedMultiplier;
        this.meleeDamageMultiplier = builder.meleeDamageMultiplier;
    }


    public static class Builder {
        private double damage = 0;
        private double critChance = 0;
        private double critDamage = 0;
        private double meleeCritChance = 0;
        private double meleeCritDamage = 0;
        private double projectileCritChance = 0;
        private double projectileCritDamage = 0;
        private double moveSpeed = 0;
        private double attackSpeed = 0;
        private int fort = 0;
        private int perc = 0;
        private int con = 0;
        private double armor = 0;
        private double maxHealth = 0;
        private int str = 0;
        private int dex = 0;
        private int wis = 0;
        private double knockbackResist = 0;
        private double armorToughness = 0;
        private double drawSpeed = 0;
        private double projectileDamage = 0;
        private double swimSpeed = 0;
        private double blockReach = 0;
        private double entityReach = 0;
        private double debuffResist = 0;
        private double luck = 0;
        private double stepHeight = 0;
        private double jumpBoost = 0;
        private double xpGain = 0;
        private double miningSpeed = 0;
        public double armorMultiplier = 0;
        public double toughnessMultiplier = 0;
        public double healthMultiplier = 0;
        public double damageMultiplier = 0;
        public double speedMultiplier = 0;
        public double critChanceMultiplier = 0;
        public double critDamageMultiplier = 0;
        public double meleeCritChanceMultiplier = 0;
        public double meleeCritDamageMultiplier = 0;
        public double projectileCritChanceMultiplier = 0;
        public double projectileCritDamageMultiplier = 0;
        public double projectileDamageMultiplier = 0;
        public double drawSpeedMultiplier = 0;
        public double meleeDamage = 0;
        public double meleeDamageMultiplier = 0;

        public Builder setDamage(double damage) {
            this.damage = damage;
            return this;
        }

        public Builder setCritChance(double critChance) {
            this.critChance = critChance;
            return this;
        }

        public Builder setCritDamage(double critDamage) {
            this.critDamage = critDamage;
            return this;
        }
        public Builder setMeleeCritChance(double meleeCritChance) {
            this.meleeCritChance = meleeCritChance;
            return this;
        }

        public Builder setMeleeCritDamage(double meleeCritDamage) {
            this.meleeCritDamage = meleeCritDamage;
            return this;
        }

        public Builder setProjectileCritChance(double projectileCritChance) {
            this.projectileCritChance = projectileCritChance;
            return this;
        }

        public Builder setProjectileCritDamage(double projectileCritDamage) {
            this.projectileCritDamage = projectileCritDamage;
            return this;
        }

        public Builder setMoveSpeed(double moveSpeed) {
            this.moveSpeed = moveSpeed;
            return this;
        }

        public Builder setAttackSpeed(double attackSpeed) {
            this.attackSpeed = attackSpeed;
            return this;
        }

        public Builder setFort(int fort) {
            this.fort = fort;
            return this;
        }

        public Builder setPerc(int perc) {
            this.perc = perc;
            return this;
        }

        public Builder setCon(int con) {
            this.con = con;
            return this;
        }

        public Builder setWis(int wis) {
            this.wis = wis;
            return this;
        }

        public Builder setArmor(double armor) {
            this.armor = armor;
            return this;
        }

        public Builder setMaxHealth(double maxHealth) {
            this.maxHealth = maxHealth;
            return this;
        }

        public Builder setStr(int str) {
            this.str = str;
            return this;
        }

        public Builder setDex(int dex) {
            this.dex = dex;
            return this;
        }

        public Builder setKnockbackResist(double knockbackResist) {
            this.knockbackResist = knockbackResist;
            return this;
        }

        public Builder setArmorToughness(double armorToughness) {
            this.armorToughness = armorToughness;
            return this;
        }

        public Builder setDrawSpeed(double drawSpeed) {
            this.drawSpeed = drawSpeed;
            return this;
        }

        public Builder setProjectileDamage(double projectileDamage) {
            this.projectileDamage = projectileDamage;
            return this;
        }

        public Builder setSwimSpeed(double swimSpeed) {
            this.swimSpeed = swimSpeed;
            return this;
        }

        public Builder setEntityReach(double entityReach) {
            this.entityReach = entityReach;
            return this;
        }

        public Builder setBlockReach(double blockReach) {
            this.blockReach = blockReach;
            return this;
        }

        public Builder setDebuffResist(double debuffResist) {
            this.debuffResist = debuffResist;
            return this;
        }

        public Builder setLuck(double luck) {
            this.luck = luck;
            return this;
        }

        public Builder setStepHeight(double stepHeight) {
            this.stepHeight = stepHeight;
            return this;
        }

        public Builder setJumpBoost(double jumpBoost) {
            this.jumpBoost = jumpBoost;
            return this;
        }

        public Builder setXpGain(double xpGain) {
            this.xpGain = xpGain;
            return this;
        }

        public Builder setMiningSpeed(double miningSpeed) {
            this.miningSpeed = miningSpeed;
            return this;
        }

        public Builder setToughnessMultiplier(double toughnessMultiplier) {
            this.toughnessMultiplier = toughnessMultiplier;
            return this;
        }

        public Builder setHealthMultiplier(double healthMultiplier) {
            this.healthMultiplier = healthMultiplier;
            return this;
        }

        public Builder setDamageMultiplier(double damageMultiplier) {
            this.damageMultiplier = damageMultiplier;
            return this;
        }

        public Builder setMeleeDamageMultiplier(double meleeDamageMultiplier) {
            this.meleeDamageMultiplier = meleeDamageMultiplier;
            return this;
        }

        public Builder setSpeedMultiplier(double speedMultiplier) {
            this.speedMultiplier = speedMultiplier;
            return this;
        }

        public Builder setCritChanceMultiplier(double critChanceMultiplier) {
            this.critChanceMultiplier = critChanceMultiplier;
            return this;
        }

        public Builder setCritDamageMultiplier(double critDamageMultiplier) {
            this.critDamageMultiplier = critDamageMultiplier;
            return this;
        }

        public Builder setMeleeCritChanceMultiplier(double meleeCritChanceMultiplier) {
            this.meleeCritChanceMultiplier = meleeCritChanceMultiplier;
            return this;
        }

        public Builder setMeleeCritDamageMultiplier(double meleeCritDamageMultiplier) {
            this.meleeCritDamageMultiplier = meleeCritDamageMultiplier;
            return this;
        }

        public Builder setProjectileCritChanceMultiplier(double projectileCritChanceMultiplier) {
            this.projectileCritChanceMultiplier = projectileCritChanceMultiplier;
            return this;
        }

        public Builder setProjectileCritDamageMultiplier(double projectileCritDamageMultiplier) {
            this.projectileCritDamageMultiplier = projectileCritDamageMultiplier;
            return this;
        }

        public Builder setProjectileDamageMultiplier(double projectileDamageMultiplier) {
            this.projectileDamageMultiplier = projectileDamageMultiplier;
            return this;
        }

        public Builder setArmorMultiplier(double armorMultiplier) {
            this.armorMultiplier = armorMultiplier;
            return this;
        }

        public Builder setDrawSpeedMultiplier(double drawSpeedMultiplier) {
            this.drawSpeedMultiplier = drawSpeedMultiplier;
            return this;
        }

        public Builder setMeleeDamage(double meleeDamage) {
            this.meleeDamage = meleeDamage;
            return this;
        }

        public CustomStats build() {
            return new CustomStats(this);
        }
    }
}
