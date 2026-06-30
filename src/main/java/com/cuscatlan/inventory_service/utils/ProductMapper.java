package com.cuscatlan.inventory_service.utils;

import com.cuscatlan.inventory_service.dto.ProductResponse;
import com.cuscatlan.inventory_service.models.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse toDto(Product product);
}
