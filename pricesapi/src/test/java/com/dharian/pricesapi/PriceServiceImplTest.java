package com.dharian.pricesapi;

import com.dharian.pricesapi.application.service.PriceServiceImpl;
import com.dharian.pricesapi.domain.Price;
import com.dharian.pricesapi.infraestructure.mapper.PriceEntityMapper;
import com.dharian.pricesapi.infraestructure.repository.entity.PriceEntity;
import com.dharian.pricesapi.infraestructure.repository.h2.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class PriceServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceEntityMapper priceEntityMapper;

    @InjectMocks
    private PriceServiceImpl priceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPrice_Success() {
        LocalDateTime applicationDate = LocalDateTime.now();
        Integer productId = 35455;
        Integer brandId = 1;
        Timestamp applicationTimestamp = Timestamp.valueOf(applicationDate);

        PriceEntity mockPriceEntity = new PriceEntity();
        Price mockPrice = new Price();
        when(priceRepository.getSinglePrice(brandId, productId, applicationTimestamp)).thenReturn(mockPriceEntity);
        when(priceEntityMapper.priceEntityToPrice(mockPriceEntity)).thenReturn(mockPrice);

        Price result = priceService.getPrice(applicationDate, productId, brandId);

        assertNotNull(result);
        assertEquals(mockPrice, result);
        verify(priceRepository).getSinglePrice(brandId, productId, applicationTimestamp);
        verify(priceEntityMapper).priceEntityToPrice(mockPriceEntity);
    }

    @Test
    public void testGetPrice_NotFound() {
        LocalDateTime applicationDate = LocalDateTime.now();
        Integer productId = 1;
        Integer brandId = 1;
        Timestamp applicationTimestamp = Timestamp.valueOf(applicationDate);

        when(priceRepository.getSinglePrice(brandId, productId, applicationTimestamp)).thenReturn(null);

        ResponseStatusException exception = (ResponseStatusException) assertThrows(RuntimeException.class, () -> {
            priceService.getPrice(applicationDate, productId, brandId);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Price not found", exception.getReason());
        verify(priceRepository).getSinglePrice(brandId, productId, applicationTimestamp);
    }
}
