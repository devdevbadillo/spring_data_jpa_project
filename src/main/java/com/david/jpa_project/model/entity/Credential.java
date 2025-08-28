package com.david.jpa_project.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

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
    @Column(length = 15)
    private String password;

    @Basic(optional = false)
    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @Basic(optional = false)
    @Column(name = "is_verified")
    private Boolean isVerified;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false, fetch = FetchType.LAZY)
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false, fetch = FetchType.LAZY)
    @Column(name = "upadated_at")
    private Timestamp updatedAt;
}
