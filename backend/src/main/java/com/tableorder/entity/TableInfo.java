package com.tableorder.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "table_info")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TableInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Integer tableNumber;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    @Builder.Default
    private Boolean active = false;
}
