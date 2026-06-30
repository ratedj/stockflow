package com.cuscatlan.inventory_service.services;

import com.cuscatlan.inventory_service.dto.ProductResponse;

import java.util.List;

public interface IProductService {

    public List<ProductResponse> GetAll();

}
