package org.mashup.takoyaki.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "TB_TRUCK_IMAGES")
@Getter @Setter
@ToString
public class FoodTruckImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
    private Report reportId;

    @Column(name = "truck_image_url")
    private String truckImageUrl;

}
