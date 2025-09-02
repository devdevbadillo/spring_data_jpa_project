package com.david.jpa_project.model.entities.embeddable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RefreshTokenId implements Serializable {
    private Long accessTokenId;

    @Basic(optional = false)
    @Column(name = "jwt_id")
    private String jwtId;
}
