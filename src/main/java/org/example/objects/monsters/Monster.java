package org.example.objects.monsters;

import org.example.objects.Entity;
import org.example.objects.player.PlayerCharacter;
import org.example.objects.player.PlayerSkills;

public abstract class Monster extends Entity {
    private String monsterSkill;
    private String bossSkill;
    private int expReward;

    public Monster(String name,
                   int hp,
                   int baseAttack,
                   int baseDefense,
                   int baseFocusPoints,
                   int focusPoints,
                   int focusPointsPerTurn,
                   int skillDamageVarianceBound,
                   int skillDamageVarianceOrigin,
                   String monsterSkill,
                   String bossSkill,
                   int expReward) {
        super(name,
                hp,
                baseAttack,
                baseDefense,
                baseFocusPoints,
                focusPoints,
                focusPointsPerTurn,
                skillDamageVarianceBound,
                skillDamageVarianceOrigin);
        this.expReward = expReward;
        this.monsterSkill = monsterSkill;
        this.bossSkill = bossSkill;
    }

    @Override
    public String getCSV(){
        return name + "," + hp + "," + attack + "," + defense + "," + baseFocusPoints + "," + focusPoints + "," +
                focusPointsPerTurn + "," + skillDamageVarianceBound + "," + skillDamageVarianceOrigin + "," +
                monsterSkill + "," + bossSkill + "," + expReward;
    }

    public double basicAttack(){
        System.out.println(getName() + " attacks you.\n");
        return getAttack();
    }
    @Override
    public void takeDamage(int damageTaken){
        int damageTotal = damageTaken - getDefense()/5;
        if(damageTotal < 0){
            System.out.println("The attack was deflected! " + getName() + " took no damage!\n");
        }else{
            setHp(getHp() - damageTotal);
            System.out.println("The " + getName() + " took " + damageTotal + " damage!\n");
        }
    }
    public void dealSkillDamage(PlayerCharacter playerCharacter, Monster monster,
                                String skillName, MonsterSkills monsterSkills){
        playerCharacter.takeDamage((int)monsterSkills.getMonsterSkills().get(skillName).execute(monster, skillName));
    }
    public void healViaSkill(Monster monster, MonsterSkills monsterSkills, String skillName){
        int healedAmount = (int)monsterSkills.getMonsterSkills().get(skillName).execute(monster, skillName);

        if(healedAmount > 0.33 * getHp()) {
            healedAmount = (int)(getHp() * 0.33);
        }

        setHp(healedAmount);
    }

    @Override
    public void passiveFPRegen(){
        int fpRegained = 0;
        if(getFocusPoints() < getBaseFocusPoints()){
            if(getBaseFocusPoints() < (getFocusPoints() + getFocusPointsPerTurn())){
                fpRegained = getFocusPointsPerTurn() - (getFocusPoints() + getFocusPointsPerTurn() - getBaseFocusPoints());
            }
            else{
                fpRegained = getFocusPointsPerTurn();
            }
            setFocusPoints(getFocusPoints() + fpRegained);
        }
    }

    public String getMonsterSkill() {
        return monsterSkill;
    }

    public void setMonsterSkill(String monsterSkill) {
        this.monsterSkill = monsterSkill;
    }

    public String getBossSkill() {
        return bossSkill;
    }

    public void setBossSkill(String bossSkill) {
        this.bossSkill = bossSkill;
    }

    public int getExpReward() {
        return expReward;
    }

    public void setExpReward(int expReward) {
        this.expReward = expReward;
    }
}
