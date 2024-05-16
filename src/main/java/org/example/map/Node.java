package org.example.map;

import java.util.ArrayList;

public class Node {
    private int x;
    private int y;
    private Node previousNode;
    //private ArrayList<Neighbour> neighbourList = new ArrayList<>();
    private double pathCost;
    private double distanceToGoal;
    private boolean playerInRoom;
    private boolean visitedRoom;
    private boolean treasureRoom;
    private boolean monsterRoom;
    private boolean pathwayRoom;
    private boolean emptySpace;
    private boolean start;
    private boolean goal;
    private char sign = '.';

    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }

    public double getValue() {
        return pathCost + distanceToGoal;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }

    /*public ArrayList<Neighbour> getNeighbourList() {
        return neighbourList;
    }

    public void setNeighbourList(ArrayList<Neighbour> neighbourList) {
        this.neighbourList = neighbourList;
    }*/

    public double getPathCost() {
        return pathCost;
    }

    public void setPathCost(double pathCost) {
        this.pathCost = pathCost;
    }

    public double getDistanceToGoal() {
        return distanceToGoal;
    }

    public void setDistanceToGoal(double distanceToGoal) {
        this.distanceToGoal = distanceToGoal;
    }

    public boolean isPlayerInRoom() {
        return playerInRoom;
    }

    public void setPlayerInRoom(boolean playerInRoom) {
        this.playerInRoom = playerInRoom;
    }

    public boolean isVisitedRoom() {
        return visitedRoom;
    }

    public void setVisitedRoom(boolean visitedRoom) {
        this.visitedRoom = visitedRoom;
    }

    public boolean isTreasureRoom() {
        return treasureRoom;
    }

    public void setTreasureRoom(boolean treasureRoom) {
        this.treasureRoom = treasureRoom;
    }

    public boolean isMonsterRoom() {
        return monsterRoom;
    }

    public void setMonsterRoom(boolean monsterRoom) {
        this.monsterRoom = monsterRoom;
    }

    public boolean isPathwayRoom() {
        return pathwayRoom;
    }

    public void setPathwayRoom(boolean pathwayRoom) {
        this.pathwayRoom = pathwayRoom;
    }

    public boolean isEmptySpace() {
        return emptySpace;
    }

    public void setEmptySpace(boolean emptySpace) {
        this.emptySpace = emptySpace;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isGoal() {
        return goal;
    }

    public void setGoal(boolean goal) {
        this.goal = goal;
    }

    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }
}
