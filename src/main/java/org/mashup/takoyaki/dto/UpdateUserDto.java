package org.mashup.takoyaki.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author 한태웅
 * @since 2018-08-11
 */
@Data
public class UpdateUserDto {

    @JsonIgnore
    private String token;
    private String email;
    private String nickname;

}
