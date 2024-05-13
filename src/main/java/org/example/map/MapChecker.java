package org.example.map;

import java.util.ArrayList;



import static org.example.Game.checkedRoomSign;
import static org.example.Game.playerInRoomSign;

public class MapChecker {
    MapBuilder mapBuilder = new MapBuilder();
    Node[][] nodeMap;
    Node goal;
    Node start;
    ArrayList<Node> alreadyChecked = new ArrayList<>();
    ArrayList<Node> openList = new ArrayList<>();

    public void checkMap(){
        nodeMap = mapBuilder.getMap();
        goal = mapBuilder.getGoal();
        start = mapBuilder.getStart();

        //printMap(nodeMap);
        aStar();
    }

    private void aStar(){
        openList.add(start);
        setDistanceToGoal(start);

        while(openList.size() > 0){
            Node nextNode = openList.get(0);
            if(nextNode.isGoal()) {
                printResult(nextNode);
                return;
            }
            else{
                //addNeighborsToOpen(nextNode);
            }
            //mapBuilder.printMap(nodeMap);
        }
    }

    /*private void addNeighborsToOpen(Node node){
        alreadyChecked.add(node);
        if (!node.isStart()) {
            node.setSign(playerInRoomSign);
        }
        openList.remove(node);
        ArrayList<Neighbour> neighbourList = node.getNeighbourList();
        for(Neighbour neighbour: neighbourList){
            Node neighbourNode = neighbour.getNode();
            if (neighbourNode.isEmptySpace()){
                continue;
            } else if (neighbourNode.isMonsterRoom()) {
                continue;
            }
            double distance = neighbour.getDistance();
            if (isAlreadyInNeighbourList(neighbourNode) && pathIsShorter(node, neighbourNode, distance)){
                reAddToNodeList(node, neighbourNode, distance);
            }
            else{
                addToNodeList(node,neighbourNode,distance);
            }
        }
    }*/

    private boolean isAlreadyInNeighbourList(Node neighbourNode){
        return openList.contains(neighbourNode) || alreadyChecked.contains(neighbourNode);
    }

    private boolean pathIsShorter(Node node, Node neighbourNode, double distance){
        return neighbourNode.getPathCost() > node.getPathCost() + distance;
    }

    private void addToNodeList(Node node, Node neighbourNode, double distance){
        neighbourNode.setPathCost(node.getPathCost() + distance);
        setDistanceToGoal(neighbourNode);
        neighbourNode.setPreviousNode(neighbourNode);
        openList.add(neighbourNode);
        if(!neighbourNode.isGoal()){
            neighbourNode.setSign(checkedRoomSign);
        }
    }

    private void reAddToNodeList(Node node, Node neighbourNode, double distance){
        neighbourNode.setPathCost((node.getPathCost() + distance));
        neighbourNode.setPreviousNode(node);
        if(alreadyChecked.contains(neighbourNode)){
            alreadyChecked.remove(neighbourNode);
            neighbourNode.setSign(checkedRoomSign);
            openList.add(neighbourNode);
        }
    }

    private void setDistanceToGoal(Node node){
        double distanceX = node.getX() - goal.getX();
        double distanceY = node.getY() - goal.getY();
        double distance = Math.sqrt(Math.pow(distanceY, 2) + Math.pow(distanceX, 2));
        node.setDistanceToGoal(distance);
    }

    private ArrayList<Node> getResult(Node node){
        ArrayList<Node> result = new ArrayList<>();
        Node currentNode = node;
        while(currentNode != null){
            result.add(currentNode);
            currentNode = currentNode.getPreviousNode();
        }
        return result;
    }

    private void printResult(Node nextNode){
        ArrayList<Node> result = getResult(nextNode);
        for(Node node : result){
            if(!node.isGoal() && !node.isStart()){
                node.setSign("X");
            }
        }
    }
}
