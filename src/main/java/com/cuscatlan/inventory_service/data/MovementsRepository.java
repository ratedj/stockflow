package com.cuscatlan.inventory_service.data;

import com.cuscatlan.inventory_service.models.Movement;
import com.cuscatlan.inventory_service.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MovementsRepository {

    private final MovementsJPARepository movementsJPARepository;

    public List<Movement> findAll() {
        return movementsJPARepository.findAll();
    }

    public Movement findById(int id) {
        return movementsJPARepository.findById(id).get();
    }

    public Movement save(Movement movement) {
        return movementsJPARepository.save(movement);
    }

    public List<Movement> findByProductId(int productId) {
        return movementsJPARepository.findByProductIdOrderByTimestampDesc(productId);
    }
}