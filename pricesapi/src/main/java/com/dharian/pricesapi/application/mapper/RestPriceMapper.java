package com.dharian.pricesapi.application.mapper;


import com.dharian.pricesapi.domain.Price;
import com.dharian.pricesapi.infraestructure.dto.PriceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface RestPriceMapper {
    //TODO
    //@Mapping(source="applicationDate", target="applicationDate")
    @Mapping(source="brandId", target="brandId")
    @Mapping(source="productId", target="productId")
    @Mapping(source="priceList", target="priceList")
    @Mapping(source="price", target="price")
    PriceDTO priceTopriceDTO(Price price);
}
