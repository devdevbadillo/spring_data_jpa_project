package com.david.jpa_project.model.entity;

import com.david.jpa_project.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_ORDERS", schema = "demo")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    private OrderStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false, fetch = FetchType.LAZY)
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false, fetch = FetchType.LAZY)
    @Column(name = "upadated_at")
    private Timestamp updatedAt;
}
