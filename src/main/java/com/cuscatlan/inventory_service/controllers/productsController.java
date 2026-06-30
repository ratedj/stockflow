package com.cuscatlan.inventory_service.controllers;

import com.cuscatlan.inventory_service.dto.ProductResponse;
import com.cuscatlan.inventory_service.services.ProductServiceImp;
import io.swagger.v3.oas.annotations.Operation;
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

@RestController
@RequiredArgsConstructor
@Tag(name="productos controller", description = "Catalogo de productos")
@RequestMapping("/api/v1/products")
public class productsController {

    @Autowired
    private final ProductServiceImp productServiceImp;

    @GetMapping
    @Operation(
            operationId = "Obtener productos",
            description = "Permite obtener los productos",
            summary = "Se devuelve una lista de todos los productos almacenados en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class)))
public ResponseEntity<List<ProductResponse>> GetAll() {

    return ResponseEntity.ok(productServiceImp.GetAll());
    }

}
