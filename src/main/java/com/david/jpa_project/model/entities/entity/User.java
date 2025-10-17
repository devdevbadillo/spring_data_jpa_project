package com.david.jpa_project.model.entities.entity;

import com.david.jpa_project.model.entities.mapped.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_USERS", schema = "demo")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @Column(length = 50)
    private String name;

    @Basic(optional = false)
    @Column(length = 50)
    private String lastName;

    @Transient
    private String fullName;
}
