package org.example.map;

public class Neighbour {
    private Node node;
    private double distance;

    public Neighbour(Node node, double distance){
        this.node = node;
        this.distance = distance;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
