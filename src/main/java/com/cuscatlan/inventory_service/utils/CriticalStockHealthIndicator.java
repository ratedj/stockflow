package com.cuscatlan.inventory_service.utils;

import com.cuscatlan.inventory_service.data.AlertsRepository;
import com.cuscatlan.inventory_service.data.ProductsRepository;
import com.cuscatlan.inventory_service.models.StockAlert;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CriticalStockHealthIndicator implements HealthIndicator {

    private static final double CRITICAL_THRESHOLD_PERCENTAGE = 20.0;

    private final ProductsRepository productsRepository;
    private final AlertsRepository alertsRepository;

    @Override
    public Health health() {
        long totalProducts = productsRepository.findAll().size();

        if (totalProducts == 0) {
            return Health.up()
                    .withDetail("message", "No hay productos registrados")
                    .withDetail("criticalPercentage", 0.0)
                    .build();
        }

        List<StockAlert> alerts = alertsRepository.findAll();
        long criticalCount = alerts.stream()
                .filter(alert -> alert.getSeverity() == StockAlert.Severity.CRITICAL)
                .count();

        double criticalPercentage = (criticalCount * 100.0) / totalProducts;

        Health.Builder builder = criticalPercentage > CRITICAL_THRESHOLD_PERCENTAGE
                ? Health.down()
                : Health.up();

        return builder
                .withDetail("totalProducts", totalProducts)
                .withDetail("criticalProducts", criticalCount)
                .withDetail("criticalPercentage", String.format("%.2f%%", criticalPercentage))
                .withDetail("threshold", CRITICAL_THRESHOLD_PERCENTAGE + "%")
                .build();
    }
}