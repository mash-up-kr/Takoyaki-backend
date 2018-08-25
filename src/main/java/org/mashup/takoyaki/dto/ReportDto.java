package org.mashup.takoyaki.dto;

<<<<<<< Updated upstream
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.mashup.takoyaki.common.type.RegionType;

import java.time.LocalDate;

@Data
public class ReportDto {

=======
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.mashup.takoyaki.common.type.RegionType;
import org.mashup.takoyaki.entity.Region;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReportDto {
>>>>>>> Stashed changes
    @JsonProperty(value = "truck_name")
    private String truckName;

    private String description;

<<<<<<< Updated upstream
    @JsonProperty(value = "expiration_date")
    private LocalDate expirationDate;

    @JsonProperty(value = "region_name")
    private RegionType regionName;

=======
>>>>>>> Stashed changes
    private double latitude;

    private double longitude;

<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
