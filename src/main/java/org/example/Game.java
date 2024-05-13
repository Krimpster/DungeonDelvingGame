package org.example;

import org.example.builders.PlayerBuilder;
import org.example.builders.PlayerDirector;
import org.example.map.MapBuilder;
import org.example.map.Node;
import org.example.interfaces.ICommand;
import org.example.objects.*;
import org.example.objects.items.Equipment;
import org.example.objects.monsters.BossMonster;
import org.example.objects.monsters.Monster;
import org.example.objects.monsters.NormalMonster;
import org.example.objects.player.PlayerCharacter;
import org.example.objects.player.PlayerSkills;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
    private Scanner scanner = new Scanner(System.in);
    MapBuilder mapBuilder;
    Node[][] nodeMap;
    Node start;
    Node goal;
    Exploration exploration;
    Combat combat;
    InputGetter inputGetter = new InputGetter();
    PlayerBuilder playerBuilder = new PlayerBuilder();
    PlayerDirector playerDirector = new PlayerDirector();
    PlayerCharacter playerCharacter;
    public static final String checkedRoomSign = "O";
    public static final String playerInRoomSign = "P";
    ArrayList<NormalMonster> monsters;
    ArrayList<BossMonster> bosses;
    ArrayList<Item> items;
    ArrayList<Equipment> equipment;
    ArrayList<String> menuChoices = new ArrayList<>();
    HashMap<String, ICommand> menuCommandHashMap = new HashMap<>();

    public Game(){
        menuChoices.add("Start your dungeon delve. (start)");
        menuChoices.add("View leaderboard. (leader)");
        menuChoices.add("View the bestiary. (bestiary)");
        menuChoices.add("View the item and equipment list. (items)");
        menuChoices.add("Quit the game. (quit)\n");

        menuCommandHashMap.put("start", () -> startGame());
        menuCommandHashMap.put("test", () -> combatTest());
        menuCommandHashMap.put("quit", () -> quitGame());

        mapBuilder = new MapBuilder();
        nodeMap = mapBuilder.getMap();
        start = mapBuilder.getStart();
        goal = mapBuilder.getGoal();
    }

    public void menu(){
        /*MapChecker mapChecker = new MapChecker();
        mapChecker.checkMap();*/
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

    private void startGame(){
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
        exploration = new Exploration(playerCharacter, nodeMap, start, goal, mapBuilder, exploration);
        exploration.run();
    }

    private void combatTest(){
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
        combat = new Combat(playerCharacter);
        combat.startTestEncounter();
    }

    private void quitGame() {
        System.exit(0);
    }

}
