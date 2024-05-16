package org.example.objects.items;

import org.example.objects.Item;

public class Equipment extends Item{
    private int hpModifier;
    private int attackModifier;
    private int defenseModifier;
    private int fpModifier;
    private int fpPerTurnModifier;

    public Equipment(String itemName, String itemType, int hpModifier, int attackModifier,
                     int defenseModifier, int fpModifier, int fpPerTurnModifier){
        super(itemName, itemType);
        this.hpModifier = hpModifier;
        this.attackModifier = attackModifier;
        this.defenseModifier = defenseModifier;
        this.fpModifier = fpModifier;
        this.fpPerTurnModifier = fpPerTurnModifier;
    }

    @Override
    public String getCSV(){
        return itemName + "," + itemType + "," + attackModifier + "," +
                defenseModifier + "," + fpModifier + "," + fpPerTurnModifier;
    }

    public int getHpModifier() {
        return hpModifier;
    }

    public void setHpModifier(int hpModifier) {
        this.hpModifier = hpModifier;
    }

    public int getAttackModifier() {
        return attackModifier;
    }

    public void setAttackModifier(int attackModifier) {
        this.attackModifier = attackModifier;
    }

    public int getDefenseModifier() {
        return defenseModifier;
    }

    public void setDefenseModifier(int defenseModifier) {
        this.defenseModifier = defenseModifier;
    }

    public int getFpModifier() {
        return fpModifier;
    }

    public void setFpModifier(int fpModifier) {
        this.fpModifier = fpModifier;
    }

    public int getFpPerTurnModifier() {
        return fpPerTurnModifier;
    }

    public void setFpPerTurnModifier(int fpPerTurnModifier) {
        this.fpPerTurnModifier = fpPerTurnModifier;
    }
}
