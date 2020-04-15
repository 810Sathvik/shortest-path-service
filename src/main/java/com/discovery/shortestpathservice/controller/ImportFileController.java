package com.discovery.shortestpathservice.controller;

import com.discovery.shortestpathservice.repository.PlanetRouteRepository;
import com.discovery.shortestpathservice.entity.PlanetRoutes;
import com.discovery.shortestpathservice.service.ImportFileService;
import com.discovery.shortestpathservice.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SheshidharReddy.Amireddy
 */

@Slf4j
@RestController
@RequestMapping("/importFileSer")
public class ImportFileController {

    @Autowired
    ImportFileService importFileService;

    @RequestMapping(value = "/importFile", method = RequestMethod.GET)
    public String importFile() throws IOException {
        log.info("###### start:: importFile #################");
        Path filepath = Paths.get("src/main/resources/graph/planetroutes.txt");
        List<PlanetRoutes> routeList = new ArrayList<PlanetRoutes>();
        String response = null;
        try {
            boolean result = importFileService.importFile(filepath);
            if(result){
                response = Constants.SUCCESS_RESPONSE;
            } else {
                response =  Constants.FAILURE_RESPONSE;
            }
        } catch (IOException e) {
            response =  Constants.FAILURE_RESPONSE;
        }
        return response;
    }
}
