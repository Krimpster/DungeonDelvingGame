package org.example.builders;

import org.example.objects.player.PlayerCharacter;
import org.example.objects.player.PlayerSkills;

import java.util.ArrayList;

public class PlayerDirector {
    PlayerSkills playerSkills = new PlayerSkills(new PlayerCharacter());
    public PlayerCharacter makeFighter(PlayerBuilder playerBuilder, String adventurerName, String adventurerClass) {
        int fighterBaseHP = 90;
        int fighterBaseAttack = 15;
        int fighterBaseDefense = 25;
        int fighterBaseFP = 100;
        int fighterBaseFpPerTurn = 12;
        int fighterSkillDamageVarianceBound = 110;
        int fighterSkillDamageVarianceOrigin = 90;
        ArrayList<String> skillList = playerSkills.getFighterSkillList();
        ArrayList<String> basicSkillList = playerSkills.getBasicSkillList();

        playerBuilder.setName(adventurerName);
        playerBuilder.setPlayerClass(adventurerClass);
        playerBuilder.setHp(fighterBaseHP);
        playerBuilder.setMaxHp(fighterBaseHP);
        playerBuilder.setAttack(fighterBaseAttack);
        playerBuilder.setDefense(fighterBaseDefense);
        playerBuilder.setBaseFocusPoints(fighterBaseFP);
        playerBuilder.setFocusPoints(fighterBaseFP);
        playerBuilder.setFocusPointsPerTurn(fighterBaseFpPerTurn);
        playerBuilder.setSkillDamageVarianceBound(fighterSkillDamageVarianceBound);
        playerBuilder.setSkillDamageVarianceOrigin(fighterSkillDamageVarianceOrigin);
        playerBuilder.setSkillList(skillList);
        playerBuilder.setBasicSkillList(basicSkillList);

        return playerBuilder.build();
    }

    public PlayerCharacter makeMage(PlayerBuilder playerBuilder, String adventurerName, String adventurerClass){
        int mageBaseHP = 60;
        int mageBaseAttack = 35;
        int mageBaseDefense = 10;
        int mageBaseFP = 300;
        int mageBaseFpPerTurn = 65;
        int mageSkillDamageVarianceBound = 100;
        int mageSkillDamageVarianceOrigin = 60;
        ArrayList<String> skillList = playerSkills.getMageSkillList();
        ArrayList<String> basicSkillList = playerSkills.getBasicSkillList();

        playerBuilder.setName(adventurerName);
        playerBuilder.setPlayerClass(adventurerClass);
        playerBuilder.setHp(mageBaseHP);
        playerBuilder.setMaxHp(mageBaseHP);
        playerBuilder.setAttack(mageBaseAttack);
        playerBuilder.setDefense(mageBaseDefense);
        playerBuilder.setBaseFocusPoints(mageBaseFP);
        playerBuilder.setFocusPoints(mageBaseFP);
        playerBuilder.setFocusPointsPerTurn(mageBaseFpPerTurn);
        playerBuilder.setSkillDamageVarianceBound(mageSkillDamageVarianceBound);
        playerBuilder.setSkillDamageVarianceOrigin(mageSkillDamageVarianceOrigin);
        playerBuilder.setSkillList(skillList);
        playerBuilder.setBasicSkillList(basicSkillList);

        return playerBuilder.build();
    }

    public PlayerCharacter makeRogue(PlayerBuilder playerBuilder, String adventurerName, String adventurerClass){
        int rogueBaseHP = 75;
        int rogueBaseAttack = 25;
        int rogueBaseDefense = 20;
        int rogueBaseFP = 200;
        int rogueBaseFpPerTurn = 40;
        int rogueSkillDamageVarianceBound = 115;
        int rogueSkillDamageVarianceOrigin = 85;
        ArrayList<String> skillList = playerSkills.getRogueSkillList();
        ArrayList<String> basicSkillList = playerSkills.getBasicSkillList();

        playerBuilder.setName(adventurerName);
        playerBuilder.setPlayerClass(adventurerClass);
        playerBuilder.setHp(rogueBaseHP);
        playerBuilder.setMaxHp(rogueBaseHP);
        playerBuilder.setAttack(rogueBaseAttack);
        playerBuilder.setDefense(rogueBaseDefense);
        playerBuilder.setBaseFocusPoints(rogueBaseFP);
        playerBuilder.setFocusPoints(rogueBaseFP);
        playerBuilder.setFocusPointsPerTurn(rogueBaseFpPerTurn);
        playerBuilder.setSkillDamageVarianceBound(rogueSkillDamageVarianceBound);
        playerBuilder.setSkillDamageVarianceOrigin(rogueSkillDamageVarianceOrigin);
        playerBuilder.setSkillList(skillList);
        playerBuilder.setBasicSkillList(basicSkillList);

        return playerBuilder.build();
    }
}
