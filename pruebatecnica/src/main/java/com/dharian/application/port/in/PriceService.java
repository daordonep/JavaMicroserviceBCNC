package com.dharian.application.port.in;

import com.dharian.domain.Price;

import java.time.LocalDateTime;


public interface PriceService{
    Price getPrice(LocalDateTime applicationDate, Integer productId, Integer brandId);
}