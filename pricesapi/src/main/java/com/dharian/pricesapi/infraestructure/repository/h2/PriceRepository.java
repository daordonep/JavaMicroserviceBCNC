package com.dharian.pricesapi.infraestructure.repository.h2;


import com.dharian.pricesapi.infraestructure.repository.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Integer> {

    @Query("select p from PriceEntity p " +
            "where p.brandId = :brandId and p.productId = :productId and p.startDate <= :applicationDate and p.endDate >= :applicationDate")
    List<PriceEntity> findByBrandIdAndProductIdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(int brandId, int productId, Timestamp applicationDate);
}