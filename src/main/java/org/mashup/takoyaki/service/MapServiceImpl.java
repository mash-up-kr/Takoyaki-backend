package org.mashup.takoyaki.service;

import lombok.extern.slf4j.Slf4j;
import org.mashup.takoyaki.dto.ReportDto;
import org.mashup.takoyaki.entity.Report;
import org.mashup.takoyaki.repository.MapRepository;
import org.mashup.takoyaki.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.rmi.runtime.Log;


import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("mapService")
public class MapServiceImpl implements MapService {

    private MapRepository mapRepository;
    private double latitude;
    private double longitude;

    @Autowired
    MapService mapService;

    //**왜 이렇게 값을 받을까??
    @Autowired
    public MapServiceImpl(MapRepository mapRepository) {
        this.mapRepository= mapRepository;
    }



    @Override
    public List<Report> getMapTruck(double userLatitude, double userLongitude){
       //report = mapRepository.find

        log.info("from Client lattitude");
        log.info(Double.toString(userLatitude));

        Page<Report> getPagingTrucks = mapRepository.findAll(PageRequest.of(0,10,Sort.DEFAULT_DIRECTION, "id"));
        log.info("data : {}", getPagingTrucks.getContent().get(0));
        log.info("data count : {}", getPagingTrucks.getTotalElements());

        List<Report> truckLists = new ArrayList<Report>();
        truckLists = getPagingTrucks.getContent();

        List<Report> resultTruckLists = new ArrayList<Report>();


        for(int i=0; i<truckLists.size(); i++){
            this.latitude = truckLists.get(i).getLatitude();
            this.longitude = truckLists.get(i).getLongitude();

            double distanceResult = distance(userLatitude, userLongitude, latitude, longitude);

            if(distanceResult <= 2.5){
                resultTruckLists.add(truckLists.get(i));
            }
        }

        return resultTruckLists;

    }

    public static double distance(double lat1, double lon1, double lat2, double lon2){
        double p = 0.017453292519943295;    // Math.PI / 180
        double a = 0.5 - Math.cos((lat2 - lat1) * p)/2 + Math.cos(lat1 * p) * Math.cos(lat2 * p) * (1 - Math.cos((lon2 - lon1) * p)) / 2;

        return 12742 * Math.asin(Math.sqrt(a));

    }
}
