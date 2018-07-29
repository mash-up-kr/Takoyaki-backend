package org.mashup.takoyaki.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "TB_REVIEWS")
@Getter @Setter
@ToString
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    /**
     * `id` int(11) NOT NULL AUTO_INCREMENT,
     *   `user_id` int(11) NOT NULL,
     *   `report_id` int(11) NOT NULL,
     *   PRIMARY KEY (`id`)
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
    private Report reportId;

    @Column(name = "review", nullable = false)
    private String review;

    @Column(name = "star")
    private Long star;

}
