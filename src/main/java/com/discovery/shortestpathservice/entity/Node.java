package com.discovery.shortestpathservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author SheshidharReddy Amireddy
 */

@Slf4j
public class Node {

    private String name;

    private List<Node> shortestPath = new LinkedList<Node>();

    private String path;

    private Float distance = Float.MAX_VALUE;

    private Map<Node, Float> adjacentNodes = new HashMap<>();

    public Node(String name) {
        this.name = name;
    }

    public void addDestination(Node destination, Float distance) {
        adjacentNodes.put(destination, distance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Node, Float> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Float> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(LinkedList<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

}