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
@Table(name = "T_CUSTOMERS", schema = "demo",
        uniqueConstraints = { @UniqueConstraint( columnNames = { "phone_number" } ) }
)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Basic(optional = false)
    @Column(length = 50)
    private String name;

    @Basic(optional = false, fetch = FetchType.LAZY)
    @Column(length = 50, name = "last_name")
    private String lastName;

    @Transient
    private String fullName;

    @Basic(optional = false, fetch = FetchType.LAZY)
    @Column(length = 20, name = "phone_number")
    private String phoneNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false)
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false)
    @Column(name = "upadated_at")
    private Timestamp updatedAt;
}
