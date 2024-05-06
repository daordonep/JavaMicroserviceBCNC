package com.dharian.pricesapi.infraestructure.repository.h2;


import com.dharian.pricesapi.infraestructure.repository.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Integer> {

    @Query("select p from PriceEntity p " +
            "where p.brandId = ?1 and p.productId = ?2 and p.startDate >= ?3 and p.endDate <= ?3")
    PriceEntity findByBrandIdAndProductIdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(int brandId, int productId, Timestamp applicationDate);
}