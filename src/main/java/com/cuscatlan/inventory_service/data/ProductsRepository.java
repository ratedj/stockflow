package com.cuscatlan.inventory_service.data;

import com.cuscatlan.inventory_service.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductsRepository {

    private final ProductsJPARepository productsJPARepository;

    public List<Product> findAll() {
        return productsJPARepository.findAll();
    }

    public Product findById(int id) {
        return productsJPARepository.findById(id).get();
    }
}