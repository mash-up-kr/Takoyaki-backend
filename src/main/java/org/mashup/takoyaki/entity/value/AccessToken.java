package org.mashup.takoyaki.entity.value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

/**
 * @author 한태웅
 * @since 2018-08-11
 */
//TODO createdAt LocalDateTime으로 json serialize가 필요합니다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AccessToken {

    @Column(name = "access_token")
    private String token;

    @JsonProperty(value = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    @Column(name = "token_created_at")
    private LocalDateTime createdAt;

}
