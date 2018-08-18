package org.mashup.takoyaki.controller;

import org.mashup.takoyaki.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/map/trucks")
public class MapController {

    @Autowired
    MapService mapService;

    @GetMapping("/trucks")
    public List mapTruck(@RequestParam("latitude")long latitude, @RequestParam("longitude")long longitude) {
        return mapService.getMapTruck(latitude, longitude);
    }
}
