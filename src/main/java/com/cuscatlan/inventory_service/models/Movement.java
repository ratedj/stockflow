package com.cuscatlan.inventory_service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "movements")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_id", nullable = false)
    private int productId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private MovementType type;

    @Column(nullable = false)
    private Integer quantity;

    @Column(length = 255)
    private String reason;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public enum MovementType {
        IN,
        OUT
    }
}