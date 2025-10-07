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
    @Basic(optional = false)
    private String jwtId;

    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    private AccessType accessType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Embedded
    private AuditData auditData;
}
