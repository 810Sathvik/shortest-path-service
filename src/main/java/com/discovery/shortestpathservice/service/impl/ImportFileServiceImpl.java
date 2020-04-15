package com.discovery.shortestpathservice.service.impl;

import com.discovery.shortestpathservice.entity.PlanetRoutes;
import com.discovery.shortestpathservice.repository.PlanetRouteRepository;
import com.discovery.shortestpathservice.service.ImportFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SheshidharReddy.Amireddy
 */
@Slf4j
@Service
public class ImportFileServiceImpl implements ImportFileService {

    @Autowired
    PlanetRouteRepository planetRouteRepository;

    public boolean importFile(Path filePath) throws IOException {

        List<PlanetRoutes> planetRouteList = new ArrayList<PlanetRoutes>();
        boolean flag = false;

        try (BufferedReader br = Files.newBufferedReader(filePath)) {
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

            log.info("-----------> size of the PlanetRoute List is from H2 DB <---------------"+routeList.size());
            log.info("-----------> ended:: importPlanetRouteFile <---------------");
            if(routeList.size() > 0){
                return flag = true;
            }
        } catch (IOException e) {
            return flag;
        }
        return flag;
    }

}
