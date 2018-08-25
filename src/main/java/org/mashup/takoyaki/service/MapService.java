package org.mashup.takoyaki.service;

import org.mashup.takoyaki.dto.ReportDto;
import org.mashup.takoyaki.entity.Report;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MapService {
    List getMapTruck(double userLatitude, double userLongitude);
}
