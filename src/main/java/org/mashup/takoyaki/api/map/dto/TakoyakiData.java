package org.mashup.takoyaki.api.map.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TakoyakiData {

    private String truckName;
    private double latitude;
    private double longitude;
    private String region;
    private String description;
    private String type;

}
