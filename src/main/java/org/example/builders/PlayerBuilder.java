package org.example.builders;

import org.example.interfaces.ICommand;
import org.example.interfaces.ISkillCommand;
import org.example.objects.items.Equipment;
import org.example.objects.player.PlayerCharacter;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerBuilder {
    private String name = "";
    private String playerClass = "";
    private int baseFocusPoints = 0;
    private int focusPoints = 0;
    private int focusPointsPerTurn = 0;
    private int skillDamageVariance = 0;
    private int hp = 0;
    private int maxHp = 0;
    private int attack = 0;
    private int defense = 0;
    private ArrayList<Equipment> absorbedEquipment = null;
    private ArrayList<String> skillList = null;
    private HashMap<String, ISkillCommand> skillCommandHashMap = null;
    private ArrayList<String> basicSkillList = null;
    private HashMap<String, ISkillCommand> basicSkillCommandHashMap = null;
    private int experienceGained = 0;
    private int level = 1;
    private int scoreTotal = 0;

    public PlayerBuilder setName(String name){
        this.name = name;
        return this;
    }
    public PlayerBuilder setPlayerClass(String playerClass){
        this.playerClass = playerClass;
        return this;
    }
    public PlayerBuilder setBaseFocusPoints(int baseFocusPoints){
        this.baseFocusPoints = baseFocusPoints;
        return this;
    }
    public PlayerBuilder setFocusPoints(int focusPoints){
        this.focusPoints = focusPoints;
        return this;
    }
    public PlayerBuilder setFocusPointsPerTurn(int focusPointsPerTurn){
        this.focusPointsPerTurn = focusPointsPerTurn;
        return this;
    }
    public PlayerBuilder setSkillDamageVariance(int skillDamageVariance){
        this.skillDamageVariance = skillDamageVariance;
        return this;
    }
    public PlayerBuilder setHp(int hp){
        this.hp = hp;
        return this;
    }
    public PlayerBuilder setMaxHp(int maxHp){
        this.maxHp = maxHp;
        return this;
    }
    public PlayerBuilder setAttack(int attack){
        this.attack = attack;
        return this;
    }
    public PlayerBuilder setDefense(int defense){
        this.defense = defense;
        return this;
    }
    public PlayerBuilder setAbsorbedEquipment(ArrayList<Equipment> absorbedEquipment){
        this.absorbedEquipment = absorbedEquipment;
        return this;
    }
    public PlayerBuilder setSkillList(ArrayList<String> skillList){
        this.skillList = skillList;
        return this;
    }
    public PlayerBuilder setSkillCommandHashMap(HashMap<String, ISkillCommand> skillCommandHashMap){
        this.skillCommandHashMap = skillCommandHashMap;
        return this;
    }
    public PlayerBuilder setBasicSkillList(ArrayList<String> basicSkillList){
        this.basicSkillList = basicSkillList;
        return this;
    }
    public PlayerBuilder setBasicSkillCommandHashMap(HashMap<String, ISkillCommand> basicSkillCommandHashMap){
        this.basicSkillCommandHashMap = basicSkillCommandHashMap;
        return this;
    }
    public PlayerBuilder setExperienceGained(int experienceGained){
        this.experienceGained = experienceGained;
        return this;
    }
    public PlayerBuilder setLevel(int level){
        this.level = level;
        return this;
    }
    public PlayerBuilder setScoreTotal(int scoreTotal){
        this.scoreTotal = scoreTotal;
        return this;
    }

    public PlayerCharacter build(){
        return new PlayerCharacter(name,
                hp,
                maxHp,
                attack,
                defense,
                playerClass,
                baseFocusPoints,
                focusPoints,
                focusPointsPerTurn,
                absorbedEquipment,
                skillList,
                basicSkillList,
                skillDamageVariance,
                experienceGained,
                level,
                scoreTotal);
    }
}
