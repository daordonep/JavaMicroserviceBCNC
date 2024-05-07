package com.dharian.pricesapi.application.mapper;


import com.dharian.pricesapi.domain.Price;
import com.dharian.pricesapi.infraestructure.dto.PriceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface RestPriceMapper {

    @Mapping(source="startDate", target="startDate")
    @Mapping(source="endDate", target="endDate")
    @Mapping(source="brandId", target="brandId", qualifiedByName = "getBrandName")
    @Mapping(source="productId", target="productId")
    @Mapping(source="priceList", target="priceList")
    @Mapping(source="price", target="price")
    PriceDTO priceTopriceDTO(Price price);

    @Named("getBrandName")
    default String getBrandName(Integer brandId) {return (brandId == 1) ? "ZARA" : "";}
}
