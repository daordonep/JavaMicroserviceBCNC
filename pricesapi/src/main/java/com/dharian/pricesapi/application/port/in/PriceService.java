package com.dharian.pricesapi.application.port.in;


import com.dharian.pricesapi.domain.Price;

import java.time.LocalDateTime;


public interface PriceService{
    Price getPrice(LocalDateTime applicationDate, Integer productId, Integer brandId);
}