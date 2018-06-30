package org.mashup.takoyaki.api.map.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TakoyakiPosition {

    private int latitude;
    private int longitude;
    private String region;

}
