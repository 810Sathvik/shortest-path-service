package com.discovery.shortestpathservice.service;


import com.discovery.shortestpathservice.entity.PlanetRoutes;

import java.util.List;

/**
 * @author SheshidharReddy.Amireddy
 */
public interface PlanetRouteService {

	public List<PlanetRoutes> getAllPlanetRoutes();

	public void saveRoute(PlanetRoutes route);

	public void deleteRoute(Long routeId);

}
