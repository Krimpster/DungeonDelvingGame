package org.example.objects.monsters;

import org.example.interfaces.ICommand;
import org.example.objects.Entity;

import java.util.HashMap;

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
                   int skillDamageVariance,
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
                skillDamageVariance);
        this.expReward = expReward;
        this.monsterSkill = monsterSkill;
        this.bossSkill = bossSkill;
    }

    @Override
    public String getCSV(){
        return name + "," + hp + "," + attack + "," + defense + "," + focusPoints + "," +
                focusPointsPerTurn + "," + expReward + "," + monsterSkill + "," + skillDamageVariance;
    }

    public double basicAttack(){
        System.out.println(getName() + " attacks you.");
        return getAttack();
    }

    public void takeDamage(int damageTaken){
        int damageTotal = damageTaken - getDefense()/5;
        if(damageTotal < 0){
            System.out.println("The attack was deflected! " + getName() + " took no damage!");
        }else{
            setHp(getHp() - damageTotal);
            System.out.println("The " + getName() + " took " + damageTotal + "damage!");
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
