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
@Table(name = "T_CUSTOMERS", schema = "demo",
        uniqueConstraints = { @UniqueConstraint( columnNames = { "phone_number" } ) }
)
public class Customer extends User{
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Credential credential;

    @Basic(optional = false)
    @Column(length = 20)
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private List<Address> addresses;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private List<Order> orders;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<AccessToken> accessTokens;

    @Embedded
    private AuditData auditData;
}
