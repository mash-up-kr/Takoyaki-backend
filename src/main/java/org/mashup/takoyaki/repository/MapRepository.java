package org.mashup.takoyaki.repository;

import org.mashup.takoyaki.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MapRepository extends JpaRepository<Report, Long> {
    @Query(value = "SELECT *, (6371*acos(cos(radians(userLatitude))*cos(radians(latitude))*cos(radians(longitude)-radians(userLatitude))+sin(radians(userLongitude))*sin(radians(latitude)))) AS distance FROM Report HAVING distance <= 2.5", nativeQuery = true)
    List<Report> findByNearTruck(long userLatitude, long userLongitude);
}



