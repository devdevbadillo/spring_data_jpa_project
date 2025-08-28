package com.david.jpa_project.model.entity;

import com.david.jpa_project.model.embeddable.AuditData;
import com.david.jpa_project.model.enums.AccessType;
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
    @Column(name = "jwt_id")
    private String jwtId;

    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = "access_type")
    private AccessType accessType;

    @Embedded
    private AuditData auditData;
}
