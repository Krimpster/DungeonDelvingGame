package org.example.objects;

public class Entity {
    protected String name;
    protected int hp;
    protected int attack;
    protected int defense;
    protected int baseFocusPoints;
    protected int focusPoints;
    protected int focusPointsPerTurn;
    protected int skillDamageVariance;

    public Entity(){

    }

    public Entity(String name, int hp, int attack, int defense, int focusPoints,
                  int baseFocusPoints, int focusPointsPerTurn, int skillDamageVariance) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.baseFocusPoints = baseFocusPoints;
        this.focusPoints = focusPoints;
        this.focusPointsPerTurn = focusPointsPerTurn;
        this.skillDamageVariance = skillDamageVariance;
    }

    public String getCSV(){
        return name + "," + hp + "," + defense + "," + defense + "," + baseFocusPoints + "," + focusPointsPerTurn;
    }

    public int getSkillDamageVariance() {
        return skillDamageVariance;
    }

    public void setSkillDamageVariance(int skillDamageVariance) {
        this.skillDamageVariance = skillDamageVariance;
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
