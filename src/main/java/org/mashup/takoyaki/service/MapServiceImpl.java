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

        log.info("from Client lat");
        log.info(Double.toString(userLatitude));

        Page<Report> aa = mapRepository.findAll();
       // log.info("findall truck info ={}", aa.get(0).getLatitude().toString());

        //PageRequest pageRequest = new PageRequest(1, 20 );

//       PagingAndSortingRepository<Report, Long> repository = mapRepository;
//        Page<Report> reports = repository.findAll(new PageRequest(0,1));
//        List<Report> mapTruckList = reports.getContent();


      //  PageRequest pageRequest = new PageRequest(0,1, new Sort(Sort.Direction.DESC,"id"));

//        Page<Report> result = mapRepository.findById(Integer.toUnsignedLong(1), pageRequest);

        //List<Report> reports = result.getContent();
        //List<Report>  mapTruckList = ((MapRepository) repository).findAll();


        //List<Report> reportList = mapRepository.findAll(new PageRequest(0, 4));

        log.info("result size");
//        log.info(Integer.toString(result.getSize()));
//        log.info(Long.toString(result.getTotalElements()));


        log.info("reports size");
//       log.info(Integer.toString(reports.size()));
        log.info("mapTruckList size");
        //log.info(Integer.toString(mapTruckList.size()));


        log.info("reports length");
//log.info(Integer.toString(reports.stream().toArray().length));
        log.info("get map data ");
//        log.info(reports.get(0).getLatitude().toString());


        //Page<Report> reports = mapRepository.findAll(PageRequest.of(1,20));
       // List<Report> truckLists = new ArrayList<Report>();



        //log.info(mapTruckList.get(0).getLatitude().toString());


        //System.out.println(truckList.get(0).getLatitude());
       // log.info(truckList.get(0).getLatitude().toString());


//        for(int i=0; i<truckList.size();i++) {
//
//            this.latitude = truckList.get(i).getLatitude();
//            this.longitude = truckList.get(i).getLongitude();
//
//            double distanceResult = distance(latitude, longitude, this.latitude, this.longitude);
//
//            if(distanceResult <= 0.25){
//                mapTruckList.add(truckList.get(i));
//            }
//        }
//        return truckList;
        return reports;

    }

    public static double distance(double lat1, double lon1, double lat2, double lon2){
        double p = 0.017453292519943295;    // Math.PI / 180
        double a = 0.5 - Math.cos((lat2 - lat1) * p)/2 + Math.cos(lat1 * p) * Math.cos(lat2 * p) * (1 - Math.cos((lon2 - lon1) * p)) / 2;

        return 12742 * Math.asin(Math.sqrt(a));

    }
}
