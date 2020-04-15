package com.discovery.shortestpathservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.discovery.shortestpathservice.entity.PlanetRoutes;
import org.springframework.stereotype.Repository;

/**
 * @author SheshidharReddy.Amireddy
 */
@Repository
public interface PlanetRouteRepository extends CrudRepository<PlanetRoutes, Long>{

}
