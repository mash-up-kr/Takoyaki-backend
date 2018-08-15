package org.mashup.takoyaki.service;

import org.mashup.takoyaki.entity.Report;
import org.mashup.takoyaki.repository.MapRepository;
import org.mashup.takoyaki.repository.UserRepository;
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


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class MapServiceImpl implements MapService {

    private static final Logger logger = Logger.getLogger(MapServiceImpl.class.getName());

    private MapRepository mapRepository;
    private double latitude;
    private double longitude;

    //**왜 이렇게 값을 받을까??
    @Autowired
    public MapServiceImpl(MapRepository mapRepository) { this.mapRepository= mapRepository; }

//    public Page<Report> findAll(Pageable pageable){
//        return mapRepository.findAll(pageable);
//    }

    @Override
    public List getMapTruck(double userLatitude, double userLongitude){

        List<Report> truckList;
        List<Report> mapTruckList = new ArrayList<>();

        Page<Report> reports = mapRepository.findAll(PageRequest.of(1,20));
        truckList = reports.getContent();

        System.out.println("로그");
        System.out.println(truckList.get(0).getLatitude());
        logger.info("로그");



        for(int i=0; i<truckList.size();i++) {

            this.latitude = truckList.get(i).getLatitude();
            this.longitude = truckList.get(i).getLongitude();

            double distanceResult = distance(latitude, longitude, this.latitude, this.longitude);

            if(distanceResult <= 0.25){
                mapTruckList.add(truckList.get(i));
            }
        }
        return truckList;

    }

    public static double distance(double lat1, double lon1, double lat2, double lon2){
        double p = 0.017453292519943295;    // Math.PI / 180
        double a = 0.5 - Math.cos((lat2 - lat1) * p)/2 + Math.cos(lat1 * p) * Math.cos(lat2 * p) * (1 - Math.cos((lon2 - lon1) * p)) / 2;

        return 12742 * Math.asin(Math.sqrt(a));

    }



}
