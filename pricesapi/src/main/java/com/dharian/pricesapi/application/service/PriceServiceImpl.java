package com.dharian.pricesapi.application.service;


import com.dharian.pricesapi.application.port.in.PriceService;
import com.dharian.pricesapi.domain.Price;
import com.dharian.pricesapi.infraestructure.mapper.PriceEntityMapper;
import com.dharian.pricesapi.infraestructure.repository.h2.PriceRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;


@Service
public class PriceServiceImpl implements PriceService {
    private PriceRepository priceRepository;
    private PriceEntityMapper priceEntityMapper;
    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository, PriceEntityMapper priceEntityMapper) {
        this.priceRepository = priceRepository;
        this.priceEntityMapper = priceEntityMapper;
    }

    @Override
    public Price getPrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        try {

            //PriceEntity priceEntity = priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualApplicationDateAndEndDateGreaterThanEqualApplicationDate(brandId, productId, Timestamp.valueOf(applicationDate));
            //return priceEntityMapper.priceEntityToPrice(new PriceEntity());
            return new Price();
        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Price not found", e);

        }
    }
}