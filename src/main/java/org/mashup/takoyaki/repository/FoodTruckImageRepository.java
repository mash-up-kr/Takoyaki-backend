package org.mashup.takoyaki.repository;


import org.mashup.takoyaki.entity.FoodTruckImage;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author 이채은
 * @since 2018-08-18
 */
public interface FoodTruckImageRepository extends JpaRepository<FoodTruckImage, Long> {
}
