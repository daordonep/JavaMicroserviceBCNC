package com.dharian.pricesapi.infraestructure.repository.h2;


import com.dharian.pricesapi.infraestructure.repository.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Integer> {
   // PriceEntity findByBrandIdAndProductIdAndStartDateLessThanEqualApplicationDateAndEndDateGreaterThanEqualApplicationDate(Integer brandId, Integer productId, Timestamp applicationDate);
}