package org.mashup.takoyaki.repository;

import org.mashup.takoyaki.common.type.RegionType;
import org.mashup.takoyaki.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByRegionType(RegionType regionType);
}
