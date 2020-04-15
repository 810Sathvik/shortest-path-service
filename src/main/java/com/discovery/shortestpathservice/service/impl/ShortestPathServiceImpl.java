package com.discovery.shortestpathservice.service.impl;

import com.discovery.shortestpathservice.config.ShortestPathAlgorithm;
import com.discovery.shortestpathservice.entity.Graph;
import com.discovery.shortestpathservice.entity.Node;
import com.discovery.shortestpathservice.entity.PlanetNames;
import com.discovery.shortestpathservice.entity.PlanetRoutes;
import com.discovery.shortestpathservice.repository.PlanetNameRepository;
import com.discovery.shortestpathservice.repository.PlanetRouteRepository;
import com.discovery.shortestpathservice.service.ShortestPathService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SheshidharReddy.Amireddy
 */
@Slf4j
@Service
public class ShortestPathServiceImpl implements ShortestPathService {

    @Autowired
    PlanetNameRepository planetNameRepository;

    @Autowired
    PlanetRouteRepository planetRouteRepository;

    public String getShortestPath(String sourceNode, String destinationNode) {

    	log.info("inside getShortestPath() of ShortestPathServiceImpl with Params Source Node -->"+sourceNode+" Destination Node ----->"+destinationNode);

        List<PlanetNames> planetNames = (List<PlanetNames>)planetNameRepository.findAll();

        List<Node> nodesList = new ArrayList<Node>();

        planetNames.forEach(s -> {
            Node node = new Node(s.getPlanetNode());
            nodesList.add(node);
        });

        List<PlanetRoutes> routes = (List<PlanetRoutes>)planetRouteRepository.findAll();
        nodesList.forEach(n -> {
            addDestination(n, nodesList, routes);
        });

        Graph graph = new Graph();
        for (Node node : nodesList) {
            graph.addNode(node);
        }
        graph = ShortestPathAlgorithm.calculateShortestPath(graph, nodesList.get(0));

        log.info(">>>>>>>>>>>>>>>  graph after calculating Shortest Path >>>>>>>>>>>>>>>>");

        StringBuffer sb = new StringBuffer();
        for( Node node:graph.getNodes()) {

            if(node.getName().equalsIgnoreCase(destinationNode)) {
                for(Node n: node.getShortestPath()) {
                    sb.append(n.getName()).append("->");
                }
            }

        }
        String shortestPath = sb.append(destinationNode).toString();
        log.info("displaying :: shortest Path started  >>>>>>>>>>>>>>>>>>>>>>>>>> "+ shortestPath);
        log.info(">>>>>>>>>>>>>> end :: shortestPath >>>>>>>>>>>>>");
        return shortestPath;
    }

    private void addDestination(Node n, List<Node> listNode, List<PlanetRoutes> routes) {

        routes.forEach(r -> {
            if (r.getPlanetSource().equalsIgnoreCase(n.getName())) {
                listNode.forEach(l -> {
                    if (l.getName().equalsIgnoreCase(r.getPlanetDestination())) {
                        n.addDestination(l, r.getDistance());
                    }
                });
            }
        });

    }

}
