package com.david.jpa_project.model.entity;

import com.david.jpa_project.model.enums.JobTitle;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "T_EMPLOYEES", schema = "demo")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @Column(length = 50)
    private String name;

    @Basic(optional = false, fetch = FetchType.LAZY)
    @Column(length = 50, name = "last_name")
    private String lastName;

    @Transient
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    private JobTitle jobTitle;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false, fetch = FetchType.LAZY)
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false, fetch = FetchType.LAZY)
    @Column(name = "upadated_at")
    private Timestamp updatedAt;

    @Basic(optional = false, fetch = FetchType.LAZY)
    @Column(name = "user_audit")
    private String userAudit;
}
