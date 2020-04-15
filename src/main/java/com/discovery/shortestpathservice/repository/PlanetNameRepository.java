package com.discovery.shortestpathservice.repository;

import com.discovery.shortestpathservice.entity.PlanetNames;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author SheshidharReddy.Amireddy
 */
@Repository
public interface PlanetNameRepository extends CrudRepository<PlanetNames, Long> {
}
