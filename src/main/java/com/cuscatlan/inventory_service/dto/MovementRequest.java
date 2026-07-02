package com.cuscatlan.inventory_service.dto;

import com.cuscatlan.inventory_service.models.Movement;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MovementRequest(

        @NotNull(message = "El id del producto es obligatorio")
        int productId,

        @NotNull(message = "El tipo de movimiento es obligatorio (IN o OUT)")
        Movement.MovementType type,

        @NotNull(message = "La cantidad es obligatoria")
        @Min(value = 1, message = "La cantidad debe ser mayor o igual a 1")
        @Max(value = 100000, message = "La cantidad no puede exceder 100000 unidades")
        Integer quantity,

        @NotBlank(message = "La razón del movimiento es obligatoria")
        @Size(max = 255, message = "La razón no puede exceder 255 caracteres")
        String reason
) {
}