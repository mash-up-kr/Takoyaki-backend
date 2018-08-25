package org.mashup.takoyaki.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.mashup.takoyaki.common.type.RegionType;

import java.time.LocalDate;

@Data
public class ReportDto {

    @JsonProperty(value = "truck_name")
    private String truckName;

    private String description;

    @JsonProperty(value = "expiration_date")
    private LocalDate expirationDate;

    @JsonProperty(value = "region_name")
    private RegionType regionName;

    private double latitude;

    private double longitude;

}