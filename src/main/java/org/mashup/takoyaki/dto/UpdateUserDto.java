package org.mashup.takoyaki.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author 한태웅
 * @since 2018-08-11
 */
//TODO Validation에 관련된 애너테이션 문서를 찾아본 후 적용해야 합니다.
@Data
public class UpdateUserDto {

    @JsonIgnore
    private String token;
    private String email;
    private String nickname;

}
