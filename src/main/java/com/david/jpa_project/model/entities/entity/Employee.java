package com.david.jpa_project.model.entities.entity;

import com.david.jpa_project.model.entities.embeddable.AuditData;
import com.david.jpa_project.model.entities.enums.JobTitle;
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
@Table(name = "T_EMPLOYEES", schema = "demo")
public class Employee extends User {
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    private JobTitle jobTitle;

    @OneToOne(mappedBy = "user")
    private Credential credential;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<AccessToken> accessTokens;

    @Embedded
    private AuditData auditData;

    @Basic(optional = false)
    private String userAudit;
}
