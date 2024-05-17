package org.example.game;

import org.example.game.Combat;
import org.example.interfaces.ICommand;
import org.example.map.MapBuilder;
import org.example.map.Node;
import org.example.objects.items.Equipment;
import org.example.objects.monsters.BossMonster;
import org.example.objects.monsters.NormalMonster;
import org.example.objects.player.PlayerCharacter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Exploration {
    private ArrayList<NormalMonster> monsterList;
    private ArrayList<NormalMonster> higherRiskList = new ArrayList<>();
    private ArrayList<BossMonster> bossList;
    private ArrayList<Equipment> equipment;
    private Random random = new Random();
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
    private PlayerCharacter playerCharacter;
    private ArrayList<String> explorationChoices = new ArrayList<>();
    private HashMap<String, ICommand> explorationCommandHashMap = new HashMap<>();
    private boolean running = true;

    public Exploration(PlayerCharacter playerCharacter,
                       Node[][] nodeMap,
                       Node start,
                       Node goal,
                       MapBuilder mapBuilder,
                       ArrayList<NormalMonster> monsterList,
                       ArrayList<NormalMonster> higherRiskList,
                       ArrayList<BossMonster> bossList,
                       ArrayList<Equipment> equipment){
        this.playerCharacter = playerCharacter;
        this.nodeMap = nodeMap;
        this.start = start;
        this.goal = goal;
        this.mapBuilder = mapBuilder;
        this.monsterList = monsterList;
        this.higherRiskList = higherRiskList;
        this.bossList = bossList;
        this.equipment = equipment;

        if(playerLocation == null){
            playerLocation = nodeMap[start.getY()][start.getX()];
            start.setPlayerInRoom(true);
            mapBuilder.setGoal(goal);
        }

        explorationChoices.add("Move northward. (up)");
        explorationChoices.add("Move eastward. (r)");
        explorationChoices.add("Move westward. (l)");
        explorationChoices.add("Move southward. (d)");
        explorationChoices.add("Scavenge current room. (scav)");
        explorationChoices.add("Check status. (status)\n\n\n");

        explorationCommandHashMap.put("up", () -> movePlayer(actionInput, playerLocation, nodeMap));
        explorationCommandHashMap.put("r", () -> movePlayer(actionInput, playerLocation, nodeMap));
        explorationCommandHashMap.put("l", () -> movePlayer(actionInput, playerLocation, nodeMap));
        explorationCommandHashMap.put("d", () -> movePlayer(actionInput, playerLocation, nodeMap));
        explorationCommandHashMap.put("scav", () -> scavengeRoom(playerLocation));
        explorationCommandHashMap.put("status", () -> playerCharacter.listCurrentStats());
    }

    public Exploration(PlayerCharacter playerCharacter,
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

        explorationChoices.add("Move northward. (up)");
        explorationChoices.add("Move eastward. (r)");
        explorationChoices.add("Move westward. (l)");
        explorationChoices.add("Move southward. (d)");
        explorationChoices.add("Scavenge current room. (scav)");
        explorationChoices.add("Check status. (status)\n\n\n");

        explorationCommandHashMap.put("up", () -> movePlayer(actionInput, playerLocation, nodeMap));
        explorationCommandHashMap.put("r", () -> movePlayer(actionInput, playerLocation, nodeMap));
        explorationCommandHashMap.put("l", () -> movePlayer(actionInput, playerLocation, nodeMap));
        explorationCommandHashMap.put("d", () -> movePlayer(actionInput, playerLocation, nodeMap));
        explorationCommandHashMap.put("scav", () -> scavengeRoom(playerLocation));
        explorationCommandHashMap.put("status", () -> playerCharacter.listCurrentStats());
    }

    public void run(){
        String bossEncounterMessage = "You have reached the end of the dungeon, prepare for a big fight!\n";
        String monsterEncounterMessage = "You sense a malicious presence in the room..\n";
        while(running) {
            mapBuilder.printMap(nodeMap);
            for (String action : explorationChoices) {
                System.out.println(action);
            }
            actionInput = scanner.nextLine();
            if (explorationCommandHashMap.containsKey(actionInput)) {
                playerStatActions(playerCharacter);
                explorationCommandHashMap.get(actionInput).execute();
            }
            if (nodeMap[playerLocation.getY()][playerLocation.getX()].isGoal()) {
                playerStatActions(playerCharacter);
                startCombat(bossEncounterMessage);
                running = false;
            }
            if(nodeMap[playerLocation.getY()][playerLocation.getX()].isMonsterRoom()){
                playerStatActions(playerCharacter);
                startCombat(monsterEncounterMessage);
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
                movementActions(nodeMap, previousPosYValue, previousPosXValue,
                        playerPosYValue - 1, playerPosXValue, "north");

            }
            if (actionInput.equals("r")) {
                movementActions(nodeMap, previousPosYValue, previousPosXValue,
                        playerPosYValue, playerPosXValue + 1, "east");
            }
            if (actionInput.equals("l")) {
                movementActions(nodeMap, previousPosYValue, previousPosXValue,
                        playerPosYValue, playerPosXValue - 1, "west");
            }
            if (actionInput.equals("d")) {
                movementActions(nodeMap, previousPosYValue, previousPosXValue,
                        playerPosYValue + 1, playerPosXValue, "south");
            }
            turnsTaken++;
        }
        catch(Exception e){
            System.out.println("There's a wall blocking your passage.\n");
            run();
        }
    }

    private void scavengeRoom(Node playerLocation){
        if (playerLocation.isTreasureRoom()){
            int randomSelectedItem = random.nextInt(equipment.size());
            System.out.println("You found an item! It's a " + equipment.get(randomSelectedItem).getItemType() + ".\n" +
                    equipment.get(randomSelectedItem).getItemName() + " added to inventory! \n");
            playerCharacter.addEquipmentToAbsorbedList(equipment.get(randomSelectedItem));
        }else{
            System.out.println("You were unable to find anything. What a shame...\n");
        }
        run();
    }
    private void startCombat(String message){
        System.out.println(message);
        Combat combat = new Combat(playerCharacter, mapBuilder, nodeMap, playerLocation,
                start, goal, turnsTaken, monsterList, higherRiskList, bossList, equipment);
        combat.startCombat();
    }
    private void movementActions(Node[][] nodeMap, int previousPosYValue, int previousPosXValue,
                                 int modifiedPosYValue, int modifiedPosXValue, String directionOfMovement){
        if (!nodeMap[modifiedPosYValue][modifiedPosXValue].isEmptySpace()){
            setPlayerInRoom(nodeMap, previousPosYValue, previousPosXValue, false);
            setVisitedRoom(nodeMap, previousPosYValue, previousPosXValue);
            setPlayerInRoom(nodeMap, modifiedPosYValue, modifiedPosXValue, true);
            System.out.println("You move " + directionOfMovement + ".");
        }
        else{
            System.out.println("A wall is blocking your passage.");
            run();
        }
    }
    private void setPlayerInRoom(Node[][] nodeMap, int posY, int posX, boolean state){
         nodeMap[posY][posX].setPlayerInRoom(state);
         this.playerLocation = nodeMap[posY][posX];
    }
    private void setVisitedRoom(Node[][] nodeMap, int posY, int posX){
        nodeMap[posY][posX].setVisitedRoom(true);
    }

    private void playerStatActions(PlayerCharacter playerCharacter){
        playerCharacter.passiveFPRegen();
    }
}
