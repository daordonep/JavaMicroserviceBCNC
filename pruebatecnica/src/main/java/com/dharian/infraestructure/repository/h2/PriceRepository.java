package com.dharian.infraestructure.repository.h2;

import com.dharian.infraestructure.repository.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;


public interface PriceRepository extends JpaRepository<PriceEntity, Integer> {
    PriceEntity findByBrandIdAndProductIdAndStartDateLessThanEqualApplicationDateAndEndDateGreaterThanEqualApplicationDate(Integer brandId, Integer productId, Timestamp applicationDate);
}