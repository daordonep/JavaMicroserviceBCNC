package com.dharian.infraestructure.controller;

import com.dharian.application.mapper.RestPriceMapper;
import com.dharian.application.service.PriceServiceImpl;
import com.dharian.infraestructure.dto.PriceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestController
public class PriceController {


    private final PriceServiceImpl priceService;
    private final RestPriceMapper restPriceMapper;

    @Autowired
    public PriceController(PriceServiceImpl priceService, RestPriceMapper restPriceMapper) {
        this.priceService = priceService;
        this.restPriceMapper = restPriceMapper;
    }

    @GetMapping(value = "/prices")
    ResponseEntity<PriceDTO> getPrices(

            @RequestParam(value = "applicationDate", required = true) LocalDateTime applicationDate,
            @RequestParam(value = "productId", required = true) Integer productId,
            @RequestParam(value = "brandId", required = true) Integer brandId

    ) {
        try {

            PriceDTO priceDTO = restPriceMapper.priceTopriceDTO(priceService.getPrice(applicationDate, productId, brandId));
            return ResponseEntity.ok(priceDTO);

        } catch (ResponseStatusException e) {

            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {

                return ResponseEntity.notFound().build();

            } else {

                return ResponseEntity.status(e.getStatusCode()).build();

            }
        }
    }
}