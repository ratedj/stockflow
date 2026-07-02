package com.cuscatlan.inventory_service.data;

import com.cuscatlan.inventory_service.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface ProductsJPARepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

    @Query("SELECT p FROM Product p WHERE p.currentStock <= p.minStock")
    List<Product> findProductsWithLowStock();
}
