package org.mashup.takoyaki.service;

import org.mashup.takoyaki.entity.Report;
import org.mashup.takoyaki.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;

public class MapServiceImpl implements MapService {

    private List trucks = new ArrayList();

    @Autowired
    private MapRepository mapRepository;

    List<Report> truckList;

    @Override
    public List getMapTruck(long latitude, long longitude){

        truckList = mapRepository.findByNearTruck(latitude, longitude);
        return truckList;

    }

}
