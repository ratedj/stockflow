package com.cuscatlan.inventory_service.controllers;

import com.cuscatlan.inventory_service.dto.AlertResponse;
import com.cuscatlan.inventory_service.services.AlertServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Tag(name="alertas controller", description = "Alertas")
@RequestMapping("/api/v1/alerts")
public class AlertsController {

    private final AlertServiceImp  alertServiceImp;

    @GetMapping
    @Operation(
            operationId = "Alertas",
            summary = "Obtiene los productos con stock bajo",
            description = "Retorna todos los productos cuyo stock actual es menor o igual a su stock mínimo definido, " +
                    "incluyendo un nivel de severidad calculado (LOW o CRITICAL).")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de productos en estado de alerta (puede estar vacía si ninguno está bajo el mínimo)",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AlertResponse.class))))
    })
    public ResponseEntity<List<AlertResponse>> getLowStockAlerts() {
        List<AlertResponse> alerts = alertServiceImp.getLowStockAlerts();
        return ResponseEntity.ok(alerts);
    }
}
