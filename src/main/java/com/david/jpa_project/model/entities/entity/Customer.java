package com.david.jpa_project.model.entities.entity;

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

    @OneToMany(mappedBy = "customer", fetch =  FetchType.LAZY)
    private List<Address> addresses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AccessToken> accessTokens;
}
