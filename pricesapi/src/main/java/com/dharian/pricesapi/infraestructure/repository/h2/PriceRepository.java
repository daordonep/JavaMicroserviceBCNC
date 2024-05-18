package com.dharian.pricesapi.infraestructure.repository.h2;


import com.dharian.pricesapi.infraestructure.repository.entity.PriceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PriceRepository {

    private final EntityManager entityManager;


    public PriceEntity getSinglePrice(int brandId, int productId, Timestamp applicationDate) {
        TypedQuery<PriceEntity> query = entityManager.createQuery(
                        "SELECT p FROM PriceEntity p " +
                                "WHERE p.brandId = :brandId " +
                                "AND p.productId = :productId " +
                                "AND p.startDate <= :applicationDate " +
                                "AND p.endDate >= :applicationDate" +
                                " ORDER BY p.priority DESC", PriceEntity.class)
                .setParameter("brandId", brandId)
                .setParameter("productId", productId)
                .setParameter("applicationDate", applicationDate)
                .setMaxResults(1);
        List<PriceEntity> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

   
}