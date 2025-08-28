package com.david.jpa_project.model.entity;

import com.david.jpa_project.model.embeddable.AuditData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @Basic(optional = false)
    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "T_PRODUCT_CATEGORIES",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"),
            schema = "demo"
    )
    private List<Category> categories;

    @Embedded
    private AuditData auditData;

    @Basic(optional = false)
    @Column(name = "user_audit")
    private String userAudit;
}