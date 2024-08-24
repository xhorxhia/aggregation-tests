package com.controller;



import com.model.GeoDetails;
import com.model.GeoRequest;
import com.model.RoadDetail;
import com.repository.RoadDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roadDetail")
public class RoadDetailController {

    @Autowired
    private RoadDetailRepository repo;

 /*  Project GeoDetails obj (get an obj for each road) */
    @GetMapping("/roads")
    public List<GeoDetails> getRoads(){

        return repo.getRoads();
    }

    /*  Project GeoDetails obj (get an obj for each direction) */
    @GetMapping("/allDirections")
    public List<GeoDetails> getDirections(){

        return repo.getAllDirections();
    }


    /*  Project GeoDetails obj (get directions for a road) */
    @PostMapping("/allDirectionsForRoad")
    public List<GeoDetails> getDirectionsForRoad(@RequestBody GeoRequest geoRequest){
        return repo.getDirectionsForARoad(geoRequest.getRoadId());
    }


    /*  Project GeoDetails obj (get an obj for each section of each direction) */
    @GetMapping("/allSections")
    public List<GeoDetails> getSections(){

        return repo.getAllSections();
    }


    /*  Project GeoDetails obj (get sections for a road) */
    @PostMapping("/allSectionsForRoad")
    public List<GeoDetails> getSectionsForRoad(@RequestBody GeoRequest geoRequest){
        return repo.getSectionsForARoad(geoRequest.getRoadId());
    }


    /*  Project GeoDetails obj (get sections for a road and direction) */
    @PostMapping("/sectionsForRoadDirection")
    public List<GeoDetails> getSectionsForRoadAndDirection(@RequestBody GeoRequest geoRequest){
        return repo.getSectionForARoadAndDirection(geoRequest.getRoadId(), geoRequest.getDirectionId());
    }












}
