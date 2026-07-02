package com.cuscatlan.inventory_service.dto;

public record AlertResponse(
        int productId,
        String productName,
        Integer currentStock,
        Integer minStock,
        String severity
) {
}