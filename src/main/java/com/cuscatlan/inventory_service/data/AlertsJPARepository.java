package com.cuscatlan.inventory_service.data;

import com.cuscatlan.inventory_service.models.Product;
import com.cuscatlan.inventory_service.models.StockAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AlertsJPARepository extends JpaRepository<StockAlert, Integer>, JpaSpecificationExecutor<StockAlert> {
}
