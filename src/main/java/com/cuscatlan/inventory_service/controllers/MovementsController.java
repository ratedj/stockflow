package com.cuscatlan.inventory_service.controllers;

import com.cuscatlan.inventory_service.dto.MovementRequest;
import com.cuscatlan.inventory_service.dto.MovementResponse;
import com.cuscatlan.inventory_service.models.Movement;
import com.cuscatlan.inventory_service.services.MovementServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name="productos controller", description = "Catalogo de productos")
@RequestMapping("/api/v1/movements")
public class MovementsController {

    private final MovementServiceImp  movementServiceImp;

    @PostMapping
    @Operation(
            operationId = "registerMovement",
            summary = "Registra un movimiento de inventario",
            description = "Registra una entrada (IN) o salida (OUT) de stock para un producto, actualizando automáticamente el stock actual del producto afectado.")

            @ApiResponse(
                    responseCode = "201",
                    description = "Movimiento registrado y stock actualizado correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Movement.class)))

            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos o stock insuficiente para un movimiento de salida (OUT)",
                    content = @Content)

            @ApiResponse(
                    responseCode = "404",
                    description = "El producto indicado no existe",
                    content = @Content)

    public ResponseEntity<MovementResponse> create(@Valid @RequestBody MovementRequest request) {
        MovementResponse created = movementServiceImp.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{productId}/history")
    @Operation(
            operationId = "getMovementsHistoryByProduct",
            summary = "Obtiene el historial de movimientos de un producto",
            description = "Retorna todos los movimientos (entradas y salidas) registrados para un producto específico, " +
                    "ordenados del más reciente al más antiguo.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Movement.class)))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<List<MovementResponse>> getHistoryByProduct(@PathVariable int productId) {
        List<MovementResponse> history = movementServiceImp.getHistoryByProduct(productId);
        return ResponseEntity.ok(history);
    }
}
