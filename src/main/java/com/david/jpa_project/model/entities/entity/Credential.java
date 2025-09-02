package com.david.jpa_project.model.entities.entity;

import com.david.jpa_project.model.entities.embeddable.AuditData;
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
@Table(name = "T_CREDENTIALS", schema = "demo")
public class Credential {
    @Id
    private Long id;

    @Basic(optional = false)
    @Column(unique = true, length = 50)
    private String email;

    @Basic(optional = false)
    @Column(length = 40)
    private String password;

    @Basic(optional = false)
    private Boolean isEnabled;

    @Basic(optional = false)
    private Boolean isVerified;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Embedded
    private AuditData auditData;
}
