package com.david.jpa_project.model.entities.entity;

import com.david.jpa_project.model.entities.embeddable.AuditData;
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
@Table(name = "T_ADDRESSES", schema = "demo")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @Column(length = 70)
    private String street;

    @Basic(optional = false)
    @Column(length = 50)
    private String city;

    @Basic(optional = false)
    @Column(length = 50)
    private String state;

    @Basic(optional = false)
    @Column(length = 50)
    private String country;

    @Basic(optional = false)
    @Column(length = 20)
    private String zipCode;

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
    private List<Order> orders;

    @Embedded
    private AuditData auditData;
}
