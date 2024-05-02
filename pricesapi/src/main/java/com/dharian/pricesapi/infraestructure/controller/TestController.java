package com.dharian.pricesapi.infraestructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    ResponseEntity getPrices2(  ){return ResponseEntity.ok().build();}
}
