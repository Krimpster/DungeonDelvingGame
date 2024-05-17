package org.example.game;

import org.example.startup.FileManager;
import org.example.interfaces.ICommand;
import org.example.map.MapBuilder;
import org.example.map.Node;
import org.example.objects.Leaderboard;
import org.example.objects.items.Equipment;
import org.example.objects.monsters.BossMonster;
import org.example.objects.monsters.Monster;
import org.example.objects.monsters.MonsterSkills;
import org.example.objects.monsters.NormalMonster;
import org.example.objects.player.PlayerCharacter;
import org.example.objects.player.PlayerSkills;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static org.example.startup.FileManager.leaderboardFilePath;

public class Combat {
    private InputGetter inputGetter = new InputGetter();
    private Leaderboard leaderboardClass;
    private MapBuilder mapBuilder;
    private Exploration exploration;
    private Game game;
    private ArrayList<NormalMonster> monsterList;
    private ArrayList<BossMonster> bossList;
    private ArrayList<NormalMonster> higherRiskList = new ArrayList<>();
    private ArrayList<Equipment> equipment;
    private NormalMonster normalMonster;
    private BossMonster bossMonster;
    private Monster monster;
    private MonsterSkills monsterSkills;
    private String monsterSkillName;
    private PlayerCharacter playerCharacter;
    private PlayerSkills playerSkills;
    private FileManager fileManager = new FileManager();
    private ArrayList<String> combatChoices = new ArrayList<>();
    private HashMap<String, ICommand> combatCommandHashMap = new HashMap<>();
    private Random random = new Random();
    private int turn = 1;
    int defenseIncreaseDuration = 0;
    private final int monsterCombatWinScore = 2500;
    private final int bossCombatWinScore = 20000;
    private Node[][] nodeMap;
    private Node playerLocation;
    private Node start;
    private Node goal;
    private int turnsTaken;
    public Combat(PlayerCharacter playerCharacter,
                  MapBuilder mapBuilder,
                  Node[][] nodeMap,
                  Node playerLocation,
                  Node start,
                  Node goal,
                  int turnsTaken,
                  ArrayList<NormalMonster> monsterList,
                  ArrayList<NormalMonster> higherRiskList,
                  ArrayList<BossMonster> bossList,
                  ArrayList<Equipment> equipment){
        this.playerCharacter = playerCharacter;
        this.mapBuilder = mapBuilder;
        this.nodeMap = nodeMap;
        this.playerLocation = playerLocation;
        this.start = start;
        this.goal = goal;
        this.turnsTaken = turnsTaken;
        this.monsterList = monsterList;
        this.higherRiskList = higherRiskList;
        this.bossList = bossList;
        this.equipment = equipment;
        playerSkills = new PlayerSkills(playerCharacter, "");

        combatChoices.add("Use a basic skill. (b)");
        combatChoices.add("Use a profession skill. (s)");

        combatCommandHashMap.put("b", () -> listBasicSkills(playerCharacter));
        combatCommandHashMap.put("s", () -> listPlayerSkills(playerCharacter));

    }

    private Monster getMonster(){
        if(playerLocation.isGoal()){;
            int selectedMonster = random.nextInt(bossList.size());
            bossMonster = bossList.get(selectedMonster);
            monsterSkills = new MonsterSkills(bossMonster, "");
            return bossMonster;
        }
        if (playerLocation.isMonsterRoom() && playerCharacter.getMonstersDefeated() < 4) {
            int selectedMonster = random.nextInt(monsterList.size());
            normalMonster = monsterList.get(selectedMonster);
            return normalMonster;
        }
        if (playerLocation.isMonsterRoom() && playerCharacter.getMonstersDefeated() >= 4) {
            int selectedMonster = random.nextInt(higherRiskList.size());
            normalMonster = higherRiskList.get(selectedMonster);
            return normalMonster;
        }
        System.out.println("Something went wrong.");
        exploration = new Exploration(playerCharacter, mapBuilder, nodeMap, playerLocation, start, goal, turnsTaken,
                monsterList, higherRiskList, bossList, equipment);
        return null;
    }

    public void startCombat(){
        monster = getMonster();
        boolean running = true;

        while(running){
            if(turn % 2 != 0){
                playerCharacter.listCurrentHPAndFP();
                playerTakesTurn(playerCharacter);
            }
            else{
                monsterTakesTurn(monster);
                turn++;
            }
            if(playerCharacter.getHp() <= 0){
                System.out.println("You have died, your adventure is over!\n");
                gameOver();
                running = false;
            }
            if(monster.getHp() <= 0 && monster.getBossSkill().equals("none")){
                monsterCombatWin();
                playerLocation.setMonsterRoom(false);
                running = false;
            }
            else if (monster.getHp() <= 0 && !monster.getBossSkill().equals("none")) {
                bossCombatWin();
                leaderboardClass = new Leaderboard(playerCharacter.getName(), playerCharacter.getPlayerClass(),
                        turnsTaken, playerCharacter.getMonstersDefeated());
                leaderboardClass.calculateScore(playerCharacter, turnsTaken);
                fileManager.addToLeaderboardList(leaderboardClass);
                fileManager.saveLeaderBoard(leaderboardFilePath.toString());
                gameOver();
                running = false;
            }
        }
    }

    private void playerTakesTurn(PlayerCharacter playerCharacter){
        for(String combatChoice : combatChoices){
            System.out.println(combatChoice);
        }
        String input = inputGetter.getStringInput("What would you like to do?\n");

        if (combatCommandHashMap.containsKey(input)){
                combatCommandHashMap.get(input).execute();
        }
    }
    private void monsterTakesTurn(Monster monster){
        int monsterRandomness = random.nextInt(100);
        double damageTaken;
        if (monsterRandomness <= 15){
            damageTaken = monster.basicAttack();
            playerCharacter.takeDamage((int)damageTaken);
        }
        else {
            monsterSkills = getSkillName(monster);
            if(monster.getMonsterSkill().equals("Heal")){
                monster.healViaSkill(monster, monsterSkills, monsterSkillName);
            }
            else {
                monster.dealSkillDamage(playerCharacter, monster, monsterSkillName, monsterSkills);
            }
        }
    }

    private void listBasicSkills(PlayerCharacter playerCharacter){
        for (String skillName : playerCharacter.getBasicSkillList()){
            System.out.println(skillName);
        }
        if (turn == defenseIncreaseDuration){
            playerCharacter.setDefense(playerCharacter.getDefense() / 4);
            System.out.println("The bolstering fades away...\n");
        }

        String input = inputGetter.getStringInput("Which basic skill do you want to use?\n");

        if(playerSkills.getBasicSkillCommandHashMap().containsKey(input)){
            if(input.equals("d")){
                playerCharacter.defenseIncrease(playerSkills, input);
                defenseIncreaseDuration = turn + 2;
                endTurn(playerCharacter);
            }else {
                playerCharacter.dealBasicDamage(monster, input, playerSkills);
                endTurn(playerCharacter);
            }

        }
    }

    private void listPlayerSkills(PlayerCharacter playerCharacter){
        for(String skillName : playerCharacter.getSkillList()){
            System.out.println(skillName);
        }
        String input = inputGetter.getStringInput("Which skill do you want to use?\n");
        if (playerSkills.getProfessionSkillCommandHashMap().containsKey(input)){
            playerSkills = getInput(playerCharacter, input);
            if(input.equals("i")){
                playerCharacter.healViaSkill(playerCharacter, playerSkills, input);
                endTurn(playerCharacter);
            }
            else{
                playerCharacter.dealSkillDamage(playerCharacter, monster, input, playerSkills);
                endTurn(playerCharacter);
            }
        }else{
            System.out.println("That isn't a skill you posses.\n");
        }
    }

    private void endTurn(PlayerCharacter playerCharacter){
        playerCharacter.passiveFPRegen();
        turn++;
    }

    private void monsterCombatWin(){
        System.out.println("You have slain the " + monster.getName() + "!\n");
        postWinRewards();
        exploration = new Exploration(playerCharacter, mapBuilder, nodeMap, playerLocation, start, goal, turnsTaken,
                monsterList, higherRiskList, bossList, equipment);
    }

    private void bossCombatWin(){
        System.out.println("You have slain the " + monster.getName() + "!\n" +
                "The lord of the dungeon is defeated and your adventure is over, congratulations!\n");
        postWinRewards();
    }

    private void postWinRewards() {
        playerCharacter.setExperienceGained(playerCharacter.getExperienceGained() +
                monster.getExpReward());
        playerCharacter.setMonstersDefeated(playerCharacter.getMonstersDefeated() + 1);
        playerCharacter.levelUp();
        playerCharacter.passiveHeal();
        turnsTaken = turnsTaken + turn;
        playerCharacter.setScoreTotal(playerCharacter.getScoreTotal() + bossCombatWinScore);
    }

    private PlayerSkills getInput(PlayerCharacter playerCharacter, String input){
        return new PlayerSkills(playerCharacter, input);
    }

    private MonsterSkills getSkillName(Monster monster){
        int percent = 100;
        int percentChanceOfMonsterSkill = 35;
        int percentage = random.nextInt(percent);
        if (percentage < percentChanceOfMonsterSkill){
            this.monsterSkillName = monster.getMonsterSkill();
            return new MonsterSkills(monster, monster.getMonsterSkill());
        }
        this.monsterSkillName = monster.getBossSkill();
        return new MonsterSkills(monster, monster.getBossSkill());
    }

    private void gameOver(){
        game = new Game();
        game.startGame();
    }
}
