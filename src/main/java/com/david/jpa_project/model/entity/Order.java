package com.david.jpa_project.model.entity;

import com.david.jpa_project.model.enums.OrderStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "T_ORDERS", schema = "demo")
public class Order {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}
