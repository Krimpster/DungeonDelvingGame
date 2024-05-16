package org.example.game;

import org.example.InputGetter;
import org.example.builders.PlayerBuilder;
import org.example.builders.PlayerDirector;
import org.example.map.MapBuilder;
import org.example.map.Node;
import org.example.interfaces.ICommand;
import org.example.objects.*;
import org.example.objects.items.Equipment;
import org.example.objects.monsters.BossMonster;
import org.example.objects.monsters.NormalMonster;
import org.example.objects.player.PlayerCharacter;

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
    private PlayerCharacter playerCharacter;
    private ArrayList<NormalMonster> monsterList = new ArrayList<>();
    private ArrayList<BossMonster> bossList = new ArrayList<>();
    private ArrayList<Equipment> equipment = new ArrayList<>();
    private  ArrayList<Leaderboard> leaderboard = new ArrayList<>();
    private ArrayList<String> menuChoices = new ArrayList<>();
    private HashMap<String, ICommand> menuCommandHashMap = new HashMap<>();

    public Game(){
        menuChoices.add("\nStart your dungeon delve. (start)");
        menuChoices.add("Preview map (map)");
        menuChoices.add("View leaderboard. (leader)");
        menuChoices.add("View the bestiary. (bestiary)");
        menuChoices.add("View the equipment list. (equip)");
        menuChoices.add("Quit the game. (quit)\n");

        menuCommandHashMap.put("start", () -> startGame());
        menuCommandHashMap.put("map", () -> previewMap());
        menuCommandHashMap.put("quit", () -> quitGame());

        mapBuilder = new MapBuilder();
        nodeMap = mapBuilder.getMap();
        start = mapBuilder.getStart();
        goal = mapBuilder.getGoal();
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
        else{
            System.out.println("That is not a valid class\n");
            startGame();
        }
        exploration = new Exploration(playerCharacter, nodeMap, start, goal,
                mapBuilder, monsterList, bossList, equipment);
        exploration.run();
    }

    private void previewMap(){
        mapBuilder.printMap(nodeMap);
        menu();
    }

    private void quitGame() {
        System.exit(0);
    }

}
