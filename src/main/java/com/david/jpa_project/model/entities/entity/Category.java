package com.david.jpa_project.model.entities.entity;

import com.david.jpa_project.model.entities.mapped.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "T_CATEGORY", schema = "demo", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Category extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private String userAudit;
}
