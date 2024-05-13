package org.example;

import org.example.interfaces.ICommand;
import org.example.map.MapBuilder;
import org.example.map.Node;
import org.example.objects.player.PlayerCharacter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Exploration {
    private int turnsTaken;
    int playerPosXValue;
    int playerPosYValue;
    private String actionInput;
    private Scanner scanner = new Scanner(System.in);
    private Node[][] nodeMap;
    private Node start;
    private Node goal;
    private Node playerLocation;
    private Node previousNode;
    private MapBuilder mapBuilder;
    private Exploration exploration;
    private PlayerCharacter playerCharacter;
    private ArrayList<String> explorationChoices = new ArrayList<>();
    private HashMap<String, ICommand> explorationCommandHashMap = new HashMap<>();
    private boolean running = true;
    public Exploration(PlayerCharacter playerCharacter,
                       Node[][] nodeMap,
                       Node start,
                       Node goal,
                       MapBuilder mapBuilder,
                       Exploration exploration){
        this.playerCharacter = playerCharacter;
        this.nodeMap = nodeMap;
        this.start = start;
        this.goal = goal;
        this.mapBuilder = mapBuilder;
        this.exploration = exploration;

        if(playerLocation == null){
            playerLocation = nodeMap[start.getY()][start.getX()];
            start.setPlayerInRoom(true);
            mapBuilder.setGoal(goal);
        }


        explorationChoices.add("Move northward. (up)");
        explorationChoices.add("Move eastward. (r)");
        explorationChoices.add("Move westward. (l)");
        explorationChoices.add("Move southward. (d)");
        explorationChoices.add("Scavenge current room. (scav)\n\n\n");

        explorationCommandHashMap.put("up", () -> movePlayer(actionInput, playerLocation, nodeMap));
        explorationCommandHashMap.put("r", () -> movePlayer(actionInput, playerLocation, nodeMap));
        explorationCommandHashMap.put("l", () -> movePlayer(actionInput, playerLocation, nodeMap));
        explorationCommandHashMap.put("d", () -> movePlayer(actionInput, playerLocation, nodeMap));
        explorationCommandHashMap.put("scav", () -> scavengeRoom(playerLocation));
    }

    public void run(){
        while(running) {
            mapBuilder.printMap(nodeMap);
            for (String action : explorationChoices) {
                System.out.println(action);
            }
            actionInput = scanner.nextLine();
            if (explorationCommandHashMap.containsKey(actionInput)) {
                explorationCommandHashMap.get(actionInput).execute();
                turnsTaken++;
            }
            if (nodeMap[playerLocation.getY()][playerLocation.getX()].isGoal()) {
                System.out.println("You have reached the end of the dungeon, prepare for a big fight!");
                Combat combat = new Combat(playerCharacter, turnsTaken);
                combat.startBossEncounter();
                running = false;
            }
            if(nodeMap[playerLocation.getY()][playerLocation.getX()].isMonsterRoom()){
                Combat combat = new Combat(playerCharacter, nodeMap, start, goal, mapBuilder, exploration);
                combat.startCombat();
            }
        }
    }

    private void movePlayer(String actionInput, Node playerLocation, Node[][] nodeMap){
        previousNode = playerLocation;
        for(Node[] row: nodeMap){
            for(Node node : row){
                if (node.isPlayerInRoom()){
                    playerPosXValue = node.getX();
                    playerPosYValue = node.getY();
                }
            }
        }
        int previousPosXValue = previousNode.getX();
        int previousPosYValue = previousNode.getY();
        try {
            if (actionInput.equals("up")) {
                setPlayerInRoom(nodeMap, previousPosYValue, previousPosXValue, false);
                setVisitedRoom(nodeMap, previousPosYValue, previousPosXValue);
                setPlayerInRoom(nodeMap, playerPosYValue - 1, playerPosXValue, true);
            }
            if (actionInput.equals("r")) {
                setPlayerInRoom(nodeMap, previousPosYValue, previousPosXValue, false);
                setVisitedRoom(nodeMap, previousPosYValue, previousPosXValue);
                setPlayerInRoom(nodeMap, playerPosYValue, playerPosXValue + 1, true);
            }
            if (actionInput.equals("l")) {
                setPlayerInRoom(nodeMap, previousPosYValue, previousPosXValue, false);
                setVisitedRoom(nodeMap, previousPosYValue, previousPosXValue);
                setPlayerInRoom(nodeMap, playerPosYValue, playerPosXValue - 1, true);
            }
            if (actionInput.equals("d")) {
                setPlayerInRoom(nodeMap, previousPosYValue, previousPosXValue, false);
                setVisitedRoom(nodeMap, previousPosYValue, previousPosXValue);
                setPlayerInRoom(nodeMap, playerPosYValue + 1, playerPosXValue, true);
            }
        }
        catch(Exception e){
            System.out.println("There's a wall blocking your passage.\n");
            this.playerLocation = previousNode;
            run();
        }
    }

    private void scavengeRoom(Node playerLocation){
        if (playerLocation.isTreasureRoom()){
            System.out.println("You got an item\n");
        }else{
            System.out.println("You were unable to find anything. What a shame...\n");
        }
        run();
    }
    private void setPlayerInRoom(Node[][] nodeMap, int posY, int posX, boolean state){
         nodeMap[posY][posX].setPlayerInRoom(state);
         this.playerLocation = nodeMap[posY][posX];
    }
    private void setVisitedRoom(Node[][] nodeMap, int posY, int posX){
        nodeMap[posY][posX].setVisitedRoom(true);
    }

}
