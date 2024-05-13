package org.example.builders;

import org.example.interfaces.ICommand;
import org.example.objects.player.PlayerCharacter;
import org.example.objects.player.PlayerSkills;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerDirector {
    PlayerSkills playerSkills = new PlayerSkills(new PlayerCharacter());
    public PlayerCharacter makeFighter(PlayerBuilder playerBuilder, String adventurerName, String adventurerClass) {
        int fighterBaseHP = 90;
        int fighterBaseAttack = 15;
        int fighterBaseDefense = 25;
        int fighterBaseFP = 100;
        int fighterBaseFpPerTurn = 12;
        int fighterSkillDamageVariance = 110;
        ArrayList<String> skillList = playerSkills.getFighterSkillList();
        HashMap<String, ICommand> skillCommandHashMap = playerSkills.getFighterSkillCommandHashMap();

        playerBuilder.setName(adventurerName);
        playerBuilder.setPlayerClass(adventurerClass);
        playerBuilder.setHp(fighterBaseHP);
        playerBuilder.setMaxHp(fighterBaseHP);
        playerBuilder.setAttack(fighterBaseAttack);
        playerBuilder.setDefense(fighterBaseDefense);
        playerBuilder.setBaseFocusPoints(fighterBaseFP);
        playerBuilder.setFocusPoints(fighterBaseFP);
        playerBuilder.setFocusPointsPerTurn(fighterBaseFpPerTurn);
        playerBuilder.setSkillDamageVariance(fighterSkillDamageVariance);
        playerBuilder.setSkillList(skillList);
        playerBuilder.setSkillCommandHashMap(skillCommandHashMap);

        return playerBuilder.build();
    }

    public PlayerCharacter makeMage(PlayerBuilder playerBuilder, String adventurerName, String adventurerClass){
        int mageBaseHP = 60;
        int mageBaseAttack = 35;
        int mageBaseDefense = 10;
        int mageBaseFP = 300;
        int mageBaseFpPerTurn = 65;
        int mageSkillDamageVariance = 130;
        ArrayList<String> skillList = playerSkills.getMageSkillList();
        HashMap<String, ICommand> skillCommandHashMap = playerSkills.getMageSkillCommandHashMap();

        playerBuilder.setName(adventurerName);
        playerBuilder.setPlayerClass(adventurerClass);
        playerBuilder.setHp(mageBaseHP);
        playerBuilder.setMaxHp(mageBaseHP);
        playerBuilder.setAttack(mageBaseAttack);
        playerBuilder.setDefense(mageBaseDefense);
        playerBuilder.setBaseFocusPoints(mageBaseFP);
        playerBuilder.setFocusPoints(mageBaseFP);
        playerBuilder.setFocusPointsPerTurn(mageBaseFpPerTurn);
        playerBuilder.setSkillDamageVariance(mageSkillDamageVariance);
        playerBuilder.setSkillList(skillList);
        playerBuilder.setSkillCommandHashMap(skillCommandHashMap);

        return playerBuilder.build();
    }

    public PlayerCharacter makeRogue(PlayerBuilder playerBuilder, String adventurerName, String adventurerClass){
        int rogueBaseHP = 75;
        int rogueBaseAttack = 25;
        int rogueBaseDefense = 20;
        int rogueBaseFP = 200;
        int rogueBaseFpPerTurn = 40;
        int rogueSkillDamageVariance = 115;
        ArrayList<String> skillList = playerSkills.getRogueSkillList();
        HashMap<String, ICommand> skillCommandHashMap = playerSkills.getRogueSkillCommandHashMap();

        playerBuilder.setName(adventurerName);
        playerBuilder.setPlayerClass(adventurerClass);
        playerBuilder.setHp(rogueBaseHP);
        playerBuilder.setMaxHp(rogueBaseHP);
        playerBuilder.setAttack(rogueBaseAttack);
        playerBuilder.setDefense(rogueBaseDefense);
        playerBuilder.setBaseFocusPoints(rogueBaseFP);
        playerBuilder.setFocusPoints(rogueBaseFP);
        playerBuilder.setFocusPointsPerTurn(rogueBaseFpPerTurn);
        playerBuilder.setSkillDamageVariance(rogueSkillDamageVariance);
        playerBuilder.setSkillList(skillList);
        playerBuilder.setSkillCommandHashMap(skillCommandHashMap);

        return playerBuilder.build();
    }
}
