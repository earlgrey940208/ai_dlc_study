package com.tableorder.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_history")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long tableId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String orderData;

    @Column(nullable = false)
    private Integer totalAmount;

    @Column(nullable = false)
    private LocalDateTime completedAt;

    @PrePersist
    protected void onCreate() {
        completedAt = LocalDateTime.now();
    }
}
