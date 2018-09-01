package org.mashup.takoyaki.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.mashup.takoyaki.entity.value.AccessToken;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@ToString(exclude = "reportList")
@EqualsAndHashCode
@Entity
@Table(name = "TB_USERS",
        uniqueConstraints = @UniqueConstraint(columnNames = "access_token"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private AccessToken accessToken;

    @Column(name = "email")
    private String eamil;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @JsonIgnore
    @OneToMany(mappedBy = "userId")
    private List<Report> reportList;

    @PrePersist
    public void initData() {
        //TODO nickname 발급 로직이 필요합니다.
        this.nickname = "TAKOYAKI";
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void initUpdateDate() {
        this.updatedAt = LocalDateTime.now();
    }

}
