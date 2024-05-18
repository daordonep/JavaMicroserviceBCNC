package com.dharian.pricesapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerExceptionHandlerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testControllerExceptionHandler() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 06, 14, 10, 00);
        mockMvc.perform(MockMvcRequestBuilders.get("/prices-api/prices")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", String.valueOf(1))
                        .param("brandId", String.valueOf(1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
