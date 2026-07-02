package com.cuscatlan.inventory_service.data;

import com.cuscatlan.inventory_service.models.Movement;
import com.cuscatlan.inventory_service.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MovementsJPARepository extends JpaRepository<Movement, Integer>, JpaSpecificationExecutor<Movement> {
    List<Movement> findByProductIdOrderByTimestampDesc(int productId);
}
