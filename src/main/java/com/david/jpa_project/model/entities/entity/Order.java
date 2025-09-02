package com.david.jpa_project.model.entities.entity;

import com.david.jpa_project.model.entities.embeddable.AuditData;
import com.david.jpa_project.model.entities.enums.OrderStatus;
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
@Table(name = "T_ORDERS", schema = "demo")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "T_ORDER_PRODUCTS",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"),
            schema = "demo"
    )
    private List<Product> products;

    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Embedded
    private AuditData auditData;
}
