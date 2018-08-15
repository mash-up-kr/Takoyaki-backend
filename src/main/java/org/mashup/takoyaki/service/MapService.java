package org.mashup.takoyaki.service;

import org.mashup.takoyaki.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MapService {
    List getMapTruck(double latitude, double longitude);
}
