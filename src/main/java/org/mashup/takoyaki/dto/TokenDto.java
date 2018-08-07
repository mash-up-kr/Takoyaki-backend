package org.mashup.takoyaki.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

//TODO createdAt LocalDateTime으로 json serialize가 필요합니다.
@Data
public class TokenDto {

    private String token;
    @JsonProperty(value = "created_at")
    private String createdAt;

}
