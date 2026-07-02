package com.cuscatlan.inventory_service.services;

import com.cuscatlan.inventory_service.data.ProductsRepository;
import com.cuscatlan.inventory_service.dto.AlertResponse;
import com.cuscatlan.inventory_service.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertServiceImp {

    private static final double CRITICAL_RATIO = 0.5;

    private final ProductsRepository productsRepository;

    public List<AlertResponse> getLowStockAlerts() {
        List<Product> lowStockProducts = productsRepository.findLowStockProducts();

        return lowStockProducts.stream()
                .map(this::toAlertResponse)
                .toList();
    }

    private AlertResponse toAlertResponse(Product product) {
        String severity = calculateSeverity(product);
        return new AlertResponse(
                product.getId(),
                product.getName(),
                product.getCurrentStock(),
                product.getMinStock(),
                severity
        );
    }

    private String calculateSeverity(Product product) {
        // CRITICAL si el stock actual es igual o menor a la mitad del mínimo permitido.
        // LOW si está por debajo del mínimo pero aún no en un nivel crítico.
        if (product.getCurrentStock() <= product.getMinStock() * CRITICAL_RATIO) {
            return "CRITICAL";
        }
        return "LOW";
    }
}