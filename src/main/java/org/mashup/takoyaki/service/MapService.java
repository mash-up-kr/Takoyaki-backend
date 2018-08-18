package org.mashup.takoyaki.service;

import java.util.List;

public interface MapService {
    List getMapTruck(long latitude, long longitude);
}
