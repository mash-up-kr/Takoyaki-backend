package org.mashup.takoyaki.dto;

import lombok.Data;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

/**
 * @author 한태웅
 * @since 2018-08-11
 */
//TODO Validation에 관련된 애너테이션 문서를 찾아본 후 적용해야 합니다.
@Data
@Validated
public class UpdateUserDto {

    @Nullable
    private String email;
    @Nullable
    private String nickname;

}
