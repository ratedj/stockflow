package com.cuscatlan.inventory_service.services;

import com.cuscatlan.inventory_service.data.MovementsRepository;
import com.cuscatlan.inventory_service.data.ProductsRepository;
import com.cuscatlan.inventory_service.dto.MovementRequest;
import com.cuscatlan.inventory_service.dto.MovementResponse;
import com.cuscatlan.inventory_service.exceptions.InsufficientStockException;
import com.cuscatlan.inventory_service.exceptions.ProductNotFoundException;
import com.cuscatlan.inventory_service.models.Movement;
import com.cuscatlan.inventory_service.models.Product;
import com.cuscatlan.inventory_service.utils.MovementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

    @Service
    @RequiredArgsConstructor
    public class MovementServiceImp {

        private final MovementsRepository movementsRepository;
        private final ProductsRepository productsRepository;
        private final MovementMapper movementMapper;

 @Transactional
        public MovementResponse create(MovementRequest request) {
            Product product = productsRepository.findById(request.productId());
            if (product == null) {
                throw new ProductNotFoundException(request.productId());
            }

            int newStock = switch (request.type()) {
                case IN -> product.getCurrentStock() + request.quantity();
                case OUT -> product.getCurrentStock() - request.quantity();
            };

            if (newStock < 0) {
                throw new InsufficientStockException(
                        product.getId(), product.getCurrentStock(), request.quantity());
            }

            product.setCurrentStock(newStock);
            productsRepository.save(product);

            Movement movement = Movement.builder()
                    .productId(request.productId())
                    .type(request.type())
                    .quantity(request.quantity())
                    .reason(request.reason())
                    .timestamp(LocalDateTime.now())
                    .build();

            Movement saved = movementsRepository.save(movement);
            return movementMapper.toDto(saved);
        }


        public List<MovementResponse> getHistoryByProduct(int productId) {
            Product product = productsRepository.findById(productId);
            if (product == null) {
                throw new ProductNotFoundException(productId);
            }
            return movementMapper.toDtoList(movementsRepository.findByProductId(productId));
        }

        public List<MovementResponse> findAll() {
            return movementMapper.toDtoList(movementsRepository.findAll());
        }
    }