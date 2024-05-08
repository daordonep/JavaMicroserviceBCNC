package com.dharian.pricesapi;

import org.junit.jupiter.api.DisplayName;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class PricesapiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
	public void testGetPrices1() throws Exception {
		LocalDateTime applicationDate = LocalDateTime.of(2020,06,14,10,00);
		int productId = 35455;
		int brandId = 1;
		getCallAndResult(applicationDate, productId, brandId);
	}

	@Test
	@DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
	public void testGetPrices2() throws Exception {
		LocalDateTime applicationDate = LocalDateTime.of(2020,06,14,16,00);
		int productId = 35455;
		int brandId = 1;
		getCallAndResult(applicationDate, productId, brandId);
	}
	@Test
	@DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
	public void testGetPrices3() throws Exception {
		LocalDateTime applicationDate = LocalDateTime.of(2020,06,14,21,00);
		int productId = 35455;
		int brandId = 1;
		getCallAndResult(applicationDate, productId, brandId);
	}
	@Test
	@DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)")
	public void testGetPrices4() throws Exception {
		LocalDateTime applicationDate = LocalDateTime.of(2020,06,15,10,00);
		int productId = 35455;
		int brandId = 1;
		getCallAndResult(applicationDate, productId, brandId);
	}
	@Test
	@DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)")
	public void testGetPrices5() throws Exception {
		LocalDateTime applicationDate = LocalDateTime.of(2020,06,16,21,00);
		int productId = 35455;
		int brandId = 1;
		getCallAndResult(applicationDate, productId, brandId);
	}

	private void getCallAndResult(LocalDateTime applicationDate, int productId, int brandId) throws Exception {
		String response = mockMvc.perform(MockMvcRequestBuilders.get("/prices-api/prices")
						.param("applicationDate", applicationDate.toString())
						.param("productId", String.valueOf(productId))
						.param("brandId", String.valueOf(brandId))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse().getContentAsString();
		System.out.println(response);
	}
}
