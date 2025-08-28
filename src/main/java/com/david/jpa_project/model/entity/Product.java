package com.david.jpa_project.model.entity;

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
@Table(name = "T_PRODUCTS", schema = "demo")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    private String name;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String description;

    @Basic(optional = false)
    private Double price;

    @Basic(optional = false, fetch = FetchType.LAZY)
    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false, fetch = FetchType.LAZY)
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false, fetch = FetchType.LAZY)
    @Column(name = "upadated_at")
    private Timestamp updatedAt;

    @Basic(optional = false, fetch = FetchType.LAZY)
    @Column(name = "user_audit")
    private String userAudit;
}