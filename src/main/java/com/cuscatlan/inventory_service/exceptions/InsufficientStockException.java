package com.cuscatlan.inventory_service.exceptions;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(int productId, int currentStock, int requested) {
        super("Stock insuficiente para el producto " + productId +
                ". Disponible: " + currentStock + ", solicitado: " + requested);
    }
}