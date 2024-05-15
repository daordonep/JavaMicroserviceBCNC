package com.dharian.pricesapi.application.service;


import com.dharian.pricesapi.application.port.in.PriceService;
import com.dharian.pricesapi.domain.Price;
import com.dharian.pricesapi.infraestructure.mapper.PriceEntityMapper;
import com.dharian.pricesapi.infraestructure.repository.h2.PriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Service
@Slf4j
public class PriceServiceImpl implements PriceService {
    private final PriceRepository priceRepository;
    private final PriceEntityMapper priceEntityMapper;


    @Override
    public Price getPrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        try {
            return priceEntityMapper.priceEntityToPrice(priceRepository.getSinglePrice(brandId, productId, Timestamp.valueOf(applicationDate)));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Price not found", e);
        }
    }
}