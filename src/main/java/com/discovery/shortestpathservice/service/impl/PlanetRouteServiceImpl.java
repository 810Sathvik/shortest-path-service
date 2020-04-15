package com.discovery.shortestpathservice.service.impl;

import com.discovery.shortestpathservice.entity.PlanetRoutes;
import com.discovery.shortestpathservice.repository.PlanetRouteRepository;
import com.discovery.shortestpathservice.service.PlanetRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SheshidharReddy.Amireddy
 */
@Slf4j
@Service
public class PlanetRouteServiceImpl implements PlanetRouteService {

    @Autowired
    PlanetRouteRepository planetRouteRepository;

    public List<PlanetRoutes>getAllPlanetRoutes(){
        return (List<PlanetRoutes>) planetRouteRepository.findAll();
    }

    public void saveRoute(PlanetRoutes route) {
        log.info("-----------------> start PlanetRouteServiceImpl :: saveRoute <------------------");
        planetRouteRepository.save(route);
    }

    public void deleteRoute(Long routeId) {
        log.info("---------------------> start PlanetRouteServiceImpl :: deleteRoute <-----------------");
        planetRouteRepository.deleteById(routeId);
    }

}
