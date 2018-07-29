package org.mashup.takoyaki.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_USERS",
        uniqueConstraints = @UniqueConstraint(columnNames = "access_token"))
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "access_token", nullable = false)
    private String accessToken;

    @Column(name = "email")
    private String eamil;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @OneToMany(mappedBy = "userId")
    private List<Report> reportList;

    @PrePersist
    public void initData() {
        this.createdAt = LocalDateTime.now();
    }

}
