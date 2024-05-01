package com.dharian.application.mapper;

import com.dharian.domain.Price;
import com.dharian.infraestructure.dto.PriceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RestPriceMapper {
    @Mapping(source="brandId", target="brandId")
    @Mapping(source="productId", target="productId")
    @Mapping(source="priceList", target="priceList")
    //@Mapping(source="applicationDate", target="applicationDate")
    @Mapping(source="price", target="price")
    PriceDTO priceTopriceDTO(Price price);
}
