package org.example.objects.player;

import org.example.objects.Entity;
import org.example.objects.items.Equipment;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerCharacter extends Entity {
    private String playerClass;
    private ArrayList<Equipment> absorbedEquipment = new ArrayList<>();
    private ArrayList<String> skillList;
    private ArrayList<String> basicSkillList;
    private int maxHp;
    private int experienceGained;
    private int level;
    private int scoreTotal;
    private int monstersDefeated;
    private HashMap<Integer, Integer> levelBreakpoints = new HashMap<>();
    public PlayerCharacter(){
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
                           int skillDamageVarianceBound,
                           int skillDamageVarianceOrigin,
                           int experienceGained,
                           int level,
                           int scoreTotal,
                           int monstersDefeated) {
        super(name, hp, attack, defense, focusPoints, baseFocusPoints,
                focusPointsPerTurn, skillDamageVarianceBound, skillDamageVarianceOrigin);
        this.maxHp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.focusPoints = focusPoints;
        this.skillDamageVarianceBound = skillDamageVarianceBound;
        this.skillDamageVarianceOrigin = skillDamageVarianceOrigin;
        this.playerClass = playerClass;
        this.absorbedEquipment = absorbedEquipment;
        this.skillList = skillList;
        this.basicSkillList = basicSkillList;
        this.experienceGained = experienceGained;
        this.level = level;
        this.scoreTotal = scoreTotal;
        this.monstersDefeated = monstersDefeated;

        levelBreakpoints.put(1, 100);
        levelBreakpoints.put(2, 250);
        levelBreakpoints.put(3, 400);
        levelBreakpoints.put(4, 600);
        levelBreakpoints.put(5, 800);
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
        int hpStatIncrease = 7;
        int attackStatIncrease = 5;
        int defenseStatIncrease = 2;
        double focusPointStatIncrease = getFocusPoints() * 0.2;
        double fpPerTurnStatIncrease = getFocusPointsPerTurn() * 0.2;
        int levelIncrease = 1;

        setMaxHp(getMaxHp() + hpStatIncrease);
        setHp(getHp() + hpStatIncrease);
        setAttack(getAttack() + attackStatIncrease);
        setDefense(getDefense() + defenseStatIncrease);
        setBaseFocusPoints(getBaseFocusPoints() + (int)focusPointStatIncrease);
        setFocusPoints(getFocusPoints() + (int)focusPointStatIncrease);
        setFocusPointsPerTurn(getFocusPointsPerTurn() + (int)fpPerTurnStatIncrease);

        setLevel(getLevel() + levelIncrease);
    }
    private void increaseStatsOnEquipmentAbsorption(Equipment equipment){
        setMaxHp(getMaxHp() + equipment.getHpModifier());
        setHp(getHp() + equipment.getHpModifier());
        setAttack(getAttack() + equipment.getAttackModifier());
        setDefense(getDefense() + equipment.getDefenseModifier());
        setBaseFocusPoints(getBaseFocusPoints() + equipment.getFpModifier());
        setFocusPoints(getFocusPoints() + equipment.getFpModifier());
        setBaseFocusPoints(getFocusPointsPerTurn() + equipment.getFpPerTurnModifier());
    }
    @Override
    public void takeDamage(int damageTaken){
        int damageTotal = damageTaken - getDefense()/2;
        if(damageTotal <= 0){
            System.out.println("The blow was deflected! You didn't take any damage!\n");
        }else{
            setHp(getHp() - damageTotal);
            System.out.println("You took " + damageTotal + " damage from the monster's attack!\n");
        }
    }
    public void passiveHeal(){
        int hpRegained = 0;
        int passiveHPRegen  = (int)(getMaxHp() * 0.75);
        if(getHp() < getMaxHp()){
            if(getMaxHp() < (getHp() + passiveHPRegen)){
                hpRegained = passiveHPRegen - (getHp() + passiveHPRegen - getMaxHp());
            }
            else{
                hpRegained = passiveHPRegen;
            }
            System.out.println("You rest after the battle and regain " + hpRegained + " HP.\n");
            setHp(getHp() + hpRegained);
        }
    }
    public void defenseIncrease(PlayerSkills playerSkills, String input){
        setDefense((int)playerSkills.getBasicSkillCommandHashMap().get(input).execute());
    }

    public void listCurrentHPAndFP(){
        System.out.println("HP: " + getHp() + "     FP: " + getFocusPoints() + "\n");
    }

    public void listCurrentStats(){
        System.out.println("HP: " + getHp() + "/" + getMaxHp() +  " | FP: " + getFocusPoints() + "/" + getBaseFocusPoints() + "\n" +
                "Level: " + getLevel() + " | EXP Total: " + getExperienceGained() + "\n" +
                "Attack: " + getAttack() + " | Defense: " + getDefense() + "\n" +
                "FP Regen: " + getBaseFocusPoints() + " | Monsters Defeated: " + getMonstersDefeated() + "\n" +
                "Current Score: " + getScoreTotal() + "\n" +
                "Equipment: " + "\n");

        for (int i = 0; i < absorbedEquipment.size(); i++){
            if (i == absorbedEquipment.size() - 1){
                System.out.println(absorbedEquipment.get(i).getItemName() + "\n");
            }
            else{
                System.out.println(absorbedEquipment.get(i).getItemName() + ", ");
            }
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
    public void addEquipmentToAbsorbedList(Equipment equipment){
        absorbedEquipment.add(equipment);
        increaseStatsOnEquipmentAbsorption(equipment);
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

    public int getMonstersDefeated() {
        return monstersDefeated;
    }

    public void setMonstersDefeated(int monstersDefeated) {
        this.monstersDefeated = monstersDefeated;
    }
}
