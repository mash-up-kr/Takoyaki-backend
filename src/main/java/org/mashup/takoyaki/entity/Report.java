package org.mashup.takoyaki.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_REPORTS")
@Getter @Setter
@ToString
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region regionId;

    @Column(name = "truck_name", length = 45)
    private String truckName;

    @Column(name = "reported_at", nullable = false)
    private LocalDateTime reportedAt;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "description")
    private String description;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @PrePersist
    public void initData() {
        this.reportedAt = LocalDateTime.now();
    }

}
