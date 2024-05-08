package com.dharian.pricesapi.application.service;


import com.dharian.pricesapi.application.port.in.PriceService;
import com.dharian.pricesapi.domain.Price;
import com.dharian.pricesapi.infraestructure.mapper.PriceEntityMapper;
import com.dharian.pricesapi.infraestructure.repository.entity.PriceEntity;
import com.dharian.pricesapi.infraestructure.repository.h2.PriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Service
@Slf4j
public class PriceServiceImpl implements PriceService {
    private final PriceRepository priceRepository;
    private final PriceEntityMapper priceEntityMapper;



    @Override
    public Price getPrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        try {
            List<PriceEntity> listPriceEntity = priceRepository.findByBrandIdAndProductIdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(brandId, productId, Timestamp.valueOf(applicationDate));
            PriceEntity priceEntity = listPriceEntity
                    .stream()
                    .filter(priceEntity1 -> priceEntity1.getPriority() == 1)
                    .findFirst()
                    .orElse(listPriceEntity
                            .stream()
                            .filter(priceEntity2 -> priceEntity2.getPriority() == 0)
                            .findFirst().get());


            return priceEntityMapper.priceEntityToPrice(priceEntity);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Price not found", e);

        }
    }
}