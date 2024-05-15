package org.example;

import org.example.interfaces.ICommand;
import org.example.map.MapBuilder;
import org.example.map.Node;
import org.example.objects.monsters.BossMonster;
import org.example.objects.monsters.MonsterSkills;
import org.example.objects.monsters.NormalMonster;
import org.example.objects.player.PlayerCharacter;
import org.example.objects.player.PlayerSkills;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static org.example.FileManager.bestiary;

public class Combat {
    private InputGetter inputGetter = new InputGetter();
    private ArrayList<NormalMonster> monsterList = new ArrayList<>();
    private ArrayList<BossMonster> bossList = new ArrayList<>();
    private NormalMonster normalMonster;
    private MonsterSkills monsterSkills;
    private PlayerCharacter playerCharacter;
    private PlayerSkills playerSkills;
    private FileManager fileManager = new FileManager();
    private ArrayList<String> combatChoices = new ArrayList<>();
    private HashMap<String, ICommand> combatCommandHashMap = new HashMap<>();
    private Random random = new Random();
    private int turn = 1;
    public Combat(PlayerCharacter playerCharacter,
                  Node[][] nodeMap,
                  Node start,
                  Node goal,
                  MapBuilder mapBuilder,
                  Exploration exploration){

    }
    public Combat(PlayerCharacter playerCharacter, int turnsTaken){

    }

    public Combat(PlayerCharacter playerCharacter){
        this.playerCharacter = playerCharacter;
        playerSkills = new PlayerSkills(playerCharacter);

        combatChoices.add("Use a basic skill. (b)");
        combatChoices.add("Use a profession skill. (s)");

        combatCommandHashMap.put("b", () -> listBasicSkills(playerCharacter));
        combatCommandHashMap.put("s", () -> listPlayerSkills(playerCharacter));
    }

    public void startCombat(){

    }

    public void startBossEncounter(){

    }

    public void startTestEncounter(){
        fileManager.load(bestiary.toString());
        monsterList = fileManager.getMonsters();
        int selectedMonster = random.nextInt(monsterList.size()-1);
        normalMonster = monsterList.get(selectedMonster);
        monsterSkills = new MonsterSkills(normalMonster);

        boolean running = true;
        while(running){
            if(turn % 2 != 0){
                playerTakesTurn(playerCharacter);
            }
            else{
                monsterTakesTurn(normalMonster);
                turn++;
            }
            if(playerCharacter.getHp() <= 0){
                System.out.println("You have died, your adventure is over!");
                running = false;
            }
            if(normalMonster.getHp() <= 0){
                System.out.println("You have slain the " + normalMonster.getName() + "!\n");
                playerCharacter.setExperienceGained(playerCharacter.getExperienceGained() +
                        normalMonster.getExpReward());
                running = false;
            }
        }
    }

    private void playerTakesTurn(PlayerCharacter playerCharacter){
        double damageTaken;
        for(String combatChoice : combatChoices){
            System.out.println(combatChoice);
        }
        String input = inputGetter.getStringInput("What would you like to do?\n");

        if (combatCommandHashMap.containsKey(input)){
            if(input.equals("a")){
                combatCommandHashMap.get(input).execute();
            }
            else{
                combatCommandHashMap.get(input).execute();
            }
        }
    }
    private void monsterTakesTurn(NormalMonster normalMonster){
        int monsterRandomness = random.nextInt(100);
        double damageTaken;
        double healedAmount;
        if (monsterRandomness <= 75){
            damageTaken = normalMonster.basicAttack();
            playerCharacter.takeDamage((int)damageTaken);
        }else {
            if(normalMonster.getMonsterSkill().equals("Heal")){
                healedAmount = monsterSkills.getMonsterSkills().get(normalMonster.getMonsterSkill()).execute();
                normalMonster.setHp((int)healedAmount);
            }else {
                damageTaken = monsterSkills.getMonsterSkills().get(normalMonster.getMonsterSkill()).execute();
                playerCharacter.takeDamage((int)damageTaken);
            }
        }
    }

    private void listBasicSkills(PlayerCharacter playerCharacter){
        double damageTaken;
        double defenseIncrease;
        int defenseIncreaseduration = 0;
        for (String skillName : playerCharacter.getBasicSkillList()){
            System.out.println(skillName);
        }
        if (turn == defenseIncreaseduration){
            playerCharacter.setDefense(playerCharacter.getDefense() / 4);
            System.out.println("The bolstering fades away...\n");
        }

        String input = inputGetter.getStringInput("Which basic skill do you want to use?");

        if(playerSkills.getBasicSkillCommandHashMap().containsKey(input)){
            if(input.equals("d")){
                defenseIncrease = playerSkills.getBasicSkillCommandHashMap().get(input).execute();
                playerCharacter.setDefense((int)defenseIncrease);
                defenseIncreaseduration = turn + 2;
                turn++;
            }else {
                damageTaken = playerSkills.getBasicSkillCommandHashMap().get(input).execute();
                normalMonster.takeDamage((int)damageTaken);
                turn++;
            }

        }
    }

    private void listPlayerSkills(PlayerCharacter playerCharacter){
        double damageTaken;
        double healedAmount;
        for(String skillName : playerCharacter.getSkillList()){
            System.out.println(skillName);
        }
        String input = inputGetter.getStringInput("Which skill do you want to use?\n");
        if (playerSkills.getProfessionSkillCommandHashMap().containsKey(input)){
            if(input.equals("i")){
                healedAmount = playerSkills.getProfessionSkillCommandHashMap().get(input).execute();
                playerCharacter.setHp((int)healedAmount);
                System.out.println("You healed yourself for " + (int)healedAmount + " HP!\n");
                turn++;
            }
            else{
                damageTaken = playerSkills.getProfessionSkillCommandHashMap().get(input).execute();
                normalMonster.takeDamage((int)damageTaken);
                turn++;
            }
        }else{
            System.out.println("That isn't a skill you posses.\n");
        }
    }
}
