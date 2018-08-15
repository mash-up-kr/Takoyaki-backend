package org.mashup.takoyaki.controller;

import org.mashup.takoyaki.common.model.ApiResponseModel;
import org.mashup.takoyaki.entity.value.AccessToken;
import org.mashup.takoyaki.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping(value = "/v1/map/trucks")
public class MapController {

    @Autowired
    MapService mapService;

    @GetMapping(value = "/v1/map/trucks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ApiResponseModel<List> getMapTruck(@RequestParam("latitude")double latitude, @RequestParam("longitude")double longitude) {

        System.out.println("ㅎㅎ");

        System.out.println("ㅋㅋ");
        ApiResponseModel<List> response = new ApiResponseModel<>();
        response.setCode(HttpStatus.OK.value());
        response.setMsg(HttpStatus.OK.toString());
        response.setResult(mapService.getMapTruck(latitude,longitude));
        return response;
    }




}
