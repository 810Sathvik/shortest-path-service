package com.discovery.shortestpathservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author SheshidharReddy.Amireddy
 */

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class PlanetRoutes {

    @Id
    private long id;
    private String planetSource;
    private String planetDestination;
    private Float distance;

    public static class Builder {
        private long id;
        private String planetSource;
        private String planetDestination;
        private Float distance;

        public Builder(){}
        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setPlanetSource(String planetSource) {
            this.planetSource = planetSource;
            return this;
        }

        public Builder setPlanetDestination(String planetDestination) {
            this.planetDestination = planetDestination;
            return this;
        }

        public Builder setDistance(Float distance) {
            this.distance = distance;
            return this;
        }

        public PlanetRoutes build() {
            return new PlanetRoutes(id, planetSource, planetDestination, distance);
        }
    }

}
