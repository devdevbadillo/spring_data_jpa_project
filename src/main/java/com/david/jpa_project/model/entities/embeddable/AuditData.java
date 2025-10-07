package com.david.jpa_project.model.entities.embeddable;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class AuditData implements Serializable {
    @Basic(optional = false)
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false)
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
