package com.cuscatlan.inventory_service.dto;

import com.cuscatlan.inventory_service.models.Movement;

import java.time.LocalDateTime;

public record MovementResponse(
        int id,
        int productId,
        Movement.MovementType type,
        Integer quantity,
        String reason,
        LocalDateTime timestamp
) {
}