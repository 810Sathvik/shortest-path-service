package com.discovery.shortestpathservice;

import com.discovery.shortestpathservice.entity.PlanetNames;
import com.discovery.shortestpathservice.entity.PlanetRoutes;
import com.discovery.shortestpathservice.repository.PlanetNameRepository;
import com.discovery.shortestpathservice.repository.PlanetRouteRepository;

import com.discovery.shortestpathservice.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SheshidharReddy.Amireddy
 */
@Slf4j
@SpringBootApplication (scanBasePackages = "com.discovery.shortestpathservice.*")
@EnableJpaRepositories("com.discovery.shortestpathservice.*")
@EntityScan( basePackages = {"com.discovery.shortestpathservice.*"} )
@EnableTransactionManagement
public class ShortestPathServiceApplication implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    PlanetRouteRepository planetRouteRepository;

    @Autowired
    PlanetNameRepository planetNameRepository;


    public static void main(String[] args) {
        SpringApplication.run(ShortestPathServiceApplication.class, args);
    }


   @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        try {
            log.info("-------------------> import PlanetNames started <-------------------------");
            importPlanetNamesFile();
            log.info("-------------------> import PlanetNames completed <-------------------------");
            log.info("-------------------> Import PlanetRoutes started <-------------------------");
            importPlanetRouteFile();
            log.info("-------------------> Import PlanetRoutes completed <-------------------------");

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void importPlanetRouteFile() throws IOException {
        log.info("-------------------> inside :: importPlanetRouteFile() of ShortestPathServiceApplication <-------------------------");
        log.info("-------------------> started :: importPlanetRouteFile <-------------------------");

        List<PlanetRoutes> planetRouteList = new ArrayList<PlanetRoutes>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(Constants.PLANET_ROUTES_FILE_PATH))){
            br.readLine();
            String readLine = "";
            while ((readLine = br.readLine()) != null) {
                PlanetRoutes planetRoute = new PlanetRoutes.Builder()
                        .setId(Long.parseLong(readLine.substring(0, 4).trim()))
                        .setPlanetSource(readLine.substring(4, 8).trim())
                        .setPlanetDestination(readLine.substring(8, 12).trim())
                        .setDistance(Float.parseFloat(readLine.substring(12).trim())).build();
                planetRouteList.add(planetRoute);
            }
            planetRouteRepository.saveAll(planetRouteList);

            List<PlanetRoutes> routeList = (List<PlanetRoutes>)planetRouteRepository.findAll();
            log.info("-------------------> size of the PlanetRoute List is from H2 DB <-------------------------"+routeList.size());
            log.info("-------------------> ended:: importPlanetRouteFile <-------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void importPlanetNamesFile() throws IOException {
        log.info("-------------------> inside :: importPlanetNamesFile() of ShortestPathServiceApplication <-------------------------");
        log.info("-------------------> started:: importPlanetNamesFile <-------------------------");
        List<PlanetNames> planetNamesList = new ArrayList<PlanetNames>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(Constants.PLANET_NAMES_FILE_PATH))) {
            br.readLine();
            String readLine = "";
            while ((readLine = br.readLine()) != null) {
                PlanetNames planetNames = new PlanetNames.Builder()
                        .setPlanetNode(readLine.substring(0, 4).trim())
                        .setPlanetSourceName(readLine.substring(4).trim()).build();
                planetNamesList.add(planetNames);
            }

            planetNameRepository.saveAll(planetNamesList);

            List<PlanetNames> namesList = (List<PlanetNames>)planetNameRepository.findAll();
            log.info("-------------------> size of the Planet Names list is from H2 DB <-------------------------"+namesList.size());
            log.info("-------------------> ended:: importPlanetNamesFile <-------------------------");
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }


}

