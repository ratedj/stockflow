package com.cuscatlan.inventory_service.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(int productId) {
        super("Producto no encontrado con id: " + productId);
    }
}