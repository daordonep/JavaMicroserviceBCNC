package com.dharian.application.service;

import com.dharian.application.port.in.PriceService;
import com.dharian.domain.Price;
import com.dharian.infraestructure.mapper.PriceEntityMapper;
import com.dharian.infraestructure.repository.entity.PriceEntity;
import com.dharian.infraestructure.repository.h2.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final PriceEntityMapper priceEntityMapper;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository, PriceEntityMapper priceEntityMapper) {
        this.priceRepository = priceRepository;
        this.priceEntityMapper = priceEntityMapper;
    }

    @Override
    public Price getPrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        try {

            PriceEntity priceEntity = priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualApplicationDateAndEndDateGreaterThanEqualApplicationDate(brandId, productId, Timestamp.valueOf(applicationDate));
            return priceEntityMapper.priceEntityToPrice(priceEntity);

        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Price not found", e);

        }
    }
}