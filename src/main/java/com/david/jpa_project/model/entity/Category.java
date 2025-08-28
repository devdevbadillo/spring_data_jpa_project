package com.david.jpa_project.model.entity;

import com.david.jpa_project.model.embeddable.AuditData;
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
@Table(name = "T_CATEGORY", schema = "demo", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    @Basic(optional = false)
    private String name;

    @Embedded
    private AuditData auditData;

    @Basic(optional = false)
    @Column(name = "user_audit")
    private String userAudit;
}
