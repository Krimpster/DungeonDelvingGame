package org.example.game;

import org.example.builders.PlayerBuilder;
import org.example.builders.PlayerDirector;
import org.example.factories.BossFactory;
import org.example.factories.MonsterFactory;
import org.example.game.sortingalgorithms.InsertionSort;
import org.example.game.sortingalgorithms.NameInsertionSort;
import org.example.game.sortingalgorithms.QuickSort;
import org.example.interfaces.ISortingOption;
import org.example.map.MapBuilder;
import org.example.map.Node;
import org.example.interfaces.ICommand;
import org.example.objects.*;
import org.example.objects.items.Equipment;
import org.example.objects.monsters.BossMonster;
import org.example.objects.monsters.NormalMonster;
import org.example.objects.player.PlayerCharacter;
import org.example.startup.FileManager;
import org.example.startup.Request;
import org.example.startup.handlers.LoadEquipmentHandler;
import org.example.startup.handlers.LoadLeaderboardHandler;
import org.example.startup.handlers.LoadMonstersHandler;
import static org.example.startup.FileManager.bestiaryFilePath;
import static org.example.startup.FileManager.equipmentFilePath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
    private Scanner scanner = new Scanner(System.in);
    private MapBuilder mapBuilder;
    private Node[][] nodeMap;
    private Node start;
    private Node goal;
    private Exploration exploration;
    private InputGetter inputGetter = new InputGetter();
    private PlayerBuilder playerBuilder = new PlayerBuilder();
    private PlayerDirector playerDirector = new PlayerDirector();
    private MonsterFactory normalFactory = new MonsterFactory();
    private BossFactory bossFactory = new BossFactory();
    private PlayerCharacter playerCharacter;
    private FileManager fileManager = new FileManager();
    private ArrayList<NormalMonster> monsterList = new ArrayList<>();
    private ArrayList<NormalMonster> higherRiskList = new ArrayList<>();
    private ArrayList<BossMonster> bossList = new ArrayList<>();
    private ArrayList<Equipment> equipment = new ArrayList<>();
    private  ArrayList<Leaderboard> leaderboard = new ArrayList<>();
    private ArrayList<String> menuChoices = new ArrayList<>();
    private HashMap<String, ICommand> menuCommandHashMap = new HashMap<>();
    private ArrayList<String> listActions = new ArrayList<>();
    private HashMap<String, ICommand> listActionsCommandHashMap = new HashMap<>();

    public Game(){
        menuChoices.add("\nStart your dungeon delve. (start)");
        menuChoices.add("Preview map (map)");
        menuChoices.add("View leaderboard. (leader)");
        menuChoices.add("View the bestiary. (bestiary)");
        menuChoices.add("View the equipment list. (equip)");
        menuChoices.add("Quit the game. (quit)\n");

        menuCommandHashMap.put("start", () -> characterCreator());
        menuCommandHashMap.put("map", () -> previewMap());
        menuCommandHashMap.put("leader", () -> printLeaderboard(leaderboard));
        menuCommandHashMap.put("bestiary", () -> printMonsters(monsterList, higherRiskList, bossList, "monster"));
        menuCommandHashMap.put("equip", () -> printEquipment(equipment, "equipment"));
        menuCommandHashMap.put("quit", () -> quitGame());

        listActions.add("\nAdd new ");
        listActions.add("Return to menu. (menu)\n");

        listActionsCommandHashMap.put("monster", () -> addMonster());
        listActionsCommandHashMap.put("equipment", () -> addEquipment());
        listActionsCommandHashMap.put("menu", () -> menu());

        mapBuilder = new MapBuilder();
        nodeMap = mapBuilder.getMap();
        start = mapBuilder.getStart();
        goal = mapBuilder.getGoal();
    }

    public void startGame(){
        LoadLeaderboardHandler loadLeaderboardHandler = new LoadLeaderboardHandler(null, fileManager);
        LoadEquipmentHandler loadEquipmentHandler = new LoadEquipmentHandler(loadLeaderboardHandler, fileManager);
        LoadMonstersHandler loadMonstersHandler = new LoadMonstersHandler(loadEquipmentHandler, fileManager);

        Request request = new Request();
        loadMonstersHandler.handle(request, fileManager);


        monsterList = fileManager.getMonsters();
        higherRiskList = fileManager.getRiskierMonsters();
        bossList = fileManager.getBosses();
        equipment = fileManager.getEquipment();
        NameInsertionSort nameInsertionSort = new NameInsertionSort(fileManager.getLeaderboards());
        leaderboard = nameInsertionSort.sortByName();

        menu();
    }

    public void menu(){
        for(String menuChoice: menuChoices){
            System.out.println(menuChoice);
        }

        String startInput = scanner.nextLine();
        if(menuCommandHashMap.containsKey(startInput)){
            menuCommandHashMap.get(startInput).execute();
        }
        else{
            System.out.println("That is not a valid input.\n");
            menu();
        }

    }

    private void characterCreator(){
        String adventurerName = inputGetter.getStringInput("What is your name, adventurer?\n");
        String adventurerClass = inputGetter.getStringInput("What is your given profession? (fighter, mage, rogue)\n");
        if(adventurerClass.equals("fighter")){
            playerCharacter = playerDirector.makeFighter(playerBuilder, adventurerName, adventurerClass);
        }
        else if (adventurerClass.equals("mage")) {
            playerCharacter = playerDirector.makeMage(playerBuilder, adventurerName, adventurerClass);
        }
        else if (adventurerClass.equals("rogue")) {
            playerCharacter = playerDirector.makeRogue(playerBuilder, adventurerName, adventurerClass);
        }
        else{
            System.out.println("That is not a valid class\n");
            characterCreator();
        }
        exploration = new Exploration(playerCharacter, nodeMap, start, goal,
                mapBuilder, monsterList, higherRiskList, bossList, equipment);
        exploration.run();
    }

    private void previewMap(){
        mapBuilder.printMap(nodeMap);
        menu();
    }

    private void doListAction(String message){
        for (int i = 0; i < listActions.size(); i++){
            if(i == listActions.size() - 1) {
                System.out.print(listActions.get(i));
            }
            else {
                System.out.println(listActions.get(i) + message + ". (" + message + ")");
            }
        }

        String input = inputGetter.getStringInput("What would you like to do?\n");

        if(listActionsCommandHashMap.containsKey(input)){
            listActionsCommandHashMap.get(input).execute();
        }
        else{
            System.out.println("That is not a valid input.\n");
            doListAction(message);
        }
    }

    private void quitGame() {
        System.exit(0);
    }

    private void printLeaderboard(ArrayList<Leaderboard> leaderboard){
        System.out.println("\n---LEADERBOARD---\n");
        for (Leaderboard l : leaderboard){
            System.out.println(l.getDetails());
        }
        String input = inputGetter.getStringInput("What would you like to do? (quick, insert, menu) \n");
        if (input.equals("quick")){
            sortLeaderboard(new QuickSort(), leaderboard);
        }
        if (input.equals("insert")){
            sortLeaderboard(new InsertionSort(), leaderboard);
        }
        if (input.equals("menu")){
            menu();
        }
        else {
            System.out.println("Invalid input!");
        }
    }
    private void sortLeaderboard(ISortingOption sortingOption, ArrayList<Leaderboard> leaderboard){
        printLeaderboard(sortingOption.sort(leaderboard));
    }

    private void printMonsters(ArrayList<NormalMonster> monsterList, ArrayList<NormalMonster> higherRiskList,
                               ArrayList<BossMonster> bossList, String message){
        System.out.println("\n---NORMAL MONSTERS---\n");
        for (NormalMonster nm : monsterList){
            System.out.println(nm.getDetails());
        }
        System.out.println("\n---HIGHER RISK MONSTERS---\n");
        for (NormalMonster nm : higherRiskList){
            System.out.println(nm.getDetails());
        }
        System.out.println("\n---BOSS MONSTERS---\n");
        for (BossMonster bm : bossList){
            System.out.println(bm.getDetails());
        }
        doListAction(message);
    }

    private void printEquipment(ArrayList<Equipment> equipment, String message){
        System.out.println("\n---EQUIPMENT---\n");
        for (Equipment e : equipment){
            System.out.println(e.getDetails());
        }
        doListAction(message);
    }

    private void addMonster() {
        String typeOfMonster = inputGetter.getStringInput("What type of monster do you want to add? (normal, high or boss)");

        if (typeOfMonster.equals("normal")){
            monsterList.add(createMonster());
        }
        else if (typeOfMonster.equals("high")){
            higherRiskList.add(createMonster());
        }
        else if (typeOfMonster.equals("boss")){
            bossList.add(createBoss());
        }
        else {
            System.out.println("Not a valid input!");
        }
        fileManager.saveMonsters(bestiaryFilePath.toString());
        menu();
    }

    private NormalMonster createMonster(){
        String name = inputGetter.getStringInput("\nWhat will it's name be? ");
        int hp = inputGetter.getIntInput("\nWhat will it's base hp be? ");
        int baseAttack = inputGetter.getIntInput("\nWhat will it's attack value be? ");
        int baseDefense = inputGetter.getIntInput("\nWhat will it's defense value be? ");
        int baseFocusPoints = inputGetter.getIntInput("\nWhat will it's base focus point value be? ");
        int focusPoints = baseFocusPoints;
        int focusPointsPerTurn = inputGetter.getIntInput("\nWhat will it's focus point regen be? ");
        int skillDamageVarianceOrigin = inputGetter.getIntInput("\nWhat will it's minimum skill damage bound be? ");
        int skillDamageVarianceBound = inputGetter.getIntInput("\nWhat will it's maximum skill damage bound be? ");
        String monsterSkill = inputGetter.getSkillStringInput("\nWhat will it's skill be? You can only choose from these:\n");
        String bossSkill = "none";
        int expReward = inputGetter.getIntInput("\nWhat will it's EXP reward be? ");
        return normalFactory.createMonster(name, hp, baseAttack, baseDefense, baseFocusPoints,
                focusPoints, focusPointsPerTurn, skillDamageVarianceBound, skillDamageVarianceOrigin,
                monsterSkill, bossSkill, expReward);
    }

    private BossMonster createBoss(){
        String name = inputGetter.getStringInput("\nWhat will it's name be? ");
        int hp = inputGetter.getIntInput("\nWhat will it's base hp be? ");
        int baseAttack = inputGetter.getIntInput("\nWhat will it's attack value be? ");
        int baseDefense = inputGetter.getIntInput("\nWhat will it's defense value be? ");
        int baseFocusPoints = inputGetter.getIntInput("\nWhat will it's base focus point value be? ");
        int focusPoints = baseFocusPoints;
        int focusPointsPerTurn = inputGetter.getIntInput("\nWhat will it's focus point regen be? ");
        int skillDamageVarianceOrigin = inputGetter.getIntInput("\nWhat will it's minimum skill damage bound be? ");
        int skillDamageVarianceBound = inputGetter.getIntInput("\nWhat will it's maximum skill damage bound be? ");
        String monsterSkill = inputGetter.getSkillStringInput("\nWhat will it's first skill be? You can only choose from these:\n");
        String bossSkill = inputGetter.getSkillStringInput("\nWhat will it's second skill be? You can only choose from these:\n");
        int expReward = inputGetter.getIntInput("\nWhat will it's EXP reward be? ");
        return bossFactory.createMonster(name, hp, baseAttack, baseDefense, baseFocusPoints,
                focusPoints, focusPointsPerTurn, skillDamageVarianceBound, skillDamageVarianceOrigin,
                monsterSkill, bossSkill, expReward);
    }


    private void addEquipment() {
        String itemName = inputGetter.getStringInput("\nWhat will the item be called? ");
        String itemType = inputGetter.getStringInput("\nWhat type of item is it? ");
        int hpModifier = inputGetter.getIntInput("\nHow much HP does it give? ");
        int attackModifier = inputGetter.getIntInput("\nHow much attack does it give? ");
        int defenseModifier = inputGetter.getIntInput("\nHow much defense does it give? ");
        int fpModifier = inputGetter.getIntInput("\nHow much FP does it give? ");
        int fpPerTurnModifier = inputGetter.getIntInput("\nHow much FP regen does it give? ");
        equipment.add(new Equipment(itemName, itemType, hpModifier, attackModifier,
                defenseModifier, fpModifier, fpPerTurnModifier));
        fileManager.saveEquipment(equipmentFilePath.toString());
        menu();
    }

}
