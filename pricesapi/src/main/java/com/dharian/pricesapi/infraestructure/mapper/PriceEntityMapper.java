package com.dharian.pricesapi.infraestructure.mapper;


import com.dharian.pricesapi.domain.Price;
import com.dharian.pricesapi.infraestructure.repository.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

    @Mapping(source="brandId", target="brandId")
    @Mapping(source="startDate", target="startDate", qualifiedByName = "timestampToLocalDateTime")
    @Mapping(source="endDate", target="endDate", qualifiedByName = "timestampToLocalDateTime")
    @Mapping(source="priceList", target="priceList")
    @Mapping(source="productId", target="productId")
    @Mapping(source="priority", target="priority")
    @Mapping(source="price", target="price")
    @Mapping(source="curr", target="currency")
    Price priceEntityToPrice(PriceEntity priceEntity);

    @Named("timestampToLocalDateTime")
    default LocalDateTime timestampToLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }
}
