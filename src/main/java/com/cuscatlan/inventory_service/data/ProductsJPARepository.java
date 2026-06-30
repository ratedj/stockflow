package com.cuscatlan.inventory_service.data;

import com.cuscatlan.inventory_service.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductsJPARepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
}
