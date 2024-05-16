package org.example.objects;

import org.example.objects.monsters.Monster;
import org.example.objects.player.PlayerCharacter;
import org.example.objects.player.PlayerSkills;

public class Entity {
    protected String name;
    protected int hp;
    protected int attack;
    protected int defense;
    protected int baseFocusPoints;
    protected int focusPoints;
    protected int focusPointsPerTurn;
    protected int skillDamageVarianceBound;
    protected int skillDamageVarianceOrigin;

    public Entity(){

    }

    public Entity(String name, int hp, int attack, int defense, int focusPoints, int baseFocusPoints,
                  int focusPointsPerTurn, int skillDamageVarianceBound, int skillDamageVarianceOrigin) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.baseFocusPoints = baseFocusPoints;
        this.focusPoints = focusPoints;
        this.focusPointsPerTurn = focusPointsPerTurn;
        this.skillDamageVarianceBound = skillDamageVarianceBound;
        this.skillDamageVarianceOrigin = skillDamageVarianceOrigin;
    }

    public void takeDamage(int damageTaken){
        int damageTotal = damageTaken - getDefense()/2;
        if(damageTotal < 0){
            System.out.println("The blow was deflected! You didn't take any damage!\n");
        }else{
            setHp(getHp() - damageTotal);
            System.out.println("You took " + damageTotal + " damage!\n");
        }
    }

    public void dealBasicDamage(Monster monster, String input, PlayerSkills playerSkills){
        monster.takeDamage((int)playerSkills.getBasicSkillCommandHashMap().get(input).execute());
    }

    public void dealSkillDamage(PlayerCharacter playerCharacter, Monster monster, String input, PlayerSkills playerSkills){
        monster.takeDamage((int)playerSkills.getProfessionSkillCommandHashMap().get(input).execute(playerCharacter, input));
    }

    public void healViaSkill(PlayerCharacter playerCharacter, PlayerSkills playerSkills, String input){
        int healedAmount = (int)playerSkills.getProfessionSkillCommandHashMap().get(input).execute(playerCharacter, input);
        setHp(healedAmount);
        System.out.println("You healed yourself for " + healedAmount + " HP!\n");
    }

    public void passiveFPRegen(){
        int fpRegained = 0;
        if(getFocusPoints() < getBaseFocusPoints()){
            if(getBaseFocusPoints() < (getFocusPoints() + getFocusPointsPerTurn())){
                fpRegained = getFocusPointsPerTurn() - (getFocusPoints() + getFocusPointsPerTurn() - getBaseFocusPoints());
            }
            else{
                fpRegained = getFocusPointsPerTurn();
            }
            if(fpRegained != 0) {
                System.out.println("You have regened " + fpRegained + " FP.\n");
                setFocusPoints(getFocusPoints() + fpRegained);
            }
        }
    }

    public String getCSV(){
        return name + "," + hp + "," + defense + "," + defense + "," + baseFocusPoints + "," + focusPointsPerTurn;
    }

    public int getSkillDamageVarianceBound() {
        return skillDamageVarianceBound;
    }

    public void setSkillDamageVarianceBound(int skillDamageVarianceBound) {
        this.skillDamageVarianceBound = skillDamageVarianceBound;
    }

    public int getSkillDamageVarianceOrigin() {
        return skillDamageVarianceOrigin;
    }

    public void setSkillDamageVarianceOrigin(int skillDamageVarianceOrigin) {
        this.skillDamageVarianceOrigin = skillDamageVarianceOrigin;
    }

    public int getFocusPoints() {
        return focusPoints;
    }

    public void setFocusPoints(int focusPoints) {
        this.focusPoints = focusPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getBaseFocusPoints() {
        return baseFocusPoints;
    }

    public void setBaseFocusPoints(int baseFocusPoints) {
        this.baseFocusPoints = baseFocusPoints;
    }

    public int getFocusPointsPerTurn() {
        return focusPointsPerTurn;
    }

    public void setFocusPointsPerTurn(int focusPointsPerTurn) {
        this.focusPointsPerTurn = focusPointsPerTurn;
    }
}
