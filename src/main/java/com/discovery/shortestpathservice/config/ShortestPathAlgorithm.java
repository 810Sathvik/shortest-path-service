package com.discovery.shortestpathservice.config;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import com.discovery.shortestpathservice.entity.Graph;
import com.discovery.shortestpathservice.entity.Node;

/**
 * @author SheshidharReddy.Amireddy
 */

@Slf4j
public class ShortestPathAlgorithm {

	public static Graph calculateShortestPath(Graph graph, Node source) {
    	log.info("-----------------------> start :: calculateShortestPath <-----------------------");
        source.setDistance(0f);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();
        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Entry<Node, Float> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Float edgeWeigh = adjacencyPair.getValue();

                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeigh, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        log.info("------------------> end :: calculateShortestPath <-----------------");
        return graph;
    }

    private static void calculateMinimumDistance(Node evaluationNode, Float edgeWeigh, Node sourceNode) {
    	log.info("--------------------> start :: calculateMinimumDistance <-----------------------");
        Float sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
        log.info("-------------------> end :: calculateMinimumDistance <--------------------");
    }

    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
    	log.info("---------------------> start :: getLowestDistanceNode <-------------------");
        Node lowestDistanceNode = null;
        Float lowestDistance = Float.MAX_VALUE;
        for (Node node : unsettledNodes) {
            Float nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        log.info("----------------> end :: getLowestDistanceNode <--------------------------");
        return lowestDistanceNode;
    }
}