package com.discovery.shortestpathservice.controller;


import com.discovery.shortestpathservice.model.PlanetNamesVO;
import com.discovery.shortestpathservice.service.ShortestPathService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * @author SheshidharReddy.Amireddy
 */

@Slf4j
@Controller
public class ShortestPathController {

    @Autowired
    private ShortestPathService shortestPathService;

    
    @GetMapping("/")
    public String loadUIPage(Model model) {
        model.addAttribute("planetNames", new PlanetNamesVO());
        return "displayUI";
    }
    
    

    @PostMapping("/shortestpath")
    public String getShortestPath(Model model, @ModelAttribute PlanetNamesVO planetNamesVO) {
    	log.info("------------> start :: getShortestPath() -----------> with sourceNode "+planetNamesVO.getPlanetSourceName()+ "and distination Name "+planetNamesVO.getDestinationPlanetName());
        String shortestPath = shortestPathService.getShortestPath(planetNamesVO.getPlanetSourceName(), planetNamesVO.getDestinationPlanetName());
        log.info("------------> end :: getShortestPath() --------------> ");
        model.addAttribute("shortestPath", shortestPath);
        return "success";
    }


}
