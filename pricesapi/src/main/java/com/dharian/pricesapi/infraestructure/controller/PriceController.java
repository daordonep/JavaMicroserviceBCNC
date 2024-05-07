package com.dharian.pricesapi.infraestructure.controller;


import com.dharian.pricesapi.application.mapper.RestPriceMapper;
import com.dharian.pricesapi.application.service.PriceServiceImpl;
import com.dharian.pricesapi.infraestructure.dto.PriceDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class PriceController {


    private final PriceServiceImpl priceService;
    private final RestPriceMapper restPriceMapper;
    private static final Logger logger = LogManager.getLogger(PriceController.class);
    @Autowired
    public PriceController(PriceServiceImpl priceService, RestPriceMapper restPriceMapper) {
        this.priceService = priceService;
        this.restPriceMapper = restPriceMapper;
    }

    @GetMapping(value = "/prices")
    ResponseEntity<PriceDTO> getPrices(

            @RequestParam(value = "applicationDate", required = false ) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime  applicationDate,
            @RequestParam(value = "productId", required = false) Integer productId,
            @RequestParam(value = "brandId", required = false) Integer brandId

    ) {
        try {
            PriceDTO priceDTO = restPriceMapper.priceTopriceDTO(priceService.getPrice(applicationDate, productId, brandId));
            return ResponseEntity.ok(priceDTO);

        } catch (ResponseStatusException e) {
        logger.error(e.getMessage());
            if (e.getStatus().equals(HttpStatus.NOT_FOUND)) {

                return ResponseEntity.notFound().build();

            } else {

                return ResponseEntity.status(e.getRawStatusCode()).build();

            }
        }
    }
}