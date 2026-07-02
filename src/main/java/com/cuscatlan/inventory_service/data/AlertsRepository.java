package com.cuscatlan.inventory_service.data;

import com.cuscatlan.inventory_service.models.Movement;
import com.cuscatlan.inventory_service.models.StockAlert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AlertsRepository {

    private final AlertsJPARepository alertsJPARepository;

    public List<StockAlert> findAll() {
        return alertsJPARepository.findAll();
    }

    public StockAlert findById(int id) {
        return alertsJPARepository.findById(id).get();
    }

    public StockAlert save(StockAlert stockAlert) {
        return alertsJPARepository.save(stockAlert);
    }
}