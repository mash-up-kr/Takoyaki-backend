package org.mashup.takoyaki.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mashup.takoyaki.common.type.RegionType;

import javax.persistence.*;

@Entity
@Table(name = "TB_REGIONS",
        uniqueConstraints = @UniqueConstraint(columnNames = "region_name"))
@Getter @Setter
@ToString
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "region_name", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private RegionType regionType;

}
