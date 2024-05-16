package org.example.map;

import java.util.Random;

public class MapBuilder {
    private Node start;
    private Node goal;
    private Node[][] nodeMap;
    public final String checkedRoomSign = "O";
    public final char playerInRoomSign = '☻';
    private Random randomGenerator = new Random();

    public Node[][] getMap(){
        int[][] intMap = generateIntMap();
        nodeMap = new Node[intMap.length][intMap[0].length];
        char startSign = 'S';
        char goalSign = 'G';
        char combatRoomSign = 'M';
        char treasureRoomSign = '*';
        char emptySpaceSign = 'X';
        char pathwayRoomSign = '.';

        for (int y = 0; y < intMap.length; y++){
            Node[] row = new Node[intMap.length];
            for(int x = 0; x < intMap[y].length; x++){
                Node node = new Node(x, y);
                if(intMap[y][x] == 1){
                    node.setStart(true);
                    start = node;
                    node.setSign(startSign);
                }
                else if (intMap[y][x] == 2) {
                    node.setGoal(true);
                    goal = node;
                    node.setSign(goalSign);
                }
                else if (intMap[y][x] >= 3 && intMap[y][x] <= 13) {
                    node.setEmptySpace(true);
                    node.setSign(emptySpaceSign);
                }
                else if (intMap[y][x] >= 14 && intMap[y][x] <= 99) {
                    node.setPathwayRoom(true);
                    node.setSign(pathwayRoomSign);
                }
                else if (intMap[y][x] >= 100 && intMap[y][x] <= 110) {
                    node.setTreasureRoom(true);
                    node.setSign(treasureRoomSign);
                }
                else{
                    node.setMonsterRoom(true);
                    node.setSign(combatRoomSign);
                }
                row[x] = node;
            }
            nodeMap[y] = row;
        }
        return nodeMap;
    }

    private int[][] generateIntMap(){
        int mapSizeX = 8;
        int mapSizeY = 8;
        int randomGeneratorBound = 150;
        int randomGeneratorOffset = 3;
        int[][] newIntMap = new int[mapSizeX][mapSizeY];
        for(int i = 0; i < mapSizeX; i++){
            for(int j = 0; j < mapSizeY; j++){
                if(i == 0 && j == 3){
                    newIntMap[i][j] = 2;
                }
                else if(i == 7 && j == 5){
                    newIntMap[i][j] = 1;
                }
                else{
                    newIntMap[i][j] = randomGenerator.nextInt(randomGeneratorBound) + randomGeneratorOffset;
                }
            }
        }
        return newIntMap;
    }

    public void printMap(Node[][] nodeMap){
        for(Node[] row: nodeMap){
            for(Node node : row){
                if (node.isPlayerInRoom()) {
                    System.out.print(playerInRoomSign + " ");
                }
                else if (node.isVisitedRoom()) {
                    System.out.print(checkedRoomSign + " ");
                }
                else{
                    System.out.print(node.getSign() + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getGoal() {
        return goal;
    }

    public void setGoal(Node goal) {
        this.goal = goal;
    }
}
