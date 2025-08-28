package com.david.jpa_project.model.entity;

import com.david.jpa_project.model.embeddable.RefreshTokenId;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_REFRESH_ACCESS_TOKEN", schema = "demo")
public class RefreshToken {
    @EmbeddedId
    private RefreshTokenId id;

    @Basic(optional = false)
    @Column(name = "jwt_id")
    private String jwtId;

    @Basic(optional = false, fetch = FetchType.LAZY)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @Basic(optional = false, fetch = FetchType.LAZY)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
