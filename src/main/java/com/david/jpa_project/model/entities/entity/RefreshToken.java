package com.david.jpa_project.model.entities.entity;

import com.david.jpa_project.model.entities.mapped.Auditable;
import com.david.jpa_project.model.entities.embeddable.RefreshTokenId;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_REFRESH_ACCESS_TOKEN", schema = "demo")
public class RefreshToken extends Auditable{
    @EmbeddedId
    private RefreshTokenId id;

    @MapsId("accessTokenId")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "access_token_id", nullable = false)
    private AccessToken accessToken;
}
