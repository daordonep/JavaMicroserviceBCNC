package com.dharian.pricesapi.infraestructure.controller;


import com.dharian.pricesapi.application.mapper.RestPriceMapper;
import com.dharian.pricesapi.application.service.PriceServiceImpl;
import com.dharian.pricesapi.infraestructure.dto.PriceDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices-api")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class PriceController {


    private final PriceServiceImpl priceService;
    private final RestPriceMapper restPriceMapper;


    @GetMapping(value = "/prices")
    ResponseEntity<PriceDTO> getPrices(

            @RequestParam(value = "applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime  applicationDate,
            @RequestParam(value = "productId") Integer productId,
            @RequestParam(value = "brandId") Integer brandId

    ) {
        try {
           return ResponseEntity.ok(restPriceMapper.priceTopriceDTO(priceService.getPrice(applicationDate, productId, brandId)));


        } catch (ResponseStatusException e) {
        log.error(e.getMessage());
            if (e.getStatus().equals(HttpStatus.NOT_FOUND)) {

                return ResponseEntity.notFound().build();

            } else {

                return ResponseEntity.status(e.getRawStatusCode()).build();

            }
        }
    }
}