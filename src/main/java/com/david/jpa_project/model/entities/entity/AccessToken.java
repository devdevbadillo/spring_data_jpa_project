package com.david.jpa_project.model.entities.entity;

import com.david.jpa_project.model.entities.embeddable.AuditData;
import com.david.jpa_project.model.entities.enums.AccessType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_ACCESS_TOKEN", schema = "demo")
public class AccessToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    private String jwtId;

    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    private AccessType accessType;

    @Embedded
    private AuditData auditData;
}
