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
import org.springframework.web.bind.annotation.*;

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
public ResponseEntity<List<ProductResponse>> getAll() {

    return ResponseEntity.ok(productServiceImp.GetAll());
    }

    @GetMapping("/{id}")
    @Operation(
            operationId = "Obtene información de un producto",
            description = "Permite obtener información de un producto",
            summary = "Se devuelve la información del producto almacenado en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class)))
    public ResponseEntity<ProductResponse> getById(@PathVariable int id) {
        return  ResponseEntity.ok(productServiceImp.GetById(id));
    }

}
