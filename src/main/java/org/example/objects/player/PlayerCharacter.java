package org.example.objects.player;

import org.example.interfaces.ICommand;
import org.example.interfaces.ISkillCommand;
import org.example.objects.Entity;
import org.example.objects.items.Equipment;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerCharacter extends Entity {
    private String playerClass;
    private ArrayList<Equipment> absorbedEquipment;
    private ArrayList<String> skillList;
    private HashMap<String, ISkillCommand> skillCommandHashMap;
    private ArrayList<String> basicSkillList;
    private HashMap<String, ISkillCommand> basicSkillCommandHashMap;
    private int maxHp;
    private int experienceGained;
    private int level;
    private int scoreTotal;
    private HashMap<Integer, Integer> levelBreakpoints = new HashMap<>();
    public PlayerCharacter(){
        levelBreakpoints.put(2, 100);
        levelBreakpoints.put(3, 250);
        levelBreakpoints.put(4, 400);
        levelBreakpoints.put(5, 600);
    }

    public PlayerCharacter(String name,
                           int hp,
                           int maxHp,
                           int attack,
                           int defense,
                           String playerClass,
                           int baseFocusPoints,
                           int focusPoints,
                           int focusPointsPerTurn,
                           ArrayList<Equipment> absorbedEquipment,
                           ArrayList<String> skillList,
                           ArrayList<String> basicSkillList,
                           int skillDamageVariance,
                           int experienceGained,
                           int level,
                           int scoreTotal) {
        super(name, hp, attack, defense, focusPoints, baseFocusPoints, focusPointsPerTurn, skillDamageVariance);
        this.maxHp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.focusPoints = focusPoints;
        this.skillDamageVariance = skillDamageVariance;
        this.playerClass = playerClass;
        this.absorbedEquipment = absorbedEquipment;
        this.skillList = skillList;
        this.basicSkillList = basicSkillList;
        this.experienceGained = experienceGained;
        this.level = level;
        this.scoreTotal = scoreTotal;
    }

    public void levelUp(){
        if(levelBreakpoints.containsKey(getLevel())){
            if(experienceGained >= levelBreakpoints.get(getLevel())){
                System.out.println("You have leveled up!\n");
                increaseStatsOnLevelUp();
            }
        }
    }

    private void increaseStatsOnLevelUp(){
        int hpStatIncrease = 3;
        int attackStatIncrease = 5;
        int defenseStatIncrease = 4;
        int focusPointStatIncrease = 10;
        int fpPerTurnStatIncrease = 5;
        int levelIncrease = 1;

        setMaxHp(getMaxHp() + hpStatIncrease);
        setHp(getHp() + hpStatIncrease);
        setAttack(getAttack() + attackStatIncrease);
        setDefense(getDefense() + defenseStatIncrease);
        setBaseFocusPoints(getBaseFocusPoints() + focusPointStatIncrease);
        setFocusPoints(getFocusPoints() + focusPointStatIncrease);
        setBaseFocusPoints(getFocusPointsPerTurn() + fpPerTurnStatIncrease);

        setLevel(getLevel() + levelIncrease);
    }

    public void takeDamage(int damageTaken){
        int damageTotal = damageTaken - getDefense()/2;
        if(damageTotal < 0){
            System.out.println("The blow was deflected! You didn't take any damage!");
        }else{
            setHp(getHp() - damageTotal);
            System.out.println("You took " + damageTotal + " damage from the monster's attack!");
        }
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public String getPlayerClass() {
        return playerClass;
    }

    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
    }

    public ArrayList<Equipment> getAbsorbedEquipment() {
        return absorbedEquipment;
    }

    public void setAbsorbedEquipment(ArrayList<Equipment> absorbedEquipment) {
        this.absorbedEquipment = absorbedEquipment;
    }

    public ArrayList<String> getSkillList() {
        return skillList;
    }

    public void setSkillList(ArrayList<String> skillList) {
        this.skillList = skillList;
    }

    public ArrayList<String> getBasicSkillList() {
        return basicSkillList;
    }

    public int getExperienceGained() {
        return experienceGained;
    }

    public void setExperienceGained(int experienceGained) {
        this.experienceGained = experienceGained;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(int scoreTotal) {
        this.scoreTotal = scoreTotal;
    }



}
